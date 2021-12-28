var myapp=angular.module("myapp",[]).controller("sysNoticereleaseView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("sys_sysNoticerelease_id");
    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }
    //获取测试信息
    $http({
        url:"/sofn-sys-web/sysNoticerelease/queryById",
        headers: {
            token: window.localStorage.getItem("token")
        },
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
    	$scope.releaseTime = new Date(data.sysNoticerelease.releaseTime).format('yyyy-MM-dd hh:mm:ss');
    	$scope.releasePerson = data.sysNoticerelease.releasePerson;
    	$scope.orgName = data.orgName;
    	$scope.phone = data.sysNoticerelease.phone;
    	$scope.email = data.sysNoticerelease.email;
    	$scope.userType = data.sysNoticerelease.userType;
    	$scope.releaseRange = data.sysNoticerelease.releaseRange;
    	$scope.noticeType = data.sysNoticerelease.noticeType;
    	$scope.title = data.sysNoticerelease.title;
    	$scope.content = data.sysNoticerelease.content;
    }).error(function(data, status){
        ajaxErrorHandler(status);
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})