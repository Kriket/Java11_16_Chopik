function validateForm() {
  var PASSWORD_NOT_EQUAL = "Пароли не совпадают";
  var INVALID_PASSWORD = "Пароль должен содержать хотя бы 1 цифру и не менее 1 буквы в каждом регистре";
  
  var PASSWORD = "password";
  
  var ERR_PASSWORD = "err-password";

  var errPassword =  document.getElementById(ERR_PASSWORD);

  errPassword.innerHTML = "";

  var password = document.forms[0][PASSWORD].value;     
  
  if (!password.match(/[A-Z]/) | !password.match(/[a-z]/) | !password.match(/[\d]/)) {
    errPassword.innerHTML = INVALID_PASSWORD;
    document.forms[0][PASSWORD].value = "";  
    return false;
  }

  return true;
}

function addEmail() {
	
	var emailP = document.getElementById("email-p");
	var addEmailButton = document.getElementById("add-email-button");
	var addEmailNum = addEmailButton.getAttribute("data-add-email-num");
  
  	var addedEmail = document.createElement("input");

		  addedEmail.type = "text";
		  addedEmail.name = "email-" + addEmailNum;
		  addedEmail.id = "add-email-" + addEmailNum;
		  addedEmail.title = "Обязательные символы: '@' и '.'";
		  addedEmail.pattern = "[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\.[a-z]{2,3}$";
		  addedEmail.required = true;
  
  	var deleteAddedEmailButton = document.createElement("input");

		  deleteAddedEmailButton.type = "button";
		  deleteAddedEmailButton.className = "delete-email-button";
		  deleteAddedEmailButton.id = "delete-email-button-" + addEmailNum;
		  deleteAddedEmailButton.value = "Delete email";

		  /*deleteAddedEmailButton.style.margin = "16px";*/

		  deleteAddedEmailButton.onclick = function() { 
		    emailP.removeChild(addedEmail);
		    emailP.removeChild(deleteAddedEmailButton);
		    emailP.removeChild(br);
		    emailP.removeChild(br1);
		  }; 

	var br = document.createElement("br");
	var br1 = document.createElement("br");

	emailP.insertBefore(addedEmail, addEmailButton);	
	emailP.insertBefore(br, addedEmail);
	emailP.insertBefore(deleteAddedEmailButton, br);  	
	emailP.insertBefore(br1, addEmailButton);	
	
	
	var nextAddEmailNum = 1 + Number(addEmailNum);
	addEmailButton.setAttribute("data-add-email-num", nextAddEmailNum);

}

function setLanguage(page, local){		
	$.get(
		    "controller",
		    {
		    	 command : "CHANGE_LOCALIZATION",			    
			     destinationPage : page,
			     local : local			    
		    },
		    function(data) {
		       window.location.replace(page);
		    }
		);
}