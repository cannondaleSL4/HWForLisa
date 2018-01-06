/**
 * Created by dima on 06.01.18.
 */
$(document).ready(function () {
    $.getJSON(
        '/drug/all',
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
                '<tbody>' +
                '<tr>'
            for(var k in json){
                table += '<th scope="row">'+ (row_count++) + '</th>' + '<td>' + k.drug_name + '</td>' +
                    '<td>' + k.amount + '</td>' +
                    '<td>' + k.price+ '</td>' +
                    '</tr>'
            }
            $('table').append(table);
        });
});

//     <table class="table">
//     <caption>List of users</caption>
// <thead>
// <tr>
// <th scope="col">#</th>
// <th scope="col">First</th>
//     <th scope="col">Last</th>
//     <th scope="col">Handle</th>
//     </tr>
//     </thead>
//     <tbody>
//     <tr>
//     <th scope="row">1</th>
//     <td>Mark</td>
//     <td>Otto</td>
//     <td>@mdo</td>
// </tr>
// <tr>
// <th scope="row">2</th>
//     <td>Jacob</td>
//     <td>Thornton</td>
//     <td>@fat</td>
// </tr>
// <tr>
// <th scope="row">3</th>
//     <td>Larry</td>
//     <td>the Bird</td>
// <td>@twitter</td>
// </tr>
// </tbody>
// </table>