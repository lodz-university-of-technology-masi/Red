
//***************************************************************
//  A P I       T E S T S
//***************************************************************

test_api = "/api/tests";
translate_api = "/api/translate";

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
            success: function () {
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
    console.log($('#'+ form.id).prop('files')[0]) //todo logika
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

function addQuestion(testId, data) {
    var type = data.
    jsonObject["type"] = type;
    jsonObject["content"] = $("#newQuestionContent").val();
    jsonObject["language"] = $("#newQuestionLanguage").val();
    jsonObject["suggestedAnswer"] = $("#newQuestionSuggestedAnswer").val();
    if(type === "Scale") {
        jsonObject["minValue"] = $("#newQuestionMinValueInput").val();
        jsonObject["maxValue"] = $("#newQuestionMaxValueInput").val();
        jsonObject["interval"] = $("#newQuestionIntervalInput").val();
    } else if(type === "SingleChoice") {
        jsonObject["possibleAnswers"] = jQuery.map( $("[id*=newQuestionPossibleAnswerInput]"), function( item ) {
            return ( item.value );
        });
    }

    var url = testsApi + testId + '/questions';

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(jsonObject),
        success: function (response) {
            alert(response);
            setTimeout(function () {
                window.location.reload(true);
            }, 1000);
        },
        error: function (e) {
            console.log(e);
            alert('Nie udało się dodać pytania.')
        }
    });
}
function translateTest(id){
    var testId = id.split("-");
    var url = "https://translate.yandex.net/api/v1.5/tr.json/translate",
        keyAPI = "trnsl.1.1.20190526T182448Z.7b7ab41004a59b15.e43446553cfbc62f604c9a85415a3b64f58ca47e";
    var qcontent;
    var languageJson;
    var jsonObject = {};
    $.ajax({
        type: "GET",
        url: translate_api + "/" + testId[1],
        success: function (data) {
            console.log("Fajnie ",data);
            var language = null;
            if (data.language === 'EN') {
                language = 'pl';
            } else {
                language = 'en';
            }
            languageJson = language.toUpperCase();

            jsonObject["editorId"] = data.editorId;
            jsonObject["jobTitleId"] = data.jobTitleId;
            jsonObject["language"] = languageJson;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: test_api,
                data: JSON.stringify(jsonObject),
                success: function () {
                    setTimeout(function () {
                        window.location.reload(true);
                    }, 2000);
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            });

            $.each(data.questions, function (index, value) {
                var questionData = `${value.content}/${value.suggestedAnswer}`;
                var request = new XMLHttpRequest(),
                    data = "key=" + keyAPI + "&text=" + questionData + "&lang=" + language;
                request.open("POST", url, true);
                request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                request.send(data);
                request.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var res = this.responseText;
                        var json = JSON.parse(res);
                        qcontent = json.text[0];
                    }
                    var array1 = qcontent.toString();
                    var array = array1.split("/");

                    var jsonObject = {};
                    var url1 = test_api +"/"+ testId[1] + '/questions';
                    jsonObject["type"] = value.type;
                    jsonObject["content"] = array[0];
                    jsonObject["language"] = languageJson;
                    jsonObject["suggestedAnswer"] = array[1];
                    if(value.type === "Scale") {
                        jsonObject["minValue"] = value.minValue;
                        jsonObject["maxValue"] = value.maxValue;
                        jsonObject["interval"] = value.interval;
                    }
                    console.log(jsonObject);

                $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: url1,
                        data: JSON.stringify(jsonObject),
                        success: function (response) {
                            alert(response);
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
            }

            });

        },
        error: function (e) {
            console.error(e);
        }
    });

}
