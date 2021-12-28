var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("emergencyList",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("应急任务");
    //获取缓存查询参数
    $scope.emergencyListQueryParams = JSON.parse(window.sessionStorage.getItem("emergencyListQueryParams"));
    var area = ""
    var bjArea = ""
    var orgId = ""
    $http({
        url:"/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        area = data.data.regionId;
        bjArea= data.data.orgLevel;
        orgId = data.data.orgId;
        if(bjArea=="1"){
            $.fn.mycity("sheng","shi","xian");
        }else{
            $.fn.mycity("sheng","shi","xian",area.substring(0,2));
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
        if($scope.emergencyListQueryParams){
            $("input[name='taskYear']").val($scope.emergencyListQueryParams.taskYear);
            $("#bearUnit").val($scope.emergencyListQueryParams.bearUnit);
            $("input[name='datebegin']").val($scope.emergencyListQueryParams.dateBegin);
            $("input[name='dateend']").val($scope.emergencyListQueryParams.dateEnd);
            $("#queryCon").val($scope.emergencyListQueryParams.queryCon);
            $.fn.mycity("sheng","shi","xian",$scope.emergencyListQueryParams.areaId);
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
        var emergencyListQueryParams = {};
        emergencyListQueryParams.queryCon = $("input[name='queryCon']").val();
        emergencyListQueryParams.taskYear = $("input[name='taskYear']").val();
        emergencyListQueryParams.dateBegin = $("input[name='datebegin']").val();
        emergencyListQueryParams.dateEnd = $("input[name='dateend']").val();
        emergencyListQueryParams.areaId = $("select[name='areaId']").val();
        emergencyListQueryParams.releaseUnit = $("select[name='releaseUnit'] option:selected").val();
        emergencyListQueryParams.bearUnit = $("input[name='bearUnit']").val();
        window.sessionStorage.setItem("emergencyListQueryParams",JSON.stringify(emergencyListQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    //$.fn.mycity("sheng","shi","xian")
//分页
        //bootstrap表格
    function loadGrid() {
        var TaskTime = new Date(new Date().getTime()+ 28800000).toISOString();
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/superviseEmergency/getSuperviseEmergencyList",
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
                        if (json.data.list[i].TASK_BEGIN != null && json.data.list[i].TASK_BEGIN != "" && json.data.list[i].TASK_BEGIN != undefined) {
                            json.data.list[i].TASK_BEGIN = new Date(parseInt(json.data.list[i].TASK_BEGIN)+28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].TASK_END != null && json.data.list[i].TASK_END != "" && json.data.list[i].TASK_END != undefined) {
                            json.data.list[i].TASK_END = new Date(parseInt(json.data.list[i].TASK_END)+28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].CREATE_TIME != null && json.data.list[i].CREATE_TIME != "" && json.data.list[i].CREATE_TIME != undefined) {
                            json.data.list[i].CREATE_TIME = new Date(parseInt(json.data.list[i].CREATE_TIME)+28800000).toISOString().slice(0, 10);
                        }
                        $scope.checkData(json.data.list[i]);
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.xcPsersionId = window.localStorage.getItem("xcPsersionId", "xcPsersionId");
                    params.createOrgId = orgId;
                    var emergencyListQueryParams = JSON.parse(window.sessionStorage.getItem("emergencyListQueryParams"));
                    if(emergencyListQueryParams){
                        params.queryCon = emergencyListQueryParams.queryCon;
                        params.taskYear = emergencyListQueryParams.taskYear;
                        params.dateBegin = emergencyListQueryParams.dateBegin;
                        params.dateEnd = emergencyListQueryParams.dateEnd;
                        params.areaId = emergencyListQueryParams.areaId;
                        params.releaseUnit = emergencyListQueryParams.releaseUnit;
                        params.bearUnit = emergencyListQueryParams.bearUnit;
                    }else{
                        params.queryCon = $("input[name='queryCon']").val();
                        params.taskYear = $("input[name='taskYear']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.areaId = $("select[name='areaId']").val();
                        params.releaseUnit = $("select[name='releaseUnit'] option:selected").val();
                        params.bearUnit = $("input[name='bearUnit']").val();
                    }
                },
                type: "post",
            },
            columns: [
                {
                    data: "RN",
                    title: "序号",
                    width:"4%"
                },
                {
                    data: "TASK_NAME",
                    title: "任务名称",
                    width:"15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>23){
                            return "<a id='TASK_NAME"+row.ID+"' onmouseover=showPopContent('TASK_NAME"+row.ID+"','"+data+"')>"+data.substring(0,23)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "TASK_TYPE",
                    title: "任务类型",
                    width:"6%"
                },
                {
                    data: "AREA_ID",
                    title: "区域",
                    width:"10%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>15){
                            return "<a id='AREA_ID"+row.ID+"' onmouseover=showPopContent('AREA_ID"+row.ID+"','"+data+"')>"+data.substring(0,15)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "RELEASE_UNIT",
                    title: "发布单位",
                    width:"12%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='RELEASE_UNIT"+row.ID+"' onmouseover=showPopContent('RELEASE_UNIT"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "BEAR_UNIT",
                    title: "承担单位",
                    width:"12%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='BEAR_UNIT"+row.ID+"' onmouseover=showPopContent('BEAR_UNIT"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "FILE_CODE",
                    title: "文件号",
                    width:"8%"
                },
                {
                    data: "TASK_BEGIN",
                    title: "开始时间",
                    width:"7%"
                },
                {
                    data: "TASK_END",
                    title: "结束时间",
                    width:"7%"
                },
                {
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width:"7%"
                },
                {
                    data: "ENABLE",
                    title: "任务状态",
                    width:"6%",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.TASK_END < TaskTime ) {
                            return "已完成";
                        } else {
                            return "执行中";
                        }

                    },
                },
                {
                    data: "ID",
                    title: "操作",
                    width:"4%",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt = '<a style="cursor: pointer" class="Check-report" data-toggle="modal" data-target="#myModal" onclick="CheckreportEmergency(\'' + data + '\')">查看</a>';
                        if($scope.iswrite==2){
                            butt +=  '<a style="cursor: pointer" class="Check-report" data-toggle="modal" onclick="updateEmergency(\'' + data + '\')">修改</a>';
                        }
                        return butt;
                    },
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
        $("input[name='dateend']").val("");
        $("input[name='datebegin']").val("");
        $("select[name='releaseUnit']").val("");
        $("input[name='bearUnit']").val("");
        $("input[name='taskYear']").val("");
        if(bjArea=="1"){
            $.fn.mycity("sheng","shi","xian");
        }else{
            $.fn.mycity("sheng","shi","xian",area.substring(0,2));
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
        $scope.selects = [];
        window.sessionStorage.removeItem("emergencyListQueryParams");
        $("#grid").dataTable().api().ajax.reload();
    };
    /**
     * ---------承担单位选择列表--------------
     * */
        //机构类别
    $("#selectTocheck").click(function(){
        //点击间隔
        $scope.btnBearUnitDisabled = true;
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
        loudData()
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
    window.checkClick = function(o){
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
    //承担单位查询条件重置
    $scope.cleanCdModelQuerys = function(){

        //点击间隔
        $scope.btnBearUnitReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnBearUnitReloadDisabled = false);
        }, 500);

        $("input[name='selectQueryName']").val("");
        $("#orgchange").val("1");
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
    window.CheckreportEmergency=function(data) {
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/superviseEmergency/findAsmsEmergencyTaskById",
            method: "post",
            data: {id: data}
        }).success(function (data) {
            $scope.quyTime = new Date();
            $scope.reCord = data.asmsEmergencyTask;
            $scope.areaId= $.fn.Cityname($scope.reCord.areaId);
            $scope.reCord.taskBegin = new Date(parseInt(data.asmsEmergencyTask.taskBegin)+28800000).toISOString().slice(0, 10);
            $scope.reCord.taskEnd = new Date(parseInt(data.asmsEmergencyTask.taskEnd)+28800000).toISOString().slice(0, 10);
            if (new Date($scope.reCord.taskEnd) < $scope.quyTime) {
                $scope.reCord.enable = "已完成";
            } else {
                $scope.reCord.enable = "执行中";
            }
            if(data.asmsEmergencyTask.releaseUnitLevel=="1"){
                $scope.reCord.releaseUnitLevel="部级"
            }
            if(data.asmsEmergencyTask.releaseUnitLevel=="2"){
                $scope.reCord.releaseUnitLevel="省级"
            }
            if(data.asmsEmergencyTask.releaseUnitLevel=="3"){
                $scope.reCord.releaseUnitLevel="市级"
            }
            if(data.asmsEmergencyTask.releaseUnitLevel=="4"){
                $scope.reCord.releaseUnitLevel="县级"
            }
            $scope.checkData($scope.reCord);
        }).
            error(function () {
            });
    }
    window.updateEmergency=function(data) {
        window.localStorage.setItem("id",data);
        window.localStorage.setItem("asms_etl_updateFlag","1");
        $state.go("index.content.asms/emergency/emergencyAdd")
    }
    $scope.emergenAdd = function(){
        window.localStorage.setItem("asms_etl_updateFlag","0");
    }
    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.reCord.files);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.reCord.filesName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
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
    //字段值为空显示为无
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }
});

