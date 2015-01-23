function mainOnClick() {
	document.getElementById("content").src = "description.jsp";
}
function catalogOnClick() {
	document.getElementById("content").src = "searchResult.jsp";
}
function cartOnClick() {
	document.getElementById("content").src = "cart.jsp";
}
function changeStyle() {
	document.getElementById("content").src = "changestyle.html";
}
function applyChanges() {
	var style = window.parent.document.getElementById("style");
	var radio = document.getElementsByName("style");
	for (var i = 0; i < radio.length; i++) {
		if (radio[i].checked) {
			if (radio[i].value == "light")
				style.href = "./css/light.css";
			else if (radio[i].value == "dark")
				style.href = "./css/dark.css";
		}
	}
}

