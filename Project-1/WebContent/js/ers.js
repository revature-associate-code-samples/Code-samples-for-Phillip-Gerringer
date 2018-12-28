/**
 * after the window loads...do this
 */
window.onload = function() {
	$('#logoutBtn').on('click', doLogout);
	$('#adminNav').on('click', loadAdminView);
	$('#managerNav').on('click', loadManagerView);
	$('#employeeNav').on('click', loadEmployeeView);
	$('#accountNav').on('click', loadAccountView);
	$('#historyNav').on('click', loadHistoryView);
	$('#brand').on('click',refreshCount());

	pullRoleData();
	pullTypeData();
	pullStatusData();
	checkLogin();
}

/**
 * checks to see if a login exists, if it does, use it
 * @returns
 */
function checkLogin() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		let json = JSON.parse(xhr.responseText);
    		
    		// determine if we have a valid login
    	    if(json.employee_id == 0) {
    	    	loadLoginView();
    	    }
    	    else {
    	    	loadUser(json.first_name, json.last_name, json.role.name);
    	    }
    	}
    }
    
    xhr.open("GET", 'login', true);
    xhr.send();	
}

/**
 * loads the login view
 * @returns
 */
function loadLoginView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		$('#logo').hide();
    	}
    }
    
    xhr.open("GET", 'login.view', true);
    xhr.send();
}

/**
 * loads the admin view
 * @returns
 */
function loadAdminView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		refreshCount();
    		populateRoleDropDown();
    		loadEmployeeList();
    		populateEmployeeOff();
    	}
    }
    
    xhr.open("GET", 'admin.view', true);
    xhr.send();
}

/**
 * loads the approval view
 * @returns
 */
function loadManagerView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		$(document).ready( function () {
    			refreshCount();
    			loadReimbManageList();
    			populateManageOff();
    			populateTypeDropDown('type-dropdown');
    			populateStatusDropDown('status-dropdown');
    			
    		} );
    	}
    }
    
    xhr.open("GET", 'manager.view', true);
    xhr.send();
}

/**
 * loads the request view
 * @returns
 */
function loadEmployeeView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		refreshCount();
    		loadReimbursementList();
    		populateTypeDropDown('type-dropdown');
    		populateRequestOff();
    	}
    }
    
    xhr.open("GET", 'employee.view', true);
    xhr.send();
}

/**
 * loads the your profile view
 * @returns
 */
function loadAccountView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		refreshCount();
    		populateRoleDropDown();
    		setAccountOff();
      	}
    }
    
    xhr.open("GET", 'account.view', true);
    xhr.send();
}

/**
 * loads the history view
 * @returns
 */
function loadHistoryView() {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		$('#view').html(xhr.responseText);
    		loadHistoryList();
    		refreshCount();
      	}
    }
    
    xhr.open("GET", 'history.view', true);
    xhr.send();
}

/**
 * attempts to match the user name and password with a record in the db
 * @returns
 */
function doLogin() {
	let userNameInput = $('#userNameInput').val();
	let userPasswordInput = $('#passwordInput').val();
	let json = null;
	let messages = [];
	
	// empty the password field
	$('#passwordInput').val("");
	
	let loginObj = {
			"username": userNameInput,
            "password": userPasswordInput
    };
	
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
    	if(xhr.readyState == 4) {
    		switch (xhr.status) {
    		case 200:
    			json = JSON.parse(xhr.responseText);
        		user_id = json.employee_id;
        		
        		loadUser(json.first_name, json.last_name, json.role.name);
        		break;
    		case 401:
    			json = JSON.parse(xhr.responseText);
    			messages.push(json.text);
    			raiseAlert(json.code,messages);
    			break;
    		case 404:
    			json = JSON.parse(xhr.responseText);
    			messages.push(json.text);
    			raiseAlert(json.code,messages);
    			break;
    		}
    	}
    }
    
    xhr.open("POST", 'login');
    xhr.setRequestHeader("Content-type", "application/json");
    let toSend = JSON.stringify(loginObj);
    
    xhr.send(toSend);
}

/**
 * clears the session and sets up the header and view for a login.  functionality moved to the ViewServlet
 * @returns
 */
