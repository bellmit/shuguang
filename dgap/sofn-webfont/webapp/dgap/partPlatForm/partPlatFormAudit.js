var myapp = angular.module("myapp", []);
myapp.controller("partPlatFormAudit", function ($scope, $http, $state) {
    //预先声明变量
    $scope.partPlatFormApply = {};
    var area = "";
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
        $.fn.mycity("sheng","shi","xian",$scope.jurisdictionArea);
        $("select[name='sheng']").val("");
        $("select[name='shi']").val("");
        $("select[name='xian']").val("");
        $("#sheng").attr("disabled",false);
        $("#shi").attr("disabled",false);
        $("#xian").attr("disabled",false);
    }).error(function(){
    });



    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    /* 清除查询框内容*/
    $scope.cleanData = function () {
        $("input[name='dateBegin']").val("");
        $("input[name='dateEnd']").val("");
        $("input[name='queryPlatFormName']").val("");
        $("select[name='status']").val("");
        $("select[name='queryPlatFormLevel']").val("");
        $("select[name='sheng']").val("");
        $("select[name='shi']").val("");
        $("select[name='xian']").val("");
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
            url: "/sofn-dgap-web/paltFormAudit/list",
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
                }
                return json.data.list;
            },
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.dateBegin = $("input[name='dateBegin']").val();
                params.dateEnd = $("input[name='dateEnd']").val();
                params.status = $("select[name='status']").val();
                params.platFormName = $("input[name='queryPlatFormName']").val();
                params.platFormLevel = $("select[name='queryPlatFormLevel']").val();
                params.enableStatus=$("select[name='enableStatus']").val();//启用状态
                sheng=$("select[name='sheng']").val();
                shi=$("select[name='shi']").val();
                xian=$("select[name='xian']").val();
                if(sheng!=""&& sheng!=null&&sheng!=undefined){
                    if(shi!=""&& shi!=null&&shi!=undefined) {
                        if(xian!=""&& xian!=null&&xian!=undefined){
                            params.platFormAreaId=xian
                        }else{
                            params.platFormAreaId=shi
                        }
                    }else {
                        params.platFormAreaId = sheng
                    }
                }
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
                data: "APPLICATION_DEPARTMENT",
                title: "申请单位",
            },
            {
                data: "APPLICATION_TIME",
                title: "申请时间",
            },
            {
                data: "STATUS",
                title: "审核状态",
            },
            {
                data: "ENABLE_STATUS",
                title: "启禁用按钮",
                render: function (data, type, row) {
                    var sta_a="STARTED";
                    var sta_b="STOPPED";
                    if(row.STATUS =="审核通过"&&row.ENABLE_STATUS==null){
                        var look_c = '<a data-toggle="modal1" style="margin-right: 10px;cursor: pointer"  onclick="enablePart (\'' + row.ID + '\',\''+sta_a+'\') ">启用</a>';

                        var look_d = '<a data-toggle="modal1"  style="margin-right: 10px;cursor: pointer" onclick="enablePart(\'' + row.ID + '\' ,\''+sta_b+'\')">禁用</a>';
                    } else if(row.ENABLE_STATUS =="STARTED"){
                        var look_c = '<a data-toggle="modal1" style="margin-right: 10px;cursor: pointer" onclick="enablePart(\'' + row.ID + '\',\''+sta_b+'\')">禁用</a>';
                        var look_d="";
                    }else if(row.ENABLE_STATUS =="STOPPED"){
                        var look_c = '<a data-toggle="modal1"  style="margin-right: 10px;cursor: pointer" onclick="enablePart(\'' + row.ID + '\',\''+sta_a+'\')">启用</a>';
                        var look_d="";
                    }else{
                        var look_c="";  var look_d="";
                    }
                    return look_c+""+look_d;
                }

            },
            {
                data: "AUDIT_USER_ID",
                title: "审核人",
            },
            {
                data: "ID",
                title: "操作",
                render: function (data, type, row) { // 模板化列显示内容  deletes(\'' + data + '\')
                    var look_a=  '<a data-toggle="modal1" data-target="#platFormShow" style="margin-right: 30px;cursor: pointer" onclick="Checkreport(\'' + data + '\')">查看</a>' ;
                    if(row.STATUS == "待审核"){
                        var look_b=  '<a data-toggle="modal1" data-target="#resDirAdd" style="margin-right: 30px;cursor: pointer" onclick="audit (\'' + data + '\')">审核</a>';
                    } else {
                        var look_b="";
                    };
                    return look_a+look_b;
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


    //审核通过
    $scope.successAudit = function () {
        // $scope.partPlatFormApply.auditComments = $("input[name='auditComments']").val();
        $scope.partPlatFormApply.id = $("input[name='HiddenID']").val();
        $scope.partPlatFormApply.status = "APPROVED";
        $http({
            url: "/sofn-dgap-web/paltFormAudit/formAudit",
            data: $scope.partPlatFormApply,
            method: 'post',
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).success(function (data) {
            if(data.data!="BAD_REQUEST"){
                $.jBox.tip("审核通过成功！");
            }else{
                $.jBox.tip("未填写审核意见！");
            }
            $("button").attr("disabled", false);
            $scope.partPlatFormApply = {};
            $("#reDir").dataTable().api().ajax.reload();

        }).error(function () {
            $("button").attr("disabled", false);
            $.jBox.tip("审核通过失败！");
        })

    }
    //审核拒绝
    $scope.failAudit = function () {
            $scope.partPlatFormApply.id = $("input[name='HiddenID']").val();
            $scope.partPlatFormApply.status = "REJECTED";
            $http({
                url: "/sofn-dgap-web/paltFormAudit/formAudit",
                data: $scope.partPlatFormApply,
                method: 'post',
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function (data) {

                if(data.data!="BAD_REQUEST"){
                    $.jBox.tip("拒绝审核成功！");
                }else{
                    $.jBox.tip("未填写审核意见！");
                }
                $("button").attr("disabled", false);
                $scope.partPlatFormApply = {};
                $("#reDir").dataTable().api().ajax.reload();
            }).error(function () {
                $("button").attr("disabled", false);
                $.jBox.tip("拒绝审核失败！");
            })

        }



    //审核数据回显
    window.audit = function (data) {
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
            $("#industry1").prop("checked",false);//防止页面记忆选中
            $("#industry2").prop("checked",false);
            $("#industry3").prop("checked",false);
            $scope.partPlatFormApply = data.data;
            //隐藏域赋值
            $scope.area =$.fn.Cityname($scope.partPlatFormApply.platFormAreaId)
            $("input[name='platFormAreaId']").val($scope.area);
            $("input[name='HiddenID']").val($scope.partPlatFormApply.id);
            $("input[name='platFormName']").val($scope.partPlatFormApply.platFormName);
            $("select[name='platFormLevel']").val($scope.partPlatFormApply.platFormLevel);
            $("textarea[name='note']").val($scope.partPlatFormApply.note);
            $("input[name='url']").val($scope.partPlatFormApply.url);
            $("input[name='dataNotifierUrl']").val($scope.partPlatFormApply.dataNotifierUrl);
            if($scope.partPlatFormApply.appliCationTime!= null && $scope.partPlatFormApply.appliCationTime != ""
                && $scope.partPlatFormApply.appliCationTime != undefined){
                $("input[name='appliCationTime']").val(new Date(parseInt($scope.partPlatFormApply.appliCationTime)).toISOString().slice(0, 10));
            }
            if($scope.partPlatFormApply.industry.indexOf("AGRICUTURE")!=-1){
                $("#industry1").prop("checked",true);
            }
            if($scope.partPlatFormApply.industry.indexOf("HUSBANDRY")!=-1){
                $("#industry2").prop("checked",true);
            }
            if($scope.partPlatFormApply.industry.indexOf("AQUACULTURE")!=-1){
                $("#industry3").prop("checked",true);
            }
            $("#audit").modal("show");
        }).error(function (data) {

        });
    };




    //起禁用
    window.enablePart = function (data,status) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/paltFormAudit/enablePart",
                    type: "post",
                    dataType: "json",
                    data: {id:data,enableStatus: status},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#reDir").dataTable().api().ajax.reload();
                            jBox.tip("成功！");
                        }else {
                            jBox.tip("操作不成功！");
                        }
                    }
                });
            }
            else
                jBox.tip("取消", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认启用或禁用吗？", "启禁提示", submit, {buttons: {'确认': true, '取消': false}});
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


});
