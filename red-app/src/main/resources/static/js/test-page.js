$(document).ready(function () {
    $(".rangeInput").slider();

    $(".rangeInput").slider({
        tooltip: 'always',
        tooltip_position: 'top'
    });
});


function sendTestForm() {


    var jobTitleId = $("#jobTitleId").val();
    var candidateTest = {};
    candidateTest.testId = parseInt($("#testId").val());
    candidateTest.username = $("#candidate").val();
    candidateTest.answers = [];

    $("form#testAnswerForm input[name^=Q]").each(function(index){

        var QID = $(this).val();

        var ASTR = $("[name='A"+QID+"']").val();

         var answerObject = {};
         answerObject.questionId = parseInt(QID);
         answerObject.answer = ASTR;
         candidateTest.answers[index] = answerObject;
    });

    console.log(JSON.stringify(candidateTest));

    var candidate_api = "/kandydat/stanowisko/";

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: candidate_api + jobTitleId,
        data: JSON.stringify(candidateTest),
        dataType: 'json',
        success: function(data) {

            alert(data);
            window.location.href = "/kandydat/stanowisko/"+jobTitleId + "/wynik";
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}

$(document).ready(function () {
    $('input:radio[name=A2]').change(function () {
       alert($(this).val());
    });
});
