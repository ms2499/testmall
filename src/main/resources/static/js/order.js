$('#insertModal').on('hide.bs.modal', function (event) {
    let formList = $('#insert-name').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function insertItem(){
    let dataUrl = remoteUrl + "/order/insertOrder"
    let jsonData = {
                     OrderNo : 0,
                     OrderAccount : $('#insert-account').val(),
                     OrderDate : $('#insert-date').val(),
                     OrderTotal : Number($('#insert-total').val())
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
            $('#insertModal').modal('hide')
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("新增訂單失敗!")
        },
    });
}

function getAll(){
    let dataUrl = remoteUrl + "/order/getAll"

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
                        '<th scope="col">訂單編號</th>'+
                        '<th scope="col">帳號</th>'+
                        '<th scope="col">日期</th>'+
                        '<th scope="col">總金額</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox-'+n.OrderNo+'">'+
                            '</div>'+
                        '</th>'+
                        '<td>'+n.OrderNo+'</td>'+
                        '<td>'+n.OrderAccount+'</td>'+
                        '<td>'+n.OrderDate+'</td>'+
                        '<td>'+n.OrderTotal+'</td>'+
                    '</tr>');
            })
        },
        error: err => {
            console.log(err)
            $('.table').append("<p>查無資料</p>");
        },
    });
}

$('#updateModal').on('shown.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length != 1){
        window.alert("請選擇一個訂單編號!")
        $('#updateModal').modal('hide')
        return
    }
})

$('#updateModal').on('show.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length == 1){
        let childList = $(checkbox).parents(".form-check").parent().parent().children("td")
        let formList = $('#update-account').parents("form").children()

        for (let i = 0; i < formList.length; i++){
            $(formList).eq(i).children("input").val($(childList).eq(i + 1).text())
        }
    }
})

$('#updateModal').on('hide.bs.modal', function (event) {
    let formList = $('#update-account').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function updateItem(){
    let dataUrl = remoteUrl + "/order/updateOrder"
    let jsonData = {
                     orderNo : Number($('.form-check-input:checkbox:checked').parents(".form-check").parent().next().text()),
                     orderAccount : $('#update-account').val(),
                     orderDate : $('#update-date').val(),
                     orderTotal : Number($('#update-total').val())
                   }

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
            $('#updateModal').modal('hide')
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("更新失敗!")
        },
    });
}

function deleteItem(){
    let dataUrl = remoteUrl + "/order/deleteOrder"
    let no = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的訂單!")
        return
    }

    checkboxes.each(function (i) {
        no.push($(this).parents(".form-check").parent().next().text());
    });

    $.ajax({
        url: dataUrl,
        method: 'POST',
        dataType: 'text',
        data: JSON.stringify(no),
        async: true,
        contentType: 'application/json;charset=utf-8',
        cache: false,

        success: res => {
            window.alert(res)
            getAll()
        },

        error: err => {
            console.log(err)
            window.alert("刪除失敗!")
        },
    });
}