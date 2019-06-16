//***************************************************************
//  CANDIDATE
//***************************************************************

let candidateLanguage;
$(document).ready(() => {
    $("#LanguageButtonPL").click(() => {
        candidateLanguage = $("#LanguageButtonPL").attr("data-arg");
        $.get("/jobTitles?language=" + candidateLanguage, (data) => {
            $("#resultJobTitle").empty();
            $("#resultJobTitle").html("");

            if (data) {
                $.each(data, (index, value) => {
                    $("#resultJobTitle").append("<li>" + value.name + "</li>\n");
                });
            }
        });
        $("#search").prop('disabled', false);
    });

    $("#LanguageButtonEN").click(() => {
        candidateLanguage = $("#LanguageButtonEN").attr("data-arg");
        $.get("/jobTitles?language=" + candidateLanguage, (data) => {
            $("#resultJobTitle").empty();
            $("#resultJobTitle").html("");

            if (data) {
                $.each(data, (index, value) => {
                    $("#resultJobTitle").append("<li class=\"border border-secondary p-3 h6 mb-1 bg-light" +
                        "text-dark text-center btn btn-outline-success btn-lg btn-block \">" + value.name + "</li>\n");
                });
            }
        });
        $("#search").prop('disabled', false);
    })
});

function searchFunction() {
    const input = $("#search");
    const filter = input.val().toUpperCase();
    const ul = $("#resultJobTitle");
    const li = ul.find("li");

    for (let i = 0; i < li.length; i++) {
        let a = li[i];
        let txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}
