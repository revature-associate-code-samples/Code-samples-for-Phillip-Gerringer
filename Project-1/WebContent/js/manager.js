/**
 * loads the Submitted reimbursement list
 */
var manageRowCount = 0;
function loadReimbManageList() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				manageRowCount=0;
				// for some reason you have to take out the []
				let myArr = JSON.parse(xhr.responseText);
				for ( let idx in myArr) {
					// to add to the DataTable's framework
					checkManageTable();
					manageTable.row.add( [myArr[idx].reimbursement_id,
						myArr[idx].submit_date,
						myArr[idx].submitter.user_name,
				    	myArr[idx].type.name,
				    	myArr[idx].amount,
				    	myArr[idx].detail
				        ] ).draw();
					
					manageRowCount++;
				}
				break;
			case (403):
				break;
			case (500):
				console.log('server error');
				break;
			}
		}
	}
    
    xhr.open("GET", 'manage');
    xhr.send();	
}

/**
 * populates the reimbursement manage box 
 * @param data
 * @param idx
 * @returns
 */
function populateReimbManage(data,idx) {
	if(data != undefined) {
		let reimb_id = data[0];
		
		if(reimb_id > 0) {

			let reimbObj = {
					"id": reimb_id
		    };

			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				// define functionality for response
				if (xhr.readyState == 4) {
					// check response status
					switch (xhr.status) {
					case (200):
						let json = JSON.parse(xhr.responseText);
					
						$('#hidden-reimbursement-idx').val(idx);
						$('#hidden-reimbursement-id').val(json.reimbursement_id);
						$('#detail-form').val(json.detail);
					    $('#reimb-amount-form').val(json.amount);
					    $('#type-dropdown').val(json.type.type_id);
						$('#status-dropdown').val(json.status.status_id);
						
						populateManageOn();
					    configReimbursement(json);

					    // turn off un-editable fields
					    $('#detail-form').prop("disabled", true);
						$('#reimb-amount-form').prop("disabled", true);
						$('#type-dropdown').prop("disabled", true);
						
						break;
					case (403):
						break;
					case (500):
						console.log('server error');
						break;
					}
				}
			}
		    
		    xhr.open("POST", 'reimbursement');
		    xhr.setRequestHeader("Content-type", "application/json");
		    let toSend = JSON.stringify(reimbObj);
		    xhr.send(toSend);
		}
		}
}

/**
 * check to see if the manage table has been datatabled, if not, do it
 * @returns
 */
function checkManageTable() {
//	if(manageTable == null) {
		manageTable = $('#manage-table').DataTable( {
	    	retrieve: true,
	        info: true,
	        paging: true,
	        "pagingType": "full_numbers",
	        searching: true,
	        "order": [[ 1, "desc" ]],
	        "columnDefs": [
			    { "visible": false, "targets": 0 },
			    { type: 'currency', targets: 4 },
			  ]
	    } );
	    
	    $('#manage-table tbody').on('click', 'tr', function () {
	        var data = manageTable.row( this ).data();
	        let idx = manageTable.row( this ).index();
	        populateReimbManage(data,idx);
	    } );
//	}
}

/**
 * sets up the manage box to not receive input
 * @returns
 */
function populateManageOff() {
	$('#hidden-reimbursement-id').val(0);
	$('#hidden-reimbursement-idx').val(0);
    $('#detail-form').val("");
    $('#reimb-amount-form').val(null);
    $('#type-dropdown').val(null);
    $('#status-dropdown').val(null);
    $('#status_info').html("");
	
	$('#detail-form').prop("disabled", true);
	$('#reimb-amount-form').prop("disabled", true);
	$('#type-dropdown').prop("disabled", true);
	$('#status-dropdown').prop("disabled", true);
	$('#manage-submit').hide();
	$('#manage-cancel').hide();
}

/**
 * sets up the manage box for edit
 * @returns
 */
function populateManageOn() {
	$('#detail-form').prop("disabled", true);
	$('#reimb-amount-form').prop("disabled", true);
	$('#type-dropdown').prop("disabled", true);
	
	$('#status-dropdown').prop("disabled", false);
	$('#manage-submit').show();
	$('#manage-cancel').show();
}

/**
 * issues a PUT with the data in the manage box
 * @returns
 */
function manageSubmit() {
	let reimbursement_idx = $('#hidden-reimbursement-idx').val();
	let reimbursement_id = $('#hidden-reimbursement-id').val();
	let status_name = $('#status-dropdown').find(":selected").text();
	let status_id = $('#status-dropdown').val();
	let detail = $('#detail-form').val();
	let type_name = $('#type-dropdown').find(":selected").text();
	let type_id = $('#type-dropdown').val();
	let amount = $('#reimb-amount-form').val();
	
	// build the data from the input
	let dataObj = {
		"reimbursement_id":reimbursement_id,
		"amount":amount,
		"submit_date":null,
		"resolve_date":null,
		"detail":detail,
		"submitter":{"employee_id":0,"first_name":"","last_name":"","user_name":"","password":"","email":"","role":{"role_id":0,"name":""}},
		"resolver":{"employee_id":0,"first_name":"","last_name":"","user_name":"","password":"","email":"","role":{"role_id":0,"name":""}},
		"status":{"status_id":status_id,"name":status_name},
		"type":{"type_id":type_id,"name":type_name}
		};

	// initiate the save
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				// remove the processed row from the table
				manageTable.row(reimbursement_idx).remove().draw();
			
				// clean up the entry box
				populateManageOff();
				refreshCount();
				break;
			case (403):
				break;
			case (500):
				alert("(500) There was a server error.");
        		break;
			}
			refreshCount();
		}
	}
    
    xhr.open("PUT", 'manage');
    xhr.setRequestHeader("Content-type", "application/json");
    let toSend = JSON.stringify(dataObj);
    
    xhr.send(toSend);	
}
