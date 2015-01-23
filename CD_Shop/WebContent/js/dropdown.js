// set javascript event attributes for all select items in the current page
function setSelectEvents() {
    var selects = document.getElementsByTagName("select");
    var arrOnfocus = new Array(); 
    var arrOnkeydown = new Array();
    var arrOnkeyup = new Array();
    var arrOnkeypress = new Array(); 

    for (var i = 0; i < selects.length; i++) {
        selects[i].title = i;
        // if there is a pre-existing function, save it
        if(typeof(selects[i].onfocus) == 'function') {
            arrOnfocus[selects[i].title] = selects[i].onfocus;
            selects[i].onfocus = 
              function() { arrOnfocus[this.title](); this.enteredText=''; }
        }
        else {
            selects[i].onfocus = function() { this.enteredText=''; }
        }

        // if there is a pre-existing function, save it
        if(typeof(selects[i].onkeydown) == 'function') {
            arrOnkeydown[selects[i].title] = selects[i].onkeydown;
            selects[i].onkeydown = 
              function() { arrOnkeydown[this.title](); return handleKey(); }
        }
        else {
            selects[i].onkeydown = function() { return handleKey(); }
        }

        // if there is a pre-existing function, save it
        if(typeof(selects[i].onkeyup) == 'function') {
            arrOnkeyup[selects[i].title] = selects[i].onkeyup;
            selects[i].onkeyup = 
              function() { arrOnkeyup[this.title](); event.cancelbubble=true;return false; }
        }
        else {
            selects[i].onkeyup = function() { event.cancelbubble=true;return false; }
        }

        // if there is a pre-existing function, save it
        if(typeof(selects[i].onkeypress) == 'function') {
            arrOnkeypress[selects[i].title] = selects[i].onkeypress;
            selects[i].onkeypress = 
               function() { arrOnkeypress[this.title](); return selectItem(); }
        }
        else { // there is no pre-existing function
            selects[i].onkeypress = function() { return selectItem(); }
        }
    }
}