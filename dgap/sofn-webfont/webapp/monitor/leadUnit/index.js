var myapp=angular.module("myapp",[]);
myapp.controller("index",function($scope,$http,$state){

    var token =  window.localStorage.getItem("token");

    /**
     * 项目管理
     */
    $http({
        //url:"/sofn-ads-web/adsMonitorTask/getPageInfo",
        url:"/sofn-ads-web/adsRoutineMonitor/list",//查询监管下发的任务
        method:"post",
        dataType:"json",
        //params : { "start": 0, "length": 3,"taskSource" :"SDPTASK","publishStatus":1,"token":token,"monitorType":"监督抽查"},//SDPTASK,这是部级任务，bug修改要求监管任务
        params : { "start": 0, "length": 3,"rmState":1,"token":token},
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
    }).success(function(data){
        for(var i=0;i<data.data.list.length;i++){
            //时间格式化
            if(data.data.list[i].RMDATEBEGIN!=null&&data.data.list[i].RMDATEBEGIN!=""&&data.data.list[i].RMDATEBEGIN!=undefined){
                data.data.list[i].RMDATEBEGIN=new Date(parseInt(data.data.list[i].RMDATEBEGIN)+28800000).toISOString().slice(0,10);
            }
            if(data.data.list[i].RMDATEEND!=null&&data.data.list[i].RMDATEEND!=""&&data.data.list[i].RMDATEEND!=undefined){
                data.data.list[i].RMDATEEND=new Date(parseInt(data.data.list[i].RMDATEEND)+28800000).toISOString().slice(0,10);
            }
            //任务状态
            /*if(json.data.list[i].RMSTATE!=null&&json.data.list[i].RMSTATE!=""&&json.data.list[i].RMSTATE!=undefined){
                if(json.data.list[i].RMSTATE=='0'){
                    json.data.list[i].RMSTATE='未发布';
                }
                if(json.data.list[i].RMSTATE=='1'){
                    json.data.list[i].RMSTATE='已发布';
                }
                if(json.data.list[i].RMSTATE=='2'){
                    json.data.list[i].RMSTATE='已废止';
                }
            }*/
        }
        var list = data.data.list;
        $scope.leadItems = list;
    });


    /*$http({
        url:"/sofn-ads-web/adsMonitorTask/getPageInfo",
        method:"post",
        dataType:"json",
        params : { "start": 0, "length": 2,"taskSource" :"DEPTASK","publishStatus":1},
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
    }).success(function(data){
        var list = data.data.list;
        $scope.execItems = list;
    });*/
    /**
     * 承担任务
     */
    $http({
        url:"/sofn-ads-web/adsOrganTask/queryExecUnitTaskPageInfo",
        method:"POST",
        params:{"token":token,"start":0,"length":3}
    }).success(function(data){
        $scope.sampleOrganId=data.sampleOrganId;
        $scope.sampleOrgan=data.sampleOrgan;
        $scope.execItems=data.data.list;
        $scope.total = data.data.total;
    }).error(function(){
        jBox.tip("加载异常","error");
    });

    /**
     * 跳转项目管理（原来是跳转到新任务，现在改为跳转到接收任务tab页）
     */
    /*$scope.goNewTaskList=function () {
       $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    }*/
    $scope.goNewTaskList=function () {
       $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
    }

    /**
     * 跳转承担任务
     */
    $scope.goExecTaskList=function () {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskManage")
    }

    /**
     * 跳转到承担任务详情
     */
    $scope.goExecTaskDetal=function (id) {
        window.localStorage.setItem("adsMonitorTaskId",id);
        window.localStorage.setItem("sampleOrgan",$scope.sampleOrgan);//监测机构名称
        window.localStorage.setItem("sampleOrganId",$scope.sampleOrganId);//机构任务id
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }

    /**
     * 跳转到任务详情==》点击，执行任务
     * @param id
     */
    $scope.goTaskDetail=function (task) {
        /*window.localStorage.setItem("adsMonitorTaskId",id);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");*/
        //要求是监管下发的任务，点击事件是去执行监管下发的任务
        runTask(task);
    }

    /**
     * 执行任务
     */
    window.runTask =function (task) {
        var task = task;
        var doc = "";
        if(null != task.RMFILE){
            var index = task.RMFILE.lastIndexOf("/");
            doc = task.RMFILE.substr(index+1);
        }else{
            doc = task.RMFILE;
        }
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url:"/sofn-ads-web/adsMonitorTask/addAdsMonitorTaskSup",
                    type:"post",
                    dataType:"json",
                    data:{"taskName":task.RMNAME,"monitorClass":task.RMTYPE,"releaseUnit":task.RMRELEASEUNIT,"year":task.RMYEAR,
                        "batch":task.RMBATCH,"separation":0,"startTime":task.RMDATEBEGIN,"endTime":task.RMDATEEND,"taskSource":"SDPTASK","supId":task.ID,
                        "attachment":doc,"attachmentcode":task.RMFILENUM,"comment":task.RMREMARK,"publishStatus":0,"attachmentAddress":task.RMFILE,
                        "token":token},
                    success:function(data){
                        if(data.httpCode == 500){
                            jBox.tip("保存失败", 'info');
                        }else if(data.httpCode == 400) {
                            jBox.tip("没有登录", 'info');
                        }else if(data.httpCode==409){
                            jBox.tip("该任务正在执行中，不可重复执行", 'info');
                        }else if(data.httpCode==410) {
                            jBox.tip("该任务已被执行4次，不可再次执行", 'info');
                        }else{
                            window.localStorage.setItem("adsMonitorTaskId",data.adsMonitorTask.id);
                            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
                            jBox.tip("任务执行成功，请继续配置任务信息。", 'success');
                        }
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认要执行任务吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

})

