
var map = {}; // You could also use an array


//***********************************
// C A P T U R E    K E Y S
//***********************************
onkeydown = onkeyup = function(e){
    e = e || event; // to deal with IE
    map[e.keyCode] = e.type == 'keydown';
    /* insert conditional here */

    if(map[16] && map[68]){
        alert('Shift +  D');

        doScreenCapture();

        map = {};
        return false;
    }


    if(map[16] && map[82]){
        alert('Shift +  R');
        map = {};
        return false;
    }

    if(map[16] && map[87]){
        alert('Shift +  W');
        map = {};
        return false;
    }

}

//***********************************
// S C R E E N    D A T A
//***********************************

function doScreenCapture() {

    console.log("screen cap");

    var screen_api = "http://localhost:6067/metryka/screen";
    var filename = "screencapture.png";


    $.ajax({
        type: "POST",
        url: screen_api,
        success: (e) => console.log(e.responseText),
        error: (e) => console.error(e.responseText)
});


}


//**************************************************
// M E A S U R E    C L I C K     D I S T A N C E
//**************************************************

var previousMouseX = 0;
var previousMouseY = 0;

document.addEventListener("click", function(){

    var mouse_X = event.clientX;
    var mouse_Y = event.clientY;

    var distance = Math.sqrt( Math.pow( previousMouseX - mouse_X , 2) + Math.pow(previousMouseY-mouse_Y, 2) );

    console.log("Current : X." + mouse_X + " Y." + mouse_Y + " | Previous : X." + previousMouseX + " Y." + previousMouseY + " | Distance : " + distance);

    previousMouseX = mouse_X;
    previousMouseY = mouse_Y;

});

