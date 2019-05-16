
//***************************************************************
//  A P I       T E S T S
//***************************************************************

test_api = "/api/tests";

$(document).ready(function() {

    $("#FormCreateTestButton").click(function () {
        var jsonObject = {};
        var editor = JSON.parse($('#createTestEditorSelect').val());
        if(editor) {
            jsonObject["editorId"] = editor.id;
        }
        jsonObject["jobTitleId"] = JSON.parse($('#createTestJobTitleSelect').val()).id;
        jsonObject["language"] = $('#createTestLanguageSelect').val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: test_api,
            data: JSON.stringify(jsonObject),
            success: function (data) {
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
        var jsonObject = {};
        var editor = JSON.parse($('#createTestEditorSelect').val());
        if(editor) {
            jsonObject["editorId"] = editor.id;
        }
        jsonObject["jobTitleId"] = JSON.parse($('#updateTestJobTitleSelect').val()).id;
        jsonObject["language"] = $('#updateTestLanguageSelect').val();
        var testId = $('#updatedTestId').val();
        jsonObject["id"] = testId

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: test_api + "/" + testId,
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

        var testID = event.target.id.split('-');

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

function editTest(id) {
    resetMessages();
    var testId = id.split("-");

    $.ajax({
        type: "GET",
        url: test_api + "/" + testId[1],
        success: function(data) {
            $('#updatedTestId').val(data.id);

            selectAppropriateJobTitle(data.jobTitleName);
            selectAppropriateEditor(data.editorName);
            $('#updateTestLanguageSelect').val(data.language)
        },
        error: function (e) {
            console.error(e);
        }
    });
}

function importTestFromCSV(form) {
    console.log($('#'+ form.id).prop('files')[0])
    $('.importSuccessfulMessage').removeClass('d-none') //when success
    // when failed $('.importFailedMessage').removeClass('d-none')
}

function selectAppropriateJobTitle(jobTitleName) {
    $('[id=updateTestJobTitleSelect] option').filter(function() {
        return ($(this).text() === jobTitleName);
    }).prop('selected', true);
}

function selectAppropriateEditor(editorName) {
    if(editorName) {
        $('[id=updateTestEditorSelect] option').filter(function() {
            return ($(this).text() === editorName);
        }).prop('selected', true);
    } else {
        $('#updateTestEditorSelect').val("")
    }

}
