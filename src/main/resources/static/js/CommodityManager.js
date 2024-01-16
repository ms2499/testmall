function getAllCom(){
    var dataUrl = "http://localhost:9090/com/getAll"

    $('.table').empty();

    $.ajax({
        url: dataUrl,
        method: 'GET',
        dataType: 'json',
        data: '',
        async: true,      

        success: res => {
            console.log(res)

            $('.table').append(
                '<thead>'+
                    '<tr>'+
                        '<th scope="col"></th>'+
                        '<th scope="col">商品編號</th>'+
                        '<th scope="col">商品名稱</th>'+
                        '<th scope="col">庫存數量</th>'+
                        '<th scope="col">價格</th>'+
                        '<th scope="col">商品分類</th>'+
                        '<th scope="col">圖片位址</th>'+
                        '<th scope="col">詳細資訊</th>'+
                        '<th scope="col">銷售中</th>'+
                        '<th scope="col">折扣</th>'+
                        '<th scope="col">幾折</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.commodityID+'">'+
                            '</div>'+
                        '</th>'+
                        '<td id="com-id">'+n.commodityID+'</td>'+
                        '<td>'+n.commodityName+'</td>'+
                        '<td>'+n.commodityQty+'</td>'+
                        '<td>'+n.commodityPrice+'</td>'+
                        '<td>'+n.commodityTag+'</td>'+
                        '<td>'+n.commodityImgPath+'</td>'+
                        '<td>'+n.commodityDetail+'</td>'+
                        '<td>'+n.commoditySaleFlag+'</td>'+
                        '<td>'+n.commodityDiscount+'</td>'+
                        '<td>'+n.commodityDisRate+'</td>'+
                    '</tr>');
            })
        },
    // <thead>
    //     <tr>
    //         <th scope="col"></th>
    //         <th scope="col">商品編號</th>
    //         <th scope="col">商品名稱</th>
    //         <th scope="col">庫存數量</th>
    //         <th scope="col">價格</th>
    //         <th scope="col">商品分類</th>
    //         <th scope="col">圖片位址</th>
    //         <th scope="col">詳細資訊</th>
    //         <th scope="col">銷售中</th>
    //         <th scope="col">折扣</th>
    //         <th scope="col">幾折</th>
    //     </tr>
    // </thead>
    // <tbody>
    //     <tr>
    //         <th scope="row">
    //             <div class="form-check">
    //                 <input class="form-check-input" type="checkbox" value="" id="checkbox-1">
    //             </div>
    //         </th>
    //         <td>1</td>
    //         <td>Mark</td>
    //         <td>Otto</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdo</td>
    //         <td>@mdd</td>
    //     </tr>
    // </tbody>
        error: err => {
            console.log(err)
            $('.table').append("<p>查無資料</p>");
        },
    });
}


$('#insertModal').on('show.bs.modal', function (event) {
    // // Button that triggered the modal
    // var button = event.relatedTarget
    // // Extract info from data-bs-* attributes
    // var recipient = button.getAttribute('data-bs-whatever')
    // // If necessary, you could initiate an AJAX request here
    // // and then do the updating in a callback.
    // //
    // // Update the modal's content.
    // var modalTitle = insertModal.querySelector('.modal-title')
    // var modalBodyInput = insertModal.querySelector('.modal-body input')

    $('#insert-imgPath').val("static/images/")
    
})

function insertItem(){
    let dataUrl = "http://localhost:9090/com/insertItem"
    let jsonData = { 
                     commodityId : 1,
                     commodityName : $('#insert-name').val(),
                     commodityQty : Number($('#insert-qty').val()),
                     commodityPrice : Number($('#insert-price').val()),
                     commodityTag : $('#insert-tag').val(),
                     commodityImgPath : $('#insert-imgPath').val(),
                     commodityDetail : $('#insert-detail').val(),
                     commoditySaleFlag : Number($('#insert-sale').val()),
                     commodityDiscount : Number($('#insert-discount').val()),
                     commodityDisRate : Number($('#insert-rate').val())
                   }
    console.log(jsonData)

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            getAllCom()
        },

        error: err => {
            console.log(err)
            window.alert("新增失敗!")
        },
    });
}

function deleteItem(){
    let dataUrl = "http://localhost:9090/com/deleteItem"
    let idList = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的商品!")
        return
    }

    checkboxes.each(function (i) {
        idList.push(Number($(this).parents(".form-check").parent().next().text()));
    });

    console.log(idList)

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(idList),
        async: true,      
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            getAllCom()
        },

        error: err => {
            console.log(err)
            window.alert("ajax刪除失敗!")
        },
    });
}