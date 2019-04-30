//***************************************************************
//  CANDIDATE
//***************************************************************

 var candidateLanguage;
 $(document).ready(function() {
 $("#LanguageButtonPL").click(function (){
 candidateLanguage = $("#LanguageButtonPL").attr("data-arg");
   $.get( "/jobTitles/language=" + candidateLanguage, function( data ) {

   $("#resultJobTitle").empty();

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
       $.get( "/jobTitles/language=" + candidateLanguage, function( data ) {
       $("#resultJobTitle").empty();

            $("#resultJobTitle").html("");

            if(data) {
                $.each(data, function (index, value) {
                  $("#resultJobTitle").append("<li class=\"border border-secondary p-3 h6 mb-1 bg-light" +
                   "text-dark text-center btn btn-outline-success btn-lg btn-block \">"+ value.name + "</li>\n");
                        });
                     }
        })
        $("#search").prop('disabled', false);
        })
    });

    function searchFunction() {
      var input, filter, ul, li, a, i, txtValue;
      input = $("#search");
      filter = input.val().toUpperCase();
      ul = $("#resultJobTitle");
      li = ul.find("li");

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
