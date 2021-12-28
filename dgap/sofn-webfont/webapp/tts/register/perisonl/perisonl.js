var isExist = "N";
var myapp = angular.module("myapp", []).controller("perisonl", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")
    $.fn.mycity("selectedSiteProvince", "selectedSiteCity", "selectedSiteArea");
    inits($scope);

    var thirds_json = window.localStorage.getItem("tts_register_third");
    if (thirds_json == null || thirds_json <= 0) {

    } else {
        var first_json = JSON.parse(thirds_json);

        if(first_json.area1){
            $('#selectedSiteProvince')
                .find('option[value='+first_json.area1+']')
                .prop('selected', true)
                .end()
                .val(first_json.area1).change();
        }
        if(first_json.area2){
            $('#selectedSiteCity')
                .find('option[value='+first_json.area2+']')
                .prop('selected', true)
                .end()
                .val(first_json.area2).change();
        }
        if(first_json.area3){
            $('#selectedSiteArea')
                .find('option[value='+first_json.area3+']')
                .prop('selected', true)
                .end()
                .val(first_json.area3).change();
        }
        $scope.realName = first_json.realName;
        $scope.idCode = first_json.idcode;
        $scope.realName = first_json.legalName;
        $scope.idCode = first_json.legalIdnumber;
        $scope.contactPhone = first_json.legalPhone;
        $scope.contactPhone = first_json.contactPhone;
        $scope.annualOutput = first_json.annualOutput;
        $scope.selectedSiteUnit = first_json.unit
        $scope.address = first_json.address;
        $scope.contactEmail = first_json.contactEmail;
        $scope.faxNumber = first_json.faxNumber;
        $scope.lat = first_json.latitude;
        $scope.lon = first_json.longitude;
        $scope.lonLatName = first_json.lonLatName;
        if (first_json.legalImages1 != null && first_json.legalImages1 != '') {
            $scope.img11 = first_json.legalImages1;
            $("#img11").attr("isUpload", "Y");
        }
        if (first_json.legalImages2 != null && first_json.legalImages2 != '') {
            $scope.img12 = first_json.legalImages2;
            $("#img12").attr("isUpload", "Y");
        }
        if (first_json.legalImages3 != null && first_json.legalImages3 != '') {
            $scope.img13 = first_json.legalImages3;
            $("#img13").attr("isUpload", "Y");
        }
    }


    $scope.perisonlReNextStep = function () {

        var thirds = {
            "realName": $scope.realName,
            "idcode": $scope.idCode,
            "legalName": $scope.realName,
            "legalIdnumber": $scope.idCode,
            "legalPhone": $scope.contactPhone,
            "contactName" : $scope.realName,
            "contactPhone": $scope.contactPhone,
            "annualOutput": $scope.annualOutput,
            "unit": $("#selectedSiteUnit").val(),
            "unitName": $("#selectedSiteUnit option:selected").text(),
            "area1": $("#selectedSiteProvince option:selected").val(),
            "areaname1": $("#selectedSiteProvince option:selected").text(),
            "area2": $("#selectedSiteCity option:selected").val(),
            "areaname2": $("#selectedSiteCity option:selected").text(),
            "area3": $("#selectedSiteArea option:selected").val(),
            "areaname3": $("#selectedSiteArea option:selected").text(),
            "address": $scope.address,
            "contactEmail": $scope.contactEmail,
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "legalImages1": $("#img11").attr("src"),
            "legalImages2": $("#img12").attr("src"),
            "legalImages3": $("#img13").attr("src"),
            "lonLatName": $("input[name='lonLatName']").val(),
        }
        thirds = JSON.stringify(thirds);
        var third_json = window.localStorage.setItem("tts_register_third", thirds)
        $state.go("tts/register/secondChoose/secondChoose")
    }

    function inits($scope) {
        var tts_register_second = $.parseJSON(window.localStorage.getItem("tts_register_second"));
        var id = tts_register_second.entityIndustry;
        //请求注销接口
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/getUnits",
            data: id,
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.units = data.data.units;
        })

    };

    //验证法人身份证是否已经存在
    window.queryByIdCode = function () {
        //验证企业注册码是否存在
        if (null != $("#idCode").val() && $("#idCode").val().length == 18) {
            $http({
                url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByIdCode",
                data: $scope.idCode,
                method: "post",
                //post传输方式   头信息
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }).success(function (data) {
                if (data.isExists == "Y") {
                    $scope.flag = true;
                    isExist = "Y";
                    return false;
                } else {
                    $scope.flag = false;
                    isExist = "N";
                }
            })
        }
    }
    //申请备案
    $scope.perisonlNextStep = function () {
        //表单校验
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            alert("表单校验失败!");
            return false;
        }
        // if($("#lon").val()==undefined||$("#lon").val()==null||$("#lon").val()==""||$("#lat").val()==undefined||$("#lat").val()==null||$("#lat").val()=="" ){
        //     jBox.tip("经纬度为空", 'info');
        //     return false;
        // }
        //验证那个注册号是否重复
        if (isExist == "Y") {
            alert("法人身份证号已经存在,请核实重新填写!")
            return false;
        }
        //图片信息验证
        if ($("#img11").attr("isUpload") == "N" || $("#img12").attr("isUpload") == "N" || $("#img13").attr("isUpload") == "N") {
            alert("请将图片资料上传完整,缺一不可,谢谢!")
            return false;
        }
        if (!$("#selectedSiteArea option:selected").val()) {
            alert("所属区域必须选到区县！");
            return false;
        }
        var third = {
            "realName": $scope.realName,
            "idcode": $scope.idCode,
            "legalName": $scope.realName,
            "legalIdnumber": $scope.idCode,
            "legalPhone": $scope.contactPhone,
            "contactPhone": $scope.contactPhone,
            "contactName":$scope.realName,
            "annualOutput": $scope.annualOutput,
            "unit": $("#selectedSiteUnit").val(),
            "unitName": $("#selectedSiteUnit option:selected").text(),
            "area": $("#selectedSiteArea option:selected").val(),
            "address": $scope.address,
            "contactEmail": $scope.contactEmail,
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "legalImages": $("#img11").attr("src") + "," + $("#img12").attr("src") + "," + $("#img13").attr("src"),
            "positiveIdcardeimg" : $("#img11").attr("src"),
            "negativeIdcardimg" : $("#img12").attr("src"),
            "handIdcardimg" : $("#img13").attr("src")
        };
        var first = $.parseJSON(window.localStorage.getItem("tts_register_first"));
        var second = $.parseJSON(window.localStorage.getItem("tts_register_second"));
        var third = third;
        var params = {};
        $.extend(params, first, second, third);
        //请求申请备案接口
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/applyRecord",
            data: params,
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            alert("httpCode:" + data.httpCode + "msg:" + data.msg);
            if (data.httpCode == "200") {
                $state.go("tts/register/success/success");
                //清除缓存
                window.localStorage.removeItem("tts_register_first");
                window.localStorage.removeItem("tts_register_second");
            }else{
                $state.go("login/ttsLogin");
                alert("保存失败");
            }
        })
    };

    //必须有
    $('#attributeForm').bootstrapValidator();//必须有


    //modal 展示
    $scope.myModal11 = function () {
        $("#myModal11").modal("show");
    }
    $scope.myModal12 = function () {
        $("#myModal12").modal("show");
    }
    //modal 展示
    $scope.myModal13 = function () {
        $("#myModal13").modal("show");
    }


    //正面===================================================================
    $scope.LunboName = "";
    $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjRegiter/upload";
    $("#file1-1").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        allowedFileExtensions: ['jpg', 'jpeg', 'bmp', 'png'],
        maxFilesNum: 10,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '身份证正面'
    });
    //var response = [];
    $("#file1-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img11").attr("src", "http://" + data.response.path);
            $("#img11").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //反面===================================================================
    $("#file1-2").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        allowedFileExtensions: ['jpg', 'jpeg', 'bmp', 'png'],
        maxFilesNum: 10,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '身份证反面'
    });
    //var response = [];
    $("#file1-2").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img12").attr("src", "http://" + data.response.path);
            $("#img12").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
    //手持面===================================================================
    $("#file1-3").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        allowedFileExtensions: ['jpg', 'jpeg', 'bmp', 'png'],
        maxFilesNum: 10,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '手持身份证'
    });
    //var response = [];
    $("#file1-3").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img13").attr("src", "http://" + data.response.path);
            $("#img13").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
});

