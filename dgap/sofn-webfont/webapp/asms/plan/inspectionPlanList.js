var myapp = angular.module("myapp", []);
myapp.controller("inspectionPlanList", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("巡查计划");
    $scope.inspectionListQueryParams = JSON.parse(window.sessionStorage.getItem("inspectionListQueryParams"));
    $scope.taskTypeTouched = false;
    $scope.statusDict = [{
        name: "未开始",
        value: "0"
    }, {
        name: "执行中",
        value: "1"
    }, {
        name: "已结束,已完成",
        value: "2"
    }, {
        name: "已结束,未完成",
        value: "3"
    }];
    $scope.taskTypeDict = [{
        name: "基地",
        value: "1"
    }, {
        name: "主体",
        value: "2"
    }, {
        name: "农资",
        value: "3"
    }, {
        name: "其它",
        value: "0"
    }];

    //定义一个主体选择的容器  储存巡查主体 id entityName
    $scope.ents = [];

    /**
     * 表单验证
     * 新增/修改/退出需清除验证状态
     * */
    //验证初始化
    $('#attributeForm').bootstrapValidator();
    //验证是否通过
    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };
    //清除验证状态
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };

    //重置区域
    $scope.resetPlan = function () {
        $("#searchForm").form("reset");
    };

    //巡查计划列表
    $scope.loadGrid = function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/inspectionPlan/getInspPlanList",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.start = json.data.start;//起始位置
                    json.length = json.data.length;
                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityName = $("input[name='entityName']").val();
                    params.planName = $("input[name='planName']").val();
                    params.taskName = $("input[name='taskName']").val();
                    params.status = $("select[name='status']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width: "2%"
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "planName",
                    title: "巡查计划"
                },
                {
                    data: "taskName",
                    title: "考核任务"
                },
                {
                    data: "areaId",
                    title: "巡查区域",
                    render: function (data) {
                        if (data) {
                            return $.fn.Cityname(data);
                        }
                    }
                },
                {
                    data: "planStartDate",
                    title: "开始时间",
                    render: function (data) {
                        if (data) {
                            return new Date(parseInt(data) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data: "planEndDate",
                    title: "结束时间",
                    render: function (data) {
                        if (data) {
                            return new Date(parseInt(data) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data: "taskType",
                    title: "任务类型",
                    width: "11%",
                    render: function (data) {
                        if (!data) {
                            return;
                        }
                        var resArr = [];
                        $.each(data.split(","), function (i, o) {
                            $.each($scope.taskTypeDict, function (j, p) {
                                if (o == p.value) {
                                    resArr.push(p.name);
                                }
                            })
                        })
                        return resArr.join(",");
                    }
                },
                {
                    data: "status",
                    title: "任务状态",
                    width: "11%",
                    render: function (data) {
                        //未发布
                        if (data == 0) {
                            return '<span style="color:#1f934b;font-weight: bold;">未开始</span>';
                        }
                        //执行中
                        if (data == 1) {
                            return '<span style="color:#333333;font-weight: bold;">执行中</span>';
                        }
                        //已结束,已完成
                        if (data == 2) {
                            return '<span style="color:#bfbfbf;font-weight: bold;">已结束,已完成</span>';
                        }
                        //已结束,未完成
                        if (data == 3) {
                            return '<span style="color:#ff0d06;font-weight: bold;">已结束,未完成</span>';
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt = '<a style="margin-right: 5px;cursor: pointer" class="Check-report color"  onclick="planView(\'' + data + '\')">查看</a>';
                        if ($scope.iswrite == 2) {
                            if (row.createBy == $scope.user.id) {
                                butt += '<a style="margin-right: 5px;cursor: pointer" class="Check-report color" onclick="updatePlanModel(\'' + data + '\' , \'' + row.status + '\')">修改</a>';
                            }
                        }
                        return butt;
                    }
                },
            ],
        });
    }

    //从缓存从获取数据
    if (window.localStorage.getItem("asmsAllSysDictAndUserInfo")) {
        //获取用户信息
        $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
        $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
        //所属区域
        $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['allIndustryId'];
        $scope.resetPlan();
        $scope.loadGrid();
        loadEntGrid();
    }

    //重新加载基地巡查列表
    $scope.queryPlan = function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function () {
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid").dataTable().api().ajax.reload();
    };

    //巡查计划修改模态框
    window.updatePlanModel = function (planId, status) {
        if (status && status !== "0") {
            $.jBox.tip("任务已在执行中,不允许修改!");
            return;
        }
        $scope.taskTypeTouched = false;
        $("#planOprModelTitle").html("修改计划");
        $('#planOprModel').modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/inspectionPlan/findOne",
            method: "post",
            params: {"id": planId}
        }).success(function (res) {
            $scope.plan = res.data;
            $scope.plan.planEndDate = new Date(parseInt($scope.plan.planEndDate) + 28800000).toISOString().slice(0, 10);
            $scope.plan.planStartDate = new Date(parseInt($scope.plan.planStartDate) + 28800000).toISOString().slice(0, 10);
            $("input[name='planEndDate']").val($scope.plan.planEndDate);
            $("input[name='planStartDate']").val($scope.plan.planStartDate);
            //任务类型的回显
            $scope.plan.taskType.split(',').map(function (type) {
                $("input[name = 'taskType'][value=" + type + "]").prop('checked', true);
            });
            $.fn.mycity("sheng", "shi", "xian", $scope.plan.areaId);
            if ($scope.organization.orgLevel == 2) {
                $("#sheng").prop("disabled", true);
            } else if ($scope.organization.orgLevel == 3) {
                $("#sheng").prop("disabled", true);
                $("#shi").prop("disabled", true);
            } else if ($scope.organization.orgLevel == 4) {
                $("#sheng").prop("disabled", true);
                $("#shi").prop("disabled", true);
                $("#xian").prop("disabled", true);
            }
            //关联考核任务区域
            $scope.taskAreaId = $scope.plan.taskAreaId;
            $scope.disabledArea("sheng", "shi", "xian", $scope.plan.taskAreaId);
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.plan.taskAreaId);
            $scope.disabledArea("querySheng", "queryShi", "queryXian", $scope.plan.taskAreaId);
            $.fn.mycity("queryTempSheng", "queryTempShi", "queryTempXian", $scope.plan.taskAreaId);
            $scope.disabledArea("queryTempSheng", "queryTempShi", "queryTempXian", $scope.plan.taskAreaId);
            //巡查主体回显
            var entityNameStr = [];
            $.each($scope.plan.ents, function (i, o) {
                entityNameStr.push(o.entityName);
            })
            $scope.plan.entityName = entityNameStr.join(",");
            //关联考核任务回显
            $scope.taskName = $scope.plan.taskName;
            $scope.taskId = $scope.plan.taskId;
            $("#taskName").val($scope.plan.taskName);
            $("#entityName").val($scope.plan.entityName);
            $scope.ents = $scope.plan.ents;
        });

    }

    //巡查查看详情
    window.planView = function (planId) {
        $scope.planInfo = {};
        $("#planInfoModel").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/inspectionPlan/findOne",
            method: "post",
            params: {"id": planId}
        }).success(function (res) {
            $scope.planInfo = res.data;
            $scope.planInfo.planEndDate = new Date(parseInt($scope.planInfo.planEndDate) + 28800000).toISOString().slice(0, 10);
            $scope.planInfo.planStartDate = new Date(parseInt($scope.planInfo.planStartDate) + 28800000).toISOString().slice(0, 10);
            var taskTypeArr = [];
            $.each($scope.planInfo.taskType.split(","), function (i, o) {
                $.each($scope.taskTypeDict, function (j, p) {
                    if (o == p.value) {
                        taskTypeArr.push(p.name);
                    }
                })
            });
            $scope.planInfo.taskType = taskTypeArr.join(",");
            var entityNameStr = [];
            $.each($scope.planInfo.ents, function (i, o) {
                entityNameStr.push(o.entityName);
            })
            $scope.planInfo.entityName = entityNameStr.join(",");
        });
    };


    //新增巡查计划
    $scope.savePlan = function ($event) {
        if (!$scope.dataFormValidator()) {
            return false;
        }
        //巡查时间
        $scope.plan.planStartDate = $("#planStartDate").val();
        $scope.plan.planEndDate = $("#planEndDate").val();
        //巡查区域
        $scope.plan.areaId = $scope.getAreaId();
        //巡查类型
        var taskTypeArr = [];
        $("input[name='taskType']:checked").each(function (i, o) {
            if ($(o).val()) {
                taskTypeArr.push($(o).val());
            }
        });
        $scope.plan.taskId = $scope.taskId;
        $scope.plan.taskName = $scope.taskName;
        $scope.plan.taskType = taskTypeArr.join(",");
        if (!$scope.plan.planStartDate) {
            $.jBox.tip("巡查开始时间不能为空!");
            return;
        }
        if (!$scope.plan.planEndDate) {
            $.jBox.tip("巡查结束时间不能为空!");
            return;
        }
        if (!$scope.plan.taskType) {
            $.jBox.tip("巡查类型不能为空!");
            return;
        }
        $scope.plan.areaName = $.fn.Cityname($scope.plan.areaId);
        $scope.plan.ents = $scope.ents;
        $scope.plan.taskAreaId = $scope.taskAreaId;
        $($event.currentTarget).prop("disabled", true);
        //保存
        $http.post("/sofn-asms-web/inspectionPlan/addOrUpdate", $scope.plan, {
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).then(function (res) {
            $($event.currentTarget).prop("disabled", false);
            if (res.data.httpCode == 200) {
                $.jBox.tip("成功!");
                $('#grid').dataTable().api().ajax.reload();
                $('#planOprModel').modal('hide');
            } else {
                $.jBox.tip("失败!");
            }
        }, function (res) {
            
        })

    };


    //删除巡查计划
    $scope.deletePlans = function (data) {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        //限制仅未开始任务能修改
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].status && rows[i].status !== "0") {
                $.jBox.tip("任务已在执行中,不允许删除!");
                return;
            }
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].createBy.trim() != "admin" && rows[n].createBy.trim() != $scope.user.id) {
                $.jBox.tip("没有权限,仅创建者可以删除!");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        if (rows != false) {
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    //保存
                    $http.post("/sofn-asms-web/inspectionPlan/deletePlans", $.param({ids: ids.join(",")}), {
                        headers: {
                            "Content-Type": 'application/x-www-form-urlencoded',
                            token: window.localStorage.getItem("token")
                        }
                    }).then(function (res) {
                        if (res.data.httpCode == 200) {
                            $.jBox.tip("删除成功!");
                            $('#grid').dataTable().api().ajax.reload();
                            $('#planOprModel').modal('hide');
                        } else {
                            $.jBox.tip("删除失败!");
                        }
                    }, function (res) {
                        
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

    //鼠标悬停弹窗显示
    window.showPopContent = function (id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: 'right',
            trigger: 'hover',
            content: content,
            delay: 0,
            container: 'body'
        });
        $("#" + id).popover("show");
    }
    //新增-模态框
    $scope.addPlan = function () {
        $scope.taskTypeTouched = true;
        $scope.plan = {};
        $scope.ents = [];
        $("#attributeForm").form("reset");
        $("#planOprModelTitle").html("新增计划");
        $.fn.mycity("sheng", "shi", "xian", $scope.organization.regionId);
        //清除区域限制
        $("#sheng").prop("disabled", false);
        $("#shi").prop("disabled", false);
        $("#xian").prop("disabled", false);
        //默认限制当前区域
        if ($scope.organization.orgLevel == 2) {
            $("#sheng").prop("disabled", true);
        } else if ($scope.organization.orgLevel == 3) {
            $("#sheng").prop("disabled", true);
            $("#shi").prop("disabled", true);
        } else if ($scope.organization.orgLevel == 4) {
            $("#sheng").prop("disabled", true);
            $("#shi").prop("disabled", true);
            $("#xian").prop("disabled", true);
        }
        $('#planOprModel').modal("show");
    };
    //选择关联考核任务-模态框
    $scope.selectTask = function () {
        $("input[name='taskId']").prop("checked", false);
        $("#selectTaskModal").modal('show');
    };

    //关联考核任务列表
    $("#selectTaskGrid").dataTable({
        ajax: {
            url: "/sofn-asms-web/tspinstask/getUserKhTaskLit",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.start = json.data.start;//起始位置
                json.length = json.data.length;
                for (var i = 0; i < json.data.list.length; i++) {
                    //日期格式化
                    if (json.data.list[i].CREATETIME != null && json.data.list[i].CREATETIME != "" && json.data.list[i].CREATETIME != undefined) {
                        json.data.list[i].CREATETIME = new Date(parseInt(json.data.list[i].CREATETIME)).toISOString().slice(0, 10);
                    }
                    //任务状态判断
                    //任务时间

                    var date = new Date();
                    var taskType = $.trim(json.data.list[i].TASKDATETYPE);
                    var taskYear = json.data.list[i].TASKDATEYEAR;//任务时间-年
                    var taskYearMonth = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEMONTHS;//任务时间-年+月
                    var taskDateMonth = json.data.list[i].TASKDATEMONTHS;
                    var taskYearQuarter = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEQUARTER;//任务时间-年+季度
                    var taskQuarter = json.data.list[i].TASKDATEQUARTER;

                    //当前时间
                    var nowYear = date.getFullYear();//当前年
                    var nowYearMonth;//当前年+月
                    var nowMonth = date.getMonth() + 1;//当前月份
                    if (nowMonth < 10) {
                        nowYearMonth = date.getFullYear() + "0" + nowMonth;
                    } else {
                        nowYearMonth = date.getFullYear() + "" + nowMonth;
                    }
                    var currQuarter = Math.floor((nowMonth % 3 == 0 ? (nowMonth / 3) : (nowMonth / 3 + 1)));
                    var nowYearQuarter = date.getFullYear() + "" + currQuarter;//当前年+季度
                    if (taskType == "年度") {
                        if (nowYear > taskYear) {
                            json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                        } else if (nowYear < taskYear) {
                            json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                        } else {
                            json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                        }
                        json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年";
                    } else if (taskType == "月度") {
                        if (nowYearMonth > taskYearMonth) {
                            json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                        } else if (nowYearMonth < taskYearMonth) {
                            json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                        } else {
                            json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                        }
                        json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEMONTHS + "月";
                    } else if (taskType == "季度") {
                        if (nowYearQuarter > taskYearQuarter) {
                            json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                        } else if (nowYearQuarter < taskYearQuarter) {
                            json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                        } else {
                            json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                        }
                        json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEQUARTER + "季度";
                    }
                    if (json.data.list[i].FINISHFLAG == true) {
                        json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已完成</B></p>';
                    }
                }
                return json.data.list;
            },
            headers: {
                token: window.localStorage.getItem("token")
            },
            type: "post"
        },
        columns: [
            //选择按钮
            {
                data: "ck",
                title: '选择',
                render: function (data, type, row) { // 模板化列显示内容
                    return '<input name="taskId" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                }
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: 'ID',
                title: 'ID',
                visible: false
            },
            {
                data: "TASKNAME",
                title: "任务名称",
                width: "18%",
                render: function (data, type, row) {
                    return data;
                }
            },
            {
                data: "INSPECTIONAREAID",
                title: "巡查区域",
                width: "18%",
                render: function (data, type, row) {
                    return $.fn.Cityname(data);
                }
            },
            {
                data: "TASKDATETYPE",
                title: "考核类型"
            },
            {
                data: "TASKDATETYPE",
                title: "考核时间",
                render: function (data, type, row) {
                    if (!data) {
                        return;
                    }
                    data = data.trim();
                    if (data == '年度') {
                        return row.TASKDATEYEAR + "年";
                    } else if (data == '季度') {
                        return row.TASKDATEYEAR + "年" + row.TASKDATEQUARTER + "季度";
                    } else if (data == '月度') {
                        return row.TASKDATEYEAR + "年" + row.TASKDATEMONTHS + "月";
                    }
                }
            },
            {
                data: "TASKTYPENAME",
                title: "任务类型"
            },
            {
                data: "INSPECTIONCOUNT",
                title: "巡查次数"
            },
            {
                data: "ENABLE",
                title: "任务状态"
            },
        ],
        fnDrawCallback: function () {
            //设置选中状态
            $("#selectTaskGrid >tbody >tr").click(function () {
                $(this).children("td").eq(0).children().prop("checked", true);
                $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                if ($(this).hasClass('selected')) {
                    $("input[name='taskId']").removeAttr("checked");
                    $(this).removeClass('selected');
                } else {
                    $('#selectTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        },
    });
    //提交所选考核任务
    $scope.submitTask = function () {
        var rows = $("#selectTaskGrid").DataTable().rows('.selected').data();
        if (rows.length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        if (rows[0].ENABLE && (rows[0].ENABLE.indexOf('已结束') > 0)) {
            $.jBox.tip("该任务已结束！");
            return false;
        }
        if (rows[0].ENABLE && (rows[0].ENABLE.indexOf('已完成') > 0)) {
            $.jBox.tip("该任务已完成！");
            return false;
        }
        $scope.taskId = rows[0].ID;
        $scope.taskName = rows[0].TASKNAME;
        $("#taskName").val($scope.taskName);
        var area = rows[0].INSPECTIONAREAID;
        $.fn.mycity("sheng", "shi", "xian", area);
        $scope.disabledArea("sheng", "shi", "xian", area);
        $scope.taskAreaId = area;
        $('#selectTaskModal').modal('hide');
    };
    //限制区域选择
    $scope.disabledArea = function (sheng, shi, xian, area) {
        if (area) {
            var provinceReg = /^[1-9][0-9]0000$/ig;
            var cityReg = /^[[1-9][0-9]{2}[1-9]00$/ig;
            if (provinceReg.test(area)) {
                $("#" + sheng).prop("disabled", true);
                $("#" + shi).prop("disabled", false);
                $("#" + xian).prop("disabled", false);
            } else if (cityReg.test(area)) {
                $("#" + sheng).prop("disabled", true);
                $("#" + shi).prop("disabled", true);
                $("#" + xian).prop("disabled", false);
            } else {
                $("#" + sheng).prop("disabled", true);
                $("#" + shi).prop("disabled", true);
                $("#" + xian).prop("disabled", true);
            }
        }
    };


    //选择生产经营主体模态框显示
    $scope.selectEnt = function () {
        $("#enterpriseModal").modal("show");
        var area = $scope.getAreaId();
        $.fn.mycity("querySheng", "queryShi", "queryXian", area);
        $scope.disabledArea("querySheng", "queryShi", "queryXian", area);
        $.fn.mycity("queryTempSheng", "queryTempShi", "queryTempXian", area);
        $scope.disabledArea("queryTempSheng", "queryTempShi", "queryTempXian", area);
        $scope.querySubjEntList();
        $scope.querySubjEntTempList();
    };

    //选择临时生产经营主体模态框显示
    $scope.selectEntTemp = function () {
        $("#entTempListModal").modal("show");
        $scope.querySubjEntTempList();
    };

    //切换到生产经营主体
    $scope.switchEnt = function () {
        $("#entTab").addClass("gui-TabCheck");
        $("#entTempTab").removeClass("gui-TabCheck");
        $("#entDiv").show();
        $("#entTempDiv").hide();
    }

    //切换到临时生产经营主体
    $scope.switchEntTemp = function () {
        $("#entTempTab").addClass("gui-TabCheck");
        $("#entTab").removeClass("gui-TabCheck");
        $("#entTempDiv").show();
        $("#entDiv").hide();
    }

    //生产经营主体列表
    function loadEntGrid() {
        $("#enterpriseGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.enterpriseName = $("input[name='entName']").val();
                    params.userIdcode = $("input[name='userIdcode']").val();
                    params.approveStatus = "1,3,4,5"; //查询可用的生产经营主体
                    params.entityIndustry = $scope.allIndustryId; //所属行业
                    var province = $("#querySheng").val();
                    var city = $("#queryShi").val();
                    var county = $("#queryXian").val();
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
                    title: '选择',
                    width: "2%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.ents.length; i++) {
                            if ($scope.ents[i] != undefined && data == $scope.ents[i].entityId) {
                                return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "2%"

                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    width: "13%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 16) {
                            return "<a name='entityName' id='subjEntEnterpriseName" + row.id + "' onmouseover=showPopContent('subjEntEnterpriseName" + row.id + "','" + data + "')>" + data.substring(0, 16) + "...</a>";
                        } else {
                            return '<span name="entityName">' + data + '</span>';
                        }
                    },
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width: "7%"
                },
                {
                    data: "address",
                    title: "主体地址",
                    width: "12%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 14) {
                            return "<a id='subjEntAddress" + row.id + "' onmouseover=showPopContent('subjEntAddress" + row.id + "','" + data + "')>" + data.substring(0, 14) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "legalName",
                    title: "法定代表人",
                    width: "4%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 4) {
                            return "<a id='contactName" + row.id + "' onmouseover=showPopContent('contactName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "legalPhone",
                    title: "代表电话",
                    width: "5%"
                },
                {
                    data: "contactName",
                    title: "联系人",
                    width: "4%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 4) {
                            return "<a id='contactName" + row.id + "' onmouseover=showPopContent('contactName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "contactPhone",
                    title: "联系电话",
                    width: "5%"
                }
            ],
            fnDrawCallback: function () {
                //点击行内任意内容即选择行
                $("#enterpriseGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        console.log($scope.ents);
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //主体移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0; i < $scope.ents.length; i++) {
                                if ($scope.ents[i] != undefined && u_d == $scope.ents[i].entityId) {
                                    $scope.ents.splice(i, 1);
                                    i--;
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //添加主体
                            var map = {
                                entityId: $(this).parent().find("[name='ck']").val(),
                                entityName: $(this).parent().find("[name='entityName']").html()
                            };
                            $scope.ents.push(map);
                        }
                    });
                })
            }
        });

        $("#entTempGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntTempList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityName = $("input[name='entTempName']").val();
                    params.entityIndustry = $scope.allIndustryId;
                    var province = $("#queryTempSheng").val();
                    var city = $("#queryTempShi").val();
                    var county = $("#queryTempXian").val();
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
                    title: '选择',
                    width: "2%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.ents.length; i++) {
                            if ($scope.ents[i] != undefined && data == $scope.ents[i].entityId) {
                                return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "2%"
                },
                {
                    data: "entityName",
                    title: "主体名称",
                    width: "15%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 20) {
                            return "<a name='entityName' id='subjEntTempEnterpriseName" + row.id + "' onmouseover=showPopContent('subjEntTempEnterpriseName" + row.id + "','" + data + "')>" + data.substring(0, 20) + "...</a>";
                        } else {
                            return '<span name="entityName">' + data + '</span>';
                        }
                    },
                },
                {
                    data: "entityIndustry",
                    title: "所属行业",
                    width: "7%"
                },
                {
                    data: "address",
                    title: "主体地址",
                    width: "15%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 20) {
                            return "<a id='subjEntTempAddress" + row.id + "' onmouseover=showPopContent('subjEntTempAddress" + row.id + "','" + data + "')>" + data.substring(0, 20) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "contactName",
                    title: "联系人",
                    width: "5%",
                    render: function (data, type, row) {
                        if (data != null && data != undefined && data.length > 5) {
                            return "<a id='contactTempName" + row.id + "' onmouseover=showPopContent('contactTempName" + row.id + "','" + data + "')>" + data.substring(0, 5) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "contactPhone",
                    title: "联系电话",
                    width: "5%"
                }
            ],
            fnDrawCallback: function () {
                //点击行内任意内容即选择行
                $("#entTempGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //主体移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0; i < $scope.ents.length; i++) {
                                if ($scope.ents[i] != undefined && u_d == $scope.ents[i].entityId) {
                                    $scope.ents.splice(i, 1);
                                    i--;
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //添加主体
                            var map = {
                                entityId: $(this).parent().find("[name='ck']").val(),
                                entityName: $(this).parent().find("[name='entityName']").html()
                            };
                            $scope.ents.push(map);
                        }
                    });
                })
            }
        });
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
    };

    //提交生产经营主体
    $scope.submitEnt = function () {
        var tempArr = [];
        $.each($scope.ents, function (i, o) {
            if (o) {
                tempArr.push(o.entityName);
            }
        });
        $("#entityName").val(tempArr.join(","));
        $('#enterpriseModal').modal('hide');
    };


    //重新加载生产经营主体表格
    $scope.querySubjEntList = function () {
        $("#enterpriseGrid").dataTable().api().ajax.reload();
    }

    //重新加载临时生产经营主体表格
    $scope.querySubjEntTempList = function () {
        $("#entTempGrid").dataTable().api().ajax.reload();
    }

    $scope.getAreaId = function () {
        //巡查区域
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city + "00";
        } else if (province != null && province != undefined && province != "") {
            areaId = province + "0000";
        }
        return areaId;
    }

    $("#sheng, #shi, #xian").each(function (i, o) {
        $(o).click(function () {
            $scope.ents = [];
            $("#entityName").val(null);
        })
    })

})

