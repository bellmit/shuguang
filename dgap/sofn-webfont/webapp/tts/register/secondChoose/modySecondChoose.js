var myapp = angular.module("myapp", []).controller("modySecondChoose", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")

    inits($scope);
    $scope.bean = {};
    $scope.bean.ztProperty = {};
    $scope.bean.industryType={};

    var second = window.localStorage.getItem("tts_register_modysecond");
    if(!second || second.length <= 0){
        second = window.localStorage.getItem("againreg");
    }
        var second_json =JSON.parse(second);
        $scope.bean.userType=second_json.entityScale;
        $scope.bean.ztType=second_json.entityType;
        //主体类型
        var entityPropertys = second_json.entityProperty.split(",");

        entityPropertys.map(function(entityProperty){
            $scope.bean.ztProperty[entityProperty] = true;
        });

        //所属行业
        var industry = second_json.entityIndustry.split(",");

        industry.map(function (entityIndustry) {
            $scope.bean.industryType[entityIndustry]=true;
        })

        // $scope.bean.industryType=second_json.entityIndustry;



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
            // alert("httpCode:"+data.httpCode);
            $scope.industrys=data.data.industrys;
            $scope.subjPropertys=data.data.subjPropertys;
            $scope.subTypes=data.data.subTypes;
            $scope.orgs=data.data.orgs;
        }).error(function () {
            alert("加载失败");
        })

    };

    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue',
        increaseArea: '20%' // optional
    });
    
    $scope.secondChooseReNextStep=function () {
         $state.go("tts/register/register");
    }

    $scope.secondChooseNextStep = function () {
         // console.log('$scope.bean.ztProperty', $scope.bean.ztProperty);
        // return;
        //获取值
        var checkedUserType = $("input[name='userType']:checked").val();
        var checkedUserTypeName = $("input[name='userType']:checked").next().text();
        if(""==checkedUserType || null == checkedUserType || undefined == checkedUserType){
            alert("请选择组织形式!")
            return false;
        }
        var checkedZtType = $("input[name='ztType']:checked").val();
        var checkedZtTypeName = $("input[name='ztType']:checked").next().text();
        if(""==checkedZtType || null == checkedZtType || undefined == checkedZtType){
            alert("请选择主体类型!")
            return false;
        }

        var ztPropertys = $scope.bean.ztProperty;
        var checkedZtPropertys = [];
        for( ztProperty in ztPropertys){
            if(ztProperty.length && ztPropertys[ztProperty]){
                checkedZtPropertys[checkedZtPropertys.length] = ztProperty;
            }
        }
        var ztpnames = $scope.subjPropertys;
        var ztpdmap = {};
        for(var ztp in ztpnames){
            ztpdmap[ztpnames[ztp].id] = ztpnames[ztp].dictName;
        }
        var ztPropertyNames = [];
        for(zz in checkedZtPropertys){
            ztPropertyNames.push(ztpdmap[checkedZtPropertys[zz]]);
        }
        var checkedZtProperty = checkedZtPropertys.join(',');
        var checkedZtPropertyName = ztPropertyNames.join(',');
        if(checkedZtPropertys.length <= 0){
            alert("请选择主体属性!")
            return false;
        }

        var industry=$scope.bean.industryType;
        var industrys=[];
        for (industryType in industry){
            if(industryType.length && industry[industryType]){
            industrys[industrys.length]=industryType;
            }
        }
        var indnames = $scope.industrys;
        var indmap = {};
        for(var ind in indnames){
            indmap[indnames[ind].id] = indnames[ind].dictName;
        }
        var industrysNames = [];
        for(aa in industrys){
            industrysNames.push(indmap[industrys[aa]]);
        }
        var checkedIndustryType = industrys.join(',');
        var checkedIndustryTypeName = industrysNames.join(',');
        if(industrys.length <= 0){
            alert("请选择所属行业!")
            return false;
        }
        var data = {
            "entityScale": checkedUserType,
            "entityType": checkedZtType,
            "entityProperty": checkedZtProperty,
            "entityIndustry": checkedIndustryType,
            "entityScaleName":checkedUserTypeName,
            "entityTypeName":checkedZtTypeName,
            "entityPropertyName":checkedZtPropertyName,
            "entityIndustryName":checkedIndustryTypeName
        };
        data = JSON.stringify(data);
        //设置缓存
        //清除tts_register_first缓存
        window.localStorage.removeItem("tts_register_modysecond");
        //生成tts_register_first缓存
        window.localStorage.setItem("tts_register_modysecond", data);
        //判断跳转页面

        //var checkedUserTypeText = $("input[name='userType']:checked").val();
        var checkedUserTypeText = $("input[name='userType']:checked").attr("dictValue");
        switch (checkedUserTypeText) {
            case "3"://个人
                $state.go("tts/register/perisonl/modyPerisonl");
                break;
            case "2"://家庭农场
                $state.go("tts/register/homeFarm/modyHomeFarm");
                break;
            case "0"://企业/个体工商户
                $state.go("tts/register/enterprise/modyEnterprise");
                break;
            case "1"://合作社
                $state.go("tts/register/cooperation/modyCooperation");
                break;
            default:
                alert("请选择注册类型!");
        }
    };
});

