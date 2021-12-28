var myapp = angular.module("myapp", []);
myapp.controller("sysCodeRuleView", function ($scope, $http, $state) {
    //监听浏览器变化重新加载
    $(window).resize(function () {
        $("#grid").dataTable().api().ajax.reload();
    })
//分页
    $(function () {
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            //searching : true,
            //bProcessing: true,
            //paging:true,
            //bInfo:true,
            //bJQueryUI: true,
            //bPaginate: false,// 分页按钮
            //bFilter: true,// 搜索栏
            //iDisplayLength: 10,// 每页显示行数
            //bSort: false,// 排序
            dom: '<"top">rt<"bottom"pli><"clear">',
            bAutoWidth: false,
            //sScrollX :true,
            sScrollY: "auto",
            autoWidth: true,
            ajax: {
                url: "/sofn-sys-web/sysCodeRule/read/list",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    console.log(json);
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.length; i++) {
                        if (json.data[i].INSPECTION_TIME != null && json.data[i].INSPECTION_TIME != "" && json.data[i].INSPECTION_TIME != undefined) {
                            json.data[i].INSPECTION_TIME = new Date(parseInt(json.data[i].INSPECTION_TIME) + 1000 * 60 * 60 * 24).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    /*params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");*/
                    params.resourcetype = $("select[name='resourceType']").val();
                    params.professionalFiled = $("select[name='professionalFiled']").val();
                    params.Location = $("select[name='Location']").val();
                    params.enterpriseName = $("input[name='enterpriseName']").val();
                },
                type: "POST",
            },
            columns: [
                {
                    data: "ck",
                    checkbox: true,
                    title: '<input id="checkall" name="" type="checkbox" value="" onclick="checkAll(this)" />',
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<input type="checkbox" value="'
                            + row.id
                            + '" onclick="checkClick(this)" />';
                    },
                    width: "40px"
                },

                {
                    data: "RESOURCETYPE",
                    title: "名词"
                },
                {
                    data: "RESOURCETYPE",
                    title: "相关解释"
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
            sPaginationType: "full_numbers",
        });
    });
    window.checkAll = function (obj) {
        alert(obj)
        console.log(obj)
    }
    //选中一行
    window.checkClick = function (ck) {
        $($(ck).parent().parent()[0]).toggleClass('selected');
        var e = window.event || arguments.callee.caller.arguments[0];
        if (e && e.stopPropagation) {
            e.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
    }
})



