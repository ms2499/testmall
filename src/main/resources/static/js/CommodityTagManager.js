function getAll(){
    let dataUrl = remoteUrl + "/tag/getAll"

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
                        '<th scope="col">小類</th>'+
                        '<th scope="col">大類</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody id="tbody">'+
                '</tbody>');

            $.each(res, function(i, n){
                $('#tbody').append(
                    '<tr>'+
                        '<th scope="row">'+
                            '<div class="form-check">'+
                                '<input class="form-check-input" type="checkbox" value="" id="checkbox">'+
                            '</div>'+
                        '</th>'+
                        '<td>'+n.commoditySubTag+'</td>'+
                        '<td>'+n.commodityMainTag+'</td>'+
                    '</tr>');
            })
        },
        error: err => {
            console.log(err)
            $('.table').append("<p>查無資料</p>");
        },
    });
}

$('#insertModal').on('hide.bs.modal', function (event) {
    let formList = $('#insert-sub').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function insertItem(){
    let dataUrl = remoteUrl + "/tag/addTags"
    let jsonData = {
                     commoditySubTag : $('#insert-sub').val(),
                     commodityMainTag : $('#insert-main').val(),
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
            window.alert("新增失敗!")
        },
    });
}

$('#updateModal').on('shown.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length != 1){
        window.alert("請選擇一個商品!")
        $('#updateModal').modal('hide')
        return
    }
})

$('#updateModal').on('show.bs.modal', function (event) {
    let checkbox = $('.form-check-input:checkbox:checked')

    if (checkbox.length == 1){
        let childList = $(checkbox).parents(".form-check").parent().parent().children("td")
        let formList = $('#update-sub').parents("form").children()

        for (let i = 0; i < formList.length; i++){
            $(formList).eq(i).children("input").val($(childList).eq(i).text())
        }
    }
})

$('#updateModal').on('hide.bs.modal', function (event) {
    let formList = $('#update-sub').parents("form").children()

    for (let i = 0; i < formList.length; i++){
        $(formList).eq(i).children("input").val(null)
    }
})

function updateItem(){
    let dataUrl = remoteUrl + "/tag/updateTags"
    let jsonData = { 
                     commoditySubTag : $('#update-sub').val(),
                     commodityMainTag : $('#update-main').val(),
                   }

    $.ajax({
        url: dataUrl,
        method: 'PATCH',
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
    let dataUrl = remoteUrl + "/tag/deleteItem"
    let idList = [];
    let checkboxes = $('.form-check-input:checkbox:checked')

    if (checkboxes.length == 0){
        window.alert("請先勾選要刪除的商品!")
        return
    }

    checkboxes.each(function (i) {
        idList.push($(this).parents(".form-check").parent().next().text());
    });

    $.ajax({
        url: dataUrl,
        method: 'DELETE',
        dataType: 'text',
        data: JSON.stringify(idList),
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