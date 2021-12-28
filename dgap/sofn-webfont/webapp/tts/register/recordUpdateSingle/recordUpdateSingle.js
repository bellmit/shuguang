var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
var myapp = angular.module("myapp", []).controller("recordUpdateSingle", function ($scope, $http, $state) {
    $scope.mainTip=function(obj){
        var content = "生产主体：仅从事农产品种植/养殖"
        $("#"+obj).popover({
            animation:true,
            html:true,
            placement:'right',
            trigger:'hover',
            content:"生产主体：仅从事农产品种植/养殖<br/>经营主体：仅从事农产品收购、仓储等<br/>生产经营主体：从事农产品种植/养殖、收购、仓储等",
            delay:0,
            container:'body'
        });
        $("#"+obj).popover("show");
    }
    inits($scope);
    $scope.bean = {};
    $scope.bean.ztProperty = {};
    $scope.bean.industryType={};
    function inits($scope) {
        //获取主体字典
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/getEntityTypes",
            data: "",
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.industrys=data.data.industrys;
            $scope.subjPropertys=data.data.subjPropertys;
            $scope.subTypes=data.data.subTypes;
            $scope.orgs=data.data.orgs;
        }).error(function () {
            jBox.tip("加载失败");
        })

    };
    var flag3=false,flag4=false,flag5=false;
    //加载初始备案信息
    detailView($scope);
    window.repeatCheck = function () {
            $http({
                url: "/sofn-tts-web/ttsScltxxcjRegiter/getRegiterByAccount",
                data: entityInfo.data.account,
                method: "post",
                //post传输方式   头信息
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }).success(function (data) {
                if (data.vo.approveStatus == "3") {
                    isExist = true;
                    $scope.flag=true;
                    return false;
                } else {
                    isExist = false;
                    $scope.flag=false;
                }
            }).error(function () {
                alert("网络连接的错误异常!");
                return false;
            })
    };

    //备案注销
    $scope.addTtsScltxxcjChangeRecordApply = function () {
        //验证
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        if(entityInfo.data.approveStatus == "3"){
            jBox.tip("你提交的备案变更申请正在审核中，请勿重复提交申请");
            return false;
        }
        if(entityInfo.data.approveStatus == "5"){
            jBox.tip("你提交的撤销申请正在审核中，请耐心等待该审核完成");
            return false;
        }
        if(entityInfo.data.approveStatus == "4"){
            jBox.tip("你提交的注销正在审核中，请耐心等待该审核完成");
            return false;
        }
        var updateContent = getUpdateContent();
        if(entityInfo==null){
            jBox.tip("你已注销!");
            return;
        }

        if(!$("#selectedSiteArea option:selected").val()){
            jBox.tip("所属区域请选择完整");
            return false;
        }
        //获取主体属性
        var ztPropertys = $scope.bean.ztProperty;
        var checkedZtPropertys = [];
        for( ztProperty in ztPropertys){
            if(ztProperty.length && ztPropertys[ztProperty]){
                checkedZtPropertys[checkedZtPropertys.length] = ztProperty;
            }
        }
        var ztpnames = $scope.subjPropertys;
        var ztpdmap = {};
        for(var ztp in ztpnames){
            ztpdmap[ztpnames[ztp].id] = ztpnames[ztp].dictName;
        }
        var ztPropertyNames = [];
        for(zz in checkedZtPropertys){
            ztPropertyNames.push(ztpdmap[checkedZtPropertys[zz]]);
        }
        var checkedZtProperty = checkedZtPropertys.join(',');
        var checkedZtPropertyName = ztPropertyNames.join(',');
        //获取行业
        var industry=$scope.bean.industryType;
        var industrys=[];
        for (industryType in industry){
            if(industryType.length && industry[industryType]){
                industrys[industrys.length]=industryType;
            }
        }
        var indnames = $scope.industrys;
        var indmap = {};
        for(var ind in indnames){
            indmap[indnames[ind].id] = indnames[ind].dictName;
        }
        var industrysNames = [];
        for(aa in industrys){
            industrysNames.push(indmap[industrys[aa]]);
        }
        var checkedIndustryType = industrys.join(',');
        var checkedIndustryTypeName = industrysNames.join(',');
        var params = {
            "entityId":entityInfo.data.id,
            "realName":entityInfo.data.contactName,
            "idcode":entityInfo.data.idcode,
            "entityScale":entityInfo.data.entityScale,
            "entityType":$("input[name='ztType']:checked").val(),
            "entityProperty":checkedZtProperty,
            "entityIndustry":checkedIndustryType,
            "entityScaleName":$scope.entityScaleName,
            "entityTypeName":$("input[name='ztType']:checked").next().text(),
            "entityPropertyName":checkedZtPropertyName,
            "entityIndustryName":checkedIndustryTypeName,
            "cardType":$("input[name='cardType']:checked").val()?$("input[name='cardType']:checked").val():entityInfo.data.entityType,
            "businessOperation":entityInfo.data.businessOperation,
            "businessOperationStart":entityInfo.data.businessOperationStart,
            "businessOperationEnd":entityInfo.data.businessOperationEnd,
            "enterpriseIndustry":entityInfo.data.enterpriseIndustry,
            "annualOutput":entityInfo.data.annualOutput,
            "unit":entityInfo.data.unit,
            "unitName":entityInfo.data.unitName,
            "longitude":entityInfo.data.longitude,
            "latitude":entityInfo.data.latitude,
            "legalIdnumber":$scope.legalIdnumber,
            "legalImages":entityInfo.data.legalImages,
            "faxNumber":entityInfo.data.faxNumber,
            "contactEmail":$scope.contactEmail,
            "registerTime":entityInfo.data.registerTime,
            "approveStatus":"0",
            "approveOpinion":"",
            "approveUserIdcode":entityInfo.data.approveUserIdcode,
            "approveName":entityInfo.data.approveName,
            "approveTime":entityInfo.data.approveTime,
            "userIdcode":entityInfo.data.userIdcode,
            "entityIdcode":entityInfo.data.entityIdcode,
            "applyUserId":entityInfo.data.id,
            "applyUpdateName":entityInfo.data.legalName,
            "applyUpdateEntityIdcode":entityInfo.data.entityIdcode,
            "applyUpdateUserIdcode":entityInfo.data.userIdcode,

            "approveType":"2",
            "reason": $("#recordLogout").val()==null?"reson":$("#recordLogout").val(),
            "legalName" : $scope.legalName,
            "legalPhone":$scope.legalPhone,
            "contactName":$scope.contactName,
            "contactPhone":$scope.contactPhone,
            "creditCode":$scope.ZZJGDMZ==null?entityInfo.data.creditCode:$scope.ZZJGDMZ,
            "area": $("#selectedSiteArea option:selected").val(),
            "enterpriseName":$scope.enterpriseName==null?entityInfo.data.enterpriseName:$scope.enterpriseName,
            "address":$scope.address==null?entityInfo.data.address:$scope.address,
            "account":$scope.accounts==null?entityInfo.data.account:$scope.accounts,
            "positiveIdcardeimg" : $("#img23").attr("src")&&$("#img23").attr("isUpload") == "Y"?$("#img23").attr("src"):entityInfo.data.positiveIdcardeimg,
            "negativeIdcardimg" :  $("#img24").attr("src")&&$("#img24").attr("isUpload") == "Y"?$("#img24").attr("src"):entityInfo.data.negativeIdcardimg,
            "handIdcardimg" :  $("#img25").attr("src")&&$("#img25").attr("isUpload") == "Y"?$("#img25").attr("src"):entityInfo.data.handIdcardimg,
            //=================变更字段====================
            "documentImages":$("#img61").attr("src")+","+$("#img62").attr("src"),
            "updateContent":updateContent,
            "updateBeforeJson":getBeforeJson()
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
            if(data.httpCode=="200"){
                $state.go("tts/register/success/success");
            }else{
                jBox.tip("保存失败");
                $state.go("login/logintts");
            }
        })
    };
    //获取更新后的内容
    function getUpdateContent() {
        var updateContent ="";
        if(entityInfo.data.area!=$("#selectedSiteArea option:selected").val()){
            updateContent+="省份变更为:"+$("#selectedSiteArea option:selected").val()+";";
            flag = true;
        }
        if(entityInfo.data.address!=$scope.address){
            updateContent+="主体地址变更为:"+$scope.address+";";
            flag = true;
        }
        if(entityInfo.data.legalName!=$scope.legalName){
            updateContent+="法人联系人变更为:"+$scope.legalName+";";
        }
        if(entityInfo.data.legalPhone!=$scope.legalPhone){
            updateContent+="法人联系方式变更为:"+$scope.legalPhone+";";
        }
        if(entityInfo.data.legalIdnumber!=$scope.legalIdnumber){
            updateContent+="法人身份证变更为:"+$scope.legalIdnumber+";";
        }
        if(flag3==true){
            updateContent+="身份证正面已上传新的附件";
        }
        if(flag4==true){
            updateContent+="身份证反面已上传新的附件";
        }
        if(flag5==true){
            updateContent+="手持身份证已上传新的附件";
        }
        return updateContent ;
    }
    //获取之前的JSON
    function getBeforeJson() {
        var jsonBefore =
        {
            "id":entityInfo.data.id,
            "area":entityInfo.data.area,
            "address":entityInfo.data.address,
            "enterpriseName":entityInfo.data.enterpriseName,
            "contactPhone":entityInfo.data.contactPhone,
            "contactName":entityInfo.data.contactName,
            "creditCode":entityInfo.data.creditCode==null?entityInfo.data.legalIdnumber:entityInfo.data.creditCode,
            "realName":entityInfo.data.realName,
            "idcode":entityInfo.data.idcode,
            "entityScaleName":entityInfo.data.entityScaleName,
            "entityTypeName":entityInfo.data.entityTypeName,
            "entityPropertyName":entityInfo.data.entityPropertyName,
            "entityIndustryName":entityInfo.data.entityIndustryName,
            "cardType":entityInfo.data.entityType,
            "businessOperation":entityInfo.data.businessOperation,
            "businessOperationStart":entityInfo.data.businessOperationStart,
            "businessOperationEnd":entityInfo.data.businessOperationEnd,
            "enterpriseIndustry":entityInfo.data.enterpriseIndustry,
            "annualOutput":entityInfo.data.annualOutput,
            "unit":entityInfo.data.unit,
            "unitName":entityInfo.data.unitName,
            "documentImages":entityInfo.data.documentImages,
            "longitude":entityInfo.data.longitude,
            "latitude":entityInfo.data.latitude,
            "legalName":entityInfo.data.legalName,
            "legalPhone":entityInfo.data.legalPhone,
            "legalIdnumber":$scope.legalIdnumber,
            "legalImages":entityInfo.data.legalImages,
            "faxNumber":entityInfo.data.faxNumber,
            "contactEmail":entityInfo.data.contactEmail,
            "registerTime":entityInfo.data.registerTime,
            "userIdcode":entityInfo.data.userIdcode,
            "entityIdcode":entityInfo.data.entityIdcode,
            "businessLicenceimg" : entityInfo.data.businessLicenceimg,
            "organizationCertificateimg" : entityInfo.data.organizationCertificateimg,
            "positiveIdcardeimg" : entityInfo.data.positiveIdcardeimg,
            "negativeIdcardimg" : entityInfo.data.negativeIdcardimg,
            "handIdcardimg" : entityInfo.data.handIdcardimg,
            "isLong":entityInfo.data.isLong
        }
        return  jsonBefore =JSON.stringify(jsonBefore);
    }



    $('#attributeForm').bootstrapValidator();//必须有

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
    //身份证正面===================================================================
    $("#file2-3").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 5300,
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
            flag3 =true;
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
            flag4 = true;
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
            flag5 = true;
            console.log($scope.response);
        }
        return;
    });
    function detailView($scope) {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        $scope.bean.userType=entityInfo.data.entityScale;
        $scope.bean.ztType=entityInfo.data.entityType;
        //主体类型
        var entityPropertys = entityInfo.data.entityProperty.split(",");
        entityPropertys.map(function(entityProperty){
            $scope.bean.ztProperty[entityProperty] = true;
        });
        //所属行业
        var industry = entityInfo.data.entityIndustry.split(",");
        industry.map(function (entityIndustry) {
            $scope.bean.industryType[entityIndustry]=true;
        })
        if (entityInfo) {
            if(entityInfo.data.entityScaleName == "家庭农场"){
                $("#entity").show();
                $scope.entityScaleName = entityInfo.data.enterpriseName;
            }
            if(entityInfo.data.entityScaleName == "个人"){
                $("#entity").hide();
            }
            $scope.longitude = entityInfo.data.longitude;
            $scope.latitude = entityInfo.data.latitude;
            $scope.lonLatName = entityInfo.data.address;
            $scope.legalName = entityInfo.data.legalName;
            $scope.legalIdnumber = entityInfo.data.legalIdnumber;
            $scope.legalPhone = entityInfo.data.legalPhone;
            $scope.address = entityInfo.data.address;
            $scope.contactPhone = entityInfo.data.contactPhone;
            $scope.contactEmail = entityInfo.data.contactEmail;
            var area =  entityInfo.data.area;
            $.fn.mycity("selectedSiteProvince","selectedSiteCity","selectedSiteArea",area);
            $("#imgOriginal3").attr("src", !entityInfo.data.positiveIdcardeimg? "images/register/add.jpg" : entityInfo.data.positiveIdcardeimg);
            $("#imgOriginal4").attr("src", !entityInfo.data.negativeIdcardimg? "images/register/add.jpg" : entityInfo.data.negativeIdcardimg);
            $("#imgOriginal5").attr("src", !entityInfo.data.handIdcardimg? "images/register/add.jpg" : entityInfo.data.handIdcardimg);
        }
    };
});

