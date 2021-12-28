var myapp=angular.module("myapp",[]);
myapp.controller("adsMonitorTaskView",function($scope,$http,$state, $stateParams){
	var token = window.localStorage.getItem("token");
	var monitorClass = window.localStorage.getItem("monitorClass");
	var task = window.localStorage.getItem("task");
	$("#adsMonitorTaskView").click(function(){
		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView")
	});
	var checkModelFlag = "";
    var deadlineFlag = "";
	$("#organRegionTaskList").click(function(){
		if(checkModelFlag == "" || checkModelFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
		}
        if(deadlineFlag == "" || deadlineFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
        }
        window.localStorage.setItem("token",token);
        window.localStorage.setItem("levelEnum",levelEnum);
        window.localStorage.setItem("createOrgRegionId",createOrgRegionId);
        window.localStorage.setItem("separation",separationData);
		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/OrganRegion/organRegionTaskList")
	});

	$("#overView").click(function(){
        if(checkModelFlag == "" || checkModelFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
        }
        if(deadlineFlag == "" || deadlineFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
        }
        window.localStorage.setItem("token",token);
		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/taskOverView")
	});

	var separationData = "";
	var attachment = "";
	var form="";
	var address = "";
	var checkModelValue="";
	$scope.checkModel="";
	$scope.address="";
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
	//导航对齐

	$("#collapseTwo").attr("cla`ss","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
	$scope.id=window.localStorage.getItem("adsMonitorTaskId");
    var a = $scope.id;

	$http({
		url:"/sofn-ads-web/adsNewModel/selectAllModel",
        data:token,
		method:"post",
		headers: {
			post: { 'Content-Type': 'application/x-www-form-urlencoded' }
		}
	}).success(function (datax) {
		if(datax.httpCode == 200){
			var datasx = datax.data;
			$scope.checkModel=datasx;
			$scope.loadTaskInfo();
		}else{
			jBox.tip("数据有误", 'info');
		}
	}).error(function (data) {
		jBox.tip("数据有误", 'info');
	});

	var levelEnum = "";
	var createOrgRegionId = "";
	/**
	 * 加载监测任务基础信息
     */
	$scope.loadTaskInfo= function () {
		$http({
			url:"/sofn-ads-web/adsMonitorTask/queryById",
			data:a,
			method:"post",
			headers: {
				post: { 'Content-Type': 'application/x-www-form-urlencoded' },
                token: window.localStorage.getItem("token")
			}
		}).success(function(data){
			if(data.code == 200){
				checkModelValue = data.adsMonitorTask.checkModel;
				$scope.selectedCheckModel = checkModelValue;
				var publishStatus = data.adsMonitorTask.publishStatus;
				var update = data.adsMonitorTask.taskSource;
				if(update == "DEPTASK" && publishStatus == 0){
					$('input').attr("readonly",false);
					$('select').removeAttr("disabled");
					$("#upDate").css('display','block');
					$("#next").css('display','block');
					$('#startTime').attr("readonly","readonly");
					$('#endTime').attr("readonly","readonly");
					$('#deadline').attr("readonly","readonly");
					$('#comment').attr("readonly",false);
					$('#attachment').attr("readonly","readonly");
				}else if(update == "SUPTASK"){
					$("#tip_info").removeClass("hide").html("提示，该任务类型为监管局下发的任务，不能修改任务配置。");
					$("#loadFile").removeAttr("onclick");
					$("#startTime").removeAttr("onclick");
					$("#endTime").removeAttr("onclick");
					$("#deadline").removeAttr("onclick");
					$("#loadFile").css('display','none');
					$("#organRegionTaskList").css('display','none');
					$("#zong").css('display','none');
				}
				if(update == "SDPTASK"){
					$("#tip_info").removeClass("hide").html("提示，该任务类型为监管局执行的任务，只能修改部分任务配置。");
					$("#loadFile").css('display','none');
					$('#taskName').attr("readonly",false);
					$('#checkModel').removeAttr("disabled");
					$('#batch').removeAttr("disabled");
					$('#separation').removeAttr("disabled");
					$('#comment').attr("readonly",false);
					$("#upDate").css('display','block');
					$("#next").css('display','block');
				}
				if(publishStatus != 0){
					$("#tip_info").removeClass("hide").html("提示，该任务已发布，只能查看，不能修改任务配置。");
					$("#loadFile").addClass("hide");
					//已经发布的任务，只能查看,不用再提交
					$('input').attr("disabled","disabled");
					$('select').attr("disabled","disabled");
					$('#comment').attr("disabled","disabled");
					//不用再保存，去掉保存按钮
					$("#upDate").css('display','none');
				}
				if(publishStatus == 2 || publishStatus == 3){
                    $("#loadFile").removeAttr("onclick");
                    $("#startTime").removeAttr("onclick");
                    $("#endTime").removeAttr("onclick");
                    $("#deadline").removeAttr("onclick");
                    $("#loadFile").css('display','none');
                    $("#zong").css('display','none');
                }
                checkModelFlag = data.adsMonitorTask.checkModel;
                deadlineFlag = data.adsMonitorTask.deadline;
				$scope.selectedMonitorClass = data.adsMonitorTask.monitorClass;
				//$("select[name='monitorClass']").find("option[text='专项监测']").attr("selected","selected");
				$scope.taskName = data.adsMonitorTask.taskName;
				$scope.releaseUnit = data.adsMonitorTask.releaseUnit;
				$scope.selectedYear = data.adsMonitorTask.year;
				$scope.selectedBatch = data.adsMonitorTask.batch;

                var priority = data.adsMonitorTask.priority
				if (priority == "1"){
                    $scope.priority = "特急";
				}else if (priority == "2"){
                    $scope.priority = "紧急";
				}else {
                    $scope.priority = "一般";
				}

                separationData = data.adsMonitorTask.separation;
				if(separationData == 0){
					$scope.selectedSeparation = 0;
				}else if(separationData == 1){
					$scope.selectedSeparation = 1;
				}
				$scope.startTime = data.adsMonitorTask.startTime;
				$scope.endTime = data.adsMonitorTask.endTime;
				$scope.deadline = data.adsMonitorTask.deadline;
				attachment = data.adsMonitorTask.attachment;
				//$scope.attachment = data.adsMonitorTask.attachment;
				$scope.attachmentcode = data.adsMonitorTask.attachmentcode;
				$scope.comment = data.adsMonitorTask.comment;
				$scope.publishStatus = data.adsMonitorTask.publishStatus;
				address = data.adsMonitorTask.attachmentAddress;
                createOrgRegionId = data.adsMonitorTask.createOrgRegionId;
                $scope.createOrgRegionId = $.fn.Cityname(createOrgRegionId);
                levelEnum = data.adsMonitorTask.levelEnum;
                /**
                 * 加载机构级别字典
                 */
                $http({
                    url:"/sofn-ads-web/adsDictData/getLevelDictData",
                    method:"post"
                }).success(function(data){
                    $.each(data.data,function (idx) {
                        var a = $(this)[0];
                        if(a.dictValue == $.trim(levelEnum)){
                            $scope.levelEnum = a.dictName;
                        }
                    });
                })
				if(address != null){
					//截取文件后缀
					form = address.split("\.");
					$scope.attachment =data.adsMonitorTask.attachment;
					if(form[form.length-1] == "pdf"){
						$('#view').attr('href',"http://"+data.adsMonitorTask.attachmentAddress);
					}else{
						$('#view').attr('href',"");
					}
				}
			}else{
				jBox.tip("数据有误", 'info');
			}
		}).error(function(){
			jBox.tip("数据有误", 'info');
		});
	}

	var myDate = new Date();
	//更新
	$('#upDate').click(function () {
		if (!$("#viewForm").data('bootstrapValidator').validate().isValid()) {
			return false;
		}else{
			var id = a;
			var taskName = $('#taskName').val();
			var monitorClass = $('#monitorClass') .val();
			var releaseUnit = $('#releaseUnit').val();
			var checkModel = $("#checkModel").find("option:selected").val();//checkModelValue;
			var year =$('#year') .val();
			var batch = $scope.selectedBatch;
			var separation = $("#separation").find("option:selected").val();
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var deadline = $('#deadline').val();
			attachment = $('#attachment').val();
			var attachmentcode = $('#attachmentcode').val();
			var comment = $('#comment').val();
			var attachmentAddress = address;
			if(startTime == null || startTime ==""){
				jBox.tip("请选择开始时间", "info");
				return false;
			}else if(endTime == null ||  endTime == ""){
				jBox.tip("请选择结束时间", 'info');
				return false;
			}else if(deadline == null ||  deadline == ""){
				jBox.tip("请选择截止时间", 'info');
				return false;
			}else if(new Date(deadline.replace("-", "/").replace("-", "/")) < new Date(startTime.replace("-", "/").replace("-", "/"))){
				jBox.tip("截止时间不能小于开始时间", "info");
				return false;
			}else if(new Date(deadline.replace("-", "/").replace("-", "/")) > new Date(endTime.replace("-", "/").replace("-", "/"))){
				jBox.tip("截止时间不能大于结束时间", 'info');
				return false;
			}else if(checkModel == null || checkModel =="" || checkModel=="? object:null ?"){
				jBox.tip("请选择检测模型", "info");
				return false;
			}else if(new Date(endTime.replace("-", "/").replace("-", "/")) < new Date(myDate.toLocaleDateString().replace("-", "/").replace("-", "/"))){
                jBox.tip("结束时间不得小于当前时间", 'info');
                return false;
			}else {
				$.ajax({
					url:"/sofn-ads-web/adsMonitorTask/updateAdsMonitorTask",
					type:"post",
					dataType:"json",
					data:{"id":id,"taskName":taskName,"monitorClass":monitorClass,"releaseUnit":releaseUnit,"checkModel":checkModel,"year":year,
						"batch":batch,"separation":separation,"startTime":startTime,"endTime":endTime,"deadline":deadline,
						"attachment":attachment,"attachmentcode":attachmentcode,"comment":comment,"attachmentAddress":attachmentAddress,
						"token":token},
					success:function(data){
						if(data.httpCode == 500){
							jBox.tip("保存失败", 'info');
						}else if(data.httpCode == 400){
							jBox.tip("没有登录", 'info');
						}else{
                            checkModelFlag = checkModel;
                            deadlineFlag = deadline;
							$state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
							jBox.tip("保存成功", 'info');
						}
					}
				});
			}
		}
	});

	$('#next').click(function () {
        if(checkModelFlag == "" || checkModelFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
        }
        if(deadlineFlag == "" || deadlineFlag == null){
            jBox.tip("请保存基本信息", 'info');
            return false;
        }
		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
        window.localStorage.setItem("token",token);
        window.localStorage.setItem("levelEnum",levelEnum);
        window.localStorage.setItem("createOrgRegionId",createOrgRegionId);
        window.localStorage.setItem("separation",separationData);
		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/OrganRegion/organRegionTaskList")
	});

    //清除缓存
    // $('#removeLocalStorage').click(function () {
		// window.localStorage.removeItem("adsMonitorTaskId");
		// var mon = window.localStorage.getItem("monitorClass");
		// if(mon == "例行监测"){
		// 	if ($stateParams.source && $stateParams.source == 'history') {
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList");
		// 	}else if ($stateParams.source && $stateParams.source == 'pub'){
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList");
		// 	}else{
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList");
		// 	}
		// }else if(mon == "专项监测"){
		// 	if ($stateParams.source && $stateParams.source == 'history') {
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList");
		// 	}else if ($stateParams.source && $stateParams.source == 'pub'){
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList");
		// 	}else{
		// 		$state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList");
		// 	}
		// }
    //
    // });

	//下载时无附件
	$scope.downFile = function () {
		if(address == null){
			jBox.tip("请先上传附件后再下载", 'info');
		}
	};

	//上传附件
	window.loadFile = function () {
		$("#loadModal").modal("show");
	};
	$("#file-1").fileinput({
		language: 'zh',
		uploadUrl: "/sofn-ads-web/adsFile/saveInfo",
		// showPreview:false,
		allowedFileExtensions : ['doc', 'docx','pdf','rar','zip','xlsx','bmp','png','jpg'],
		maxFileSize: 51200,
		maxFilesNum: 1,
		enctype: 'multipart/form-data',
		maxFileCount: 1, //表示允许同时上传的最大文件个数
		msgFilesTooMany: "请选择{m}个文件！",
		msgSizeTooLarge: "只允许上传10MB的文件"
		// dropZoneTitle: '基地图片预览'
	}).on("fileuploaded", function(event, data) {
		if(200==data.response.code){
			attachment = data.response.origFileName;
			address = data.response.address;
			form = address.split("\.");
			if(form[form.length-1] == "pdf"){
				$('#view').attr('href',"http://"+data.response.address);
			}else{
				$('#view').attr('href',"");
			}
			$("#attachment").val($("#file-1").val());
			$("#loadModal").modal("hide");
			jBox.tip("附件上传成功", 'success');
		}
	});

	//预览附件,只允许pdf,gif,png,jpg格式
	$scope.viewFile = function () {
		if(address == null){
			jBox.tip("请先上传附件后再查看", 'info');
			return false;
		}
		if(form[form.length-1] == "jpg" || form[form.length-1] == "png" ||form[form.length-1] == "bmp"){
			$("#viewModal").modal("show");
			$('#viewImg').attr('src',"http://" + address);
		}else{
			jBox.tip("只允许pdf,bmp,png,jpg格式文件进行查看,其它格式文件请下载附件", 'info');
		}
	};

	$scope.downFile=function(){
		if(address == "" || address == null){
			jBox.tip("请先上传附件后再下载",'info');
			return false;
		}
		var form = $("<form>");
		form.attr('style', 'display:none');
		form.attr('target', '');
		form.attr('method', 'post');
		form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
		var input1 = $('<input>');
		input1.attr('type', 'hidden');
		input1.attr('name', 'fileUrl');
		input1.attr('value', "http://" + address);
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
		$('#viewForm').bootstrapValidator();
	});
});