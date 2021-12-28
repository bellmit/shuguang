var myapp=angular.module("myapp",[]);
myapp.controller("adsMonitorTaskAdd",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //页面跳转
    $("#adsMonitorTaskAdd").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskAdd")
    });

    $("#organRegionTaskList").click(function(){
        jBox.tip("请保存监测任务", 'info');
    });

    $("#overView").click(function(){
        jBox.tip("请保存监测任务", 'info');
    });

    /**
     * 查询用户基础信息
     */
    $http({
        url:"/sofn-ads-web/adsCheckInfo/findSysUserByToken",
        method:"post",
        params:{"token":token},
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function (data) {
        if(data.httpCode == 200){
            $("#releaseUnit").val(data.user.orgName);
        }
    });

    var formView="";
    var attachment = "";
    var attachmentAddress = "";
    $scope.checkModel="";
    $scope.loadInit=function () {
        $http({
            url:"/sofn-ads-web/adsCheckModel/selectAll",
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (data) {
            if(data.httpCode == 200){
                var datas = data.data;
                $scope.checkModel=datas;
            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
    };

    $(function () {
        $("#view").hide();//隐藏
        $("#down").hide();//隐藏
        $scope.loadInit();
    });

    $scope.monitorClass = ["例行监测","专项监测","监督抽查"];
    $scope.batch = [{site:"1",url:"第一次"},{site:"2",url:"第二次"},{site:"3",url:"第三次"},{site:"4",url:"第四次"}];
    $scope.separation = ["否","是"];
    $scope.year = [];

    var myDate = new Date();
    var startDate = myDate.getFullYear()-50;//起始年份
    var endDate = myDate.getFullYear()+50;//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    $scope.selectedYear = myDate.getFullYear();


    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");

    //新增测试信息
    $scope.addAdsMonitorTask = function(){
        if (!$("#addForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }else{
            var startTime = $("input[name='startTime']").val();
            var endTime = $("input[name='endTime']").val();
            var deadline = $("input[name='deadline']").val();
            if(startTime == null || startTime ==""){
                jBox.tip("请选择开始时间", "info");
                return false;
            }else if(endTime == null ||  endTime == ""){
                jBox.tip("请选择结束时间", 'info');
                return false;
            }else if(deadline == null ||  deadline == ""){
                jBox.tip("请选择截止时间", 'info');
                return false;
            }else if(deadline < startTime){
                jBox.tip("截止时间不能小于开始时间", "info");
                return false;
            }else if(deadline > endTime){
                jBox.tip("截止时间不能大于结束时间", 'info');
                return false;
            } else{
                var taskName = $("input[name='taskName']").val();
                var monitorClass = $('#monitorClass') .val();
                var releaseUnit = $("input[name='releaseUnit']").val();
                var checkModel = $("#checkModel").val();
                var year =$('#year') .val();
                var batch = $scope.selectedBatch;
                if($('#separation') .val() == "否"){
                    var separation = 0;
                }else{
                    var separation = 1;
                }
                var attachmentcode = $("input[name='attachmentcode']").val();
                var comment = $("#comment").val();
                var publishStatus = 0;
                $.ajax({
                    url:"/sofn-ads-web/adsMonitorTask/addAdsMonitorTask",
                    type:"post",
                    dataType:"json",
                    data:{"taskName":taskName,"monitorClass":monitorClass,"releaseUnit":releaseUnit,"checkModel":checkModel,"year":year,
                        "batch":batch,"separation":separation,"startTime":startTime,"endTime":endTime,"deadline":deadline,
                        "attachment":attachment,"attachmentcode":attachmentcode,"comment":comment,"publishStatus":publishStatus,"attachmentAddress":attachmentAddress,
                        "token":token},
                    success:function(data){
                        if(data.httpCode == 500){
                            jBox.tip("保存失败", 'info');
                        }else if(data.httpCode == 400){
                            jBox.tip("没有登录", 'info');
                        }else{
                            window.localStorage.setItem("adsMonitorTaskId",data.adsMonitorTask.id);
                            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/OrganRegion/organRegionTaskList");
                            jBox.tip("保存成功", 'info');
                        }
                    }
                });
            }
        }
    };

    $scope.loadFile = function () {
        $("#loadModal").modal("show");
    };

    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsFile/saveInfo",
        // showPreview:false,
        allowedFileExtensions : ['doc', 'docx','pdf','rar','zip','xlsx','gif','png','jpg'],
        maxFileSize: 10240,
        maxFilesNum: 1,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "请选择{m}个文件！",
        msgSizeTooLarge: "只允许上传10MB的文件"
    }).on("fileuploaded", function(event, data) {
        if(200==data.response.code){
            attachmentAddress = data.response.address;
            attachment = data.response.origFileName;
            $("#view").show();//显示
            $("#down").show();//显示
            formView = attachmentAddress.split("\.");
            $("#attachment").val(attachment);
            $("#loadModal").modal("hide");
            jBox.tip("附件上传成功", 'success');
        }
    });

    //预览附件,只允许pdf,gif,png,jpg格式
    $scope.viewFile = function () {
        if(formView[formView.length-1] == "jpg" || formView[formView.length-1] == "png" ||formView[formView.length-1] == "bmp"){
            $("#viewModal").modal("show");
            $('#viewImg').attr('src',"http://" + attachmentAddress);
        }else{
            jBox.tip("只允许pdf,bmp,png,jpg格式文件进行查看,其它格式文件请下载附件", 'info');
        }
    };

    $scope.downFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', "http://" + attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', attachment);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    };

    $(document).ready(function() {
        $('#addForm').bootstrapValidator();
    });
});