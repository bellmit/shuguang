//    angularjs数据传输
var myapp=angular.module("myapp",[]);
myapp.controller("emergencyAdd",function($scope,$http,$state){
    $scope.releaseUnitLevelName={};
    $scope.asmsEmergencyTask = {};//应急任务
    //激活验证规则
    $('#produceAdminPunishForm').bootstrapValidator();//必须有
    $.fn.mycity("sheng","shi","xian",0);
    $scope.updateFlag = window.localStorage.getItem("asms_etl_updateFlag");
    $scope.id=window.localStorage.getItem("id");
    var token = window.localStorage.getItem("token")
    var area = ""
    var areaId=""
    var bjArea=""
    var orgId =""
    $http({
        url:"/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        $(".youBearUnit").attr("checked",true);
        area = data.data.regionId;
        areaId =area.substring(0,2);
        orgId = data.data.orgId;
        $.fn.mycity("sheng","shi","xian",area);
        if($("#sheng").val()!=null&&$("#sheng").val()!=""&&$("#sheng").val()!=undefined){
            $("#sheng").attr("disabled",true);
        }
        if($("#shi").val()!=null&&$("#shi").val()!=""&&$("#shi").val()!=undefined){
            $("#shi").attr("disabled",true);
        }
        if($("#xian").val()!=null&&$("#xian").val()!=""&&$("#xian").val()!=undefined){
            $("#xian").attr("disabled",true);
        }
    }).error(function (data) {
        
    });

    /**
     * 当前用户机构
     * */
    $http({
        url:"/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        area = data.data.regionId;
        areaId = area.substring(0,2);
        bjArea=data.data.orgLevel;
        orgId = data.data.orgId;
        $("input[name='taskType']").val("应急任务");
        //当前用户机构
        $("input[name='releaseUnit']").val(data.data.orgName);
        //当前用户机构级别
        $("input[name='releaseUnitLevel']").val(data.data.orgLevel);
        if(data.data.orgLevel=="1"){
            $scope.releaseUnitLevelName.name="部级";
        }
        if(data.data.orgLevel=="2"){
            $scope.releaseUnitLevelName.name="省级";
        }
        if(data.data.orgLevel=="3"){
            $scope.releaseUnitLevelName.name="市级";
        }
        if(data.data.orgLevel=="4"){
            $scope.releaseUnitLevelName.name="县级";
        }

    }).error(function (data) {
        
    });
    if($scope.updateFlag=="1"){
        $("#breadcrumb").html("修改");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/superviseEmergency/findAsmsEmergencyTaskById",
            method:"post",
            data:{id:$scope.id}
        }).success(function(data){
            $scope.asmsEmergencyTask=data.asmsEmergencyTask;
            //区域回显的处理
            $scope.ObjXian = $scope.asmsEmergencyTask.areaId;
            //隐藏域赋值
            $.fn.mycity("sheng","shi","xian",$scope.ObjXian)
            $("input[name='HiddenID']").val($scope.id);
            $("input[name='taskBegin']").val(new Date(parseInt($scope.asmsEmergencyTask.taskBegin)+28800000).toISOString().slice(0, 10));
            $("input[name='taskEnd']").val(new Date(parseInt($scope.asmsEmergencyTask.taskEnd)+28800000).toISOString().slice(0, 10));
            //初始文件
            vm.files = [
                { //占位
                    hidden: false,
                    src: $scope.asmsEmergencyTask.files,
                    name: $scope.asmsEmergencyTask.filesName
                }
            ];
            $("textarea[name='expertName']").val($scope.asmsEmergencyTask.expertName);
            if ($scope.asmsEmergencyTask.isBearUnit == '1') {
                $(".bearUnit").css("display","block")
                $(".youBearUnit").prop("checked",true);
            }else {
                $(".bearUnit").css("display","none")
                $(".meiYouBearUnit").prop("checked",true);
            }
        });
    }else{
        $("#produceAdminPunishForm").form("reset")
    }
    $scope.inspectionTime = new Date(new Date().getTime()+ 28800000).toISOString();
    //***
    //* 多文件上传
    //* */
    var vm = this;

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-asms-web/asmsFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        accept:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,image/*,application/pdf',
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            // var acceptFileTypes = /^((doc|docx|pdf)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            // if (file.type && file.type != '') {
            //     if (!acceptFileTypes.test(file.type)) {
            //         swal('', '只能上传word类型文档', 'warning');
            //         return false;
            //     }
            // } else {
            //     if (!/\.(doc|docx)$/i.test(file.name)) {
            //         swal('', '只能上传word类型文档', 'warning');
            //         return false;
            //     }
            // }
            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.Go_back=function(){
        for(var i=0;i<vm.files.length;i++){
            $scope.asmsEmergencyTask.file=vm.files[i].src;
            $scope.asmsEmergencyTask.fileName=vm.files[i].name;
        }
        if($scope.asmsEmergencyTask.file==null||$scope.asmsEmergencyTask.file==""||$scope.asmsEmergencyTask.file==undefined){
            jBox.tip("请上传文件！", 'info');
            return false;
        }
        if (!$("#produceAdminPunishForm").data('bootstrapValidator').validate().isValid()) {
            $scope.asmsEmergencyTask.file = "";
            $scope.asmsEmergencyTask.fileName = "";
            return false;
            if($("input[name='taskBegin']").val()==""){
                $(".objBeginTime").css("display","block")
                return;
            }else{
                $(".objBeginTime").css("display","none")
            }
            if($("input[name='taskEnd']").val()==""){
                $(".objEndTime").css("display","block");
                return;
            }else{
                $(".objEndTime").css("display","none")
            }
            return false;
        }//必须有
        if($("input[name='isBearUnit']:checked").val()=="0"){
            $("input[name='bearUnit']").val("")
        }
        ObjShen=$("select[name='ObjShen']").val();
        ObjShi=$("select[name='ObjShi']").val();
        ObjXian=$("select[name='ObjXian']").val();
        if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
            if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                    $scope.ObjXian=ObjXian
                }else{
                    $scope.ObjXian=ObjShi
                }
            }else {
                $scope.ObjXian= ObjShen
            }
        }
        $scope.asmsEmergencyTask.createOrgId = orgId;
        $scope.AREA_ID=$scope.ObjXian;
        $scope.asmsEmergencyTask.id = $("input[name='HiddenID']").val();
        $scope.asmsEmergencyTask.taskName = $("input[name='taskName']").val()
        $scope.asmsEmergencyTask.taskType = $("input[name='taskType']").val()
        $scope.asmsEmergencyTask.areaId = $scope.AREA_ID
        $scope.asmsEmergencyTask.taskBegin = new Date(($("input[name='taskBegin']").val()+" 00:00:00").replace(/-/g,"/"))
        $scope.asmsEmergencyTask.taskEnd  = new Date(($("input[name='taskEnd']").val()+" 00:00:00").replace(/-/g,"/"))
        $scope.asmsEmergencyTask.releaseUnit = $("input[name='releaseUnit']").val()
        $scope.asmsEmergencyTask.releaseUnitLevel = $("input[name='releaseUnitLevel']").val();
        $scope.asmsEmergencyTask.isBearUnit = $("input[name='isBearUnit']:checked").val()
        $scope.asmsEmergencyTask.bearUnit = $("input[name='bearUnit']").val()
        $scope.asmsEmergencyTask.files = $scope.asmsEmergencyTask.file
        $scope.asmsEmergencyTask.filesName = $scope.asmsEmergencyTask.fileName
        $scope.asmsEmergencyTask.fileCode = $("input[name='fileCode']").val()
        $scope.asmsEmergencyTask.remark = $("textarea[name='remark']").val()
        $scope.asmsEmergencyTask.expertName = $("textarea[name='expertName']").val()
        $scope.asmsEmergencyTask.expertId = $("input[name='expertId']").val();
        if($scope.updateFlag=="1"){
            $("button").attr("disabled",true);
            $http({
                url:"/sofn-asms-web/superviseEmergency/updateSuperviseEmergency",
                data:$scope.asmsEmergencyTask,
                method:"post",
                headers:{
                    token:window.localStorage.getItem("token")
                },
            }).success(function(data){
                $("button").attr("disabled",false);
                window.localStorage.removeItem("id");
                window.localStorage.removeItem("asms_etl_updateFlag");
                $state.go("index.content.asms/emergency/emergencyList");
                $.jBox.tip("发布成功！");
            }).error(function(data){
                
                    $("button").attr("disabled",false);
                    $.jBox.tip("发布失败");
                })
        }else{
            $("button").attr("disabled",true);
            $http({
                url:"/sofn-asms-web/superviseEmergency/addSuperviseEmergency",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data:$scope.asmsEmergencyTask,
                method:"post",

            }).success(function(data){
                $("button").attr("disabled",false);
                $state.go("index.content.asms/emergency/emergencyList");
                $.jBox.tip("发布成功！");
            }).error(function(data){
                    
                    $("button").attr("disabled",false);
                    $.jBox.tip("发布失败");
                })
        }
    };
    $scope.emergencyTaskIsBearUnit=function(){
        var emergencyIsBearUnit=$("input[name='isBearUnit']:checked").val()
        if(emergencyIsBearUnit=="0"){
            $(".bearUnit").css("display","none")
        }else{
            $(".bearUnit").css("display","block")
        }
    }
    //对执法开始时间的验证
    window.taskendInput = function(){
        if($("input[name='taskEnd']").val()==""){
            $(".objEndTime").css("display","block");
            return;
        }else{
            $(".objEndTime").css("display","none")
        }
    }
    //对执法结束时间的验证
    window.clearValidate = function(){
        $("#produceAdminPunishForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#produceAdminPunishForm").bootstrapValidator();//初始化验证，与上面对应
    }
    //取消清楚缓存
    $scope.quxiao=function(){
        window.localStorage.removeItem("id");
        window.localStorage.removeItem("asms_etl_updateFlag");
        $state.go("index.content.asms/emergency/emergencyList");
    };
    //清楚缓存
    $scope.RemoveLocal=function(){
        window.localStorage.removeItem("id");
        window.localStorage.removeItem("asms_etl_updateFlag");
    };
    //获取投诉主题(职级)数据字典
    $scope.jobTitles = [];
    $http({
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        headers:{
            token:window.localStorage.getItem("token")
        },
        data:"3",
        method:"post"
    }).success(function(data) {
        $scope.jobTitles=JSON.parse(data.data);
    })


    $scope.selectsperson = new Array();
    $(function(){
        //模态框设置
        $("#expertName").click(function(){
            //根据登录人省份默认专家资源省份
            if(bjArea=="1"){
                $.fn.mycity("expertSheng","expertShi","expertXian");
            }else{
                $.fn.mycity("expertSheng","expertShi","expertXian",areaId);
                if ($("#expertSheng").val() != null && $("#expertSheng").val() != "" && $("#expertSheng").val() != undefined) {
                    $("#expertSheng").attr("disabled", true);
                }
                if ($("#expertShi").val() != null && $("#expertShi").val() != "" && $("#expertShi").val() != undefined) {
                    $("#expertShi").attr("disabled", true);
                }
                if ($("#expertXian").val() != null && $("#expertXian").val() != "" && $("#expertXian").val() != undefined) {
                    $("#expertXian").attr("disabled", true);
                }
            }
            $("#sysResGrid").dataTable().api().ajax.reload();
            $("#sysResource_modal").modal("show");
        });
        $("#ToCheck").click(function(){
            //点击间隔
            $scope.$apply($scope.btnSysResourceDisabled = true);
            setTimeout(function(){
                $scope.$apply($scope.btnSysResourceDisabled = false);
            }, 500);

            $("#sysResGrid").dataTable().api().ajax.reload();
        });
        $("#sysResGrid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            //paging:true,
            //bInfo:true,
            //bJQueryUI: true,
            //bPaginate: false,// 分页按钮
            //bFilter: true,// 搜索栏
            iDisplayLength: 10,// 每页显示行数
            //bSort: false,// 排序
            //sScrollY : "auto",//模态框不取消影响展示效果
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            fnDrawCallback: function() {
                $("#checkAllPerson").prop("checked",$("#sysResGrid input[name='selectZiyuan']").length==$("#sysResGrid input[name='selectZiyuan']:checked").length&&$("#sysResGrid input[name='selectZiyuan']").length>0);
                $("#sysResGrid tbody tr td").each(function(i,o){
                    $(o).click(function(){
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if(checked.checked){
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //移除
                            var u_d = $(this).parent().find("[name='selectZiyuan']").val();
                            for (var i = 0 ; i < $scope.selectsperson.length ; i++){
                                if($scope.selectsperson[i] != undefined && u_d == $scope.selectsperson[i].selectZiyuan){
                                    delete $scope.selectsperson[i];
                                }
                            }
                        }else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //选择记录
                            /**
                             * 使用superviseBearUnitId与leadUnitName字段方便映射
                             * */
                            var map={
                                selectZiyuan:$(this).parent().find("[name='selectZiyuan']").val(),
                                resourceName:$(this).parent().find("[name='resourceName']").html()
                            };
                            $scope.selectsperson.push(map);
                        }
                        $("#checkAllPerson").prop("checked",$("#sysResGrid input[name='selectZiyuan']").length==$("#sysResGrid input[name='selectZiyuan']:checked").length&&$("#sysResGrid input[name='selectZiyuan']").length>0);
                    });
                });
            },
            ajax : {
                url : "/sofn-asms-web/sysResource/getSysResourceList",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].resourcetype != null && json.data.list[i].resourcetype != "" && json.data.list[i].resourcetype != undefined) {
                            if(json.data.list[i].resourcetype=="1"){
                                json.data.list[i].resourcetype="专家";
                            }
                        }
                        if (json.data.list[i].professionalfiled != null && json.data.list[i].professionalfiled != "" && json.data.list[i].professionalfiled != undefined) {
                            if(json.data.list[i].professionalfiled=="1"){
                                json.data.list[i].professionalfiled="检测";
                            }
                            if(json.data.list[i].professionalfiled=="2"){
                                json.data.list[i].professionalfiled="农药";
                            }
                            if(json.data.list[i].professionalfiled=="3"){
                                json.data.list[i].professionalfiled="检测检疫"
                            }
                        }
                    }
                    return json.data.list;

                },
                data : function(params) {
                    params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                    params.professionalfiled=$("select[name='professionalfiled']").val();
                    params.location=$("select[name='location']").val();
                    params.queryCon=$("input[name='queryCon']").val();
                },
                type:"post",
            },
            columns : [
                //选择按钮
                {
                    data : "id",
                    title : '<input id="checkAllPerson" onclick="checkAllPerson(this.checked)" type=checkbox>',
                    width : "3%",
                    render : function(data, type, row) {
                        for(var i = 0; i <  $scope.selectsperson.length;i++) {
                            if ($scope.selectsperson[i] != undefined && data == $scope.selectsperson[i].selectZiyuan) {
                                return '<input class="selectZiyuan" name="selectZiyuan" checked = "true" type="checkbox" style="cursor: pointer;width:15px;height:15px" value="' + data + '" onclick="checkClick(this)" />';
                            }
                        }
                        return '<input class="selectZiyuan" name="selectZiyuan" type="checkbox" style="cursor: pointer;width:15px;height:15px" value="' + data + '" onclick="checkClick(this)" />';
                    }
                },
                {
                    data : 'id',
                    title : 'ID',
                    visible : false
                },
                {
                    //data : "RN",
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width : "3%",
                },
                {
                    data : "resourcetype",
                    title : "资源类型",
                    width : "6%"
                },
                {
                    data : "professionalfiled",
                    title : "专业领域",
                    width : "6%"
                },
                {
                    data : "name",
                    title : "姓名",
                    width : "8%",
                    render : function(data, type, row) {
                        return '<span name = "resourceName">'+data+'</span>';
                    }
                },
                {
                    data : "jobtitle",
                    title : "职称",
                    width : "6%",
                    render: function (data, type, row) {
                        for (var j = 0; j < $scope.jobTitles.length; j++){
                            if($scope.jobTitles[j].dictValue == row.jobtitle){
                                return $scope.jobTitles[j].dictName;
                            }
                        }
                        return data;
                    }
                },
                {
                    data : "location",
                    title : "所在地",
                    width : "10%",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(row.location == null){
                            return "无";
                        }
                        return $.fn.Cityname(row.location)
                    }
                },
                {
                    data : "unit",
                    title : "单位",
                    width : "8%"
                },
                {
                    data : "address",
                    title : "详细地址",
                    width : "8%"
                },
                {
                    data : "contract",
                    title : "联系方式",
                    width : "6%"
                }
            ]
        });
    });

    //专家资源全选,全不选
    window.checkAllPerson = function(checked){
        //移除本页全部专家资源
        $("#sysResGrid input[name='selectZiyuan']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selectsperson.length ; i++){
                if($scope.selectsperson[i] != undefined && u_d == $scope.selectsperson[i].selectZiyuan){
                    delete $scope.selectsperson[i];
                }
            }
        })
        //全选本页专家资源
        if(checked){
            $("#sysResGrid input[name='selectZiyuan']:checked").each(function(i,o) {
                var map = {
                    selectZiyuan: $(o).val(),
                    resourceName: $(o).parent().parent().find("[name='resourceName']").html()
                };
                $scope.selectsperson.push(map);
            });
        }
    }

    //重置
    $scope.cleanSysResource = function(){

        //点击间隔
        $scope.btnSysResourceReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnSysResourceReloadDisabled = false);
        }, 500);

        $("select[name='professionalfiled']").val("");
        $("input[name='queryCon']").val("");
        $.fn.mycity("shen","shi","xian",areaId);
        $("#sysResGrid").dataTable().api().ajax.reload();
    }
    $scope.cleanselectSysResource=function(){

        //点击间隔
        $scope.btnSysResourceClearDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnSysResourceClearDisabled = false);
        }, 500);

        $scope.selectsperson  = null;
        $scope.selectsperson  = new Array();
        $("#checkAllPerson").prop("checked",false);
        $("#sysResGrid").dataTable().api().ajax.reload();
        $("textarea[name='expertName']").val("")
        $("input[name='expertId']").val("")
        $("input[name='selectZiyuan']").removeAttr("checked","checked");
    }
    //提交
    $scope.SubMain=function(){
        var userName = "";
        var userNameID = ""
        for(var i = 0; i < $scope.selectsperson.length ; i++){
            if ($scope.selectsperson[i] != undefined){
                if (userName != ""){
                    userName+=","+$scope.selectsperson[i].resourceName;
                    userNameID+=","+$scope.selectsperson[i].selectZiyuan;
                }else {
                    userName = $scope.selectsperson[i].resourceName;
                    userNameID = $scope.selectsperson[i].selectZiyuan;
                }
            }
        }
        //$("textarea[name='expertName']").val("");//初始化
        //$("input[name='expertId']").val("");//初始化

        $("textarea[name='expertName']").val(userName);
        $("input[name='expertId']").val(userNameID);
        $("#sysResource_modal").modal("hide");

    }



    /**
     * ---------承担单位选择列表--------------
     * */
        //机构类别
    $("#selectTocheck").click(function(){
        //点击间隔
        $scope.$apply($scope.btnBearUnitDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnBearUnitDisabled = false);
        }, 500);
        $("#selectGrid1").dataTable().api().ajax.reload();
        $("#selectGrid2").dataTable().api().ajax.reload();
        $("#selectGrid3").dataTable().api().ajax.reload();
    });
    /**
     * 加载机构数据
     */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects  = new Array();

    $('#selectModal').modal({
        show:false,
        backdrop:'static',
        keyboard:false
    });
    $scope.selectBearUnit= function() {
        if (bjArea == "1") {
            $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");
        } else {
            $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", area);
            if ($("#selectQueryShen").val() != null && $("#selectQueryShen").val() != "" && $("#selectQueryShen").val() != undefined) {
                $("#selectQueryShen").attr("disabled", true);
            }
            if ($("#selectQueryShi").val() != null && $("#selectQueryShi").val() != "" && $("#selectQueryShi").val() != undefined) {
                $("#selectQueryShi").attr("disabled", true);
            }
            if ($("#selectQueryXian").val() != null && $("#selectQueryXian").val() != "" && $("#selectQueryXian").val() != undefined) {
                $("#selectQueryXian").attr("disabled", true);
            }
        }
        $('#selectGrid1').dataTable().api().ajax.reload();//由于修改初始数据加载顺序原因导致已选择数据无法自动选定，所以显示选择框时必须根据已选择数据刷新一次
        loudData();
        $("#selectModal").modal('show');

    }
    //机构类型的选择
    $("#orgchange").change(function(){
        loudData();
    });
    function loudData(){
        //批次属性显示判断
        var orgType = $("select[name='orgchange']").val();
        if("1" == orgType){
            $('#selectGrid1').dataTable().api().ajax.reload();
            $("#selectGrid1_wrapper").show();
            $("#selectGrid2_wrapper").hide();
            $("#selectGrid3_wrapper").hide();
        }
        if("2" == orgType){
            $('#selectGrid2').dataTable().api().ajax.reload();
            $("#selectGrid2_wrapper").show();
            $("#selectGrid1_wrapper").hide();
            $("#selectGrid3_wrapper").hide();
        }
        if("3"==orgType ){
            $('#selectGrid3').dataTable().api().ajax.reload();
            $("#selectGrid3_wrapper").show();
            $("#selectGrid1_wrapper").hide();
            $("#selectGrid2_wrapper").hide();
        }
    }
    $("#selectGrid1").dataTable({
        fnDrawCallback: function() {
            $("#checkAllJg").prop("checked",$("#selectGrid1 input[name='ck']").length==$("#selectGrid1 input[name='ck']:checked").length&&$("#selectGrid1 input[name='ck']").length>0);
            //点击行内任意内容即选择行
            $("#selectGrid1 tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[type='checkbox']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        //移除
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0 ; i < $scope.selects.length ; i++){
                            if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                                delete $scope.selects[i];
                            }
                        }
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        //选择记录
                        /**
                         * 使用leadUnitId与leadUnitName字段方便映射
                         * */
                        var map={
                            leadUnitId:$(this).parent().find("[name='ck']").val(),
                            leadUnitName:$(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                    }
                    $("#checkAllJg").prop("checked",$("#selectGrid1 input[name='ck']").length==$("#selectGrid1 input[name='ck']:checked").length&&$("#selectGrid1 input[name='ck']").length>0);
                });
            })
        },
        ajax : {
            url : "/sofn-asms-web/subjSupervise/getSubjSuperviseList",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc:function(json){
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                return json.list;
            },
            data : function(params) {
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != ""){
                    params.areaId = $("#selectQueryXian").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == ""){
                    params.areaId = $("#selectQueryShi").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == ""){
                    params.areaId = $("#selectQueryShen").val();
                }
                params.svName = $("input[name='selectQueryName']").val();
            },
            type:"post"
        },
        columns : [
            {
                data : "id",
                width : "4%",
                title : '<input id="checkAllJg" onclick="checkAllJg(this.checked)" type=checkbox>',
                render : function(data, type, row) {
                    for(var i = 0; i <  $scope.selects.length;i++){
                        if ($scope.selects[i] != undefined && data == $scope.selects[i].leadUnitId){
                            return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                }
            },
            {
                data : 'id',
                title : 'ID',
                visible : false
            },
            {
                title : "序号",
                width : "6%",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "svName",
                title : "单位名称",
                width : "35%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>20){
                        return "<a name='name' id='svName"+row.id+"' onmouseover=showPopContent('svName"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                    }else{
                        return '<span name = "name">'+data+'</span>';
                    }
                },
            },
            {
                data : "svType",
                title : "所属行业",
                width:"10%"
            },
            {
                data : "svAddress",
                title : "所属区域",
                width : "25%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>14){
                        return "<a id='svAddress"+row.id+"' onmouseover=showPopContent('svAddress"+row.id+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                    }else{
                        return data;
                    }
                },
            }
        ]

    });
    //监管单位全选,全不选
    window.checkAllJg = function(checked){
        //移除本页全部监管机构
        $("#selectGrid1 input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selects.length ; i++){
                if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                    delete $scope.selects[i];
                }
            }
        })
        //全选本页全部监管机构
        if(checked){
            $("#selectGrid1 input[name='ck']:checked").each(function(i,o) {
                var map = {
                    leadUnitId: $(o).val(),
                    leadUnitName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }

    $("#selectGrid2").dataTable({
        fnDrawCallback: function() {
            $("#checkAllZf").prop("checked",$("#selectGrid2 input[name='ck']").length==$("#selectGrid2 input[name='ck']:checked").length&&$("#selectGrid2 input[name='ck']").length>0);
            //点击行内任意内容即选择行
            $("#selectGrid2 tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[type='checkbox']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        //移除
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0 ; i < $scope.selects.length ; i++){
                            if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                                delete $scope.selects[i];
                            }
                        }
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        //选择记录
                        /**
                         * 使用leadUnitId与leadUnitName字段方便映射
                         * */
                        var map={
                            leadUnitId:$(this).parent().find("[name='ck']").val(),
                            leadUnitName:$(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                    }
                    $("#checkAllZf").prop("checked",$("#selectGrid2 input[name='ck']").length==$("#selectGrid2 input[name='ck']:checked").length&&$("#selectGrid2 input[name='ck']").length>0);
                });
            })
        },
        ajax : {
            url : "/sofn-asms-web/asmsTools/getZfOrgsListByUserToken",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc:function(json){
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                return json.data;
            },
            data : function(params) {
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != ""){
                    params.elAreaId = $("#selectQueryXian").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == ""){
                    params.elAreaId = $("#selectQueryShi").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == ""){
                    params.elAreaId = $("#selectQueryShen").val();
                }
                params.elName = $("input[name='selectQueryName']").val();
            },
            type:"post"
        },
        columns : [
            {
                data : "id",
                width : "4%",
                title : '<input id="checkAllZf" onclick="checkAllZf(this.checked)" type=checkbox>',
                render : function(data, type, row) {
                    for(var i = 0; i <  $scope.selects.length;i++){
                        if ($scope.selects[i] != undefined && data == $scope.selects[i].leadUnitId){
                            return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                }
            },
            {
                data : 'id',
                title : 'ID',
                visible : false
            },
            {
                title : "序号",
                width : "6%",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "elName",
                title : "单位名称",
                width : "35%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>20){
                        return "<a name='name' id='elName"+row.id+"' onmouseover=showPopContent('elName"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                    }else{
                        return '<span name = "name">'+data+'</span>';
                    }
                }
            },
            {
                data : "elType",
                title : "所属行业",
                width : "10%",
            },
            {
                data : "elAddress",
                title : "所属区域",
                width : "25%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>14){
                        return "<a id='svAddress"+row.id+"' onmouseover=showPopContent('svAddress"+row.id+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                    }else{
                        return data;
                    }
                },
            }
        ]
    });
    //执法单位全选,全不选
    window.checkAllZf = function(checked){
        //移除本页全部执法机构
        $("#selectGrid2 input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selects.length ; i++){
                if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                    delete $scope.selects[i];
                }
            }
        })
        //全选本页全部执法机构
        if(checked){
            $("#selectGrid2 input[name='ck']:checked").each(function(i,o) {
                var map = {
                    leadUnitId: $(o).val(),
                    leadUnitName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }
    $("#selectGrid3").dataTable({
        fnDrawCallback: function() {
            $("#checkAllJc").prop("checked",$("#selectGrid3 input[name='ck']").length==$("#selectGrid3 input[name='ck']:checked").length&&$("#selectGrid3 input[name='ck']").length>0);
            //点击行内任意内容即选择行
            $("#selectGrid3 tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[type='checkbox']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        //移除
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0 ; i < $scope.selects.length ; i++){
                            if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                                delete $scope.selects[i];
                            }
                        }
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        //选择记录
                        /**
                         * 使用leadUnitId与leadUnitName字段方便映射
                         * */
                        var map={
                            leadUnitId:$(this).parent().find("[name='ck']").val(),
                            leadUnitName:$(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                    }
                    $("#checkAllJc").prop("checked",$("#selectGrid3 input[name='ck']").length==$("#selectGrid3 input[name='ck']:checked").length&&$("#selectGrid3 input[name='ck']").length>0);
                });
            })
        },
        ajax : {
            url : "/sofn-asms-web/asmsTools/getOrgsListByUserToken",
            headers:{
                token:window.localStorage.getItem("token")
            },
            dataSrc:function(json){
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                return json.data;
            },
            data : function(params) {
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != ""){
                    params.dtAreaId = $("#selectQueryXian").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == ""){
                    params.dtAreaId = $("#selectQueryShi").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == ""){
                    params.dtAreaId = $("#selectQueryShen").val();
                }
                params.power = "limit";
                params.dtName = $("input[name='selectQueryName']").val();
            },
            type:"post"
        },
        columns : [
            {
                data : "id",
                width : "4%",
                title : '<input id="checkAllJc" onclick="checkAllJc(this.checked)" type=checkbox>',
                render : function(data, type, row) {
                    for(var i = 0; i <  $scope.selects.length;i++){
                        if ($scope.selects[i] != undefined && data == $scope.selects[i].leadUnitId){
                            return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                }
            },
            {
                data : 'id',
                title : 'ID',
                visible : false
            },
            {
                title : "序号",
                width : "6%",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "dtName",
                title : "单位名称",
                width : "35%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>20){
                        return "<a name='name' id='dtName"+row.id+"' onmouseover=showPopContent('dtName"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                    }else{
                        return '<span name = "name">'+data+'</span>';
                    }
                },
            },
            {
                data : "dtType",
                title : "所属行业",
                width : "10%",
            },
            {
                data : "dtAddress",
                title : "所属区域",
                width : "25%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>14){
                        return "<a id='svAddress"+row.id+"' onmouseover=showPopContent('svAddress"+row.id+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                    }else{
                        return data;
                    }
                },
            }
        ]
    });
    //监测单位全选,全不选
    window.checkAllJc = function(checked){
        //移除本页全部监测机构
        $("#selectGrid3 input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selects.length ; i++){
                if($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId){
                    delete $scope.selects[i];
                }
            }
        })
        //全选本页全部监测机构
        if(checked){
            $("#selectGrid3 input[name='ck']:checked").each(function(i,o) {
                var map = {
                    leadUnitId: $(o).val(),
                    leadUnitName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }
    //确定
    $scope.selectModalQdButt = function(){
        var name = "";
        for(var i = 0; i < $scope.selects.length ; i++){
            if ($scope.selects[i] != undefined){
                if (name != ""){
                    name+=","+$scope.selects[i].leadUnitName;
                }else {
                    name = $scope.selects[i].leadUnitName;
                }
            }
        }
        $("input[name='bearUnit']").val(name);
        $("#selectGrid1_wrapper").hide();
        $("#selectGrid2_wrapper").hide();
        $("#selectGrid3_wrapper").hide();
        $("#selectModal").modal('hide');
        //SdDateValid.ValidByCode("input","bearUnit");
    };



    //针对复选框无法选中bug
    window.checkSelectThisBox = function(o){
        if(o.checked){
            o.checked = false;
            $(o).removeClass("selected");
        }else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    //查询条件重置
    $scope.cleanSelectModelQuerys = function(){
        //点击间隔
        $scope.btnBearUnitReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnBearUnitReloadDisabled = false);
        }, 500);
        $("input[name='selectQueryName']").val("");
        $("input[name='bearUnit']").val("");
        if(bjArea=="1"){
            $.fn.mycity("selectQueryShen","selectQueryShi","selectQueryXian");
        }else{
            $.fn.mycity("selectQueryShen","selectQueryShi","selectQueryXian",area);
            if ($("#selectQueryShen").val() != null && $("#selectQueryShen").val() != "" && $("#selectQueryShen").val() != undefined) {
                $("#selectQueryShen").attr("disabled", true);
            }
            if ($("#selectQueryShi").val() != null && $("#selectQueryShi").val() != "" && $("#selectQueryShi").val() != undefined) {
                $("#selectQueryShi").attr("disabled", true);
            }
            if ($("#selectQueryXian").val() != null && $("#selectQueryXian").val() != "" && $("#selectQueryXian").val() != undefined) {
                $("#selectQueryXian").attr("disabled", true);
            }
        }
        $("#selectGrid1").dataTable().api().ajax.reload();
        $("#selectGrid2").dataTable().api().ajax.reload();
        $("#selectGrid3").dataTable().api().ajax.reload();
    };

    //清空已选择
    $scope.cleanSelecteds = function(){
        //点击间隔
        $scope.btnBearUnitClearDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnBearUnitClearDisabled = false);
        }, 500);
        $scope.selects  = null;
        $scope.selects  = new Array();
        $('#selectGrid1').dataTable().api().ajax.reload();
        $('#selectGrid2').dataTable().api().ajax.reload();
        $('#selectGrid3').dataTable().api().ajax.reload();
    };
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
});



