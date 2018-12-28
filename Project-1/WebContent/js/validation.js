/**
 * function that shows the user error messages (currently uses Bootstrap's Modal)
 * @param code
 * @param messages
 * @returns
 */
function raiseAlert(code,messages) {
	let code_text = "<ul>";
	
	for (let i = 0; i < messages.length; i++) {
		code_text = `${code_text}<li>${messages[i]}</li>`;
	}

	code_text = `${code_text}</ul>`;
		
	//alert(`(${code}) ${code_text}`);
	$('#errorCode').text(code);
	$('#errorText').html(code_text);
	$('#errorModal').modal('show');
}

/**
 * returns the error message if the first name isn't valid
 * @param first_name
 * @returns
 */
function validateFirstName(first_name) {
	// first name is required
	if((first_name == null) || (first_name.trim() == "")) {
		return("First Name is an invalid format.");
	}
	
	return "";
}

/**
 * returns the error message if the last name isn't valid
 * @param last_name
 * @returns
 */
function validateLastName(last_name) {
	// last name is required
	if((last_name == null) || (last_name.trim() == "")) {
		return("Last Name is an invalid format.");
	}
	
	return "";
}

/**
 * returns the error message if the user name isn't valid
 * @param user_name
 * @returns
 */
function validateUserName(user_name) {
	let message = "";
	let invalid = "User Name is an invalid format.";
	// duplicate user names are determined server side

	// User name is required
	if((user_name == null) || (user_name.trim() == "")) {
		message = invalid;
	}

    return message;
}

/**
 * returns the error message if the password isn't valid
 * @param userPass
 * @returns
 */
function validatePassword(userPass) {
	let message = "";
	
	// password is required
	if((userPass == null) || (userPass.trim() == "")) {
		message = "Password is in an invalid format.";
	}
	return message;
}

/**
 * returns the error message if the email isn't valid
 * @param email
 * @returns
 */
function validateEmail(email) {
	let message = "";
	let num = -1;
	
	let invalid = "Email is in an invalid format.  Needs to be in the format of <i>user</i>@<i>domain</i>";
	
	// email is required
	if((email == null) || (email.trim() == "")) {
		message = invalid;
	}
	
	// https://en.wikipedia.org/wiki/Email_address
	//must have an @
	num = email.indexOf("@");
	if (num < 0) {
		message = invalid;
	}
	
	// we found an @ now go see about . something
	if(num >= 0) {
		num = email.indexOf(".",num);
		if (num < 0) {
			message = invalid;
		}
	}
	
	return message;
}

/**
 * returns the error message if the role isn't valid
 * @param role_id
 * @returns
 */
function validateRole(role_id) {
	let message = "";
	// role_id must exist
	if((role_id == null) || (role_id == 0)) {
		message = "Role is required.";
	}
	return message;
}

/**
 * returns the error message if the type isn't valid
 * @param type_id
 * @returns
 */
function validateType(type_id) {
	let message = "";
	// role_id must exist
	if((type_id == null) || (type_id == 0)) {
		message = "You must choose a type.";
	}
	return message;
}

/**
 * returns the error message if the amount isn't valid
 * @param amount
 * @returns
 */
function validateAmount(amount) {
	let message = "";
	
	// must have an amount
	if((amount == null) || (amount <= 0)) {
		message="You must include an amount.";
	}
	
	return message;
}

/**
 * returns the error message if the detail isn't valid
 * @param detail
 * @returns
 */
function validateDetail(detail) {
	let message = "";
	
	// no restrictions on data
	
	return message;
}