var myapp = angular.module("myapp", []);
myapp.controller("qryMyCheckTask", function($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    /*    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function(item,i){
        if(item.text=="监督抽查任务"){
            $(".qryEntrustDetection").css("display","inline-block");
        }
    });*/
    var vm = this;
    $scope.tabOrIdx = window.localStorage.getItem(
        "ales_taskmanage_mychecktask_taskinformation_opr_idx_II"
    );
    if ($scope.tabOrIdx == "history") {
        window.localStorage.removeItem(
            "ales_taskmanage_mychecktask_taskinformation_opr_idx_II"
        );
        $("li[name='newTask']").removeClass("active");
        $("li[name='historyTask']").addClass("active");
    }
    /**
     * *
     * *承担任务包含新任务与历史任务，判断标准为任务是否填写抽样单信息
     * *
     * */
    var token = window.localStorage.getItem("token");

    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //机构类型
    $scope.orgType = "";
    var area = "";
    var jibie = "";
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post"
    })
        .success(function(data) {
            area = data.user.regionId;
            jibie = data.user.orgLevel;
            if (jibie == 1) {
                area = 0;
            }
            if (jibie == 2) {
                area = area.substr(0, 2);
            }
            if (jibie == 3) {
                area = area.substr(0.4);
            }
            if (jibie == 4) {
                area = area.substring(0, 6);
            }
            $.fn.mycity("sheng", "shi", "xian", area);
            //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
            if (
                $("#sheng").val() != null &&
                $("#sheng").val() != "" &&
                $("#sheng").val() != undefined
            ) {
                $("#sheng")
                    .attr("disabled", true)
                    .parent()
                    .addClass("disabled");
            }
            if (
                $("#shi").val() != null &&
                $("#shi").val() != "" &&
                $("#shi").val() != undefined
            ) {
                $("#shi")
                    .attr("disabled", true)
                    .parent()
                    .addClass("disabled");
            }
            if (
                $("#xian").val() != null &&
                $("#xian").val() != "" &&
                $("#xian").val() != undefined
            ) {
                $("#xian")
                    .attr("disabled", true)
                    .parent()
                    .addClass("disabled");
            }
            $scope.loadGrid(); /*else{        取消的区域隐藏 2018年5月24日08:48:26
            //执法机构   隐藏区域
           $("#AnyArea").hide();
            $scope.loadGrid();
            //$("#grid").dataTable().api().ajax.reload();
        }*/
            /* $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        $scope.orgType= $scope.organization.orgType;
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));*/

            //监管机构可以按照区域查询
            /*   if($scope.orgType=='ASMS'){
            //监管机构，显示区域框
            $("#AnyArea").show();
            //初始化区域下拉框初始选项
            $scope.reloadArea();
            $scope.loadGrid();
            //$("#grid").dataTable().api().ajax.reload();
        }*/
        })
        .error(function(data) {
            console.log(data.httpCode);
            
        });
    //重置区域 取消限制 2018年5月24日10:48:14
    /*   $scope.reloadArea=function(){
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
        }
    }*/

    $scope.getAreaBySelect = function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (
            province != null &&
            province != undefined &&
            province != ""
        ) {
            areaId = province;
        }
        console.log("------------" + areaId);
        return areaId;
    };

    /**
     *公共值
     * */

    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    /**
     *任务列表
     * */
    $scope.loadGrid = function() {
        $("#grid").dataTable({
            ordering: true, //开启排序
            order: [[5, "asc"]], //默认排序项
            scrollX: true, //横向滚动
            fnDrawCallback: function() {},
            ajax: {
                url:
                    "/sofn-qry-web/alesmynewchecktask/getHistoryCheckTaskListByUser_two",
                dataSrc: function(json) {
                    /*if (json.httpCode != 200) {
                        swal("系统提示", "网络连接异常！", "error");
                        return false;
                    }*/
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        //时间格式化
                        if (
                            json.data.list[i].TASKBEGINTIME != null &&
                            json.data.list[i].TASKBEGINTIME != "" &&
                            json.data.list[i].TASKBEGINTIME != undefined
                        ) {
                            json.data.list[i].TASKBEGINTIME = new Date(
                                parseInt(json.data.list[i].TASKBEGINTIME) +
                                    28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                        if (
                            json.data.list[i].TASKENDTIME != null &&
                            json.data.list[i].TASKENDTIME != "" &&
                            json.data.list[i].TASKENDTIME != undefined
                        ) {
                            json.data.list[i].TASKENDTIME = new Date(
                                parseInt(json.data.list[i].TASKENDTIME) +
                                    28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                        //任务状态
                        if (
                            json.data.list[i].STATE != null &&
                            json.data.list[i].STATE != "" &&
                            json.data.list[i].STATE != undefined
                        ) {
                            if (json.data.list[i].STATE == "0") {
                                json.data.list[i].STATE = "未发布";
                            }
                            if (json.data.list[i].STATE == "1") {
                                json.data.list[i].STATE = "已发布";
                            }
                            if (json.data.list[i].STATE == "2") {
                                json.data.list[i].STATE = "已废止";
                            }
                        }
                    }
                    $scope.$apply(function() {
                        $scope.tableTotal = json.data.total;
                    });
                    return json.data.list;
                },
                error: function(data) {
                    console.log(data);
                    
                },
                data: function(params) {
                    //监管区域
                    // if($scope.orgType=='ASMS'){
                    params.regionId = $scope.getAreaBySelect();
                    //  }else{
                    //  params.regionId="";
                    // }
                    //任务状态
                    params.isSample = $("select[name='isSample']").val();
                    //任务类型
                    params.taskType = $("select[name='taskType']").val();
                    //任务年度
                    params.taskYear = $("input[name='taskYear']").val();
                    params.taskName = $(
                        "input[name='History_Task_NAME']"
                    ).val();
                    //开始时间beginEndyzncxz功能 2018年5月31日10:42:52
                    params.dateBegin = $(
                        "input[name='History_Task_Date_Begin']"
                    ).val();
                    params.beginEnd = $(
                        "input[name='History_Task_Date_BeginEnd']"
                    ).val();
                    //结束时间yzncxz功能 2018年5月31日10:42:52
                    params.dateEnd = $(
                        "input[name='History_Task_Date_End']"
                    ).val();
                    params.endBegin = $(
                        "input[name='History_Task_Date_EndBegin']"
                    ).val();

                    params.token = token;

                    //custom condition
                    params.taskReleaseUnit = formatParams($scope.taskReleaseUnit);//任务发布单位ok
                    params.cyUnitName = formatParams($scope.cyUnitName);//抽样单位
                    params.JdUnitName = formatParams($scope.JdUnitName);//监督执法单位ok
                    params.jcUnitName = formatParams($scope.jcUnitName);//检测单位ok
                    params.fileNumber = formatParams($scope.fileNumber);//文件号

                    params.standardName = formatParams( $scope.standardName);//判定标准

                    console.log("========="+params)
                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                    orderKey + " " + params.order[0]["dir"];
                        }
                    }
                    console.log("=============params"+params)
                    $scope.chartParams = params; //图标参数赋值
                    console.log("=============$scope.chartParams "+$scope.chartParams )
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }
                },
                type: "post"
            },
            columns: [
                {
                    data: "ID",
                    title: "ID",
                    visible: false
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%",
                    orderable: false
                },
                {
                    data: "TASKNAME",
                    title: "任务名称",
                    width: "10%",
                    render: function(data, type, row) {
                        if (data != null && data.length > 30) {
                            return (
                                "<a id='TASKNAME" +
                                row.ID +
                                "' onmouseover=showPopContent('TASKNAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 29) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "TASKYEAR",
                    title: "年度",
                    width: "8%"
                },
                {
                    data: "TASKTYPE",
                    title: "监测类型",
                    width: "8%"
                },
                {
                    data: "TASKBEGINTIME",
                    title: "开始时间",
                    width: "10%"
                },
                {
                    data: "TASKENDTIME",
                    title: "结束时间",
                    width: "10%"
                },
                {
                    data: "ISSAMPLE",
                    title: "任务状态",
                    width: "8%",
                    render: function(data, type, row) {
                        if (data == "0") {
                            return "未提交";
                        } else if (data == "1") {
                            return "已提交";
                        } else if (data == "2") {
                            return "部分提交";
                        }
                    }
                },
                {
                    data: "ISSAMPLE",
                    title: "抽样单",
                    width: "8%",
                    render: function(data, type, row) {
                        if (data == "0") {
                            return "未提交";
                        } else if (data == "1") {
                            return "已提交";
                        } else if (data == "2") {
                            return "部分提交";
                        }
                    }
                },
                {
                    data: "TASKRELEASEUNIT",
                    name: "taskReleaseUnit",
                    title: "任务发布单位",
                    visible: false
                },
                {
                    data: "CYUNITNAME",
                    name: "cyUnitName",
                    title: "抽样单位",
                    visible: false
                },
                {
                    data: "JDUNITNAME",
                    name: "JdUnitName",
                    title: "监督执法单位",
                    visible: false
                },
                {
                    data: "JCUNITNAME",
                    name: "jcUnitName",
                    title: "检测单位",
                    visible: false
                },
                {
                    data: "STANDARDNAME",
                    name: "standardName",
                    title: "判定标准",
                    visible: false
                },
                {
                    data: "FILENUMBER",
                    name: "fileNumber",
                    title: "文件号",
                    visible: false
                },
                {
                    data: "ID",
                    title: "报告",
                    width: "5%",
                    render: function(data, type, row) {
                        return (
                            '<a style="cursor: pointer" onclick="queryHistoryInfo(\'' +
                            data +
                            "')\">任务详情</a>"
                        ); /*     &nbsp;&nbsp;&nbsp;' +
                            ' <a style="cursor: pointer" onclick="queryHistoryInfo(\''+data+'\')">查看报告</a>';*/
                    },
                    orderable: false
                }

            ],
            language: {
                sProcessing: "处理中...",
                sLengthMenu: "显示 _MENU_ 项结果",
                sZeroRecords: "没有匹配结果",
                sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项结果，共 项",
                sInfoFiltered: "(由 _MAX_ 项结果过滤)",
                sInfoPostFix: "",
                sSearch: "搜索:",
                sUrl: "",
                sEmptyTable: "表中数据为空",
                sLoadingRecords: "载入中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNext: "下页",
                    sLast: "末页"
                },
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列"
                }
            },
            sPaginationType: "full_numbers"
        });
    };

    /**
     *查询按钮
     * */

    $scope.querySelectList =function ($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid")
        .dataTable()
        .api()
        .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    }
    /*$("#History_Task_ToCheck").click(function() {
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    });*/

    //鼠标悬停弹窗显示
    window.showPopContent = function(id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: "right",
            trigger: "hover",
            content: content,
            delay: 0,
            container: "body"
        });
        $("#" + id).popover("show");
    };

    /**
     *任务详情查看
     * */
    window.queryHistoryInfo = function(o) {
        window.localStorage.removeItem(
            "ales_taskmanage_mychecktask_taskinformation_opr_idx"
        );
        window.localStorage.setItem(
            "ales_taskmanage_mychecktask_taskinformation_opr_idx",
            "history"
        );
        window.localStorage.removeItem(
            "ales_taskmanage_mychecktask_taskinformation_opr"
        );
        window.localStorage.setItem(
            "ales_taskmanage_mychecktask_taskinformation_opr",
            o
        );
        $state.go("index.content.qry/taskManage/qryMyTaskInformation");
    };

    //数据导出
    $scope.exportData = function() {

        //提交数据
        var taskYear = $("input[name='taskYear']").val();
        var isSample = $("select[name='isSample']").val();
        // var taskType=$("select[name='taskType']").val(); 类型取消 2018年5月24日14:55:47
        var taskName = $("input[name='History_Task_NAME']").val();
        //开始时间
        var dateBegin = $("input[name='History_Task_Date_Begin']").val();
        var beginEnd = $("input[name='History_Task_Date_BeginEnd']").val();
        //结束时间yzncxz功能 2018年5月31日10:42:52
        var dateEnd = $("input[name='History_Task_Date_End']").val();
        var endBegin = $("input[name='History_Task_Date_EndBegin']").val();


        var token = window.localStorage.getItem("token");

        var taskReleaseUnit = $("input[name='taskReleaseUnit']").val();//任务发布单位
        var cyUnitName = $("input[name='cyUnitName']").val();//抽样单位
        var JdUnitName = $("input[name='JdUnitName']").val();//监督执法单位
        var jcUnitName = $("input[name='jcUnitName']").val();//检测单位
        var standardName = $("input[name='standardName']").val();//判定标准
        var fileNumber = $("input[name='fileNumber']").val();//文件号
        //监管区域
        //if($scope.orgType=='ASMS'){ 取消消监管机构限制 2018年5月24日14:45:48
        var regionId = $scope.getAreaBySelect();
        /*}else{
            var regionId="";
        }*/

        //form
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr(
            "action",
            "/sofn-qry-web/alesmynewchecktask/exportSubjSv?token=" +
                token +
                "&taskName=" +
                taskName +
                "&dateBegin=" +
                dateBegin +
                "&beginEnd=" +
                beginEnd +
                "&dateEnd=" +
                dateEnd +
                "&endBegin=" +
                endBegin +
                "&regionId=" +
                regionId +
                "&taskYear=" +
                taskYear +
                "&isSample=" +
                isSample+
                "&taskReleaseUnit=" +
                taskReleaseUnit+
                "&cyUnitName=" +
                cyUnitName+
                "&JdUnitName=" +
                JdUnitName+
                "&jcUnitName=" +
                jcUnitName+
                "&standardName=" +
                standardName+
                "&fileNumber=" +
                fileNumber

        ); //+'&taskType='+taskType 取消类型 2018年5月24日14:58:24
        $("body").append(form);
        form.submit();
    };

    //重置
    $scope.cleanSelectModelQuery = function($event) {
        if ($event) {
            $($event.currentTarget)
            .prev()
            .text("查询");
        }
        $("input[name='History_Task_NAME']").val("");
        $("input[name='taskYear']").val("");
        $("input[name='History_Task_Date_Begin']").val("");
        //yzxj功能 2018年5月31日11:13:10
        $("input[name='History_Task_Date_BeginEnd']").val("");
        $("input[name='History_Task_Date_EndBegin']").val("");
        $("input[name='History_Task_Date_End']").val("");
        $("select[name='taskType']").val("");
        $("select[name='isSample']").val("");
        $("select[name='shiName']:enabled").val("");
        $("select[name='xianName']:enabled").val("");
        $("select[value='']").attr("selected", true);
        //custom condition
        $scope.taskReleaseUnit = null;
        $scope.cyUnitName = null;
        $scope.JdUnitName = null;
        $scope.jcUnitName = null;
        $scope.fileNumber = null;
        $scope.standardName = null;
        $.fn.mycity("sheng", "shi", "xian", area);
        if ($scope.orgType == "ASMS") {
            $scope.reloadArea();
        }
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    };
    /**************************
        CUSTOM QUERY CONDITION
    *************************/

    $scope.confirmCondition = function() {
        var modal = $("#modalCondition");
        modal
            .find(".gui-list li")
            .not(".disabled")
            .map(function() {
                var key = $.trim($(this).attr("data-key"));
                var checked = $(this)
                    .find(":checkbox")
                    .is(":checked");
                if (!checked) {
                    var control = $(
                        '.gui-form-group .condition-custom[ng-show="visible' +
                            key +
                            '"]'
                    );
                    var controlType = control.attr("data-control");
                    switch (controlType) {
                        case "text":
                            $scope[key] = "";
                            break;
                        case "select":
                            $scope[key] = "";
                            control
                                .find("option:first")
                                .prop("selected", true)
                                .trigger("change");
                            break;
                        case "checkbox":
                            $scope[key] = "";
                            control
                                .find(":checkbox")
                                .first()
                                .prop("checked", true)
                                .end()
                                .siblings()
                                .prop("checked", false);
                            break;
                        case "date":
                            control
                                .find("input:text")
                                .val("")
                                .trigger("input");
                            break;
                        case "radio":
                            break;
                    }
                }
                $scope["visible" + key] = checked;
                //切换表格列是否可见
                var table = $("#grid").DataTable();
                var column = table.column(key + ":name");
                column.visible(checked);
            });
        modal.modal("hide");
    };
    $("#modalCondition")
        .on("change", ".condition-checked-all", function() {
            var checked = $(this).is(":checked");
            $("#modalCondition")
                .find("li")
                .not(".disabled")
                .find(":checkbox")
                .prop("checked", checked);
        })
        .on("change", ".gui-list :checkbox", function() {
            var unchecked = $(this)
                .closest(".gui-list")
                .find(":checkbox")
                .filter(function() {
                    return !$(this).is(":checked");
                });
            $(".condition-checked-all").prop("checked", !!!unchecked.length);
        })
        .on("shown.bs.modal", function() {
            $(".gui-form-group .condition-custom").each(function() {
                var visible = $(this).is(":visible");
                var key = $(this)
                    .attr("ng-show")
                    .replace("visible", "");
                $(".gui-list.list-condition")
                    .find('li[data-key="' + key + '"]')
                    .find(":checkbox")
                    .prop("checked", visible)
                    .trigger("change");
            });
        });

    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-qry-web/alesmynewchecktask/getHistoryCheckTaskListByUser_two_Statis",
        grid: [2,2,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
