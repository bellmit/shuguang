var myapp=angular.module("myapp",[]);
myapp.controller("inspectionList",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("基地巡查");
    $scope.inspectionListQueryParams = JSON.parse(window.sessionStorage.getItem("inspectionListQueryParams"));

    //重置区域
    $scope.resetArea=function(){
       $("select[name='entityScaleId']").find("option:first").prop("selected",true);
       $("select[name='entityTypeId']").find("option:first").prop("selected",true);
       $("select[name='enterpriseIndustryId']").find("option:first").prop("selected",true);
       $("select[name='inspectionResult']").find("option:first").prop("selected",true);
       $("input[name='enterpriseName']").val("");
       $("input[name='dateBegin']").val("");
       $("input[name='dateEnd']").val("");
       $("input[name='queryYear']").val("");
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

    //基地巡查列表
    $scope.loadGrid = function() {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/baseInspection/getBaseInspectionAllList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].INSPECTION_TIME != null && json.list[i].INSPECTION_TIME != "" && json.list[i].INSPECTION_TIME != undefined) {
                            json.list[i].INSPECTION_TIME = new Date(parseInt(json.list[i].INSPECTION_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.list[i].CREATE_TIME != null && json.list[i].CREATE_TIME != "" && json.list[i].CREATE_TIME != undefined) {
                            json.list[i].CREATE_TIME = new Date(parseInt(json.list[i].CREATE_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    var inspectionListQueryParams = JSON.parse(window.sessionStorage.getItem("inspectionListQueryParams",inspectionListQueryParams));
                    if(inspectionListQueryParams){
                        params.enterpriseIndustryId = inspectionListQueryParams.enterpriseIndustryId;
                        params.inspectionResult = inspectionListQueryParams.inspectionResult;
                        params.entityScaleId = inspectionListQueryParams.entityScaleId;
                        params.entityTypeId = inspectionListQueryParams.entityTypeId;
                        params.enterpriseName = inspectionListQueryParams.enterpriseName;
                        params.dateBegin = inspectionListQueryParams.dateBegin;
                        params.dateEnd = inspectionListQueryParams.dateEnd;
                        params.area = inspectionListQueryParams.area;
                        params.queryYear = inspectionListQueryParams.queryYear;
                    }else{
                        params.enterpriseIndustryId = $("select[name='enterpriseIndustryId']").val()||$scope.allIndustryId;
                        params.inspectionResult = $("select[name='inspectionResult']").val();
                        params.entityScaleId = $("select[name='entityScaleId']").val();
                        params.entityTypeId = $("select[name='entityTypeId']").val();
                        params.enterpriseName = $("input[name='enterpriseName']").val();
                        params.dateBegin = $("input[name='dateBegin']").val();
                        params.dateEnd = $("input[name='dateEnd']").val();
                        params.queryYear = $("input[name='queryYear']").val();
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
                        params.area = areaId;
                    }
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width: "2%"
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"

                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    width:"14%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>10){
                            return "<a id='ENTERPRISE_NAME"+row.ID+"' onmouseover=showPopContent('ENTERPRISE_NAME"+row.ID+"','"+data+"')>"+data.substring(0,10)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                // {
                //     data : "ENTITY_INDUSTRY_NAME",
                //     title : "所属行业"
                // },
                {
                    data: "AREA",
                    title: "区域",
                    width:"12%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>9){
                            return "<a id='AREA"+row.ID+"' onmouseover=showPopContent('AREA"+row.ID+"','"+data+"')>"+data.substring(0,9)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "CONTACT_NAME",
                    title: "联系人",
                    width:"5%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>3){
                            return "<a id='CONTACT_NAME"+row.ID+"' onmouseover=showPopContent('CONTACT_NAME"+row.ID+"','"+data+"')>"+data.substring(0,3)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "INSPECTION_TYPE_NAME",
                    title: "任务类型",
                    width:"11%"
                },
                {
                    data: "INSPECTION_RESULT",
                    title: "巡查结果",
                    width:"6%",
                    render: function (data, type, row) {
                        for(var i=0;i<$scope.inspectionResults.length;i++){
                            if (data == $scope.inspectionResults[i].dictValue) {
                                return $scope.inspectionResults[i].dictName;
                            }
                        }
                    }
                },
                {
                    data: "INSPECTION_TIME",
                    title: "巡查时间",
                    width:"7%"
                },
                {
                    data: "INSPECTION_USER_NAME",
                    title: "巡查员",
                    width:"8%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>6){
                            return "<a id='INSPECTION_USER_NAME"+row.ID+"' onmouseover=showPopContent('INSPECTION_USER_NAME"+row.ID+"','"+data+"')>"+data.substring(0,6)+"...</a>";
                        }else{
                            return data;
                        }
                    }

                },
                {
                    data: "USER_NAME",
                    title: "创建者",
                    width:"7%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>4){
                            return "<a id='USER_NAME"+row.ID+"' onmouseover=showPopContent('USER_NAME"+row.ID+"','"+data+"')>"+data.substring(0,4)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width:"7%"
                },
                {
                    data: "STATUS",
                    title: "状态",
                    width:"5%",
                    render: function (data, type, row) {
                        if (data && data == '1') {
                            return "草稿";
                        }else {
                            return "已上传";
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt = '<a style="margin-right: 5px;cursor: pointer" class="Check-report color"  onclick="inspectionView(\'' + data + '\')">查看</a>';
                        if ($scope.iswrite==2) {
                            if(row.CREATE_BY==$scope.user.id) {
                                butt += '<a style="margin-right: 5px;cursor: pointer" class="Check-report color" onclick="gotoUpdateBaseInspection(\'' + data + '\')">修改</a>';
                            }
                            if(row.STATUS != '1'){
                                if(row.INSPECTION_RESULT!=2){
                                    butt += '<a style="margin-right: 5px;cursor: pointer;display: none;" class="Check-report color" onclick="gotoAddCheckTask(\'' + data + '\',\'' + row.INSPECTION_RESULT + '\')">执法检查</a>';
                                    return butt;
                                }else{
                                    butt += '<a style="margin-right: 5px;cursor: pointer" class="Check-report color" onclick="gotoAddCheckTask(\'' + data + '\',\'' + row.INSPECTION_RESULT + '\')">执法检查</a>';
                                    return butt;
                                }
                            }
                        }
                        return butt;
                    }
                },
            ],
        });
    }
    //从缓存从获取数据
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
        //巡查结果
        $scope.inspectionResults = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['patrolResult'];
        $scope.resetArea();
        $scope.loadGrid();
        //参数回显,延时解决参数回显bug
        setTimeout(function(){
            if($scope.inspectionListQueryParams){
                $("select[name='inspectionResult']").val($scope.inspectionListQueryParams.inspectionResult);
                $("#dateBegin").val($scope.inspectionListQueryParams.dateBegin);
                $("#dateEnd").val($scope.inspectionListQueryParams.dateEnd);
                $("input[name='queryYear']").val($scope.inspectionListQueryParams.queryYear);
                $("input[name='enterpriseName']").val($scope.inspectionListQueryParams.enterpriseName);
                $scope.entityScaleId = $scope.inspectionListQueryParams.entityScaleId;
                $scope.entityTypeId = $scope.inspectionListQueryParams.entityTypeId;
                $scope.enterpriseIndustryId = $scope.inspectionListQueryParams.enterpriseIndustryId;
                $.fn.mycity("sheng", "shi", "xian",$scope.inspectionListQueryParams.area);
            }
        },50)
    }else{
        $scope.initAllSysDictAndUserInfo();
    }



    //重新加载基地巡查列表
    $scope.queryBaseInspection=function(){
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var inspectionListQueryParams = {};
        inspectionListQueryParams.enterpriseIndustryId = $("select[name='enterpriseIndustryId']").val();
        inspectionListQueryParams.inspectionResult = $("select[name='inspectionResult']").val();
        inspectionListQueryParams.entityScaleId = $("select[name='entityScaleId']").val();
        inspectionListQueryParams.entityTypeId = $("select[name='entityTypeId']").val();
        inspectionListQueryParams.enterpriseName = $("input[name='enterpriseName']").val();
        inspectionListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        inspectionListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        inspectionListQueryParams.queryYear = $("input[name='queryYear']").val();
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
        inspectionListQueryParams.area = areaId;
        window.sessionStorage.setItem("inspectionListQueryParams",JSON.stringify(inspectionListQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    }

    //跳转页面-查看基地巡查详情
    window.inspectionView=function(data){
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId",data);
        window.localStorage.removeItem("inspectionViewEntryType");
        window.localStorage.setItem("inspectionViewEntryType", "0");//基地巡查详情，从基地巡查列表进入查看的
        $state.go("index.content.asms/baseInspection/inspectionView");
    }

    //跳转页面-修改基地巡查
    window.gotoUpdateBaseInspection = function(data){
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId",data);
        $state.go("index.content.asms/baseInspection/inspectionUpdate");
    }

    //跳转页面-进行执法检测
    window.gotoAddCheckTask = function(data,inspectionResult){
        if(inspectionResult!="2"){
            jBox.tip("只能针对不合格的基地巡查进行执法检查！","info");
            return false;
        }
        //清楚监督抽查任务id
        window.localStorage.removeItem("asms_checktask_taskinfo_taskid");
        window.localStorage.removeItem("inspectionIdForCheckTask");
        window.localStorage.setItem("inspectionIdForCheckTask",data);
        $state.go("index.content.asms/check/checkTaskAddSj");
    }

    //删除基地巡查
    $scope.deleteBaseInspection = function(data){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].CREATE_BY.trim() != "admin" && rows[n].CREATE_BY.trim() != $scope.user.id) {
                $.jBox.tip("没有权限,仅创建者可以删除!");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        if (rows != false) {
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    $.ajax({
                        url: "/sofn-asms-web/baseInspection/deleteBaseInspection",
                        type: "post",
                        dataType: "json",
                        data: {ids:ids.toString()},
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $('#grid').dataTable().api().ajax.reload();
                        },
                        error: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };
    $scope.updateBaseInspectionDraftBatch = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].STATUS == '0') {
                $.jBox.tip("仅限草稿上传!");
                return false;
            }
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].CREATE_BY.trim() != "admin" && rows[n].CREATE_BY.trim() != $scope.user.id) {
                $.jBox.tip("没有权限,仅创建者可以上传!");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        if (rows != false) {
            $.jBox.confirm("是否上传已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    $.ajax({
                        url: "/sofn-asms-web/baseInspection/updateBaseInspectionDraftBatch",
                        type: "post",
                        dataType: "json",
                        data: {ids:ids.toString()},
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $('#grid').dataTable().api().ajax.reload();
                        },
                        error: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
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
    //附件下载
    $scope.downloadFile=function(fileAddress, fileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', fileAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', fileName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //获取基地巡查基础信息采集表,并打开模态框
    $scope.downBaseInpsCollTab = function () {
        var url = "/sofn-asms-web/baseInspection/getBaseInspeInfoCollTab";
        var data = "";
        $http.post(url, data, {
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).then(function(res){
            if (res.data.httpCode == "200") {
                var fileAddress = res.data.data.fileAddress;
                var fileName = res.data.data.fileName;
                $scope.downloadFile(fileAddress, fileName);
            } else {
                $.jBox.tip("未找到可用文件");
            }
        }, function(res){


        })
    }

})

