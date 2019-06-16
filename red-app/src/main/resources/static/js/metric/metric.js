
var map = {}; // You could also use an array
const USABILITY_DATA_URL = '/api/usabilityData';

//**********************************************************
//  C A P T U R E    K E Y S    +   M A I N     L O G I C
//**********************************************************
onkeydown = onkeyup = function(e){
    e = e || event; // to deal with IE
    map[e.keyCode] = e.type == 'keydown';

    var session = localStorage.getItem('session');


    // SHIFT + D || START || STOP ||
    if(map[16] && map[68]){
        //alert('Shift +  D');

        if(session == "true"){ //THEN STOP REDORDING
            console.log("DONE");
            doScreenCapture("finish");

            localStorage.setItem('session','false');

            $.get('https://api.ipify.org', (ip) => {
                persistUsabilityData(ip, 0, 0);
                resetLocalStorage();
            });
        }else if(session == "false" || session == null || session == ""){

            localStorage.setItem('session','true');
            saveScreenResolution();
            doScreenCapture("start");
            timeStart();


            console.log("STARTED METRIC");
        }

        map = {};
        return false;
    }

    // SHIFT + R || FAILED ||
    if(map[16] && map[82]){
        //alert('Shift +  R');

        if(session == "true"){ //THEN STOP REDORDING
            doScreenCapture("finish");

            alert("FAILED");
            localStorage.setItem('session','false');

            $.get('https://api.ipify.org', (ip) => {
                persistUsabilityData(ip, 1, 1);
                resetLocalStorage();
            });
        }

        map = {};
        return false;
    }

    // SHIFT + W || ABORTED ||
    if(map[16] && map[87]){
        //alert('Shift +  W');

        if(session == "true"){ //THEN STOP REDORDING
            alert("CANCELED");
            localStorage.setItem('session','false');

            resetLocalStorage();
        }

        map = {};
        return false;
    }
}

function persistUsabilityData(ip, failed, errorType) {
    var elapsed = timeStop();
    var sumclicks = getMouseClicks();
    var distance = getTotalDistance();
    var height = parseInt(localStorage.getItem('screenHeight'));
    var width = parseInt(localStorage.getItem('screenWidth'));

    const jsonObject = {
        ip: ip,
        browser: getBrowserCode(),
        resolutionWidth: width,
        resolutionHeight: height,
        mouseClicksNumber: sumclicks,
        timeElapsed: elapsed,
        distanceTravelled: distance,
        failed: failed,
        errorType: errorType
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: USABILITY_DATA_URL,
        data: JSON.stringify(jsonObject),
        success: () =>  alert("Zapisano dane."),
        error: (e) => console.error(e.responseText)
    });
}

function getBrowserCode() {
    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    var isEdge = !isIE && !!window.StyleMedia;
    var isFirefox = typeof InstallTrigger !== 'undefined';
    var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);
    if(isIE || isEdge) {
        return "I";
    } else if (isFirefox) {
        return "F";
    } else if (isChrome) {
        return "C";
    } else {
        return "UNKNOWN";
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
// localStorage.getItem('timeStart');       // Time of started session
// localStorage.getItem('timeStop');        // Time of stopped session
// localStorage.getItem('session');         // TRUE - metric session is recording || FALSE - metric session if stopped
// localStorage.getItem('mouseClicks');     // Sum of mouse clicks

function resetLocalStorage(){
    localStorage.setItem('screenWidth', '');
    localStorage.setItem('screenHeight', '');
    localStorage.setItem('timeStart', '');
    localStorage.setItem('timeStop', '');
    localStorage.setItem('mouseClicks', '');
    localStorage.setItem('distance', '');
}


//*************************
//  R E S O L U T I O N
//*************************

var screenWidth;
var screenHeight;

$(document).ready(function(){

    saveScreenResolution();
});

function saveScreenResolution() {

    screenWidth = screen.width;
    screenHeight = screen.height;
    localStorage.setItem('screenWidth', screenWidth.toString() );
    localStorage.setItem('screenHeight', screenHeight.toString() );
}
//******************************
// S C R E E N   C A P T U R E
//******************************

function doScreenCapture(metricpart) {
    //console.log("screen cap " + metricpart);

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
        success: (e) => console.log(e),
        error: (e) => console.error(e)
    });

}


//**************************************************
// M E A S U R E    C L I C K     D I S T A N C E ?
//**************************************************

var previousMouseX = 0;
var previousMouseY = 0;

document.addEventListener("click", function(){

    var session = localStorage.getItem('session');

    if(session == "true"){

        var mouse_X = event.clientX;
        var mouse_Y = event.clientY;

        var distance = Math.sqrt( Math.pow( previousMouseX - mouse_X , 2) + Math.pow(previousMouseY-mouse_Y, 2) );

        //console.log("Current : X." + mouse_X + " Y." + mouse_Y + " | Previous : X." + previousMouseX + " Y." + previousMouseY + " | Distance : " + distance);

        var distanceCount = localStorage.getItem('distance');

        if( distanceCount == "" || distanceCount == null){
            var temp = 0;
            localStorage.setItem('distance', temp.toString());
        }else{
            var temp = parseInt(distanceCount, 10);
            temp += distance;
            localStorage.setItem('distance', temp.toString());
        }

        previousMouseX = mouse_X;
        previousMouseY = mouse_Y;
    }


});

function getTotalDistance() {

    var totalDistance = localStorage.getItem('distance');
    var result = 0;

    if(totalDistance == ""){
        result = 0;
    }else{
        var temp = parseInt(totalDistance, 10);
        result = temp;
    }

    return result;

}


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

var button = document.querySelector('html');
button.addEventListener('mouseup', logMouseButton);

function logMouseButton(e) {

    var session = localStorage.getItem('session');

    if (typeof e === 'object') {
        switch (e.button) {
            case 0:
                if(session == "true") {addMouseClick(); }
                break;
            case 1:
                if(session == "true") {addMouseClick(); }
                break;
            case 2:
                if(session == "true") {addMouseClick(); }
                break;
            default:
                console.log("Not a mouse click...");
        }
    }
}

function addMouseClick(){

    var mouseClicks = localStorage.getItem('mouseClicks');

    console.log("click # " + mouseClicks);

    if( mouseClicks == "" || mouseClicks == null || mouseClicks == 0){
        var temp = 1;
        localStorage.setItem('mouseClicks', temp.toString());
    }else{
        var temp = parseInt(mouseClicks, 10);
        temp++;
        localStorage.setItem('mouseClicks', temp.toString());
    }
}

function getMouseClicks(){

    var mouseClicks = localStorage.getItem('mouseClicks');
    var result = 0;

    if(mouseClicks == ""){
        result = 0;
    }else{
        var temp = parseInt(mouseClicks, 10);
        result = temp;
    }

    return result;
}