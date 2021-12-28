var myapp=angular.module("myapp",[]).controller("adsSpecialMonitor",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="专项监测"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    $("#newTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    });

    $("#bePubTask").click(function(){
        window.localStorage.setItem("monitorClass","专项监测");//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    $("#historyTask").click(function(){
        window.localStorage.setItem("monitorClass","专项监测");//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList")
    });

    $("#routine").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
    });

    /**
     * 任务列表
     * */
    //查询按钮
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });

    //自定义hashmap
    var hashMap = {
        Set : function(key,value){this[key] = value},
        Get : function(key){return this[key]},
        Contains : function(key){return this.Get(key) == null?false:true},
        Remove : function(key){delete this[key]}
    }

    $(function() {
        $("#grid").dataTable({
            /*fnDrawCallback: function() {
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
            },*/
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsRoutineMonitor/getSpecialList",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        //时间格式化
                        if(json.data.list[i].SMDATEBEGIN!=null&&json.data.list[i].SMDATEBEGIN!=""&&json.data.list[i].SMDATEBEGIN!=undefined){
                            json.data.list[i].SMDATEBEGIN=new Date(parseInt(json.data.list[i].SMDATEBEGIN)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].SMDATEEND!=null&&json.data.list[i].SMDATEEND!=""&&json.data.list[i].SMDATEEND!=undefined){
                            json.data.list[i].SMDATEEND=new Date(parseInt(json.data.list[i].SMDATEEND)+28800000).toISOString().slice(0,10);
                        }
                        //任务状态
                        if(json.data.list[i].SMSTATE!=null&&json.data.list[i].SMSTATE!=""&&json.data.list[i].SMSTATE!=undefined){
                            if(json.data.list[i].SMSTATE=='0'){
                                json.data.list[i].SMSTATE='未发布';
                            }
                            if(json.data.list[i].SMSTATE=='1'){
                                json.data.list[i].SMSTATE='已发布';
                            }
                            if(json.data.list[i].SMSTATE=='2'){
                                json.data.list[i].SMSTATE='已废止';
                            }
                        }
                    }
                    return json.data.list;
                },
                data : function(params) {
                    params.token = window.localStorage.getItem("token");
                    params.smName=$("input[name='smName']").val();
                    params.smState="1"; //查询已发布任务
                    params.dateBegin=$("input[name='datebegin']").val();
                    params.dateEnd=$("input[name='dateend']").val();
                    params.smYear=$("input[name='queryYear']").val();
                },
                type:"post"
            },
            columns : [
                /*{
                    data : "ck",
                    title : '<input id="checkAll"  type="checkbox"/>',
                    render : function(data, type, row) {
                        return '<input type="checkbox" name="ck" onclick="checkThisBox(this)"/>';
                    },
                    width : "3%"
                },*/
                {
                    data : 'ID',
                    title : 'ID',
                    visible : false
                },
                {
                    title : "序号",
                    width:"3%",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },

                },
                {
                    data : "SMNAME",
                    title : "任务名称",
                    width:"14.5%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='SMNAME"+row.ID+"' onmouseover=showPopContent('SMNAME"+row.ID+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }

                },
                {
                    data : "SMTYPE",
                    title : "监测类型",

                },
                {
                    data : "SMYEAR",
                    width:"5%",
                    title : "年度",

                },
                {
                    data : "SMFILENUM",
                    title : "文件号",
                },
                {
                    data : "SMFILE",
                    title : "附件",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var file = "";
                        if(data!=null && data!=''){
                            var arr = data.split("//");
                            file = '<a class="color " style="cursor: pointer" class="Check-report" onclick="downloadFile(\''+row.SMFILE+"','"+row.SMFILENAME+'\')">下载</a>';
                        }else{
                            file = '无';
                        }
                        return file;
                    }
                },
                {
                    data : "SMDATEBEGIN",
                    title : "开始时间",

                },
                {
                    data : "SMDATEEND",
                    title : "结束时间",

                },
                // {
                //     data : "SMSTATE",
                //     title : "任务状态",
                //
                // },
                {
                    data : "SMPRIORITY",
                    title : "优先级",
                    render : function (data, type, row) {
                        // 数据操作选择项
                        return getPriorityName(data);
                    }
                },
                {
                    data : "ID",
                    title : "任务配置",
                    render : function(data, type, row) {
                        hashMap.Set(data,row);
                        var datax = data;
                        var token = window.localStorage.getItem("token");
                        var url = "/sofn-ads-web/adsMonitorTask/findSupId?time="+new Date();
                        // $.ajax({
                        //     url: url,
                        //     type: "post",
                        //     dataType: "json",
                        //     data: {"id":data,"token":token},
                        //     success: function (data) {
                        //         var zhixing = data.data;
                        //         if(1 == zhixing){
                        //             var node = document.getElementById('zhixing'+data);
                        //             console.log(node);
                        //             // node.removeAttribute(onclick);
                        //         }
                        //     },
                        //     fail: function (data) {
                        //     }
                        // });

                        $http({
                            url: url,
                            method: "post",
                            params: {"id":data,"token":token}
                        }).success(function (data) {
                            var zhixing = data.data;
                            if(1 == zhixing){
                                var node = document.getElementById('zhixing'+datax);
                                node.style.color='darkgray';
                                document.getElementById('zhixing'+datax).onclick = null;
                            }
                        }).error(function () {
                        });

                        var view = '<a id="zhixing'+data+'" title="点击执行任务" class="pointer" onclick="runTask(\''+data+'\')">执行任务</a>';
                        if($scope.disabled){
                            return '<a id="zhixing" title="点击执行任务" class="pointer" style="color: grey">执行任务</a>';
                        }
                        return view;

                    }
                }
            ],
        });



    });

    //获取发布状态名称
    function getPriorityName(type) {
        var str = "";
/*        switch (type){
            case 1:
                str = '<span style="line-height:2;" class="label label-danger">特急</span>';
                break;
            case 2:
                str = '<span style="line-height:2;" class="label label-warning">紧急</span>';
                break;
            case 3:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
            default:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
        }*/
        if (type =="1"){
            str = '<span style="line-height:2;" class="label label-danger">特急</span>';
        }else if (type == "2"){
            str = '<span style="line-height:2;" class="label label-warning">紧急</span>';
        }else if (type == "3"){
            str = '<span style="line-height:2;" class="label label-info">一般</span>';
        }
        return str;
    }
    /**
     * 执行任务
     */
    window.runTask =function (id) {
        var task = hashMap.Get(id);
        var levelEnum = task.LEVELENUM;
        var createOrgRegionId = task.CREATEORGREGIONID;
        var priority = task.SMPRIORITY;
        var submit = function (v, h, f) {
            if (v == true) {
                var url = "/sofn-ads-web/adsMonitorTask/findSpecialState?time="+new Date();
                $http({
                    url: url,
                    method: "post",
                    params: {"id":id,"token":token}
                }).success(function (data) {
                    var feizhi = data.data;
                    if(1 == feizhi){
                        jBox.tip("该任务已废止，不可继续执行", 'info');
                        return true;
                    }else{
                        $.ajax({
                            url: "/sofn-ads-web/adsMonitorTask/addAdsMonitorTaskSup",
                            type: "post",
                            dataType: "json",
                            data: {"taskName":task.SMNAME,"monitorClass":task.SMTYPE,"releaseUnit":task.SMRELEASEUNIT,"year":task.SMYEAR,
                                "batch":task.SMBATCH,"separation":0,"startTime":task.SMDATEBEGIN,"endTime":task.SMDATEEND,"taskSource":"SDPTASK","supId":task.ID,
                                "attachment":task.SMFILENAME,"attachmentcode":task.SMFILENUM,"comment":task.SMREMARK,"publishStatus":0,"attachmentAddress":task.SMFILE,
                                "levelEnum":levelEnum,"createOrgRegionId":createOrgRegionId,"priority":priority,
                                "token":token},
                            success: function (data) {
                                if (data.httpCode == 500) {
                                    jBox.tip("保存失败", 'info');
                                } else if (data.httpCode == 400) {
                                    jBox.tip("没有登录", 'info');
                                } else if (data.httpCode == 409) {
                                    jBox.tip("该任务正在执行中，不可重复执行", 'info');
                                } else if (data.httpCode == 406) {
                                    jBox.tip("该任务已过期，不能再执行任务", 'info');
                                } else if (data.httpCode == 200) {
                                    window.localStorage.setItem("adsMonitorTaskId", data.adsMonitorTask.id);
                                    window.localStorage.setItem("monitorClass", data.adsMonitorTask.monitorClass);

                                    $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
                                    jBox.tip("任务执行成功，请继续配置任务信息。", 'success');
                                }
                            }
                        });
                    }
                }).error(function () {
                });
            } else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认要执行任务吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }


    //表格刷新
    $scope.TableReload = function(){
        $('#grid').dataTable().api().ajax.reload();
    }

    //提示条初始化
    //$("[data-toggle='tooltip']").tooltip();

    //字符串逆转
    function strturn(str) {
        if (str != "") {
            var str1 = "";
            for (var i = str.length - 1; i >= 0; i--) {
                str1 += str.charAt(i);
            }
            return (str1);
        }
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

    //取文件后缀名
    function GetFileExt(filepath) {
        if (filepath != "") {
            var pos = "." + filepath.replace(/.+\./, "");
            return pos;
        }
    }
    function GetFileNameNoExt(filepath) {
        var pos = strturn(GetFileExt(filepath));
        var file = strturn(filepath);
        var pos1 =strturn( file.replace(pos, ""));
        var pos2 = GetFileName(pos1);
        return pos2;
    }
    //取文件全名名称
    function GetFileName(filepath) {
        if (filepath != "") {
            var names = filepath.split("\\");
            return names[names.length - 1];
        }
    }

    window.check=function(e) {
        //var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        var re = /^[\u4E00-\u9FA5A-Za-z0-9_]{1,40}$/;
        if(e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入40个字符");
                e.value = "";
                e.focus();
            }
        }
    }

    window.downloadFile=function(pdfAddress,filename){
        var file = GetFileNameNoExt(filename);
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
        input2.attr('value', file);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
});
