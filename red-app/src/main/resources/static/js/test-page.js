$(document).ready(() => {
    $(".rangeInput").slider();

    $(".rangeInput").slider({
        tooltip: 'always',
        tooltip_position: 'top'
    });
});


function sendTestForm() {


    const jobTitleId = $("#jobTitleId").val();
    const candidateTest = {
        testId: parseInt($("#testId").val()),
        username: $("#candidate").val(),
        answers: []
    };

    $("form#testAnswerForm input[name^=Q]").each((index) => {

        const QID = $(this).val();
        const ASTR = $("[name='A" + QID + "']").val();

        candidateTest.answers[index] = {questionId: parseInt(QID), answer: ASTR};
    });

    const candidate_api = "/api/kandydat/stanowisko/";

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: candidate_api + jobTitleId,
        data: JSON.stringify(candidateTest),
        dataType: 'json',
        success: (data) => {
            if (data) {
                alert("Gratulacje!!! Wypełniono test");
            }

            window.location.href = "/kandydat/stanowisko/" + jobTitleId + "/wynik";
        },
        error: (e) => console.error(e.responseText)
    });

}

$(document).ready(() => {
    $('input:radio[name=A2]').change(() => {
        alert($(this).val());
    });
});
