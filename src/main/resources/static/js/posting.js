/**
 * Created by dima on 12.01.18.
 */
$(document).ready(function () {
    $.getJSON(
        '/drugstore/all',
        function (json) {
            var row_count = 1;
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
                    '<tr><th scope="row">'+ (row_count++) + '</th>' + '<td>' + json[k].drug_name + '</td>' +
                    '<td><input type="number" id="amount" name="amount" value="0" min="0" size="10" placeholder="Amount of goods"  required="true" autofocus="true" /></td>' +
                    '<td><input type="hidden" name = "drugname" value ="' + json[k].drug_name + '" /> '+ '</td>' +
                    '<td></td>' +
                    '</tr>'

            }
            table += '</tbody>'
            $('table').append(table);
        });
});
//<input type="text" id="username" name="username" class="form-control" placeholder="User name"  required="true" autofocus="true"></input>