//***************************************************************
//  CANDIDATE
//***************************************************************

 var candidateLanguage;
 $(document).ready(function() {
 $("#LanguageButtonPL").click(function (){
 candidateLanguage = $("#LanguageButtonPL").attr("data-arg");
   $.get( "/jobTitles/language/" + candidateLanguage, function( data ) {

   var list = document.getElementById("resultJobTitle");
   while (list.hasChildNodes()) {
      list.removeChild(list.firstChild);
   }

        console.log(data);

        $("#resultJobTitle").html("");

        if(data) {

            $.each(data, function (index, value) {

                $("#resultJobTitle").append("<li>"+ value.name + "</li>\n");
            });
        }
    })
    $("#search").prop('disabled', false);
    })

    $("#LanguageButtonEN").click(function (){
     candidateLanguage = $("#LanguageButtonEN").attr("data-arg");
       $.get( "/jobTitles/language/" + candidateLanguage, function( data ) {
       var list = document.getElementById("resultJobTitle");
       while (list.hasChildNodes()) {
          list.removeChild(list.firstChild);
       }
            console.log(data);

            $("#resultJobTitle").html("");

            if(data) {
                $.each(data, function (index, value) {
                  $("#resultJobTitle").append("<li>"+ value.name + "</li>\n");
                        });
                     }
        })
        $("#search").prop('disabled', false);
        })
    });

    function searchFunction() {
      var input, filter, ul, li, a, i, txtValue;
      input = document.getElementById("search");
      filter = input.value.toUpperCase();
      ul = document.getElementById("resultJobTitle");
      li = ul.getElementsByTagName("li");

      for (i = 0; i < li.length; i++) {
        a = li[i];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          li[i].style.display = "";
        } else {
          li[i].style.display = "none";
        }
      }
    }
