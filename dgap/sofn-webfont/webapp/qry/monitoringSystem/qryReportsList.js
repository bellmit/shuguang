var myapp = angular.module("myapp", []);
myapp.controller("qryReportsList", function ($scope, $http, $state) {

    var token = window.localStorage.getItem("token");
    var taskName = window.localStorage.getItem("taskName");

    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    //重置
    $("#resetBtn").click(function () {
        $("input[name='fileName']").val("");
    });

    $(function(){
        //bootstrap表格
        $("#grid").dataTable({
            dom: '<"top">rt<"bottom"pli><"clear">',
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/adsMonitorTask/getPageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    console.log(json.data.list)
                    return json.data.list;
                },
                error : function (msg) {
                    console.log(msg);
                    
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                   // params.field = $('#industry option:selected').val();//行业
                   // params.monitorType = $('#category option:selected').val();//监测类型
                    //params.years = $('#year option:selected').val();  //查询年度
                    params.monitorTask = taskName;//任务名称
                    params.fileName = $("input[name='fileName']").val();    //文件名称
                    params.source="承担单位";
                    params.token=token;
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkAll' onclick='checkAll1()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='file' value='" +row.fileName +"=="+ row.fileAddress + "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "monitorTask",
                    title: "任务名称",
                    width:"14.5%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='monitorTask"+row.ID+"' onmouseover=showPopContent('monitorTask"+row.ID+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }
                },
                {
                    data: "monitorType",
                    title: "监测类型"
                },
                {
                    data: "years",
                    title: "年度"
                },
                {
                    data: "fileName",
                    title: "报告名称"
                },
                {
                    data: "uploadDepartment",
                    title: "报告单位"
                },
                {
                    data: "createTime",
                    title: "上传日期",
                    render: function (data, type, row) {
                        var time = new Date(data).format('yyyy-MM-dd');
                        return time;
                    }
                },
                {
                    data : "id",
                    title : "样品及检测信息",
                    render : function(data, type, row) {
                        if (data == null){
                            return null;
                        }else {
                            return '<a style="cursor:pointer" onclick="querySampleJcInfoList(\''+data.trim()+'\')">查看</a>';
                        }
                    },
                    width : "7%"
                },
                {
                    data: "fileAddress",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        /*var look_a = '<a href="' + row.fileAddress + '"  download="' + row.fileName + '"  target="_blank" >下载</a>';*/
                        var look_a = '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\''+row.fileAddress+"','"+row.fileName+'\')">下载</a>';
                        return look_a;
                    }
                },
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
    });

    window.querySampleJcInfoList=function(){
        $state.go("index.content.qry/monitoringSystem/qryTestListInfo");
    }

    window.downloadFile=function(pdfAddress,filename){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', pdfAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', filename);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    /**
     * 批量下载检测项目
     */
    $scope.batchDownload = function () {
        var check = $("input[name='file']:checked");
        console.log(check);
        if (check.length > 0) {
            var arr = new Array();
            var batchFileName = "";
            var batchFileAddress = "";
            $(check).each(function(){
                var file = $(this)[0];
                var fileLink = $(file).val();
                console.log(fileLink);
                var doc = new Array();
                doc = fileLink.split("==");
                console.log(doc[0]);
                console.log(doc[1]);
                batchFileName += doc[0];
                batchFileName += ",";
                batchFileAddress += doc[1];
                batchFileAddress += ",";
                /*try {
                 var a = document.getElementById("downFile");
                 a.href = doc[1];
                 a.click();
                 } catch (e) {
                 jBox.tip("下载错误！", "error");
                 }*/

            });
            var form = $("<form>");
            form.attr('style', 'display:none');
            form.attr('target', '');
            form.attr('method', 'post');
            form.attr('action', "/sofn-ads-web/adsFile/fileBatchDownload");
            var input1 = $('<input>');
            input1.attr('type', 'hidden');
            input1.attr('name', 'fileUrl');
            input1.attr('value', batchFileAddress.toString());
            var input2 = $('<input>');
            input2.attr('type', 'hidden');
            input2.attr('name', 'fileName');
            input2.attr('value', batchFileName.toString());
            $('body').append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
            // 自定义按钮
            //$.jBox.confirm("确认要删除所选检测项目吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        } else {
            jBox.tip("请先选择报告", 'info');
        }
    }

    $scope.rebackBtn = function () {
        $state.go("index.content.qry/monitoringSystem/monitorTaskList");
    }
});
