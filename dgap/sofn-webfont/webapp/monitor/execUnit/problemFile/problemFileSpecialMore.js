/**
 * Created by tianc on 2016/12/02.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("problemFileSpecialMore",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");
    var monitorKind = window.localStorage.getItem("monitorKind");

    $("#routine").click(function(){
        window.localStorage.setItem("token",token);//token
        window.localStorage.setItem("monitorKind",monitorKind);//行业
        $state.go("index.content.monitor/execUnit/problemFile/problemFileRoutineMore")
    });

    $("#audit").click(function(){
        window.localStorage.setItem("token",token);//token
        window.localStorage.setItem("monitorKind",monitorKind);//行业
        $state.go("index.content.monitor/execUnit/problemFile/problemFileAuditMore")
    });

    $("#detection").click(function(){
        window.localStorage.setItem("token",token);//token
        window.localStorage.setItem("monitorKind",monitorKind);//行业
        $state.go("index.content.monitor/execUnit/problemFile/probleFileDetectionMore")
    });

    $("#others").click(function(){
        window.localStorage.setItem("token",token);//token
        window.localStorage.setItem("monitorKind",monitorKind);//行业
        $state.go("index.content.monitor/execUnit/problemFile/problemFileOthersMore")
    });
    //行业,监测类别

    var monitorType = "专项监测";
    //年份
    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    $scope.selectedYear = myDate.getFullYear();
    //任务名称
    $scope.monitorTask = [];
    window.findMonitorTask = function () {
        var adsMonitorTask ={};
        adsMonitorTask.monitorClass = monitorType;
        adsMonitorTask.industry = monitorKind;
        adsMonitorTask.year = $("#year option:selected").val();
        //获取任务ID,任务名称,年度
        $http({
            url:"/sofn-ads-web/adsMonitorTask/getNameByParam",
            method:"post",
            params:{token:token},
            data:adsMonitorTask,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.monitorTask=datasx;
                $scope.selectedMonitorTask = $scope.monitorTask[0].taskName;
            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
    };
    //刷新任务名称
    $(document).ready(function(){
        $('#year').change(function(){
            findMonitorTask();
        });
    });

    //取文件后缀名
    window.GetFileExt = function(filepath) {
        if (filepath != "") {
            var pos = "." + filepath.replace(/.+\./, "");
            return pos;
        }
    }

    //字符串逆转
    window.strTurn = function(str) {
        if (str != "") {
            var str1 = "";
            for (var i = str.length - 1; i >= 0; i--) {
                str1 += str.charAt(i);
            }
            return (str1);
        }
    };

    //取文件全名名称
    window.GetFileName = function(filepath) {
        if (filepath != "") {
            var names = filepath.split("\\");
            return names[names.length - 1];
        }
    };

    //取文件不带后缀名
    window.GetFileNameNoExt = function (fileName) {
        var pos = strTurn(GetFileExt(fileName));
        var file = strTurn(fileName);
        var pos1 =strTurn(file.replace(pos,""));
        return GetFileName(pos1);
    };

    $(function () {
        findMonitorTask();
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsFile/getPageInfoProblemFile",
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
                    params.field = monitorKind;
                    params.monitorType = monitorType;
                    params.years = $("#year option:selected").val();
                    params.monitorTask = $("#monitorTask option:selected").val();
                    params.folder = "YES";
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },{
                    data: "fileName",
                    title: "图片名称",
                    render : function(data, type, row) {
                        return GetFileNameNoExt(data);
                    }
                },
                {
                    data: "monitorTask",
                    title: "任务名称",
                    width:"14.5%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='monitorTask"+row.id+"' onmouseover=showPopContent('monitorTask"+row.id+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }
                },
                {
                    data: "years",
                    title: "年份"
                },
                {
                    data: "createTime",
                    title: "上传日期",
                    render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
                },
                {
                    data: "fileAddress",
                    title: "图片",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a class="pointer mr15" onclick="viewFile(\''+row.fileAddress+'\') ">预览</a>';
                        //var look_b = '<a href="http://'+row.fileAddress+'" class="pointer" download="" target="_blank"">下载</a>';
                        var look_b = '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\''+row.fileAddress+"','"+row.fileName+'\')">下载</a>';
                        return look_a + look_b;
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
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });

    window.downloadFile=function(pdfAddress,filename){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', pdfAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', GetFileNameNoExt(filename));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
        $("#"+id).popover({
            animation:true,
            html:false,
            placement:'right',
            trigger:'hover',
            content:content,
            delay:0,
            container:'body'
        });
        $("#"+id).popover("show");
    }

    //查询更新
    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    window.viewFile = function (fileAddress) {
        $("#viewModal").modal("show");
        $('#viewImg').attr('src',"/sofn-sys-web/sysTemplate/downPic?fileUrl=http:" + fileAddress);
    }

    //返回上一级页面
    $("#back").click(function () {
        window.localStorage.setItem("token",token);//token
        $state.go("index.content.monitor/execUnit/problemFile/problemFileSpecial");
    });
});