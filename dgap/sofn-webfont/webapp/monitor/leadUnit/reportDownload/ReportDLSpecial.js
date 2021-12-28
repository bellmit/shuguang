/**
 * Created by leipe on 2016/11/2.
 */
var myapp = angular.module("myapp", []);
myapp.controller("ReportDLSpecial", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");
    $("#routine").click(function () {
        $state.go('index.content.monitor/leadUnit/reportDownload/ReportDLRoutine')
    });

    $("#special").click(function () {
        $state.go("index.content.monitor/leadUnit/reportDownload/ReportDLSpecial")
    });

    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear() - 7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for (var i = startDate; i <= endDate; i++) {
        $scope.year.push(i);
    }

    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsFile/getPageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                    params.years = $('#year option:selected').val();  //查询年度
                    params.monitorTask = $('#taskName option:selected').val();
                    params.monitorType = "专项监测";
                    params.source = "承担单位";
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='file' value='"+row.fileName +"==" + row.fileAddress + "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "monitorTask",
                    width: "14.5%",
                    title: "任务名称",
                    render: function (data, type, row) {
                        if (data != null && data.length > 30) {
                            return "<a id='monitorTask" + row.ID + "' onmouseover=showPopContent('monitorTask" + row.ID + "','" + data + "')>" + data.substring(0, 29) + "...</a>";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "uploadDepartment",
                    title: "报告单位"
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
                    data: "createTime",
                    title: "创建时间",
                    render: function (data, type, row) {
                        var time = new Date(row.createTime).format('yyyy-MM-dd');
                        return time;
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        /* var look_a = '<a href="' + row.fileAddress + '"  class="" download="' + row.fileName + '"  target="_blank">下载</a>';*/
                        var look_a = '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\'' + row.fileAddress + "','" + row.fileName + '\')">下载</a>';
                        return look_a;
                    }
                },
            ],
        });

        $http({
            url: "/sofn-ads-web/adsFile/getTask",
            method: "post",
            dataType: "json",
            params: {folder: "no", monitorType: "专项监测", source: "承担单位", token: token},
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
        }).success(function (data) {
            $scope.seachTask = data.data;
        });

        //字符串逆转
        function strturn(str) {
            if (str != "") {
                var str1 = "";
                for (var i = str.length - 1; i >= 0; i--) {
                    str1 += str.charAt(i);
                }
                return (str1);
            }
        }

        //鼠标悬停弹窗显示
        window.showPopContent = function (id, content) {
            $("#" + id).popover({
                animation: true,
                html: false,
                placement: 'right',
                trigger: 'hover',
                content: content,
                delay: 0,
                container: 'body'
            });
            $("#" + id).popover("show");
        }

        //取文件后缀名
        function GetFileExt(filepath) {
            if (filepath != "") {
                var pos = "." + filepath.replace(/.+\./, "");
                return pos;
            }
        }

        function GetFileNameNoExt(filepath) {
            var pos = strturn(GetFileExt(filepath));
            var file = strturn(filepath);
            var pos1 = strturn(file.replace(pos, ""));
            var pos2 = GetFileName(pos1);
            return pos2;
        }

        //取文件全名名称
        function GetFileName(filepath) {
            if (filepath != "") {
                var names = filepath.split("\\");
                return names[names.length - 1];
            }
        }

        window.downloadFile = function (pdfAddress, filename) {
            var file = GetFileNameNoExt(filename);
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
            input2.attr('value', file);
            $('body').append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
        }

    });

    //重置
    $scope.resetBtn = function () {
        $(".gui-select select").val("");
        $("#grid").dataTable().api().ajax.reload();
    }

    /**
     * 批量下载检测项目
     */
    $scope.batchDownload = function () {
        var check = $("input[name='file']:checked");
        if (check.length > 0) {
            var arr = new Array();
            $(check).each(function () {
                var arr = new Array();
                var batchFileName = "";
                var batchFileAddress = "";
                $(check).each(function(){
                    var file = $(this)[0];
                    var fileLink = $(file).val();
                    var doc = new Array();
                    doc = fileLink.split("==");
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

            });
        } else {
            jBox.tip("请先选择报告", 'info');
        }
    }


    /**
     * 全选任务数据
     */
    function checkAll() {
        var val = $("input[name='checkAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='file']").attr("checked", true);
        } else {
            $("input[name='file`']").attr("checked", false);
        }
    }

})

