var myapp=angular.module("myapp",[]);
myapp.controller("forgetads",function($scope,$http,$state){

    /** 第一部分，初始化时加载的内容 **/
    //激活验证规则
    $('#forgetPwdForm').bootstrapValidator();//必须有
    $('#forgetAccountForm').bootstrapValidator();//必须有
    $('#changePwdForm').bootstrapValidator();//必须有

    $.fn.mycity("sheng", "shi", "xian");
    $.fn.mycity("sheng1", "shi1", "xian1");
    $scope.subj={};
    $scope.subj.type="0";
    $scope.subj.level="0";
    $scope.subjA={};
    $scope.subjA.type="0";
    $scope.subjA.level="0";

    // //获取机构级别数据字典
    // $scope.orgLevel=[];
    // $http({
    //     url:"/sofn-asms-web/sysDict/getSysDictByCode",
    //     data:"1",
    //     method:"post"
    // }).success(function(data) {
    //     $scope.orgLevel=JSON.parse(data.data);
    //     $scope.subjSv.svLevelId=$scope.orgLevel[0].id;
    // })

    /** 第二部分，主动调用才执行的部分 **/
    $scope.changeTab=function(param) {
        $("#account").hide();
        $("#pwd").hide();
        if(param==1){
            $("#pwd").show();
        }else{
            $("#account").show();
        }
    }

    //根据输入条件获取用户后弹出修改密码框
    $scope.changePwd=function() {
        if(!$('#forgetPwdForm').data('bootstrapValidator').validate().isValid()){
            return false;
        }//必须有
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        $scope.subj.areaId=areaId;
        $http({
            url:"/sofn-ads-web/acctInfo/getUserByCondition",
            data:$scope.subj,
            method:"post"
        }).success(function(data) {
            if(data.httpCode=="200"){
                jBox.tip("验证成功", 'success');
                $scope.uuid = data.uuid;
                $scope.code = data.code;
                $scope.accountChange = data.user.ACCOUNT;
                $("#pwdModal").modal("show");
            }else{
                jBox.tip(data.message, 'info');
            }
        })
    }

    //根据输入条件获取用户后弹出账号信息
    $scope.showAccount=function() {
        if(!$('#forgetPwdForm').data('bootstrapValidator').validate().isValid()){
            return false;
        }//必须有
        var province = $("#sheng1").val();
        var city = $("#shi1").val();
        var county = $("#xian1").val();
        var areaId = "";
        var superiorAreaId = "";
        var superiorAreaIdB = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
            superiorAreaId = city;
            superiorAreaIdB = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
            superiorAreaId = province;
            superiorAreaIdB = city;
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
            superiorAreaId = "";
            superiorAreaIdB = province;
        }
        $scope.subjA.areaId=areaId;
        if($scope.subjA.type=="0") {
            $scope.subjA.superiorAreaId = superiorAreaId;
            $scope.subjA.superiorLevel = $scope.subjA.level-1;
        }else{
            $scope.subjA.superiorAreaId = superiorAreaIdB;
            $scope.subjA.superiorLevel = $scope.subjA.level;
        }
        $http({
            url:"/sofn-ads-web/acctInfo/getSuperior",
            data:$scope.subjA,
            method:"post"
        }).success(function(data) {
            if(data.httpCode=="200"){
                jBox.tip("验证成功", 'info');
                $scope.superior = data.superior;
                $("#accountModal").modal("show");
            }else{
                jBox.tip(data.message, 'info');
            }
        })
    }

    //关闭账号模态框
    $scope.closeAccountModal=function() {
        $("#accountModal").modal("hide");
    }

    //修改密码
    $scope.resetPwd=function() {
        if (!$("#changePwdForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $http({
            url:"/sofn-ads-web/acctInfo/resetPwd",
            data:{account:$scope.accountChange,pwd:$scope.newPwd},
            params:{"uuid":$scope.uuid,"validateCode":$scope.code},
            method:"post"
        }).success(function(data){
            if(data.httpCode=="200"){
                jBox.tip("修改成功", 'success');
                $("#pwdModal").modal("hide");
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(){
        });
    }
})
