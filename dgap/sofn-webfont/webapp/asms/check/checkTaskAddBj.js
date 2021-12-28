var myapp = angular.module("myapp", []);
myapp.controller("checkTaskAddBj", function ($scope, $http, $state, $q) {

    $scope.checkTaskId = window.localStorage.getItem("asms_Check_BjTask");
    $scope.checkTask = {};


    var currentOrgRequest = $http({
        url: "/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).error(function (data) {
        
    });

    var checkTaskRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/checkTask/getTaskById",
        method: "post",
        data: {
            id: $scope.checkTaskId
        }
    }).error(function (data) {
        
    });

    $q.all([currentOrgRequest]).then(function (data) {
        $("input[name='taskReleaseUnit']").val(data[0].data.data.orgName);
        $scope.provinceVal = "";
        var orgLevel = data[0].data.data.orgLevel;
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", data[0].data.data.regionId.substr(0,2*(orgLevel-1)));
        switch (orgLevel) {
            case "2":
                $("#selectQueryShen").attr({"disabled": "disabled"});
                break;
            case "3":
                $("#selectQueryShen").attr({"disabled": "disabled"});
                $("#selectQueryShi").attr({"disabled": "disabled"});
                break;
            case "4":
                $("#selectQueryShen").attr({"disabled": "disabled"});
                $("#selectQueryShi").attr({"disabled": "disabled"});
                $("#selectQueryXian").attr({"disabled": "disabled"});
                break;
        }
        return data;
    }, function (data) {
        $.jBox.tip("机构信息获取失败！", "error");
    }).then(function (data) {
        $("#OprTitle").html("新增");
        if ($scope.checkTaskId != null || $scope.checkTaskId != undefined) {
            $("#OprTitle").html("修改");
            $q.all([checkTaskRequest]).then(function (data) {
                var person = "";//承担单位
                if (data[0].data.speList != null && data[0].data.speList != undefined) {
                    for (var i = 0; i < data[0].data.speList.length; i++) {
                        if (person != "") {
                            person += "," + data[0].data.speList[i].leadUnitName;
                        } else {
                            person += data[0].data.speList[i].leadUnitName;
                        }
                        var map = {
                            superviseBearUnitId: data[0].data.speList[i].superviseBearUnitId,
                            leadUnitName: data[0].data.speList[i].leadUnitName
                        };
                        $scope.selects.push(map);
                    }
                }
                $("input[name = 'cdName']").val(person);
                $scope.checkTask = data[0].data.data;//任务信息
                $("input[name='taskyear']").val($scope.checkTask.taskYear);
                $scope.checkTask.createTime = new Date(parseInt($scope.checkTask.createTime) + 28800000).toISOString().slice(0, 10);
                $scope.checkTask.updateTime = new Date(parseInt($scope.checkTask.updateTime) + 28800000).toISOString().slice(0, 10);
                $scope.checkTask.taskBeginTime = new Date(parseInt($scope.checkTask.taskBeginTime) + 28800000).toISOString().slice(0, 10);
                $scope.checkTask.taskEndTime = new Date(parseInt($scope.checkTask.taskEndTime) + 28800000).toISOString().slice(0, 10);
                $("#taskBeginTime").val($scope.checkTask.taskBeginTime);
                $("#taskEndTime").val($scope.checkTask.taskEndTime);
                //回调附件
                vm.files = [
                    { //占位
                        src: $scope.checkTask.attachmentAddress,
                        name: $scope.checkTask.attachmentName
                    }
                ];

            }, function (data) {
                $.jBox.tip("任务信息获取失败！", "error");
            });
        }
    });


    /**
     * 表单验证
     * */
    $('#attributeForm').bootstrapValidator();
    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };



    //***
    //* 多文件上传
    //* */
    var vm = this;



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
    /**
     * 手动表单验证
     * */
    window.SdDateValid = Object;
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
    SdDateValid.saveDataValid = function () {
        //年度
        if ($("input[name='taskyear']").val() == "") {
            $("input[name='taskyear']").next().show();
            $("input[name='taskyear']").css("border-color", "#a94442");
        } else {
            $("input[name='taskyear']").next().hide();
            $("input[name='taskyear']").css("border-color", "#3c763d");
        }
        //开始时间
        if ($("input[name='taskBeginTime']").val() == "") {
            $("input[name='taskBeginTime']").next().show();
            $("input[name='taskBeginTime']").css("border-color", "#a94442");
        } else {
            $("input[name='taskBeginTime']").next().hide();
            $("input[name='taskBeginTime']").css("border-color", "#3c763d");
        }
        //结束时间
        if ($("input[name='taskEndTime']").val() == "") {
            $("input[name='taskEndTime']").next().show();
            $("input[name='taskEndTime']").css("border-color", "#a94442");
        } else {
            $("input[name='taskEndTime']").next().hide();
            $("input[name='taskEndTime']").css("border-color", "#3c763d");
        }
        //承担单位
        if ($("input[name='cdName']").val() == "") {
            $("input[name='cdName']").next().show();
            $("input[name='cdName']").css("border-color", "#a94442");
        } else {
            $("input[name='cdName']").next().hide();
            $("input[name='cdName']").css("border-color", "#3c763d");
        }
    };
    //状态展示
    SdDateValid.DataValid = function (o) {
        //年度
        if ($("input[name='" + o.name + "']").val() == "") {
            $("input[name='" + o.name + "']").next().show();
            $("input[name='" + o.name + "']").css("border-color", "#a94442");
        } else {
            $("input[name='" + o.name + "']").next().hide();
            $("input[name='" + o.name + "']").css("border-color", "#3c763d");
        }
    };
    //判断标识
    SdDateValid.ReturnValid = function () {
        //年度
        if ($("input[name='taskyear']").val() == "") {
            return false;
        }
        //开始时间
        if ($("input[name='taskBeginTime']").val() == "") {
            return false;
        }
        //结束时间
        if ($("input[name='taskEndTime']").val() == "") {
            return false;
        }
        //牵头单位
        if ($("input[name='cdName']").val() == "") {
            return false;
        }
        return true;
    };
    $scope.checkTaskfile = {};//例行检测附件对象
    //数据保存
    $scope.infoSave = function () {

        SdDateValid.saveDataValid();
        if (!$scope.dataFormValidator()) {
            return false;
        }
        if (!SdDateValid.ReturnValid()) {
            return false
        }
        for(var i=0;i<vm.files.length;i++){
            $scope.checkTaskfile.file=vm.files[i].src;
            $scope.checkTaskfile.fileName=vm.files[i].name;
        }
        if($scope.checkTaskfile.file==null||$scope.checkTaskfile.file==""||$scope.checkTaskfile.file==undefined){
            jBox.tip("请上传文件！", 'info');
            return false;
        }
        $scope.checkTask.files = $scope.checkTaskfile.file;
        $scope.checkTask.filesname = $scope.checkTaskfile.fileName;
        // $scope.checkTask.token = window.localStorage.getItem("token");
        $scope.checkTask.taskYear = $("input[name='taskyear']").val();
        $scope.checkTask.id = $scope.checkTaskId;
        $scope.checkTask.orgs = JSON.stringify($scope.selects);
        //附件处理
        $scope.checkTask.attachmentAddress = vm.files[0].src;
        $scope.checkTask.attachmentName = vm.files[0].name;
        var d = new Date();
        if ($scope.checkTask.createTime != undefined && $scope.checkTask.createTime != null) {
            $scope.checkTask.createTime = new Date(($scope.checkTask.createTime + " 00:00:00").replace(/-/g, "/"));
            $scope.checkTask.updateTime = new Date(($scope.checkTask.updateTime + " 00:00:00").replace(/-/g, "/"));
        }
        $scope.checkTask.taskSampleDeadline = new Date((d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " 00:00:00").replace(/-/g, "/"));
        $scope.checkTask.taskBeginTime = new Date(($("input[name='taskBeginTime']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.checkTask.taskEndTime = new Date(($("input[name='taskEndTime']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.checkTask.taskType = "监督抽查";
        $scope.checkTask.taskReleaseUnit = $("input[name='taskReleaseUnit']").val();
        if ($scope.checkTaskId != null || $scope.checkTaskId != undefined) {
            $("button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/checkTask/updateBjTask",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.checkTask,
                beforeSend: function (request) {
                    request.setRequestHeader("token", window.localStorage.getItem("token"));
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    $.jBox.tip("成功！");
                    $state.go("index.content.asms/check/checkTask");
                },
                error: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    $.jBox.tip("失败");
                }
            });

        } else {
            $("button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/checkTask/addBjTask",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.checkTask,
                beforeSend: function (request) {
                    request.setRequestHeader("token", window.localStorage.getItem("token"));
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    if(data.httpCode == "418"){
                        $.jBox.tip("新增失败, 任务数被限制！");
                    } else {
                        $.jBox.tip("成功！");
                        $state.go("index.content.asms/check/checkTask");
                    }
                },
                error: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    $.jBox.tip("失败");
                }
            });
        }
    };

    /**
     * ---------监管机构选择列表--------------
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

    $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");

    //确定
    $scope.selectModalQdButt = function () {
        var name = "";
        for (var i = 0; i < $scope.selects.length; i++) {
            if ($scope.selects[i] != undefined) {
                if (name != "") {
                    name += "," + $scope.selects[i].leadUnitName;
                } else {
                    name = $scope.selects[i].leadUnitName;
                }
            }
        }
        $("input[name='cdName']").val(name);
        $("#selectModal").modal('hide');
        SdDateValid.ValidByCode("input", "cdName");
    };

    //表格
    $(function () {
        $("#selectGrid").dataTable({
            fnDrawCallback: function () {
                //判断是否全选
                $("#checkAllCDUnit").prop("checked",$("#selectGrid input[name='ck']").length==$("#selectGrid input[name='ck']:checked").length);
                $("#selectGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0; i < $scope.selects.length; i++) {
                                if ($scope.selects[i] != undefined && u_d == $scope.selects[i].superviseBearUnitId) {
                                    delete $scope.selects[i];
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            var map = {
                                superviseBearUnitId: $(this).parent().find("[name='ck']").val(),
                                leadUnitName: $(this).parent().find("[name='name']").html()
                            };
                            $scope.selects.push(map);
                        }
                        //判断是否全选
                        $("#checkAllCDUnit").prop("checked",$("#selectGrid input[name='ck']").length==$("#selectGrid input[name='ck']:checked").length);
                    });
                })
            },
            ajax: {
                url: "/sofn-asms-web/subjSupervise/getSubjSuperviseList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime)).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != "") {
                        params.areaId = $("#selectQueryXian").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == "") {
                        params.areaId = $("#selectQueryShi").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == "") {
                        params.areaId = $("#selectQueryShen").val();
                    }
                    params.svName = $("input[name='selectQueryName']").val();
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    width: "5%",
                    title: '<input id="checkAllCDUnit" onclick="checkAllCDUnit(this.checked)" type=checkbox>',
                    render: function (data, type) {
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].superviseBearUnitId) {
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
                    },
                    width: "5%"
                },
                {
                    data: "svName",
                    title: "单位名称",
                    width: "45%",
                    render: function (data) {
                        return '<span name = "name">' + data + '</span>';
                    }
                },
                {
                    data: "svType",
                    title: "机构类别",
                    width: "10%"
                },
                {
                    data: "svAreaId",
                    title: "所属区域",
                    width: "35%",
                    render: function (data) {
                        return $.fn.Cityname(data);
                    }
                }
            ]
        });
    });
    //承担单位全选,全不选
    //受检单位全选,全不选
    window.checkAllCDUnit = function(checked){
        //本页受检单位全部移除
        $("#selectGrid input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            var u_d = $(o).val();
            for (var i = 0 ; i < $scope.selects.length ; i++){
                if($scope.selects[i] != undefined && u_d == $scope.selects[i].superviseBearUnitId){
                    $scope.selects.splice(i,1);
                    i--;
                }
            }
        })
        //添加本页全部受检单位
        if(checked){
            $("#selectGrid input[name='ck']:checked").each(function(i,o) {
                var map = {
                    superviseBearUnitId: $(o).val(),
                    leadUnitName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }

    $("#selectTocheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#selectGrid").dataTable().api().ajax.reload();
    });

    //针对复选框无法选中bug
    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    //查询条件重置
    $scope.cleanSelectModelQuery = function () {
        $("input[name='selectQueryName']").val("");
        $("#selectQueryShen").val("");
        $("#selectQueryShi").val("");
        $("#selectQueryXian").val("");
        $("#selectGrid").dataTable().api().ajax.reload();
    };

    //清空已选择
    $scope.cleanSelected = function () {
        $scope.selects = null;
        $scope.selects = new Array();
        $('#selectGrid').dataTable().api().ajax.reload();
    };

    /**
     * other
     * */
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("asms_Check_BjTask");
    };
});
