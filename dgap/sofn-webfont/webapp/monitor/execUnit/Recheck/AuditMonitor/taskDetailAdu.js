var myapp=angular.module("myapp",[]);
myapp.controller("taskDetailAdu",function($scope,$http,$state) {
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
        url:"/sofn-ads-web/adsMonitorTask/findAuditAdsMonitorTaskById",
        method:"post",
        params:{"id":adsMonitorTaskId,"sampleOrganId":sampleOrganId}
    }).success(function(data){
        $scope.detail=data.data;
        $scope.taskName=data.data.taskName;
        $scope.deadline=data.data.deadline;//上报截至时间
        $scope.checkModel=data.data.checkModel;//机构任务id
        $scope.monitorClass=data.data.monitorClass//监测类型

        var priority = data.data.priority;
        $scope.priority = "";
        if(priority == 1){
            $scope.priority = "特急";
        }else if (priority == 2){
            $scope.priority = "紧急";
        }else {
            $scope.priority = "一般";
        }

        if(data.data.checkObject!=null){
            object=data.data.checkObject.split("、").length;
        }
        if(data.data.checkProject!=null){
            project=data.data.checkProject.split("、").length;
        }
        if(data.data.adsOrganTaskList!=null && data.data.adsOrganTaskList.length>0){
            $scope.organTaskId=data.data.adsOrganTaskList[0].id;
        }
        // $scope.organTaskID=data.data.adsOrganTaskList[0].id
        // $scope.numbers=data.data.adsOrganTaskList[0].numbers;
        // $scope.sampleFinishNum=data.data.adsOrganTaskList[0].sampleFinishNum;
        //加载圆图标
        // $scope.loadObjectCharts();
        // $scope.loadItemCharts();
        // $scope.loadStandCharts();
    }).error(function(){
    });
    //下载键
    $scope.download=function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-ads-web/adsOrganTask/downloadTaskDetailExcel",
                    method:"post",
                    dataType:"json",
                    params : {"token":token,"monitorTaskId":adsMonitorTaskId},
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
                        a.setAttribute('download', '任务详情.xls');
                        a.click();
                    }else{
                        jBox.tip("下载失败！","info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
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
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });
    $scope.sample = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("organTaskId",data.id);//机构任务
        // window.localStorage.setItem("organTaskId",$scope.organTaskID);//机构任务
        window.localStorage.setItem("numbers",data.numbers);//任务数
        window.localStorage.setItem("sampleFinishNum",data.sampleFinishNum);//抽样完成数
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        window.localStorage.setItem("taskName",$scope.taskName);//抽样截至时间
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/sampleMessage")
    }

    // $scope.downloadsample = function(data) {
    //     window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
    //     window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
    //     window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
    //     window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
    //     window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
    //     window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
    //     window.localStorage.setItem("taskName",$scope.taskName);//抽样截至时间
    //     $state.go("index.content.monitor/execUnit/Audit/downloadsample")
    // }
    $scope.test = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
      // $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
        var taskName = $("#taskName").text();
        window.localStorage.setItem("taskName",taskName);//任务名称
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/routineMonitorListAudit")
    }
    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("sampleOrgan",sampleOrgan);//监测机构名称
        window.localStorage.setItem("organTaskId",$scope.organTaskId);//机构任务
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务
        window.localStorage.setItem("sampleOrganId",sampleOrganId);//抽样机构代码
        window.localStorage.setItem("deadline",$scope.deadline);//抽样截至时间
        $state.go("index.content.monitor/execUnit/Audit/auditReportUpload")

    }
    //打印设置
    $("#printArea").click(function(){
        $(".Sup_contain_section").printArea();
        location.reload();
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


