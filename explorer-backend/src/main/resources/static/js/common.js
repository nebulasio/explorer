$(document).ready(function () {

    $("#search").click(function (e) {
        e.preventDefault();
        var result1 = $("#search_value").val();
        window.location.href = "/search?q=" + result1 ;
    });

})