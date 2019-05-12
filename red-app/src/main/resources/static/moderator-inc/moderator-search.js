var selectedText = "";

var cntrlIsPressed = false;

$(document).keydown(function(event){
    if(event.which=="17")
        cntrlIsPressed = true;
});

$(document).keyup(function(){
    cntrlIsPressed = false;
});



$(document).bind("contextmenu",function(e){

    if(cntrlIsPressed){
        e.preventDefault();
        selectedText = selectHTML().toString();
        //console.log(e.pageX + "," + e.pageY + selectedText);
        $("#searchBox").css("left",e.pageX);
        $("#searchBox").css("top",e.pageY);
        $("#searchBox").fadeIn(200,startFocusOut());
    }


});

function startFocusOut(){
    $(document).on("click",function(){
        $("#searchBox").hide();
        $(document).off("click");
    });
}

$(function() {
    $('#Wiki').click( function() {
        var mytext = selectedText;
        console.log("Wiki search : " + mytext);

        $.notify({
            // options
            message: 'Hello World'
        },{
            // settings
            type: 'danger'
        });

    });

    $('#Thes').click( function() {
        var mytext = selectedText;
        console.log("Thes search : " + mytext);
    });

});

function selectHTML() {
    try {
        if (window.ActiveXObject) {
            var c = document.selection.createRange();
            return c.htmlText;
        }
        var nNd = document.createElement("span");
        var w = getSelection().getRangeAt(0);
        w.surroundContents(nNd);
        return nNd.innerHTML;
    } catch (e) {
        if (window.ActiveXObject) {
            return document.selection.createRange();
        } else {
            return getSelection();
        }
    }
}