
// 画面遷移させないためのコード
$('#AjaxForm').submit(function (event) {
    event.preventDefault();
    var $form = $(this);
    var $button = $form.find('.submit');
    $.ajax({
        url: $form.attr('action'),
        type: $form.attr('method'),
        data: $form.serialize(),
        timeout: 10000,  
        beforeSend: function (xhr, settings) {
            $button.attr('disabled', true);
        },
        complete: function (xhr, textStatus) {
            $button.attr('disabled', false);
        },
        success: function (result, textStatus, xhr) {
            $form[0].reset();
            $("#result").append(result);
            location.reload();
        },
    });
});

// サブウィンドウを開き、URLパラメータに対象のidを埋め込むコード
function win_open(url, name, id) {
    var new_url = url + "?" + "id" + "=" + id;

    wx = 800;
    wy = 500;
    x = (screen.width - wx) / 2;
    y = (screen.height - wy) / 2;
    var obj_window = window.open(new_url, name, "left=" + x + ",top=" + y + ",width=" + wx + ",height=" + wy);

}