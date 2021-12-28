var myapp = angular.module("myapp", []);
myapp.controller("subjSvList", function($scope, $http, $state, $timeout) {
    var vm = this;
    //判定页面权限、tab页、按钮
    var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for (var i = 0; i < a.length; i++) {
        if (a[i].text == "监管机构主体信息") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    if ($scope.iswrite != "2") {
        $(".addSubjSv").css("display", "none");
        $(".intoSubjSv").css("display", "none");
    }
    a.map(function(item, i) {
        if (item.text == "备案变更待审核") {
            $(".subjSvChangeList").css("display", "inline-block");
        } else if (item.text == "备案注销待审核") {
            $(".subjSvCancelList").css("display", "inline-block");
        } else if (item.text == "备案撤销待审核") {
            $(".subjSvRevokeList").css("display", "inline-block");
        }
    });
    $scope.subjSvListQueryParams = JSON.parse(
        window.sessionStorage.getItem("subjSvListQueryParams")
    );
    //重置区域
    $scope.reloadArea = function($event) {
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $("#queryForm").form("reset");
        $scope.svAddress = null;
        $scope.svLeaderPhone = null;
        $scope.svCode = null;
        $scope.svContact = null;
        $scope.svContactPhone = null;
        $scope.svContactQQ = null;
        $scope.svContactEmail = null;
        $scope.svPostcode = null;
        //清除状态
        $("#status").val("0");
        if ($scope.organization.orgLevel != "1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if (
                $("#sheng").val() != null &&
                $("#sheng").val() != "" &&
                $("#sheng").val() != undefined
            ) {
                $("#sheng")
                    .attr("disabled", true)
            }
            if (
                $("#shi").val() != null &&
                $("#shi").val() != "" &&
                $("#shi").val() != undefined
            ) {
                $("#shi")
                    .attr("disabled", true)
            }
            if (
                $("#xian").val() != null &&
                $("#xian").val() != "" &&
                $("#xian").val() != undefined
            ) {
                $("#xian")
                    .attr("disabled", true)
            }
        } else {
            $.fn.mycity("sheng", "shi", "xian");
        }
        $("input[type='checkbox']").prop("checked", false);
        $("input[name='entityIndustryLimit']").prop("checked", true);
        $("input[name='statuslLimit']").prop("checked", true);
        $("input[name='svLevelLimit']").prop("checked", true);
    };
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    $scope.countChangeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/countChangeToAudit",
            method: "post",
            data: { svAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.changeToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.countCancelToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/countCancelToAudit",
            method: "post",
            data: { svAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.cancelToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.countRevokeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/countRevokeToAudit",
            method: "post",
            data: { svAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.revokeToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.getAreaBySelect = function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (
            province != null &&
            province != undefined &&
            province != ""
        ) {
            areaId = province;
        }
        return areaId;
    };

    //从缓存中获取数据
    if (window.localStorage.getItem("asmsAllSysDictAndUserInfo")) {
        //获取用户信息
        $scope.user = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["user"];
        $scope.organization = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["organization"];
        $scope.underArea = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["underArea"];
        //获取行业数据字典
        $scope.industryType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["AllIndustryType"];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["allIndustryId"];
        $scope.svLevel = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["orgLevel"];
        $scope.reloadArea();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        loadGrid();
        if ($scope.subjSvListQueryParams) {
            $("#dateBegin").val($scope.subjSvListQueryParams.dateBegin);
            $("#dateEnd").val($scope.subjSvListQueryParams.dateEnd);
            $("#status").val($scope.subjSvListQueryParams.status);
            $("input[name='svName']").val($scope.subjSvListQueryParams.svName);
            $.fn.mycity(
                "sheng",
                "shi",
                "xian",
                $scope.subjSvListQueryParams.areaId
            );
        }
    } else {
        swal("登录超时", "由于您长时间未操作,请重新登录!", "warning");
        setTimeout(function() {
            window.location.href = "#/login/login";
            window.location.reload();
        }, 2500);
    }

    //批量导入
    $scope.importSubjSv = function() {
        $("#save").attr("disabled", true);
        var fd = new FormData();
        var file = document.querySelector("input[type=file]").files[0];
        fd.append("file", file);
        $http({
            method: "POST",
            url: "/sofn-asms-web/subjSupervise/importSubjSupervise",
            data: fd,
            headers: {
                "Content-Type": undefined,
                token: window.localStorage.getItem("token")
            },
            transformRequest: angular.identity
        }).success(function(data) {
            $("#save").attr("disabled", false);
            if (data.httpCode == "200") {
                //上传成功的操作
                jBox.tip("上传成功", "info");
                $("#importModal").modal("hide");
                $("#svGrid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            } else {
                jBox.tip(data.msg, "info");
            }
        });
    };

    //模态框显示
    $("#importSubjSv").click(function() {
        $("#importModal").modal("show");
    });
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    //bootstrap表格
    //监管机构主体信息
    function loadGrid() {
        $("#svGrid").dataTable({
            ordering: true, //开启排序
            order: [[6, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-asms-web/subjSupervise/getSubjSuperviseList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.page.recordsTotal;
                    });
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].createTime != null &&
                            json.list[i].createTime != "" &&
                            json.list[i].createTime != undefined
                        ) {
                            json.list[i].createTime = new Date(
                                parseInt(json.list[i].createTime) + 28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function(params) {
                    // params.svLevelId = $("select[name='svLevel']").val();
                    var subjSvListQueryParams = JSON.parse(
                        window.sessionStorage.getItem("subjSvListQueryParams")
                    );
                    params.areaId = $scope.getAreaBySelect();
                    params.svName = $("input[name='svName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    //绑值
                    params.svAddress = formatParams($scope.svAddress);
                    params.svLeaderPhone = formatParams($scope.svLeaderPhone);
                    params.svCode = formatParams($scope.svCode);
                    params.svContact = formatParams($scope.svContact);
                    params.svContactPhone = formatParams($scope.svContactPhone);
                    params.svContactQQ = formatParams($scope.svContactQQ);
                    params.svContactEmail = formatParams($scope.svContactEmail);
                    params.svPostcode = formatParams($scope.svPostcode);
                    var entityIndustry = $(
                        "input[name='entityIndustry']:checked"
                    );
                    var arr = new Array();
                    for (var i = 0; i < entityIndustry.length; i++) {
                        arr.push($(entityIndustry[i]).val());
                    }
                    params.industryId = arr.toString() || $scope.allIndustryId;
                    var statusarr = $("input[name='status']:checked");
                    var arr = new Array();
                    for (var i = 0; i < statusarr.length; i++) {
                        arr.push($(statusarr[i]).val());
                    }
                    params.status = arr.toString();
                    var svLevel = $("input[name='svLevel']:checked");
                    var svLevelArr = new Array();
                    for (var i = 0; i < svLevel.length; i++) {
                        svLevelArr.push($(svLevel[i]).val());
                    }
                    params.svLevel = svLevelArr.toString();
                    params.svLeader = $("input[name='svLeader']").val();

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }
                    $scope.chartParams = params; //图标参数赋值
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    orderable: false,
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "svName",
                    title: "机构名称",
                    width: "25%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 22
                        ) {
                            return (
                                "<a id='svName" +
                                row.id +
                                "' onmouseover=showPopContent('svName" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 20) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "svType",
                    title: "机构类别",
                    width: "10%"
                },
                {
                    data: "svLevel",
                    title: "机构级别",
                    width: "10%",
                    render: function(data, type, row) {
                        if (data == "3") {
                            return "县级";
                        } else if (data == "2") {
                            return "市级";
                        } else if (data == "1") {
                            return "省级";
                        } else if (data == "0") {
                            return "部级";
                        }
                    }
                },
                {
                    data: "svAreaId",
                    title: "所属区域",
                    width: "18%",
                    render: function(data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 14
                        ) {
                            return (
                                "<a id='svAreaId" +
                                row.id +
                                "' onmouseover=showPopContent('svAreaId" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 14) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "svLeader",
                    title: "机构负责人",
                    width: "10%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 7
                        ) {
                            return (
                                "<a id='svLeader" +
                                row.id +
                                "' onmouseover=showPopContent('svLeader" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 7) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "createTime",
                    title: "注册时间",
                    width: "10%"
                },
                {
                    data: "status",
                    title: "状态",
                    render: function(data, type, row) {
                        if (data == "0") {
                            return "正常";
                        } else if (data == "1") {
                            return "待变更";
                        } else if (data == "2") {
                            return "待注销";
                        } else if (data == "3") {
                            return "待撤销";
                        } else if (data == "4") {
                            return "已注销";
                        } else if (data == "5") {
                            return "已撤销";
                        } else {
                            return "正常";
                        }
                    }
                },
                {
                    data: "svAddress",
                    name: "svAddress",
                    title: "通讯地址",
                    visible: false,
                    width: "5%"
                },
                {
                    data: "svCode",
                    name: "svCode",
                    title: "机构代码",
                    visible: false
                },
                {
                    data: "svLeaderPhone",
                    name: "svLeaderPhone",
                    title: "负责人电话",
                    visible: false
                },
                {
                    data: "svContact",
                    name: "svContact",
                    title: "联系人",
                    visible: false
                },
                {
                    data: "svContactPhone",
                    name: "svContactPhone",
                    title: "联系人电话",
                    visible: false
                },
                {
                    data: "svContactQQ",
                    name: "svContactQQ",
                    title: "联系人QQ",
                    visible: false
                },
                {
                    data: "svContactEmail",
                    name: "svContactEmail",
                    title: "电子邮箱",
                    visible: false
                },
                {
                    data: "svPostcode",
                    name: "svPostcode",
                    title: "邮编",
                    visible: false
                },
                {
                    data: "id",
                    title: "操作",
                    orderable: false,
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjSvView(\'' +
                            data +
                            "')\">查看</a>";
                        if (
                            $scope.iswrite == 2 &&
                            $scope.organization.orgLevel - 1 <= row.svLevel &&
                            row.status != '5'
                        ) {
                            butt +=
                                '<a style="cursor: pointer" class="Check-report color" onclick="subjSvRevokeApply(\'' +
                                row.status +
                                "','" +
                                data +
                                "','" +
                                row.svLevel +
                                "')\">撤销</a>"; //转义传值
                        }
                        return butt;
                    }
                }
            ]
        });
    }

    //重新加载表格
    $scope.querySubjSvList = function($event) {
        // var subjSvListQueryParams = {};
        // subjSvListQueryParams.areaId = $scope.getAreaBySelect();
        // subjSvListQueryParams.svName = $("input[name='svName']").val();
        // subjSvListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        // subjSvListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        // subjSvListQueryParams.status = $("#status").val();
        // window.sessionStorage.setItem(
        //     "subjSvListQueryParams",
        //     JSON.stringify(subjSvListQueryParams)
        // );

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#svGrid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };
    //控制复选框
    $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
        var _limChec = $("input[name='" + limitCheckBoxName + "']:checked");
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
        }
        if (_limChec.length == 0) {
            _chec.prop("checked", true);
        }
        if (_limChec.length > 0 && _lim.length == _limChec.length) {
            _chec.prop("checked", true);
            _limChec.each(function(i, o) {
                $(o).prop("checked", false);
            });
        }
    };
    //控制复选框--不限
    $scope.limitCheckBoxAll = function(checkBoxName, limitCheckBoxName) {
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
        }
    };
    //跳转到监管机构主体备案查看页面
    window.subjSvView = function(data) {
        window.localStorage.setItem("subjSvId", data);
        $state.go("index.content.asms/subjSv/subjSvView");
    };
    //跳转到监管机构主体撤销申请页面
    window.subjSvRevokeApply = function(status, data, svLevel) {
        if ($scope.iswrite != "2") {
            jBox.tip("对不起！您没有此操作权限", "info");
            return false;
        }
        //只撤能销下一级监管机构,改为管辖范围内所有监管机构
        // if($scope.organization.orgLevel!=svLevel){
        //     jBox.tip("只能撤销下级监管机构！", 'info');
        //     return false;
        // }
        if (status == "1") {
            jBox.tip("还有变更申请未处理！", "info");
            return false;
        } else if (status == "2") {
            jBox.tip("还有注销申请未处理！", "info");
            return false;
        } else if (status == "3") {
            jBox.tip("还有撤销申请未处理！", "info");
            return false;
        } else if (status == "0") {
            window.localStorage.setItem("subjSvId", data);
            $state.go("index.content.asms/subjSv/subjSvRevokeApply");
        }
    };

    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjSvList,.subjSvChangeList,.subjSvCancelList,.subjSvRevokeList").each(
        function(i, o) {
            $(o).click(function() {
                window.sessionStorage.clear();
            });
        }
    );
    //鼠标悬停弹窗显示
    window.showPopContent = function(id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: "right",
            trigger: "hover",
            content: content,
            delay: 0,
            container: "body"
        });
        $("#" + id).popover("show");
    };
    /**************************
     CUSTOM QUERY CONDITION
     *************************/

    $scope.confirmCondition = function() {
        var modal = $("#modalCondition");
        modal
            .find(".gui-list li")
            .not(".disabled")
            .map(function() {
                var key = $.trim($(this).attr("data-key"));
                var checked = $(this)
                    .find(":checkbox")
                    .is(":checked");
                if (!checked) {
                    var control = $(
                        '.gui-form-group .condition-custom[ng-show="visible' +
                        key +
                        '"]'
                    );
                    var controlType = control.attr("data-control");
                    switch (controlType) {
                        case "text":
                            $scope[key] = "";
                            break;
                        case "select":
                            $scope[key] = "";
                            control
                                .find("option:first")
                                .prop("selected", true)
                                .trigger("change");
                            break;
                        case "checkbox":
                            $scope[key] = "";
                            control
                                .find(":checkbox")
                                .first()
                                .prop("checked", true)
                                .end()
                                .siblings()
                                .prop("checked", false);
                            break;
                        case "date":
                            control
                                .find("input:text")
                                .val("")
                                .trigger("input");
                            break;
                        case "radio":
                            break;
                    }
                }
                $scope["visible" + key] = checked;
                //切换表格列是否可见
                var table = $("#svGrid").DataTable();
                var column = table.column(key + ":name");
                column.visible(checked);
            });
        modal.modal("hide");
    };
    $("#modalCondition")
        .on("change", ".condition-checked-all", function() {
            var checked = $(this).is(":checked");
            $("#modalCondition")
                .find("li")
                .not(".disabled")
                .find(":checkbox")
                .prop("checked", checked);
        })
        .on("change", ".gui-list :checkbox", function() {
            var unchecked = $(this)
                .closest(".gui-list")
                .find(":checkbox")
                .filter(function() {
                    return !$(this).is(":checked");
                });
            $(".condition-checked-all").prop("checked", !!!unchecked.length);
        })
        .on("shown.bs.modal", function() {
            $(".gui-form-group .condition-custom").each(function() {
                var visible = $(this).is(":visible");
                var key = $(this)
                    .attr("ng-show")
                    .replace("visible", "");
                $(".gui-list.list-condition")
                    .find('li[data-key="' + key + '"]')
                    .find(":checkbox")
                    .prop("checked", visible)
                    .trigger("change");
            });
        });

    /************************************************
     ******* chart *******
     ************************************************/

    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-asms-web/subjSupervise/getSupervisopnStatis",
        grid: [2, 2, 1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };

});
