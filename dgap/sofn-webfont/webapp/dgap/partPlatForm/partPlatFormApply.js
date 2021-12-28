var myapp = angular.module("myapp", []);
myapp.controller("partPlatFormApply", function ($scope, $http, $state) {
    //预先声明变量
    $scope.partPlatFormApply = {};
    var area = "";
    $scope.updateFlag="";
    //获取当前登录用户机构信息，并完成初始化
    $http({
        url:"/sofn-dgap-web/localPlatForm/getOrgbyToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        $scope.subjLevel= data.data.orgLevel;//主体级别
        $scope.jurisdictionArea = data.data.regionId.substr(0,2*($scope.subjLevel-1));//管辖区域
        if($scope.jurisdictionArea==null||$scope.jurisdictionArea==""){
            $scope.jurisdictionArea=data.data.regionId;
        }
        // //限定查询区域
        $.fn.mycity("ObjShen","ObjShi","ObjXian",$scope.jurisdictionArea);
        $("select[name='ObjShen']").val("");
        $("select[name='ObjShi']").val("");
        $("select[name='ObjXian']").val("");
        $("select[name='ObjShen']").prop("disabled",false);
        $("select[name='ObjShi']").prop("disabled",false);
        $("select[name='ObjXian']").prop("disabled",false);

    }).error(function(){
    });

    /*新增表单认证*/
    $('#attributeForm').bootstrapValidator();//必须有
    $(".addRes").click(function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
//           提交执行函数全写在这下面
        add();
    });

    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    /* 清除查询框内容*/
    $scope.cleanData = function () {
        $("input[name='dateBegin']").val("");
        $("input[name='dateEnd']").val("");
        $("select[name='status']").val("");
        $("select[name='enableStatus']").val("");
        $("#reDir").dataTable().api().ajax.reload();
    };



    //模态框显示

    //bootstrap表格
    $("#reDir").dataTable({
        processing: true, // 加载时提示
        serverSide: true, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        //paging:true,
        //bInfo:true,
        //bJQueryUI: true,
        //bPaginate: false,// 分页按钮
        //bFilter: true,// 搜索栏
        //iDisplayLength: 10,// 每页显示行数
        //bSort: false,// 排序
        bAutoWidth: false,
        //sScrollY: "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        ajax: {
            url: "/sofn-dgap-web/localPlatForm/list",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].PLATFORM_LEVEL != null && json.data.list[i].PLATFORM_LEVEL != "" && json.data.list[i].PLATFORM_LEVEL != undefined) {
                        json.data.list[i].PLATFORM_LEVEL =toStringShow(json.data.list[i].PLATFORM_LEVEL);
                    }
                    if (json.data.list[i].STATUS != null && json.data.list[i].STATUS != "" && json.data.list[i].STATUS != undefined) {
                        json.data.list[i].STATUS =toStringShow(json.data.list[i].STATUS);
                    }
                    if (json.data.list[i].ENABLE_STATUS != null && json.data.list[i].ENABLE_STATUS != "" && json.data.list[i].ENABLE_STATUS != undefined) {
                        json.data.list[i].ENABLE_STATUS =toStringShow(json.data.list[i].ENABLE_STATUS);
                    }
                }
                return json.data.list;
            },
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.dateBegin = $("input[name='dateBegin']").val();
                params.dateEnd = $("input[name='dateEnd']").val();
                params.status = $("select[name='status']").val();//状态
                params.enableStatus=$("select[name='enableStatus']").val();//启用状态
            },
            type: "post"
        },
        columns: [
            {
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    var unEnable = row.flagStatus;
                    return '<input onclick="unCheckAll(\'' + row.ID + '\')"  type="checkbox"' + (unEnable == "disEnable" ? "disabled" : "") + ' ' + (unEnable == "disEnable" ? "" : "name=\"ck\"") + '/>';
                },
            },
            {
                data: "PLATFORM_NAME",
                title: "平台名称",
            },
            {
                data: "PLATFORM_AREA_ID",
                title: "平台所属区域",
                render : function(data, type, row) { // 模板化列显示内容
                    if(data!=null&&data!=""&&data!=undefined){
                        return $.fn.Cityname(row.PLATFORM_AREA_ID)
                    }
                }
            },
            {
                data: "PLATFORM_LEVEL",
                title: "平台级别",
            },
            {
                data: "URL",
                title: "平台网址",
            },
            {
                data: "APPLICATION_TIME",
                title: "申请时间",
            },
            {
                data: "STATUS",
                title: "申请状态",
            },
            {
                data: "ENABLE_STATUS",
                title: "启用状态",
            },
            {
                data: "ID",
                title: "操作",
                render: function (data, type, row) { // 模板化列显示内容  deletes(\'' + data + '\')
                    var look_a=   '<a data-toggle="modal1" data-target="#platFormShow" style="margin-right: 30px;cursor: pointer" onclick="Checkreport(\'' + data + '\')">查看</a>' ;
                   if(row.STATUS=="新建") {
                       var look_b = '<a data-toggle="modal1" data-target="#resDirAdd" style="margin-right: 30px;cursor: pointer" onclick="updateShow(\'' + data + '\')">修改</a>';
                   }else{
                       var look_b="";
                   }
                    var look_c=    '<a data-toggle="modal1" style="margin-right: 30px;cursor: pointer" onclick="deletes(\'' + data + '\')">删除</a>';//转义传值
                    return look_a+look_b+look_c;
                }
            }

        ]
    });

    //重新加载表格
    $scope.resourceDirs = function () {
        $("#reDir").dataTable().api().ajax.reload();
    };


    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.resourceDirs();
        }
    };


    //新增地方平台申请   或 修改地方平台申请
    function add() {
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
        $scope.partPlatFormApply.platFormName = $("input[name='platFormName']").val();
        $scope.partPlatFormApply.url = $("input[name='url']").val();
        $scope.partPlatFormApply.dataNotifierUrl = $("input[name='dataNotifierUrl']").val();
        $scope.partPlatFormApply.industry = $("input[name='industry']:checked").val();
        $scope.partPlatFormApply.platFormLevel = $("select[name='platFormLevel']").val();
        $scope.partPlatFormApply.platFormAreaId = $scope.ObjXian;
        $scope.partPlatFormApply.note = $("textarea[name='note']").val();

        $scope.addModal = function () {
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            if ($scope.updateFlag == "1") {
                $scope.partPlatFormApply.id=$("input[name='HiddenID']").val();
                var industrys= []
                if( $("#AGRICUTURE").prop("checked")){
                    industrys.push($("input[id='AGRICUTURE']").val());
                }
                if($("#HUSBANDRY").prop("checked")){
                    industrys.push($("input[id='HUSBANDRY']").val());
                }
                if($("#AQUACULTURE").prop("checked")){
                    industrys.push($("input[id='AQUACULTURE']").val());
                }
                $scope.partPlatFormApply.industry="";
                for(var i=0;i<industrys.length;i++){
                    $scope.partPlatFormApply.industry +=industrys[i]+";";
                }
                $http({
                    url: "/sofn-dgap-web/localPlatForm/update",
                    data: $scope.partPlatFormApply,
                    method: 'post',
                    headers: {
                        token:window.localStorage.getItem("token")
                    }
                }).success(function () {
                    $("button").attr("disabled", false);
                    $scope.updateFlag="";
                    $scope.partPlatFormApply={};
                    $.jBox.tip("修改成功！");
                    $("#reDir").dataTable().api().ajax.reload();

                }).error(function () {
                    $("button").attr("disabled", false);
                    $.jBox.tip("修改失败！");
                })
            }
            else {
                var industrys= []
                if($scope.partPlatFormApply.AGRICUTURE){
                    industrys.push($("input[id='AGRICUTURE']").val());
                }
                if($scope.partPlatFormApply.HUSBANDRY){
                    industrys.push($("input[id='HUSBANDRY']").val());
                }
                if($scope.partPlatFormApply.AQUACULTURE){
                    industrys.push($("input[id='AQUACULTURE']").val());
                }
                $scope.partPlatFormApply.industry="";
                for(var i=0;i<industrys.length;i++){
                    $scope.partPlatFormApply.industry +=industrys[i]+";";
                }
                $http({
                    url: "/sofn-dgap-web/localPlatForm/add",
                    data: $scope.partPlatFormApply,
                    //post传输方式   头信息
                    method: 'post',
                    //post传输方式   头信息
                    headers: {
                        token:window.localStorage.getItem("token")
                    }
                }).success(function (data) {
                    if (data.code == 200) {
                        $("button").attr("disabled", false);
                        $.jBox.tip("新增成功！");
                    }else{
                        $.jBox.tip("新增失败！平台级别不能为空");
                    }
                    $("#reDir").dataTable().api().ajax.reload();

                }).error(function () {
                    $("button").attr("disabled", false);
                    $.jBox.tip("新增失败！");
                })
            };
            }
    }


  //修改数据回显
    window.updateShow = function (data) {
        /*  数据回显*/
        $scope.dataId = data;
        $http({
            url: "/sofn-dgap-web/localPlatForm/queryById",
            data: {
                id: data
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.updateFlag="1";
            var partPlatFormApply = data.data;
            $scope.ObjXian = partPlatFormApply.platFormAreaId;
            //隐藏域赋值
            $("#resDirAddLabel").html("修改地方平台对接申请");
            $.fn.mycity("ObjShen", "ObjShi", "ObjXian",partPlatFormApply.platFormAreaId);
            $("#ObjShen").attr("disabled",false);
            $("#ObjShi").attr("disabled",false);
            $("#ObjXian").attr("disabled",false);
            $("input[name='HiddenID']").val(partPlatFormApply.id);
            $("input[name='platFormName']").val(partPlatFormApply.platFormName);
            $("select[name='platFormLevel']").val(partPlatFormApply.platFormLevel);
            if(partPlatFormApply.industry.indexOf("AGRICUTURE")!=-1){
                $("#AGRICUTURE").prop("checked",true);
            }
            if(partPlatFormApply.industry.indexOf("HUSBANDRY")!=-1){
                $("#HUSBANDRY").prop("checked",true);
            }
            if(partPlatFormApply.industry.indexOf("AQUACULTURE")!=-1){
                $("#AQUACULTURE").prop("checked",true);
            }
            $("textarea[name='note']").val(partPlatFormApply.note);
            $("input[name='url']").val(partPlatFormApply.url);
            $("input[name='dataNotifierUrl']").val(partPlatFormApply.dataNotifierUrl);
            $("#resDirAdd").modal("show");
        }).error(function (data) {

        });
    };



    //删除
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/localPlatForm/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#reDir").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！");
                        }else {
                            jBox.tip("操作不成功！");
                        }
                    }
                });
            }
            else
                jBox.tip("取消删除", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    };





    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#reDir input[type='checkbox']:not(:disabled)").prop("checked", true);
            $("#reDir tbody tr").addClass("selected");
        } else {
            $("#reDir input[type='checkbox']").prop("checked", false);
            $("#reDir tbody tr").removeClass("selected");
        }
    });
    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }

    /**
     *列表展示时将英文显示为中文
     */
    window.toStringShow = function (data) {
        if(data=="PROVINCE"){return "省级平台"}
        if(data=="CITY"){return "市级平台"}
        if(data=="COUNTY"){return "县级平台"}
        if(data=="NEW"){return "新建"}
        if(data=="APPLICATED"){return "待审核"}
        if(data=="APPROVED"){return "审核通过"}
        if(data=="REJECTED"){return "审核不通过"}
        if(data=="STARTED"){return "启用"}
        if(data=="STOPPED"){return "禁用"}

    }
    /**
     *查看详情
     */
    window.Checkreport=function(data){
        $http({
            url: "/sofn-dgap-web/localPlatForm/queryById",
            data: {
                id: data
            },
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $("#showIndustry1").prop("checked",false);//防止页面记忆选中
            $("#showIndustry2").prop("checked",false);
            $("#showIndustry3").prop("checked",false);
            $scope.reCord = data.data;
            //隐藏域赋值
            $scope.area =$.fn.Cityname($scope.reCord.platFormAreaId)
            $("input[name='showPlatFormAreaId']").val($scope.area);
            if($scope.reCord.appliCationTime!= null && $scope.reCord.appliCationTime != "" && $scope.reCord.appliCationTime != undefined){
                $("input[name='showAppliCationTime']").val(new Date(parseInt($scope.reCord.appliCationTime)).toISOString().slice(0, 10));
            }
            $("#showPlatFormLevel").prop("disabled",true);
            if($scope.reCord.industry.indexOf("AGRICUTURE")!=-1){
                $("#showIndustry1").prop("checked",true);
            }
            if($scope.reCord.industry.indexOf("HUSBANDRY")!=-1){
                $("#showIndustry2").prop("checked",true);
            }
            if($scope.reCord.industry.indexOf("AQUACULTURE")!=-1){
                $("#showIndustry3").prop("checked",true);
            }
            $("#platFormShow").modal("show");
            $("#auditSuggestion").show();
           if($scope.reCord.status=="NEW"){
               $("#auditSuggestion").hide();
           }else if($scope.reCord.status=="APPLICATED"){
               $("#auditResult").val("待审核");
           }
           else if($scope.reCord.status=="APPROVED"){
               $("#auditResult").val("审核通过");
           }else{
               $("#auditResult").val("审核不通过");
           }
        }).error(function (data) {

        });

    }

    /*
     批量提交申请
     */
    $scope.subApply = function () {
        var rows = $("#reDir").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        if (rows != false) {
            $.jBox.confirm("是否提交申请？" ,null, function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(ids)
                    };
                    $.ajax({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url: "/sofn-dgap-web/localPlatForm/subApply",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            if (data.httpCode == 409) {
                                $.jBox.tip("提交失败！您提交的申请中有记录已提交过");
                                $scope.resourceDirs();
                            }else{
                                $.jBox.tip("成功！");
                                $scope.resourceDirs();
                            }
                        },
                        fail: function (data) {
                            $.jBox.tip("失败");
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

});
