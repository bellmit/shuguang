/**
 * Created by leipe on 2016/11/2.
 */
var myapp = angular.module("myapp", []);
myapp.controller("reportReviewRoutine", function ($scope, $http, $state) {

    var token = window.localStorage.getItem("token");

    $("#special").click(function () {
        $state.go("index.content.monitor/leadUnit/reportReview/reportReviewSpecial")
    });

    var monitorTaskId = "";
    var monitorType = "例行监测";
    var fileId = "";
    var fileName = "";
    var fileAddress = "";

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
                    params.years = $('#year option:selected').val();  //查询年度
                    params.monitorTaskId = monitorTaskId;    //任务ID
                    params.monitorType = monitorType;
                    params.source = "牵头单位";
                    params.folder = "no";
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "years",
                    title: "年度"
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
                    data: "fileName",
                    title: "报告名称"
                },
                {
                    data: "createTime",
                    title: "上传时间",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        return new Date(row.createTime).format('yyyy-MM-dd');
                    }
                },
                {
                    data: "reportStatus",
                    title: "报告状态",
                    render: function (data, type, row) {
                        return row.reportStatus == "1" ? "退回" : "已提交";
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = "";
                        if (row.reportStatus == "1")
                            look_a += '<a class="color " style="margin-right: 10px;cursor: pointer;" class="Check-report" onclick="uploadReport(\'' + row.id + "','" + row.years + "','" + row.monitorTask + '\')">重新提交</a>';
                        else
                            look_a += '<span class="color " style="margin-right: 10px;" class="Check-report" >重新提交</span>';
                        look_a += '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\'' + row.fileAddress + "','" + row.fileName + '\')">下载</a>';

                        return look_a;
                    }
                },
            ],
        });

        window.downloadFile = function (pdfAddress, filename) {
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
    });

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

    window.uploadReport = function (id, year, monitorTask) {
        $('#yearModel').val(year);
        fileId = id;
        $("#taskNameModel").text(monitorTask);
        $("#fileModal").modal("show");
    };


    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-ads-web/adsFile/upload',
        //业务参数
        data: function () {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function (file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (imageTypes.test(file.type)) {
                swal('', '只能上传“doc，docx，pdf，xls，xlsl，rar，zip” 类型文档', 'warning');
                return false;
            }

            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.addAdsFile = function () {
        if (vm.files.length <= 0) {
            alert("附件没上传,请先进行上传操作");
            return false;
        }
        fileName = vm.files[0].name;
        fileAddress = vm.files[0].src;
        var yearModel = $("#yearModel").val();
        $.ajax({
            url: "/sofn-ads-web/adsFile/updateAdsFile",
            type: "post",
            dataType: "json",
            data: {
                "id": fileId, "fileAddress": fileAddress, "fileName": fileName, "years": yearModel, "token": token
            },
            success:
                function (data) {
                    if (data.httpCode == 200) {
                        jBox.tip("上传成功", 'success');
                        $("#fileModal").modal("hide");
                        location.reload();
                    } else {
                        jBox.tip("上传失败", 'error');
                    }
                }

        });
    }

    $(document).ready(function () {
        $('#year').change(function () {
            $("#grid").dataTable().api().ajax.reload();
        });
    });

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.selectTaskName = function () {
        $("#taskNameModal").modal("show");
        setTimeout(function () {
            if (selectTaskNameFlag == 0) {
                loadTaskName();
            } else {
                $("#taskNameGrid").dataTable().api().ajax.reload();
            }
        }, 300)
    };

    /**
     * 重新任务名称数据
     */
    $("#seachTaskName").click(function () {
        $("#taskNameGrid").dataTable().api().ajax.reload();
    });

    /**
     * 加载任务名称数据
     */
    function loadTaskName() {
        //bootstrap表格
        $("#taskNameGrid").dataTable({
            ajax: {
                url: "/sofn-ads-web/adsMonitorTask/getPageInfo",
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
                    params.year = $("#year option:selected").val();
                    params.monitorClass = monitorType;
                    params.taskName = $("input[name='sTaskName']").val();
                    params.source = "牵头单位";
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data: "taskName",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn btn-sm" onclick="checkTaskName(\'' + row.taskName + "\',\'" + row.id + '\')">选择</button>';
                        return look_a;
                    }
                },
            ],
        });
        selectTaskNameFlag++;
    }

    /**
     * 选择任务名称
     * @param row
     */
    window.checkTaskName = function (taskName, id) {
        $("input[name='taskName']").val(taskName);
        monitorTaskId = id;
        $("#taskNameModal").modal("hide");
        $("#grid").dataTable().api().ajax.reload();
    };

    //重置
    $scope.resetBtn = function () {
        $(".gui-select select").val("");
        $(".gui-input").val("");
        monitorTaskId = "";
        $("#grid").dataTable().api().ajax.reload();
    }

    window.check = function (e) {
        var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        if (e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入30个汉字或者60个字母/数字");
                e.value = "";
                e.focus();
            }
        }
    }

})
