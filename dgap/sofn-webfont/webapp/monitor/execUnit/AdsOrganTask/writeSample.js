var myapp = angular.module("myapp", []);
myapp.controller("writeSample", function ($scope, $http, $state) {
    var vm = this;
    var now = new Date();
    var nowStr = now.format("yyyy-MM-dd");

    $scope.sampleDate = nowStr;
    $scope.base = "";
    $scope.slaughter = "";
    $scope.market = "";
    $scope.wholesale = "";
    $scope.supermar = "";
    $scope.else = "";
    $scope.production = "";
    $scope.stock = "";
    $scope.mainBodyNumber = "";
    $scope.projectCategoryCode = "";
    $scope.sampleCode = "";//初始化为11
    var token = window.localStorage.getItem("token");
    $scope.monitorClass = window.localStorage.getItem("monitorClass");//监测类型
    var monitorTaskId = window.localStorage.getItem("adsMonitorTaskId");//监测任务
    $scope.taskName = window.localStorage.getItem("taskName");//监测任务名称
    $scope.checkObject = window.localStorage.getItem("checkObject").split(",");
    $scope.orgId = "";
    $scope.organName = "";
    $scope.sampleNumberUnit = "";
    $scope.sampleBaseUnit = "";
    $scope.sampleNumber = "";
    $scope.unitOptions = [];
    $scope.productTraceabilityCodeHasError = false;
    $scope.sampleCodeHasError = false;
    $scope.sampleNameHasError = false;
    $scope.produceDateHasError = false;
    $scope.sampleNumberHasError = false;
    $scope.sampleBaseHasError = false;
    $scope.sampleBaseUnitHasError = false;
    $scope.produceCertificate = "";
    $scope.produceCertificateStyle = {};
    $scope.sampleNumberUnitHasError = false;
    $scope.sampleName = "";
    $scope.packing = "";
    $scope.identify = "";
    $scope.samplePlace = "";
    $scope.samplePlaceStyle = {};
    $scope.productionsTatus = "";
    $scope.samplePerson = "";
    $scope.samplePersonArray = new Array();
    var errorStyle = {"border-color": "#FA787E"};
    $scope.testedDeparmentHasError = false;
    $scope.testedAddressHasError = false;
    $scope.testedLegalrepHasError = false;
    $scope.testedLinkmanHasError = false;
    $scope.testedPersonHasError = false;
    $scope.productionDeparmentHasError = false;
    $scope.productionAddressHasError = false;
    $scope.productionLinkmanHasError = false;
    $scope.sampleDeparmentHasError = false;
    $scope.sampleLinkmanHasError = false;
    $scope.sampleAddressHasError = false;
    $scope.proofHasError = false;
    $scope.sampleDateHasError = false;
    $scope.samplePersonHasError = false;
    $scope.samplePhoneHasError = false;
    $scope.testedPersonfaxHasError = false;
    $scope.testedLinkmanfaxHasError = false;
    var organTaskId = window.localStorage.getItem("organTaskId");//机构任务ID
    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.writeSample = function (data) {

        $state.go("index.content.monitor/execUnit/AdsOrganTask/writeSample")
    }
    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/specialReportUpload")
        }
    }


    $scope.subjEntListForAdsQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntListForAdsQueryParams"));
    $scope.subjEntTempListForAdsQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntTempListForAdsQueryParams"));


    //获取organID
    $http({
        url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
        method: "post",
        dataType: "json",
        params: {"token": token}
    }).success(function (json) {
        if (json.httpCode == 200) {
            $scope.orgId = json.user.orgId;
            $scope.organName = json.user.orgName;
            $scope.samplePerson = json.user.userName;
            $scope.samplePersonArray.push(json.user.userName);
            $scope.organizationId = json.user.organizationId;
            $scope.getTestingOrgInfo(json.user.orgName);

            $scope.organization = json.user.organization;
            //定义一个管辖区域，通过机构级别+区域来判断
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
            $scope.reloadArea();
            $scope.reloadAreaTemp();

            // if($scope.subjEntListForAdsQueryParams && $scope.subjEntListForAdsQueryParams.areaId){
            //     $.fn.mycity("sheng_ent", "shi_ent", "xian_ent", $scope.subjEntListForAdsQueryParams.areaId);
            // }
            //
            // if($scope.subjEntTempListForAdsQueryParams && $scope.subjEntTempListForAdsQueryParams.areaId){
            //     $.fn.mycity("sheng_entTemp", "shi_entTemp", "xian_entTemp", $scope.subjEntTempListForAdsQueryParams.areaId);
            // }

        }
    }).error(function () {
    });


    //重置区域
    $scope.reloadArea = function () {
        var shengAreaId = "";
        var shiAreaId = "";
        var xianAreaId = "";
        getRegion("100000", "sheng_ent");
        if ($scope.organization.orgLevel !== "1") {
            //生成经营主体 初始化
            if ($scope.organization.orgLevel === "2") {
                shengAreaId = ($scope.underArea) + "0000";
                $('#sheng_ent').val(shengAreaId);
                getRegion(shengAreaId, "shi_ent");
            }
            if ($scope.organization.orgLevel === "3") {
                shengAreaId = ($scope.underArea.substr(0, 2)) + "0000";
                $('#sheng_ent').val(shengAreaId);
                shiAreaId = ($scope.underArea) + "00";
                getRegion(shengAreaId, "shi_ent");
                $('#shi_ent').val(shiAreaId);
                getRegion(shiAreaId, "xian_ent");
            }
            if ($scope.organization.orgLevel === "4") {
                shengAreaId = ($scope.underArea.substr(0, 2)) + "0000";
                $('#sheng_ent').val(shengAreaId);
                shiAreaId = ($scope.underArea.substr(0, 4)) + "00";
                getRegion(shengAreaId, "shi_ent");
                $('#shi_ent').val(shiAreaId);
                xianAreaId = $scope.underArea;
                getRegion(shiAreaId, "xian_ent");
                $('#xian_ent').val(xianAreaId);
            }
            // $.fn.mycity("sheng_ent", "shi_ent", "xian_ent", $scope.underArea);

            if ($("#sheng_ent").val() != null && $("#sheng_ent").val() != "" && $("#sheng_ent").val() != undefined) {
                $("#sheng_ent").attr("disabled", true);
            }
            if ($("#shi_ent").val() != null && $("#shi_ent").val() != "" && $("#shi_ent").val() != undefined) {
                $("#shi_ent").attr("disabled", true);
            }
            if ($("#xian_ent").val() != null && $("#xian_ent").val() != "" && $("#xian_ent").val() != undefined) {
                $("#xian_ent").attr("disabled", true);
            }
        }
    };

    //重置区域
    $scope.reloadAreaTemp = function () {
        var shengAreaId = "";
        var shiAreaId = "";
        var xianAreaId = "";
        getRegion("100000", "sheng_entTemp");
        if ($scope.organization.orgLevel !== "1") {
            //生成经营主体 初始化
            if ($scope.organization.orgLevel === "2") {
                shengAreaId = ($scope.underArea) + "0000";
                $('#sheng_entTemp').val(shengAreaId);
                getRegion(shengAreaId, "shi_entTemp");
            }
            if ($scope.organization.orgLevel === "3") {
                shengAreaId = ($scope.underArea.substr(0, 2)) + "0000";
                $('#sheng_entTemp').val(shengAreaId);
                shiAreaId = ($scope.underArea) + "00";
                getRegion(shengAreaId, "shi_entTemp");
                $('#shi_entTemp').val(shiAreaId);
                getRegion(shiAreaId, "xian_entTemp");
            }
            if ($scope.organization.orgLevel === "4") {
                shengAreaId = ($scope.underArea.substr(0, 2)) + "0000";
                $('#sheng_entTemp').val(shengAreaId);
                shiAreaId = ($scope.underArea.substr(0, 4)) + "00";
                getRegion(shengAreaId, "shi_entTemp");
                $('#shi_entTemp').val(shiAreaId);
                xianAreaId = $scope.underArea;
                getRegion(shiAreaId, "xian_entTemp");
                $('#xian_entTemp').val(xianAreaId);
            }
            //临时注册主体初始化
            // $.fn.mycity("sheng_entTemp", "shi_entTemp", "xian_entTemp", $scope.underArea);
            if ($("#sheng_entTemp").val() != null && $("#sheng_entTemp").val() != "" && $("#sheng_entTemp").val() != undefined) {
                $("#sheng_entTemp").attr("disabled", true);
            }
            if ($("#shi_entTemp").val() != null && $("#shi_entTemp").val() != "" && $("#shi_entTemp").val() != undefined) {
                $("#shi_entTemp").attr("disabled", true);
            }
            if ($("#xian_entTemp").val() != null && $("#xian_entTemp").val() != "" && $("#xian_entTemp").val() != undefined) {
                $("#xian_entTemp").attr("disabled", true);
            }
        }
    };


    // 获取检测机构信息
    $scope.getTestingOrgInfo = function (orgName) {
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getTestingOrganization",
            method: "post",
            dataType: "json",
            params: {"orgName": orgName}
        }).success(function (json) {
            if (json.httpCode == 200) {
                $scope.sampleDeparment = json.data.name;
                $scope.sampleLinkman = json.data.contact;
                $scope.sampleAddress = json.data.address;
                $scope.sampleZipcode = json.data.postcode;
                $scope.samplePhone = json.data.phone;
                $scope.sampleEmail = json.data.email;
            }
        }).error(function () {
        });
    }
    // 获取数量单位
    if (typeof(window.sessionStorage.unitDictData) == "undefined") {
        $http({
            url: "/sofn-ads-web/adsDictData/getUnitDictData",
            method: "post",
            dataType: "json"
        }).success(function (json) {
            if (json != null) {
                $scope.unitOptions = json.data;
                window.sessionStorage.setItem('unitDictData', JSON.stringify(json.data)); //缓存数量单位字典数据
            }
        }).error(function () {
        });
    } else {
        $scope.unitOptions = JSON.parse(window.sessionStorage.getItem('unitDictData'));
    }


    /**
     * 查询监测任务基本信息
     */
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ads-web/adsMonitorTask/queryById",
        method: "post",
        dataType: "json",
        data: monitorTaskId
    }).success(function (data) {
        if (data && data.adsMonitorTask && data.adsMonitorTask.publishStatus == "5") {
            $("#saveButton").hide();
            jBox.tip("该任务已废止,只能查看。", 'info');
        }
        $scope.proof = data.adsMonitorTask.attachmentcode;
    });
    // 当样品名称下拉框的值改变时，控制是否高亮显示
    $scope.sampleNameChange = function () {
        if ($scope.sampleName != null && $scope.sampleName != '') {
            $scope.sampleNameHasError = false;
        } else {
            $scope.sampleNameHasError = true;
        }
    }
    // 当抽样数量单位下拉框的值改变时，控制是否高亮显示
    $scope.sampleNumberUnitChange = function () {
        if ($scope.sampleNumberUnit != null && $scope.sampleNumberUnit != '') {
            $scope.sampleNumberUnitHasError = false;
        } else {
            $scope.sampleNumberUnitHasError = true;
        }
    }
    $scope.samplePlaceChange = function () {
        if ($scope.samplePlace != null && $scope.samplePlace != '') {
            $scope.samplePlaceStyle = {};
        }
    }

    var isSubmitting = false; // 是否正在提交，用于防重复提交
    //提交
    $scope.save = function () {
        var isAborted = false;
        //增加 已废止的状态 提醒
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ads-web/adsMonitorTask/queryById",
            method: "post",
            dataType: "json",
            data: monitorTaskId
        }).success(function (data) {
            if (data && data.adsMonitorTask && data.adsMonitorTask.publishStatus == "5") {
                $("#saveButton").hide();
                isAborted = true;
                jBox.tip("该任务已废止,只能查看。", 'info');
            }
        });

        if (isAborted)
            return;


        var producingArea = $("#xian").val();
        var producingAreaName = $.fn.Cityname($("#xian").val());

        $scope.sampleDate = $.trim($scope.sampleDate);
        if ($scope.sampleDate > nowStr) {
            jBox.tip("抽样时间不能大于当前时间", 'info');
            return false;
        }

        var fileName = "";
        var fileAddress = "";
        if (vm.files.length > 0) {
            fileName = vm.files[0].name;
            fileAddress = vm.files[0].src;
        }

        //表单验证
        var sampleCode = $scope.sampleCode;
        var sampleName = $scope.sampleName;
        var productTraceabilityCode = $scope.productTraceabilityCode;
        var produceDate = $scope.produceDate;
        var testedDeparment = $scope.testedDeparment;
        var testedAddress = $scope.testedAddress;
        var testedLegalrep = $scope.testedLegalrep;//受检单位法定代表人
        var testedPerson = $scope.testedPerson;
        var productionDeparment = $scope.productionDeparment;
        var productionAddress = $scope.productionAddress;
        var sampleDeparment = $scope.sampleDeparment;
        var sampleLinkman = $scope.sampleLinkman;
        var testedLinkmanphone = $scope.testedLinkmanphone;
        var testedLinkman = $scope.testedLinkman;//受检单位联系人
        var sampleAddress = $scope.sampleAddress;
        var productionLinkman = $scope.productionLinkman;
        var proof = $scope.proof;

        //验证样品编码是否重复
        var sampleFalg = false;
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById?time=" + new Date(),
            type: "post",
            async: false,
            data: {sampleCode: sampleCode, organTaskId: organTaskId},
            success: function (data) {
                $scope.detailData = data.data;
                if ($scope.detailData == null) {
                    sampleFalg = true
                }
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
        if (!sampleFalg) {
            jBox.tip("样品编码已经存在，请重新输入", 'info');
            return false;
        }
        //必输项校验
        /*        if (productTraceabilityCode == null || productTraceabilityCode == '') {
                    jBox.tip("请输入产品编码", 'info');
                    $scope.productTraceabilityCodeHasError = true;
                    return false;
                }*/
        if (sampleCode == null || sampleCode == '') {
            jBox.tip("请生成样品编码", 'info');
            $scope.sampleCodeHasError = true;
            return false;
        }
        if (sampleName == null || sampleName == '') {
            jBox.tip("请选择样品名称", 'info');
            //$scope.sampleNameHasError = errorStyle;
            $scope.sampleNameHasError = true;
            return false;
        }
        if (produceDate == null || produceDate == '') {
            jBox.tip("请输入生产日期或批号", 'info');
            $scope.produceDateHasError = true;
            return false;
        }

        if ($scope.produceCertificate == '') {
            jBox.tip("请选择产品认证情况", 'info');
            $scope.produceCertificateStyle = errorStyle;
            return false;
        }

        if ($scope.sampleNumber == null || $scope.sampleNumber == '') {
            jBox.tip("请输入抽样数量", 'info');
            $scope.sampleNumberHasError = true;
            return false;
        }
        if ($scope.sampleNumber != null || $scope.sampleNumber != '') {
            if (!$scope.sampleNumber.match(/^\d{0,8}$/)) {
                jBox.tip("抽样数量必须为数字", 'info');
                $scope.sampleNumberHasError = true;
                return false;
            }
        }
        if ($scope.sampleNumberUnit == null || $scope.sampleNumberUnit == '') {
            jBox.tip("请输入抽样数量单位", 'info');
            $scope.sampleNumberUnitHasError = true;
            return false;
        }

        if( $scope.sampleBase ==null ||  $scope.sampleBase == ''){
            jBox.tip("请输入抽样基数", 'info');
            $scope.sampleBaseHasError = true;
            return false;
        }

        if ($scope.samplePlace == null || $scope.samplePlace == '') {
            jBox.tip("请选择抽样场所", 'info');
            $scope.samplePlaceStyle = errorStyle;
            return false;
        }

        if (testedDeparment == null || testedDeparment == '') {
            jBox.tip("请输入受检单位名称", 'info');
            $scope.testedDeparmentHasError = true;
            return false;
        }
        if (testedAddress == null || testedAddress == '') {
            jBox.tip("请输入详细地址，乡镇、街道/社区、楼牌号", 'info');
            $scope.testedAddressHasError = true;
            return false;
        }
        if (testedLegalrep == null || testedLegalrep == '') {
            jBox.tip("请输入法定代表人", 'info');
            $scope.testedLegalrepHasError = true;
            return false;
        }
        if (testedLinkman == null || testedLinkman == '') {
            jBox.tip("请输入联系人", 'info');
            $scope.testedLinkmanHasError = true;
            return false;
        }
        if (testedPerson == null || testedPerson == '') {
            jBox.tip("请输入受检人", 'info');
            $scope.testedPersonHasError = true;
            return false;
        }
        if (productionDeparment == null || productionDeparment == '') {
            jBox.tip("请输入生产单位名称", 'info');
            $scope.productionDeparmentHasError = true;
            return false;
        }
        if (productionLinkman == null || productionLinkman == '') {
            jBox.tip("请输入生产单位联系人", 'info');
            $scope.productionLinkmanHasError = true;
            return false;
        }
        if (productionAddress == null || productionAddress == '') {
            jBox.tip("请输入详细地址，乡镇、街道/社区、楼牌号", 'info');
            $scope.productionAddressHasError = true;
            return false;
        }
        if (sampleDeparment == null || sampleDeparment == '') {
            jBox.tip("请输入抽样单位名称", 'info');
            $scope.sampleDeparmentHasError = true;
            return false;
        }
        if (sampleLinkman == null || sampleLinkman == '') {
            jBox.tip("请输入联系人", 'info');
            $scope.sampleLinkmanHasError = true;
            return false;
        }
        if ($scope.sampleDate == null || $scope.sampleDate == '') {
            jBox.tip("请输入抽样时间", 'info');
            $scope.sampleDateHasError = true;
            return false;
        }
        if (sampleAddress == null || sampleAddress == '') {
            jBox.tip("请输入详细地址，乡镇、街道/社区、楼牌号", 'info');
            $scope.sampleAddressHasError = true;
            return false;
        }
        if (proof == null || proof == '') {
            jBox.tip("请输入检测任务依据", 'info');
            $scope.proofHasError = true;
            return false;
        }
        var productTraceabilityCodereg = /[a-zA-Z0-9\.]{1,53}/;
        //var tt = /^[a-zA-Z][a-zA-Z0-9_-]{1,20}$/;
        if (productTraceabilityCode != null && productTraceabilityCode != '' && productTraceabilityCode != undefined) {
            if (!productTraceabilityCode.match(productTraceabilityCodereg)) {
                jBox.tip("请正确输入产品编码", 'info');
                $scope.productTraceabilityCodeHasError = true;
                return false;
            }
        }

        var samplecodereg = /^[a-zA-Z0-9]{1,20}$/;
        if (!sampleCode.match(samplecodereg)) {
            jBox.tip("请正确输入样品编码", 'info');
            $scope.sampleCodeHasError = true;
            return false;
        }

        var produceDatereg = /^(\d{4})-(\d{2})-(\d{2})$/;
        // var produceDatereg = /^[-a-zA-Z0-9_]{1,18}$/;
        if (!produceDate.match(produceDatereg)) {
            jBox.tip("请正确输入生产日期或批号，如：1970-01-01", 'info');
            $scope.produceDateHasError = true;
            return false;
        }

        var testedDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;
        if (!testedDeparment.match(testedDeparmentreg)) {
            jBox.tip("受检单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            $scope.testedDeparmentHasError = true;
            return false;
        }
        var testedAddressreg = /[\s\S]{0,50}/;

        if (!testedAddress.match(testedAddressreg)) {
            jBox.tip("受检单位地址必须为1-50位字符", 'info');
            $scope.testedAddressHasError = true;
            return false;
        }

        var testedLegalrepreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedLegalrep.match(testedLegalrepreg)) {
            jBox.tip("法定代表人必须为1-60位汉字或英文字符", 'info');
            $scope.testedLegalrepHasError = true;
            return false;
        }

        var testedLinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedLinkman.match(testedLinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            $scope.testedLinkmanHasError = true;
            return false;
        }


        var testedPersonreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedPerson.match(testedPersonreg)) {
            jBox.tip("受检人必须为1-60位汉字或英文字符", 'info');
            $scope.testedPersonHasError = true;
            return false;
        }

        var productionLinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!productionLinkman.match(productionLinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            $scope.productionLinkmanHasError = true;
            return false;
        }

        var productionDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;

        if (!productionDeparment.match(productionDeparmentreg)) {
            jBox.tip("生产单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            $scope.productionDeparmentHasError = true;
            return false;
        }
        var productionAddressreg = /[\s\S]{0,50}/;

        if (!productionAddress.match(productionAddressreg)) {
            jBox.tip("生产单位地址必须为1-50位字符", 'info');
            $scope.productionAddressHasError = true;
            return false;
        }
        var sampleDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;

        if (!sampleDeparment.match(sampleDeparmentreg)) {
            jBox.tip("抽样单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            $scope.sampleDeparmentHasError = true;
            return false;
        }
        var sampleLinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!sampleLinkman.match(sampleLinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或字符", 'info');
            $scope.sampleLinkmanHasError = true;
            return false;
        }

        var sampleAddressreg = /[\s\S]{0,50}/;

        if (!sampleAddress.match(sampleAddressreg)) {
            jBox.tip("抽样单位地址必须为1-50位字符", 'info');
            $scope.sampleAddressHasError = true;
            return false;
        }

        //受检单位电话传真号码校验
        if (testedLinkmanphone != null && testedLinkmanphone != "") {
            var testedLinkmanphonereg = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))|(^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$)/;
            if (!testedLinkmanphone.match(testedLinkmanphonereg)) {
                jBox.tip("请正确输入受检单位联系人电话号码", 'info');
                return false;
            }
        }

        var testedLinkmanfax = $scope.testedLinkmanfax;
        if (testedLinkmanfax != null && testedLinkmanfax != "") {
            //var testedLinkmanfaxreg = /^\d{7,8}$/;
            var testedLinkmanfaxreg = /^((^0\d{2,3}-\d{7,8}$)|(^\([0]\d{2,3}\)\d{7,8}$)|(^[0-9]\d{7}$))$/;
            if (!testedLinkmanfax.match(testedLinkmanfaxreg)) {
                jBox.tip("请正确输入受检单位联系人传真", 'info');
                $scope.testedLinkmanfaxHasError = true;
                return false;
            }
        }

        var testedPersonphone = $scope.testedPersonphone;
        if (testedPersonphone != null && testedPersonphone != "") {
            var testedPersonphonereg = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))|(^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$)/;
            if (!testedPersonphone.match(testedPersonphonereg)) {
                jBox.tip("请正确输入受检单位电话号码", 'info');
                return false;
            }
        }

        var testedPersonfax = $scope.testedPersonfax;
        if (testedPersonfax != null && testedPersonfax != "") {
            var testedPersonfaxreg = /^((^0\d{2,3}-\d{7,8}$)|(^\([0]\d{2,3}\)\d{7,8}$)|(^[0-9]\d{7}$))$/;
            if (!testedPersonfax.match(testedPersonfaxreg)) {
                jBox.tip("请正确输入受检单位传真", 'info');
                $scope.testedPersonfaxHasError = true;
                return false;
            }
        }

        //抽样单位电话传真号码校验
        var samplePhone = $scope.samplePhone;
        if (samplePhone != null && samplePhone != "") {
            var samplePhonereg = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))|(^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$)/;
            if (!samplePhone.match(samplePhonereg)) {
                jBox.tip("请正确输入抽样单位电话号码", 'info');
                return false;
            }
        }

        var sampleFax = $scope.sampleFax;
        if (sampleFax != null && sampleFax != "") {
            var sampleFaxreg = /^((^0\d{2,3}-\d{7,8}$)|(^\([0]\d{2,3}\)\d{7,8}$)|(^[0-9]\d{7}$))$/;
            if (!sampleFax.match(sampleFaxreg)) {
                jBox.tip("请正确输入抽样单位传真", 'info');
                return false;
            }
        }

        var sampleBasereg = /^\d{0,8}$/;
        var sampleBase = $scope.sampleBase;
        if (sampleBase != null && sampleBase != "") {
            if (!sampleBase.match(sampleBasereg)) {
                jBox.tip("抽样基数必须为数字", 'info');
                $scope.sampleBaseHasError = true;
                return false;
            } else {
                if ($scope.sampleBaseUnit == null || $scope.sampleBaseUnit == "") {
                    jBox.tip("请输入抽样基数单位", 'info');
                    $scope.sampleBaseUnitHasError = true;
                    return false;
                }
            }
        }
        var sampleEmail = $scope.sampleEmail;
        if (sampleEmail != null && sampleEmail != "") {
            var sampleEmailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            if (!sampleEmail.match(sampleEmailreg)) {
                jBox.tip("请正确输入邮箱", 'info');
                return false;
            }
        }
        var productionZipcode = $scope.productionZipcode;
        if (productionZipcode != null && productionZipcode != "") {
            var productionZipcodereg = /^\d{6}$/;
            if (!productionZipcode.match(productionZipcodereg)) {
                jBox.tip("请正确输入生产单位邮编", 'info');
                return false;
            }
        }
        var sampleZipcode = $scope.sampleZipcode;
        if (sampleZipcode != null && sampleZipcode != "") {
            var sampleZipcodereg = /^\d{6}$/;
            if (!sampleZipcode.match(sampleZipcodereg)) {
                jBox.tip("请正确输入抽样单位邮编", 'info');
                return false;
            }
        }


        //生产单位电话传真号码校验
        var productionLinkmanphone = $scope.productionLinkmanphone;
        if (productionLinkmanphone != null && productionLinkmanphone != "") {
            var productionLinkmanphonereg = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))|(^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$)/;
            if (!productionLinkmanphone.match(productionLinkmanphonereg)) {
                jBox.tip("请正确输入生产单位电话号码", 'info');
                return false;
            }
        }

        var productionLinkmanfax = $scope.productionLinkmanfax;
        if (productionLinkmanfax != null && productionLinkmanfax != "") {
            var productionLinkmanfaxreg = /^((^0\d{2,3}-\d{7,8}$)|(^\([0]\d{2,3}\)\d{7,8}$)|(^[0-9]\d{7}$))$/;
            if (!productionLinkmanfax.match(productionLinkmanfaxreg)) {
                jBox.tip("请正确输入生产单位传真", 'info');
                return false;
            }
        }

        var params = {
            "sampleName": $scope.sampleName, "producingArea": producingArea,
            "tradeMark": $scope.tradeMark, "packing": $scope.packing,
            "grade": $scope.grade, "identify": $scope.identify, "producingAreaName": producingAreaName,
            "specification": $scope.specification, "standard": $scope.standard,
            "produceDate": $scope.produceDate, "produceCertificate": $scope.produceCertificate,
            "certificateCode": $scope.certificateCode, "sampleBase": $scope.sampleBase,
            "samplePlace": $scope.samplePlace, "testedDeparment": $scope.testedDeparment,
            "testedAddress": $scope.testedAddress, "testedLegalrep": $scope.testedLegalrep,
            "testedLinkman": $scope.testedLinkman, "testedLinkmanphone": $scope.testedLinkmanphone,
            "testedLinkmanfax": $scope.testedLinkmanfax, "testedPerson": $scope.testedPerson,
            "testedPersonphone": $scope.testedPersonphone, "testedPersonfax": $scope.testedPersonfax,
            "productionsTatus": $scope.productionsTatus, "productionDeparment": $scope.productionDeparment,
            "productionAddress": $scope.productionAddress, "productionZipcode": $scope.productionZipcode,
            "productionLinkman": $scope.productionLinkman, "productionLinkmanphone": $scope.productionLinkmanphone,
            "productionLinkmanfax": $scope.productionLinkmanfax, "sampleDeparment": $scope.sampleDeparment,
            "sampleLinkman": $scope.sampleLinkman, "sampleAddress": $scope.sampleAddress,
            "sampleZipcode": $scope.sampleZipcode, "samplePhone": $scope.samplePhone,
            "sampleFax": $scope.sampleFax, "sampleEmail": $scope.sampleEmail,
            "proof": $scope.proof, "samplePerson": $scope.samplePerson, "sampleDate": $scope.sampleDate,
            "sampleId": "org2921c0653483b9cb86efb324b3003",//抽样单位KEY
            "monitorTaskId": monitorTaskId, "organTaskId": organTaskId,//机构任务KEY
            "sampleOrganId": $scope.orgId, "productTraceabilityCode": $scope.productTraceabilityCode,
            "sampleBarCode": $scope.sampleCode, "sampleOrgan": $scope.organName,
            "sampleCode": $scope.sampleCode, "sampleReport": "0",
            "sampleNumber": $scope.sampleNumber, "sampleNumberUnit": $scope.sampleNumberUnit,
            "sampleBaseUnit": $scope.sampleBaseUnit, "fileName": fileName,
            "fileAddress": fileAddress
        }


        if (!isSubmitting) {
            isSubmitting = true;
            $http({
                url: "/sofn-ads-web/adsMonitorSample/insertAdsMonitorSample?token=" + token,
                method: "POST",
                data: params
            }).success(function (data) {
                if (data.httpCode == 200) {
                    jBox.tip("新增成功", 'info');
                    // 根据最新需求不跳转
                    //$state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")
                    // 重置产品编码、样品编码、样品名称
                    $scope.productTraceabilityCode = "";
                    $scope.sampleCode = "";
                    // $scope.sampleName = "请选择样品名称";
                    $scope.sampleName = "";
                } else if (data.httpCode == 409) {
                    jBox.tip("样品编码已经存在，请重新输入", 'info');
                } else {
                    jBox.tip("服务器内部错误", 'info');
                }
                isSubmitting = false;

            }).error(function () {
                jBox.tip("服务器异常，请稍后重新保存", 'info');
                isSubmitting = false;
            });
        }
    }
    //重置
    $scope.reset = function () {
        if (confirm("确认重置？")) {
            window.location.reload();
        } else {
            return;
        }
    }

    // 按回车键保存抽样单
    $scope.pressEnterKeySave = function (e) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {
            $scope.save();
        }
    }
    /**
     * 展示新增模态框
     */
    $scope.temporaryCode = function () {
        $("#dModal").modal("show");
    }
    //生成临时码
    $scope.addCheckItem = function () {
        var mainManagementBody = $("#mainManagementBody").val();
        var organizationForms = $("#organizationForms").val();
        var codeType = $scope.codeType;
        var mainBodyNumber = $scope.mainBodyNumber;
        var keyword = $scope.keyword;
        if (mainManagementBody == null || mainManagementBody == '') {
            jBox.tip("请选择主题类别", 'info');
            return false;
        }
        if (organizationForms == null || organizationForms == '') {
            jBox.tip("请选择组织形式", 'info');
            return false;
        }
        if (codeType == null || codeType == '') {
            jBox.tip("请选择农产品类型", 'info');
            return false;
        }
        if (mainBodyNumber == null || mainBodyNumber == '') {
            jBox.tip("请输入主体号", 'info');
            return false;
        }
        $http({
            url: "/sofn-ads-web/adsMonitorSample/temporaryCode?time=" + new Date(),
            method: "POST",
            params: {
                "mainManagementBody": mainManagementBody, "organizationForms": organizationForms,
                "mainBodyNumber": mainBodyNumber, "projectCategoryCode": codeType,
            },
            async: true
        }).success(function (data) {
            $scope.productTraceabilityCode = data.productTemporaryCode;//临时码
            $scope.productTraceabilityCodeHasError = false;
            $("#dModal").modal("hide");
            $('#grid').dataTable().api().ajax.reload();
        }).error(function () {
        });
    }
    //调用追溯码接口
    $scope.getMessage = function () {
        var URL = '/sofn-tts-web-branch/query/getSampleSingleInfo/' + $scope.productTraceabilityCode;
        $http({
            url: URL,
            method: "POST",
            params: {},
            async: true
        }).success(function (json) {
            if (json.httpCode == 200) {
                $scope.sampleName = json.data.productName;
                for (var i = 0; i < $scope.checkObject.length; i++) {
                    if ($scope.sampleName == ($scope.checkObject[i])) {
                        //根据追溯码规则赋值信息，若是入市追溯码(3开头)，则将结果赋值生产单位，受检单位为空
                        var flag = $scope.productTraceabilityCode.substr(0, 1);
                        if (flag == "3" && json.data.proEnterpriseName == null) {
                            $scope.produceDate = new Date(json.data.proTime).format('yyyy-MM-dd');
                            $scope.mainBodyNumber = json.data.entityScaleId;
                            $scope.testedDeparment = "";
                            $scope.testedAddress = "";
                            $scope.testedLegalrep = "";
                            $scope.testedLinkman = "";
                            $scope.testedLinkmanphone = "";
                            $scope.testedLinkmanfax = "";
                            $scope.testedPerson = "";
                            $scope.productionDeparment = json.data.enterpriseName;
                            $scope.productionAddress = json.data.address;
                            $scope.productionZipcode = json.data.proZipcode;
                            $scope.productionLinkman = json.data.contactName;
                            $scope.productionLinkmanphone = json.data.contactPhone;
                            $scope.productionLinkmanfax = json.data.faxNumber;
                        } else {
                            $scope.produceDate = new Date(json.data.proTime).format('yyyy-MM-dd');
                            $scope.mainBodyNumber = json.data.entityScaleId;
                            $scope.testedDeparment = json.data.enterpriseName;
                            $scope.testedAddress = json.data.address;
                            $scope.testedLegalrep = json.data.legalName;
                            $scope.testedLinkman = json.data.contactName;
                            $scope.testedLinkmanphone = json.data.contactPhone;
                            $scope.testedLinkmanfax = json.data.faxNumber;
                            $scope.testedPerson = json.data.contactName;
                            $scope.productionDeparment = json.data.proEnterpriseName;
                            $scope.productionAddress = json.data.proAddress;
                            $scope.productionZipcode = json.data.proZipcode;
                            $scope.productionLinkman = json.data.proContactName;
                            $scope.productionLinkmanphone = json.data.proContactPhone;
                            $scope.productionLinkmanfax = json.data.proFaxNumber;
                        }
                        swal('', '追溯码确认成功,相关信息已填入表单', 'success');
                        return;
                    }
                }
                swal('', '该产品编码生成的样品名称不在所要检测的范围内,请重试！', 'error');
                $scope.sampleName = "";
            } else {
                swal('', '追溯码确认失败', 'error');
            }
        }).error(function () {
        });
    }

    //生成样品编码
    $scope.temporarySampleCode = function () {
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getSampleCode",
            method: "POST",
            params: {
                "orgId": $scope.orgId, "monitorTaskId": monitorTaskId
            },
            async: true
        }).success(function (data) {
            $scope.sampleCode = data.data;
            $scope.sampleCodeHasError = false;
            if ($scope.sampleCode != null || $scope.sampleCode != '') {
                $("#printButton").css("background-color", "");
                $("#printButton").on("click", function () {
                    var sampleShowCode = $("#sampleshowcode").val();
                    if (sampleShowCode != null && sampleShowCode != "") {
                        $('#choose_print_modal').modal();
                    } else {
                        $.jBox.tip("请生成或输入样品编码");
                    }
                });
                $("#showTaskName").text($scope.taskName);
                $("#showSampleCode").text($scope.sampleCode);
            }
        }).error(function () {
        });
    }

    window.printSampleCode = function () {
        var sampleShowCode = $("#sampleshowcode").val();
        if (sampleShowCode != null && sampleShowCode != "") {
            $('#choose_print_modal').modal();
        } else {
            $.jBox.tip("请生成或输入样品编码");
        }
    }

    //打印设置
    $("#printArea").click(function () {
        /* $("#printCount").printArea();*/

        $('#choose_print_modal').one('hidden.bs.modal', function () {
            $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");
        }).modal('hide');
        window.localStorage.setItem("adsTaskSampleSampleCode", $scope.sampleCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey", $scope.taskName);//任务名称


    });

    window.showPrintButton = function () {
        var sampleCode = $scope.sampleCode;
        if (sampleCode != null || sampleCode != '') {
            $("#printButton").css("background-color", "");
            $("#printButton").on("click", printSampleCode);
            $("#showTaskName").text($scope.taskName);
            $("#showSampleCode").text(sampleCode);
        }
    };

    window.verifyCode = function () {
        //执行样品编码规则校验
        var sampleCode = $scope.sampleCode;
        var samplecodereg = /^[a-zA-Z0-9]{1,20}$/;
        if (!sampleCode.match(samplecodereg)) {
            jBox.tip("请正确输入样品编码", 'info');
            $scope.sampleCodeHasError = true;
        }
    };
    $(function () {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });

    //后台加载行政区划信息
    getRegion("100000", "sheng");

    window.regionChanged = function (option) {
        if (option.id === "sheng") {
            $("#shi" + " option:gt(0)").remove();
            $("#xian" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "shi");
        } else {
            $("#xian" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "xian");
        }
    };

    window.regionChangedEnt = function (option) {
        if (option.id === "sheng_ent") {
            $("#shi_ent" + " option:gt(0)").remove();
            $("#xian_ent" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "shi_ent");
        } else {
            $("#xian_ent" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "xian_ent");
        }
    };

    window.regionChangedTemp = function (option) {
        if (option.id === "sheng_entTemp") {
            $("#shi_entTemp" + " option:gt(0)").remove();
            $("#xian_entTemp" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "shi_entTemp");
        } else {
            $("#xian_entTemp" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "xian_entTemp");
        }
    };


    function getRegion(regionId, id) {
        $.ajax({
            url: "/sofn-sys-web/region/queryMenu?&delFlag=Y",
            type: "post",
            dataType: "json",
            async: false,
            data: {regionId: regionId},
            success: function (data) {
                var regionName = data;
                $.each(regionName, function (k, p) {
                    var option = "<option value='" + p.id + "'>" + p.text + "</option>";
                    $("#" + id).append(option);
                });
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    // $.fn.mycity("sheng", "shi", "xian", 0, 0, 0);
    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }

    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
    }
    $scope.samplePrint = function (data) {
        window.localStorage.setItem("sampleCode", $scope.sampleCode);//监测类型
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")

    }
    $scope.writeSample = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/writeSample")

    }
    $scope.downloadSample = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/downloadsample")

    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")

    }
    $scope.sampleReport = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleReport")
    }

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }

    star = toUtf8($scope.mainBodyNumber);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height: 90, //高度
        text: star //任意内容
    });
    //弹出产品类型模态框
    /**
     * 选择产品类型modal
     */
    var selectCplxFlag = 0;
    $scope.chooseCplx = function () {
        $('#dModal').modal('hide');
        $('#choose_cplx_modal').modal();
        setTimeout(function () {
            if (selectCplxFlag == 0) {
                initCplxGrid();
            } else {
                $("#cplxGrid").dataTable().api().ajax.reload();
            }
        }, 300)
    }
    //获取主体类别数据字典
    $scope.subjType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: "7",
        method: "post"
    }).success(function (data) {
        $scope.subjType = JSON.parse(data.data);
    })

    //获取组织形式数据字典
    $scope.orgMode = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: "6",
        method: "post"
    }).success(function (data) {
        $scope.orgMode = JSON.parse(data.data);
    })


    /**
     * 弹出 选择主体 模态框
     */
    var chooseSubjEntFlag = 0;
    var chooseSubjEntTempFlag = 0;
    $scope.chooseSubjEnt = function () {
        $scope.showEnt();
    }

    //切换tab显示
    $scope.showEntTemp = function () {
        $('#choose_subjEnt_modal').modal("hide");
        $('#choose_subjEntTemp_modal').modal();
        $scope.reloadAreaTemp();
        setTimeout(function () {
            if (chooseSubjEntTempFlag == 0) {
                initEntTempGrid();
            } else {
                $("#entTempGrid").dataTable().api().ajax.reload();
            }
        }, 300)

    }

    $scope.showEnt = function () {
        $('#choose_subjEnt_modal').modal();
        $scope.reloadArea();
        $('#choose_subjEntTemp_modal').modal("hide");
        setTimeout(function () {
            if (chooseSubjEntFlag == 0) {
                initEntGrid();
            } else {
                $("#entGrid").dataTable().api().ajax.reload();
            }
        }, 300)

    }

    $scope.getAreaBySelect = function (id) {
        var province = $("#sheng_" + id).val();
        var city = $("#shi_" + id).val();
        var county = $("#xian_" + id).val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (province != null && province != undefined && province != "") {
            areaId = province;
        }
        return areaId;
    }


    //生产经营主体列表
    function initEntGrid() {

        //清理数据
        var tableName = $('#entGrid');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }

        tableName.dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntListForAds",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    var subjEntListForAdsQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntListForAdsQueryParams"));
                    if (subjEntListForAdsQueryParams) {

                        params.area = subjEntListForAdsQueryParams.area;
                        params.enterpriseName = subjEntListForAdsQueryParams.enterpriseName;
                    } else {
                        params.area = $scope.getAreaBySelect("ent");
                        params.enterpriseName = $("#choose_subjEnt_modal input[name='entName']").val();
                    }

                    //params.approveStatus="1";
                    params.token = window.localStorage.getItem("token");
                    ;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "enterpriseName",
                    title: "主体名称"
                },
                {
                    data: "entityScaleName",
                    title: "组织形式"
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业"
                },
                {
                    data: "entityTypeName",
                    title: "主体类别"
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data: "badRecord",
                    title: "不良记录（次）"
                },
                {
                    data: "createTime",
                    title: "创建时间"
                },
                {
                    data: "entityStatus",
                    title: "主体状态",
                    render: function (data, type, row) {
                        if (row.approveStatus == '1') {//正常
                            return "正常";
                        } else if (row.approveStatus == '6') {//已撤销
                            return "已注销";
                        } else if (row.approveStatus == '7') {//已注销
                            return "已注销";
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var code = row.creditCode;
                        if (row.entityScaleName == "个人" || row.entityScaleName == "家庭农场")
                            code = row.infoUnique;
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="chooseEntName(\'' + row.entityType + "\',\'" + row.entityScale + "\',\'" + row.id + "\',\'" + code + '\')">选择</a>';
                        return look_a;
                    }
                },
            ],
        });

        chooseSubjEntFlag++;
    }


    //临时生产经营主体列表
    function initEntTempGrid() {

        //清理数据
        var tableName = $('#entTempGrid');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }

        tableName.dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntTempListForAds",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    var subjEntTempListForAdsQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntTempListForAdsQueryParams"));
                    if (subjEntTempListForAdsQueryParams) {
                        params.areaId = subjEntTempListForAdsQueryParams.areaId;
                        params.entityName = subjEntTempListForAdsQueryParams.entityName;
                    } else {
                        params.areaId = $scope.getAreaBySelect("entTemp");
                        params.entityName = $("#choose_subjEntTemp_modal input[name='entName']").val();
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "entityName",
                    title: "主体名称"
                },
                {
                    data: "entityScale",
                    title: "组织形式"
                },
                {
                    data: "entityIndustry",
                    title: "所属行业"
                },
                {
                    data: "entityType",
                    title: "主体类别"
                },
                {
                    data: "areaId",
                    title: "所属区域",
                    render: function (data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data: "createTime",
                    title: "创建时间"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="chooseEntTempName(\'' + row.entityTypeId + "\',\'" + row.entityScaleId + "\',\'" + row.id + "\',\'" + row.orgCode + '\')">选择</a>';
                        return look_a;
                    }
                },
            ],
        });

        chooseSubjEntTempFlag++;
    }

    /**
     * 选择主体
     * @param row
     */
    window.chooseEntTempName = function (entityTypeId, entityScaleId, entityId, orgCode) {
        var entityTypeDict = "";
        var entityScaleDict = "";
        for (x in $scope.subjType) {
            if ($scope.subjType[x].id == entityTypeId)
                entityTypeDict = $scope.subjType[x].dictValue;
        }

        for (x in $scope.orgMode) {
            if ($scope.orgMode[x].id == entityScaleId)
                entityScaleDict = $scope.orgMode[x].dictValue;
        }

        $("#mainManagementBody").val(entityTypeDict);
        $("#organizationForms").val(entityScaleDict);

        $("#standardId").val(entityId);
        $("#standardName").val(orgCode);

        $scope.mainBodyNumber = orgCode;

        $("#choose_subjEnt_modal").modal("hide");
        $("#choose_subjEntTemp_modal").modal("hide");
    };
    window.chooseEntName = function (entityTypeId, entityScaleId, entityId, entityName) {
        var entityTypeDict = "";
        var entityScaleDict = "";
        for (x in $scope.subjType) {
            if ($scope.subjType[x].id == entityTypeId)
                entityTypeDict = $scope.subjType[x].dictValue;
        }

        for (x in $scope.orgMode) {
            if ($scope.orgMode[x].id == entityScaleId)
                entityScaleDict = $scope.orgMode[x].dictValue;
        }

        $("#mainManagementBody").val(entityTypeDict);
        $("#organizationForms").val(entityScaleDict);

        $("#standardId").val(entityId);
        $("#standardName").val(entityName);

        $scope.mainBodyNumber = entityName;

        $("#choose_subjEnt_modal").modal("hide");
        $("#choose_subjEntTemp_modal").modal("hide");
    };

    //重新加载表格
    $scope.querySubjEntList = function () {
        var subjEntListForAdsQueryParams = {};
        subjEntListForAdsQueryParams.area = $scope.getAreaBySelect("ent");
        subjEntListForAdsQueryParams.enterpriseName = $("input[name='entName']").val();
        subjEntListForAdsQueryParams.token = window.localStorage.getItem("token");
        window.sessionStorage.setItem("subjEntListForAdsQueryParams", JSON.stringify(subjEntListForAdsQueryParams));
        $scope.subjEntListForAdsQueryParams = subjEntListForAdsQueryParams;
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //重新加载表格
    $scope.querySubjEntTempList = function () {
        var subjEntTempListForAdsQueryParams = {};
        subjEntTempListForAdsQueryParams.areaId = $scope.getAreaBySelect("entTemp");
        subjEntTempListForAdsQueryParams.entityName = $("input[name='entTempName']").val();
        window.sessionStorage.setItem("subjEntTempListForAdsQueryParams", JSON.stringify(subjEntTempListForAdsQueryParams));
        $scope.subjEntTempListForAdsQueryParams = subjEntTempListForAdsQueryParams;
        $("#entTempGrid").dataTable().api().ajax.reload();
    }
    /**
     * 加载数据列表
     */
    $scope.productName = "";
    var initCplxGrid = function () {
        //清理数据
        var tableName = $('#cplxGrid');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        tableName.dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/getMappingModel",
                dataSrc: function (json) {
                    json.recordsTotal = json.total;//总个数
                    json.recordsFiltered = json.total;
                    json.pageNumber = json.pageNum;//页码
                    json.pageSize = json.pageSize;//每页个数
                    json.start = (json.pageNum - 1) * json.pageSize;//起始位置
                    json.length = json.pageSize;
                    return json.list;
                },
                data: function (params) {
                    params.monitorTaskId = monitorTaskId;
                    params.productName = $scope.productName;
                },
                type: "post"
            },
            columns: [
                {
                    data: "PRODUCT_NAME",
                    title: "农产品名称"
                },
                /*{
                 data: "monitorType",
                 title: "监测类型"
                 },*/
                {
                    data: "PRODUCT_ID",
                    title: "农产品种类"
                },
                {
                    data: "pk",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="chooseCplxName(\'' + row.PRODUCT_NAME + "\',\'" + row.PRODUCT_ID + '\')">选择</a>';
                        return look_a;
                    }
                },
            ],
        });
        selectCplxFlag++;
    }
    /**
     * 选择农产品类型
     * @param row
     */
    window.chooseCplxName = function (data, code) {
        $("#codeName").val(data);
        $("#sampleNameInput").val(data);
        $scope.sampleName = data;
        $scope.codeType = code;
        $("#choose_cplx_modal").modal("hide");
        // $("#dModal").modal();
    };

    $scope.searchProduct = function () {
        $("#cplxGrid").dataTable().api().ajax.reload();
    }

    /**
     * 产品认证情况和证书编码的联动
     * @param row
     */
    window.checkCertificateCode4 = function () {
        $('#certificateCode').attr("readonly", "readonly")//将input元素设置为readonly
        $scope.produceCertificateStyle = {};
    }
    window.checkCertificateCode1 = function () {
        $('#certificateCode').removeAttr("readonly");//将input元素设置为readonly
        $scope.produceCertificateStyle = {};
    }
    window.checkCertificateCode2 = function () {
        $('#certificateCode').removeAttr("readonly");//将input元素设置为readonly
        $scope.produceCertificateStyle = {};
    }
    window.checkCertificateCode3 = function () {
        $('#certificateCode').removeAttr("readonly");//将input元素设置为readonly
        $scope.produceCertificateStyle = {};
    }

    $scope.sampleNameOptions = [];
    //样品名称下拉选
    $http({
        url: "/sofn-ads-web/adsMonitorSample/findSampleName",
        method: "post",
        dataType: "json",
        params: {"monitorTaskId": monitorTaskId}
    }).success(function (data) {
        if (data != null) {
            $scope.sampleNameOptions = data.data.split("、");
        }
    }).error(function () {
    });

    //返回
    $scope.back = function () {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage");
    };

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-sys-web/sysTemplate/upload',
        //业务参数
        data: function () {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function (file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传图片类型文档', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };
    // 日历插件选择日期后的回调函数
    window.datePickerOnPicked = function (dp) {
        $scope.$apply(function () {
            $scope.sampleDate = dp.cal.getDateStr().trim();
        });
    }
    // 打开抽样人员选择弹框
    $scope.openChooseUserModal = function () {
        $scope.loadSampleUsers();
        $('#sample_person_modal').modal('show');
    }
    
    // 加载当前用户同一个机构下的用户数据，供选择抽样人员使用
    $scope.loadSampleUsers = function () {
        //bootstrap表格
        $("#usersGrid").dataTable({
            fnDrawCallback: function () {
                //判断是否全选
                $("#checkAll").prop("checked",$("#usersGrid input[name='checkOne']").length==$("#usersGrid input[name='checkOne']:checked").length);
                //点击行内任意内容即选择行
                $("#usersGrid tbody tr td").each(function(i,o){
                    $(o).click(function(){
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if(checked.checked){
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //用户移除
                            var user_t = $(this).parent().find("td").last().text();
                            for (var i = 0 ; i < $scope.samplePersonArray.length ; i++){
                                if($scope.samplePersonArray[i] != undefined && user_t == $scope.samplePersonArray[i]){
                                    $scope.samplePersonArray.splice(i,1);
                                }
                            }
                        }else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //用户添加
                            var user_t = $(this).parent().find("td").last().text();
                            $scope.samplePersonArray.push(user_t);
                        }
                        //判断是否全选
                        $("#checkAll").prop("checked",$("#usersGrid input[name='checkOne']").length==$("#usersGrid input[name='checkOne']:checked").length);
                    });
                })
            },
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/pageingQueryByOrganizationId",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.organizationId = $scope.organizationId;
                },
                type: "post"
            },
            columns: [{
                title: function (data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAllItems(this.checked)">';
                },
                data: "checkOne",
                render: function (data, type, row) {
                    if (type === 'display') {
                        for(var i = 0; i <  $scope.samplePersonArray.length;i++){
                            if ($scope.samplePersonArray[i] != undefined && row.userName == $scope.samplePersonArray[i]){
                                return "<input type='checkbox' checked='checked' name='checkOne' onclick=\"checkSelectThisBox(this)\">";
                            }
                        }
                        return '<input type="checkbox" name="checkOne" onclick="checkSelectThisBox(this)">';
                    }

                    return data;
                },
                className: "text-center",
                width: "5%"
            }, {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                className: "text-center",
                width: "10%"
            }, {
                data: "userName",
                title: "姓名",
                className: "text-center",
                width: "80%"
            },]
        });
    }


    //检测项全选,全不选
    window.checkAllItems = function (checked) {
        $("#usersGrid input[name='checkOne']").each(function (i, o) {
            $(o).prop("checked", checked);



            //更新选择的用户表信息
            //1、清空当前页的所有用户
            //2、若是全选，则重新添加

            var user_t = $(o).parent().next().next().text();
            for (var i = 0 ; i < $scope.samplePersonArray.length ; i++){
                if($scope.samplePersonArray[i] != undefined && user_t == $scope.samplePersonArray[i]){
                    $scope.samplePersonArray.splice(i,1);
                }
            }
            if(checked){
                $scope.samplePersonArray.push(user_t);
            }
        })
    }
    /**
     * 获取复选框选中行的用户姓名
     * @param tableId  表格标签的id名称（不带#号）
     */
    $scope.getSelectedUserName = function (tableId) {
        var rows = $("#" + tableId).DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var names = [];
        for (var i = 0; i < length; i++) {
            names.push(rows[i].userName);
        }

        return names;
    }


    //针对复选框无法选中bug
    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    }

    // 选择抽样人员
    $scope.chooseSamplePerson = function () {
        $scope.samplePerson = $scope.samplePersonArray.toString();
        $('#sample_person_modal').modal('hide');
    }
    // 自产单选钮被选择中复制受检单位到生产单位
    $scope.copyInfoToTested = function () {
        if ($scope.testedDeparment != '') {
            $scope.productionDeparment = $scope.testedDeparment;
        }
        if ($scope.testedAddress != '') {
            $scope.productionAddress = $scope.testedAddress;
        }
        if ($scope.testedLinkman != '') {
            $scope.productionLinkman = $scope.testedLinkman;
        }
        if ($scope.testedLinkmanphone != '') {
            $scope.productionLinkmanphone = $scope.testedLinkmanphone;
        }
        if ($scope.testedLinkmanfax != '') {
            $scope.productionLinkmanfax = $scope.testedLinkmanfax;
        }
    }
});
