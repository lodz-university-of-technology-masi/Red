//***************************************************************
//  A P I       T E S T S
//***************************************************************

const test_api = "/api/tests";
const translate_api = "/api/translate";


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

function importTestFromCSV() {
    const file = $('#createTestCSVImport').prop('files')[0];

    const jsonObject = {};
    const editor = JSON.parse($('#createTestEditorSelect').val());
    if (editor) {
        jsonObject["editorId"] = editor.id;
    }
    jsonObject["jobTitleId"] = JSON.parse($('#createTestJobTitleSelect').val()).id;
    jsonObject["language"] = $('#createTestLanguageSelect').val();

    const data = new FormData();
    data.append('file', file);
    data.append('test', JSON.stringify(jsonObject));

    $.ajax({
        type: 'POST',
        contentType: false,
        url: "/api/tests/import",
        data: data,
        processData: false,
        success: () => {
            $('.importSuccessfulMessage').removeClass('d-none');
            $('label[for="createTestCSVImport"] span').text("Wybierz plik CSV z testem");
            reloadWindow();
        },
        error: (e) => {
            $('.importFailedMessage').removeClass('d-none');
            $('label[for="createTestCSVImport"] span').text("Wybierz plik CSV z testem");
            console.error(e.responseText)
        },
    });
}

function refreshFileName(form) {
    const fileName = $('#' + form.id).prop('files')[0].name;

    $('label[for="createTestCSVImport"] span').text(fileName);
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


function translateTest(id) {
    var testId = id.split("-");
    var url = "https://translate.yandex.net/api/v1.5/tr.json/translate",
        keyAPI = "trnsl.1.1.20190526T182448Z.7b7ab41004a59b15.e43446553cfbc62f604c9a85415a3b64f58ca47e";
    var responseData;
    var translateLanguage;
    var jsonObject = {};
    $.ajax({
        type: "GET",
        url: translate_api + "/" + testId[1],
        success: function (data) {
            window.newTestId = null;
            var language = null;
            if (data.language === 'EN') {
                language = 'pl';
            } else {
                language = 'en';
            }
            translateLanguage = language.toUpperCase();

            jsonObject["editorId"] = data.editorId;
            jsonObject["jobTitleId"] = data.jobTitleId;
            jsonObject["language"] = translateLanguage;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: test_api,
                data: JSON.stringify(jsonObject),
                async: false,
                success: function (data) {
                    var arrayData = data.split(" ");
                    window.newTestId = arrayData[1];
                    setTimeout(function () {
                        window.location.reload(true);
                    }, 2000);
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            });

            $.each(data.questions, function (index, value) {
                var jsonObject = {};
                if (value.type === "SingleChoice") {
                    var questionData = `${value.content}/${value.suggestedAnswer}`;
                    var i;
                    questionData = questionData + "/" + value.possibleAnswers[0];
                    for (i = 1; i < value.possibleAnswers.length; i++) {
                        questionData = questionData + "/" + value.possibleAnswers[i];
                    }
                } else
                    var questionData = `${value.content}/${value.suggestedAnswer}`;

                var request = new XMLHttpRequest(),
                    data = "key=" + keyAPI + "&text=" + questionData + "&lang=" + language;
                request.open("POST", url, false);
                request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                request.send(data);
                if (request.status == 200) {
                    var res = request.responseText;
                    var json = JSON.parse(res);
                    responseData = json.text[0];
                }

                var arrayData = responseData.toString();
                var splitData = arrayData.split("/");


                var parseId = parseInt(window.newTestId);
                var urlTranslate = test_api + "/" + parseId + '/questions';
                jsonObject["type"] = value.type;
                jsonObject["content"] = splitData[0];
                jsonObject["language"] = translateLanguage;
                jsonObject["suggestedAnswer"] = splitData[1];
                if (value.type === "Scale") {
                    jsonObject["minValue"] = value.minValue;
                    jsonObject["maxValue"] = value.maxValue;
                    jsonObject["interval"] = value.interval;
                } else if (value.type === "SingleChoice") {
                    item = [];
                    var j;
                    for (j = 0; j < value.possibleAnswers.length; j++) {
                        item.push(splitData[j + 2])
                    }

                    jsonObject["possibleAnswers"] = item;
                }
                console.log(jsonObject);

                $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: urlTranslate,
                        data: JSON.stringify(jsonObject),
                        async: false,
                        success: function () {
                            setTimeout(function () {
                                window.location.reload(true);
                            }, 1000);
                        },
                        error: function (e) {
                            console.log(e);
                            alert('Nie udało się dodać pytania.')
                        }
                    }
                );


            });

        },
        error: function (e) {
            console.error(e);
        }
    });

}
