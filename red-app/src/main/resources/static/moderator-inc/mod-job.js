//***************************************************************
//  A P I       J O B T I T L E S
//***************************************************************
const jobTitle_api = "/jobTitles";

$(document).ready(function () {

    $("#FormCreateJobTitleButton").click(function () {

        const dataForm = $('#FormCreateJobTitle').serializeArray();

        const jsonObject = {
            name: dataForm[0].value,
            active: dataForm[1].value
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: jobTitle_api,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });

    $("#FormUpdateJobTitleButton").click(() => {

        const dataForm = $('#FormUpdateJobTitle').serializeArray();

        const jsonObject = {
            id: dataForm[0].value,
            name: dataForm[1].value,
            active: dataForm[2].value
        };

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: jobTitle_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        })
        ;
    });

    $(".deleteJobTitleButton").click((event) => {

        const jobTitleID = event.target.id;

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobTitleID[1],
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });

    });

    $("#FormAttachJobTestButton").click(() => {

        const dataForm = $('#FormAttachJobTest').serializeArray();

        $.ajax({
            type: "PUT",
            url: jobTitle_api + "/" + dataForm[0].value + "/tests/" + dataForm[1].value,
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });

    });


    $(".detachJobTestButton").click((event) => {

        const jobID = event.target.id;
        //{jobTitleId}/tests/{testId}
        const testID = event.target.id;

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + "/" + jobID[3] + "/tests" + testID[5],
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });
});

function editJobTitle(editorId) {

    const res = editorId.split("-");

    $.ajax({
        type: "GET",
        url: jobTitle_api + "/" + res[1],
        success: (data) => insertIntoJobTitleEditModal(data),
        error: (e) => console.error(e.responseText)
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