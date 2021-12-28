var myapp = angular.module("myapp", []).controller("qryTestedDeparment", function ($scope, $http, $state) {



    /**
     * 任务列表
     * */
    //查询按钮
 /*   $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });*/

    //查询按钮事件
    $scope.ToCheck = function ($event) {
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

    $scope.reloadArea=function($event){
        //重置 二次查询
        if ($event) {
            $($event.currentTarget).prev().text("查询");
        }
        $("#queryForm").form("reset");
        $("#grid").dataTable().api().ajax.reload();
    }


    $(function () {
        $("#grid").dataTable({
            ordering: true, //开启排序
            order: [[3, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/checkProject/getTestedDeaparment",
                dataSrc: function (json) {
                    if (json.data == null || json.data == undefined) {
                        json.recordsTotal = 0;//总个数
                        json.recordsFiltered = 0;
                        json.pageNumber = 1;//页码
                        json.pageSize = 10;//每页个数
                        json.start = 0;//起始位置
                        json.length = 10;
                        return json.data;
                    }
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        //时间格式化
                        if (json.data.list[i].RMDATEBEGIN != null && json.data.list[i].RMDATEBEGIN != "" && json.data.list[i].RMDATEBEGIN != undefined) {
                            json.data.list[i].RMDATEBEGIN = new Date(parseInt(json.data.list[i].RMDATEBEGIN) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].RMDATEEND != null && json.data.list[i].RMDATEEND != "" && json.data.list[i].RMDATEEND != undefined) {
                            json.data.list[i].RMDATEEND = new Date(parseInt(json.data.list[i].RMDATEEND) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.result = $("select[name='result']").val();//检测结果
                    params.monitorClass = $("select[name='monitorClass']").val();//检测类型
                    params.year = $("input[name='year']").val();//年份
                    params.sampleName = $("input[name='sampleName']").val();
                    params.sampleCode = $("input[name='sampleCode']").val();
                    params.testedDeparment = $("input[name='testedDeparment']").val();
                    params.taskName = $("input[name='taskName']").val();

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: 'ID',
                    visible: false,
                    orderable: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%",
                    orderable: false
                },
                {
                    data: "testedDeparment",
                    title: "受检单位",
                    width: "7%"
                },
                {
                    data: "year",
                    title: "任务年度",
                    width: "7%"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    width: "7%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='taskName"+row.id+"' onmouseover=showPopContent('taskName"+row.id+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }
                },
                {
                    data: "monitorClass",
                    title: "任务类型",
                    width: "5%"
                },
                {
                    data: "sampleName",
                    title: "样品名称",
                    width: "7%"
                },
                {
                    data: "sampleCode",
                    title: "样品编码",
                    width: "5%"
                },
                {
                    data: "result",
                    title: "检测结果",
                    width: "10%",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else if ("2" == data) {
                            return "不作判定";
                        }
                    }
                },

                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        return '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\',\''+row.sampleName+'\',\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\')" class="color">查看</a>  ';
                    },
                    width: "5%",
                    orderable: false
                }
            ],
        });
    });

    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
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

    /**
     * 任务操作
     * */

    //查看详情页面
    window.queryInfo = function (data,data1,data2,data3) {
        $("#sampleName").val(data1);

        $("#sampleCode").val(data2);

        if (data3!='null'){
            $("#productTraceabilityCode").val(data3);
        }

        $("#product_info2").modal("show");
        //清理数据
        var tableName = $('#productList1');
        if ($.fn.dataTable.isDataTable(tableName)) {

            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#productList1").dataTable({
            processing: true, // 加载时提示
            serverSide: false, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            paging: false,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-qry-web/checkProject/getCkeckById",
                dataSrc: function (json) {

                    return json.data;
                },
                data: function (params) {
                    params.id = data;

                },
                type: "post"
            },
            columns: [
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (0 == data) {
                            return '<a id="' + meta.row + '" value="' + "未检出" + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">未检出</a>';
                        } else {
                            return '<a id="' + meta.row + '" value="' + data + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">' + data + '</a>';
                        }
                    },
                },
                {
                    data: "judgeNum",
                    title: "上限值",
                    render:function (data,type,row,meta) {
                        if (data!='null'&&data!=null){
                            return data;
                        }else {
                            return "";
                        }
                    }
                },
                {
                    data: "result",
                    title: "抽检结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else {
                            return "不作判定";
                        }
                    }
                },
                {
                    title: "LOD（位）",
                    data: function (data, type, row, meta) {
                        return ""
                    }
                },
                {
                    title: "：mgkg LOO",
                    data: function (data, type, row, meta) {
                        return ""
                    }
                }
            ],
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            sPaginationType: "full_numbers"
        });
    }


    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };



});
