var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("etlDailyEnforceLawlist",function($scope,$http,$state){
    var orgId = "" //机构ID
    var author =""
    $scope.dailyEnforceLawEnterpriseId ="";
   // $scope.authorityArray = [];
   $scope.authorityArray = window.localStorage.getItem("menus");
    var authorityArray = JSON.parse($scope.authorityArray);
    for(var i =0; i < authorityArray.length;i++){
        if(authorityArray[i].id == "515"){
            if(authorityArray[i].authority == "1"){
                author = authorityArray[i].authority;
                $("#lawAddId").attr("disabled", true);
                $("#delId").attr("disabled", true);
                break;
            }
        }
    }
    //获取当前登录用户机构信息，并完成初始化
    $http({
        url:"/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        orgId = data.data.orgId;
        $scope.subjLevel= data.data.orgLevel;//主体级别
        $scope.jurisdictionArea = data.data.regionId.substr(0,2*($scope.subjLevel-1));//管辖区域
        //限定查询区域
        $.fn.mycity("sheng","shi","xian",$scope.jurisdictionArea);
        if($("#sheng").val()!=null&&$("#sheng").val()!=""&&$("#sheng").val()!=undefined){
            $("#sheng").attr("disabled",true);
        }
        if($("#shi").val()!=null&&$("#shi").val()!=""&&$("#shi").val()!=undefined){
            $("#shi").attr("disabled",true);
        }
        if($("#xian").val()!=null&&$("#xian").val()!=""&&$("#xian").val()!=undefined){
            $("#xian").attr("disabled",true);
        }
        $scope.loadGrid();
    }).error(function(msg){
        console.log(msg);
        
    });
    /**
     * 获取当前登录用户Id
     * 注释日期:2018年2月28日16:54:30
     */
    $scope.dailyEnforceId='';
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/llesEntrustDetection/getCreateBy",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.dailyEnforceId =data.data;
    }).error(function (msg) {
        console.log(msg);
        
    });
    //点击vent=document.all?window.event:event;
        if((event.keyCode || event.which)==13){
            $scope.queryEnforceLawList();
        }
    });
    //初始化日志执法列表
    $scope.loadGrid = function() {
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawList",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        if(json.data.list[i].TASK_BEGIN_TIME!=null&&json.data.list[i].TASK_BEGIN_TIME!=""&&json.data.list[i].TASK_BEGIN_TIME!=undefined){
                            json.data.list[i].TASK_BEGIN_TIME=new Date(parseInt(json.data.list[i].TASK_BEGIN_TIME)+28800000).toISOString().slice(0,10);
                           // json.data.list[i].TASK_END_TIME=new Date(parseInt(json.data.list[i].TASK_END_TIME)+28800000).toISOString().slice(0,10);//取消时间段限制 2018年4月24日17:43:34
                            json.data.list[i].CREATE_TIME=new Date(parseInt(json.data.list[i].CREATE_TIME)+28800000).toISOString().slice(0,10);
                        }else if(json.data.list[i].TASK_BEGIN_TIME == null || json.data.list[i].TASK_BEGIN_TIME == ""){ //开始时间脏数据处理 2018年6月6日13:53:36
                            json.data.list[i].TASK_BEGIN_TIME = "---";
                           // json.data.list[i].CREATE_TIME = "---";
                        }
                    }
                    return json.data.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                data : function(params) {
                    params.createOrgId=orgId;
                    params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                    params.taskYear=$("input[name='taskYear']").val();
                    params.queryCon=$("input[name='queryCon']").val();
                    params.dateBegin=$("input[name='datebegin']").val();
                    params.dateEnd=$("input[name='dateend']").val();
                    params.enforceLawResultFlag = $("select[name='enforceLawResultFlag']").val();
                    ObjShen=$("select[name='ObjShen']").val();
                    ObjShi=$("select[name='ObjShi']").val();
                    ObjXian=$("select[name='ObjXian']").val();
                    if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
                        if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                            if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                                params.areaId=ObjXian
                            }else{
                                params.areaId=ObjShi
                            }
                        }else {
                            params.areaId = ObjShen
                        }
                    }
                },
                type:"POST",
            },
            columns : [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width: "3%"
                },
                {
                    data: 'ID',
                    title: 'ID',
                    visible: false
                },
                {
                    data: 'ENTERPRISE_ID',
                    title: 'ENTERPRISE_ID',
                    visible: false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width : "3%"
                },
                {
                    data : "TASK_BEGIN_TIME",
                    title : "年度",
                    width : "8%",
                    render : function(data, type, row) { // 模板化列显示内容
                        return data.substring(0,4);
                    },
                },
                {
                    data : "TASK_NAME",
                    title : "任务名称",
                    render: function(data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                    width : "10%"
                },
                {
                    data : "ENTERPRISE_NAME",
                    title : "被执法对象",
                    render: function(data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                    width : "10%"
                },
                {
                    data : "AREA_ID",
                    title : "区域",
                    width : "10%",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(data!=null&&data!=""&&data!=undefined){
                            var region = $.fn.Cityname(row.AREA_ID);
                            var regions;
                            if (region) {
                                regions = region.substring(0, data.length);
                                var showdata = regions;
                                if (region.length > 10) {
                                    showdata = region.substring(0, 10) + "..";
                                    return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                                }
                                return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            } else {
                                return '--';
                            }
                            /*return $.fn.Cityname(row.AREA_ID)*/
                        }
                    }
                },
                {
                    data : "ENTERPRISE_ADDRESS",
                    title : "被执法对象地址",
                    render: function(data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                    width : "10%"
                },
                {
                    data : "TASK_BEGIN_TIME",
                    title : "执法时间",
                    width : "10%"
                },
                /*{
                    data : "TASK_END_TIME",
                    title : "结束时间",
                    width : "10%"
                },*/
                {
                    data : "CREATE_TIME",
                    title : "创建时间",
                    width : "10%"
                },
                {
                    data : "ENFORCE_LAW_RESULT_FLAG",
                    title : "巡查结果",
                    width : "5%",
                    render: function (data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        } else if (data == "4") {
                            return "其他";
                        }
                    }
                },
                {
                    data:"CREATE_BY",
                    title:"",
                    visible:false
                },
                {
                    data : "ID",
                    title : "操作",
                    width : "10%",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(row.CREATE_BY ==  $scope.dailyEnforceId && author != "1"){
                            return '<a type="button" data-toggle="modal" data-target="#myModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="Checkreport(\''+data+'\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\''+data+'\',\''+row.ENTERPRISE_ID+'\')">修改</a> <a type="button" data-toggle="modal" data-target="#printModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer" onclick="GotoPrintWindow(\''+data+'\')">打印</a>';//转义传值
                        }else{
                            return '<a type="button" data-toggle="modal" data-target="#myModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="Checkreport(\''+data+'\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;"<a type="button" data-toggle="modal" data-target="#printModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer" onclick="GotoPrintWindow(\''+data+'\')">打印</a>';//转义传值
                        }
                    }
                }
            ]
        });
    }
    /*
    打印的信息查看
     */
    window.GotoPrintWindow = function(data){
        window.localStorage.setItem("etlDailyEnforceLawID",data)
        window.localStorage.removeItem("motaikuangBiaoshi");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
            method:"post",
            data:{id:data}
        }).success(function(data){
            $scope.reCord = data.dailyEnforceLaw;
            $scope.dailyEnforceLawEnterpriseId = $scope.reCord.enterpriseId;
            if($scope.reCord.taskBeginTime!=null&&$scope.reCord.taskBeginTime!=""&&$scope.reCord.taskBeginTime!=undefined) {
                $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)+28800000).toISOString().slice(0, 10);
            //    $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
            }
            if($scope.reCord.areaId!=null&&$scope.reCord.areaId!=""&&$scope.reCord.areaId!=undefined) {
                $scope.areaId=$.fn.Cityname($scope.reCord.areaId);
            }
            if($scope.reCord.enforceLawResultFlag!=null&&$scope.reCord.enforceLawResultFlag!=""&&$scope.reCord.enforceLawResultFlag!=undefined){
                if($scope.reCord.enforceLawResultFlag=="1"){
                    $scope.reCord.enforceLawResultFlag="合格";
                }
                if($scope.reCord.enforceLawResultFlag=="2"){
                    $scope.reCord.enforceLawResultFlag="不合格";
                }
                if($scope.reCord.enforceLawResultFlag=="3"){
                    $scope.reCord.enforceLawResultFlag="整改";
                }
                if($scope.reCord.enforceLawResultFlag=="4"){
                    $scope.reCord.enforceLawResultFlag="其他";
                }
            };
        }).error(function(data){
            console.log(data);
            
            });
    }

    window.GotoUpdateWindow = function(data,rowID){
        window.localStorage.setItem("id",data);
        window.localStorage.setItem("ENTERPRISE_ID",rowID)
        window.localStorage.setItem("ales_etl_updateFlag","1");
        $state.go("index.content.ales/etl/etlAddDailyEnforceLaw")
    }

    $scope.cleanSelectModelQuery = function(){
        $("input[name='queryCon']").val("");
        $("input[name='taskYear']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("select[name='enforceLawResultFlag']").val("");
        $.fn.mycity("sheng","shi","xian",$scope.jurisdictionArea);
        $("#grid").dataTable().api().ajax.reload();
    };
    //删除
    $scope.del = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var k = 0; k < length; k++) {
            if(rows[k].CREATE_BY != $scope.dailyEnforceId){
                $.jBox.tip("没有权限删除本任务");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }

        if (rows != false) {
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(ids)
                    };
                    $.ajax({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url: "/sofn-ales-web/dailyEnforceLaw/del",
                        type: "post",
                        dataType: "json",
                        data: data,
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },error:function (msg) {
                            console.log(msg);
                            
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $scope.queryEnforceLawList();
                        },
                        fail: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }



                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };




    //定义变量存储值
    var dailyEnforceLawEnterpriseId = "";
    //跳转到查看详情页面
    window.subjEntView=function(data){
        window.localStorage.removeItem("etlDailyEnforceLawID");
        window.localStorage.setItem("etlDailyEnforceLawID",data);
        $state.go("index.content.ales/etl/etlEnterView", {source:'sys'});
    }
    //查看详情页面
    window.Checkreport=function(data){
        window.localStorage.setItem("etlDailyEnforceLawID",data)
        window.localStorage.removeItem("motaikuangBiaoshi");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
            method:"post",
            data:{id:data},
        }).success(function(data){
            $scope.reCord = data.dailyEnforceLaw;
            $scope.dailyEnforceLawEnterpriseId = $scope.reCord.enterpriseId;
            if($scope.reCord.taskBeginTime!=null&&$scope.reCord.taskBeginTime!=""&&$scope.reCord.taskBeginTime!=undefined) {
                $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)+28800000).toISOString().slice(0, 10);
          //      $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
            }
            if($scope.reCord.areaId!=null&&$scope.reCord.areaId!=""&&$scope.reCord.areaId!=undefined) {
                $scope.areaId=$.fn.Cityname($scope.reCord.areaId);
            }
            if($scope.reCord.enforceLawResultFlag!=null&&$scope.reCord.enforceLawResultFlag!=""&&$scope.reCord.enforceLawResultFlag!=undefined){
                if($scope.reCord.enforceLawResultFlag=="1"){
                    $scope.reCord.enforceLawResultFlag="合格";
                }
                if($scope.reCord.enforceLawResultFlag=="2"){
                    $scope.reCord.enforceLawResultFlag="不合格";
                }
                if($scope.reCord.enforceLawResultFlag=="3"){
                    $scope.reCord.enforceLawResultFlag="整改";
                }
                if($scope.reCord.enforceLawResultFlag=="4"){
                    $scope.reCord.enforceLawResultFlag="其他";
                }
            };
            $scope.reCord.address = $scope.reCord.enterpriseAddress;
            $scope.reCord.peson = $scope.reCord.principal;
           // if($scope.reCord.scenePictures!=null&&$scope.reCord.scenePictures!=undefined&&$scope.reCord.scenePictures!="") {
                var array = $scope.reCord.scenePictures.split("*##*");
                for (var i = 0; i < array.length; i++) {
                    if(i > 0){
                        $("#scenePictures").append('<img style="padding-right: 10px; width: 80px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                    }else {
                        $("#scenePictures").html('<img style="padding-right: 10px; width: 80px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                    }
               }
           // };
            //if($scope.reCord.principalSignatures!=null&&$scope.reCord.principalSignatures!=undefined&&$scope.reCord.principalSignatures!="") {
                var array = $scope.reCord.principalSignatures.split("*##*");
              ///  for (var i = 0; i < array.length; i++) {
                    $("#principalSignatures").html('<img style="padding-right: 10px; width: 80px" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[0]+ '" alt=""/>');
               // }
           // };
        }).error(function(msg){
            console.log(msg);
            
        });
    }
    //查看--被巡查对象信息
    window.selectEnterpriseInfo = function(){
        $('#myModal').on('hidden.bs.modal', function () {
            window.localStorage.setItem("dELE_EnterpriseId",$scope.dailyEnforceLawEnterpriseId);
            $state.go("index.content.ales/etl/etlEnterpriseview");
        });
        $("#myModal").modal("hide");

    }
    //打印--被巡查对象信息
    window.printEnterpriseInfo = function(){

        $('#printModal').on('hidden.bs.modal', function () {
            window.localStorage.setItem("dELE_EnterpriseId",$scope.dailyEnforceLawEnterpriseId);
            $state.go("index.content.ales/etl/etlEnterpriseview");
        });
        $("#printModal").modal("hide");
    }
    //新增
    $scope.lawAdd=function(){
        window.localStorage.setItem("ales_etl_updateFlag","0");
    }
    //获取缓存中的数据（定位标识）
    var etlID = "";
    var huoquBiaoshiEtlDaily = "";
    huoquBiaoshiEtlDaily = window.localStorage.getItem("motaikuangBiaoshi");
    if(huoquBiaoshiEtlDaily!=null){
        $("#myModal").modal("show");
        etlID = window.localStorage.getItem("etlDailyEnforceLawID");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
            method:"post",
            data:{id:etlID}
        }).success(function(data){
            $scope.reCord = data.dailyEnforceLaw;
            $scope.dailyEnforceLawEnterpriseId = $scope.reCord.enterpriseId;
            if($scope.reCord.taskBeginTime!=null&&$scope.reCord.taskBeginTime!=""&&$scope.reCord.taskBeginTime!=undefined) {
                $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)+28800000).toISOString().slice(0, 10);
              //  $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
            }
            if($scope.reCord.areaId!=null&&$scope.reCord.areaId!=""&&$scope.reCord.areaId!=undefined) {
                $scope.areaId=$.fn.Cityname($scope.reCord.areaId);
            }
            if($scope.reCord.enforceLawResultFlag!=null&&$scope.reCord.enforceLawResultFlag!=""&&$scope.reCord.enforceLawResultFlag!=undefined){
                if($scope.reCord.enforceLawResultFlag=="1"){
                    $scope.reCord.enforceLawResultFlag="合格";
                }
                if($scope.reCord.enforceLawResultFlag=="2"){
                    $scope.reCord.enforceLawResultFlag="不合格";
                }
                if($scope.reCord.enforceLawResultFlag=="3"){
                    $scope.reCord.enforceLawResultFlag="整改";
                }
                if($scope.reCord.enforceLawResultFlag=="4"){
                    $scope.reCord.enforceLawResultFlag="其他";
                }
            };
            $scope.reCord.address = $scope.reCord.enterpriseAddress;
            $scope.reCord.peson = $scope.reCord.principal;
            var array = $scope.reCord.scenePictures.split("*##*");
            $("#scenePictures").html('<img style="padding-right: 10px; width: 80px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[0]+ '" alt=""/>');
            var array = $scope.reCord.principalSignatures.split("*##*");
            $("#principalSignatures").html('<img style="padding-right: 10px; width: 80px" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[0]+ '" alt=""/>');
        }).error(function(msg){
            console.log(msg);
            
            });

    }

    window.qinchuhuanchun=function(){
        /**
         * 清除主体信息中缓存的标识
         */
        window.localStorage.removeItem("motaikuangBiaoshi");
        window.localStorage.removeItem("etl_ID");
        $("#myModal").modal("hide");

    }
    /**
     * 打印
     */
    window.printArea=function(){
        etl_ID = window.localStorage.getItem("etlDailyEnforceLawID");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
            method: "post",
            data: {id: etl_ID}
        }).success(function (data) {
            $scope.reCord=data.dailyEnforceLaw;
            dailyEnforceLawEnterpriseId = $scope.reCord.enterpriseId;
            $("input[name='taskName']").val($scope.reCord.taskName);
            $("input[name='beginTime']").val($scope.beginTime);
            $("input[name='endTime']").val($scope.endTime);
            $("input[name='enterpriseName']").val($scope.reCord.enterpriseName);
            $("input[name='areaId']").val($scope.areaId);
            $("input[name='taskPersonCount']").val($scope.reCord.taskPersonCount);
            $("input[name='taskPersonName']").val($scope.reCord.taskPersonName);
            if($scope.reCord.enforceLawResultFlag!=null&&$scope.reCord.enforceLawResultFlag!="" && $scope.reCord.enforceLawResultFlag!=undefined){
                if($scope.reCord.enforceLawResultFlag=="1"){
                    $scope.reCord.enforceLawResultFlag="合格";
                }
                if($scope.reCord.enforceLawResultFlag=="2"){
                    $scope.reCord.enforceLawResultFlag="不合格";
                }
                if($scope.reCord.enforceLawResultFlag=="3"){
                    $scope.reCord.enforceLawResultFlag="整改";
                }
                if($scope.reCord.enforceLawResultFlag=="4"){
                    $scope.reCord.enforceLawResultFlag="其他";
                }
            };
            $("input[name='enforceLawResultFlag']").val($scope.reCord.enforceLawResultFlag);
            $("input[name='enforceLawResult']").val($scope.reCord.enforceLawResult);
        }).error(function (msg) {
            console.log(msg);
            
            });
        $("#printInfo").printArea()
    }


});




