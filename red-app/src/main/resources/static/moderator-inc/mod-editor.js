
//***************************************************************
//  A P I       E D I T O R
//***************************************************************

editor_api = "/redaktor";

$(document).ready(function() {

    $("#FormCreateEditorButton").click(function () {

        var dataForm = $('#FormCreateEditor').serializeArray();

        var jsonObject = {};
        jsonObject["username"] = dataForm[0].value;
        jsonObject["email"] = dataForm[1].value;
        jsonObject["password"] = dataForm[2].value;
        jsonObject["role"] = dataForm[3].value;
        jsonObject["firstName"] = dataForm[4].value;
        jsonObject["lastName"] = dataForm[5].value;


        console.log(JSON.stringify(jsonObject));
        //console.log(dataForm);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: editor_api,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function(data) {
                console.log("Created: " + data.firstName);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });

    });

    $("#FormUpdateEditorButton").click(function () {

        var dataForm = $('#FormUpdateEditor').serializeArray();

        var jsonObject = {};
        jsonObject["id"] = parseInt(dataForm[0].value);
        jsonObject["username"] = dataForm[1].value;
        jsonObject["email"] = dataForm[2].value;
        jsonObject["password"] = dataForm[3].value;
        jsonObject["role"] = dataForm[4].value;
        jsonObject["firstName"] = dataForm[5].value;
        jsonObject["lastName"] = dataForm[6].value;

        console.log(JSON.stringify(jsonObject));


        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: editor_api + "/" + dataForm[0].value,
            data: JSON.stringify(jsonObject),
            dataType: 'json',
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });

    $(".deleteEditorButton").click(function(event){

        console.log(event.target.id);

        var editorId = event.target.id;
        var res = editorId.split("-");


        $.ajax({
            type: "DELETE",
            url: editor_api + "/" + res[1],
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });



});

function editEditor(id){

    console.log("Pobieranie editora o ID: " + id);

    var editorId = id;
    var res = editorId.split("-");

    $.ajax({
        type: "GET",
        url: editor_api + "/" + res[1],
        success: function(data) {
            console.log(data);

            insertIntoEditorEditModal(data);

        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function insertIntoEditorEditModal(data){

    clearEditorEditModal();

    $("#FUEId").val(data.id);
    $("#FUEUsername").val(data.username);
    $("#FUEMail").val(data.email);
    $("#FUEName").val(data.firstName);
    $("#FUESurname").val(data.lastName);

}

function clearEditorEditModal(){

    $("#FUEId").val("");
    $("#FUEUsername").val("");
    $("#FUEMail").val("");
    $("#FUEName").val("");
    $("#FUESurname").val("");

}
