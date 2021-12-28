var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
var myapp = angular.module("myapp", []).controller("recordUpdateApply", function ($scope, $http, $state) {
    var isExist = false,isChageImg = false,flag1=false,flag2=false,flag3=false,flag4=false,flag5=false;
    var cardTypeId = "independent";
    detailView($scope);

    var entpriseType = "";
    if(entityInfo && entityInfo.data && entityInfo.data.enterpriseName){
        entpriseType = entityInfo.data.entityScaleName;
        if(entpriseType =="个人" || entpriseType =="家庭农场"){
            $("#entprise").hide();
            $("#isEnt").hide();
        } else{
            $("#entprise").show();
            $("#isEnt").show();
        }
    }
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

    $scope.getRadio = function() {
        var radioId = $("input[name='cardType']:checked").attr("id");
        hideOrShow(radioId);
        query111();
        //$("#img23").attr("src", "images/register/add.jpg");
        //$("#img23").attr("isUpload", "N");
        //$("#img24").attr("src", "images/register/add.jpg");
        //$("#img24").attr("isUpload", "N");
        if(cardTypeId != radioId){
            if("independent" == radioId){
                $("#img61").attr("src", "images/register/add.jpg");
                $("#img61").attr("isUpload", "N");
            }else{
                $("#img62").attr("src", "images/register/add.jpg");
                $("#img62").attr("isUpload", "N");
                $("#img61").attr("src", "images/register/add.jpg");
                $("#img61").attr("isUpload", "N");
            }
        }else{
            if("independent" == radioId){
                $("#img61").attr("src", !entityInfo.data.businessLicenceimg? "images/register/add.jpg" : entityInfo.data.businessLicenceimg);
                $("#img61").attr("isUpload", !entityInfo.data.businessLicenceimg ? "N" : "Y");
            }else{
                $("#img61").attr("src", !entityInfo.data.businessLicenceimg? "images/register/add.jpg" : entityInfo.data.businessLicenceimg);
                $("#img62").attr("src", !entityInfo.data.organizationCertificateimg? "images/register/add.jpg" : entityInfo.data.organizationCertificateimg);
                $("#img61").attr("isUpload", !entityInfo.data.businessLicenceimg ? "N" : "Y");
                $("#img62").attr("isUpload", !entityInfo.data.organizationCertificateimg ? "N" : "Y");
            }
        }
    }

    function hideOrShow(id) {
        if("independent" == id){
            $("#img22div").hide();
            $("#img21Text").text("企业证件照");
        }else{
            $("#img22div").show();
            $("#img21Text").text("营业执照");
        }
    }
    window.queryByCreditCode2 = function(){
        query111();
    }

    function query111(){
        var crad = $("input[name='cardType']:checked").attr("id");
        var code = $("#ZZJGDMZ").val();
        //下面这段验证不要随便乱改乱注释，记住了！
        if (crad == "independent") {
            if(code==null || code == ""){
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("组织机构代码不能是空");
                return;
            }
            if(code.length !=18){
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("组织机构代码为18位");
                return;
            }
            if (code.length != 18 || !(code.match('^[A-Za-z0-9]+$'))) {
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $("#ZZJGDMZ").css("border-color", "#3c763d").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,0))");
                $scope.cardflag = false;
                $("#msg").html('');
            }
        } else {
            if(code==null || code == ""){
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("组织机构代码不能是空！");
                return;
            }
            if(code.length !=10){
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("组织机构代码为10位");
                return;
            }
            if (!(/^[0-9a-zA-Z]{8}-[0-9a-zA-Z]{1}$/.test(code))) {
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else {
                $scope.cardflag = false;
                $("#msg").html('');
            }
        }
    }

    //备案注销
    $scope.addTtsScltxxcjChangeRecordApply = function () {
        //验证
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        if(isExist){
             alert("已发起过变更未经监管审核不能再次发起变更！")
             return false;
         }
        if($scope.cardflag == true){
            alert("此组织机构代码有误,请核实重新填写!")
            return false;
        }
        var updateContent = getUpdateContent();
        if(!updateContent){
            alert("主体信息没有发生改变，请核对后重新发起变更！")
            return false;
        }
        //图片验证
        var cradId = $("input[name='cardType']:checked").attr("id");
        if(isChageImg){
            if(entpriseType =="个人" || entpriseType =="家庭农场"){
                if($("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                    alert("主体信息已发起变更,请重新上传所有证件图片！")
                    return false;
                }
            } else{
                if("independent" == cradId){
                    if($("#img61").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                        alert("主体信息已发起变更,请重新上传所有证件图片！")
                        return false;
                    }
                }else{
                    if($("#img61").attr("isUpload") == "N" || $("#img62").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                        alert("主体信息已发起变更,请重新上传所有证件图片！")
                        return false;
                    }
                }
            }
        }/**else{
            if(entpriseType =="个人" || entpriseType =="家庭农场"){
                if($("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                    alert("请将图片资料上传完整,缺一不可,谢谢！")
                    return false;
                }
            } else{
                if("independent" == cradId){
                    if($("#img61").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                        alert("请将图片资料上传完整,缺一不可,谢谢！")
                        return false;
                    }
                }else{
                    if($("#img61").attr("isUpload") == "N" || $("#img62").attr("isUpload") == "N" || $("#img23").attr("isUpload") == "N" ||$("#img24").attr("isUpload") == "N" ||$("#img25").attr("isUpload") == "N"){
                        alert("请将图片资料上传完整,缺一不可,谢谢! ")
                        return false;
                    }
                }
            }
        }*/


        if(entityInfo==null){
            alert("你已注销!")
            return;
        }

        if(!$("#selectedSiteArea option:selected").val()){
            alert("所属区域必须选到区县！");
            return false;
        }
        var realname = "",legalName="",legalPhone="",contactName="",contactPhone;
        if(entpriseType =="个人" || entpriseType =="家庭农场"){
            realname = $scope.contactName==null?entityInfo.data.contactName:$scope.contactName;
            legalName = $scope.contactName==null?entityInfo.data.contactName:$scope.contactName;
            legalPhone = $scope.contactPhone==null?entityInfo.data.contactPhone:$scope.contactPhone;
            contactName = $scope.contactName==null?entityInfo.data.contactName:$scope.contactName;
            contactPhone = $scope.contactPhone==null?entityInfo.data.contactPhone:$scope.contactPhone;
        }else{
            realname = entityInfo.data.realName;
            legalName = $scope.contactName==null?entityInfo.data.contactName:$scope.contactName;
            legalPhone = $scope.contactPhone==null?entityInfo.data.contactPhone:$scope.contactPhone;
            contactName = entityInfo.data.contactName;
            contactPhone = entityInfo.data.contactPhone;
        }
        var params = {
            "entityId":entityInfo.data.id,
            "realName":realname,
            "idcode":entityInfo.data.idcode,
            "entityScale":entityInfo.data.entityScale,
            "entityType":entityInfo.data.entityType,
            "entityProperty":entityInfo.data.entityProperty,
            "entityIndustry":entityInfo.data.entityIndustry,
            "entityScaleName":entityInfo.data.entityScaleName,
            "entityTypeName":entityInfo.data.entityTypeName,
            "entityPropertyName":entityInfo.data.entityPropertyName,
            "entityIndustryName":entityInfo.data.entityIndustryName,
            "cardType":$("input[name='cardType']:checked").val()?$("input[name='cardType']:checked").val():entityInfo.data.entityType,
            "businessOperation":entityInfo.data.businessOperation,
            "businessOperationStart":entityInfo.data.businessOperationStart,
            "businessOperationEnd":entityInfo.data.businessOperationEnd,
            "enterpriseIndustry":entityInfo.data.enterpriseIndustry,
            "annualOutput":entityInfo.data.annualOutput,
            "unit":entityInfo.data.unit,
            "unitName":entityInfo.data.unitName,
            //"documentImages":entityInfo.data.documentImages,
            "longitude":entityInfo.data.longitude,
            "latitude":entityInfo.data.latitude,
            "legalIdnumber":entityInfo.data.legalIdnumber,
            "legalImages":entityInfo.data.legalImages,
            "faxNumber":entityInfo.data.faxNumber,
            "contactEmail":entityInfo.data.contactEmail,
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
            "legalName" : legalName,
            "legalPhone":legalPhone,
            "contactName":contactName,
            "contactPhone":contactPhone,
            "creditCode":$scope.ZZJGDMZ==null?entityInfo.data.creditCode:$scope.ZZJGDMZ,
            "area": $("#selectedSiteArea option:selected").val(),
            //"area": $scope.selectedSiteArea==null?entityInfo.data.area:$scope.selectedSiteArea,
            "enterpriseName":$scope.enterpriseName==null?entityInfo.data.enterpriseName:$scope.enterpriseName,
            "address":$scope.address==null?entityInfo.data.address:$scope.address,
            "account":$scope.accounts==null?entityInfo.data.account:$scope.accounts,
            "businessLicenceimg" : $("#img61").attr("src")&&$("#img61").attr("isUpload") == "Y"?$("#img61").attr("src"): entityInfo.data.businessLicenceimg,
            "organizationCertificateimg" :  $("#img62").attr("src")&&$("#img62").attr("isUpload") == "Y"?$("#img62").attr("src"):entityInfo.data.organizationCertificateimg,
            "positiveIdcardeimg" : $("#img23").attr("src")&&$("#img23").attr("isUpload") == "Y"?$("#img23").attr("src"):entityInfo.data.positiveIdcardeimg,
            "negativeIdcardimg" :  $("#img24").attr("src")&&$("#img24").attr("isUpload") == "Y"?$("#img24").attr("src"):entityInfo.data.negativeIdcardimg,
            "handIdcardimg" :  $("#img25").attr("src")&&$("#img25").attr("isUpload") == "Y"?$("#img25").attr("src"):entityInfo.data.handIdcardimg,
            //=================变更字段====================
            "documentImages":$("#img61").attr("src")+","+$("#img62").attr("src"),
            "updateContent":updateContent,
            "updateBeforeJson":getBeforeJson(),
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
            alert("httpCode:"+data.httpCode);
            if(data.httpCode=="200"){
                $state.go("tts/register/success/success");
            }else{
                alert("保存失败");
                $state.go("login/logintts");
            }
        }).error(function () {
            alert("保存失败");
            $state.go("login/logintts");
        })

    };
    //获取更新后的内容
    function getUpdateContent() {
        var flag = false;
        var updateContent ="";
        if(entityInfo.data.enterpriseName!=$scope.enterpriseName){
            updateContent+="主体名称变更为:"+$scope.enterpriseName+";";
            flag = true;
        }
        if(entityInfo.data.area!=$("#selectedSiteArea option:selected").val()){
            updateContent+="省份变更为:"+$("#selectedSiteArea option:selected").val()+";";
            flag = true;
        }
        if(entityInfo.data.creditCode!=$scope.ZZJGDMZ){
            updateContent+="组织机构代码变更为:"+$scope.ZZJGDMZ+";";
            flag = true;
        }
        if(entityInfo.data.address!=$scope.address){
            updateContent+="主体地址变更为:"+$scope.address+";";
            flag = true;
        }
        if(entpriseType =="个人" || entpriseType =="家庭农场"){
            if(entityInfo.data.contactName!=$scope.contactName){
                updateContent+="法人联系人变更为:"+$scope.contactName+";";
                flag = true;
            }
            if(entityInfo.data.contactPhone!=$scope.contactPhone){
                updateContent+="法人联系方式变更为:"+$scope.contactPhone+";";
            }
        }else{
            if(entityInfo.data.legalName!=$scope.contactName){
                updateContent+="法人联系人变更为:"+$scope.contactName+";";
                flag = true;
            }
            if(entityInfo.data.legalPhone!=$scope.contactPhone){
                updateContent+="法人联系方式变更为:"+$scope.contactPhone+";";
            }
        }

        //if(entityInfo.data.documentImages!=$scope.contactPhone){
        //    updateContent+="图片路径变更为:"+$scope.contactPhone+";";
        //}
        if(flag){
            changeImg();
        }
        return updateContent ;
    }
    function changeImg() {
        isChageImg = true;
        if(entpriseType =="个人" || entpriseType =="家庭农场"){
            if(!flag3){
                $("#img23").attr("src","images/register/add.jpg");
                $("#img23").attr("isUpload","N");
            }
            if(!flag4){
                $("#img24").attr("src","images/register/add.jpg");
                $("#img24").attr("isUpload","N");
            }
           if(!flag5){
               $("#img25").attr("src","images/register/add.jpg");
               $("#img25").attr("isUpload","N");
           }
        }else{
            if(!flag1){
                $("#img61").attr("src","images/register/add.jpg");
                $("#img61").attr("isUpload","N");
            }
           if(!flag2){
               $("#img62").attr("src","images/register/add.jpg");
               $("#img62").attr("isUpload","N");
           }
            if(!flag3){
                $("#img23").attr("src","images/register/add.jpg");
                $("#img23").attr("isUpload","N");
            }
            if(!flag4){
                $("#img24").attr("src","images/register/add.jpg");
                $("#img24").attr("isUpload","N");
            }
            if(!flag5){
                $("#img25").attr("src","images/register/add.jpg");
                $("#img25").attr("isUpload","N");
            }
        }
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
            "legalIdnumber":entityInfo.data.legalIdnumber,
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
            "handIdcardimg" : entityInfo.data.handIdcardimg
        }
        return  jsonBefore =JSON.stringify(jsonBefore);
    }



    $('#attributeForm').bootstrapValidator();//必须有

    //modal 展示
    $scope.myModal61=function(){
        $("#myModal61").modal("show");
    }
    $scope.myModal62=function(){
        $("#myModal62").modal("show");
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
            $("#img61").attr("src","http://" +  data.response.path);
            $("#img61").attr("isUpload","Y");
            flag1 = true;
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
            $("#img62").attr("src","http://" +  data.response.path);
            $("#img62").attr("isUpload","Y");
            flag2 = true;
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
        if(entityInfo.data.creditCode==null || ""==entityInfo.data.creditCode){
            alert("您的主体形式为个人,不能进行备案信息变更!");
            $state.go("index.content.tts/register/accountInfo/entityIdCode");
        }
        if (entityInfo) {
            $scope.enterpriseName = entityInfo.data.enterpriseName;
            $scope.address = entityInfo.data.address;
            $scope.accounts = entityInfo.data.account;
            $scope.contactName = entityInfo.data.legalName;
            $scope.contactPhone = entityInfo.data.legalPhone;
            var radioValue = entityInfo.data.cardType;
            if(radioValue){
                $("input[value="+radioValue+"]").attr("checked",true);
                $("#ZZJGDMZ").val(entityInfo.data.creditCode);
                $scope.ZZJGDMZ = entityInfo.data.creditCode;
                cardTypeId = $("input[value="+radioValue+"]").attr("id")
                hideOrShow(cardTypeId);
                query111();
            }
            var area =  entityInfo.data.area;
            $.fn.mycity("selectedSiteProvince","selectedSiteCity","selectedSiteArea",area);
            var imgs = entityInfo.data.documentImages ;

            $("#img61").attr("src", !entityInfo.data.businessLicenceimg? "images/register/add.jpg" : entityInfo.data.businessLicenceimg);
            $("#img62").attr("src", !entityInfo.data.organizationCertificateimg? "images/register/add.jpg" : entityInfo.data.organizationCertificateimg);
            $("#img61").attr("isUpload", !entityInfo.data.businessLicenceimg ? "N" : "Y");
            $("#img62").attr("isUpload", !entityInfo.data.organizationCertificateimg ? "N" : "Y");
            $("#img23").attr("src", !entityInfo.data.positiveIdcardeimg? "images/register/add.jpg" : entityInfo.data.positiveIdcardeimg);
            $("#img24").attr("src", !entityInfo.data.negativeIdcardimg? "images/register/add.jpg" : entityInfo.data.negativeIdcardimg);
            $("#img23").attr("isUpload", !entityInfo.data.positiveIdcardeimg? "N" : "Y");
            $("#img24").attr("isUpload",  !entityInfo.data.negativeIdcardimg ? "N" : "Y");
            $("#img25").attr("src", !entityInfo.data.handIdcardimg? "images/register/add.jpg" : entityInfo.data.handIdcardimg);
            $("#img25").attr("isUpload",!entityInfo.data.handIdcardimg? "N" : "Y");

        }
    };
});

