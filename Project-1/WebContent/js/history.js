/**
 * configures the historyTable datatable.net
 * @returns
 */
function checkHistoryTable() {
	$(document).ready( function () {
//	if(empTable == null) {
	$('#history-table').DataTable( {
		retrieve: true,
		info: true,
		paging: true,
		"pagingType": "full_numbers",
		searching: true,
		"order": [[ 0, "asc" ]],
		"footerCallback": function ( row, data, start, end, display ) {
				var api = this.api(), data;

				// Remove the formatting to get integer data for summation
				var intVal = function ( i ) {
					return typeof i === 'string' ?
							i.replace(/[\$,]/g, '')*1 :
								typeof i === 'number' ?
										i : 0;
				};

				// Total over all pages
				total = api
				.column( 5 )
				.data()
				.reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );

				// Total over this page
				pageTotal = api
				.column( 5, { page: 'current'} )
				.data()
				.reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				
				$('#history-total').html('<b>Page Total</b>: $' + Number(Math.round(pageTotal+'e'+2)+'e-'+2) + '<br>   <b>Overall Total</b>: $' + Number(Math.round(total+'e'+2)+'e-'+2) );
			},
			initComplete: function () {
				let index = 0;
	            this.api().columns([0,1,2,3,4,7]).every( function () {
	                var column = this;
	                var select = $(`<select id="${index}-select"><option value=""></option></select>`).appendTo( $(column.footer()) )
	                    .on( 'change', function () {
	                        var v = $.fn.dataTable.util.escapeRegex($(this).val());
                        	column.search( v ? '^'+v+'$' : '', true, false ).draw();
	                    });
	                index = index + 1;
	            });
	        }
	    } );
	});
}

/**
 * loads the history list
 * @returns
 */
function loadHistoryList() {
	let xhr = new XMLHttpRequest();
	
	let employeeSDD = [];
	let submitSDD = [];
	let resolveSDD = [];
	let resolverSDD = [];
	let typeSDD = [];
	let statusSDD = [];
	
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
					$('#history-table').DataTable().row.add( [
						myArr[idx].submitter.user_name,
						myArr[idx].submit_date,
						myArr[idx].resolve_date,
						myArr[idx].resolver.user_name,
						myArr[idx].type.name,
				    	myArr[idx].amount,
				    	myArr[idx].detail,
				    	myArr[idx].status.name
				        ] ).draw();
					
					// populate the arrays for the (S)ort (D)rop (D)owns
					employeeSDD.push(myArr[idx].submitter.user_name);
					submitSDD.push(myArr[idx].submit_date);
					resolveSDD.push(myArr[idx].resolve_date);
					resolverSDD.push(myArr[idx].resolver.user_name);
					typeSDD.push(myArr[idx].type.name);
					statusSDD.push(myArr[idx].status.name);
				}
	
				// populate the sort drop downs
				buildSDD('#0-select',unique(employeeSDD));
				buildSDD('#1-select',unique(submitSDD));
				buildSDD('#2-select',unique(resolveSDD));
				buildSDD('#3-select',unique(resolverSDD));
				buildSDD('#4-select',unique(typeSDD));
				buildSDD('#5-select',unique(statusSDD));
				
				break;
			case (403):
				break;
			case (500):
				console.log('server error');
				break;
				
			
			}
		}
	}
    
    xhr.open("GET", 'history');
    xhr.send();	
}

/**
 * dedupes an array
 * @param arr
 * @returns
 */
function unique(arr) {
	let deduped=[];
	let prevAny=null;
	
	arr.sort().forEach(function(any,index,array){
		if((any != prevAny) && (any != "") && (any != null)) {			
			prevAny = any;
			deduped.push(any);
		}
	});
	
	return deduped;
}

/**
 * populates the sort drop down for the tag
 * @param tag
 * @param arr
 * @returns
 */
function buildSDD(tag,arr) {
	arr.forEach(function (any,index,array) {
		$(tag).append( `<option value="${any}">${any}</option>` );
	});
}