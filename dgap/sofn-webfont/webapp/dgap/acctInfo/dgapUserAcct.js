var myapp=angular.module("myapp",[]);
myapp.controller("dgapUserAcct",function($scope,$http,$state){

    $scope.back = function () {
        window.history.back(-1);
    };
    var token = window.localStorage.getItem("token");
    var account = "";

    /**
     * 加载当前用户信息
     */
    $scope.loadSysUserInfo=function () {
        $http({
            url:"/sofn-dgap-web/userInfo/getUserInfoByToken",
            method:"post",
            params : { "token": token},
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
        }).success(function(data){
            $scope.user = data.data;
            if( data.data.email==null){
                $scope.user.email="无"
            }
            if( data.data.postId==null){
                $scope.user.postName="无";
            }
            $scope.loadPostTypeData();
        })
    }

    /**
     * 加载职务字典数据
     */
    $scope.loadPostTypeData=function () {
        $http({
            url:"/sofn-dgap-web/userInfo/getPostType",
            method:"post"
        }).success(function(data){
            var posts = new Array();
            $.each(data.data,function (idx) {
                var a = $(this)[0];
                var d = {name: a.dictName, val: a.dictValue, title: a.dictName , postId : a.id};
                if($scope.user.postId == a.id){
                    $scope.user.postName = a.dictName;
                }
                posts.push(d);
            })
            $scope.postType = posts;
            $scope.selectpostType = $scope.user.postId;

        })
    }

    $scope.loadSysUserInfo();

    /**
     * 显示修改密码模态框
     */
    $scope.showUpdatePwdModal=function () {
        $("#pwd_form input").val("");   //清空表单值
        $("#dModal").modal("show");
    }


    /**
     * 显示修改个人信息模态框
     */
    $scope.showUpdateInfoModal=function () {
       //$scope.loadSysUserInfo();
       /* $("select[name='postId']").find("option[value='"+$scope.user.postId+"']").attr("selected",true);
        if($scope.user.email="无"){
            $scope.user.email="";
        }*/
        $("#uModal").modal("show");
    }

    /**
     * 显示新增监测机构主体注销申请模态框
     */
    $scope.addCheckEntityModel=function(){
        $("#cModal").modal("show");
    }

    //初始化信息变更表单验证
    $('#info_form').bootstrapValidator();
    $("#updateInfo").click(function () {
        if (!$("#info_form").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行提交操作
        $scope.updateInfo();
    })

    /**
     * 修改个人信息
     */
    $scope.updateInfo=function () {
        //表单验证
        //var postId = $("select[name='postId'] option:selected").val();
        //var postId = $scope.selectpostType;
        var new_account = $("input[name='account']").val();
        var new_userName = $("input[name='userName']").val();
        var new_postId = $("select[name='postId']").val();
        var new_phone = $("input[name='phone']").val();
        var new_email = $("input[name='email']").val();
        var reg_phone = /^0?(13[0-9]|15[012356789]|18[0-9]|17[0-9])[0-9]{8}$/;
        var reg_email = new RegExp("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        if(new_account==null || new_account==''){
            jBox.tip("账号不存在","info");
            return false;
        }
   /*     if(new_postId==null || new_postId=='' || new_postId==undefined){
            jBox.tip("请选择职务","info");
            return false;
        }*/
        if(!reg_phone.test(new_phone)){
            jBox.tip("请输入正确的电话号码","info");
            return false;
        }
        if(new_email!=null&&new_email!=""){
            if(!reg_email.test(new_email)){
                jBox.tip("请输入正确的邮箱地址","info");
                return false;
            }
        }
        if(new_email.length > 40){
            jBox.tip("邮箱长度不能超过40位","info");
            return false;
        }
        //提交表单
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-dgap-web/userInfo/changeInfo",
                    method:"post",
                    params : { "token": token,"id":$scope.user.id,"postId" : new_postId,"phone" : new_phone,"email":new_email,
                        "account":new_account,"userName":new_userName},
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    if(data.httpCode==200){
                        jBox.tip("修改成功","success");
                        $scope.loadSysUserInfo();
                        $("#uModal").modal("hide");
                    }else{
                        jBox.tip("修改失败","error");
                    }
                })
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认进行信息变更吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }
    /**
     * 修改个人信息的验证重构,数据重置
     */
   $("#ucancel").click(function(){
       $("#info_form").data('bootstrapValidator').destroy();
       $('#info_form').data('bootstrapValidator', null);
       $('#info_form').bootstrapValidator();
       $scope.loadSysUserInfo();
    })


    //备案注销表单验证
    $('#cancelInfo_form').bootstrapValidator();
    $("#cancelInfo").click(function () {
        if (!$("#cancelInfo_form").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行提交操作
        $scope.cancelInfo();
    })

    /**
     * 备案注销
     */
    $scope.cancelInfo = function () {
        var applyReason = $('#applyReason') .val();
        if(applyReason==null || applyReason==''){
            jBox.tip("请输入注销原因","info");
            return false;
        }

        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-dgap-web/userInfo/cancelInfo",
                    method:"post",
                    params : { "token": token,"applyReason":applyReason},
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    if(data.httpCode==200){
                        jBox.tip("注销成功","success");
                        $("#cModal").modal("hide");
                    }else{
                        jBox.tip("注销失败","error");
                    }
                })
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认进行注销吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

    //初始化修改密码表单验证
    $('#pwd_form').bootstrapValidator();
    $("#updatePwd").click(function () {
        if (!$("#pwd_form").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行提交操作
        $scope.updatePwd();
    })

    /**
     * 修改密码
     */
    $scope.updatePwd=function () {
        //表单验证
        var oldPwd = $("input[name='oldPwd']").val();
        var newPwd = $("input[name='newPwd']").val();
        var newPwd2 = $("input[name='newPwd2']").val();
        account = $("input[name='account']").val();
        if(account==null || account==''){
            jBox.tip("账号不存在","info");
            return false;
        }
        if(oldPwd==null || oldPwd==''){
            jBox.tip("请输入原始密码","info");
            return false;
        }
        if(newPwd==null || newPwd==''){
            jBox.tip("请输入新密码","info");
            return false;
        }
        if(newPwd2==null || newPwd2==''){
            jBox.tip("请确认新密码","info");
            return false;
        }
        if(newPwd!=newPwd2){
            jBox.tip("新密码两次输入不一致","info");
            return false;
        }
        //提交
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-dgap-web/userInfo/changePwd",
                    method:"post",
                    params : { "token": token,"account":account,"oldPwd":oldPwd,"newPwd":newPwd},
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    if(data.httpCode==200){
                        jBox.tip("修改成功","success");
                        //回到登录页面
                        // $state.go("/");
                        // window.location.reload();

                        window.localStorage && window.localStorage.clear();
                        window.sessionStorage && window.sessionStorage.clear();
                        window.location.href = "/";
                    }else{
                        jBox.tip("提示，原始密码输入错误，修改失败！","error");
                    }
                })
            else
                jBox.tip("取消成功", 'success');
            $("#dModal").modal("hide");
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认进行密码修改吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }
    /**
     * 修改密码的验证重构
     */
    $("#cancel").click(function(){
        $('#pwd_form').data('bootstrapValidator').resetForm(true);
    })
})

