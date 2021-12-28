var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("sampleTaskList", function($scope, $http, $state,$timeout) {
        var vm =this;
        $scope.authorityArray = window.localStorage.getItem("menus");
        var authorityArray = JSON.parse($scope.authorityArray);
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
        }
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
         * 获取当前登录用户Id
         * 注释日期:2018年2月26日10:17:32
         */
        $scope.linePoliticsUserId = "";
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/llesEntrustDetection/getCreateBy",
            data: "2",
            method: "post"
        })
            .success(function(data) {
                $scope.linePoliticsUserId = data.data;
            })
            .error(function(data) {
            });
        /**
         * 重置查询参数,默认显示用户所属区域内行政处罚信息
         * 注释日期:2018年2月26日10:17:17
         */
        $scope.reset = function() {
            $("input[name='planName']").val("");
            $("input[name='sampleUser']").val("");
            $("input[name='taskName']").val("");
            $("select[name='taskSource']").val("");
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
        //抽样任务list
        function loadGrid() {
            $("#grid").dataTable({
            /*    ordering: true, //开启排序
                order: [[7, "asc"]], //默认排序项
                scrollX: true, //横向滚动*/
                fnDrawCallback: function() {
                    //点击行内任意内容即选择行
              /*      $("#grid tbody tr td").each(function(i, o) {
                        $(o).click(function() {
                            var checked = $(this)
                                .parent()
                                .find("input[type='radio']")[0];
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
                    });*/
                },
                ajax: {
                    url:
                        "/sofn-ales-web/sampleTask/querySampleTask",
                    dataSrc: function(json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        for (var i = 0; i < json.data.list.length; i++) {
                            if (
                                json.data.list[i].TASK_BEGIN_TIME != null &&
                                json.data.list[i].TASK_BEGIN_TIME != "" &&
                                json.data.list[i].TASK_BEGIN_TIME != undefined
                            ) {
                                json.data.list[i].TASK_BEGIN_TIME = new Date(
                                    parseInt(
                                        json.data.list[i].TASK_BEGIN_TIME) + 28800000).toISOString().slice(0, 10);
                            }
                            if (
                                json.data.list[i].TASK_END_TIME != null &&
                                json.data.list[i].TASK_END_TIME != "" &&
                                json.data.list[i].TASK_END_TIME != undefined
                            ) {
                                json.data.list[i].TASK_END_TIME = new Date(parseInt(json.data.list[i].TASK_END_TIME) + 28800000).toISOString().slice(0, 10);
                            }
                        }
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
                                if (json.data.list[i].STATE == "3") {
                                    json.data.list[i].STATE = "结束";
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
                        params.sampleUser = $("input[name='sampleUser']").val();
                        params.state = $(
                                "select[name='taskSource']"
                        ).val();
                    },
                    type: "post"
                },
                columns: [

                 /*   { //选择按钮
                        data: "ck",
                        title: "选择",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return (
                                '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="' +
                                row.ID +
                                '" onclick="checkClick(this)" />'
                            );
                        },
                        width: "3%",
                        orderable: false
                    },*/
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
                            // 模板化列显示内容
                           // return data.substring(0, 4);
                            return data;
                        },
                        width: "7%"
                    },
                    {
                        data: "TASK_NAME",
                        title: "任务名称",
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
                        data: "SAMPLE_USER",
                        title: "抽样人员",
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
                        data: "TASK_BEGIN_TIME",
                        title: "开始时间",
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
                        data: "TASK_END_TIME",
                        title: "结束时间",
                        render: function(data, type, row) {
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
                            // 模板化列显示内容

                                return (
                                    '<a type="button"  ' + 'onclick="GotoTaskWindow(\'' +row.STATE + '\',\'' +row.PLAN_ID + '\',\''+row.TASK_NAME + '\')">执行任务' +
                                    '</a> <a style="cursor:pointer;" data-toggle="modal" data-target="#myModal" ' + 'onclick="CheckInfo(\'' + data + "')\">查看</a>"
                                ); //转义传值
                        },
                        width: "10%",
                        orderable: false
                    }
                ]
            });
        }
        //执行任务
        window.GotoTaskWindow = function(data,planId,taskName) {
            console.log("当前执行任务的名称 =："+taskName);
            if(data =="未发布"|| data == "已结束"){
               $.jBox.tip("计划任务尚未发布！");
                return false
            }
            $scope.execute(planId);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/samplePlan/samplePlanInfo",
                method: "post",
                params: { id: planId }
            })
            .success(function(data) {
                //清除上次任务信息页面的计划任务id
                window.localStorage.removeItem("ales_taskManage_sampleTask_taskId");//清楚缓存任务id
                window.localStorage.removeItem("ales_taskmanage_mychecktask_superviseCheckTaskId_key");//清楚缓存任务id
                $scope.reCord = data.alesSamplePlan;
                window.localStorage.setItem("ales_taskManage_sampleTask_taskId",  $scope.reCord.relevanceTaskId);
                $state.go("index.content.ales/taskManage/sampleTask/taskInformation");
            })
            .error(function(data) {
               
            });

        };
        $scope.execute =function (data) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/sampleTask/executeTask",
                method: "post",
                params: { id: data }
            })
            .success(function(data) {

            })
            .error(function(data) {
               
            });
        }
        //查看详情页面
        window.CheckInfo = function(data) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/sampleTask/planTaskInfo",
                method: "post",
                params: { id: data }
            })
                .success(function(data) {
                    $scope.reCord = data.planTask;
                    //抽样人员回显

                    /*if( data.planTask.sampleUser.length>30){
                     $scope.reCord.sampleUserShow = data.planTask.sampleUser.substring(0, 30) + "..."
                     }else{
                     $scope.reCord.sampleUserShow = data.planTask.sampleUser
                     }*/
                    if (
                            $scope.reCord.beginTime != null &&
                            $scope.reCord.beginTime != "" &&
                            $scope.reCord.beginTime != undefined
                    ) {
                        $scope.beginTime = new Date(parseInt($scope.reCord.beginTime) + 28800000).toISOString().slice(0, 10);
                        $scope.endTime = new Date(parseInt($scope.reCord.endTime)+28800000).toISOString().slice(0, 10);
                    }
                })
                .error(function(data) {
                    console.log(data.data.id);
                    
                });
        };
        /**
         * 点击查询按钮刷新行政处罚列表
         * 注释日期:2018年2月26日10:29:59
         */
        $scope.queryAdminPunishList = function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        /**
         *信息删除
         *注释日期:2018年2月26日10:34:58
         */
        $scope.del = function() {
            var rows = $("#grid")
                .DataTable()
                .rows(".selected")
                .data();
            var length = rows.length;
            if (length == 0) {
                $.jBox.tip("请选择一行记录！");
                return false;
            }
            for (var k = 0; k < length; k++) {
                if (rows[k].STATE != "未发布") {
                    console.log(rows[k].STATE);
                    $.jBox.tip("只能删除未发布计划");
                    return false;
                }
            }
            var ids = [];
            for (var i = 0; i < length; i++) {
                ids.push(rows[i].ID);
            }

            if (rows != false) {
                $.jBox.confirm(
                    "是否删除已选择行政处罚？",
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
                                url: "/sofn-ales-web/administrativePenalty/del",
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
                                    $scope.queryAdminPunishList();
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



        /**
         * 清除主体信息中缓存的标识
         * 注释日期:2018年2月26日10:38:43
         */
        window.qinchuhuanchun = function() {
            window.localStorage.removeItem("motaikuangBiaoshi");
            window.localStorage.removeItem("punishLawID");
            $("#myModal").modal("hide");
        };
        /**
         * 文件下载
         * 注释日期:2018年2月26日10:39:30
         */
        window.downloadFile = function() {
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-ales-web/alesFile/downloadFile");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileUrl");
            input1.attr("value", $scope.reCord.punishFiles);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "fileName");
            input2.attr("value", $scope.reCord.punishFilesName);
            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
        };
    });
