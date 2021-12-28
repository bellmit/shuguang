
var myapp = angular.module("myapp", []).controller("recordDetails", function ($scope, $http, $state) {
    detailView($scope);
    $scope.flg = true;
    window.showInfo = function(obj){
        $("#"+$(obj).attr("name")).modal("show");
      /*  //产品信息框展示
        $("#select_Entity").modal("show");
        //$('#entityList').empty();*/
    };

    $('#attributeForm').bootstrapValidator();//必须有
    //打开修改密码窗口
    window.showUpdatePwd = function () {
        $("#updatePwd").modal('show');
    }

    //检查新密码是否和原密码一样
    window.checkNewPwd = function () {
        var password = $("#formerPwd").val();
        var newPassword = $scope.TtsScltxxcjRegiter.password;
        if(password != null && newPassword != null && password != '' && newPassword != ''){
            if(password == newPassword){
                $("#newPwdMsg").show().text("新密码不能和原密码一样");
                return;
            }else{
                $("#newPwdMsg").show().text("");
            }
        }
    }

    $scope.updatepassword=function(){
        $('#attributeForm').bootstrapValidator();//必须有
        if($scope.flg == false){
            return;
        }
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        $scope.TtsScltxxcjRegiter.password = $("#newPwd").val();
        $scope.TtsScltxxcjRegiter.password = $("#checkNewPwd").val();
        $scope.TtsScltxxcjRegiter.account = entityInfo.data.account;
        $("#updatePwd").modal('hide');
        $http({
            url:"/sofn-tts-web/ttsScltxxcjRegiter/updatepassword",
            data: $scope.TtsScltxxcjRegiter,
            method:"post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }

        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("修改密码成功！","info");
                $state.go("login/logintts");
            }
        }).error(function (){
            jBox.tip("修改密码失败！","error")
        })

    };

    //检查原密码是否正确
    window.checkPwd = function(){
        var password = $("#formerPwd").val();
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        $http({
            url:"/sofn-tts-web/ttsScltxxcjRegiter/checkFormerPwd",
            data: {password : password,account : entityInfo.data.account},
            method:"post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function(data){
            if(data.code==200){
                if(data.data != null && data.data != ''){
                    $("#formerPwdMsg").show().text(data.data);
                    $scope.flg = false;
                }else{
                    $("#formerPwdMsg").hide();
                    $scope.flg = true;
                }
            }
        });
    }
});

//加载用户信息
var detailView = function($scope){
    var submit;
    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
    if(!entityInfo || !entityInfo.data){
        $.jBox.info("你已注销！", "提示", submit, { buttons: { '确认': true} });
        return ;
    }
        if("三证合一营业执照（无独立组织机构代码证）" == entityInfo.data.cardType){
            $("#zuzhi").attr("hidden","hidden");
        }
        var entityId = entityInfo.data.id;
        $scope.account = entityInfo.data.account;
        $scope.entityIdcode = entityInfo.data.entityIdcode;
        $scope.userIdcode = entityInfo.data.userIdcode;
        $scope.enterpriseName = entityInfo.data.enterpriseName;
        $scope.entityScale = entityInfo.data.entityScaleName;
        $scope.entityType = entityInfo.data.entityTypeName;
        $scope.cardType = entityInfo.data.cardType;
        $scope.creditCode = entityInfo.data.creditCode;
        $scope.area = $.fn.Cityname(entityInfo.data.area);
        $scope.legalName = entityInfo.data.legalName;
        $scope.legalIdnumber = entityInfo.data.legalIdnumber;
        $scope.legalPhone = entityInfo.data.legalPhone;
        $scope.contactName = entityInfo.data.contactName;
        $scope.contactPhone = entityInfo.data.contactPhone;
        $scope.contactEmail = entityInfo.data.contactEmail;
        if(entityInfo.data.organizationCode){
            $("#organizationCode").show();
            $scope.organizationCode = entityInfo.data.organizationCode;
        }else{
            $("#organizationCode").hide();
        }
        var industry = entityInfo.data.entityIndustryName;//所属行业
        if(industry!=null&&industry!=""){
            industry = industry.endsWith(",")?industry.substring(0,industry.length-1):industry;
            $scope.entityIndustry = industry.split(",").join("、");
        }
        var startTime = entityInfo.data.businessOperationStart;
        var endTime = entityInfo.data.businessOperationEnd;
        var timeValue = "";
        if(startTime){
            timeValue = new Date(startTime).format("yyyy-MM-dd")+" 至 ";
            if(endTime){
                timeValue = timeValue +new Date(endTime).format("yyyy-MM-dd");
            }
            $scope.businessOperation = timeValue;
        }else{
            $scope.businessOperation = "长期";
        }
    $('#operationIcode').attr('src',entityInfo.data.businessLicenceimg);
    $('#groupImage').attr('src',entityInfo.data.organizationCertificateimg);
    $('#legalImage1').attr('src',entityInfo.data.positiveIdcardeimg);
    $('#legalImage2').attr('src',entityInfo.data.negativeIdcardimg);
    $('#legalImage3').attr('src',entityInfo.data.handIdcardimg);
        $("#changeGrid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjUserChangeRecord/getPageInfo",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    params.entityId = entityId;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data : "APPLY_UPDATE_TIME",
                    title : "变更时间",
                    render : function(data, type, row) {
                        if(data){
                            return new Date(data).format('yyyy-MM-dd');
                        }
                    },
                    width:'14%',
                    className:'text-center'
                },
                {
                    data : "UPDATE_CONTENT",
                    title : "变更内容",
                    className:'text-center'
                },
                {
                    data : "REASON",
                    title : "变更原因",
                    className:'text-center'
                },

            ]
        });
};