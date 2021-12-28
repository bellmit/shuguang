var myapp = angular.module("myapp", []);
myapp.controller("sampleMessageRecheck", function ($scope, $http, $state) {
    $scope.taskName = window.localStorage.getItem("taskName");//监测任务名称
    var organTaskId = window.localStorage.getItem("organTaskId");//机构任务ID
    $scope.monitorClass = window.localStorage.getItem("monitorClass")=="undefined"?"复检任务":window.localStorage.getItem("monitorClass");//监测任务
    if (window.localStorage.getItem("numbers") == "null" || window.localStorage.getItem("numbers") == "undefined") {
        $scope.numbers = 0;
    } else {
        $scope.numbers = window.localStorage.getItem("numbers");//任务数
    }
    if (window.localStorage.getItem("sampleFinishNum") == "null" || window.localStorage.getItem("sampleFinishNum") == "undefined") {
        $scope.sampleFinishNum = 0;
    } else {
        $scope.sampleFinishNum = window.localStorage.getItem("sampleFinishNum");//任务截至时间
    }
    $scope.deadline = window.localStorage.getItem("deadline")=="undefined"?"":window.localStorage.getItem("deadline");//任务截至时间
    // alert("organTaskId="+organTaskId);
    $scope.sampleCode = "";
    $scope.innocuity = "";
    $scope.green = "";
    $scope.organic = "";
    $scope.other = "";
    $scope.base = "";
    $scope.slaughter = "";
    $scope.market = "";
    $scope.wholesale = "";
    $scope.supermar = "";
    $scope.else = "";
    $scope.production = "";
    $scope.stock = "";
    $scope.sampleName = "";
    $scope.testedDeparment = "";
    $scope.productTraceabilityCode = "";
    $scope.checkLink = "";
    $scope.toCheck = function () {
        $("#grid").dataTable().api().ajax.reload();
    }
    // $http({
    //     // url:"/sofn-ads-web/adsOrganTask/findAllAdsOrganTask",
    //     // method:"post",
    //     // params:{id:$scope.subjSvId}
    // }).success(function (data) {
    //
    //
    // }).error(function () {
    // });

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/resTaskDetail")
    }
    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/testListRecheck")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck")
    }
    $scope.routineReportUpload = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/recheckReportUpload")
    }

    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            // fnDrawCallback: function() {
            //     //点击行内任意内容即选择行
            //     $("#grid tbody tr td").each(function(i,o){
            //         $(o).click(function(){
            //             var checked = $(this).parent().find("input[name='checkOne']")[0]
            //             if(checked.checked){
            //                 checked.checked = false;
            //                 $(this).parent().removeClass("selected");
            //             }else {
            //                 checked.checked = true;
            //                 $(this).parent().addClass("selected");
            //             }
            //         });
            //     })
            // },
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getRecheckSamplePageInfo",
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
                    if (organTaskId == null || organTaskId == "")
                        params.organTaskId = -1;
                    else
                        params.organTaskId = organTaskId;
                    if ($('#sampleOrgan option:selected').text() != "全部") {//抽样单位
                        params.sampleOrgan = $('#sampleOrgan option:selected').text();
                    }
                    params.sampleCode = $scope.sampleCode;    //抽样编码
                    params.sampleName = $scope.sampleName;    //样品名称
                    var sampleDate = $("input[name='sampleDate']").val();
                    if (sampleDate != "") {
                        params.sampleDate = sampleDate + "-01-01" + " 00:00:00"
                    }
                    if ($('#taskName option:selected').text() != "全部") {
                        params.taskName = $('#taskName option:selected').text();
                    }
                    if ($('#producingAreaName option:selected').text() != "全部") {
                        params.producingAreaName = $('#producingAreaName option:selected').text();
                    }
                },
                type: "post"
            },
            columns: [
                {
                    data: "ID",
                    checkbox: true,
                    title: '<input id="checkAll" type="checkbox" value="" onclick="checkAll(this)" />',
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (row.SAMPLE_REPORT == "2") {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '" disabled/>';
                        } else {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '"/>';
                        }

                    }
                },
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (data!=null){
                            return '<input readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }else {
                            data = "";
                            return '<input readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }

                    }
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    render: function (data) { // 模板化列显示内容
                        return '<a name="SAMPLE_CODE"   onclick="checkClick(\'' + data + '\')" >' + data + '</a>';
                    }
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位"
                },
                {
                    data: "CITY_CODE",
                    title: "抽样地区",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.CITY_CODE != undefined) {
                            return $.fn.Cityname(row.CITY_CODE)
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "PRODUCING_AREA",
                    title: "样品产地",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.PRODUCING_AREA != undefined) {
                            return $.fn.Cityname(row.PRODUCING_AREA)
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "SAMPLE_DATE",
                    title: "抽样日期"
                    // render: function (data, type, row) { // 模板化列显示内容
                    // return new Date(data).getFullYear() + "-" + (new Date(data).getMonth()+1) + "-" + (new Date(data).getDay()-1);
                    //  }
                }
            ],
        });
    });

    //页面跳转到详情
    window.checkClick = function (data) {
        window.localStorage.setItem("sampleCode", data);
        window.localStorage.setItem("taskName", $scope.taskName);
        window.localStorage.setItem("organTaskId", organTaskId);
        $state.go("index.content.monitor/execUnit/Recheck/sampleDetailRecheck")
    };

    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

