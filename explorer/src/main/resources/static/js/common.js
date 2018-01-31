$(document).ready(function () {

    $("#search").click(function () {
        var result1 = $("#search_value").val();
        // alert("url = " + "/search?q=" + result1);
        window.location.href = "url = " + "/search?q=" + result1 ;
    });

})