/**
 * Created by dima on 12.01.18.
 */

$(document).ready(function () {
    var row_count = 1;
    $.getJSON(
        '/drugstore/all',
        function (json) {
            var table = '<caption>List of drugs in our farmacy</caption>' +
                '<thead>' +
                '<tr>' +
                '<th scope="col">#</th>' +
                '<th scope="col">Drug\'s name</th>' +
                '<th scope="col">Amount</th>' +
                '<th scope="col">Price</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>'
            for(var k in json){
                table +=
                    '<tr>'+
                    '<th scope="row">'+ (row_count++) + '</th>' +
                    '<td><input type="hidden" name = "drugname" value ="' + json[k].drug_name + '" /> ' + json[k].drug_name + '</td>' +
                    '<td><input type="number" id="amount" name="drugamont" value="0" min="0" size="10" placeholder="Amount of goods"  required="true" autofocus="true" /></td>' +
                    '<td><input type="hidden" name="drugprice" value="'+json[k].price+'" /></td>' +
                    '</tr>'

            }
            table += '</tbody>'
            $('table').append(table);
        });

    $('#addNew').click(function(e){
        var row = '<tr><td>'+(row_count++)+'</td>'+
            '<td><input type="text" name = "drugname" placeholder ="Enter new drug name" /></td>'+
            '<td><input type="number" name="drugamont" min="0" size="10" placeholder="Amount of goods"  required="true" autofocus="true" /></td>'+
            '<td><input type="number" name="drugprice" min="0" size="10" placeholder="Price"  required="true" autofocus="true" /></td></tr>';
        $('table').append(row);
    });
});
