angular.module("myapp",[]).controller("qryTaskSampleList",function($scope,$http,$state){

    /**
     * 该页面作为监督抽查、例行监测、专项抽样单展示页面
     * */

    /**
     * 公共值
     * */
    $scope.JdccCdUnit=[];//承担单位下拉
    $scope.JDccSonTask=[];//承担单位任务
    $scope.jcObjList=[];//检测对象下拉
    $scope.checkTaskInfomation = {};//监督抽查任务信息
    $scope.rmTaskInfomation = {};//例行监测任务信息
    $scope.smTaskInfomation = {};//专项检测任务信息
    $scope.dataTableUrl = "";//数据表格url
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.asms_Check_checkTask_forReport = window.localStorage.getItem("asms_Check_checkTask_forReport");//任务id
    if ($scope.asms_Check_checkTask_forReport == null || $scope.asms_Check_checkTask_forReport == undefined){
        $.jBox.tip("未获取到任务标示，无法获取抽样单列表");
        return;
    }


    /**
     * 根据任务区分暂时牵头单位或承担单位
     * */
    if ($scope.asms_Check_Task_Type_Title == 'JDCC'){
        $("#titleName").html("监督抽查");
        $scope.dataTableUrl = "/sofn-qry-web/monitorTask/getTaskSampleListByJcInfo";
        //部级提供承担单位、监测对象
        //省级提供检测对象
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getTaskById",
            data:{id:$scope.asms_Check_checkTask_forReport},
            method:"post"
        }).success(function(data) {
            $scope.checkTaskInfomation = data.data;
            if ("0" == data.data.taskLevel){//部级
                $("div[name='JDccCdUnitSpan']").show();
                $("div[name='JDccSonTaskSpan']").show();
                $("div[name='jcObjSpan']").show();
                $scope.JdccCdUnit=data.speList;
            }else {//省级
                $http({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url:"/sofn-qry-web/monitorTask/getObjById",
                    data:{id:$scope.asms_Check_checkTask_forReport},
                    method:"post"
                }).success(function(data) {
                    $scope.checkTaskInfomation = data.data;
                    $scope.jcObjList=data.data;
                    $("div[name='jcObjSpan']").show();
                });
            }
        }).error(function(data){
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
        $("#titleName").html("例行监测");
        //数据表格参数
        $scope.dataTableUrl = "/sofn-qry-web/monitorTask/getZLTaskSampleListByJcInfo";
        $scope.lxjcQtUnit=[];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getRMTaskById",
            data:{id:$scope.asms_Check_checkTask_forReport},
            method:"post"
        }).success(function(data) {
            $scope.rmTaskInfomation = data.data;
            $("div[name='LxJcQtUnitSpan']").show();
            $scope.lxjcQtUnit=data.rouList;
        }).error(function(data){
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC'){
        $("#titleName").html("专项检测");
        //数据表格参数
        $scope.dataTableUrl = "/sofn-qry-web/monitorTask/getZLTaskSampleListByJcInfo";
        $scope.ZxjcQtUnit=[];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getSMTaskById",
            data:{id:$scope.asms_Check_checkTask_forReport},
            method:"post"
        }).success(function(data) {
            $scope.smTaskInfomation = data.data;
            $("div[name='ZxjcQtUnitSpan']").show();
            $scope.ZxjcQtUnit=data.speList;
        }).error(function(data){
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'FJRW'){
        $("#titleName").html("复检任务");
    }


    /**
     * 根据承担单位id和父级任务id查找承担单位新建子任务列表
     * */
    window.selectCdUnit = function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getTasksByCreateOrgIdAndParentTaskId",
            data:{parentTaskId:$scope.asms_Check_checkTask_forReport,createOrgId:$("select[name='JdccCdUnit']").val()},
            method:"post"
        }).success(function(data) {
            $scope.JDccSonTask=data.data;
            $scope.jcObjList=[];
            $("select[name='JDccSonTask']").val("");
            $("select[name='jcObj']").val("");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function(data){
            
        });
    };

    /**
     * 根据子任务查找检测对象列表
     * */
    window.selectCdUnitTask = function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getObjById",
            data:{id:$("select[name='JDccSonTask']").val()},
            method:"post"
        }).success(function(data) {
            $scope.jcObjList=data.data;
            $("select[name='jcObj']").val("");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function(data){
            
        });
    };

    window.selectObj = function(){
        $("#grid").dataTable().api().ajax.reload();
    };


    /**
     * 抽样单列表
     * */
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        ajax : {
            url : $scope.dataTableUrl,
            dataSrc:function(json){
                json.recordsTotal=json.data.total;//总个数
                json.recordsFiltered=json.data.total;
                json.pageNumber=json.data.pageNum;//页码
                json.pageSize=json.data.pageSize;//每页个数
                json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                json.length=json.data.pageSize;
                return json.data.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                params.id = $scope.asms_Check_checkTask_forReport;//任务id[部级任务、非部级任务]
                var JdccCdUnit = $("select[name='JdccCdUnit']").val();//承担单位
                var sonTaskId = $("select[name='JDccSonTask']").val();//子任务
                if (JdccCdUnit != ""){
                    params.JdccCdUnit = JdccCdUnit;
                }
                if (sonTaskId != ""){
                    params.sonTaskId = sonTaskId;
                }
                //根据不同任务确定查询参数
                if ($scope.asms_Check_Task_Type_Title == 'JDCC'){
                    if ($("select[name='jcObj']").val()!=""){
                        params.taskObjId = $("select[name='jcObj']").val().trim();
                    }
                }else if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
                    if ($("select[name='lxjcQtUnit']").val()!=""){
                        params.parentTaskId = $("select[name='lxjcQtUnit']").val().trim();
                    }
                }else if ($scope.asms_Check_Task_Type_Title == 'ZXJC'){
                    if ($("select[name='ZxjcQtUnit']").val()!=""){
                        params.parentTaskId = $("select[name='ZxjcQtUnit']").val().trim();
                    }
                }
            },
            type:"post"
        },
        columns : [
            {
                data : 'id',
                title : 'ID',
                visible : false
            },
            {
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "sampleName",
                title : "样品名称"
            },
            {
                data : "sampleCode",
                title : "样品编码"
            },
            {
                data : "tradeMark",
                title : "商标"
            },
            {
                data : "grade",
                title : "等级"
            },
            {
                data : "sampleBase",
                title : "抽样基数"
            },
            {
                data : "samplePlace",
                title : "抽样场所"
            },
            {
                data : "sampleCode",
                title : "详情",
                render : function(data, type, row) {
                    return '<a style="cursor:pointer" onclick="querySampleInfo(\'' + data + '\' , \''+ row.organTaskId + '\')">抽样单详情</a>';
                }
            }
        ]
    });

    /**
     * 样品及检测信息列表
     * */
    window.querySampleInfo = function(o,p){
        window.localStorage.removeItem("asms_sample_info_id");
        window.localStorage.setItem("asms_sample_info_id",o);
        window.localStorage.removeItem("asms_sample_info_organTaskId");
        window.localStorage.setItem("asms_sample_info_organTaskId", p);
        if($scope.asms_Check_Task_Type_Title == 'JDCC'){
            $state.go("index.content.qry/monitorTask/taskSample/qryCheckSample");
        }else{
            $state.go("index.content.qry/monitorTask/taskSample/qrySample");
        }
    }
    $scope.retrunDiskTop = function () {
        if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'ZXJC'){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'FJRW'){
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }else if($scope.asms_Check_Task_Type_Title == 'JDCC'){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }
    }

});

