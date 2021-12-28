var myapp=angular.module("myapp",[]);
myapp.controller("subjElAllot",function($scope,$http,$state) {
    var a= JSON.parse(window.localStorage.getItem("menuObj")).tab;
    var  b = a.map(function(item){
        return item.url;
    });
   // 判定页面跳转地方
   for(var i=0;i< a.length;i++){
       if(a[i].authority!="0"){
           $state.go(a[i].url);
           break;
       }
   }
});