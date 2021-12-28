var myapp=angular.module("myapp",[]).controller("sysSubjDtAdd",function($scope,$http,$state){

    /** 第一部分，初始化时加载的内容 **/
    $('#addDtForm').bootstrapValidator();//初始化验证，必须有
    //获取用户信息
    $scope.user = {};//当前用户
    $scope.organization = {};//当前组织机构（系统管理里的）
    $scope.subjDt={};//检测机构主体
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        $scope.initOrgLevel();//根据用户信息来初始化机构级别
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));
        //初始化区域下拉框初始选项
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sheng", "shi", "xian");
            // $.fn.mycity("sheng", "shi", "xian",110000);
            //  $("#sheng").attr("disabled", true);
            //  $("#shi").attr("disabled", true);
            // $("#xian").attr("disabled", true);
        }
    }).error(function(){
    });

    //获取机构级别数据字典
    $scope.initOrgLevel=function() {
        $scope.orgLevel = [];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            async: false,
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "1",
            method: "post"
        }).success(function (data) {
            $scope.orgLevel = JSON.parse(data.data);
            //判断当前用户的机构级别限定新增机构的级别
            //监管机构只能添加同级检测机构，当前机构级别对应1,2,3,4，数据字典对应0,1,2,3，所以-1相等即可
            for (var i = 0; i < $scope.orgLevel.length; i++) {
                if ($scope.orgLevel[i].dictValue == $scope.organization.orgLevel-1) {
                    $scope.subjDt.dtLevelId=$scope.orgLevel[i].id;
                    $("select[name='dtLevelId']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                    $("select[name='dtLevelId']").attr("disabled", "disabled");
                }
            }
        })
    }
    //获取资质数据字典
    $scope.qualifications=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"18",
        method:"post"
    }).success(function(data) {
        $scope.qualifications=JSON.parse(data.data);
    })
    //获取机构性质数据字典
    $scope.nature=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"25",
        method:"post"
    }).success(function(data) {
        $scope.nature=JSON.parse(data.data);
    })
    //获取机构类别数据字典
    $scope.orgType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"0",
        method:"post"
    }).success(function(data) {
        $scope.orgType=JSON.parse(data.data);
        for(var i=0;i<$scope.orgType.length;i++){
            if($scope.orgType[i].dictName=="检测机构"){
                $scope.subjDt.dtTypeId=$scope.orgType[i].id;
                $("select[name='dtTypeId']").append("<option value='"+$scope.orgType[i].id+"'>"+$scope.orgType[i].dictName+"</option>");
                $("select[name='dtTypeId']").attr("disabled","disabled");
            }
        }
    })

    /** 第二部分，主动调用才执行的部分 **/
    //限制部级只能选择北京市(8月21日需求确认,部级检测不限制区域)
    $scope.limitArea=function() {
        var level = $("select[name='dtLevelId'] option:selected").attr("dictVal");
        if(level=="1"){
            $.fn.mycity("sheng", "shi", "xian");
            $("#sheng").attr("disabled", false);
            $("#shi").attr("disabled", false);
            $("#xian").attr("disabled", false);
        }else if(level=="2"){
            $.fn.mycity("sheng", "shi", "xian");
            $("#sheng").attr("disabled", false);
            $("#shi").attr("disabled", false);
            $("#xian").attr("disabled", false);
        }else{
            $.fn.mycity("sheng", "shi", "xian");
            $("#sheng").attr("disabled", false);
            $("#shi").attr("disabled", false);
            $("#xian").attr("disabled", false);
        }
    }
    //新增检测机构
    $scope.addSubjDt=function() {
        if (!$("#addDtForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        if (county == null || county == "" || county == undefined) {
            $("#addDtForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#addDtForm").bootstrapValidator();//初始化验证，与上面对应
            $("#addDtForm").data('bootstrapValidator').validate();
            return false;
        }
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city + "00";
        } else if (province != null && province != undefined && province != "") {
            areaId = province + "0000";
        }
        $scope.subjDt.dtAreaId = areaId;
        $scope.subjDt.dtLevel = $("select[name='dtLevelId'] option:selected").attr("dictVal");
        $scope.subjDt.dtType = $("select[name='dtTypeId'] option:selected").text();
        $scope.subjDt.dtNature = $("select[name='dtNatureId'] option:selected").text();
        //验证资质
        if (!$scope.checkQualifications()) {
            return;
        }
        var dtQualificationTypes = [];
        $('[name="dtQualificationTypes"]:checked').each(function () {
            dtQualificationTypes.push($(this).val());
        });
        $scope.subjDt.dtQualifications = dtQualificationTypes.join("、");
        $scope.subjDt.dtArea = $.fn.Cityname(areaId);
        $("button").attr("disabled", true);
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjDetection/addSubjDetection",
            data: $scope.subjDt,
            method: "post"
        }).success(function (data) {
            $("button").attr("disabled", false);
            if (data.httpCode == "200") {
                jBox.tip("保存成功", 'info');
                $state.go("index.content.sys/user/sysSubjDtList");
            } else if (data.httpCode == "409") {
                jBox.tip("机构名称、机构代码有一样已存在！", 'info');
            } else {
                jBox.tip(data.msg, 'info');
            }
        }).error(function () {
            $("button").attr("disabled", false);
            jBox.tip("保存失败", 'info');
        })
    }

    $scope.checkQualifications = function() {
        if ($("input[name='dtQualificationTypes']:checked").length==0) {
            $("#dtQualificationTypesMsg").show();
            return false;
        }else {
            $("#dtQualificationTypesMsg").hide();
            return true;
        }
    }

})