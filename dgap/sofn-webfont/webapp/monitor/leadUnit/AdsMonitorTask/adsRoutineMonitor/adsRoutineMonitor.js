var myapp=angular.module("myapp",[]).controller("adsRoutineMonitor",function($scope,$http,$state){
    $scope.iswrite = "";
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="例行监测"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";
    Date.prototype.Format = function(format){
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
            }
        }
        return format;
    }

    var token = window.localStorage.getItem("token");

    $("#newTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    });
    $("#supTask").click(function(){
        if(monitorClass == "例行监测"){
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
        }else if (monitorClass == "专项监测"){
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor")
        }
    });

    $("#bePubTask").click(function(){
        window.localStorage.setItem("monitorClass","例行监测");//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    $("#historyTask").click(function(){
        window.localStorage.setItem("monitorClass","例行监测");//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList")
    });

    $("#special").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor")
    });

    //查询按钮
    $("#seachModel").click(function(){
        $scope.clickEvent();
    });
    $scope.clickEvent = function() {
        $("#grid").dataTable().api().ajax.reload();
    };
    //绑定回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.clickEvent();
        }
    };


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
                $("#grid tbody tr").each(function(i,o){
                    $(o).click(function(){
                        var checked = $(this).find("input[type='checkbox']")[0];
                        if(checked.checked){
                            checked.checked = false;
                            $(this).removeClass("selected");
                        }else {
                            checked.checked = true;
                            $(this).addClass("selected");
                        }
                    });
                })
            },*/
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsRoutineMonitor/list",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        //时间格式化
                        if(json.data.list[i].RMDATEBEGIN!=null&&json.data.list[i].RMDATEBEGIN!=""&&json.data.list[i].RMDATEBEGIN!=undefined){
                            json.data.list[i].RMDATEBEGIN=new Date(parseInt(json.data.list[i].RMDATEBEGIN)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].RMDATEEND!=null&&json.data.list[i].RMDATEEND!=""&&json.data.list[i].RMDATEEND!=undefined){
                            json.data.list[i].RMDATEEND=new Date(parseInt(json.data.list[i].RMDATEEND)+28800000).toISOString().slice(0,10);
                        }
                        //任务状态
                        if(json.data.list[i].RMSTATE!=null&&json.data.list[i].RMSTATE!=""&&json.data.list[i].RMSTATE!=undefined){
                            if(json.data.list[i].RMSTATE=='0'){
                                json.data.list[i].RMSTATE='未发布';
                            }
                            if(json.data.list[i].RMSTATE=='1'){
                                json.data.list[i].RMSTATE='已发布';
                            }
                            if(json.data.list[i].RMSTATE=='2'){
                                json.data.list[i].RMSTATE='已废止';
                            }
                        }
                    }
                    return json.data.list;
                },
                data : function(params) {
                    params.token = window.localStorage.getItem("token");
                    params.rmName=$("input[name='rmName']").val();
                    params.rmState="1"; //查询已下发的任务
                    params.dateBegin=$("input[name='datebegin']").val();
                    params.dateEnd=$("input[name='dateend']").val();
                },
                type:"post"
            },
            columns : [
               /* {
                    data : "ck",
                    title : '<input id="checkAll"  type="checkbox"/>',
                    render : function(data, type, row) {
                        return '<input type="checkbox" name="ck" onclick="checkThisBox(this)"/>';
                    },
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
                    }
                },
                {
                    data : "RMNAME",
                    title : "任务名称",
                    width:"14.5%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='RMNAME"+row.ID+"' onmouseover=showPopContent('RMNAME"+row.ID+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }
                },
                {
                    data : "RMTYPE",
                    title : "监测类型"
                },
                {
                    data : "RMYEAR",
                    width:"5%",
                    title : "年度"
                },
                {
                    data : "RMBATCH",
                    width:"3%",
                    title : "批次"
                },
                {
                    data : "RMFILENUM",
                    title : "文件号"
                },
                {
                    data : "RMFILE",
                    title : "附件",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var file = "";
                        if(data!=null && data!=''){
                            var arr = data.split("//");
                            file = '<a class="color " style="cursor: pointer" class="Check-report" onclick="downloadFile(\''+row.RMFILE+"','"+row.RMFILENAME+'\')">下载</a>';
                        }else{
                            file = '无';
                        }
                        return file;
                    }
                },
                {
                    data : "RMDATEBEGIN",
                    title : "开始时间"
                },
                {
                    data : "RMDATEEND",
                    title : "结束时间"
                },
                {
                     data : "RMPRIORITY",
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

    function compareDate(DateOne, DateTwo) {
        var OneMonth = DateOne.substring(5, DateOne.lastIndexOf("-"));
        var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf("-") + 1);
        var OneYear = DateOne.substring(0, DateOne.indexOf("-"));
        var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf("-"));
        var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf("-") + 1);
        var TwoYear = DateTwo.substring(0, DateTwo.indexOf("-"));
        if (Date.parse(OneMonth + "/" + OneDay + "/" + OneYear) >= Date.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 执行任务
     */
    window.runTask =function (id) {
        var task = hashMap.Get(id);
        var levelEnum = task.LEVELENUM;
        var createOrgRegionId = task.CREATEORGREGIONID;
        var endDate = task.RMDATEEND;
        var nowDates = new  Date();
        var nowDate = nowDates.Format("yyyy-MM-dd");
        var priority = task.RMPRIORITY;
        var flag = compareDate(endDate,nowDate);
        if(flag==false){
            jBox.tip("该任务已过期，不可继续执行", 'info');
            return;
        }

        var submit = function (v, h, f) {
            if (v == true) {
                var url = "/sofn-ads-web/adsMonitorTask/findState?time="+new Date();
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
                            data: {
                                "taskName": task.RMNAME,
                                "monitorClass": task.RMTYPE,
                                "releaseUnit": task.RMRELEASEUNIT,
                                "year": task.RMYEAR,
                                "batch": task.RMBATCH,
                                "separation": 0,
                                "startTime": task.RMDATEBEGIN,
                                "endTime": task.RMDATEEND,
                                "taskSource": "SDPTASK",
                                "supId": task.ID,
                                "attachment": task.RMFILENAME,
                                "attachmentcode": task.RMFILENUM,
                                "comment": task.RMREMARK,
                                "publishStatus": 0,
                                "attachmentAddress": task.RMFILE,
                                "levelEnum": levelEnum,
                                "createOrgRegionId": createOrgRegionId,
                                "priority" : priority,
                                "token": token
                            },
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

    //导航展开
    $("#collapseFive").attr("class","panel-collapse collapse in");
    $("#collapseFive li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");


    //表格刷新
    $scope.TableReload = function(){
        $('#grid').dataTable().api().ajax.reload();
    };

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

    //提示条初始化
    //$("[data-toggle='tooltip']").tooltip();
});
