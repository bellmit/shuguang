var myapp = angular.module("myapp", []);
myapp.controller("routineUpload", function ($scope, $http, $state) {

    var fileName= "";
    var monitorType= "例行监测";
    var fileAddress= "";
    var monitorTaskId= "";
    var monitorTask= "";
    //var uploadDepartment= "成都检测中心";
    var year= "";
    var source= "牵头单位";
    var folder= "no";
    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="项目总结报告上传"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    $("#routine").click(function () {
        $state.go('index.content.monitor/leadUnit/reportUpload/routineUpload')
    });

    $("#special").click(function () {
        $state.go("index.content.monitor/leadUnit/reportUpload/specialUpload")
    });

    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.selectTaskName = function(){
        $("#taskNameModal").modal("show");
        setTimeout(function () {
            if(selectTaskNameFlag==0){
                loadTaskName();
            }else{
                $("#taskNameGrid").dataTable().api().ajax.reload();
            }
        },300)
    };

    /**
     * 重新任务名称数据
     */
    $("#seachTaskName").click(function () {
        $("#taskNameGrid").dataTable().api().ajax.reload();
    });

    /**
     * 加载任务名称数据
     */
    function loadTaskName() {
        //bootstrap表格
        $("#taskNameGrid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsMonitorTask/getPageInfo",
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
                    params.year = $('#year option:selected').val();  //查询年度
                    //params.publishStatus = 1;
                    params.is_history = "Y";
                    params.monitorClass = monitorType;
                    params.taskName =  $("input[name='sTaskName']").val();
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data : "taskName",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn btn-sm" onclick="checkTaskName(\''+row.taskName+"\',\'"+row.id+"\',\'"+row.year+'\')">选择</button>';
                        return look_a;
                    }
                },
            ],
        });
        selectTaskNameFlag++;
    }

    /**
     * 选择任务名称
     * @param row
     */
    window.checkTaskName = function(taskName,id,years){
        $("input[name='taskName']").val(taskName);
        monitorTask = taskName;
        monitorTaskId = id;
        year = years;
        $("#taskNameModal").modal("hide");
        $("#grid").dataTable().api().ajax.reload();
    };
    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-ads-web/adsFile/upload',
        //业务参数
        data: function() {
            return { };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        accept:"*",
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx|pdf|xls|xlsx|rar|zip|multipart\/x-zip)|(application\/(pdf|excel|vnd\.ms-excel|x-excel|x-msexcel|x-compressed|x-zip-compressed|zip|msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document|vnd\.openxmlformats-officedocument\.spreadsheetml\.sheet)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            var nameType=/((\.rar)|(\.doc)|(\.docx)|(\.xlsx)|(\.pdf))|(\.xls)|(\.zip)$/i;

            if (!(acceptFileTypes.test(file.type) || nameType.test(file.name))) {
                swal('', '只能上传“doc，docx，pdf，xls，xlsx，rar，zip” 类型文档', 'warning');
                return false;
            }
            if(monitorTaskId == null || monitorTaskId == "" ){
                swal('','请选择一个任务名称', 'warning');
                return false;
            }
            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    // vm.files = [{name: 'wen.png', src:'dddd.com/aaa.png'}];

    /*$("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsFile/saveInfo",
        allowedFileExtensions: ['doc', 'docx', 'pdf', 'xls', 'xlsx', 'rar', 'zip'],
        maxFileSize: 51200,
        maxFilesNum: 1,
        //showPreview: false,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "请选择{m}个文件！",
        msgSizeTooLarge: "只允许上传50MB的文件"
    }).on("click", function(event, data){
        if(monitorTaskId == null || monitorTaskId == "" ){
            jBox.tip("请选择一个任务名称", 'info');
            return false;
        }
    }).on("fileuploaded", function(event, data){
        if(200==data.response.code){
            fileName=data.response.origFileName;
            fileAddress="http://"+ data.response.address;
            addAdsFile();
        }  
    });*/

    $scope.addAdsFile = function () {
        if (vm.files.length <= 0) {
            alert("附件没上传,请先进行上传操作");
            return false;
        }
        console.log(JSON.stringify(vm.files[0])); 
        fileName = vm.files[0].name;
        fileAddress = vm.files[0].src;
        $.ajax({
            url:"/sofn-ads-web/adsFile/addAdsFile",
            type:"post",
            dataType:"json",
            data:{"fileAddress":fileAddress,"monitorTaskId":monitorTaskId,"monitorTask":monitorTask,
                "fileName":fileName,
                "monitorType":monitorType,"years":year,"source":source,"folder":folder,
                "token":token},
            success:function(data){
                if(data.httpCode == 200){
                    jBox.tip("上传成功", 'success');
                    $state.go("index.content.monitor/leadUnit/reportReview/reportReviewRoutine")
                }else{
                    jBox.tip("上传失败", 'error');
                }
            }
        });
    }

    //重置
    $scope.resetBtn = function(){
        $(".gui-select select").val("");
        $(".gui-input").val("");
        monitorTaskId = "";
    }
    window.check=function(e) {
        var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        if(e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入30个汉字或者60个字母/数字");
                e.value = "";
                e.focus();
            }
        }
    }
})
