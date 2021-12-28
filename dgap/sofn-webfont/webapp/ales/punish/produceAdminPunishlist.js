var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("produceAdminPunishlist", function($scope, $http, $state,$timeout) {
        var vm =this;
        $scope.authorityArray = window.localStorage.getItem("menus");
        var authorityArray = JSON.parse($scope.authorityArray);
        var author = "";
        for (var i = 0; i < authorityArray.length; i++) {
            if (authorityArray[i].id == "516") {
                if (authorityArray[i].authority == "1") {
                    author = authorityArray[i].authority;
                    $("#newId").attr("disabled", true);
                    $("#produceId").attr("disabled", true);
                    $("#delId").attr("disabled", true);
                    break;
                }
            }
        }
        //获取行业数据字典
        $scope.industryType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 2,
            method: "post"
        }).success(function(data) {
            $scope.industryType = JSON.parse(data.data);
        });

        //获取主体类型数据字典
        $scope.entityType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 31,
            method: "post"
        }).success(function(data) {
            $scope.entityType = JSON.parse(data.data);
        });
        function formatParams(str) {
            //return str == undefined ? "" : str;
            if(str == undefined){
                return "";
            }else if(str == "%"){
                return "@";
            }
            return str;
        }
        //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
        $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
            for (
                var i = 0;
                i < $("input[name='" + limitCheckBoxName + "']").length;
                i++
            ) {
                var lim = $("input[name='" + limitCheckBoxName + "']")[i]
                    .checked;
                if (lim) {
                    $("input[name='" + checkBoxName + "']:checked").attr(
                        "checked",
                        false
                    );
                }
            }
        };
        /**
         * 默认取用户所属区域,设置区域省市悬三级联动
         * 注释日期:2018年2月26日10:17:55
         */
        var area = "";
        var jibie = "";
        var orgId = "";
        $http({
            url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
            method: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
            .success(function(data) {
                orgId = data.data.orgId;
                area = data.data.regionId;
                jibie = data.data.orgLevel;
                if (jibie == 1) {
                    area = 0;
                }
                if (jibie == 2) {
                    area = area.substr(0, 2);
                }
                if (jibie == 3) {
                    area = area.substr(0, 4);
                }
                if (jibie == 4) {
                    area = area.substring(0, 6);
                }
                $.fn.mycity("sheng", "shi", "xian", area);
                //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
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
                //所属行业默认不限、组织形式不限、主体类别不限
                $("input[name='entityIndustryLimit']:first").prop(
                    "checked",
                    true
                );
                // $("input[name='entityScaleLimit']:first").attr("checked",true);
                $("input[name='entityTypeLimit']:first").prop("checked", true);
                loadGrid();
            })
            .error(function(msg) {
                console.log(msg);
                
            });
        /**
         * 获取当前登录用户Id
         * 注释日期:2018年2月26日10:17:32
         */
        $scope.linePoliticsUserId = "";
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/llesEntrustDetection/getCreateBy",
            data: "2",
            method: "post"
        })
            .success(function(data) {
                $scope.linePoliticsUserId = data.data;
            })
            .error(function(data) {
                
            });
        /**
         * 重置查询参数,默认显示用户所属区域内行政处罚信息
         * 注释日期:2018年2月26日10:17:17
         */
        $scope.cleanSelectModelQuery = function($event) {
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
            $("input[name='taskYear']").val("");
            $("input[name='datebegin']").val("");
            $("input[name='dateend']").val("");
            $("input[name='queryCon']").val("");
            //所属行业默认不限、组织形式不限、主体类别不限
            $("input[name='entityIndustry']").prop("checked", false);
            $("input[name='entityType']").prop("checked", false);
            $("input[name='entityIndustryLimit']:first").prop("checked", true);
            // $("input[name='entityScaleLimit']:first").attr("checked",true);
            $("input[name='entityTypeLimit']:first").prop("checked", true);
            $.fn.mycity("sheng", "shi", "xian", area);
            //绑值
            $scope.caseName = null;
            $scope.penaltySource = "";
            $scope.punishCode = null;
            $scope.productName = null;
            $scope.enteriseName = null;
            $scope.manufactureDate = null;
            $scope.enforceLawPeople = null;
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };
        /**
         * 点击查询按钮刷新datatable
         * 注释日期:2018年2月26日10:17:00
         */
        /*$("#ToCheck").click(function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        });*/
        /**查询按钮*/
        $scope.querySelectList = function ($event) {
            $("#grid").dataTable().api().ajax.reload();
            $($event.currentTarget).text("二次查询");
        }
        /**
         * 默认查询绑定回车事件。
         * 注释日期:2018年2月26日10:16:45
         */
        $("#queryCon").keydown(function(e) {
            if (e.keyCode == 13) {
                $("#grid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            }
        });
        /**
         * 行政处罚信息分页呈现,采用bootstrap表格。
         * 注释日期:2018年2月26日10:16:33
         */
        function loadGrid() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[7, "desc"]], //默认排序项
                scrollX: true, //横向滚动
                fnDrawCallback: function() {
                    //点击行内任意内容即选择行
                    $("#grid tbody tr td").each(function(i, o) {
                        $(o).click(function() {
                            var checked = $(this)
                                .parent()
                                .find("input[type='radio']")[0];
                            if (checked.checked) {
                                checked.checked = false;
                                $(this)
                                    .parent()
                                    .removeClass("selected");
                                //移除
                                var u_d = $(this)
                                    .parent()
                                    .find("[name='ck']")
                                    .val();
                            } else {
                                checked.checked = true;
                                $(this)
                                    .parent()
                                    .addClass("selected");
                            }
                        });
                    });
                },
                ajax: {
                    url:
                        "/sofn-ales-web/administrativePenalty/getAdministrativePenaltyList_two",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function(json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        //console.log(json.data.list)
                        for (var i = 0; i < json.data.list.length; i++) {
                            if (
                                json.data.list[i].ENFORCE_LAW_TIME != null &&
                                json.data.list[i].ENFORCE_LAW_TIME != "" &&
                                json.data.list[i].ENFORCE_LAW_TIME != undefined
                            ) {
                                json.data.list[i].ENFORCE_LAW_TIME = new Date(
                                    parseInt(
                                        json.data.list[i].ENFORCE_LAW_TIME
                                    ) + 28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].CREATE_TIME != null &&
                                json.data.list[i].CREATE_TIME != "" &&
                                json.data.list[i].CREATE_TIME != undefined
                            ) {
                                json.data.list[i].CREATE_TIME = new Date(
                                    parseInt(json.data.list[i].CREATE_TIME) +
                                        28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                        }
                        $scope.$apply(function() {
                            $scope.tableTotal = json.data.total;
                        });
                        return json.data.list;
                    },
                    error: function(msg) {
                        console.log(msg);
                        
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function(params) {
                        params.xcPsersionId = window.localStorage.getItem(
                            "xcPsersionId",
                            "xcPsersionId"
                        );
                        params.taskYear = $("input[name='taskYear']").val();
                        params.queryCon = $("input[name='queryCon']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.createOrgId = orgId;
                        //绑值
                        //所属行业
                        var entityIndustry = $(
                            "input[name='entityIndustry']:checked"
                        );
                        var arr01 = new Array();
                        for (var i = 0; i < entityIndustry.length; i++) {
                            arr01.push($(entityIndustry[i]).val());
                        }
                        params.entityIndustry = arr01.join(",");

                        //主体类型
                        var entityType = $("input[name='entityType']:checked");
                        var arr02 = new Array();
                        for (var i = 0; i < entityType.length; i++) {
                            arr02.push($(entityType[i]).val());
                        }
                        params.entityType = arr02.join(",");

                        params.caseName = formatParams($scope.caseName);
                        console.log("................"+params.caseName);
                        params.penaltySource = formatParams(
                            $scope.penaltySource
                        );
                        params.punishCode = formatParams($scope.punishCode);
                        params.productName = formatParams($scope.productName);
                        params.enteriseName = formatParams($scope.enteriseName);
                        params.manufactureDate = formatParams(
                            $scope.manufactureDate
                        );
                        params.enforceLawPeople = formatParams(
                            $scope.enforceLawPeople
                        );

                        ObjShen = $("select[name='ObjShen']").val();
                        ObjShi = $("select[name='ObjShi']").val();
                        ObjXian = $("select[name='ObjXian']").val();
                        if (
                            ObjShen != "" &&
                            ObjShen != null &&
                            ObjShen != undefined
                        ) {
                            if (
                                ObjShi != "" &&
                                ObjShi != null &&
                                ObjShi != undefined
                            ) {
                                if (
                                    ObjXian != "" &&
                                    ObjXian != null &&
                                    ObjXian != undefined
                                ) {
                                    params.area = ObjXian;
                                } else {
                                    params.area = ObjShi;
                                }
                            } else {
                                params.area = ObjShen;
                            }
                        }
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
                       $timeout(function () {
                            vm.chart.update(); //刷新图标
                        });
                    },
                    type: "post"
                },
                columns: [
                    //选择按钮
                    {
                        data: "ck",
                        title: "选择",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return (
                                '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="' +
                                row.ID +
                                '" onclick="checkClick(this)" />'
                            );
                        },
                        width: "3%",
                        orderable: false
                    },
                    {
                        //data : "RN",
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        width: "5%",
                        orderable: false
                    },
                    {
                        data: "ENFORCE_LAW_TIME",
                        title: "年度",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return data.substring(0, 4);
                        },
                        width: "7%"
                    },
                    {
                        data: "CASE_NAME",
                        title: "案件名称",
                        render: function(data, type, row, meta) {
                            if (data) {
                                var showdata = data;
                                if (data.length > 10) {
                                    showdata = data.substring(0, 10) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        data +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                        },
                        width: "10%"
                    },
                    {
                        data: "PUNISH_CODE",
                        title: "行政处罚决定案号",
                        render: function(data, type, row, meta) {
                            if (data) {
                                var showdata = data;
                                if (data.length > 10) {
                                    showdata = data.substring(0, 10) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        data +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                        },
                        width: "10%"
                    },
                    {
                        data: "ENTERPRISE_ID_NAME",
                        title: "当事主体",
                        render: function(data, type, row, meta) {
                            if (data) {
                                var showdata = data;
                                if (data.length > 10) {
                                    showdata = data.substring(0, 10) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        data +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                        },
                        width: "10%"
                    },
                    {
                        data: "AREA",
                        title: "区域",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            if (
                                data != null &&
                                data != "" &&
                                data != undefined
                            ) {
                                var region = $.fn.Cityname(row.AREA);
                                var regions;
                                if (region) {
                                    regions = region.substring(0, data.length);
                                    var showdata = regions;
                                    if (region.length > 60) {
                                        showdata =
                                            region.substring(0, 60) + "..";
                                        return (
                                            '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                            region +
                                            '">' +
                                            showdata +
                                            "</div>"
                                        );
                                    }
                                    return region;
                                } else {
                                    return "--";
                                }
                                /* return $.fn.Cityname(row.AREA);*/
                            }
                        },
                        width: "10%"
                    },
                    {
                        data: "ENFORCE_LAW_TIME",
                        title: "执法时间",
                        width: "10%"
                    },
                /*    {
                        data: "CREATE_TIME",
                        title: "创建时间",
                        width: "10%"
                    },*/
                    {
                        data: "CREATE_BY",
                        title: "",
                        visible: false
                    },
                  {
                        data: "PENALTY_SOURCE",
                        name: "penaltySource",
                        title: "行政处罚来源",
                        width: "10%",
                        visible: false
                    },{
                        data: "PRODUCT_NAME",
                        name: "productName",
                        title: "违法产品名称",
                        width: "10%",
                        visible: false
                    },{
                        data: "ENTERPRISE_NAME",
                        name: "enteriseName",
                        title: "标称生产企业",
                        width: "10%",
                        visible: false
                    },{
                        data: "MANUFACTURE_DATE",
                        name: "manufactureDate",
                        title: "生产日期或批号",
                        width: "10%",
                        visible: false
                    },{
                        data: "ENFORCE_LAW_PEOPLE",
                        name: "enforceLawPeople",
                        width: "10%",
                        title: "执法人员",
                        visible: false
                    },
                    {
                        data: "ID",
                        title: "操作",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            if (
                                row.CREATE_BY == $scope.linePoliticsUserId &&
                                author != "1"
                            ) {
                                return (
                                    '<a type="button" style="cursor:pointer;" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\'' +
                                    data +
                                    '\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\'' +
                                    data +
                                    "')\">修改</a>"
                                ); //转义传值
                            } else {
                                return (
                                    '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\'' +
                                    data +
                                    '\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" '
                                ); //转义传值
                            }
                        },
                        width: "10%",
                        orderable: false
                    }
                ]
            });
        }
        /**
         * data加入缓存。
         * 注释日期:2018年2月26日10:16:11
         */
        window.GotoUpdateWindow = function(data) {
            window.localStorage.setItem("id", data);
            window.localStorage.setItem("ales_punish_updateFlag", "1");
            $state.go("index.content.ales/punish/produceAdminPunishAdd");
        };
        /**
         * 查看详情页面
         * 注释日期:2018年2月26日10:47:55
         */
        $scope.punishEnterpriseId = "";
        window.Checkreport = function(data) {
            console.log("传入数据值====" + data);
            window.localStorage.setItem("punishLawID", data);
            window.localStorage.removeItem("motaikuangBiaoshi");
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/administrativePenalty/getPunishiById",
                method: "post",
                params: { id: data }
            })
                .success(function(data) {
                    $scope.reCord = data.produceAdminPunish;
                    console.log($scope.reCord);
                    $scope.punishEnterpriseId = $scope.reCord.enterpriseId;
                    console.log(
                        "punishEnterpriseId====>>>>" + $scope.punishEnterpriseId
                    );
                })
                .error(function(data) {
                    console.log(data.data.id);
                    
                });
        };
        /**
         *行政处罚信息打印。
         *注释日期:2018年2月26日10:15:45
         */
        window.produceAdminPunishPrint = function() {
            var rows = $("#grid")
                .DataTable()
                .rows(".selected")
                .data();
            var length = rows.length;
            console.log(length);
            if (length <= 0) {
                $.jBox.tip("请选择一行记录！");
                return false;
            }
            radioID = $("input[type='radio']:checked").val(); //获取选择的对象ID
            window.localStorage.setItem("radioID", radioID);
            $state.go("index.content.ales/punish/produceAdminPunishPrint");
        };
        window.checkClick = function(ck) {
            console.log("选择的ID号=：" + ck);
            $(
                $(ck)
                    .parent()
                    .parent()[0]
            ).toggleClass("selected");
            var e = window.event || arguments.callee.caller.arguments[0];
            if (e && e.stopPropagation) {
                e.stopPropagation();
            } else {
                window.event.cancelBubble = true;
            }
        };

        /**
         * 点击查询按钮刷新行政处罚列表
         * 注释日期:2018年2月26日10:29:59
         */
        $scope.queryAdminPunishList = function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        /**
         *信息删除
         *注释日期:2018年2月26日10:34:58
         */
        $scope.del = function() {
            var rows = $("#grid")
                .DataTable()
                .rows(".selected")
                .data();
            var length = rows.length;
            if (length == 0) {
                $.jBox.tip("请选择一行记录！");
                return false;
            }
            for (var k = 0; k < length; k++) {
                if (rows[k].CREATE_BY == "") {
                    console.log(rows[k].create_by);
                    console.log(rows[k].CREATE_BY);
                    console.log($scope.linePoliticsUserId);
                    $.jBox.tip("无效数据");
                    return false;
                }
                if (rows[k].CREATE_BY != $scope.linePoliticsUserId) {
                    $.jBox.tip("没有权限删除本任务");
                    console.log(rows[k].create_by);
                    console.log(rows[k].CREATE_BY);
                    console.log($scope.linePoliticsUserId);
                    return false;
                }
            }
            var ids = [];
            for (var i = 0; i < length; i++) {
                ids.push(rows[i].ID);
            }

            if (rows != false) {
                $.jBox.confirm(
                    "是否删除已选择行政处罚？",
                    "等待服务器响应",
                    function(v) {
                        if (v == true) {
                            var data = {
                                jsonString: JSON.stringify(ids)
                            };
                            $.ajax({
                                headers: {
                                    token: window.localStorage.getItem("token")
                                },
                                url: "/sofn-ales-web/administrativePenalty/del",
                                type: "post",
                                dataType: "json",
                                data: data,
                                beforeSend: function() {
                                    $("#web_app_model_userOprXz").modal({
                                        backdrop: "static",
                                        keyboard: false
                                    }); //禁止空白处点击与键盘操作关闭
                                    $("#web_app_model_userOprXz").modal("show");
                                },
                                success: function(data) {
                                    $("#web_app_model_userOprXz").modal("hide");
                                    $.jBox.tip("成功！");
                                    $scope.queryAdminPunishList();
                                },
                                fail: function(data) {
                                    $("#web_app_model_userOprXz").modal("hide");
                                    $.jBox.tip("失败");
                                }
                            }).error(function(data) {
                                console.log(data);
                                
                            });
                        }
                        return true;
                    },
                    { buttons: { 确认: true, 取消: false } }
                );
            }
        };

        /**
         * 获取缓存中的数据（定位标识）
         * 注释日期:2018年2月26日10:37:02
         */
        var punish_ID = "";
        var huoquBiaoshiPunishDaily = "";
        huoquBiaoshiPunishDaily = window.localStorage.getItem(
            "motaikuangBiaoshi"
        );
        if (huoquBiaoshiPunishDaily != null) {
            $("#myModal").modal("show");
            punish_ID = window.localStorage.getItem("punishLawID");
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/administrativePenalty/getPunishiById",
                method: "post",
                params: { id: punish_ID }
            })
                .success(function(data) {
                    console.log(data);
                    $scope.reCord = data.produceAdminPunish;
                    console.log($scope.reCord);
                    $scope.punishEnterpriseId = $scope.reCord.enterpriseId;
                })
                .error(function(data) {
                    console.log(data);
                    
                });
        }
        /*
         * 注释日期:2018年2月26日10:37:58
         */
        window.selectEnterpriseInfo = function() {
            $("#myModal").one("hidden.bs.modal", function() {
                window.localStorage.setItem(
                    "dELPUNISH_EnterpriseId",
                    $scope.punishEnterpriseId
                );
                $state.go("index.content.ales/punish/etlPunishEnterpriseview");
            });
            $("#myModal").modal("hide");
        };

        /**
         * 清除主体信息中缓存的标识
         * 注释日期:2018年2月26日10:38:43
         */
        window.qinchuhuanchun = function() {
            window.localStorage.removeItem("motaikuangBiaoshi");
            window.localStorage.removeItem("punishLawID");
            $("#myModal").modal("hide");
        };
        /**
         * 文件下载
         * 注释日期:2018年2月26日10:39:30
         */
        window.downloadFile = function() {
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-ales-web/alesFile/downloadFile");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileUrl");
            input1.attr("value", $scope.reCord.punishFiles);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "fileName");
            input2.attr("value", $scope.reCord.punishFilesName);
            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
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
                    var table = $("#grid").DataTable();
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
                $(".condition-checked-all").prop(
                    "checked",
                    !!!unchecked.length
                );
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
            url: "/sofn-ales-web/administrativePenalty/setAdministrativePenaltyGraph",
            grid: [3, 3, 3], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
