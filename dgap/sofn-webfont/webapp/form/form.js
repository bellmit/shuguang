var myapp = angular.module("myapp", []);
angular.module("myapp", []).controller("form", function($scope, $http, $state) {
	$scope.onChange = function(){
		$scope.formattedString = $scope.change + '' + $scope.change;
	}
	$scope.submit = function(){
		console.log('$scope', $scope);
		swal({
		  title: "验证通过",
		  type: "success",
		  timer: 2000,
		  confirmButtonText: "确认",
		  closeOnConfirm: true,
		  showCancelButton: false
		});
	}
});
