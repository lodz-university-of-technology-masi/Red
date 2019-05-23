var selectedText = "";

var cntrlIsPressed = false;

$(document).keydown(function (event) {
    if (event.which == "17")
        cntrlIsPressed = true;
});

$(document).keyup(function () {
    cntrlIsPressed = false;
});


$(document).bind("contextmenu", function (e) {

    if (cntrlIsPressed) {
        e.preventDefault();
        selectedText = getSelection().toString().toLowerCase();
        $("#searchBox").css("left", e.pageX);
        $("#searchBox").css("top", e.pageY);
        $("#searchBox").fadeIn(200, startFocusOut());
    }


});

function startFocusOut() {
    $(document).click(function () {
        $("#searchBox").hide();
        $(document).off("click");
    });
}

$(function () {
    $('#Wiki').click(function () {
        var mytext = selectedText;
        console.log("Wiki search : " + mytext);

        var language = $("#testLanguage").text().toLowerCase();
        var url = 'https://' + language + '.wikipedia.org/w/api.php?action=query&list=search&srsearch=' +
            mytext + '&format=json&origin=*';
        var wikipediaUrl = 'https://' + language + '.wikipedia.org/wiki/';

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: function (response) {
                console.log(response.query.search[0].snippet);
                var pageUrl = wikipediaUrl + response.query.search[0].title;
                console.log(pageUrl);

                $.notify({
                    title: "<b>" + response.query.search[0].title + " : " + response.query.search[0].snippet + "</b>",
                    message: "<br/><br/><a target='_blank' href='" + pageUrl + "'>" + pageUrl + "</a>"
                }, {
                    type: "info",
                    allow_dismiss: true,
                    delay: 0
                });

            },
            error: function (e) {
                console.error(e.responseText);
            }
        });


    });

    $('#Thes').click(function () {
        var mytext = selectedText;
        console.log("Thes search : " + mytext);

        var key = "G71vOpIjAgJllnPPFq3P";

        var language = $("#testLanguage").text().toLowerCase();
        var lang_upper = language.toUpperCase();
        if (lang_upper == "EN") {
            lang_upper = "US";
        }

        var url = 'https://thesaurus.altervista.org/thesaurus/v1?key=' + key + '&output=json&word=' + mytext + '&language=' + language + "_" + lang_upper;
        var ThesaourusUrl = 'https://www.thesaurus.com/browse/' + mytext;

        var postUrl = "";

        if (language == "en") {
            postUrl = "<br/><br/><a target='_blank' href='" + ThesaourusUrl + "'>" + ThesaourusUrl + "</a>";
        }

        $.ajax({
            url: url,
            success: function (data) {

                var synonyms = [];
                for (key in data.response) {
                    var output = data.response[key].list.synonyms + "<br>";
                    var partsOfStr = output.split('|');
                    synonyms.push(partsOfStr[0]);
                    if (partsOfStr[1]) {
                        synonyms.push(partsOfStr[1])
                    }
                    ;
                }

                var synonymString = '';
                for (var i = 0; i < synonyms.length; i++) {
                    synonymString += synonyms[i] + " ";
                }

                $.notify({
                    title: "<b>" + synonymString + "</b>",
                    message: postUrl
                }, {
                    type: "info",
                    allow_dismiss: true,
                    delay: 0
                });

            },
            error: function (e) {
                console.error(e.responseText);
            }
        });

    });

});