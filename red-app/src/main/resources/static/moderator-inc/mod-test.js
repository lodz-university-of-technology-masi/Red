
//***************************************************************
//  A P I       T E S T S
//***************************************************************

test_api = "/api/tests";

$(document).ready(function() {

    $("#FormCreateTestButton").click(function () {
        var jsonObject = {};
        var editor = JSON.parse($('#editorSelect').val());
        if(editor) {
            jsonObject["editorId"] = editor.id;
        }
        jsonObject["jobTitleId"] = JSON.parse($('#jobTitleSelect').val()).id;
        jsonObject["language"] = $('#newTestLanguage').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: test_api,
            data: JSON.stringify(jsonObject),
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
});