var isExist = "N",orgisExist= "N";
var radioId ="";
var myapp = angular.module("myapp", []).controller("modyEnterprise", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")
    //行政区划
    $.fn.mycity("selectedSiteProvince", "selectedSiteCity", "selectedSiteArea");
    inits($scope);
    var third_json = window.localStorage.getItem("tts_register_modythirds");
    if (!third_json || third_json.length <= 0) {
        var modyJson = JSON.parse(window.localStorage.getItem("againreg"));
        var area = modyJson.area;
        if(area){
            modyJson.area1 =area.length>1?area.substr(0,2):"";
            modyJson.area2 =area.length>3?area.substr(0,4):"";
            modyJson.area3 =area.length>5?area.substr(0,6):"";
        }
        var cardType = modyJson.cardType;
        if(cardType){
            var rainput = $("input[value="+cardType+"]");
            modyJson.radioId = rainput? rainput.attr("id"):"";
        }
        third_json = JSON.stringify(modyJson);
    }

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
        $scope.creditCode = first_json.creditCode;
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
        $scope.faxNumber = first_json.faxNumber;
        $scope.lat = first_json.latitude
        $scope.lon = first_json.longitude
        $scope.lonLatName = first_json.lonLatName;
        $("#organizationCode").val(first_json.organizationCode);
        $scope.organizationCode = first_json.organizationCode;

        if (first_json.documentImages1 != null && first_json.documentImages1 != '') {
            $scope.img21 = first_json.documentImages1;
            $("#img21").attr("isUpload", "Y");
        }
        if (first_json.documentImages2 != null && first_json.documentImages2 != '') {
            $scope.img22 = first_json.documentImages2;
            $("#img22").attr("isUpload", "Y");
        }
        if (first_json.legalImages1 != null && first_json.legalImages1 != '') {
            $scope.img23 = first_json.legalImages1;
            $("#img23").attr("isUpload", "Y");
        }
        if (first_json.legalImages2 != null && first_json.legalImages2 != '') {
            $scope.img24 = first_json.legalImages2;
            $("#img24").attr("isUpload", "Y");
        }
        if (first_json.legalImages3 != null && first_json.legalImages3 != '') {
            $scope.img25 = first_json.legalImages3;
            $("#img25").attr("isUpload", "Y");
        }
        hideOrShow(hideId);


    $scope.enterpriseReNextStep = function () {

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
            "legalImages1": $("#img23").attr("src"),
            "legalImages2": $("#img24").attr("src"),
            "legalImages3": $("#img25").attr("src"),
            "contactName": $scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail": $scope.contactEmail,
            "documentImages1": $("#img21").attr("src"),
            "documentImages2": $("#img22").attr("src"),
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "lonLatName": $("input[name='lonLatName']").val(),
            "radioId" : radioId,
            "organizationCode" : $scope.organizationCode
        };
        thirds = JSON.stringify(thirds);
        var third_json = window.localStorage.setItem("tts_register_modythirds", thirds);
        $state.go("tts/register/secondChoose/modySecondChoose");
    };

    function inits($scope) {
        var tts_register_second = $.parseJSON(window.localStorage.getItem("tts_register_modysecond"));
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
        }).error(function () {
            alert("单位下拉框加载失败");
        })

    };

    $scope.getRadio = function(){
        radioId = $("input[name='cardType']:checked").attr("id");
        $("#img21").attr("src", "images/register/add.jpg");
        $("#img21").attr("isUpload", "N");
        $("#img22").attr("src", "images/register/add.jpg");
        $("#img22").attr("isUpload", "N");
        hideOrShow(radioId);
    }

    function hideOrShow(id) {
        if("independent" == id){
            $("#img22div").hide();
            $("#img21Text").text("企业证件照");
            $("#orgcode").hide();
        }else{
            $("#orgcode").show();
            $("#img22div").show();
            $("#img21Text").text("营业执照");
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
    }

    function query111(){
        var crad = $("input[name='cardType']:checked").attr("id");
        var code = $("#creditCode").val();
        //下面这段验证不要随便乱改乱注释，记住了！
        if (crad == "independent") {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业工商注册号不能是空！");
                return;
            }else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
            if (code.length != 18 || !(/^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$/.test(code))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $scope.flag = false;
                isExist = "N";
                $("#msg").html('');
            }
        } else {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.flag = true;
                $("#msg").css("display", "block").html("企业工商注册号不能是空！");
                return;
            }else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
            if (!(/^[0-9]{15}$/.test(code))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $scope.flag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
        }

        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByCreditCode",
            data: {creditCode:$scope.creditCode,organizationCode:""},
            type: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.isExists == "Y") {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $("#msg").css("display", "block").html("该企业工商注册号已经存在");
                $scope.flag = true;
                isExist = "Y";
                return false;
            } else {
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $("#msg").html('');
                $scope.flag = false;
                isExist = "N";
            }
        }).error(function () {
            alert("网络连接的错误异常!");
            return false;
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
        }else{
            $("#organizationCode").css("border-color", "#c5c5c5").css("box-shadow", "");
            $("#omsg").html('');
            $scope.flag = false;
            isExist = "N";
        }

        if (!(/^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/.test(code))) {
            $("#organizationCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
            $scope.flag = true;
            $("#omsg").css("display", "block").html("不符合所选证件类型规范!");
            return;
        } else {
            $("#organizationCode").css("border-color", "#c5c5c5").css("box-shadow", "");
            $("#omsg").html('');
            $scope.flag = false;
            isExist = "N";
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
                $("#organizationCode").css("border-color", "#c5c5c5").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $("#omsg").html('');
                $scope.flag1 = false;
                orgisExist = "N";
            }
        }).error(function () {
            alert("网络连接的错误异常!");
            return false;
        })
    }
    //验证注册号是否已经存在
    window.queryByCreditCode1 = function () {
        //验证企业注册码是否存在
        if (null != $("#creditCode").val() && $("#creditCode").val().length == 18) {
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
                    $scope.flag = true;
                    isExist = "Y";
                    return false;
                } else {
                    $scope.flag = false;
                    isExist = "N";
                }
            }).error(function () {
                alert("网络连接的错误异常!");
                return false;
            })
        }
    }

    $scope.enterpriseNextStep = function () {
        // if($("#lon").val()==undefined||$("#lon").val()==null||$("#lon").val()==""||$("#lat").val()==undefined||$("#lat").val()==null||$("#lat").val()=="" ){
        //     jBox.tip("经纬度为空", 'info');
        //     return false;
        // }
        //表单校验
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            alert("请填写完整的信息，谢谢！");
            return false;
        }
        if($scope.flag == true){
            alert("此企业工商注册号有误,请核实重新填写!")
            return false;
        }
        //验证那个注册号是否重复
        if (isExist == "Y") {
            alert("此企业工商注册号已经存在,请核实重新填写!")
            return false;
        }
        //营业期限
        if (!$("input[name='dateBegin']").val() || !$("input[name='dateEnd']").val()) {
            alert("请选择营业期限!")
            return false;
        }
        //图片信息验证
        var indpent = $("input[name='cardType']:checked").attr("id");
        if(indpent && "independent" == indpent){
            if ($("#img21").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" || $("#img24").attr("isUpload") == "N" || $("#img25").attr("isUpload") == "N") {
                alert("请将图片资料上传完整,缺一不可,谢谢!")
                return false;
            }
        }else{
            if ($("#img21").attr("isUpload") == "N" || $("#img22").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" || $("#img24").attr("isUpload") == "N" || $("#img25").attr("isUpload") == "N") {
                alert("请将图片资料上传完整,缺一不可,谢谢!")
                return false;
            }
            if($scope.flag1 == true){
                alert("此组织机构代码有误,请核实重新填写!")
                return false;
            }
            if (orgisExist == "Y") {
                alert("此组织机构代码已经存在,请核实重新填写!")
                return false;
            }
        }
        if (!$("#selectedSiteArea option:selected").val()) {
            alert("所属区域必须选到区县！");
            return false;
        }

        var third = {
            "account" : first_json.account,
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
            "legalImages": $("#img23").attr("src") + "," + $("#img24").attr("src") + "," + $("#img25").attr("src"),
            "contactName": $scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail": $scope.contactEmail,
            "documentImages": $("#img21").attr("src") + "," + $("#img22").attr("src"),
            "faxNumber": $scope.faxNumber,
            "latitude": $("#lat").val(),
            "longitude": $("#lon").val(),
            "cardType": $("input[name='cardType']:checked").val(),
            "lonLatName": $scope.lonLatName,
            "businessLicenceimg" : $("#img21").attr("src"),
            "organizationCertificateimg" : $("#img22").attr("src"),
            "positiveIdcardeimg" : $("#img23").attr("src"),
            "negativeIdcardimg" : $("#img24").attr("src"),
            "handIdcardimg" : $("#img25").attr("src"),
            "organizationCode" : $scope.organizationCode

        };
        var second = $.parseJSON(window.localStorage.getItem("tts_register_modysecond"));
        var third = third;
        var params = {};
        $.extend(params, second, third);
        //请求申请备案接口
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/updateRecord",
            data: params,
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.httpCode == '200') {
                // alert("申请备案成功!");
                $state.go("tts/register/success/success");
                //清除缓存
                window.localStorage.removeItem("tts_register_modysecond");
                window.localStorage.removeItem("tts_register_modythirds");
                window.localStorage.removeItem("againreg");
            } else {
                alert("申请备案失败!");
            }

        }).error(function () {
            $state.go("login/ttsLogin");
            alert("保存失败");
        })
    };

    //必须有
    $('#attributeForm').bootstrapValidator();//必须有

    //modal 展示
    $scope.myModal21 = function () {
        $("#myModal21").modal("show");
    }
    $scope.myModal22 = function () {
        $("#myModal22").modal("show");
    }
    //modal 展示
    $scope.myModal23 = function () {
        $("#myModal23").modal("show");
    }
    $scope.myModal24 = function () {
        $("#myModal24").modal("show");
    }
    //modal 展示
    $scope.myModal25 = function () {
        $("#myModal25").modal("show");
    }


    //营业执照===================================================================
    $scope.LunboName = "";
    $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjRegiter/upload";
    $("#file2-1").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        allowedFileExtensions: ['jpg', 'jpeg', 'bmp', 'png'],
        maxFilesNum: 10,
        allowedFileExtensions: ['jpg', 'gif', 'png'],
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '营业执照'
    });
    //var response = [];
    $("#file2-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img21").attr("src", "http://" + data.response.path);
            $("#img21").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //组织机构代码证===================================================================
    $("#file2-2").fileinput({
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
    $("#file2-2").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img22").attr("src", "http://" + data.response.path);
            $("#img22").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });


    //身份证正面===================================================================
    $("#file2-3").fileinput({
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
    $("#file2-3").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img23").attr("src", "http://" + data.response.path);
            $("#img23").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //身份证反面===================================================================
    $("#file2-4").fileinput({
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
    $("#file2-4").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img24").attr("src", "http://" + data.response.path);
            $("#img24").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
    //手持面===================================================================
    $("#file2-5").fileinput({
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
    $("#file2-5").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img25").attr("src", "http://" + data.response.path);
            $("#img25").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });



});

