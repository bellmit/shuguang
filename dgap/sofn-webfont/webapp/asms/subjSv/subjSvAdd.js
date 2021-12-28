var myapp=angular.module("myapp",[]).controller("subjSvAdd",function($scope,$http,$state){

    /** 第一部分，初始化时加载的内容 **/
    $('#addSvForm').bootstrapValidator();//初始化验证，必须有
    $scope.user = {};//当前用户
    $scope.organization = {};//当前组织机构（系统管理里的）
    $scope.subjSv={};//监管机构主体
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
        }else{
            $.fn.mycity("sheng", "shi", "xian");
            $("#sheng").attr("disabled", false);
            $("#shi").attr("disabled", false);
            $("#xian").attr("disabled", false);
        }
    }).error(function (data) {
        
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
            //判断当前用户的机构级别限定新增机构的级别
            if ($scope.organization.orgLevel == "1") {
                //部级可添加省级
                for (var i = 0; i < $scope.orgLevel.length; i++) {
                    //部级可添加部级
                    if ($scope.orgLevel[i].dictName == "省级") {
                        $scope.subjSv.svLevelId = $scope.orgLevel[i].id;
                        $("select[name='svLevel']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                        $("select[name='svLevel']").attr("disabled", "disabled");
                    }
                }
            } else if ($scope.organization.orgLevel == "2") {
                //省级可添加市级
                for (var i = 0; i < $scope.orgLevel.length; i++) {
                    //部级可添加部级
                    if ($scope.orgLevel[i].dictName == "市级") {
                        $scope.subjSv.svLevelId = $scope.orgLevel[i].id;
                        $("select[name='svLevel']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                        $("select[name='svLevel']").attr("disabled", "disabled");
                    }
                }
            } else if ($scope.organization.orgLevel == "3") {
                //市级，可添加县级
                for (var i = 0; i < $scope.orgLevel.length; i++) {
                    //部级可添加部级
                    if ($scope.orgLevel[i].dictName == "县级") {
                        $scope.subjSv.svLevelId = $scope.orgLevel[i].id;
                        $("select[name='svLevel']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                        $("select[name='svLevel']").attr("disabled", "disabled");
                    }
                }
            } else if ($scope.organization.orgLevel == "4") {
                //县级4，暂时默认县级
                for (var i = 0; i < $scope.orgLevel.length; i++) {
                    //部级可添加部级
                    if ($scope.orgLevel[i].dictName == "县级") {
                        $scope.subjSv.svLevelId = $scope.orgLevel[i].id;
                        $("select[name='svLevel']").append("<option selected=selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                        $("select[name='svLevel']").attr("disabled", "disabled");
                    }
                }
            }

        }).error(function (data) {
            
        });
    }
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
                $("select[name='svType']").attr("disabled","disabled");
            }
        }
    }).error(function (data) {
        
    });

    /** 第二部分，主动调用才执行的部分 **/
    //限制部级只能选择北京市
    $scope.limitArea=function() {
        var level = $("select[name='svLevel'] option:selected").attr("dictVal");
        //var level=2;
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
        if (!$("#addSvForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
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
                $state.go("index.content.sys/user/subjSvList");
            }else if(data.httpCode=="409"){
                jBox.tip("机构名称、机构代码、所属区域,机构级别和所属行业有一样已存在！", 'info');
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(){
            $("button").attr("disabled",false);
            jBox.tip("保存失败", 'info');
        })
    }
})