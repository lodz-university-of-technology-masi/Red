
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

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: editor_api + "/" + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value + "/" + dataForm[3].value + "/" + dataForm[4].value + "/" + dataForm[5].value + "/" + dataForm[6].value,
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

        $.ajax({
            type: "DELETE",
            url: editor_api + "/" + editorId[1],
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
