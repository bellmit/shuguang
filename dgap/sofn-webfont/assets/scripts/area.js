var areaDtd = $.ajax({
    type: "POST",
    url: "/sofn-sys-web/region/getProvinceAndCityAndCountryList",
    timeout: 30 * 1000
})
    .done(function(res) {
        if (res.httpCode != 200) {
            swal("系统提示", "查询区域信息列表错误", "error");
            return;
        }
        GlobalAreaJson = res.data;
    })
    .fail(function() {
        swal("系统提示", "查询区域信息异常", "error");
    });