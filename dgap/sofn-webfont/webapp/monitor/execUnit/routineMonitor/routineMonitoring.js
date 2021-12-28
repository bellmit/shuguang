var myapp = angular.module("myapp", []);
myapp.controller("routineMonitoring", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    });

    var orgId = "";

    var token = window.localStorage.getItem("token");

    /**
     * 分页展示数据
     */
    $(function() {
        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
            type: "post",
            dataType: "json",
            data: { token: token },
            success: function(data) {
                orgId = data.user.orgId;
                $("#grid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url:
                    "/sofn-ads-web/adsMonitorTask/getPageInfoWithParam?time=" +
                    new Date(),
                dataSrc: function(json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function(params) {
                    params.industry = $("#hangye option:selected").text();
                    var yearparam = $("#year").val();
                    if (yearparam == "") {
                        params.year = "全部";
                    } else {
                        params.year = $("#year").val();
                    }
                    params.status = $("#status option:selected").val();
                    params.level = $("#level option:selected").val();
                    params.orgId = orgId;
                    params.token = token;
                    params.type = "例行监测";
                },
                type: "post"
            },
            columns: [
                {
                    title:
                        "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render: function(data, type, row) {
                        // 数据操作选择项
                        var view =
                            "<input type='checkbox' name='task' value='" +
                            row.id +
                            "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号",
                    render: function(data, type, row) {
                        var datax = row.id;
                        var token = window.localStorage.getItem("token");
                        var url =
                            "/sofn-ads-web/adsOrganTask/getOrganStatus?time=" +
                            new Date();
                        $http({
                            url: url,
                            method: "post",
                            params: { id: datax, token: token }
                        })
                            .success(function(data) {
                                var status = data.data;
                                if (0 == status) {
                                    // var node = document.getElementById(datax);
                                    var Row2 = "";
                                    Row2 =
                                        '<span id="' +
                                        row.id +
                                        '" class="pull-left task-new">New</span>';
                                    $("#" + datax + "").html(Row2);
                                    // node.remove();
                                }
                            })
                            .error(function() {});
                        return (
                            '<span id="' +
                            row.id +
                            '"></span>' +
                            "&nbsp;&nbsp;" +
                            data
                        );
                    }
                },
                {
                    data: "year",
                    title: "年度"
                },
                {
                    data: "levelEnum",
                    title: "级别",
                    render: function(data, type, row) {
                        if ("0 " == data) {
                            return "部级";
                        } else if ("1 " == data) {
                            return "省级";
                        } else if ("2 " == data) {
                            return "市级";
                        } else {
                            return "县级";
                        }
                    }
                },
                {
                    data: "industry",
                    title: "监测行业"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    render: function(data, type, row) {
                        if (data != null && data.length > 30) {
                            var look_a =
                                '<a id="RMNAME' +
                                row.id +
                                '" onmouseover="showPopContent(' +
                                "'" +
                                "RMNAME" +
                                row.id +
                                "'" +
                                "," +
                                "'" +
                                data +
                                "'" +
                                ')"  style="margin-right: 10px;cursor: pointer" onclick="goto(' +
                                "'" +
                                row.id +
                                "'" +
                                "," +
                                "'" +
                                row.publishStatus +
                                "'" +
                                "," +
                                "'RMNAME" +
                                row.id +
                                "'" +
                                ')">' +
                                data.substring(0, 29) +
                                "..." +
                                "</a>";
                            return look_a;
                        } else {
                            var look_a =
                                '<a style="margin-right: 10px;cursor: pointer" onclick="goto(' +
                                "'" +
                                row.id +
                                "'" +
                                "," +
                                "'" +
                                row.publishStatus +
                                "'" +
                                ')">' +
                                data +
                                "</a>";
                            return look_a;
                        }
                    }
                },
                {
                    data: "releaseUnit",
                    title: "主管单位"
                },
                {
                    data: "startTime",
                    title: "开始时间",
                    render: function(data, type, row) {
                        return new Date(data).format("yyyy-MM-dd");
                    }
                },
                {
                    data: "endTime",
                    title: "结束时间",
                    render: function(data, type, row) {
                        return new Date(data).format("yyyy-MM-dd");
                    }
                },
                {
                    data: "priority",
                    title: "优先级",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        return getPriorityName(data);
                    }
                },
                {
                    data: "publishStatus",
                    title: "状态",
                    render: function(data, type, row) {
                        var datax = row.id;

                        var token = window.localStorage.getItem("token");
                        var url =
                            "/sofn-ads-web/adsOrganTask/getOrganStatusForB?time=" +
                            new Date();
                        $http({
                            url: url,
                            method: "post",
                            params: { id: datax, token: token }
                        })
                            .success(function(data) {
                                var status = data.data;
                                var node = document.getElementById(
                                    "status" + datax
                                );
                                if (2 == status) {
                                    $("#status" + datax)
                                        .addClass("label label-success")
                                        .html("执行中")
                                        .attr("val", "2");
                                } else if (3 == status) {
                                    $("#status" + datax)
                                        .addClass("label label-default")
                                        .html("已结束,已完成")
                                        .attr("val", "3");
                                } else if (4 == status) {
                                    $("#status" + datax)
                                        .addClass("label label-warning")
                                        .html("已结束,未完成")
                                        .attr("val", "4");
                                } else if (5 == status) {
                                    $("#status" + datax)
                                        .addClass("label label-danger")
                                        .html("已废止")
                                        .attr("val", "5");
                                } else {
                                    $("#status" + datax)
                                        .addClass("label label-primary")
                                        .html("已发布");
                                }
                            })
                            .error(function() {});
                        return (
                            '<a data-toggle="modal" data-target="#statusModal" data-id="' +
                            row.id +
                            '" id="status' +
                            datax +
                            '" style="line-height:2;" ></a>'
                        );
                    }
                }
            ]
        });

        //获取发布状态名称
        function getPriorityName(type) {
            var str = "";
            switch (type){
                case 1:
                    str = '<span style="line-height:2;" class="label label-danger">特急</span>';
                    break;
                case 2:
                    str = '<span style="line-height:2;" class="label label-warning">紧急</span>';
                    break;
                case 3:
                    str = '<span style="line-height:2;" class="label label-info">一般</span>';
                    break;
                default:
                    str = '<span style="line-height:2;" class="label label-info">一般</span>';
                    break;
            }
            return str;
        }
        /**
         * 导出
         */
        $scope.exportExcel = function() {
            var check = $("input[name='task']:checked");
            if (check.length > 0) {
                var arr = new Array();
                $(check).each(function() {
                    var task = $(this)[0];
                    var id = $(task).val();
                    arr.push(id);
                });
                var submit = function(v, h, f) {
                    if (v == true)
                        $http({
                            url:
                                "/sofn-ads-web/adsMonitorTask/exportAdsTaskExcel",
                            method: "post",
                            dataType: "json",
                            params: {
                                ids: arr.toString(),
                                token: token
                            },
                            responseType: "arraybuffer",
                            headers: {
                                "Content-Type":
                                    "application/x-www-form-urlencoded"
                            }
                        }).success(function(data) {
                            var blob = new Blob([data], {
                                type:
                                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                            });
                            if (blob.size > 0) {
                                var objectUrl = URL.createObjectURL(blob);
                                var a = document.createElement("a");
                                document.body.appendChild(a);
                                a.setAttribute("style", "display:none");
                                a.setAttribute("href", objectUrl);
                                a.setAttribute("download", "导出任务.xls");
                                a.click();
                            } else {
                                jBox.tip("导出失败，没有数据！", "info");
                            }
                        });
                    else jBox.tip("取消成功", "success");
                    return true;
                };
                // 自定义按钮
                $.jBox.confirm(
                    "导出操作需要花费一点时间，点击确认继续",
                    "操作提示",
                    submit,
                    { buttons: { 确认: true, 取消: false } }
                );
            } else {
                jBox.tip("请选择你需要导出的记录", "info");
            }
        };
       /* var url = "/sofn-ads-web/adsMonitorTask/getHangye?time=" + new Date();*/
       var url = "/sofn-ads-web/adsDictData/getIndustryTypeData";
        //行业下拉框
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
              /*  var datas = data.data;
                var Row = "<option>全部</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        Row = Row + "<option>" + td.industry + "</option> ";
                    }
                }
                $("#hangye").html(Row);*/
                $scope.industry = data.data;
                var Row = '<option>全部</option> ';
                for (var i = 0; i < $scope.industry.length; i++) {
                    Row = Row + '<option value= "' + $scope.industry[i].id + '">' + $scope.industry[i].dictName + '</option>'
                }
                $("#hangye").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var urlYear = "/sofn-ads-web/adsMonitorTask/getYear?time=" + new Date();
        //年度下拉框
        $.ajax({
            url: urlYear,
            type: "post",
            dataType: "json",
            data: { phase: 1 },
            success: function(data) {
                var datas = data.data;
                var Row = "<option>全部</option> ";
                if (datas.length > 0) {
                    for (var i = 1; i <= datas.length; i++) {
                        var td = datas[i - 1];
                        Row = Row + "<option>" + td.year + "</option> ";
                    }
                }
                $("#year").html(Row);
            },
            fail: function(data) {
                $.jBox.tip("失败");
            }
        });

        var selectHangYe = document.getElementById("hangye");
        selectHangYe.onchange = function() {
            var hangye = $("#hangye option:selected").text();
            if ("全部" != hangye) {
                if (document.getElementById("hli")) {
                    var obj = document.getElementById("hli");
                    obj.parentNode.removeChild(obj);
                }
                var lihtml =
                    '<li style="margin: 10px;float: left" id="hli">' +
                    "监测行业：" +
                    hangye +
                    '<button class="gui-btn" id="hangye" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                var lilength = $(".setslist ul li").length;
                if (lilength == 0) {
                    $(".setslist ul").prepend(lihtml);
                } else {
                    $(".setslist ul li:last").after(lihtml);
                }
            } else {
                if (document.getElementById("hli")) {
                    var obj = document.getElementById("hli");
                    obj.parentNode.removeChild(obj);
                }
            }
        };

        var selectYear = document.getElementById("year");
        selectYear.onblur = function() {
            var year = $("#year").val();
            if (year != "") {
                if ("全部" != year) {
                    if (document.getElementById("yli")) {
                        var obj = document.getElementById("yli");
                        obj.parentNode.removeChild(obj);
                    }
                    var lihtml =
                        '<li style="margin: 10px;float: left" id="yli">' +
                        "年度：" +
                        year +
                        '<button class="gui-btn" id="year" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                    var lilength = $(".setslist ul li").length;
                    if (lilength == 0) {
                        $(".setslist ul").prepend(lihtml);
                    } else {
                        $(".setslist ul li:last").after(lihtml);
                    }
                } else {
                    if (document.getElementById("yli")) {
                        var obj = document.getElementById("yli");
                        obj.parentNode.removeChild(obj);
                    }
                }
            }
        };

        var selectLevel = document.getElementById("level");
        selectLevel.onchange = function() {
            var level = $("#level option:selected").text();
            if ("全部" != level) {
                if (document.getElementById("eli")) {
                    var obj = document.getElementById("eli");
                    obj.parentNode.removeChild(obj);
                }
                var lihtml =
                    '<li style="margin: 10px;float: left" id="eli">' +
                    "级别：" +
                    level +
                    '<button class="gui-btn" id="level" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                var lilength = $(".setslist ul li").length;
                if (lilength == 0) {
                    $(".setslist ul").prepend(lihtml);
                } else {
                    $(".setslist ul li:last").after(lihtml);
                }
            } else {
                if (document.getElementById("eli")) {
                    var obj = document.getElementById("eli");
                    obj.parentNode.removeChild(obj);
                }
            }
        };

        var selectStatus = document.getElementById("status");
        selectStatus.onchange = function() {
            var status = $("#status option:selected").text();
            if ("全部" != status) {
                if (document.getElementById("sli")) {
                    var obj = document.getElementById("sli");
                    obj.parentNode.removeChild(obj);
                }
                var lihtml =
                    '<li style="margin: 10px;float: left" id="sli">' +
                    "状态：" +
                    status +
                    '<button class="gui-btn" id="status" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                var lilength = $(".setslist ul li").length;
                if (lilength == 0) {
                    $(".setslist ul").prepend(lihtml);
                } else {
                    $(".setslist ul li:last").after(lihtml);
                }
            } else {
                if (document.getElementById("sli")) {
                    var obj = document.getElementById("sli");
                    obj.parentNode.removeChild(obj);
                }
            }
        };

        $(".setslist").on("click", "button", function() {
            var $this = $(this);
            $this.parents("li").remove();
            var aa = $(this).attr("id");
            if (aa == "year") {
                document.getElementById("year").value = "全部";
            } else {
                var a = document.getElementById(aa);
                a.options[0].selected = true;
            }
        });

        $(".cq2").on("click", function() {
            $(".setslist ul li").remove();
            document.getElementById("hangye").options[0].selected = true;
            //document.getElementById('year').options[0].selected = true;
            document.getElementById("year").value = "全部";
            document.getElementById("level").options[0].selected = true;
            document.getElementById("status").options[0].selected = true;
        });
    });

    window.goto = function(id, rowId) {
        var token = window.localStorage.getItem("token");
        $http({
            url: "/sofn-ads-web/adsMonitorTask/queryAndUpdateStatusById",
            method: "POST",
            params: { id: id, token: token }
        })
            .success(function(data) {})
            .error(function() {});

        if (rowId != null && rowId != undefined) {
            $("#" + rowId).popover("hide");
        }
        var status = $("#status" + id).attr("val");
        $scope.sampleOrganId = orgId; //通过token获取抽样机构代码
        window.localStorage.setItem("adsMonitorTaskId", id); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("sampleOrganId", $scope.sampleOrganId); //监测任务id
        window.localStorage.setItem("type", "1"); //类型
        window.localStorage.setItem("status", status);
        hidePopContent("RMNAME" + id);
        $state.go(
            "index.content.monitor/execUnit/AdsOrganTask/taskDetailAdsOrgan"
        );
    };
    //全选
    window.checkAll = function() {
        var val = $("input[name='checkAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='task']").attr("checked", true);
        } else {
            $("input[name='task']").attr("checked", false);
        }
    };

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

    //鼠标悬停弹窗显示
    window.hidePopContent = function(id) {
        $("#" + id).popover("hide");
    };
    var graph;
    $("#statusModal")
        .on("shown.bs.modal", function(evt) {
            var sourceData = {
                nodes: [],
                edges: []
            };

            var id = $.trim($(evt.relatedTarget).attr("data-id"));
            function requestItem1(callback) {
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-ads-web/adsMonitorTask/queryById",
                    method: "post",
                    dataType: "json",
                    data: id
                }).success(function(data) {
                    if (data.code != 200) {
                        swal("流程状态", "查询流程状态信息出错", "error");
                        return;
                    }
                    var adsMonitorTask = data.adsMonitorTask;
                    $scope.currentAdsMonitorTask = adsMonitorTask;
                    sourceData.nodes.push({
                        id: adsMonitorTask.taskName + ' - '+ (new Date(adsMonitorTask.createTime).format('yyyy-MM-dd')),
                        css:{
                            stroke: "#faebcc",
                            fill: "#fcf8e3",
                            fillOpacity: 0.45,
                            lineWidth: 2
                        }
                    });
                    callback && callback();
                });
            }
            function requestItem2() {
                $http({
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-ads-web/adsOrganTask/getPageInfoByMonitorTask",
                    method: "post",
                    dataType: "json",
                    data: $.param({ monitorTaskId: id, start: 0, length: 50 })
                }).success(function(res) {
                    if (res.httpCode != 200) {
                        swal("流程状态", "查询流程状态信息出错", "error");
                        return;
                    }
                    res.data.list.map(function(item) {
                        if(item.tasksStatus == 'N')
                            item.tasksStatus = '6';
                        sourceData.nodes.push({
                            id: item.sampleOrgan + '- '+ ['未发布', '已发布','执行中','已结束,已完成','已结束，未完成','已废止','已发布'][item.tasksStatus],
                            css:{
                                stroke: ["#d9edf7","#337ab7","#5cb85c","#777","#f0ad4e","#777","#337ab7"][item.tasksStatus] || '#faebcc',
                                fill: ["#d9edf7","#337ab7","#5cb85c","#777","#f0ad4e","#777","#337ab7"][item.tasksStatus] || '#fcf8e3',
                                fillOpacity: 0.45,
                                lineWidth: 2
                            }
                        });
                        sourceData.edges.push({
                            source: sourceData.nodes[0].id,
                            target: item.sampleOrgan + '- '+ ['未发布', '已发布','执行中','已结束,已完成','已结束，未完成','已废止','已发布'][item.tasksStatus],
                        });
                        sourceData.edges.push({
                            source: item.sampleOrgan + '- '+ ['未发布', '已发布','执行中','已结束,已完成','已结束，未完成','已废止','已发布'][item.tasksStatus],
                            target:"结束 - " + (new Date($scope.currentAdsMonitorTask.endTime).format('yyyy-MM-dd')),
                        });
                    });
                    sourceData.nodes.push({
                        id: "结束 - " + (new Date($scope.currentAdsMonitorTask.endTime).format('yyyy-MM-dd')),
                        css:{
                            stroke: '#30aa81',
                            fill: '#47b995',
                            fillOpacity: 0.45,
                            lineWidth: 2
                        }
                    });
                    console.log("sourceData", sourceData);
                    renderChart(sourceData);
                });
            }

            function renderChart(data) {
                G6.registerNode("rect", {
                    getPath: function getPath(item) {
                        var width = 250; // 一半宽
                        var height = 40; // 一半高
                        return G6.Util.getRectPath(
                            -width / 2,
                            -height / 2,
                            width,
                            height,
                            10
                        );
                    }
                });

                graph = new G6.Graph({
                    container: "g6MountNode",
                    fitView: "cc",
                    height: 400, // 画布高
                    plugins: [new G6.Plugins["layout.dagre"]()],
                    defaultIntersectBox: "rect" // 使用矩形包围盒
                });

                graph.node({
                    shape: "rect",
                    label: function label(model) {
                        return model.id;
                    },
                    style: function(model) {
                        console.log(model)
                        return model.css;
                    }
                    
                });
                graph.edge({
                    style: {
                        endArrow: true
                    }
                });
                graph.read(data);
                JSON.stringify(data);
            }
            requestItem1(requestItem2);
        })
        .on("hidden.bs.modal", function(evt) {
            if (graph) {
                graph.destroy();
            }
        });
});
