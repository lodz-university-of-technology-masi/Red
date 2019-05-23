//***************************************************************
//  A P I       R E G I S T E R
//***************************************************************
register_api = "/register";

$(document).ready(function () {

    $("#FormCreateCandidateButton").click(function () {

        var dataForm = $('#FormCreateCandidate').serializeArray();

        var jsonObject = {};
        jsonObject["username"] = dataForm[0].value;
        jsonObject["email"] = dataForm[1].value;
        jsonObject["password"] = dataForm[2].value;
        jsonObject["firstName"] = dataForm[3].value;
        jsonObject["lastName"] = dataForm[4].value;

        console.log(JSON.stringify(jsonObject));

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: register_api,
            data: JSON.stringify(jsonObject),
            dataType: 'text',
            success: function () {
                alert("Utworzono konto... Zaraz nastapi przekierowanie.");

                setTimeout(function () {
                    window.location.reload();
                }, 200);
            },
            error: function (e) {

                alert("Cos poszło nie tak... Spróbuj ponownie");

                console.error(e.responseText);
            }
        });
    });
});