var myapp=angular.module("myapp",[]);
myapp.controller("qryWriteSample",function($scope,$http,$state) {
    $('#attributeForm').bootstrapValidator();//必须有
    /**
     * public
     * */
    $.fn.mycity("sheng","shi","xian",0,0,0);
    $scope.dZOpr = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");//操作动作
    $scope.jcxxId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");//检测信息id
    $scope.cyxxid = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");//抽样单id
    $scope.alesTaskSample = {};//抽样单信息
    $scope.alesTaskSample.sampleCode = "";

    $('#attributeForm').bootstrapValidator();
    //验证是否通过
    $scope.dataFormValidator = function(){
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }else {
            return true;
        }
    };
    //清除验证状态
    $scope.dataFormValidatorReset = function(){
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };
    //获取当前登录用户（抽样人员默认显示当前登录人）
    $http({
        url:"/sofn-ales-web/subjEnforceLaw/findCurrentUserByToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        $scope.user = data.user;
        $("input[name='samplePerson']").val($scope.user.userName);
    }).error(function(){

    });
    //抽样时间显示问题（直接在文本框中显示当前时间）
    $(function(){
        $("#sampleDate").val(new Date().format("yyyy-MM-dd"));
    });

    /**
     * 动作判断
     * */
    if ("query" == $scope.dZOpr){
        //查看动作
        $("button[name='reset_button']").hide();
        $("button[name='saveInfo_button']").hide();
        $("input").attr("disabled","disabled");
        $("radio").attr("disabled","disabled");
        $("select").attr("disabled","disabled");
        $("span[name='productTraceabilityCodeButton']").hide();
    }else if("update" == $scope.dZOpr){
        //修改动作
        $("input[name='productTraceabilityCodeInput']").attr("disabled","disabled");
        $("input[name='sampleCodeInput']").attr("disabled","disabled");
    }else if("add" == $scope.dZOpr){
        $("button[name='reset_button']").show();//重置按钮显示
    }
    /**
     * 修改初始化
     * */
    if($scope.cyxxid != undefined && $scope.cyxxid !=null){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/alesmynewchecktask/getSampleById",
            method:"post",
            data:{"id":$scope.cyxxid}
        }).success(function(json){
            //通用数据传递
            $scope.alesTaskSample = json.data;
            console.info($scope.alesTaskSample);
            //行政区划
            $.fn.mycity("sheng","shi","xian",json.data.producingArea);
            //时间处理
            $("input[name='sampleDate']").val(new Date(parseInt(json.data.sampleDate)+28800000).toISOString().slice(0,10));
            //单选按钮
            $("input:radio[value='"+$scope.alesTaskSample.produceCertificate+"']").attr('checked','true');
            $("input:radio[value='"+$scope.alesTaskSample.samplePlace+"']").attr('checked','true');
            $("input:radio[value='"+$scope.alesTaskSample.productionsTatus+"']").attr('checked','true');
        });
    }

    //重置
    $scope.reset = function() {
        window.location.reload();
    }

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
    star=toUtf8($scope.alesTaskSample.sampleCode);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height:90, //高度
        text:star //任意内容
    });

    //检查样品编码是否存在
   $scope.checkCode=function(code){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesmynewchecktask/findAdsMonitorSample?time="+new Date(),
            method:"POST",
            params: {"sampleCode":code},
            async:true
        }).success(function(data){
            var idx = data.data;
            if(idx == 'false'){
                $.jBox.tip("样品编码已存在，请重新录入！");
                $("input[name='sampleCodeInput']").focus();
            }
        }).error(function(){
        });
    };

    /**
     * 临时码生成
     * */
    /**
     * 展示新增模态框
     */
    $scope.temporaryCode=function () {
        $("#dModal").modal("show");
    };
    //生成临时码
    $scope.addCheckItem=function () {
        var mainManagementBody=$("#mainManagementBody").val();
        var organizationForms=$("#organizationForms").val();
        var mainBodyNumber= $scope.mainBodyNumber;
        var projectCategoryCode=$scope.projectCategoryCode;
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesmynewchecktask/temporaryCode?time="+new Date(),
            method:"POST",
            params: {"mainManagementBody":mainManagementBody,"organizationForms":organizationForms,
                "mainBodyNumber":mainBodyNumber,"projectCategoryCode":projectCategoryCode},
            async:true
        }).success(function(data){
            $scope.alesTaskSample.productTraceabilityCode=data.productTemporaryCode;//临时码
            $("#dModal").modal("hide");
        }).error(function(){
        });
    }
});


