var myapp = angular.module("myapp", []);
myapp.controller("monitorTaskList_new", function($scope, $http, $state) {
    $("#toCheck").click(function() {
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    });
    //重置
    $scope.resetTask = function() {
        $("#year").val("");
        $("#cdatebegin").val("");
        $("#cdateend").val("");
        $("#datebegin").val("");
        $("#dateend").val("");
        $("#monitorClass").val("");
        $("#industry").val("");
        $("#ReleaseUnit").val("");
        $("#taskName").val("");
        $('#sheng, #shi, #xian').val('');
        $('#separation').find('option:first').prop('selected', true);
        $scope.separation = null;
        $scope.orgName = null;
        $scope.cityCode = null;
        $scope.checkUnit = null;
        $scope.checkObject = null;
        $scope.checkProject = null;
        $scope.sampleUnit = null;
        $scope.sampleArea = null;
        $scope.deparment = null;
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
        // jBox.tip("重置成功！","success");
    };

    var token = window.localStorage.getItem("token");
        function formatParams(str) {
            return str == undefined ? "" : str;
        }
    $(function() {
        $.fn.mycity("sheng", "shi", "xian", 0, 0, 0);

        var urlIndustry = "/sofn-qry-web/adsMonitorTask/getIndustry";
        //行业下拉框
        $.ajax({
            url: urlIndustry,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.INDUSTRY || "" == td.INDUSTRY) {
                            continue;
                        }
                        Row = Row + "<option>" + td.INDUSTRY + "</option> ";
                    }
                }
                $("#industry").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlReleaseUnit = "/sofn-qry-web/adsMonitorTask/getReleaseUnit";
        //发布机构下拉框
        $.ajax({
            url: urlReleaseUnit,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.RELEASE_UNIT || "" == td.RELEASE_UNIT) {
                            continue;
                        }
                        Row = Row + "<option>" + td.RELEASE_UNIT + "</option> ";
                    }
                }
                $("#ReleaseUnit").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlSampleLink = "/sofn-qry-web/adsMonitorTask/getSampleLink";
        //检测环节下拉框
        $.ajax({
            url: urlSampleLink,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.SAMPLE_LINK || "" == td.SAMPLE_LINK) {
                            continue;
                        }
                        Row = Row + "<option>" + td.SAMPLE_LINK + "</option> ";
                    }
                }
                $("#SampleLink").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlDetectionOrgan =
            "/sofn-qry-web/adsMonitorTask/getDetectionOrgan";
        //检测单位下拉框
        $.ajax({
            url: urlDetectionOrgan,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (
                            null == td.detectionOrgan ||
                            "" == td.detectionOrgan
                        ) {
                            continue;
                        }
                        Row =
                            Row + "<option>" + td.detectionOrgan + "</option> ";
                    }
                }
                $("#DetectionOrgan").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlSampleOrgan = "/sofn-qry-web/adsMonitorTask/getSampleOrgan";
        //抽样单位下拉框
        $.ajax({
            url: urlSampleOrgan,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>不限</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        if (null == td.sampleOrgan || "" == td.sampleOrgan) {
                            continue;
                        }
                        Row = Row + "<option>" + td.sampleOrgan + "</option> ";
                    }
                }
                $("#SampleOrgan").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-qry-web/adsMonitorTask/getRoutineMonitorListNew",
                headers: {
                    token: token
                },
                dataSrc: function(json) {
                    console.log(json);
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.data.total;
                    });
                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.a = "a";
                    var xian = $("#xian option:selected").val();
                    var shi = $("#shi option:selected").val();
                    var sheng = $("#sheng option:selected").val();
                    if ("" != xian) {
                        params.area = xian;
                    } else if ("" != shi) {
                        params.area = shi;
                    } else {
                        params.area = sheng;
                    }
                    params.taskName = $("#taskName").val();
                    params.year = $("input[name='queryYear']").val();
                    params.cstartTime = $("input[name='cstartTime']").val(); //开始时间
                    params.cendTime = $("input[name='cendTime']").val(); //结束时间
                    params.startTime = $("input[name='startTime']").val(); //开始时间
                    params.endTime = $("input[name='endTime']").val(); //结束时间
                    params.monitorClass = $(
                        "#monitorClass option:selected"
                    ).text();
                    params.industry = $("#industry option:selected").text();
                    params.result = $("#result option:selected").text();
                    params.ReleaseUnit = $(
                        "#ReleaseUnit option:selected"
                    ).text();
                    params.qiantou = $("#qiantou option:selected").text();
                    params.SampleLink = $("#SampleLink option:selected").text();
                    params.DetectionOrgan = $(
                        "#DetectionOrgan option:selected"
                    ).text();
                    params.SampleOrgan = $(
                        "#SampleOrgan option:selected"
                    ).text();
                    params.token = token;

                    params.cityCode = $('#xian').val();
                    
                    params.separation = formatParams($scope.separation);
                    params.orgName = formatParams($scope.orgName);
                    params.cityCode = formatParams($scope.cityCode);
                    params.checkUnit = formatParams($scope.checkUnit);
                    params.checkObject = formatParams($scope.checkObject);
                    params.checkProject = formatParams($scope.checkProject);
                    params.sampleUnit = formatParams($scope.sampleUnit);
                    params.sampleArea = formatParams($scope.sampleArea);
                    params.deparment = formatParams($scope.deparment);

                    // params.keyWord = $("input[name='keyWord']").val();//关键词   暂时不知道怎么用
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    width: "25%"
                },
                {
                    data: "year",
                    title: "任务年度"
                },
                {
                    data: "monitorClass",
                    title: "任务类型",
                    width: "7%"
                },
                {
                    data: "industry",
                    title: "监测行业"
                },

                {
                    data: "releaseUnit",
                    title: "发布机构"
                },
                {
                    data: "createTime",
                    title: "创建时间",
                    render: function(data, type, row) {
                        return new Date(data).format("yyyy-MM-dd");
                    }
                },
                {
                    data: "startTime",
                    title: "发布时间",
                    render: function(data, type, row) {
                        return new Date(data).format("yyyy-MM-dd");
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function(data, type, row) {
                        var look_a =
                            '<a style="margin-right: 10px;cursor: pointer" onclick="goto(\'' +
                            row.id +
                            "')\">" +
                            "查看" +
                            "</a>";
                        if (
                            row.monitorClass == "例行监测" ||
                            row.monitorClass == "专项监测"
                        ) {
                            // look_a += ' <a style="cursor:pointer;margin-right: 10px;" onclick="queryTaskCondition(\''+row.id+'\')" class="color">任务完成情况</a>';
                            look_a +=
                                ' <a style="cursor:pointer;margin-right: 10px;" onclick="queryTaskCondition(\'' +
                                row.id +
                                "'" +
                                ",'" +
                                row.taskName +
                                '\')"class="color">任务完成情况</a>';
                        } else {
                            look_a +=
                                ' <a style="cursor: pointer;opacity: 0.2;margin-right: 10px;" class="color" disabled="disabled">任务完成情况</a>';
                        }
                        look_a +=
                            '<a style="cursor:pointer;margin-right: 10px;" onclick="querySamples(\'' +
                            row.id +
                            "','" +
                            row.monitorClass +
                            "','" +
                            row.taskName +
                            "','" +
                            row.industry +
                            '\')" class="color">抽样单</a>  ';
                        look_a +=
                            '<a style="cursor:pointer" onclick="queryReport(\'' +
                            row.id +
                            "','" +
                            row.monitorClass +
                            "','" +
                            row.taskName +
                            "','" +
                            row.industry +
                            '\')" class="color">承担单位报告</a>  ';
                        return look_a;
                    }
                }
            ]
        });
    });

    window.goto = function(data) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        $state.go("index.content.qry/monitoringSystem/taskDetail");
    };

    window.queryTaskCondition = function(data, taskName) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("taskName", taskName); //监测任务id
        $state.go("index.content.qry/monitoringSystem/taskCondition");
    };

    window.querySamples = function(data, monitorClass, taskName, industry) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        if (
            monitorClass == "例行监测" ||
            monitorClass == "专项监测" ||
            monitorClass == "复检任务"
        ) {
            $state.go("index.content.qry/monitoringSystem/qrySampleMessage");
        } else if (monitorClass == "监督抽查") {
            $state.go(
                "index.content.qry/monitoringSystem/qryAuditSampleMessage"
            );
        } else if (monitorClass == "受托检测") {
            $state.go(
                "index.content.qry/monitoringSystem/qryDelegationSampleMessage"
            );
        }
    };

    window.queryReport = function(data, monitorClass, taskName, industry) {
        window.localStorage.setItem("adsMonitorTaskId", data); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("taskName", taskName); //任务名称
        window.localStorage.setItem("monitorClass", monitorClass); //任务类型
        $state.go("index.content.qry/monitoringSystem/qryReportsList");
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
                            control.find('option:first').prop('selected', true).trigger('change');
                            break;
                        case "checkbox":
                            $scope[key] = "";
                            control.find(':checkbox').first().prop('checked', true).end().siblings().prop('checked', false);
                            break;
                        case "date":
                            control.find('input:text').val('').trigger('input');
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
});
