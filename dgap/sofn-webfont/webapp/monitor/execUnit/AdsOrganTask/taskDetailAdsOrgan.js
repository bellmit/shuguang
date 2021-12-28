var myapp = angular.module("myapp", []);
myapp.controller("taskDetailAdsOrgan", function ($scope, $http, $state) {
    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");//监测任务
    var sampleOrganId = window.localStorage.getItem("sampleOrganId");//抽样机构代码
    var sampleOrgan = window.localStorage.getItem("sampleOrgan");//监测机构名称
    var token = window.localStorage.getItem("token");//监测机构名称
    var type = window.localStorage.getItem("type");//获取类型来源

    $scope.iswrite = "";
    var b = window.localStorage.getItem("AdsMenuObj");
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    var url1 = "index.content.monitor/execUnit/routineMonitor/routineMonitoring";
    var url2 = "index.content.monitor/execUnit/specialMonitor/routineMonitoringSpecial";
    for (var i = 0; i < a.length; i++) {
        if (type == "1") {
            if (a[i].menuName == "例行监测" && a[i].url == url1) {
                $scope.iswrite = a[i].authority;
                break;
            }
        } else {
            if (a[i].menuName == "专项监测" && a[i].url == url2) {
                $scope.iswrite = a[i].authority;
                break;
            }
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    $scope.detail = "";
    $scope.organTaskId = "";
    $scope.deadline = "";

    $scope.writeSampleEnabled = true;

    var project = 0;
    var object = 0;
    $scope.checkObject = [];
    $http({
        url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method: "post",
        params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
    }).success(function (data) {
        $scope.detail = data.adsMonitorTask[0];
        $scope.taskName = data.adsMonitorTask[0].taskName;
        $scope.deadline = data.adsMonitorTask[0].deadline;//上报截至时间
        $scope.organTaskId = data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
        $scope.checkModel = data.adsMonitorTask[0].checkModel;//机构任务id
        $scope.sampleFinishNum = data.adsMonitorTask[0].adsOrganTaskList[0].sampleFinishNum;
        $scope.numbers = data.adsMonitorTask[0].adsOrganTaskList[0].numbers;

        var priority = data.adsMonitorTask[0].priority;
        $scope.priority = "";
        if(priority == 1){
            $scope.priority = "特急";
        }else if (priority == 2){
            $scope.priority = "紧急";
        }else {
            $scope.priority = "一般";
        }

        window.localStorage.setItem("numbers", $scope.numbers);//全局缓存任务数

        $scope.monitorClass = data.adsMonitorTask[0].monitorClass//监测类型
        if (data.adsMonitorTask[0].checkObject != null) {
            $scope.checkObject = data.adsMonitorTask[0].checkObject.split("、");
            window.localStorage.setItem("checkObject", $scope.checkObject);
            object = data.adsMonitorTask[0].checkObject.split("、").length;
        }
        if (data.adsMonitorTask[0].checkProject != null) {
            project = data.adsMonitorTask[0].checkProject.split("、").length;
        }

        if (data.adsMonitorTask[0].publishStatus == '5')
            $scope.writeSampleEnabled = false;
        else
            $scope.writeSampleEnabled = true;
        //getSamplePlan();


        // $scope.loadObjectCharts();
        // $scope.loadItemCharts();
        // $scope.loadStandCharts();
    }).error(function () {
    });

    //获取抽样计划任务,并判断是否可以填报
    function getSamplePlan() {
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/getSamplePlanByMonitorTaskId",
            type: "post",
            dataType: "json",
            data: {id: adsMonitorTaskId, token: token},
            async: false,
            success: function (data) {
                if (parseInt(data.data) > 0) {
                    $scope.writeSampleEnabled = true;
                } else
                    $scope.writeSampleEnabled = false;
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    $scope.loadObjectCharts = function () {
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: ['监测对象']
            },
            series: [
                {
                    name: '总览',
                    type: 'pie',
                    radius: ['60%', '80%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: true,
                            formatter: '{c}',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: object, name: '监测对象'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.on('click', function (params) {
            // 控制台打印数据的名称
            var content = $("#v_checkObject").html();
            $scope.showXModal(params.name, content);
        });
    }

    /**
     * 加载检测项目图表
     */
    $scope.loadItemCharts = function () {
        var myChart2 = echarts.init(document.getElementById('main_2'));

        // 指定图表的配置项和数据
        option2 = {
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: ['检测项目']
            },
            series: [
                {
                    name: '总览',
                    type: 'pie',
                    radius: ['60%', '80%'],
                    avoidLabelOverlap: false,
                    color: ['#61a0a8'],
                    label: {
                        normal: {
                            show: true,
                            formatter: '{c}',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: project, name: '检测项目'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option2);
        myChart2.on('click', function (params) {
            // 控制台打印数据的名称
            var content = $("#v_checkProject").html();
            $scope.showXModal(params.name, content);
        });
    }

    /**
     * 加载判定标准图表
     */
    $scope.loadStandCharts = function () {
        var myChart3 = echarts.init(document.getElementById('main_3'));

        // 指定图表的配置项和数据
        option3 = {
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: ['判定标准']
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: ['60%', '80%'],
                    avoidLabelOverlap: false,
                    color: ['#91c7ae'],
                    label: {
                        normal: {
                            show: true,
                            formatter: '{c}',
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {value: object * project, name: '判定标准'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart3.setOption(option3);
        myChart3.on('click', function (params) {
            // 控制台打印数据的名称
            $scope.showCheckObjectModal();
        });
    }

    //返回键
    $scope.return = function () {
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        //$state.go("index.content.monitor/execUnit/routineMonitor/routineMonitoring")
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitoring")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/routineMonitoringSpecial")
        }
    }
    //下载键
    $scope.download = function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsOrganTask/downloadTaskDetailExcel",
                    method: "post",
                    dataType: "json",
                    params: {"token": token, "monitorTaskId": adsMonitorTaskId},
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
                        a.setAttribute('download', '任务详情.xls');
                        a.click();
                    } else {
                        jBox.tip("下载失败！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    /**
     * 打开检测对象模态框
     */
    $scope.showCheckObjectModal = function () {
        //$("#dModal").modal("show");
        // setTimeout(function () {
        //     $scope.loadCheckObjectData($scope.checkModel);
        // },300);
        //bootstrap表格
        $("#itemGrid").dataTable({
            ajax: {
                url: "/sofn-ads-web/adsNewModel/getPageInfoAllByDetail",
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
                    params.itemName = $("#checkNames").val();
                    params.token = token;
                    params.nameArray = $("#checkProjectDetail").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "itemName",
                    title: "检测项目"
                },
                {
                    data: "standardCode",
                    title: "检测标准"
                }
            ],
        });
        $("#selectPackage").modal("show");
    }

    $("#seachTask1").click(function () {
        var str = $("#checkNames").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("长度过长,请更改输入内容", 'info');
        } else $("#itemGrid").dataTable().api().ajax.reload();
    });
    /**
     * 显示监测对象、检测项目模态框
     * @param title
     * @param content
     */
    $scope.showXModal = function (title, content) {
        $("#x_title").html(title);
        $("#x_content").html(content);
        $("#xModal").modal("show");
    }

    /**
     * 查询机构任务数据
     */
    $http({
        url: "/sofn-ads-web/adsOrganTask/getPageInfoByMonitorTask",
        method: "post",
        dataType: "json",
        params: {"start": 0, "length": 99, "monitorTaskId": adsMonitorTaskId},
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        var list = data.data.list;
        $.each(list, function () {
            var i = $(this)[0];
            i.cityCode = $.fn.Cityname(i.cityCode);
        })
        $scope.organList = list;
    });

    $(function () {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });
    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.writeSample = function (data) {
        var newDate = getNowFormatDate();//获取当前日期
        if (newDate <= $scope.deadline) {
            window.localStorage.setItem("monitorClass", $scope.monitorClass);//监测类型
            window.localStorage.setItem("sampleOrgan", sampleOrgan);//监测机构名称
            window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
            window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);//监测任务
            window.localStorage.setItem("sampleOrganId", sampleOrganId);//抽样机构代码
            window.localStorage.setItem("deadline", $scope.deadline);//抽样截至时间
            window.localStorage.setItem("taskName", $scope.taskName);//抽样截至时间
            $state.go("index.content.monitor/execUnit/AdsOrganTask/writeSample")
        } else {
            jBox.tip("超过截止时间的任务不允许抽样", "info")
        }
    }
    $scope.sampleMessage = function (data) {
        window.localStorage.setItem("monitorClass", $scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan", sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId", sampleOrganId);//抽样机构代码
        if ($scope.deadline == null) {
            window.localStorage.setItem("deadline", "");//抽样截至时间
        } else {
            window.localStorage.setItem("deadline", $scope.deadline);//抽样截至时间
        }
        window.localStorage.setItem("taskName", $scope.taskName);//抽样截至时间

        if($scope.numbers != null && $scope.numbers == "")
            window.localStorage.setItem("numbers", $scope.numbers);//任务数

        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")
    }
    $scope.test = function (data) {
        window.localStorage.setItem("monitorClass", $scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan", sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId", sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline", $scope.deadline);//抽样截至时间
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        window.localStorage.setItem("monitorClass", $scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan", sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId", sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline", $scope.deadline);//抽样截至时间
        //获取任务名称
        var taskName = $("#taskName").text();
        window.localStorage.setItem("taskName", $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        window.localStorage.setItem("monitorClass", $scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan", sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId", sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline", $scope.deadline);//抽样截至时间
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/specialReportUpload")
        }
    }
    //返回主页
    $scope.backHome = function (data) {
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitoring")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/routineMonitoringSpecial")
        }
    }

    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
        // + " " + date.getHours() + seperator2 + date.getMinutes()
        // + seperator2 + date.getSeconds();
        return currentdate;
    }

    /**
     * 加载检测对象列表
     * @param model_id
     */
    $scope.loadCheckObjectData = function (model_id) {
        $http({
            url: "/sofn-ads-web/adsModelCheckObject/getPageInfoByModel",
            method: "post",
            dataType: "json",
            params: {"model_id": model_id, "start": 1, "length": 99},
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.items = data.items;
            $scope.adsCheckObject = data.pageInfo.list;
            setTimeout(function () {
                $.each(data.pageInfo.list, function (idx) {
                    var obj = $(this)[0];
                    var itmeList = obj.itemList;
                    var str = "";
                    $.each(data.items, function () {
                        var item = $(this)[0];
                        var val = item.standardValue;
                        var flag = 0;
                        $.each(itmeList, function () {
                            var tmp = $(this)[0];
                            if (tmp.id == item.id) {
                                flag = 1;
                                return false;
                            }
                        });
                        if (flag == 0) {
                            val = "-";
                        }
                        str += "<td>" + val + "</td>";
                    });
                    $("#" + obj.id).append(str);
                })
            }, 300)
        });
    }

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

    //附件下载
    $scope.downloadFile = function () {
        var file = GetFileNameNoExt($scope.detail.attachment);
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.detail.attachmentAddress);
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


