var myapp = angular.module("myapp", []).controller("recordLogout", function ($scope, $http, $state) {
    detailView($scope);
    $('#attributeForm').bootstrapValidator();//必须有
    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
    if("三证合一营业执照（无独立组织机构代码证）" == entityInfo.data.cardType){
        $("#img62").attr("hidden","hidden");
    }
    //备案注销
    $scope.addTtsScltxxcjChangeRecord = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        //图片验证
        if ($("#img61").attr("isUpload") == "N" || $("#img62").attr("isUpload") == "N") {
            alert("请将图片资料上传完整,缺一不可,谢谢!")
            return false;
        }
        if(entityInfo.data.approveStatus == "3"){
            jBox.tip("你提交的备案变更申请正在审核中，请耐心等待该审核完成");
            return false;
        }
        if(entityInfo.data.approveStatus == "5"){
            jBox.tip("你提交的撤销申请正在审核中，请耐心等待该审核完成");
            return false;
        }
        if(entityInfo.data.approveStatus == "4"){
            jBox.tip("你提交的注销正在审核中，请勿重复提交申请");
            return false;
        }
        var params = {
            "entityId": entityInfo.data.id,
            "account": entityInfo.data.account,
            "realName": entityInfo.data.realName,
            "idcode": entityInfo.data.idcode,
            "entityScale": entityInfo.data.entityScale,
            "entityType": entityInfo.data.entityType,
            "entityProperty": entityInfo.data.entityProperty,
            "entityIndustry": entityInfo.data.entityIndustry,
            "entityScaleName": entityInfo.data.entityScaleName,
            "entityTypeName": entityInfo.data.entityTypeName,
            "entityPropertyName": entityInfo.data.entityPropertyName,
            "entityIndustryName": entityInfo.data.entityIndustryName,
            "enterpriseName": entityInfo.data.enterpriseName,
            "cardType": entityInfo.data.cardType,
            "creditCode": entityInfo.data.creditCode,
            "businessOperation": entityInfo.data.businessOperation,
            "businessOperationStart": entityInfo.data.businessOperationStart,
            "businessOperationEnd": entityInfo.data.businessOperationEnd,
            "enterpriseIndustry": entityInfo.data.enterpriseIndustry,
            "annualOutput": entityInfo.data.annualOutput,
            "unit": entityInfo.data.unit,
            "unitName": entityInfo.data.unitName,
            "address": entityInfo.data.address,
            "documentImages": entityInfo.data.documentImages,
            "area": entityInfo.data.area,
            "longitude": entityInfo.data.longitude,
            "latitude": entityInfo.data.latitude,
            "legalName": entityInfo.data.legalName,
            "legalIdnumber": entityInfo.data.legalIdnumber,
            "legalPhone": entityInfo.data.legalPhone,
            "legalImages": entityInfo.data.legalImages,
            "faxNumber": entityInfo.data.faxNumber,
            "contactName": entityInfo.data.contactName,
            "contactPhone": entityInfo.data.contactPhone,
            "contactEmail": entityInfo.data.contactEmail,
            "registerTime": entityInfo.data.registerTime,
            "approveStatus": "0",
            "approveOpinion": entityInfo.data.approveOpinion,
            "approveUserIdcode": entityInfo.data.approveUserIdcode,
            "approveName": entityInfo.data.approveName,
            "approveTime": entityInfo.data.approveTime,
            "userIdcode": entityInfo.data.userIdcode,
            "entityIdcode": entityInfo.data.entityIdcode,
            "approveType": "1",
            "reason": $("#recordLogout").val() == null ? "reson" : $("#recordLogout").val(),
            "applyUserId": entityInfo.data.id,
            "applyUpdateName": entityInfo.data.legalName,
            "applyUpdateEntityIdcode": entityInfo.data.entityIdcode,
            "applyUpdateUserIdcode": entityInfo.data.userIdcode
        };
        //请求注销接口
        $http({
            url: "/sofn-tts-web/ttsScltxxcjUserChangeRecord/addTtsScltxxcjUserChangeRecord",
            data: params,
            method: "post",
            //post传输方式   头信息
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function (data) {
            jBox.tip("保存成功");
            //清除缓存
            window.localStorage.removeItem("token");
            window.localStorage.removeItem("entityInfo");
            $state.go("tts/register/success/indexSuccess");
        }).error(function () {
            jBox.tip("保存失败");
            $state.go("login/ttsLogin");
        })

    };
    //modal 展示
    $scope.myModal61 = function () {
        $("#myModal61").modal("show");
    }
    $scope.myModal62 = function () {
        $("#myModal62").modal("show");
    }
    //营业执照===================================================================
    $scope.LunboName = "";
    $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjRegiter/upload";
    $("#file6-1").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        maxFilesNum: 10,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '营业执照'
    });
    //var response = [];
    $("#file6-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img61").attr("src", "http://" + data.response.path);
            $("#img61").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });

    //组织机构代码证===================================================================
    $("#file6-2").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
        maxFilesNum: 10,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传5MB的商品图片",
        dropZoneTitle: '组织机构代码证'
    });
    //var response = [];
    $("#file6-2").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response = data.response.path;
            $("#img62").attr("src", "http://" + data.response.path);
            $("#img62").attr("isUpload", "Y");
            console.log($scope.response);
        }
        return;
    });
    function detailView($scope) {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        if (entityInfo) {
            var imgs = entityInfo.data.documentImages;
            if (imgs != null) {
                var imgsArray = imgs.split(',');
                $("#img61").attr("src", imgsArray[0] == null || imgsArray[0] == "" ? "images/register/add.jpg" : imgsArray[0]);
                $("#img62").attr("src", imgsArray[1] == null || imgsArray[1] == "" ? "images/register/add.jpg" : imgsArray[1]);
                $("#img61").attr("isUpload", imgsArray[0] == null || imgsArray[0] == "" ? "N" : "Y");
                $("#img62").attr("isUpload", imgsArray[1] == null || imgsArray[1] == "" ? "N" : "Y");
            }
        }
    };
});

