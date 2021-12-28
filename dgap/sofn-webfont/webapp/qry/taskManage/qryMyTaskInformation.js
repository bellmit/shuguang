var myapp=angular.module("myapp",[]);
myapp.controller("qryMyTaskInformation",function($scope,$http,$state){
    /**
     * *
     * *承担任务涵盖监测信息，一个承担任务（监督抽查任务）包含多个监测对线信息
     * *
     * */

    /**
     *公共值
     * */
    $scope.taskId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr");//任务id
    $scope.oprIdx = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");//任务操作标示

    /**
     * 任务基本信息及检测对象
     * */
    $scope.initInfo =  function() {
        //基本信息
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/alesmynewchecktask/getTaskById",
            method:"post",
            data:{id:$scope.taskId}
        }).success(function(obj){
            $scope.checkTask = obj.data;
            $scope.checkTask.taskBeginTime = new Date(parseInt(obj.data.taskBeginTime)+28800000).toISOString().slice(0,10);
            $scope.checkTask.taskEndTime = new Date(parseInt(obj.data.taskEndTime)+28800000).toISOString().slice(0,10);
            $scope.checkTask.taskSampleDeadline = new Date(parseInt(obj.data.taskSampleDeadline)+28800000).toISOString().slice(0,10);
            $scope.checkTask.createTime = new Date(parseInt(obj.data.createTime)+28800000).toISOString().slice(0,10);
            console.log("========"+$scope.checkTask.attachmentName);
            downloadFile($scope.checkTask.attachmentName);
            var area = obj.data.createOrgRegionId
            $scope.checkTask.createOrgRegionId= $.fn.Cityname(area);

            //根据任务级别显示承担单位、抽样单位、检测单位
            if("0" == obj.data.taskLevel){
                $("div[name='cdUnit']").show();
                //承担单位
                $scope.checkTask.cdUnits = "";
                if(obj.speList.length != 0){
                    for (var i = 0; i < obj.speList.length ; i++){
                        if ($scope.checkTask.cdUnits == ""){
                            $scope.checkTask.cdUnits += obj.speList[i].leadUnitName;
                        }else {
                            $scope.checkTask.cdUnits += ","+obj.speList[i].leadUnitName;
                        }
                    }
                }else {
                    $scope.checkTask.cdUnits = "无";
                }
            }else if("1" == obj.data.taskLevel){
                $("div[name='cyUnit']").show();
                $("div[name='jcUnit']").show();
            }

            //抽样单提交状态
            if ("2" == obj.data.isSample){
                $scope.synType = "部分提交";
            }else if ("1" == obj.data.isSample){
                $scope.synType = "已提交";
                $("button[name='sumSampleBut']").attr("disabled", true);//禁止点击
            }else {
                $scope.synType = "未提交";
            }
        }).error(function(){
        });
    };
    $scope.initInfo();

    //根据任务获取监测对象
    $("#JC_XX_Grid").dataTable({
        processing : true, // 加载时提示
        serverSide : true, // 分页，是否服务器端处理
        ordering : false,
        searching : false,
        bProcessing: true,
        bAutoWidth:true,
        //sScrollY : "auto",
        dom : '<"top">rt<"bottom"pli><"clear">',
        autoWidth : false,
        fnDrawCallback: function() {
            //点击行内任意内容即选择行
            $("#grid tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[type='checkbox']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                    }
                });
            })
        },
        ajax : {
            url : "/sofn-qry-web/alesmynewchecktask/getMyTaskObjById",
            dataSrc:function(json){
                console.info(json)
                json.recordsTotal=json.data.total;//总个数
                json.recordsFiltered=json.data.total;
                json.pageNumber=json.data.pageNum;//页码
                json.pageSize=json.data.pageSize;//每页个数
                json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                json.length=json.data.pageSize;
                return json.data.list;
            },error:function (msg) {
                console.log(msg);
                
            }, headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                params.id = $scope.taskId;
            },
            type:"post"
        },
        columns : [
            {
                data : 'id',
                title : 'ID',
                visible : false
            },
            {
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "industry",
                title : "所属行业",
                render : function(data, type, row) {
                    if (data == '01'){
                        return '种植业';
                    }else if (data == '02'){
                        return "畜牧业";
                    }else{
                        '渔业'
                    }
                }
            },
            {
                data : "productType",
                title : "产品种类"
            },
            {
                data : "productName",
                title : "产品名称"
            },
            {
                data : "monitorNum",
                title : "抽样数量"
            },
            {
                data : "areaId",
                title : "受检区域",
                render : function(data, type, row) {
                    if (data != null && data != undefined){
                        return $.fn.Cityname(data);
                    }else {
                        return "未填写";
                    }
                }
            },
            {
                data : "isSample",
                title : "状态",
                render : function(data, type, row) {
                    if (data == "0"){
                        return "未提交";
                    }else if (data == "1"){
                        return "已提交";
                    }else if(data == "2"){
                        return "部分提交";
                    }
                }
            },
            {
                data : "id",
                title : "操作",
                render : function(data, type, row) {
                    return '<a style="cursor:pointer" onclick="cySubmit(\''+data+'\')">抽样单</a>';
                }
            }
        ],
        language : {
            "sProcessing" : "处理中...",
            "sLengthMenu" : "显示 _MENU_ 项结果",
            "sZeroRecords" : "没有匹配结果",
            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix" : "",
            "sSearch" : "搜索:",
            "sUrl" : "",
            "sEmptyTable" : "表中数据为空",
            "sLoadingRecords" : "载入中...",
            "sInfoThousands" : ",",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上页",
                "sNext" : "下页",
                "sLast" : "末页"
            },
            "oAria" : {
                "sSortAscending" : ": 以升序排列此列",
                "sSortDescending" : ": 以降序排列此列"
            }
        },
        sPaginationType : "full_numbers"
    });

    //抽样信息填报
    window.cySubmit = function(o){
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_taskEndTime",$scope.checkTask.taskEndTime)
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_taskInfos");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_taskInfos",$scope.checkTask.isSample);//当前任务
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr",o);//当前检测对象id
        $state.go("index.content.qry/taskManage/qryMyTaskSampleList");
    };

    /**
     * other
     * */
  /*  window.downloadFile= function (o) {
        $("div[name='File']").html('<label class="col-lg-5 col-md-5 col-sm-5 " style="text-align: right;padding-right: 10px;">附件：</label>');
        if (o != undefined && o!=null && o!=""){
            var rs;
            rs=o.split("|");
            for (var i=0;i<rs.length ;i++ )
            {
                if(rs[i] != undefined && rs[i]!=null && rs[i]!=""){
                    var fileName = rs[i].substr(rs[i].lastIndexOf('\/')+1);
                   // $("div[name='File']").append('<a href="http://'+rs[i]+'">'+fileName+'</a>');
                   $("div[name='File']").append('<span class="col-lg-5 col-md-5 col-sm-5 " style="text-align: left   ">'+fileName+'</span>');
                }
            }
        }else {
            $("div[name='File']").append('无');
        }
    };*/
    window.downFile = function() {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/qryFile/downloadFile");
        var input1 = $("<input>");
        input1.attr("type", "hidden");
        input1.attr("name", "fileUrl");
        input1.attr("value", $scope.checkTask.attachmentAddress);
        var input2 = $("<input>");
        input2.attr("type", "hidden");
        input2.attr("name", "fileName");
        input2.attr("value", $scope.checkTask.attachmentName);
        $("body").append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    };



    $scope.retrunDiskTop = function () {
        if ($scope.oprIdx =="history"){
            window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II","history");
            $state.go("index.content.qry/taskManage/qryMyCheckTask");
        }else {
            window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II","newCheck");
            $state.go("index.content.qry/taskManage/qryMyCheckTask");
        }
    }
});
