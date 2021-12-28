/**
 * Created by leipe on 2016/11/2.
 */
var myapp = angular.module("myapp", []);
myapp.controller("reportSummary", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");

    var fileId = "";
    var fileName = "";
    var fileAddress = "";
    /**
     * 全选任务数据
     */
    window.checkAll1 = function () {
        var val = $("input[name='checkAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='file']").attr("checked", true);
        } else {
            $("input[name='file']").attr("checked", false);
        }
    }

    //绑定回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $("#grid").dataTable().api().ajax.reload();
        }
    };

    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    //重置
    $("#resetBtn").click(function () {
        $(".gui-select select").val("");
        $(".gui-select").val("");
        $('.setslist ul li').remove();
        $("[name=fileName]").val("");
        $("#grid").dataTable().api().ajax.reload();
    })


    $scope.year = [];

    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    /*$scope.industry = ["全部", "蔬菜", "茶叶", "水果", "畜禽", "水产"];*/
    /*$scope.category = ["全部", "例行监测", "专项监测", "监督抽查"];*/
  /*  $scope.taskName = ["全部", "例行监测任务1", "例行监测任务2", "例行监测任务3", "例行监测任务4"];*/

    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            dom: '<"top">rt<"bottom"pli><"clear">',
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
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
                    params.field = $('#industry option:selected').val();//行业
                    params.monitorType = $('#category option:selected').val();//监测类型
                    params.years = $('#year option:selected').val();  //查询年度
                    params.monitorTask = $('#taskName option:selected').val();//任务名称
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
                    data: "reportStatus",
                    title: "报告状态",
                    render: function (data, type, row) {
                        return row.reportStatus == "1" ? "退回" : "已提交";
                    }
                },
                {
                    data: "fileAddress",
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
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");






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
            if (check.length > 0) {
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
            } else {
                jBox.tip("请先选择报告", 'info');
            }
        }






    });
    /*var url = "/sofn-ads-web/adsFile/getType?time=" + new Date();
    //行业下拉框
    $http({
        url: url,
        method: "post",
        data: {"phase": 1},
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function (datax) {
        /!*$scope.ads=datax.a;
         $scope.monitorTask=datax.b;*!/
        var datasx = datax.data;
        $scope.category=datasx;

    }).error(function (data) {
        jBox.tip("数据有误", 'info');
    });*/

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

    $scope.category = ["例行监测","专项监测","监督抽查","受托检测"];

    var url = "/sofn-ads-web/adsFile/getField";
    //行业下拉框
/*    $http({
        url: url,
        method: "post",
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function (datax) {
        /!*$scope.ads=datax.a;
         $scope.monitorTask=datax.b;*!/
        var datasx = datax.data;
        $scope.industry=datasx;

    }).error(function (data) {
        jBox.tip("数据有误", 'info');
    });*/

