$('#AjaxForm').submit(function (event) {
    // HTMLでの送信をキャンセル
    event.preventDefault();
    var $form = $(this);
    var $button = $form.find('.submit');
    $.ajax({
        url: $form.attr('action'),
        type: $form.attr('method'),
        data: $form.serialize(),
        timeout: 10000,  // 単位はミリ秒
        // 送信前
        beforeSend: function (xhr, settings) {
            // ボタンを無効化し、二重送信を防止
            $button.attr('disabled', true);
        },
        // 応答後
        complete: function (xhr, textStatus) {
            // ボタンを有効化し、再送信を許可
            $button.attr('disabled', false);
        },
        // 通信成功時の処理
        success: function (result, textStatus, xhr) {
            // 入力値を初期化
            $form[0].reset();
            $("#result").append(result);
            location.reload();
        },

    });

});

function win_open1(url, name) {

    var obj_window = window.open(url, name, 'width=800,height=500');
    // 親ウィンドウから子ウィンドウへ値を渡す
    /*obj_window.document.getElementById("chl_name").value = document.getElementById("pr_name").innerHTML;
    obj_window.document.getElementById("chl_age").value  = document.getElementById("pr_age").innerHTML;*/
}