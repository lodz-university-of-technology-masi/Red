
editor_api = "/redaktor/";

$(document).ready(function() {

    $("#FormCreateEditorButton").click(function () {

        var dataForm = $('#FormCreateEditor').serializeArray();

        console.log(dataForm);

        $.post(editor_api + dataForm[0].value + "/" + dataForm[1].value,
            function (data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);


            });

    });

    $("#FormUpdateEditorButton").click(function () {

        var dataForm = $('#FormUpdateEditor').serializeArray();

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: editor_api + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value,
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            }
        });

    });

    $(".deleteEditorButton").click(function(event){
        console.log(event.target.id);

        $.ajax({
            type: "DELETE",
            url: editor_api + event.target.id,
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 1000);
            }
        });

    });


});


jobTitle_api = "/jobTitles/";

$(document).ready(function() {

    $("#FormCreateJobTitleButton").click(function () {

        var dataForm = $('#FormCreateJobTitle').serializeArray();

        console.log(dataForm);

        $.post(jobTitle_api + dataForm[0].value + "/" + dataForm[1].value,
            function (data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);


            });

    });

    $("#FormUpdateJobTitleButton").click(function () {

        var dataForm = $('#FormUpdateJobTitle').serializeArray();

        console.log(dataForm);

        $.ajax({
            type: "PUT",
            url: jobTitle_api + dataForm[0].value + "/" + dataForm[1].value + "/" + dataForm[2].value,
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 2000);
            }
        });

    });

    $(".deleteJobTitleButton").click(function(event){
        console.log(event.target.id);

        $.ajax({
            type: "DELETE",
            url: jobTitle_api + event.target.id,
            success: function(data) {
                console.log(data);

                setTimeout(function(){
                    window.location.reload(true);
                }, 1000);
            }
        });

    });


});