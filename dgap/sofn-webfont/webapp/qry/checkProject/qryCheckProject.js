var myapp = angular.module("myapp", []).controller("qryCheckProject", function ($scope, $http, $state) {
//地区


    // $.fn.mycity("sheng","shi","xian",0,0,0);

    var area = ""
    var jibie = ""
    $http({
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        area = data.user.regionId;
        jibie = data.user.orgLevel;
        if (jibie == 1) {
            area = 0
        }
        if (jibie == 2) {
            area = area.substr(0, 2);
        }
        if (jibie == 3) {
            area = area.substr(0.4)
        }
        if (jibie == 4) {
            area = area.substring(0, 6)
        }
        $.fn.mycity("sheng", "shi", "xian", area);
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
            $("#sheng").attr("disabled", true);
        }
        if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
            $("#shi").attr("disabled", true);
        }
        if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
            $("#xian").attr("disabled", true);
        }

        $("#grid").dataTable({
            // fnDrawCallback: function() {
            //     //点击行内任意内容即选择行
            //     $("#grid tbody tr").each(function(i,o){
            //         $(o).click(function(){
            //             var checked = $(this).find("input[type='checkbox']")[0];
            //             if(checked.checked){
            //                 checked.checked = false;
            //                 $(this).removeClass("selected");
            //             }else {
            //                 checked.checked = true;
            //                 $(this).addClass("selected");
            //             }
            //         });
            //     })
            // },
            ordering: true, //开启排序
            order: [[2, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/checkProject/getPageInfoParamsNew",
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
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    for (var i = 0; i < json.data.list.length; i++) {
                        //时间格式化
                        if (json.data.list[i].RMDATEBEGIN != null && json.data.list[i].RMDATEBEGIN != "" && json.data.list[i].RMDATEBEGIN != undefined) {
                            json.data.list[i].RMDATEBEGIN = new Date(parseInt(json.data.list[i].RMDATEBEGIN) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].RMDATEEND != null && json.data.list[i].RMDATEEND != "" && json.data.list[i].RMDATEEND != undefined) {
                            json.data.list[i].RMDATEEND = new Date(parseInt(json.data.list[i].RMDATEEND) + 28800000).toISOString().slice(0, 10);
                        }
                        //任务状态
                        // if(json.data.list[i].RMSTATE!=null&&json.data.list[i].RMSTATE!=""&&json.data.list[i].RMSTATE!=undefined){
                        //     if(json.data.list[i].RMSTATE=='0'){
                        //         json.data.list[i].RMSTATE='未发布';
                        //     }
                        //     if(json.data.list[i].RMSTATE=='1'){
                        //         json.data.list[i].RMSTATE='已发布';
                        //     }
                        //     if(json.data.list[i].RMSTATE=='2'){
                        //         json.data.list[i].RMSTATE='已废止';
                        //     }
                        // }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.checkProject = $("input[name='checkProject']").val();//检测项目
                    params.industry = $("select[name='hangye']").val();//行业
                    params.result = $("select[name='result']").val();//检测结果
                    params.startTime = $("input[name='datebegin']").val();//开始时间
                    params.endTime = $("input[name='dateend']").val();//结束时间
                    params.year = $("input[name='year']").val();//年份
                    // if($("input[name='sampleName']").val()!=''||$("input[name='sampleName']").val()!=null||$("input[name='sampleName']").val()!=""){
                    params.name = $("input[name='sampleName']").val();
                    params.sampleCode = $("input[name='sampleCode']").val();
                    params.productTraceabilityCode = $("input[name='productTraceabilityCode']").val();
                    // }
                    // else {
                    //      params.name=$("input[name='productName']").val();
                    //  }
                    //地区
                    ObjShen = $("select[name='ObjShen']").val();
                    ObjShi = $("select[name='ObjShi']").val();
                    ObjXian = $("select[name='ObjXian']").val();
                    if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                        if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                            if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                                params.cityCode = ObjXian
                            } else {
                                params.cityCode = ObjShi
                            }
                        } else {
                            params.cityCode = ObjShen
                        }
                    }

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
                    width: "3%",
                    orderable: false
                },
                {
                    data: "year",
                    title: "任务年度",
                    width: "3%"
                },
                {
                    data: "sampleName",
                    title: "产品名称",
                    width: "5%"
                },
                {
                    data: "sampleCode",
                    title: "样品编码",
                    width: "7%"
                },

                {
                    data: "checkProject",
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
                    orderable: false
                },
                {
                    data: "result",
                    title: "检测结果",
                    width: "5%",
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
                // {
                //     data : "RMSTATE",
                //     title : "任务状态",
                //     width : "6%"
                // },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        return '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\',\''+row.sampleName+'\',\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\')" class="color">查看</a>  ';

                        // +
                        // '<a style="cursor:pointer" onclick="queryReports(\''+data+'\')" class="color">报告</a>  ' +
                        // '<a style="cursor:pointer" onclick="querySamples(\''+data+'\')" class="color">抽样单</a>';
                    },
                    width: "5%",
                    orderable: false
                }
            ],
        });

    }).error(function () {

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
    $scope.reloadArea = function ($event) {

        //重置 二次查询
        if ($event) {
            $($event.currentTarget).prev().text("查询");
        }
        $("input[name='checkProject']").val("");
        $("input[name='year']").val("");
        $("input[name='sampleName']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='sampleCode']").val("");
        $("input[name='productTraceabilityCode']").val("");
        $("checkbox[name='resultLimit']").prop("checked",true);
        // $("select[name='result']").prop("checked",false);
        document.getElementById('result').options[0].selected = true;
        $("select[name='hangye']").val("");
        // $("select[name='ObjShen']").val("");
        // $("select[name='ObjShi']").val("");
        // $("select[name='Objxian']").val("");
        document.getElementById('xian').options[0].selected = true;
        document.getElementById('shi').options[0].selected = true;
        document.getElementById('sheng').options[0].selected = true;

        $.fn.mycity("sheng", "shi", "xian", area);
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
            $("#sheng").attr("disabled", true);
        }
        if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
            $("#shi").attr("disabled", true);
        }
        if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
            $("#xian").attr("disabled", true);
        }

        $("#grid").dataTable().api().ajax.reload();
    };
    //获取行业下拉框
 /*   $http({
        url: "/sofn-qry-web/checkProject/getHangye",
        method: "post",
        // data:tokenId
    }).success(function (datax) {
        if (datax.httpCode == 200) {
            var datasx = datax.data;
            $scope.hangye = datasx;

        } else {
            jBox.tip("数据有误", 'info');
        }
    }).error(function () {
    });*/

    var urlIndustry = "/sofn-qry-web/adsMonitorTask/getIndustryTypeData";
    //行业下拉框
    $.ajax({
        url: urlIndustry,
        type: "post",
        dataType: "json",
        data: { phase: 1 },
        success: function(data) {
            $scope.industry = data.data;
            var Row = '<option value="">选择行业</option> ';
            for (var i = 0; i < $scope.industry.length; i++) {
                Row = Row + '<option value= "' + $scope.industry[i].dictName + '">' + $scope.industry[i].dictName + '</option>'
            }
            $("select[name='hangye']").html(Row);
        },
        fail: function(data) {
            $.jBox.tip("失败");
        }
    });

    //查询按钮
    $("#seach").click(function () {
        $("#checkList").dataTable().api().ajax.reload();
    });

       //获取检测项目


    window.checks = function () {
        $("#check_info").modal("show");
        //清理数据
        var tableName = $('#checkList');
        // var products=$('#products');
        if ($.fn.dataTable.isDataTable(tableName)) {
            // products.val().clear();
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $(function () {
            $("#checkList").dataTable({
                // fnDrawCallback: function () {
                //     //点击行内任意内容即选择行
                //     // $("#grid tbody tr").each(function (i, o) {
                //     //     $(o).click(function () {
                //     //         var checked = $(this).find("input[type='checkbox']")[0];
                //     //         if (checked.checked) {
                //     //             checked.checked = false;
                //     //             $(this).removeClass("selected");
                //     //         } else {
                //     //             checked.checked = true;
                //     //             $(this).addClass("selected");
                //     //         }
                //     //     });
                //     // })
                // },
                ajax: {
                    url: "/sofn-qry-web/checkProject/getCheckInfo",
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

                        return json.data.list;
                    },
                    data: function (params) {
                        // params.token = window.localStorage.getItem("token");
                        params.checkProject = $("input[name='checkname']").val();
                        // params.productName = type;
                        // params.dateBegin=$("input[name='datebegin']").val();
                        // params.dateEnd=$("input[name='dateend']").val();
                        // params.rmYear=$("input[name='queryYear']").val();
                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "checkName",
                        checkbox: true,
                        title: '<input id="checkall1" name="" type="checkbox" value="" />',
                        render: function (data, type, row, meta) {
                            // 数据操作选择项
                            // var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                            // return view;
                            return '<input id="ch' + meta.row + '" class="editor-active" type="checkbox" name="checkOne1"/>';
                        },
                        width: "1%"
                    },
                    {
                        data: 'id',
                        title: 'ID',
                        visible: false
                    },
                    {
                        title: "序号",
                        data: function (data, type, row, meta) {
                            return meta.row + 1
                        },
                        width: "10%"
                    },
                    {
                        data: "checkName",
                        title: "检测项目",
                        width: "30%"
                    },


                    // {
                    //     data: "productName",
                    //     title: "产品名称"
                    // },
                    // {
                    //     data : "ID",
                    //     title : "操作",
                    //     render : function(data, type, row) {
                    //         return '<a style="cursor:pointer" onclick="queryInfo(\''+data+'\')" class="color">详情</a>  <a style="cursor:pointer" onclick="queryReports(\''+data+'\')" class="color">报告</a>  <a style="cursor:pointer" onclick="querySamples(\''+data+'\')" class="color">抽样单</a>';
                    //     },
                    //     width : "10%"
                    // }
                ],
            });
        });

    }


    //查询按钮
    $("#chaxun").click(function () {
        $("#productList").dataTable().api().ajax.reload();
    });
