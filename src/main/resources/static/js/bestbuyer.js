/**
 * Created by  lisa on 08.01.18.
 */
$(document).ready(function () {
    $.getJSON(
        '/best/buyer',
        function (json) {
            var row_count = 1;
            var table = '<caption>Best Buyer</caption>' +
                '<thead>' +
                '<tr>' +
                '<th scope="col">#</th>' +
                '<th scope="col">Client</th>' +
                '<th scope="col">Summ</th>'+
                '</tr>' +
                '</thead>' +
                '<tbody>'
            for(var k in json){
                table +='<tr><th scope="row">'+ (row_count++) + '</th>'+
                    '<td>' + k + '</td><td>' +json[k]+'</td></tr>';
            }
                table +='</tr>'
            table += '</tbody>'
            $('#tablebestbuyer').append(table);
        });
});
