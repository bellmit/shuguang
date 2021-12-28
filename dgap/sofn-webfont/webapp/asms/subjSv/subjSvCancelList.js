var myapp=angular.module("myapp",[]);
myapp.controller("subjSvCancelList",function($scope,$http,$state){

    //判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for(var i=0;i< a.length;i++){
        if(a[i].text=="备案注销待审核"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    a.map(function(item,i){
        if(item.text=="监管机构主体信息"){
            $(".subjSvList").css("display","inline-block");
        }else if(item.text=="备案撤销待审核"){
            $(".subjSvRevokeList").css("display","inline-block");
        }else if(item.text=="备案变更待审核"){
            $(".subjSvChangeList").css("display","inline-block");
        }
    });
    $scope.subjSvCancelListQueryParams = JSON.parse(window.sessionStorage.getItem("subjSvCancelListQueryParams"));
    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    //重置区域
    $scope.reloadArea=function(){
        $("#queryForm").form("reset");
        //默认待审核
        $("select[name='auditState']").val("0");
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sheng", "shi", "xian");
        }
    }

    $scope.countChangeToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/countChangeToAudit",
            method:"post",
            data:{svAreaId:$scope.getAreaBySelect()}
        }).success(function(data){
            $scope.changeToAuditCount=data.count;
        }).error(function(){
        });
    }
    $scope.countCancelToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/countCancelToAudit",
            method:"post",
            data:{svAreaId:$scope.getAreaBySelect()}
        }).success(function(data){
            $scope.cancelToAuditCount=data.count;
        }).error(function(){
        });
    }
    $scope.countRevokeToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/countRevokeToAudit",
            method:"post",
            data:{svAreaId:$scope.getAreaBySelect()}
        }).success(function(data){
            $scope.revokeToAuditCount=data.count;
        }).error(function(){
        });
    }
    $scope.getAreaBySelect=function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (province != null && province != undefined && province != "") {
            areaId = province;
        }
        return areaId;
    }
    //从缓存中获取数据
    if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
        //获取用户信息
        $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
        $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        //初始化区域下拉框初始选项
        $("select[name='auditState']").val("0");
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sheng", "shi", "xian");
        }
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        if($scope.subjSvCancelListQueryParams){
            $("input[name='date']").val($scope.subjSvCancelListQueryParams.date);
            $("input[name='svName']").val($scope.subjSvCancelListQueryParams.svName);
            $("select[name='auditState']").val($scope.subjSvCancelListQueryParams.auditState);
            $.fn.mycity("sheng", "shi", "xian",$scope.subjSvCancelListQueryParams.areaId);
        }
        loadGrid();
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }

    //监管机构主体注销待审核列表
    function loadGrid() {
        $("#svCancelGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjSupervise/getSubjSvCancelList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].APPLYTIME != null && json.list[i].APPLYTIME != "" && json.list[i].APPLYTIME != undefined) {
                            json.list[i].APPLYTIME = new Date(parseInt(json.list[i].APPLYTIME)+28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    var subjSvCancelListQueryParams = JSON.parse(window.sessionStorage.getItem("subjSvCancelListQueryParams"));
                    if(subjSvCancelListQueryParams){
                        params.svName =subjSvCancelListQueryParams.svName;
                        params.date = subjSvCancelListQueryParams.date;
                        params.areaId = subjSvCancelListQueryParams.areaId;
                        params.auditState = subjSvCancelListQueryParams.auditState;
                    }else{
                        params.svName = $("input[name='svName']").val();
                        params.date = $("input[name='date']").val();
                        params.areaId = $scope.getAreaBySelect();
                        params.auditState = $("select[name='auditState']").val();

                    }

                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"

                },
                {
                    data: "SVNAME",
                    title: "机构名称",
                    width:"28%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>22){
                            return "<a id='SVNAME"+row.ID+"' onmouseover=showPopContent('SVNAME"+row.ID+"','"+data+"')>"+data.substring(0,22)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "SVAREAID",
                    title: "所属区域",
                    width:"20%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>15){
                            return "<a id='SVAREAID"+row.ID+"' onmouseover=showPopContent('SVAREAID"+row.ID+"','"+data+"')>"+data.substring(0,15)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "APPLYTIME",
                    title: "申请时间",
                    width:"14%"
                },
                {
                    data: "SVLEADER",
                    title: "负责人",
                    width:"14%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>9){
                            return "<a id='svLeader"+row.ID+"' onmouseover=showPopContent('svLeader"+row.ID+"','"+data+"')>"+data.substring(0,9)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "AUDITSTATE",
                    title: "审核状态",
                    render:function(data, type, row){
                        if (row.AUDITSTATE == "0") {
                            return '<span>待审核</span>';
                        } else if (row.AUDITSTATE == "1") {
                            return '<span>已通过</span>';
                        } else if (row.AUDITSTATE == "2") {
                            return '<span>未通过</span>';
                        }
                    },
                    width:"12%"
                },
                {
                    data: "ID",
                    title: "操作",
                    visible: $scope.iswrite==2,
                    render: function (data, type, row) { // 模板化列显示内容
                        if($scope.iswrite == 2){
                            if (row.AUDITSTATE != "0") {
                                return '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjSvCancelAuditShow(\'' + data + '\')">查看</a>';//转义传值
                            } else {
                                return '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjSvCancelAudit(\'' + data + '\')">审核</a>';//转义传值
                            }
                        }
                    }
                },
            ],
        });
    }

    //重新加载表格
    $scope.querySubjSvCancel=function(){

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var subjSvCancelListQueryParams = {};
        subjSvCancelListQueryParams.svName = $("input[name='svName']").val();
        subjSvCancelListQueryParams.date = $("input[name='date']").val();
        subjSvCancelListQueryParams.areaId = $scope.getAreaBySelect();
        subjSvCancelListQueryParams.auditState = $("select[name='auditState']").val();
        window.sessionStorage.setItem("subjSvCancelListQueryParams",JSON.stringify(subjSvCancelListQueryParams));
        $("#svCancelGrid").dataTable().api().ajax.reload();
    }

    //跳转到注销审核页面
    window.subjSvCancelAuditShow=function(data){
        window.localStorage.setItem("subjSvCancelId",data);
        $state.go("index.content.asms/subjSv/subjSvCancelAudit");
    }

    //跳转到注销审核页面
    window.subjSvCancelAudit=function(data){
        if($scope.iswrite!="2"){
            jBox.tip("对不起！您没有此操作权限", 'info');
            return false;
        }
        window.localStorage.setItem("subjSvCancelId",data);
        $state.go("index.content.asms/subjSv/subjSvCancelAudit");
    }
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjSvList,.subjSvChangeList,.subjSvCancelList,.subjSvRevokeList").each(function(i,o){
        $(o).click(function(){
            window.sessionStorage.clear();
        })
    })
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
})