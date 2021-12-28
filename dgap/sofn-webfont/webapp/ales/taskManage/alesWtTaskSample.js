var myapp = angular.module("myapp", []);
myapp.controller("alesWtTaskSample", function ($scope, $http, $state) {

    $scope.authority = window.localStorage.getItem("alesEntrustDetection_subset_authority");//读写标识
    console.log("权限标识为==>>>"+$scope.authority);
    if($scope.authority == "1"){
        $("#insert").attr("disabled", true);
        $("#update").attr("disabled", true);
        $("#delete").attr("disabled", true);
    }
    /**
     *
     * 委托任务-抽样单列表
     * */
    $scope.wtTaskState = window.localStorage.getItem("wtTaskState");//1、2对应已废止/发布
    $scope.wtTaskId = window.localStorage.getItem("wtTaskId");//任务id
    $scope.moniterId = window.localStorage.getItem("moniterId");//检测对象id
   // $scope.fileCode = window.localStorage.getItem("fileCode");//文件号
    $scope.checkProductName = window.localStorage.getItem("taskManage_alesWtTaskSample_productName");
    $scope.checkIndustry = window.localStorage.getItem("taskManage_alesWtTaskSample_checkIndustry");
    if ($scope.wtTaskState == "1" || $scope.wtTaskState == "2") {
        $("#insert").css("display", "none");
        $("#update").css("display", "none");
        $("#delete").css("display", "none");
    }
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        fnDrawCallback: function () {
            $("#grid tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).parent().find("input[type='checkbox']")[0]
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
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].SAMPLEDATE != null && json.data.list[i].SAMPLEDATE != "" && json.data.list[i].SAMPLEDATE != undefined) {
                        json.data.list[i].SAMPLEDATE = new Date(parseInt(json.data.list[i].SAMPLEDATE)).toISOString().slice(0, 10);
                    }
                }
                return json.data.list;

            },
            error:function (msg) {
                console.log(msg);
                
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: function (params) {
                params.organTaskId = $scope.moniterId;
                params.productName = $("input[name='productName']").val();
                params.queryCon = $("input[name='queryCon']").val();
                params.productCode = $("input[name='productCode']").val();
                params.sampleCode = $("input[name='sampleCode']").val();
            },
            type: "POST"
        },
        columns: [
            {
                data: "ck",
                title: '选择',
                render: function (data, type, row) {
                    return '<input type="checkbox" name="ck" onclick="checkThisBox(this)"/>';
                },
                orderable: false
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
                title: "商标"
            },
            {
                data: "grade",
                title: "等级"
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
                        return "已提交";
                    } else if ("0" == data) {
                        return "未提交";
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
                    var butt = '';
                    //butt += '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\''+data+'\')">检测详情</a>&nbsp;';
                    butt += '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\')">抽样单详情</a>';
                    return butt;
                }
            }
        ]
    });
    $scope.returnTaskList = function () {
        $state.go("index.content.ales/taskManage/alesEntrustDetection")
    };
    //重置按钮
    $scope.cleanSelectModelQuery = function () {
        $("input[name='productName']").val("");
        $("input[name='productCode']").val("");
        $("input[name='sampleCode']").val("");
        $("input[name='queryCon']").val("");
        $("#grid").dataTable().api().ajax.reload();
    };
    /**
     * 选择按钮
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
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        return ids;
    };
    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
    };
    //查看
    window.queryInfo = function (id) {
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "query");//查看动作
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr", id);//抽样记录
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "WTJC");
        $state.go("index.content.ales/taskManage/sample/writeSample");
    };
    //修改
    $scope.upd = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr", rows[0].id);
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "WTJC");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "update");
        $state.go("index.content.ales/taskManage/sample/writeSample")
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
                    }).error(function (msg) {
                       
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };
    //新增样品信息
    $scope.addLine = function () {
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
        window.localStorage.setItem("moniterId", $scope.moniterId);
        window.localStorage.setItem("ales_taskmanager_oration_tasktype", "WTJC");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo", "add");
        window.localStorage.setItem("fileCode", $scope.fileCode);
      //  $scope.checkProductName = window.localStorage.getItem("taskManage_alesWtTaskSample_productName");
        window.localStorage.setItem("taskManage_sample_writeSample_productName",$scope.checkProductName);//农产品名称
        window.localStorage.setItem("taskManage_sample_writeSample_checkIndustry",$scope.checkIndustry )//农产品行业
        $state.go("index.content.ales/taskManage/sample/writeSample")
    };
    $("#Fjtable tbody tr td").each(function (i, o) {
        $(o).click(function () {
            var checked = $(this).parent().find("input[type='checkbox']")[0];
            if (checked.checked) {
                checked.checked = false;
                $(this).parent().removeClass("selected");
            } else {
                checked.checked = true;
                $(this).parent().addClass("selected");
            }
        });
    });
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("ales_taskManage_alesEntrustDetectionOpr");
        window.localStorage.removeItem("ales_taskManage_alesEntrustDetectionOpr_Index");
    };
    $scope.getTableData = function (tid) {
        var t_data = "";
        var f = -1;
        var td_data_temp = "";
        //tr
        $("#" + tid).find("tr").each(function (i) {
            if ($("#" + tid).find("tr:eq(" + i + ")").find("td").length != 0) {
                //td
                $("#" + tid).find("tr:eq(" + i + ")").find("td").each(function (j) {
                    if ($(this).css("display") != "none") {
                        if ($(this).find("input[type='text']").length == 0) {

                            td_data_temp = ($(this).text() == "" ? " " : $(this).text());
                        } else {
                            td_data_temp = ($(this).find("input[type='text']:eq(0)").val() == "" ? " " : $(this).find("input[type='text']:eq(0)").val());
                        }

                        if (td_data_temp.toString().indexOf("全选") != -1) {
                            f = j;
                        } else {
                            if (f != j)
                                t_data += td_data_temp + "TableTD";
                        }
                    }
                });
            } else {
                //th
                $("#" + tid).find("tr:eq(" + i + ")").find("th").each(function (j) {
                    if ($(this).css("display") != "none") {
                        if ($(this).find("input[type='text']").length == 0) {

                            td_data_temp = ($(this).text() == "" ? " " : $(this).text());
                        } else {
                            td_data_temp = ($(this).find("input[type='text']:eq(0)").val() == "" ? " " : $(this).find("input[type='text']:eq(0)").val());
                        }

                        if (td_data_temp.toString().indexOf("全选") != -1) {
                            f = j;
                        } else {
                            if (f != j)
                                t_data += td_data_temp + "TableTD";
                        }
                    }
                });
            }
            t_data += "TableTR";
        });
        return t_data;
    };

    //查看详情页面
    window.viewOrganTask=function(obj){
        $.ajax({
            url:"/sofn-ales-web/alesSample/getCheckInfoIdBySampleId",
            type:"post",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataType:"json",
            data:{sampleId:obj},
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
                            },error:function (msg) {
                               
                                },
                            data : function(params) {
                                params.id = data.checkInfoId;
                                params.token = window.localStorage.getItem("token");
                            },
                            type:"post"
                        },
                        columns : [
                            {
                                data : "checkProject",
                                title : "检测项目"
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
                                title : "LOD（位）",
                                data : function(data, type, row, meta) {
                                    return ""
                                }
                            },
                            {
                                title : "：mgkg LOO",
                                data : function(data, type, row, meta) {
                                    return ""
                                }
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