//产品摸态框


    window.checkProduct = function () {
        $("#product_info").modal("show");

        //清理数据
        var tableName = $('#productList');
        // var products=$('#products');
        if ($.fn.dataTable.isDataTable(tableName)) {
            // products.val().clear();
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $(function () {
            $("#productList").dataTable({
                // fnDrawCallback: function() {
                //     //点击行内任意内容即选择行
                //     $("#grid tbody tr").each(function(i,o){
                //         $(o).click(function(){
                //             var checked = $(this).find("input[type='checkbox']")[0];
                //             if(checked.checked){
                //                 checked.checked = false;
                //                 $(this).removeClass("selected");
                //             }else {
                //                 checked.checked = true;
                //                 $(this).addClass("selected");
                //             }
                //         });
                //     })
                // },
                ajax: {
                    url: "/sofn-qry-web/checkProject/getSampleName",
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
                        params.name = $("input[name='name1']").val();
                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "productName",
                        checkbox: true,
                        title: '<input id="checkall" name="" type="checkbox" value="" />',
                        render: function (data, type, row, meta) {
                            // 数据操作选择项
                            // var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                            // return view;
                            return '<input id="c' + meta.row + '" class="editor-active" type="checkbox" name="checkOne"/>';
                        },
                        width: "1%"
                    },
                    {
                        data: "id",
                        title: 'ID',
                        visible: false
                    },
                    {
                        title : "序号",
                        data : function(data, type, row, meta) {
                            return meta.row + 1
                        },
                        width : "4%"
                    },
                    {
                        data: "productName",
                        title: "产品名称",
                        width: "15%"
                    },
                    {
                        data: "industry",
                        title: "所属行业",
                        width: "15%"
                    },


                ],
            });
        });

    }
    window.sure1 = function () {
        var ids = "";
        var rows = $("#checkList").DataTable().data();
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox1= document.getElementById('ch' + b);
            if (checkbox1.checked) {
                ids= ids + td.checkName + ",";
                num++;
            }
        }
        if (0 == num) {
            $.jBox.tip("至少选择一行！");
            return;
        }
        $scope.checkProject = ids;

        $("#check_info").modal("hide");
        $state.go("index.content.qry/checkProject/qryCheckProject")

    }

    window.sure = function () {
        var idss = "";
        var rows = $("#productList").DataTable().data();
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {

                idss = idss + td.productName + ",";
                num++;
                $scope.names = $('input[name="checkOne"]:checked ').val(idss);
            }
        }
        if (0 == num) {
            $.jBox.tip("至少选择一行！");
            return;
        }
        $scope.names = idss;
        console.log($scope.names)

        $("#product_info").modal("hide");
        $state.go("index.content.qry/checkProject/qryCheckProject")

    }






    /**
     * 全选任务数据
     */
    $("#checkall").click(function () {
        if ($(this).prop("checked") == true) {
            $(".editor-active").each(function () {
                if (!$(this).is(':disabled')) {
                    $("#grid input[type='checkbox']").prop("checked", true);
                    $("#grid tbody tr").addClass("selected");
                    $(this).prop('checked', true);
                }
            })
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    $("#checkall1").click(function () {
        if ($(this).prop("checked") == true) {
            $(".editor-active").each(function () {
                if (!$(this).is(':disabled')) {
                    $("#grid input[type='checkbox']").prop("checked", true);
                    $("#grid tbody tr").addClass("selected");
                    $(this).prop('checked', true);
                }
            })
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    /**
     * 任务列表
     * */
    //查询按钮
   /* $("#ToCheck").click(function () {
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
    // $(function () {
    //     $("#grid").dataTable({
    //         ajax: {
    //             url: "/sofn-qry-web/checkProject/getPageInfoParams",
    //             dataSrc: function (json) {
    //                 if (json.data == null || json.data == undefined) {
    //                     json.recordsTotal = 0;//总个数
    //                     json.recordsFiltered = 0;
    //                     json.pageNumber = 1;//页码
    //                     json.pageSize = 10;//每页个数
    //                     json.start = 0;//起始位置
    //                     json.length = 10;
    //                     return json.data;
    //                 }
    //                 json.recordsTotal = json.data.total;//总个数
    //                 json.recordsFiltered = json.data.total;
    //                 json.pageNumber = json.data.pageNum;//页码
    //                 json.pageSize = json.data.pageSize;//每页个数
    //                 json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
    //                 json.length = json.data.pageSize;
    //                 for (var i = 0; i < json.data.list.length; i++) {
    //                     //时间格式化
    //                     if (json.data.list[i].RMDATEBEGIN != null && json.data.list[i].RMDATEBEGIN != "" && json.data.list[i].RMDATEBEGIN != undefined) {
    //                         json.data.list[i].RMDATEBEGIN = new Date(parseInt(json.data.list[i].RMDATEBEGIN) + 28800000).toISOString().slice(0, 10);
    //                     }
    //                     if (json.data.list[i].RMDATEEND != null && json.data.list[i].RMDATEEND != "" && json.data.list[i].RMDATEEND != undefined) {
    //                         json.data.list[i].RMDATEEND = new Date(parseInt(json.data.list[i].RMDATEEND) + 28800000).toISOString().slice(0, 10);
    //                     }
    //                 }
    //                 return json.data.list;
    //             },
    //             data: function (params) {
    //                 params.token = window.localStorage.getItem("token");
    //                 params.checkProject = $("input[name='checkProject']").val();//检测项目
    //                 params.industry = $("select[name='hangye']").val();//行业
    //                 params.result = $("select[name='result']").val();//检测结果
    //                 params.startTime = $("input[name='datebegin']").val();//开始时间
    //                 params.endTime = $("input[name='dateend']").val();//结束时间
    //                 params.year = $("input[name='year']").val();//年份
    //                 params.name = $("input[name='sampleName']").val();
    //                 //地区
    //                 ObjShen = $("select[name='ObjShen']").val();
    //                 ObjShi = $("select[name='ObjShi']").val();
    //                 ObjXian = $("select[name='ObjXian']").val();
    //                 alert(ObjShen);
    //                 if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
    //                     if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
    //                         if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
    //                             params.cityCode = ObjXian
    //                         } else {
    //                             params.cityCode = ObjShi
    //                         }
    //                     } else {
    //                         params.cityCode = ObjShen
    //                         alert("sssssssss");
    //                     }
    //                 }
    //             },
    //             type: "post"
    //         },
    //         columns: [
    //             {
    //                 data: "id",
    //                 title: 'ID',
    //                 visible: false
    //             },
    //             {
    //                 title: "序号",
    //                 data: function (data, type, row, meta) {
    //                     return meta.row + 1
    //                 },
    //                 width: "3%"
    //             },
    //             {
    //                 data: "year",
    //                 title: "任务年度",
    //                 width: "3%"
    //             },
    //             {
    //                 data: "sampleName",
    //                 title: "产品名称",
    //                 width: "5%"
    //             },
    //             {
    //                 data: "sampleCode",
    //                 title: "样品编码",
    //                 width: "7%"
    //             },
    //
    //             {
    //                 data: "checkProject",
    //                 title: "检测项目",
    //                 width: "14.5%",
    //                 render:function (data,type,row) {
    //                     if(data!=null && data.length>30){
    //                         return "<a id='checkProject"+row.id+"' onmouseover=showPopContent('checkProject"+row.id+"')>"+data.substring(0,29)+"...</a>"+
    //                                 "<input value='" + data +"' type='hidden'/>";
    //                     }else {
    //                         return data;
    //                     }
    //                 }
    //             },
    //             {
    //                 data: "result",
    //                 title: "检测结果",
    //                 width: "5%",
    //                 render: function (data, type, row) {
    //                     if ("1" == data) {
    //                         return "合格";
    //                     } else if ("0" == data) {
    //                         return "不合格";
    //                     } else {
    //                         return "暂未检测";
    //                     }
    //                 }
    //             },
    //             {
    //                 data: "id",
    //                 title: "操作",
    //                 render: function (data, type, row) {
    //                     return '<a style="cursor:pointer" onclick="queryInfo(\'' + data + '\',\''+row.sampleName+'\',\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\')" class="color">查看</a>  ';
    //                 },
    //                 width: "5%"
    //             }
    //         ],
    //     });
    // });

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

    //导出excel
    $scope.exportData = function () {
        // var monitorClass=$scope.monitorClass;
        var tokenId = window.localStorage.getItem("token");
        var checkProject = $("input[name='checkProject']").val();//检测项目
        var industry = $("select[name='hangye']").val();//行业
        var result = $("select[name='result']").val();//检测结果
        var startTime = $("input[name='datebegin']").val();//开始时间
        var endTime = $("input[name='dateend']").val();//结束时间
        var year = $("input[name='year']").val();//年份
        var name = $("input[name='sampleName']").val();
        var sampleCode = $("input[name='sampleCode']").val();
        var productTraceabilityCode = $("input[name='productTraceabilityCode']").val();
        var cityCode = "";
        var ObjShen = $("select[name='ObjShen']").val();
        var ObjShi = $("select[name='ObjShi']").val();
        var ObjXian = $("select[name='ObjXian']").val();
        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    cityCode = ObjXian
                } else {
                    cityCode = ObjShi
                }
            } else {
                cityCode = ObjShen
            }
        }
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-qry-web/checkProject/getCheckListNew",
                    method: "post",
                    dataType: "json",
                    params: {"token": tokenId,"checkProject":checkProject,"industry":industry,"result":result,"startTime":startTime,"endTime":endTime,"year":year,
                    "name":name,"sampleCode":sampleCode,"productTraceabilityCode":productTraceabilityCode,"cityCode":cityCode},
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '检测项目.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});


    };


    /**
     * 任务操作
     * */
    //查看抽样单
    // window.querySamples=function(obj){
    //     window.localStorage.removeItem("asms_Check_checkTask_forReport");
    //     window.localStorage.setItem("asms_Check_checkTask_forReport",obj);
    //     window.localStorage.removeItem("asms_Check_Task_Type_Title");
    //     window.localStorage.setItem("asms_Check_Task_Type_Title",'LXJC');
    //     $state.go("index.content.asms/check/TaskSample/TaskSampleList");
    // };


    //查看详情页面
    window.queryInfo = function (data,data1,data2,data3) {
        $("#sampleName").val(data1);

        $("#sampleCode").val(data2);

        if("null" == data3){
            data3 = "";
        }
        $("#productTraceabilityCode").val(data3);

        // window.localStorage.setItem("ID",obj);
        // $scope.ID = obj;
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
            paging: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-qry-web/checkProject/getCkeckById",
                dataSrc: function (json) {
                    // console.log(json);
                    // json.recordsTotal=json.data.total;//总个数
                    // json.recordsFiltered=json.data.total;
                    // json.pageNumber=json.data.pageNum;//页码
                    // json.pageSize=json.data.pageSize;//每页个数
                    // json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    // json.length=json.data.pageSize;
                    return json.data;
                },
                data: function (params) {
                    params.id = data;
                    // params.token = window.localStorage.getItem("token");
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
                    render : function (data, type, row, meta) {
                        if (data == "null"){
                            data = "";
                        }
                        return data;
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
                        } else if("0" == data){
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

    //查看详情
    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });
    // window.queryInfo=function(o){
    //     $scope.routineMonitor = {};
    //     $("#TaskInfoModel").modal("show");
    //     $http({
    //         url:"/sofn-qry-web/checkProject/getCkeckById",
    //         method:"post",
    //         data:{"id":o}
    //     }).success(function(json){
    //         //任务
    //         $scope.routineMonitor = json.data;
    //         $scope.routineMonitor.rmDateBegin = new Date(parseInt(json.data.rmDateBegin)+28800000).toISOString().slice(0,10);
    //         $scope.routineMonitor.rmDateEnd = new Date(parseInt(json.data.rmDateEnd)+28800000).toISOString().slice(0,10);
    //         downloadFile(json.data.rmFile);
    //         //牵头单位
    //         $scope.routineMonitor.qtName = "";
    //         for (var i = 0; i < json.rouList.length ; i++){
    //             if ($scope.routineMonitor.qtName == ""){
    //                 $scope.routineMonitor.qtName += json.rouList[i].leadUnitName;
    //             }else {
    //                 $scope.routineMonitor.qtName += ","+json.rouList[i].leadUnitName;
    //             }
    //         }
    //     });
    // };

    //查看报告
    // window.queryReports=function(obj){
    //     window.localStorage.removeItem("asms_Check_checkTask_forReport");
    //     window.localStorage.setItem("asms_Check_checkTask_forReport",obj);
    //     window.localStorage.removeItem("asms_Check_Task_Type_Title");
    //     window.localStorage.setItem("asms_Check_Task_Type_Title",'LXJC');
    //     $state.go("index.content.asms/check/TaskReport/TaskReportList");
    // };
    //
    // //清除存储信息
    // $scope.cleanCookie=function(){
    //     window.localStorage.removeItem("asms_Check_routineMonitor");
    // };

    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };

    window.checkThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
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

    //附件下载
    window.downloadFile = function (o) {
        $("#File").html('<label class="col-lg-4 col-md-4 col-sm-4" style="text-align: right;padding-right: 10px">附件：</label>');
        if (o != undefined && o != null && o != "") {
            var rs;
            rs = o.split("|");
            console.info(rs);
            for (var i = 0; i < rs.length; i++) {
                if (rs[i] != undefined && rs[i] != null && rs[i] != "") {
                    var fileName = rs[i].substr(rs[i].lastIndexOf('\/') + 1);
                    $("#File").append('附件' + i + ':<a href="http://' + rs[i] + '">' + fileName + '</a>');
                }
            }
        } else {
            $("#File").append('无');
        }
    };
});
