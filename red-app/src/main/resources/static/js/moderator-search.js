let selectedText = "";

let cntrlIsPressed = false;

$(document).keydown((event) => {
    if (event.which == "17")
        cntrlIsPressed = true;
});

$(document).keyup(() => {
    cntrlIsPressed = false;
});


$(document).bind("contextmenu", (e) => {

    if (cntrlIsPressed) {
        e.preventDefault();
        selectedText = getSelection().toString().toLowerCase();
        $("#searchBox").css("left", e.pageX);
        $("#searchBox").css("top", e.pageY);
        $("#searchBox").fadeIn(200, startFocusOut());
    }


});

function startFocusOut() {
    $(document).click(() => {
        $("#searchBox").hide();
        $(document).off("click");
    });
}

$(() => {
    $('#Wiki').click(() => {
        const mytext = selectedText;

        const language = $("#testLanguage").text().toLowerCase();
        const url = 'https://' + language + '.wikipedia.org/w/api.php?action=query&list=search&srsearch=' +
            mytext + '&format=json&origin=*';
        const wikipediaUrl = 'https://' + language + '.wikipedia.org/wiki/';

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: url,
            success: (response) => {
                const pageUrl = wikipediaUrl + response.query.search[0].title;

                $.notify({
                    title: "<b>" + response.query.search[0].title + " : " + response.query.search[0].snippet + "</b>",
                    message: "<br/><br/><a target='_blank' href='" + pageUrl + "'>" + pageUrl + "</a>"
                }, {
                    type: "info",
                    allow_dismiss: true,
                    delay: 0
                });
            },
            error: (e) => console.error(e.responseText)
        });
    });

    $('#Thes').click(() => {
        const mytext = selectedText;

        let key = "G71vOpIjAgJllnPPFq3P";

        const language = $("#testLanguage").text().toLowerCase();
        let lang_upper = language.toUpperCase();
        if (lang_upper === "EN") {
            lang_upper = "US";
        }

        const url = 'https://thesaurus.altervista.org/thesaurus/v1?key=' + key + '&output=json&word=' + mytext + '&language=' + language + "_" + lang_upper;
        const ThesaourusUrl = 'https://www.thesaurus.com/browse/' + mytext;

        let postUrl = "";

        if (language === "en") {
            postUrl = "<br/><br/><a target='_blank' href='" + ThesaourusUrl + "'>" + ThesaourusUrl + "</a>";
        }

        $.ajax({
            url: url,
            success: (data) => {

                const synonyms = [];
                for (key in data.response) {
                    const output = data.response[key].list.synonyms + "<br>";
                    const partsOfStr = output.split('|');
                    synonyms.push(partsOfStr[0]);
                    if (partsOfStr[1]) {
                        synonyms.push(partsOfStr[1])
                    }
                }

                let synonymString = '';
                for (let i = 0; i < synonyms.length; i++) {
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
            error: (e) => console.error(e.responseText)
        });
    });
});