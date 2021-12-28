var myapp = angular.module("myapp", []).controller("password", function ($scope, $http, $state) {

    var account=window.localStorage.getItem("tts_ttsScltxxcjRegiter_id");

    $(document).ready(function() {
        $('#attributeForm').bootstrapValidator();//必须有
//        提交
        $("#regin").click(function(){
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }//必须有
//           提交执行函数全写在这下面
            $scope.updatepassword=function(){

                $scope.TtsScltxxcjRegiter.password = $("input[name='password']").val();
                $scope.TtsScltxxcjRegiter.password = $("input[name='confirmPassword']").val();
                $scope.TtsScltxxcjRegiter.account = account;

                $http({
                    url:"/sofn-tts-web/ttsScltxxcjRegiter/updatepassword",
                    data: $scope.TtsScltxxcjRegiter,
                    method:"post",
                    headers: {
                        post: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }

                }).success(function(data){
                    if(data.httpCode==200){
                        jBox.tip("修改密码成功！","info");
                        $state.go("login/logintts");
                    }
                }).error(function (){
                    jBox.tip("修改密码失败！","error")
                })

            };
        })
//    重置
        $("#repeat").click(function(){
            $('#attributeForm').data('bootstrapValidator').resetForm(true);
        })

    });
    // $scope.updatepassword=function(){
    //
    //        $scope.TtsScltxxcjRegiter.password = $("input[name='password']").val();
    //        $scope.TtsScltxxcjRegiter.account = account;
    //
    //    $http({
    //        url:"/sofn-tts-web/ttsScltxxcjRegiter/updatepassword",
    //        data: $scope.TtsScltxxcjRegiter,
    //        method:"post",
    //        headers: {
    //            post: {'Content-Type': 'application/x-www-form-urlencoded'}
    //        }
    //
    //    }).success(function(data){
    //        if(data.httpCode==200){
    //            jBox.tip("修改密码成功！","info");
    //            $state.go("login/logintts");
    //        }
    //    }).error(function (){
    //        jBox.tip("修改密码失败！","error")
    //    })
    //
    //};

});