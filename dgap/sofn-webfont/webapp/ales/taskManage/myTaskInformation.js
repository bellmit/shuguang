var myapp = angular.module("myapp", []);
myapp.controller("myTaskInformation", function ($scope, $http, $state) {
    $scope.authority = window.localStorage.getItem("myCheckTask_subset_authority");//读写标识
    console.log("权限标识为==>>>"+$scope.authority);
    if($scope.authority == "1"){
        $("#syncToAdsId").attr("disabled", true);
        $("#downSampleId").attr("disabled", true);
    }
    /**
     * *
     * *承担任务涵盖监测信息，一个承担任务（监督抽查任务）包含多个监测对象信息
     * *
     * */

    /**F
     *公共值
     * */
    $scope.taskId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr");//任务id
     $scope.product = "";
    $scope.chooseTaskId = "";//最终值任务id
    function strturn(str) {
        if (str != "") {
            var str1 = "";
            for (var i = str.length - 1; i >= 0; i--) {
                str1 += str.charAt(i);
            }
            return (str1);
        }
    }
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
    function GetFileName(filepath) {
        if (filepath != "") {
            var names = filepath.split("\\");
            return names[names.length - 1];

        }
    }
    /**
     * 历史任务查看无需展示提交按钮
     * */
    if ("history" == $scope.oprIdx) {
        $("button[name='sumSampleBut']").hide();
    }
    /**
     * 任务基本信息及检测对象
     * */
   // $scope.initInfo = function () {
        var id ="";
        if($scope.taskId != undefined){
            id = $scope.taskId;
        }else{
            $.jBox.tip("系统异常返回登录页。。。！");
            $state.go("login/login");
        }
        $scope.chooseTaskId = id;//保存最终值任务id
        //基本信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/alesmynewchecktask/getTaskById",
            method: "post",
            data: {id: id}
        }).success(function (obj) {
            $scope.checkTask = obj.data;
            $scope.checkTask.taskBeginTime = new Date(parseInt(obj.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
            $scope.checkTask.taskEndTime = new Date(parseInt(obj.data.taskEndTime) + 28800000).toISOString().slice(0, 10);
            $scope.checkTask.taskSampleDeadline = new Date(parseInt(obj.data.taskSampleDeadline) + 28800000).toISOString().slice(0, 10);
            //根据任务级别显示承担单位、抽样单位、检测单位
            if ("0" == obj.data.taskLevel) {
                $("div[name='cdUnit']").show();
                //承担单位
                $scope.checkTask.cdUnits = "";
                if (obj.speList.length != 0) {
                    for (var i = 0; i < obj.speList.length; i++) {
                        if ($scope.checkTask.cdUnits == "") {
                            $scope.checkTask.cdUnits += obj.speList[i].leadUnitName;
                        } else {
                            $scope.checkTask.cdUnits += "," + obj.speList[i].leadUnitName;
                        }
                    }
                } else {
                    $scope.checkTask.cdUnits = "无";
                }
            } else if ("1" == obj.data.taskLevel) {
                $("div[name='cyUnit']").show();
                $("div[name='jcUnit']").show();
            }
            //抽样单提交状态
            if ("2" == obj.data.isSample) {
                $scope.synType = "部分提交";
            } else if ("1" == obj.data.isSample) {
                $scope.synType = "已提交";
                $("button[name='sumSampleBut']").attr("disabled", true);//禁止点击
            } else {
                $scope.synType = "未提交";
            }
        }).error(function (obj) {
        });
   // };
    //$scope.initInfo();

    //根据任务获取监测对象
    $("#JC_XX_Grid").dataTable({
        // fnDrawCallback: function () {
        //     //点击行内任意内容即选择行
        //     $("#JC_XX_Grid tbody tr td").each(function (i, o) {
        //         $(o).click(function () {
        //             var checked = $(this).parent().find("input[type='checkbox']")[0]
        //             if (checked.checked) {
        //                 checked.checked = false;
        //                 $(this).parent().removeClass("selected");
        //             } else {
        //                 checked.checked = true;
        //                 $(this).parent().addClass("selected");
        //             }
        //         });
        //     })
        // },
        ajax: {
            url: "/sofn-ales-web/alesmynewchecktask/getMyTaskObjById",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            error:function (msg) {
                console.log(msg);
                
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: function (params) {
                if($scope.chooseTaskId != undefined){
                    params.id = $scope.chooseTaskId;
                }
            },
            type: "post"
        },
        columns: [
            {
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input type="checkbox" name="ck"/>';
                },
                width: "3%"
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
                data: "productType",
                title: "产品种类"
            },
            {
                data: "productName",
                title: "产品名称"
            },
            {
                data: "monitorNum",
                title: "抽样数量"
            },
            {
                data: "areaId",
                title: "受检区域",
                render: function (data, type, row) {
                    if (data != null && data != undefined) {
                        return $.fn.Cityname(data);
                    } else {
                        return "未填写";
                    }
                }
            },
            {
                data: "isSample",
                title: "状态",
                render: function (data, type, row) {
                    if (data == "0") {
                        return '<span name ="state" style="color:#9c3328">'+ "未上报"+'</span>';
                    } else if (data == "1") {
                        return '<span name ="state" style="color:#9dbed8">'+"已上报"+'</span>';
                    }else if(data == "2"){
                        return '<span name ="state" style="color:#9acfea">'+ "部分上报"+'</span>';
                    }
                }
            },
            {
                data: "id",
                title: "操作",
                render: function (data, type, row) {
                    return '<a style="cursor:pointer" onclick="cySubmit(\'' + row.id+','+row.productName+ ','+row.industry+','+ row.superviseCheckTaskId+'\')">抽样单</a>';
                }
            }
        ]
    });
    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#JC_XX_Grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i <rows.length; i++) {
            if (rows[i].isSample == '1' ) {
                $.jBox.tip("所选任务包括已上报任务，无法进行操作！");
                return false;
            }
            ids.push(rows[i].id);
            console.log(rows[i].id);
        }
        return ids;
      //  return rows;
    };
    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#JC_XX_Grid input[type='checkbox']").prop("checked", true);
            $("#JC_XX_Grid tbody tr").addClass("selected");
        } else {
            $("#JC_XX_Grid input[type='checkbox']").prop("checked", false);
            $("#JC_XX_Grid tbody tr").removeClass("selected");
        }
    });

    //获取选择行记录id集合
    $scope.getSelectRowsIds = function () {
        var rows = $("#JC_XX_Grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            if (rows[i].isSample == '1' ) {
                $.jBox.tip("所选任务包括已上报任务，无法进行操作！");
                return false;
            }
            ids.push(rows[i].id);
        }
        return ids;
    };
    //获取当前时间，格式YYYY-MM-DD
    $scope.getNowFormatDate = function() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year+"" + month+"" + strDate;
        return currentdate;
    }
    /**
     * 同步至检测系统
     * */
    $scope.syncToAds = function () {
        var date = $scope.getNowFormatDate();//.replace("-","");
        var taskend = $scope.checkTask.taskSampleDeadline.replace("-","").replace("-","");
      if(date > taskend){
          $.jBox.tip("当前任务已过抽样截止日期禁止上报抽样内容！！！");
          return false;
      }
        var rows = $scope.getSelectLinaDates();
        if (rows) {
            for (var k = 0; k < rows.length; k++) {
                if (rows[k].isSample == '1' ) {
                    $.jBox.tip("所选任务包括已上报任务，无法进行操作！");
                    return false;
                }
            }
        }
        var i = $scope.getSelectRowsIds();

        if (i != false) {
            for (var b = 0; b < i.length;b++){
                str = i[b];
            }
            var data = {
                jsonString: JSON.stringify(rows)
            };
        $.jBox.confirm("确认提交该监测信息下所有抽样单信息，提交后将不能新增抽样单或修改、删除该任务下任意抽样单", "等待服务器响应", function (v) {
            if (v == true) {
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url:"/sofn-ales-web/alesmynewchecktask/syncToAds",
                    method: "POST",
                    dataType: "Json" ,
                    data: rows,
                }).success(function (data) {
                    console.log("后台响应服务状态码"+data.httpCode)
                    if(data.key == "0"){
                        $.jBox.tip("没有抽样单！");
                        $("#JC_XX_Grid").dataTable().api().ajax.reload();
                    }else{
                        $.jBox.tip("成功！");
                        $("#JC_XX_Grid").dataTable().api().ajax.reload();
                    }
                }).error(function (data) {
                    console.log(data);
                    
                    $("button").attr("disabled", false);
                });
            }

            return true;
        }, {buttons: {'确认': true, '取消': false}});}
    };

    //抽样信息填报
    window.cySubmit = function (o) {
        window.localStorage.setItem("ales_taskManage_sample_writeMytaskSample_checkTaskTaskName_print",$scope.checkTask.taskName);//任务名称(打印样品编码使用)
        var p = o.split(",");
        var dateEnd = $scope.checkTask.taskEndTime.replace("-","").replace("-","");
        window.localStorage.setItem("ales_taskManage_myTaskInformation_jcUnitName",$scope.checkTask.jcUnitName);
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_dateEnd",dateEnd);
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_taskInfos", $scope.checkTask.isSample);//当前任务
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_sampleList_opr", p[0]);//当前检测对象id
        window.localStorage.setItem("ales_taskmanage_mychecktask_productName_key",p[1]);
        window.localStorage.setItem("ales_taskmanage_mychecktask_industry_key",p[2]);//名称
        window.localStorage.setItem("ales_taskmanage_mychecktask_superviseCheckTaskId_key",p[3]);//任务id
        $state.go("index.content.ales/taskManage/myTaskSampleList");//行业
    };
    $scope.downSample=function () {
        window.location.href ="webapp/ales/taskManage/sample/JDCMB.docx"
    }
    /**
     * 下载
     * */
    window.downFile = function () {
        var pdfAddress=$scope.checkTask.attachmentAddress;
        var filename=$scope.checkTask.attachmentName;
        var file = GetFileNameNoExt(filename);
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ales-web/alesmynewchecktask/downFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', pdfAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', file);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    $scope.retrunDiskTop = function () {
        if ($scope.oprIdx == "history") {
            window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II", "history");
            $state.go("index.content.ales/taskManage/myCheckTask");
        } else {
            window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II", "newCheck");
            $state.go("index.content.ales/taskManage/myCheckTask");
        }
    }

});
