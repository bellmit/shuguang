var myapp = angular.module("myapp", []);
myapp.controller("samplePlanConfig", function ($scope, $http, $state) {


    var token = window.localStorage.getItem("token");

    var samplePlanId = window.localStorage.getItem("samplePlanId");

    var monitorTaskId = window.localStorage.getItem("monitorTaskId");

    var taskStatus = window.localStorage.getItem("taskStatus");

    $scope.disabled = false;

    if (taskStatus != '0'){
        $scope.disabled = true;
    }
    $scope.checkItems = [];

    $scope.productId = [];

    var namesValue;
    var numbersValue;
    var productsIds;

    $.fn.mycity("sheng", "shi", "xian");

    $.fn.mycity("sheng1", "shi1", "xian1");

    $scope.toCheck = function () {
        $("#grid").dataTable().api().ajax.reload();
    };

    $http({
        url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method: "post",
        params: {"token": token, "monitorTaskId": monitorTaskId, "monitorClass": ""}
    }).success(function (result) {
        var startTime = result.adsMonitorTask[0].startTime;
        var deadline = result.adsMonitorTask[0].deadline;//上报截至时间
        $("#startTime").val(startTime);
        $("#deadline").val(deadline);

        /* $("#modfystartTime").val(startTime);
         $("#modfydeadline").val(deadline);*/
    });

    $scope.clear = function () {
        $("#samplePlanMember").val("");
        $("#sheng1").val("");
        $("#shi1").val("");
        $("#xian1").val("");
    }

    //获取organID
    $http({
        url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
        method: "post",
        dataType: "json",
        params: {"token": token}
    }).success(function (json) {
        if (json.httpCode == 200) {
            $scope.orgId = json.user.orgId;
            $scope.organName = json.user.orgName;
            $scope.organizationId = json.user.organizationId;
            $scope.organization = json.user.organization;
        }
    }).error(function () {
    });

    //检测对象包分页查询显示
    $(function () {
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getSamplePlanConfigList",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.samplePlanMember = $("#samplePlanMember").val();
                    params.status = $("#status").val();
                    ObjShen = $("select[name='ObjShen1']").val();
                    ObjShi = $("select[name='ObjShi1']").val();
                    ObjXian = $("select[name='ObjXian1']").val();
                    if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                        if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                            if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                                params.cityCode = ObjXian
                            } else {
                                params.cityCode = ObjShi
                            }
                        } else {
                            params.cityCode = ObjShen
                        }
                    }
                    params.samplePlanId = samplePlanId;
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='" + row.id + "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "userNmames",
                    title: "抽样人员"
                },
                {
                    data: "areaId",
                    title: "计划抽样地区",
                    render: function (data, type, row) {
                        data = $.fn.Cityname(data);
                        return data;
                    }
                },
                {
                    data: "sampleObjectName",
                    title: "抽样对象及数量"
                },
                {
                    data: "dateStart",
                    title: "计划抽样开始时间"
                },
                {
                    data: "dateEnd",
                    title: "计划抽样结束时间"
                },
                {
                    data: "id",
                    title: "数据操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a class="pointer mr15" onclick="configSamplePlan(\'' + row.id + '\') ">查看</a>';
                        if (taskStatus == '0'){
                            var look_b = '<a class="pointer mr15" onclick="modfySamplePlan(\'' + row.id + '\') ">修改</a>';
                        }else {
                            var look_b = '<span class="mr15">修改</span>';
                        }

                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    $scope.openChooseObjectModalWindow = function () {
        $scope.productId = [];
        if (numbersValue != undefined) {
            var numarry = numbersValue.split(",");
            var productarry = productsIds.split(",");
            for (var i = 0; i < numarry.length; i++) {
                var map = {
                    id: productarry[i],
                    productNum: numarry[i]
                };
                $scope.productId.push(map);
            }
        }
        if ($scope.stand_flagObject == 0) {
            $scope.openChooseObjectModal();
        } else {
            $("#sampleObjectGrid").dataTable().api().ajax.reload();
        }
        $('#sample_object_modal').modal("show");
    }
    $scope.openChooseObjectModal = function () {
        //bootstrap表格
        $("#sampleObjectGrid").dataTable({
            "paging": false, // 禁止分页
            /* scrollY: 400,*/
            "info": false,
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/getItemAndNum",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params = params || {};
                    params.monitorId = monitorTaskId;
                    params.token = token;
                    return params;
                },
                type: "post"
            },
            columns: [{
                title: function (data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAll">';
                },
                data: "checkOne",
                render: function (data, type, row) {
                    if (type === 'display') {

                        var view2 = '<input type="checkbox" name="checkOne" id="' + row.productId + '">';
                        for (var i = 0; i < $scope.productId.length; i++) {
                            if ($scope.productId[i] != undefined && row.productId == $scope.productId[i].id) {
                                view2 = '<input type="checkbox" name="checkOne" checked="checked" id="' + row.productId + '">';
                            }
                        }
                        return view2;
                    }
                    return data;
                },
                className: "text-center",
                width: "5%"
            }, {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                className: "text-center",
                width: "10%"
            }, {
                title: "名称",
                width: "20%",
                className: "text-center",
                data: "sampleObjectName"
            },
                {
                    data: "alias",
                    title: "数量",
                    width: "10%",
                    render: function (data, type, row, meta) {

                        var view1 = '<input type="text" id="row_' + row.productId + '" name="objectNum" onkeyup="this.value=this.value.replace(/[^0-9.]/g,\'\')" maxlength="4">';
                        for (var i = 0; i < $scope.productId.length; i++) {
                            if ($scope.productId[i] != undefined && row.productId == $scope.productId[i].id) {
                                view1 = '<input type="text" id="row_' + row.productId + '" name="objectNum" onkeyup="this.value=this.value.replace(/[^0-9.]/g,\'\')" maxlength="4" value="' + $scope.productId[i].productNum + '">';
                            }
                        }
                        return view1;
                    }
                },
                {
                    data: "productId",
                    title: "产品ID",
                    width: "10%",
                    visible: false
                }]
        });
        $scope.stand_flagObject = 1;
    }

    $scope.selectSampleNum = function () {
        $('#selectSampleNumModal').modal("show");
    }

    $scope.getSamplePlanUser = function () {

        var num = $("#samplePlanNumber").val();
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/getSamplePlanUser",
            type: "post",
            dataType: "json",
            data: {
                "organization": $scope.organizationId,
                "num": num,
                "token": token
            },
            success: function (data) {
                var str = "";
                var ids = "";
                var list = data.data;
                if (data.httpCode == 200) {
                    for (var i = 0; i < list.length; i++) {
                        var id = list[i].id;
                        var userName = list[i].userName;
                        str += userName + ",";
                        ids += id + ",";
                    }
                    str = str.substring(0, str.lastIndexOf(','));
                    ids = ids.substring(0, ids.lastIndexOf(','));

                    var strParam = str.split(",");
                    var idsParam = ids.split(",");
                    $scope.selectedUsers = [];
                    for (var x=0;x<strParam.length;x++){
                        var userName = strParam[x];
                        var userId = idsParam[x];
                        var user = {id: userId, name: userName};
                        $scope.selectedUsers.push(user);
                    }

                    $("#sampleMember").val(str);
                    $("#userIds").val(ids);

                    $("#modfysampleMember").val(str);
                    $("#modfyuserIds").val(ids);
                    $('#selectSampleNumModal').modal("hide");
                    userNameIdParam = ids;
                    //$('#addObjectPackageModal').modal("show");
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("抽样人员大于机构所有人员，请重新选择", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    }
    //新增检测对象包对话框
    $scope.addObjectPackage = function () {

        $("#sampleMember").val("");
        $("#sampleObject").val("");
        $("#sheng").val("");
        $("#shi").val("");
        $("#xian").val("");
        $("#datebegin").val("");
        $("#dateend").val("");
        userNameIdParam = "";
        numbersValue = "";
        productsIds = "";
        $('#addObjectPackageModal').modal("show");
    };

    $scope.stand_flag = 0;//加载标识
    $scope.stand_flagObject = 0;//加载标识抽样对象选择


    //var namesArray;
    var namesArray = new Array();
    var userNameIdParam;

    $scope.selectedUsers = new Array();

    // 新增时候打开抽样人员选择弹框
    $scope.openChooseUserModal = function () {
        $scope.checkItems = [];
        if (userNameIdParam != undefined) {
            var useridArray = userNameIdParam.split(",");
            for (var i = 0; i < useridArray.length; i++) {
                var map = {
                    id: useridArray[i]
                };
                $scope.checkItems.push(map);
            }
        }
        if (namesArray.length > 0) {
            $("input[type='checkbox']").removeAttr("checked");
        }

        if ($scope.stand_flag == 0) {
            $scope.loadSampleUsers();
        } else {
            $("#usersGrid").dataTable().api().ajax.reload();
        }

        var str = $("#sampleMember").val();
        var modfystr = $("#modfysampleMember").val();
        /*alert(str);
         alert(modfystr);*/
        if (str==""){
            $scope.selectedUsers = [];
        }
        $('#sample_person_modal').modal('show');
    }

    // 修改时候打开抽样人员选择弹框
    $scope.openChooseUserModalModify = function () {
        $scope.checkItems = [];
        if (userNameIdParam != undefined) {
            var useridArray = userNameIdParam.split(",");
            for (var i = 0; i < useridArray.length; i++) {
                var map = {
                    id: useridArray[i]
                };
                $scope.checkItems.push(map);
            }
        }
        if (namesArray.length > 0) {
            $("input[type='checkbox']").removeAttr("checked");
        }

        if ($scope.stand_flag == 0) {
            $scope.loadSampleUsers();
        } else {
            $("#usersGrid").dataTable().api().ajax.reload();
        }

        $('#sample_person_modal').modal('show');
    }
    $scope.chooseSamplePerson = function () {
        // 选择抽样人员
        namesArray = new Array();
        var userIdsArray = new Array();
        for (var i = 0; i < $scope.selectedUsers.length; i++) {
            namesArray.push($scope.selectedUsers[i].name);
            userIdsArray.push($scope.selectedUsers[i].id);
        }

        $scope.samplePerson = namesArray.toString();
        $scope.modfysampleMember = namesArray.toString();
        $("#userIds").val(userIdsArray.toString());
        $("#modfyuserIds").val(userIdsArray.toString());
        $('#sample_person_modal').modal('hide');
        userNameIdParam = userIdsArray.toString();

    }

    $scope.getSelectedUserName = function (tableId) {
        var rows = $("#" + tableId).DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var names = [];
        for (var i = 0; i < length; i++) {
            names.push(rows[i].userName);
        }

        return names;
    }

    $scope.getuserIds = function (tableId) {
        var rows = $("#" + tableId).DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var userIds = [];
        for (var i = 0; i < length; i++) {
            userIds.push(rows[i].id);
        }

        return userIds;
    }

    $scope.chooseSampleObject = function () {

        var selrows = $("#sampleObjectGrid").find("input[type='checkbox']:checked");
        selrows.parents("tr").addClass("selected");
        var rows = $("#sampleObjectGrid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }


        var names = [];
        var str = "";
        var productId = [];
        for (var i = 0; i < length; i++) {
            names.push(rows[i].sampleObjectName);
        }

        for (var i = 0; i < length; i++) {
            productId.push(rows[i].productId);
        }

        // alert(productId.toString());
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById(td.productId);
            if (checkbox.checked) {
                var temp = $("#row_" + td.productId).val();
                if (temp == null || temp == "" || temp == undefined || temp == 0) {
                    var parent = $("#row_" + td.productId).parent();
                    alert("第" + parent.prev().prev().text() + "行 [" + parent.prev().text() + "] 数量 必须大于0");
                    return false;
                }
                str += $("#row_" + td.productId).val() + ",";
            }

        }
        var nameValue = names.toString().split(",");
        var inputValue = "";
        if (str != "") {
            str = str.substring(0, str.lastIndexOf(','));
            var strValue = str.split(",");
            //给全局变量设值
            numbersValue = str;
            namesValue = names.toString();
            productsIds = productId.toString();
            for (var i = 0; i < strValue.length; i++) {
                inputValue += nameValue[i] + "(" + strValue[i] + ")" + "、";
            }

            inputValue = inputValue.substring(0, inputValue.lastIndexOf("、"));
            $("#sampleObject").val(inputValue);
            $("#modfysampleObject").val(inputValue);
        }

        $('#sample_object_modal').modal("hide");
    }

    // 加载当前用户同一个机构下的用户数据，供选择抽样人员使用
    $scope.loadSampleUsers = function () {
        //bootstrap表格
        $("#usersGrid").dataTable({
            fnDrawCallback: function () {
                //判断是否全选
                $("#checkAllItem").prop("checked", $("#usersGrid input[name='checkOne']").length == $("#usersGrid input[name='checkOne']:checked").length);
                //点击行内任意内容即选择行
                $("#usersGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");

                            //用户移除
                            var user_id = $(this).parent().find("input[type='checkbox']").attr("userId");
                            for (var i = 0 ; i < $scope.selectedUsers.length ; i++){
                                if($scope.selectedUsers[i] != undefined && user_id == $scope.selectedUsers[i].id){
                                    $scope.selectedUsers.splice(i,1);
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");

                            //用户添加
                            var user_id = $(this).parent().find("input[type='checkbox']").attr("userId");
                            var user_name = $(this).parent().find("input[type='checkbox']").attr("userName");
                            var user = {id: user_id, name: user_name};
                            $scope.selectedUsers.push(user);
                        }
                        //判断是否全选
                        $("#checkAllItem").prop("checked", $("#usersGrid input[name='checkOne']").length == $("#usersGrid input[name='checkOne']:checked").length);
                    });
                })
            },
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/pageingQueryByOrganizationId",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.organizationId = $scope.organizationId;
                },
                type: "post"
            },
            columns: [{
                title: function (data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAllItem" onclick="checkAllItems(this.checked)">';
                },
                data: "checkOne",
                render: function (data, type, row) {
                    if (type === 'display') {
                        var view2 = '<input type="checkbox" userId="' + row.id + '" userName="' + row.userName +'" name="checkOne" onclick="checkSelectThisBox(this)">';
                        /*for (var i = 0; i < $scope.checkItems.length; i++) {
                            if ($scope.checkItems[i] != undefined && row.id == $scope.checkItems[i].id) {
                                view2 = '<input type="checkbox" name="checkOne" checked="checked" onclick="checkSelectThisBox(this)">';
                            }
                        }*/

                        for (var i = 0; i < $scope.selectedUsers.length; i++) {
                            if ($scope.selectedUsers[i] != undefined && row.id == $scope.selectedUsers[i].id) {
                                view2 = '<input type="checkbox" userId="' + row.id + '" userName="' + row.userName +'" name="checkOne" checked="checked" onclick="checkSelectThisBox(this)">';
                            }
                        }
                        return view2;
                    }
                    return data;
                },
                className: "text-center",
                width: "5%"
            }, {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                className: "text-center",
                width: "10%"
            }, {
                data: "userName",
                title: "姓名",
                className: "text-center",
                width: "80%"
            },
                {
                    data: "id",
                    title: "用户ID",
                    className: "text-center",
                    width: "10%",
                    visible: false
                }]
        });

        $scope.stand_flag = 1;
    }


    //检测项全选,全不选
    window.checkAllItems = function (checked) {
        $("#usersGrid input[name='checkOne']").each(function (i, o) {
            $(o).prop("checked", checked);

            //全选与取消 更新用户选择对象
            var user_id = $(o).attr("userId");
            var user_name = $(o).attr("userName");
            var user = {id: user_id, name: user_name};

            for (var i = 0 ; i < $scope.selectedUsers.length ; i++){
                if($scope.selectedUsers[i] != undefined && user_id == $scope.selectedUsers[i].id){
                    $scope.selectedUsers.splice(i,1);
                }
            }
            if(checked)
                $scope.selectedUsers.push(user);

        })
    }

    //针对复选框无法选中bug
    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    }

    $scope.updateSamplePlanConfig = function () {
        var datebegin = $('#modfydatebegin').val();
        var dateend = $('#modfydateend').val();

        var sampleMember = $('#modfysampleMember').val();

        ObjShen = $("select[name='modfysheng']").val();
        ObjShi = $("select[name='modfyshi']").val();
        ObjXian = $("select[name='modfyxian']").val();
        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    var cityCode = ObjXian
                } else {
                    var cityCode = ObjShi
                }
            } else {
                var cityCode = ObjShen
            }
        }

        var sampleObject = $('#modfysampleObject').val();
        var userIds = $('#modfyuserIds').val();
        var modfyconfigid = $("#modfyconfigid").val();
        var productIds = $("#productid").val();
        var objectName = $("#objectName").val();
        var objectNum = $("#objectNum").val();

        if (datebegin == null || datebegin == '') {
            jBox.tip("请输入计划抽样时间", 'info');
            return false;
        }

        if (dateend == null || dateend == '') {
            jBox.tip("请输入计划抽样时间", 'info');
            return false;
        }

        if (sampleMember == null || sampleMember == '') {
            jBox.tip("请输入抽样人员", 'info');
            return false;
        }
        if (ObjShen == null || ObjShen == '') {
            jBox.tip("请输入计划抽样地区", 'info');
            return false;
        }
        if (sampleObject == null || sampleObject == '') {
            jBox.tip("请输入抽样对象及数量", 'info');
            return false;
        }

        var areaName = $.fn.Cityname(cityCode);
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/updateSamplePlanConfig",
            type: "post",
            dataType: "json",
            data: {
                "dateStart": datebegin,
                "dateEnd": dateend,
                "userNmames": sampleMember,
                "areaId": cityCode,
                //"samplePlanId":samplePlanId,
                "sampleObjectName": namesValue,
                "numbers": numbersValue,
                "sampleObjectId": productsIds,
                "areaName": areaName,
                "userIds": userIds,
                "id": modfyconfigid,
                "token": token
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#modfyObjectPackageModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    }


    $scope.saveSamplePlan = function () {
        var datebegin = $('#datebegin').val();
        var dateend = $('#dateend').val();

        if(new Date(Date.parse(datebegin.replace("-", "/"))) > new Date(Date.parse(dateend.replace("-", "/")))){
            jBox.tip("计划抽样时间开始时间不能大于结束时间", 'info');
            return false;
        }

        var sampleMember = $('#sampleMember').val();

        ObjShen = $("select[name='ObjShen']").val();
        ObjShi = $("select[name='ObjShi']").val();
        ObjXian = $("select[name='ObjXian']").val();
        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    var cityCode = ObjXian
                } else {
                    var cityCode = ObjShi
                }
            } else {
                var cityCode = ObjShen
            }
        }

        var sampleObject = $('#sampleObject').val();
        var sampleNum = $('#sampleNum').val();
        var userIds = $('#userIds').val();
        var sampleObject = $("#sampleObject").val();

        if (datebegin == null || datebegin == '') {
            jBox.tip("请输入计划抽样时间", 'info');
            return false;
        }

        if (dateend == null || dateend == '') {
            jBox.tip("请输入计划抽样时间", 'info');
            return false;
        }

        if (sampleMember == null || sampleMember == '') {
            jBox.tip("请输入抽样人员", 'info');
            return false;
        }
        if (ObjShen == null || ObjShen == '') {
            jBox.tip("请输入计划抽样地区", 'info');
            return false;
        }
        if (sampleObject == null || sampleObject == '') {
            jBox.tip("请输入抽样对象及数量", 'info');
            return false;
        }
        var areaName = $.fn.Cityname(cityCode);
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/addSamplePlanConfig",
            type: "post",
            dataType: "json",
            data: {
                "dateStart": datebegin,
                "dateEnd": dateend,
                "userNmames": sampleMember,
                "areaId": cityCode,
                "samplePlanId": samplePlanId,
                "sampleObjectName": namesValue,
                "numbers": numbersValue,
                "sampleObjectId": productsIds,
                "areaName": areaName,
                "userIds": userIds,
                "token": token
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#addObjectPackageModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    };

    window.modfySamplePlan = function (id) {
        $("#modfyObjectPackageModal").modal("show");
        $scope.checkItems = [];
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getSampleConfigInfoByConfigId",
            method: "post",
            dataType: "json",
            params: {"samplePlanId": id, "token": token}
        }).success(function (data) {
            $scope.modfydatebegin = data.data.dateStart;
            $scope.modfydateend = data.data.dateEnd;
            $("#modfydatebegin").val($scope.modfydatebegin);
            $("#modfydateend").val($scope.modfydateend);
            $scope.modfysampleMember = data.data.userNmames;
            var userid = data.data.userIds;
            var id = data.data.id;
            $("#modfyconfigid").val(id);

            $("#modfyuserIds").val(userid);
            var userIds = data.data.userIds;
            var userNmames = data.data.userNmames;

            var useridsParm = userIds.split(',');
            var userNameParam = userNmames.split(',');
            $scope.selectedUsers = [];
            for (var y=0;y<useridsParm.length;y++){
                var usId = useridsParm[y];
                var usname = userNameParam[y];
                var user = {id: usId, name: usname};
                $scope.selectedUsers.push(user);
            }
            //var itemIdArray = userIds.split(',');
            var sampleObjectId = data.data.sampleObjectId;

            userNameIdParam = userIds;
            sampleObjectId = sampleObjectId.substring(0, sampleObjectId.lastIndexOf(','));
            var productid = sampleObjectId.split(",");
            var objectnum = data.data.objectNum;
            objectnum = objectnum.substring(0, objectnum.lastIndexOf(','));

            $scope.productId = [];
            var objectnumparam = objectnum.split(",");
            for (var i = 0; i < productid.length; i++) {
                var map = {
                    id: productid[i],
                    productNum: objectnumparam[i]
                };
                $scope.productId.push(map);
            }
            /* var city = $.fn.Cityname(data.data.areaId);*/
            var areaId = data.data.areaId;
            $.fn.mycity("modfysheng", "modfyshi", "modfyxian", areaId);
            var sampleObjectId = data.data.sampleObjectId;
            sampleObjectId = sampleObjectId.substring(0, sampleObjectId.lastIndexOf(','));
            //$("#productid").val(sampleObjectId);
            var objectName = data.data.objectName;
            objectName = objectName.substring(0, objectName.lastIndexOf(','));
            //给全局变量赋值
            numbersValue = objectnum;
            namesValue = objectName;
            productsIds = sampleObjectId;
            $("#modfysampleObject").val(data.data.sampleObjectName);
        });
    }

    window.configSamplePlan = function (data) {
        $("#showModal").modal("show");
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getSampleConfigInfoByConfigId",
            method: "post",
            dataType: "json",
            params: {"samplePlanId": data, "token": token}
        }).success(function (data) {
            $scope.dateStart = data.data.dateStart;
            $scope.userNmames = data.data.userNmames;
            var city = $.fn.Cityname(data.data.areaId);
            $scope.areaId = city;
            $scope.sampleObjectName = data.data.sampleObjectName;
        });
    }
    $scope.updateSamplePlan = function () {

        var taskName = $('#taskNameModfy').val();
        var samplePlanName = $('#samplePlanNameModfy').val();
        var modyfySampleId = $('#modyfySampleId').val();

        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/updateSamplePlanById",
            type: "post",
            dataType: "json",
            data: {
                "name": samplePlanName,
                "monitorTaskId": taskName,
                "id": modyfySampleId,
                "token": token
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#modifyModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败", 'info');
                }
            }
        });
    }

    //批量删除抽样计划
    window.delSamplePlanInfo = function () {
        var check = $("input[name='task']:checked");
        if (check.length > 0) {
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id = $(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsMonitorSample/delSamplePlanConfigById",
                        type: "post",
                        dataType: "json",
                        data: {"ids": arr.toString(), "token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                jBox.tip("删除成功", 'success');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("删除失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除抽样计划记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先选择抽样计划记录", 'info');
        }
    };
});
