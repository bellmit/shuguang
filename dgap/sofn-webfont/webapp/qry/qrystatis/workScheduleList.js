var myapp = angular.module("myapp", []);
myapp.controller("workScheduleList", function ($scope, $http) {
    $scope.END_TIME = "";

    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post"
    }).success(function (data) {
        $scope.user = data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(
            0,
            2 * ($scope.organization.orgLevel - 1)
        );

        $scope.reloadArea();

        loadGrid();
    });
    /*.error(function (data) {
           
        })*/

    //监管系统、执法系统、检测系统、追溯系统
    $scope.ASMS = [
        {id: "ASMS_ALL", name: "不限"},
        {
            id: "ASMS_BASE_INSPECTION",
            name: "基地巡查"
        },
        {id: "ASMS_ROUTINE_MONITOR", name: "例行监测"},
        {
            id: "ASMS_SPECIAL_MONITOR",
            name: "专项监测"
        },
        {id: "ASMS_CHECK_TASK", name: "监督抽查"},
        {
            id: "ASMS_EMERGENCY_TASK",
            name: "应急任务"
        },
        {id: "ASMS_TTS_SCLTXXCJ_COMPLAINT", name: "投诉处理"}
    ];
    $scope.ALES = [
        {id: "ALES_ALL", name: "不限"},
        {
            id: "ALES_DAILY_ENFORCE_LAW",
            name: "现场巡查"
        },
        {id: "ALES_ENTRUST_DETECTION", name: "委托任务"},
        {
            id: "ALES_PRODUCE_ADMIN_PUNISH",
            name: "行政处罚"
        },
        {id: "ALES_CHECK_TASK", name: "监督抽查"}
    ];
    $scope.ADS = [
        {id: "ADS_ALL", name: "不限"},
        {
            id: "ADS_ROUTINE_MONITOR",
            name: "例行监测"
        },
        {id: "ADS_SPECIAL_MONITOR", name: "专项监测"},
        {
            id: "ADS_CHECK_TASK",
            name: "监督抽查"
        },
        {id: "ADS_DELEGATION", name: "受托检测"},
        {id: "ADS_RECHECK_TASK", name: "复检任务"}
    ];
    $scope.TTS = [
        {id: "TTS_ALL", name: "不限"},
        {id: "ENTCOUNT", name: "主体注册"},
        {
            id: "BATCHCOUNT",
            name: "生成批次总数"
        },
        {
            id: "PRINTCOUNT",
            name: "追溯码打印次数"
        },
        {id: "SALECOUNT", name: "交易总数"},
        {id: "SALEOVERCOUNT", name: "交易成功数"}
    ];

    //重置区域
    $scope.reloadArea = function () {
        $("#queryForm").form("reset");

        var shengAreaId = "";
        var shiAreaId = "";
        var xianAreaId = "";
        getRegion("100000", "sheng");
        if ($scope.organization.orgLevel !== "1") {
            if ($scope.organization.orgLevel === "2") {
                shengAreaId = $scope.underArea + "0000";
                $("#sheng").val(shengAreaId);
                getRegion(shengAreaId, "shi");
            }
            if ($scope.organization.orgLevel === "3") {
                shengAreaId = $scope.underArea.substr(0, 2) + "0000";
                $("#sheng").val(shengAreaId);
                shiAreaId = $scope.underArea + "00";
                getRegion(shengAreaId, "shi");
                $("#shi").val(shiAreaId);
                getRegion(shiAreaId, "xian");
            }
            if ($scope.organization.orgLevel === "4") {
                shengAreaId = $scope.underArea.substr(0, 2) + "0000";
                $("#sheng").val(shengAreaId);
                shiAreaId = $scope.underArea.substr(0, 4) + "00";
                getRegion(shengAreaId, "shi");
                $("#shi").val(shiAreaId);
                xianAreaId = $scope.underArea;
                getRegion(shiAreaId, "xian");
                $("#xian").val(xianAreaId);
            }

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
        }
    };

    function getRegion(regionId, id) {
        $.ajax({
            url: "/sofn-sys-web/region/queryMenu?&delFlag=Y",
            type: "post",
            dataType: "json",
            async: false,
            data: {regionId: regionId},
            success: function (data) {
                var regionName = data;
                $.each(regionName, function (k, p) {
                    var option =
                        "<option value='" + p.id + "'>" + p.text + "</option>";
                    $("#" + id).append(option);
                });
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    $scope.changeCheck = function (obj) {
        if ($("#" + obj.id).is(":checked")) {
            var name = $("#" + obj.id).attr("name");
            if (obj.id == name + "_ALL") {
                $("[name=" + name + "]").prop({checked: false});
                $("#" + name + "_ALL").prop({checked: true});
            } else
                $("#" + $("#" + obj.id).attr("name") + "_ALL").prop({
                    checked: false
                });
        }
    };

    //重新加载列表
    $scope.query = function () {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function () {
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        if ($("#span_0").hasClass("gui-btn")) {
            $scope.dateBegin = $("#datebegin").val().trim();
            $scope.dateEnd = $("#dateend").val().trim();
            if($scope.dateBegin == "" && $scope.dateEnd!="") {
                $.jBox.tip("统计开始时间不能为空");
                return;
            }

            if($scope.dateBegin != "" && $scope.dateEnd!="" && $scope.dateEnd<$scope.dateBegin) {
                $.jBox.tip("开始时间不能大于结束时间");
                return;
            }
        }

        $("#dtGrid")
            .dataTable()
            .api()
            .ajax.reload();
    };


    //根据选择的值生成开始时间、结束时间
    $scope.dateBegin = "";
    $scope.dateEnd = "";

    $scope.choose = function (n, obj) {
        $("span.span_date").removeClass("gui-btn");
        $("#span_" + n).addClass("gui-btn");
        switch (n) {
            case 1:
                $scope.dateBegin = $scope.getDifDate(2);
                $scope.dateEnd = $scope.getDifDate(1);
                break;
            case 2:
                $scope.dateBegin = $scope.getDifDate(1);
                $scope.dateEnd = $scope.getDifDate(0);
                break;
            case 3:
                $scope.dateBegin = $scope.getDifDate(7);
                $scope.dateEnd = $scope.getDifDate(0);
                break;
            case 4:
                $scope.dateBegin = $scope.getDifDate(30);
                $scope.dateEnd = $scope.getDifDate(0);
                break;
            case 5:
                $scope.dateBegin = $scope.getDifDate(60);
                $scope.dateEnd = $scope.getDifDate(0);
                break;
            case 6:
                $scope.dateBegin = $scope.getDifDate(90);
                $scope.dateEnd = $scope.getDifDate(0);
                break;
            default:
                $scope.dateBegin = "";
                $scope.dateEnd = "";
        }
    };


    $scope.getDifDate = function (dif) {
        var date = new Date();
        date.setTime(date.getTime() - dif * 24 * 60 * 60 * 1000);
        var month = date.getMonth() + 1;
        month = month < 10 ? "0" + month : month;
        var day = date.getDate();
        day = day < 10 ? "0" + day : day;
        return date.getFullYear() + "-" + month + "-" + day;
    }

    $scope.reset = function () {

        $scope.choose(0);

        $("[type=checkbox]").prop({checked: false});
        $("#ASMS_ALL").prop({checked: true});
        $("#ALES_ALL").prop({checked: true});
        $("#ADS_ALL").prop({checked: true});
        $("#TTS_ALL").prop({checked: true});

        if ($scope.underArea.length == 4) $("#xian").val("");
        else if ($scope.underArea.length == 2) {
            $("#shi").val("");
            $("#xian").val("");
        } else if ($scope.underArea.length == 0) {
            $("#sheng").val("");
            $("#shi").val("");
            $("#xian").val("");
        }
    };


    $scope.detail = function () {
        $("#content").html($("#table").html());
        $("#model").modal("show");
    }

    //bootstrap表格
    function loadGrid() {
        $("#dtGrid").dataTable({
            paging: false,
            info: false,
            scrollY: 500,
            ajax: {
                url: "/sofn-qry-web/qryStatis/getWorkScheduleList",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;

                    if (json.data.list.length > 0)
                        $("#END_TIME").text(json.data.list[0]["END_TIME"]);

                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    var value = $("#xian").val();
                    if (value == "") {
                        value = $("#shi").val();
                        if (value == "") value = $("#sheng").val();
                    }
                    params.areaId = value;
                    params.token = window.localStorage.getItem("token");


                    params.dateBegin = $scope.dateBegin;
                    params.dateEnd = $scope.dateEnd;

                    if ($scope.dateBegin != "" && $scope.dateEnd == "")
                        params.dateEnd = $scope.getDifDate(0);

                    //获取被勾选的查询项
                    var checkFlag = "";//被勾选中为1，未勾选中为0
                    //默认每一项都被勾选中
                    var ASMS_BASE_INSPECTION = "1";
                    var ASMS_ROUTINE_MONITOR = "1";
                    var ASMS_SPECIAL_MONITOR = "1";
                    var ASMS_CHECK_TASK = "1";
                    var ASMS_EMERGENCY_TASK = "1";
                    var ASMS_TTS_SCLTXXCJ_COMPLAINT = "1";

                    if (!$('#ASMS_ALL').is(":checked")) {
                        if (!$('#ASMS_BASE_INSPECTION').is(":checked")) {
                            ASMS_BASE_INSPECTION = "0";
                        }
                        if (!$('#ASMS_ROUTINE_MONITOR').is(":checked")) {
                            ASMS_ROUTINE_MONITOR = "0";
                        }
                        if (!$('#ASMS_SPECIAL_MONITOR').is(":checked")) {
                            ASMS_SPECIAL_MONITOR = "0";
                        }
                        if (!$('#ASMS_CHECK_TASK').is(":checked")) {
                            ASMS_CHECK_TASK = "0";
                        }
                        if (!$('#ASMS_EMERGENCY_TASK').is(":checked")) {
                            ASMS_EMERGENCY_TASK = "0";
                        }
                        if (!$('#ASMS_TTS_SCLTXXCJ_COMPLAINT').is(":checked")) {
                            ASMS_TTS_SCLTXXCJ_COMPLAINT = "0";
                        }
                    }

                    var ALES_DAILY_ENFORCE_LAW = "1";
                    var ALES_ENTRUST_DETECTION = "1";
                    var ALES_PRODUCE_ADMIN_PUNISH = "1";
                    var ALES_CHECK_TASK = "1";

                    if (!$('#ALES_ALL').is(":checked")) {
                        if (!$('#ALES_DAILY_ENFORCE_LAW').is(":checked")) {
                            ALES_DAILY_ENFORCE_LAW = "0";
                        }
                        if (!$('#ALES_ENTRUST_DETECTION').is(":checked")) {
                            ALES_ENTRUST_DETECTION = "0";
                        }
                        if (!$('#ALES_PRODUCE_ADMIN_PUNISH').is(":checked")) {
                            ALES_PRODUCE_ADMIN_PUNISH = "0";
                        }
                        if (!$('#ALES_CHECK_TASK').is(":checked")) {
                            ALES_CHECK_TASK = "0";
                        }
                    }

                    var ADS_ROUTINE_MONITOR = "1";
                    var ADS_SPECIAL_MONITOR = "1";
                    var ADS_CHECK_TASK = "1";
                    var ADS_DELEGATION = "1";
                    var ADS_RECHECK_TASK = "1";

                    if (!$('#ADS_ALL').is(":checked")) {
                        if (!$('#ADS_ROUTINE_MONITOR').is(":checked")) {
                            ADS_ROUTINE_MONITOR = "0";
                        }
                        if (!$('#ADS_SPECIAL_MONITOR').is(":checked")) {
                            ADS_SPECIAL_MONITOR = "0";
                        }
                        if (!$('#ADS_CHECK_TASK').is(":checked")) {
                            ADS_CHECK_TASK = "0";
                        }
                        if (!$('#ADS_DELEGATION').is(":checked")) {
                            ADS_DELEGATION = "0";
                        }
                        if (!$('#ADS_RECHECK_TASK').is(":checked")) {
                            ADS_RECHECK_TASK = "0";
                        }
                    }

                    var ENTCOUNT = "1";
                    var BATCHCOUNT = "1";
                    var PRINTCOUNT = "1";
                    var SALECOUNT = "1";
                    var SALEOVERCOUNT = "1";

                    if (!$('#TTS_ALL').is(":checked")) {
                        if (!$('#ENTCOUNT').is(":checked")) {
                            ENTCOUNT = "0";
                        }
                        if (!$('#BATCHCOUNT').is(":checked")) {
                            BATCHCOUNT = "0";
                        }
                        if (!$('#PRINTCOUNT').is(":checked")) {
                            PRINTCOUNT = "0";
                        }
                        if (!$('#SALECOUNT').is(":checked")) {
                            SALECOUNT = "0";
                        }
                        if (!$('#SALEOVERCOUNT').is(":checked")) {
                            SALEOVERCOUNT = "0";
                        }
                    }

                    checkFlag = ASMS_BASE_INSPECTION + ASMS_ROUTINE_MONITOR + ASMS_SPECIAL_MONITOR + ASMS_CHECK_TASK + ASMS_EMERGENCY_TASK + ASMS_TTS_SCLTXXCJ_COMPLAINT + ALES_DAILY_ENFORCE_LAW + ALES_ENTRUST_DETECTION + ALES_PRODUCE_ADMIN_PUNISH + ALES_CHECK_TASK + ADS_ROUTINE_MONITOR + ADS_SPECIAL_MONITOR + ADS_CHECK_TASK + ADS_DELEGATION + ADS_RECHECK_TASK + ENTCOUNT + BATCHCOUNT + PRINTCOUNT + SALECOUNT + SALEOVERCOUNT;
                    params.checkFlag = checkFlag;
                },
                type: "post"
            },
            columns: [
                // {
                //     title: "序号",
                //     data: function(data, type, row, meta) {
                //         return meta.row + 1;
                //     }
                // },
                {
                    data: "AREA_NAME",
                    title: "区域",
                    className: "",
                    render: function (data, type, row, meta) {
                        return data == null ? "小计" : data;
                    }
                },
                {
                    data: "ASMS_BASE_INSPECTION",
                    title: "基地巡查",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_BASE_INSPECTION").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ASMS_ROUTINE_MONITOR",
                    title: "例行监测",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_ROUTINE_MONITOR").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ASMS_SPECIAL_MONITOR",
                    title: "专项监测",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_SPECIAL_MONITOR").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ASMS_CHECK_TASK",
                    title: "监督抽查",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_CHECK_TASK").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ASMS_EMERGENCY_TASK",
                    title: "应急任务",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_EMERGENCY_TASK").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ASMS_TTS_SCLTXXCJ_COMPLAINT",
                    title: "投诉受理",
                    render: function (data, type, row) {
                        if (
                            $("#ASMS_ALL").is(":checked") ||
                            $("#ASMS_TTS_SCLTXXCJ_COMPLAINT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ALES_DAILY_ENFORCE_LAW",
                    title: "现场巡查",
                    render: function (data, type, row) {
                        if (
                            $("#ALES_ALL").is(":checked") ||
                            $("#ALES_DAILY_ENFORCE_LAW").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ALES_ENTRUST_DETECTION",
                    title: "委托任务",
                    render: function (data, type, row) {
                        if (
                            $("#ALES_ALL").is(":checked") ||
                            $("#ALES_ENTRUST_DETECTION").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ALES_PRODUCE_ADMIN_PUNISH",
                    title: "行政处罚",
                    render: function (data, type, row) {
                        if (
                            $("#ALES_ALL").is(":checked") ||
                            $("#ALES_PRODUCE_ADMIN_PUNISH").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ALES_CHECK_TASK",
                    title: "监督抽查",
                    render: function (data, type, row) {
                        if (
                            $("#ALES_ALL").is(":checked") ||
                            $("#ALES_CHECK_TASK").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ADS_ROUTINE_MONITOR",
                    title: "例行监测",
                    render: function (data, type, row) {
                        if (
                            $("#ADS_ALL").is(":checked") ||
                            $("#ADS_ROUTINE_MONITOR").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ADS_SPECIAL_MONITOR",
                    title: "专项监测",
                    render: function (data, type, row) {
                        if (
                            $("#ADS_ALL").is(":checked") ||
                            $("#ADS_SPECIAL_MONITOR").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ADS_CHECK_TASK",
                    title: "监督抽查",
                    render: function (data, type, row) {
                        if (
                            $("#ADS_ALL").is(":checked") ||
                            $("#ADS_CHECK_TASK").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ADS_DELEGATION",
                    title: "受托检测",
                    render: function (data, type, row) {
                        if (
                            $("#ADS_ALL").is(":checked") ||
                            $("#ADS_DELEGATION").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "ADS_RECHECK_TASK",
                    title: "复检任务",
                    render: function (data, type, row) {
                        if (
                            $("#ADS_ALL").is(":checked") ||
                            $("#ADS_RECHECK_TASK").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "SUM",
                    title: "合计",
                    className: "highlight-orange",
                    render: function (data, type, row, meta) {
                        return (
                            '<span id="' +
                            meta.row +
                            "d" +
                            '">' +
                            $scope.sum(row) +
                            "</span>"
                        );
                    }
                },
                {
                    data: "ENTCOUNT",
                    title: "主体注册",
                    render: function (data, type, row) {
                        if (
                            $("#TTS_ALL").is(":checked") ||
                            $("#ENTCOUNT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "BATCHCOUNT",
                    title: "生成批次总数",
                    render: function (data, type, row) {
                        if (
                            $("#TTS_ALL").is(":checked") ||
                            $("#BATCHCOUNT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "PRINTCOUNT",
                    title: "追溯码打印次数",
                    render: function (data, type, row) {
                        if (
                            $("#TTS_ALL").is(":checked") ||
                            $("#PRINTCOUNT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "SALECOUNT",
                    title: "交易总数",
                    render: function (data, type, row) {
                        if (
                            $("#TTS_ALL").is(":checked") ||
                            $("#SALECOUNT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "SALEOVERCOUNT",
                    title: "交易成功数",
                    render: function (data, type, row) {
                        if (
                            $("#TTS_ALL").is(":checked") ||
                            $("#SALEOVERCOUNT").is(":checked")
                        )
                            if (data < 0) {
                                data = 0;
                            }
                        return data == "0" ? "" : data;
                    }
                },
                {
                    data: "TOTAL",
                    title: "总计",
                    className: "highlight-orange",
                    render: function (data, type, row, meta) {
                        return (
                            '<span id="' +
                            meta.row +
                            '">' +
                            $scope.total(row) +
                            "</span>"
                        );
                    }
                }
            ],
            rowCallback: function (row, data) {
                $(row).toggleClass("highlight-orange", data.AREA_ID == null);
            },
            fnDrawCallback: function () {
                var json = this.api().ajax.json();
                var numberByChecked = function (item, tag, prefix) {
                    prefix = prefix || tag.split('_')[0];
                    var isChecked = $("#" + prefix + "_ALL").is(":checked") || $("#" + tag).is(":checked");
                    var value = item[tag];
                    if(value < 0)
                        value = 0;
                    return isChecked ? (value|| 0) : 0;
                }

                if (json.data && json.data.list) {
                    var ASMS_TOTAL = ALES_TOTAL = ADS_TOTAL = TTS_TOTAL = 0;
                    var data = json.data.list.map(function (item) {
                        if (item.AREA_ID == null) {
                            ASMS_TOTAL += numberByChecked(item, 'ASMS_BASE_INSPECTION') + numberByChecked(item, 'ASMS_ROUTINE_MONITOR') + numberByChecked(item, 'ASMS_SPECIAL_MONITOR') + numberByChecked(item, 'ASMS_CHECK_TASK') + numberByChecked(item, 'ASMS_EMERGENCY_TASK') + numberByChecked(item, 'ASMS_TTS_SCLTXXCJ_COMPLAINT');
                            ALES_TOTAL += numberByChecked(item, 'ALES_DAILY_ENFORCE_LAW') + numberByChecked(item, 'ALES_ENTRUST_DETECTION') + numberByChecked(item, 'ALES_PRODUCE_ADMIN_PUNISH') + numberByChecked(item, 'ALES_CHECK_TASK');
                            ADS_TOTAL += numberByChecked(item, 'ADS_ROUTINE_MONITOR') + numberByChecked(item, 'ADS_SPECIAL_MONITOR') + numberByChecked(item, 'ADS_CHECK_TASK') + numberByChecked(item, 'ADS_DELEGATION') + numberByChecked(item, 'ADS_RECHECK_TASK');
                            TTS_TOTAL += numberByChecked(item, 'ENTCOUNT', 'TTS') + numberByChecked(item, 'BATCHCOUNT', 'TTS') + numberByChecked(item, 'PRINTCOUNT', 'TTS') + numberByChecked(item, 'SALECOUNT', 'TTS') + numberByChecked(item, 'SALEOVERCOUNT', 'TTS');
                        }
                    });

                    $scope.$apply(function () {
                        if (ASMS_TOTAL < 0) {
                            ASMS_TOTAL = 0;
                        }
                        $scope.ASMS_TOTAL = ASMS_TOTAL;
                        if (ALES_TOTAL < 0) {
                            ALES_TOTAL = 0;
                        }
                        $scope.ALES_TOTAL = ALES_TOTAL;
                        if (ADS_TOTAL < 0) {
                            ADS_TOTAL = 0;
                        }
                        $scope.ADS_TOTAL = ADS_TOTAL;
                        if (TTS_TOTAL < 0) {
                            TTS_TOTAL = 0;
                        }
                        $scope.TTS_TOTAL = TTS_TOTAL;
                    });
                }
            }
        });
    }

    $scope.sum = function (row) {
        var sum = 0;
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_BASE_INSPECTION").is(":checked")
        )
            sum += parseInt(row.ASMS_BASE_INSPECTION);
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_ROUTINE_MONITOR").is(":checked")
        )
            sum += parseInt(row.ASMS_ROUTINE_MONITOR);
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_SPECIAL_MONITOR").is(":checked")
        )
            sum += parseInt(row.ASMS_SPECIAL_MONITOR);
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_CHECK_TASK").is(":checked")
        )
            sum += parseInt(row.ASMS_CHECK_TASK);
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_EMERGENCY_TASK").is(":checked")
        )
            sum += parseInt(row.ASMS_EMERGENCY_TASK);
        if (
            $("#ASMS_ALL").is(":checked") ||
            $("#ASMS_TTS_SCLTXXCJ_COMPLAINT").is(":checked")
        )
            sum += parseInt(row.ASMS_TTS_SCLTXXCJ_COMPLAINT);

        if (
            $("#ALES_ALL").is(":checked") ||
            $("#ALES_DAILY_ENFORCE_LAW").is(":checked")
        )
            sum += parseInt(row.ALES_DAILY_ENFORCE_LAW);
        if (
            $("#ALES_ALL").is(":checked") ||
            $("#ALES_ENTRUST_DETECTION").is(":checked")
        )
            sum += parseInt(row.ALES_ENTRUST_DETECTION);
        if (
            $("#ALES_ALL").is(":checked") ||
            $("#ALES_PRODUCE_ADMIN_PUNISH").is(":checked")
        )
            sum += parseInt(row.ALES_PRODUCE_ADMIN_PUNISH);
        if (
            $("#ALES_ALL").is(":checked") ||
            $("#ALES_CHECK_TASK").is(":checked")
        )
            sum += parseInt(row.ALES_CHECK_TASK);

        if (
            $("#ADS_ALL").is(":checked") ||
            $("#ADS_ROUTINE_MONITOR").is(":checked")
        )
            sum += parseInt(row.ADS_ROUTINE_MONITOR);
        if (
            $("#ADS_ALL").is(":checked") ||
            $("#ADS_SPECIAL_MONITOR").is(":checked")
        )
            sum += parseInt(row.ADS_SPECIAL_MONITOR);
        if ($("#ADS_ALL").is(":checked") || $("#ADS_CHECK_TASK").is(":checked"))
            sum += parseInt(row.ADS_CHECK_TASK);
        if ($("#ADS_ALL").is(":checked") || $("#ADS_DELEGATION").is(":checked"))
            sum += parseInt(row.ADS_DELEGATION);
        if (
            $("#ADS_ALL").is(":checked") ||
            $("#ADS_RECHECK_TASK").is(":checked")
        )
            sum += parseInt(row.ADS_RECHECK_TASK);

        return sum > 0 ? sum : "";
    };

    $scope.total = function (row) {
        var sum = $scope.sum(row);
        if (sum == "") sum = 0;

        if ($("#TTS_ALL").is(":checked") || $("#ENTCOUNT").is(":checked"))
            sum += parseInt(row.ENTCOUNT);
        if ($("#TTS_ALL").is(":checked") || $("#BATCHCOUNT").is(":checked"))
            sum += parseInt(row.BATCHCOUNT);
        /*if ($('#TTS_ALL').is(":checked") || $('#PRINTCOUNT').is(":checked"))
            sum += parseInt(row.PRINTCOUNT);*/
        if ($("#TTS_ALL").is(":checked") || $("#SALECOUNT").is(":checked"))
            sum += parseInt(row.SALECOUNT);
        if ($("#TTS_ALL").is(":checked") || $("#SALEOVERCOUNT").is(":checked"))
            sum += parseInt(row.SALEOVERCOUNT);

        return sum > 0 ? sum : "";
    };

    window.regionChange = function (option) {
        if (option.value === "") {
            return;
        }
        if (option.id === "sheng") {
            $("#shi" + " option:gt(0)").remove();
            $("#xian" + " option:gt(0)").remove();

            getRegion(option.value, "shi");
        } else {
            $("#xian" + " option:gt(0)").remove();
            getRegion(option.value, "xian");
        }
    };

    //导出Excel
    $scope.exportData = function () {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/subject/exportSubjDt");
        var input2 = $("<input>");
        input2.attr("type", "hidden");
        input2.attr("name", "status");
        input2.attr("value", $("#status").val());
        var input3 = $("<input>");
        input3.attr("type", "hidden");
        input3.attr("name", "dtAreaId");
        input3.attr("value", $scope.getAreaBySelect());
        var input4 = $("<input>");
        input4.attr("type", "hidden");
        input4.attr("name", "dtName");
        input4.attr("value", $("input[name='dtName']").val());
        var input5 = $("<input>");
        input5.attr("type", "hidden");
        input5.attr("name", "dateBegin");
        input5.attr("value", $("input[name='dateBegin']").val());
        var input6 = $("<input>");
        input6.attr("type", "hidden");
        input6.attr("name", "dateEnd");
        input6.attr("value", $("input[name='dateEnd']").val());
        var input7 = $("<input>");
        input7.attr("type", "hidden");
        input7.attr("name", "token");
        input7.attr("value", window.localStorage.getItem("token"));
        var dtNature = $("input[name='dtNature']:checked");
        var dtNaturelArr = new Array();
        for (var i = 0; i < dtNature.length; i++) {
            dtNaturelArr.push($(dtNature[i]).val());
        }
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "dtNatureId");
        input8.attr("value", dtNaturelArr.toString());
        var dtQualifications = $("input[name='dtQualifications']:checked");
        var dtQualificationsArr = new Array();
        for (var i = 0; i < dtQualifications.length; i++) {
            dtQualificationsArr.push($(dtQualifications[i]).val());
        }
        var input9 = $("<input>");
        input9.attr("type", "hidden");
        input9.attr("name", "dtQualificationsId");
        input9.attr("value", dtQualificationsArr.toString());
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "dtLeader");
        input10.attr("value", $("input[name='dtLeader']").val());
        $("body").append(form);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.append(input8);
        form.append(input9);
        form.append(input10);
        form.submit();
    };

    /**
     * 导出
     */
    $scope.exportExcel = function () {
        //获取被勾选的查询项
        var checkFlag = "";//被勾选中为1，未勾选中为0
        //默认每一项都被勾选中
        var ASMS_BASE_INSPECTION = "1";
        var ASMS_ROUTINE_MONITOR = "1";
        var ASMS_SPECIAL_MONITOR = "1";
        var ASMS_CHECK_TASK = "1";
        var ASMS_EMERGENCY_TASK = "1";
        var ASMS_TTS_SCLTXXCJ_COMPLAINT = "1";

        if (!$('#ASMS_ALL').is(":checked")) {
            if (!$('#ASMS_BASE_INSPECTION').is(":checked")) {
                ASMS_BASE_INSPECTION = "0";
            }
            if (!$('#ASMS_ROUTINE_MONITOR').is(":checked")) {
                ASMS_ROUTINE_MONITOR = "0";
            }
            if (!$('#ASMS_SPECIAL_MONITOR').is(":checked")) {
                ASMS_SPECIAL_MONITOR = "0";
            }
            if (!$('#ASMS_CHECK_TASK').is(":checked")) {
                ASMS_CHECK_TASK = "0";
            }
            if (!$('#ASMS_EMERGENCY_TASK').is(":checked")) {
                ASMS_EMERGENCY_TASK = "0";
            }
            if (!$('#ASMS_TTS_SCLTXXCJ_COMPLAINT').is(":checked")) {
                ASMS_TTS_SCLTXXCJ_COMPLAINT = "0";
            }
        }

        var ALES_DAILY_ENFORCE_LAW = "1";
        var ALES_ENTRUST_DETECTION = "1";
        var ALES_PRODUCE_ADMIN_PUNISH = "1";
        var ALES_CHECK_TASK = "1";

        if (!$('#ALES_ALL').is(":checked")) {
            if (!$('#ALES_DAILY_ENFORCE_LAW').is(":checked")) {
                ALES_DAILY_ENFORCE_LAW = "0";
            }
            if (!$('#ALES_ENTRUST_DETECTION').is(":checked")) {
                ALES_ENTRUST_DETECTION = "0";
            }
            if (!$('#ALES_PRODUCE_ADMIN_PUNISH').is(":checked")) {
                ALES_PRODUCE_ADMIN_PUNISH = "0";
            }
            if (!$('#ALES_CHECK_TASK').is(":checked")) {
                ALES_CHECK_TASK = "0";
            }
        }

        var ADS_ROUTINE_MONITOR = "1";
        var ADS_SPECIAL_MONITOR = "1";
        var ADS_CHECK_TASK = "1";
        var ADS_DELEGATION = "1";
        var ADS_RECHECK_TASK = "1";

        if (!$('#ADS_ALL').is(":checked")) {
            if (!$('#ADS_ROUTINE_MONITOR').is(":checked")) {
                ADS_ROUTINE_MONITOR = "0";
            }
            if (!$('#ADS_SPECIAL_MONITOR').is(":checked")) {
                ADS_SPECIAL_MONITOR = "0";
            }
            if (!$('#ADS_CHECK_TASK').is(":checked")) {
                ADS_CHECK_TASK = "0";
            }
            if (!$('#ADS_DELEGATION').is(":checked")) {
                ADS_DELEGATION = "0";
            }
            if (!$('#ADS_RECHECK_TASK').is(":checked")) {
                ADS_RECHECK_TASK = "0";
            }
        }

        var ENTCOUNT = "1";
        var BATCHCOUNT = "1";
        var PRINTCOUNT = "1";
        var SALECOUNT = "1";
        var SALEOVERCOUNT = "1";

        if (!$('#TTS_ALL').is(":checked")) {
            if (!$('#ENTCOUNT').is(":checked")) {
                ENTCOUNT = "0";
            }
            if (!$('#BATCHCOUNT').is(":checked")) {
                BATCHCOUNT = "0";
            }
            if (!$('#PRINTCOUNT').is(":checked")) {
                PRINTCOUNT = "0";
            }
            if (!$('#SALECOUNT').is(":checked")) {
                SALECOUNT = "0";
            }
            if (!$('#SALEOVERCOUNT').is(":checked")) {
                SALEOVERCOUNT = "0";
            }
        }

        checkFlag = ASMS_BASE_INSPECTION + ASMS_ROUTINE_MONITOR + ASMS_SPECIAL_MONITOR + ASMS_CHECK_TASK + ASMS_EMERGENCY_TASK + ASMS_TTS_SCLTXXCJ_COMPLAINT + ALES_DAILY_ENFORCE_LAW + ALES_ENTRUST_DETECTION + ALES_PRODUCE_ADMIN_PUNISH + ALES_CHECK_TASK + ADS_ROUTINE_MONITOR + ADS_SPECIAL_MONITOR + ADS_CHECK_TASK + ADS_DELEGATION + ADS_RECHECK_TASK + ENTCOUNT + BATCHCOUNT + PRINTCOUNT + SALECOUNT + SALEOVERCOUNT;
        var value = $('#xian').val();
        if (value == "") {
            value = $('#shi').val();
            if (value == "")
                value = $('#sheng').val();
        }
        var dateBegin = $scope.dateBegin;
        var dateEnd = $scope.dateEnd;
        var token = window.localStorage.getItem("token");
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-qry-web/qryStatis/exportWorkSchedule",
                    method: "post",
                    dataType: "json",
                    params: {
                        "areaId": value,
                        "checkFlag": checkFlag,
                        "dateBegin": dateBegin,
                        "dateEnd": dateEnd,
                        "token": token
                    },
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '查询统计报表.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }


    // $scope.exportExcel = function () {
    //     var x = [];
    //     var rows = $("#dtGrid").DataTable().data();
    //     for (var i = 1; i <=rows.length; i++){
    //         var td = rows[i-1];
    //         var obj = new Object();
    //         obj.AREA_NAME = td.AREA_NAME;
    //         obj.ASMS_BASE_INSPECTION = (td.ASMS_BASE_INSPECTION== "0" ? "" : td.ASMS_BASE_INSPECTION);
    //         obj.ASMS_ROUTINE_MONITOR = (td.ASMS_ROUTINE_MONITOR== "0" ? "" : td.ASMS_ROUTINE_MONITOR);
    //         obj.ASMS_SPECIAL_MONITOR = (td.ASMS_SPECIAL_MONITOR== "0" ? "" : td.ASMS_SPECIAL_MONITOR);
    //         obj.ASMS_CHECK_TASK = (td.ASMS_CHECK_TASK== "0" ? "" : td.ASMS_CHECK_TASK);
    //         obj.ASMS_EMERGENCY_TASK = (td.ASMS_EMERGENCY_TASK== "0" ? "" : td.ASMS_EMERGENCY_TASK);
    //         obj.ASMS_TTS_SCLTXXCJ_COMPLAINT = (td.ASMS_TTS_SCLTXXCJ_COMPLAINT== "0" ? "" : td.ASMS_TTS_SCLTXXCJ_COMPLAINT);
    //         obj.ALES_DAILY_ENFORCE_LAW = (td.ALES_DAILY_ENFORCE_LAW== "0" ? "" : td.ALES_DAILY_ENFORCE_LAW);
    //         obj.ALES_ENTRUST_DETECTION = (td.ALES_ENTRUST_DETECTION== "0" ? "" : td.ALES_ENTRUST_DETECTION);
    //         obj.ALES_PRODUCE_ADMIN_PUNISH = (td.ALES_PRODUCE_ADMIN_PUNISH== "0" ? "" : td.ALES_PRODUCE_ADMIN_PUNISH);
    //         obj.ALES_CHECK_TASK = (td.ALES_CHECK_TASK== "0" ? "" : td.ALES_CHECK_TASK);
    //         obj.ADS_ROUTINE_MONITOR = (td.ADS_ROUTINE_MONITOR== "0" ? "" : td.ADS_ROUTINE_MONITOR);
    //         obj.ADS_SPECIAL_MONITOR = (td.ADS_SPECIAL_MONITOR== "0" ? "" : td.ADS_SPECIAL_MONITOR);
    //         obj.ADS_CHECK_TASK = (td.ADS_CHECK_TASK== "0" ? "" : td.ADS_CHECK_TASK);
    //         obj.ADS_DELEGATION = (td.ADS_DELEGATION== "0" ? "" : td.ADS_DELEGATION);
    //         obj.ADS_RECHECK_TASK = (td.ADS_RECHECK_TASK== "0" ? "" : td.ADS_RECHECK_TASK);
    //         var b = i-1;
    //         obj.SUM = $("#"+b+"d").html();
    //         obj.ENTCOUNT = (td.ENTCOUNT== "0" ? "" : td.ENTCOUNT);
    //         obj.BATCHCOUNT = (td.BATCHCOUNT== "0" ? "" : td.BATCHCOUNT);
    //         obj.PRINTCOUNT = (td.PRINTCOUNT== "0" ? "" : td.PRINTCOUNT);
    //         obj.SALECOUNT = (td.SALECOUNT== "0" ? "" : td.SALECOUNT);
    //         obj.SALEOVERCOUNT = (td.SALEOVERCOUNT== "0" ? "" : td.SALEOVERCOUNT);
    //         obj.TOTAL = $("#"+b+"").html();
    //         x.push(obj);
    //     }
    //     var jsonArray = JSON.stringify(x);
    //     var token = window.localStorage.getItem("token");
    //     var submit = function (v, h, f) {
    //         if (v == true)
    //             $http({
    //                 url: "/sofn-qry-web/qryStatis/exportWorkSchedule",
    //                 method: "post",
    //                 dataType: "json",
    //                 params: {
    //                     "jsonArray": jsonArray,
    //                     "token": token
    //                 },
    //                 responseType: 'arraybuffer',
    //                 headers: {"Content-Type": "application/x-www-form-urlencoded"},
    //             }).success(function (data) {
    //                 var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
    //                 if (blob.size > 0) {
    //                     var objectUrl = URL.createObjectURL(blob);
    //                     var a = document.createElement('a');
    //                     document.body.appendChild(a);
    //                     a.setAttribute('style', 'display:none');
    //                     a.setAttribute('href', objectUrl);
    //                     a.setAttribute('download', '查询统计报表.xls');
    //                     a.click();
    //                 } else {
    //                     jBox.tip("导出失败，没有数据！", "info")
    //                 }
    //             });
    //         else
    //             jBox.tip("取消成功", 'success');
    //         return true;
    //     };
    //     // 自定义按钮
    //     $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    // }
});
