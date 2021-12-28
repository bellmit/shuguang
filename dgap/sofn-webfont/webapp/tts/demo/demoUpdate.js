//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("demoUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("id");
		$http({
			url:"/sofn-tts-web/testDemo/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
			$("input[name='name']").val(data.demo.name);
			$("input[name='tel']").val(data.demo.tel);
	        $("input[name='cjrq']").val(new Date(data.demo.cjrq).format('yyyy-MM-dd'));
		});
		//
        $scope.demoUpdate=function(){
        	 $scope.demo.name= $("input[name='name']").val();
             $scope.demo.tel= $("input[name='tel']").val();
             $scope.demo.cjrq= $("input[name='cjrq']").val();
             $scope.demo.id = $scope.id
             
            $http({
                    url:"/sofn-tts-web/testDemo/updateTempDemo",
                    data:$scope.demo,
                    method:"post",
                    //post传输方式   头信息
                    headers: {
                        post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                    }
                }).success(function(data){
                alert("修改成功");
                $state.go("index.content.tts/demo/demo");
            }).
            error(function(){
				alert("修改失败");
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};
    });


