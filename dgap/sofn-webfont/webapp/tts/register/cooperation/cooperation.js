var radioId ="";
var isExist = "N",orgisExist= "N";
var myapp = angular.module("myapp", []).controller("cooperation", function ($scope, $http, $state) {
    $.fn.mycity("selectedSiteProvince", "selectedSiteCity", "selectedSiteArea");
    inits($scope);
    var third_json = window.localStorage.getItem("tts_register_thirds");
    if (third_json == null || third_json.length <= 0) {
        hideOrShow("independent");
    } else {

        var first_json = JSON.parse(third_json);

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
        var hideId = "independent";
        if(first_json.radioId){
            $("#"+first_json.radioId).attr("checked",true);
            hideId = first_json.radioId;
        }
        $scope.dateBegin = first_json.businessOperationStart;
        $scope.dateEnd = first_json.businessOperationEnd
        $scope.contactName = first_json.realName;
        $scope.enterpriseName = first_json.enterpriseName;
        $("#creditCode").val(first_json.creditCode);
        $scope.creditCode = first_json.creditCode
        $scope.dateBegin = first_json.businessOperationStart;
        $scope.dateEnd = first_json.businessOperationEnd;
        $scope.annualOutput = first_json.annualOutput;
        $scope.selectedSiteUnit = first_json.unit;
        $scope.address = first_json.address;
        $scope.legalName = first_json.legalName;
        $scope.legalIdNumber = first_json.idcode
        $scope.legalIdNumber = first_json.legalIdnumber;
        $scope.legalPhone = first_json.legalPhone;
        $scope.contactName = first_json.contactName;
        $scope.contactPhone = first_json.contactPhone;
        $scope.contactEmail = first_json.contactEmail;
        $scope.faxNumber = first_json.faxNumber;
        $scope.lat = first_json.latitude;
        $scope.lon = first_json.longitude;
        //$scope.cardType1 = first_json.cardType1;
        $scope.faxNumber = first_json.faxNumber;
        $scope.lat = first_json.latitude
        $scope.lon = first_json.longitude
         //$scope.cardType2 = first_json.cardType2;
        $scope.lonLatName = first_json.lonLatName;
        $("#organizationCode").val(first_json.organizationCode);
        $scope.organizationCode = first_json.organizationCode;

        if (first_json.documentImages1 != null && first_json.documentImages1 != '') {
            $scope.img41 = first_json.documentImages1;
            $("#img41").attr("isUpload", "Y");
        }
        if (first_json.documentImages2 != null && first_json.documentImages2 != '') {
            $scope.img42 = first_json.documentImages2;
            $("#img42").attr("isUpload", "Y");
        }
        if (first_json.legalImages1 != null && first_json.legalImages1 != '') {
            $scope.img43 = first_json.legalImages1;
            $("#img43").attr("isUpload", "Y");
        }
        if (first_json.legalImages2 != null && first_json.legalImages2 != '') {
            $scope.img44 = first_json.legalImages2;
            $("#img44").attr("isUpload", "Y");
        }
        if (first_json.legalImages3 != null && first_json.legalImages3 != '') {
            $scope.img45 = first_json.legalImages3;
            $("#img45").attr("isUpload", "Y");
        }
        hideOrShow(hideId);
    }

    $scope.onChangeIsLong = function ($event) {
        if($scope.isLong){
            $scope.businessOperationStart = null;
            $scope.businessOperationEnd = null;
        }
    };
    $scope.cooperationReNextStep = function () {

        var thirds = {
            "realName": $scope.contactName,
            "enterpriseName": $scope.enterpriseName,
            "creditCode": $scope.creditCode,
            "businessOperationStart": $("input[name='dateBegin']").val(),
            "businessOperationEnd": $("input[name='dateEnd']").val(),
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
            "legalName": $scope.legalName,
            "idcode": $scope.legalIdNumber,
            "legalIdnumber": $scope.legalIdNumber,
            "legalPhone": $scope.legalPhone,
            "legalImages1": $("#img43").attr("src"),
            "legalImages2": $("#img44").attr("src"),
            "legalImages3": $("#img45").attr("src"),
            "contactName": $scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail": $scope.contactEmail,
            "documentImages1": $("#img41").attr("src"),
            "documentImages2": $("#img42").attr("src"),
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "cardType1": $scope.cardType1,
            "cardType2": $scope.cardType2,
            "lonLatName": $("input[name='lonLatName']").val(),
            "radioId" : radioId,
            "organizationCode" : $scope.organizationCode
        };
        thirds = JSON.stringify(thirds);
        var third_json = window.localStorage.setItem("tts_register_thirds", thirds);
        $state.go("tts/register/secondChoose/secondChoose");
    };

    $scope.secondChoose = function () {
        $state.go("tts/register/secondChoose/secondChoose");
    }

    function inits($scope) {
        var tts_register_second = $.parseJSON(window.localStorage.getItem("tts_register_second"));
        var id = tts_register_second.entityIndustry;
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
    //行政区划

    $scope.getRadio = function(){
        radioId = $("input[name='cardType']:checked").attr("id");
        $("#img41").attr("src", "images/register/add.jpg");
        $("#img41").attr("isUpload", "N");
        $("#img42").attr("src", "images/register/add.jpg");
        $("#img42").attr("isUpload", "N");
        hideOrShow(radioId);
    }

    function hideOrShow(id) {
        if("independent" == id){
            $("#img22div").hide();
            $("#img21Text").text("企业证件照");
            $("#orgcode").hide();
        }else{
            $("#img22div").show();
            $("#img21Text").text("营业执照");
            $("#orgcode").show();
            query222();
        }
        query111();
    }

    window.queryByorgCode = function () {
        query222();
    }
    //验证注册号是否已经存在
    window.queryByCreditCode2 = function () {
        query111();
        /*var crad = $("input[name='cardType']:checked").attr("id");
        var code = $("#creditCode").val();
        //下面这段验证不要随便乱改乱注释，记住了！
        if (crad == "independent") {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号不能是空");
                return;
            }
            if(code.length !=18){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号为18位");
                return;
            }
            if (code.length != 18 || !(code.match('^[A-Za-z0-9]+$'))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $("#creditCode").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $scope.flag = false;
                $("#msg").html('');
            }
        } else {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号不能是空！");
                return;
            }
            if(code.length !=10){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号为10位");
                return;
            }
            if (!(/^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{1}$/.test(code))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $scope.flag = false;
                $("#msg").html('');
            }
        }

        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByCreditCode",
            data: $scope.creditCode,
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.isExists == "Y") {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $("#msg").css("display", "block").html("该企业注册号已经存在");
                $scope.flag = true;
                isExist = "Y";
                return false;
            } else {
                $("#creditCode").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
        }).error(function () {
            alert("网络连接的错误异常!");
            return false;
        })*/
    }

    function query111(){
        var crad = $("input[name='cardType']:checked").attr("id");
        var code = $("#creditCode").val();
        //下面这段验证不要随便乱改乱注释，记住了！
        if (crad == "independent") {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号不能是空");
                return;
            }
            if(code.length !=18){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号为18位");
                return;
            }
            if (code.length != 18 || !(code.match('^[A-Za-z0-9]+$'))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $("#creditCode").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $scope.flag = false;
                $("#msg").html('');
            }
        } else {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号不能是空！");
                return;
            }
            if(code.length !=10){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业注册号为10位");
                return;
            }
            if (!(/^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{1}$/.test(code))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $scope.flag = false;
                $("#msg").html('');
            }
        }

        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByCreditCode",
            data: {creditCode: $scope.creditCode,organizationCode:""},
            type: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.isExists == "Y") {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $("#msg").css("display", "block").html("该企业注册号已经存在");
                $scope.flag = true;
                isExist = "Y";
                return false;
            } else {
                $("#creditCode").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
        })
    }
    function query222(){

        var code = $("#organizationCode").val();
        //下面这段验证不要随便乱改乱注释，记住了！

        if(code==null || code == ""){
            $("#organizationCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
            $scope.flag1 = true;
            $("#omsg").css("display", "block").html("组织机构代码不能是空！");
            return;
        }
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByCreditCode",
            data: {creditCode:"",organizationCode:$scope.organizationCode},
            type: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.isExists == "Y") {
                $("#organizationCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $("#omsg").css("display", "block").html("该组织机构代码已经存在");
                $scope.flag1 = true;
                orgisExist = "Y";
                return false;
            } else {
                $("#organizationCode").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $("#omsg").html('');
                $scope.flag1 = false;
                orgisExist = "N";
            }
        })
    }

    $scope.cooperationNextStep = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            //表单校验
            jBox.tip("请填写完整的信息，谢谢！");
            return false;
        }
        if ($("#lon").val() == undefined || $("#lon").val() == null || $("#lon").val() == "" || $("#lat").val() == undefined || $("#lat").val() == null || $("#lat").val() == "") {
            jBox.tip("经纬度为空", 'info');
            return false;
        }
        if($scope.flag == true){
            jBox.tip("此企业注册号有误,请核实重新填写!")
            return false;
        }
        //验证那个注册号是否重复
        if (isExist == "Y") {
            jBox.tip("此企业注册号已经存在,请核实重新填写!")
            return false;
        }
        //营业期限
        if (!$("input[name='dateBegin']").val() || !$("input[name='dateEnd']").val()) {
            jBox.tip("请选择营业期限!")
            return false;
        }
        radioId = $("input[name='cardType']:checked").attr("id");
        //图片信息验证
        if("independent" == radioId){
            if ($("#img41").attr("isUpload") == "N" || $("#img43").attr("isUpload") == "N" || $("#img44").attr("isUpload") == "N" || $("#img45").attr("isUpload") == "N") {
                jBox.tip("请将图片资料上传完整,缺一不可,谢谢!")
                return false;
            }
        }else{
            if ($("#img41").attr("isUpload") == "N" || $("#img42").attr("isUpload") == "N" || $("#img43").attr("isUpload") == "N" || $("#img44").attr("isUpload") == "N" || $("#img45").attr("isUpload") == "N") {
                jBox.tip("请将图片资料上传完整,缺一不可,谢谢!")
                return false;
            }
            if($scope.flag1 == true){
                jBox.tip("此组织机构代码有误,请核实重新填写!")
                return false;
            }
            if (orgisExist == "Y") {
                jBox.tip("此组织机构代码已经存在,请核实重新填写!")
                return false;
            }
        }

        if (!$("#selectedSiteArea option:selected").val()) {
            jBox.tip("所属区域必须选到区县！");
            return false;
        }

        var third = {
            "realName": $scope.contactName,
            "enterpriseName": $scope.enterpriseName,
            "creditCode": $scope.creditCode,
            "businessOperationStart": $("input[name='dateBegin']").val(),
            "businessOperationEnd": $("input[name='dateEnd']").val(),
            "annualOutput": $scope.annualOutput,
            "unit": $("#selectedSiteUnit").val(),
            "unitName": $("#selectedSiteUnit option:selected").text(),
            "area": $("#selectedSiteArea option:selected").val(),
            "address": $scope.address,
            "legalName": $scope.legalName,
            "idcode": $scope.legalIdNumber,
            "legalIdnumber": $scope.legalIdNumber,
            "legalPhone": $scope.legalPhone,
            "legalImages": $("#img43").attr("src") + "," + $("#img44").attr("src") + "," + $("#img45").attr("src"),
            "contactName": $scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail": $scope.contactEmail,
            "documentImages": $("#img41").attr("src") + "," + $("#img42").attr("src"),
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "cardType": $("input[name='cardType']:checked").val(),
            "businessLicenceimg" : $("#img41").attr("src"),
            "organizationCertificateimg" : $("#img42").attr("src"),
            "positiveIdcardeimg" : $("#img43").attr("src"),
            "negativeIdcardimg" : $("#img44").attr("src"),
            "handIdcardimg" : $("#img45").attr("src"),
            "organizationCode" : $scope.organizationCode
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
            if(data.httpCode == "200"){
                $state.go("tts/register/success/success");
                jBox.tip("保存成功");
                //清除缓存
                window.localStorage.removeItem("tts_register_first");
                window.localStorage.removeItem("tts_register_second");
                window.localStorage.removeItem("tts_register_thirds");
            }else{
                $state.go("login/ttsLogin");
                jBox.tip("保存失败");
            }
        })
    };

    //校验样式
    $('#attributeForm').bootstrapValidator();//必须有

    //modal 展示
    $scope.myModal41 = function () {
        $("#myModal41").modal("show");
    }
    $scope.myModal42 = function () {
        $("#myModal42").modal("show");
    }
    //modal 展示
    $scope.myModal43 = function () {
        $("#myModal43").modal("show");
    }
    $scope.myModal44 = function () {
        $("#myModal44").modal("show");
    }
    //modal 展示
    $scope.myModal45 = function () {
        $("#myModal45").modal("show");
    }


    //营业执照===================================================================
    $scope.LunboName = "";
    $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjRegiter/upload";
    $("#file4-1").fileinput({
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
        dropZoneTitle: '营业执照'
    });
    //var response = [];
    $("#file4-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img41").attr("src", "http://" + data.response.path);
            $("#img41").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //组织机构代码证===================================================================
    $("#file4-2").fileinput({
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
        dropZoneTitle: '组织机构代码证'
    });
    //var response = [];
    $("#file4-2").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img42").attr("src", "http://" + data.response.path);
            $("#img42").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });


    //身份证正面===================================================================
    $("#file4-3").fileinput({
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
    $("#file4-3").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img43").attr("src", "http://" + data.response.path);
            $("#img43").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //身份证反面===================================================================
    $("#file4-4").fileinput({
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
    $("#file4-4").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img44").attr("src", "http://" + data.response.path);
            $("#img44").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
    //手持面===================================================================
    $("#file4-5").fileinput({
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
    $("#file4-5").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img45").attr("src", "http://" + data.response.path);
            $("#img45").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
});

