var myapp=angular.module("myapp",[]);
myapp.controller("routineMonitorListRecheck",function($scope,$http,$state){

    var taskName = window.localStorage.getItem("taskName");//任务名称
    /**
     * 收起部分条件选择框
     */
    $scope.hideInput = function () {
        $(".more").addClass("hide");
        $(".sh").removeClass("hide");
        $("#show").removeClass("hide");
    }
    $scope.hideInput();
    /**
     * 显示更多选择条件
     */
    $scope.showInput = function () {
        $(".more").removeClass("hide");
        $(".sh").addClass("hide");
        $("#show").addClass("hide");
    }

    //刷新表格
    $(".seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    //重置
    $scope.resetInput = function () {
        $(".clearfix>.gui-input").val("");
        $(".gui-select").val("");
        jBox.tip("重置成功！","success");
    }

    /**
     * 查询当前用户信息
     */
    var token = window.localStorage.getItem("token");
    $scope.monitorClass=window.localStorage.getItem("monitorClass");


    var orgId = window.localStorage.getItem("organTaskId");//任务名称Id;
    $.ajax({
        url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
        type: "post",
        dataType: "json",
        data: {"token":token},
        success: function (data) {
            // orgId = data.user.orgId;
            $("#grid").dataTable().api().ajax.reload();
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });

    $scope.year = [];

    var myDate = new Date();
    var startDate = myDate.getFullYear()-50;//起始年份
    var endDate = myDate.getFullYear()+50;//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }

    /**
     * 初始化省市县控件
     */
    $.fn.mycity("sheng","shi","xian",0,0,0);
    $.fn.mycity("sheng1","shi1","xian1",0,0,0);


    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/resTaskDetail")
    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/sampleMessageRecheck")
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


    $(function (){
       var monitorClass=$scope.monitorClass;

        //任务名
        $.ajax({
            url:"/sofn-ads-web/adsCheckInfo/getTaskName",
            type:"post",
            dataType:"json",
            data:{"token":token,"monitorClass" : monitorClass},
            success:function(datax){
                if(datax.httpCode == 200){
                    var datasx = datax.data;
                    $scope.taskName=datasx;
                }else{
                    jBox.tip("数据有误", 'info');
                }
            },
            error:function(datax){
                jBox.tip("数据有误", 'info');
            }
        });
/*        $http({
            url:"/sofn-ads-web/adsCheckInfo/getTaskName",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.taskName=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });*/
        //样品名称
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getSampleName",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.sampleName=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });


        //抽样单位
        /*$http({
            url:"/sofn-ads-web/adsCheckInfo/getSampleDeparment",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.sampleDeparment=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //检测单位
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getCheckOrgan",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.checkOrgan=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //受检单位
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getTestedDeparment",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.testedDeparment=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });*/


    });


    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsCheckInfo/getInfoByOrgName",
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
                    params.monitorClass=$scope.monitorClass;
                    params.orgId=orgId;//登录机构
                    params.year = $('#year option:selected').val();//年份
                   // params.taskName = taskName;//任务名称
                    params.result = $('#result option:selected').val();//结果
                    //单位改为文本框模糊查询
                    params.sampleDeparment = $('#sampleDeparment').val();//抽样单位
                    params.checkOrgan = $('#checkOrgan').val();//监测单位
                    params.testedDeparment = $('#testedDeparment').val();//受检单位
                    params.productTraceabilityCode = $("input[name='productCode']").val();    //产品编码
                    params.sampleCode = $("input[name='sampleCode']").val();    //样品编码
                    params.sampleName = $("#sampleName").val();    //样品名称
                    params.checkLink = $("input[name='checkLink']").val();    //检测环节
                    params.startTime = $("input[name='startTime']").val();//开始时间
                    params.endTime = $("input[name='endTime']").val();    //结束时间
                    if($('#sheng option:selected').val()!=null){
                        if($('#shi option:selected').val()!=null){
                            if($('#xian option:selected').val()!=null){
                                params.producingArea=$('#xian option:selected').val();
                            }else{
                                params.producingArea=$('#shi option:selected').val();
                            }
                        }else{
                            params.producingArea=$('#sheng option:selected').val();
                        }
                    }
                    if($('#sheng1 option:selected').val()!=null){
                        if($('#shi1 option:selected').val()!=null){
                            if($('#xian1 option:selected').val()!=null){
                                params.cityCode=$('#xian1 option:selected').val();
                            }else{
                                params.cityCode=$('#shi1 option:selected').val();
                            }
                        }else{
                            params.cityCode=$('#sheng1 option:selected').val();
                        }
                    }

                },
                type:"post"
            },
            columns : [
                /*{
                 title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                 return view;
                 }
                 },*/
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "productTraceabilityCode",
                    title: "产品编码",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if (data!=null){
                            return '<input style="overflow: auto; overflow-y:hidden" readonly="readonly" class="gui-input"  value="'+data+'" type="text"/>';
                        }else {
                            data = "";
                            return '<input style="overflow: auto; overflow-y:hidden" readonly="readonly" class="gui-input"  value="'+data+'" type="text"/>';
                        }

                    }


                },
                {
                    data: "sampleCode",
                    title: "样品编码",
                    render : function(data, type, row) {
                        var look_a = '<a  style="margin-right: 10px;cursor: pointer;" id=" sampleCode "    onclick="lookOrganRegion(\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\',\''+row.sampleName+'\') ">'+row.sampleCode+'</a>';
                        return look_a;
                    }
                },
                {
                    data: "sampleName",
                    title: "样品名称"
                },
                {
                    data: "testedDeparment",
                    title: "受检单位"
                },
                {
                    data: "sampleDeparment",
                    title: "抽样单位"
                },
                {
                    data: "sampleDate",
                    title: "抽样时间"
                },
                {
                    data: "checkOrgan",
                    title: "检测单位"
                },
                {
                    data: "checkTime",
                    title: "检测时间",
                    render : function(data, type, row) {
                        var time = new Date(row.checkTime).format('yyyy-MM-dd');
                        return time;
                    }
                },
                {
                    data: "result",
                    title: "判定结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else if("0"==data){
                            return "不合格";
                        }else{
                            return "-";
                        }
                    }
                },
                /*{
                 data : "ID",
                 title : "状态",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var look_a = '待发布';
                 return look_a;
                 }
                 },*/
            ],
        });
    });

    //导出excel
    $scope.exportExcel = function (type) {
        var monitorClass=$scope.monitorClass;
        if(type=="s"){
            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url:"/sofn-ads-web/adsCheckInfo/getOrgList",
                        method:"post",
                        dataType:"json",
                        params:{"monitorClass":monitorClass,"orgId":orgId},
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
                            a.setAttribute('download', '监测汇总.xls');
                            a.click();
                        }else{
                            jBox.tip("导出失败，没有数据！","info")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }else if(type=="a"){

            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url:"/sofn-ads-web/adsCheckInfo/getOrgListAll",
                        method:"post",
                        dataType:"json",
                        params:{"monitorClass":monitorClass,"orgId":orgId},
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
                            a.setAttribute('download', '详细信息.xls');
                            a.click();
                        }else{
                            jBox.tip("导出失败，没有数据！","info")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }

    };

    /**
     * 跳转到机构与地域页面
     */
    window.lookOrganRegion=function(data,data1,data2){
        window.localStorage.setItem("ads_sample_code",data);
        window.localStorage.setItem("ads_product_traceability_code",data1);
        window.localStorage.setItem("ads_sample_name",data2);

        window.localStorage.setItem("urlSource","");
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleInfo");
    };
    $scope.reback = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }
})






