/**
 * Created by czx on 2016/12/13.
 */
var myapp = angular.module("myapp", []).controller("forgetpassword", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")


    inits($scope);
    $("#account").hide();
    $("#lxPhone").hide();
    $scope.entityScales={};
    function inits($scope) {
        //获取字典
        $http({
            url: "/sofn-tts-web/ttsScltxxcjRegiter/getEntityTypes",
            data: "",
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
          //  alert("httpCode:"+data.httpCode);
            $scope.industrys=data.data.industrys;
            $scope.subjPropertys=data.data.subjPropertys;
            $scope.subTypes=data.data.subTypes;
            $scope.orgs=data.data.orgs;
        }).error(function () {
            alert("加载失败");
        })

    };
    $scope.changeTab=function(param) {
        $("input[type='radio']").removeAttr('checked');
        $("#account").hide();
        $("#pwd").hide();
        if(param == 1){
            $("input[name='userType']:eq(0)").attr("checked",'checked');
            $("#pwd").show();
            // $("#id_radio")
        }else{
            $("input[name='userType1']:eq(0)").attr("checked",'checked');
            $("#account").show();
            $("#f_credNo").show();
            $("#f_entN").show();
            $("#f_usrN").show();
            $("#f_IdN").show();
            $("#id_title").show();
            $("#f_lxPhone").hide();
            $("#f_entName").html("");
            $("#f_entName").html("企业名称");
            $("#enterpriseNames").attr('placeholder','请输入企业名称');
            $("#f_usrName").html("");
            $("#f_usrName").html("法人姓名");
            $("#f_name").attr('placeholder','请输入法人姓名');
            $("#f_IdNo").html("");
            $("#f_IdNo").html("法人身份证号");
            $("#f_id").attr('placeholder','请输入法人身份证号');

        }
    };
    //忘记密码
    $("#organization").click(function () {
        var checkedUserTypeText = $("input[name='userType']:checked").attr("dictValue");
        // alert(checkedUserTypeText);
        switch (checkedUserTypeText) {
            case "3"://个人
                $("#credNo").hide();
                $("#entN").hide();
                $("#lxPhone").show();
                $("#usrName").html("");
                $("#usrName").html("负责人姓名");
                $("input[name='LEGAL_NAME']").attr('placeholder','请输入负责人姓名');
                $("#IdNo").html("");
                $("#IdNo").html("负责人身份证号");
                $("input[name='LEGAL_IDNUMBER']").attr('placeholder','请输入负责人身份证号');
                break;
            case "2"://家庭农场
                $("#credNo").hide();
                $("#entN").show();
                $("#lxPhone").hide();
                $("#entName").html("");
                $("#entName").html("家庭农场名称");
                $("#enterpriseName").attr('placeholder','请输入家庭农场名称');
                $("#usrName").html("");
                $("#usrName").html("运营者姓名");
                $("input[name='LEGAL_NAME']").attr('placeholder','请输入运营者姓名');
                $("#IdNo").html("");
                $("#IdNo").html("运营者身份证号");
                $("input[name='LEGAL_IDNUMBER']").attr('placeholder','请输入运营者身份证号');
                break;
            case "0"://企业/个体工商户
                $("#credNo").show();
                $("#entN").show();
                $("#lxPhone").hide();
                $("#entName").html("");
                $("#entName").html("企业名称");
                $("#enterpriseName").attr('placeholder','请输入企业名称');
                $("#usrName").html("");
                $("#usrName").html("法人姓名");
                $("input[name='LEGAL_NAME']").attr('placeholder','请输入法人姓名');
                $("#IdNo").html("");
                $("#IdNo").html("法人身份证号");
                $("input[name='LEGAL_IDNUMBER']").attr('placeholder','请输入法人身份证号');
                break;
            case "1"://合作社
                $("#credNo").show();
                $("#entN").show();
                $("#lxPhone").hide();
                $("#entName").html("");
                $("#entName").html("合作社名称");
                $("#enterpriseName").attr('placeholder','请输入合作社名称');
                $("#usrName").html("");
                $("#usrName").html("法人姓名");
                $("input[name='LEGAL_NAME']").attr('placeholder','请输入法人姓名');
                $("#IdNo").html("");
                $("#IdNo").html("法人身份证号");
                $("input[name='LEGAL_IDNUMBER']").attr('placeholder','请输入法人身份证号');
                break;
            default:
        }
    });
    //忘记账号
    $("#f_organization").click(function () {
        var checkedValue = $("input[name='userType1']:checked").attr("dictValue");
        // alert(checkedValue);
        switch (checkedValue) {
            case "3"://个人
                $("#f_credNo").hide();
                $("#f_entN").hide();
                $("#f_lxPhone").show();
                $("#f_usrName").html("");
                $("#f_usrName").html("负责人姓名");
                $("#f_name").attr('placeholder','请输入负责人姓名');
                $("#f_IdNo").html("");
                $("#f_IdNo").html("负责人身份证号");
                $("#f_id").attr('placeholder','请输入负责人身份证号');
                break;
            case "2"://家庭农场
                $("#f_credNo").hide();
                $("#f_entN").show();
                $("#f_lxPhone").hide();
                $("#f_entName").html("");
                $("#f_entName").html("家庭农场名称");
                $("#enterpriseNames").attr('placeholder','请输入家庭农场名称');
                $("#f_usrName").html("");
                $("#f_usrName").html("运营者姓名");
                $("#f_name").attr('placeholder','请输入运营者姓名');
                $("#f_IdNo").html("");
                $("#f_IdNo").html("运营者身份证号");
                $("#f_id").attr('placeholder','请输入运营者身份证号');
                break;
            case "0"://企业/个体工商户
                $("#f_credNo").show();
                $("#f_entN").show();
                $("#f_lxPhone").hide();
                $("#f_entName").html("");
                $("#f_entName").html("企业名称");
                $("#enterpriseNames").attr('placeholder','请输入企业名称');
                $("#f_usrName").html("");
                $("#f_usrName").html("法人姓名");
                $("#f_name").attr('placeholder','请输入法人姓名');
                $("#f_IdNo").html("");
                $("#f_IdNo").html("法人身份证号");
                $("#f_id").attr('placeholder','请输入法人身份证号');
                break;
            case "1"://合作社
                $("#f_credNo").show();
                $("#f_entN").show();
                $("#f_lxPhone").hide();
                $("#f_entName").html("");
                $("#f_entName").html("合作社名称");
                $("#enterpriseNames").attr('placeholder','请输入合作社名称');
                $("#f_usrName").html("");
                $("#f_usrName").html("法人姓名");
                $("#f_name").attr('placeholder','请输入法人姓名');
                $("#f_IdNo").html("");
                $("#f_IdNo").html("法人身份证号");
                $("#f_id").attr('placeholder','请输入法人身份证号');
                break;
            default:
        }
    });
    //忘记密码验证
    $(document).ready(function() {
        $('#attributeForm').bootstrapValidator();//必须有
//        提交
        $("#regin").click(function(){
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }//必须有
//           提交执行函数全写在这下面
            $scope.verification=function(){

                //var ENTITY_SCALE = $("input[name='ENTITY_SCALE']").val();

                $scope.TtsScltxxcjRegiter.entityScale = $("input[name='userType']:checked").val();
                $scope.TtsScltxxcjRegiter.account =$("input[name='ACCOUNT']").val();
                $scope.TtsScltxxcjRegiter.enterpriseName =$("input[name='ENTERPRISE_NAME']").val();
                $scope.TtsScltxxcjRegiter.creditCode =$("input[name='CREDIT_CODE']").val();
                $scope.TtsScltxxcjRegiter.legalName =$("input[name='LEGAL_NAME']").val();
                $scope.TtsScltxxcjRegiter.legalIdnumber =$("input[name='LEGAL_IDNUMBER']").val();


                $http({
                    url:"/sofn-tts-web/ttsScltxxcjRegiter/forgetpassword",
                    data: $scope.TtsScltxxcjRegiter,
                    method:"post",
                    headers: {
                        post: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }

                }).success(function(data){

                    if(data.code==200){
                        jBox.tip("输入信息正确，点击确定修改密码！","info")
                        window.localStorage.setItem("tts_ttsScltxxcjRegiter_id",data.account);
                        $state.go("tts/forgetpassword/password");
                    }else{
                        jBox.tip("你输入的信息有误！","error")
                    }

                }).error(function (){
                    jBox.tip("你输入的信息有误！","error")
                })
            };
        })
        //忘记账号验证
        $('#accounts').bootstrapValidator();//必须有
        $("#regins").click(function(){
            if (!$("#accounts").data('bootstrapValidator').validate().isValid()) {
                return false;
            }//必须有
//           提交执行函数全写在这下面
            $scope.verification1=function(){
                //var ENTITY_SCALE = $("input[name='ENTITY_SCALE']").val();
                $scope.TtsScltxxcjRegiter.entityScale = $("input[name='userType']:checked").val();
                // $scope.TtsScltxxcjRegiter.account =$("input[name='ACCOUNT']").val();
                $scope.TtsScltxxcjRegiter.enterpriseName =$("input[name='ENTERPRISE_NAME']").val();
                $scope.TtsScltxxcjRegiter.creditCode =$("input[name='CREDIT_CODE']").val();
                $scope.TtsScltxxcjRegiter.legalName =$("input[name='LEGAL_NAME']").val();
                $scope.TtsScltxxcjRegiter.legalIdnumber =$("input[name='LEGAL_IDNUMBER']").val();
                $scope.TtsScltxxcjRegiter.legalPhone =$("input[name='LEGALPHONE']").val();

                $http({
                    url:"/sofn-tts-web/ttsScltxxcjRegiter/forgetAccount",
                    data: $scope.TtsScltxxcjRegiter,
                    method:"post",
                    headers: {
                        post: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }
                }).success(function(data){
                    if(data.code==200){
                        /*jBox.tip("输入信息正确，点击确定修改密码！","info")
                         window.localStorage.setItem("tts_ttsScltxxcjRegiter_id",data.account);
                         $state.go("tts/forgetpassword/password");*/
                        $("#detail_modal").modal('show');
                        $("#msg").html(data.msg);
                        $("#jgName").html(data.name);
                        $("#jgPhone").html(data.phone);
                    }else{
                        jBox.tip("你输入的信息有误！","error")
                    }
                }).error(function (){
                    jBox.tip("你输入的信息有误！","error")
                })


            };
        })
/*   //重置
        $("#repeat").click(function(){
            $('#attributeForm').data('bootstrapValidator').resetForm(true);
        })*/

    });


});