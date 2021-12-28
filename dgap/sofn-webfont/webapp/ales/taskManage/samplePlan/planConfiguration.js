var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("planConfiguration", function($scope, $http, $state,$timeout) {
        var vm =this;
        $scope.selects  = new Array();//抽样人员选择
        $scope.userId ="";//人员ID
        $scope.authorityArray = window.localStorage.getItem("menus");
        $scope.samplePlanId = window.localStorage.getItem("samplanlist_planConfigurtion_keyid");
        $scope.samplePlanTaskId = window.localStorage.getItem("samplanlist_planConfigurtion_keytaskId");
        var relevanceTaskId = window.localStorage.getItem("samplanlist_planConfigurtion_keytaskId");
        console.log("===:"+$scope.samplePlanTaskId);
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
                $scope.registerUserId = data.data;
            })
            .error(function(data) {
            });
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/sampleTask/queryPlanTime",
            method: "post",
            params: { planId: $scope.samplePlanId }
        }).success(function(data){
            $scope.taskbegin = new Date(parseInt(data.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
            $scope.taskend = new Date(parseInt(data.data.taskEndTime)+28800000).toISOString().slice(0, 10);
        });

        /**
         * 获取监督抽查任务的开始于结束时间
         */
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/alesmynewchecktask/getTaskById",
            method: "post",
            data: { "id": relevanceTaskId}
        }).success(function (data) {

            $scope.startTime = new Date(parseInt(data.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
            $scope.deadline = new Date(parseInt(data.data.taskEndTime)+28800000).toISOString().slice(0, 10);

            $("#startTime").val( $scope.startTime);
            $("#deadline").val($scope.deadline);


        });
        
        //重置
        $scope.reset = function() {
            $("input[name='sampleUser']").val("");
            $("input[name='taskName']").val("");
            $("#grid").dataTable().api().ajax.reload();
        };
        $scope.restss =  function(){
            $("input[name = 'sampleUser']").val("");

            $("input[neme = 'intota']").val();

        }
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

        //计划配置列表；
        function loadGrid() {
            $("#grid").dataTable({
                fnDrawCallback: function() {
                    $('#grid tbody').on('change', ':radio', function(){
                        var checked = $(this).prop('checked');
                        if(checked){
                            $(this).closest('tr').addClass('selected').siblings().removeClass('selected');
                        }
                    })
                },
                ajax: {
                    url:
                        "/sofn-ales-web/sampleTask/sampleTask",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function(json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
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
                                json.data.list[i].TASK_END_TIME = new Date(
                                    parseInt(json.data.list[i].TASK_END_TIME) + 28800000).toISOString().slice(0, 10);
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
                        params.sampleUser = $("input[name='sampleUser']").val();
                        params.taskName = $("input[name='taskName']").val();
                        params.planId =$scope.samplePlanId;
                    },
                    type: "post"
                },
                columns: [
                    //选择按钮
                    {
                        data: "ck",
                        title: "",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return (
                                '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="' +
                                row.ID +
                                '"/>'
                            );
                        },
                        width: "1%",
                        orderable: false
                    },
                    {
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        width: "5%",
                        orderable: false
                    },
                    {
                        data: "SAMPLE_USER",
                        title: "抽样人员",
                        render: function(data, type, row) {
                            //return data.substring(0, 4);
                            return data;
                        },
                        width: "7%"
                    },
                    {
                        data: "SAMPLE_NUMBER",
                        title: "抽样计划对象及数量",
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
                        data: "ID",
                        title: "操作",
                        render: function(data, type, row) {// 模板化列显示内容
                                return (
                                    '<a type="button" style="cursor:pointer;" data-toggle="modal" data-target="#planTaskInfoModal" onclick="Checkreport(\'' + data + '\')">查看</a>')
                                  /*  +'<a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\'' + data + '\')">修改</a>'; //转义传值
                */        },
                        width: "10%",
                        orderable: false
                    }
                ]
            });
        }
        //新增计划任务
        $scope.AlesSampleTask = {};
        $scope.addPlanTask = function () {
            $scope.AlesSampleTask.sampleUser = $("input[name='userNumber']").val();
            $scope.AlesSampleTask.sampleNumber = $scope.objNumber;
            $scope.AlesSampleTask.beginTime = $("input[name='datebegin']").val();
            $scope.AlesSampleTask.endTime = $("input[name='dateend']").val();
            //临时处理
            if($scope.AlesSampleTask.endTime ==""||$scope.AlesSampleTask.endTime == null || $scope.AlesSampleTask.endTime == undefined){$.jBox.tip("任务信息填写不完整！"); return false;}
            if($scope.AlesSampleTask.sampleUser ==""||$scope.AlesSampleTask.sampleUser == null || $scope.AlesSampleTask.sampleUser == undefined){$.jBox.tip("任务信息填写不完整！"); return false;}
            if($scope.AlesSampleTask.sampleNumber ==""||$scope.AlesSampleTask.sampleNumber == null || $scope.AlesSampleTask.sampleNumber == undefined){$.jBox.tip("任务信息填写不完整！"); return false;}
            if($scope.AlesSampleTask.beginTime ==""||$scope.AlesSampleTask.beginTime == null || $scope.AlesSampleTask.beginTime == undefined){$.jBox.tip("任务信息填写不完整！"); return false;}
            $scope.AlesSampleTask.planId = $scope.samplePlanId;
            $scope.AlesSampleTask.sampleUserId = $scope.userId;
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/sampleTask/addPlanTask",
                method: "post",
                data: $scope.AlesSampleTask,
            }).success(function(data) {
                $("#myModal").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
            })
            .error(function(data) {
            });
        }
        //选择抽样人员
        $scope.selectUserNumber =function () {
                $('#selectUserNumberGrid').dataTable().api().ajax.reload();
                $("#selectUserNumberModal").modal('show');
        }
        var table = $('#selectUserNumberGrid');
            table.find('tbody .selected').removeClass('selected').find(':checkbox').prop('checked', false)//移除复选择样式
        $("#selectUserNumberGrid").dataTable({
            fnDrawCallback: function() {
                //点击行内任意内容即选择行
                $("#selectUserNumberGrid tbody tr td").each(function(i,o){
                    $(o).click(function(){
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if(checked.checked){
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //人员移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0 ; i < $scope.selects.length ; i++){
                                if($scope.selects[i] != undefined && u_d == $scope.selects[i].id){
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
                            console.info($scope.selects)
                        }
                    });
                })
            },
            ajax : {
                url : "/sofn-ales-web/dailyEnforceLaw/getUsersListByUserToken",
                dataSrc:function(json){
                    if(json.data == null || json.data == undefined){
                        json.recordsTotal=0;//总个数
                        json.recordsFiltered=0;
                        json.pageNumber=1;//页码
                        json.pageSize=10;//每页个数
                        json.start=0;//起始位置
                        json.length=10;
                        return json.data;
                    }
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.pageNumber = json.page.start/json.page.length;//页码
                    json.pageSize = json.page.length;//每页个数
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.data;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data : function(params) {
                    params.token= window.localStorage.getItem("token");
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    params.keyword = $("input[name='selectQueryName']").val();
                },
                type:"post",
                headers:{
                    token:window.localStorage.getItem("token")
                }
            },
            columns : [
                {
                    data : "id",
                    title : '*',
                    render : function(data, type, row) {
                        for(var i = 0; i <  $scope.selects.length;i++){
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].id){
                                return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                    },
                    width : "3%"
                },
                {
                    data : 'id',
                    title : 'ID',
                    visible : false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data : "userName",
                    title : "姓名",
                    render : function(data, type, row) {
                        return '<span name = "userName">'+data+'</span>';
                    }
                },
                {
                    data : "phone",
                    title : "联系电话"
                },
                {
                    data : "email",
                    title : "电子邮箱"
                }
            ],
        });
        window.checkSelectThisBox = function(o){
            if(o.checked){
                o.checked = false;
                $(o).removeClass("selected");
            }else {
                o.checked = true;
                $(o).addClass("selected");
            }
        };
        //选择确定人员
        $scope.selectUser = function(){
            var userName = "";
            for(var i = 0; i < $scope.selects.length ; i++){
                if ($scope.selects[i] != undefined){
                    if (userName != ""){
                        userName+=","+$scope.selects[i].userName;
                        $scope.userId+=","+$scope.selects[i].id;
                    }else {
                        userName = $scope.selects[i].userName;
                        $scope.userId=$scope.selects[i].id;
                    }
                }
            }
            $("input[name='userNumber']").val(userName);
            $("input[name='userNumber']").val(userName);
            if($("input[name='userNumber']").val()==""){
                $(".objPerson").css("display","block");
                return;
            }else{
                $(".objPerson").css("display","none")
            }
            $("#selectUserNumberModal").modal('hide');
        };
        /**
         * data加入缓存。
         * 注释日期:2018年2月26日10:16:11
         */
        window.GotoUpdateWindow = function(data) {
            window.localStorage.setItem("id", data);
            window.localStorage.setItem("ales_punish_updateFlag", "1");
            $state.go("index.content.ales/punish/produceAdminPunishAdd");
        };
        /**
         * 查看详情页面
         * 注释日期:2018年2月26日10:47:55
         */
        $scope.punishEnterpriseId = "";
        window.Checkreport = function(data) {
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
                        $scope.reCord.sampleUserShow = data.planTask.sampleUser.substring(0, 30) + "....."
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
        $scope.add = function () {
            $scope.objNumber = null;
            $scope.userNumber = null;
            $scope.selects  = []
            $("#myModal").modal("show").on('show.bs.modal', function (e) {
                $("input[name='datebegin']").val("");
                $("input[name='dateend']").val("");
                $("#objNumberId, #userNumberId").val('').text('');
                $('#selectUserNumberGrid :checkbox').prop('checked', false).closest('tr').removeClass('selected');
            })
        }
        $scope.automatic = function () {
            $("#adjModal").modal("show");
        }
        $scope.selectModalQdButt = function () {
            $("#selectModal").modal('show');
        };
        /**
         *信息删除
         *注释日期:2018年2月26日10:34:58
         */attributeForm
        $scope.delPlanTask = function() {
            var rows = $("#grid").DataTable().rows(".selected").data();
            if (rows.length == 0) {
                $.jBox.tip("请选择一行记录！");
                return false;
            }
            var delId =[];
            for(var i=0 ; i < rows.length;i++){
                delId.push( rows[i].ID);
            }
            if (rows != false) {
                $.jBox.confirm(
                    "是否删除已选择抽样计划配置？",
                    "等待服务器响应",
                    function(v) {
                        if (v) {
                            var data = {
                                jsonString: JSON.stringify(delId)
                            };
                            $.ajax({
                                headers: {
                                    token: window.localStorage.getItem("token")
                                },
                                url: "/sofn-ales-web/sampleTask/delPlanTask",
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
                            });
                        }
                        return true;
                    },
                    { buttons: { 确认: true, 取消: false } }
                );
            }
        };
        //分配抽样人员
        $scope.allot = function () {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/samplePlan/allotUserNumber",
                method: "post",
                params: { count: $scope.count }
            })
            .success(function(data) {
                $("#adjModal").modal('hide');
                if(data.httpCode == '409'){
                    $.jBox.tip("没有下属人员！");
                }
                var userName ="";
                for(var i = 0; i< data.data.length;i++){
                    if (data.data[i] != undefined){
                        if (userName != ""){
                            userName+=","+data.data[i].userName;
                            $scope.userId+=","+data.data[i].id;
                        }else {
                            userName = data.data[i].userName;
                            $scope.userId=data.data[i].id;
                        }
                    }
                }
                    $("#userNumberId").val(userName);

            })
        }
        $scope.selectModalQdButt = function () {
            $('#selectGrid').dataTable().api().ajax.reload();
            $("#selectModal").modal('show');
        };
        //表格
        $("#selectGrid").dataTable({
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/alesmynewchecktask/getMyTaskObjById",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;
                    json.recordsFiltered = json.data.total;
                    json.start = json.data.pageNum;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.id = $scope.samplePlanTaskId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '*',
                    render: function (data, type,   row) {
                            return '<input type="checkbox"  name="ck" data-id="'+ data +'" data-code="'+row.standardCode+'"/>';
                    }
                },
                {
                    data: "productName",
                    title: "抽样对象",
                    render: function (data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div data-text="'+data+'"  style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div data-text="'+data+'" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                },
              {
                    title: "数量",
                    render: function (data, type, row) {
                        return '<input class="gui-input" type="number" min="1" step="1" maxlength="10" />';
                    }
              }
            ]
        });
        var selecteds = [];
        $scope.selectPDBzButt = function () {
            selecteds = [];
            $('#selectGrid').find('tbody tr :checked').closest('tr').each(function(){
                console.log( 'xxx', $(':checkbox', this).attr('data-id'));
                selecteds[selecteds.length] = {
                    id: $(':checkbox', this).attr('data-id'),
                    text : $('td:eq(1)', this).children().attr('data-text'),
                    value:  $('td:eq(2)', this).children().val(),
                };
            });
            console.log(selecteds);
            $scope._alesPdStandard = selecteds;
            $scope.objNumber = selecteds.map(function(item){
                return  [item.text, item.value].join(':')
            }).join(',');
            $("#objNumberId").text($scope.objNumber);
            $('#objNumberId').trigger('input');
            $("#judgeStandard_Model").modal('hide');
            // SdDateValid.ValidByCode("input", "detection");
        };//判定标准确定按钮
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
