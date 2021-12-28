var myapp=angular.module("myapp",[]);
myapp.controller("taskOverView",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");
    var monitorClass = window.localStorage.getItem("monitorClass");
    var task = window.localStorage.getItem("task");
    var adsMonitorTaskId =  window.localStorage.getItem("adsMonitorTaskId");
    $scope.id=adsMonitorTaskId;
    /**
     * 加载监测任务图表统计数据
     * @param model_id
     */
    $scope.loadTaskCountData=function (model_id) {
        $http({
            url:"/sofn-ads-web/adsMonitorTask/getTaskCountDataByModel",
            method:"post",
            dataType:"json",
            params : {"model_id" : model_id},
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(data){
            $scope.loadObjectCharts(data.data.checkObjectCount);
            $scope.loadItemCharts(data.data.checkItemCount);
            $scope.loadStandCharts(data.data.standardCount);
        });
    }

    /**
     * 加载检测对象图表
     */
    $scope.loadObjectCharts=function (num) {
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
                data:['监测对象']
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
                        {value:num, name:'监测对象'}
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

    /**
     * 加载检测项目图表
     */
    $scope.loadItemCharts=function (num) {
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
                        {value:num, name:'检测项目'}
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
    if(task == "pub"){
        $("#pubTask").html("待发布任务");
    }else if(task == "his"){
        $("#pubTask").html("历史任务");
    }
    if(monitorClass == "例行监测"){
        $("#jiance").html("例行监测");
    }else if(monitorClass == "专项监测"){
        $("#jiance").html("专项监测");
    }
    $("#jiance").click(function () {
        if(monitorClass == "例行监测"){
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        }else if(monitorClass == "专项监测"){
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor");
        }else{
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        }
    })
    $("#pubTask").click(function () {
        if(task == "pub"){
            $("#pubTask").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/pubTaskList");
        }else if(task == "his"){
            $("#pubTask").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/historyTaskList");
        }
    })
    /**
     * 加载判定标准图表
     */
    $scope.loadStandCharts=function (num) {
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
                data:['检测标准']
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
                        {value:num, name:'检测标准'}
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

    /**
     * 返回到新任务列表
     */
    $("#back").click(function () {
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    /**
     * 返回到基本信息
     */
    $("#info").click(function () {
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
    });

    /**
     * 返回到机构与地域
     */
    $("#orgReg").click(function () {
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/OrganRegion/organRegionTaskList")
    });

    /**
     * 打开检测对象模态框
     */
    $scope.showCheckObjectModal=function () {
        $("#dModal").modal("show");
        setTimeout(function () {
            $scope.loadCheckObjectData($scope.adsMonitorTask.checkModel);
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

    /**
     * 查询监测任务基本信息
     */
    $http({
        url:"/sofn-ads-web/adsMonitorTask/queryById",
        method:"post",
        dataType:"json",
        data : $scope.id,
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' },
            token: window.localStorage.getItem("token")
        }
    }).success(function(data){
        var adsMonitorTask = data.adsMonitorTask;
        $scope.adsMonitorTask = adsMonitorTask;
        $scope.initPubStatus(adsMonitorTask.publishStatus);
        //加载图表数据
        //$scope.loadTaskCountData($scope.adsMonitorTask.checkModel);
    });

    /**
     * 对发布状态做初始化操作
     * @param status
     */
    $scope.initPubStatus=function (status) {
        if(status == 0){
            $("#pubStatus").addClass("label-danger").html("未发布");
        }
        else if(status == 5){
            $("#pubStatus").addClass("label-danger").html("已废止");
        }
        else{
            $("#pubStatus").addClass("label-success").html("已发布");
            //已发布监测任务，不能再编辑机构任务
            $("#addOrgTaskForm").addClass("hide");
            $("#brtchDelete").addClass("hide");
        }
    }

    /**
     * 发布监测任务
     */
    window.publishAdsTask = function () {
        var now = new Date();
        var nowStr = now.format("yyyy-MM-dd");
        var date = new Date($scope.adsMonitorTask.deadline);
        var dateStr = date.format("yyyy-MM-dd");
        if (nowStr>dateStr) {
            jBox.tip("任务抽样日期已截止，不能发布任务", 'info');
            return false;
        }
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url:"/sofn-ads-web/adsMonitorTask/publishAdsMonitorTask",
                    type:"post",
                    dataType:"json",
                    data:{"token":token,"task_ids":adsMonitorTaskId},
                    success:function(data){
                        if(data.httpCode==200){
                            $("#pubStatus").removeClass("label-danger").addClass("label-success").html("已发布");
                            $("#btn-pub").css("background-color","silver").unbind("click").click(function () {
                                jBox.tip("提示，该任务已经发布了！","info");
                            });
                            jBox.tip("发布成功，在历史任务里可以查看已发布的任务", 'success');
                        }else{
                            jBox.tip(data.msg, 'info');
                        }
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认发布任务吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

    /**
     * 查询机构任务数据
     */
    $http({
        url:"/sofn-ads-web/adsOrganTask/getPageInfoByMonitorTask",
        method:"post",
        dataType:"json",
        params : { "start": 0, "length": 99,"monitorTaskId" :adsMonitorTaskId},
        headers: {
            "Content-Type": "application/x-www-form-urlencoded" ,
             token: window.localStorage.getItem("token")
        },
    }).success(function(data){
        var list = data.data.list;
        $.each(list,function () {
            var i = $(this)[0];
            i.cityCode = $.fn.Cityname(i.cityCode);
        })
        $scope.organList = list;
    });

    /**
     * 导出监测任务excel数据
     */
    $scope.exportWord = function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-ads-web/adsMonitorTask/exportTaskDoc",
                    method:"post",
                    dataType:"json",
                    params : {"adsMonitorTaskId":adsMonitorTaskId},
                    responseType: 'arraybuffer',
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if(blob.size > 0){
                        var taskName = $scope.adsMonitorTask.taskName+".doc";
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', taskName);
                        a.click();
                    }else{
                        jBox.tip("导出失败，没有数据！","error")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

    var checkModelType = "JUDGE_STANDARD";
    //延时加载数据
    $scope.stand_flag = 0;//加载标识
    /**
     * 打开判定标准文件模态框
     */
    $scope.showJudgeStandardModal=function () {
        $("#jModal").modal("show");
        setTimeout(function () {
            if($scope.stand_flag==0){
                $scope.loadCheckStandardData($scope.adsMonitorTask.checkModel);
            }else{
                $("#itemGrid").dataTable().api().ajax.reload();
            }
        },300);
    }

    /**
     * 加载项目列表数据
     */
    $scope.loadCheckStandardData = function (modelId) {
        $("#itemGrid").dataTable({
            dom : '<"top">rt<"bottom"pli><"clear">',
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsCheckModel/getPageInfoCheckedAndJudgeStandard",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    params.modelId = modelId;
                    params.type = checkModelType;
                    params.name = $("#nameS").val();
                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "判定标准名称"
                },
                {
                    data : "filePath",
                    title : "数据操作",
                    render : function(data, type, row) {
                        var file ='';
                        if(data!=null && data!=''){
                            file = '<a title="下载附件" download="" href="'+data+'" class="pointer">下载</a>';
                        }else{
                            file = '无';
                        }
                        return file;
                    }
                },
            ],
            language : {
                "sProcessing" : "处理中...",
                "sLengthMenu" : "显示 _MENU_ 项结果",
                "sZeroRecords" : "没有匹配结果",
                "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
                "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix" : "",
                "sSearch" : "搜索:",
                "sUrl" : "",
                "sEmptyTable" : "表中数据为空",
                "sLoadingRecords" : "载入中...",
                "sInfoThousands" : ",",
                "oPaginate" : {
                    "sFirst" : "首页",
                    "sPrevious" : "上页",
                    "sNext" : "下页",
                    "sLast" : "末页"
                },
                "oAria" : {
                    "sSortAscending" : ": 以升序排列此列",
                    "sSortDescending" : ": 以降序排列此列"
                }
            },
            sPaginationType : "full_numbers"
        });
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
        $scope.stand_flag =1;
    };


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
        var pos1 =strturn( file.replace(pos, ""));
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
    $scope.downloadFile=function(){
        var file = GetFileNameNoExt($scope.adsMonitorTask.attachment);
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.adsMonitorTask.attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', file);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

})

