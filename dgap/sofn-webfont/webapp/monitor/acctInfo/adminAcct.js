var myapp = angular.module("myapp", []);
myapp.controller("adminAcct", function($scope, $http, $state) {

    var token = window.localStorage.getItem("token");
    var dtId = "";
    var status = ""; //机构流程状态（0-没有流程，1-待变更，2-待注销，3-待撤销，4-已注销，5-已撤销）
    var account = "";

    /**
     * 加载当前用户信息
     */
    $scope.loadSysUserInfo=function () {
        $http({
            url:"/sofn-ads-web/acctInfo/findSysUserByToken",
            method:"post",
            params : { "token": token},
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
        }).success(function(data){
            $scope.user = data.user;
            if(data.user.account!=null){
                account = $scope.user.account;
            }
        })
    }

    $scope.loadSysUserInfo();

    var datatable = function($scope, dtId) {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/acctInfo/getChangeListBySvId",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].APPLY_TIME != null && json.list[i].APPLY_TIME != "" && json.list[i].APPLY_TIME != undefined) {
                            json.list[i].APPLY_TIME = new Date(parseInt(json.list[i].APPLY_TIME)).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function(params) {
                    //params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                    //params.svCode="123456";
                    params.svId = dtId;

                },
                type: "post"
            },
            columns: [{
                    //data : "RN",
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "CHANGE_CONTENT",
                    width: '50%',
                    title: "变更内容"
                },
                {
                    data: "APPLY_REASON",
                    title: "变更原因"
                },
                {
                    data: "APPLY_TIME",
                    title: "申请时间"
                },
                //{
                //    data : "APPLY_USER_ID",
                //    title : "申请人"
                //},
                {
                    data: "AUDIT_STATE",
                    title: "审核状态",
                    render: function(data, type, row) {
                        if (data != null && data != "" && data != undefined) {
                            if (data == "0") {
                                return "待审核"
                            } else if (data == "1") {
                                return "已通过";
                            } else if (data == "2") {
                                return "未通过";
                            }
                        } else {
                            return "待审核";
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function(data, type, row) { // 模板化列显示内容
                        return '<a style="cursor: pointer" class="Check-report"  onclick="viewSubjSvChange(\'' + data + '\')">查看</a>'; //转义传值
                    }
                },
            ],
        });
    };

    //查询表单数据
    $scope.queryForm = function() {
        $http({
            url: "/sofn-ads-web/acctInfo/findDetectionByToken",
            method: "post",
            params: { "token": token },
        }).success(function(data) {
            status = data.data.status; //机构流程状态（0-没有流程，1-待变更，2-待注销，3-待撤销，4-已注销，5-已撤销）
            dtId = data.data.id;
            datatable($scope, dtId);
            $scope.subjDt = data.data;
            $scope.subjDt.dtLevelName = getDtLevelName($scope.subjDt.dtLevel); //机构级别名称
            dtAreaId = $scope.subjDt.dtAreaId;
            $scope.subjDt.dtAreaValue = $scope.subjDt.dtArea;
            $.fn.mycity("sheng", "shi", "xian", dtAreaId);

            $scope.subjDt.dtQualifications = $scope.subjDt.dtQualifications;
            $scope.subjDt.dtQualificationsArray = $scope.subjDt.dtQualifications == null ? null :$scope.subjDt.dtQualifications.split("、");

            $scope.queryQualification(); //资质
            $scope.queryNature(); //机构性质
        }).error(function() {});
    }
    $scope.queryForm();

    /**
     * 加载机构类别字典
     */
    $scope.queryOrgType = function() {
        $http({
            url: "/sofn-ads-web/adsDictData/getOrgTypeDictData",
            method: "post"
        }).success(function(data) {
            var orgs = new Array();
            $.each(data.data, function(idx) {
                var a = $(this)[0];
                var d = { name: a.dictName, val: a.id, title: a.dictName };
                orgs.push(d);
            })
            $scope.orgtype = orgs;
            $scope.selectOrgType = $scope.subjDt.dtTypeId;
        })
    }

    /**
     * 加载机构级别字典
     */
    $scope.queryLevel = function() {
        $http({
            url: "/sofn-ads-web/adsDictData/getLevelDictData",
            method: "post"
        }).success(function(data) {
            var level = new Array();
            $.each(data.data, function(idx) {
                var a = $(this)[0];
                var d = { name: a.dictName, val: a.id, title: a.dictName, emoney: a.dictValue };
                level.push(d);
            })
            $scope.level = level;
            $scope.selectLevel = $scope.subjDt.dtLevelName + "级";
        })
    }

    /**
     * 加载资质
     */
    $scope.queryQualification = function() {
        $http({
            url: "/sofn-ads-web/adsDictData/getQualificationsDictData",
            method: "post"
        }).success(function(data) {
            var qual = new Array();
            $.each(data.data, function(idx) {
                var a = $(this)[0];
                var value = false;
                console.log($scope.subjDt.dtQualificationsArray)
                for(t in $scope.subjDt.dtQualificationsArray){
                    if($scope.subjDt.dtQualificationsArray[t] == a.dictName){
                        value = true;
                        break;
                    }
                }

                var d = { name: a.dictName, val: a.id, title: a.dictName ,isChecked:value};
                qual.push(d);
            })
            $scope.qualifications = qual;
        })
    }
    /**
     * 加载机构性质字典
     */
    $scope.queryNature = function() {
        $http({
            url: "/sofn-ads-web/adsDictData/getNatureDictData",
            method: "post"
        }).success(function(data) {
            var nature = new Array();
            $.each(data.data, function(idx) {
                var a = $(this)[0];
                var d = { name: a.dictName, val: a.id, title: a.dictName };
                nature.push(d);
            });
            $scope.nature = nature;
            $scope.selectNature = $scope.subjDt.dtNatureId;
        })
    }
    var vm = this;


    //IE兼容
    if (navigator.appVersion.substring(22, 23) == 9) {
        if ($(".myPlaceholder").val() == "") {
            $(this).val("点击选择查询日期");
        }
    }

    /**
     * 显示新增监测机构主体注销申请模态框
     */
    $scope.addCheckEntityModel = function() {
        if (status != "0") {
            jBox.tip("提示，账号信息正在审核中，不能再次申请注销！", 'info');
            return false;
        }
        $("#cModal").modal("show");
    }

    //备案注销表单验证
    $('#cancelInfo_form').bootstrapValidator();
    $("#cancelInfo").click(function() {
        if (!$("#cancelInfo_form").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行提交操作
        $scope.cancelInfo();
    })

    /**
     * 备案注销
     */
    $scope.cancelInfo = function() {
        var applyReason = $('#applyReasonC').val();
        if (applyReason == null || applyReason == '') {
            jBox.tip("请输入注销原因", "info");
            return false;
        }

        var submit = function(v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/acctInfo/cancelInfo",
                    method: "post",
                    params: { "token": token, "applyReason": applyReason },
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data) {
                    if (data.httpCode == 200) {
                        jBox.tip("注销申请已提交", "success");
                        $("#cModal").modal("hide");
                        setTimeout(loginOut,3000);
                    } else {
                        jBox.tip("注销失败", "error");
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认进行注销吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false } });
    }

    window.loginOut = function () {
        swal({
            title: "退出登录",
            text: "已成功注销账号信息!",
            type: "success",
            timer: 2000,
            showConfirmButton: false
        }, function () {
            swal.close();
            window.localStorage && window.localStorage.clear();
            window.sessionStorage && window.sessionStorage.clear();
            window.location.href = "/";
        });
    }
    /**
     * 查看机构变更历史
     * @param id
     */
    window.viewSubjSvChange = function(id) {
        $scope.subjElChangeId = id;
        setTimeout(function() {
            $http({
                url: "/sofn-ads-web/acctInfo/findSubjDtChangeById",
                method: "post",
                data: { id: $scope.subjElChangeId }
            }).success(function(data) {
                //得到变更前信息
                $scope.before = data.subjDt;
                if($scope.before.dtRelyOnUnit==="null"){
                    $scope.before.dtRelyOnUnit = "";
                }
                if($scope.before.dtLeaderPhone==="null"){
                    $scope.before.dtLeaderPhone = "";
                }
                if($scope.before.dtTechnicalDirector==="null"){
                    $scope.before.dtTechnicalDirector = "";
                }
                if($scope.before.dtParameter==="null"){
                    $scope.before.dtParameter = "";
                }
                if($scope.before.dtQualityDirector==="null"){
                    $scope.before.dtQualityDirector = "";
                }
                if($scope.before.dtContactQQ==="null"){
                    $scope.before.dtContactQQ = "";
                }
                if($scope.before.dtProductRange==="null"){
                    $scope.before.dtProductRange = "";
                }
                if($scope.before.dtContactEmail==="null"){
                    $scope.before.dtContactEmail = "";
                }
                if($scope.before.dtPostcode==="null"){
                    $scope.before.dtPostcode = "";
                }
                //得到变更后信息
                $scope.after = data.subjDtChange;
                if($scope.after.dtRelyOnUnit==="null"){
                    $scope.after.dtRelyOnUnit = "";
                }
                if($scope.after.dtLeaderPhone==="null"){
                    $scope.after.dtLeaderPhone = "";
                }
                if($scope.after.dtTechnicalDirector==="null"){
                    $scope.after.dtTechnicalDirector = "";
                }
                if($scope.after.dtParameter==="null"){
                    $scope.after.dtParameter = "";
                }
                if($scope.after.dtQualityDirector==="null"){
                    $scope.after.dtQualityDirector = "";
                }
                if($scope.after.dtContactQQ==="null"){
                    $scope.after.dtContactQQ = "";
                }
                if($scope.after.dtProductRange==="null"){
                    $scope.after.dtProductRange = "";
                }
                if($scope.after.dtContactEmail==="null"){
                    $scope.after.dtContactEmail = "";
                }
                if($scope.after.dtPostcode==="null"){
                    $scope.after.dtPostcode = "";
                }
                if($scope.before.dtLevel=="0"){
                    $scope.before.dtLevel="部";
                }else if($scope.before.dtLevel=="1"){
                    $scope.before.dtLevel="省";
                }else if($scope.before.dtLevel=="2"){
                    $scope.before.dtLevel="市";
                }else if($scope.before.dtLevel=="3"){
                    $scope.before.dtLevel="县";
                }
                if($scope.after.dtLevel=="0"){
                    $scope.after.dtLevel="部";
                }else if($scope.after.dtLevel=="1"){
                    $scope.after.dtLevel="省";
                }else if($scope.after.dtLevel=="2"){
                    $scope.after.dtLevel="市";
                }else if($scope.after.dtLevel=="3"){
                    $scope.after.dtLevel="县";
                }
                if (data.subjDtChange.auditState == "0") {
                    $scope.after.auditState = "待审核";
                } else if (data.subjDtChange.auditState == "1") {
                    $scope.after.auditState = "已通过";
                } else {
                    $scope.after.auditState = "未通过";
                }

            }).error(function() {});
        }, 300);
        $("#dModal").modal("show");
    }

    $scope.queryList = function() {
        //$("#tb123").treegrid("reload");
        var param = {
            //"parentId": node.id,
            keyword: $scope.keyword,
            typeId: $scope.selectIndustry,
            //"start": 0
        };
        $("#grid").dataTable().api().settings()[0].ajax.data = param;
        $("#grid").dataTable().api().ajax.reload();
        $("#grid").dataTable().api().ajax.reload();
    }
    $scope.addModal = function() {
        $scope.title = "新增";
        $("#add_modal").modal("show");
    }

    /*$scope.updateModal = function (el) {
        var row = $scope.getSelectRow();
        if (row == null || row == "")  return;
        $scope.putRow(row);
        $scope.title = "编辑";
        $("#add_modal").modal("show");
    }*/
    $scope.updateModal = function() {
        //$scope.putRow(row);
        $scope.queryForm();
        if (status === "0") {
            $scope.title = "账号信息 >  机构信息变更";
            $("#add_modal").modal("show");
        } else {
            jBox.tip("提示，账号信息正在审核中，不能再次申请变更！", 'info');
        }
    }


    /**
     * 显示修改密码模态框
     */
    $scope.showUpdatePwdModal=function () {
        $("#pwd_form input").val("");   //清空表单值
        $("#passwordModal").modal("show");
    }


    $('#pwd_form').bootstrapValidator();
    $("#updatePwd").click(function () {
        if (!$("#pwd_form").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行提交操作
        //表单验证
        var oldPwd = $("input[name='oldPwd']").val();
        var newPwd = $("input[name='newPwd']").val();
        var newPwd2 = $("input[name='newPwd2']").val();
        if(account==null || account==''){
            jBox.tip("账号不存在","info");
            return false;
        }
        if(oldPwd==null || oldPwd==''){
            jBox.tip("请输入原始密码","info");
            return false;
        }
        if(newPwd==null || newPwd==''){
            jBox.tip("请输入新密码","info");
            return false;
        }
        if(newPwd2==null || newPwd2==''){
            jBox.tip("请确认新密码","info");
            return false;
        }
        if(newPwd!=newPwd2){
            jBox.tip("新密码两次输入不一致","info");
            return false;
        }
        //提交

        $http({
            url:"/sofn-ads-web/acctInfo/changePwd",
            method:"post",
            params : { "token": token,"account":account,"oldPwd":oldPwd,"newPwd":newPwd},
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("修改成功","success");
                //回到登录页面
                window.location.href = '#/login/login';
                window.location.reload();
            }else{
                jBox.tip("提示，原始密码输入错误，修改失败！","error");
            }
        })
    $("#passwordModal").modal("hide");
    })

    $scope.putRow = function(row) {
        $("input[name='id']").val(row.id);
        $("input[name='typeId']").val(row.typeId);
        $("input[name='productCode']").val(row.productCode);
        $("input[name='useCode']").val(row.useCode);
        $("input[name='name']").val(row.name);
        $("input[name='alias']").val(row.alias);
        $("input[name='enname']").val(row.enname);
        $("input[name='gbCode']").val(row.gbCode);
        $("textarea[name='description']").val(row.description);
    }

    $scope.cancelMain = function() {
        //$scope.getFormJson( $("#add_modal"));
        $("#add_modal").modal("hide");
    };

    $scope.save = function() {
        var params = $scope.subjDt;
        save($state, $http);
    }

    $scope.delete = function() {
        var i = $scope.getSelectRowsIds();

        if (i != false) {
            var submit = function(v, h, f) {
                if (v == true)
                    var params = {
                        jsonIds: JSON.stringify(i)
                    };
                $.ajax({
                    url: "/sofn-sys-web/region/delete",
                    type: "post",
                    dataType: "json",
                    data: params,
                    success: function(resp) {
                        jBox.tip("删除成功", 'info');
                        $scope.queryList();
                        $('#cc').combotree('reload');
                    },
                    fail: function(data) {
                        jBox.tip("删除失败", 'info');
                    }
                });
            };
            $.jBox.confirm("确认修改吗？", "修改提示", submit, { buttons: { '确认': true, '取消': false } });
        }
    }

    $scope.getSelectRowsIds = function() {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        return ids;
    }

    $scope.getSelectRow = function() {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length < 1) {
            alert("请至少选择一行记录！");
            return false;
        } else if (length > 1) {
            alert("只能选择一行记录！");
            return false;
        }
        return rows[0];
    }

    $scope.import = function() {
        $("#file-1").trigger("click");
    }



    //未知
    /*setup()*/
    //var dataTable = datatable($scope,dtId);
    //easytree(dataTable);
    checkBoxChange();

    //使form里面的input只读
    readOnlyInput();

    var dtAreaId = "";

    //获取机构级别名称
    function getDtLevelName(dtLevel) {
        var str = "";
        switch (dtLevel) {
            case "0":
                str = "部";
                break;
            case "1":
                str = "省";
                break;
            case "2":
                str = "市";
                break;
            case "3":
                str = "县";
                break;
        }
        return str;
    }

    //初始化表单验证
    $('#modalForm').bootstrapValidator();
    $("#save").click(function() {
        if (!$("#modalForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //增加机构名称变更
        var dtNameLength = $("input[name='dtName']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;

        var dtAddressLength = $("input[name='dtAddress']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtLeaderLength = $("input[name='dtLeader']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtContactLength = $("input[name='dtContact']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtTechnicalDirectorLength = $("input[name='dtTechnicalDirector']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtParameterLength = $("input[name='dtParameter']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtRelyOnUnitLength = $("input[name='dtRelyOnUnit']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtLegalManLength = $("input[name='dtLegalMan']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtQualityDirectorLength = $("input[name='dtQualityDirector']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var dtProductRangeLength = $("input[name='dtProductRange']").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var applyReasonLength = $("#applyReason").val().replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        var myReg = /^[\u4e00-\u9fa5]+$/; //只允许汉字
        var manReg = /^[\u4e00-\u9fa5a-zA-Z·]{0,60}$/;//人名正则表达式
        var shi = $("#shi option:selected").val();
        var xian = $("#xian option:selected").val();

        if (dtAddressLength > 100) {
            jBox.tip("通讯地址长度过长,允许输入不超过50个汉字或者不超过100个英文字母/数字,请更改输入内容", 'info');
            return false;
        }
        if (myReg.test($("input[name='dtName']").val())){
            if (dtNameLength>60){
                jBox.tip("机构名称名字过长,允许输入不超过60个汉字,请更改输入内容", 'info');
                return false;
            }
        }else {
            jBox.tip("机构名称只允许输入中文,请更改输入内容", 'info');
            return false;
        }
        if (!manReg.test($("input[name='dtLeader']").val())) {
            /*if (dtLeaderLength > 60) {
                jBox.tip("负责人名字过长,允许输入不超过30个汉字,请更改输入内容", 'info');
                return false;
            }*/
            jBox.tip("负责人必须为1-60位汉字或英文字符", 'info');
            return false;
        }/* else {
            jBox.tip("负责人名字只允许输入中文,请更改输入内容", 'info');
            return false;
        }*/
        if (!manReg.test($("input[name='dtContact']").val())) {
            /*if (dtContactLength > 60) {
                jBox.tip("联系人名字过长,允许输入不超过30个汉字,请更改输入内容", 'info');
                return false;
            }*/
            jBox.tip("联系人必须为1-60位汉字或英文字符", 'info');
            return false;
        } /*else {
            jBox.tip("联系人只允许输入中文,请更改输入内容", 'info');
            return false;
        }*/
        //技术负责人改为非必填
 /*       if (myReg.test($("input[name='dtTechnicalDirector']").val())) {
            if (dtTechnicalDirectorLength > 60) {
                jBox.tip("技术负责人名字过长,允许输入不超过30个汉字,请更改输入内容", 'info');
                return false;
            }
        } else {
            if($("input[name='dtTechnicalDirector']").val()) {
                jBox.tip("技术负责人只允许输入中文,请更改输入内容", 'info');
                return false;
            }
        }*/
        var dtTechnicalDirector = $("input[name='dtTechnicalDirector']").val();
        if (dtTechnicalDirector!=null && dtTechnicalDirector!=""){
            if (!manReg.test($("input[name='dtTechnicalDirector']").val())) {
                jBox.tip("技术负责人必须为1-60位汉字或英文字符", 'info');
                return false;
            }
        }
        if (dtParameterLength > 60) {
            jBox.tip("参数长度过长,允许输入不超过30个汉字或者不超过60个英文字母/数字,请更改输入内容", 'info');
            return false;
        }
        if (dtRelyOnUnitLength > 60) {
            jBox.tip("依托单位长度过长,允许输入不超过30个汉字或者不超过60个英文字母/数字,请更改输入内容", 'info');
            return false;
        }
        if (!manReg.test($("input[name='dtLegalMan']").val())) {
           /* if (dtLegalManLength > 60) {
                jBox.tip("法人名字过长,允许输入不超过30个汉字,请更改输入内容", 'info');
                return false;
            }*/
            jBox.tip("法人必须为1-60位汉字或英文字符", 'info');
            return false;
        } /*else {
            jBox.tip("法人只允许输入中文,请更改输入内容", 'info');
            return false;
        }*/
        //质量负责人改为非必填
   /*     if (myReg.test($("input[name='dtQualityDirector']").val())) {
            if (dtQualityDirectorLength > 60) {
                jBox.tip("质量负责人名字过长,允许输入不超过30个汉字,请更改输入内容", 'info');
                return false;
            }
        } else {
            if($("input[name='dtQualityDirector']").val()) {
                jBox.tip("质量负责人只允许输入中文,请更改输入内容", 'info');
                return false;
            }
        }*/
        var dtQualityDirector = $("input[name='dtQualityDirector']").val();
        if (dtQualityDirector!=null && dtQualityDirector !=""){
            if (!manReg.test($("input[name='dtQualityDirector']").val())) {
                jBox.tip("质量负责人必须为1-60位汉字或英文字符", 'info');
                return false;
            }
        }
        if (dtProductRangeLength > 60) {
            jBox.tip("产品范围长度过长,允许输入不超过30个汉字或者不超过60个英文字母/数字,请更改输入内容", 'info');
            return false;
        }
        if (applyReasonLength > 300) {
            jBox.tip("变更原因长度过长,允许输入不超过150个汉字或者不超过300个英文字母/数字,请更改输入内容", 'info');
            return false;
        }
        if (shi == '0') {
            jBox.tip("所属区域不能为空", 'info');
            return false;
        }
        if (xian == '0') {
            jBox.tip("所属区域不能为空", 'info');
            return false;
        }
        //执行添加操作
        window.save($scope, $http, token);
    })

})

var readOnlyInput = function() {
    var ff = document.forms[0];
    for (var i = 0; i < ff.elements.length; i++) {
        var ee = ff.elements[i];
        if ("INPUT" == ee.tagName || "SELECT" == ee.tagName) {
            ee.readOnly = true;
        };
    }
}

var save = function(state, http, token) {
    //省市县区域赋值
    var sheng = $("#sheng option:selected").val();
    var shi = $("#shi option:selected").val();
    var xian = $("#xian option:selected").val();
    if (sheng != null && sheng != '' && sheng != '0') {
        $("input[name='dtAreaId']").val(sheng);
        $("input[name='dtArea']").val($.fn.Cityname(sheng));
    }
    if (shi != null && shi != '' && shi != '0') {
        $("input[name='dtAreaId']").val(shi);
        $("input[name='dtArea']").val($.fn.Cityname(shi));
    }
    if (xian != null && xian != '' && xian != '0') {
        $("input[name='dtAreaId']").val(xian);
        $("input[name='dtArea']").val($.fn.Cityname(xian));
    }
    //字典数据赋值
   // var dtQualifications = $("select[name='dtQualificationsId']").find("option:selected").text();
    var str = "";
    $.each($('input:checkbox:checked'),function(){
        str+= $(this).val()+"、";
    });
    var qualificationVal = str.substring(0,str.length-1);
    $("input[name='dtQualifications']").val(qualificationVal);
    var params = {};
    var obj = obj || $("#modalForm").serializeArray();
    $.each(obj, function() {
        if (params[this.name]) {
            if (!params[this.name].push) {
                params[this.name] = [params[this.name]];
            }
            params[this.name].push(this.value || '');
        } else {
            params[this.name] = this.value || '';
        }
    });

    params['dtNature']= $("select[name='dtNatureId']").find("option:selected").text();
    var req = {
        method: 'post',
        url: '/sofn-ads-web/acctInfo/addSubjDtChange',
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        },
        data: params,
        params: { "token": token }
    }

    http(req).then(function(data) {
        $("#add_modal").modal("hide");
        $("#grid").dataTable().api().ajax.reload();
        if (data.data.httpCode == "200") {
            jBox.tip("保存成功", 'success');
            location.reload();
        } else {
            jBox.tip(data.data.msg, 'error');
        }
    }, function(data) {
        $("#tb123").datagrid("reload", {});
    });
}
var checkRecode = function(el) {
    var bool = $(el).prop("checked");
    $(el).prop("checked", !bool);
    var tr = $(el).parent().parent();
    bool ? tr.removeClass("selected") : tr.addClass("selected");
}
var checkBoxChange = function() {
    $("#checkAll").click(function() {
        if ($(this).prop("checked") == true) {
            $("#grid input[name='checkOne']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[name='checkOne']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    /* $("#grid input[name='checkOne']").on("click",function() {
     if (this.prop("checked") == true) {
     this.prop("checked", true);
     $("#grid tbody tr").addClass("selected");
     } else {
     $("#grid input[name='checkOne']").prop("checked", false);
     $("#grid tbody tr").removeClass("selected");
     }
     });*/
}