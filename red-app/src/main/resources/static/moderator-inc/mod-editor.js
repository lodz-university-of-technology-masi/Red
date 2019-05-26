import {reloadWindow} from "../js/commons/common-functions";
//***************************************************************
//  A P I       E D I T O R
//***************************************************************

const editor_api = "/api/users";
const register_admin_api = "/register/administrative";

$(document).ready(() => {

    $("#FormCreateEditorButton").click(() => {

        const dataForm = $('#FormCreateEditor').serializeArray();

        const roleName = dataForm[3].value;

        const jsonObject = {
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
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });

    });

    $("#FormUpdateEditorButton").click(() => {

        const dataForm = $('#FormUpdateEditor').serializeArray();

        const jsonObject = {
            id: parseInt(dataForm[0].value),
            username: dataForm[1].value,
            email: dataForm[2].value,
            password: dataForm[3].value,
            roles: [{name: dataForm[4].value, active: true}],
            firstName: dataForm[5].value,
            lastName: dataForm[6].value
        };

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: editor_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });

    $(".deleteEditorButton").click((event) => {

        const editorId = event.target.id;
        const res = editorId.split("-");

        $.ajax({
            type: "DELETE",
            url: editor_api + "/" + res[1],
            success: () => reloadWindow(),
            error: (e) => console.error(e.responseText)
        });
    });


});

function editEditor(editorId) {

    const res = editorId.split("-");

    $.ajax({
        type: "GET",
        url: editor_api + "/" + res[1],
        success: (data) => insertIntoEditorEditModal(data),
        error: (e) => console.error(e.responseText)
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
