
var myapp=angular.module("myapp",[]);
myapp.controller("delegateList",function($scope,$http,$state){



    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });


    /**
     * 查询当前用户信息
     */
    var token = window.localStorage.getItem("token");
   /* $scope.orgName = window.localStorage.getItem("orgName");
*/
     $http({
     url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
     method: "post",
     dataType: "json",
     data: token,
     headers: {"Content-Type": "application/x-www-form-urlencoded"},
     }).success(function (data) {
     $scope.user = data.user;
     if(data.user.orgName!=null){
     var  OrganName = $scope.user.orgName;
     $scope.orgName=OrganName;
     }

     /*  createBy=data.user.id;*/
     });

    /*   $scope.year = ["2015", "2016", "2017", "2018"];*/
    $scope.result=["合格","不合格"];
    /**
     * 初始化省市县控件
     */
    $.fn.mycity("sheng","shi","xian",0,0,0);
    $.fn.mycity("sheng1","shi1","xian1",0,0,0);
    $scope.year = [];

    var myDate = new Date();
    var startDate = myDate.getFullYear()-50;//起始年份
    var endDate = myDate.getFullYear()+50;//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    /* $scope.taskName = ["第一次", "第二次", "第三次", "第四次"];*/
    /*$scope.year=[];
     $scope.taskName=[];*/
    /*$scope.sites = [
     {site : "2015", url : "第一次"},
     {site : "2016", url : "第二次"},
     {site : "2017", url : "第三次"}
     ];*/



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
        var monitorClass="委托检测";
        //任务名
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getTaskName",
            method:"post",
            data:monitorClass,
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.taskName=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
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
        //结果
        /*  $http({
         url:"/sofn-ads-web/adsCheckInfo/getResult",
         method:"post",
         headers: {
         post: { 'Content-Type': 'application/x-www-form-urlencoded' }
         }
         }).success(function (datax) {
         if(datax.httpCode == 200){
         var datasx = datax.data;
         $scope.result=datasx;

         }else{
         jBox.tip("数据有误", 'info');
         }
         }).error(function (data) {
         jBox.tip("数据有误", 'info');
         });*/
        //抽样单位
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getSampleDeparment",
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.sampleDeparment=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //检测单位
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getCheckOrgan",
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function (datax) {
            if(datax.httpCode == 200){
                var datasx = datax.data;
                $scope.checkOrgan=datasx;

            }else{
                jBox.tip("数据有误", 'info');
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });
        //受检单位
        $http({
            url:"/sofn-ads-web/adsCheckInfo/getTestedDeparment",
            method:"post",
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
        });


    });

    $scope.taskDetail = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/delegatetaskDetail")
    }
    $scope.sampelPrint = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")
    }
    $scope.test = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/check")
    }
    $scope.import = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/import")
    }
    $scope.routineMonitorList = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/detectionInfo")
    }
    $scope.historyTaskList = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/historyTaskList")
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsCheckInfo/getInfoByOrgName",
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
                    params.monitorClass="委托检测";
                    params.orgName=$scope.orgName;//登录机构
                    params.year = $('#year option:selected').val();//年份
                    params.taskName = $('#taskName option:selected').val();//任务名称
                    params.result = $('#result option:selected').val();//结果

                    params.sampleDeparment = $('#sampleDeparment option:selected').val();//抽样单位
                    params.checkOrgan = $('#checkOrgan option:selected').val();//监测单位
                    params.testedDeparment = $('#testedDeparment option:selected').val();//受检单位
                    params.productCode = $("input[name='productCode']").val();    //产品编码
                    params.sampleCode = $("input[name='sampleCode']").val();    //样品编码
                    params.sampleName = $("#sampleName option:selected").val();    //样品名称
                    params.checkLink = $("input[name='checkLink']").val();    //检测环节
                    params.startTime = $("input[name='startTime']").val();//开始时间
                    params.endTime = $("input[name='endTime']").val();    //结束时间
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
                /*{
                 title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                 return view;
                 }
                 },*/
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                /*{
                 data: "productCode",
                 title: "产品编码"


                 },*/
                {
                    data: "sampleCode",
                    title: "样品编码",
                    render : function(data, type, row) {
                        var look_a = '<a  style="margin-right: 10px;cursor: pointer;color: #0000FF" class="Check-report" id=" sampleCode "    onclick="lookOrganRegion(\''+row.sampleCode+'\',\''+row.productCode+'\',\''+row.sampleName+'\') ">'+row.sampleCode+'</a>';
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
                /*{
                 data: "sampleDate",
                 title: "抽样时间"
                 },*/
                {
                    data: "checkOrgan",
                    title: "检测单位"
                },
                /* {
                 data: "checkTime",
                 title: "检测时间"
                 },*/
                {
                    data: "result",
                    title: "判定结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else if("0"==data){
                            return "不合格";
                        }
                    }
                },
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
    $(function() {
        //bootstrap表格
        $("#grids").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getInfoByOrgNameAll",
                headers: {
                    post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                },
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
                    /*   params.publishStatus = 0;   //查询未发布的任务*/
                    params.monitorClass="委托检测";
                    params.orgName=$scope.orgName;
                    params.year = $('#year option:selected').val();//年份
                    params.taskName = $('#taskName option:selected').val();//任务名称
                    params.result=$('#result option:selected').val();//结果
                    params.sampleDeparment = $('#sampleDeparment option:selected').val();//抽样单位
                    params.checkOrgan = $('#checkOrgan option:selected').val();//监测单位
                    params.testedDeparment = $('#testedDeparment option:selected').val();//受检单位
                    params.productCode = $("input[name='productCode']").val();    //产品编码
                    params.sampleCode = $("input[name='sampleCode']").val();    //样品编码
                    params.sampleName = $("#sampleName option:selected").val();    //样品名称
                    params.checkLink = $("input[name='checkLink']").val();    //检测环节
                    params.startTime = $("input[name='startTime']").val();//开始时间
                    params.endTime = $("input[name='endTime']").val();

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
                /*{
                 title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                 return view;
                 }
                 },*/
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
                        return '<input style="overflow: auto; overflow-y:hidden" value="'+data+'" type="text"/>';
                    }


                },
                {
                    data: "sampleCode",
                    title: "样品编码",
                    render : function(data, type, row) {
                        var look_a = '<a  style="margin-right: 10px;cursor: pointer;color: #0000FF;" class="Check-report" id=" sampleCode "    onclick="lookOrganRegion(\''+row.sampleCode+'\',\''+row.productTraceabilityCode+'\',\''+row.sampleName+'\') ">'+row.sampleCode+'</a>';
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
                            return "检测未上报";
                        }
                    }
                },
                {
                    data: "producingArea",
                    title: "抽样地区",
                    render:function(data,type,row){
                        var area=$.fn.Cityname(row.producingArea);
                        return area;

                    }
                },
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值"
                },
                {
                    data: "judgeNum",
                    title: "判定值"
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
    /**
     * 全选任务数据
     */
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#grid input[type='checkbox']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });
    //导出excel
    $scope.exportExcel = function (type) {
        var monitorClass="委托检测";
        if(type=="s"){
            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url:"/sofn-ads-web/adsCheckInfo/getOrgList",
                        method:"post",
                        dataType:"json",
                        params:{"monitorClass":monitorClass,"orgId":orgId},
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
                            a.setAttribute('download', '监测汇总.xls');
                            a.click();
                        }else{
                            jBox.tip("导出失败，没有数据！","info")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }else if(type=="a"){

            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url:"/sofn-ads-web/adsCheckInfo/getOrgListAll",
                        method:"post",
                        dataType:"json",
                        params:{"monitorClass":monitorClass,"orgId":orgId},
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
                            a.setAttribute('download', '详细信息.xls');
                            a.click();
                        }else{
                            jBox.tip("导出失败，没有数据！","info")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }

    };

    /**
     * 跳转到机构与地域页面
     */
    /*alert(sampleCode);*/
    window.lookOrganRegion=function(data,data1,data2){
        window.localStorage.setItem("ads_sample_code",data);
        window.localStorage.setItem("ads_product_traceability_code",data1);
        window.localStorage.setItem("ads_sample_name",data2);
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleInfo");
    };
    /* $scope.asd=window.localStorage.getItem("ads_product_code");*//* $scope.asd=window.localStorage.getItem("ads_product_code");*/
})