//抽样机构下拉选
    var url = "/sofn-ads-web/adsOrganTask/findSampleOrgan?time=" + new Date();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        success: function (data) {
            var data = data;
            var Row = '<option>全部</option> ';
            if (data.length > 0) {
                for (var i = 1; i <= data.length; i++) {
                    var td = data[i - 1];
                    Row = Row + '<option>' + td.sampleOrgan + '</option> ';

                }
            }
            $("#sampleOrgan").html(Row);
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });

    //查询任务名称
    var url = "/sofn-ads-web/adsMonitorTask/selectTaskName?time=" + new Date();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        success: function (data) {
            var data = data.data;
            var Row = '<option>全部</option> ';
            if (data.length > 0) {
                for (var i = 1; i <= data.length; i++) {
                    var td = data[i - 1];
                    Row = Row + '<option>' + td + '</option> ';

                }
            }
            $("#taskName").html(Row);
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });

    //抽样单位下拉选
    var url = "/sofn-ads-web/adsMonitorSample/findDepartment?time=" + new Date();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        success: function (data) {
            var data = data;
            var Row = '<option>全部</option> ';
            if (data.length > 0) {
                for (var i = 1; i <= data.length; i++) {
                    var td = data[i - 1];
                    Row = Row + '<option>' + td.sampleDeparment + '</option> ';
                }
            }
            $("#sampleDeparment").html(Row);
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });
    $.fn.mycity("sheng", "shi", "xian", 0, 0, 0);
    $.fn.mycity("sheng1", "shi1", "xian1", 0, 0, 0);


    //重置
    $scope.reset = function () {
        window.location.reload();
    }

    /**
     * 导出监测任务excel数据
     */
    $scope.exportExcel = function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/downloadMonitorRecheckSample",
                    method: "post",
                    dataType: "json",
                    params: {"start": 1, "length": 5000,"organTaskId":organTaskId},
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
                        a.setAttribute('download', '抽样信息管理表.xls');
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
    }
    function dateFormatUtil(longTypeDate) {
        var dateTypeDate = "";
        var date = new Date();
        date.setTime(longTypeDate);
        dateTypeDate += date.getFullYear();   //年
        dateTypeDate += "-" + getMonth(date); //月
        dateTypeDate += "-" + getDay(date);   //日
        return dateTypeDate;
    }

    //返回 01-12 的月份值
    function getMonth(date) {
        var month = "";
        month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    //返回01-30的日期
    function getDay(date) {
        var day = "";
        day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $(".editor-active").each(function () {
                if (!$(this).is(':disabled')) {
                    $(this).prop('checked', true);
                }
            })
            // $("#grid input[type='checkbox']").prop("checked", true);
            // $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });
    //获取选择行记录id
    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().data();
        var ids = [];
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {
                ids.push(rows[i - 1].ID);
                num++;
            }
        }
        if (num == 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        return ids;
    }
    //获取选择行SAMPLE_CODE记录
    $scope.getSelectRowsSampleCode = function () {
        var rows = $("#grid").DataTable().data();
        var ids = [];
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {
                ids.push(rows[i - 1].SAMPLE_CODE);
                num++;
            }
            // var rows = $("#grid").DataTable().rows('.selected').data();
            // var length = rows.length;
            // if(length<=0){
            //     alert("请至少选择一行记录！");
            //     return false;
            // }
            // var ids = [];
            // for (var i = 0; i < length; i++) {
            //     ids.push(rows[i].SAMPLE_CODE);
            // }
            // return ids;
        }
        if (num > 1) {
            $.messager.show({
                title: "提示信息",
                msg: "只能选择一行记录修改！",
                showType: "slide",
                timeout: 5000
            });
        } else if (0 == num) {
            $.messager.show({
                title: "提示信息",
                msg: "至少选择一行记录修改！",
                showType: "slide",
                timeout: 5000
            });
        }
        return ids;
    }
    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    }

    // var sheng = document.getElementById('sheng');
    // sheng.onchange = function () {
    //     var sheng = $('#sheng option:selected').text();
    //     if ("全部" != sheng) {
    //         if (document.getElementById('yli')) {
    //             var obj = document.getElementById("yli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //         var lihtml = '<li style="margin: 10px;float: left" id="yli">' + sheng + '<button id="sheng" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
    //         var lilength = $(".setslist ul li").length;
    //         if (lilength == 0) {
    //             $(".setslist ul").prepend(lihtml);
    //         } else {
    //             $(".setslist ul li:last").after(lihtml);
    //         }
    //     } else {
    //         if (document.getElementById('yli')) {
    //             var obj = document.getElementById("yli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //     }
    // }
    // var shi = document.getElementById('shi');
    // shi.onchange = function () {
    //     var shi = $('#shi option:selected').text();
    //     if ("全部" != shi) {
    //         if (document.getElementById('eli')) {
    //             var obj = document.getElementById("eli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //         var lihtml = '<li style="margin: 10px;float: left" id="eli">' + shi + '<button id="shi" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
    //         var lilength = $(".setslist ul li").length;
    //         if (lilength == 0) {
    //             $(".setslist ul").prepend(lihtml);
    //         } else {
    //             $(".setslist ul li:last").after(lihtml);
    //         }
    //     } else {
    //         if (document.getElementById('eli')) {
    //             var obj = document.getElementById("eli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //     }
    // }
    // var xian = document.getElementById('xian');
    // xian.onchange = function () {
    //     var xian = $('#xian option:selected').text();
    //     if ("全部" != xian) {
    //         if (document.getElementById('sli')) {
    //             var obj = document.getElementById("sli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //         var lihtml = '<li style="margin: 10px;float: left" id="sli">' + xian + '<button id="xian" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
    //         var lilength = $(".setslist ul li").length;
    //         if (lilength == 0) {
    //             $(".setslist ul").prepend(lihtml);
    //         } else {
    //             $(".setslist ul li:last").after(lihtml);
    //         }
    //     } else {
    //         if (document.getElementById('sli')) {
    //             var obj = document.getElementById("sli");
    //             obj.parentNode.removeChild(obj)
    //         }
    //     }
    // }

    function get_unix_time(dateStr) {
        var newstr = dateStr.replace(/-/g, '/');
        var date = new Date(newstr);
        var time_str = date.getTime().toString();
        return time_str.substr(0, 10);
    }

    $(".setslist").on("click", "button", function () {
        var $this = $(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });

    $(".cq2").on("click", "button", function () {
        $('.setslist ul li').remove();
        document.getElementById('sheng').options[0].selected = true;
        document.getElementById('shi').options[0].selected = true;
        document.getElementById('xian').options[0].selected = true;

    });
    //设置中文
    // function toUtf8(str) {
    //     var out, i, len, c;
    //     out = "";
    //     len = str.length;
    //     for(i = 0; i < len; i++) {
    //         c = str.charCodeAt(i);
    //         if ((c >= 0x0001) && (c <= 0x007F)) {
    //             out += str.charAt(i);
    //         } else if (c > 0x07FF) {
    //             out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
    //             out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
    //             out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
    //         } else {
    //             out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
    //             out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
    //         }
    //     }
    //     return out;
    // }

    $scope.downloadSample = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/downloadsample")

    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/sampleMessageRecheck")
    }
    $scope.reback = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }
})


