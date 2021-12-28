var myapp=angular.module("myapp",[]);
myapp.controller("editFile",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");

    var judgeStandardId = window.localStorage.getItem("judgeStandardId");

    /**
     * 返回判定标准列表页
     */
    $scope.goJudgeList = function () {
        $state.go("index.content.sys/judgeStandard/adsModelJudgeStandardList");
    }

    /**
     * 跳转到基本信息页面
     */
    $scope.goBaseInfo = function () {
        $state.go("index.content.sys/judgeStandard/editBaseInfo");
    }

    var name = "";
    /**
     * 查询判定标准基础信息
     */
    $http({
        url:"/sofn-sys-web/adsModelJudgeStandard/queryById",
        method:"post",
        dataType:"json",
        params : {"id" : judgeStandardId},
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function(data){
        name = data.adsModelJudgeStandard.name;
        var filePath = data.adsModelJudgeStandard.filePath;
        vm.files = [{
            name: data.adsModelJudgeStandard.name,
            src: ~filePath.indexOf('//') ? filePath : '//' + filePath
        }];
        if(filePath!=null && filePath!=''){
//            $("#file_name").html("附件下载").attr("onclick",filePath);
            var tempname = data.adsModelJudgeStandard.name+ '附件';
            var file = '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\'' +filePath + "','" + tempname + '\')">附件下载</a>';
            $("#file_name").html(file);
        }
//      initFile();
    });


	var vm = this;
	
//  window.printFiles = function() {
//      console.log('xxxxxxxxxxxxxxx', vm.files);
//  }

    //初始文件
//  vm.files = [{ //赋值
//      hidden: true,
//      src: 'https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png',
//      name: 'baidu.com'
//  }, { //占位
//      hidden: false,
//      picture: '../../temp/pixel-c.jpg',
//      title: 'pixel-c.jpg'
//  }, {
//      hidden: false,
//      picture: '../../temp/pixel-c.jpg',
//      title: 'pixel-c.jpg'
//  }];

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-sys-web/adsModelJudgeStandard/saveFile',
        //业务参数
        data: function() {
            return {
                id: judgeStandardId,
                name : name,
                token:token
            };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
	       filter: function(file, info) {
	           var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
	           var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
//	           if (!acceptFileTypes.test(file.type)) {
//	               swal('', '只能上传word类型文档', 'warning');
//	               return false;
//	           }
	           if (file.size > 5 * 1024 * 1024) {
	               swal('', '文件大小不能超过5MB', 'warning');
	               return false;
	           }
	           return true;
	       }
    };

    window.downloadFile = function(fileAddress, filename) {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-sys-web/sysTemplate/dowload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', fileAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', filename);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
	return this;
})

