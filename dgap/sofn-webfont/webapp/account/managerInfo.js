var myapp = angular.module("myapp", []).controller("managerInfo", function ($scope, $http, $state) {
    //返回前一页
    $scope.callBack=function(){
        $state.go(window.localStorage.getItem("historyUrl"))

    }
    inits($scope);
    //init 初始化数据
});
var inits = function ($scope) {
    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
    if (entityInfo) {
        $scope.accounts = entityInfo.data.account;
        $scope.enterpriseName = entityInfo.data.enterpriseName;
        $scope.address = entityInfo.data.address;
        $scope.realName = entityInfo.data.realName;
        $scope.contactPhone = entityInfo.data.contactPhone;
        $scope.contactEmail = entityInfo.data.contactEmail;
        $scope.legalIdnumber = entityInfo.data.legalIdnumber;
    }
};
//
//var params = {
//    "account": $("#loginName").val(),
//    "password": $("#password").val(),
//    "code": $("#validateCode").val(),
//    "uuid": localStorage.getItem("prev")
//}
//params = JSON.stringify(params);
////注册
//$http({
//    url: "/sofn-tts-web/ttsLogin/login?account=" + $("#loginName").val() + "&password=" + $("#password").val() + "&code=" + $("#validateCode").val() + "&uuid=" + localStorage.getItem("prev"),
//    data: params,
//    method: "post",
//    //post传输方式   头信息
//    headers: {
//        post: {'Content-Type': 'application/x-www-form-urlencoded'}
//    }
//}).success(function (data) {
//    alert("状态码:" + data.httpCode + ",状态消息:" + data.msg + "!!");
//}).error(function () {
//    alert("登录异常！");
//})
