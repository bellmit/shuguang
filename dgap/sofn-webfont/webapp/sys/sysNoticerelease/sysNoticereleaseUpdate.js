//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("sysNoticereleaseUpdate",function($scope,$http,$state){
        $('#attributeForm').bootstrapValidator();//必须有
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
			$scope.sysNoticerelease = data.sysNoticerelease
			$scope.userType = data.sysNoticerelease.userType;
			$scope.noticeType = data.sysNoticerelease.noticeType
            $scope.orgName = data.orgName;
            var released = new Date(data.sysNoticerelease.releaseTime);
            $scope.releaseTime =  dateformat(released);
			$("select[name='noticeType']").get(0).value=$scope.noticeType;
			$("select[name='userType']").get(0).value=$scope.userType;
			var releaseranges = data.sysNoticerelease.releaseRange;

			var vals = releaseranges.split(",");
			var boxes = document.getElementsByName("releaseRange");
			for(i=0;i<boxes.length;i++){
				for(j=0;j<vals.length;j++){
					if(boxes[i].value == vals[j]){
						boxes[i].checked = true;
					}
				}
			}
		}).error(function(data, status) {
            ajaxErrorHandler(status);
		});


        $scope.updateSysNoticerelease = function(){
            if($("input[name='releaseTime']").val() == ""){
                alert("发布时间不能为空");
                return false ;
            }
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
			var obj = document.getElementsByName("releaseRange");
			var check_val = [];
			for(k in obj){
				if(obj[k].checked)
					check_val.push(obj[k].value);
			}
			var releaseRange = check_val.toString();
			$scope.sysNoticerelease.releaseRange = releaseRange;
			//$scope.sysNoticerelease.id = $scope.id;
            $http({
                url:"/sofn-sys-web/sysNoticerelease/updateSysNoticerelease",
                data:$scope.sysNoticerelease,
                params :{releaseTime:$("input[name='releaseTime']").val()},
                method:"post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function(data){
                alert("修改成功");
                $state.go("index.content.sys/sysNoticerelease/sysNocList");
            }).error(function(data, status){
				ajaxErrorHandler(status);
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};

    });
function dateformat(date){
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    month = month < 10 ? '0'+month : month;
    day = day < 10 ? '0'+day : day;
    return [year, month, day].join('-') ;
}


