var myapp=angular.module("myapp",[]);
myapp.controller("forgetPwd",function($scope,$http,$state){

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
        //alert("pwd");
        //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
        if($("input[name='newPwd']").val()!=$("input[name='newPwdC']").val()){
            $("#changePwdForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#changePwdForm").bootstrapValidator();//初始化验证，与上面对应
            $("#changePwdForm").data('bootstrapValidator').validate();
            return false;
        }
        if(!$('#forgetPwdForm').data('bootstrapValidator').validate().isValid()){
            return false;
        }//必须有
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        //再次验证是否选中省市县
        if(county==null||county==""||county==undefined){
            $("#forgetPwdForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#forgetPwdForm").bootstrapValidator();//初始化验证，与上面对应
            $("#forgetPwdForm").data('bootstrapValidator').validate();
            return false;
        }
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        $scope.subj.areaId=areaId;
        $http({
            url:"/sofn-asms-web/subjSupervise/getUserByCondition",
            data:$scope.subj,
            method:"post"
        }).success(function(data) {
            if(data.httpCode=="200"){
                jBox.tip("验证成功", 'info');
                $scope.uuid = data.uuid;
                $scope.validateCode = data.validateCode;
                $scope.accountChange = data.user.ACCOUNT;
                $("#pwdModal").modal("show");
            }else{
                jBox.tip(data.message, 'info');
            }
        })
    }

    //根据输入条件获取用户后弹出账号信息
    $scope.showAccount=function() {
        //alert("account");
        if(!$('#forgetAccountForm').data('bootstrapValidator').validate().isValid()){
            return false;
        }//必须有
        var province = $("#sheng1").val();
        var city = $("#shi1").val();
        var county = $("#xian1").val();
        var areaId = "";
        var superiorAreaId = "";
        var superiorAreaIdB = "";
        //再次验证是否选中省市县
        if(county==null||county==""||county==undefined){
            $("#forgetAccountForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#forgetAccountForm").bootstrapValidator();//初始化验证，与上面对应
            $("#forgetAccountForm").data('bootstrapValidator').validate();
            return false;
        }
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
            $scope.subjA.superiorLevel = $scope.subjA.level-1;
            $scope.subjA.superiorAreaId = superiorAreaId.slice(0,$scope.subjA.superiorLevel*2);
        }else{
            $scope.subjA.superiorLevel = $scope.subjA.level;
            $scope.subjA.superiorAreaId = superiorAreaIdB.slice(0,$scope.subjA.superiorLevel*2);
        }
        $http({
            url:"/sofn-asms-web/subjSupervise/getSuperior",
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
        //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
        if($("input[name='newPwd']").val()!=$("input[name='newPwdC']").val()){
            $("#changePwdForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#changePwdForm").bootstrapValidator();//初始化验证，与上面对应
            $("#changePwdForm").data('bootstrapValidator').validate();
            return false;
        }
        $http({
            url:"/sofn-asms-web/subjSupervise/resetPwd",
            data:{account:$scope.accountChange,pwd:$scope.newPwd},
            params:{"uuid":$scope.uuid,"validateCode":$scope.validateCode},
            method:"post"
        }).success(function(data){
            if(data.httpCode=="200"){
                jBox.tip("修改成功", 'info');
                $("#pwdModal").modal("hide");
            }else{
                jBox.tip(data.message, 'info');
                $("#forgetPwdForm").form("reset");
                $("#pwdModal").modal("hide");
            }
        }).error(function(){
        });
    }
})
