var myapp=angular.module("myapp",[]);
myapp.controller("resTaskDetail",function($scope,$http,$state) {
    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//监测任务
    var sampleOrganId=window.localStorage.getItem("sampleOrganId");//抽样机构代码
    var sampleOrgan=window.localStorage.getItem("sampleOrgan");//监测机构名称
    var token=window.localStorage.getItem("token");//监测机构名称
    $scope.detail="";
    $scope.organTaskId="";
    $scope.numbers="";
    var project=0;
    var object=0;

    $http({
        url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method:"post",
        params:{"token":token,"monitorTaskId":adsMonitorTaskId,"monitorClass":""}
        }).success(function (data) {
            $scope.detail=data.adsMonitorTask[0];
            $scope.taskName=data.adsMonitorTask[0].taskName;
            $scope.deadline=data.adsMonitorTask[0].deadline;//上报截至时间
            $scope.organTaskId=data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
            $scope.detectionOrgan=data.adsMonitorTask[0].adsOrganTaskList[0].detectionOrgan;//机构任务id
            $scope.checkModel=data.adsMonitorTask[0].checkModel;//机构任务id
            $scope.monitorClass=data.adsMonitorTask[0].monitorClass//监测类型
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
            if(data.adsMonitorTask[0].checkObject!=null){
                object=data.adsMonitorTask[0].checkObject.split("、").length;
            }
            if(data.adsMonitorTask[0].checkProject!=null){
                project=data.adsMonitorTask[0].checkProject.split("、").length;
            }
            $("#grid").dataTable().api().ajax.reload();
        }).error(function(){
    });

    $scope.loadObjectCharts=function () {
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
                data:['抽查对象']
            },
            series: [
                {
                    name:'总览',
                    type:'pie',
                    radius: ['50%', '70%'],
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
                    data:[
                        {value:object, name:'抽查对象'}
                    ]
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.on('click', function (params) {
            // 控制台打印数据的名称
            var content = $("#v_checkObject").html();
            $scope.showXModal(params.name,content);
        });

    }
    //返回键
    $scope.returnResume=function () {
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitoringRecheck")
    }
    /**
     * 加载检测项目图表
     */
    $scope.loadItemCharts=function () {
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
                data:['检测项目']
            },
            series: [
                {
                    name:'总览',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    color:['#61a0a8'],
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
                    data:[
                        {value:project, name:'检测项目'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option2);
        myChart2.on('click', function (params) {
            // 控制台打印数据的名称
            var content = $("#v_checkProject").html();
            $scope.showXModal(params.name,content);
        });
    }

    /**
     * 加载判定标准图表
     */
    $scope.loadStandCharts=function () {
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
                data:['检测工作量']
            },
            series: [
                {
                    name:'访问来源',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    color:['#91c7ae'],
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
                    data:[
                        {value:object*project, name:'判定标准'}
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
    $scope.samplePrint=function () {

    }
    //下载键
    $scope.download=function () {
        var arr = new Array();
        arr.push(adsMonitorTaskId);
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorTask/exportAdsTaskExcel",
                    method: "post",
                    dataType: "json",
                    params: {
                        "ids": arr.toString(),
                        "token": token
                    },
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
                        a.setAttribute('download', '导出任务.xls');
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

    /**
     * 打开检测对象模态框
     */
    $scope.showCheckObjectModal=function () {
        $("#dModal").modal("show");
        setTimeout(function () {
            $scope.loadCheckObjectData($scope.checkModel);
        },300);
    }

    /**
     * 显示监测对象、检测项目模态框
     * @param title
     * @param content
     */
    $scope.showXModal = function (title,content) {
        $("#x_title").html(title);
        $("#x_content").html(content);
        $("#xModal").modal("show");
    }

    /**
     * 加载检测对象列表
     * @param model_id
     */
    // $scope.loadCheckObjectData=function (model_id) {
    //     $http({
    //         url:"/sofn-ads-web/adsModelCheckObject/getPageInfoByModel",
    //         method:"post",
    //         dataType:"json",
    //         params : {"model_id" : model_id,"start":1,"length":20},
    //         headers: {
    //             post: { 'Content-Type': 'application/x-www-form-urlencoded' }
    //         }
    //     }).success(function(data){
    //         $scope.items = data.items;
    //         $scope.adsCheckObject = data.pageInfo.list;
    //         setTimeout(function () {
    //             $.each(data.pageInfo.list,function (idx) {
    //                 var obj = $(this)[0];
    //                 var itmeList = obj.itemList;
    //                 var str="";
    //                 $.each(data.items,function () {
    //                     var item = $(this)[0];
    //                     var val = item.standardValue;
    //                     var flag = 0;
    //                     $.each(itmeList,function () {
    //                         var tmp = $(this)[0];
    //                         if(tmp.id==item.id){
    //                             flag = 1;
    //                             return false;
    //                         }
    //                     });
    //                     if(flag == 0){
    //                         val = "-";
    //                     }
    //                     str+="<td>"+val+"</td>";
    //                 });
    //                 $("#"+obj.id).append(str);
    //             })
    //         },300)
    //     });
    // }
    $(function () {

        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getEntrustSamplePageInfo?time="+new Date(),
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
                    if($scope.organTaskId == null || $scope.organTaskId == "")
                        params.organTaskId = -1;
                    else
                        params.organTaskId = $scope.organTaskId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "NUM",
                    title: "序号",
                    width:"5%"
                },

                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },

                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    render: function (data) { // 模板化列显示内容
                        return '<a name="SAMPLE_CODE"   onclick="resCheckClick(\'' + data + '\')" >' + data + '</a>';
                    }
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if(data!=null){
                            return '<input readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }else {
                            data = "";
                            return '<input readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }

                    }
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
                        }else {
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
                        }else{
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

        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });


    window.resCheckClick = function(data) {
        window.localStorage.setItem("sampleCode", data);
        window.localStorage.setItem("taskName", $scope.taskName);
        window.localStorage.setItem("organTaskId", $scope.organTaskId);
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/Recheck/sampleDetailRecheck")
    };
    $scope.taskDetail = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/Recheck/resTaskDetail");
    }

    $scope.sampleMessage = function (data) {
        window.localStorage.setItem("taskName", $scope.taskName);
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/Recheck/sampleMessageRecheck");
    }

    $scope.test = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/Recheck/testListRecheck");
    }

    $scope.routineMonitorList = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        var taskName = $("#taskName").text();
        window.localStorage.setItem("taskName",taskName);//任务名称
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck");
    }

    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("numbers", $scope.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum", $scope.sampleFinishNum);//抽样完成数
        $state.go("index.content.monitor/execUnit/Recheck/recheckReportUpload")
    }

    //打印设置
    $("#printArea").click(function(){
        $("#printContent").printArea();
    });

    /**
     * 加载检测对象列表
     * @param model_id
     */
    $scope.loadCheckObjectData=function (model_id) {
        $http({
            url:"/sofn-ads-web/adsModelCheckObject/getPageInfoByModel",
            method:"post",
            dataType:"json",
            params : {"model_id" : model_id,"start":1,"length":99},
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(data){
            $scope.items = data.items;
            $scope.adsCheckObject = data.pageInfo.list;
            setTimeout(function () {
                $.each(data.pageInfo.list,function (idx) {
                    var obj = $(this)[0];
                    var itmeList = obj.itemList;
                    var str="";
                    $.each(data.items,function () {
                        var item = $(this)[0];
                        var val = item.standardValue;
                        var flag = 0;
                        $.each(itmeList,function () {
                            var tmp = $(this)[0];
                            if(tmp.id==item.id){
                                flag = 1;
                                return false;
                            }
                        });
                        if(flag == 0){
                            val = "-";
                        }
                        str+="<td>"+val+"</td>";
                    });
                    $("#"+obj.id).append(str);
                })
            },300)
        });
    }
});
