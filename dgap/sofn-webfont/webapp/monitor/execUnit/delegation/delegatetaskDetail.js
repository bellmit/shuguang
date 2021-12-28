var myapp = angular.module("myapp", []);
myapp.controller("delegatetaskDetail", function ($scope, $http, $state) {

    $scope.iswrite = "";
    var b = window.localStorage.getItem("AdsMenuObj");
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="受托检测"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");//监测任务
    var sampleOrganId = window.localStorage.getItem("sampleOrganId");//抽样机构代码
    var sampleOrgan = window.localStorage.getItem("sampleOrgan");//监测机构名称
    var token = window.localStorage.getItem("token");//监测机构名称
    $scope.detail = "";
    $scope.organTaskId = "";
    // $scope.numbers = "";
    // var project = 0;
    // var object = 0;
    $http({
        url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method: "post",
        params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
    }).success(function (data) {
        $scope.detail = data.adsMonitorTask[0];
        $scope.taskName = data.adsMonitorTask[0].taskName;
        $scope.organTaskId = data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
        $scope.monitorClass = data.adsMonitorTask[0].monitorClass//监测类型;

        var priority = data.adsMonitorTask[0].priority;
        $scope.priority = "";
        if(priority == 1){
            $scope.priority = "特急";
        }else if (priority == 2){
            $scope.priority = "紧急";
        }else {
            $scope.priority = "一般";
        }

        $("#grid").dataTable({
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsOrganTask/pages",
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
                    params.superviseCheckTaskId = adsMonitorTaskId;//任务id
                    params.detectionUnitId = $scope.organTaskId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "productType",
                    title: "产品种类"
                },
                {
                    data: "productName",
                    title: "产品名称"
                },
           /*     {
                    data: "areaId",
                    title: "受检区域",
                    render: function (data, type, row) {
                        if (data == "" || data == undefined) {
                            return "未填写"
                        } else {
                            return $.fn.Cityname(data);
                        }
                    }
                },*/
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        if ($scope.detailCheckInfoId == "")
                            $scope.detailCheckInfoId = data;
                        // 数据操作选择项
                        var look_a = '';
                        //状态为已上报或者权限为只读时不能配置上限值
                        if(row.status=="1"||$scope.disabled){
                            look_a =  '<a class="pointer mr15" style="color: grey;cursor: default;">配置限量值</a>';
                        }else{
                            look_a = '<a class="pointer mr15" onclick="configValue(\'' + row.id + '\',\'' + row.productName + '\') ">配置限量值</a>';
                        }
                        var look_b = "";//'<a class="pointer mr15" onclick="sample(\'' + data+ '\')">查看产品信息</a>';
                        return look_a + look_b;
                    }
                }
            ],
        });
    }).error(function () {
    });
    /*配置上限值*/
    $scope.isConfigValue = true;
    window.configValue = function (id, productName) {
        $http({
            url: "/sofn-ales-web/alesWtTaskMonitor/getObjByObjId",
            method: "post",
            data: {"id": id},
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'},
                token: window.localStorage.getItem("token")
            }
        }).success(function (data) {
            $scope.crits = data.criterions;
            $scope.productName = productName;

            $scope.checkIsConfigValue(productName);

            $("#checkModal").modal("show");
        })

    };

    //查看产品信息
    $scope.detailCheckInfoId = "";
    window.detailCheckInfo =function () {
        window.sample($scope.detailCheckInfoId);
    }

    //根据抽样名称来检测是否已存在上报检测结果
    $scope.checkIsConfigValue = function (name) {
        $scope.isConfigValue = true;
        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/getPageInfoWithOrgTaskIdAndInsert?start=0&length=1000",
            method: "post",
            dataType: "json",
            data: {"organTaskId": $scope.organTaskId},
            headers: {
                'token': token
            },
            success: function (data) {
                var list = data.data.list;
                for(var i=0;i<list.length;i++) {
                    var obj = list[i];
                    console.log(obj['SAMPLE_NAME'] ==  name)
                    console.log(obj['CHECK_REPORT'] > 0)
                    if(obj['SAMPLE_NAME'] ==  name && obj['CHECK_REPORT'] > 0) {
                        $scope.isConfigValue = false;
                        break;
                    }
                }
            }
        })
    }

    /*点击input框*/
    $scope.changeTableValue = function ($event) {
        angular.element($event.target).trigger("focus").trigger("select");
    };
    /*保存数据*/
    $scope.saveTableValue = function () {
        var num = 0;
        var spperLimitNum = 0;
        $("#checkTable input[unit='unit']").each(
            function () {
                var unitValue = $(this).val();
                if(unitValue==null || unitValue ==""){
                    num++;
                    return false;
                }
            }
        );

        $("#checkTable input[spperLimit='spperLimit']").each(
            function () {
                var spperLimitValue = $(this).val();
                if(spperLimitValue==null || spperLimitValue ==""){
                    spperLimitNum++;
                    return false;
                }
            }
        );
        if(num>0){
            alert("单位为必填项");
            return false;
        }
        if(spperLimitNum>0){
            alert("限量值为必填项");
            return false;
        }
        $scope._sjUnit = $scope.crits;
        for (var i = 0; i < $scope.crits.length; i++) {
            $scope.crits[i].spperLimit = angular.element("#checkTable input[spperLimit='spperLimit']").eq(i).val();
            $scope.crits[i].unit = angular.element("#checkTable input[unit='unit']").eq(i).val();
        }


        //检验抽样是否已上报
        if(!$scope.isConfigValue){
            jBox.tip("该产品已上报，无法修改！", "info");
            return false;
        }

        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            type: 'POST',
            data: {"criterions": JSON.stringify($scope._sjUnit)},
            url: '/sofn-ales-web/alesWtTaskMonitor/updateSpperLimit',
            success: function (data) {
                alert("保存成功");
                $("#checkModal").modal("hide");
            },
            error: function (e) {
                alert("保存失败");
            }
        })
    }

    //下载键
    $scope.download = function () {
        var arr = new Array();
        arr.push(adsMonitorTaskId);
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorTask/exportAdsTaskExcel",
                    method: "post",
                    dataType: "json",
                    params: {
                        "ids": arr.toString(),
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
                        a.setAttribute('download', '导出任务.xls');
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

    /**
     * 打开检测对象模态框
     */
    $scope.showCheckObjectModal = function () {
        $("#dModal").modal("show");
        setTimeout(function () {
            $scope.loadCheckObjectData($scope.checkModel);
        }, 300);
    }

    /**
     * 显示监测对象、检测项目模态框
     * @param title
     * @param content
     */
    $scope.showXModal = function (title, content) {
        $("#x_title").html(title);
        $("#x_content").html(content);
        $("#xModal").modal("show");
    }

    $(function () {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });

    window.sample = function (data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("taskName",$scope.taskName);//抽样截至时间
        $state.go("index.content.monitor/execUnit/delegation/delegationTestList")
    }

    //打印设置
    $("#printArea").click(function () {
        $(".Sup_contain_section [type=button]").hide();
        $(".Sup_contain_section").printArea();
        $(".Sup_contain_section [type=button]").show();
    });

    /**
     * 加载检测对象列表
     * @param model_id
     */
    $scope.loadCheckObjectData = function (model_id) {
        $http({
            url: "/sofn-ads-web/adsModelCheckObject/getPageInfoByModel",
            method: "post",
            dataType: "json",
            params: {"model_id": model_id, "start": 1, "length": 99},
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.items = data.items;
            $scope.adsCheckObject = data.pageInfo.list;
            setTimeout(function () {
                $.each(data.pageInfo.list, function (idx) {
                    var obj = $(this)[0];
                    var itmeList = obj.itemList;
                    var str = "";
                    $.each(data.items, function () {
                        var item = $(this)[0];
                        var val = item.standardValue;
                        var flag = 0;
                        $.each(itmeList, function () {
                            var tmp = $(this)[0];
                            if (tmp.id == item.id) {
                                flag = 1;
                                return false;
                            }
                        });
                        if (flag == 0) {
                            val = "-";
                        }
                        str += "<td>" + val + "</td>";
                    });
                    $("#" + obj.id).append(str);
                })
            }, 300)
        });
    }
    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/delegation/delegatetaskDetail")
    }

    $scope.test = function(data) {
        window.localStorage.setItem("adsMonitorTaskId",adsMonitorTaskId);//监测任务id
        $state.go("index.content.monitor/execUnit/delegation/delegationTestList")
    }

    $scope.routineReportUpload = function(data) {
        $state.go("index.content.monitor/execUnit/delegation/delegationReportUpload")
    }

    //字符串逆转
    function strturn(str) {
        if (str != "") {
            var str1 = "";
            for (var i = str.length - 1; i >= 0; i--) {
                str1 += str.charAt(i);
            }
            return (str1);
        }
    }
    //取文件后缀名
    function GetFileExt(filepath) {
        if (filepath != "") {
            var pos = "." + filepath.replace(/.+\./, "");
            return pos;
        }
    }
    function GetFileNameNoExt(filepath) {
        var pos = strturn(GetFileExt(filepath));
        var file = strturn(filepath);
        var pos1 =strturn( file.replace(pos, ""));
        var pos2 = GetFileName(pos1);
        return pos2;
    }
    //取文件全名名称
    function GetFileName(filepath) {
        if (filepath != "") {
            var names = filepath.split("\\");
            return names[names.length - 1];
        }
    }
    //附件下载
    $scope.downloadFile=function(){
        var file = GetFileNameNoExt($scope.detail.attachment);
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsFile/fileDownload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.detail.attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', file);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

});


