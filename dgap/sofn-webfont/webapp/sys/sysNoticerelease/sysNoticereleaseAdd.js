var myapp=angular.module("myapp",[]).controller("sysNoticereleaseAdd",function($scope,$http,$state){
    // $scope.depts = "";
    $scope.organs = "";
    $scope.user = "";
    $scope.organId = "";
    var token = window.localStorage.getItem("token");
    $('#attributeForm').bootstrapValidator();//必须有

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
    //默认加载机构数据
    $http({
        url:"/sofn-sys-web/sysNoticerelease/addSysNoticereInit",
        method:"post",
        params: {"token": token},
        headers:{
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function(data){
        $scope.organs=data.orList;
        $scope.user = data.user;
        $scope.organId = data.user.organizationId;


    }).error(function (data, status) {
        ajaxErrorHandler(status);
    })

    //获取选中的机构id
 $("#organs").change(function () {
     //获取value值
     $scope.organId = $scope.user.organizationId;
     $http({
         url:"/sofn-sys-web/sysNoticerelease/findDeptByOrganId",
         data:$scope.organId,
         method:"post",
         headers: {
             post: { 'Content-Type': 'application/x-www-form-urlencoded' }
         }
     }).success(function(data){
         $scope.depts = data.deptList;
     }).error(function(){
         alert("系统异常,请与后台工作人员联系");
     })
 })


    $scope.deptChange = function () {
        $scope.deptId = $("select[name='deptId']").val();
        alert($scope.deptId);
    }

    //新增测试信息
    $scope.sysNoticerelease={}
    $scope.addSysNoticerelease = function(){
        if($("input[name='releaseTime']").val() == ""){
            alert("发布时间不能为空");
            return false ;
        }
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }

    	 $scope.sysNoticerelease.releaseTime = "2015-01-02";
    	 $scope.sysNoticerelease.releasePerson = $("input[name='releasePerson']").val();
    	 $scope.sysNoticerelease.organId = $scope.organId //$("select[name='organId']").val();
    	 $scope.sysNoticerelease.phone = $("input[name='phone']").val();
    	 $scope.sysNoticerelease.email = $("input[name='email']").val();
    	 $scope.sysNoticerelease.userType = $("#userType option:selected").val()//$("select[name='userType']").val();
    	 $scope.sysNoticerelease.releaseRange = $("#releaseRange option:selected").val()//$("select[name='releaseRange']").val();
    	 $scope.sysNoticerelease.noticeType = $("#noticeType option:selected").val()//$("select[name='noticeType']").val();
    	 $scope.sysNoticerelease.title = $("input[name='title']").val();
    	 $scope.sysNoticerelease.content = $("textarea[name='content']").val();
    	 $scope.sysNoticerelease.delFlag = $("input[name='delFlag']").val();
         var obj = document.getElementsByName("releaseRange");
            var check_val = [];
            for(k in obj){
                if(obj[k].checked)
                    check_val.push(obj[k].value);
            }
        var releaseRange = check_val.toString();
        $scope.sysNoticerelease.releaseRange = releaseRange;
        $http({
            url:"/sofn-sys-web/sysNoticerelease/addSysNoticerelease",
            params :{releaseTime:$("input[name='releaseTime']").val()},
            data:$scope.sysNoticerelease,
            method:"post",
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).success(function(data){
            alert("保存成功");
            $state.go("index.content.sys/sysNoticerelease/sysNocList");
        }).error(function(data, status){
            ajaxErrorHandler(status);
        })
    }
})