/*    $http({
        url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
        method:"post",
        dataType:"json",
    }).success(function(data){
        $scope.industry = data.data;
    });*/


  /*  var url = "/sofn-ads-web/adsMonitorTask/getHangye?time=" + new Date();*/
    var url = "/sofn-ads-web/adsDictData/getIndustryTypeData";
    //行业下拉框
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data: { phase: 1 },
        success: function(data) {
           /* var datas = data.data;
            var Row = "<option value=''>全部</option> ";
            if (datas.length > 0) {
                for (var i = 1; i <= datas.length; i++) {
                    var td = datas[i - 1];
                    Row = Row + "<option>" + td.industry + "</option> ";
                }
            }
            $("#industry").html(Row);*/
            $scope.industry = data.data;
            var Row = "<option value=''>全部</option> ";
            for (var i = 0; i < $scope.industry.length; i++) {
                Row = Row + '<option value= "' + $scope.industry[i].dictName + '">' + $scope.industry[i].dictName + '</option>'
            }
            $("#industry").html(Row);
        },
        fail: function(data) {
            $.jBox.tip("失败");
        }
    });

    $("#industry").change(function(){
        var  url = "/sofn-ads-web/adsFile/getTaskNameByType";
        var field=$('#industry option:selected').val();
        var type=$('#category option:selected').val();
        type = type == "" ? "全部" : type;
        $http({
            url:url,
            method:"post",
            data: field,
            params : {token:token,industry:field,type:type},
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            var datasx = datax.data;
            $scope.monitorTask=datasx;
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
    })

    $("#category").change(function(){
        var  url = "/sofn-ads-web/adsFile/getTaskNameByType";
        var field=$('#industry option:selected').val();
        var type=$('#category option:selected').val();
        type = type == "" ? "全部" : type;
        $http({
            url:url,
            method:"post",
            data: field,
            params : {token:token,type:type,industry:field},
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            var datasx = datax.data;
            $scope.monitorTask=datasx;
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
    })

    var field=$('#industry option:selected').val();
    var  url = "/sofn-ads-web/adsFile/getTaskName";
    //任务名称下拉框
    $http({
        url:url,
        method:"post",
        data: field,
        params : {token:token},
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function (datax) {
        var datasx = datax.data;
        $scope.monitorTask=datasx;
    }).error(function (data) {
        jBox.tip("数据有误", 'info');
    });
//根据条件刷新表格
    $(document).ready(function(){
        if($("#industry").text!="全部"){
            $("#industry").change(function(){
                $("#grid").dataTable().api().ajax.reload();
            });
        }
        if($("#category").text!="全部") {
            $("#category").change(function () {
                $("#grid").dataTable().api().ajax.reload();
            });
        }if($("#year").text!="全部") {
            $("#year").change(function () {
                $("#grid").dataTable().api().ajax.reload();
            });
        }if($("#taskName").text!="全部") {
            $("#taskName").change(function () {
                $("#grid").dataTable().api().ajax.reload();
            });
        }
    });

    //行业---------------------------------------------------------------------------
    var selectIndustry = document.getElementById('industry');

    selectIndustry.onchange = function () {
        var industry = $('#industry option:selected').text();
        if ("全部" != industry) {
            if (document.getElementById('industry1')) {
                var obj = document.getElementById("industry1");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="industry1" style="display: block;float: left">' + '报告汇总：' + industry + '<button class="gui-btn" id="industry" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength = $(".setslist ul li").length;
            if (lilength == 0) {
                $(".setslist ul").prepend(lihtml);
            } else {
                $(".setslist ul li:last").after(lihtml);
            }
        } else {
            if (document.getElementById('industry1')) {
                var obj = document.getElementById("industry1");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
        $("#grid").dataTable().api().ajax.reload();
    });


    //监测类型---------------------------------------------------------------------------
    var selectCategory = document.getElementById('category');

    selectCategory.onchange = function () {
        var category = $('#category option:selected').text();
        if ("全部" != category) {
            if (document.getElementById('category1')) {
                var obj = document.getElementById("category1");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="category1" style="display: block;float: left">' + '报告汇总：' + category + '<button class="gui-btn" id="category" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength = $(".setslist ul li").length;
            if (lilength == 0) {
                $(".setslist ul").prepend(lihtml);
            } else {
                $(".setslist ul li:last").after(lihtml);
            }
        } else {
            if (document.getElementById('category1')) {
                var obj = document.getElementById("category1");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;

    });


    //年度---------------------------------------------------------------------------
    var selectYear = document.getElementById('year');

    selectYear.onchange = function () {
        var year = $('#year option:selected').text();
        if ("全部" != year) {
            if (document.getElementById('year1')) {
                var obj = document.getElementById("year1");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="year1" style="display: block;float: left">' + '报告汇总：' + year + '<button class="gui-btn" id="year" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength = $(".setslist ul li").length;
            if (lilength == 0) {
                $(".setslist ul").prepend(lihtml);
            } else {
                $(".setslist ul li:last").after(lihtml);
            }
        } else {
            if (document.getElementById('year1')) {
                var obj = document.getElementById("year1");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });

    //任务名称---------------------------------------------------------------------------
    var selectTaskName = document.getElementById('taskName');

    selectTaskName.onchange = function () {
        var taskName = $('#taskName option:selected').text();
        if ("全部" != taskName) {
            if (document.getElementById('taskName1')) {
                var obj = document.getElementById("taskName1");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="taskName1" style="display: block;float: left">' + '报告汇总：' + taskName + '<button class="gui-btn" id="taskName" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength = $(".setslist ul li").length;
            if (lilength == 0) {
                $(".setslist ul").prepend(lihtml);
            } else {
                $(".setslist ul li:last").after(lihtml);
            }
        } else {
            if (document.getElementById('taskName1')) {
                var obj = document.getElementById("taskName1");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });


//清除筛选
    $(".cq2").on("click", "button", function () {
        $('.setslist ul li').remove();
        document.getElementById('taskName').options[0].selected = true;
        document.getElementById('year').options[0].selected = true;
        document.getElementById('category').options[0].selected = true;
        document.getElementById('industry').options[0].selected = true;
        $("#grid").dataTable().api().ajax.reload();

    });


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

})