function doLogout() {
// !!!!  Will not be sending a request to the logoutServlet because it
// did not work 100% of the time.  Now, the act of loading the login
// view will empty the session attribute
//	let xhrLogout = new XMLHttpRequest();
//	xhrLogout.onreadystatechange = function(){ 
//    	if(xhrLogout.readyState == 4) {
//    		if(xhrLogout.status == 200) {
//    			console.log("doLogout 200");
    			$('#adminNav').hide();
    			$('#managerNav').hide();
    			$('#employeeNav').hide();
    			$('#accountNav').hide();
    			$('#historyNav').hide();
    			$('#WelcomeText').html("");	
    			$('#logoutBtn').hide();
    			$('#logo').hide();
    			loadLoginView();
//    		}
//    	}
//    }
//    
//	xhrLogout.open("GET", 'logout');
//	xhrLogout.send();
}

/**
 * based on the user roll, loads the appropriate views
 * @param first_name
 * @param last_name
 * @param role_name
 * @returns
 */
function loadUser(first_name, last_name, role_name) {
	if(role_name == "Administrator") {
		buildHeader(first_name, last_name);
		$('#adminNav').show();
		$('#managerNav').show();
		$('#historyNav').show();
		$('#employeeNav').show();
		$('#accountNav').show();
		loadAdminView();
    }
	else if(role_name == "Manager") {
		buildHeader(first_name, last_name);
		$('#adminNav').hide();
		$('#managerNav').show();
		$('#historyNav').show();
		$('#employeeNav').show();
		$('#accountNav').show();
		loadManagerView();
	}
	else if(role_name == "Employee") {
		buildHeader(first_name, last_name);
		$('#adminNav').hide();
		$('#managerNav').hide();
		$('#historyNav').hide();
		$('#employeeNav').show();
		$('#accountNav').show();
		loadEmployeeView();
    }
}

/**
 * populates the upper right section of the nav bar
 * @param first_name
 * @param last_name
 * @returns
 */
function buildHeader(first_name, last_name) {	
	$('#WelcomeText').html(`Welcome, ${first_name} ${last_name}` );	
	$('#logoutBtn').show();
	$('#logo').show();
}

/**
 * pulls role data to be used throughout the session
 */
var roles = [];
function pullRoleData() {
	if (roles.length == 0) {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			// define functionality for response
			if (xhr.readyState == 4) {
				// check response status
				switch (xhr.status) {
				case (200):
					roles = JSON.parse(xhr.responseText);
					break;
				case (500):
					console.log('server error');
					break;
				}
//			populateRoleDropDown();
			}
		}

		xhr.open("GET", 'roles');
		xhr.send();// send our object as a JSON string to server
	}
	
	return roles;
}

/**
 * pulls type data to be used throughout the session
 */
var types = [];
function pullTypeData() {
	if (types.length == 0) {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			// define functionality for response
			if (xhr.readyState == 4) {
				// check response status
				switch (xhr.status) {
				case (200):
					types = JSON.parse(xhr.responseText);
					break;
				case (500):
					console.log('server error');
					break;
				}
			}
		}

		xhr.open("GET", 'type');
		xhr.send();// send our object as a JSON string to server
	}
	
	return types;
}

/**
 * pulls status data to be used throughout the session
 */
var statuses = [];
function pullStatusData() {
	if (statuses.length == 0) {
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			// define functionality for response
			if (xhr.readyState == 4) {
				// check response status
				switch (xhr.status) {
				case (200):
					statuses = JSON.parse(xhr.responseText);
					break;
				case (500):
					console.log('server error');
					break;
				}
			}
		}

		xhr.open("GET", 'status');
		xhr.send();// send our object as a JSON string to server
	}
	
	return statuses;
}

/**
 * populate the role drop down 
 * @returns
 */
function populateRoleDropDown() {
	// populate the option lists if they don't exist
	let roleDropdown = document.getElementById('role-dropdown');	
	if(roleDropdown.length == 0) {
		rolesData = pullRoleData();
		
		for (let i=0; i<=rolesData.length-1; i++) {
			let option = document.createElement("option");
			option.text = rolesData[i].name;
			option.value = rolesData[i].role_id;
			roleDropdown.appendChild(option);
		}
	}
}

