$(document).ready(() => {
    $(".rangeInput").slider();

    $(".rangeInput").slider({
        tooltip: 'always',
        tooltip_position: 'top'
    });
    $('time').each((i, e) => {
        const time = moment($(e).attr('datetime')).format('YYYY-MM-DD HH:mm:ss');
        $(e).html('<span>' + time + '</span>');
    });
});

const testsApi = '/api/tests/';

const reloadWindow = () => {
    setTimeout(() => {
        window.location.reload();
    }, 200);
};

function detachQuestionFromTest(questionId, testId) {
    const url = testsApi + testId + "/questions/" + questionId;
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: url,
        success: (response) => {
            $("#question" + questionId).remove();
            alert(response)
        },
        error: (e) => {
            alert('Wystąpił błąd podczas odpinania pytania od testu');
            console.error(e.responseText);
        }
    });
}

function toggleNewQuestionFieldsVisibility(element) {
    $("#possibleAnswersFields").addClass("d-none");
    $("#newQuestionScaleProperties").addClass("d-none");
    $("#newQuestionSuggestedAnswer").attr("type", "text");
    switch (element.value) {
        case 'SingleChoice':
            $("#possibleAnswersFields").removeClass("d-none")
            break;
        case 'Open':
            break;
        case 'Numeric':
            $("#newQuestionSuggestedAnswer").attr("type", "number");
            break;
        case 'Scale':
            $("#newQuestionScaleProperties").removeClass("d-none");
            $("#newQuestionSuggestedAnswer").attr("type", "number");
            break;
        default:
            alert('Wybrano zły typ pytania');
            break;
    }
}

let possibleAnswerId = 1;

function addPossibleAnswer() {
    possibleAnswerId++;
    $("#possibleAnswers").append("<div id=\'newQuestionPossibleAnswer" + possibleAnswerId + "\'><label class=\"row\" for=\'newQuestionPossibleAnswerInput" + possibleAnswerId + "\'>Możliwa odpowiedź: \n" +
        "                <a class=\"material-icons text-danger\" role=\"button\"\n" +
        "                   onclick=\"deletePossibleAnswer(this)\" style=\"cursor:pointer;\">delete</a></label>\n" +
        "            <input type=\"text\" id=\'newQuestionPossibleAnswerInput" + possibleAnswerId + "\' class=\"form-control rounded-1 blue-input row\"></div>")
}

function deletePossibleAnswer(element) {
    element.parentElement.parentElement.remove();
}

function showAddNewQuestionForm() {
    $("#addNewQuestionForm").removeClass("d-none");
    $("#showAddNewQuestionFormButton").addClass("d-none");
    $("#newQuestionLanguage").val($("#testLanguage").text());
    $("#newQuestionLanguage").prop('disabled', true);
}

function hideAddNewQuestionForm() {
    $("#addNewQuestionForm").addClass("d-none");
    $("#showAddNewQuestionFormButton").removeClass("d-none");
}

function addQuestionToTest(testId) {
    let existingQuestion = $("#existingQuestionSelect").val();
    const jsonObject = {};
    if (existingQuestion) {
        existingQuestion = $.parseJSON(existingQuestion);
        jsonObject["id"] = existingQuestion.id;
        jsonObject["creationTime"] = existingQuestion.creationTime;
        jsonObject["originalQuestionId"] = existingQuestion.originalQuestionId;
    }
    const type = $("#newQuestionType").val();
    jsonObject["type"] = type;
    jsonObject["content"] = $("#newQuestionContent").val();
    jsonObject["language"] = $("#newQuestionLanguage").val();
    jsonObject["suggestedAnswer"] = $("#newQuestionSuggestedAnswer").val();
    if (type === "Scale") {
        jsonObject["minValue"] = $("#newQuestionMinValueInput").val();
        jsonObject["maxValue"] = $("#newQuestionMaxValueInput").val();
        jsonObject["interval"] = $("#newQuestionIntervalInput").val();
    } else if (type === "SingleChoice") {
        jsonObject["possibleAnswers"] =
            jQuery.map($("[id*=newQuestionPossibleAnswerInput]"), item => item.value);
    }

    const url = testsApi + testId + '/questions';

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(jsonObject),
        success: (response) => {
            alert(response);
            reloadWindow()
        },
        error: (e) => {
            console.error(e.responseText);
            alert('Nie udało się dodać pytania.')
        }
    });
}

function updateNewQuestionFields() {
    const question = $.parseJSON($("#existingQuestionSelect").val());
    $("#newQuestionType").val(question.type);
    $("#newQuestionType").prop('disabled', true);
    $("#newQuestionType").change();
    $("#newQuestionContent").val(question.content);
    $("#newQuestionContent").prop('disabled', true);
    $("#newQuestionLanguage").val(question.language);
    $("#newQuestionLanguage").prop('disabled', true);
    $("#newQuestionSuggestedAnswer").val(question.suggestedAnswer);
    $("#newQuestionSuggestedAnswer").prop('disabled', true);
    $("#newQuestionMinValueInput").val(question.minValue);
    $("#newQuestionMinValueInput").prop('disabled', true);
    $("#newQuestionMaxValueInput").val(question.maxValue);
    $("#newQuestionMaxValueInput").prop('disabled', true);
    $("#newQuestionIntervalInput").val(question.interval);
    $("#newQuestionIntervalInput").prop('disabled', true);
    $("[id*=newQuestionPossibleAnswer]").remove();
    if (question.possibleAnswers) {
        for (let i = 0; i < question.possibleAnswers.length; i++) {
            addPossibleAnswer();
            $("#newQuestionPossibleAnswerInput" + possibleAnswerId).val(question.possibleAnswers[i]);
            $("#newQuestionPossibleAnswerInput" + possibleAnswerId).prop('disabled', true);
        }
    }
}

function resetFields() {
    $("#existingQuestionSelect").val("");
    $("#newQuestionType").val("");
    $("#newQuestionType").prop('disabled', false);
    $("#newQuestionContent").val("");
    $("#newQuestionContent").prop('disabled', false);
    $("#newQuestionLanguage").val($("#testLanguage").text());
    $("#newQuestionLanguage").prop('disabled', true);
    $("#newQuestionSuggestedAnswer").val("");
    $("#newQuestionSuggestedAnswer").prop('disabled', false);
    $("#newQuestionMinValueInput").val("");
    $("#newQuestionMinValueInput").prop('disabled', false);
    $("#newQuestionMaxValueInput").val("");
    $("#newQuestionMaxValueInput").prop('disabled', false);
    $("#newQuestionIntervalInput").val("");
    $("#newQuestionIntervalInput").prop('disabled', false);
    $("[id*=newQuestionPossibleAnswer]").remove();

    $("[id*=newQuestionPossibleAnswerInput]").val("");
    $("[id*=newQuestionPossibleAnswerInput]").prop('disabled', false);
}
