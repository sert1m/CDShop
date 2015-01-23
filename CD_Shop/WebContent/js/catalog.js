 //Don`t used. It was my first experiments. Left as examples.
function search() {

	var xmlhttp = new XMLHttpRequest();

	var sel = document.getElementById("type");
	var type = "type=" + sel.options[sel.selectedIndex].value;
	
	sel = document.getElementById("genre");
	var genre = "genre=" + sel.options[sel.selectedIndex].value;		
	var name = "name=" + document.getElementById("name").value;

	xmlhttp.open('GET', '/CD_Shop/SearchServlet?' + type + '&' + genre + '&' + name, false);
	xmlhttp.send(null);

	if (xmlhttp.status == 200) {
		response = xmlhttp.responseText;
		document.getElementById("table").innerHTML = response;
		var count = (response.match(/<tr>/g) || []).length;
		if (count <= 1)
			alert("Not found!");
	}
	else 
		alert('Error ' + xmlhttp.status + ': ' + xmlhttp.statusText);

}

function putInCart() {
	 var checkboxes = document.getElementsByName("disksToBuy");
	 var values = [];
	 for (var i = 0; i < checkboxes.length; i++)
		 if (checkboxes[i].checked) {
			 values.push(checkboxes[i].value);		 
		 }
	 
	 if (values.length == 0)
		 return;
//	 if (!supports_html5_storage()) {
//		 alert("Your browser don`t support local storage! :(");
//		 return;
//	 }
//
//	 var inCart = localStorage.getItem("amount");
//	 
//	 if (inCart == null)
//		 inCart = 0;
//	 else 
//		 inCart = parseInt(inCart);
//	 
//	 localStorage.setItem("amount", values.length + inCart);
//	 for (var i = inCart; i < values.length + inCart; i++)
//		 localStorage.setItem(i, values[i - inCart]);
//	 
//	 var amount = getCookie("amount");
//	 if (amount == undefined)
//		 amount = 0;
//	 else 
//		 amount = parseInt(amount);
//	 document.cookie = "amount=" + (amount + values.length);
//	 alert(document.cookie);
//	 for (var i = amount; i < values.length + amount; i++)
//		 document.cookie += ";" + i + "=" + values[i];
//	 document.cookie += "path=/;"
	 alert(document.cookie);
}

//Don`t used. It was my first experiments. Left as examples.
function getCookie(name) {
	  var matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)" ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
}

function supports_html5_storage() {
	try {
		  return 'localStorage' in window && window['localStorage'] !== null;
	  } catch (e) {
		return false;
	}
}