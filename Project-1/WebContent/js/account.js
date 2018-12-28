/**
 * configures the account box to not receive input
 * @returns
 */
function setAccountOff() {
	$('#first-name-form').prop("disabled", true);
	$('#last-name-form').prop("disabled", true);
	$('#user-name-form').prop("disabled", true);
	$('#password-form').prop("disabled", true);
	$('#email-form').prop("disabled", true);
	$('#role-dropdown').prop("disabled", true);
	$('#account-change').show();
	$('#employee-submit').hide();
	$('#account-cancel').hide();
}

/**
 * configures the account box to allow user to edit
 * @returns
 */
function setAccountChange() {
	$('#first-name-form').prop("disabled", false);
	$('#last-name-form').prop("disabled", false);
	$('#password-form').prop("disabled", false);
	$('#email-form').prop("disabled", false);
	$('#role-dropdown').prop("disabled", true);
	$('#account-change').hide();
	$('#employee-submit').show();
	$('#account-cancel').show();
}

/**
 * populates the account box with the logged in user's data
 * @returns
 */
function populateAccountData() {
	// if the user is logged in, then the user information is already in session
	// the login servlet takes this into consideration
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){ 
    	if((xhr.readyState == 4) && (xhr.status == 200)) {
    		let json = JSON.parse(xhr.responseText);
    		// determine if we have a valid login
    	    if(json.employee_id == 0) {
    	    	loadLoginView();
    	    }
    	    else {
    	    	populateAccount(json);
    	    	setAccountOff(); 	 
    	    }
    	}
    }
    
    xhr.open("GET", 'login', true);
    xhr.send();	
}

/**
 * submits the account box changes to the servlet for processing
 * @returns
 */
function accountSubmit() {
	let textFirstName = $('#first-name-form').val();
	let textLastName = $('#last-name-form').val();
	let textUserName = $('#user-name-form').val();
	let textPassword = $('#password-form').val();
	let textEmail = $('#email-form').val();
	let textRole = $('#role-dropdown').find(":selected").text();
	let valRole = $('#role-dropdown').val();
	let employee_id = $('#hidden-employee-id').val();

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
	
	if(accountValidation(textFirstName, textLastName, textUserName, textEmail, textPassword)) {
	// populate the user list
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		// define functionality for response
		if (xhr.readyState == 4) {
			// check response status
			switch (xhr.status) {
			case (200):
				let json = JSON.parse(xhr.responseText);
	    		// determine if we have a valid login
	    	    if(json.employee_id == 0) {
	    	    	loadLoginView();
	    	    }
	    	    else {
	    	    	let json = JSON.parse(xhr.responseText);
	    	    	buildHeader(json.first_name, json.last_name)
	    	    	populateAccount(json);
	    	    	setAccountOff();
	    	    }
				break;
			case (403):
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
 * populates the fields in the account box
 * @param json
 * @returns
 */
function populateAccount(json) {
    $('#hidden-employee-id').val(json.employee_id);
	$('#first-name-form').val(json.first_name);
	$('#last-name-form').val(json.last_name);
	$('#user-name-form').val(json.user_name);
	$('#password-form').val(json.password);
	$('#email-form').val(json.email);
	$('#role-dropdown').val(json.role.role_id);
}

/**
 * validates the account box data before submission
 * @param textFirstName
 * @param textLastName
 * @param textUserName
 * @param textEmail
 * @param textPassword
 * @returns
 */
function accountValidation(textFirstName, textLastName, textUserName, textEmail, textPassword) {
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
	
	if(messages.length > 0) {
		isValid = false;
		raiseAlert(403,messages);
	}

	return isValid;
}