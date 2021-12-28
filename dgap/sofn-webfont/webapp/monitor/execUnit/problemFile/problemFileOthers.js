/**
 * Created by leipe on 2016/11/2.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("problemFileOthers",function($scope,$http,$state){
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
                    Row = Row+'<div class="text-left pd-20 mg-auto"><form class="form-inline well well-sm"><div class="form-group line-20"><label class="control-task">'+monitorKind[i].dictName+'</label></div><button class="gui-btn" onclick="moreFile('+"'"+monitorKind[i].dictName+"'"+')">更多</button><button class="gui-btn" onclick="loadFile('+"'"+monitorKind[i].dictName+"'"+')">上传</button></form></div>';
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
    var startDate = myDate.getFullYear()-50;//起始年份
    var endDate = myDate.getFullYear()+50;//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    $scope.selectedYear = myDate.getFullYear();

    var monitorType = "其它";
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
        $state.go("index.content.monitor/execUnit/problemFile/problemFileRoutine")
    });

    $("#special").click(function(){
        $state.go("index.content.monitor/execUnit/problemFile/problemFileSpecial")
    });

    $("#audit").click(function(){
        $state.go("index.content.monitor/execUnit/problemFile/problemFileAudit")
    });

    $("#detection").click(function(){
        $state.go("index.content.monitor/execUnit/problemFile/probleFileDetection")
    });

    $("#others").click(function(){
        $state.go("index.content.monitor/execUnit/problemFile/problemFileOthers")
    });
    /**
     * 基地图片
     * @type {string}
     */
    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsFile/saveInfo",
        /*showPreview:false,*/
        allowedFileExtensions : ['jpg', 'png','gif'],
        maxFileSize: 1000,
        maxFilesNum: 1,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "请选择{m}张图片！",
        msgSizeTooLarge: "只允许上传1MB的基地图片"
        // dropZoneTitle: '基地图片预览'
    }).on("fileuploaded", function(event, data) {
        if(200==data.response.code){
            fileAddress = data.response.address;
            var fileName = $("#file-1").val();
            if(fileName!=null && fileName.length > 0){
                var arr = fileName.split("\\");
                fileName = arr[arr.length-1];
            }
            $("#fileName").val(fileName);
            $("#loadModal").modal("hide");
            getNameByParam();
            $("#addModal").modal("show");
        }
    });

    window.loadFile = function (hangYe1) {
        hangYe = hangYe1;
        $("#loadModal").modal("show");
    };

    //刷新任务名称
    $(document).ready(function(){
        $('#year').change(function(){
            getNameByParam();
        });
    });

    window.getNameByParam = function () {
        var adsMonitorTask ={};
        adsMonitorTask.monitorClass = monitorType;
        adsMonitorTask.industry = hangYe;
        adsMonitorTask.year = $("#year option:selected").val();
        //获取任务ID,任务名称,年度
        $http({
            url:"/sofn-ads-web/adsMonitorTask/getNameByParam",
            method:"post",
            data:adsMonitorTask,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.monitorTask=datasx;
                $scope.selectedMonitorTask = $scope.monitorTask[0].taskName;
                monitorTaskId = $scope.monitorTask[0].id;
                year = $scope.monitorTask[0].year;
            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
    };

    //二级联动改变值和获取值
    $(document).ready(function(){
        $('#monitorTask').change(function(){
            var adsMonitorTask ={};
            adsMonitorTask.monitorClass = monitorType;
            adsMonitorTask.industry = hangYe;
            var s = $('#monitorTask option:selected').val();
            $http({
                url:"/sofn-ads-web/adsMonitorTask/getNameByParam",
                method:"post",
                data:adsMonitorTask,
                headers: {
                    post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                }
            }).success(function (data) {
                if(data.httpCode == 200){
                    var datas = data.data;
                    ;  $(datas).each(function (idx) {
                        var a = $(this)[0];
                        if(a.taskName == s){
                            monitorTaskId = a.id;
                            year = a.year;
                            return false;
                        }
                    });

                }else{
                    jBox.tip("数据有误", 'info');
                }
            }).error(function (data) {
                jBox.tip("数据有误", 'info');
            });

        });
    });

    //添加文件
    $scope.addAdsFile = function () {
        var monitorTask = $("#monitorTask option:selected").val();
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
        $state.go("index.content.monitor/execUnit/problemFile/problemFileOthersMore")
    };

/*    //刷新页面
    window.refresh = function () {
        history.go(0);
    };*/

})



