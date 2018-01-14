/**
 * Created by  lisa on 06.01.18.
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
                '<th onclick="sortTable(0)" scope="col">Drug\'s name</th>' +
                '<th onclick="sortTable(1)" scope="col">Amount</th>' +
                '<th onclick="sortTable(2)" scope="col">Price</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>'
            for(var k in json){
                table += '<tr><th scope="row">'+ (row_count++) + '</th>' + '<td>' + json[k].drug_name + '</td>' +
                    '<td>' + json[k].amount + '</td>' +
                    '<td>' + json[k].price+ '</td>' +
                    '</tr>'
            }
            table += '</tbody>'
            $('table').append(table);
        });
});
