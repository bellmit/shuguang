var myapp = angular.module("myapp", []).controller("asmsKhTask", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("考核任务");
    $scope.taskType = {};
    $scope.taskTypeTouched = false;
    /**
     * public
     * */
    $.fn.mycity("querySheng", "queryShi", "queryXian");//初始化区域下拉框初始选项
    $scope.asmsKhTaskQueryParams = JSON.parse(window.sessionStorage.getItem("asmsKhTaskQueryParams"));
    /**
     * 当前用户机构
     * */
    $http({
        url: "/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        regionId = data.data.regionId;
        $.fn.mycity("ObjShen", "ObjShi", "ObjXian", regionId);
        $.fn.mycity("querySheng", "queryShi", "queryXian", regionId);
        //根据用户初始化行政区划选择下拉框
        var orgLevel = data.data.orgLevel;
        if (orgLevel == "2") {
            $("#ObjShen").attr({"disabled": "disabled"});
            $("#querySheng").attr({"disabled": "disabled"});
        } else if (orgLevel == "3") {
            $("#ObjShen").attr({"disabled": "disabled"});
            $("#ObjShi").attr({"disabled": "disabled"});
            $("#querySheng").attr({"disabled": "disabled"});
            $("#queryShi").attr({"disabled": "disabled"});
        } else if (orgLevel == "4") {
            $("#ObjShen").attr({"disabled": "disabled"});
            $("#ObjShi").attr({"disabled": "disabled"});
            $("#ObjXian").attr({"disabled": "disabled"});
            $("#querySheng").attr({"disabled": "disabled"});
            $("#queryShi").attr({"disabled": "disabled"});
            $("#queryXian").attr({"disabled": "disabled"});
        }
        $scope.loadDataTable();
    }).error(function (data) {
        
    });

    /**
     * 表单验证
     * 新增/修改/退出需清除验证状态
     * */
    //验证初始化
    $('#attributeForm').bootstrapValidator();
    //验证是否通过
    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };
    //清除验证状态
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };
    /**
     *----------变量------------
     * @author LiBling
     * */
    $scope.KhTaskId = "";
    /**
     *----------任务列表------------
     * @author LiBling
     * */
    //查询按钮
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var asmsKhTaskQueryParams = {};
        asmsKhTaskQueryParams.taskDateType = $("select[name='taskDateType']").val();
        asmsKhTaskQueryParams.taskType = $("select[name='taskType']").val();
        asmsKhTaskQueryParams.querySheng = $("#querySheng").val();
        asmsKhTaskQueryParams.queryShi = $("#queryShi").val();
        asmsKhTaskQueryParams.queryXian = $("#queryXian").val();
        window.sessionStorage.setItem("asmsKhTaskQueryParams",JSON.stringify(asmsKhTaskQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    if($scope.asmsKhTaskQueryParams){
        $("select[name='taskDateType']").val($scope.asmsKhTaskQueryParams.taskDateType);
        $("select[name='taskType']").val($scope.asmsKhTaskQueryParams.taskType);
        if($scope.asmsKhTaskQueryParams.queryXian){
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.asmsKhTaskQueryParams.queryXian);
        }else if($scope.asmsKhTaskQueryParams.queryShi){
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.asmsKhTaskQueryParams.queryShi);
        }else if($scope.asmsKhTaskQueryParams.querySheng) {
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.asmsKhTaskQueryParams.querySheng)
        } else {
            $.fn.mycity("querySheng", "queryShi", "queryXian");
            }
        }


    //列表
    $scope.loadDataTable = function() {
            $("#grid").dataTable({
                ajax: {
                    url: "/sofn-asms-web/tspinstask/taskList",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function (json) {
                        if (json.data == null || json.data == undefined) {
                            json.recordsTotal = 0;//总个数
                            json.recordsFiltered = 0;
                            json.pageNumber = 1;//页码
                            json.pageSize = 10;//每页个数
                            json.start = 0;//起始位置
                            json.length = 10;
                            return json.data;
                        }
                        json.recordsTotal = json.data.total;//总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum;//页码
                        json.pageSize = json.data.pageSize;//每页个数
                        json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                        json.length = json.data.pageSize;
                        for (var i = 0; i < json.data.list.length; i++) {
                            //日期格式化
                            if (json.data.list[i].CREATETIME != null && json.data.list[i].CREATETIME != "" && json.data.list[i].CREATETIME != undefined) {
                                json.data.list[i].CREATETIME = new Date(parseInt(json.data.list[i].CREATETIME)).toISOString().slice(0, 10);
                            }
                            //任务状态判断
                            //任务时间

                            var date = new Date();
                            var taskType = $.trim(json.data.list[i].TASKDATETYPE);
                            var taskYear = json.data.list[i].TASKDATEYEAR;//任务时间-年
                            var taskYearMonth = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEMONTHS;//任务时间-年+月
                            var taskDateMonth = json.data.list[i].TASKDATEMONTHS;
                            var taskYearQuarter = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEQUARTER;//任务时间-年+季度
                            var taskQuarter = json.data.list[i].TASKDATEQUARTER;

                            //当前时间
                            var nowYear = date.getFullYear();//当前年
                            var nowYearMonth;//当前年+月
                            var nowMonth = date.getMonth() + 1;//当前月份
                            if (nowMonth < 10) {
                                nowYearMonth = date.getFullYear() + "0" + nowMonth;
                            } else {
                                nowYearMonth = date.getFullYear() + "" + nowMonth;
                            }
                            var currQuarter = Math.floor(( nowMonth % 3 == 0 ? ( nowMonth / 3 ) : ( nowMonth / 3 + 1 ) ));
                            var nowYearQuarter = date.getFullYear() + "" + currQuarter;//当前年+季度
                            if (taskType == "年度") {
                                if (nowYear > taskYear) {
                                    json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                                } else if (nowYear < taskYear) {
                                    json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                                } else {
                                    json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                                }
                                json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年";
                            } else if (taskType == "月度") {
                                if (nowYearMonth > taskYearMonth) {
                                    json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                                } else if (nowYearMonth < taskYearMonth) {
                                    json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                                } else {
                                    json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                                }
                                json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEMONTHS + "月";
                            } else if (taskType == "季度") {
                                if (nowYearQuarter > taskYearQuarter) {
                                    json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                                } else if (nowYearQuarter < taskYearQuarter) {
                                    json.data.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                                } else {
                                    json.data.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                                }
                                json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEQUARTER + "季度";
                            }
                            if (json.data.list[i].FINISHFLAG == true) {
                                json.data.list[i].ENABLE = '<p style="color:#EA0000"><B>已完成</B></p>';
                            }
                        }
                        return json.data.list;
                    },
                    data: function (params) {
                        params.token = window.localStorage.getItem("token");
                        var asmsKhTaskQueryParams = JSON.parse(window.sessionStorage.getItem("asmsKhTaskQueryParams"));
                        if(asmsKhTaskQueryParams){
                            params.taskDateType = asmsKhTaskQueryParams.taskDateType;
                            params.taskType = asmsKhTaskQueryParams.taskType;
                            params.querySheng = asmsKhTaskQueryParams.querySheng;
                            params.queryShi = asmsKhTaskQueryParams.queryShi;
                            params.queryXian = asmsKhTaskQueryParams.queryXian;
                        }else{
                            params.taskDateType = $("select[name='taskDateType']").val();
                            params.taskType = $("select[name='taskType']").val();
                            params.querySheng = $("#querySheng").val();
                            params.queryShi = $("#queryShi").val();
                            params.queryXian = $("#queryXian").val();
                        }
                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "ck",
                        title: '<input id="checkAll"  type="checkbox"/>',
                        render: function (data, type, row) {
                            return '<input type="checkbox"  name="ck"/>';
                        }
                    },
                    {
                        title: "序号",
                        data: function (data, type, row, meta) {
                            return meta.row + 1
                        }
                    },
                    {
                        data: 'ID',
                        title: 'ID',
                        visible: false
                    },
                    {
                        data: "TASKNAME",
                        title: "任务名称",
                        width: "18%",
                        render: function (data, type, row) {
                            return data;
                        }
                    },
                    {
                        data: "INSPECTIONAREAID",
                        title: "巡查区域",
                        width:"18%",
                        render:function(data,type,row){
                            data = $.fn.Cityname(data);
                            if(data!=null&&data!=undefined&&data.length>14){
                                return "<a id='INSPECTIONAREAID"+row.ID+"' onmouseover=showPopContent('INSPECTIONAREAID"+row.ID+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                            }else{
                                return data;
                            }
                        }
                    },
                    {
                        data: "TASKDATETYPE",
                        title: "考核类型"
                    },
                    {
                        data: "taskDate",
                        title: "考核时间"
                    },
                    {
                        data: "TASKTYPENAME",
                        title: "任务类型"
                    },
                    {
                        data: "INSPECTIONCOUNT",
                        title: "巡查次数"
                    },
                    {
                        data: "CREATETIME",
                        title: "创建日期"
                    },
                    {
                        data: "ENABLE",
                        title: "任务状态"
                    },
                    {
                        data: "ID",
                        title: "操作",
                        render: function (data, type, row) { // 模板化列显示内容
                            return '<a style="cursor: pointer" class="color"  onclick="queryKhTaskInfo(\'' + data + '\')">详情</a>';//转义传值
                        }
                    }
                ]
            });
        }


    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#grid input[type='checkbox']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    //列表刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };

    //针对复选框无法选中bug
    window.checkThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    /**
     * ---------人员选择列表--------------
     * */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects = new Array();

    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.selectPerson = function () {
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };

    //确定
    $scope.selectModalQdButt = function () {
        var userName = "";
        for (var i = 0; i < $scope.selects.length; i++) {
            if ($scope.selects[i] != undefined) {
                if (userName != "") {
                    userName += "," + $scope.selects[i].userName;
                } else {
                    userName = $scope.selects[i].userName;
                }
            }
        }
        $("input[name='ObjName']").val(userName);
        $("#selectModal").modal('hide');
        SdDateValid.ValidByCode("input", "ObjName");
    };

    $(function () {
        $("#selectGrid").dataTable({
            fnDrawCallback: function () {
                //判断是否全选
                $("#checkAllPerson").prop("checked",$("#selectGrid input[name='ck']").length == $("#selectGrid input[name='ck']:checked").length);
                $("#selectGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //人员移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0; i < $scope.selects.length; i++) {
                                if ($scope.selects[i] != undefined && u_d == $scope.selects[i].id) {
                                    delete $scope.selects[i];
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //人员选择记录
                            var map = {
                                id: $(this).parent().find("[name='ck']").val(),
                                userName: $(this).parent().find("[name='userName']").html()
                            };
                            $scope.selects.push(map);
                        }
                        //判断是否全选
                       $("#checkAllPerson").prop("checked",$("#selectGrid input[name='ck']").length == $("#selectGrid input[name='ck']:checked").length);
                    });
                })
            },
            ajax: {
                url: "/sofn-asms-web/baseInspection/getSysUserListByOrgId",
                dataSrc: function (json) {
                    json.recordsTotal=json.page.recordsTotal;//总个数
                    json.recordsFiltered=json.page.recordsFiltered;
                    json.start=json.page.start;//起始位置
                    json.length=json.page.length;
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    // params.keyword = $("input[name='selectQueryName']").val();
                    params.userName = $("input[name='selectQueryName']").val();
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '<input id="checkAllPerson" onclick="checkAllPerson(this.checked)"  type="checkbox"/>',
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].id) {
                                return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                    }
                },
                {
                    data: 'id',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "userName",
                    title: "姓名",
                    render: function (data, type, row) {
                        return '<span name = "userName">' + data + '</span>';
                    }
                },
                {
                    data: "phone",
                    title: "联系电话"
                },
                {
                    data: "email",
                    title: "电子邮箱"
                }
            ]
        });
    });

    $("#selectTocheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnPersonDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnPersonDisabled = false);
        }, 500);
        $("#selectGrid").dataTable().api().ajax.reload();
    });

    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };
    //巡查人员全选,全不选
    window.checkAllPerson = function(checked){
        //本页人员全部移除
        $("#selectGrid input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selects.length ; i++){
                if($scope.selects[i] != undefined && u_d == $scope.selects[i].id){
                    delete $scope.selects[i];
                }
            }
        })
        //添加本页全部人员
        if(checked){
            $("#selectGrid input[name='ck']:checked").each(function(i,o) {
                var map = {
                    id: $(o).val(),
                    userName: $(o).parent().parent().find("[name='userName']").html()
                };
                $scope.selects.push(map);
            });
        }
    }

    //查询条件重置
    $scope.cleanSelectModelQuery = function () {
        $("input[name='selectQueryName']").val("");
        $("#selectGrid").dataTable().api().ajax.reload();
    };

    //清空已选择
    $scope.cleanSelected = function () {
        $("#selectGrid input[name = 'taskType']").prop('checked', false);
        $scope.selects = null;
        $scope.selects = new Array();
        $('#selectGrid').dataTable().api().ajax.reload();
        $scope.dataFormValidatorReset();//表单验证清除
    };

    /**
     *----------任务操作 新增 修改 查看------------
     * @author LiBling
     * */

    //根据考核类型判断周期选择
    $("select[name='taskDateMonths']").hide();
    $("select[name='taskDateQuarter']").hide();
    // $("input[name='taskDateYear']").hide();
    $scope.taskDateType = function () {
        $("input[name='taskDateYear']").show();
        $("select[name='taskDateMonths']").show();
        $("select[name='taskDateQuarter']").show();
        //下面定义的变量用来控制季度和月度的范围，根据所选年份来控制，另外一处时间选择也要进行处理
        var date = new Date();
        var year = date.getFullYear();//当前年份
        var nowMonth = date.getMonth();//当前月份
        var quarter = parseInt(nowMonth / 3);
        var taskYear = $("input[name='taskDateYear']").val();
        if ($scope.examine.taskDateType == "年度") {
            $("select[name='taskDateMonths']").hide();
            $("select[name='taskDateQuarter']").hide();
        }
        if ($scope.examine.taskDateType == "季度") {
            $("select[name='taskDateMonths']").hide();
            //已选择任务年份，并且是当前年才进行此操作
            if (taskYear != null && taskYear != "" && taskYear != undefined && taskYear == year) {
                for (var i = 1; i < quarter + 1; i++) {
                    $("select[name='taskDateQuarter'] option[value='" + i + "']").hide();
                }
            } else {
                for (var i = 1; i < quarter + 1; i++) {
                    $("select[name='taskDateQuarter'] option[value='" + i + "']").show();
                }
            }
        }
        if ($scope.examine.taskDateType == "月度") {
            $("select[name='taskDateQuarter']").hide();
            //已选择任务年份，并且是当前年才进行此操作
            if (taskYear != null && taskYear != "" && taskYear != undefined && taskYear == year) {
                for (var i = 1; i < nowMonth + 1; i++) {
                    if(i>9){
                        $("select[name='taskDateMonths'] option[value='" + i + "']").hide();
                    }else{
                        $("select[name='taskDateMonths'] option[value='0" + i + "']").hide();
                    }
                }
            } else {
                for (var i = 1; i < nowMonth + 1; i++) {
                    if(i>9){
                        $("select[name='taskDateMonths'] option[value='" + i + "']").show();
                    }else{
                        $("select[name='taskDateMonths'] option[value='0" + i + "']").show();
                    }
                }
            }
        }
        if ($scope.examine.taskDateType == "") {
            $("select[name='taskDateMonths']").hide();
            $("select[name='taskDateQuarter']").hide();
            // $("input[name='taskDateYear']").hide();
        }
    };

    $('#TaskOprModel').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });


    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });

    //新增
    $scope.addTask = function () {
        $("#TaskOprModelTitle").html("新增任务");
        $('#TaskOprModel').modal("show");
        $scope.examine = {};
        $("[name='ObjName']").val("");
        $("select[name='taskDateMonths']").hide();
        $("select[name='taskDateQuarter']").hide();
        $scope.KhTaskId = "";//操作变量赋值
        $.fn.mycity("ObjShen", "ObjShi", "ObjXian", regionId);
        vm.files[0] = {};
        $("input[name='taskType']").prop("checked", false);
        $(".form-group").removeClass("has-success");
        $(".form-group textarea").removeClass("ng-valid");
        $(".form-group textarea").removeClass("ng-dirty");
        $(".form-group textarea").removeClass("ng-valid-parse");
        $(".form-group textarea").removeClass("ng-touched");
        $(".DataValidTip").hide();
        $("#TaskOprModel").find("input").css("border-color", "#ccc");
        $("#TaskOprModel").find("select").css("border-color", "#ccc");
    };

    //修改
    $scope.updateTask = function () {
        $(".DataValidTip").hide();
        $("#TaskOprModel").find("input").css("border-color", "#ccc");
        $("#TaskOprModel").find("select").css("border-color", "#ccc");
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for(var i=0; i< rows.length; i++){
            if(rows[i].ENABLE.indexOf("未开始") == -1){
                $.jBox.tip("只能修改未开始的任务");
                return false;
            }
        }
        if (rows[0].ENABLE == "时间截止") {
            $.jBox.tip("任务时间已经截止，无法修改任务");
            return;
        }
        $scope.KhTaskId = rows[0].ID;//操作变量赋值
        $("#TaskOprModelTitle").html("修改任务");
        $('#TaskOprModel').modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/tspinstask/queryById",
            method: "post",
            data: {"id": $scope.KhTaskId}
        }).success(function (json) {
            //任务类型的回显
            json.data.inspectionTask.taskType.split(',').map(function (type) {
                $("input[name = 'taskType'][value=" + type + "]").prop('checked', true);
            });
            var person = json.data.inspectionAssess;//巡查人员
            var personName = "";
            if (person != null && person != undefined) {
                for (var i = 0; i < person.length; i++) {
                    if (personName != "") {
                        personName += "," + person[i].userName;
                    } else {
                        personName += person[i].userName;
                    }
                    var map = {
                        id: person[i].userId,
                        userName: person[i].userName
                    };
                    $scope.selects.push(map);
                }
            }
            $("input[name = 'ObjName']").val(personName);
            //任务
            $scope.examine = json.data.inspectionTask;
            //上传附件的回显
            vm.files[0] = {
                src:$scope.examine.attachmentAddress,
                name:$scope.examine.attachmentName
            }
            $scope.taskDateType();//考核时间显示
            $.fn.mycity("ObjShen", "ObjShi", "ObjXian", json.data.inspectionTask.inspectionAreaId);//巡查区划
        }).error(function (data) {
            
        });
    };

    /**
     * 手动表单验证
     * */
    window.SdDateValid = Object;
    //清除关联
    SdDateValid.cleanGLValid = function () {
        $("input[name='taskDateYear']").next().hide();
        $("select[name='taskDateQuarter']").next().hide();
        $("select[name='taskDateMonths']").next().hide();
    };
    //关联判断状态
    SdDateValid.ValidByCode = function (type, name) {
        if ($(type + "[name='" + name + "']").val() == "") {
            $(type + "[name='" + name + "']").next().show();
            $(type + "[name='" + name + "']").css("border-color", "#a94442");
        } else {
            $(type + "[name='" + name + "']").next().hide();
            $(type + "[name='" + name + "']").css("border-color", "#3c763d");
        }
    };
    //状态展示
    SdDateValid.DataValid = function () {
        //下面定义的变量用来控制季度和月度的范围，根据所选年份来控制，另外一处任务类型选择也要进行处理
        var date = new Date();
        var year = date.getFullYear();//当前年份
        var nowMonth = date.getMonth();//当前月份
        var quarter = parseInt(nowMonth / 3);
        var taskYear = $("input[name='taskDateYear']").val();
        //巡查人员
        if ($("input[name='ObjName']").val() == "") {
            $("input[name='ObjName']").next().show();
            $("input[name='ObjName']").css("border-color", "#a94442");
        } else {
            $("input[name='ObjName']").next().hide();
            $("input[name='ObjName']").css("border-color", "#3c763d");
        }
        //考核时间
        var taskDateType = $("#taskDateType").val();
        if (taskDateType == "年度") {
            if ($("input[name='taskDateYear']").val() == "") {
                $("input[name='taskDateYear']").next().show();
                $("input[name='taskDateYear']").css("border-color", "#a94442");
            } else {
                $("input[name='taskDateYear']").next().hide();
                $("input[name='taskDateYear']").css("border-color", "#3c763d");
            }
        } else if (taskDateType == "季度") {
            //已选择任务年份，并且是当前年才进行此操作
            if (taskYear != null && taskYear != "" && taskYear != undefined && taskYear == year) {
                for (var i = 1; i < quarter + 1; i++) {
                    $("select[name='taskDateQuarter'] option[value='" + i + "']").hide();
                }
                if ($("select[name='taskDateQuarter']").val() < quarter + 1) {
                    $("select[name='taskDateQuarter']").val("")
                }
            } else {
                for (var i = 1; i < quarter + 1; i++) {
                    $("select[name='taskDateQuarter'] option[value='" + i + "']").show();
                }
            }
            if ($("input[name='taskDateYear']").val() == "") {
                $("input[name='taskDateYear']").next().show();
                $("input[name='taskDateYear']").css("border-color", "#a94442");
            } else {
                $("input[name='taskDateYear']").next().hide();
                $("input[name='taskDateYear']").css("border-color", "#3c763d");
            }
            if ($("select[name='taskDateQuarter']").val() == "") {
                $("select[name='taskDateQuarter']").next().show();
                $("select[name='taskDateQuarter']").css("border-color", "#a94442");
            } else {
                $("select[name='taskDateQuarter']").next().hide();
                $("select[name='taskDateQuarter']").css("border-color", "#3c763d");
            }
        } else if (taskDateType == "月度") {
            //已选择任务年份，并且是当前年才进行此操作
            if (taskYear != null && taskYear != "" && taskYear != undefined && taskYear == year) {
                for (var i = 1; i < nowMonth + 1; i++) {
                    if(i>9){
                        $("select[name='taskDateMonths'] option[value='" + i + "']").hide();
                    }else{
                        $("select[name='taskDateMonths'] option[value='0" + i + "']").hide();
                    }

                }
                if ($("select[name='taskDateMonths']").val() < nowMonth + 1) {
                    $("select[name='taskDateMonths']").val("")
                }
            } else {
                for (var i = 1; i < nowMonth + 1; i++) {
                    if(i>9){
                        $("select[name='taskDateMonths'] option[value='" + i + "']").show();
                    }else{
                        $("select[name='taskDateMonths'] option[value='0" + i + "']").show();
                    }
                }
            }
            if ($("input[name='taskDateYear']").val() == "") {
                $("input[name='taskDateYear']").next().show();
                $("input[name='taskDateYear']").css("border-color", "#a94442");
            } else {
                $("input[name='taskDateYear']").next().hide();
                $("input[name='taskDateYear']").css("border-color", "#3c763d");
            }
            if ($("select[name='taskDateMonths']").val() == "") {
                $("select[name='taskDateMonths']").next().show();
                $("select[name='taskDateMonths']").css("border-color", "#a94442");
            } else {
                $("select[name='taskDateMonths']").next().hide();
                $("select[name='taskDateMonths']").css("border-color", "#3c763d");
            }
        }

    };
    //判断标识
    SdDateValid.ReturnValid = function () {
        if ($("input[name='ObjName']").val() == "") {
            return false;
        }

        var taskDateType = $("#taskDateType").val();
        if (taskDateType == "年度") {
            if ($("input[name='taskDateYear']").val() == "") {
                return false;
            }
        } else if (taskDateType == "季度") {
            if ($("input[name='taskDateYear']").val() == "") {
                return false;
            }
            if ($("select[name='taskDateQuarter']").val() == "") {
                return false;
            }
        } else if (taskDateType == "月度") {
            if ($("input[name='taskDateYear']").val() == "") {
                return false;
            }
            if ($("select[name='taskDateMonths']").val() == "") {
                return false;
            }
        }
        return true;
    };

    //任务保存
    $scope.saveTask = function () {
        $scope.taskTypeTouched = true;
        var types = [];
        var typesName = [];
        $('[name="taskType"]:checked').each(function () {
            types.push($(this).val());
        });
        var type = "";
        for (var i = 0; i < types.length; i++) {
            if (types[i] != undefined) {
                if (type != "") {
                    type += "," + types[i];
                } else {
                    type = types[i];
                }
            }
        }
        $('[name="taskType"]:checked').each(function () {
            typesName.push($(this).next("span").text())
        });
        var typeName = "";
        for (var i = 0; i < typesName.length; i++) {
            if (typesName[i] != undefined) {
                if (typeName != "") {
                    typeName += "," + typesName[i]
                } else {
                    typeName = typesName[i]
                }
            }
        }
        $scope.examine.taskType = type;
        $scope.examine.taskTypeName = typeName;
        SdDateValid.DataValid();
        if (!$scope.dataFormValidator()) {
            return false;
        }
        if (!SdDateValid.ReturnValid()) {
            return false
        }
        if(!$scope.examine.taskTypeName){
            jBox.tip(" 任务类型为必填!");
            return false;
        }
        $scope.examine.id = $scope.KhTaskId;
        $scope.examine.taskDateYear = $("input[name='taskDateYear']").val();//年份无法直接双向绑定需要手动赋值
        $scope.examine.userIds = JSON.stringify($scope.selects);//巡查人员信息
        var province = $("#ObjShen").val();
        var city = $("#ObjShi").val();
        var county = $("#ObjXian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city + "00";
        } else if (province != null && province != undefined && province != "") {
            areaId = province + "0000";
        }
        $scope.examine.inspectionAreaId = areaId;//巡查区域
        $scope.examine.attachmentAddress = vm.files[0].src;
        $scope.examine.attachmentName = vm.files[0].name;
        if(!$scope.examine.attachmentAddress){
            jBox.tip("附件为必传!");
            return false;
        }
        if($scope.examine.taskDateQuarter == null && $scope.examine.taskDateType == "季度"){
            jBox.tip("请填写季度!");
            return false;
        }
        if($scope.examine.taskDateMonths == null && $scope.examine.taskDateType == "月度"){
            jBox.tip("请填写月度!");
            return false;
        }
        if ($scope.KhTaskId != "") {
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/tspinstask/update",
                method: "post",
                data: $scope.examine
            }).success(function (o) {
                $("button").attr("disabled", false);
                $('#TaskOprModel').modal("hide");
                $.jBox.tip(o.msg);
                $scope.TableReload();
                $scope.dataFormValidatorReset();
            }).error(function (o) {
                $("button").attr("disabled", false);
                $('#TaskOprModel').modal("hide");
                $.jBox.tip("失败");
                $scope.TableReload();
                $scope.dataFormValidatorReset();
            })
        } else {
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/tspinstask/add",
                method: "post",
                data: $scope.examine
            }).success(function (o) {
                $("button").attr("disabled", false);
                $('#TaskOprModel').modal("hide");
                $.jBox.tip(o.msg);
                $scope.TableReload();
                $scope.dataFormValidatorReset();
            }).error(function (o) {
                $("button").attr("disabled", false);
                $('#TaskOprModel').modal("hide");
                $.jBox.tip("失败");
                $scope.TableReload();
                $scope.dataFormValidatorReset();
            })
        }
        $scope.cleanSelected();//清空已选择
    };

    //查看
    window.queryKhTaskInfo = function (taskId) {
        $scope.examineInfo = {};
        $("#TaskInfoModel").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/tspinstask/queryById",
            method: "post",
            data: {"id": taskId}
        }).success(function (json) {
            $scope.examineInfo = json.data.inspectionTask;
            //设置复选框的选中状态
            $scope.examineInfo.person = "无";
            if (json.data.inspectionAssess != null && json.data.inspectionAssess != undefined && json.data.inspectionAssess.length != 0) {
                $scope.examineInfo.person = "";
                for (var i = 0; i < json.data.inspectionAssess.length; i++) {
                    if ($scope.examineInfo.person != "") {
                        $scope.examineInfo.person += "," + json.data.inspectionAssess[i].userName;
                    } else {
                        $scope.examineInfo.person += json.data.inspectionAssess[i].userName;
                    }
                }
            }
            switch ($scope.examineInfo.taskDateType) {
                case "年度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年";
                    break;
                case "月度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年" + json.data.inspectionTask.taskDateMonths + "月";
                    break;
                case "季度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年" + json.data.inspectionTask.taskDateQuarter + "季度";
                    break;
            }
            $scope.examineInfo.inspectionAreaId = $.fn.Cityname(json.data.inspectionTask.inspectionAreaId);//行政区划
        });
    };

    //取消任务
    $scope.cancel = function () {
        var i = $scope.getSelectRowsIdsByDelete();
        if (i != false) {
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $("button").attr("disabled", true);
                    $.ajax({
                        url: "/sofn-asms-web/tspinstask/delete",
                        type: "post",
                        dataType: "json",
                        data: data,
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("button").attr("disabled", false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("删除成功！");
                            $scope.TableReload();
                        },
                        error: function (data) {
                            $("button").attr("disabled", false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("删除失败");
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

    //废止
    $scope.abo = function () {
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $.ajax({
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        url: "/sofn-asms-web/reCheckTask/abo",
                        type: "post",
                        dataType: "json",
                        data: data,
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("废止成功！");
                            $scope.TableReload();
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

    /**
     *----------other------------
     * @author LiBling
     * */


    //获取选择行记录id集合(用于取消或删除操作)
    $scope.getSelectRowsIdsByDelete = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        for(var i=0; i< rows.length; i++){
            if(rows[i].ENABLE.indexOf("未开始") == -1){
                $.jBox.tip("只能删除未开始的任务");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            if (rows[i].ENABLE == "时间截止") {
                $.jBox.tip("选中任务中包含时间已截止任务，无法对任务进行删除，请选择时间未截止任务!");
                return false;
            }
            ids.push(rows[i].ID);
        }
        return ids;
    };

    //获取选择行记录id集合
    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        return ids;
    };

    //查询条件重置
    $scope.cleanQueryPar = function () {
        $("select[name='taskType']").val("");
        $("select[name='taskDateType']").val("");
        $.fn.mycity("querySheng", "queryShi", "queryXian", regionId);
    };
    var vm = this;

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-asms-web/asmsFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        accept:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,image/*,application/pdf',
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };
    //附件下载
    window.downloadFileName=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.examineInfo.attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.examineInfo.attachmentName);
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
});
