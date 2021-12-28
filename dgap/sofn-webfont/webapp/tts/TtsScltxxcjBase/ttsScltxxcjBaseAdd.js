var myapp=angular.module("myapp",[]).controller("ttsScltxxcjBaseAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")
    $.fn.mycity("sheng","shi","xian")
    $scope.ttsScltxxcjBase = {};
    //校验样式
    $('#attributeForm').bootstrapValidator();//必须有
    $scope.addTtsScltxxcjBase = function(){
        // 自定义按钮
        var defaultImg = '../../../images/baseImage/noDelete.jpg';
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
        if($("#lon").val()==undefined||$("#lon").val()==null||$("#lon").val()==""||$("#lat").val()==undefined||$("#lat").val()==null||$("#lat").val()=="" ){
            jBox.tip("经纬度为空", 'info');
            return false;
        }
        if(!$("#xian option:selected").val()){
            jBox.tip("所属区域请选择完整");
            return false;
        }
        if(getImgSrc($("#yulan-1")[0].src)=="noDelete.jpg" || getImgSrc($("#yulan-2")[0].src) == "noDelete.jpg"){
            jBox.tip("请将基地图片上传完整", 'info');
            return false;
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
        $scope.ttsScltxxcjBase.longitude = $("#lon").val();
        $scope.ttsScltxxcjBase.latitude = $("#lat").val();
        $http({
            url:"/sofn-tts-web/ttsScltxxcjBase/addTtsScltxxcjBase?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
            data:$scope.ttsScltxxcjBase,
            method:"post",
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("保存成功", 'info');
            }else{
                jBox.tip("保存失败", 'error');
            }
            $state.go("index.content.tts/TtsScltxxcjBase/TtsScltxxcjBaselist");
        })
    }



    /**
     * 获取所选图片名称，注意，不是获取路径
     * @param src
     */
    function getImgSrc(src){
        var index = src .lastIndexOf("\/");
        src  = src .substring(index + 1, src .length);
        return src;
    }
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
})