/**
 * populates the status drop down
 * @param statusDropDownElement
 * @returns
 */
function populateStatusDropDown(statusDropDownElement) {
	// populate the option lists if they don't exist
	let statusDropdown = document.getElementById(statusDropDownElement);	
	if(statusDropdown.length == 0) {
		let statusData = pullStatusData();
		
		for (let i=0; i<=statusData.length-1; i++) {
			let option = document.createElement("option");
			option.text = statusData[i].name;
			option.value = statusData[i].status_id;
			statusDropdown.appendChild(option);
		}
	}
}

/**
 * loads the employee list for the admin
 * @returns
 */
function loadEmployeeList() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				let myArr = JSON.parse(xhr.responseText);

				for ( let idx in myArr) {
					// to add to the DataTable's framework
					checkEmpTable();
					empTable.row.add( [myArr[idx].employee_id,
						myArr[idx].last_name,
						myArr[idx].first_name,
				    	myArr[idx].user_name,
				    	myArr[idx].password,
				    	myArr[idx].email,
				    	myArr[idx].role.name, 
				    	myArr[idx].role.role_id
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
    
    xhr.open("GET", 'employee');
    xhr.send();	
}

/**
 * takes the row the user clicked on and populates the employee box and gets it ready to edit
 * @param data
 * @param idx
 * @returns
 */
function populateEmployee(data, idx) {
    $('#hidden-employee-id').val(data[0]);
    $('#hidden-employee-idx').val(idx);
	$('#first-name-form').val(data[2]);
	$('#last-name-form').val(data[1]);
	$('#user-name-form').val(data[3]);
	$('#password-form').val(data[4]);
	$('#email-form').val(data[5]);
	$('#role-dropdown').val(data[7]);
	
	$('#first-name-form').prop("disabled", false);
	$('#last-name-form').prop("disabled", false);
	$('#user-name-form').prop("disabled", false);
	$('#password-form').prop("disabled", false);
	$('#email-form').prop("disabled", false);
	$('#role-dropdown').prop("disabled", false);
	$('#employee-new').hide();
	$('#employee-submit').show();
	$('#employee-cancel').show();
}

/**
 * turns off all editing of the employee box
 * @returns
 */
function populateEmployeeOff() {
	$('#hidden-employee-id').val(0);
	$('#hidden-employee-idx').val(0);
	$('#first-name-form').val("");
	$('#last-name-form').val("");
	$('#user-name-form').val("");
	$('#password-form').val("");
	$('#email-form').val("");
	$('#role-dropdown').val("");
	
	$('#first-name-form').prop("disabled", true);
	$('#last-name-form').prop("disabled", true);
	$('#user-name-form').prop("disabled", true);
	$('#password-form').prop("disabled", true);
	$('#email-form').prop("disabled", true);
	$('#role-dropdown').prop("disabled", true);
	$('#employee-new').show();
	$('#employee-submit').hide();
	$('#employee-cancel').hide();
}

/**
 * sets up the employee box to take new input
 * @returns
 */
function populateEmployeeNew() {
	$('#hidden-employee-id').val(0);
	$('#hidden-employee-idx').val(0);
	$('#first-name-form').val("");
	$('#last-name-form').val("");
	$('#user-name-form').val("");
	$('#password-form').val("");
	$('#email-form').val("");
	$('#role-dropdown').val("");
	
	$('#first-name-form').prop("disabled", false);
	$('#last-name-form').prop("disabled", false);
	$('#user-name-form').prop("disabled", false);
	$('#password-form').prop("disabled", false);
	$('#email-form').prop("disabled", false);
	$('#role-dropdown').prop("disabled", false);
	$('#employee-new').hide();
	$('#employee-submit').show();
	$('#employee-cancel').show();
}

/**
 * executes after the user hits submit.  sends the data to the servlet for processing
 * @returns
 */
function employeeSubmit() {
	let textFirstName = $('#first-name-form').val();
	let textLastName = $('#last-name-form').val();
	let textUserName = $('#user-name-form').val();
	let textPassword = $('#password-form').val();
	let textEmail = $('#email-form').val();
	let textRole = $('#role-dropdown').find(":selected").text();
	let valRole = $('#role-dropdown').val();
	let employee_id = $('#hidden-employee-id').val();
	let employee_idx = $('#hidden-employee-idx').val();
	let json = "";
	
	// build the data from the input
	let dataObj = {
		"employee_id" : employee_id,
		"first_name" : textFirstName,
		"last_name" : textLastName,
		"user_name" :  textUserName,
		"password" : textPassword,
		"email" : textEmail,
		"role" : {"role_id" : valRole, "name" : textRole}
		};
	if(employeeValidation(textFirstName, textLastName, textUserName, textEmail, textPassword, valRole)) {
	// populate the user list
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				json = JSON.parse(xhr.responseText);
		
				// look for the duplicate row and remove it before adding
				// matching on employee_id
				empTable.row(employee_idx).remove().draw();
				// we were successful so add it to the list
				// to add to the the DataTable's framework
				empTable.row.add( [json.employee_id,
					json.last_name,
					json.first_name,
					json.user_name,
					json.password,
					json.email,
					json.role.name, 
					json.role.role_id
			        ] ).draw();

				// clean up the entry box
				populateEmployeeOff();
				break;
			case (412):
				json = JSON.parse(xhr.responseText);
				let messages = [];
				messages.push(json.text);
				raiseAlert(json.code, messages);
				break;
			case (500):
				alert("(500) There was a server error.");
        		break;
			}
		}
	}
    
    xhr.open("PUT", 'employee');
    xhr.setRequestHeader("Content-type", "application/json");
    let toSend = JSON.stringify(dataObj);
    
    xhr.send(toSend);	
	}
}

