var myapp = angular.module("myapp", []);
myapp.controller("sampleMessage", function ($scope, $http, $state) {
    var vm = this;
    var token = window.localStorage.getItem("token");
    var type = window.localStorage.getItem("type");//获取类型来源

    $scope.iswrite = "";
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    var url1 = "index.content.monitor/execUnit/routineMonitor/routineMonitoring";
    var url2 = "index.content.monitor/execUnit/specialMonitor/routineMonitoringSpecial";
    for (var i = 0; i < a.length; i++) {
        // if((a[i].menuName=="例行监测" && a[i].url==url1) || (a[i].menuName=="专项监测" && a[i].url==url2)){
        //     $scope.iswrite=a[i].authority;
        //     break;
        // }
        if (type == "1") {
            if (a[i].menuName == "例行监测" && a[i].url == url1) {
                $scope.iswrite = a[i].authority;
                break;
            }
        } else {
            if (a[i].menuName == "专项监测" && a[i].url == url2) {
                $scope.iswrite = a[i].authority;
                break;
            }
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    $scope.taskName = window.localStorage.getItem("taskName");//监测任务名称
    var organTaskId = window.localStorage.getItem("organTaskId");//机构任务ID
    var deadline = window.localStorage.getItem("deadline");//抽样截至时间
    $scope.monitorClass = window.localStorage.getItem("monitorClass");//监测任务
    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");//监测任务ID


    $scope.sampleMessageEnabled = true;

    var now = new Date();
    var nowStr = now.format("yyyy-MM-dd");

    if (window.localStorage.getItem("numbers") == "null") {
        $scope.numbers = 0;
    } else {
        $scope.numbers = window.localStorage.getItem("numbers");//任务数
    }

    //后台加载行政区划信息
    getRegion("100000", "sheng10");

    window.regionChanged = function (option) {
        if (option.id === "sheng10") {
            $("#shi10" + " option:gt(0)").remove();
            $("#xian10" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "shi10");
        } else {
            $("#xian10" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "xian10");
        }
    };

    window.regionModifyChanged = function (option) {
        if (option.id === "sheng2") {
            $("#shi2" + " option:gt(0)").remove();
            $("#xian2" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "shi2");
        } else {
            $("#xian2" + " option:gt(0)").remove();
            if (option.value === "") {
                return;
            }
            getRegion(option.value, "xian2");
        }
    };

    function getRegion(regionId, id) {
        $.ajax({
            url: "/sofn-sys-web/region/queryMenu?&delFlag=Y",
            type: "post",
            dataType: "json",
            data: {regionId: regionId},
            async: false,
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

    //获取抽样计划任务,并判断是否可以填报
    function getSamplePlan() {
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/getSamplePlanByMonitorTaskId",
            type: "post",
            dataType: "json",
            data: {id: adsMonitorTaskId, token: token},
            async: false,
            success: function (data) {
                if(parseInt(data.data)>0){
                    $scope.sampleMessageEnabled = true;
                } else
                    $scope.sampleMessageEnabled = false;
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    /*    $http({
            url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
            method: "post",
            params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
        }).success(function (data) {
            $scope.sampleFinishNum = data.adsMonitorTask[0].adsOrganTaskList[0].sampleFinishNum;

            if(data.adsMonitorTask[0].publishStatus == '5'){
                $scope.sampleMessageEnabled = false;
            } else{
                $scope.sampleMessageEnabled = true;
            }
        }).error(function () {
        });*/

    //检查该任务是否已废止
    function checkIsAborted() {
        $.ajax({
            url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
            type: "post",
            async: false,
            dataType: "json",
            data: {"monitorTaskId": adsMonitorTaskId, "monitorClass": "", "token": token},
            success: function (data) {
                $scope.checkObject = [];
                if (data.adsMonitorTask[0].checkObject != null) {
                    $scope.checkObject = data.adsMonitorTask[0].checkObject.split("、");
                    window.localStorage.setItem("checkObject", $scope.checkObject);
                }
                if (data.adsMonitorTask[0].publishStatus == "5") {
                    $scope.sampleMessageEnabled = false;
                    $.jBox.tip("该任务已废止，只能查看。");
                }
                else
                    $scope.sampleMessageEnabled = true;
            }
        });

        return !$scope.sampleMessageEnabled;
    }


    $scope.deadline = window.localStorage.getItem("deadline");//任务截至时间
    // alert("organTaskId="+organTaskId);
    $scope.sampleCode = "";
    $scope.innocuity = "";
    $scope.green = "";
    $scope.organic = "";
    $scope.other = "";
    $scope.base = "";
    $scope.slaughter = "";
    $scope.market = "";
    $scope.wholesale = "";
    $scope.supermar = "";
    $scope.else = "";
    $scope.production = "";
    $scope.stock = "";
    $scope.sampleName = "";
    $scope.testedDeparment = "";
    $scope.productTraceabilityCode = "";
    $scope.checkLink = "";
    $scope.toCheck = function () {
        $("#grid").dataTable().api().ajax.reload();
    }

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetailAdsOrgan")
    }
    $scope.sampelPrint = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")
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

    $(function () {

        $http({
            url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
            method: "post",
            params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
        }).success(function (data) {
            $scope.sampleFinishNum = data.adsMonitorTask[0].adsOrganTaskList[0].sampleFinishNum;

            if (data.adsMonitorTask[0].publishStatus == '5') {
                $scope.sampleMessageEnabled = false;
            } else {
                $scope.sampleMessageEnabled = true;
                //getSamplePlan();
            }

            //bootstrap表格
            $("#grid").dataTable({
                processing: true, // 加载时提示
                serverSide: true, // 分页，是否服务器端处理
                ordering: false,
                searching: false,
                bProcessing: true,
                bAutoWidth: true,
                dom: '<"top">rt<"bottom"pli><"clear">',
                autoWidth: false,
                ajax: {
                    url: "/sofn-ads-web/adsMonitorSample/getSamplePageInfo",
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
                        var xian10 = $("#xian10").val();
                        if (xian10 != null || xian10 != "") {
                            xian10 = $("#xian10").val();
                        }
                        params.organTaskId = organTaskId;
                        //分页条件查询，不实现，自己写
                        params.producingArea = xian10;//抽样产地
                        if ($('#sampleDeparment option:selected').text() != "全部") {//抽样单位
                            params.sampleDeparment = $('#sampleDeparment option:selected').text();
                        }
                        params.productTraceabilityCode = $scope.productTraceabilityCode;
                        params.sampleCode = $scope.sampleCode;    //抽样编码
                        params.testedDeparment = $scope.testedDeparment;//受检单位
                        params.sampleName = $scope.sampleName;    //样品名称
                        // params.cityCode = sheng9 + shi9 + xian9;//抽样地区
                        var sampleDate = $("input[name='sampleDate']").val();
                        if (sampleDate != "") {
                            params.sampleDate = sampleDate + "00:00:00"
                        }
                        params.checkLink = $scope.checkLink;  //监测环节
                        if ($('#sampleOrgan option:selected').text() != "全部") {//抽样机构
                            params.sampleOrgan = $('#sampleOrgan option:selected').text();
                        }
                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "ID",
                        checkbox: true,
                        title: '<input id="checkAll" type="checkbox" value="" />',
                        render: function (data, type, row, meta) { // 模板化列显示内容
                            if (row.SAMPLE_REPORT == "4" || row.SAMPLE_REPORT == "2") {
                                return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                    + data + '" disabled/>';
                            } else {
                                return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                    + data + '"/>';
                            }

                        }
                    },
                    {
                        data: "RN",
                        title: "序号"
                    },
                    {
                        data: "PRODUCT_TRACEABILITY_CODE",
                        title: "产品编码",
                        render: function (data, type, row, meta) { // 模板化列显示内容
                            if (data != null) {
                                return '<input class="gui-input" readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                            } else {
                                data = "";
                                return '<input class="gui-input" readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                            }
                        }
                    },
                    {
                        data: "SAMPLE_CODE",
                        title: "样品编码",
                        render: function (data) { // 模板化列显示内容
                            if ($scope.disabled) {
                                return '<a name="SAMPLE_CODE" style="color: grey">' + data + '</a>';
                            }
                            return '<a name="SAMPLE_CODE" onclick="checkClick(\'' + data + '\')" >' + data + '</a>';
                        }
                    },
                    {
                        data: "SAMPLE_NAME",
                        title: "样品名称"
                    },
                    {
                        data: "TESTED_DEPARMENT",
                        title: "受检单位"
                    },
                    {
                        data: "PRODUCING_AREA_NAME",
                        title: "样品产地"
                        // render: function (data, type, row) { // 模板化列显示内容
                        //     if (row.PRODUCING_AREA != undefined) {
                        //         return $.fn.Cityname(row.PRODUCING_AREA)
                        //     } else {
                        //         return "";
                        //     }
                        // }
                    },
                    {
                        data: "CHECK_LINK",
                        title: "抽样环节",
                        visible: false
                    },
                    {
                        data: "SAMPLE_DATE",
                        title: "抽样日期"
                    },
                    {
                        data: "ID",
                        title: "ID",
                        visible: false
                    },
                    {
                        data: "SAMPLE_REPORT",
                        title: "状态",
                        render: function (data, type, row) {
                            if ("0" == data) {
                                return "未上报";
                            } else if ("2" == data) {
                                return "已上报";
                            } else if ("3" == data) {
                                return "牵头退回";
                            } else if ("4" == data) {
                                return "监管退回";
                            }
                        }
                    },
                    {
                        data: "ID",
                        title: "操作",
                        render: function (data, type, row) { // 模板化列显示内容
                            if (row.SAMPLE_REPORT == "4" || row.SAMPLE_REPORT == "2" || $scope.disabled || !$scope.sampleMessageEnabled) {//已上报状态
                                return '<div style="color: darkgray" >修改</div>'
                            } else {
                                return '<a class="color" onclick="upd(\'' + row.SAMPLE_CODE + '\')">修改</a>'
                            }
                        }
                    }
                ],
            });
        }).error(function () {
        });

    })


    //删除
    $scope.del = function () {
        if (checkIsAborted())
            return;

        var i = $scope.getSelectRowsIds();
        if (i != false) {
            if (confirm('是否删除已选择任务？')) {
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/deleteAdsMonitorSample",
                    method: "post",
                    params: {"id": JSON.stringify(i), "token": token}
                }).success(function (data) {
                    $.messager.show({
                        title: "提示信息",
                        msg: "删除成功！",
                        showType: "slide",
                        timeout: 5000
                    });
                    $('#grid').dataTable().api().ajax.reload();
                    $('#checkAll').prop("checked", false);
                }).error(function () {
                    alert("失败");
                });
            }
        }
    }

    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

//抽样机构下拉选
    var url = "/sofn-ads-web/adsOrganTask/findSampleOrgan?time=" + new Date();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data: {"monitorTaskId": adsMonitorTaskId},
        success: function (data) {
            var data = data;
            var Row = '<option>全部</option> ';
            if (data.length > 0) {
                for (var i = 1; i <= data.length; i++) {
                    var td = data[i - 1];
                    Row = Row + '<option>' + td.sampleOrgan + '</option> ';

                }
            }
            $("#sampleOrgan").html(Row);
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });
    //抽样单位下拉选
    var url = "/sofn-ads-web/adsMonitorSample/findDepartment?time=" + new Date();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data: {"organTaskId": organTaskId},
        success: function (data) {
            var data = data;
            var Row = '<option>全部</option> ';
            if (data.length > 0) {
                for (var i = 1; i <= data.length; i++) {
                    var td = data[i - 1];
                    Row = Row + '<option>' + td.sampleDeparment + '</option> ';
                }
            }
            $("#sampleDeparment").html(Row);
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });
    //$.fn.mycity("sheng9", "shi9", "xian9", 0, 0, 0);
    //$.fn.mycity("sheng10", "shi10", "xian10", 0, 0, 0);
    // $.fn.mycity("sheng2", "shi2", "xian2", 0, 0, 0);
    //上报信息
    $scope.isSubmit = false;
    $scope.rep = function () {

        if (checkIsAborted())
            return;

        var i = $scope.getSelectRowsIds();
        if (i != false) {

            if($scope.isSubmit)
                return;
            $scope.isSubmit = true;

            if (confirm('是否上报抽样信息？')) {
                var date = new Date();
                var newDate = getNowFormatDate();//获取当前日期
                if (newDate <= deadline) {
                    $http({
                        url: "/sofn-ads-web/adsMonitorSample/updateAdsMonitorSampleById",
                        method: "post",
                        params: {"id": JSON.stringify(i), "organTaskId": organTaskId, "token": token}
                    }).success(function (data) {
                        jBox.tip("上报成功", 'info');
                        $('#grid').dataTable().api().ajax.reload();
                        $('#checkAll').prop("checked", false);
                    }).error(function () {
                    });
                } else {
                    jBox.tip("超过了抽样信息上报截止时间，不允许上报", 'info');
                }


            }

            $scope.isSubmit = false;
        }


    }

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }

    //重置
    $scope.reset = function () {
        window.location.reload();
    }

    // 抽样时间日历控件选择日期后的回调函数
    window.datePickerOnPicked = function (dp) {
        $scope.$apply(function () {
            $scope.data.SAMPLE_DATE = dp.cal.getDateStr().trim();
        });
    }

    //弹出修改
    window.upd = function (code) {

        //清空区域值
        $("#xian2").val("");
        $("#shi2").val("");
        $("#sheng2").val("");
        if (code != false) {
            if (confirm('是否修改抽样信息？')) {
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById",
                    method: "post",
                    params: {sampleCode: code, organTaskId: organTaskId}
                }).success(function (data) {
                    $scope.producingArea2 = data.data.PRODUCING_AREA;
                    getRegion("100000", "sheng2");
                    if (data.data.PRODUCING_AREA !== null) {
                        getProducingArea($scope.producingArea2);
                    }
                    $scope.data = data.data;
                    if (data.data.PRODUCE_DATE == null || data.data.PRODUCE_DATE == "1970-01-01") {
                        $scope.produceDate = "";
                    } else {
                        $scope.produceDate = data.data.PRODUCE_DATE;
                    }

                    $("#code").empty();// 解决二维码重复生成BUG

                    //二维码
                    star = code[0];
                    $("#code").qrcode({
                        render: "table", //table方式
                        width: 90, //宽度
                        height: 90, //高度
                        text: star //任意内容
                    });
                    $("#dModal").modal("show");

                }).error(function () {

                });
            }
        }
    };

    function getProducingArea(areaCode) {
        getRegion("100000", "sheng2");
        var shengAreaId = (areaCode.substr(0, 2)) + "0000";
        $('#sheng2').val(shengAreaId);
        var shiAreaId = (areaCode.substr(0, 4)) + "00";
        getRegion(shengAreaId, "shi2");
        $('#shi2').val(shiAreaId);
        var xianAreaId = areaCode;
        getRegion(shiAreaId, "xian2");
        $('#xian2').val(xianAreaId);
    }

    /**
     * 导出监测任务excel数据
     */
    if ($('#sampleDeparment option:selected').text() != "全部") {//抽样单位
        var sampleDeparment = $('#sampleDeparment option:selected').text();
    }
    if ($("input[name='sampleDate']").val() != "") {
        var sampleDate = sampleDate + "00:00:00"
    }
    if ($('#sampleOrgan option:selected').text() != "全部") {//抽样机构
        var sampleOrgan = $('#sampleOrgan option:selected').text();
    }
    var params1 = {
        "organTaskId": organTaskId,
        //分页条件查询，不实现，自己写
        "producingArea": $("#xian1").val(),//抽样产地
        "sampleDeparment": sampleDeparment,
        "productTraceabilityCode": $scope.productTraceabilityCode,
        "sampleCode": $scope.sampleCode,    //抽样编码
        "testedDeparment": $scope.testedDeparment,//受检单位
        "sampleName": $scope.sampleName,    //样品名称
        "cityCode": $("#xian").val(),//抽样地区
        // "sampleDate": $("input[name='sampleDate']").val(),
        "sampleDate": sampleDate,
        "checkLink": $scope.checkLink,  //监测环节
        "sampleOrgan": sampleOrgan
    }

    $scope.exportExcel = function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/downloadMonitorSample",
                    method: "post",
                    dataType: "json",
                    params: params1,
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '抽样信息管理表.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    $scope.close = function () {
        $("#dModal").modal("hide");
    }

    $scope.detailClose = function () {
        $("#detailModal").modal("hide");
    }
    //确认修改提交
    $scope.sub = function () {
        var producingArea2 = $("#xian2").val();
        /*var shengName = $("#sheng2").find("option:selected").text();
        var shiName = $("#shi2").find("option:selected").text();
        var xianName = $("#xian2").find("option:selected").text();*/
        var producingAreaName2 = $.fn.Cityname(producingArea2);
        ;
        $scope.data.SAMPLE_DATE = $.trim($scope.data.SAMPLE_DATE);
        if ($scope.data.SAMPLE_DATE > nowStr) {
            jBox.tip("抽样时间不能大于当前时间", 'info');
            return false;
        }

        var testedDeparment = $scope.data.TESTED_DEPARMENT;
        var productionDeparment = $scope.data.PRODUCTION_DEPARMENT;
        var sampleDeparment = $scope.data.SAMPLE_DEPARMENT;

        //增加姓名校验规则
        var testedLegalrep = $scope.data.TESTED_LEGALREP;
        var testedLinkman = $scope.data.TESTED_LINKMAN;
        var testedPerson = $scope.data.TESTED_PERSON;
        var productionLinkman = $scope.data.PRODUCTION_LINKMAN;
        var samplelinkman = $scope.data.SAMPLE_LINKMAN;

        var sampleDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;
        if (!sampleDeparment.match(sampleDeparmentreg)) {
            jBox.tip("抽样单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            return false;
        }

        var productionDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;
        if (!productionDeparment.match(productionDeparmentreg)) {
            jBox.tip("生产单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            return false;
        }

        var testedDeparmentreg = /^[\u4e00-\u9fa5(（)）]{0,60}$/;
        if (!testedDeparment.match(testedDeparmentreg)) {
            jBox.tip("受检单位只能包含60位以内的汉字、中英文括号及其组合", 'info');
            return false;
        }

        var testedLinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedLinkman.match(testedLinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            return false;
        }

        var testedLegalrepreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedLegalrep.match(testedLegalrepreg)) {
            jBox.tip("法人必须为1-60位汉字或英文字符", 'info');
            return false;
        }

        var testedPersonreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!testedPerson.match(testedPersonreg)) {
            jBox.tip("受检人必须为1-60位汉字或英文字符", 'info');
            return false;
        }

        var productionLinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!productionLinkman.match(productionLinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            return false;
        }

        var samplelinkmanreg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;

        if (!samplelinkman.match(samplelinkmanreg)) {
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            return false;
        }

        var params = {
            "producingArea": producingArea2,
            "producingAreaName": producingAreaName2,
            "id": $scope.data.ID,
            "sampleName": $scope.data.SAMPLE_NAME,
            "sampleCode": $scope.data.SAMPLE_CODE,
            "tradeMark": $scope.data.TRADE_MARK,
            "packing": $scope.data.PACKING,
            "grade": $scope.data.GRADE,
            "identify": $scope.data.IDENTIFY,
            "specification": $scope.data.SPECIFICATION,
            "standard": $scope.data.STANDARD,
            "produceDate": $scope.produceDate,
            "produceCertificate": $scope.data.PRODUCE_CERTIFICATE,
            "certificateCode": $scope.data.CERTIFICATE_CODE,
            "sampleBase": $scope.data.SAMPLE_BASE,
            "samplePlace": $scope.data.SAMPLE_PLACE,
            "testedDeparment": $scope.data.TESTED_DEPARMENT,
            "testedAddress": $scope.data.TESTED_ADDRESS,
            "testedLegalrep": $scope.data.TESTED_LEGALREP,
            "testedLinkman": $scope.data.TESTED_LINKMAN,
            "testedLinkmanphone": $scope.data.TESTED_LINKMANPHONE,
            "testedLinkmanfax": $scope.data.TESTED_LINKMANFAX,
            "testedPerson": $scope.data.TESTED_PERSON,
            "testedPersonphone": $scope.data.TESTED_PERSONPHONE,
            "testedPersonfax": $scope.data.TESTED_PERSONFAX,
            "productionsTatus": $scope.data.PRODUCTIONS_TATUS,
            "productionDeparment": $scope.data.PRODUCTION_DEPARMENT,
            "productionAddress": $scope.data.PRODUCTION_ADDRESS,
            "productionZipcode": $scope.data.PRODUCTION_ZIPCODE,
            "productionLinkman": $scope.data.PRODUCTION_LINKMAN,
            "productionLinkmanphone": $scope.data.PRODUCTION_LINKMANPHONE,
            "productionLinkmanfax": $scope.data.PRODUCTION_LINKMANFAX,
            "sampleDeparment": $scope.data.SAMPLE_DEPARMENT,
            "sampleLinkman": $scope.data.SAMPLE_LINKMAN,
            "sampleAddress": $scope.data.SAMPLE_ADDRESS,
            "sampleZipcode": $scope.data.SAMPLE_ZIPCODE,
            "samplePhone": $scope.data.SAMPLE_PHONE,
            "sampleFax": $scope.data.SAMPLE_FAX,
            "sampleEmail": $scope.data.SAMPLE_EMAIL,
            "proof": $scope.data.PROOF,
            "samplePerson": $scope.data.SAMPLE_PERSON,
            "sampleDate": $scope.data.SAMPLE_DATE,
            "sampleId": "org2921c0653483b9cb86efb324b3003",//抽样单位KEY
            "productTraceabilityCode": $scope.data.PRODUCT_TRACEABILITY_CODE,
            "organTaskId": organTaskId,//机构任务KEY
            // "sampleBarCode": $scope.data.SAMPLE_CODE,
            "sampleNumber": $scope.data.SAMPLE_NUMBER,
            "sampleNumberUnit": $scope.data.SAMPLE_NUMBER_UNIT,
            "sampleBaseUnit": $scope.data.SAMPLE_BASE_UNIT,
            "fileName": $scope.data.FILE_NAME,
            "fileAddress": $scope.data.FILE_ADDRESS,
            "delFlag": "N"
        }
        $http({
            url: "/sofn-ads-web/adsMonitorSample/updateAdsMonitorSample?token=" + token,
            method: "POST",
            data: params
        }).success(function (data) {
            jBox.tip("修改成功", 'info');
            $("#dModal").modal("hide");
            //解决点击样品编码后，将样品编码赋值空，解决修改后显示一条数据问题
            $scope.sampleCode = "";
            $('#grid').dataTable().api().ajax.reload();
        }).error(function () {
        });
    }


    function dateFormatUtil(longTypeDate) {
        var dateTypeDate = "";
        var date = new Date();
        date.setTime(longTypeDate);
        dateTypeDate += date.getFullYear();   //年
        dateTypeDate += "-" + getMonth(date); //月
        dateTypeDate += "-" + getDay(date);   //日
        return dateTypeDate;
    }

    //返回 01-12 的月份值
    function getMonth(date) {
        var month = "";
        month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    function trim(param) {
        if ((vRet = param) == '') {
            return vRet;
        }
        while (true) {
            if (vRet.indexOf(' ') == 0) {
                vRet = vRet.substring(1, parseInt(vRet.length));
            } else if ((parseInt(vRet.length) != 0) && (vRet.lastIndexOf(' ') == parseInt(vRet.length) - 1)) {
                vRet = vRet.substring(0, parseInt(vRet.length) - 1);
            } else {
                return vRet;
            }
        }
    }

    //返回01-30的日期
    function getDay(date) {
        var day = "";
        day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

    //全选/全不选--此方法不起作用，该功能现由全局JS控制
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $(".editor-active").each(function () {
                if (!$(this).is(':disabled')) {
                    $(this).prop('checked', true);
                }
            });
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });
    //获取选择行记录id
    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().data();
        var ids = [];
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {
                ids.push(rows[i - 1].ID);
                num++;
            }
        }
        if (num == 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        return ids;
    }
    //获取选择行SAMPLE_CODE记录
    $scope.getSelectRowsSampleCode = function () {
        var rows = $("#grid").DataTable().data();
        var ids = [];
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {
                ids.push(rows[i - 1].SAMPLE_CODE);
                num++;
            }
        }
        if (num > 1) {
            alert("只能选择一行记录修改！");
        } else if (0 == num) {
            alert("至少选择一行记录修改！");
        }
        return ids;
    }

    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    }

    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });

    $(".cq2").on("click", "button", function () {
        $('.setslist ul li').remove();
        document.getElementById('sheng').options[0].selected = true;
        document.getElementById('shi').options[0].selected = true;
        document.getElementById('xian').options[0].selected = true;

    });
    $scope.sampelPrint = function (data) {
        window.localStorage.setItem("organTaskId", organTaskId);//机构任务ID
        window.localStorage.setItem("sampleCode", $scope.sampleCode);
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")

    }
    $scope.writeSample = function (data) {

        if (checkIsAborted())
            return;

        var newDate = getNowFormatDate();//获取当前日期
        if (deadline != "" && newDate <= deadline) {
            $state.go("index.content.monitor/execUnit/AdsOrganTask/writeSample")
        } else {
            jBox.tip("超过截止时间的任务不允许抽样", "info")
        }
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

    //查看详情弹出
    $.fn.mycity("sheng", "shi", "xian", 0, 0, 0);
    window.checkClick = function (data) {
        // $scope.sampleCode=data;
        $http({
            url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById?time=" + new Date(),
            method: "post",
            params: {sampleCode: data, organTaskId: organTaskId}
        }).success(function (data) {
            $scope.detailData = data.data;
            if (data.data.PRODUCING_AREA == null) {
                $scope.producingAreaName = "";
            } else {
                $scope.producingAreaName = data.data.PRODUCING_AREA_NAME;
            }

            $("#erweimacode").empty(); // 解决每次打开模态窗口都重复生成二维码的问题
            //二维码
            star = toUtf8(data);
            $("#erweimacode").qrcode({
                render: "table", //table方式
                width: 90, //宽度
                height: 90, //高度
                text: star //任意内容
            });
            $("#detailModal").modal("show");
        }).error(function () {
        });
    }

    //打印设置
    $("#printArea").click(function () {
        $("#printCount").printArea();
    });

    $scope.printAreaSampleModfy = function () {
        var sampleCode = $("#showSampleModfy").val();
        $("#showTaskNames").text($scope.taskName);
        $("#showSampleCodes").text(sampleCode);
        $("#dModal").modal("hide");
        $('#choose_printmodfy_modal').modal("show");
    }

    $("#printAreasSamples").click(function () {
        var sampleCode = $("#showSampleModfy").val();
        $("#choose_printmodfy_modal").modal("hide");
        $(".modal-backdrop").hide();
        window.localStorage.setItem("adsTaskSampleSampleCode", sampleCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey", $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");
    });

    $scope.printAreaSample = function () {
        var sampleCode = $("#showSample").val();
        $("#showTaskName").text($scope.taskName);
        $("#showSampleCode").text(sampleCode);
        $("#detailModal").modal("hide");
        $('#choose_print_modal').modal("show");
    }

    $("#printAreasSample").click(function () {
        var sampleCode = $("#showSample").val();
        $("#choose_print_modal").modal("hide");
        $(".modal-backdrop").hide();
        window.localStorage.setItem("adsTaskSampleSampleCode", sampleCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey", $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");
    });

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

    function dateFormatUtil(longTypeDate) {
        var dateTypeDate = "";
        var date = new Date();
        date.setTime(longTypeDate);
        dateTypeDate += date.getFullYear();   //年
        dateTypeDate += "-" + getMonth(date); //月
        dateTypeDate += "-" + getDay(date);   //日
        return dateTypeDate;
    }

    //返回 01-12 的月份值
    function getMonth(date) {
        var month = "";
        month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    //返回01-30的日期
    function getDay(date) {
        var day = "";
        day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

    /**
     * 下载检测任务单
     */
    window.downFile = function (data) {
        var submit = function (v, h, f) {
            //var sampleCode = $scope.sampleCode;
            var sampleCode = $("#showSample").val();
            var monitorClass = $scope.monitorClass;
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/writeWordFile",
                    method: "post",
                    dataType: "json",
                    params: {sampleCode: sampleCode, organTaskId: organTaskId, monitorClass: monitorClass},
                    responseType: 'arraybuffer',
                    headers: {
                        post: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '抽样单.doc');
                        a.click();
                    } else {
                        jBox.tip("下载失败！", "info")
                    }
                });

            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

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
        },
        complete: function (err, xhr) {
            if (!err) { // 文件上传成功后更新文件名和文件存放地址
                $scope.$apply(function () {
                    $scope.data.FILE_NAME = vm.files[0].name;
                    $scope.data.FILE_ADDRESS = vm.files[0].src;
                });
            } else {
            }
        }
    };

    //返回主页
    $scope.backHome = function (data) {
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitoring")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/routineMonitoringSpecial")
        }
    }
})


