$(document).ready(function() {
    //var order=[];
    //model.paramOne="${model.order}";
    console.log(order);
    //console.log(model);

});
// /**
//  * Created by dima on 09.01.18.
//  */
// $(document).ready(function () {
//     var personJson = $('#order');
//     console.log(order);
//     // $.getJSON(
//     //     '/orders/all',
//     //     function (json) {
//     //         var row_count = 1;
//     //         var table = '<caption>All orders</caption>' +
//     //             '<thead>' +
//     //             '<tr>' +
//     //             '<th scope="col">#</th>' +
//     //             '<th scope="col">Number of order</th>' +
//     //             '<th scope="col">Client</th>' +
//     //             '<th scope="col">Pharmacist</th>' +
//     //             '<th scope="col">Drug\'s name</th>' +
//     //             '<th scope="col">Amount</th>' +
//     //             '<th scope="col">Price</th>' +
//     //             '<th scope="col">Summ</th>'+
//     //             '</tr>' +
//     //             '</thead>' +
//     //             '<tbody>'
//     //         for(var k in json){
//     //             table += '<tr><th scope="row">'+ (row_count++) + '</th>' +
//     //                 '<td>' + json[k].id_order + '</td>' +
//     //                 '<td>' + json[k].clientName + '</td>'
//     //             if(json[k].pharmasyName == 'default'){
//     //                 table +='<td>' + '</td>'
//     //             }else{
//     //                 table +='<td>' + json[k].pharmasyName+ '</td>'
//     //             }
//     //
//     //             var map = json[k].sells;
//     //             var count = 0;
//     //             var summ = 0;
//     //             for(i in map){
//     //                 if (count !=0 ){
//     //                     summ += map[i].key * map[i].value;
//     //                     table +='</tr><tr><td></td><td></td><td></td><td></td>'+'<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
//     //                 }else{
//     //                     summ += map[i].key * map[i].value;
//     //                     table +='<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
//     //                 }
//     //
//     //                 if(count == Object.keys(map).length - 1){
//     //                     table +='</tr><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>' +summ + '</td>';
//     //                 }
//     //                 count++;
//     //             }
//     //
//     //             table +='</tr>'
//     //         }
//     //         table += '</tbody>'
//     //         $('#allorders').append(table);
//     //     });
// });
