function insertItem(){
    let dataUrl = remoteUrl + "/order/insertItem"
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