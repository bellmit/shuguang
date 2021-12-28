var myapp=angular.module("myapp",[]);
myapp.controller("header",function($scope,$http,$state){
    alert(111)
    var oUl=document.getElementById("oUl");
    oUl.onclick=function(ev){
        var interceptUrl=window.location.href;
        interceptUrl=interceptUrl.split("index/content/")[1].split("/")[0];
        var ev = ev || window.event;
        var target = ev.target || ev.srcElement;
        console.log(ev.target)
        if(target.nodeName.toLowerCase()=="a"){
            switch (target.name){
                case "个人中心":
                    alert("个人中心")
                    break;
                case "退出登录":
                    alert(11)
                    $state.go("login/login"+interceptUrl);
                    break;
            }
        }

    }

});