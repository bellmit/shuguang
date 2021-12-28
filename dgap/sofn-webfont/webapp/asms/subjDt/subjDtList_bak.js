
var myapp=angular.module("myapp",[]);
myapp.controller("subjDtList",function($scope,$http,$state){
//判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for(var i=0;i< a.length;i++){
        if(a[i].text=="检测机构主体信息"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    if($scope.iswrite!="2"){
        $(".addSubjSv").css("display","none");
        $(".intoSubjSv").css("display","none");
    }
    a.map(function(item,i){
        if(item.text=="备案变更待审核"){
            $(".subjDtChangeList").css("display","inline-block");
        }else if(item.text=="备案注销待审核"){
            $(".subjDtCancelList").css("display","inline-block");
        }else if(item.text=="备案撤销待审核"){
            $(".subjDtRevokeList").css("display","inline-block");
        }
    });
    $scope.subjDtListQueryParams = JSON.parse(window.sessionStorage.getItem("subjDtListQueryParams"));
    //获取用户信息
    $scope.user = {};
    //用户类型  根据用户类型限制是否有撤销权限功能
    var userType="";
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
        $("#status").val("0");
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
            url:"/sofn-asms-web/subjDetection/countChangeToAudit",
            method:"post",
            data:{dtAreaId:$scope.getAreaBySelect()}
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
            url:"/sofn-asms-web/subjDetection/countCancelToAudit",
            method:"post",
            data:{dtAreaId:$scope.getAreaBySelect()}
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
            url:"/sofn-asms-web/subjDetection/countRevokeToAudit",
            method:"post",
            data:{dtAreaId:$scope.getAreaBySelect()}
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
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        if($scope.subjDtListQueryParams){
            $("#dateBegin").val($scope.subjDtListQueryParams.dateBegin);
            $("#dateEnd").val($scope.subjDtListQueryParams.dateEnd);
            $("#status").val($scope.subjDtListQueryParams.status);
            $("input[name='dtName']").val($scope.subjDtListQueryParams.dtName);
            $.fn.mycity("sheng", "shi", "xian", $scope.subjDtListQueryParams.dtAreaId);
        }
        loadGrid();
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }


    //批量导入检测机构主体
    $scope.importSubjDt=function(){
        $("#save").attr("disabled",true);
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('file', file);
        $http({
            method:'POST',
            url:"/sofn-asms-web/subjDetection/importSubjDetection",
            data: fd,
            headers: {
                token:window.localStorage.getItem("token"),
                'Content-Type':undefined
            },
            transformRequest: angular.identity
        }).success( function ( data )
        {
            $("#save").attr("disabled", false);
            if(data.httpCode=="200") {
                //上传成功的操作
                jBox.tip("上传成功","info");
                $("#importModal").modal("hide");
                $("#dtGrid").dataTable().api().ajax.reload();
            }else{
                jBox.tip(data.msg,"info");
            }
        });
    }

    //模态框显示
    $("#importSubjDt").click(function(){
        $("#importModal").modal("show");
    });

    //bootstrap表格
    //检测机构主体信息
    function loadGrid() {
        $("#dtGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjDetection/getSubjDetectionList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime)+28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    var subjDtListQueryParams = JSON.parse(window.sessionStorage.getItem("subjDtListQueryParams"));
                    if(subjDtListQueryParams){
                        params.dtAreaId = subjDtListQueryParams.dtAreaId;
                        params.dtName = subjDtListQueryParams.dtName;
                        params.dateBegin = subjDtListQueryParams.dateBegin;
                        params.dateEnd = subjDtListQueryParams.dateEnd;
                        params.status = subjDtListQueryParams.status;
                    }else{
                        params.dtAreaId = $scope.getAreaBySelect();
                        params.dtName = $("input[name='dtName']").val();
                        params.dateBegin = $("input[name='dateBegin']").val();
                        params.dateEnd = $("input[name='dateEnd']").val();
                        params.status = $("#status").val();
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"

                },
                {
                    data: "dtName",
                    title: "机构名称",
                    width:"25%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>22){
                            return "<a id='dtName"+row.id+"' onmouseover=showPopContent('dtName"+row.id+"','"+data+"')>"+data.substring(0,22)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "dtType",
                    title: "机构类别",
                    width:"10%"
                },
                {
                    data: "dtLevel",
                    title: "机构级别",
                    render:function(data, type, row) {
                        if(data=="3"){
                            return "县级";
                        }else if(data=="2"){
                            return "市级";
                        }else if(data=="1"){
                            return "省级";
                        }else if(data=="0"){
                            return "部级";
                        }
                    },
                    width:"10%"
                },
                {
                    data: "dtAreaId",
                    title: "所属区域",
                    width:"18%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>14){
                            return "<a id='dtAreaId"+row.id+"' onmouseover=showPopContent('dtAreaId"+row.id+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "dtLeader",
                    title: "机构负责人",
                    width:"10%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>7){
                            return "<a id='dtLeader"+row.id+"' onmouseover=showPopContent('dtLeader"+row.id+"','"+data+"')>"+data.substring(0,7)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "createTime",
                    title: "注册时间"
                },
                {
                    data: "status",
                    title: "状态",
                    render: function(data,type,row){
                        if(data == '0'){
                            return "正常";
                        }
                        else if(data == '1'){
                            return "待变更";
                        }
                        else if(data == '2'){
                            return "待注销";
                        }
                        else if(data == '3'){
                            return "待撤销";
                        }
                        else if(data == '4'){
                            return "已注销";
                        }
                        else if(data == '5'){
                            return "已撤销";
                        }
                        else{
                            return "正常";
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjDtView(\'' + data + '\')">查看</a>';
                        if($scope.iswrite==2){
                            butt += '<a style="cursor: pointer" class="Check-report color" onclick="subjDtRevokeApply(\'' + row.status + '\',\'' + data + '\',\'' + row.dtLevel + '\')">撤销</a>';//转义传值
                        }
                        return butt;
                    }
                },
            ]
        });
    }

    //重新加载列表
    $scope.querySubjDtList=function(){
        var subjDtListQueryParams = {};
        subjDtListQueryParams.dtAreaId = $scope.getAreaBySelect();
        subjDtListQueryParams.dtName = $("input[name='dtName']").val();
        subjDtListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        subjDtListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        subjDtListQueryParams.status = $("#status").val();
        window.sessionStorage.setItem("subjDtListQueryParams",JSON.stringify(subjDtListQueryParams));
        $("#dtGrid").dataTable().api().ajax.reload();
    }

    //跳转到主体备案查看页面
    window.subjDtView=function(data){
        window.localStorage.removeItem("subjDtId");
        window.localStorage.setItem("subjDtId",data);
        $state.go("index.content.asms/subjDt/subjDtView");
    }
    //跳转到主体撤销申请页面
    window.subjDtRevokeApply=function(status,data,dtLevel){
        if($scope.iswrite!="2"){
            jBox.tip("对不起！您没有此操作权限", 'info');
            return false;
        }
        if(($scope.organization.orgLevel-1)!=dtLevel){
            jBox.tip("只能撤销同级检测机构！", 'info');
            return false;
        }
        if(status=="1"){
            jBox.tip("还有变更申请未处理！", 'info');
            return false;
        }else if(status=="2"){
            jBox.tip("还有注销申请未处理！", 'info');
            return false;
        }else if(status=="3"){
            jBox.tip("还有撤销申请未处理！", 'info');
            return false;
        }else if(status=="0"){
            window.localStorage.removeItem("subjDtId");
            window.localStorage.setItem("subjDtId",data);
            $state.go("index.content.asms/subjDt/subjDtRevokeApply");
        }
    }
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjDtList,.subjDtChangeList,.subjDtCancelList,.subjDtRevokeList").each(function(i,o){
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