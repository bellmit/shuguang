var myapp = angular.module("myapp", []).controller("taskSampleList", function ($scope, $http, $state) {
    /**
     * *
     **监测信息对象涵盖抽样单列表，一个监测信息对应多条抽样单信息
     * *
     * */
    $scope.authority = window.localStorage.getItem("myCheckTask_subset_authority");//读写标识
    if($scope.authority == window.MENU_READONLY_AUTHORITY){
        $("#singleSyncToAdsId").attr("disabled", true);
        $("#updId").attr("disabled", true);
        $("#delId").attr("disabled", true);
        $("#cleanCookieId").attr("disabled", true);
    }
    /**
     * 公共值
     * */
    $scope.taskState = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_taskInfos");//当前任务状态
    $scope.jcxxId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");//检测信息id
    $scope.taskId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr");//任务id
    $scope.oprIdx = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");//任务操作标示
    $scope.productNameValue = window.localStorage.getItem("ales_taskmanage_mychecktask_productName_key");//农产品名
    $scope.industry = window.localStorage.getItem("ales_taskmanage_mychecktask_industry_key")//行业
    $scope.taskend = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_dateEnd");
    $(function () {
        $("#grid").dataTable({
            fnDrawCallback: function () {
                $("#grid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0];
                        console.log("选择的数据=："+checked)
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                        }
                    });
                })
            },
            ajax: {
                url: "/sofn-ales-web/alesmynewchecktask/getTaskSampleListByJcInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.organTaskId = $scope.jcxxId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {/*<input id="checkAll"  type="checkbox"/>*/
                        return '<input type="checkbox" name="ck" onclick="checkThisBox(this)"/>';
                    }
                },
                {
                    data: 'id',
                    title: 'id',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "sampleName",
                    title: "样品名称"
                },
                {
                    data: "sampleCode",
                    title: "样品编码"
                },
                {
                    data: "tradeMark",
                    title: "商标",
                    visible: false
                },
                {
                    data: "grade",
                    title: "等级",
                    visible: false
                },
                {
                    data: "sampleBase",
                    title: "抽样基数"
                },
                {
                    data: "samplePlace",
                    title: "抽样场所"
                },
                {
                    data: "isSync",
                    title: "状态",
                    render: function (data) {
                        if ("1" == data) {
                            return'<span name ="state" style="color:#9dbed8">'+"已上报"+'</span>' ;
                        } else if ("0" == data) {
                            return '<span name ="state" style="color:#9c3328">'+"未上报"+'</span>';
                        }
                    }
                }, {
                    data: "result",
                    title: "检测结果",
                    render: function (data, type, row) {
                        switch (data) {
                            case "-1" :
                                return "--";
                            case "1" :
                                return '<a style="cursor:pointer" onclick="checkResult(\'' + row.organTaskId + '\')">合格</a>';
                            case "0" :
                                return '<a style="cursor:pointer" onclick="checkResult(\'' + row.organTaskId + '\')">不合格</a>';
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        var butt = "";
                        if(row.result == "-1"){
                            butt += '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\')">抽样单详情</a>';
                            return butt;
                        }
                        butt += '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\''+data+'\')">检测详情</a>&nbsp;';
                        butt += '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\')">抽样单详情</a>';
                        return butt;

                    }
                }
            ]
        });
    });
    /**
     * 上报抽样单
     * 2018年3月2日17:45:04
     **/
    $scope.singleSyncToAds = function () {
        var date = $scope.getNowFormatDate();
        if(date > $scope.taskend){
            $.jBox.tip("当前任务已过截止日期禁止上报抽样内容！！！")
            return false;
        }
        console.log("日期当前=："+date+"结束日期"+$scope.taskend);
        var i = $scope.getSelectRowsIds();
        if (i != false) {
        $.jBox.confirm("确认提交抽样单信息，提交后将不能修改、删除该抽样单", "等待服务器响应", function (v) {
            if (v == true) {
                var data = {
                    jsonString: JSON.stringify(i)
                };
                $("button").attr("disabled", true);
                console.log("数据=："+data);
                $.ajax({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url: "/sofn-ales-web/alesmynewchecktask/singleSyncToAds",
                    type: "post",
                    dataType: "json",
                    data: data,
                    beforeSend: function () {
                        $("button").attr("disabled", false);
                        $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                        $("#web_app_model_userOprXz").modal("show");
                    },
                    success: function (data) {
                        if(data.key == '0'){
                            $.jBox.tip("上报失败！");
                            $("#web_app_model_userOprXz").modal("hide");
                            $("#grid").dataTable().api().ajax.reload();
                        }else{
                        $("#web_app_model_userOprXz").modal("hide");
                        $("#grid").dataTable().api().ajax.reload();
                        $.jBox.tip("上报成功！");
                        }

                    }});
            }
            return true;
        }, {buttons: {'确认': true, '取消': false}});};
    };

    /**
     * 任务操作为历史任务查看隐藏操作按钮
     * */
    if ("history" == $scope.oprIdx) {
        $("button[name='addButton']").hide();
        $("button[name='updButton']").hide();
        $("button[name='delButton']").hide();
        $("button[name='sumButton']").hide();
    }

    /**
     * 根据任务状态初始化页面节点
     * */
    /*if ("1" == $scope.taskState || "2" == $scope.taskState) { // qu取消已提交或部分提交任务状态禁止操作按钮 2018年5月23日17:48:09
        $("button[name='addButton']").hide(); //已提交或部分提交状态禁止操作按钮
        $("button[name='updButton']").hide();
        $("button[name='delButton']").hide();
    }*/

    /**
     * 操作
     * */
    //检测结果
    window.checkResult = function (data) {
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinfomation_samplelist_checkresult");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinfomation_samplelist_checkresult", data);
        $state.go("index.content.ales/taskManage/sampleResult/TaskSampleJcInfoList");
    };


    //查看
    window.queryInfo = function (o) {
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "query");//查看动作
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr", o);//抽样记录
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "JDCC");
        $state.go("index.content.ales/taskManage/sampleTask/writeMytaskSample");
    };

    //新增
    $scope.add = function () {
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "add");//修改动作
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "JDCC");
        window.localStorage.setItem("ales_taskmanage_mychecktask_productName_key_myTaskSamplelist_productName_key",$scope.productNameValue);//农产品名称
        window.localStorage.setItem("ales_taskmanage_mychecktask_industry_key_myTaskSamplelist_industry_key",$scope.industry);
        $state.go("index.content.ales/taskManage/sampleTask/writeMytaskSample");
    };

    //修改
    $scope.upd = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        if (rows[0].isSync == '1') {
            $.jBox.tip("所选记录已提交，无法进行修改操作！");
            return false;
        }
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "update");//修改动作
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr", rows[0].id);
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "JDCC");
        $state.go("index.content.ales/taskManage/sampleTask/writeMytaskSample");
    };

    //删除
    $scope.del = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            if (rows[0].isSync == '1') {
                $.jBox.tip("所选记录中包含已提交数据，请重新选择");
                return false;
            }
            ids.push(rows[i].id);
        }
        var i = ids;
        if (i != false) {
            $.jBox.confirm("是否批量删除已选择信息？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $("button").attr("disabled", true);
                    $.ajax({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url: "/sofn-ales-web/alesmynewchecktask/delSampleById",
                        type: "post",
                        dataType: "json",
                        data: data,
                        beforeSend: function () {
                            $("button").attr("disabled", false);
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $("#grid").dataTable().api().ajax.reload();
                        },
                        fail: function (data) {
                            $("button").attr("disabled", false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    }).error(function (data) {
                        console.log(data);
                        
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

    /**
     * other
     * */
    window.checkThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };
    //新增抽样单??????
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");
    };

    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#grid input[type='checkbox']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    //获取选择行记录id集合
    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        console.log("长度"+length);
        if (length == 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;

        }
        console.log("状态=："+length);
        var idarray =[];
        var json = {};
        for (var i = 0; i < length; i++) {
            var ids =[];
            if (rows[i].isSync == '1' ) {
                $.jBox.tip("所选任务包括已上报任务，无法进行操作！");
                return false;
            }
            ids.push(rows[i].id);
            ids.push(rows[i].sampleCode);
            ids.push(rows[i].organTaskId);
            idarray.push(ids);
        }

        return idarray;
    };
    //获取当前时间，格式YYYY-MM-DD
    $scope.getNowFormatDate = function() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year+"" + month +""+ strDate;
        return currentdate;
    }
    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
    };


    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };
    $scope.previous = function () {
        console.log("返回上一页面。。。。。。。。。");
       // ales/taskManage/sampleTask/taskInformation
        $state.go("index.content.ales/taskManage/sampleTask/taskInformation");
    }

    //查看详情页面
    window.viewOrganTask=function(obj){
        $.ajax({
            url:"/sofn-ales-web/alesSample/getCheckInfoIdBySampleId",
            type:"post",
            dataType:"json",
            data:{sampleId:obj},
            headers: {
                token: window.localStorage.getItem("token")
            },
            success:function(data){
                if(data.httpCode=="200"){
                    window.localStorage.setItem("ID",data.checkInfoId);
                    // $scope.ID = obj;
                    $("#product_info2").modal("show");
                    //清理数据
                    var tableName = $('#productList');
                    if ($.fn.dataTable.isDataTable(tableName)) {
                        tableName.DataTable().clear();
                        tableName.DataTable().destroy();
                    }
                    //加载数据
                    $("#productList").dataTable({
                        processing : true, // 加载时提示
                        serverSide : false, // 分页，是否服务器端处理
                        ordering : false,
                        searching : false,
                        bProcessing: true,
                        paging:false,
                        dom : '<"top">rt<"bottom"pli><"clear">',
                        autoWidth : false,
                        ajax : {
                            url : "/sofn-ads-web/adsCheckInfo/getChild",
                            headers: {
                                token: window.localStorage.getItem("token")
                            },
                            dataSrc:function(json){
                                console.log(json);
                                // json.recordsTotal=json.data.total;//总个数
                                // json.recordsFiltered=json.data.total;
                                // json.pageNumber=json.data.pageNum;//页码
                                // json.pageSize=json.data.pageSize;//每页个数
                                // json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                                // json.length=json.data.pageSize;
                                return json.data;
                            },
                            data : function(params) {
                                params.id = data.checkInfoId;
                                params.token = window.localStorage.getItem("token");
                            },
                            type:"post"
                        },
                        columns : [
                            {
                                data: "checkProject",
                                title: "检测项目",
                                render: function(data, type, row, meta) {
                                    if (data) {
                                        var showdata = data;
                                        if (data.length > 10) {
                                            showdata = data.substring(0, 10) + "..";
                                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                        }
                                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                    } else {
                                        return '--';
                                    }
                                },
                            },
                            {
                                data : "checkNum",
                                title : "检测值",
                                render : function(data, type, row,meta) { // 模板化列显示内容
                                    if(0 == data){
                                        return '<a id="'+meta.row+'" value="'+"未检出"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">未检出</a>';
                                    }else{
                                        return '<a id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                                    }
                                },
                            },
                            {
                                data : "judgeNum",
                                title : "判定值"
                            },
                            {
                                data : "result",
                                title : "抽检结果",
                                render : function(data, type, row) {
                                    if("-1" == data){
                                        return "暂未检测";
                                    }else if("1" == data){
                                        return "合格";
                                    }else{
                                        return "不合格";
                                    }
                                }
                            },
                            {
                                data:"lod",
                                title : "LOD（位）",

                            },
                            {
                                data:"loo",
                                title : "：mgkg LOO",
                            }
                        ],
                        language : {
                            "sProcessing" : "处理中...",
                            "sLengthMenu" : "显示 _MENU_ 项结果",
                            "sZeroRecords" : "没有匹配结果",
                            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                            "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
                            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
                            "sInfoPostFix" : "",
                            "sSearch" : "搜索:",
                            "sUrl" : "",
                            "sEmptyTable" : "表中数据为空",
                            "sLoadingRecords" : "载入中...",
                            "sInfoThousands" : ",",
                            "oPaginate" : {
                                "sFirst" : "首页",
                                "sPrevious" : "上页",
                                "sNext" : "下页",
                                "sLast" : "末页"
                            },
                            "oAria" : {
                                "sSortAscending" : ": 以升序排列此列",
                                "sSortDescending" : ": 以降序排列此列"
                            }
                        },
                        sPaginationType : "full_numbers"
                    });
                }else{
                    $.jBox.tip(data.msg);
                }
            },
            error: function (data) {
                
            }
        });
    }
});
