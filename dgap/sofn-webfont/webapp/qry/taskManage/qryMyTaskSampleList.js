var myapp=angular.module("myapp",[]).controller("qryMyTaskSampleList",function($scope,$http,$state){
    /**
     * *
     **监测信息对象涵盖抽样单列表，一个监测信息对应多条抽样单信息
     * *
     * */

    /**
     * 公共值
     * */
    $scope.taskState = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_taskInfos");//当前任务状态
    $scope.jcxxId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");//检测信息id
    $scope.taskId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr");//任务id
    $scope.oprIdx = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");//任务操作标示

    $(function() {
        $("#grid").dataTable({
            fnDrawCallback: function() {
            },
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-qry-web/alesmynewchecktask/getTaskSampleListByJcInfo",
                dataSrc:function(json){
                    console.info(json);
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    //已
                    return json.data.list;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data : function(params) {
                    params.organTaskId = $scope.jcxxId;
                },
                type:"post"
            },
            columns : [
                {
                    data : 'ID',
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
                    data : "SAMPLENAME",
                    title : "样品名称"
                },
                {
                    data : "SAMPLECODE",
                    title : "样品编码"
                },
                {
                    data : "TRADEMARK",
                    title : "商标"
                },
                {
                    data : "GRADE",
                    title : "等级"
                },
                {
                    data : "SAMPLEBASE",
                    title : "抽样基数"
                },
                {
                    data : "SAMPLEPLACE",
                    title : "抽样场所"
                },
                {
                    data : "ISSYNC",
                    title : "状态",
                    render : function(data, type, row) {
                        if("1" == data){
                            return "已提交";
                        }else if("0" == data){
                            return "未提交";
                        }
                    }
                },
                {
                    data : "ID",
                    title : "报告",
                    render : function(data, type, row) {
                        return '<a style="cursor:pointer" onclick="queryInfo(\''+data+'\')">查看</a>';
                    }
                }
            ],
        });
    });

    /**
     * 操作
     * */
    //查看
    window.queryInfo = function(o){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo","query");//查看动作
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr",o);//抽样记录
        $state.go("index.content.qry/taskManage/qryWriteSample");
    };

    $scope.cleanCookie=function(){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr");
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");
    };

    //表格刷新
    $scope.TableReload = function(){
        $('#grid').dataTable().api().ajax.reload();
    };
    $scope.retrunDiskTop = function () {
        $state.go("index.content.qry/taskManage/qryMyTaskInformation");
    }
});
