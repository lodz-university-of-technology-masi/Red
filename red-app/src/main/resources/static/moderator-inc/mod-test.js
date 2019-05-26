//***************************************************************
//  A P I       T E S T S
//***************************************************************

const test_api = "/api/tests";

$(document).ready(() => {

    $("#FormCreateTestButton").click(() => {
        const jsonObject = {};
        const editor = JSON.parse($('#createTestEditorSelect').val());
        if (editor) {
            jsonObject["editorId"] = editor.id;
        }
        jsonObject["jobTitleId"] = JSON.parse($('#createTestJobTitleSelect').val()).id;
        jsonObject["language"] = $('#createTestLanguageSelect').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: test_api,
            data: JSON.stringify(jsonObject),
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });

    $("#FormUpdateTestButton").click(() => {
        const jsonObject = {};
        const editor = JSON.parse($('#createTestEditorSelect').val());
        if (editor) {
            jsonObject["editorId"] = editor.id;
        }
        jsonObject["jobTitleId"] = JSON.parse($('#updateTestJobTitleSelect').val()).id;
        jsonObject["language"] = $('#updateTestLanguageSelect').val();
        const testId = $('#updatedTestId').val();
        jsonObject["id"] = testId;

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: test_api + "/" + testId,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });

    $("#resultTest").on("click", ".deleteTestButton", function (event) {

        const testID = event.target.id.split('-');

        $.ajax({
            type: "DELETE",
            url: test_api + "/" + testID[1],
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });
});

function editTest(id) {
    resetMessages();
    const testId = id.split("-");

    $.ajax({
        type: "GET",
        url: test_api + "/" + testId[1],
        success: (data) => {
            $('#updatedTestId').val(data.id);

            selectAppropriateJobTitle(data.jobTitleName);
            selectAppropriateEditor(data.editorName);
            $('#updateTestLanguageSelect').val(data.language)
        },
        error: (e) => console.error(e.responseText)
    });
}

function importTestFromCSV(form) {
    console.log($('#' + form.id).prop('files')[0]) //todo logika
    $('.importSuccessfulMessage').removeClass('d-none') //when success
    // when failed $('.importFailedMessage').removeClass('d-none')
}

function selectAppropriateJobTitle(jobTitleName) {
    $('[id=updateTestJobTitleSelect] option').filter(function () {
        return ($(this).text() === jobTitleName);
    }).prop('selected', true);
}

function selectAppropriateEditor(editorName) {
    if (editorName) {
        $('[id=updateTestEditorSelect] option').filter(function () {
            return ($(this).text() === editorName);
        }).prop('selected', true);
    } else {
        $('#updateTestEditorSelect').val("")
    }

}
