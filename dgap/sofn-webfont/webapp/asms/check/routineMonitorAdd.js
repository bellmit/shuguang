var myapp = angular.module("myapp", []);
myapp.controller("routineMonitorAdd", function ($scope, $http, $state, $q) {

    $scope.routineMonitorId = window.localStorage.getItem("asms_Check_routineMonitor");
    $scope.routineMonitor = {};//例行检测对象
    var currentOrgRequest = $http({
        url: "/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).error(function (data) {
        
    });

    var routineMonitorRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/routineMonitor/getTaskById",
        method: "post",
        data: {
            id: $scope.routineMonitorId
        }
    }).error(function (data) {
        
    });
    //区域Id
    var regionId;
    $q.all([currentOrgRequest]).then(function (data) {
        $("input[name='rmReleaseUnit']").val(data[0].data.data.orgName);
        $scope.provinceVal = "";
        var orgLevel = data[0].data.data.orgLevel;
        regionId = data[0].data.data.regionId;
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", regionId);
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
        $scope.initDataTable();
        $("#checkAll").prop("checked",$("input[name='ck']").length!=0&&$("input[name='ck']").length==$("input[name='ck']:checked").length);
        return data;
    }, function (data) {
        $.jBox.tip("机构信息获取失败！", "error");
    }).then(function (data) {
        $("#OprTitle").html("新增");
        if ($scope.routineMonitorId != null || $scope.routineMonitorId != undefined) {
            $("#OprTitle").html("修改");
            $q.all([routineMonitorRequest]).then(function (data) {
                var person = "";//承担单位
                if (data[0].data.rouList != null && data[0].data.rouList != undefined) {
                    for (var i = 0; i < data[0].data.rouList.length; i++) {
                        if (person != "") {
                            person += "," + data[0].data.rouList[i].leadUnitName;
                        } else {
                            person += data[0].data.rouList[i].leadUnitName;
                        }
                        var map = {
                            leadUnitId: data[0].data.rouList[i].leadUnitId,
                            leadUnitName: data[0].data.rouList[i].leadUnitName
                        };
                        $scope.selects.push(map);
                    }
                }
                $("input[name = 'qtName']").val(person);
                $scope.routineMonitor = data[0].data.data;//任务信息
                $scope.routineMonitor.createTime = new Date(parseInt($scope.routineMonitor.createTime) + 28800000).toISOString().slice(0, 10);
                $scope.routineMonitor.updateTime = new Date(parseInt($scope.routineMonitor.updateTime) + 28800000).toISOString().slice(0, 10);
                $scope.routineMonitor.rmDateBegin = new Date(parseInt($scope.routineMonitor.rmDateBegin) + 28800000).toISOString().slice(0, 10);
                $scope.routineMonitor.rmDateEnd = new Date(parseInt($scope.routineMonitor.rmDateEnd) + 28800000).toISOString().slice(0, 10);
                $("input[name='rmDateBegin']").val($scope.routineMonitor.rmDateBegin);
                $("input[name='rmDateEnd']").val($scope.routineMonitor.rmDateEnd);
                //回调附件
                vm.files = [
                    { //占位
                        hidden: false,
                        src: $scope.routineMonitor.rmFile,
                        name: $scope.routineMonitor.rmFileName
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

    //* 多文件上传
    //* */
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
            // var acceptFileTypes = /^((doc|docx|pdf)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            // if (file.type && file.type != '') {
            //     if (!acceptFileTypes.test(file.type)) {
            //         swal('', '只能上传word类型文档', 'warning');
            //         return false;
            //     }
            // } else {
            //     if (!/\.(doc|docx)$/i.test(file.name)) {
            //         swal('', '只能上传word类型文档', 'warning');
            //         return false;
            //     }
            // }
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
    SdDateValid.saveDataValid = function () {
        //年度
        if ($("input[name='rmYear']").val() == "") {
            $("input[name='rmYear']").next().show();
            $("input[name='rmYear']").css("border-color", "#a94442");
        } else {
            $("input[name='rmYear']").next().hide();
            $("input[name='rmYear']").css("border-color", "#3c763d");
        }
        //开始时间
        if ($("input[name='rmDateBegin']").val() == "") {
            $("input[name='rmDateBegin']").next().show();
            $("input[name='rmDateBegin']").css("border-color", "#a94442");
        } else {
            $("input[name='rmDateBegin']").next().hide();
            $("input[name='rmDateBegin']").css("border-color", "#3c763d");
        }
        //结束时间
        if ($("input[name='rmDateEnd']").val() == "") {
            $("input[name='rmDateEnd']").next().show();
            $("input[name='rmDateEnd']").css("border-color", "#a94442");
        } else {
            $("input[name='rmDateEnd']").next().hide();
            $("input[name='rmDateEnd']").css("border-color", "#3c763d");
        }
        //牵头单位
        if ($("input[name='qtName']").val() == "") {
            $("input[name='qtName']").next().show();
            $("input[name='qtName']").css("border-color", "#a94442");
        } else {
            $("input[name='qtName']").next().hide();
            $("input[name='qtName']").css("border-color", "#3c763d");
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
        if ($("input[name='rmYear']").val() == "") {
            return false;
        }
        //开始时间
        if ($("input[name='rmDateBegin']").val() == "") {
            return false;
        }
        //结束时间
        if ($("input[name='rmDateEnd']").val() == "") {
            return false;
        }
        //牵头单位
        if ($("input[name='qtName']").val() == "") {
            return false;
        }
        return true;
    };
    /***
     * 数据操作
     * */
    $scope.routineMonitorfile = {};//例行检测附件对象
    $scope.infoSave = function () {
        SdDateValid.saveDataValid();
        if (!$scope.dataFormValidator()) {
            return false;
        }
        if (!SdDateValid.ReturnValid()) {
            return false
        }
        //获取附件 路径 名称
        for(var i=0;i<vm.files.length;i++){
            $scope.routineMonitorfile.file=vm.files[i].src;
            $scope.routineMonitorfile.fileName=vm.files[i].name;
        }
        if($scope.routineMonitorfile.file==null||$scope.routineMonitorfile.file==""||$scope.routineMonitorfile.file==undefined){
            jBox.tip("请上传文件！", 'info');
            return false;
        }
        //格式处理
        $scope.routineMonitor.rmFile = $scope.routineMonitorfile.file;
        $scope.routineMonitor.rmFileName = $scope.routineMonitorfile.fileName;
        // $scope.routineMonitor.token = window.localStorage.getItem("token");
        $scope.routineMonitor.id = $scope.routineMonitorId;
        $scope.routineMonitor.rmYear = $("input[name='rmYear']").val();
        $scope.routineMonitor.orgs = JSON.stringify($scope.selects);
        $scope.routineMonitor.rmDateBegin = new Date(($("input[name='rmDateBegin']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.routineMonitor.rmDateEnd = new Date(($("input[name='rmDateEnd']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.routineMonitor.rmType = "例行监测";
        $scope.routineMonitor.rmReleaseUnit = $("input[name='rmReleaseUnit']").val();
        if ($scope.routineMonitor.createTime != null && $scope.routineMonitor.createTime != "" && $scope.routineMonitor.createTime != undefined) {
            $scope.routineMonitor.createTime = new Date(($scope.routineMonitor.createTime + " 00:00:00").replace(/-/g, "/"));
        }
        if ($scope.routineMonitor.updateTime != null && $scope.routineMonitor.updateTime != "" && $scope.routineMonitor.updateTime != undefined) {
            $scope.routineMonitor.updateTime = new Date(($scope.routineMonitor.updateTime + " 00:00:00").replace(/-/g, "/"));
        }
        if ($scope.routineMonitorId != null || $scope.routineMonitorId != undefined) {
            $("button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/routineMonitor/update",
                type: "post",
                dataType: "json",
                data: $scope.routineMonitor,
                headers:{
                    token:window.localStorage.getItem("token")
                },
                beforeSend: function () {
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    if (data.httpCode == 200) {
                        $.jBox.tip("成功！");
                        $state.go("index.content.asms/check/routineMonitor");
                    }else if (data.httpCode == 400){
                        $.jBox.tip(data.msg);
                    }
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
                url: "/sofn-asms-web/routineMonitor/add",
                type: "post",
                dataType: "json",
                data: $scope.routineMonitor,
                headers:{
                    token:window.localStorage.getItem("token")
                },
                beforeSend: function () {
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {

                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    if (data.httpCode == 200) {
                        $.jBox.tip("成功！");
                        $state.go("index.content.asms/check/routineMonitor");
                    } else if(data.httpCode == "418"){
                        $.jBox.tip("新增失败, 任务数被限制！");
                    }else if(data.httpCode == 400){
                        $.jBox.tip(data.data);
                    }else{
                        $.jBox.tip(data.msg);
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
     * ---------检测机构选择列表--------------
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
        $("input[name='qtName']").val(name);
        $("#selectModal").modal('hide');
        SdDateValid.ValidByCode("input", "qtName");
    };

//表格
    $scope.initDataTable = function () {
        $("#selectGrid").dataTable({
            fnDrawCallback: function () {
                //判断是否全选
                $("#checkAll").prop("checked",$("input[name='ck']").length==$("input[name='ck']:checked").length&&$("input[name='ck']").length>0);
                $("#selectGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0; i < $scope.selects.length; i++) {
                                if ($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId) {
                                    delete $scope.selects[i];
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //选择记录
                            /**
                             * 使用leadUnitId与leadUnitName字段方便映射
                             * */
                            var map = {
                                leadUnitId: $(this).parent().find("[name='ck']").val(),
                                leadUnitName: $(this).parent().find("[name='name']").html()
                            };
                            $scope.selects.push(map);
                        }
                        //判断是否全选
                        $("#checkAll").prop("checked",$("input[name='ck']").length==$("input[name='ck']:checked").length&&$("input[name='ck']").length>0);
                    });
                })
            },
            ajax: {
                url: "/sofn-asms-web/asmsTools/getOrgsListByUserToken",

                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.data;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != "") {
                        params.dtAreaId = $("#selectQueryXian").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == "") {
                        params.dtAreaId = $("#selectQueryShi").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == "") {
                        params.dtAreaId = $("#selectQueryShen").val();
                    }
                    params.power = "limit";
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    params.dtName = $("input[name='selectQueryName']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title : '<input id="checkAll" onclick="checkAll(this.checked)" type=checkbox>',
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].leadUnitId) {
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
                    width:"5%"
                },
                {
                    data: "dtName",
                    title: "单位名称",
                    render: function (data, type, row) {
                        return '<span name = "name">' + data + '</span>';
                    },
                    width:"45%"
                },
                {
                    data: "dtType",
                    title: "机构类别",
                    width:"10%"
                },
                {
                    data: "dtAddress",
                    title: "所属区域",
                    width:"35%"
                }
            ]
        });
    };

    $("#selectTocheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
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

    //牵头单位全选,全不选
    window.checkAll = function(checked){
        //清空本页牵头单位
        $("input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            $("input[name='ck']").each(function(i,o) {
                var u_d =  $(o).val();
                for (var i = 0; i < $scope.selects.length; i++) {
                    if ($scope.selects[i] != undefined && u_d == $scope.selects[i].leadUnitId) {
                        delete $scope.selects[i];
                    }
                }
            })
        })
        //选中本页已选择牵头单位
        if(checked){
            $("input[name='ck']:checked").each(function(i,o) {
                var map = {
                    leadUnitId: $(o).val(),
                    leadUnitName: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }

//查询条件重置
    $scope.cleanSelectModelQuery = function () {
        //点击间隔
        $scope.btnReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnReloadDisabled = false);
        }, 500);
        $("input[name='selectQueryName']").val("");
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian",regionId);
        $("#selectGrid").dataTable().api().ajax.reload();
    };

//清空已选择
    $scope.cleanSelected = function () {
        //点击间隔
        $scope.btnClearDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnClearDisabled = false);
        }, 500);
        $scope.selects = null;
        $scope.selects = new Array();
        //关闭窗口后，会再次校验
        $("input[name='qtName']").val('');
        SdDateValid.ValidByCode("input", "qtName");
        $('#selectGrid').dataTable().api().ajax.reload();
    };

    /***
     * other
     * */
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("asms_Check_routineMonitor");
    };

})
;
