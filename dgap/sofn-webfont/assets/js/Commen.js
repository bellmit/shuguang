(function() {
    //====================行政区域代码转地域名==============
    $.fn.mycity = (function(i, j, k) {
        var a = function(i, j, o, m) {
            var province = GlobalAreaJson.province;
            $.each(province, function(k, p) {
                var option =
                    "<option value='" +
                    p.province +
                    "'>" +
                    p.name +
                    "</option>";
                $("#" + i).append(option);
            });
            $("#" + i).change(function() {
                var selValue = $(this).val();
                $("#" + j + " option:gt(0)").remove();
                var city = GlobalAreaJson.city;
                $.each(city, function(k, p) {
                    if (p.province == selValue) {
                        var araeID = p.province + p.city;
                        var option =
                            "<option value='" +
                            araeID +
                            "'>" +
                            p.name +
                            "</option>";
                        $("#" + j).append(option);
                    }
                });
                $("#" + j).trigger("change");
            });
            $("#" + j).change(function() {
                var selValue = $(this).val();
                $("#" + o + " option:gt(0)").remove();
                var District = GlobalAreaJson.country;
                $.each(District, function(k, p) {
                    var cityID = p.province + p.city;
                    var cuntryID = p.province + p.city + p.country;
                    if (cityID == selValue) {
                        var option =
                            "<option value='" +
                            cuntryID +
                            "'>" +
                            p.name +
                            "</option>";
                        $("#" + o).append(option);
                    }
                });
            });
            //初始化省市区
            var x = "",
                y = "",
                z = "";
            if (m != null && m != undefined) {
                m = m.toString();
                if (m.length >= 2) {
                    x = m.substring(0, 2);
                }
                if (m.length >= 4) {
                    if (m.substring(2, 4) == "00") {
                        y = "";
                    } else {
                        y = m.substring(0, 4);
                    }
                }
                if (m.length == 6) {
                    if (m.substring(4, 6) == "00") {
                        z = "";
                    } else {
                        z = m;
                    }
                }
                if (m.length > 6) {
                    z = m;
                }
            }
            $("#" + i).val(x);
            $("#" + i).trigger("change");
            $("#" + j).val(y);
            $("#" + j).trigger("change");
            $("#" + o).val(z);
        };
        return a;
    })(jQuery);

    //========================根据编码返回名称=============
    $.fn.Cityname = (function(x) {
        //var myprobinceID= x.substring(0,2)
        //alert(myprobinceID)
        if($.trim(x) == '' ){
            return x;
        }
        var b = function(x) {
            if (!x) {
                return "";
            }
            x = x.toString();
            var myprobince = "",
                mycity = "",
                myDistrict = "";
            var myprobinceID = x.substring(0, 2);
            var mycityID = x.substring(2, 4);
            var myDistrictID = x.substring(4);
            var province = GlobalAreaJson.province;
            $.each(province, function(k, p) {
                if (p.province == myprobinceID) {
                    myprobince = p.name;
                }
            });
            var city = GlobalAreaJson.city;
            var District = GlobalAreaJson.country;
            $.each(city, function(k, p) {
                if (p.province == myprobinceID && mycityID == p.city) {
                    mycity = p.name;
                }
            });
            $.each(District, function(k, p) {
                if (
                    p.province == myprobinceID &&
                    mycityID == p.city &&
                    p.country == myDistrictID
                ) {
                    myDistrict = p.name;
                }
            });
            return myprobince + mycity + myDistrict;
        };
        return b;
    })(jQuery);
    //========通过权限判定是否显示增�?�删、改、查按钮================
    $.fn.iSnotShow = (function(arr) {
        var a = function(arr) {
            if (
                JSON.parse(window.localStorage.getItem("menuObj")).authority !=
                "2"
            ) {
                for (var a = 0; a < arr.length; a++) {
                    $("." + arr[a]).css("display", "none");
                }
            }
        };
        return a;
    })(jQuery);
    //==========通过权限判定是否显示操作栏===========
    $.fn.ifIsShow = (function(arr, data) {
        var a = function(arr, data) {
            if (data == "" && data == undefined && data == null) {
                data = "2";
            }
            if (data == "0") {
                for (var a = 0; a < arr.length; a++) {
                    $("." + arr[a]).css("display", "none");
                }
            }
        };
        return a;
    })(jQuery);
})();
