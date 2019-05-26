window.onload = () => {
    getEditors();
    getJobTitles();
    getTests();
};

$(document).ready(() => {
    $('#EditorsTable').DataTable();
    $('#JobTitleTable').DataTable();
    $('#TestTable').DataTable();
});

function getEditors() {
    $.get("/api/users/all?role=EDITOR", (data) => {
        $("#resultEditor").html("");
        if (data) {
            fillEditorSelects(data);
            $.each(data, (index, value) => {
                $("#resultEditor").append("<tr><td>" + value.id + "</td>\n" +
                    "                <td>" + value.username + "</td>\n" +
                    "                <td>" + value.email + "</td>\n" +
                    "                <td>" + value.firstName + "</td>\n" +
                    "                <td>" + value.lastName + "</td>\n" +
                    "                <td>\n" +
                    "                    <button id=\'EE-" + value.id + "\' class=\"btn btn-sm btn-outline-info editEditorButton\" onClick=\" editEditor(this.id); \" data-toggle=\"modal\" data-target=\"#updateEditor\"><i class=\"material-icons md-24\">add_circle_outline</i> Edytuj Redaktora</button>\n" +
                    "                    <button id=\'DE-" + value.id + "\' class=\"btn btn-sm btn-outline-danger deleteEditorButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń Redaktora</button>\n" +
                    "                </td></tr>");
            });
        }
    });
}


function getJobTitles() {
    $.get("/jobTitles", (data) => {
        $("#resultJobTitle").html("");

        if (data) {
            fillJobTitleSelects(data);
            $.each(data, (index, value) => {
                let testName = "";
                let testID = 0;

                if (value.testList) {
                    $.each(value.testList, (index, test) => {
                        testName = test.name;
                        testID = test.id;

                    });
                }

                $("#resultJobTitle").append("<tr><td>" + value.id + "</td>\n" +
                    "                <td>" + value.name + "</td>\n" +
                    "                <td>" + testName + "</td>\n" +
                    "                <td>" + value.active + "</td>\n" +
                    "                <td>\n" +
                    "                    <button id=\'JE-" + value.id + "\' class=\"btn btn-sm btn-outline-info \" onClick=\" editJobTitle(this.id); \" data-toggle=\"modal\" data-target=\"#updateJobTitle\"><i class=\"material-icons md-24\">add_circle_outline</i> Edytuj Stanowisko</button>\n" +
                    "                    <button id=\'J" + value.id + "\' class=\"btn btn-sm btn-outline-danger deleteJobTitleButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń Stanowisko</button>\n" +
                    "                    <button class=\"btn btn-sm btn-outline-success attachJobTestButton\" type=\"button\" data-toggle=\"modal\" data-target=\"#attachJobTest\" ><i class=\"material-icons md-24\">add_circle_outline</i> Dodaj Test</button>\n" +
                    "                    <button id=\'TJD" + value.id + "T" + testID + "\' class=\"btn btn-sm btn-outline-warning detachJobTestButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Odepnij Test</button>\n" +
                    "                    <!--<button id=\"DJ0\" class=\"btn btn-sm btn-outline-default deactivateJobTitleButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Dezaktywuj Stanowisko</button>-->\n" +
                    "                </td></tr>");
            });
        }
    });
}

function getTests() {
    $.get("/api/tests/all", (data) => {

        $("#resultTest").html("");

        if (data) {
            const baseHref = location.href.replace(/\/+$/, "");
            $.each(data, (index, value) => {
                let language = null;
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
                    "                    <a id=\'T-" + value.id + "\' class=\"dropdown-item \" onClick=\"editTest(this.id);\" data-toggle=\"modal\" data-target=\"#updateTest\"><i class=\"material-icons md-24\">add_circle_outline</i> Edytuj test</a>\n" +
                    "                    <a id=\'T-" + value.id + "\' class=\"dropdown-item deleteTestButton\" ><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń test</a>\n" +
                    "                    <a href=\'" + baseHref + "/tests/" + value.id + "\' class=\"dropdown-item\"><i class=\"material-icons md-24\">add_circle_outline</i> Zarządzaj pytaniami</a>\n" +
                    "                    <a class=\"dropdown-item \" href=\'/api/tests/" + value.id + "/export\' target='_blank' download'><i class=\"material-icons md-24\">import_export</i> Eksport do csv</a>\n" +
                    "                    <a id =\'translate" + value.id + "\' class=\"dropdown-item \" ><i class=\"material-icons md-24\">translate</i> Tłumaczenie testu na </a>\n" +
                    "</div>" +
                    "</div>" +
                    "                </td></tr>");
                const translateButton = document.getElementById("translate" + value.id);
                const text = document.createTextNode(language);
                translateButton.appendChild(text);
            });
        }
        $("div.dropdown-menu > a").hover(
            () => {
                $(this).css('background-color', '#2da447');
                $(this).css('cursor', 'pointer');
            }, () => {
                $(this).css('background-color', '')
            }
        );
    });
}

function fillJobTitleSelects(jobTitles) {
    $.each(jobTitles, (i, item) => {
        $('[id*=JobTitleSelect]').append($('<option>', {
            value: JSON.stringify(item),
            text: item.name
        }));
    });
}

function fillEditorSelects(editors) {
    $.each(editors, (i, item) => {
        $('[id*=EditorSelect]').append($('<option>', {
            value: JSON.stringify(item),
            text: item.fullName
        }));
    });
}

function resetMessages() {
    $('.importSuccessfulMessage').addClass('d-none');
    $('.importFailedMessage').addClass('d-none');
}