/**
 * configures the empTable datatable.net
 * @returns
 */
function checkEmpTable() {
//	if(empTable == null) {
		empTable = $('#employees-table').DataTable( {
	        retrieve: true,
	        info: true,
	        paging: true,
	        "pagingType": "full_numbers",
	        searching: true,
	        "order": [[ 1, "asc" ]],
	        "columnDefs": [
			    { "visible": false, "targets": 0 },
			    { "visible": false, "targets": 7 }
			  ]
	    } );
	    
	    $('#employees-table tbody').on('click', 'tr', function () {
	        var data = empTable.row( this ).data();
	        var idx = empTable.row( this ).index();
	        populateEmployee(data, idx);
	    } );
//	}
}

/**
 * populate the Type dropdown
 * @param dropDownName
 * @returns
 */
function populateTypeDropDown(dropDownName) {
	// populate the option lists if they don't exist
	let typeDropdown = document.getElementById(dropDownName);	
	if(typeDropdown.length == 0) {
		typeData = pullTypeData();
		for (let i=0; i<=typeData.length-1; i++) {
			let option = document.createElement("option");
			option.text = typeData[i].name;
			option.value = typeData[i].type_id;
			typeDropdown.appendChild(option);
		}
	}
}

/**
 * refreshes the count indicator for requests and approvals 
 * @returns
 */
function refreshCount() {
	setCount('manager');
	setCount('employee');
}

/**
 * retrieves the counts for the intended source
 * @param source
 * @returns
 */
function setCount(source) {
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
			let json = JSON.parse(xhr.responseText);
			
			if(json.count > 0) {
				$(`#${source}-count`).text(json.count);
			}
			else {
				$(`#${source}-count`).text("");
			}
    	}
    }
    
    xhr.open("GET", source + ".count", true);
    xhr.send();
}

/**
 * processes data validation for the passed in params
 * @param textFirstName
 * @param textLastName
 * @param textUserName
 * @param textEmail
 * @param textPassword
 * @param role_id
 * @returns
 */
function employeeValidation(textFirstName, textLastName, textUserName, textEmail, textPassword, role_id) {
	let messages = [];
	let isValid = true;
	let feedback = "";
	
	// validate the firstname
	feedback = validateFirstName(textFirstName);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}

	// validate the lastname
	feedback = validateLastName(textLastName);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}

	// validate the username
	feedback = validateUserName(textUserName);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}
	
	// validate the email
	feedback = validateEmail(textEmail);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}

	// validate the password
	feedback = validatePassword(textPassword);
	if (feedback.trim().length > 0) {
		messages.push(feedback);
		feedback = "";
	}

	// validate the Role
	feedback = validateRole(role_id);
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