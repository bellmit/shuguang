var myapp=angular.module("myapp",[]);
var targe=0;
myapp.controller("login",function($scope,$http,$state){
    setInterval(function(){
        $(".login_bg").css("height",$(window).height());
       $(".login_text").css("margin-top",($(window).height()-$(".login_text").height()-$(".login_box").height())/2+"px")
    },0);
    //
    $(".rightimgCut").attr("src","../../assets/imgs/login/choose01.png");
    $scope.imgCut=function(){
        if(targe==0){
            $(".rightimgCut").attr("src","../../assets/imgs/login/choose.png");
            targe=1;
        }else{
            $(".rightimgCut").attr("src","../../assets/imgs/login/choose01.png");
            targe=0;
        }

    };
    //回车键监听
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){
            $scope.login();
        }
    };

    //初始化UUID和生成验证码
    $scope.prevUUID={};
    $scope.flushValidateCode=function(){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/getUUID",
            data:{prev:$scope.prevUUID},
            type:"get"
        }).done(function(data){
            $scope.prevUUID=data.uuid;
            //先获取UUID，再获取验证码图片
            $.ajax({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/generatorImage",
                data:{curr:$scope.prevUUID},
                type:"get"
            }).done(function(data){
                $("#validateImg").prop("src","/sofn-ales-web/generatorImage?curr="+$scope.prevUUID);
            });
        });
    }
//判定页面权限、tab页、按钮
 /*   $http({
        url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
        method: "post",
        data: {
            type: "ALES"
        },
        headers: {
            "Accept": "*!/!*",
            token: window.localStorage.getItem("token")
        },
    }).success(function(data){
        var root = _.groupBy(data.data, 'menuValue');
        var current = root["ALES"];
        window.localStorage.setItem("AlesMenuObj",JSON.stringify(current));
    }).error(function(){
    });*/
    //每次刷新页面验证码刷新
    $scope.flushValidateCode();

    $scope.login=function(){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/login",
            data:{account:$scope.account,password:$scope.password,uuid:$scope.prevUUID,code:$scope.code},
            type:"post",
            success:function(data){
                if(data.httpCode=="500"){
                    alert(data.msg);
                    $scope.flushValidateCode();
                }
                if(data.httpCode=="400"){
                    alert(data.msg);
                    $scope.flushValidateCode();
                }
                if(data.httpCode=="303"){
                    alert(data.msg);
                    $scope.flushValidateCode();
                }
                if(data.httpCode=="200"){
                    $state.go("index.content.ales/enforceLaw/subjEnforceLaw");
                }
                window.localStorage.setItem("token",data.token);
            }
        });
    };
})
