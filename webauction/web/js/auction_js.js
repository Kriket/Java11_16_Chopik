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

function getAuctions() {

    var xmlhttp = getXmlHttp()
    xmlhttp.open('GET', '/controller', true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if(xmlhttp.status == 200) {
                alert(xmlhttp.par);
            }
        }
    };
    xmlhttp.send(null);

	$.get(
		"controller",
		{
			command : "AUCTIONS_UPDATE"
		},
		function (data) {
			var auctionList = document.getElementById("auction-list");

        }
	);
}

function addThing() {
	var thing = $("div.thing:first").clone().appendTo("#thing-container");
    var thingContainer = document.getElementById("thing-container");

    var deleteButtonExample = document.getElementById("delete-example");

    $("div.thing:last textarea").val("");

	var deleteButton = document.createElement("input");
    deleteButton.type = "button";
    deleteButton.className = "delete-email-button";
    deleteButton.value = deleteButtonExample.value;

    deleteButton.onclick = function() {
        thing.remove();
        deleteButton.remove();
    };

    thingContainer.appendChild(deleteButton);
}

function refreshAuctions() {
    $.get(
        "getaucs",
        {
            command : "AUCTIONS_UPDATE"
        },
        function (data) {
            var arr = data.split(",");
            $('#auctions').empty();
            arr.forEach(function (item, i) {

            	if(i!=arr.length-1) {
					var subItem = item.split(" ");
					$('#auctions').append('<option value="'+subItem[0]+'">'+subItem[1]+'</option>');
                }
            })
        }
    );
}

function refreshAuctionsForDeleteLos() {
    $.get(
        "getaucs",
        {
            command : "AUCTIONS_UPDATE_FOR__DELETE_LOTS"
        },
        function (data) {
            var arr = data.split(",");
            $('#auctions').empty();
            arr.forEach(function (item, i) {

                if(i!=arr.length-1) {
                    var subItem = item.split(" ");
                    $('#auctions').append('<option value="'+subItem[0]+'">'+subItem[1]+'</option>');
                }
            })
        }
    );
}

function refreshInfo(page, auctionId){
    $.get(
        "controller",
        {
            command : "REFRESH_AUCTION",
            destinationPage : page,
            auctions : auctionId
        },
        function(data) {
            window.location.replace(page);
        }
    );
}