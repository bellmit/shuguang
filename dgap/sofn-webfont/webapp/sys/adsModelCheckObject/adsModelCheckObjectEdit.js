var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckObjectEdit",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的id,name
    var id = window.localStorage.getItem("id");
    var name = window.localStorage.getItem("name");
    var numbers = window.localStorage.getItem("numbers");

    $(function() {
        //给DIV赋值
        $("#nameU").val(name);
        $("#idU").val(id);
        $("#numbersU").val(numbers);
    });

    //修改模型_检测对象名称按钮事件
    $scope.updateAdsModel = function(){
        if (!$("#updateAdsModelCheckObject").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var id = $("#idU").val();
        var name = $("#nameU").val();
        var numbers = $("#numbersU").val();
        $.ajax({
            url:"/sofn-sys-web/adsModelCheckObject/updateAdsModelCheckObject",
            type:"post",
            dataType:"json",
            data:{"id":id,"name":name,"numbers":numbers,"token":token},
            success:function(data){
                if(data.httpCode == 200){
                    jBox.tip("修改成功", 'info');
                }else if(data.httpCode == 400){
                    jBox.tip("没有登录", 'info');
                }else if(data.httpCode == 409){
                    jBox.tip("修改失败,该名称已被使用", 'info');
                }else{
                    jBox.tip("修改失败", 'info');
                }
            }
        });
    };

    $scope.back = function(){
        $state.go("index.content.sys/adsModelCheckObject/adsModelCheckObject");
    };

    $scope.edit = function(){
        window.localStorage.setItem("objectId",id);
        $state.go("index.content.sys/adsModelCheckObject/adsModelCheckObjectEditO");
    };

    $(document).ready(function() {
        $('#updateAdsModelCheckObject').bootstrapValidator();
    });
});