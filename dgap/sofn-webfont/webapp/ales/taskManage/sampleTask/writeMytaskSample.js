var myapp = angular.module("myapp", []);
myapp.controller("writeMytaskSample", function ($scope, $http, $state, $q) {
    $scope.taskType = window.localStorage.getItem("ales_taskmanager_oration_tasktype");//任务类型
    $scope.operation = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");//操作动作
    $scope.objectId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");//检测信息id
    $scope.sampleId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
    $scope.productNames = window.localStorage.getItem("ales_taskmanage_mychecktask_productName_key_myTaskSamplelist_productName_key");//农产品名
    $scope.industry = window.localStorage.getItem("ales_taskmanage_mychecktask_industry_key_myTaskSamplelist_industry_key");//名称所属行业
    $scope.entrustTaskName = window.localStorage.getItem("ales_taskManage_sample_writeMytaskSample_checkTaskTaskName_print");//任务名称
   // $scope.standardName;
    $scope.standardName ="^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$)|(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)";
    var vm = this;//(生产经营临时主体注册-上传框对象);
    $('#entTempModal').bootstrapValidator(); //必须有(验证开启-生产经营临时主体注册)；
    //抽样单id
    $scope.alesTaskSample = {};//抽样单
    $scope.alesTaskSample.sampleCode = "";
    $scope.alesTaskSample.sampleName = $scope.productNames;//样品名称
    $scope.currentUser = {};//用户
    $scope.currentOrg = {};
    var isCode;
    $('#entTemp').bootstrapValidator(); //必须有(激活验证)
    switch ($scope.operation) {
        case "query":
            $("button[name='reset_button']").hide();
            $("button[name='saveInfo_button']").hide();
            $("input").attr("disabled", "disabled");
            $("radio").attr("disabled", "disabled");
            $("select").attr("disabled", "disabled");
            $("button[name='temporarySampleCode']").attr("disabled", "disabled");
            $("button[name='productTraceabilityCodeButton']").attr("disabled", "disabled");
            $("td[name='filesupload']").hide();
            $("td[name='filesview']").show();
            break;
        case "update":
            $("input[name='sampleCodeInput']").attr("disabled", "disabled");
            $("button[name='temporarySampleCode']").attr("disabled", "disabled");
            break;
        case "add":
            $("button[name='sampleCodePrint']").attr("disabled", true);//
            $("button[name='reset_button']").show();
            break;
    }

    $('#attributeForm').bootstrapValidator();
    $('#attributeForms').bootstrapValidator();
    $('#tempCodeForm').bootstrapValidator();//必须有

    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }  if (!$("#attributeForms").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };//验证是否通过
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
        $("#attributeForms").data("bootstrapValidator").destroy();
        $("#attributeForms").bootstrapValidator();
    };//清除验证状态
    
    //根据token获取执法机构信息
    $http({
        url: "/sofn-ales-web/alesTools/getTestingOrganization",
        method: "post",
        dataType: "json",
        params: {"token":  window.localStorage.getItem("token")}
    }).success(function (json) {
        if (json.httpCode == 200) {
            $scope.alesTaskSample.sampleDeparment = json.data.elName;
            $scope.alesTaskSample.sampleLinkman = json.data.elContact;
            $scope.alesTaskSample.sampleAddress = json.data.elAddress;
            $scope.alesTaskSample.samplePhone = json.data.elContactPhone;
        }
    }).error(function (json) {
        console.log(json);
        
    });

    var currentUserRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/subjEnforceLaw/findCurrentUserByToken",
        method: "post",
        dataType: "json"
    }).error(function (json) {
        console.log(json);
        
    });
    var alesTaskSampleRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/alesmynewchecktask/getJdccSampleById",
        method: "post",
        data: {"id": $scope.sampleId}
    }).error(function (msg) {
        console.log(msg);
        
    });




    //获取抽样单位数据字典
    var initSampleUnitTypes = function() {
        $scope.sampleUnitTypes = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/sysDict/getSysDictByCode",
            data: "9",
            method: "post"
        }).success(function (data) {
            $scope.a = JSON.parse(data.data);
            $scope.sampleUnitTypes = [];
            var arr = new Array();
            //var idArr = new Array();
            for(var i=0;i<$scope.a.length;i++){
                arr.push($scope.a[i]);
                //idArr.push($scope.a[i].dictName);
            }
            //$("select[name='sampleUnitType'] option:first").val(idArr.toString());
            $scope.sampleUnitTypes = arr;
        }).error(function (data) {
            console.log(data);
            
        })
    }
    

    $q.all([currentUserRequest]).then(function (data) {
        $("#sampleCodeRule").text($scope.taskType);
        $scope.currentUser = data[0].data.user;
        return data;
    }, function (data) {
        $.jBox.tip("用户获取失败！", "error");
        console.log("用户获取失败！", data);
    }).then(function (data) {
        if ($scope.sampleId != undefined && $scope.sampleId != null) {
            $q.all([alesTaskSampleRequest]).then(function (data) {
                switch ($scope.taskType) {
                    case "JDCC":
                        $("#sampleCodeRule").text('JDCC');
                        break;
                }
                $scope.alesTaskSample = data[0].data.data;
                if($scope.alesTaskSample.sampleSource == "自产"){
                    $(".sampleSource").css("display","none");
                    $(".selfProduced").attr("checked",true);
                }else{
                    $(".sampleSource").css("display","block");
                    $(".notSelfProduced").attr("checked",true);
                };
                switch ($scope.operation) {
                    case "query":
                        //处理图片
                        if($scope.alesTaskSample.attachments!=null&&$scope.alesTaskSample.attachments!=undefined&&$scope.alesTaskSample.attachments!="") {
                            var array = $scope.alesTaskSample.attachments.split("*##*");
                            for (var i = 0; i < array.length; i++) {
                                // $("#imgSpot").append('<img style="padding-right: 10px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                                $("#imgSpot").append('<a data-fancybox href="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/> </a>');
                            }
                        }                
                        break;
                    case "update":
                        //回调附件
                        vm.files=[];
                        if($scope.alesTaskSample.attachments!=null&&$scope.alesTaskSample.attachments!=undefined&&      $scope.alesTaskSample.attachments!="") {
                            var urls = $scope.alesTaskSample.attachments.split("*##*");
                            var names = $scope.alesTaskSample.attachmentNames.split("*##*");
                            for (var i = 0; i < urls.length; i++) {
                                console.debug("src is:"+urls[i]+",name is:"+names[i]);
                                vm.files = vm.files.concat([{
                                    hidden: false,
                                    src: urls[i],
                                    name: names[i]}]);
                            }
                        };
                        break;
                    case "add":
                        break;
                };
                $scope.selects=[];
                var names = $scope.alesTaskSample.samplePerson.split(',');
                for (var i = 0; i < names.length; i++) {
                    $scope.selects = $scope.selects.concat([{
                        id: '',
                        userName: names[i]}]);
                }
                $("input[name='produceDate']").val(new Date(parseInt($scope.alesTaskSample.produceDate) + 28800000).toISOString().slice(0, 10));
                return data;
            }, function (data) {
                $.jBox.tip("抽样单获取失败！", "error");
                console.log("抽样单获取失败！", data);
            })
        }
    }).then(initSampleUnitTypes);

    //保存
    $scope.saveInfo = function () {
        if ($scope.alesTaskSample.sampleCode == "") {
            $.jBox.tip("请输入样品编码！");
            return;
        }else if(isCode == "false"){
            return;
        }else if(!/^\w+$/.test($scope.alesTaskSample.sampleCode)){
            $.jBox.tip("样品编码无效！");
            return;
        }else if($scope.alesTaskSample.sampleCode == undefined){
            $.jBox.tip("样品编码无效！");
            return;
        }if($scope.alesTaskSample.sampleSource =="" || $scope.alesTaskSample.sampleSource == undefined){
            $.jBox.tip("请填写样品来源！");
            return;
        }
        if (!$scope.dataFormValidator()) {
            return false;
        }
        
        //取附件照片，并判断是否有至少一张
        $scope.alesTaskSample.attachments = "";
        $scope.alesTaskSample.attachmentNames = "";
        for(var i=0;i<vm.files.length;i++){
            if(i==vm.files.length-1){
                $scope.alesTaskSample.attachments = $scope.alesTaskSample.attachments+vm.files[i].src;
                $scope.alesTaskSample.attachmentNames = $scope.alesTaskSample.attachmentNames+vm.files[i].name;
            }else{
                $scope.alesTaskSample.attachments = $scope.alesTaskSample.attachments+vm.files[i].src+"*##*";
                $scope.alesTaskSample.attachmentNames = $scope.alesTaskSample.attachmentNames+vm.files[i].name+"*##*";
            }
        }
        console.log("上传图片的信息2:"+$scope.alesTaskSample.attachments);
        if($scope.alesTaskSample.attachments == null | $scope.alesTaskSample.attachments == "" | $scope.alesTaskSample.attachments == undefined){
            console.log("上传图片的信息:"+$scope.alesTaskSample.attachments);
            jBox.tip("请上传一张受检单位签字照片！", 'info');
            return false;
        }
        if($scope.alesTaskSample.attachments == "null"){
            console.log("上传图片的信息:"+$scope.alesTaskSample.attachments);
            jBox.tip("请上传一张受检单位签字照片！", 'info');
            return false;
        }

        $scope.alesTaskSample.id = $scope.sampleId;
        $scope.alesTaskSample.sampleUnitType = $("select[name='sampleUnitType']").val();
        $scope.alesTaskSample.sampleBaseUnitType = $("select[name='sampleBaseUnitType']").val();
        $scope.alesTaskSample.produceDate = new Date(($("input[name='produceDate']").val() + " 00:00:00").replace(/-/g, "/"));
        if($scope.alesTaskSample.samplePerson =="" || $scope.alesTaskSample.samplePerson == null || $scope.alesTaskSample.samplePerson == undefined){
            jBox.tip("请选择抽样人员",'info');
            return false;
        }
        var URL;
        $scope.alesTaskSample.organTaskId = $scope.objectId;
        if ($scope.sampleId != undefined && $scope.sampleId != null) {
            URL = "/sofn-ales-web/alesmynewchecktask/updJdccSample";
        } else {
            URL = "/sofn-ales-web/alesmynewchecktask/addJdccSample";
        }
        $("button").attr("disabled",true);
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: URL,
            method: "POST",
            data: $scope.alesTaskSample
        }).success(function (data) {
            if(data.httpCode == "400"){
                $("button").attr("disabled",false);
                $.jBox.tip(data.msg);
            }else{
                $("button").attr("disabled",false);
                $.jBox.tip("成功");
                switch ($scope.taskType) {
                    case "JDCC":
                        $state.go("index.content.ales/taskManage/sampleTask/taskSampleList");
                        break;
                }
            }

        }).error(function (data) {
            console.log(data);
            
            $("button").attr("disabled",false);
            $.jBox.tip("请求失败");
            console.log("抽样单保存异常！", data);
        });
    };

    //返回
    $scope.back = function () {
        switch ($scope.taskType) {
            case "JDCC":
                $state.go("index.content.ales/taskManage/sampleTask/taskSampleList");
                break;
        }
    };

    //重置
    $scope.reset = function () {
        window.location.reload();
    };
    $scope.samplePrint = function (data) {
        window.localStorage.setItem("alesTaskSampleSampleCode", $scope.alesTaskSample.sampleCode);//监测类型
        $state.go("index.content.ales/taskManage/sample/samplePrint")
    };
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

    var content = toUtf8($scope.alesTaskSample.sampleCode);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height: 90, //高度
        text: content //任意内容
    });

    //调用追溯码接口(追溯码确认)
    $scope.getMessage = function () {
        var URL = '/sofn-tts-web-branch/query/getSampleSingleInfo/' + $scope.alesTaskSample.productTraceabilityCode;
        $http({
            url: URL,
            method: "POST",
            params: {
            },
            async: true
        }).success(function (json) {
            if (json.httpCode == 200) {
                //判断
                if($scope.productNames != json.data.productName ){
                    jBox.tip("样品名称与抽样农产品名称不符","info");
                    return false;
                }
                $scope.alesTaskSample.sampleName = json.data.productName;
                $scope.alesTaskSample.produceDate = new Date(json.data.proTime).format('yyyy-MM-dd');
               // $("input[name='produceDate']").val(new Date(parseInt($scope.alesTaskSample.produceDate) + 28800000).toISOString().slice(0, 10));
                $("input[name='produceDate']").val($scope.alesTaskSample.produceDate);
                $scope.alesTaskSample.mainBodyNumber = json.data.entityScaleId;
                $scope.alesTaskSample.testedDeparment = json.data.enterpriseName;
                $scope.alesTaskSample.testedAddress = json.data.address;
                $scope.alesTaskSample.testedLegalrep = json.data.legalName;
                $scope.alesTaskSample.testedLinkman = json.data.contactName;
                $scope.alesTaskSample.testedLinkmanphone = json.data.contactPhone;
                $scope.alesTaskSample.testedLinkmanfax = json.data.faxNumber;
                $scope.alesTaskSample.testedPerson = json.data.contactName;
                $scope.alesTaskSample.productionDeparment = json.data.proEnterpriseName;
                $scope.alesTaskSample.productionAddress = json.data.proAddress;
                $scope.alesTaskSample.productionZipcode = json.data.proZipcode;
                $scope.alesTaskSample.productionLinkman = json.data.proContactName;
                $scope.alesTaskSample.productionLinkmanphone = json.data.proContactPhone;
                $scope.alesTaskSample.productionLinkmanfax = json.data.proFaxNumber;
                swal('', '追溯码确认成功,相关信息已填入表单', 'success');
            } else {
                swal('', '追溯码确认失败', 'error');
            }
        }).error(function (json) {

        });
    }

    /**
     * 获取当前用户所属区域
     */

    var HyFlFunc = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        method: "post",
        data: "2"
    }).error(function (data) {
        console.log(data);
        
    });
    var currentOrgRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method: "post",
        dataType: "json"
    }).error(function (json) {
        console.log(json);
        
    });
    $q.all([HyFlFunc, currentOrgRequest]).then(function (data) {
        $scope.HyFl = JSON.parse(data[0].data.data);
        $scope.currentOrg = data[1].data.data;
        $scope.underArea = $scope.currentOrg.regionId.substr(0,2*($scope.currentOrg.orgLevel-1));
        //初始化区域下拉框初始选项
        $scope.reloadArea();
    });
    /**
     * 选择主体
     * 日期:2018年2月26日13:40:20
     */
    $scope.chooseBody = function () {
        $("#sJUnit_Model").modal('show');
        var table = $('#sJUnitTaskGrid');
        if ($.fn.dataTable.isDataTable(table)) {
            table.DataTable().clear();
            table.DataTable().destroy();
        }
        $("#sJUnitTaskGrid").dataTable({
            fnDrawCallback: function () {
                //设置选中状态
                $("#sJUnitTaskGrid >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ck']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#sJUnitTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            },
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/getSubjEntList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//排查bug开端
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;
                    json.length = json.page.length;
                    return json.list;
                },
                error:function (msg) {
                    console.log(msg);
                    
                }
                ,
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.enterpriseName = $("input[name='sjUnitName']").val();
                    params.entityIdcode = $("input[name='entIdCode']").val();
                    params.approveStatus = "1,3,4,5";//可用主体
                    params.entityIndustry = $scope.allIndustryId;
                    var province = $("#sjUnitShen").val();
                    var city = $("#sjUnitShi").val();
                    var county = $("#sjUnitXian").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (city != null && city != undefined && city != "") {
                        areaId = city;
                    } else if (province != null && province != undefined && province != "") {
                        areaId = province;
                    }
                    params.area = areaId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '*',
                    render: function (data, type, row) {
                        return '<input type="radio" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                },
                {
                    data: 'id',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "enterpriseName",
                    title: "单位名称",
                    render: function (data, type, row) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                       /* return '<span name = "name">' + data + '</span>';*/
                    }
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业"
                },
                {
                    data:"infoUnique",
                    title:"",
                    visible:false
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                        var region = $.fn.Cityname(data);
                        var regions;
                        if (region) {
                            regions = region.substring(0, data.length);
                            var showdata = regions;
                            if (region.length > 14) {
                                showdata = region.substring(0, 14) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                       /* return $.fn.Cityname(data);*/
                    }
                }
            ]

        });
        $("#sJUnitTaskGridTemp").dataTable({
            fnDrawCallback: function () {
                //设置选中状态
                $("#sJUnitTaskGridTemp >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ck']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#sJUnitTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            },
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/getSubjTempEntList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;
                    json.length = json.page.length;
                    return json.list;
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityName = $("input[name='sjUnitNameTemp']").val();
                    var province = $("#sjUnitShenTemp").val();
                    var city = $("#sjUnitShiTemp").val();
                    var county = $("#sjUnitXianTemp").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (city != null && city != undefined && city != "") {
                        areaId = city;
                    } else if (province != null && province != undefined && province != "") {
                        areaId = province;
                    }
                    params.areaId = areaId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '*',
                    render: function (data, type, row) {
                        return '<input type="radio" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                },

                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "entityName",
                    title: "单位名称",
                    render: function (data, type, row) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                        /*return '<span name = "name">' + data + '</span>';*/
                    }
                },
                {
                    data: "entityIndustry",
                    title: "所属行业"
                },
                {
                    data: "areaId",
                    title: "所属区域",
                    render: function (data, type, row) {
                        var region = $.fn.Cityname(data);
                        var regions;
                        if (region) {
                            regions = region.substring(0, data.length);
                            var showdata = regions;
                            if (region.length > 14) {
                                showdata = region.substring(0, 14) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                      /*  return $.fn.Cityname(data);*/
                    }
                }
            ]
        });
    };

    //查询受检单位 生产经营主体
    $scope.querySjUnit = function(){
        $("#sJUnitTaskGrid").dataTable().api().ajax.reload();
    }
    //查询受检单位 临时生产主体
    $scope.querySjUnitTemp = function(){
        $("#sJUnitTaskGridTemp").dataTable().api().ajax.reload();
    }
    //切换到生产经营主体
    $scope.switchEnt = function() {
        $("#entTab").addClass("gui-TabCheck");
        $("#entTempTab").removeClass("gui-TabCheck");
        $("#entDiv").show();
        $("#entTempDiv").hide();
    }
    //切换到临时生产经营主体
    $scope.switchEntTemp = function() {
        $("#entTempTab").addClass("gui-TabCheck");
        $("#entTab").removeClass("gui-TabCheck");
        $("#entTempDiv").show();
        $("#entDiv").hide();
    }

    //重置区域
    $scope.reloadArea=function(){
        if($scope.currentOrg.orgLevel!="1") {
            $.fn.mycity("sjUnitShen", "sjUnitShi", "sjUnitXian", $scope.underArea);
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp", $scope.underArea);
            $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
            if ($("#sjUnitShen").val() != null && $("#sjUnitShen").val() != "" && $("#sjUnitShen").val() != undefined) {
                $("#sjUnitShen").attr("disabled", true);
            }
            if ($("#sjUnitShenTemp").val() != null && $("#sjUnitShenTemp").val() != "" && $("#sjUnitShenTemp").val() != undefined) {
                $("#sjUnitShenTemp").attr("disabled", true);
            }

            if($("#subjTempSheng").val() != null && $("#subjTempSheng").val() != "" && $("#subjTempSheng").val() != undefined){
                $("#subjTempSheng").attr("disabled", true);
            }
            if ($("#sjUnitShi").val() != null && $("#sjUnitShi").val() != "" && $("#sjUnitShi").val() != undefined) {
                $("#sjUnitShi").attr("disabled", true);
            }
            if ($("#sjUnitShiTemp").val() != null && $("#sjUnitShiTemp").val() != "" && $("#sjUnitShiTemp").val() != undefined) {
                $("#sjUnitShiTemp").attr("disabled", true);
            }
            if ($("#subjTempShi").val() != null && $("#subjTempShi").val() != "" && $("#subjTempShi").val() != undefined) {
                $("#subjTempShi").attr("disabled", true);
            }
            if ($("#sjUnitXian").val() != null && $("#sjUnitXian").val() != "" && $("#sjUnitXian").val() != undefined) {
                $("#sjUnitXian").attr("disabled", true);
            }
            if ($("#sjUnitXianTemp").val() != null && $("#sjUnitXianTemp").val() != "" && $("#sjUnitXianTemp").val() != undefined) {
                $("#sjUnitXianTemp").attr("disabled", true);
            }
            if ($("#subjTempXian").val() != null && $("#subjTempXian").val() != "" && $("#subjTempXian").val() != undefined) {
                $("#subjTempXian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp");
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp", $scope.underArea);
            $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
        }
    }
    /**
     * 新增临时主体备案
     * 日期:2018年2月26日13:44:55
     */
    $scope.addEntTemp = function() {
        $("#entTempModal").modal("show");
        $("input[name='entityScaleId']:first").attr("checked", "checked");
        $("input[name='entityTypeId']:first").attr("checked", "checked");
        $("input[name='entityIndustryId']:first").attr("checked", "checked");
    };

    /**
     *新增生产经营主体临时备案
     *日期:2018年2月26日13:44:40
     */
    $scope.addSubjEntTemp=function(){
        var industryCheck = [];
        var industryNameCheck = [];
        $("input[name='entityIndustryId']:checked").each(function(){
            industryCheck.push($(this).val());
            industryNameCheck.push($(this).next().text());
        });
        if(industryCheck.length == 0){
            $scope.industryChecked = true;
            return false;
        }
        $scope.industryChecked = false;
        $scope.subjEntTemp.entityIndustryId=industryCheck.toString();
        $scope.subjEntTemp.entityIndustry=industryNameCheck.toString();
        //if (!$("#entTemp").data('bootstrapValidator').validate().isValid()) {
        //    return false;
        //}//必须有
        var province = $("#subjTempSheng").val();
        var city = $("#subjTempShi").val();
        var county = $("#subjTempXian").val();
        var areaId = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        $scope.subjEntTemp.entityScale=$("input[name='entityScaleId']:checked").next().text();
        $scope.subjEntTemp.entityType=$("input[name='entityTypeId']:checked").next().text();
        $scope.subjEntTemp.areaId=areaId;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEnt/addSubjEntTemp",
            data:$scope.subjEntTemp,
            method:"post"
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("保存成功", 'info');
                $("#entTempModal").modal("hide");
                $("#sJUnitTaskGridTemp").dataTable().api().ajax.reload();
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(data){
            console.log(data);
            
            $("button").attr("disabled",false);
            jBox.tip("保存失败", 'info');
        })
    }
    /**
     * 提交生产经营主体
     * @returns {boolean}
     */
    $scope.selectSzDwButt = function () {
        var rows = $("#sJUnitTaskGrid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.sjUnit = new Map();//生产主体单位
        var map = {
            enterpriseId: rows[0].id,
            production:rows[0].entityTypeName,
            tissue:rows[0].entityScaleName,
            subjectCode:rows[0].creditCode,
            identityCode:rows[0].infoUnique
    };
        $scope.sjUnit =map;
        $scope.productions = $scope.sjUnit.production; //主体类别
        $scope.tissues = $scope.sjUnit.tissue;      //组织形式
        if($scope.sjUnit.subjectCode == null){
            $scope.mainBodyNumber = $scope.sjUnit.identityCode;//身份证号
        }else {
        $scope.mainBodyNumber = $scope.sjUnit.subjectCode;//主体号
        }
        $("#sJUnit_Model").modal('hide');
    };//生产主体单位确定按钮

    /**
     * 提交临时生产主体
     * 注释日期:2018年2月28日10:32:57
     */
    $scope.subjEntTempInfo={};
 //   $scope.industryType=[];
    $scope.selectSzDwButtTemp = function () {
        var rows = $("#sJUnitTaskGridTemp").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.sjUnit = new Map();//临时主体单位
        var map = {
            enterpriseId: rows[0].id,
            enterpriseName: rows[0].entityName,
            production:rows[0].entityType,
            tissue:rows[0].entityScale,
            subjectCode:rows[0].orgCode
        };
        $scope.sjUnit =map;
        $scope.productions = $scope.sjUnit.production;
       $scope.tissues = $scope.sjUnit.tissue;
        $scope.mainBodyNumber = $scope.sjUnit.subjectCode;//主体号
        $("#sJUnit_Model").modal('hide');
        //获取选择单位信息
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEnt/getSubjEntTempInfo",
            method:"post",
            params :{
                "subj":$scope.sjUnit.subjectCode
            }
        }).success(function(data) {
            $scope.alesTaskSample.testedAddress = data.data.address;
            $scope.alesTaskSample.testedDeparment = data.data.entityName;
            $scope.alesTaskSample.testedLinkman = data.data.contactName;
            $scope.alesTaskSample.testedLinkmanphone = data.data.contactPhone;

        }).error(function (data) {
            console.log(data);
            
        })
    };//临时主体单位确定按钮
    /**
     * 手动选择主体类型前先清空已有的主体号
     */
    $scope.subjectCear = function () {
        $("input[name='subjectName']").val("");
    }
    /**
     * 针对复选框无法选中bug
     * 注释日期:2018年2月27日11:30:53
     */
    window.checkThisBox = function(o){
        if(o.checked){
            o.checked = false;
            $(o).removeClass("selected");
        }else {
            o.checked = true;
            $(o).addClass("selected");
        }
    }
    /**
     * 获取行业数据字典
     * 注释日期:2018年2月27日11:30:09
     */
    $scope.subjEntTemp={};
    $scope.industryType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.industryType=JSON.parse(data.data);
        $scope.subjEntTemp.entityIndustryId=$scope.industryType[0].id;
    }).error(function (data) {
        console.log(data);
        
    });
    /**
     * 获取临时注册主体类型数据字典
     * 注释日期:2018年2月26日13:47:37
     */
    $scope.subjType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"31",
        method:"post"
    }).success(function(data) {
        $scope.subjType=JSON.parse(data.data);
        $scope.subjEntTemp.entityTypeId=$scope.subjType[0].id;
    }).error(function (data) {
        console.log(data);
        
    });
    /**
     * 获取组织形式数据字典
     * 注释日期:2018年2月26日14:45:40
     */
    $scope.orgMode=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"6",
        method:"post"
    }).success(function(data) {
        $scope.orgMode=JSON.parse(data.data);
        $scope.subjEntTemp.entityScaleId=$scope.orgMode[0].id;
    }).error(function (data) {
        console.log(data);
        
    });
    /**
     * 编码框值
     * 注释日期:2018年2月27日11:20:56
     */
    $scope.reload = function() {
        $("input[name='entIdCode']").val("");
        $("input[name='sjUnitName']").val("");
        $scope.reloadArea();
    }
    /**
     * 生成临时码
     * 注释日期:2018年2月26日14:21:43
     */
    $scope.temporaryCode = function () {
        $("#dModal").modal("show");
       $scope.projectCategoryCode = $scope.productNames;
    };

    /**
     * 生成临时码
     * 注释日期:2018年2月27日11:24:32
     */
    $scope.addCheckItem = function () {
        if (!$("#tempCodeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var mainManagementBody = $("#mainManagementBody").val();
        if(mainManagementBody =="生产主体"){
            mainManagementBody ="0";
        }else if (mainManagementBody == "经营主体"){
            mainManagementBody ="1";
        }else if(mainManagementBody == "生产经营主体"){
            mainManagementBody ="2";
        }else if(mainManagementBody == "农资主体"){
            mainManagementBody ="3";
        }
        var organizationForms = $("#organizationForms").val();
        var mainBodyNumber = $scope.mainBodyNumber;
        var projectCategoryCode = $scope.projectCategoryCode;
        var currentindustry = $scope.industry;
        if(organizationForms == "企业/个体工商户"){
            organizationForms = "0";
        }else if(organizationForms == "合作社"){
            organizationForms = "3";
        }else if(organizationForms == "家庭农场"){
            organizationForms = "2";
        }else if(organizationForms == "个人"){
            organizationForms = "1";
        }
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/alesmynewchecktask/temporaryCode?time=" + new Date(),
            method: "POST",
            params: {
                "mainManagementBody": mainManagementBody, "organizationForms": organizationForms,
                "mainBodyNumber": mainBodyNumber, "projectCategoryCode": projectCategoryCode,
                "currentindustry":currentindustry
            },
            async: true
        }).success(function (data) {
            $scope.alesTaskSample.productTraceabilityCode = data.productTemporaryCode;//临时码
            $scope.alesTaskSample.sampleName = projectCategoryCode;
            $("#dModal").modal("hide");
        }).error(function (data) {
            console.log(data);
            
        });
    };
    //生成样品编码
    $scope.temporarySampleCode = function () {
        $("button[name='sampleCodePrint']").attr("disabled", false);//解禁打印样品编码按钮
        $http({
            url: "/sofn-ales-web/alesSample/getSampleCode",
            headers: {
                token: window.localStorage.getItem("token")
            },
            method: "POST",
            params: {
                "monitorTaskId":$scope.objectId,"taskType":$scope.taskType
            },
            async: true
        }).success(function (data) {
            $scope.alesTaskSample.sampleCode=data.data;
        }).error(function (data) {
            console.log(data);
            
        });
    }
    //校验输入样品编码(2018年8月21日11:40:54)
    if($scope.alesTaskSample.sampleCode == "" || $scope.alesTaskSample.sampleCode == null){
        $(".isCode").css("display", "none");
    }

    window.verifyCode = function () {
        var sampleCode =  $scope.alesTaskSample.sampleCode;
        console.log(sampleCode);
        $http({
            url: "/sofn-ales-web/alesmynewchecktask/findAdsMonitorSample",
            headers: {
                token: window.localStorage.getItem("token")
            },
            method: "GET",
            params: {
                "sampleCode":sampleCode
            }
        }).success(function (data) {
            isCode = data.data;
            console.log("信息返回==:"+data.data)
            if(data.data == "false"){
                $(".isCode").css("display", "block");
            }else if(data.data == "true"){
                $("button[name='sampleCodePrint']").attr("disabled", false);//解禁打印样品编码按钮
                $(".isCode").css("display", "none");
            }
        }).error(function (msg) {
            
        });
    }
    //打印样品编码
    $scope.sampleCodePrint = function () {
        $scope.sampleCodeInfo = $scope.alesTaskSample.sampleCode;
        $scope.entrustTaskNameInfo = $scope.entrustTaskName;
        $("#sample_Code_Modal").modal('show');

    }
    $scope.printSampleCode = function () {
        $('#sample_Code_Modal').one('hidden.bs.modal', function(){
            $state.go("index.content.ales/taskManage/sample/sampleCodePrints");
        }).modal('hide');
        window.localStorage.setItem("alesTaskSampleSampleCodes", $scope.alesTaskSample.sampleCode);//样品编码
        window.localStorage.setItem("alesTaskEntrustTaskName_iskeys", $scope.entrustTaskName);//任务名称
    }

    /**
     * 人员选择
     * 注释日期:2018年2月27日11:22:36
     */
    $scope.selects = new Array();

    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.selectPerson = function () {
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };
    $("#selectGrid").dataTable({
        fnDrawCallback: function () {
            $("#selectGrid tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        //人员移除
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0 ; i < $scope.selects.length ; i++){
                            if($scope.selects[i] != undefined && u_d == $scope.selects[i].userName){
                                delete $scope.selects[i];
                            }
                        }
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        //人员选择记录
                        var map={
                            id:$(this).parent().find("[name='ck']").val(),
                            userName:$(this).parent().find("[name='userName']").html()
                        };
                        $scope.selects.push(map);
                    }
                });
                //console.log($scope.selects.length)
            })
        },
        ajax: {
            url: "/sofn-ales-web/alesTools/getUsersListByUserToken",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;
                json.pageSize = json.data.pageSize;
                json.start = (json.data.pageNum - 1) * json.data.pageSize;
                json.length = json.data.pageSize;
                return json.data.list;
            },
            error:function (msg) {
                console.log(msg);
                
            },
            data: function (params) {
                params.token = window.localStorage.getItem("token");
                params.keyword = $("input[name='selectQueryName']").val();
            },
            headers: {
                token: window.localStorage.getItem("token")
            },
            type: "post"
        },
        columns: [
            {
                data : "id",
                title : '*',
                render : function(data, type, row) {
                    for(var i = 0; i <  $scope.selects.length;i++){
                        if ($scope.selects[i] != undefined && row.userName == $scope.selects[i].userName){
                            return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+row.userName+'"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+row.userName+'"/>';
                }
            },
            {
                data: 'id',
                render: function (data, type, row, meta) {
                    return '<input type="hidden" name="id" value = "' + data + '"/>';
                }
            },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "userName",
                title: "姓名",
                render: function (data, type, row) {
                    return '<span name = "userName">' + data + '</span>';
                }
            },
            {
                data: "phone",
                title: "联系电话"
            },
            {
                data: "email",
                title: "电子邮箱"
            }
        ]
    });

    $("#selectTocheck").click(function () {
        $("#selectGrid").dataTable().api().ajax.reload();
    });


    //抽样人员赋值
    $scope.selectModalQdButt = function(){
        var userName = "";
        $("input[name='samplePerson']").val("");
        for(var i = 0; i < $scope.selects.length ; i++){
            if($scope.selects[i] != undefined){
                if (userName != ""){
                    userName+=","+$scope.selects[i].userName;
                }else {
                    userName = $scope.selects[i].userName;
                }
            }
        }
        $("input[name='samplePerson']").val(userName);
        $scope.alesTaskSample.samplePerson = userName;
        //$scope.$apply(function () {
        //    $scope.alesTaskSample.samplePerson = userName;
        //});
        $("#selectModal").modal('hide');
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    };

    //针对复选框无法选中bug
    window.checkSelectThisBox = function(o){
        if(o.checked){
            o.checked = false;
            $(o).removeClass("selected");
        }else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    window.clearValidate=function(){
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    };

    $scope.switchIsSelfProduced=function(){
        var isSelfProduced=$("input[name='isSelfProduced']:checked").val()
        if(isSelfProduced=="自产"){
            $(".sampleSource").css("display","none")
            $scope.alesTaskSample.sampleSource="自产";
        }else{
            $scope.alesTaskSample.sampleSource="其他";
            $(".sampleSource").css("display","block")
        }
    };

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传jpg,jpeg,png,gif,bmp类型图片', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };
    //-----------生产经营临时注册提示-----
    /*window.entityNameValidation = function(){
        var entityNameVal = $("input[name ='entityName']").val();
        if(entityNameVal == "" || entityNameVal == " " ){
            $(".entityNullCss").css("display", "block");
            return;
        }else {
            $(".entityNullCss").css("display", "none");
        }
    }
    window.orgCodeValidation = function () {
        var validation =/^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])|X\d{1}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$)|(^[A|B|C|D|E|F|G|H|Q|W|O|P|J|M|N|X|L|K|U|Y|T]\d{8}|[0-9]\d{8}([0-9]|[A|B|C|D|E|F|G|H|Q|W|O|P|J|M|N|X|L|K|U|Y|T])\d{9}[A|B|C|D|E|F|G|H|Q|W|O|P|J|M|N|X|L|K|U|Y|T]\d{1}$)|(^[A|B|C|D|E|F|G|H|Q|W|O|P|J|M|N|X|L|K|U|Y|T]|[0-9]\d{15}$)/;
        var orgCodeVal = $("input[name ='orgCode']").val();
        if(orgCodeVal == "" || orgCodeVal == " " || !validation.test(orgCodeVal)){
            $(".orgCodeNullValCss").css("display", "block");
            return;
        }else {
            $(".orgCodeNullValCss").css("display", "none");
        }

    }
    window.addressReminder = function(){
        var addVal = $("input[name ='address']").val();
        if(addVal == "" || addVal == " " ){
            $(".addNullCss").css("display", "block");
            return;
        }else {
            $(".addNullCss").css("display", "none");
        }
    }
    window.contactNameValidation = function () {
        var contactNameVal = $("input[name ='contactName']").val();
        if(contactNameVal == "" || contactNameVal == " " ){
            $(".contactNameNullValCss").css("display", "block");
            return;
        }else {
            $(".contactNameNullValCss").css("display", "none");
        }
    }
    window.contactPhoneValidation = function () {
        var validation =/^0\d{2}-\d{7,8}|^1[34578]\d{9}$/;
        var contactPhoneVal = $("input[name ='contactPhone']").val();
        if(contactPhoneVal == "" || contactPhoneVal == " " || !validation.test(contactPhoneVal)){
            $(".contactPhoneNullValCss").css("display", "block");
            return;
        }else {
            $(".contactPhoneNullValCss").css("display", "none");
        }
    }*/
    //判断组织机构代码照是否显示
    $scope.selectBusiness = function() {//selectBusiness
        if ($scope.subjEntTemp.credType == "普通营业执照(有独立组织机构代码证)") {
            $("#selectOrganizationCertificateimg").show();
        } else {
            $("#selectOrganizationCertificateimg").hide();
            vm.organizationCertificateimg = [{
                vname: '组织机构照',
            }]
        }
    };
    //----------------------------------------------------
    //临时主体上传照片默认显示
    vm.businessLicenceimg = [{
        vname: '企业证件照',
    }]
    vm.organizationCertificateimg = [{
        vname: '组织机构照',
    }]
    vm.positiveIdcardeimg = [{
        vname: '身份证正面',
        picture: 'images/dict/id_f.png'
    }]
    vm.negativeIdcardimg = [{
        vname: '身份证反面',
        picture: 'images/dict/id_z.png'
    }]
    vm.handIdcardimg = [{
        vname: '手持身份证',
        picture: 'images/dict/id_s.png'
    }]

    vm.uploadHeadOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传图片', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };

});


