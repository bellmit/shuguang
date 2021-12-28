var myapp=angular.module("myapp",[]);
var targe=0;
myapp.controller("login",function($scope,$http,$state){
    setInterval(function(){
        $(".login_bg").css("height",$(window).height());
       $(".login_text").css("margin-top",($(window).height()-$(".login_text").height()-$(".login_box").height())/2+"px")
    },0);
    //�Զ���¼ͼƬ�л�
    $(".rightimgCut").attr("src","../../assets/imgs/login/choose01.png");
    $scope.imgCut=function(){
        if(targe==0){
            $(".rightimgCut").attr("src","../../assets/imgs/login/choose.png");
            targe=1;
        }else{
            $(".rightimgCut").attr("src","../../assets/imgs/login/choose01.png");
            targe=0;
        }

    };/*
    $scope.login=function(){
        $state.go("index.content.sys/sysDept/list")
    }*/
    //初始化UUID和生成验证码
    $scope.prevUUID={};
    $scope.flushValidateCode=function(){
        $.ajax({
            url: "/sofn-sys-web/getUUID",
            data:{prev:$scope.prevUUID},
            type:"get"
        }).done(function(data){
            $scope.prevUUID=data.uuid;
            //先获取UUID，再获取验证码图片
            $("#validateImg").prop("src","/sofn-sys-web/generatorImage?curr="+$scope.prevUUID);
            /*$.ajax({
                url: "/sofn-sys-web/generatorImage",
                data:{curr:$scope.prevUUID},
                type:"get"
            }).done(function(data){
            });*/
        });
    }
    $scope.login=function(){
        //需要加密的业务数据
        var data={account:$("#account").val(),password:$("#password").val(),uuid:$scope.prevUUID,code:$("#code").val()};
        //生成des随机key,此key用作提交的业务数据加密和服务器返回数据的解密
        var desKey=Math.uuid().replace(/-/g,'');
        //调用加密
        var dataStr=encodeDES(data,desKey);

        //从缓存中获取公钥对象
        var publicKey=JSON.parse(window.localStorage.getItem("publicKey"));
        //调用RSA加密
        var encodeKey=encodeRSA(desKey,publicKey);

        $.ajax({
            url:"/sofn-sys-web/login",
            type:"post",
            dataType:"json",
            data:{ciphertext:dataStr,desKey:encodeKey},
            success:function(json){
                if(json.httpCode!=200){
                    alert(json.msg);
                    return;
                }
                //des随机key解密token
                var decryptText=decryptDES(json.token,desKey)
                alert(decryptText);
                window.localStorage.setItem("token",decryptText);
                $state.go("index.content.sys/sysDept/list")
            },
            fail: function (data) {
                alert("失败");
            }
        });
    };
    $scope.getPublic=function(){
        $.ajax({
            url:"/sofn-sys-web/getPublicKey",
            type:"post",
            dataType:"json",
            success:function(json){
                if(json.httpCode!=200){
                    alert(json.msg);
                    return;
                }
                //des随机key解密token
                window.localStorage.setItem("publicKey",JSON.stringify(json.publicKey));
            },
            fail: function (data) {
                alert("失败");
            }
        });
    };
    //每次刷新页面验证码刷新
    $scope.flushValidateCode();
    $scope.getPublic();
})
