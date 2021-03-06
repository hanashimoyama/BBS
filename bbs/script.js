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

function win_open(url, name, id) {


    var new_url = url + "?" + "id" + "=" + id;

    wx = 800;
    wy = 500;
    x = (screen.width - wx) / 2;
    y = (screen.height - wy) / 2;
    var obj_window = window.open(new_url, name, "left=" + x + ",top=" + y + ",width=" + wx + ",height=" + wy);

}