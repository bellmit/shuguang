angular.module("myapp",[]).controller("subjEntTempList",function($scope,$http,$state){
    $scope.allIndustryId="";
//判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for(var i=0;i< a.length;i++){
        if(a[i].text=="临时备案主体"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    a.map(function(item,i){
        if(item.text=="生产经营主体信息"){
            $(".subjEntList").css("display","inline-block");
        }else if(item.text=="备案待审核"){
            $(".subjEntRegisterList").css("display","inline-block");
        }else if(item.text=="备案变更待审核"){
            $(".subjEntChangeList").css("display","inline-block");
        }else if(item.text=="备案撤销待审核"){
            $(".subjEntRevokeList").css("display","inline-block");
        }else if(item.text=="备案注销待审核"){
            $(".subjEntCancelList").css("display","inline-block");
        }else if(item.text=="注销主体激活申请待审核"){
            $(".subjEntActivationList").css("display","inline-block");
        }
    });
    $scope.subjEntTempListQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntTempListQueryParams"));

    //重置区域
    $scope.reloadArea=function(){
        $("select[name='entityIndustry']").find("option:first").prop("selected",true);
        $("select[name='entityScale']").find("option:first").prop("selected",true);
        $("select[name='entityType']").find("option:first").prop("selected",true);
        $("input[name='entName']").val("");
        $("#dateBegin").val("");
        $("#dateEnd").val("");
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

    //备案待审核数量
    $scope.registerToAuditCount = 0;
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;

    //注销主体激活申请待审核数量
    $scope.activationToAuditCount = 0;

    $scope.countActivationToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countActivationToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.activationToAuditCount=data.count;
        }).error(function(data) {
            
        });
    }

    $scope.countRegisterToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countRegisterToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.registerToAuditCount=data.count;
        }).error(function(data) {
            
        });
    }
    $scope.countChangeToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countChangeToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.changeToAuditCount=data.count;
        }).error(function(data) {
            
        });
    }
    $scope.countCancelToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countCancelToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.cancelToAuditCount=data.count;
        }).error(function(data) {
            
        });
    }
    $scope.countRevokeToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countRevokeToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.revokeToAuditCount=data.count;
        }).error(function(data) {
            
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
        //获取主体类别数据字典
        $scope.subjType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['linshizhutileixing'];
        //获取组织形式数据字典
        $scope.orgMode = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['orgMode'];
        //获取行业数据字典
        $scope.industryType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['industryType'];
        //所属区域
        $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['allIndustryId'];
        $scope.reloadArea();
        $scope.countRegisterToAudit();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        $scope.countActivationToAudit();
        loadGrid();
        //参数回显,延时解决参数回显bug
        setTimeout(function(){
            if($scope.subjEntTempListQueryParams){
                $.fn.mycity("sheng", "shi", "xian", $scope.subjEntTempListQueryParams.areaId);
                $("input[name='entName']").val($scope.subjEntTempListQueryParams.entityName);
                $("input[name='dateBegin']").val($scope.subjEntTempListQueryParams.dateBegin);
                $("input[name='dateEnd']").val($scope.subjEntTempListQueryParams.dateEnd);
                $("select[name='entityIndustry']").val($scope.subjEntTempListQueryParams.entityIndustry);
                $("select[name='entityScale']").val($scope.subjEntTempListQueryParams.entityScale);
                $("select[name='entityType']").val($scope.subjEntTempListQueryParams.entityType);
                $scope.entityScale = $scope.subjEntTempListQueryParams.entityScale;
                $scope.entityType = $scope.subjEntTempListQueryParams.entityType;
            }
        },50)
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }


    //bootstrap表格
    //临时生产经营主体列表
    function loadGrid() {
        $("#entTempGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntTempList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
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
                    var subjEntTempListQueryParams = JSON.parse(window.sessionStorage.getItem("subjEntTempListQueryParams"));
                    if(subjEntTempListQueryParams){
                        params.entityIndustry = subjEntTempListQueryParams.entityIndustry;
                        params.entityType = subjEntTempListQueryParams.entityType;
                        params.entityScale = subjEntTempListQueryParams.entityScale;
                        params.areaId = subjEntTempListQueryParams.areaId;
                        params.entityName = subjEntTempListQueryParams.entityName;
                        params.dateBegin = subjEntTempListQueryParams.dateBegin;
                        params.dateEnd = subjEntTempListQueryParams.dateEnd;
                    }else{
                        params.entityIndustry = $scope.allIndustryId;
                        params.entityType = $("select[name='entityType']").val();
                        params.entityScale = $("select[name='entityScale']").val();
                        params.areaId = $scope.getAreaBySelect();
                        params.entityName = $("input[name='entName']").val();
                        params.dateBegin = $("input[name='dateBegin']").val();
                        params.dateEnd = $("input[name='dateEnd']").val();
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
                    data: "entityName",
                    title: "主体名称",
                    width:"28%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>22){
                            return "<a id='entityName"+row.id+"' onmouseover=showPopContent('entityName"+row.id+"','"+data+"')>"+data.substring(0,22)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "entityScale",
                    title: "组织形式",
                    width:"12%"
                },
                {
                    data: "entityIndustry",
                    title: "所属行业",
                    width:"14%"
                },
                {
                    data: "entityType",
                    title: "主体类别",
                    width:"10%"
                },
                {
                    data: "areaId",
                    title: "所属区域",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>14){
                            return "<a id='areaId"+row.id+"' onmouseover=showPopContent('areaId"+row.id+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                    width:"18%"
                },
                {
                    data: "createTime",
                    title: "注册时间",
                    width:"8%"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntTempView(\'' + data + '\')">查看</a>';
                        return butt;
                    }
                },
            ],
        });
    }

    //重新加载表格
    $scope.querySubjEntTempList=function(){

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var subjEntTempListQueryParams = {};
        subjEntTempListQueryParams.entityIndustry = $("select[name='entityIndustry']").val();
        subjEntTempListQueryParams.entityType = $("select[name='entityType']").val();
        subjEntTempListQueryParams.entityScale = $("select[name='entityScale']").val();
        subjEntTempListQueryParams.areaId = $scope.getAreaBySelect();
        subjEntTempListQueryParams.entityName = $("input[name='entName']").val();
        subjEntTempListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        subjEntTempListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        window.sessionStorage.setItem("subjEntTempListQueryParams",JSON.stringify(subjEntTempListQueryParams));
        $("#entTempGrid").dataTable().api().ajax.reload();
    }

    //跳转到临时生产经营主体备案查看页面
    window.subjEntTempView=function(data){
        window.localStorage.setItem("subjEntTempId",data);
        $state.go("index.content.asms/subjEnt/subjEntTempView");
    }
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjEntList,.subjEntTempList,.subjEntChangeList,.subjEntRevokeList,.subjEntRegisterList,.subjEntCancelList,.subjEntSPYBList").each(function(i,o){
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