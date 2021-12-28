var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("samplePlanList", function($scope, $http, $state,$timeout) {
        var vm =this;
        $scope.taskid = '';
        $scope.authorityArray = window.localStorage.getItem("menus");
      /*  var authorityArray = JSON.parse($scope.authorityArray);
        var author = "";
        for (var i = 0; i < authorityArray.length; i++) {
            if (authorityArray[i].id == "516") {
                if (authorityArray[i].authority == "1") {
                    author = authorityArray[i].authority;
                    $("#newId").attr("disabled", true);
                    $("#produceId").attr("disabled", true);
                    $("#delId").attr("disabled", true);
                    break;
                }
            }
        }*/
        $scope.dailyEnforceId = "";
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/llesEntrustDetection/getCreateBy",
            data: "2",
            method: "post"
        })
        .success(function(data) {
            $scope.dailyEnforceId = data.data;
        })
        .error(function(msg) {
            
        });
        //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
        $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
            for (
                var i = 0;
                i < $("input[name='" + limitCheckBoxName + "']").length;
                i++
            ) {
                var lim = $("input[name='" + limitCheckBoxName + "']")[i]
                    .checked;
                if (lim) {
                    $("input[name='" + checkBoxName + "']:checked").attr(
                        "checked",
                        false
                    );
                }
            }
        };
        loadGrid();
        /**
         * 重置查询参数,默认显示用户所属区域内行政处罚信息
         * 注释日期:2018年2月26日10:17:17
         */
        $scope.reset = function() {
            $("input[name='planName']").val("");
            $("input[name='taskName']").val("");
            $("select[name='planSource']").val("");
            $("#grid").dataTable().api().ajax.reload();
        };
        /**
         * 点击查询按钮刷新datatable
         * 注释日期:2018年2月26日10:17:00
         */
        $("#ToCheck").click(function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        });
        /**
         * 默认查询绑定回车事件。
         * 注释日期:2018年2月26日10:16:45
         */
        $("#queryCon").keydown(function(e) {
            if (e.keyCode == 13) {
                $("#grid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            }
        });
      //抽样计划list
        function loadGrid() {
            $("#grid").dataTable({
               /* ordering: true, //开启排序
                order: [[7, "asc"]], //默认排序项
                scrollX: true, //横向滚动*/
                fnDrawCallback: function() {
                    //点击行内任意内容即选择行
                    $("#grid tbody tr td").each(function(i, o) {
                        $(o).click(function() {
                            var checked = $(this)
                                .parent()
                                .find("input[type='checkbox']")[0];
                            if (checked.checked) {
                                checked.checked = false;
                                $(this)
                                    .parent()
                                    .removeClass("selected");
                                //移除
                                var u_d = $(this)
                                    .parent()
                                    .find("[name='ck']")
                                    .val();
                            } else {
                                checked.checked = true;
                                $(this)
                                    .parent()
                                    .addClass("selected");
                            }
                        });
                    });
                },
                ajax: {
                    url:
                        "/sofn-ales-web/samplePlan/querySamplePlan",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function(json) {
                        if(json.code == 400){
                        $.jBox.tip("新增失败！！！");
                        }
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        for (var i = 0; i < json.data.list.length; i++) {
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
                                    json.data.list[i].STATE = "执行中";
                                }
                                if (json.data.list[i].STATE == "3" || json.data.list[i].STATE == "4") {
                                    json.data.list[i].STATE = "已结束";
                                }
                            }
                        }
                        $scope.$apply(function() {
                            $scope.tableTotal = json.data.total;
                        });
                        return json.data.list;
                    },
                    error: function(msg) {
                        console.log(msg);
                        
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function(params) {
                        params.xcPsersionId = window.localStorage.getItem(
                            "xcPsersionId",
                            "xcPsersionId"
                        );
                        params.planName = $("input[name='planName']").val();
                        params.taskName = $("input[name='taskName']").val();
                        params.state = $(
                                "select[name='planSource']"
                        ).val();
                    },
                    type: "post"
                },
                columns: [
                    //选择按钮
                    {
                        data: "ck",
                        title: "选择",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return (
                                '<input class="selectZiyuan" name="selectZiyuan" type="checkbox" style="cursor: pointer;width:15px;height:15px" value="' +
                                row.ID +
                                '" onclick="checkClick(this)" />'
                            );
                        },
                        width: "3%",
                        orderable: false
                    },
                    {
                        //data : "RN",
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        width: "5%",
                        orderable: false
                    },
                    {
                        data: "PLAN_NAME",
                        title: "计划名称",
                        render: function(data, type, row) {
                            return data;
                        },
                        width: "7%"
                    },
                    {
                        data: "RELEVANCE_TASK_NAME",
                        title: "任务名称",
                        render: function(data, type, row, meta) {
                            if (data) {
                                var showdata = data;
                                if (data.length > 10) {
                                    showdata = data.substring(0, 40) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        data +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                        },
                        width: "10%"
                    },
                    {
                        data: "STATE",
                        title: "状态",
                        render: function(data, type, row, meta) {
                            if (data) {
                                var showdata = data;
                                if (data.length > 10) {
                                    showdata = data.substring(0, 10) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        data +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                        },
                        width: "10%"
                    },

                    {
                        data: "ID",
                        title: "操作",
                        render: function(data, type, row) {
                                if(row.STATE!= "未发布"){
                                    return"计划已发布/执行中不可操作"/*(
                                        '<a type="button" style="cursor:pointer;" ' + ' onclick="updateInfo(\'' + data + '\',\'' + row.STATE + '\')">修改</a> ' +
                                        '<a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\'' + data + '\',\'' + row.RELEVANCE_TASK_ID + '\')">配置</a>')
*/
                                }
                                return (
                                    '<a type="button" style="cursor:pointer;" ' + ' onclick="updateInfo(\'' + data + '\',\'' + row.STATE + '\')">修改</a> ' +
                                    '<a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\'' + data + '\',\'' + row.RELEVANCE_TASK_ID + '\')">配置</a>')
                        },
                        width: "10%",
                        orderable: false
                    }
                ]
            });
        }
        //临时处理
        window.checkClick = function(ck) {
            console.log("选择的ID号=：" + ck);
            $(
                    $(ck)
                    .parent()
                    .parent()[0]
            ).toggleClass("selected");
            var e = window.event || arguments.callee.caller.arguments[0];
            if (e && e.stopPropagation) {
                e.stopPropagation();
            } else {
                window.event.cancelBubble = true;
            }
        };
        $scope.selectModalQdButt = function () {
            $('#selectGrid').dataTable().api().ajax.reload();
            $("#selectModal").modal('show');
        };
        //表格
        $("#selectGrid").dataTable({
            fnDrawCallback: function () {
                $("#selectGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        $scope.selects = null;
                        $scope.selects = new Array();
                        var map = {
                            leadUnitId: $(this).parent().find("[name='id']").val(),
                            leadUnitName: $(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                        var name = "";
                        for (var i = 0; i < $scope.selects.length; i++) {
                            console.log($scope.selects[i]);
                            console.log($scope.selects[i].leadUnitId);
                            if ($scope.selects[i] != undefined) {
                                if (name != "") {
                                    name += "," + $scope.selects[i].leadUnitName;
                                } else {
                                    name = $scope.selects[i].leadUnitName;
                                    $scope.taskid = $scope.selects[i].leadUnitId;
                                }
                            }
                        }
                        $("input[name='taskNames']").val(name);
                        $("#selectModal").modal('hide');
                    });
                })
            },
            "paging": false,//禁止分页
            "info": false,//禁止显示信息
            ajax: {
                url: "/sofn-ales-web/alesmynewchecktask/getCheckTaskByUser",
                dataSrc: function (json) {

                    return json.data;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                },
                type: "post"
            },
            columns: [
                {
                    data: 'ID',
                    render: function (data, type, row, meta) {
                        return '<input type="hidden" name="id" value = "' + data + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "TASK_NAME",
                    title: "任务名称",
                    render: function (data, type, row) {
                        return '<span name = "name">' + data + '</span>';
                    }
                },

            ]
        });

     //新增计划
        $scope.addPlan = function () {
            var planName = $("input[name='planNames']").val();
            var taskName = $("input[name='taskNames']").val();
            if(taskName ==""||taskName == null || taskName == undefined){
                $.jBox.tip("计划信息填写不完整！");
                return false
            }
            if(planName ==""||planName == null || planName == undefined){
                $.jBox.tip("计划信息填写不完整！");
                return false
            }
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/samplePlan/addPlan",
                method: "post",
                params: { planName: planName, relevanceTaskName:taskName,relevanceTaskId:$scope.taskid}
            }).success(function(data) {
                if(data.code == 400){
                    $.jBox.tip("该"+taskName+"已有抽样计划！");
                }
                $("#myModal").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
            })
            .error(function(data) {
                
            });
        }

        window.GotoUpdateWindow = function(data,taskId) {
            window.localStorage.setItem("samplanlist_planConfigurtion_keyid",data);
            window.localStorage.setItem("samplanlist_planConfigurtion_keytaskId",taskId);
            $state.go("index.content.ales/taskManage/samplePlan/planConfiguration");
        };
        //查看计划信息
        $scope.PlanId ="";
        $scope.relevanceTaskId ="";
        window.Checkreport = function(data) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/samplePlan/samplePlanInfo",
                method: "post",
                params: { id: data }
            }).success(function(data) {
                $scope.planInfo = data.alesSamplePlan;
                $scope.planNames = $scope.planInfo.planName;
                $scope.taskNames = $scope.planInfo.relevanceTaskName;
                $scope.PlanId   = $scope.planInfo.id;
                $scope.relevanceTaskId   = $scope.planInfo.relevanceTaskId;
                })
                .error(function(data) {
                    
                });
        };
        //验证计划状态
        window.updateInfo =function(data,state){
            if(state != "未发布"){
                $.jBox.tip("只能修改未发布计划！");
                return false;
            }else {
                $("#myModals").modal("show");
            }
            window.Checkreport(data);
        }
        //修改计划
        $scope.updatePlan = function(data,state) {
            data =$scope.PlanId;
            var planName = $("input[name='planNames']").val();
            var taskName = $("input[name='taskNames']").val();
            var relevance_TaskId ='';
            if($scope.taskid != null && $scope.taskid != "" && $scope.taskid != undefined){
                relevance_TaskId =   $scope.taskid;
                $scope.taskid = null;
            }else {
                relevance_TaskId =  $scope.relevanceTaskId;
            }
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/samplePlan/updatePlan",
                method: "post",
                params: { id:data,planName: planName, relevanceTaskName:taskName,relevanceTaskId:relevance_TaskId}
            }).success(function(data) {
                $("#myModals").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
                })
                .error(function(data) {
                    
                });
        };
        //删除抽样计划
        $scope.delPlan = function() {
            var rows = $("#grid").DataTable().rows(".selected").data();
            var length = rows.length;
            if (length == 0) {
                $.jBox.tip("请选择一行记录！");
                return false;
            }
            for (var k = 0; k < length; k++) {
                if (rows[k].STATE != "未发布") {
                    console.log(rows[k].STATE);
                    $.jBox.tip("删除操作仅限于未发布任务！");
                    return false;
                }
                if(rows[k].CREATE_BY == null){
                    $.jBox.tip("只能制定者才能做删除操作！！");
                    return false;
                }
                if(rows[k].CREATE_BY != $scope.dailyEnforceId){
                    $.jBox.tip("只能制定者才能做删除操作！！");
                    return false;
                }
            }
            var ids = [];
            for (var i = 0; i < length; i++) {
                ids.push(rows[i].ID);
            }
            if (rows != false) {
                $.jBox.confirm(
                    "是否删除已选择行抽样计划？",
                    "等待服务器响应",
                    function(v) {
                        if (v == true) {
                            var data = {
                                jsonString: JSON.stringify(ids)
                            };
                            $.ajax({
                                headers: {
                                    token: window.localStorage.getItem("token")
                                },
                                url: "/sofn-ales-web/samplePlan/delPlan",
                                type: "post",
                                dataType: "json",
                                data: data,
                                beforeSend: function() {
                                    $("#web_app_model_userOprXz").modal({
                                        backdrop: "static",
                                        keyboard: false
                                    }); //禁止空白处点击与键盘操作关闭
                                    $("#web_app_model_userOprXz").modal("show");
                                },
                                success: function(data) {
                                    $("#web_app_model_userOprXz").modal("hide");
                                    $.jBox.tip("成功！");
                                    $("#grid").dataTable().api().ajax.reload();
                                },
                                fail: function(data) {
                                    $("#web_app_model_userOprXz").modal("hide");
                                    $.jBox.tip("失败");
                                }
                            }).error(function(data) {
                                console.log(data);
                                
                            });
                        }
                        return true;
                    },
                    { buttons: { 确认: true, 取消: false } }
                );
            }
        };
        //获取选择行记录id集合
        $scope.getSelectRowsIds = function() {
            var rows = $("#grid").DataTable().rows(".selected").data();
            var length = rows.length;
            if (length <= 0) {
                $.jBox.tip("请至少选择一行记录！");
                return false;
            }
            var ids = [];
            for (var i = 0; i < length; i++) {
                console.log(rows[i].STATE);
                ids.push(rows[i]);
            }
            return ids;
        };
    //发布计划
    $scope.issue = function () {
        var i = $scope.getSelectRowsIds();

        for( var k =0 ;k < i.length; k++){
            console.log(i[k].STATE);
            if(i[k].STATE != "未发布"){
                $.jBox.tip("只能发布未发布计划");
                return false;
            }
            if(i[k].CREATE_BY == null){
                $.jBox.tip("只能制定者才能发布计划！！");
                return false;
            }
            if(i[k].CREATE_BY != $scope.dailyEnforceId){
                $.jBox.tip("只能制定者才能发布计划！！");
                return false;
            }
        }
        if (i != false) {
            $.jBox.confirm(
                    "是否批量发布已选择任务？",
                    "等待服务器响应",
                    function(v) {
                        if (v == true) {
                            var data = {
                                jsonString: JSON.stringify(i)
                            };
                            $("button").attr("disabled", true);
                            $.ajax({
                                url: "/sofn-ales-web/samplePlan/issuePlan",
                                headers: {
                                    token: window.localStorage.getItem("token")
                                },
                                type: "post",
                                dataType: "json",
                                data: data,
                                beforeSend: function() {
                                    $("#web_app_model_userOprXz").modal({
                                        backdrop: "static",
                                        keyboard: false
                                    });
                                    $("#web_app_model_userOprXz").modal("show");
                                },
                               
                                success: function(data) {
                                    $("button").attr("disabled", false);
                                    $("#web_app_model_userOprXz").modal("hide");
                                    console.log(data.data.success);
                                    if (data.data.success) {
                                        $("#grid").dataTable().api().ajax.reload();
                                        $.jBox.tip("成功", "info");
                                    }else {
                                        $("#grid").dataTable().api().ajax.reload();
                                        $.jBox.tip("有计划未配置任务！");
                                    }
                                },
                                fail: function(data) {
                                    $("button").attr("disabled", false);
                                    $("#web_app_model_userOprXz").modal("hide");
                                    $.jBox.tip("失败", "error");
                                }
                            });
                        }
                        return true;
                    },
                    { buttons: { 确认: true, 取消: false } }
            );
        }
    }
        $scope.add = function () {
            $scope.planNames = null;
            $scope.taskNames = null;
            $("#myModal").modal("show").on('show.bs.modal',function (e) {
                $("input[name='planNames']").val("");
                $("input[name='taskNames']").val("");
            });

        }


    });
