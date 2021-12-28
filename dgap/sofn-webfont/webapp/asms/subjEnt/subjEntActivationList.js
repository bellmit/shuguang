var myapp=angular.module("myapp",[]);
myapp.controller("subjEntActivationList",function($scope,$http,$state){
    $scope.allIndustryId="";
//判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for(var i=0;i< a.length;i++){
        if(a[i].text=="备案变更待审核"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    a.map(function(item,i){
        if(item.text=="临时备案主体"){
            $(".subjEntTempList").css("display","inline-block");
        }else if(item.text=="备案待审核"){
            $(".subjEntRegisterList").css("display","inline-block");
        }else if(item.text=="生产经营主体信息"){
            $(".subjEntList").css("display","inline-block");
        }else if(item.text=="备案撤销待审核"){
            $(".subjEntRevokeList").css("display","inline-block");
        }else if(item.text=="备案注销待审核"){
            $(".subjEntCancelList").css("display","inline-block");
        }else if(item.text == "备案变更待审核"){
            $(".subjEntChangeList").css("display","inline-block");
        }
    });
    $scope.subjActivationListQueryParams =  JSON.parse(window.sessionStorage.getItem("subjActivationListQueryParams"));
    $scope.authenticationType = [{"dictName":"绿色食品", "dictValue":"绿色认证"},
        {"dictName":"有机食品", "dictValue":"有机认证"},
        {"dictName":"无公害农产品", "dictValue":"无公害认证"},
        {"dictName":"无", "dictValue":"无"}]
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

    //重置区域
    $scope.reloadArea=function(){
        $("select[name='entityIndustry'] option:first").prop("selected",true);
        $("select[name='entityScale'] option:first").prop("selected",true);
        $("select[name='entityType'] option:first").prop("selected",true);
        $("select[name='spybLicType'] option:first").prop("selected",true);
        $("input[name='dateBegin']").val("");
        $("input[name='dateEnd']").val("");
        $("input[name='entName']").val("");
        //默认待审核
        $("select[name='approveStatus']").val("0");
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
        $scope.subjType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['subjType'];
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
            if($scope.subjActivationListQueryParams){
                $("input[name='entName']").val($scope.subjActivationListQueryParams.enterpriseName);
                $("input[name='dateBegin']").val($scope.subjActivationListQueryParams.dateBegin);
                $("input[name='dateEnd']").val($scope.subjActivationListQueryParams.dateEnd);
                $("select[name='approveStatus']").val($scope.subjActivationListQueryParams.approveStatus);
                $("select[name='entityIndustry']").val($scope.subjActivationListQueryParams.entityIndustry);
                $("select[name='entityScale']").val($scope.subjActivationListQueryParams.entityScale);
                $("select[name='entityType']").val($scope.subjActivationListQueryParams.entityType);
                $("select[name='spybLicType']").val($scope.subjActivationListQueryParams.spybLicType);
                $.fn.mycity("sheng", "shi", "xian",  $scope.subjActivationListQueryParams.area);
                $scope.entityScale = $scope.subjActivationListQueryParams.entityScale;
                $scope.entityType = $scope.subjActivationListQueryParams.entityType;
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
    //生产经营主体变更申请列表
    function loadGrid() {
        $("#entChangeGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntActivationList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;

                    return json.data.list;
                },
                data: function (params) {

                        params.entityIndustry = $("select[name='entityIndustry']").val() || $scope.allIndustryId;
                        params.entityType = $("select[name='entityType']").val();
                        params.entityScale = $("select[name='entityScale']").val();
                        params.area = $scope.getAreaBySelect();
                        params.enterpriseName = $("input[name='entName']").val();
                        params.dateBegin = $("input[name='dateBegin']").val();
                        params.dateEnd = $("input[name='dateEnd']").val();
                        params.approveStatus = $("select[name='approveStatus']").val();
                        params.spybLicType = $("select[name='spybLicType']").val();

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
                    data: "ttsScltxxcjRegiter.enterpriseName",
                    title: "主体名称",
                    width:"24%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='ENTERPRISE_NAME"+row.ID+"' onmouseover=showPopContent('ENTERPRISE_NAME"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "ttsScltxxcjRegiter.entityTypeName",
                    title: "主体类别",
                    width:"8%"
                },
                {
                    data: "ttsScltxxcjRegiter.entityIndustryName",
                    title: "所属行业",
                    width:"12%"
                },
                {
                    data: "ttsScltxxcjRegiter.entityScaleName",
                    title: "组织形式",
                    width:"10%"
                },
                {
                    data: "ttsScltxxcjRegiter.spybLicType",
                    title: "认证类型",
                    width:"10%",
                    render: function (data) {
                        return data==null?"无":data;

                    }
                },
                {
                    data: "ttsScltxxcjRegiter.area",
                    title: "所属区域",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>11){
                            return "<a id='AREA"+row.ID+"' onmouseover=showPopContent('AREA"+row.ID+"','"+data+"')>"+data.substring(0,11)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                    width:"14%"
                },
                {
                    data: "createTime",
                    title: "申请时间",
                    width:"7%",
                    render: function (data) {
                        if (data) {
                            return new Date(parseInt(data)+28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data: "status",
                    title: "审核状态",
                    render:function (data, type, row) {
                        if (data == "1") {
                            return "已通过";
                        } else if (data == "2") {
                            return "未通过";
                        } else {
                            return "待审核";
                        }
                    },
                    width:"6%"
                },
                {
                    data: "id",
                    title: "操作",
                    visible: $scope.iswrite==2,
                    render: function (data, type, row) { // 模板化列显示内容
                        if($scope.iswrite == 2){
                            var butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntActivationAudit(\'' + data + '\')">';
                            if(row.status != "0"){
                                butt += "查看";
                            }else {
                                butt += "审核";
                            }
                            butt += '</a>';
                            return butt;
                        }
                    }
                }
            ]
        });
    }

    //重新加载表格
    $scope.querySubjEntActivationList=function(){

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        /*var subjActivationListQueryParams = {};
        subjActivationListQueryParams.approveStatus=$("select[name='approveStatus']").val();
        subjActivationListQueryParams.token=window.localStorage.getItem("token");
        subjActivationListQueryParams.entityIndustry = $("select[name='entityIndustry']").val();
        subjActivationListQueryParams.entityScale = $("select[name='entityScale']").val();
        subjActivationListQueryParams.entityType = $("select[name='entityType']").val();
        subjActivationListQueryParams.area = $scope.getAreaBySelect();
        subjActivationListQueryParams.enterpriseName = $("input[name='entName']").val();
        subjActivationListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        subjActivationListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        subjActivationListQueryParams.spybLicType = $("select[name='spybLicType']").val();
        window.sessionStorage.setItem("subjActivationListQueryParams",JSON.stringify(subjActivationListQueryParams));*/
        $("#entChangeGrid").dataTable().api().ajax.reload();
    }

    //跳转到监管机构主体备案查看页面
    window.subjEntActivationAudit=function(data){
        if($scope.iswrite!="2"){
            jBox.tip("对不起！您没有此操作权限", 'info');
            return false;
        }
        window.localStorage.setItem("subjEntActivationId",data);
        $state.go("index.content.asms/subjEnt/subjEntActivationAudit");
    }
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjEntList,.subjEntTempList,.subjEntChangeList,.subjEntRevokeList,.subjEntRegisterList,.subjEntCancelList,.subjEntSPYBList, .subjEntActivationList").each(function(i,o){
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