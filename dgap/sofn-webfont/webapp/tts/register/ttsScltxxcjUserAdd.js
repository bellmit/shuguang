var myapp=angular.module("myapp",[]).controller("ttsScltxxcjUserAdd",function($scope,$http,$state){
    $('#attributeForm').bootstrapValidator();
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")
    window.changeIdCard = function(){
        var idCard = $("input[name='idCard']").val();
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            method:'POST',
            url:'/sofn-tts-web/ttsScltxxcjUser/queryUserByIdCard?idCard='+idCard
        }).success(function(data){
            if(data.httpCode == 200){
                jBox.tip("身份证号码已被注册", 'error');
                $("input[name='idCard']").val('');
            }
        })
    }

    $('#attributeForm').bootstrapValidator();//必须有
    //新增测试信息
    $scope.addTtsScltxxcjUser = function(){
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }

        // 自定义按钮
        var submit = null;
        //表单
        var name = $("input[name='name']").val();
        var idCard = $("input[name='idCard']").val();
        var phone = $("input[name='phone']").val();
        var email = $("input[name='email']").val();
        if(name != null && name != '' && idCard != null && idCard != '' && phone != null && phone != ''){
           /* if(name.match('[\u4e00-\u9fa5]')){
                if(name.length < 2 || name.length > 10){
                    jBox.tip("姓名的长度为2-10个字符", 'info');
                    return;
                }
            }else{
                jBox.tip("姓名只能是汉字", 'info');
                $("input[name='name']").val('');
                return;
            }
            if(!(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(idCard)) || (idCard.length > 18 || idCard.length < 18)){
                jBox.tip("身份证号码格式不正确", 'info');
                $("input[name='idCard']").val('');
                return;
            }
            if(!(/^1[34578]\d{9}$/.test(phone))){
                jBox.tip("手机号码格式不正确", 'info');
                $("input[name='phone']").val('');
                return;
            }*/
        }else{
            jBox.tip("标注有*号的栏不能为空或是请选择状态", 'info');
            return;
        }
       /* if(email != null && email != ''){
            if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(email))){
                jBox.tip("邮箱格式不正确", 'info');
                $("input[name='email']").val('');
                return;
            }
        }*/
        var token =  window.localStorage.getItem("token");
        var userId = $.parseJSON(window.localStorage.getItem("entityInfo")).data.id;
        $scope.ttsScltxxcjUser.name = $("input[name='name']").val();
    	 $scope.ttsScltxxcjUser.idCard = $("input[name='idCard']").val();
    	 $scope.ttsScltxxcjUser.phone = $("input[name='phone']").val();
    	 $scope.ttsScltxxcjUser.email = $("input[name='email']").val();
    	 $scope.ttsScltxxcjUser.delFlag = $("input[name='delFlag']").val();
        $http({
            url:"/sofn-tts-web/ttsScltxxcjUser/addTtsScltxxcjUser?id="+userId+"&token="+token,
            data:$scope.ttsScltxxcjUser,
            method:"post",
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("保存成功", 'info');
            }else{
                jBox.tip("保存失败", 'error');
            }
            $state.go("index.content.tts/register/registerlist");
        })
    }
})