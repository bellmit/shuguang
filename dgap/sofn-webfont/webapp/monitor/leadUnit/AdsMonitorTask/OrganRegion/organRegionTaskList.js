var myapp=angular.module("myapp",[]);
myapp.controller("organRegionTaskList",function($scope,$http,$state, $filter){
    var monitorClass = window.localStorage.getItem("monitorClass");
    var task = window.localStorage.getItem("task");
    var levelEnum = $.trim(window.localStorage.getItem("levelEnum"));
    var createOrgRegionId = window.localStorage.getItem("createOrgRegionId");
    var separationData = window.localStorage.getItem("separation");
    var token = window.localStorage.getItem("token");
    var adsMonitorTaskId =  window.localStorage.getItem("adsMonitorTaskId");
    $scope.id=adsMonitorTaskId;
    $scope.regionId = "";

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
        //初始化页面
       var adsMonitorTask = data.adsMonitorTask;
       $scope.adsMonitorTask = adsMonitorTask;
       $scope.initPubStatus(adsMonitorTask.publishStatus);
       //抽检分离
       if(adsMonitorTask.separation==0){
           $("#div-detection").addClass("hide");
           $("#lb-sample").html("抽检机构");
       }
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

    //后台加载行政区划信息
    getRegion("100000","sheng10");

    window.regionChanged = function (option) {
        if (option.id ==="sheng10"){
            $("#shi10"+" option:gt(0)").remove();
            $("#xian10"+" option:gt(0)").remove();
            if (option.value==="0"){
                return;
            }
            getRegion(option.value,"shi10");
        }else {
            $("#xian10"+" option:gt(0)").remove();
            if (option.value==="0"){
                return;
            }
            getRegion(option.value,"xian10");
        }
    };

    function getRegion(regionId,id) {
        $.ajax({
            url: "/sofn-sys-web/region/queryMenu?&delFlag=Y",
            type: "post",
            dataType: "json",
            data:{regionId:regionId},
            success: function (data) {
                var regionName = data;
                $.each(regionName, function (k, p) {
                    var option = "<option value='" + p.id + "'>" + p.text + "</option>";
                    $("#"+id).append(option);
                });
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    /**
     * 初始化省市县控件
     */
    // $.fn.mycity("sheng","shi","xian");
    // $("#sheng").val(0);$("#shi").val(0);$("#xian").val(0);

    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    /**
     * 跳转到监测任务基本信息页面
     */
    $scope.goAdsMonitorTaskInfo = function () {
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
    }

    /**
     * 跳转到监测任务总览页面
     */
    $scope.goTaskOverView = function () {
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/taskOverView");
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
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsOrganTask/getPageInfoByMonitorTask",
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
                    params.monitorTaskId = adsMonitorTaskId;
                    params.sampleOrgan = $("input[name='sampleOrgan']").val();
                    params.detectionOrgan = $("input[name='detectionOrgan']").val();
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "sampleOrgan",
                    title: "抽样机构"
                },
                {
                    data: "detectionOrgan",
                    title: "检测机构"
                },
                {
                    data: "numbers",
                    title: "抽样地区和数量",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = $.fn.Cityname(row.cityCode)+"（"+row.numbers+"）";
                        return look_a;
                    }
                },
                {
                    data: "deparment",
                    title: "报告上传单位"
                },
                {
                    data: "uploadTime",
                    title: "报告上传截止时间"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="" onclick="viewOrganTask(\''+row.id+'\',\''+row.sampleOrgan+'\',\''+row.detectionOrgan+'\',\''+row.numbers+'\',\''+row.uploadTime+'\',\''+row.deparment+'\',\''+row.cityCode+'\')">查看</a>';
                        return look_a;
                    }
                },
            ],
        });
    });

    /**
     * 查看机构任务
     */
    window.viewOrganTask=function (id,sampleOrgan,detectionOrgan,numbers,uploadTime,deparment,cityCode) {
        $("#vModal").modal("show");
        setTimeout(function () {
            $("#v_sampleOrgan").html(sampleOrgan);
            $("#v_detectionOrgan").html(detectionOrgan);
            $("#v_numbers").html(numbers);
            $("#v_uploadTime").html(uploadTime);
            $("#v_deparment").html(deparment);
            $("#v_citycode").html( $.fn.Cityname(cityCode));
        },300)
    }

    /**
     * 全选任务数据
     */
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选
            $("input[name='task']").attr("checked",true);
        }else{
            $("input[name='task']").attr("checked",false);
        }
    }

    /**
     * 删除机构任务
     */
    window.delAdsOrganTask=function () {
        var check = $("input[name='task']:checked");
        if(check.length > 0){
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id =$(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url:"/sofn-ads-web/adsOrganTask/deleteAdsOrganTask",
                        type:"post",
                        dataType:"json",
                        data:{"token":token,"monitorTaskId":adsMonitorTaskId,"ids":arr.toString()},
                        success:function(data){
                            if(data.httpCode==200){
                                jBox.tip("删除成功", 'success');
                            }else{
                                jBox.tip("删除失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除机构任务吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择机构任务数据", 'info');
        }
    }

    /**
     * 跳转到监测新任务页面
     */
    window.lookMonitorNewTaskList=function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList");
    }

    /**
     * 收起添加机构任务表单
     */
    window.hideOrgTaskAddForm=function(){
        $("#addOrgTaskForm").hide();
        $("#showAddForm").removeClass("hide");
    }

    /**
     * 显示添加机构任务表单
     */
    window.showOrgTaskAddForm=function(){
        $("#addOrgTaskForm").show();
        $("#showAddForm").addClass("hide");
    }

    var sampleGrgan = 0;
    $scope.orgType="";
    /**
     * 选择抽样或检测机构
     */
    $scope.selectOrgan=function(type){
        $scope.orgType=type;
        if(type=="S"){
            $("#modalTitle").html("选择抽样机构");
            $("#organTitle").html("抽样机构");
        }else{
            $("#modalTitle").html("选择检测机构");
            $("#organTitle").html("检测机构");
        }
        $("#dModal").modal("show");
        setTimeout(function () {
            if(sampleGrgan==0){
                loadSampleOrganData();
            }else{
                $("#sampleOrganGrid").dataTable().api().ajax.reload();
            }
        },300)
    }

    /**
     * 重新加载抽样机构数据
     */
    $("#seachSampleOrgan").click(function () {
        $("#sampleOrganGrid").dataTable().api().ajax.reload();
    });


    /**
     * 选择抽样机构或检测机构
     * @param row
     */
    window.checkOrgan=function(id,name){
        if($scope.orgType=="S"){
            //抽样机构
            $("input[name='sampleOrganId']").val(id);
            $("input[name='sampleOrgName']").val(name);
            //抽检不分离
            if($scope.adsMonitorTask.separation==0){
                $("input[name='detectionOrganId']").val(id);
            }
        }else{
            //检测机构
            $("input[name='detectionOrganId']").val(id);
            $("input[name='deteOrgName']").val(name);
        }
        $("#dModal").modal("hide");
        callbackDeparment();
    }

    /**
     * 回传机构值给报告单位
     */
    window.callbackDeparment=function () {
        var sampleOrganId = $("input[name='sampleOrganId']").val();
        var detectionOrganId = $("input[name='detectionOrganId']").val();
        var sampleOrgName = $("input[name='sampleOrgName']").val();
        var deteOrgName = $("input[name='deteOrgName']").val();
        $("select[name='deparment']").find("option").remove()
        if(sampleOrgName!=null && sampleOrgName!=""){
            var option = "<option value='"+sampleOrganId+"'>"+sampleOrgName+"</option>";
            $("select[name='deparment']").append(option);
        }
        if(deteOrgName!=null && deteOrgName!=""){
            var option = "<option value='"+detectionOrganId+"' selected='selected'>"+deteOrgName+"</option>";
            $("select[name='deparment']").append(option);
        }
    }

    //重置
    $scope.resetBtn=function(){
        $('#addOrgTaskForm').data('bootstrapValidator').resetForm(true);
        $('.gui-select').val("");
        $('.gui-input').val("");
        $("#sheng10").val(0);$("#shi10").val(0);$("#xian10").val(0);
        /*$("#uploadDate").val("");*/
    }

    $('#addOrgTaskForm').bootstrapValidator();//必须有
    //添加机构任务
    $("#btnAddTask").click(function () {
        if (!$("#addOrgTaskForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行添加操作
        window.addOrganTask();
    })

    /**
     * 创建机构任务
     */
    window.addOrganTask=function () {
        //表单验证
        var sampleOrganId = $("input[name='sampleOrganId']").val();
        var detectionOrganId = $("input[name='detectionOrganId']").val();
        var sampleOrgName = $("input[name='sampleOrgName']").val();
        var deteOrgName = $("input[name='deteOrgName']").val();
        var numbers = $("input[name='numbers']").val();
        var deparment = $("select[name='deparment']").find("option:selected").text();
        var deparmentId =$("select[name='deparment']").find("option:selected").val();
        var regId = 0;
        var uploadTime = $("input[name='uploadTime']").val();
        var sheng = $("#sheng10 option:selected").val();
        var shi = $("#shi10 option:selected").val();
        var xian = $("#xian10 option:selected").val();
        var shengName = $("#sheng10 option:selected").text();
        var shiName = $("#shi10 option:selected").text();
        var xianName = $("#xian10 option:selected").text();

        //目前需求最后一级为市级
        var cityCode = shi;
        var cityName = shengName+shiName;


        if(adsMonitorTaskId==null || adsMonitorTaskId==""){
            jBox.tip("监测任务不存在", 'info');
            return false;
        }
        if(separationData == 0){
            if(sampleOrganId==""){
                jBox.tip("请先选择抽样和检测机构，再选择报告上传单位", 'info');
                return false;
            }
        }else if(separationData == 1){
            if(sampleOrganId==""){
                jBox.tip("请先选择抽样机构", 'info');
                return false;
            }
            if(detectionOrganId==""){
                jBox.tip("请先选择检测机构", 'info');
                return false;
            }
        }
        //判断是否抽检分离
        if($scope.adsMonitorTask.separation==1 && sampleOrganId==detectionOrganId){
            jBox.tip("抽检分离的任务不能选择相同的抽检机构", 'info');
            return false;
        }
        if($scope.adsMonitorTask.separation==0){
            deteOrgName = sampleOrgName;
            detectionOrganId = sampleOrganId;
        }
        if(sheng==undefined || sheng==null || sheng=='0' || shi =='0'){
            jBox.tip("请选择抽样地区", 'info');
            return false;
        }

        var reg = /^[1-9]\d*$/;
        if(!reg.test(numbers)){
            jBox.tip("请输入正确格式的抽样数量（正整数）", 'info');
            return false;
        }
        if(uploadTime==""){
            jBox.tip("请先选择上报时间", 'info');
            return false;
        }
        //判断上报时间，需要晚于抽样截止时间，早于任务结束时间
        var deadline =  new Date($scope.adsMonitorTask.deadline).format("yyyy-MM-dd");//抽样截止时间
        var endTime = new Date($scope.adsMonitorTask.endTime).format("yyyy-MM-dd");    //任务结束时间
        // var upTime = new Date(uploadTime).format("yyyy-MM-dd");
        var upTime =  $filter('date')(new Date($.trim(uploadTime)), 'yyyy-MM-dd');  //报告上传时间
        if(upTime < deadline || upTime > endTime){
            jBox.tip("报告上传时间要不早于抽样截止时间("+$scope.adsMonitorTask.deadline+")，不晚于任务结束时间("+$scope.adsMonitorTask.endTime+")","info");
            return false;
        }
        //提交
        $.ajax({
            url:"/sofn-ads-web/adsOrganTask/addAdsOrganTask",
            type:"post",
            dataType:"json",
            data:{"token":token,"monitorTaskId":adsMonitorTaskId,"sampleOrgan":sampleOrgName,"sampleOrganId" : sampleOrganId,
                "detectionOrgan" : deteOrgName,"detectionOrganId" : detectionOrganId,"regId" : regId,
            "numbers" : numbers, "deparment" : deparment ,"deparmentId": deparmentId, "uploadTime" : uploadTime,"cityCode":cityCode , "cityName" : cityName},
            beforeSend: function(){
                $("#btnAddTask").attr("disabled",true).html("正在添加...");
            },
            complete: function(){
                $("#btnAddTask").attr("disabled",false).html("添加");
            },
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("添加成功", 'success');
                    window.clear();
                    $("#grid").dataTable().api().ajax.reload();
                }else{
                    jBox.tip(data.msg, 'info');
                }
            }
        });
    }

    /**
     * 加载抽样机构数据
     */
    function loadSampleOrganData() {
        //bootstrap表格
        $("#sampleOrganGrid").dataTable({
            dom : '<"top">rt<"bottom"pli><"clear">',
            ajax : {
                url : "/sofn-ads-web/adsSubjDetection/getPageInfo",
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
                    params.dtName =  $("input[name='orgName']").val();
                    if(levelEnum == "0"){
                        createOrgRegionId = "";
                    }/*else if (levelEnum == "1"){

                        createOrgRegionId = createOrgRegionId.substr(0, 2);
                    }else if (levelEnum == "2"){
                        createOrgRegionId = createOrgRegionId.substr(0, 4);
                    }*//*else if (levelEnum == "3"){
                        createOrgRegionId = createOrgRegionId;
                    }*/
                    else {
                        createOrgRegionId = createOrgRegionId.substr(0, 2);
                    }
                    params.dtAreaId = createOrgRegionId;
                },
                type:"post"
            },
            columns : [
                {
                    data: "dtCode",
                    title: "机构代码"
                },
                {
                    data: "dtName",
                    title: "机构名称"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn" onclick="checkOrgan(\''+row.id+'\',\''+row.dtName+'\')">选择</button>';
                        return look_a;
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
        sampleGrgan++;
    }

    $scope.nextBtn = function () {
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/taskOverView");
    }

    window.clear=function () {
        $("input[name='sampleOrgName']").val("");
        $("input[name='numbers']").val("");
        $("select[name='deparment']").empty();
        $("#sheng10").val(0);
        $("#shi10").val(0);
        $("#xian10").val(0);
        $("input[name='uploadTime']").val("");
    }
})

