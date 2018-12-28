/**
 * loads the reimbursement list for the employee's reimbursements
 * @returns
 */
function loadReimbursementList() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				// for some reason you have to take out the []
				let myArr = JSON.parse(xhr.responseText);
				for ( let idx in myArr) {
					// to add to the DataTable's framework
					checkReimbTable();
					reimbTable.row.add( [myArr[idx].reimbursement_id,
						myArr[idx].submit_date,
						myArr[idx].type.name,
				    	myArr[idx].amount,
				    	myArr[idx].detail,
				    	myArr[idx].status.name
				        ] ).draw();
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
    
    xhr.open("GET", 'reimbursement');
    xhr.send();	
}

/**
 * populates the reimbursment box based on the row selected by the user
 * @param data
 * @param idx
 * @returns
 */
function populateReimbursement(data, idx) {
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

					$('#hidden-reimbursement-id').val(json.reimbursement_id);
					$('#hidden-reimbursement-idx').val(idx);
					$('#hidden-status-id').val(json.status.status_id);
				    $('#detail-form').val(json.detail);
				    $('#reimb-amount-form').val(json.amount);
				    $('#type-dropdown').val(json.type.type_id);
				    
				    configReimbursement(json);
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
 * sets the fields on the reimbursement box based on status
 * @param json
 * @returns
 */
function configReimbursement(json) {
	let status_info = "This request was last ";
	
	switch (json.status.name) {
	case "Submitted" :
		status_info = "This request was last <span class=\"submitted\">" + json.status.name + "</span> " +
			"on <b>" + json.submit_date + "</b> by <b>" + json.submitter.user_name + "</b>. <hr>";
		
		// user can edit
		$('#detail-form').prop("disabled", false);
		$('#reimb-amount-form').prop("disabled", false);
		$('#type-dropdown').prop("disabled", false);
		$('#request-new').hide();
		$('#request-submit').show();
		$('#request-cancel').show();
		break;
	case "Approved" :
		status_info = "This request was last <span class=\"approved\">" + json.status.name + "</span> " +
		"on <b>" + json.resolve_date + "</b> by <b>" + json.resolver.user_name + "</b>.";
		
		// user can NOT edit
		$('#detail-form').prop("disabled", true);
		$('#reimb-amount-form').prop("disabled", true);
		$('#type-dropdown').prop("disabled", true);
		$('#request-new').show();
		$('#request-submit').hide();
		$('#request-cancel').hide();
		break;
	case "Denied" :
		status_info = "This request was last <span class=\"declined\">" + json.status.name + "</span> " +
		"on <b>" + json.resolve_date + "</b> by <b>" + json.resolver.user_name + "</b>.";

		// user can NOT edit
		$('#detail-form').prop("disabled", true);
		$('#reimb-amount-form').prop("disabled", true);
		$('#type-dropdown').prop("disabled", true);
		$('#request-new').show();
		$('#request-submit').hide();
		$('#request-cancel').hide();
		break;
	}
	
	$('#status_info').html(status_info);
}

/**
 * configures the reimbursement table - datatables.net
 * @returns
 */
function checkReimbTable() {
//	if(reimbTable == null) {
		reimbTable = $('#reimb-table').DataTable( {
			retrieve: true,
	        info: true,
	        paging: true,
	        "pagingType": "full_numbers",
	        searching: true,
	        "order": [[ 1, "desc" ]],
	        "columnDefs": [
			    { "visible": false, "targets": 0 },
			    { type: 'currency', targets: 3 },
			  ]
		} );
		
		$('#reimb-table tbody').on('click', 'tr', function () {
	        var data = reimbTable.row( this ).data();
	        let idx = reimbTable.row( this ).index();
	        populateReimbursement(data, idx);
	    } );
//	}
}

/**
 * sets up the reimbursement box to receive a new entry
 * @returns
 */
function populateRequestNew() {
	$('#hidden-reimbursement-id').val(0);
	$('#hidden-reimbursement-idx').val(-1);
	$('#hidden-status-id').val(0);
    $('#detail-form').val("");
    $('#reimb-amount-form').val(null);
    $('#type-dropdown').val(null);
    $('#status_info').html("");
	
	$('#detail-form').prop("disabled", false);
	$('#reimb-amount-form').prop("disabled", false);
	$('#type-dropdown').prop("disabled", false);
	$('#request-new').hide();
	$('#request-submit').show();
	$('#request-cancel').show();
}

/**
 * sets up the reinbursement box to not receive input
 * @returns
 */
function populateRequestOff() {
	$('#hidden-reimbursement-id').val(0);
	$('#hidden-reimbursement-idx').val(-1);
	$('#hidden-status-id').val(0);
    $('#detail-form').val("");
    $('#reimb-amount-form').val(null);
    $('#type-dropdown').val(null);
    $('#status_info').html("");
	
	$('#detail-form').prop("disabled", true);
	$('#reimb-amount-form').prop("disabled", true);
	$('#type-dropdown').prop("disabled", true);
	$('#request-new').show();
	$('#request-submit').hide();
	$('#request-cancel').hide();
}

/**
 * submits the reimbursement box data to the servlet to be processed
 * @returns
 */
function RequestSubmit() {
	let reimbursement_id = $('#hidden-reimbursement-id').val();
	let reimbursement_idx = $('#hidden-reimbursement-idx').val();
	let status_id = $('#hidden-status-id').val();
	let detail = $('#detail-form').val();
	let type_name = $('#type-dropdown').find(":selected").text();
	let type_id = $('#type-dropdown').val();
	let amount = $('#reimb-amount-form').val();
	
	if(reimbValidate(detail, type_id, amount)) {
	// build the data from the input
	let dataObj = {
		"reimbursement_id":reimbursement_id,
		"amount":amount,
		"submit_date":null,
		"resolve_date":null,
		"detail":detail,
		"submitter":{"employee_id":0,"first_name":"","last_name":"","user_name":"","password":"","email":"","role":{"role_id":0,"name":""}},
		"resolver":{"employee_id":0,"first_name":"","last_name":"","user_name":"","password":"","email":"","role":{"role_id":0,"name":""}},
		"status":{"status_id":status_id,"name":""},
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
				let json = JSON.parse(xhr.responseText);
			
				// look for the duplicate row and remove it before adding
				// matching on employee_id  (0 is the first row)
				if(reimbursement_id > 0) {
					reimbTable.row(reimbursement_idx).remove().draw();
				}

				// we were successful so add it to the list
				// to add to the the DataTable's framework
				reimbTable.row.add( [json.reimbursement_id,
					json.submit_date,
					json.type.name,
					json.amount,
					json.detail,
					json.status.name
			        ] ).draw();

				// clean up the entry box
				populateRequestOff();
				refreshCount();
				break;
			case (403):
				break;
			case (500):
				alert("(500) There was a server error.");
        		break;
			}
		}
	}
    
    xhr.open("PUT", 'reimbursement');
    xhr.setRequestHeader("Content-type", "application/json");
    let toSend = JSON.stringify(dataObj);
    
    xhr.send(toSend);	
	}
}

/**
 * validates the data before submission
 * @param detail
 * @param type_id
 * @param amount
 * @returns
 */
function reimbValidate(detail, type_id, amount) {
	let messages = [];
	let isValid = true;
	let feedback = "";
	
	// validate the detail
	feedback = validateDetail(detail);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}
	
	// validate the type
	feedback = validateType(type_id);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}
	
	// validate the type
	feedback = validateAmount(amount);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}

	if(messages.length > 0) {
		isValid = false;
		raiseAlert(403,messages);
	}
	
	return isValid;
}