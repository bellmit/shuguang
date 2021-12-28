var myapp = angular.module("myapp", []);
myapp.controller("codeManage", function($scope, $http, $state) {
    window.changeConfig = function() {
        // window.localStorage.removeItem("changeConfig");
        // window.localStorage.setItem("changeConfig", data);
        $state.go("index.content.asms/subjEnt/changeConfig");
    };
    window.configInfo = function() {
        // window.localStorage.removeItem("changeConfig");
        // window.localStorage.setItem("changeConfig", data);
        $state.go("index.content.asms/subjEnt/configInfo");
    };
})
