var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
var myapp = angular.module("myapp", []).controller("recordUpdateFirm", function ($scope, $http, $state) {
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

    $scope.onChangeIsLong = function ($event) {
        if($scope.isLong){
            $scope.businessOperationStart = null;
            $scope.businessOperationEnd = null;
        }
    };

    inits($scope);
    $scope.bean = {};
    $scope.bean.ztProperty = {};
    $scope.bean.industryType={};
    function inits($scope) {
        //获取字典
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/getEntityTypes",
            data: "",
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            // alert("httpCode:"+data.httpCode);
            $scope.industrys=data.data.industrys;
            $scope.subjPropertys=data.data.subjPropertys;
            $scope.subTypes=data.data.subTypes;
            $scope.orgs=data.data.orgs;
        }).error(function () {
            jBox.tip("加载失败");
        })

    };
    var isExist = false,flag1=false,flag2=false,flag3=false,flag4=false,flag5=false;
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
                    entityInfo.data.approveStatus = data.vo.approveStatus;
                }
            }).error(function () {
                alert("网络连接的错误异常!");
                return false;
            })
    };

    $scope.getRadio = function() {
        var radioId = $("input[name='cardType']:checked").attr("id");
        hideOrShow(radioId);
    }

    function hideOrShow(id) {
        if("independent" == id){
            $("#tissue").hide();
            $("#img22div").hide();
            $("#img62fa").hide();
            inspectType(id);
        }else{
            $("#tissue").show();
            $("#img22div").show();
            $("#img62fa").show();
            inspectType(id);
        }
    }
    window.queryByCreditCode2 = function(){
        var id = $("input[name='cardType']:checked").attr("id");
        inspectType(id);
    }
    /**
     * 检查证件类型
     */
    function inspectType(id){
        var code = $("#creditCode").val();
        var zzjg = $("#ZZJGDMZ").val();
        //下面这段验证不要随便乱改乱，记住了！
        if (id == "independent") {
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg2").css("display", "block").html("企业注册号不能是空");
                return;
            }else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg2").html('');
                $scope.cardflag = false;
            }
            if (code.length != 18 || !(/^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$/.test(code))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg2").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg2").html('');
                $scope.cardflag = false;
            }
        } else {
            //企业注册号验证
            if(code==null || code == ""){
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg2").css("display", "block").html("企业注册号不能是空");
                return;
            }else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg2").html('');
                $scope.cardflag = false;
            }
            if (code.length != 15 || !(code.match('^[A-Za-z0-9]+$'))) {
                $("#creditCode").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg2").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else{
                $("#creditCode").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg2").html('');
                $scope.cardflag = false;
            }
            //组织机构验证
            if(zzjg==null || zzjg == ""){
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483")
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("组织机构代码不能是空");
                return;
            }else{
                $("#ZZJGDMZ").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg").html('');
                $scope.cardflag = false;
            }
            if (!(/^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/.test(zzjg))) {
                $("#ZZJGDMZ").css("border-color", "#a94442").css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483");
                $scope.cardflag = true;
                $("#msg").css("display", "block").html("不符合所选证件类型规范");
                return;
            } else{
                $("#ZZJGDMZ").css("border-color", "#c5c5c5").css("box-shadow", "");
                $("#msg").html('');
                $scope.cardflag = false;
            }
        }
    }

    //备案变更
    $scope.addTtsScltxxcjChangeRecordApply = function () {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
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
        if($scope.cardflag == true){
            jBox.tip("你的证件号码填写有误,请核实重新填写")
            return false;
        }
        //判断是否有核心信息的变更，如果存在核心信息的变更后台则需要提交审核申请并记录
        var updateContent = getUpdateContent();
        if(entityInfo==null){
            jBox.tip("你已注销");
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
            "realName":entityInfo.data.realName,
            "idcode":entityInfo.data.idcode,
            "entityScale":$("input[name='userType']:checked").val(),
            "entityType":$("input[name='ztType']:checked").val(),
            "entityProperty":checkedZtProperty,
            "entityIndustry":checkedIndustryType,
            "entityScaleName":$("input[name='userType']:checked").next().text(),
            "entityTypeName":$("input[name='ztType']:checked").next().text(),
            "entityPropertyName":checkedZtPropertyName,
            "entityIndustryName":checkedIndustryTypeName,
            "cardType":$("input[name='cardType']:checked").val()?$("input[name='cardType']:checked").val():entityInfo.data.entityType,
            "businessOperation":entityInfo.data.businessOperation,
            "businessOperationStart":$scope.businessOperationStart,
            "businessOperationEnd":$scope.businessOperationEnd,
            "businessOperation":$scope.isLong,
            "enterpriseIndustry":entityInfo.data.enterpriseIndustry,
            "annualOutput":entityInfo.data.annualOutput,
            "unit":entityInfo.data.unit,
            "unitName":entityInfo.data.unitName,
            "longitude":$scope.longitude,
            "latitude":$scope.latitude,
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
            "creditCode":$scope.creditCode,
            "area": $("#selectedSiteArea option:selected").val(),
            "enterpriseName":$scope.enterpriseName,
            "address":$scope.address,
            "organizationCode":$scope.organizationCode,
            "account":$scope.accounts==null?entityInfo.data.account:$scope.accounts,
            "businessLicenceimg" :getImgSrc($("#img61")[0].src)=="add.jpg"?"":$("#img61")[0].src,
            "organizationCertificateimg" :getImgSrc($("#img62")[0].src)=="add.jpg"?"":$("#img62")[0].src,
            "positiveIdcardeimg" :getImgSrc($("#img23")[0].src)=="add.jpg"?"":$("#img23")[0].src,
            "negativeIdcardimg" :getImgSrc($("#img24")[0].src)=="add.jpg"?"":$("#img24")[0].src,
            "handIdcardimg" :getImgSrc($("#img25")[0].src)=="add.jpg"?"":$("#img25")[0].src,
            //=================变更字段====================
            "updateContent":updateContent,
            "updateBeforeJson":getBeforeJson()
        };
        //请求备案变更接口
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
                jBox.tip("提交成功");
                $state.go("login/logintts");
            }
        })
    };
    //获取更新后的内容
    function getUpdateContent() {
        var updateContent ="";
        if(entityInfo.data.enterpriseName!=$scope.enterpriseName){
            updateContent+="主体名称变更为:"+$scope.enterpriseName+";";
        }
        if(entityInfo.data.area!=$("#selectedSiteArea option:selected").val()){
            updateContent+="省份变更为:"+$("#selectedSiteArea option:selected").val()+";";
        }
        if(entityInfo.data.organizationCode!=$scope.organizationCode){
            updateContent+="组织机构代码变更为:"+$scope.organizationCode+";";
        }
        if(entityInfo.data.creditCode!=$scope.creditCode){
            updateContent+="企业注册号变更为:"+$scope.creditCode+";";
        }
        if(entityInfo.data.address!=$scope.address){
            updateContent+="主体地址变更为:"+$scope.address+";";
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
        if(flag1==true){
            updateContent+="营业执照已上传新的附件";
        }
        var radioId = $("input[name='cardType']:checked").attr("id");
        if(radioId != "independent"){
            if(flag2==true){
                updateContent+="组织机构代码证已上传新的附件";
            }
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
            "creditCode":$scope.creditCode,
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
            "handIdcardimg" : entityInfo.data.handIdcardimg,
            "isLong":entityInfo.data.isLong
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
    /**
     * 获取所选图片名称，注意，不是获取路径
     * @param src
     */
    function getImgSrc(src){
        var index = src .lastIndexOf("\/");
        src  = src .substring(index + 1, src .length);
        return src;
    }
    /**
     * 加载初始备案信息
     * @param $scope
     */
    function detailView($scope) {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        // if(entityInfo.data.creditCode==null || ""==entityInfo.data.creditCode){
        //     alert("您的主体形式为个人,不能进行备案信息变更!");
        //     $state.go("index.content.tts/register/accountInfo/entityIdCode");
        // }
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
            $scope.longitude = entityInfo.data.longitude;
            $scope.latitude = entityInfo.data.latitude;
            $scope.legalIdnumber = entityInfo.data.legalIdnumber;
            $scope.enterpriseName = entityInfo.data.enterpriseName;
            $scope.address = entityInfo.data.address;
            $scope.accounts = entityInfo.data.account;
            $scope.contactName = entityInfo.data.contactName;
            $scope.legalName = entityInfo.data.legalName
            $scope.contactPhone = entityInfo.data.contactPhone;
            $scope.legalPhone = entityInfo.data.legalPhone;
            $scope.contactEmail = entityInfo.data.contactEmail;
            if(entityInfo.data.businessOperationStart != null && entityInfo.data.businessOperationEnd != null){
                $scope.businessOperationStart = new Date(entityInfo.data.businessOperationStart).format("yyyy-MM-dd");
                $scope.businessOperationEnd = new Date(entityInfo.data.businessOperationEnd).format("yyyy-MM-dd");
            }else{
                $scope.isLong = true;
            }
            var radioValue = entityInfo.data.cardType;
            if(radioValue){
                $("input[value="+radioValue+"]").attr("checked",true);
                if(radioValue == '三证合一营业执照（无独立组织机构代码证）'){
                    $("#tissue").hide();
                    $("#img22div").hide();
                    $("#img62fa").hide();
                }
                $scope.organizationCode = entityInfo.data.organizationCode;
                $scope.creditCode = entityInfo.data.creditCode;
            }
            var area =  entityInfo.data.area;
            $.fn.mycity("selectedSiteProvince","selectedSiteCity","selectedSiteArea",area);
            $("#imgOriginal1").attr("src", !entityInfo.data.businessLicenceimg? "images/register/add.jpg" : entityInfo.data.businessLicenceimg);
            $("#imgOriginal2").attr("src", !entityInfo.data.organizationCertificateimg? "images/register/add.jpg" : entityInfo.data.organizationCertificateimg);
            $("#imgOriginal3").attr("src", !entityInfo.data.positiveIdcardeimg? "images/register/add.jpg" : entityInfo.data.positiveIdcardeimg);
            $("#imgOriginal4").attr("src", !entityInfo.data.negativeIdcardimg? "images/register/add.jpg" : entityInfo.data.negativeIdcardimg);
            $("#imgOriginal5").attr("src", !entityInfo.data.handIdcardimg? "images/register/add.jpg" : entityInfo.data.handIdcardimg);
        }
    };
});

