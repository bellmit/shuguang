var myapp = angular.module("myapp", []);
myapp.controller("checkTaskAddSj", function($scope, $http, $state, $q) {
    $scope.allIndustryId = "";
    /**
     * public
     * */
    $scope.userToken = window.localStorage.getItem("token"); //用户token
    $scope.taskId = window.localStorage.getItem("asms_checktask_taskinfo_taskid"); //任务id
    $scope.baseInspectionId = window.localStorage.getItem("inspectionIdForCheckTask"); //基地巡查

    /**
     * private
     * */
    $scope._checkTaskData = {}; //监督抽查
    $scope.monitorobject = {}; //检测对象
    $scope._parentTask = undefined; //父级任务
    $scope.selects = new Array(); //抽样机构
    $scope.jdUnits = new Array();//监督执法单位
    $scope.jcSelects = new Array(); //检测机构
    $scope._asmsJcStandard = []; //检测标准
    $scope._asmsPdStandard = []; //判定标准
    $scope._asmsCheckObjectCriterion = new Array(); //检测项
    $scope._sjUnit = new Array(); //受检单位
    $scope.standards = [];
    setTimeout(function(){
        //从缓存中获取数据
        if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
            //获取用户信息
            $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
            //获取机构信息
            $scope._userOrgData = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
            $scope.orgLevel = $scope._userOrgData.orgLevel;
            //获取行业数据字典
            $scope.industryType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['industryType'];
            //同步前端绑定
            $scope.$apply(function(){
                //去除字典全部项
                $scope.industryType = $scope.industryType.slice(1,$scope.industryType.length);
            })
            //所属区域
            $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
            //全部拥有的行业
            $scope.allIndustryId = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['allIndustryId'];
            /**
             * 字典数据
             * */
            $scope.HyLx = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['monitorModel']; //监测模型
            $scope.HyFl = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['industryType']; //行业分类

            $scope.regionId = $scope._userOrgData.regionId;
            $("input[name='taskReleaseUnit']").val($scope._userOrgData.orgName);
            $.fn.mycity("sheng", "shi", "xian", $scope.regionId);
            $.fn.mycity("objsheng", "objshi", "objxian", $scope.regionId);
            $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", $scope.regionId);
            $.fn.mycity("jcSelectQueryShen", "jcSelectQueryShi", "jcSelectQueryXian", $scope.regionId);
            $.fn.mycity("sjUnitShen", "sjUnitShi", "sjUnitXian", $scope.regionId);
            var orgLevel = $scope._userOrgData.orgLevel;
            if(orgLevel == "2"){
                $("#sheng").attr({ "disabled": "disabled" });
                $("#objsheng").attr({ "disabled": "disabled" });
                $("#selectQueryShen").attr({ "disabled": "disabled" });
                $("#jcSelectQueryShen").attr({ "disabled": "disabled" });
                $("#sjUnitShen").attr({ "disabled": "disabled" });
            }else if(orgLevel == "3"){
                $("#sheng").attr({ "disabled": "disabled" });
                $("#shi").attr({ "disabled": "disabled" });
                $("#objsheng").attr({ "disabled": "disabled" });
                $("#objshi").attr({ "disabled": "disabled" });
                $("#selectQueryShen").attr({ "disabled": "disabled" });
                $("#jcSelectQueryShen").attr({ "disabled": "disabled" });
                $("#sjUnitShen").attr({ "disabled": "disabled" });
                $("#selectQueryShi").attr({ "disabled": "disabled" });
                // 检测机构字段支持选择本省内所有检测机构
                //$("#jcSelectQueryShi").attr({ "disabled": "disabled" });
                $("#sjUnitShi").attr({ "disabled": "disabled" });
            }else if (orgLevel == "4"){
                $("#sheng").attr({ "disabled": "disabled" });
                $("#shi").attr({ "disabled": "disabled" });
                $("#xian").attr({ "disabled": "disabled" });
                $("#objsheng").attr({ "disabled": "disabled" });
                $("#objshi").attr({ "disabled": "disabled" });
                $("#objxian").attr({ "disabled": "disabled" });
                $("#selectQueryShen").attr({ "disabled": "disabled" });
                $("#jcSelectQueryShen").attr({ "disabled": "disabled" });
                $("#sjUnitShen").attr({ "disabled": "disabled" });
                $("#selectQueryShi").attr({ "disabled": "disabled" });
                // 检测机构字段支持选择本省内所有检测机构
                // $("#jcSelectQueryShi").attr({ "disabled": "disabled" });
                $("#sjUnitShi").attr({ "disabled": "disabled" });
                $("#selectQueryXian").attr({ "disabled": "disabled" });
                // 检测机构字段支持选择本省内所有检测机构
                // $("#jcSelectQueryXian").attr({ "disabled": "disabled" });
                $("#sjUnitXian").attr({ "disabled": "disabled" });
            }
            //判断修改或者新增操作
            if ($scope.taskId != undefined && $scope.taskId != null) {
                $("#OprTitle").html("修改");
                $http({
                    url:"/sofn-asms-web/checkTask/getTaskById",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data:{ id: $scope.taskId },
                    method:"post"
                }).success(function(data){
                    $scope._checkTaskData = data.data;
                    $("input[name='taskyear']").val($scope._checkTaskData.taskYear); //插件兼容性处理
                    $("input[name='taskBeginTime']").val(new Date(parseInt($scope._checkTaskData.taskBeginTime) + 28800000).toISOString().slice(0, 10));
                    $("input[name='taskEndTime']").val(new Date(parseInt($scope._checkTaskData.taskEndTime) + 28800000).toISOString().slice(0, 10));
                    $("input[name='taskSampleDeadline']").val(new Date(parseInt($scope._checkTaskData.taskSampleDeadline) + 28800000).toISOString().slice(0, 10));
                    $.fn.mycity("sheng", "shi", "xian", $scope._checkTaskData.taskAreaId);
                    $scope._parentTask = null;
                    $scope._parentTask = new Array(); //父级任务
                    var map1 = {
                        parentTaskId: $scope._checkTaskData.parentTaskId,
                        parentTaskName: $scope._checkTaskData.parentTaskName
                    };
                    $scope._parentTask.push(map1);
                    $scope.selects = null;
                    $scope.selects = new Array(); //抽样单位
                    var map2 = { leadUnitId: $scope._checkTaskData.cyUnitId, leadUnitName: $scope._checkTaskData.cyUnitName };
                    $scope.selects.push(map2);
                    $scope.jcSelects = null;
                    $scope.jcSelects = new Array(); //检测单位
                    var map3 = { leadUnitId: $scope._checkTaskData.jcUnitId, leadUnitName: $scope._checkTaskData.jcUnitName };
                    var map4 = { leadUnitId: $scope._checkTaskData.cyUnitId, leadUnitName: $scope._checkTaskData.cyUnitName };
                    $scope.jdUnits = null;
                    $scope.jdUnits = new Array();//监督执法单位
                    $scope.jdUnits.push(map4);
                    $scope.jcSelects.push(map3);
                    var jc = data.jcList; //检测标准
                    if (jc.length != 0) {
                        $scope._checkTaskData.detectionStandards = undefined;
                        for (var i = 0; i < jc.length; i++) {
                            var map = {
                                standardId: jc[i].standardId,
                                standardName: jc[i].standardName
                            };
                            $scope._asmsJcStandard.push(map);
                            if ($scope._checkTaskData.detectionStandards != undefined) {
                                $scope._checkTaskData.detectionStandards += "," + jc[i].standardName;
                            } else {
                                $scope._checkTaskData.detectionStandards = jc[i].standardName;
                            }
                        }
                    }
                    var pd = data.pdList; //判断标准
                    if (pd.length != 0) {
                        $scope._checkTaskData.judgeStandards = undefined;
                        for (var i = 0; i < pd.length; i++) {
                            var map = {
                                standardId: pd[i].standardId,
                                standardName: pd[i].standardName
                            };
                            $scope._asmsPdStandard.push(map);
                            if ($scope._checkTaskData.judgeStandards != undefined) {
                                $scope._checkTaskData.judgeStandards += "," + pd[i].standardName;
                            } else {
                                $scope._checkTaskData.judgeStandards = pd[i].standardName;
                            }
                        }
                    }
                    //附件回显
                    vm.files[0] = {
                        src:$scope._checkTaskData.attachmentAddress,
                        name:$scope._checkTaskData.attachmentName
                    }
                    $("button[name='nextStepButton']").show();
                })
            } else {
                $("#OprTitle").html("新增");
            }
        }else{
            swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
            setTimeout(function(){
                window.location.href = '#/login/login';
                window.location.reload();
            },2500)
        }

    },100)

    /**
     * dataGrid
     * */
    var parentTaskGrid = $("#parentTaskGrid").dataTable({
        fnDrawCallback: function() {
            $("#parentTaskGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    $scope._parentTask = null;
                    $scope._parentTask = new Array();
                    var map = {
                        parentTaskId: $(this).parent().find("[name='id']").val(),
                        parentTaskName: $(this).parent().find("[name='name']").html()
                    };
                    if (!map.parentTaskId&&!map.parentTaskName) {
                        return;
                    }
                    $scope._parentTask.push(map);
                    var name = "";
                    for (var i = 0; i < $scope._parentTask.length; i++) {
                        if ($scope._parentTask[i] != undefined) {
                            if (name != "") {
                                name += "," + $scope._parentTask[i].parentTaskName;
                            } else {
                                name = $scope._parentTask[i].parentTaskName;
                            }
                        }
                    }
                    $("input[name='parentTaskName']").val(name);
                    //上级任务的年度时间不可修改
                    $("input[name='taskyear']").val($(this).parent().find("[name='taskYearF']").val());
                    $("input[name='taskyear']").prop("disabled",true);
                    //上级任务时间显示可以修改
                    $("input[name='taskBeginTime']").val(new Date(parseInt($(this).parent().find("[name='taskBeginTimeF']").val()) + 28800000).toISOString().slice(0, 10));
                    $("input[name='taskEndTime']").val(new Date(parseInt($(this).parent().find("[name='taskEndTimeF']").val()) + 28800000).toISOString().slice(0, 10));
                    $("#parent_Task_Model").modal('hide');
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/checkTask/getParentTaskList",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.data.total; //总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum; //页码
                json.pageSize = json.data.pageSize; //每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function(params) {
                params.taskName = $("input[name='queryTaskName']").val();
            },
            type: "post"
        },
        columns: [{
            data: 'ID',
            render: function(data, type, row, meta) {
                var input1 = '<input type="hidden" name="id" value = "' + data + '"/>';
                var input2 = '<input type="hidden" name="taskBeginTimeF" value = "' + row.TASK_BEGIN_TIME + '"/>';
                var input3 = '<input type="hidden" name="taskEndTimeF" value = "' + row.TASK_END_TIME + '"/>';
                var input4 = '<input type="hidden" name="taskYearF" value = "' + row.TASK_YEAR + '"/>';
                return input1+input2+input3+input4;
            }
        },
            {
                data: 'ID',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                width: "5%",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "TASK_NAME",
                title: "任务名称",
                width: "40%",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "TASK_YEAR",
                title: "年度",
                width: "5%"
            },
            {
                data: "TASK_TYPE",
                title: "任务类型",
                width: "8%"
            },
            {
                data: "CREATE_ORG_NAME",
                title: "发布单位",
                width: "28%"
            },
            {
                data: "TASK_PRIORITY",
                title: "任务优先级",
                width: "10%",
                render: function (data, type, row) {
                    //特急
                    if (data == 1) {
                        return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #CE0000;">特急</label>';
                    }
                    //紧急
                    if (data == 2) {
                        return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #D9B300;">紧急</label>';
                    }
                    //一般
                    if (data == 3) {
                        return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #0080FF;">一般</label>';
                    }
                }
            }
        ]
    }); //父级任务
    var selectGrid = $("#selectGrid").dataTable({
        fnDrawCallback: function() {
            $("#selectGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    if($scope.cyOrJd == "cy"){
                        $scope.selects = null;
                        $scope.selects = new Array();
                        var map = {
                            leadUnitId: $(this).parent().find("[name='id']").val(),
                            leadUnitName: $(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                        var name = "";
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined) {
                                if (name != "") {
                                    name += "," + $scope.selects[i].leadUnitName;
                                } else {
                                    name = $scope.selects[i].leadUnitName;
                                }
                            }
                        }
                        $scope.$apply(function() {
                            $scope._checkTaskData.cyUnitName = name;
                        });
                    }else{
                        $scope.jdUnits = null;
                        $scope.jdUnits = new Array();
                        var map1 = {
                            leadUnitId: $(this).parent().find("[name='id']").val(),
                            leadUnitName: $(this).parent().find("[name='name']").html()
                        };
                        $scope.jdUnits.push(map1);
                        var name = "";
                        for (var i = 0; i < $scope.jdUnits.length; i++) {
                            if ($scope.jdUnits[i] != undefined) {
                                if (name != "") {
                                    name += "," + $scope.jdUnits[i].leadUnitName;
                                } else {
                                    name = $scope.jdUnits[i].leadUnitName;
                                }
                            }
                        }
                        $scope.$apply(function() {
                            $scope._checkTaskData.jdUnitName = name;
                        });
                    }
                    $("#selectModal").modal('hide');
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/asmsTools/getZfOrgsListByUserToken",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal; //总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start; //起始位置
                json.length = json.page.length;
                return json.data;
            },
            data: function(params) {
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != "") {
                    params.elAreaId = $("#selectQueryXian").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == "") {
                    params.elAreaId = $("#selectQueryShi").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == "") {
                    params.elAreaId = $("#selectQueryShen").val();
                }
                params.querySheng = $("#querySheng").val();
                params.queryShi = $("#queryShi").val();
                params.queryXian = $("#queryXian").val();
                params.elName = $("input[name='selectQueryName']").val();
            },
            type: "post"
        },
        columns: [{
            data: 'id',
            width: "2%",
            render: function(data, type, row, meta) {
                return '<input type="hidden" name="id" value = "' + data + '"/>';
            }
        },
            {
                title: "序号",
                width: "5%",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "elName",
                title: "单位名称",
                width: "50%",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "elType",
                title: "机构类别",
                width: "10%",
            },
            {
                data: "elAreaId",
                title: "所属区域",
                width: "35%",
                render: function(data) {
                    return $.fn.Cityname(data);
                }
            }
        ]
    }); //抽样机构
    var jcSelectGrid = $("#jcSelectGrid").dataTable({
        fnDrawCallback: function() {
            $("#jcSelectGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    $scope.jcSelects = null;
                    $scope.jcSelects = new Array();
                    var map = {
                        leadUnitId: $(this).parent().find("[name='id']").val(),
                        leadUnitName: $(this).parent().find("[name='name']").html()
                    };
                    $scope.jcSelects.push(map);
                    var name = "";
                    for (var i = 0; i < $scope.jcSelects.length; i++) {
                        if ($scope.jcSelects[i] != undefined) {
                            if (name != "") {
                                name += "," + $scope.jcSelects[i].leadUnitName;
                            } else {
                                name = $scope.jcSelects[i].leadUnitName;
                            }
                        }
                    }
                    $scope.$apply(function() {
                        $scope._checkTaskData.jcUnitName = name;
                    })
                    $("#jcSelectModal").modal('hide');
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/asmsTools/getOrgsListByUserToken",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal;
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start;
                json.length = json.page.length;
                return json.data;
            },
            data: function(params) {
                if ($("#jcSelectQueryShen").val() != "" && $("#jcSelectQueryShi").val() != "" && $("#jcSelectQueryXian").val() != "") {
                    params.dtAreaId = $("#jcSelectQueryXian").val();
                }
                if ($("#jcSelectQueryShen").val() != "" && $("#jcSelectQueryShi").val() != "" && $("#jcSelectQueryXian").val() == "") {
                    params.dtAreaId = $("#jcSelectQueryShi").val();
                }
                if ($("#jcSelectQueryShen").val() != "" && $("#jcSelectQueryShi").val() == "" && $("#jcSelectQueryXian").val() == "") {
                    params.dtAreaId = $("#jcSelectQueryShen").val();
                }
                params.querySheng = $("#querySheng").val();
                params.queryShi = $("#queryShi").val();
                params.queryXian = $("#queryXian").val();
                params.dtName = $("input[name='jcSelectQueryName']").val();
            },
            type: "post"
        },
        columns: [{
            data: 'id',
            width: "2%",
            render: function(data, type, row, meta) {
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
                width: "5%",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "dtName",
                title: "单位名称",
                width: "50%",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "dtType",
                title: "机构类别",
                width: "10%",
            },
            {
                data: "dtAreaId",
                title: "所属区域",
                width: "35%",
                render: function(data) {
                    return $.fn.Cityname(data);
                }
            }
        ]
    }); //检测机构
    var JC_DX_LIST = $("#JC_DX_LIST").dataTable({
        fnDrawCallback: function() {
            $("#checkAllObjectCk").prop("checked",$("#JC_DX_LIST input[name='objectCk']").length>0&&$("#JC_DX_LIST input[name='objectCk']").length==$("#JC_DX_LIST input[name='objectCk']:checked").length);
            $("#JC_DX_LIST tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                    }
                    $("#checkAllObjectCk").prop("checked",$("#JC_DX_LIST input[name='objectCk']").length>0&&$("#JC_DX_LIST input[name='objectCk']").length==$("#JC_DX_LIST input[name='objectCk']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/checkTask/objList",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.data.total; //总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum; //页码
                json.pageSize = json.data.pageSize; //每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function(params) {
                params.superviseCheckTaskId = $scope.taskId; //任务id
            },
            type: "post"
        },
        columns: [{
            data: "objectCk",
            title: '<input id="checkAllObjectCk" onclick="checkAllObjectCk(this)"  type="checkbox"/>',
            render: function(data, type, row) {
                return '<input type="checkbox" name="objectCk" onclick="checkThisBox(this)"/>';
            },
            width: "3%"
        },
            {
                data: 'id',
                title: 'ID',
                visible: false
            }, {
                data: "productName",
                title: "名称",
                width: "10%"
            },
            {
                data: "productType",
                title: "类型",
                width: "5%"
            },
            {
                data: "monitorNum",
                title: "抽样数量",
                width: "5%"
            },
            {
                data: "areaId",
                title: "受检区域",
                width: "10%",
                render: function(data, type, row) {
                    if (data == "" || data == undefined) {
                        return "未填写"
                    } else {
                        return $.fn.Cityname(data);
                    }
                }
            }
        ]
    }); //监测对象
    var detectionStandardTaskGrid = $("#detectionStandardTaskGrid").dataTable({
        fnDrawCallback: function() {
            //判断是否全选
            $("#checkAllJcStandard").prop("checked",$("#detectionStandardTaskGrid input[name='ck']").length>0&&$("#detectionStandardTaskGrid input[name='ck']").length==$("#detectionStandardTaskGrid input[name='ck']:checked").length);
            $("#detectionStandardTaskGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0; i < $scope._asmsJcStandard.length; i++) {
                            if ($scope._asmsJcStandard[i] != undefined && u_d == $scope._asmsJcStandard[i].standardId) {
                                delete $scope._asmsJcStandard[i];
                            }
                        }
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        var map = {
                            standardId: $(this).parent().find("[name='ck']").val(),
                            standardName: $(this).parent().find("[name='name']").html()
                        };
                        $scope._asmsJcStandard.push(map);
                    }
                    //判断是否全选
                    $("#checkAllJcStandard").prop("checked",$("#detectionStandardTaskGrid input[name='ck']").length>0&&$("#detectionStandardTaskGrid input[name='ck']").length==$("#detectionStandardTaskGrid input[name='ck']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/sysDict/getStandardList",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal; //总个数
                json.recordsFiltered = json.page.recordsTotal;
                json.start = json.page.start; //起始位置
                json.length = json.page.length;
                return json.list;
            },
            type: "post"
        },
        columns: [{
            data: "id",
            title : '<input id="checkAllJcStandard" onclick="checkAllJcStandard(this.checked)" type=checkbox>',
            width:"5%",
            render: function(data, type, row) {
                for (var i = 0; i < $scope._asmsJcStandard.length; i++) {
                    if ($scope._asmsJcStandard[i] != undefined && data == $scope._asmsJcStandard[i].standardId) {
                        return '<input type="checkbox" checked = "true" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                }
                return '<input type="checkbox" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
            }
        },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                },
                width:"5%"
            },
            {
                data: "standardName",
                title: "名称",
                width:"25%"
            },
            {
                data: "standardCode",
                title: "标准号",
                width:"25%",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "remark",
                title: "备注",
                width:"25%"
            }
        ]
    }); //检测标准
    var judgeStandardTaskGrid = $("#judgeStandardTaskGrid").dataTable({
        bPaginate: false,
        bInfo: false,
        fnDrawCallback: function() {
            //判断是否全选
            $("#checkAllPdStandard").prop("checked",$("#judgeStandardTaskGrid input[name='ck']").length>0&&$("#judgeStandardTaskGrid input[name='ck']").length==$("#judgeStandardTaskGrid input[name='ck']:checked").length);
            $("#judgeStandardTaskGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0; i < $scope._asmsPdStandard.length; i++) {
                            if ($scope._asmsPdStandard[i] != undefined && u_d == $scope._asmsPdStandard[i].standardId) {
                                delete $scope._asmsPdStandard[i];
                            }
                        }
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        var map = {
                            standardId: $(this).parent().find("[name='ck']").val(),
                            standardName: $(this).parent().find("[name='name']").html()
                        };
                        $scope._asmsPdStandard.push(map);
                    }
                    //判断是否全选
                    $("#checkAllPdStandard").prop("checked",$("#judgeStandardTaskGrid input[name='ck']").length>0&&$("#judgeStandardTaskGrid input[name='ck']").length==$("#judgeStandardTaskGrid input[name='ck']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/sysDict/getDictListByCode",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                return json.data;
            },
            data: function(params) {
                params.type = 26;
            },
            type: "post"
        },
        columns: [{
            data: "id",
            title : '<input id="checkAllPdStandard" onclick="checkAllPdStandard(this.checked)" type=checkbox>',
            render: function(data, type, row) {
                for (var i = 0; i < $scope._asmsPdStandard.length; i++) {
                    if ($scope._asmsPdStandard[i] != undefined && data == $scope._asmsPdStandard[i].standardId) {
                        return '<input type="checkbox" checked = "true" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                }
                return '<input type="checkbox" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
            }
        },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "dictName",
                title: "名称"
            },
            {
                data: "dictValue",
                title: "值"
            },
            {
                data: "dictCode",
                title: "编码",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            }
        ]
    }); //判定标准
    $("#criteList").dataTable({
        fnDrawCallback: function() {
            //判断是否全选
            $("#checkAllCrite").prop("checked",$("#criteList input[name='ck']").length>0&&$("#criteList input[name='ck']").length==$("#criteList input[name='ck']:checked").length);
            $("#criteList tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0; i < $scope._asmsCheckObjectCriterion.length; i++) {
                            if ($scope._asmsCheckObjectCriterion[i] != undefined && u_d == $scope._asmsCheckObjectCriterion[i].criterionId) {
                                delete $scope._asmsCheckObjectCriterion[i];
                            }
                        }
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        var map = {
                            criterionId: $(this).parent().find("[name='ck']").val(),
                            criterionName: $(this).parent().find("[name='name']").html()
                        };
                        $scope._asmsCheckObjectCriterion.push(map);
                    }
                    //判断是否全选
                    $("#checkAllCrite").prop("checked",$("#criteList input[name='ck']").length>0&&$("#criteList input[name='ck']").length==$("#criteList input[name='ck']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/checkTask/getCheckItemList",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal;
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start;
                json.length = json.page.length;
                return json.list;
            },
            data: function(params) {
                params.standardCode =$("#standardCode").val();
                params.itemName = $("#itemName").val();
                //行业
                if ($scope.monitorobject.industry && $scope.industryType) {
                    var industry;
                    for (var i = 0; i < $scope.industryType.length; i++) {
                        if ($scope.monitorobject.industry === $scope.industryType[i].dictValue) {
                            industry = $scope.industryType[i].dictName;
                            break;
                        }
                    }
                    params.industry = industry;
                }
            },
            type: "post"
        },
        columns: [{
            data: "id",
            width: "4%",
            title : '<input id="checkAllCrite" onclick="checkAllCrite(this.checked)" type=checkbox>',
            render: function(data, type, row) {
                for (var i = 0; i < $scope._asmsCheckObjectCriterion.length; i++) {
                    if ($scope._asmsCheckObjectCriterion[i] != undefined && data == $scope._asmsCheckObjectCriterion[i].criterionId) {
                        return '<input type="checkbox" checked = "true" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                }
                return '<input type="checkbox" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
            }
        },
            {
                title: "序号",
                width: "5%",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "standardCode",
                title: "标准号",
                width: "28%",
            },
            {
                data: "itemName",
                title: "检测项",
                width: "42%",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "scope",
                title: "所属行业",
                width: "12%",
            }
        ]
    }); //检测项
    //监测对象全选全不选
    window.checkAllObjectCk = function(target) {
        $("#JC_DX_LIST tr").each(function(i,o){
            $(o).find("input[name='objectCk']").prop("checked",target.checked);
            if (target.checked) {
                $(o).addClass("selected");
            } else {
                $(o).removeClass("selected");
            }

        })
    }

    //检测项全选,全不选
    window.checkAllCrite = function(checked){
        //本页检测项全部移除
        $("#criteList input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope._asmsCheckObjectCriterion.length ; i++){
                if($scope._asmsCheckObjectCriterion[i] != undefined && u_d == $scope._asmsCheckObjectCriterion[i].criterionId){
                    delete $scope._asmsCheckObjectCriterion[i];
                }
            }
        })
        //添加本页全部检测项
        if(checked){
            $("#criteList input[name='ck']:checked").each(function(i,o) {
                var map = {
                    criterionId: $(o).val(),
                    criterionName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope._asmsCheckObjectCriterion.push(map);
            });
        }
    }

    var sJUnitTaskGrid = $("#sJUnitTaskGrid").dataTable({
        fnDrawCallback: function() {
            //判断是否全选
            $("#checkAllSjUnit").prop("checked",$("#sJUnitTaskGrid input[name='ck']").length>0&&$("#sJUnitTaskGrid input[name='ck']").length==$("#sJUnitTaskGrid input[name='ck']:checked").length);
            $("#sJUnitTaskGrid tbody tr td").each(function(i, o) {
                $(o).click(function() {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0; i < $scope._sjUnit.length; i++) {
                            if ($scope._sjUnit[i] != undefined && u_d == $scope._sjUnit[i].enterpriseId) {
                                delete $scope._sjUnit[i];
                            }
                        }
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        var map = {
                            enterpriseId: $(this).parent().find("[name='ck']").val(),
                            enterpriseName: $(this).parent().find("[name='name']").html()
                        };
                        $scope._sjUnit.push(map);
                    }
                    //判断是否全选
                    $("#checkAllSjUnit").prop("checked",$("#sJUnitTaskGrid input[name='ck']").length>0&&$("#sJUnitTaskGrid input[name='ck']").length==$("#sJUnitTaskGrid input[name='ck']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/subjEnt/getSubjEntList",
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal;
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start;
                json.length = json.page.length;
                return json.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: function(params) {
                params.enterpriseName = $("input[name='sjUnitName']").val();
                params.entityIdcode = $("input[name='entIdCode']").val();
                params.approveStatus = "1,3,4,5"; //可用主体
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
        columns: [{
            data: "id",
            title : '<input id="checkAllSjUnit" onclick="checkAllSjUnit(this.checked)" type=checkbox>',
            render: function(data, type, row) {
                for (var i = 0; i < $scope._sjUnit.length; i++) {
                    if ($scope._sjUnit[i] != undefined && data == $scope._sjUnit[i].enterpriseId) {
                        return '<input type="checkbox" checked = "true" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                }
                return '<input type="checkbox" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
            }
        },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "enterpriseName",
                title: "单位名称",
                render: function(data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "entityIndustryName",
                title: "所属行业"
            },
            {
                data: "area",
                title: "所属区域",
                render: function(data, type, row) {
                    return $.fn.Cityname(data);
                }
            }
        ]
    }); //受检单位

    //受检单位全选,全不选
    window.checkAllSjUnit = function(checked){
        //本页受检单位全部移除
        $("#sJUnitTaskGrid input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope._sjUnit.length ; i++){
                if($scope._sjUnit[i] != undefined && u_d == $scope._sjUnit[i].enterpriseId){
                    delete $scope._sjUnit[i];
                }
            }
        })
        //添加本页全部受检单位
        if(checked){
            $("#sJUnitTaskGrid input[name='ck']:checked").each(function(i,o) {
                var map = {
                    enterpriseId: $(o).val(),
                    enterpriseName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope._sjUnit.push(map);
            });
        }
    }

    $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");
    $.fn.mycity("jcSelectQueryShen", "jcSelectQueryShi", "jcSelectQueryXian");
    $.fn.mycity("objsheng", "objshi", "objxian");
    $.fn.mycity("sjUnitShen", "sjUnitShi", "sjUnitXian");
    /**
     * model
     * */
    $('#parent_Task_Model').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.parentTaskModel = function() {
        $('#parentTaskGrid').dataTable().api().ajax.reload();
        $("#parent_Task_Model").modal('show');
    }; //父级任务
    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.cyOrJd = null;
    $scope.selectCyUnits = function() {
        $scope.cyOrJd = "cy";
        $("#selectModal").find(".modal-title").html("<span>抽样机构选择</span>");
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    }; //抽样机构
    $scope.selectJdUnits = function() {
        $scope.cyOrJd = "jd";
        $("#selectModal").find(".modal-title").html("<span>监督执法单位选择</span>");
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    }; //抽样机构
    $('#jcSelectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.selectJcUnits = function() {
        $('#jcSelectGrid').dataTable().api().ajax.reload();
        $("#jcSelectModal").modal('show');
    }; //检测机构
    $('#detectionStandard_Model').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.selectdetectionStandards = function() {
        $('#detectionStandardTaskGrid').dataTable().api().ajax.reload();
        $("#detectionStandard_Model").modal('show');
    }; //检测标准
    $('#judgeStandard_Model').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.judgeStandardStandards = function() {
        $('#judgeStandardTaskGrid').dataTable().api().ajax.reload();
        $("#judgeStandard_Model").modal('show');
    }; //判定标准
    $('#sJUnit_Model').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.sJUnitStandards = function() {
        $('#sJUnitTaskGrid').dataTable().api().ajax.reload();
        $("#sJUnit_Model").modal('show');
    }; //受检单位
    $scope.criteCheck = function() {
        $("#standardCode").html("");
        for (var i = 0; i < $scope._asmsJcStandard.length;i++) {
            if ($scope._asmsJcStandard[i]) {
                $("#standardCode").append("<option value='"+$scope._asmsJcStandard[i].standardName+"'>"+$scope._asmsJcStandard[i].standardName+"</option>")
            }
        }
        $('#criteList').dataTable().api().ajax.reload();
        $("#crite_info").modal('show');
    }; //检测项
    $('#crite_info').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });

    /**
     * grid click
     * */
    $("#parentTaskSelectTocheck").click(function() {
        //点击间隔
        $scope.$apply($scope.btnParenTaskDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnParenTaskDisabled = false);
        }, 500);
        $("#parentTaskGrid").dataTable().api().ajax.reload();
    });
    $("#selectTocheck").click(function() {
        //点击间隔
        $scope.$apply($scope.btnZFDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnZFDisabled = false);
        }, 500);
        $("#selectGrid").dataTable().api().ajax.reload();
    });
    $("#jcSelectTocheck").click(function() {
        //点击间隔
        $scope.$apply($scope.btnJCDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnJCDisabled = false);
        }, 500);
        $("#jcSelectGrid").dataTable().api().ajax.reload();
    });
    $("#sJUnitSelectTocheck").click(function() {
        //点击间隔
        $scope.$apply($scope.btnSJDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnSJDisabled = false);
        }, 500);
        $("#sJUnitTaskGrid").dataTable().api().ajax.reload();
    });
    $("#ToCheck").click(function() {
        //点击间隔
        $scope.$apply($scope.btnProductDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnProductDisabled = false);
        }, 500);
        $("#productList1").dataTable().api().ajax.reload();
    });

    /**
     * button
     * */
    $scope.overTek = function() {
        $("#JC_INFO").hide();
        $("#JB_INFO").show();
    }; //上一步
    $scope.nextstep = function() {
        $("#JC_INFO").show();
        $("#JB_INFO").hide();
    }; //下一步
    $scope.exitOpr = function() {
        //cleanCookie
        window.localStorage.removeItem("asms_checktask_taskinfo_taskid");
        if ($scope.baseInspectionId != undefined && $scope.baseInspectionId != null) {
            $state.go("index.content.asms/baseInspection/inspectionList");
        } else {
            $state.go("index.content.asms/check/checkTask");
        }
    }; //取消
    $scope.selectJCBzButt = function() {
        $scope._checkTaskData.detectionStandards = undefined;
        for (var i = 0; i < $scope._asmsJcStandard.length; i++) {
            if ($scope._asmsJcStandard[i] != undefined) {
                if ($scope._checkTaskData.detectionStandards != undefined) {
                    $scope._checkTaskData.detectionStandards += "," + $scope._asmsJcStandard[i].standardName;
                } else {
                    $scope._checkTaskData.detectionStandards = $scope._asmsJcStandard[i].standardName;
                }
            }
        }
        //去掉可能存在的空格
        $scope._checkTaskData.detectionStandards = $scope._checkTaskData.detectionStandards&&$scope._checkTaskData.detectionStandards.replace(/&nbsp;/ig," ");
        $("#detectionStandard_Model").modal('hide');
    }; //检测标准确定按钮
    //检测标准全选,全不选
    window.checkAllJcStandard = function(checked){
        //移除本页全部检测标准
        $("#detectionStandardTaskGrid input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0; i < $scope._asmsJcStandard.length; i++) {
                if ($scope._asmsJcStandard[i] != undefined && u_d == $scope._asmsJcStandard[i].standardId) {
                    $scope._asmsJcStandard.splice(i,1);
                    i--;
                }
            }
        })
        //添加本页全部检测标准
        if(checked){
            $("#detectionStandardTaskGrid input[name='ck']:checked").each(function(i,o) {
                var map = {
                    standardId: $(o).val(),
                    standardName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope._asmsJcStandard.push(map);
            });
        }
    }
    $scope.selectPDBzButt = function() {
        $scope._checkTaskData.judgeStandards = undefined;
        for (var i = 0; i < $scope._asmsPdStandard.length; i++) {
            if ($scope._asmsPdStandard[i] != undefined) {
                if ($scope._checkTaskData.judgeStandards != undefined) {
                    $scope._checkTaskData.judgeStandards += "," + $scope._asmsPdStandard[i].standardName;
                } else {
                    $scope._checkTaskData.judgeStandards = $scope._asmsPdStandard[i].standardName;
                }
            }
        }
        $scope._checkTaskData.judgeStandards = $scope._checkTaskData.judgeStandards&&$scope._checkTaskData.judgeStandards.replace(/&nbsp;/ig," ");
        $("#judgeStandard_Model").modal('hide');
    }; //判定标准确定按钮
    //判定标准全选,全不选
    window.checkAllPdStandard = function(checked){
        //移除本页全部判定标准
        $("#judgeStandardTaskGrid input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0; i < $scope._asmsPdStandard.length; i++) {
                if ($scope._asmsPdStandard[i] != undefined && u_d == $scope._asmsPdStandard[i].standardId) {
                    $scope._asmsPdStandard.splice(i,1);
                    i--;
                }
            }
        })
        //全选本页判定标准
        if(checked){
            $("#judgeStandardTaskGrid input[name='ck']:checked").each(function(i,o) {
                var map = {
                    standardId: $(o).val(),
                    standardName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope._asmsPdStandard.push(map);
            });
        }
    }
    $scope.selectSzDwButt = function() {
        $scope.monitorobject.szUnits = undefined;
        for (var i = 0; i < $scope._sjUnit.length; i++) {
            if ($scope._sjUnit[i] != undefined) {
                if ($scope.monitorobject.szUnits != undefined) {
                    $scope.monitorobject.szUnits += "," + $scope._sjUnit[i].enterpriseName;
                } else {
                    $scope.monitorobject.szUnits = $scope._sjUnit[i].enterpriseName;
                }
            }
        }
        $("#sJUnit_Model").modal('hide');
    }; //受检单位确定按钮
    $scope.selectCriteButt = function() {
        $scope.monitorobject.crites = undefined;
        for (var i = 0; i < $scope._asmsCheckObjectCriterion.length; i++) {
            if ($scope._asmsCheckObjectCriterion[i] != undefined) {
                if ($scope.monitorobject.crites != undefined) {
                    $scope.monitorobject.crites += "," + $scope._asmsCheckObjectCriterion[i].criterionName;
                } else {
                    $scope.monitorobject.crites = $scope._asmsCheckObjectCriterion[i].criterionName;
                }
            }
        }
        $("#crite_info").modal('hide');
    }; //检测项确定按钮


    /**
     * function
     * */
    $scope.infoSave = function() {
        delete $scope._checkTaskData.createBy;
        delete $scope._checkTaskData.createTime;
        delete $scope._checkTaskData.updateBy;
        delete $scope._checkTaskData.updateTime;
        delete $scope._checkTaskData.enable;
        delete $scope._checkTaskData.delFlag;
        if ($scope._parentTask != undefined) {
            $scope._checkTaskData.parentTaskId = $scope._parentTask[0].parentTaskId;
            $scope._checkTaskData.parentTaskName = $scope._parentTask[0].parentTaskName;
        } //父级任务
        $scope._checkTaskData.token = $scope.userToken;
        $scope._checkTaskData.id = $scope.taskId;
        $scope._checkTaskData.taskYear = $("input[name='taskyear']").val();
        $scope._checkTaskData.taskIndustry = $("#taskIndustry").val();
        $scope._checkTaskData.baseInspectionId = $scope.baseInspectionId;
        $scope._checkTaskData.taskType = '监督抽查';
        if ($("#xian").val() == null || $("#xian").val() == "") {
            if ($("#shi").val() == null || $("#shi").val() == "") {
                if ($("#sheng").val() == null || $("#sheng").val() == "") {
                    $.jBox.tip("请选择区域！", "info");
                } else {
                    $scope._checkTaskData.taskAreaId = $("#sheng").val() + "0000";
                }
            } else {
                $scope._checkTaskData.taskAreaId = $("#shi").val() + "00";
            }
        } else {
            $scope._checkTaskData.taskAreaId = $("#xian").val();
        }
        if ($("input[name='taskyear']").val() == null || $("input[name='taskyear']").val() == "") {
            $.jBox.tip("年度不能为空！");
            return false;
        }
        if ($("input[name='taskBeginTime']").val() == null || $("input[name='taskBeginTime']").val() == "") {
            $.jBox.tip("任务开始时间不能为空！");
            return false;
        }
        if ($("input[name='taskEndTime']").val() == null || $("input[name='taskEndTime']").val() == "") {
            $.jBox.tip("任务结束时间不能为空！");
            return false;
        }
        if ($("input[name='taskSampleDeadline']").val() == null || $("input[name='taskSampleDeadline']").val() == "") {
            $.jBox.tip("抽样截止时间不能为空！");
            return false;
        }
        if ($("input[name='taskName']").val() == null || $("input[name='taskName']").val() == "") {
            $.jBox.tip("任务名称不能为空！");
            return false;
        }
        if ($("input[name='cyUnit']").val() == null || $("input[name='cyUnit']").val() == "") {
            $.jBox.tip("抽样单位不能为空！");
            return false;
        }
        if ($("input[name='jcUnit']").val() == null || $("input[name='jcUnit']").val() == "") {
            $.jBox.tip("检测单位不能为空！");
            return false;
        }
        if ($("input[name='taskReleaseUnit']").val() == null || $("input[name='taskReleaseUnit']").val() == "") {
            $.jBox.tip("抽样组织单位单位不能为空！");
            return false;
        }
        if ($scope._checkTaskData.taskIndustry == null || $scope._checkTaskData.taskIndustry == "") {
            $.jBox.tip("行业不能为空！");
            return false;
        }
        if ($("input[name='detectionStandards']").val() == null || $("input[name='detectionStandards']").val() == "") {
            $.jBox.tip("检测标准不能为空！");
            return false;
        }
        if ($("input[name='judgeStandards']").val() == null || $("input[name='judgeStandards']").val() == "") {
            $.jBox.tip("判定标准不能为空！");
            return false;
        }
        if(!vm.files[0].src){
            $.jBox.tip("附件为必传！");
            return false;
        }
        $scope._checkTaskData.taskBeginTime = new Date(($("input[name='taskBeginTime']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope._checkTaskData.taskEndTime = new Date(($("input[name='taskEndTime']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope._checkTaskData.taskSampleDeadline = new Date(($("input[name='taskSampleDeadline']").val() + " 00:00:00").replace(/-/g, "/"));
        var start = Date.parse($scope._checkTaskData.taskBeginTime);
        var end = Date.parse($scope._checkTaskData.taskEndTime);
        var mid = Date.parse($scope._checkTaskData.taskSampleDeadline);
        if (mid < start || mid > end) {
            $.jBox.tip("截止时间必须在任务时间范围！");
            $scope._checkTaskData.taskBeginTime = $("input[name='taskBeginTime']").val();
            $scope._checkTaskData.taskEndTime = $("input[name='taskEndTime']").val();
            $scope._checkTaskData.taskSampleDeadline = $("input[name='taskSampleDeadline']").val();
            return false;
        }
        $scope._checkTaskData.taskReleaseUnit = $("input[name='taskReleaseUnit']").val();
        $scope._checkTaskData.jcUnitName = $scope.jcSelects[0].leadUnitName;
        $scope._checkTaskData.jcUnitId = $scope.jcSelects[0].leadUnitId;
        $scope._checkTaskData.cyUnitName = $scope.selects[0].leadUnitName;
        $scope._checkTaskData.cyUnitId = $scope.selects[0].leadUnitId;
        if($scope.jdUnits.length>0&&!$scope.jdUnits[0]){
            $scope._checkTaskData.jdUnitName = $scope.jdUnits[0].leadUnitName;
            $scope._checkTaskData.jdUnitId = $scope.jdUnits[0].leadUnitId;
        }

        $scope._checkTaskData.attachmentAddress = vm.files[0].src;
        $scope._checkTaskData.attachmentName = vm.files[0].name;
        $scope._checkTaskData._asmsJcStandard = JSON.stringify($scope._asmsJcStandard);
        $scope._checkTaskData._asmsJcStandard = $scope._checkTaskData._asmsJcStandard&&$scope._checkTaskData._asmsJcStandard.replace(/&nbsp;/ig," ");
        $scope._checkTaskData._asmsPdStandard = JSON.stringify($scope._asmsPdStandard);
        $scope._checkTaskData._asmsPdStandard = $scope._checkTaskData._asmsPdStandard&&$scope._checkTaskData._asmsPdStandard.replace(/&nbsp;/ig," ");
        $scope._checkTaskData.taskIsSeparate = "1"; //抽检分离写死为1表示分离
        if ($scope.taskId != null || $scope.taskId != undefined) {
            $("#myForm button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/checkTask/update",
                type: "post",
                dataType: "json",
                data: $scope._checkTaskData,
                headers:{
                    token:window.localStorage.getItem("token")
                },
                success: function(data) {
                    $("input[name='taskBeginTime']").val(format($scope._checkTaskData.taskBeginTime, 'yyyy-MM-dd'));
                    $("input[name='taskEndTime']").val(format($scope._checkTaskData.taskEndTime, 'yyyy-MM-dd'));
                    $("input[name='taskSampleDeadline']").val(format($scope._checkTaskData.taskSampleDeadline, 'yyyy-MM-dd'));
                    $("#myForm button").attr("disabled", false);
                    $.jBox.tip("成功！");
                },
                error: function(data) {
                    $("#myForm button").attr("disabled", false);
                    $.jBox.tip("失败");
                }
            });
        } else {
            $("#myForm button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/checkTask/add",
                type: "post",
                dataType: "json",
                data: $scope._checkTaskData,
                headers:{
                    token:window.localStorage.getItem("token")
                },
                success: function(data) {
                    if(data.httpCode == "418"){
                        $("#myForm button").attr("disabled", false);
                        $.jBox.tip("新增失败, 任务数被限制！");
                    } else {
                        $("input[name='taskBeginTime']").val(format($scope._checkTaskData.taskBeginTime, 'yyyy-MM-dd'));
                        $("input[name='taskEndTime']").val(format($scope._checkTaskData.taskEndTime, 'yyyy-MM-dd'));
                        $("input[name='taskSampleDeadline']").val(format($scope._checkTaskData.taskSampleDeadline, 'yyyy-MM-dd'));
                        $("#myForm button").attr("disabled", false);
                        $scope.taskId = data.data;
                        $.jBox.tip("成功！");
                        $("button[name='nextStepButton']").show();
                    }
                },
                error: function(data) {
                    $("#myForm button").attr("disabled", false);
                    $.jBox.tip("失败");
                }
            });
        }
    }; //任务保存
    window.checkThisBox = function(o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };
    $scope.addObj = function() {
        $scope.monitorobject = {};
        for(var i=0;i<$scope.industryType.length;i++){
            if($scope._checkTaskData.taskIndustry==$scope.industryType[i].id){
                $scope.monitorobject.industry =$scope.industryType[i].dictValue;
            }
        }
        $scope._sjUnit = null;
        $scope._sjUnit = new Array();
        $scope._asmsCheckObjectCriterion = null;
        $scope._asmsCheckObjectCriterion = new Array();
        //默认选中该任务所选行业
        if ($scope._userOrgData.regionId == null || $scope._userOrgData.regionId == undefined || $scope._userOrgData.regionId == "") {
            $.jBox.tip("初始数据获取异常,请重新登陆！", "error");
            return;
        }
        $.fn.mycity("objsheng", "objshi", "objxian", $scope._checkTaskData.taskAreaId);
        $("#MOB_INFO_MODEL").modal('show');
    }; //监测对象新增
    $scope.updObj = function() {
        var rows = $("#JC_DX_LIST").DataTable().rows('.selected').data();
        if (rows.length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $("#MOB_INFO_MODEL").modal('show');
        var objId = rows[0].id;
        $http({
            url: "/sofn-asms-web/checkTask/getObjByObjId",
            headers:{
                token:window.localStorage.getItem("token")
            },
            method: "post",
            data: { id: objId }
        }).success(function(obj) {
            $scope.monitorobject = {};
            $scope.monitorobject = obj.data;
            $.fn.mycity("objsheng", "objshi", "objxian", obj.data.areaId);
            var ents = obj.ents;
            $scope._sjUnit = null; //受检单位
            $scope._sjUnit = new Array();
            if (ents.length != 0) {
                $scope.monitorobject.szUnits = undefined;
                for (var i = 0; i < ents.length; i++) {
                    var map = {
                        enterpriseId: ents[i].enterpriseId,
                        enterpriseName: ents[i].enterpriseName
                    };
                    $scope._sjUnit.push(map);
                    if ($scope.monitorobject.szUnits != undefined) {
                        $scope.monitorobject.szUnits += "," + ents[i].enterpriseName;
                    } else {
                        $scope.monitorobject.szUnits = ents[i].enterpriseName;
                    }
                }
            }
            //检测项目
            var crits = obj.crits;
            $scope._asmsCheckObjectCriterion = null;
            $scope._asmsCheckObjectCriterion = new Array();
            if (crits.length != 0) {
                $scope.monitorobject.crites = undefined;
                for (var i = 0; i < crits.length; i++) {
                    var map = {
                        criterionId: crits[i].criterionId,
                        criterionName: crits[i].criterionName
                    };
                    $scope._asmsCheckObjectCriterion.push(map);
                    if ($scope.monitorobject.crites != undefined) {
                        $scope.monitorobject.crites += "," + crits[i].criterionName;
                    } else {
                        $scope.monitorobject.crites = crits[i].criterionName;
                    }
                }
            }
        }).error(function() {});
    }; //监测对象修改
    $scope.delObj = function() {
        var rows = $("#JC_DX_LIST").DataTable().rows('.selected').data();
        if (rows.length < 1) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        $.jBox.confirm("是否删除已选监测对象？", "等待服务器响应", function(v) {
            if (v == true) {
                var ids = [];
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }
                $http({
                    url: "/sofn-asms-web/checkTask/delObj",
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    method: "post",
                    data: { id: JSON.stringify(ids) }
                }).success(function(obj) {
                    $("#web_app_model_userOprXz").modal("hide");
                    $("#JC_DX_LIST").dataTable().api().ajax.reload();
                }).error(function(data) {
                    
                });

            }
            return true;
        }, { buttons: { '确认': true, '取消': false } });
    }; //检测对象批量删除
    $scope.saveObj = function() {
        $("#saveObjBtn").prop("disabled",true);
        delete $scope.monitorobject.createBy;
        delete $scope.monitorobject.createTime;
        delete $scope.monitorobject.updateBy;
        delete $scope.monitorobject.updateTime;
        delete $scope.monitorobject.enable;
        delete $scope.monitorobject.delFlag;
        delete $scope.monitorobject.taskBeginTime;
        delete $scope.monitorobject.taskEndTime;
        //所属任务
        $scope.monitorobject.superviseCheckTaskId = $scope.taskId;
        if ($("#objxian").val() == null || $("#objxian").val() == "") {
            if ($("#objshi").val() == null || $("#objshi").val() == "") {
                if ($("#objsheng").val() == null || $("#objsheng").val() == "") {
                    $.jBox.tip("请选择区域！", "info");
                } else {
                    $scope.monitorobject.areaId = $("#objsheng").val() + "0000";
                }
            } else {
                $scope.monitorobject.areaId = $("#objshi").val() + "00";
            }
        } else {
            $scope.monitorobject.areaId = $("#objxian").val();
        }
        $scope.monitorobject.szUnits = JSON.stringify($scope._sjUnit);
        $scope.monitorobject.crits = JSON.stringify($scope._asmsCheckObjectCriterion);
        if ($scope.monitorobject.id == null || $scope.monitorobject.id == undefined) {
            $.ajax({
                url: "/sofn-asms-web/checkTask/addObj",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.monitorobject,
                success: function(data) {
                    if (data) {
                        $.jBox.tip("成功！");
                    } else {
                        $.jBox.tip("失败！");
                    }
                    $("#MOB_INFO_MODEL").modal("hide");
                    $("#JC_DX_LIST").dataTable().api().ajax.reload();
                },
                error: function(data) {
                    $.jBox.tip("失败");
                }
            });
        } else {
            $.ajax({
                url: "/sofn-asms-web/checkTask/updateObj",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.monitorobject,
                success: function(data) {
                    if (data) {
                        $.jBox.tip("成功！");
                    } else {
                        $.jBox.tip("失败！");
                    }
                    $("#MOB_INFO_MODEL").modal("hide");
                    $("#JC_DX_LIST").dataTable().api().ajax.reload();
                },
                error: function(data) {
                    $.jBox.tip("失败");
                }
            });
        }
        $('#saveObjBtn').removeAttr('disabled');
    }; //监测对象保存
    $scope.findProductInfo = function() {
        if ($scope.monitorobject.industry == null || $scope.monitorobject.industry == "") {
            jBox.tip("请先选择所属行业", 'info');
            return;
        }
        $("#product_info").modal('show');
        var tableName = $('#productList1');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#productList1").dataTable({
            fnDrawCallback: function() {
                $("#productList1 tbody tr td").each(function(i, o) {
                    $(o).click(function() {
                        var name = $(this).parent().find("[name='ProName']").text();
                        var type = $(this).parent().find("[name='ProType']").text();
                        $scope.$apply(function() {
                            $scope.monitorobject.productName = name;
                            $scope.monitorobject.productType = type;
                        });
                        $("#product_info").modal('hide');
                    });
                })
            },
            ajax: {
                url: "/sofn-asms-web/checkTask/getProList",
                dataSrc: function(json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function(params) {
                    params.industry = $scope.monitorobject.industry;
                    params.keyWord = $("input[name='keyWord']").val();
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                data: "name",
                title: "名称",
                render: function(data, type, row, meta) {
                    return '<span name = "ProName">' + data + '</span>';
                }
            }, {
                data: "parentName",
                title: "类型",
                render: function(data, type, row, meta) {
                    return '<span name = "ProType">' + data + '</span>';
                }
            }]
        }).on('shown.bs.tab', function(e) {
            $.fn.dataTable.tables({ visible: true, api: true }).columns.adjust();
        });

    }; //产品选择列表
    $scope.typeClean = function() {
        // $scope.$apply(function () {
        $scope.monitorobject.productName = null;
        $scope.monitorobject.productType = null;
        //清空检测项显示
        $scope.monitorobject.crites = null;
        //清空检测项
        $scope._asmsCheckObjectCriterion = [];
        // });
    }; //产品所属行业重新选择


    var format = function(time, format) {
        var t = new Date(time);
        var tf = function(i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        });
    }; //中国时间转标准时间

    //抽样单位查询条件重置
    $scope.cleanSelectModelQuery = function () {
        //点击间隔
        $scope.btnZFReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnZFReloadDisabled = false);
        }, 500);
        $("input[name='selectQueryName']").val("");
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", $scope.regionId);
        $("#selectGrid").dataTable().api().ajax.reload();
    };
    //受检单位查询条件重置
    $scope.cleanSjUnitModelQuery = function () {
        //点击间隔
        $scope.btnSJReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnSJReloadDisabled = false);
        }, 500);
        $("input[name='entIdCode']").val("");
        $("input[name='sjUnitName']").val("");
        $.fn.mycity("sjUnitShen", "sjUnitShi", "sjUnitXian", $scope.regionId);
        $("#selectGrid").dataTable().api().ajax.reload();
    };
    //上级任务
    $scope.cleanSelectModel = function(){
        $("input[name='parentTaskName']").val("");
        //上级任务的年度时间不可修改
        $("input[name='taskyear']").val("");
        $("input[name='taskyear']").removeProp("disabled");
        //上级任务时间显示可以修改
        $("input[name='taskBeginTime']").val("");
        $("input[name='taskEndTime']").val("");
        $scope._checkTaskData.parentTaskName = "";
        $scope._parentTask = null;
    }
    //检测单位查询条件重置
    $scope.cleanJcSelectModelQuery = function () {
        //点击间隔
        $scope.btnJCReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnJCReloadDisabled = false);
        }, 500);
        $("input[name='jcSelectQueryName']").val("");
        $.fn.mycity("jcSelectQueryShen", "jcSelectQueryShi", "jcSelectQueryXian", $scope.regionId);
        $("#jcSelectGrid").dataTable().api().ajax.reload();
    };

    var vm = this;

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-asms-web/asmsFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        accept:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,image/*,application/pdf',
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };
    $("#standardCode").change(function(){
        //点击间隔
        $scope.btnStandardCodeDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnStandardCodeDisabled = false);
        }, 500);
        $("#criteList").dataTable().api().ajax.reload();
    })

    $scope.queryCheckItem = function(){
        //点击间隔
        $scope.btnCheckItemDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnCheckItemDisabled = false);
        }, 500);
        $("#criteList").dataTable().api().ajax.reload();
    }

});
