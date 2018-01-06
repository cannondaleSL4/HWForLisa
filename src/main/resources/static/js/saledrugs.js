/**
 * Created by dima on 06.01.18.
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
                '<th scope="col">Amount for sale</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>';
            for(var k in json){
                var max =  +json[k].amount;
                table += '<tr><th scope="row">'+ (row_count++) + '</th>' + '<td>' + json[k].drug_name + '</td>' +
                    '<td>' + json[k].amount + '</td>' +
                    '<td>' + json[k].price+ '</td>' +
                    '<td>' +'<input type="number" value="0" min="0" max="' + max + '"/> '+ '</td>' +
                    '</tr>'
            }
            table += '</tbody>';
            $('table').append(table);
        });
});
