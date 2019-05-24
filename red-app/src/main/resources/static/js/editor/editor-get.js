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
                var language = null;
                if (value.language === 'EN') {
                    language = 'polski';
                } else {
                    language = 'angielski';
                }
                $("#resultTest").append("<tr><td>" + value.id + "</td>\n" +
                    "                <td>" + value.jobTitleName + "</td>\n" +
                    "                <td>" + value.language + "</td>\n" +
                    "                <td>" + ((value.editorName) ? value.editorName : "brak") + "</td>\n" +
                    "                <td>" + value.questionsNumber + "</td>\n" +
                    "                <td>" + moment(value.creationTime).format('YYYY-MM-DD HH:mm:ss') + "</td>\n" +
                    "                <td>\n" +
                    " <div className=\"btn-group\">" +
                    "<button type=\"button\" class=\"btn btn-success dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">Zarządzaj testem </button>" +
                    "    <div class=\"dropdown-menu\">" +
                    "                    <a id=\'T-" + value.id + "\' class=\"dropdown-item \" onClick=\"editTest(this.id) \" data-toggle=\"modal\" data-target=\"#updateTest\"><i class=\"material-icons md-24\">add_circle_outline</i> Edytuj test</a>\n" +
                    "                    <a id=\'T-" + value.id + "\' class=\"dropdown-item deleteTestButton\" ><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń test</a>\n" +
                    "                    <a href=\'" + baseHref + "/tests/" + value.id + "\' class=\"dropdown-item\"><i class=\"material-icons md-24\">add_circle_outline</i> Zarządzaj pytaniami</a>\n" +
                    "                    <a class=\"dropdown-item \" href=\'/api/tests/" + value.id + "/export\' target='_blank' download'><i class=\"material-icons md-24\">import_export</i> Eksport do csv</a>\n" +
                    "                    <a id =\'translate" + value.id + "\' class=\"dropdown-item \" ><i class=\"material-icons md-24\">translate</i> Tłumaczenie testu na </a>\n" +
                    "</div>" +
                    "</div>" +
                    "                </td></tr>");
                var translateButton = document.getElementById("translate" + value.id);
                var text = document.createTextNode(language);
                translateButton.appendChild(text);
            });
        }
        $("div.dropdown-menu > a").hover(
            function () {
                $(this).css('background-color', '#2da447')
                $(this).css('cursor', 'pointer');
            }, function () {
                $(this).css('background-color', '')
            }
        );
    });
}

function resetMessages() {
    $('.importSuccessfulMessage').addClass('d-none');
    $('.importFailedMessage').addClass('d-none');
}
