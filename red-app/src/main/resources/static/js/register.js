import {reloadWindow} from "./commons/common-functions";
//***************************************************************
//  A P I       R E G I S T E R
//***************************************************************
const register_api = "/register";

$(document).ready(() => {

    $("#FormCreateCandidateButton").click(() => {

        const dataForm = $('#FormCreateCandidate').serializeArray();

        const jsonObject = {
            username: dataForm[0].value,
            email: dataForm[1].value,
            password: dataForm[2].value,
            firstName: dataForm[3].value,
            lastName: dataForm[4].value
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: register_api,
            data: JSON.stringify(jsonObject),
            dataType: 'text',
            success: () => {
                alert("Utworzono konto... Zaraz nastapi przekierowanie.");
                reloadWindow()
            },
            error: (e) => {
                alert("Cos poszło nie tak... Spróbuj ponownie");
                console.error(e.responseText);
            }
        });
    });
});