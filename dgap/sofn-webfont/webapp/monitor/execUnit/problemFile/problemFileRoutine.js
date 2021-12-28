/**
 * Created by leipe on 2016/11/2.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("problemFileRoutine",function($scope,$http,$state){

    $scope.iswrite = "";
    var b = window.localStorage.getItem("AdsMenuObj");
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="问题单据"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    var token = window.localStorage.getItem("token");

    var monitorKind = new Array();
    window.loadA = function () {
        /**
         * 加载监测种类字典
         */
        $.ajax({
            url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
            type: "post",
            dataType: "json",
            success: function (data) {
                monitorKind = data.data;
                var Row="";
                Row=Row+'<div class="tab-content">';
                for(var i=0;i<monitorKind.length;i++){
                    if($scope.disabled){
                        Row = Row+'<div class="text-left pd-20 mg-auto"><form class="form-inline well well-sm"><div class="form-group line-20"><label class="control-task">'+monitorKind[i].dictName+'</label></div><button type="button" class="gui-btn" onclick="moreFile('+"'"+monitorKind[i].dictName+"'"+');">更多</button><button type="button" class="gui-btn" style="background-color: grey;">上传</button></form></div>';
                    }else {
                        Row = Row+'<div class="text-left pd-20 mg-auto"><form class="form-inline well well-sm"><div class="form-group line-20"><label class="control-task">'+monitorKind[i].dictName+'</label></div><button type="button" class="gui-btn" onclick="moreFile('+"'"+monitorKind[i].dictName+"'"+');">更多</button><button type="button" class="gui-btn" onclick="loadFile('+"'"+monitorKind[i].dictName+"'"+');">上传</button></form></div>';
                    }

                }
                Row=Row+'</div>';
                $("#list").html(Row);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

    };

    //年度
    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    $scope.selectedYear = myDate.getFullYear();

    var monitorType = "例行监测";
    var hangYe ="";
    var uploadDepartment = "问题管理单位";
    var source = "牵头单位";
    var year = "";
    var monitorTaskId = "";
    var fileAddress = "";
    $scope.monitorTask = "";
    $(function() {
        loadA();
    });


    $("#routine").click(function(){
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileRoutine")
    });

    $("#special").click(function(){
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileSpecial")
    });

    $("#audit").click(function(){
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileAudit")
    });

    $("#detection").click(function(){
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/probleFileDetection")
    });

    $("#others").click(function(){
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileOthers")
    });

    window.loadFile = function (hangYe1) {
        hangYe = hangYe1;
        $("#loadModal").modal("show");
    };

    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-ads-web/adsFile/saveInfo',
        //业务参数
        data: function() {
            return { };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传“jpg，png，jpeg，bmp，gif” 类型图片', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.loadFile2 = function () {
        var fileName = vm.files[0].name;
        fileAddress = vm.files[0].src;
        if(fileName!=null && fileName.length > 0){
            var arr = fileName.split("\\");
            fileName = arr[arr.length-1];
        }
        $("#fileName").val(fileName);
        $("#loadModal").modal("hide");
        $("#addModal").modal("show");
    };

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.selectTaskName = function(){
        $("#addModal").modal("hide");
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
                url : "/sofn-ads-web/adsMonitorTask/getPageInfoTaskName",
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
                    params.taskName = $("input[name='sTaskName']").val();
                    params.monitorClass = monitorType;
                    params.industry = hangYe;
                    params.year = $("#year option:selected").val();
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
    window.checkTaskName = function(taskName,id,sYear){
        $("input[name='taskName']").val(taskName);
        monitorTaskId = id;
        year = sYear;
        $("#taskNameModal").modal("hide");
        $("#addModal").modal("show");
    };

    //添加文件
    $scope.addAdsFile = function () {
        var monitorTask = $("#taskName").val();
        //var monitorTask = $("#monitorTask option:selected").val();
        var fileName = $("#fileName").val();
        var folder = "YES";
        if(monitorTaskId == null || monitorTaskId == ""){
            jBox.tip("请选择一个任务", 'info');
            return false;
        }else{
            $.ajax({
                url:"/sofn-ads-web/adsFile/addAdsFile",
                type:"post",
                dataType:"json",
                data:{"fileAddress":fileAddress,"monitorTaskId":monitorTaskId,"monitorTask":monitorTask,
                    "uploadDepartment":uploadDepartment,"fileName":fileName,
                    "monitorType":monitorType,"years":year,"field":hangYe,"source":source,"folder":folder,
                    "token":token},
                success:function(data){
                    if(data.httpCode == 200){
                        jBox.tip("保存成功", 'success');
                        $("#addModal").modal("hide");
                        //moreFile(hangYe);
                    }else{
                        jBox.tip("保存失败", 'error');
                    }
                }
            });
        }
    };

    //跳转到更多图片页面
    window.moreFile = function (hangYe1) {
        window.localStorage.setItem("monitorKind",hangYe1);//行业
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileRoutineMore")
    };

    //取消选择任务并返回上一个模态框
    $scope.cancel = function () {
        $("#addModal").modal("show");
    };

});
