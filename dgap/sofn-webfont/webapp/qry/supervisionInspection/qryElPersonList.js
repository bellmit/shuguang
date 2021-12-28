var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("qryElPersonList",function($scope,$http,$state,$timeout){
    var vm = this;
    $http({
        url:"/sofn-qry-web/subject/findCurrentUserByToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        loadGrid();
    }).error(function(data){
        
    })
    //获取机构类别数据字典
    $scope.orgLevel=[];
    $http.post("/sofn-qry-web/sysDict/getSysDictByCode","1",{
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).then(function (res) {
        $scope.orgLevel=JSON.parse(res.data.data);
    },function (res) {
        
    })

    //获取职务数据字典
    $scope.userPost=[];
    $http.post("/sofn-qry-web/sysDict/getSysDictByCode","3",{
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).then(function (res) {
        $scope.userPost=JSON.parse(res.data.data);
    },function (res) {
        
    })

    //重置区域
    $scope.reloadArea=function(){
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

    //点击查询按钮刷新datatable
    $scope.queryEnfPerson = function(){
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid").dataTable().api().ajax.reload();
    };

    //分页
    //bootstrap表格
    function loadGrid() {
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-qry-web/supervisionInspection/getEnforceLawPersonList_v2",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc:function(json){
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    return json.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data : function(params) {
                    params.token=window.localStorage.getItem("token");
                    params.areaId = $scope.getAreaBySelect();
                    params.levelId = $("select[name='level']").val();
                    params.userPostId=$("select[name='userPostId']").val();
                    params.userName=$("input[name='userName']").val();
                    params.phone=$("input[name='phone']").val();
                    params.elName=$("input[name='elName']").val();

                    $scope.chartParams = params; //图标参数赋值
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }
                },
                type:"POST",
            },
            columns : [
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"
                },
                {
                    data : "userName",
                    title : "姓名",
                    width:"20%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>16){
                            return "<a id='userName"+row.id+"' onmouseover=showPopContent('userName"+row.id+"','"+data+"')>"+data.substring(0,16)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "userPost",
                    title : "职务",
                    width:"12%",
                    render:function(data,type,row){
                        if(!data){
                            return "无";
                        }
                        if(data.length>9){
                            return "<a id='userPost"+row.id+"' onmouseover=showPopContent('userPost"+row.id+"','"+data+"')>"+data.substring(0,9)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "userPhone",
                    title: "电话",
                    width:"10%",
                    render:function(data,type,row){
                        if(!data){
                            return "无";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "name",
                    title : "所属单位",
                    width:"25%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='name"+row.id+"' onmouseover=showPopContent('name"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "area",
                    title : "行政区域",
                    width:"25%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='area"+row.id+"' onmouseover=showPopContent('area"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "id",
                    title : "操作",
                    render : function(data, type, row) { // 模板化列显示内容
                        return '<a type="button" data-toggle="modal" data-target="#myModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="viewDetail(\''+data+'\')">查看</a>';//转义传值
                    },
                },
            ],
        });
    }

    //数据导出
    $scope.exportData=function(){
        //提交数据
        var token=window.localStorage.getItem("token");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'token');
        input1.attr('value', token);

        var areaId = $scope.getAreaBySelect();
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'areaId');
        input2.attr('value', areaId);

        var levelId = $("select[name='level']").val();
        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'levelId');
        input3.attr('value', levelId);

        var userPostId=$("select[name='userPostId']").val();
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'userPostId');
        input4.attr('value', userPostId);

        var userName=$("input[name='userName']").val();
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'userName');
        input5.attr('value', userName);

        var phone=$("input[name='phone']").val();
        var input6 = $('<input>');
        input6.attr('type', 'hidden');
        input6.attr('name', 'phone');
        input6.attr('value', phone);

        var elName=$("input[name='elName']").val();
        var input7 = $('<input>');
        input7.attr('type', 'hidden');
        input7.attr('name', 'elName');
        input7.attr('value', elName);
        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "orderBy");
        input8.attr("value", $scope.chartParams.orderBy);

        //form
        var form=$("<form>");
        form.attr('style','display:none');
        form.attr('target','');
        form.attr('method','post');
        form.attr('action',"/sofn-qry-web/supervisionInspection/exportELPList");
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.append(input8);
        form.submit();
    }


    $scope.cleanSelectModelQuery = function(){
        $("select[name='level']").val("");
        $("select[name='userPostId']").val("");
        $("input[name='userName']").val("");
        $("input[name='phone']").val("");
        $("input[name='elName']").val("");
        $scope.reloadArea();
    };

    //查看详情页面
    window.viewDetail=function(data){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/supervisionInspection/findEnforceLawPersonById",
            method:"post",
            data:{id:data}
        }).success(function(data){
            if(data.httpCode=='200'){
                $scope.enforceLawUser = data.data;
                $("#enforceLawPersonModal").modal("show");
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(data){
            
        })
    }
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
    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-qry-web/supervisionInspection/getEnforceLawPersonStatis",
        grid: [1,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});

