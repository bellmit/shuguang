
var myapp=angular.module("myapp",[]);
myapp.controller("specialMonitorView",function($scope,$http,$state){

    var token =  window.localStorage.getItem("token");

    $("#special").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/specialMonitorView")
    });
    $("#routine").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/routineMonitorView")
    });

    /**
     * 收起部分条件选择框
     */
    $scope.hideInput = function () {
        $(".more").addClass("hide");
        $(".sh").removeClass("hide");
        $("#show").removeClass("hide");
    }
    $scope.hideInput();
    /**
     * 显示更多选择条件
     */
    $scope.showInput = function () {
        $(".more").removeClass("hide");
        $(".sh").addClass("hide");
        $("#show").addClass("hide");
    }

    //刷新表格
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
    //查询
    $(".seachTask").click(function () {
        $scope.clickEvent();
    });

    //重置
    $scope.resetInput = function () {
        $(".clearfix>.gui-input").val("");
        $(".gui-select").val("");
        $("#grid").dataTable().api().ajax.reload();
        jBox.tip("重置成功！","success");
    }

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("adsCheckInfoId");

    $scope.year = [];

    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    $scope.result=["合格","不合格"];
    /**
     * 初始化省市县控件
     */
    $.fn.mycity("sheng","shi","xian",0);
    $.fn.mycity("sheng1","shi1","xian1",0);



    $(function (){
        //年份
        /*$http({
            url:"/sofn-ads-web/adsCheckInfo/getYear",
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.year=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });*/
        var monitorClass="专项监测";
        //任务名
        $.ajax({
            url:"/sofn-ads-web/adsCheckInfo/getTaskName",
            type:"post",
            dataType:"json",
            data:{"token":token,"monitorClass" : monitorClass},
            success:function(datax){
                if(datax.httpCode == 200){
                    var datasx = datax.data;
                    $scope.taskName=datasx;
                }else{
                    jBox.tip("数据有误", 'info');
                }
            }
        });
        //样品名称
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getSampleName",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.sampleName=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //检测环节
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getCheckLink",
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.checkLink=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //受检单位
        /*$http({
            url:"/sofn-ads-web/adsCheckInfo/getTestedDeparment",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.testedDeparment=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });*/


    });



    //时间转换
    function timeStamp2String (time){
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1;
        var date = datetime.getDate();
        return year + "-" + month + "-" + date;
    };


    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsCheckInfo/getPageInfoWithParam",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;

                    //增加显示任务状态
                    if($('#taskName').val() != '' && json.data && json.data.list && json.data.list.length > 0){
                        var STATUS = new Array('未发布','已发布','执行中','已终止','已结束,未完成','已废止');

                        $('#taskStatusStatus').show();
                        $('#taskStatusValue').text(STATUS[json.data.list[0].publishStatus]);
                    } else
                        $('#taskStatusStatus').hide();

                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    params.monitorClass="专项监测";
                    params.year = $('#year option:selected').val();//年份
                    params.taskName = $('#taskName option:selected').val();//任务名称
                    params.result = $('#result option:selected').val();//结果
                    //三个单位改为文本框模糊查询
                    params.sampleDeparment = $('#sampleDeparment').val();//抽样单位
                    params.checkOrgan = $('#checkOrgan').val();//监测单位
                    params.testedDeparment = $('#testedDeparment').val();//受检单位
                    params.productTraceabilityCode = $("input[name='productCode']").val();    //产品编码
                    params.sampleCode = $("input[name='sampleCode']").val();    //样品编码
                    params.sampleName = $("#sampleName").val();    //样品名称
                    params.checkLink = $("#checkLink option:selected").val();    //检测环节
                    params.startTime = $("input[name='startTime']").val();//开始时间
                    params.endTime = $("input[name='endTime']").val();    //结束时间
                    params.token = token;

                    if($('#sheng option:selected').val()!=null){
                        if($('#shi option:selected').val()!=null){
                            if($('#xian option:selected').val()!=null){
                                params.producingArea=$('#xian option:selected').val();
                            }else{
                                params.producingArea=$('#shi option:selected').val();
                            }
                        }else{
                            params.producingArea=$('#sheng option:selected').val();
                        }
                    }
                    if($('#sheng1 option:selected').val()!=null){
                        if($('#shi1 option:selected').val()!=null){
                            if($('#xian1 option:selected').val()!=null){
                                params.cityCode=$('#xian1 option:selected').val();
                            }else{
                                params.cityCode=$('#shi1 option:selected').val();
                            }
                        }else{
                            params.cityCode=$('#sheng1 option:selected').val();
                        }
                    }

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
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "productTraceabilityCode",
                    title: "产品编码",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if (data!=null){
                            return '<input style="overflow: auto; overflow-y:hidden;" class="gui-input" readonly="readonly" value="'+data+'" type="text"/>';
                        }else {
                            data = "";
                            return '<input style="overflow: auto; overflow-y:hidden;" class="gui-input" readonly="readonly" value="'+data+'" type="text"/>';
                        }
                    }

                },
                {
                    data: "sampleCode",
                    title: "样品编码",
                    render : function(data, type, row) {
                        row.monitorClass="专项监测";
                        var look_a = '<a class="pointer" title="查看详情" id="sampleCode "  onclick="lookOrganRegion(\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\',\''+row.sampleName+'\',\''+row.monitorClass+'\') ">'+row.sampleCode+'</a>';
                        return look_a;
                    }
                },
                {
                    data: "sampleName",
                    title: "样品名称"
                },
                {
                    data: "testedDeparment",
                    title: "受检单位"
                },
                {
                    data: "sampleDeparment",
                    title: "抽样单位"
                },
                {
                    data: "sampleDate",
                    title: "抽样时间"
                },
                {
                    data: "checkOrgan",
                    title: "检测单位"
                },
                {
                    data: "checkTime",
                    title: "检测时间",
                    render : function(data, type, row) {
                        var time = row.checkTime; //or time=1439018115000; 结果一样
                        var strdate2 = timeStamp2String(time);
                        return strdate2;
                    }
                },
                {
                    data: "result",
                    title: "判定结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else if("0"==data){
                            return "不合格";
                        }else if("-1"==data){
                            return "暂未检测";
                        }else{
                            return "-";
                        }
                    }
                },
                {
                    data : "id",
                    title : "生成检测报告",
                    render : function(data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="allInfo(\''+data+'\')">生成报告</a>';
                        return butt;
                    },
                    width:"6%"
                }
                /*{
                 data : "ID",
                 title : "状态",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var look_a = '待发布';
                 return look_a;
                 }
                 },*/
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

    window.allInfo=function(obj){

        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-ads-web/adsCheckInfo/getAllByCheckInfoId",
                    method:"post",
                    dataType:"json",
                    params : {"checkInfoId":obj},
                    responseType: 'arraybuffer',
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if(blob.size > 0){
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', "检测报告.doc");
                        a.click();
                    }else{
                        jBox.tip("导出失败，没有数据！","error")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

    //监测单位筛选

    var selectCheckOrgan = document.getElementById('checkOrgan');

    selectCheckOrgan.onchange = function(){
        var checkOrgan = $('#checkOrgan option:selected').text();
        if("全部"!=checkOrgan){
            if(document.getElementById('hli')){
                var obj = document.getElementById("hli");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="hli" style="display: block;float: left;position: relative;">'+'检测单位：'+checkOrgan+'<button id="checkOrgan" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength =  $(".setslist ul li").length;
            if(lilength == 0){
                $(".setslist ul").prepend(lihtml);
            }else{
                $(".setslist ul li:last").after(lihtml);
            }
        }else{
            if(document.getElementById('hli')){
                var obj = document.getElementById("hli");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function(){
        var $this=$(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });
    //样品名称
    var selectSampleName = document.getElementById('sampleName');

    selectSampleName.onchange = function(){
        var sampleName = $('#sampleName option:selected').text();
        if("全部"!=sampleName){
            if(document.getElementById('sam')){
                var obj = document.getElementById("sam");
                obj.parentNode.removeChild(obj)
            }
            var lihtml = '<li id="sam" style="display: block;float: left;position: relative;">'+'样品名称：'+sampleName+'<button id="sampleName" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
            var lilength =  $(".setslist ul li").length;
            if(lilength == 0){
                $(".setslist ul").prepend(lihtml);
            }else{
                $(".setslist ul li:last").after(lihtml);
            }
        }else{
            if(document.getElementById('sam')){
                var obj = document.getElementById("sam");
                obj.parentNode.removeChild(obj)
            }
        }
    }
    $(".setslist").on("click", "button", function(){
        var $this=$(this);
        $this.parents("li").remove();
        var aa = $(this).attr('id');
        var a = document.getElementById(aa);
        a.options[0].selected = true;
    });

    var temp=false;
    //全选
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选;
            temp = true;
            $("input[name='task']").attr("checked",true);
        }else{
            $("input[name='task']").attr("checked",false);
        }
    };


    $scope.exportExcel = function (type) {
        var monitorClass="专项监测";
        var producingArea="";
        var cityCode="";
        // if(temp==false){
            var check = $("input[name='task']:checked");
            if(check.length > 0) {
                var arr = new Array();
                $(check).each(function () {
                    var task = $(this)[0];
                    var id = $(task).val();
                    arr.push(id);
                });
                var submit = function (v, h, f) {
                    if (v == true)
                        $http({
                            url: "/sofn-ads-web/adsCheckInfo/getCheckListByCheck",
                            method: "post",
                            dataType: "json",
                            params: {
                                "monitorClass":monitorClass,
                                "ids": arr.toString(),
                                "token": token
                            },
                            responseType: 'arraybuffer',
                            headers: {"Content-Type": "application/x-www-form-urlencoded"},
                        }).success(function (data) {
                            var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                            if (blob.size > 0) {
                                var objectUrl = URL.createObjectURL(blob);
                                var a = document.createElement('a');
                                document.body.appendChild(a);
                                a.setAttribute('style', 'display:none');
                                a.setAttribute('href', objectUrl);
                                a.setAttribute('download', '专项监测汇总.xls');
                                a.click();
                            } else {
                                jBox.tip("导出失败，没有数据！", "info")
                            }
                        });
                    else
                        jBox.tip("取消成功", 'success');
                    return true;
                };
                // 自定义按钮
                $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
            }else{
                jBox.tip("请选择你需要导出的记录", 'info');
            }
        // }else {
        //     if($('#sheng option:selected').val()!=null){
        //         if($('#shi option:selected').val()!=null){
        //             if($('#xian option:selected').val()!=null){
        //                 producingArea=$('#xian option:selected').val();
        //             }else{
        //                 producingArea=$('#shi option:selected').val();
        //             }
        //         }else{
        //             producingArea=$('#sheng option:selected').val();
        //         }
        //     }
        //     if($('#sheng1 option:selected').val()!=null){
        //         if($('#shi1 option:selected').val()!=null){
        //             if($('#xian1 option:selected').val()!=null){
        //                 cityCode=$('#xian1 option:selected').val();
        //             }else{
        //                 cityCode=$('#shi1 option:selected').val();
        //             }
        //         }else{
        //             cityCode=$('#sheng1 option:selected').val();
        //         }
        //     }
        //     if(type=="s"){
        //         var submit = function (v, h, f) {
        //             if (v == true)
        //                 $http({
        //                     url:"/sofn-ads-web/adsCheckInfo/getCheckList",
        //                     method:"post",
        //                     dataType:"json",
        //                     params:{
        //                         "monitorClass":monitorClass,
        //                         "year" : $('#year option:selected').val(),//年份
        //                         "taskName" : $('#taskName option:selected').val(),//任务名称
        //                         "result":$('#result option:selected').val(),//结果
        //                         //三个单位改为文本框模糊查询
        //                         "sampleDeparment":$('#sampleDeparment').val(),//抽样单位
        //                         "checkOrgan":$('#checkOrgan').val(),//监测单位
        //                         "testedDeparment":$('#testedDeparment').val(),//受检单位
        //                         "productTraceabilityCode": $("input[name='productCode']").val(),    //产品编码
        //                         "sampleCode": $("input[name='sampleCode']").val(),    //样品编码
        //                         "sampleName": $("#sampleName").val(),   //样品名称
        //                         "checkLink": $("#checkLink option:selected").val(),    //检测环节
        //                         "startTime":$("input[name='startTime']").val(),//检测时间
        //                         "endTime":$("input[name='endTime']").val(),
        //                         "token":token,
        //                         "producingArea":producingArea,
        //                         "cityCode":cityCode,
        //                     },
        //                     responseType: 'arraybuffer',
        //                     headers: { "Content-Type": "application/x-www-form-urlencoded" },
        //                 }).success(function(data){
        //
        //                     var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
        //                     if(blob.size > 0){
        //                         var objectUrl = URL.createObjectURL(blob);
        //                         var a = document.createElement('a');
        //                         document.body.appendChild(a);
        //                         a.setAttribute('style', 'display:none');
        //                         a.setAttribute('href', objectUrl);
        //                         a.setAttribute('download', '专项监测汇总.xls');
        //                         a.click();
        //                     }else{
        //                         jBox.tip("导出失败，没有数据！","info")
        //                     }
        //                 });
        //             else
        //                 jBox.tip("取消成功", 'success');
        //             return true;
        //         };
        //         // 自定义按钮
        //         $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        //     }else if(type=="a"){
        //
        //         var submit = function (v, h, f) {
        //             if (v == true)
        //                 $http({
        //                     url:"/sofn-ads-web/adsCheckInfo/getCheckListAll",
        //                     method:"post",
        //                     dataType:"json",
        //                     params:{"monitorClass":monitorClass},
        //                     responseType: 'arraybuffer',
        //                     headers: { "Content-Type": "application/x-www-form-urlencoded" },
        //                 }).success(function(data){
        //                     var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
        //                     if(blob.size > 0){
        //                         var objectUrl = URL.createObjectURL(blob);
        //                         var a = document.createElement('a');
        //                         document.body.appendChild(a);
        //                         a.setAttribute('style', 'display:none');
        //                         a.setAttribute('href', objectUrl);
        //                         a.setAttribute('download', '详细信息.xls');
        //                         a.click();
        //                     }else{
        //                         jBox.tip("导出失败，没有数据！","info")
        //                     }
        //                 });
        //             else
        //                 jBox.tip("取消成功", 'success');
        //             return true;
        //         };
        //         // 自定义按钮
        //         $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        //     }
        // }

    };

//清除筛选
    $(".cq2").on("click",  function(){
        $('.setslist ul li').remove();
        document.getElementById('sampleName').options[0].selected = true;
        document.getElementById('checkOrgan').options[0].selected = true;
        $("#grid").dataTable().api().ajax.reload();

    });
    /**
     * 跳转到机构与地域页面
     */
    /*alert(sampleCode);*/
    window.lookOrganRegion=function(data,data1,data2,data3){
        window.localStorage.setItem("ads_sample_code",data);
        window.localStorage.setItem("ads_product_traceability_code",data1);
        window.localStorage.setItem("ads_sample_name",data2);
        window.localStorage.setItem("ads_monitor_class",data3);

        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/sampleInfo");
    };

})






