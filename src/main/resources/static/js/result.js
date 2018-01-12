function result() {
    $(document).ready(function(){
        $("#customerForm").submit(function(event) {
            event.preventDefault();
            $.ajax({
                type : "GET",
                contentType : "application/json",
                url : '/buy',
                data : $('#customerForm').serialize(),
                dataType : 'json',
                success : function(data) {
                    console.log(data.id_order);
                    console.log(data.id_order);
                    $('table').empty();
                    $('#buy').attr("hidden","true");
                    var row_count = 1;
                    var table = '<caption>List of drugs in our farmacy</caption>' +
                        '<thead>' +
                        '<tr>' +
                        '<th scope="col">#</th>' +
                        '<th scope="col">Number of order</th>' +
                        '<th scope="col">Client</th>' +
                        '<th scope="col">Drug\'s name</th>' +
                        '<th scope="col">Amount</th>' +
                        '<th scope="col">Price</th>' +
                        '<th scope="col">Summ</th>'+
                        '</tr>' +
                        '</thead>' +
                        '<tbody>';

                    table += '<tr><th scope="row">'+ (row_count++) + '</th>' +
                        '<td>' + data.id_order + '</td>' +
                        '<td>' + data.clientName + '</td>'
                    if(data.pharmasyName == 'default'){
                        table +='<td>' + '</td>'
                    }else{
                        table +='<td>' + data.pharmasyName+ '</td>'
                    }

                    var map = data.sells;
                    var count = 0;
                    var summ = 0;
                    for(i in map){
                        if (count !=0 ){
                            summ += map[i].key * map[i].value;
                            table +='</tr><tr><td></td><td></td><td></td><td></td>'+'<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
                        }else{
                            summ += map[i].key * map[i].value;
                            table +='<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
                        }

                        if(count == Object.keys(map).length - 1){
                            table +='</tr><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>' +summ + '</td>';
                        }
                        count++;
                    }
                table +='</tr>';
                table += '</tbody>';
                $('table').append(table);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                }
            });
        });
    });

        // event.preventDefault();
        // $.ajax({
        //     type : "POST",
        //     contentType : "application/json",
        //     url : window.location +"/sale",
        //     data : JSON.stringify(formData),
        //     dataType : 'json',
        //     success : function(json) {
        //         $('main').empty();
        //         var row_count = 1;
        //         var table = '<caption>All orders</caption>' +
        //             '<thead>' +
        //             '<tr>' +
        //             '<th scope="col">#</th>' +
        //             '<th scope="col">Number of order</th>' +
        //             '<th scope="col">Client</th>' +
        //             '<th scope="col">Pharmacist</th>' +
        //             '<th scope="col">Drug\'s name</th>' +
        //             '<th scope="col">Amount</th>' +
        //             '<th scope="col">Price</th>' +
        //             '<th scope="col">Summ</th>'+
        //             '</tr>' +
        //             '</thead>' +
        //             '<tbody>'
        //         for(var k in json){
        //             table += '<tr><th scope="row">'+ (row_count++) + '</th>' +
        //                 '<td>' + json[k].id_order + '</td>' +
        //                 '<td>' + json[k].clientName + '</td>'
        //             if(json[k].pharmasyName == 'default'){
        //                 table +='<td>' + '</td>'
        //             }else{
        //                 table +='<td>' + json[k].pharmasyName+ '</td>'
        //             }
        //
        //             var map = json[k].sells;
        //             var count = 0;
        //             var summ = 0;
        //             for(i in map){
        //                 if (count !=0 ){
        //                     summ += map[i].key * map[i].value;
        //                     table +='</tr><tr><td></td><td></td><td></td><td></td>'+'<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
        //                 }else{
        //                     summ += map[i].key * map[i].value;
        //                     table +='<td>' +i.split(/'/)[1] + '<td>' +map[i].key+ '</td><td>' +map[i].value +'</td><td>'+(map[i].key * map[i].value)+'</td>';
        //                 }
        //
        //                 if(count == Object.keys(map).length - 1){
        //                     table +='</tr><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>' +summ + '</td>';
        //                 }
        //                 count++;
        //             }
        //
        //             table +='</tr>'
        //         }
        //         table += '</tbody>'
        //         $('main').append(table);
        //     },
        //     error : function(e) {
        //         alert("Error!")
        //         console.log("ERROR: ", e);
        //     }
        // });
// http://javasampleapproach.com/java-integration/integrate-jquery-ajax-post-get-spring-boot-web-service
    //https://www.boraji.com/spring-4-mvc-jquery-ajax-form-submit-example
}