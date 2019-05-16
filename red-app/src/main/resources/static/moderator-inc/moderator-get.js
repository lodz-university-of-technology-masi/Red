window.onload = function () {

    getEditors();
    getJobTitles();
    getTests();
};

$(document).ready(function() {
    $('#EditorsTable').DataTable();
    $('#JobTitleTable').DataTable();
    $('#TestTable').DataTable();
} );

function getEditors() {
    $.get( "/redaktor/all", function( data ) {

        console.log(data);

        $("#resultEditor").html("");

        if(data) {
            fillEditorSelects(data);
            $.each(data, function (index, value) {
                console.log(index + " : " + value.id + value.firstName + value.lastName);

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
    $.get( "/jobTitles", function( data ) {

        console.log(data);

        $("#resultJobTitle").html("");
        if(data) {
            fillJobTitleSelects(data);
            $.each(data, function (index, value) {

                var testName = "";
                var testID = 0;

                if(value.testList){

                    console.log(value.testList);

                    $.each(value.testList, function(index, test){

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
                    "                    <button id=\'TJD" + value.id + "T" + testID +"\' class=\"btn btn-sm btn-outline-warning detachJobTestButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Odepnij Test</button>\n" +
                    "                    <!--<button id=\"DJ0\" class=\"btn btn-sm btn-outline-default deactivateJobTitleButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Dezaktywuj Stanowisko</button>-->\n" +
                    "                </td></tr>");
            });
        }
    });
}

function getTests() {
    $.get( "/api/tests", function( data ) {

        $("#resultTest").html("");

        if(data) {
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
                    "                    <button id=\'T" + value.id + "\' class=\"btn btn-sm btn-outline-danger deleteTestButton\" type=\"button\"><i class=\"material-icons md-24\">remove_circle_outline</i> Usuń test</button>\n" +
                    "                    <a href=\'" + baseHref + "/tests/" + value.id + "\' class=\"btn btn-sm btn-outline-success\"><i class=\"material-icons md-24\">add_circle_outline</i> Zarządzaj pytaniami</a>\n" +
                    "                </td></tr>");
            });
        }
    });
}

function fillJobTitleSelects(jobTitles) {
    $.each(jobTitles, function (i, item) {
        $('[id*=JobTitleSelect]').append($('<option>', {
            value: JSON.stringify(item),
            text : item.name
        }));
    });
}

function fillEditorSelects(editors) {
    $.each(editors, function (i, item) {
        $('[id*=EditorSelect]').append($('<option>', {
            value: JSON.stringify(item),
            text : item.fullName
        }));
    });
}