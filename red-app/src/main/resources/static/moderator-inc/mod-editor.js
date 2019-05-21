//***************************************************************
//  A P I       E D I T O R
//***************************************************************

editor_api = "/api/users";
register_admin_api = "/register/administrative";

$(document).ready(function () {

    $("#FormCreateEditorButton").click(function () {

        var dataForm = $('#FormCreateEditor').serializeArray();

        var roleName = dataForm[3].value;

        var jsonObject = {
            username: dataForm[0].value,
            email: dataForm[1].value,
            password: dataForm[2].value,
            roles: [{name: roleName, active: true}],
            firstName: dataForm[4].value,
            lastName: dataForm[5].value
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: register_admin_api,
            data: JSON.stringify(jsonObject),
            success: function () {

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });

    });

    $("#FormUpdateEditorButton").click(function () {

        var dataForm = $('#FormUpdateEditor').serializeArray();

        var jsonObject = {
            id: parseInt(dataForm[0].value),
            username: dataForm[1].value,
            email: dataForm[2].value,
            password: dataForm[3].value,
            roles: [{name: dataForm[4].value, active: true}],
            firstName: dataForm[5].value,
            lastName: dataForm[6].value
        };

        console.log(JSON.stringify(jsonObject));


        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: editor_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function () {
                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });
    });

    $(".deleteEditorButton").click(function (event) {

        var editorId = event.target.id;
        var res = editorId.split("-");

        $.ajax({
            type: "DELETE",
            url: editor_api + "/" + res[1],
            success: function (data) {
                console.log(data);

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {
                console.error(e.responseText);
            }
        });
    });


});

function editEditor(id) {

    console.log("Pobieranie editora o ID: " + id);

    var editorId = id;
    var res = editorId.split("-");

    $.ajax({
        type: "GET",
        url: editor_api + "/" + res[1],
        success: function (data) {
            console.log(data);

            insertIntoEditorEditModal(data);

        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function insertIntoEditorEditModal(data) {

    clearEditorEditModal();

    $("#FUEId").val(data.id);
    $("#FUEUsername").val(data.username);
    $("#FUEMail").val(data.email);
    $("#FUEName").val(data.firstName);
    $("#FUESurname").val(data.lastName);

}

function clearEditorEditModal() {

    $("#FUEId").val("");
    $("#FUEUsername").val("");
    $("#FUEMail").val("");
    $("#FUEName").val("");
    $("#FUESurname").val("");

}
