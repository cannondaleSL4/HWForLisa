/**
 * Created by dima on 07.01.18.
 */
/**
 * Created by dima on 06.01.18.
 */
$(document).ready(function () {
    $.getJSON(
        '/orders/all',
        function (json) {
            var row_count = 1;
            var table = '<caption>List of drugs in our farmacy</caption>' +
                '<thead>' +
                '<tr>' +
                '<th scope="col">#</th>' +
                '<th scope="col">Number of order</th>' +
                '<th scope="col">Client</th>' +
                '<th scope="col">Pharmacist</th>' +
                '<th scope="col">Drug\'s name</th>' +
                '<th scope="col">Amount</th>' +
                '<th scope="col">Price</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>'
            for(var k in json){
                table += '<tr><th scope="row">'+ (row_count++) + '</th>' +
                    '<td>' + json[k].id_order + '</td>' +
                    '<td>' + json[k].amount + '</td>' +
                    '<td>' + json[k].price+ '</td>' +
                    '</tr>'
            }
            table += '</tbody>'
            $('table').append(table);
        });
});

