var myapp=angular.module("myapp",[]);
myapp.controller("comHandle",function($scope,$http,$state){
    //激活验证规则
    $('#comHandleForm').bootstrapValidator();//必须有
    //获取用户信息
    $scope.user = {};
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        /**
         * 获取受理用户的用户名
         */
        $scope.accEntityIdcode=$scope.user.userName;
        /**
         * 获取受理用户的id
         */
        $scope.accUserIdcode=$scope.user.id;
    }).error(function (data) {
        
    });

    $scope.ID=window.localStorage.getItem("ID");
    $scope.enti_name=window.localStorage.getItem("enti_name");
    $scope.be_enti_name=window.localStorage.getItem("be_enti_name");
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/ttsScltxxcjComplaint/getTtsScltxxcjComplaintById",
        method:"post",
        data:{id:$scope.ID}
    }).success(function(data){
        $scope.reCord=data.ttsScltxxcjComplaint;
        $scope.reCord.enti_name=$scope.enti_name;
        $scope.reCord.be_enti_name=$scope.be_enti_name;
        if($scope.reCord.complaintTime!=null&&$scope.reCord.complaintTime!=""&&$scope.reCord.complaintTime!=undefined) {
            $scope.reCord.complaintTime = new Date(parseInt($scope.reCord.complaintTime)+28800000).toISOString().slice(0, 10);}
        a=$scope.reCord.status;
        $scope.areaId= $.fn.Cityname($scope.reCord.areaId);
        if($scope.reCord.status=="0"){
            $scope.reCord.status="未受理";
        }
        if($scope.reCord.status=="1") {
            $scope.reCord.status = "已受理";
        }
        if($scope.reCord.status=="2"){
            $scope.reCord.status="待受理"
        }
    }).error(function (data) {
        if(!data){
            swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
            setTimeout(function(){
                window.location.href = '#/login/login';
                window.location.reload();
            },2500)
        }
    });
    //点击保存任务

    $scope.complaintHandle=function(){
        if (!$("#comHandleForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        if(a!=1){
        $scope.ACCEPTANCE=$("textarea[name='acceptance']").val();
            $("button").attr("disabled",true);
            $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url:"/sofn-asms-web/ttsScltxxcjComplaint/updateTtsScltxxcjComplaint",
                method:"post",
                data:{
                    accUserIdcode:$scope.accUserIdcode,
                    accEntityIdcode:$scope.accEntityIdcode,
                    id:window.localStorage.getItem("ID"),
                    acceptance:$scope.ACCEPTANCE,
                }
            }).success(function(data){
                $("button").attr("disabled",false);
                window.localStorage.removeItem("enti_name");
                if(data.httpCode == 200){
                    $.jBox.tip("受理保存成功！");
                    $state.go("index.content.asms/complain/comComplain")
                }else if(data.httpCode == 400){
                    $.jBox.tip(data.msg);
                    setTimeout(function(){
                        $state.go("index.content.asms/complain/comComplain")
                    },500);
                }

            }).error(function(){
                $("button").attr("disabled",false);
                $.jBox.tip("受理保存失败！");

            })
    }else{
            $.jBox.tip("已受理！");
        }
    };
    //清楚缓存
    $scope.RemoveLocal=function(){
        window.localStorage.removeItem("ID");
        window.localStorage.removeItem("enti_name");
        window.localStorage.removeItem("be_enti_name");
    }
    window.selectEnterprideInfo = function(){
        window.localStorage.setItem("entityIdcode",$scope.reCord.entityIdcode);
        window.localStorage.setItem("comComplainEnterUrl","index.content.asms/complain/comHandle");
        $state.go("index.content.asms/complain/complainEnterpriseview")
    }
    window.selectEnterprideInfoBe= function(){
        window.localStorage.setItem("entityIdcode",$scope.reCord.beEntityIdcode);
        window.localStorage.setItem("comComplainEnterUrl","index.content.asms/complain/comHandle");
        $state.go("index.content.asms/complain/complainEnterpriseview")
    }
    //文件下载
    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.reCord.attachmentPath);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.reCord.attachmentName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
});
