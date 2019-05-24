window.onload = function () {
    getJobTitles();
    getTests();
};

$(document).ready(function () {
    $('#TestTable').DataTable();
});

function getJobTitles() {
    $.get("/jobTitles", function (data) {
        $("#resultJobTitle").html("");
        if (data) {
            $.each(data, function (i, item) {
                $('[id*=JobTitleSelect]').append($('<option>', {
                    value: JSON.stringify(item),
                    text: item.name
                }));
            });
        }
    });
}


function getTests() {
    $.get("/api/tests", function (data) {

        $("#resultTest").html("");

        if (data) {
            var baseHref = location.href.replace(/\/+$/, "");
            $.each(data, function (index, value) {
                $("#resultTest").append("<tr><td>" + value.id + "</td>\n" +
                    "                <td>" + value.jobTitleName + "</td>\n" +
                    "                <td>" + value.language + "</td>\n" +
                    "                <td>" + ((value.editorName) ? value.editorName : "brak") + "</td>\n" +
                    "                <td>" + value.questionsNumber + "</td>\n" +
                    "                <td>" + moment(value.creationTime).format('YYYY-MM-DD HH:mm:ss') + "</td>\n" +
                    "                <td>\n" +
                    "                    <button id=\'T-" + value.id + "\' class=\"btn btn-sm btn-outline-info \" onClick=\"editTest(this.id) \" data-toggle=\"modal\" data-target=\"#updateTest\"><i class=\"material-icons md-24\">add_circle_outline</i> Edytuj test</button>\n" +
                    "                    <button id=\'T-" + value.id + "\' class=\"btn btn-sm btn-outline-danger deleteTestButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń test</button>\n" +
                    "                    <a href=\'" + baseHref + "/tests/" + value.id + "\' class=\"btn btn-sm btn-outline-success\"><i class=\"material-icons md-24\">add_circle_outline</i> Zarządzaj pytaniami</a>\n" +
                    "                </td></tr>");
            });
        }
    });
}

function resetMessages() {
    $('.importSuccessfulMessage').addClass('d-none');
    $('.importFailedMessage').addClass('d-none');
}
