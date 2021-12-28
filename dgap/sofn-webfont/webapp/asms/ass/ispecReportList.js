var myapp = angular.module("myapp", []);
myapp.controller("ispecReportList", function ($scope, $http, $state) {
    /**
     * public
     * */
    $scope.cdDate = window.localStorage.getItem("ASMS_XCKH_XCPERSON_ID").split(",");

    /**
     * 根据人员id获取报告列表
     * 接口调用巡查任务控制器接口
     * */

    //IE兼容
    if (navigator.appVersion.substring(22, 23) == 9) {
        if ($(".myPlaceholder").val() == "") {
            $(this).val("点击选择查询日期");
        }
    }

    //从缓存中获取数据
    if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
        $scope.inspectionResults = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['patrolResult'];
        $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
        $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];

        //初始化区域下拉框初始选项
        $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
        if ($scope.organization.orgLevel != "1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if (
                $("#sheng").val() != null &&
                $("#sheng").val() != "" &&
                $("#sheng").val() != undefined
            ) {
                $("#sheng").attr("disabled", true);
            }
            if (
                $("#shi").val() != null &&
                $("#shi").val() != "" &&
                $("#shi").val() != undefined
            ) {
                $("#shi").attr("disabled", true);
            }
            if (
                $("#xian").val() != null &&
                $("#xian").val() != "" &&
                $("#xian").val() != undefined
            ) {
                $("#xian").attr("disabled", true);
            }
        } else {
            $.fn.mycity("sheng", "shi", "xian");
        }
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }

    //dataTable
    //基地巡查列表
    $(function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/baseInspection/getBaseInspectionList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].INSPECTION_TIME != null && json.list[i].INSPECTION_TIME != "" && json.list[i].INSPECTION_TIME != undefined) {
                            json.list[i].INSPECTION_TIME = new Date(parseInt(json.list[i].INSPECTION_TIME) + 1000 * 60 * 60 * 24).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    // params.enterpriseIndustry = $("select[name='enterpriseIndustry']").val();
                    params.inspectionResult = $("select[name='inspectionResult']").val();
                    // params.entityScale = $("select[name='entityScale']").val();
                    params.enterpriseName = $("input[name='enterpriseName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
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
                    params.area = areaId;//区域id
                    params.taskDateType = $scope.cdDate[0];//考核类型
                    params.taskDateYear = $scope.cdDate[1];//年
                    params.taskDateMonths = $scope.cdDate[2];//月
                    params.taskDateQuarter = $scope.cdDate[3];//季度
                    params.inspectionPersonId = $scope.cdDate[4];//巡查人员id
                    params.inspectionType = $("select[name='inspectionType']").val();
                    // params.taskType = $scope.cdDate[5];//任务类型
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
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    width:"22%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='ENTERPRISE_NAME"+row.ID+"' onmouseover=showPopContent('ENTERPRISE_NAME"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "AREA",
                    title: "区域",
                    width:"14%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>10){
                            return "<a id='AREA"+row.ID+"' onmouseover=showPopContent('AREA"+row.ID+"','"+data+"')>"+data.substring(0,10)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "CONTACT_NAME",
                    title: "联系人",
                    width:"8%"
                },
                {
                    data: "INSPECTION_TYPE_NAME",
                    title: "任务类型",
                    width:"10%"
                },
                {
                    data: "INSPECTION_RESULT",
                    title: "巡查结果",
                    width:"8%",
                    render: function (data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        } else if (data == "4") {
                            return "待考究";
                        }
                    }
                },
                {
                    data: "INSPECTION_TIME",
                    title: "巡查时间",
                    width:"8%"
                },
                {
                    data: "INSPECTION_USER_NAME",
                    title: "巡查员",
                    width:"18%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='INSPECTION_USER_NAME"+row.ID+"' onmouseover=showPopContent('INSPECTION_USER_NAME"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="inspectionView(\'' + data + '\')">查看</a>';
                        //'<a style="cursor: pointer" onclick="deleteBaseInspection(\''+data+'\')">删除</a>';//转义传值
                    }
                }
            ]
        });
    });

    //重新加载基地巡查列表
    $scope.queryBaseInspection = function () {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid").dataTable().api().ajax.reload();
    };

    //跳转页面-查看基地巡查详情
    window.inspectionView = function (data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        window.localStorage.removeItem("inspectionViewEntryType");
        window.localStorage.setItem("inspectionViewEntryType", "1");//基地巡查详情，从巡查人员报告进入查看的
        $state.go("index.content.asms/baseInspection/inspectionView");
    };

    //跳转页面-修改基地巡查
    window.gotoUpdateBaseInspection = function (data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        $state.go("index.content.asms/baseInspection/inspectionUpdate");
    };

    //删除基地巡查
    window.deleteBaseInspection = function (data) {
        $("button").attr("disabled", true);
        $.ajax({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/baseInspection/deleteBaseInspection",
            type: "post",
            dataType: "json",
            data: {id: data},
            success: function (data) {
                $("button").attr("disabled", false);
                alert("删除成功！");
                $("#grid").dataTable().api().ajax.reload();
            }
        });

    };
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

