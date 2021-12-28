var myapp=angular.module("myapp",[]);
myapp.controller("subjEntList",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
    // var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    // for(var i=0;i< a.length;i++){
    //     if(a[i].text=="生产经营主体信息"){
    //         $scope.iswrite=a[i].authority;
    //         break;
    //     }
    // }
    // a.map(function(item,i){
    //     if(item.text=="临时备案主体"){
    //         $(".subjEntTempList").css("display","inline-block");
    //     }else if(item.text=="备案待审核"){
    //         $(".subjEntRegisterList").css("display","inline-block");
    //     }else if(item.text=="备案变更待审核"){
    //         $(".subjEntChangeList").css("display","inline-block");
    //     }else if(item.text=="备案撤销待审核"){
    //         $(".subjEntRevokeList").css("display","inline-block");
    //     }else if(item.text=="备案注销待审核"){
    //         $(".subjEntCancelList").css("display","inline-block");
    //     }
    // });
    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //备案待审核数量
    $scope.registerToAuditCount = 0;
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.countRegisterToAudit();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        loadGrid();
    }).error(function(){
    });

    //重置区域
    $scope.reloadArea=function(){
        $("#queryForm").form("reset");
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("sheng", "shi", "xian",  $scope.underArea);
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

    $scope.countRegisterToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countRegisterToAudit",
            method:"post",
            data:{area:$scope.getAreaBySelect()}
        }).success(function(data){
            $scope.registerToAuditCount=data.count;
        }).error(function(){
        });
    }
    $scope.countChangeToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countChangeToAudit",
            method:"post",
            data:{area:$scope.getAreaBySelect()}
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
            url:"/sofn-asms-web/subjEnt/countCancelToAudit",
            method:"post",
            data:{area:$scope.getAreaBySelect()}
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
            url:"/sofn-asms-web/subjEnt/countRevokeToAudit",
            method:"post",
            data:{area:$scope.getAreaBySelect()}
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

    //获取行业数据字典
    $scope.industryType=[];
    $http({
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:2,
        method:"post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        $scope.industryType=JSON.parse(data.data);
    })
    //获取主体类别数据字典
    $scope.subjType=[];
    $http({
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:7,
        method:"post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        $scope.subjType=JSON.parse(data.data);
    })
    //获取组织形式数据字典
    $scope.orgMode=[];
    $http({
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:6,
        method:"post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        $scope.orgMode=JSON.parse(data.data);
    })

    //bootstrap表格
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntList",
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
                    params.entityIndustry = $("select[name='entityIndustry']").val();
                    params.entityScale = $("select[name='entityScale']").val();
                    params.entityType = $("select[name='entityType']").val();
                    params.badRecord = $("select[name='badRecord']").val();
                    params.area = $scope.getAreaBySelect();
                    params.enterpriseName = $("input[name='entName']").val();
                },
                type: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "enterpriseName",
                    title: "主体名称"
                },
                {
                    data: "userAccount",
                    title: "管理员账号"
                },
                {
                    data: "userPassword",
                    title: "初始密码"
                },
                {
                    data: "entityTypeName",
                    title: "主体类别"
                },
                {
                    data: "entityScaleName",
                    title: "组织形式"
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data : "approveStatus",
                    title : "主体状态",
                    render:function(data, type, row) {
                        switch (data){
                            case "0":
                                return "注册待审核";
                                break;
                            case "1":
                                return "审核通过";
                                break;
                            case "2":
                                return "注册审核不通过";
                                break;
                            case "3":
                                return "变更待审核";
                                break;
                            case "4":
                                return "注销待审核";
                                break;
                            case "5":
                                return "注销待审核";
                                break;
                            case "6":
                                return "注销通过";
                                break;
                            case "7":
                                return "注销通过";
                                break;
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntView(\'' + data + '\')">查看</a>';
                        butt += '<a style="cursor: pointer" class="Check-report color" onclick="subjEntRevokeApply(\''+row.userAccount+'\')">重置密码</a>';//转义传值
                        return butt;
                    }
                },
            ],
        });
    }

    //重新加载表格
    $scope.querySubjEntList=function(){
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //跳转到生产经营主体备案查看页面
    window.subjEntView=function(data){
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId",data);
        $state.go("index.content.asms/subjEnt/subjEntView", {source:'sys'});
    }
    //跳转到生产经营主体撤销申请页面
    window.subjEntRevokeApply=function(account){
        if (confirm('是否重置密码？')) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-sys-web/user/resetPassword",
                method: "post",
                params: {'account': account}
            }).success(function (data) {
                if (data.httpCode == 200)
                    jBox.tip("重置成功！", 'info');
                else
                    jBox.tip("重置失败！", 'info');
                $("#entGrid").dataTable().api().ajax.reload();
            }).error(function () {
                jBox.tip("重置失败！", 'info');
            });
        }
    }
})