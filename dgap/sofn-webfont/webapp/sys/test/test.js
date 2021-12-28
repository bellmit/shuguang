/**
 * Created by Administrator on 2017/11/9/009.
 */
var hello = {sayHello: function() {return "hello angular !"}}
var myapp = angular.module("myapp", []).factory('h', function(){
    return hello
});
myapp.controller("test", function($scope, $http, $state, h){
    //$scope.keyWord = '';
    $scope.w = 300;
    $scope.clock = {now: new Date()}
    var updateClock = function(){
        $scope.clock.now = new Date();
    }
    updateClock();
    setInterval(function(){
        $scope.$apply(updateClock);
        //updateClock();
    }, 1000);
    $scope.click = function() {
        $scope.keyWord = h.sayHello();
    }
    $scope.$watch('keyWord',function(to, from){
        console.log("关键值to：'%s'", to);
        console.log("关键值from: '%s'", from);
    });
});