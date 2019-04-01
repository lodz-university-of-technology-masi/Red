
//***************************************************************
//  A P I       E D I T O R
//***************************************************************

editor_api = "/redaktor/";

$(document).ready(function() {

    $("#FormCreateEditorButton").click(function () {

        var dataForm = $('#FormCreateEditor').serializeArray();

        console.log(dataForm);

        $.post(editor_api + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value + "/" + dataForm[3].value + "/" + dataForm[4].value + "/" + dataForm[5].value,
            function (data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            });
    });

    $("#FormUpdateEditorButton").click(function () {

        var dataForm = $('#FormUpdateEditor').serializeArray();

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: editor_api + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value + "/" + dataForm[3].value + "/" + dataForm[4].value + "/" + dataForm[5].value + "/" + dataForm[6].value,
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
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

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });
});

//***************************************************************
//  A P I       T E S T S
//***************************************************************

test_api = "/tests";

$(document).ready(function() {

    $("#FormCreateTestButton").click(function () {

        var dataForm = $('#FormCreateTest').serializeArray();

        var jsonObject = {};
        jsonObject["jobTitleId"] = dataForm[0].value;


        console.log(JSON.stringify(jsonObject));

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: test_api,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function (data) {
                console.log("response: " + data);

                setTimeout(function () {
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $("#FormUpdateTestButton").click(function () {

        var dataForm = $('#FormUpdateTest').serializeArray();

        var jsonObject = {};
        jsonObject["id"] = dataForm[0].value;
        jsonObject["jobTitleId"] = dataForm[1].value;

        console.log(JSON.stringify(jsonObject));

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: test_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function (data) {
                console.log(data);

                setTimeout(function () {
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $("#resultTest").on("click", ".deleteTestButton", function (event) {

        console.log(event.target.id);

        var testID = event.target.id;
        console.log("ID: " + testID[1]);


        $.ajax({
            type: "DELETE",
            url: test_api + "/" + testID[1],
            success: function (data) {
                console.log(data);

                setTimeout(function () {
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });

    });

    $(".addQuestionToTestCreateButton").click(function () {
       $("#questionsToTestCreate")[0].innerHTML += $('#QuestionIdCreateInput')[0].value + ', ';
    });
    $(".addQuestionToTestUpdateButton").click(function () {
        $("#questionsToTest")[0].innerHTML += $('#QuestionIdInput')[0].value + ', ';
    });
});