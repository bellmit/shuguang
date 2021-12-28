var myapp = angular.module("myapp", []);
myapp.controller("alesEntrustDetectionObject", function ($scope, $http, $state, $q) {

    $scope.taskId = window.localStorage.getItem("ales_wtTaskInfo_ID_DetecTionObject");
    $scope.detection= window.localStorage.getItem("alesEntrustDetectionAdd_detection");/*判定标准号*/
  //  $scope.fileCode=window.localStorage.getItem("fileCode");
    var vm = this;
    $('#entTempModal').bootstrapValidator(); //必须有(验证开启-生产经营临时主体注册)；
    $scope.grid = $('#grid');
    $scope.currentOrg = {};
    $scope.alesWtTaskMonitor = {};
    $scope.HyFl = [1];
    $scope.criterion = new Array();/*检测项目*/
    $scope.authority = window.localStorage.getItem("alesEntrustDetection_subset_authority");//读写标识
    if($scope.authority == "1"){
        $("#addId").attr("disabled", true);
        $("#updateId").attr("disabled", true);
        $("#deleteId").attr("disabled", true);
    }
    /*检测项目模态框的查询更新*/
    $("#toCheckId").click(function () {
        $("#productList").dataTable().api().ajax.reload();
    });
    /*绑定回车事件*/
    $('#toCheckId').keydown(function(e){
        if(e.keyCode==13){
            $("#productList").dataTable().api().ajax.reload();
        }
    });
    var HyFlFunc = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        method: "post",
        data: "2"
    }).error(function (msg) {
    });

    var currentOrgRequest = $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method: "post",
        dataType: "json"
    }).error(function (msg) {

    });

    $q.all([HyFlFunc, currentOrgRequest]).then(function (data) {
        $scope.HyFl = JSON.parse(data[0].data.data);
        $scope.currentOrg = data[1].data.data;
        $scope.underArea = $scope.currentOrg.regionId.substr(0,2*($scope.currentOrg.orgLevel-1));
        //初始化区域下拉框初始选项
        $scope.reloadArea();
    }, function (data) {
        $.jBox.tip("初始数据获取异常！", "error");
        console.log("初始数据获取异常！", data);
    }).then(function (data) {

        $("#grid").dataTable({
       /*     fnDrawCallback: function () {
                $("#grid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var checked = $(this).parent().find("input[type='checkbox']")[0];
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                        }
                    });
                })
            },*/
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/pages",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;
                    json.pageSize = json.data.pageSize;
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;
                    json.length = json.data.pageSize;
                    return json.data.list;
                },error:function (msg) {
                    console.log(msg);
                },
                data: function (params) {
                    params.superviseCheckTaskId = $scope.taskId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '选择',
                    render: function (data, type, row) {
                        return '<input type="checkbox" name="ck" "/>';//onclick="checkThisBox(this)
                    },
                    width: "5%",
                    orderable: false
                },
                {
                    data: 'id',
                    title: 'ID',
                    visible: false
                }, {
                    data: "productName",
                    title: "名称"
                },
                {
                    data: "productType",
                    title: "类型"
                },
                {
                    data: "id",
                    title: "抽样单",
                    render: function (data, type, row) {
                        return '<a style="cursor: pointer" onclick="wtTaskSample(\'' + [row.id, row.superviseCheckTaskId,row.productName,row.industry] + '\')">抽样单</a>';
                    }
                }
            ]
        });
    });

    window.wtTaskSample = function (data) {
        var dataArray = data.split(",");
        var moniterId = dataArray[0];
        var taskId = dataArray[1];
        var checkProductName = dataArray[2];
        var checkIndustry = dataArray[3];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/llesEntrustDetection/getTaskById",
            method: "post",
            data: {id: taskId}
        }).success(function (obj) {
            window.localStorage.setItem("wtTaskState", obj.data.state);
            window.localStorage.setItem("wtTaskId", data);
            window.localStorage.setItem("moniterId", moniterId);
           // window.localStorage.setItem("fileCode", $scope.fileCode);
            window.localStorage.setItem("taskManage_alesWtTaskSample_productName",checkProductName);//农产品名称
            window.localStorage.setItem("taskManage_alesWtTaskSample_checkIndustry",checkIndustry);
            $state.go("index.content.ales/taskManage/alesWtTaskSample");
        }).error(function (msg) {

        })
    };

    $scope.reload = function(){
        $("input[name='entIdCode']").val("");
        $("input[name='sjUnitName']").val("");
        $scope.reloadArea();
    }
    //重置区域
    $scope.reloadArea=function(){
        if($scope.currentOrg.orgLevel!="1") {
            $.fn.mycity("sjUnitShen", "sjUnitShi", "sjUnitXian", $scope.underArea);
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp", $scope.underArea);
            $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
            if ($("#sjUnitShen").val() != null && $("#sjUnitShen").val() != "" && $("#sjUnitShen").val() != undefined) {
                $("#sjUnitShen").attr("disabled", true);
            }
            if ($("#sjUnitShenTemp").val() != null && $("#sjUnitShenTemp").val() != "" && $("#sjUnitShenTemp").val() != undefined) {
                $("#sjUnitShenTemp").attr("disabled", true);
            }
            if($("#subjTempSheng").val() != null && $("#subjTempSheng").val() != "" && $("#subjTempSheng").val() != undefined){
                $("#subjTempSheng").attr("disabled", true);
            }
            if ($("#sjUnitShi").val() != null && $("#sjUnitShi").val() != "" && $("#sjUnitShi").val() != undefined) {
                $("#sjUnitShi").attr("disabled", true);
            }
            if ($("#sjUnitShiTemp").val() != null && $("#sjUnitShiTemp").val() != "" && $("#sjUnitShiTemp").val() != undefined) {
                $("#sjUnitShiTemp").attr("disabled", true);
            }
            if ($("#subjTempShi").val() != null && $("#subjTempShi").val() != "" && $("#subjTempShi").val() != undefined) {
                $("#subjTempShi").attr("disabled", true);
            }
            if ($("#sjUnitXian").val() != null && $("#sjUnitXian").val() != "" && $("#sjUnitXian").val() != undefined) {
                $("#sjUnitXian").attr("disabled", true);
            }
            if ($("#sjUnitXianTemp").val() != null && $("#sjUnitXianTemp").val() != "" && $("#sjUnitXianTemp").val() != undefined) {
                $("#sjUnitXianTemp").attr("disabled", true);
            }
            if ($("#subjTempXian").val() != null && $("#subjTempXian").val() != "" && $("#subjTempXian").val() != undefined) {
                $("#subjTempXian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp");
            $.fn.mycity("sjUnitShenTemp", "sjUnitShiTemp", "sjUnitXianTemp", $scope.underArea);
            $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
        }
    }
    //新增-弹出框
    $scope.add = function () {
        $scope.alesWtTaskMonitor = {};
        $scope.criterion = new Array();
        $("#MOB_INFO_MODEL").modal('show');
    };
    $scope.update = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $("#MOB_INFO_MODEL").modal('show');
        var objId = rows[0].id;
        $http({//请求数据
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/alesWtTaskMonitor/getObjByObjId",
            method: "post",
            data: {id: objId}
        }).success(function (obj) {
            $scope.alesWtTaskMonitor = {};
            $scope.alesWtTaskMonitor = obj.data;
            var ents = obj.enterprises;
            $scope.sjUnit = null;//受检单位
            $scope.alesWtTaskMonitor.szUnits=ents.enterpriseName;

            //检测项目
            var crits = obj.criterions;
            $scope.criterion = null;
            $scope.criterion = new Array();
            if (crits.length != 0) {
                $scope.alesWtTaskMonitor.crites = undefined;
                for (var i = 0; i < crits.length; i++) {
                    var map = {
                        criterionId: crits[i].criterionId,
                        criterionName: crits[i].criterionName
                    };
                    $scope.criterion.push(map);
                    if ($scope.alesWtTaskMonitor.crites != undefined) {
                        $scope.alesWtTaskMonitor.crites += "," + crits[i].criterionName;
                    } else {
                        $scope.alesWtTaskMonitor.crites = crits[i].criterionName;
                    }
                }
            }
        }).error(function (msg) {
           
        });
    };
    $scope.delete = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length < 1) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        $.jBox.confirm("是否删除已选监测对象？", "等待服务器响应", function (v) {
            if (v == true) {
                var ids = [];
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }
                $http({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url: "/sofn-ales-web/alesWtTaskMonitor/deleteAlesWtTaskMonitor",
                    method: "post",
                    data: {id: JSON.stringify(ids)}
                }).success(function (obj) {
                    $.jBox.tip("成功！");
                    $("#grid").dataTable().api().ajax.reload();
                }).error(function (msg) {
                    
                });

            }
            return true;
        }, {buttons: {'确认': true, '取消': false}});
    };
    $scope.save = function () {
        console.log("............");

        delete $scope.alesWtTaskMonitor.createBy;
        delete $scope.alesWtTaskMonitor.createTime;
        delete $scope.alesWtTaskMonitor.updateBy;
        delete $scope.alesWtTaskMonitor.updateTime;
        delete $scope.alesWtTaskMonitor.enable;
        delete $scope.alesWtTaskMonitor.delFlag;
        delete $scope.alesWtTaskMonitor.taskBeginTime;
        delete $scope.alesWtTaskMonitor.taskEndTime;
        $scope.alesWtTaskMonitor.superviseCheckTaskId = $scope.taskId;//所属任务
        $scope.alesWtTaskMonitor.enterprise = JSON.stringify($scope.sjUnit);
        $scope.alesWtTaskMonitor.criterions = JSON.stringify($scope.criterion);
        var URL;
        if ($scope.alesWtTaskMonitor.id == null || $scope.alesWtTaskMonitor.id == undefined) {
            URL = "/sofn-ales-web/alesWtTaskMonitor/addAlesWtTaskMonitor";
        } else {
            URL = "/sofn-ales-web/alesWtTaskMonitor/updateAlesWtTaskMonitor";
        }
        $.ajax({
            url: URL,
            headers:{
                token:window.localStorage.getItem("token")
            },
            type: "post",
            dataType: "json",
            data: $scope.alesWtTaskMonitor,
            success: function (data) {
                if(data.httpCode == "400"){
                    $.jBox.tip("成功！");
                }else{
                    $.jBox.tip("成功！");
                    $("#MOB_INFO_MODEL").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                }

            },
            fail: function (data) {
                $.jBox.tip("失败");
                console.log("保存失败！", data);
            }
        }).error(function (msg) {

        });
    };

    $scope.findProductInfo = function () {
        if ($scope.alesWtTaskMonitor.industry == null || $scope.alesWtTaskMonitor.industry == "") {
            jBox.tip("请先选择所属行业", 'info');
            return;
        }
        $("#product_info").modal('show');
        var table = $('#productList');
        if ($.fn.dataTable.isDataTable(table)) {
            table.DataTable().clear();
            table.DataTable().destroy();
        }
        $("#productList").dataTable({
            fnDrawCallback: function () {
                $("#productList tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        var name = $(this).parent().find("[name='ProName']").text();
                        var type = $(this).parent().find("[name='ProType']").text();
                        $scope.$apply(function () {
                            $scope.alesWtTaskMonitor.productName = name;
                            $scope.alesWtTaskMonitor.productType = type;
                        });
                        $("#product_info").modal('hide');
                    });
                })
            },
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/productList",
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
                    params.industry = $scope.alesWtTaskMonitor.industry;
                    params.keyWord = $("input[name='keyWord']").val();
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                data: "name",
                title: "名称",
                render: function (data, type, row, meta) {
                    return '<span name = "ProName">' + data + '</span>';
                }
            }, {
                data: "parentName",
                title: "类型",
                render: function (data, type, row, meta) {
                    return '<span name = "ProType">' + data + '</span>';
                }
            }]
        }).on('shown.bs.tab', function (e) {
            $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
        });

    };
    /*
     * 受检项目-检测项
     * 1.检测标准号默认页面带参数传递
     * 2.默认数据显示首位标准号下的检测项数据
     */
    $("#criteList").dataTable({

        fnDrawCallback: function () {
            $("#criteList tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    $(this).parent().removeClass("selected");
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        var u_d = $(this).parent().find("[name='ck']").val();
                        for (var i = 0; i < $scope.criterion.length; i++) {
                            if ($scope.criterion[i] != undefined && u_d == $scope.criterion[i].criterionId) {
                                delete $scope.criterion[i];
                            }
                        }
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                        var map = {
                            criterionId: $(this).parent().find("[name='ck']").val(),
                            criterionName: $(this).parent().find("[name='name']").html()

                        };
                        $scope.criterion.push(map);
                    }
                });
            })
        },
        ajax: {
            headers:{
                token:window.localStorage.getItem("token")
            },
            ///受检项目地址   //
            url: "/sofn-ales-web/alesSample/getTestitemlist",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;
                json.recordsFiltered = json.data.total;
                json.start = json.data.pageNum;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },error:function (msg) {
                console.log(msg);
                
            },
            data: function (params) {
                params.standardCode = $scope.selectedStandardCode;
                if($scope.alesWtTaskMonitor.industry == "01"){
                    params.industries ="种植业"
                }else if($scope.alesWtTaskMonitor.industry == "02"){
                    params.industries ="畜牧业"
                }else if($scope.alesWtTaskMonitor.industry == "03"){
                    params.industries ="渔业"
                }
                params.itemName = $scope.queryTestObjectName;

                return params;
            },
            type: "post"
        },
        columns: [
            {
                data: "id",
                title: '选择',
                render: function (data, type, row) {

                    for (var i = 0; i < $scope.criterion.length; i++) {
                        if ($scope.criterion[i] != undefined && data == $scope.criterion[i].criterionId) {
                            return '<input type="checkbox" checked = "true" name="ck" onclick="checkThisBox(this)" value="' + row.id + '"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" onclick="checkThisBox(this)" value="' + row.id + '"/>';//onclick="checkThisBox(this)" value="' + row.id + '"
                }
            },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                data: "standardCode",
                title: "标准号",
            },
            {
                data: "itemName",
                title: "检测项",
                render: function (data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }

            },
            {
                data:"scope",
                title:"行业",
                render: function (data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            }
        ]
    });//检测项
    $scope.criteCheck = function () {

        $("#crite_info").modal('show');
        //$scope.criterion = new Array()
        if($scope.detection != null){
        $scope.selectedStandardCode = detectionArray[0];
        }
        var table = $('#criteList');
        table.find('tbody .selected').removeClass('selected').find(':checkbox').prop('checked', false)//移除复选择样式
        table.dataTable().api().ajax.reload();
    };

    /*设置页面已选定的判定标准号*/
    $scope.itemCode = [];
    $scope.itemstandardCode = new Array();
    var detectionArray;
    if($scope.detection != null){
     detectionArray = $scope.detection.split(",");
    for(var i = 0; i< detectionArray.length;i++){
        $scope.itemCode.push(detectionArray[i]);
    }
    }else{
       $http({
           headers:{
               token:window.localStorage.getItem("token")
           },
           url: "/sofn-ales-web/alesTools/getTestStandard",
           method:"post"
       }).success(function(data) {//standardCode
           $scope.itemstandardCode = data.data;
         for( var i = 0; i< $scope.itemstandardCode.length;i++){
             $scope.itemCode.push(data.data[i].standardCode);
         }


       }).error(function (msg) {
          
       });
    }
    /*切换到生产经营主体*/
    $scope.switchEnt = function() {
        $("#entTab").addClass("gui-TabCheck");
        $("#entTempTab").removeClass("gui-TabCheck");
        $("#entDiv").show();
        $("#entTempDiv").hide();
    }
    /*切换到临时生产经营主体*/
    $scope.switchEntTemp = function() {
        $("#entTempTab").addClass("gui-TabCheck");
        $("#entTab").removeClass("gui-TabCheck");
        $("#entTempDiv").show();
        $("#entDiv").hide();
    }


    $scope.sJUnitStandards = function () {
        $("#sJUnit_Model").modal('show');
        var table = $('#sJUnitTaskGrid');
        if ($.fn.dataTable.isDataTable(table)) {
            table.DataTable().clear();
            table.DataTable().destroy();
        }
        $("#sJUnitTaskGrid").dataTable({
            fnDrawCallback: function () {
                /*设置选中状态*/
                $("#sJUnitTaskGrid >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ck']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#sJUnitTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            },
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/getSubjEntList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;
                    json.length = json.page.length;
                    return json.list;
                },error:function (msg) {
                   
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.enterpriseName = $("input[name='sjUnitName']").val();
                  /*  params.entityIdcode = $("input[name='entIdCode']").val(); 编码查询屏蔽 2018年4月26日18:26:40*/
                    params.approveStatus = "1,3,4,5";//可用主体
                    params.entityIndustry = $scope.allIndustryId;
                    var province = $("#sjUnitShen").val();
                    var city = $("#sjUnitShi").val();
                    var county = $("#sjUnitXian").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (city != null && city != undefined && city != "") {
                        areaId = city;
                    } else if (province != null && province != undefined && province != "") {
                        areaId = province;
                    }
                    params.area = areaId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '*',
                    render: function (data, type, row) {
                        return '<input type="radio" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
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
                    }
                },
                {
                    data: "enterpriseName",
                    title: "单位名称",
                    render: function (data, type, row) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                        /*return '<span name = "name">' + data + '</span>';*/
                    }
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业"
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                       var region = $.fn.Cityname(data);
                        var regions;
                        if (region) {
                            regions = region.substring(0, data.length);
                            var showdata = regions;
                            if (region.length > 14) {
                                showdata = region.substring(0, 14) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                       /* return $.fn.Cityname(data);*/
                    }
                }
            ]

        });
        $("#sJUnitTaskGridTemp").dataTable({
            fnDrawCallback: function () {
                //设置选中状态
                $("#sJUnitTaskGridTemp >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ck']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#sJUnitTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            },
            ajax: {
                url: "/sofn-ales-web/alesWtTaskMonitor/getSubjTempEntList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;
                    json.length = json.page.length;
                    return json.list;
                },error:function (msg) {
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityName = $("input[name='sjUnitNameTemp']").val();
                    var province = $("#sjUnitShenTemp").val();
                    var city = $("#sjUnitShiTemp").val();
                    var county = $("#sjUnitXianTemp").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (city != null && city != undefined && city != "") {
                        areaId = city;
                    } else if (province != null && province != undefined && province != "") {
                        areaId = province;
                    }
                    params.areaId = areaId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "id",
                    title: '*',
                    render: function (data, type, row) {
                        return '<input type="radio" name="ck" onclick="checkThisBox(this)" value="' + data + '"/>';
                    }
                },

                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "entityName",
                    title: "单位名称",
                    render: function (data, type, row) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    }
                },
                {
                    data: "entityIndustry",
                    title: "所属行业"
                },
                {
                    data: "areaId",
                    title: "所属区域",
                    render: function (data, type, row) {
                        var region = $.fn.Cityname(data);
                        var regions;
                        if (region) {
                            regions = region.substring(0, data.length);
                            var showdata = regions;
                            if (region.length > 14) {
                                showdata = region.substring(0, 14) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    }
                }
            ]
        });
    };

    /*查询受检单位-生产经营主体*/
    $scope.querySjUnit = function(){
        $("#sJUnitTaskGrid").dataTable().api().ajax.reload();
    }
    /*查询受检单位-临时生产主体*/
    $scope.querySjUnitTemp = function(){
        $("#sJUnitTaskGridTemp").dataTable().api().ajax.reload();
    }
    /*绑定回车事件*/
    $('#sJUnitSelectTocheckTemp').keydown(function(e){
        if(e.keyCode==13){
            $("#sJUnitTaskGridTemp").dataTable().api().ajax.reload();
        }
    });
    //赋值检测项目
    $scope.selectCriteButt = function () {
        $scope.alesWtTaskMonitor.crites = undefined;
        for (var i = 0; i < $scope.criterion.length; i++) {
            if ($scope.criterion[i] != undefined) {
                if ($scope.alesWtTaskMonitor.crites != undefined) {
                    $scope.alesWtTaskMonitor.crites += "," + $scope.criterion[i].criterionName;
                } else {
                    $scope.alesWtTaskMonitor.crites = $scope.criterion[i].criterionName;
                }
            }
        }
        $("#crite_info").modal('hide');

    };
    /*
     * 提交生产经营主体
     * @returns {boolean}
     */
    $scope.selectSzDwButt = function () {
        var rows = $("#sJUnitTaskGrid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.sjUnit = new Map();/*受检单位*/
        var map = {
            enterpriseId: rows[0].id,
            enterpriseName: rows[0].enterpriseName
        };
        $scope.sjUnit =map;
        $scope.alesWtTaskMonitor.szUnits =$scope.sjUnit.enterpriseName;
        $("#sJUnit_Model").modal('hide');
    };//受检单位确定按钮

    /**
     * 提交临时生产主体
     * @returns {boolean}
     */
    $scope.selectSzDwButtTemp = function () {
        var rows = $("#sJUnitTaskGridTemp").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.sjUnit = new Map();//受检单位
        var map = {
            enterpriseId: rows[0].id,
            enterpriseName: rows[0].entityName
        };
        $scope.sjUnit =map;
        $scope.alesWtTaskMonitor.szUnits =$scope.sjUnit.enterpriseName;
        $("#sJUnit_Model").modal('hide');
    };//受检单位确定按钮

    //针对复选框无法选中bug
    window.checkThisBox = function(o){
        if(o.checked){
            o.checked = false;
            $(o).removeClass("selected");
        }else {
            o.checked = true;
            $(o).addClass("selected");
        }
    }
    $scope.subjEntTemp={};
    //获取行业数据字典
    $scope.industryType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.industryType=JSON.parse(data.data);
        $scope.subjEntTemp.entityIndustryId=$scope.industryType[0].id;
    }).error(function (msg) {
       
    });
    //获取主体类别数据字典
    $scope.subjType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"31",
        method:"post"
    }).success(function(data) {
        $scope.subjType=JSON.parse(data.data);
        $scope.subjEntTemp.entityTypeId=$scope.subjType[0].id;
    }).error(function (msg) {
       
    });
    //获取组织形式数据字典
    $scope.orgMode=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/sysDict/getSysDictByCode",
        data:"6",
        method:"post"
    }).success(function(data) {
        $scope.orgMode=JSON.parse(data.data);
        $scope.subjEntTemp.entityScaleId=$scope.orgMode[0].id;
    }).error(function (msg) {
        
    });

    //生产经营主体临时备案
    $scope.addEntTemp = function() {
        $("#entTempModal").modal("show");
        $("input[name='entityScaleId']:first").attr("checked", "checked");
        $("input[name='entityTypeId']:first").attr("checked", "checked");
        $("input[name='entityIndustryId']:first").attr("checked", "checked");
    };

    //新增生产经营主体临时备案
    $scope.addSubjEntTemp=function(){
        var industryCheck = [];
        var industryNameCheck = [];
        $("input[name='entityIndustryId']:checked").each(function(){
            industryCheck.push($(this).val());
            industryNameCheck.push($(this).next().text());
        });
        if(industryCheck.length==0){
            $scope.industryChecked = true;
            return false;
        }
        $scope.industryChecked = false;
        $scope.subjEntTemp.entityIndustryId=industryCheck.toString();
        $scope.subjEntTemp.entityIndustry=industryNameCheck.toString();
        //必须有
        var province = $("#subjTempSheng").val();
        var city = $("#subjTempShi").val();
        var county = $("#subjTempXian").val();
        var areaId = "";
        if(county!=null&&county!=undefined&&county!=""){
            areaId = county;
        }else if(city!=null&&city!=undefined&&city!=""){
            areaId = city+"00";
        }else if(province!=null&&province!=undefined&&province!=""){
            areaId = province+"0000";
        }
        $scope.subjEntTemp.entityScale=$("input[name='entityScaleId']:checked").next().text();
        $scope.subjEntTemp.entityType=$("input[name='entityTypeId']:checked").next().text();
        $scope.subjEntTemp.areaId=areaId;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEnt/addSubjEntTemp",
            data:$scope.subjEntTemp,
            method:"post"
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("保存成功", 'info');
                $("#entTempModal").modal("hide");
                $("#sJUnitTaskGridTemp").dataTable().api().ajax.reload();
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(msg){
            console.log(msg);
            $("button").attr("disabled",false);
            jBox.tip("保存失败", 'info');
        })
    }
    $scope.queryList = function(){
        $("#criteList").dataTable().api().ajax.reload();
    }
    //绑定回车事件
    $('#queryId').keydown(function(e){
        if(e.keyCode==13){
            $("#criteList").dataTable().api().ajax.reload();
        }
    });
    //-----------------------------------------------------------------
    //判断组织机构代码照是否显示
    $scope.selectBusiness = function() {//selectBusiness
        if ($scope.subjEntTemp.credType == "普通营业执照(有独立组织机构代码证)") {
            $("#selectOrganizationCertificateimg").show();
        } else {
            $("#selectOrganizationCertificateimg").hide();
            vm.organizationCertificateimg = [{
                vname: '组织机构照',
            }]
        }
    };
    //临时主体上传照片默认显示
    vm.businessLicenceimg = [{
        vname: '企业证件照',
    }]
    vm.organizationCertificateimg = [{
        vname: '组织机构照',
    }]
    vm.positiveIdcardeimg = [{
        vname: '身份证正面',
        picture: 'images/dict/id_f.png'
    }]
    vm.negativeIdcardimg = [{
        vname: '身份证反面',
        picture: 'images/dict/id_z.png'
    }]
    vm.handIdcardimg = [{
        vname: '手持身份证',
        picture: 'images/dict/id_s.png'
    }]

    vm.uploadHeadOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传图片', 'warning');
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };

})
;