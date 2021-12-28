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

    //初始化UUID和生成验证码
    $scope.prevUUID={};
    $scope.flushValidateCode=function(){
        $.ajax({
            url: "/sofn-asms-web/getUUID",
            data:{prev:$scope.prevUUID},
            type:"get"
        }).done(function(data){
            $scope.prevUUID=data.uuid;
            //先获取UUID，再获取验证码图片
            $.ajax({
                url: "/sofn-asms-web/generatorImage",
                data:{curr:$scope.prevUUID},
                type:"get"
            }).done(function(data){
                $("#validateImg").prop("src","/sofn-asms-web/generatorImage?curr="+$scope.prevUUID);
            });
        });
    }

    //每次刷新页面验证码刷新
    $scope.flushValidateCode();

    $scope.login=function(){
        $.ajax({
            url: "/sofn-asms-web/login",
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
                    $state.go("index.content.asms/account/asmsAccountInformation");
                }
                window.localStorage.setItem("token",data.token);
            }
        });
    };

    //回车监听事件
    document.onkeydown=function() {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){
            $scope.login();
        }
    }
})
