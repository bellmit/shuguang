var myapp = angular.module("myapp", []);
angular.module("myapp", []).controller("fileupload", function($scope, $http, $state) {
    var vm = this;
    //主题证照信息
    vm.files = [{
        name: '企业证件照'
    }, {
        name: '组织机构代码照'
    }]
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-tts-web-branch/ttsScltxxcjRegiter/upload',
        //业务参数
        data: function() {
            return {};
        },
        //初始文件数量
        min: 2,
        //最大文件数量
        max: 2,
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传图片', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };
});