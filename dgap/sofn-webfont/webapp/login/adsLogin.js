var myapp=angular.module("myapp",[]);
var targe=0;
myapp.controller("adsLogin",function($scope,$http,$state){
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

    };
    $scope.login=function(){
        $state.go("index.content.sys/sysDept/list")
    }
    //$scope.login=function(){
    //    $.ajax({
    //        url:"/sofn-sys-web/login",
    //        type:"post",
    //        dataType:"json",
    //        data:{account:$("#account").val(),password:$("#password").val(),uuid:"1",code:$("#code").val()},
    //        success:function(json){
    //            window.localStorage.setItem("token",json.token);
    //            $state.go("index.content.sys/sysDept/list")
    //        },
    //        fail: function (data) {
    //            alert("失败");
    //        }
    //    });
    //};
    /*$http({
       url:""
        success(){

    }
    })*/
})
