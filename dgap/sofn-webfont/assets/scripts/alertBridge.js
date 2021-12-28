//桥接新的alert库至旧的call方法
window.jBox = {
    tip: function(text, type) {
        swal({
            title: text,
            type: "warning",
            timer: 2000,
            showConfirmButton: false
        });
    },
    confirm: function(title, successText, callback, options) {
        var confirmButtonText = '确认',
            cancelButtonText = '取消',
            confirmButtonColor = '#DD6B55';
        if (options && options.buttons) {
            confirmButtonText = options.buttons['确认'] ? '确认' : '删除';
            confirmButtonColor = options.buttons['确认'] ? '#47b995' : '#DD6B55';
        }
        swal({
            title: title,
            // text: "You will not be able to recover this imaginary file!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: confirmButtonColor,
            cancelButtonText: cancelButtonText,
            confirmButtonText: confirmButtonText,
            closeOnConfirm: false,
            html: false
        }, function(v) {
            callback && callback(v);
            v && swal(successText, "操作已成功!", "success");
        });
    },
    info: function(title, successText, callback) {
        swal({
            title: title,
            text: "",
            type: "info",
            confirmButtonText: "确认",
            closeOnConfirm: true,
            showCancelButton: false
        }, function(v) {
            callback && callback(v);
        });
    }
};


(function($) {
    $.fn.jBox = window.jBox;
    $.jBox = window.jBox;
    swal.setDefaults({ confirmButtonText: '确认', 'cancelButtonText': '取消', confirmButtonColor: '#47b995' });
})(jQuery)
