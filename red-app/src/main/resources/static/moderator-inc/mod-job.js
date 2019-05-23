//***************************************************************
//  A P I       J O B T I T L E S
//***************************************************************
jobTitle_api = "/jobTitles";

$(document).ready(function () {

    $("#FormCreateJobTitleButton").click(function () {

        var dataForm = $('#FormCreateJobTitle').serializeArray();

        var jsonObject = {};
        jsonObject["name"] = dataForm[0].value;
        jsonObject["active"] = dataForm[1].value;

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: jobTitle_api,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function () {

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });
    });

    $("#FormUpdateJobTitleButton").click(function () {

        var dataForm = $('#FormUpdateJobTitle').serializeArray();

        var jsonObject = {};
        jsonObject["id"] = dataForm[0].value;
        jsonObject["name"] = dataForm[1].value;
        jsonObject["active"] = dataForm[2].value;

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: jobTitle_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function () {

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });
    });

    $(".deleteJobTitleButton").click(function (event) {

        var jobTitleID = event.target.id;

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobTitleID[1],
            success: function () {

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });

    });

    $("#FormAttachJobTestButton").click(function () {

        var dataForm = $('#FormAttachJobTest').serializeArray();

        $.ajax({
            type: "PUT",
            url: jobTitle_api + "/" + dataForm[0].value + "/tests/" + dataForm[1].value,
            success: function () {

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });

    });


    $(".detachJobTestButton").click(function (event) {

        var jobID = event.target.id;
        //{jobTitleId}/tests/{testId}
        var testID = event.target.id;

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobID[3] + "/tests" + testID[5],
            success: function () {
                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });
    });
});

function editJobTitle(editorId) {

    var res = editorId.split("-");

    $.ajax({
        type: "GET",
        url: jobTitle_api + "/" + res[1],
        success: function (data) {

            insertIntoJobTitleEditModal(data);

        },
        error: function (e) {
            console.error(e.responseText);
        }
    });
}

function insertIntoJobTitleEditModal(data) {

    clearJobTitleEditModal();

    $("#FUJTID").val(data.id);
    $("#FUJTName").val(data.name);
    $("#FUJTActive").val(data.active);


}

function clearJobTitleEditModal() {

    $("#FUJTID").val("");
    $("#FUJTName").val("");
    $("#FUJTActive").val("");


}