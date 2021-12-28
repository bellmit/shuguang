var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("fileinput",function($scope,$http){
    //商品主图
    var GoodsName="";
//轮播图
    $scope.LunboName="";
    $scope.uploadUrl="/sofn-asms-web/subjectSupervise/  ";
    $("#file-1").fileinput({
        language: 'zh',
        showPreview:true,//预览区域是否显示，默认为true
        showRemove:false,//是否显示删除/清空按钮。默认值true
        showCaption:true,//是否显示文件的标题。默认值true。
        showUpload:true,//是否显示文件上传按钮。默认是submit按钮，除非指定了uploadUrl属性。默认值true。
        uploadExtraData:{"id":"123.js"},//传参
        uploadUrl: $scope.uploadUrl,
        overwriteInitial: false,
        maxFileSize: 1000,//文件大小限制
        maxFilesNum: 10,//上传数量
        allowedFileExtensions:['jpg', 'gif', 'png'],
        enctype: 'multipart/form-data',


        maxFileCount: 5, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "最多上传{m}张图片！",
        msgSizeTooLarge: "只允许上传1MB的商品图片",
        dropZoneTitle: '肖烈'
    });
    //var response = [];
    $("#file-1").on("fileuploaded", function (event, data, previewId, index) {
        if (data && data['response']) {
            $scope.response="http://192.168.0.163:8192/sofn-asms-web/"+data.response.path;
            $("#myImg").attr("src","http://192.168.0.163:8192/sofn-asms-web/"+data.response.path);
            console.log($scope.response);



//            if (response['code'] == '200') {
//                alert('1')
//                GoodsName = response['api_data'];
//                console.log(GroupName);
//                //alert(imageName)
////                logoImgEl.attr('src',imageUrl+imageName);
//                $("#logo").val(GroupName);
////                logoUploadDiv.hide();
////                fileLogo.fileinput('reset');
//                $("#cancalLogoButton").hide();
//            }
        }
        return;
    });
    $scope.name="xiaolie";
    $scope.scr="../../assets/imgs/photo/u4694.png"

});

