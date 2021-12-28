var myapp=angular.module("myapp",[]);
myapp.controller("reportCheckOrgan",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");

    /**
     * 跳转到抽样环节统计报表
     */
    $scope.goSamplingLingk = function () {
        $state.go("index.content.monitor/report/sampling/reportSamplingLink")
    }
    /**
     * 跳转到抽样区域统计报表
     */
    $scope.goSamplingArea = function () {
        $state.go("index.content.monitor/report/sampling/reportSamplingArea")
    }
    /**
     * 跳转到抽样机构统计报表
     */
    $scope.goSamplingOrgan = function () {
        $state.go("index.content.monitor/report/sampling/reportSamplingOrgan")
    }
    /**
     * 跳转到检测环节统计报表
     */
    $scope.goCheckLink = function () {
        $state.go("index.content.monitor/report/detection/reportCheckLink")
    }
    /**
     * 跳转到检测地区统计报表
     */
    $scope.goCheckArea = function () {
        $state.go("index.content.monitor/report/detection/reportCheckArea")
    }
    /**
     * 跳转到检测对象统计报表
     */
    $scope.goCheckObject = function () {
        $state.go("index.content.monitor/report/detection/reportCheckObject")
    }
    /**
     * 跳转到检测地区对象统计报表
     */
    $scope.goCheckAreaObj = function () {
        $state.go("index.content.monitor/report/detection/reportCheckAreaObj")
    }
    /**
     * 跳转到检测项目统计报表
     */
    $scope.goCheckInfo = function () {
        $state.go("index.content.monitor/report/detection/reportCheckInfo")
    }

    var monitorTaskId = "";
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url:"/sofn-ads-web/adsReport/getCheckOrganReportPageInfo",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    $scope.loadReportCharts(json.data.list);
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    params.monitorTaskId = monitorTaskId;
                },
                type:"post"
            },
            columns : [
                {
                    data: "RN",
                    title: "序号"
                },
                {
                    data: "DETECTION_ORGAN",
                    title: "检测机构"
                },
                {
                    data: "DETECTION_NUM",
                    title: "检测数量"
                },
                {
                    data: "QUALIFIE_NUM",
                    title: "合格数"
                },
                {
                    data: "PASS",
                    title: "合格率",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        return data * 100 + "%";
                    }
                }
            ],
        });
    });

    /**
     * 加载图表数据
     * @param data
     */
    $scope.loadReportCharts = function(data){
        var category = new Array(); //对象分类
        var arr1 = new Array();  //抽检数量
        var arr2 = new Array();    //合格数
        if(data.length == 0){
            $("#main").addClass("hide");
            return false;
        }
        $("#main").removeClass("hide");
        $(data).each(function () {
            var a = $(this)[0];
            category.push(a.DETECTION_ORGAN);
            arr1.push(a.DETECTION_NUM);
            arr2.push(a.QUALIFIE_NUM);
        })
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['检测数量','合格数']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : category
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'检测数量',
                    type:'bar',
                    barWidth : 10,
                    data:arr1
                },
                {
                    name:'合格数',
                    type:'bar',
                    barWidth : 10,
                    stack: '合格',
                    data:arr2
                }
            ]
        };
        myChart.setOption(option);
    }

    /**
     * 导出excel
     */
    $scope.exportExcel = function () {
        jBox.tip("功能开发中","info");
    }

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.selectTaskName = function(){
        $("#taskNameModal").modal("show");
        setTimeout(function () {
            if(selectTaskNameFlag==0){
                loadTaskName();
            }else{
                $("#taskNameGrid").dataTable().api().ajax.reload();
            }
        },300)
    };

    $("#seachTaskName").click(function () {
        $("#taskNameGrid").dataTable().api().ajax.reload();
    })

    /**
     * 选择任务名称
     * @param row
     */
    window.checkTaskName = function(taskName,id){
        $("input[name='taskName']").val(taskName);
        $("#taskNameModal").modal("hide");
        //刷新列表
        monitorTaskId = id;
        $("#grid").dataTable().api().ajax.reload();
    };

    /**
     * 加载任务名称数据
     */
    function loadTaskName() {
        //bootstrap表格
        $("#taskNameGrid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsMonitorTask/getPageInfo",
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
                    params.publishStatus = 3;
                    params.is_history = "Y";
                    params.taskName =  $("input[name='sTaskName']").val();
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data : "taskName",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn" onclick="checkTaskName(\''+row.taskName+'\',\''+row.id+'\')">选择</button>';
                        return look_a;
                    }
                },
            ],
        });
        selectTaskNameFlag++;
    }

    /**
     * 导出
     */
    $scope.exportExcel = function () {
        if(monitorTaskId == "" || monitorTaskId == null){
            jBox.tip("请选择一个监测任务","info");
            return false;
        }
        var submit = function (v, h, f) {
            if (v == true){
                var taskName = $("input[name='taskName']").val();
                $http({
                    url:"/sofn-ads-web/adsReport/exportCheckOrganExcel",
                    method:"post",
                    dataType:"json",
                    params : {"monitorTaskId":monitorTaskId},
                    responseType: 'arraybuffer',
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if(blob.size > 0){
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', taskName + '检测机构报表.xls');
                        a.click();
                    }else{
                        jBox.tip("导出失败，没有数据！","info")
                    }
                });
            }
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

    window.check=function(e) {
        var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        if(e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入30个汉字或者60个字母/数字");
                e.value = "";
                e.focus();
            }
        }
    }

})

