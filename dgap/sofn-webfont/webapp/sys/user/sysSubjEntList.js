var myapp = angular.module("myapp", []);
myapp.filter('toTrustHtml', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    }
}]);
myapp.controller("sysSubjEntList", function ($scope, $http, $state, $interval) {
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('机构用户管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    //判定页面权限、tab页、按钮
    // var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    // for(var i=0;i< a.length;i++){
    //     if(a[i].text=="生产经营主体信息"){
    //         $scope.iswrite=a[i].authority;
    //         break;
    //     }
    // }
    // a.map(function(item,i){
    //     if(item.text=="临时备案主体"){
    //         $(".subjEntTempList").css("display","inline-block");
    //     }else if(item.text=="备案待审核"){
    //         $(".subjEntRegisterList").css("display","inline-block");
    //     }else if(item.text=="备案变更待审核"){
    //         $(".subjEntChangeList").css("display","inline-block");
    //     }else if(item.text=="备案撤销待审核"){
    //         $(".subjEntRevokeList").css("display","inline-block");
    //     }else if(item.text=="备案注销待审核"){
    //         $(".subjEntCancelList").css("display","inline-block");
    //     }
    // });
    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //备案待审核数量
    $scope.registerToAuditCount = 0;
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method: "post"
    }).success(function (data) {
        $scope.user = data.user;
        $scope.initIndustryRole();
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        } else {
            $scope.underArea = $scope.organization.regionId;
        }
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.countRegisterToAudit();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        //loadGrid();
    }).error(function () {
    });
    //认证类型
    $scope.authenticationType = [{"dictName": "绿色食品", "dictValue": "绿色认证"},
        {"dictName": "有机食品", "dictValue": "有机认证"},
        {"dictName": "无公害农产品", "dictValue": "无公害认证"},
        {"dictName": "无", "dictValue": "无"}]
    //账号来源
    $scope.accountResource = [{"dictName": "三品一标导入", "dictValue": "SPYB"},
        {"dictName": "其它", "dictValue": "其它"}]


    //获取当前登录用户行业角色
    $scope.initIndustryRole = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/getUserIndustryRoleByToken",
            method: "post"
        }).success(function (data) {
            $scope.industryRole = data.industryRole;
            $scope.initIndustry();
        }).error(function () {
        });
    }

    //获取行业数据字典
    $scope.initIndustry = function () {
        $scope.industryType = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "2",
            method: "post"
        }).success(function (data) {
            $scope.a = JSON.parse(data.data);
            $scope.industryType = [];
            var arr = new Array();
            var idArr = new Array();
            for (var i = 0; i < $scope.a.length; i++) {
                for (var j = 0; j < $scope.industryRole.length; j++) {
                    if ($scope.industryRole[j] == $scope.a[i].dictValue) {
                        arr.push($scope.a[i]);
                        idArr.push($scope.a[i].id);
                    }
                }
            }
            $("select[name='entityIndustry'] option:first").val(idArr.toString());
            // window.localStorage.setItem("FlushEntityIndustry",$("select[name='entityIndustry']").val());
            $scope.industryType = arr;
            loadGrid();
        })
    };
    //重置区域
    $scope.reloadArea = function () {
        $("#queryForm").form("reset");
        if ($scope.organization.orgLevel != "1") {
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
        } else {
            $.fn.mycity("sheng", "shi", "xian");
        }
    }

    $scope.countRegisterToAudit = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countRegisterToAudit",
            method: "post",
            data: {area: $scope.getAreaBySelect()}
        }).success(function (data) {
            $scope.registerToAuditCount = data.count;
        }).error(function () {
        });
    }
    $scope.countChangeToAudit = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countChangeToAudit",
            method: "post",
            data: {area: $scope.getAreaBySelect()}
        }).success(function (data) {
            $scope.changeToAuditCount = data.count;
        }).error(function () {
        });
    }
    $scope.countCancelToAudit = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countCancelToAudit",
            method: "post",
            data: {area: $scope.getAreaBySelect()}
        }).success(function (data) {
            $scope.cancelToAuditCount = data.count;
        }).error(function () {
        });
    }
    $scope.countRevokeToAudit = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countRevokeToAudit",
            method: "post",
            data: {area: $scope.getAreaBySelect()}
        }).success(function (data) {
            $scope.revokeToAuditCount = data.count;
        }).error(function () {
        });
    }
    $scope.getAreaBySelect = function () {
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
        return areaId;
    }

    //获取行业数据字典
    $scope.industryType = [];
    $http({
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: 2,
        method: "post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        $scope.industryType = JSON.parse(data.data);
    })
    //获取主体类别数据字典
    $scope.subjType = [];
    $http({
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: 7,
        method: "post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        $scope.subjType = JSON.parse(data.data);
    })
    //获取组织形式数据字典
    $scope.orgMode = [];
    $http({
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: 6,
        method: "post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        $scope.orgMode = JSON.parse(data.data);
    })

    //批量导入
    $scope.importSubEnt = function () {
        $("#save").attr("disabled", true);
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        if (!file) {
            jBox.tip("请先选择文件，再提交", "info");
            $("#save").attr("disabled", false);
            return;
        }
        fd.append('file', file);
        $http({
            method: 'POST',
            url: "/sofn-asms-web/subjEnt/importSubjEnt",
            data: fd,
            headers: {'Content-Type': undefined, token: window.localStorage.getItem("token")},
            transformRequest: angular.identity
        }).success(function (data) {
            if (data.httpCode == "200") {
                $("#save").attr("disabled", false);
                //上传成功的操作
                $("#importModal").modal("hide");
                var text = "请耐心等待, 勿重复操作!";
                swal({
                    title: "数据导入中···",
                    text: text,
                    type: "info",
                    button: "确定"
                });
                var intervalID = setInterval(function () {
                    $http({
                        method: 'POST',
                        url: "/sofn-asms-web/subjEnt/getImportSubjEntMsg",
                        params: {"importKey": data.importKey},
                        headers: {token: window.localStorage.getItem("token")},
                    }).success(function (res) {
                        var status = res.status;
                        var msgList = res.importMsg;
                        if (status) {
                            if (status === "success") {
                                clearInterval(intervalID);
                                //上传成功的操作
                                $("#importModal").modal("hide");
                                $("#entGrid").dataTable().api().ajax.reload();
                                swal({
                                    title: "导入成功!",
                                    type: "success",
                                    button: "确定"
                                });
                            } else if (status === "error") {
                                clearInterval(intervalID);
                                swal.close();
                                $("#entGrid").dataTable().api().ajax.reload();
                                setTimeout(function(){
                                    alert(msgList);
                                }, 500)
                            }
                        } else {
                            clearInterval(intervalID);
                        }
                    })
                }, 3000);

            }
        });
    };

    //模态框显示
    $("#importSubEnt").click(function () {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $("#importModal").modal("show");
    });

    //bootstrap表格
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntListForSys",
                dataSrc: function (json) {
                    if (json.httpCode != 200) {
                        json.error = json.msg;
                        return json;
                    }
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    //主体状态
                    params.approveStatus = $("select[name='entStatu']").val();
                    params.entityIndustry = $("select[name='entityIndustry']").val();
                    params.entityScale = $("select[name='entityScale']").val();
                    params.entityType = $("select[name='entityType']").val();
                    params.badRecord = $("select[name='badRecord']").val();
                    params.accountResource = $("select[name='accountResource']").val();
                    params.spybLicType = $("select[name='spybLicType']").val();
                    params.area = $scope.getAreaBySelect();
                    params.enterpriseName = $("input[name='entName']").val();
                    params.token = window.localStorage.getItem("token");
                },
                type: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            },
            columns: [{
                //data : "RN",
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }

            },
                {
                    data: "enterpriseName",
                    title: "主体名称"
                },
                {
                    data: "account",
                    title: "管理员账号"
                },
                {
                    data: "userPassword",
                    title: "初始密码"
                },
                {
                    data: "accountResource",
                    title: "账号来源",
                    render: function (data) {
                        if (data == "SPYB") {
                            return "三品一标导入";
                        }
                        return "其它";
                    }
                },
                {
                    data: "spybLicType",
                    title: "认证类型",
                    render: function (data) {
                        return data == null ? "无" : data;

                    }
                },
                {
                    data: "entityTypeName",
                    title: "主体类别"
                },
                {
                    data: "entityScaleName",
                    title: "组织形式"
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data: "createTime",
                    title: "注册时间"
                },
                {
                    data: "approveStatus",
                    title: "主体状态",
                    render: function (data, type, row) {
                        switch (data) {
                            case "0":
                                return "注册待审核";
                                break;
                            case "1":
                                return "审核通过";
                                break;
                            case "2":
                                return "注册审核不通过";
                                break;
                            case "3":
                                return "变更待审核";
                                break;
                            case "4":
                                return "注销待审核";
                                break;
                            case "5":
                                return "注销待审核";
                                break;
                            case "6":
                                return "已注销";
                                break;
                            case "7":
                                return "已注销";
                                break;
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntView(\'' + data + '\')">查看</a>';
                        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
                            butt += '<a style="cursor: pointer" class="Check-report color" onclick="subjEntRevokeApply(\'' + row.account + '\')">重置密码</a>'; //转义传值
                        }
                        return butt;
                    }
                },
            ],
        });
    }

    //重新加载表格
    $scope.querySubjEntList = function () {
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //跳转到生产经营主体备案查看页面
    window.subjEntView = function (data) {
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId", data);
        $state.go("index.content.asms/subjEnt/subjEntView", {source: 'sys'});
    };
    //跳转到生产经营主体撤销申请页面
    window.subjEntRevokeApply = function (account) {
        if (!account || account === 'null') {
            alert("未找到该账号,请先分配账号");
            return;
        }
        if (confirm('是否重置密码？')) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-sys-web/user/resetPassword",
                method: "post",
                params: {'account': account}
            }).success(function (data) {
                if (data.httpCode == 200)
                    jBox.tip("重置成功！", 'info');
                else
                    jBox.tip("重置失败！", 'info');
                $("#entGrid").dataTable().api().ajax.reload();
            }).error(function () {
                jBox.tip("重置失败！", 'info');
            });
        }
    }
    /**
     * 导出账号
     */
    $scope.exportSubjEnt = function () {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/subjEnt/exportSubjEnt");
        var entityIndustry = $("select[name='entityIndustry']").val();
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'entityIndustry');
        input1.attr('value', entityIndustry);
        var entityScale = $("select[name='entityScale']").val();
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'entityScale');
        input2.attr('value', entityScale);
        var entityType = $("select[name='entityType']").val();
        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'entityType');
        input3.attr('value', entityType);
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'badRecord');
        input4.attr('value', $("select[name='badRecord']").val());
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'area');
        input5.attr('value', $scope.getAreaBySelect());
        var input6 = $('<input>');
        input6.attr('type', 'hidden');
        input6.attr('name', 'enterpriseName');
        input6.attr('value', $("input[name='entName']").val());
        var input7 = $('<input>');
        input7.attr('type', 'hidden');
        input7.attr('name', 'token');
        input7.attr('value', window.localStorage.getItem("token"));
        var input8 = $('<input>');
        input8.attr('type', 'hidden');
        input8.attr('name', 'spybLicType');
        input8.attr('value', $("select[name='spybLicType']").val());
        var input9 = $('<input>');
        input9.attr('type', 'hidden');
        input9.attr('name', 'accountResource');
        input9.attr('value', $("select[name='accountResource']").val());
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.append(input8);
        form.append(input9);
        form.submit();
    }

    var spybImportTitleInitValue = '三品一标数据导入中，请耐心等待……';
    var spybImportContentInitValue = '<p style="height: 100px;line-height: 100px;text-align: center">' +
        '<img src="../assets/imgs/common/loading.gif"/>' +
        '</p><p style="height: 80px;line-height: 80px;text-align: center">' +
        '<img src="../assets/imgs/common/warning_16.png"/>点击“取消”会将已导入数据一并删除</p>';

    $scope.spybImportStatusModalTitle = spybImportTitleInitValue;
    $scope.spybImportStatusModalContent = spybImportContentInitValue;
    $scope.spybImportStatusModalCancelButtonIsShow = true;

    var spybImportResultTimer;
    var spybImportSubmitting = false; // 正在提交数据的标识，用于防重复提交
    $scope.submittingValidate = false; // 提交校验标识，控制前端显示校验提示信息

    // 三品一标导入参数
    $scope.spybImportParam = {
        wsdl: "",
        organicCertificationToken: "",
        greenCertificationToken: "",
        nuisancelessCertificationToken: ""
    };

    // 异步请求错误处理函数
    function ajaxErrorHandler(statusCode) {
        jBox.tip('返回数据不正常，操作失败', 'error');
    }

    // 打开三品一标导入弹框
    $scope.openSpybImportModal = function () {
        // $scope.clearSpybImportParam();
        $scope.spybImportForm.$setPristine();
        spybImportSubmitting = false;
        $scope.submittingValidate = false;
        $("#spybImportModal").modal("show");
    }
    // 关闭三品一标导入弹框事件处理
    $("#spybImportModal").on("hide.bs.modal", function () {
        // $scope.clearSpybImportParam();
        $scope.spybImportForm.$setPristine();
        spybImportSubmitting = false;
        $scope.submittingValidate = false;
    });

    // 清空三品一标导入参数
    $scope.clearSpybImportParam = function () {
        $scope.spybImportParam.wsdl = "";
        $scope.spybImportParam.organicCertificationToken = "";
        $scope.spybImportParam.greenCertificationToken = "";
        $scope.spybImportParam.nuisancelessCertificationToken = "";
    }

    // 查询导入结果
    $scope.requestImportResult = function (importJobId) {
        $http({
            url: "/sofn-asms-web/spyb/queryImportResult",
            method: "post",
            data: {jobId: importJobId},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            if (json.httpCode == 200) {
                if (json.data.jobStatus == 'done') {
                    $interval.cancel(spybImportResultTimer);
                    var templateString = '<p>导入主体总数：<%= totalCount %>个</p>' +
                        '<p>生成新账号：<%= newAccountCount %>个</p>' +
                        '<p>关联已存在主体：<%= relateEntCount %>个</p>' +
                        '<p>绿色食品：<%= greenCount %>个</p>' +
                        '<p>有机食品：<%= organicCount %>个</p>' +
                        '<p>无公害农产品：<%= nuisancelessCount %>个</p>';
                    var template = _.template(templateString);
                    // 将数据加载到模板中
                    $scope.spybImportStatusModalTitle = '三品一标数据导入结果';
                    $scope.spybImportStatusModalContent = template({
                        totalCount: json.data.totalCount,
                        greenCount: json.data.greenCertificationCount,
                        organicCount: json.data.organicCertificationCount,
                        nuisancelessCount: json.data.nuisancelessCertificationCount,
                        newAccountCount: json.data.newAccountCount,
                        relateEntCount: json.data.nuisancelessRelateEntCount + json.data.organicRelateEntCount + json.data.greenRelateEntCount
                    });
                    $scope.spybImportStatusModalCancelButtonIsShow = false;
                } else if (json.data.jobStatus == 'error') {
                    $interval.cancel(spybImportResultTimer);
                    $("#spybImportStatusModal").modal('hide');
                    swal('错误', '导入发生错误，导入操作已终止', 'error');
                }
            } else {
                $interval.cancel(spybImportResultTimer);
                jBox.tip('导入操作失败', 'error');
            }

        }).error(function (json, status) {
            $interval.cancel(spybImportResultTimer);
            ajaxErrorHandler(status);
        });
    }

    var jobId = "";
    // 执行导入操作
    /**
     * 异步请求后端，把数据共享系统webservice参数提交过去，然后正常返回，再打开下面导入状态弹框，同时开启一个定时器，每隔10秒钟异步请求后端检查数据导入是否完成，
     * 如果导入已经完成，返回导入结果数据，并显示结果信息，如果导入未完成，不做任何操作，如果导入出错，弹出错误提示
     * @param isValid 表单校验结果
     */
    $scope.spybImport = function (isValid) {
        $scope.submittingValidate = true;
        // 表单校验通过，提交导入需要的参数
        if (isValid && (!spybImportSubmitting)) {
            $scope.submittingValidate = false;
            spybImportSubmitting = true;
            $scope.spybImportForm.$setPristine();

            $http({
                url: "/sofn-asms-web/spyb/dataImport",
                method: "post",
                data: $scope.spybImportParam,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'token': window.localStorage.getItem("token")
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                spybImportSubmitting = false;
                if (json.httpCode == 200) {
                    jobId = json.jobId;
                    $("#spybImportModal").modal("hide");

                    $scope.spybImportStatusModalTitle = spybImportTitleInitValue;
                    $scope.spybImportStatusModalContent = spybImportContentInitValue;
                    $scope.spybImportStatusModalCancelButtonIsShow = true;
                    $("#spybImportStatusModal").modal("show");

                    // 轮询导入结果
                    spybImportResultTimer = $interval(function () {
                        $scope.requestImportResult(json.jobId);
                    }, 5000);
                } else {
                    if (json.httpCode == 423) {
                        jBox.tip('有导入操作正在执行中，请稍后再试', 'info');
                    } else {
                        jBox.tip('导入操作失败', 'error');
                    }
                }

            }).error(function (json, status) {
                spybImportSubmitting = false;
                ajaxErrorHandler(status);
            });
        }

    }

    // 取消三品一标导入
    window.cancelSpybImport = function (v, h, f) {
        if (v == true) {
            // 取消轮询导入结果
            $interval.cancel(spybImportResultTimer);
            $http({
                url: "/sofn-asms-web/spyb/cancelImport",
                method: "post",
                data: {"jobId": jobId},
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'token': window.localStorage.getItem("token")
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                if (json.httpCode == 200) {
                    $("#spybImportStatusModal").modal('hide');
                    jBox.tip('已取消导入', 'info');
                } else {
                    $interval.cancel(spybImportResultTimer);
                    jBox.tip('取消导入失败', 'error');
                }

            }).error(function (json, status) {
                $interval.cancel(spybImportResultTimer);
                ajaxErrorHandler(status);
            });
        }
    }

    // 三品一标导入状态框取消按钮事件处理
    $scope.cancelSpybImportEventHandler = function () {
        jBox.confirm("确认要取消三品一标数据导入吗？", "导入已取消", cancelSpybImport, {buttons: {'确认': true, '取消': false}});
    }
    // 三品一标导入状态框确定按钮事件处理
    $scope.closeSpybImportEventHandler = function () {
        $("#spybImportStatusModal").modal('hide');
    }
})