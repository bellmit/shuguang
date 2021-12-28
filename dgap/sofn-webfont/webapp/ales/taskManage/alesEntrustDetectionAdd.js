var myapp = angular.module("myapp", []);
myapp.controller("alesEntrustDetectionAdd", function ($scope, $http, $state, $q) {

    $scope.wtTaskId = window.localStorage.getItem("ales_taskManage_alesEntrustDetectionOpr");//委托任务id
    $scope.currentOrg = {};//当前机构
    $scope._alesPdStandard = [];//判定标准
    $scope.alesEntrustDetection = {};//委托任务bean
    $scope.operation = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_sampleList_cyxx_opr_dongzuo");//操作动作

$scope.STDWselects = [];
    //验证初始化
    $('#attributeForm').bootstrapValidator();
    $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");

    var currentOrgRequest = $http({
        error:function (msg) {
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method: "post",
        dataType: "json"
    });

    var entrustDetectionRequest = $http({
        error:function (msg) {
           
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/llesEntrustDetection/getTaskById",
        method: "post",
        dataType: "json",
        data: {id: $scope.wtTaskId}
    });

    $q.all([currentOrgRequest]).then(function (data) {
            $scope.currentOrg = data[0].data.data;

            //任务优先级-->默认一般(3) 紧急(2) 特急(1)
            $scope.priority = 3;
            $scope.alesEntrustDetection.taskReleaseUnit = $scope.currentOrg.orgName;
            if ($scope.wtTaskId == null || $scope.wtTaskId == undefined) {
                $scope.alesEntrustDetection.createOrgName = $scope.currentOrg.orgName;
                $scope.alesEntrustDetection.createOrgId = $scope.currentOrg.orgId;
            }
            $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", $scope.currentOrg.regionId);
            switch ($scope.currentOrg.orgLevel) {
                case "2":
                    $("#selectQueryShen").attr({"disabled": "disabled"});
                    break;
                case "3":
                    $("#selectQueryShen").attr({"disabled": "disabled"});
                   // $("#selectQueryShi").attr({"disabled": "disabled"});
                    break;
                case "4":
                    $("#selectQueryShen").attr({"disabled": "disabled"});
                   // $("#selectQueryShi").attr({"disabled": "disabled"});
                   // $("#selectQueryXian").attr({"disabled": "disabled"});
                    break;
            }
            return data;
        }, function (data) {
            $.jBox.tip("机构数据获取失败！", "error");
            console.log("机构数据获取失败！", data);
        }
    ).then(function (data) {
        if ($scope.wtTaskId != null) {
            $("#OprTitle").html("修改");

            $q.all([entrustDetectionRequest]).then(function (data) {
                $scope.alesEntrustDetection = data[0].data.data;
                //任务优先级-->默认一般(3) 紧急(2) 特急(1)
                if($scope.alesEntrustDetection.priority != null){
                    $scope.priority = $scope.alesEntrustDetection.priority;
                }else{
                    $scope.priority = 3;
                }

                delete $scope.alesEntrustDetection.createTime ;
                delete $scope.alesEntrustDetection.updateTime ;
                vm.files=[];
                if($scope.alesEntrustDetection.attachments!=null&&$scope.alesEntrustDetection.attachments!=undefined&& $scope.alesEntrustDetection.attachments!="") {
                    var urls = $scope.alesEntrustDetection.attachments.split("*##*");
                    var names = $scope.alesEntrustDetection.attachmentNames.split("*##*");
                    for (var i = 0; i < urls.length; i++) {
                        console.debug("src is:"+urls[i]+",name is:"+names[i]);
                        vm.files = vm.files.concat([{
                            hidden: false,
                            src: urls[i],
                            name: names[i]}]);
                    }
                };
                //任务时间
                $("input[name='taskBeginTime']").val(new Date(parseInt($scope.alesEntrustDetection.taskBeginTime) + 28800000).toISOString().slice(0, 10));
                $("input[name='taskEndTime']").val(new Date(parseInt($scope.alesEntrustDetection.taskEndTime) + 28800000).toISOString().slice(0, 10));
                $("input[name='taskYear']").val($scope.alesEntrustDetection.taskyear);//年度
                $scope.Upselects =[];
                var map = {
                    leadUnitId: $scope.alesEntrustDetection.stUnitId,
                    leadUnitName: $scope.alesEntrustDetection.stUnitName
                };
                $scope.Upselects.push(map);
                $scope.STDWselects.push(map);
                $scope._alesPdStandard = [];//判定标准数组
                var pd = data[0].data.standards;
                if (pd.length != 0) {
                    for (var i = 0; i < pd.length; i++) {
                        var map = {
                            id: pd[i].standardId,
                            code: pd[i].standardName
                        };
                        $scope._alesPdStandard.push(map);
                    }
                    $scope.alesEntrustDetection.detection = $scope._alesPdStandard.map(function (item) {
                        return item.code;
                    }).join(',');
                    $scope.alesEntrustDetection.standardId = $scope._alesPdStandard.map(function (item) {
                        return item.id;
                    }).join(',');

                }
                $("button[name='sampleButton']").show();
            }, function (data) {
                $.jBox.tip("任务信息获取失败！", "error");
                console.log("任务信息获取失败！", data);
            });
        }
    });

    //验证是否通过
    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };

    //清除验证状态
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };

    /**
     * 手动表单验证
     * */
    window.SdDateValid = Object;
    SdDateValid.ValidByCode = function (type, name) {//关联判断状态
        if ($(type + "[name='" + name + "']").val() == "") {
            $(type + "[name='" + name + "']").next().show();
            $(type + "[name='" + name + "']").css("border-color", "#a94442");
        } else {
            $(type + "[name='" + name + "']").next().hide();
            $(type + "[name='" + name + "']").css("border-color", "#3c763d");
        }
    };

    SdDateValid.saveDataValid = function () {//状态展示
        //年度
        if ($("input[name='taskYear']").val() == "") {
            $("input[name='taskYear']").next().show();
            $("input[name='taskYear']").css("border-color", "#a94442");
        } else {
            $("input[name='taskYear']").next().hide();
            $("input[name='taskYear']").css("border-color", "#3c763d");
        }
        //开始时间
        if ($("input[name='taskBeginTime']").val() == "") {
            $("input[name='taskBeginTime']").next().show();
            $("input[name='taskBeginTime']").css("border-color", "#a94442");
        } else {
            $("input[name='taskBeginTime']").next().hide();
            $("input[name='taskBeginTime']").css("border-color", "#3c763d");
        }
        //结束时间
        if ($("input[name='taskEndTime']").val() == "") {
            $("input[name='taskEndTime']").next().show();
            $("input[name='taskEndTime']").css("border-color", "#a94442");
        } else {
            $("input[name='taskEndTime']").next().hide();
            $("input[name='taskEndTime']").css("border-color", "#3c763d");
        }
        //受托单位
        if ($("input[name='stUnitName']").val() == "") {
            $("input[name='stUnitName']").next().show();
            $("input[name='stUnitName']").css("border-color", "#a94442");
        } else {
            $("input[name='stUnitName']").next().hide();
            $("input[name='stUnitName']").css("border-color", "#3c763d");
        }
        //判定标准
        if ($("input[name='detection']").val() == "") {
            $("input[name='detection']").next().show();
            $("input[name='detection']").css("border-color", "#a94442");
        } else {
            $("input[name='detection']").next().hide();
            $("input[name='detection']").css("border-color", "#3c763d");
        }
    };
    //状态展示
    SdDateValid.DataValid = function (o) {
        //年度
        if ($("input[name='" + o.name + "']").val() == "") {
            $("input[name='" + o.name + "']").next().show();
            $("input[name='" + o.name + "']").css("border-color", "#a94442");
        } else {
            $("input[name='" + o.name + "']").next().hide();
            $("input[name='" + o.name + "']").css("border-color", "#3c763d");
        }
    };
    //判断标识
    SdDateValid.ReturnValid = function () {
        //年度
        if ($("input[name='taskYear']").val() == "") {
            return false;
        }
        //开始时间
        if ($("input[name='taskBeginTime']").val() == "") {
            return false;
        }
        //结束时间
        if ($("input[name='taskEndTime']").val() == "") {
            return false;
        }
        //受托单位
        if ($("input[name='stUnitName']").val() == "") {
            return false;
        }
        //判定标准
        if ($("input[name='detection']").val() == "") {
            return false;
        }
        return true;
    };



    //检测对象列表
    $scope.sample = function () {
        window.localStorage.setItem("wtTaskId", $scope.wtTaskId);
        window.localStorage.removeItem("ales_wtTaskInfo_ID_DetecTionObject");
        window.localStorage.setItem("ales_wtTaskInfo_ID_DetecTionObject", $scope.wtTaskId);
        window.localStorage.setItem("fileCode", $scope.alesEntrustDetection.fileCode);
        window.localStorage.setItem("alesEntrustDetectionAdd_detection",$scope.alesEntrustDetection.detection);//判定标准

        // 缓存任务名称
        window.localStorage.setItem("ales_wtTaskInfo_ID_DetecTionObjectTsample_writeSample_taskname",$scope.alesEntrustDetection.taskName);


        $state.go("index.content.ales/taskManage/alesEntrustDetectionObject/alesEntrustDetectionObject");
    };


    /**
     * 委托检测单位选择列表
     * */
    $scope.selects =[];

    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });

    $scope.selectModalQdButt = function () {
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };

    //表格
    $("#selectGrid").dataTable({
        fnDrawCallback: function () {
            $("#selectGrid tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    $scope.STDWselects = [];
                    var map = {
                        leadUnitId: $(this).parent().find("[name='id']").val(),
                        leadUnitName: $(this).parent().find("[name='name']").html()
                    };
                    $scope.STDWselects.push(map);
                    var name = "";
                    for (var i = 0; i < $scope.STDWselects.length; i++) {
                        if ($scope.STDWselects[i] != undefined) {
                            if (name != "") {
                                name += "," + $scope.STDWselects[i].leadUnitName;
                            } else {
                                name = $scope.STDWselects[i].leadUnitName;
                            }
                        }
                    }
                    $("input[name='stUnitName']").val(name);
                    $("#selectModal").modal('hide');
                    SdDateValid.ValidByCode("input", "stUnitName");
                });
            })
        },
        ajax: {
            url: "/sofn-ales-web/dailyEnforceLaw/getOrgsListByUserToken",
            dataSrc: function (json) {
                json.recordsTotal = json.page.recordsTotal;//总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start;//起始位置
                json.length = json.page.length;
                return json.data;
            },error:function (msg) {
                console.log(msg);
                
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: function (params) {
                params.token = window.localStorage.getItem("token");
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != "") {
                    params.dtAreaId = $("#selectQueryXian").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == "") {
                    params.dtAreaId = $("#selectQueryShi").val();
                }
                if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == "") {
                    params.dtAreaId = $("#selectQueryShen").val();
                }

                params.dtName = $("input[name='selectQueryName']").val();
            },
            type: "post"
        },
        columns: [
            {
                data: 'id',
                render: function (data, type, row, meta) {
                    return '<input type="hidden" name="id" value = "' + data + '"/>';
                }
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "dtName",
                title: "单位名称",
                render: function (data, type, row) {
                    return '<span name = "name">' + data + '</span>';
                }
            },
            {
                data: "dtType",
                title: "所属行业"
            },
            {
                data: "dtAddress",
                title: "所属区域"
            }
        ]
    });

    $("#selectTocheck").click(function () {
        $("#selectGrid").dataTable().api().ajax.reload();
    });

    //查询条件重置

    $scope.cleanSelectModelQuery = function () {
        $("input[name='selectQueryName']").val("");
    };

    /**
     * other
     * */


    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#WT_CYD_GRID input[type='checkbox']").prop("checked", true);
            $("#WT_CYD_GRID tbody tr").addClass("selected");
        } else {
            $("#WT_CYD_GRID input[type='checkbox']").prop("checked", false);
            $("#WT_CYD_GRID tbody tr").removeClass("selected");
        }
    });


    /**
     * 判定标准-选择弹框数据
     * **/
    $('#judgeStandard_Model').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $('#judgeStandard_Model').on('show.bs.modal', function () {

    });
    $scope.judgeStandardStandards = function () {
        //回显
        if(!$scope.selects || $scope.selects.length <= 0){
            if($scope.alesEntrustDetection.detection && $scope.alesEntrustDetection.standardId){
                var codes =$scope.alesEntrustDetection.detection.replace(/,$/, '').replace(/^,/, '').split(',');
                var ids= $scope.alesEntrustDetection.standardId.replace(/,$/, '').replace(/^,/, '').split(',');
                $scope.selects = ids.map(function(id,i) {return {id: id, code: codes[i]}});
            }
        }
        $('#judgeStandardTaskGrid').dataTable().api().ajax.reload();
        $("#judgeStandard_Model").modal('show');
    };//判定标准
    /*记录已选择数据*/
    $scope.selects = [];
    var judgeStandardTaskGrid = $("#judgeStandardTaskGrid").dataTable({
       /* bPaginate: false, //不显示分页信息
        bInfo:false,*/
        fnDrawCallback: function () {
            //点击行内任意内容即选择行
            var table = $(this);
            table.off('click', 'tbody tr td').on('click', 'tbody tr td', function(){
                var checked = $(this).parent().find("input[type='checkbox']")[0];
                if (!checked.checked) {
                    $(this).parent().removeClass("selected");
                    //人员移除
                    var u_d = $(this).parent().find("[name='ck']").val();
                    $scope.selects.map(function(item, i){
                        if(item.id == u_d){
                            $scope.selects.splice(i, 1);
                        }
                    });

                } else {
                    // checked.checked = true;
                    $(this).parent().addClass("selected");
                    //人员选择记录
                    var u_d = $(this).parent().find("[name='ck']").val();
                    var map = {
                        id: $(this).parent().find("[name='ck']").val(),
                        code: $(this).parent().find("[name='name']").html()
                    };
                    var hasInclude = false;
                    $scope.selects.map(function(item, i){
                        if(item.id == u_d){
                            $scope.selects[i] = map;
                            hasInclude = true;
                        }
                    });
                    if(!hasInclude){
                        $scope.selects.push(map);
                    }


                }
            });
        },
        ajax: {
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/alesTools/geteDtermineStandard",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;
                json.recordsFiltered = json.data.total;
                json.start = json.data.pageNum;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            type: "post"
        },
        columns: [
            {
                data: "id",
                title : '<input id="checkAll" onclick="checkAll(this.checked)" type=checkbox>',
                render: function (data, type, row) {
                    for (var i = 0; i < $scope.selects.length; i++) {
                        if ($scope.selects[i] != undefined && data == $scope.selects[i].id) {
                            return '<input type="checkbox" checked = "true" name="ck"  value="' + data + '"/>';
                        }
                    }
                    return '<input type="checkbox" name="ck" value="' + data + '"/>';
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
                data: "standardName",
                title: "检测标准名称",
                render: function (data, type, row, meta) {
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
                },
            },
            {
                data: "standardCode",
                title: "标准号",
                render: function (data, type, row) {
                  return '<span name = "name" title="">' + data + '</span>';
                }
            }
            ,
            {
                data: "remark",
                title: "说明",
                render: function (data, type, row, meta) {
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
                },
            },
        ]
    }).error(function (msg) {
        console.log(msg);
        
    });
    //判定标准
    window.checkAll = function(checked){
        //清空本页牵头单位
        $("input[name='ck']").each(function(i,o){
            $(o).prop("checked",checked);
            $("input[name='ck']").each(function(i,o) {
                var u_d =  $(o).val();
                for (var i = 0; i < $scope.selects.length; i++) {
                    if ($scope.selects[i] != undefined && u_d == $scope.selects[i].id) {
                        $scope.selects.splice(i, 1)
                    }
                }
            })
        })
        //选中本页已选择牵头单位
        if(checked){
            $("input[name='ck']:checked").each(function(i,o) {
                var map = {
                    id: $(o).val(),
                    code: $(o).parent().parent().find("[name='name']").html()
                };
                $scope.selects.push(map);
            });
        }
    }
    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    //确定
    $scope.selectPDBzButt = function () {

        $("input[name='detection']").val("");
        var standardCode = $scope.selects.map(function(item){ return item.code}).join(',');

        $("#inputDetection").val(standardCode);
        $("#judgeStandard_Model").modal('hide');
        $('#inputDetection').trigger('input');
        SdDateValid.ValidByCode("input", "qtName");

        if($("#inputDetection").val() =="" || $("#inputDetection").val() == null || $("#inputDetection").val() ==undefined){
            $(".PdDataValidTip").css("display", "block");
            return;
        }else{
            $(".PdDataValidTip").css("display", "none")
        }
        $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应

    };
    //判定标准赋值
/*    var selecteds = [];
    $scope.selectPDBzButt = function () {
        selecteds = [];
        $('#judgeStandardTaskGrid').find('tbody tr :checked').closest('tr').each(function(){
            console.log( 'xxx', $(':checkbox', this).attr('data-id'));
            selecteds[selecteds.length] = {
                id: $(':checkbox', this).attr('data-id'),
                code : $(':checkbox', this).attr('data-code')
            };
        });
        $scope._alesPdStandard = selecteds;
        $scope.alesEntrustDetection.detection = selecteds.map(function(item){
           return  item.code
        }).join(',');
        $("#inputDetection").val($scope.alesEntrustDetection.detection);
        $('#inputDetection').trigger('input');
        $("#judgeStandard_Model").modal('hide');
        SdDateValid.ValidByCode("input", "detection");
    };*///判定标准确定按钮

    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
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
            var acceptFileTypes = /\.(doc|docx|xls|xlsx|pdf|rar|zip|jpg|jpeg|png|gif|bmp)$/i;
            var imageTypes = /\.(jpg|jpeg|png|gif|bmp)$/i;

                if (!acceptFileTypes.test(file.name)) {
                    swal('', '只能上传doc,docx,xls,xlsx,pdf,rar,zipg类型文档或者jpg,jpeg,png,gif,bmp类型图片', 'warning');
                    return false;
                }

            if (imageTypes.test(file.name)) {
                if (file.size > 5* 1024 * 1024) {
                    swal('', '图片大小不能超过5MB', 'warning');
                    return false;
                }
            }else{
                if (file.size > 50 * 1024 * 1024) {
                    swal('', '文件大小不能超过50MB', 'warning');
                    return false;
                }
            }

        /*   if(file.src == null){
               $.jBox.tip("文件暂时未上传成功请等待。。。。。！")
               $("button[name='saveName']").attr("disabled", false);//禁止点击
           }else if(file.src != null){
               $("button[name='saveName']").attr("disabled", true);//禁止点击
               return true;
           }*/
            return true;
        },
        complete: function(err, xhr) {
            if (!err) {
                // All files successfully uploaded.
                swal("上传成功", "操作成功!", "success");
            } else {
                swal("上传失败", "操作失败!", "error");
            }
        }
    };
    /**
     * 任务基本信息
     * */
    //保存任务
    $scope.saveTask = function () {
        SdDateValid.saveDataValid();
        if (!$scope.dataFormValidator()) {
            return false;
        }
        if (!SdDateValid.ReturnValid()) {
            return false
        }

        // 缓存任务名称
        window.localStorage.setItem("ales_wtTaskInfo_ID_DetecTionObjectTsample_writeSample_taskname",$scope.alesEntrustDetection.taskName);

        //无用数据清除
        //delete $scope.alesEntrustDetection.createTime;
        //delete $scope.alesEntrustDetection.updateTime;
        //$scope.alesEntrustDetection.id = $scope.wtTaskId;
        //数据填充
        
        //优先级(3--一般,2--紧急,1--特急)
        $scope.alesEntrustDetection.priority = $("select[name='priority']").val();
        $scope.alesEntrustDetection.taskyear = $("input[name='taskYear']").val();
        $scope.alesEntrustDetection.taskBeginTime = $("#taskBeginTime").val();
        $scope.alesEntrustDetection.taskEndTime = $("#taskEndTime").val();
        //受托单位
        $scope.alesEntrustDetection.stUnitId = $scope.STDWselects[0].leadUnitId;
        $scope.alesEntrustDetection.stUnitName = $scope.STDWselects[0].leadUnitName;
        //增加判断，如果非弹出框选择，则默认值
        if($scope.selects.length<1)
            $scope.selects = $scope._alesPdStandard;


        //过滤null
        var temp =[];
        for(var i =0;i<$scope.selects.length;i++){
            if($scope.selects[i] != null)
                temp.push($scope.selects[i]);
        }

        $scope.selects = temp;
        $scope.alesEntrustDetection.pdStandard = JSON.stringify($scope.selects);
        $scope.alesEntrustDetection.token = window.localStorage.getItem("token");

        //取附件照片，并判断是否有至少一张
        $scope.alesEntrustDetection.attachments = "";
        $scope.alesEntrustDetection.attachmentNames = "";
        for(var i=0;i<vm.files.length;i++){
            if(i==vm.files.length-1){
                $scope.alesEntrustDetection.attachments=$scope.alesEntrustDetection.attachments+vm.files[i].src;
                $scope.alesEntrustDetection.attachmentNames=$scope.alesEntrustDetection.attachmentNames+vm.files[i].name;
            }else{
                $scope.alesEntrustDetection.attachments=$scope.alesEntrustDetection.attachments+vm.files[i].src+"*##*";
                $scope.alesEntrustDetection.attachmentNames=$scope.alesEntrustDetection.attachmentNames+vm.files[i].name+"*##*";
            }
        }
        if($scope.alesEntrustDetection.attachments == null|| $scope.alesEntrustDetection.attachments == ""|| $scope.alesEntrustDetection.attachments == undefined){
            jBox.tip("附件照片请至少上传一张！", 'info');
            return false;
        }
        if( vm.files.length <= 0 || vm.files[0].src == null){
            $.jBox.tip("文件暂时未上传成功请等待。。。。。！")
            return false;
        }

        var URL;
        if ($scope.wtTaskId == null) {
            URL = "/sofn-ales-web/llesEntrustDetection/add";
        } else {
            URL = "/sofn-ales-web/llesEntrustDetection/update";
        }
        $("button").attr("disabled", true);
        $.ajax({
            url: URL,
            headers:{
                token:window.localStorage.getItem("token")
            },
            type: "post",
            dataType: "json",
            data: $scope.alesEntrustDetection,
            success: function (data) {
                if(data.httpCode =="400"){
                    $("button").attr("disabled", false);
                    $.jBox.tip(data.msg);
                }else{
                    $("button").attr("disabled", false);
                    $("button[name='sampleButton']").show();
                    $scope.wtTaskId = data.data.id;
                    $.jBox.tip("成功！");
                }

            },
            fail: function (data) {
                $("button").attr("disabled", false);
                $.jBox.tip("失败");
            }
        }).error(function (msg) {
           
        });
    };
});