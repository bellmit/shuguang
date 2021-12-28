var myapp=angular.module("myapp",[]).controller("sysSubjSvAdd",function($scope,$http,$state){

    /** 第一部分，初始化时加载的内容 **/
    $('#addSvForm').bootstrapValidator();//初始化验证，必须有
    $scope.user = {};//当前用户
    $scope.organization = {};//当前组织机构（系统管理里的）
    $scope.subjSv={};//监管机构主体
    $scope.industryChecked = false;//默认所属行业未选中
    //获取用户信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        $scope.level();
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));
        //初始化区域下拉框初始选项--部级不限制区域
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
            if($scope.organization.orgLevel=="4"){
                alert("当前用户没有新增机构管理员权限");
            }
        }else{
            $.fn.mycity("sheng", "shi", "xian");
            // $("#sheng").attr("disabled", false);
            // $("#shi").attr("disabled", false);
            // $("#xian").attr("disabled", false);
        }
        $scope.handleProvAdmCounty();
    }).error(function(){
    });

    //获取机构级别数据字典
    $scope.level=function() {
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
            $scope.selectSvLevel($scope.organization.orgLevel);
        })
    };

    $scope.selectSvLevel = function(level) {
        for (var i = 0; i < $scope.orgLevel.length; i++) {
            //监管机构只能添加下级监管机构，当前机构级别对应1,2,3,4，数据字典对应0,1,2,3，所以相等即可
            if ($scope.orgLevel[i].dictValue == level) {
                $scope.subjSv.svLevelId = $scope.orgLevel[i].id;
                $("select[name='svLevel']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                $("select[name='svLevel']").attr("disabled", "disabled");
            }
        }
    };

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
            if($scope.orgType[i].dictName=="监管机构"){
                $scope.subjSv.svTypeId=$scope.orgType[i].id;
                $("select[name='svType']").append("<option value='"+$scope.orgType[i].id+"'>"+$scope.orgType[i].dictName+"</option>");
                $("select[name='svType']").attr("disabled","disabled");
            }
        }
    })

    //获取行业数据字典
    $scope.industryType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.industryType=JSON.parse(data.data);
    })

    /** 第二部分，主动调用才执行的部分 **/
    //限制部级只能选择北京市
    $scope.limitArea=function() {
        var level = $("select[name='svLevel'] option:selected").attr("dictVal");
        if(level=="0"){
            $.fn.mycity("sheng", "shi", "xian", 110000);
            $("#sheng").attr("disabled", true);
            $("#shi").attr("disabled", true);
            $("#xian").attr("disabled", true);
        }else if(level=="1"){
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
    //新增监管机构
    $scope.addSubjSupervise=function(){
        var industryCheck = [];
        var industryNameCheck = [];
        var industryValueCheck = [];

        //机构名称只能输入中文
        var myReg = /^[\u4e00-\u9fa5]+$/;
        if (!myReg.test($scope.subjSv.svName)) {
            jBox.tip("机构名称只能输入中文", 'info');
            return false;
        }


        $("input[name='entityIndustryId']:checked").each(function(){
            industryCheck.push($(this).val());
            industryValueCheck.push($(this).attr("dictVal"));
            industryNameCheck.push($(this).next().text());
        });
        if(industryCheck.length==0){
            $scope.industryChecked = true;
            return false;
        }
        $scope.industryChecked = false;
        $scope.subjSv.industryId=industryCheck.toString();
        $scope.subjSv.industryValue=industryValueCheck.toString();
        $scope.subjSv.industryName=industryNameCheck.toString();
        if (!$("#addSvForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        if(county==null||county==""||county==undefined){
            $("#addSvForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#addSvForm").bootstrapValidator();//初始化验证，与上面对应
            $("#addSvForm").data('bootstrapValidator').validate();
            return false;
        }
        var areaId = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        $scope.subjSv.svType=$("select[name='svType'] option:selected").text();
        $scope.subjSv.svLevel=$("select[name='svLevel'] option:selected").attr("dictVal");
        $scope.subjSv.svAreaId=areaId;
        $scope.subjSv.svArea=$.fn.Cityname(areaId);
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/addSubjSupervise",
            data:$scope.subjSv,
            method:"post"
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("保存成功", 'info');
                $state.go("index.content.sys/user/sysSubjSvList");
            }else if(data.httpCode=="409"){
                jBox.tip("机构名称、机构代码、所属区域,机构级别和所属行业有一样已存在！", 'info');
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(){
            $("button").attr("disabled",false);
            jBox.tip("保存失败", 'info');
        })
    };

    $scope.getAreaId = function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        return areaId;
    };
    // 处理省管县
    $scope.handleProvAdmCounty = function() {
        if ($scope.organization.orgLevel == "2") {
            $("#sheng, #shi, #xian").each(function (i, o) {
                $(o).click(function () {
                    $http({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url:"/sofn-asms-web/asmsTools/isProvAdmCounty",
                        params: {"code": $scope.getAreaId()},
                        method:"post"
                    }).success(function(res) {
                        if (res.data) {
                            // 省管县, 默认县级
                            $scope.selectSvLevel("3");
                        } else {
                            // 省管县, 默认市级
                            $scope.selectSvLevel("2");
                        }
                    })
                })
            })
        }
    }
})