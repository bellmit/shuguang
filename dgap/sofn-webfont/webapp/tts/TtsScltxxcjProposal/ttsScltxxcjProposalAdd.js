var myapp=angular.module("myapp",[]).controller("ttsScltxxcjProposalAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //校验样式
    $('#attributeForm').bootstrapValidator();//必须有


    //modal 展示
    $scope.myModal61=function(){
        $("#myModal1").modal("show");
        /**
         * 基地图片1
         * @type {string}
         */
        $scope.LunboName = "";
        $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjProposal/upload";
        $("#file-1").fileinput({
            language: 'zh',
            uploadUrl: $scope.uploadUrl,
            //allowedFileExtensions : ['jpg', 'png','gif'],
            maxFileSize: 5300,
            maxFilesNum: 1,
            enctype: 'multipart/form-data',
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            msgFilesTooMany: "请选择{m}个附件！",
            msgSizeTooLarge: "只允许上传5MB以内的附件",
            dropZoneTitle: '附件预览'
        }).on("fileuploaded", function (event, data, previewId, index) {
            if (data && data['response']) {
                $scope.response = data.response.path;
                $("#attachPath").val(data.response.path);
                $("#fileName").val($($('div[class="file-caption-name"]')[0]).text());
            }
            return;
        });
    }

    $scope.setFileInput = function(){
        if(!$($('div[class="file-caption-name"]')[0]).text()){
            $("#attachPath").val("");
            $("#fileName").val("");
        }
    }

    //返回创建
    $scope.returenAdd = function(){
        $state.reload("index.content.tts/TtsScltxxcjProposal/ttsScltxxcjProposalAdd");
        //$("#addForm").show();
       // $("#success").hide();
    }
    //新增测试信息
    $scope.addTtsScltxxcjProposal = function(){
        var submit = null;
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        var userId;
        if(!entityInfo){
           jBox.tip("你已注销！", "提示", submit, { buttons: { '确认': true} });
            return ;
        }
        userId = entityInfo.data.id;
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有

        $scope.ttsScltxxcjProposal.title = $("input[name='title']").val();
        $scope.ttsScltxxcjProposal.content = $("textarea[name='content']").val();
    	// $scope.ttsScltxxcjProposal.time = $("input[name='time']").val();
    	 $scope.ttsScltxxcjProposal.attachName = $("#fileName").val();
    	 $scope.ttsScltxxcjProposal.attachPath = $("#fileName").val()?$("#attachPath").val():"";
    	 //$scope.ttsScltxxcjProposal.entityIdcode = entityInfo.data.entityIdcode;
    	 //$scope.ttsScltxxcjProposal.userIdcode = entityInfo.data.userIdcode;
    	 $scope.ttsScltxxcjProposal.ip = $("input[name='ip']").val();
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-tts-web/ttsScltxxcjProposal/addTtsScltxxcjProposal?userId="+userId,
            data:$scope.ttsScltxxcjProposal,
            method:"post"
        }).success(function(data){
            if(data.code && data.code =='200'){

                // alert("保存成功");
              jBox.tip("保存成功！", "提示", submit, { buttons: { '确认': true} });
                $("#addForm").hide();
                $("#success").show();
            }else{
                $.jBox.info("保存失败！", "提示", submit, { buttons: { '确认': true} });
            }

            //$state.go("index.content.tts/ttsScltxxcjProposal/TtsScltxxcjProposallist");
        }).error(function(){
            jBox.tip("保存失败！", "提示", submit, { buttons: { '确认': true} });
            //alert("保存失败");
        })
    }
})