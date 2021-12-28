var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("seSysResourcelist",function($scope,$http,$state){
    $scope.comComplainQueryParams = JSON.parse(window.sessionStorage.getItem("comComplainQueryParams"));
    var area = ""
    var bjArea =""
    $http({
        url:"/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        area = data.data.regionId;
        var areaId = area.substring(0,2)
        bjArea= data.data.orgLevel;
        if(bjArea=="1"){
            $.fn.mycity("sheng","shi","xian");
        }else{
            $.fn.mycity("sheng","shi","xian",areaId);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        }
        if($scope.comComplainQueryParams){
            $("select[name='professionalfiled']").val($scope.comComplainQueryParams.professionalfiled);
            $("select[name='location']").val($scope.comComplainQueryParams.location);
            $("input[name='queryCon']").val($scope.comComplainQueryParams.queryCon);
        }
        loadGrid();

    }).error(function (data) {
        
    });

    //点击查询按钮刷新datatable
    $("#ToCheck").click(function(){
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var comComplainQueryParams = {};
        comComplainQueryParams.professionalfiled = $("select[name='professionalfiled']").val();
        comComplainQueryParams.location = $("select[name='location']").val();
        comComplainQueryParams.queryCon = $("input[name='queryCon']").val();
        window.sessionStorage.setItem("comComplainQueryParams",JSON.stringify(comComplainQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    //获取投诉主题(职级)数据字典
    $scope.jobTitles = [];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"3",
        method:"post"
    }).success(function(data) {
        $scope.jobTitles=JSON.parse(data.data);
    }).error(function (data) {
        
    });
//分页
        //bootstrap表格
    function loadGrid() {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/sysResource/getSysResourceList",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
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
                        };
                        if (json.data.list[i].createTime != null && json.data.list[i].createTime != "" && json.data.list[i].createTime != undefined) {
                            json.data.list[i].createTime = new Date(parseInt(json.data.list[i].createTime)+28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                //传入后台的参数（点击查询 前台页面传入后台的参数）
                data: function (params) {
                    params.xcPsersionId = window.localStorage.getItem("xcPsersionId", "xcPsersionId");
                    var comComplainQueryParams = JSON.parse(window.sessionStorage.getItem("comComplainQueryParams"));
                    if(comComplainQueryParams){
                        params.professionalfiled = comComplainQueryParams.professionalfiled;
                        params.location  =comComplainQueryParams.location;
                        params.queryCon = comComplainQueryParams.queryCon;
                    }else{
                        params.professionalfiled = $("select[name='professionalfiled']").val();
                        params.location = $("select[name='location']").val();
                        params.queryCon = $("input[name='queryCon']").val();
                    }
                },
                type: "POST"
            },
            columns: [
                //页面展示头信息
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"
                },
                {
                    data: "resourcetype",
                    title: "资源类型",
                    width:"6%"
                },
                {
                    data: "professionalfiled",
                    title: "专业领域",
                    width:"6%"
                },
                {
                    data: "name",
                    title: "姓名",
                    width:"8%"
                },
                {
                    data: "jobtitle",
                    title: "职称",
                    width:"10%",
                    render: function (data, type, row) {
                        for (var j = 0; j < $scope.jobTitles.length; j++){
                            if($scope.jobTitles[j].dictValue == data){
                                data = $scope.jobTitles[j].dictName
                                    if(data!=null&&data!=undefined&&data.length>6){
                                        return "<a id='enterpriseName"+row.id+"' onmouseover=showPopContent('enterpriseName"+row.id+"','"+data+"')>"+data.substring(0,6)+"...</a>";
                                    }else{
                                        return data;
                                    }
                            }
                        }
                        return data;
                    }
                },
                {
                    data: "location",
                    title: "所在地",
                    width:"15%",
                    render:function(data,type,row){
                        if(data){
                            data = $.fn.Cityname(data);
                        }
                        if(data!=null&&data!=undefined&&data.length>11){
                            return "<a id='location"+row.id+"' onmouseover=showPopContent('location"+row.id+"','"+data+"')>"+data.substring(0,11)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "unit",
                    title: "单位",
                    width:"15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>11){
                            return "<a id='unit"+row.id+"' onmouseover=showPopContent('unit"+row.id+"','"+data+"')>"+data.substring(0,11)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "address",
                    title: "详细地址",
                    width:"15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>11){
                            return "<a id='address"+row.id+"' onmouseover=showPopContent('address"+row.id+"','"+data+"')>"+data.substring(0,11)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "contract",
                    title: "联系方式",
                    width:"9%"
                },
                {
                    data: "createTime",
                    title: "创建时间",
                    width:"8%"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#myModal" onclick="CheckreportResource(\'' + data + '\')">查看</a>';//转义传值
                    }
                },
            ],
        });
    }
    $scope.cleanSelectModelQuery = function(){
        //点击间隔
        $scope.btnReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnReloadDisabled = false);
        }, 500);
        $("input[name='queryCon']").val("");
        $("select[name='professionalfiled']").val("");
        $.fn.mycity("sheng","shi","xian",area.substring(0,2));
        $("#grid").dataTable().api().ajax.reload();
    };
        //查看详情页面
    window.CheckreportResource=function(data){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/sysResource/getSysResourceById",
            method:"post",
            data:{id:data}
        }).success(function(data){
            $scope.reCord=data.sysResource;
            $scope.location = $.fn.Cityname($scope.reCord.location);
            if ($scope.reCord.resourcetype != null && $scope.reCord.resourcetype != "" && $scope.reCord.resourcetype != undefined) {
                if($scope.reCord.resourcetype=="1"){
                    $scope.reCord.resourcetype="专家";
                }
            }
            if ($scope.reCord.professionalfiled != null && $scope.reCord.professionalfiled != "" && $scope.reCord.professionalfiled != undefined) {
                if($scope.reCord.professionalfiled=="1"){
                    $scope.reCord.professionalfiled="检测";
                }
                if($scope.reCord.professionalfiled=="2"){
                    $scope.reCord.professionalfiled="农药";
                }
                if($scope.reCord.professionalfiled=="3"){
                    $scope.reCord.professionalfiled="检测检疫"
                }
            }
            for (var j = 0; j < $scope.jobTitles.length; j++){
                if($scope.jobTitles[j].dictValue == $scope.reCord.jobtitle){
                    $scope.reCord.jobtitle=$scope.jobTitles[j].dictName;
                }
            }
            $scope.reCord.status = $scope.reCord.status=='1'?'正常':'禁用';
            if($scope.reCord.resourcetype=="1"){
                $scope.reCord.resourcetype="专家";
            }
        }).
            error(function(){

            });

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

});

