var myapp = angular.module("myapp", []).controller("modyHomeFarm", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")
    $.fn.mycity("selectedSiteProvince","selectedSiteCity","selectedSiteArea");
    inits($scope);
    var third_json = window.localStorage.getItem("tts_register_modythirds");
    if(third_json == null || third_json.length <= 0){
        var modyJson = JSON.parse(window.localStorage.getItem("againreg"));
        var area = modyJson.area;
        if(area){
            modyJson.area1 =area.length>1?area.substr(0,2):"";
            modyJson.area2 =area.length>3?area.substr(0,4):"";
            modyJson.area3 =area.length>5?area.substr(0,6):"";
        }
        third_json = JSON.stringify(modyJson);
    }
        var thirds_json = JSON.parse(third_json);


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
            $scope.realName = thirds_json.realName;
            $scope.enterpriseName = thirds_json.enterpriseName;
            $scope.annualOutput = thirds_json.annualOutput;
            $scope.selectedSiteUnit = thirds_json.unit;
            $scope.address = thirds_json.address
            $scope.legalName = thirds_json.legalName;
            $scope.legalIdNumber = thirds_json.idcode
            $scope.legalIdNumber = thirds_json.legalIdnumber;
            $scope.legalPhone = thirds_json.legalPhone
            $scope.contactName = thirds_json.contactName;
            $scope.contactPhone = thirds_json.contactPhone;
            $scope.contactEmail = thirds_json.contactEmail;
            $scope.faxNumber = thirds_json.faxNumber;
            $scope.lat = thirds_json.latitude;
            $scope.lon = thirds_json.longitude;
            $scope.cardType = thirds_json.cardType;
            $scope.lonLatName = thirds_json.lonLatName;


        if(thirds_json.documentImages1!=null &&thirds_json.documentImages1!=''){
            $scope.img31 = thirds_json.documentImages1;
            $("#img31").attr("isUpload", "Y");
        }
        if(thirds_json.documentImages2!=null &&thirds_json.documentImages2!=''){
            $scope.img32 = thirds_json.documentImages2;
            $("#img32").attr("isUpload", "Y");
        }
        if(thirds_json.legalImages1!=null &&thirds_json.legalImages1!=''){
            $scope.img33 = thirds_json.legalImages1;
            $("#img33").attr("isUpload", "Y");
        }
        if(thirds_json.legalImages2!=null &&thirds_json.legalImages2!=''){
            $scope.img34 = thirds_json.legalImages2;
            $("#img34").attr("isUpload", "Y");
        }
        if(thirds_json.legalImages3!=null &&thirds_json.legalImages3!=''){
            $scope.img35 = thirds_json.legalImages3;
            $("#img35").attr("isUpload", "Y");
        }


    $scope.homeFarmReNextStep=function () {


        var thirds = {

            "realName": $scope.contactName,
            "enterpriseName": $scope.enterpriseName,
            "annualOutput": $scope.annualOutput,
            "unit": $("#selectedSiteUnit").val(),
            "unitName": $("#selectedSiteUnit option:selected").text(),
            "address": $scope.address,
            "legalName":$scope.contactName,
            "idcode": $scope.legalIdNumber,
            "legalIdnumber": $scope.legalIdNumber,
            "legalPhone":$scope.contactPhone,
            "contactName":$scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail":$scope.contactEmail,
            "faxNumber":$scope.faxNumber,
            "latitude":$("#lat").val(),
            "longitude":$("#lon").val(),
            "cardType":$("input[name='cardType']:checked").val(),
            "legalImages1": $("#img33").attr("src"),
            "legalImages2": $("#img34").attr("src"),
            "legalImages3": $("#img35").attr("src"),
            "documentImages1": "",
            "documentImages2": "",
            "lonLatName":$("input[name='lonLatName']").val(),

            "area1": $("#selectedSiteProvince option:selected").val(),
            "areaname1": $("#selectedSiteProvince option:selected").text(),

            "area2": $("#selectedSiteCity option:selected").val(),
            "areaname2": $("#selectedSiteCity option:selected").text(),

            "area3": $("#selectedSiteArea option:selected").val(),
            "areaname3": $("#selectedSiteArea option:selected").text(),
        };
        thirds = JSON.stringify(thirds);
        var third_json = window.localStorage.setItem("tts_register_modythirds",thirds);
        $state.go("tts/register/secondChoose/modySecondChoose");
    };

    function inits($scope) {
        var tts_register_second = $.parseJSON(window.localStorage.getItem("tts_register_modysecond"));
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
            $scope.units=data.data.units;
        }).error(function () {
            alert("单位下拉框加载失败");
        })

    };
    //行政区划
    $scope.homeFarmNextStep = function () {
        //表单校验
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            alert("表单校验失败!");
            return false;
        }
        if($("#lon").val()==undefined||$("#lon").val()==null||$("#lon").val()==""||$("#lat").val()==undefined||$("#lat").val()==null||$("#lat").val()=="" ){
            jBox.tip("经纬度为空", 'info');
            return false;
        }
        //图片信息验证
        if ( $("#img33").attr("isUpload") == "N" || $("#img34").attr("isUpload") == "N" || $("#img35").attr("isUpload") == "N" ) {
            alert("请将图片资料上传完整,缺一不可,谢谢!")
            return false;
        }
        if(!$("#selectedSiteArea option:selected").val()){
            alert("所属区域必须选到区县！");
            return false;
        }
        var third = {
            "account" : first_json.account,
            "realName": $scope.contactName,
            "enterpriseName": $scope.enterpriseName,
            "annualOutput": $scope.annualOutput,
            "unit": $("#selectedSiteUnit").val(),
            "unitName": $("#selectedSiteUnit option:selected").text(),
            "area": $("#selectedSiteArea option:selected").val(),
            "address": $scope.address,
            "legalName":$scope.contactName,
            "idcode": $scope.legalIdNumber,
            "legalIdnumber": $scope.legalIdNumber,
            "legalPhone":$scope.contactPhone,
            "contactName":$scope.contactName,
            "contactPhone": $scope.contactPhone,
            "contactEmail":$scope.contactEmail,
            "faxNumber":$scope.faxNumber,
            "latitude":$("#lat").val(),
            "longitude":$("#lon").val(),
            "cardType":$("input[name='cardType']:checked").val(),
            "legalImages":$("#img33").attr("src")+","+$("#img34").attr("src")+","+$("#img35").attr("src"),
            "documentImages":$("#img31").attr("src")+","+$("#img32").attr("src"),
            "businessLicenceimg" : "",
            "organizationCertificateimg" :"",
            "positiveIdcardeimg" : $("#img33").attr("src"),
            "negativeIdcardimg" : $("#img34").attr("src"),
            "handIdcardimg" : $("#img35").attr("src")
        };
        var second = $.parseJSON (window.localStorage.getItem("tts_register_modysecond"));
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
            $state.go("tts/register/success/success");
            alert("保存成功");
            //清除缓存
            window.localStorage.removeItem("tts_register_modysecond");
            window.localStorage.removeItem("tts_register_modythirds");
            window.localStorage.removeItem("againreg");
        }).error(function () {
            $state.go("login/ttsLogin");
            alert("保存失败");
        })
    };


    //必须有
    $('#attributeForm').bootstrapValidator();//必须有

    //modal 展示
    $scope.myModal31=function(){
        $("#myModal31").modal("show");
    }
    $scope.myModal32=function(){
        $("#myModal32").modal("show");
    }
    //modal 展示
    $scope.myModal33=function(){
        $("#myModal33").modal("show");
    }
    $scope.myModal34=function(){
        $("#myModal34").modal("show");
    }
    //modal 展示
    $scope.myModal35=function(){
        $("#myModal35").modal("show");
    }

    //营业执照===================================================================
    $scope.LunboName = "";
    $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjRegiter/upload";
    $("#file3-1").fileinput({
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
    $("#file3-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img31").attr("src", "http://" + data.response.path);
            $("#img31").attr("isUpload","Y");
            console.log($scope.response);
        }
        return;
    });

    //组织机构代码证===================================================================
    $("#file3-2").fileinput({
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
    $("#file3-2").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img32").attr("src", "http://" + data.response.path);
            $("#img32").attr("isUpload","Y");
            console.log($scope.response);
        }
        return;
    });


    //身份证正面===================================================================
    $("#file3-3").fileinput({
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
    $("#file3-3").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img33").attr("src", "http://" + data.response.path);
            $("#img33").attr("isUpload","Y");
            console.log($scope.response);
        }
        return;
    });

    //身份证反面===================================================================
    $("#file3-4").fileinput({
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
    $("#file3-4").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img34").attr("src", "http://" + data.response.path);
            $("#img34").attr("isUpload","Y");
            console.log($scope.response);
        }
        return;
    });
    //手持面===================================================================
    $("#file3-5").fileinput({
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
    $("#file3-5").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img35").attr("src","http://" +  data.response.path);
            $("#img35").attr("isUpload","Y");
            console.log($scope.response);
        }
        return;
    });
});

