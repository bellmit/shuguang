//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjBaseUpdate",function($scope,$http,$state){
		$scope.ttsScltxxcjBase = {};
		$.fn.mycity("sheng","shi","xian");
		//校验样式
		$('#attributeForm').bootstrapValidator();//必须有
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjBase_id");
		$http({
			headers:{
				token:window.localStorage.getItem("token")
			},
			url:"/sofn-tts-web/ttsScltxxcjBase/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
			$.fn.mycity("sheng","shi","xian",data.ttsScltxxcjBase.addressCode);
			$("#yulan-2").attr("src", data.ttsScltxxcjBase.pictureTwo);
	    	$("input[name='name']").val(data.ttsScltxxcjBase.name);
	    	$("input[name='area']").val(data.ttsScltxxcjBase.area);
	    	$("input[name='address']").val(data.ttsScltxxcjBase.address);
	    	$("input[name='manager']").val(data.ttsScltxxcjBase.manager);
	    	$("input[name='phone']").val(data.ttsScltxxcjBase.phone);
	    	$("input[name='productNames']").val(data.ttsScltxxcjBase.productNames);
	    	$("input[name='longitude']").val(data.ttsScltxxcjBase.longitude);
	    	$("input[name='latitude']").val(data.ttsScltxxcjBase.latitude);
			$("#yulan-1").attr("src", data.ttsScltxxcjBase.picture);
			$("#status").attr('value',data.ttsScltxxcjBase.status);
	    	$("input[name='ip']").val(data.ttsScltxxcjBase.ip);
	    	$("input[name='delFlag']").val(data.ttsScltxxcjBase.delFlag);
	    	$("input[name='createBy']").val(data.ttsScltxxcjBase.createBy);
	    	$("input[name='createTime']").val(data.ttsScltxxcjBase.createTime);
		});
		//
        $scope.updateTtsScltxxcjBase = function(){
			//表单
			var name = $("input[name='name']").val();
			var address = $("input[name='address']").val();
			var area = $("input[name='area']").val();
			var manager = $("input[name='manager']").val();
			var phone = $("input[name='phone']").val();
			var status = $("select[name='status']").val();
			var tupian1 = $("#yulan-1")[0].src;
			var tupian2 = $("#yulan-2")[0].src;
			var xian = $("#xian").val();
			if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
				return false;
			}
			/*if($("#lon").val()==undefined||$("#lon").val()==null||$("#lon").val()==""||$("#lat").val()==undefined||$("#lat").val()==null||$("#lat").val()=="" ){
				jBox.tip("经纬度为空", 'info');
				return false;
			}*/
			if(name != null && name != '' && address != null && address != '' && manager != null && manager != ''
				&& phone != null && phone != '' && status != null && status != '' && tupian1 != null && tupian1 != ''
				&& tupian2 != null && tupian2 != ''){
				/*if(!(name.match('^[\u4e00-\u9fa5_a-zA-Z0-9]+$')) || (name.length >32 || name.length < 1)){
					jBox.tip("基地名称只能输入1~32个汉字、数字和字母", 'info');
					$("input[name='name']").val('');
					return;
				}
				if(xian == '0'){
					jBox.tip("你还没有选择区县栏", 'info');
					return;
				}
				if(!(address.match('^[\u4e00-\u9fa5_a-zA-Z0-9]+$')) || (address.length >32 || address.length < 1)){
					jBox.tip("基地地址只能输入1~32个汉字、数字和字母", 'info');
					$("input[name='address']").val('');
					return;
				}
				if(!(/^\+?[1-9]\d*$/.test(area)) || area > 99999){
					jBox.tip("基地面积只能输入1~99999之间的数字", 'info');
					$("input[name='area']").val('');
					return;
				}
				if(!(manager.match('[\u4e00-\u9fa5]')) || (manager.length > 10 || manager < 1)){
					jBox.tip("基地负责人只能输入1~10个中文字符", 'info');
					$("input[name='manager']").val('');
					return;
				}
				if(!(/^1[34578]\d{9}$/.test(phone))){
					jBox.tip("手机号码格式不正确", 'info');
					$("input[name='phone']").val('');
					return;
				}*/
			}else{
				jBox.tip("您还有未正确填写的栏", 'info');
				return;
			}
			$scope.ttsScltxxcjBase.pictureTwo = $("#yulan-2")[0].src;
			$scope.ttsScltxxcjBase.name = $("input[name='name']").val();
			$scope.ttsScltxxcjBase.area = $("input[name='area']").val();
			$scope.ttsScltxxcjBase.address =$("input[name='address']").val();
			$scope.ttsScltxxcjBase.addressCode = $("#xian").val();
			$scope.ttsScltxxcjBase.addressName = $("#xian").find("option:selected").text();
			$scope.ttsScltxxcjBase.manager = $("input[name='manager']").val();
			$scope.ttsScltxxcjBase.phone = $("input[name='phone']").val();
			$scope.ttsScltxxcjBase.productNames = $("input[name='productNames']").val();
			$scope.ttsScltxxcjBase.longitude = $("input[name='longitude']").val();
			$scope.ttsScltxxcjBase.latitude = $("input[name='latitude']").val();
			$scope.ttsScltxxcjBase.picture = $("#yulan-1")[0].src;
			$scope.ttsScltxxcjBase.status = $("select[name='status']").val();
			$scope.ttsScltxxcjBase.delFlag = $("input[name='delFlag']").val();
	    	 $scope.ttsScltxxcjBase.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjBase/updateTtsScltxxcjBase?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
                    data:$scope.ttsScltxxcjBase,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
					if(data.httpCode==200){
						jBox.tip("修改成功", 'info');
					}else{
						jBox.tip("修改失败", 'error');
					}
                $state.go("index.content.tts/TtsScltxxcjBase/TtsScltxxcjBaselist");
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};


		/**
		 * 基地图片1
		 * @type {string}
		 */
		$scope.LunboName = "";
		$scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjBase/upload";
		$("#file-1").fileinput({
			language: 'zh',
			uploadUrl: $scope.uploadUrl,
			allowedFileExtensions : ['jpg', 'png','gif'],
			maxFileSize: 5300,
			maxFilesNum: 1,
			enctype: 'multipart/form-data',
			maxFileCount: 1, //表示允许同时上传的最大文件个数
			msgFilesTooMany: "请选择{m}张图片！",
			msgSizeTooLarge: "只允许上传5MB的基地图片",
			dropZoneTitle: '基地图片预览'
		}).on("fileuploaded", function (event, data, previewId, index) {
			if (data && data['response']) {
				$scope.response = data.response.path;
				$("#yulan-1").attr("src", data.response.path);
			}
			return;
		});
		/**
		 * 基地图片2
		 * @type {string}
		 */
		$("#file-2").fileinput({
			language: 'zh',
			uploadUrl: $scope.uploadUrl,
			allowedFileExtensions : ['jpg', 'png','gif'],
			overwriteInitial: false,
			maxFileSize: 5300,
			maxFilesNum: 1,
			enctype: 'multipart/form-data',
			maxFileCount: 1, //表示允许同时上传的最大文件个数
			msgFilesTooMany: "请选择{m}张图片！",
			msgSizeTooLarge: "只允许上传5MB的基地图片",
			dropZoneTitle: '基地图片预览'
		}).on("fileuploaded", function (event, data, previewId, index) {
			if (data && data['response']) {
				$scope.response = data.response.path;
				$("#yulan-2").attr("src", data.response.path);
			}
			return;
		});
		$scope.showUpload1 = function() {
			//打开上传窗口
			$("#myModal1").modal('show');
		}
		$scope.showUpload2 = function() {
			//打开上传窗口
			$("#myModal2").modal('show');
		}
    });


