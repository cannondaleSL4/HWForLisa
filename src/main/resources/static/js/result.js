$(document).ready(function() {
    $("#customer_sale").submit(function(event) {
        alert("dsd");
        event.preventDefault();
        ajaxPost();
    });

    function ajaxPost(){
        $('main').empty();
        $.ajax({
            type : "Post",
            contentType : "application/json",
            url : "/sale",
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                alert("result");
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
    }
});