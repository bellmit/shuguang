var myapp = angular.module("myapp", []);
myapp.controller("monitorTaskList", function($scope, $http, $state,$timeout) {
    var area = "";
    var jibie = "";
    var vm = this;
/*    $("#toCheck").click(function() {

        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    });*/
    //查询按钮事件
    $scope.toCheck = function ($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.btnDisabled = false;
            });
        }, 500);
        $("#grid").dataTable().api().ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };
    //重置
    $scope.resetTask = function($event) {
        //重置 二次查询
        if ($event) {
            $($event.currentTarget).prev().text("查询");
        }
        $("#year").val("");
        $("#cdatebegin").val("");
        $("#cdateend").val("");
        $("#datebegin").val("");
        $("#dateend").val("");
        //$("#monitorClass").val("");
        $("input[name='monitorClass']").removeAttr("checked");
        $("input[name='monitorClassLimit']").prop("checked", true);
        $("#industry")
            .find("option:first")
            .prop("selected", true);
        $("#ReleaseUnit").val("");
        $("#taskName").val("");
        // $("#sheng, #shi, #xian").val("");
        $("#separation")
            .find("option:first")
            .prop("selected", true);
        if (jibie == 1) {
            $("#sheng, #shi, #xian").val("");
        } else if (jibie == 2) {
            $("#shi, #xian").val("");
        } else if (jibie == 3) {
            $("#xian").val("");
        }
        $scope.separation = null;
        $scope.orgName = null;
        $scope.cityCode = null;
        $scope.checkUnit = null;
        $scope.checkObject = null;
        $scope.checkProject = null;
        $scope.sampleUnit = null;
        $scope.sampleArea = null;
        $scope.deparment = null;
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
        // jBox.tip("重置成功！","success");
    };

    //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
    $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
        for (
            var i = 0;
            i < $("input[name='" + limitCheckBoxName + "']").length;
            i++
        ) {
            var lim = $("input[name='" + limitCheckBoxName + "']")[i].checked;
            if (lim) {
                $("input[name='" + checkBoxName + "']:checked").attr(
                    "checked",
                    false
                );
            }
        }
    };
    var token = window.localStorage.getItem("token");
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    $(function() {
        $.fn.mycity("sheng", "shi", "xian", 0, 0, 0);

       /* var urlIndustry = "/sofn-qry-web/adsMonitorTask/getIndustry";*/
        var urlIndustry = "/sofn-qry-web/adsMonitorTask/getIndustryTypeData";
        //行业下拉框
        $.ajax({
            url: urlIndustry,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                /*var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.INDUSTRY || "" == td.INDUSTRY) {
                            continue;
                        }
                        Row = Row + "<option>" + td.INDUSTRY + "</option> ";
                    }
                }
                $("#industry").html(Row);*/
                $scope.industry = data.data;
                var Row = '<option>不限</option> ';
                for (var i = 0; i < $scope.industry.length; i++) {
                    Row = Row + '<option value= "' + $scope.industry[i].dictName + '">' + $scope.industry[i].dictName + '</option>'
                }
                $("#industry").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlReleaseUnit = "/sofn-qry-web/adsMonitorTask/getReleaseUnit";
        //发布机构下拉框
        $.ajax({
            url: urlReleaseUnit,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.RELEASE_UNIT || "" == td.RELEASE_UNIT) {
                            continue;
                        }
                        Row = Row + "<option>" + td.RELEASE_UNIT + "</option> ";
                    }
                }
                $("#ReleaseUnit").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlSampleLink = "/sofn-qry-web/adsMonitorTask/getSampleLink";
        //检测环节下拉框
        $.ajax({
            url: urlSampleLink,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.SAMPLE_LINK || "" == td.SAMPLE_LINK) {
                            continue;
                        }
                        Row = Row + "<option>" + td.SAMPLE_LINK + "</option> ";
                    }
                }
                $("#SampleLink").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlDetectionOrgan =
            "/sofn-qry-web/adsMonitorTask/getDetectionOrgan";
        //检测单位下拉框
        $.ajax({
            url: urlDetectionOrgan,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (
                            null == td.detectionOrgan ||
                            "" == td.detectionOrgan
                        ) {
                            continue;
                        }
                        Row =
                            Row + "<option>" + td.detectionOrgan + "</option> ";
                    }
                }
                $("#DetectionOrgan").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlSampleOrgan = "/sofn-qry-web/adsMonitorTask/getSampleOrgan";
        //抽样单位下拉框
        $.ajax({
            url: urlSampleOrgan,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.sampleOrgan || "" == td.sampleOrgan) {
                            continue;
                        }
                        Row = Row + "<option>" + td.sampleOrgan + "</option> ";
                    }
                }
                $("#SampleOrgan").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        $http({
            url: "/sofn-qry-web/subject/findCurrentUserByToken",
            method: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            }
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
                    $("#sheng").attr("disabled", true);
                }
                if (
                    $("#shi").val() != null &&
                    $("#shi").val() != "" &&
                    $("#shi").val() != undefined
                ) {
                    $("#shi").attr("disabled", true);
                }
                if (
                    $("#xian").val() != null &&
                    $("#xian").val() != "" &&
                    $("#xian").val() != undefined
                ) {
                    $("#xian").attr("disabled", true);
                }

                //bootstrap表格
                $("#grid").dataTable({
                    ordering: true, //开启排序
                    order: [[6, "desc"]], //默认排序项
                    scrollX: true, //横向滚动
                    ajax: {
                        url:
                            "/sofn-qry-web/adsMonitorTask/getRoutineMonitorListNew",
                        headers: {
                            token: token
                        },
                        dataSrc: function(json) {
                            console.log(json);
                            json.recordsTotal = json.data.total; //总个数
                            json.recordsFiltered = json.data.total;
                            json.pageNumber = json.data.pageNum; //页码
                            json.pageSize = json.data.pageSize; //每页个数
                            json.start =
                                (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                            json.length = json.data.pageSize;
                            $scope.$apply(function() {
                                $scope.tableTotal = json.data.total;
                            });
                            return json.data.list;
                        },
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        data: function(params) {
                            params.a = "a";
                            var xian = $("#xian option:selected").val();
                            var shi = $("#shi option:selected").val();
                            var sheng = $("#sheng option:selected").val();
                            if ("" != xian) {
                                params.area = xian;
                            } else if ("" != shi) {
                                params.area = shi;
                            } else {
                                params.area = sheng;
                            }
                            params.taskName = $("#taskName").val();
                            params.year = $("input[name='queryYear']").val();
                            params.cstartTime = $(
                                "input[name='cstartTime']"
                            ).val(); //开始时间
                            params.cendTime = $("input[name='cendTime']").val(); //结束时间
                            params.startTime = $(
                                "input[name='startTime']"
                            ).val(); //开始时间
                            params.endTime = $("input[name='endTime']").val(); //结束时间
                            // params.monitorClass = $(
                            //     "#monitorClass option:selected"
                            // ).text();

                            var monitorClassChecked = [];
                            $(".monitorClass:checked").map(function() {
                                monitorClassChecked.push($(this).val());
                            });
                            params.monitorClass = monitorClassChecked.join(",");
                            params.industry = $(
                                "#industry option:selected"
                            ).text();
                            params.result = $("#result option:selected").text();
                            params.ReleaseUnit = $("#ReleaseUnit").val();
                            params.qiantou = $(
                                "#qiantou option:selected"
                            ).text();
                            params.SampleLink = $(
                                "#SampleLink option:selected"
                            ).text();
                            params.DetectionOrgan = $(
                                "#DetectionOrgan option:selected"
                            ).text();
                            params.SampleOrgan = $(
                                "#SampleOrgan option:selected"
                            ).text();
                            params.token = token;

                            params.cityCode = $("#xian").val();

                            ObjShen = $("select[id='sheng']").val();
                            ObjShi = $("select[id='shi']").val();
                            ObjXian = $("select[id='xian']").val();
                            if (
                                ObjShen != "" &&
                                ObjShen != null &&
                                ObjShen != undefined
                            ) {
                                if (
                                    ObjShi != "" &&
                                    ObjShi != null &&
                                    ObjShi != undefined
                                ) {
                                    if (
                                        ObjXian != "" &&
                                        ObjXian != null &&
                                        ObjXian != undefined
                                    ) {
                                        params.cityCode = ObjXian;
                                    } else {
                                        params.cityCode = ObjShi;
                                    }
                                } else {
                                    params.cityCode = ObjShen;
                                }
                            }

                            //alert( params.cityCode);
                            // params.separation = formatParams($scope.separation);
                            params.separation = $(
                                "#separation option:selected"
                            ).val();
                            params.orgName = formatParams($scope.orgName);
                            // params.cityCode = formatParams($scope.cityCode);
                            params.checkUnit = formatParams($scope.checkUnit);
                            params.checkObject = formatParams(
                                $scope.checkObject
                            );
                            params.checkProject = formatParams(
                                $scope.checkProject
                            );
                            params.sampleUnit = formatParams($scope.sampleUnit);
                            params.sampleArea = formatParams($scope.sampleArea);
                            params.deparment = formatParams($scope.deparment);

                            //排序传参
                            if (params.order[0]) {
                                var orderIndex = params.order[0]["column"];
                                var orderKey = params.columns[orderIndex]["data"];
                                if (orderKey != "function") {
                                    params.orderBy =
                                        orderKey + " " + params.order[0]["dir"];
                                }
                            }

                            $scope.chartParams = params; //图标参数赋值
                            if (vm.chart) {
                                vm.chart.update(); //刷新图标
                            } else {
                                $timeout(function () {
                                    vm.chart.update(); //刷新图标
                                }, 1000);
                            }

                            // alert(params.deparment);
                            // params.keyWord = $("input[name='keyWord']").val();//关键词   暂时不知道怎么用
                        },
                        type: "post"
                    },
                    columns: [
                        {
                            data: "rn",
                            title: "序号",
                            orderable: false
                        },
                        {
                            data: "taskName",
                            title: "任务名称",
                            width: "25%"
                        },
                        {
                            data: "year",
                            title: "任务年度"
                        },
                        {
                            data: "monitorClass",
                            title: "任务类型",
                            width: "7%"
                        },
                        {
                            data: "industry",
                            title: "监测行业"
                        },

                        {
                            data: "releaseUnit",
                            title: "发布机构"
                        },
                        {
                            data: "createTime",
                            title: "创建时间",
                            render: function(data, type, row) {
                                return new Date(data).format("yyyy-MM-dd");
                            }
                        },
                        {
                            data: "startTime",
                            title: "任务开始时间"
                        },
                        /*{
                            data: "sampleUnit",
                            name: "sampleUnit",
                            title: "抽样单位",
                            visible: false,
                            width: "5%"
                        },*/
                      /*  {
                            data: "checkUnit",
                            name: "checkUnit",
                            title: "检测单位",
                            visible: false,
                            width: "5%"
                        },*/
                      /*  {
                            data: "sampleArea",
                            name: "sampleArea",
                            title: "抽样地区",
                            visible: false,
                            width: "5%"
                        },*/
                     /*   {
                            data: "deparment",
                            name: "deparment",
                            title: "报告上传单位",
                            visible: false,
                            width: "5%"
                        },*/
      /*                  {
                            data: "cityCode",
                            name: "cityCode",
                            title: "所属区域",
                            render : function (data, type, row) {
                                if(data != null && data !="")
                                    data = $.fn.Cityname(data);
                            /!*    if (data != null && data != undefined && data.length > 14) {
                                    return (
                                        "<a id='svAreaId" +
                                        row.id +
                                        "' onmouseover=showPopContent('svAreaId" +
                                        row.id +
                                        "','" +
                                        data +
                                        "')>" +
                                        data.substring(0, 14) +
                                        "...</a>"
                                    );
                                } else {*!/
                                    return data;
                             /!*   }*!/
                            },
                            visible: false,
                            width: "5%"
                        },*/
                        {
                            data: "separation",
                            name: "separation",
                            title: "抽检分离",
                            render: function(data, type, row) {
                                if (data == 0){
                                    return "是";
                                }else if (data == 1){
                                    return "否";
                                }else {
                                    return "";
                                }
                            },
                            visible: false,
                            width: "5%"
                        },
                        /*{
                            data: "checkObject",
                            name: "checkObject",
                            title: "检测对象",
                            width: "14.5%",
                            render:function (data,type,row) {
                                if(data!=null && data.length>30){
                                    return "<a id='checkObject"+row.id+"' onmouseover=showPopContent('checkObject"+row.id+"')>"+data.substring(0,29)+"...</a>"+
                                        "<input value='" + data +"' type='hidden'/>";
                                }else {
                                    return data;
                                }
                            },
                            visible: false,
                            orderable: false
                        },
                        {
                            data: "checkProject",
                            name: "checkProject",
                            title: "检测项目",
                            width: "14.5%",
                            render:function (data,type,row) {
                                if(data!=null && data.length>30){
                                    return "<a id='checkProject"+row.id+"' onmouseover=showPopContent('checkProject"+row.id+"')>"+data.substring(0,29)+"...</a>"+
                                        "<input value='" + data +"' type='hidden'/>";
                                }else {
                                    return data;
                                }
                            },
                            visible: false,
                            orderable: false
                        },*/
                    /*    {
                            data: "orgName",
                            name: "orgName",
                            title: "牵头单位",
                            visible: false,
                            width: "5%",
                            orderable: false
                        },*/
                        {
                            data: "id",
                            title: "操作",
                            render: function(data, type, row) {
                                var look_a =
                                    '<a style="margin-right: 10px;cursor: pointer" onclick="goto(\'' +
                                    row.id +
                                    "')\">" +
                                    "查看" +
                                    "</a>";
                                if (
                                    row.monitorClass == "例行监测" ||
                                    row.monitorClass == "专项监测"
                                ) {
                                    // look_a += ' <a style="cursor:pointer;margin-right: 10px;" onclick="queryTaskCondition(\''+row.id+'\')" class="color">任务完成情况</a>';
                                    look_a +=
                                        ' <a style="cursor:pointer;margin-right: 10px;" onclick="queryTaskCondition(\'' +
                                        row.id +
                                        "'" +
                                        ",'" +
                                        row.taskName +
                                        '\')"class="color">任务完成情况</a>';
                                } else {
                                    look_a +=
                                        ' <a style="cursor: pointer;opacity: 0.2;margin-right: 10px;" class="color" disabled="disabled">任务完成情况</a>';
                                }
                                look_a +=
                                    '<a style="cursor:pointer;margin-right: 10px;" onclick="querySamples(\'' +
                                    row.id +
                                    "','" +
                                    row.monitorClass +
                                    "','" +
                                    row.taskName +
                                    "','" +
                                    row.industry +
                                    '\')" class="color">抽样单</a>  ';
                                look_a +=
                                    '<a style="cursor:pointer" onclick="queryReport(\'' +
                                    row.id +
                                    "','" +
                                    row.monitorClass +
                                    "','" +
                                    row.taskName +
                                    "','" +
                                    row.industry +
                                    '\')" class="color">承担单位报告</a>  ';
                                return look_a;
                            },
                            orderable: false
                        }
                    ]
                });
            })
            .error(function() {});
    });

    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
        content = $("#"+id).parent().find("input").val();
        $("#"+id).popover({
            animation:true,
            html:false,
            placement:'right',
            trigger:'hover',
            content:content,
            delay:0,
            container:'body'
        });
        $("#"+id).popover("show");
    }

    window.goto = function(data) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        $state.go("index.content.qry/monitoringSystem/taskDetail");
    };

    window.queryTaskCondition = function(data, taskName) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("taskName", taskName); //监测任务id
        $state.go("index.content.qry/monitoringSystem/taskCondition");
    };

    window.querySamples = function(data, monitorClass, taskName, industry) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        if (
            monitorClass == "例行监测" ||
            monitorClass == "专项监测" ||
            monitorClass == "复检任务"
        ) {
            $state.go("index.content.qry/monitoringSystem/qrySampleMessage");
        } else if (monitorClass == "监督抽查") {
            $state.go(
                "index.content.qry/monitoringSystem/qryAuditSampleMessage"
            );
        } else if (monitorClass == "受托检测") {
            $state.go(
                "index.content.qry/monitoringSystem/qryDelegationSampleMessage"
            );
        }
    };

    window.queryReport = function(data, monitorClass, taskName, industry) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("taskName", taskName); //任务名称
        window.localStorage.setItem("monitorClass", monitorClass); //任务类型
        $state.go("index.content.qry/monitoringSystem/qryReportsList");
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
        url: "/sofn-qry-web/adsMonitorTask/getMonitorTaskChart",
        grid: [2,2,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
