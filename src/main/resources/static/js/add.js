/**
 * Created by dima on 12.01.18.
 */
/**
 * Created by dima on 12.01.18.
 */
function add() {
    $(document).ready(function(){
        $("#addNew").click(function(event) {
            event.preventDefault();
            $.ajax({
                // type : "GET",
                // contentType : "application/json",
                // url : '/add',
                // data : $('#customerForm').serialize(),
                // dataType : 'json',
                success : function(data) {
                    var row = '<tr>'+
                        '<td><input type="number" id="amount" name="amount" value="0" min="0" size="10" placeholder="Amount of goods"  required="true" autofocus="true" /></td>' +
                        '<td><input type="hidden" name = "drugname" value ="' + json[k].drug_name + '" /></td></tr>';
                    $('table').append(row);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        });
    });
}

