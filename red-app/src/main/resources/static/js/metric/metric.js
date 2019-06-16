
var map = {}; // You could also use an array


//**********************************************************
//  C A P T U R E    K E Y S    +   M A I N     L O G I C
//**********************************************************
onkeydown = onkeyup = function(e){
    e = e || event; // to deal with IE
    map[e.keyCode] = e.type == 'keydown';

    var session = localStorage.getItem('session');


    // SHIFT + D || START || STOP ||
    if(map[16] && map[68]){
        alert('Shift +  D');

        if(session == "true"){ //THEN STOP REDORDING

            doScreenCapture("finish");

            var elapsed = timeStop();
            alert("elapsed : " + elapsed);

            console.log("STOPPED METRIC");
            localStorage.setItem('session','false');

            //TODO: SEND ALL DATA TO API



        }else if(session == "false" || session == null || session == ""){

            localStorage.setItem('session','true');

            doScreenCapture("start");
            timeStart();


            console.log("STARTED METRIC");
        }

        map = {};
        return false;
    }

    // SHIFT + R || FAILED ||
    if(map[16] && map[82]){
        alert('Shift +  R');

        if(session == "true"){ //THEN STOP REDORDING
            doScreenCapture("finish");

            alert("FAILED");
            localStorage.setItem('session','false');
            //TODO: SEND ALL DATA TO API
        }

        map = {};
        return false;
    }

    // SHIFT + W || ABORTED ||
    if(map[16] && map[87]){
        alert('Shift +  W');

        if(session == "true"){ //THEN STOP REDORDING
            alert("CANCELED");
            localStorage.setItem('session','false');
        }

        map = {};
        return false;
    }
}

//****************************************************
// S E S I O N      D A T A     S T O R A G E
//****************************************************

//The keys and the values are always strings (note that, as with objects, integer keys will be automatically converted to strings).
//https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage

// WHAT WE HAVE FOR NOW IN SESSION STORAGE
// localStorage.getItem('screenWidth');     // Screen width
// localStorage.getItem('screenHeight');    // Screen height
// localStorage.getItem('timeStart');       // Timme of started session
// localStorage.getItem('timeStop');        // Time of stopped session
// localStorage.getItem('session';          // TRUE - metric session is recording || FALSE - metric session if stopped

//*************************
//  R E S O L U T I O N
//*************************

var screenWidth;
var screenHeight;

$(document).ready(function(){

    screenWidth = screen.width;
    screenHeight = screen.height;


    localStorage.setItem('screenWidth', screenWidth.toString() );
    localStorage.setItem('screenHeight', screenHeight.toString() );

});


//******************************
// S C R E E N   C A P T U R E
//******************************

function doScreenCapture(metricpart) {
    console.log("screen cap " + metricpart);

    var screen_api = "http://localhost:6067/api/usabilityData/screencap";

    var jsonObject = {};
    jsonObject.height = parseInt(screenHeight, 10);
    jsonObject.width = parseInt(screenWidth, 10);
    jsonObject.status = metricpart;

    $.ajax({
        type: "POST",
        url: screen_api,
        contentType: "application/json",
        data: JSON.stringify(jsonObject),
        success: (e) => console.log(e.responseText),
        error: (e) => console.error(e.responseText)
    });

}


//**************************************************
// M E A S U R E    C L I C K     D I S T A N C E ?
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

//TODO : CALCULATE DISTANCE FROM METRIC SPECIFICAZTION + ADD DISTANCE TO LOCAL STORAGE.


//***************************************************
//  T I M E     E L A P S E     C O U N T
//***************************************************
function timeStart(){

    var startTimestamp = new Date();
    var temp = startTimestamp/1000;

    localStorage.setItem('timeStart', temp.toString() );
}

function timeStop(){

    var stopTimestamp = new Date();
    var temp = stopTimestamp/1000;

    localStorage.setItem('timeStop', temp.toString() );


    return calculateElapsedTime();
}

function calculateElapsedTime(){

    var startTime = localStorage.getItem('timeStart');
    var stopTime = localStorage.getItem('timeStop');

    var timeDiff = ( parseInt(stopTime) - parseInt(startTime) ) * 1000; // CONVERT TO MS

    return timeDiff;
}

//***************************************************
//  M O U S E       C L I C K S
//***************************************************
