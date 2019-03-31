
//***************************************************************
//  A P I       E D I T O R
//***************************************************************

editor_api = "/redaktor/";

$(document).ready(function() {

    $("#FormCreateEditorButton").click(function () {

        var dataForm = $('#FormCreateEditor').serializeArray();

        console.log(dataForm);

        $.post(editor_api + dataForm[0].value + "/" + dataForm[1].value,
            function (data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 2000);


            });

    });

    $("#FormUpdateEditorButton").click(function () {

        var dataForm = $('#FormUpdateEditor').serializeArray();

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: editor_api + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value,
            success: function(data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }

        });

    });

    $(".deleteEditorButton").click(function(event){

        console.log(event.target.id);

        var editorId = event.target.id;

        $.ajax({
            type: "DELETE",
            url: editor_api + editorId[1],
            success: function(data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 1000);
            }
        });

    });

});

//***************************************************************
//  A P I       J O B T I T L E S
//***************************************************************
jobTitle_api = "/jobTitles";

$(document).ready(function() {

    $("#FormCreateJobTitleButton").click(function () {

        var dataForm = $('#FormCreateJobTitle').serializeArray();

        var jsonObject = {};
        jsonObject["name"] = dataForm[0].value;
        jsonObject["active"] = dataForm[1].value;

        console.log(JSON.stringify(jsonObject));

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: jobTitle_api,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function(data) {
                console.log("response: " + data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 1000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $("#FormUpdateJobTitleButton").click(function () {

        var dataForm = $('#FormUpdateJobTitle').serializeArray();

        var jsonObject = {};
        jsonObject["id"] = dataForm[0].value
        jsonObject["name"] = dataForm[1].value;
        jsonObject["active"] = dataForm[2].value;

        console.log(JSON.stringify(jsonObject));

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: jobTitle_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function(data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $(".deleteJobTitleButton").click(function(event){

        console.log(event.target.id);

        var jobTitleID = event.target.id;
        console.log("ID: " + jobTitleID[1]);


        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobTitleID[1],
            success: function(data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 1000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });

    });

    $("#FormAttachJobTestButton").click(function(){

        var dataForm = $('#FormAttachJobTest').serializeArray();

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: jobTitle_api + "/" + dataForm[0].value + "/tests/" + dataForm[1].value,
            success: function(data) {
                console.log("response: " + data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 1000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });

    });


    $(".detachJobTestButton").click(function(event){
        console.log(event.target.id);

        var jobID = event.target.id;
        //{jobTitleId}/tests/{testId}
        var testID = event.target.id;

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobID[3] + "/tests" + testID[5],
            success: function(data) {
                console.log(data);

                // setTimeout(function(){
                //     window.location.reload(true);
                // }, 1000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });
});