var myapp = angular.module("myapp", []);
myapp.controller("etlAddDailyEnforceLaw", function($scope, $http, $state) {
    //激活验证规则
    $scope.DailyEnforceLaw = {};
    //$scope.DailyEnforceLaw.enforceLawResultFlag=1;//巡查结果默认为合格
    var orgId = "";
    $('#attributeForm').bootstrapValidator(); //必须有
    $('#entTemp').bootstrapValidator(); //必须有
    $scope.updateFlag = window.localStorage.getItem("ales_etl_updateFlag");
    $scope.upEnterpriseId = window.localStorage.getItem("ENTERPRISE_ID");
    $scope.id = window.localStorage.getItem("id");
    var token = window.localStorage.getItem("token");
    var area = "";

    //获取登录用户的所在地的区域
    $http({
        url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        orgId = data.data.orgId;
        area = data.data.regionId;
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = area.substr(0, 2 * (data.data.orgLevel - 1));
        $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
        $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.underArea);
        $.fn.mycity("queryTempSheng", "queryTempShi", "queryTempXian", $scope.underArea);
        $scope.disableAreaSelectByAreaId("subjTempSheng", "subjTempShi", "subjTempXian");
        $scope.disableAreaSelectByAreaId("querySheng", "queryShi", "queryXian");
        $scope.disableAreaSelectByAreaId("queryTempSheng", "queryTempShi", "queryTempXian");
        $scope.updateByGet();
        $scope.subjEntTemp.credType = "普通营业执照(有独立组织机构代码证)"; //默认选中普通执照
        loadEntGrid();
    }).error(function(msg) {

    });
    //限制区域是否可选
    $scope.disableAreaSelectByAreaId = function(provinceId, cityId, countyId) {
        if ($("#" + provinceId).val() != null && $("#" + provinceId).val() != "" && $("#" + provinceId).val() != undefined) {
            $("#" + provinceId).attr("disabled", true);
        }
        if ($("#" + cityId).val() != null && $("#" + cityId).val() != "" && $("#" + cityId).val() != undefined) {
            $("#" + cityId).attr("disabled", true);
        }
        if ($("#" + countyId).val() != null && $("#" + countyId).val() != "" && $("#" + countyId).val() != undefined) {
            $("#" + countyId).attr("disabled", true);
        }
    }

    $scope.subjEntTemp = {};
    //获取行业数据字典
    $scope.industryType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        data: "2",
        method: "post"
    }).success(function(data) {
        $scope.industryType = JSON.parse(data.data);
        $scope.subjEntTemp.entityIndustryId = $scope.industryType[0].id;
    }).error(function(msg) {
        console.log(msg);
        
    })

    //获取主体类别数据字典
    $scope.subjType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        data: "31",
        method: "post"
    }).success(function(data) {
        $scope.subjType = JSON.parse(data.data);
        $scope.subjEntTemp.entityTypeId = $scope.subjType[0].id;
    }).error(function(msg) {
        console.log(msg);
        
    })
    //获取组织形式数据字典
    $scope.orgMode = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        data: "6",
        method: "post"
    }).success(function(data) {
        $scope.orgMode = JSON.parse(data.data);
        $scope.subjEntTemp.entityScaleId = $scope.orgMode[0].id;
    }).error(function(msg) {
        
    })

    var vm = this;

    //文件上传配置
    vm.scenePicturesOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        error: function(msg) {
           
        },
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 5,
        filter: function(file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp|avi|mp4|mov)|(image\/(jpg|jpeg|png|gif|bmp|avi|mp4|mov)))$/i;
            console.log("info=:"+info);
            console.log("fileType=:"+file.type);
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
    //视频上传
    vm.sceneVideoOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        error: function(msg) {
           
        },
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始视频上传数量
        min: 1,
        //最大上传视频数量  avi/mp4/mov/
        max: 1,
        filter: function(file, info) {
            console.log("info=:"+info);
            console.log("fileType=:"+file.type);
            var imageTypes = /^((avi|mp4|mov)|(video\/(avi|mp4|mov|quicktime)))$/i;
            if (!imageTypes.test(file.type)) {
                swal('', '只能上传视频后缀为avi、mp4、mov的视频！', 'warning');
                return false;
            }
            if (file.size > 200 * 1024 * 1024) {
                swal('', '视频上传不能超过200MB', 'warning');
                return false;
            }
            return true;
        }
    };
//(jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp))
    //文件上传配置
    vm.principalSignaturesOptions = {
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        error: function(msg) {
           
        },
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

    //对啊日常执法的修改判断  修改返回数据
    $scope.updateByGet = function() {
        $scope.subjId = null;
        if ($scope.updateFlag == "1") {
            $("#OprTitle").html("修改执法日志");
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
                method: "post",
                data: { id: $scope.id }
            }).success(function(data) {
                $scope.DailyEnforceLaw = data.dailyEnforceLaw;
                console.log($scope.DailyEnforceLaw.enterpriseId);
                $scope.subjId =$scope.DailyEnforceLaw.enterpriseId;
                //区域回显的处理
                $scope.ObjXian = $scope.DailyEnforceLaw.areaId;
                //隐藏域赋值
                $.fn.mycity("ObjShen", "ObjShi", "ObjXian", $scope.DailyEnforceLaw.areaId);
                $("#ObjShen").attr("disabled", false);
                $("#ObjShi").attr("disabled", false);
                $("#ObjXian").attr("disabled", false);
                $("input[name='HiddenID']").val($scope.id);
                $("input[name='ObjName']").val($scope.DailyEnforceLaw.taskPersonName);
                $("input[name='datebegin']").val(new Date(parseInt($scope.DailyEnforceLaw.taskBeginTime)).toISOString().slice(0, 10));
                /*$("input[name='dateend']").val(new Date(parseInt($scope.DailyEnforceLaw.taskEndTime)).toISOString().slice(0, 10));*/ //取消结束时间段 2018年4月24日17:36:23
                if($scope.DailyEnforceLaw.taskPersonCount =="0"){
                   var personCount = $scope.DailyEnforceLaw.taskPersonName.split(",");
                    $("input[name='taskPersonCount']").val(personCount.length);
                }else{
                $("input[name='taskPersonCount']").val($scope.DailyEnforceLaw.taskPersonCount);
                }
                $("#enforceLawResultFlag1").prop("disabled", false);
                $("#enforceLawResultFlag2").prop("disabled", false);
                $("#enforceLawResultFlag3").prop("disabled", false);
                $("#enforceLawResultFlag4").prop("disabled", false);
                //现场照片
                vm.scenePictures = [];
                if ($scope.DailyEnforceLaw.scenePictures != null && $scope.DailyEnforceLaw.scenePictures != undefined && $scope.DailyEnforceLaw.scenePictures != "") {
                    var urls = $scope.DailyEnforceLaw.scenePictures.split("*##*");
                    var names = $scope.DailyEnforceLaw.scenePictureNames.split("*##*");
                    for (var i = 0; i < urls.length; i++) {
                        console.debug("src is:" + urls[i] + ",name is:" + names[i]);
                        vm.scenePictures = vm.scenePictures.concat([{
                            hidden: false,
                            src: urls[i],
                            name: names[i]
                        }]);
                    }

                    //--------------
                    // 获取主体经纬度
                    $http({
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        url:  "/sofn-ales-web/dailyEnforceLaw/getTtsScltxxcjRegiter",
                        data:{enterpriseId:$scope.subjId},
                        method: "post"
                    }).success(function(data) {
                        $scope.subjEnt = data.ttsScltxxcjRegiter;
                    }).error(function(msg) {
                        console.log(msg);
                        
                    });
                };
                //现场视频
                //--------------
                vm.sceneVideoUrl = [];
                if ($scope.DailyEnforceLaw.sceneVideoUrl != null && $scope.DailyEnforceLaw.sceneVideoUrl != undefined && $scope.DailyEnforceLaw.sceneVideoUrl != "") {
                    console.log("视频地址==："+$scope.DailyEnforceLaw.sceneVideoUrl)
                    var urls = $scope.DailyEnforceLaw.sceneVideoUrl.split("*##*");
                    var names = $scope.DailyEnforceLaw.sceneVideoName.split("*##*");
                    for (var i = 0; i < urls.length; i++) {
                        console.debug("src is:" + urls[i] + ",name is:" + names[i]);
                        vm.sceneVideo = vm.sceneVideoUrl.concat([{
                            hidden: false,
                            src: urls[i],
                            name: names[i]
                        }]);
                    }
                }
                //负责人签字照片
                vm.principalSignatures = [];
                if ($scope.DailyEnforceLaw.principalSignatures != null && $scope.DailyEnforceLaw.principalSignatures != undefined && $scope.DailyEnforceLaw.principalSignatures != "") {
                    var urls = $scope.DailyEnforceLaw.principalSignatures.split("*##*");
                    var names = $scope.DailyEnforceLaw.principalSignatureNames.split("*##*");
                    for (var i = 0; i < urls.length; i++) {
                        console.debug("src is:" + urls[i] + ",name is:" + names[i]);
                        vm.principalSignatures = vm.principalSignatures.concat([{
                            hidden: false,
                            src: urls[i],
                            name: names[i]
                        }]);
                    }
                };
            }).error(function(msg) {
                
            });



        } else {
            $("#attributeForm").form("reset");
            $.fn.mycity("ObjShen", "ObjShi", "ObjXian", $scope.underArea);
            $scope.disableAreaSelectByAreaId("ObjShen", "ObjShi", "ObjXian");
        }
    }
    $scope.selectOrgId = undefined;
    /**
     * ---------人员选择列表--------------
     * */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects = [];

    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    $scope.selectPerson = function() {
        //回显
        if(!$scope.selects || $scope.selects.length <= 0){
            if($scope.DailyEnforceLaw.taskPersonName && $scope.DailyEnforceLaw.taskPersonId){
                var names = $scope.DailyEnforceLaw.taskPersonName.replace(/,$/, '').replace(/^,/, '').split(',');
                var ids= $scope.DailyEnforceLaw.taskPersonId.replace(/,$/, '').replace(/^,/, '').split(',');
                $scope.selects = ids.map(function(id,i) {return {id: id, userName: names[i]}});
            }
        }

        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };
    //对执法人员选择的地域限制条件
    $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", area);
    //执法人员选择确定

    //执法人员显示列表
    $(function() {
        $("#selectGrid").dataTable({
            fnDrawCallback: function() {
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
                            userName: $(this).parent().find("[name='userName']").html()
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
                url: "/sofn-ales-web/dailyEnforceLaw/getUsersListByUserToken",
                dataSrc: function(json) {
                    if (json.data == null || json.data == undefined) {
                        json.recordsTotal = 0; //总个数
                        json.recordsFiltered = 0;
                        json.pageNumber = 1; //页码
                        json.pageSize = 10; //每页个数
                        json.start = 0; //起始位置
                        json.length = 10;
                        return json.data;
                    }
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.pageNumber = json.page.start / json.page.length; //页码
                    json.pageSize = json.page.length; //每页个数
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.data;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                error: function(msg) {
                },
                data: function(params) {
                    params.token = window.localStorage.getItem("token");
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    params.keyword = $("input[name='selectQueryName']").val();
                },
                type: "post"
            },
            columns: [{
                    data: "id",
                    title: '选择',
                    render: function(data, type, row) {
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].id) {
                                return '<input type="checkbox" checked = "true" name="ck" value="' + data + '"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" value="' + data + '"/>';
                    },
                    orderable: false
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "userName",
                    title: "姓名",
                    render: function(data, type, row) {
                        return '<span name = "userName" value="' + data + '">' + data + '</span>';
                    }
                },
                {
                    data: "phone",
                    title: "联系电话"
                },
                {
                    data: "email",
                    title: "电子邮箱"
                }
            ],
        });
    });
    //执法人员  人数 赋值
    $scope.selectModalQdButt = function() {
        $("input[name='taskPersonName']").val("");
        var userName = $scope.selects.map(function(item){ return item.userName}).join(',');
        var userId = $scope.selects.map(function(item){ return item.id}).join(',');

        $("input[name='taskPersonName']").val(userName);
        $("input[name='taskpersonId']").val(userId);
        $scope.DailyEnforceLaw.taskPersonName = userName;
        $scope.DailyEnforceLaw.taskpersonId = userId;
        $("#selectModal").modal('hide');
        if ($scope.DailyEnforceLaw.taskPersonName == "" || $scope.DailyEnforceLaw.taskPersonName ==  null || $scope.DailyEnforceLaw.taskPersonName == undefined) {
            $(".objPerson").css("display", "block");
            $("input[name='taskPersonCount']").val(0);
            return;
        } else {
            $("input[name='taskPersonCount']").val($scope.selects.length);
            $(".objPerson").css("display", "none")
        }
        $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应
    };
    //点击查询按钮
    $("#selectTocheck").click(function() {
        $("#selectGrid").dataTable().api().ajax.reload();
    });
    //全选/全不选
    $("#checkAll").click(function() {
        if ($(this).prop("checked") == true) {
            $("#selectGrid input[type='checkbox']:not(:disabled)").prop("checked", true);
            $("#selectGrid tbody tr").addClass("selected");
        } else {
            $("#selectGrid input[type='checkbox']").prop("checked", false);
            $("#selectGrid tbody tr").removeClass("selected");
        }
    });
    //针对复选框无法选中bug
    // window.checkSelectThisBox = function(o) {
    //     $("#checkAll").prop("checked", false);
    //     if (o.checked) {
    //         o.checked = false;
    //         $(o).removeClass("selected");
    //     } else {
    //         o.checked = true;
    //         $(o).addClass("selected");
    //     }
    // };
    //执法人员查询条件重置
    $scope.cleanSelectModelQuery = function() {
        $("input[name='selectQueryName']").val("");
    };
    //清空已选择 执法人员的选择
    $scope.cleanSelected = function() {
        $scope.selects = [];
        $("input[name='ObjName']").val("");
        $('#selectGrid').dataTable().api().ajax.reload();
    };

    //点击保存任务 发送请求
    $scope.dailyEnforceLawSave = function() {
        //添加表单验证
        if ($("input[name='datebegin']").val() == "") {
            $(".objBeginTime").css("display", "block")
            return;
        } else {
            $(".objBeginTime").css("display", "none")
        }
        /* if($("input[name='dateend']").val()==""){
             $(".objEndTime").css("display","block");
             return;
         }取消时间段 2018年4月24日17:38:14
        else{
             $(".objEndTime").css("display","none")
         }*/
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            jBox.tip("请填写完整带*号信息！", 'info');
            return false;
        } //必须有 验证

        if ($("input[name='ObjName']").val() == "") {
            $(".objPerson").css("display", "block");
            return;
        } else {
            $(".objPerson").css("display", "none")
        }
        ObjShen = $("select[name='ObjShen']").val();
        ObjShi = $("select[name='ObjShi']").val();
        ObjXian = $("select[name='ObjXian']").val();
        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    $scope.ObjXian = ObjXian
                } else {
                    $scope.ObjXian = ObjShi
                }
            } else {
                $scope.ObjXian = ObjShen
            }
        }

        //现场照片
        $scope.DailyEnforceLaw.scenePictures = "";
        $scope.DailyEnforceLaw.scenePictureNames = "";
        for (var i = 0; i < vm.scenePictures.length; i++) {
            if (i == vm.scenePictures.length - 1) {
                $scope.DailyEnforceLaw.scenePictures = $scope.DailyEnforceLaw.scenePictures + vm.scenePictures[i].src;
                $scope.DailyEnforceLaw.scenePictureNames = $scope.DailyEnforceLaw.scenePictureNames + vm.scenePictures[i].name;
            } else {
                $scope.DailyEnforceLaw.scenePictures = $scope.DailyEnforceLaw.scenePictures + vm.scenePictures[i].src + "*##*";
                $scope.DailyEnforceLaw.scenePictureNames = $scope.DailyEnforceLaw.scenePictureNames + vm.scenePictures[i].name + "*##*";
            }
        }
        if ($scope.DailyEnforceLaw.scenePictures == null || $scope.DailyEnforceLaw.scenePictures == "" || $scope.DailyEnforceLaw.scenePictures == undefined) {
            jBox.tip("现场照片请至少上传一张！", 'info');
            return false;
        }
        //现场视频
        $scope.DailyEnforceLaw.sceneVideoUrl = "";
        $scope.DailyEnforceLaw.sceneVideoName = "";
        for (var i = 0; i < vm.sceneVideo.length; i++) {
            if (i == vm.sceneVideo.length - 1) {
                $scope.DailyEnforceLaw.sceneVideoUrl = $scope.DailyEnforceLaw.sceneVideoUrl + vm.sceneVideo[i].src;
                $scope.DailyEnforceLaw.sceneVideoName = $scope.DailyEnforceLaw.sceneVideoName + vm.sceneVideo[i].name;
            } else {
                $scope.DailyEnforceLaw.sceneVideoUrl = $scope.DailyEnforceLaw.sceneVideoUrl + vm.sceneVideo[i].src + "*##*";
                $scope.DailyEnforceLaw.sceneVideoName = $scope.DailyEnforceLaw.sceneVideoName + vm.sceneVideo[i].name + "*##*";
            }
        }
        /*if ($scope.DailyEnforceLaw.sceneVideo == null || $scope.DailyEnforceLaw.sceneVideo == "" || $scope.DailyEnforceLaw.sceneVideo == undefined) {
            jBox.tip("请上传现场视频！", 'info');
            return false;
        }*/

        //负责人签字照片
        $scope.DailyEnforceLaw.principalSignatures = "";
        $scope.DailyEnforceLaw.principalSignatureNames = "";
        for (var i = 0; i < vm.principalSignatures.length; i++) {
            if (i == vm.principalSignatures.length - 1) {
                $scope.DailyEnforceLaw.principalSignatures = $scope.DailyEnforceLaw.principalSignatures + vm.principalSignatures[i].src;
                $scope.DailyEnforceLaw.principalSignatureNames = $scope.DailyEnforceLaw.principalSignatureNames + vm.principalSignatures[i].name;
            } else {
                $scope.DailyEnforceLaw.principalSignatures = $scope.DailyEnforceLaw.principalSignatures + vm.principalSignatures[i].src + "*##*";
                $scope.DailyEnforceLaw.principalSignatureNames = $scope.DailyEnforceLaw.principalSignatureNames + vm.principalSignatures[i].name + "*##*";
            }
        }
        if ($scope.DailyEnforceLaw.principalSignatures == null || $scope.DailyEnforceLaw.principalSignatures == "" || $scope.DailyEnforceLaw.principalSignatures == undefined) {
            jBox.tip("负责人签字照片请上传一张！", 'info');
            return false;
        }

        //获取页面数据
        $scope.DailyEnforceLaw.createOrgId = orgId;
        $scope.DailyEnforceLaw.id = $("input[name='HiddenID']").val();
        $scope.DailyEnforceLaw.taskName = $("input[name='taskName']").val();
        $scope.DailyEnforceLaw.taskBeginTime = $("input[name='datebegin']").val();
        /*$scope.DailyEnforceLaw.taskEndTime = $("input[name='dateend']").val();*/ //取消时间段 2018年4月24日17:39:06
        $scope.DailyEnforceLaw.taskPersonCount = $("input[name='taskPersonCount']").val();
        $scope.DailyEnforceLaw.areaId = $scope.ObjXian;
        $scope.DailyEnforceLaw.enforceLawResultFlag = $("input[name='enforceLawResultFlag']:checked").val();
        $scope.DailyEnforceLaw.enforceLawResult = $("textarea[name='enforceLawResult']").val();
        $scope.DailyEnforceLaw.enterpriseAddress = $("input[name='enterpriseAddress']").val();
        $scope.DailyEnforceLaw.enterpriseName = $("input[name='enterpriseName']").val();
        $scope.DailyEnforceLaw.taskPersonId = $("input[name='taskpersonId']").val();
        $scope.DailyEnforceLaw.taskPersonName = $("input[name='ObjName']").val();
        $scope.DailyEnforceLaw.enterpriseId = $("input[name='enterpriseId']").val();
        if($scope.DailyEnforceLaw.enterpriseId == ''){
            $scope.DailyEnforceLaw.enterpriseId =  $scope.upEnterpriseId;
        }
        //判断修改或者新增
        if ($scope.updateFlag == "1") {
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/dailyEnforceLaw/updateDailyEnforceLaw",
                method: "post",
                data: $scope.DailyEnforceLaw,
            }).success(function(data) {
                console.log(data)
                if(data.httpCode == "400"){
                    $("button").attr("disabled", false);
                    window.localStorage.removeItem("id");
                    $.jBox.tip(data.msg);
                }else{
                    $("button").attr("disabled", false);
                    window.localStorage.removeItem("id");
                    $.jBox.tip("修改成功！");
                    $state.go("index.content.ales/etl/etlDailyEnforceLawlist");
                }

            }).error(function(msg) {
                
                $("button").attr("disabled", false);
                $.jBox.tip("修改失败！");
            })
        } else {
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/dailyEnforceLaw/addDailyEnforceLaw",
                method: "post",
                data: $scope.DailyEnforceLaw,
            }).success(function(data) {
                if(data.httpCode == "400"){
                    $("button").attr("disabled", false);
                    window.localStorage.removeItem("id");
                    $.jBox.tip(data.msg);
                }else{
                    $("button").attr("disabled", false);
                    window.localStorage.removeItem("id");
                    $.jBox.tip("新增成功！");
                    $state.go("index.content.ales/etl/etlDailyEnforceLawlist");
                }

            }).error(function(msg) {
                
                $("button").attr("disabled", false);
                $.jBox.tip("新增成功！");
            })
        };
    };
    //对任务名称重复的验证
    window.VerifyRepeat = function() {
        equalsName = $("input[name='taskName']").val();
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/dailyEnforceLaw/equalsTaskName",
            method: "post",
            params: {
                'taskName': equalsName
            }
        }).success(function(data) {
            if (data.b == false) {
                $(".objErro").css("display", "block")
            } else {
                $(".objErro").css("display", "none")
            }
        }).error(function(msg) {
            
        });
    }
    //对执法开始时间的验证
   /* window.dateendInput = function() {
        if ($("input[name='datebegin']").val() == "") {
            $(".objEndTime").css("display", "block");
            return;
        } else {
            $(".objEndTime").css("display", "none")
        }
    }*/
    //对执法结束时间的验证
    window.datebeginInput = function() {
        if ($("input[name='datebegin']").val() == "") {
            $(".objBeginTime").css("display", "block")
            return;
        } else {
            $(".objBeginTime").css("display", "none")
        }
    }
    //---------------提示标语-----------
    //地址
    window.versionAdress = function() {
        var adreVal = $("input[name='address']").val();
        if (adreVal == " "|| adreVal == "") {
            $(".reminder").css("display", "block");
            return;
        } else {
            $(".reminder").css("display", "none")
        }

    }
    // 联系人
    window.nameReminder = function (){
        var nameVal =$("input[name ='contactName']").val();
        if(nameVal == "" || nameVal == " " ){
            $(".nameNullCss").css("display", "block");
            return;
        }else {
            $(".nameNullCss").css("display", "none");
        }
    }
    // 证照号码
    //noinspection JSAnnotator
    window.orgCodelengt = function () {

       var len = $("input[name = 'orgCode']") .val();
        if(len == 15 || len == 18){
            $(".orgCodeCss").css("display","block");
        }else{
            $(".orgCodeCss").css("display","none");
        }

    }

    //----------------------------------------------
    //取消按钮
    $scope.dailyEnforceLawCancel = function() {
        window.localStorage.removeItem("id");
        $state.go("index.content.ales/etl/etlDailyEnforceLawlist")
    }

    //选择生产经营主体模态框显示
    /*  $scope.selectEnts = function(){
          $("#enterpriseModalId").modal("show");
      };*/
    $('#selectObjectId').click(function() {
        $("#entTempGrid").dataTable().api().ajax.reload();
        $("#enterpriseModalId").modal("show");
    })
    //选择临时生产经营主体模态框显示
    // $scope.selectEntTemp = function(){
    //     $("#entTempListModal").modal("show");
    //     $scope.querySubjEntTempList();
    // };

    //切换到生产经营主体
    $scope.switchEnt = function() {
        $("#gisId").attr("disabled", false);//解禁——查看地理位置功能
        $("#entTab").addClass("gui-TabCheck");
        $("#entTempTab").removeClass("gui-TabCheck");
        $("#productionModel").show();
        $("#temProductionModel").hide();
    }

    //切换到临时生产经营主体
    $scope.switchEntTemp = function() {
        $("#gisId").attr("disabled", true);//禁用——查看地理位置功能
        $("#entTempTab").addClass("gui-TabCheck");
        $("#entTab").removeClass("gui-TabCheck");
        $("#temProductionModel").show();
        $("#productionModel").hide();
        //     $scope.entTemp();
    }

    //dataTable
    //生产经营主体列表
    function loadEntGrid() {
        $("#enterpriseGrid").dataTable({
            ajax: {
                url: "/sofn-ales-web/subjEnt/getSubjEntList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {

                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                error: function(msg) {
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.enterpriseName = $("input[name='entName']").val();
                    params.entityIdcode = $("input[name='entIdCode']").val();
                    params.approveStatus = "1,3,4,5"; //查询可用的生产经营主体
                    // var temp = $scope.organization.regionId;
                    // if(temp.substr(4,6)=="00"){
                    //     temp = temp.substr(0,4);
                    //     if(temp.substr(2,4)=="00"){
                    //         temp = temp.substr(0,2);
                    //     }
                    // }
                    $scope.countys = $("#queryXian").find("option:selected").attr('value');
                    $scope.citys = $("#queryShi").find("option:selected").attr('value');
                    if ($scope.countys != "") {
                        params.area = $scope.countys;
                    } else if ($scope.countys == "" && $scope.citys != "") {
                        params.area = $scope.citys;
                    } else {
                        params.area = $scope.underArea;
                    }
                },
                type: "post"
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function(data, type, row) { // 模板化列显示内容
                        return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
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
                    data: "entityIndustryName",
                    title: "所属行业"
                },
                {
                    data: "address",
                    title: "主体地址",
                    render: function(data, type, row, meta) {
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
                    data: "legalName",
                    title: "法定代表人"
                },
                {
                    data: "legalPhone",
                    title: "代表电话"
                },
                {
                    data: "contactName",
                    title: "联系人"
                },
                {
                    data: "contactPhone",
                    title: "联系电话"
                }
            ],
            fnDrawCallback: function() {
                //设置选中状态
                $("#enterpriseGrid >tbody >tr").click(function() {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ent']").removeAttr("checked");
                        $(this).removeClass('selected');
                    } else {
                        $('#enterpriseGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });


        //临时登记主体查询
        //  $scope.entTemp =function () {
        $("#entTempGrid").dataTable({
            ajax: {
                url: "/sofn-ales-web/subjEnt/getSubjEntTempList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                error: function(msg) {
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.entityName = $("input[name='entTempName']").val();
                    $scope.countys = $("#queryTempXian").find("option:selected").attr('value');
                    $scope.citys = $("#queryTempShi").find("option:selected").attr('value');
                    if ($scope.countys != "") {
                        params.areaId = $scope.countys;
                    } else if ($scope.countys == "" && $scope.citys != "") {
                        params.areaId = $scope.citys;
                    } else {
                        params.areaId = $scope.underArea;
                    }
                },
                type: "post",
                cache: false
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function(data, type, row) { // 模板化列显示内容
                        return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "entityName",
                    title: "主体名称",
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
                    data: "entityIndustry",
                    title: "所属行业"
                },
                {
                    data: "address",
                    title: "主体地址",
                    render: function(data, type, row, meta) {
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
                    data: "contactName",
                    title: "联系人"
                },
                {
                    data: "contactPhone",
                    title: "联系电话"
                }
            ],
            fnDrawCallback: function() {
                //设置选中状态
                $("#entTempGrid >tbody >tr").click(function() {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ent']").removeAttr("checked");
                        $(this).removeClass('selected');
                    } else {
                        $('#entTempGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });
    }
    // }
    var enterpriseArea = ""
    //提交生产经营主体
    $scope.submitEnt = function() {
        var rows = $("#enterpriseGrid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        $scope.subjEnt = rows[0];
        console.log("选择主体的id===>>>"+$scope.subjEnt.id);
        $("#enterpriseId").val($scope.subjEnt.id);
        $("#enterpriseName").val($scope.subjEnt.enterpriseName);
        $("#enterpriseAddress").val($scope.subjEnt.address);
        //$("#enterpriseIdcode").val($scope.subjEnt.entityCode)
        enterpriseArea = $scope.subjEnt.area
        $.fn.mycity("ObjShen", "ObjShi", "ObjXian", enterpriseArea);
        $("#ObjShen").attr("disabled", true);
        $("#ObjShi").attr("disabled", true);
        $("#ObjXian").attr("disabled", true);
        $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
        $("#enterpriseModalId").modal("hide");
        $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应
    }

    //提交临时生产经营主体
    var enterpriseAreaId = ""
    $scope.submitEntTemp = function() {
        var rows = $("#entTempGrid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.subjEnt = rows[0];
        $("#enterpriseId").val($scope.subjEnt.id);
        $("#enterpriseName").val($scope.subjEnt.entityName);
        $("#enterpriseAddress").val($scope.subjEnt.address);
        //$("#enterpriseIdcode").val($scope.subjEnt.identityIdcode);
        enterpriseAreaId = $scope.subjEnt.areaId;
        $.fn.mycity("ObjShen", "ObjShi", "ObjXian", enterpriseAreaId);
        $("#ObjShen").attr("disabled", true);
        $("#ObjShi").attr("disabled", true);
        $("#ObjXian").attr("disabled", true);
        $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
        $("#enterpriseModalId").modal("hide");
        $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应

    }

    //重新加载生产经营主体表格
    $scope.querySubjEntList = function() {
        $("#enterpriseGrid").dataTable().api().ajax.reload();
    }
    //重新加载临时生产经营主体表格
    $scope.querySubjEntTempList = function() {
        $("#entTempGrid").dataTable().api().ajax.reload();
    }
    //生产经营主体临时备案
    $scope.gotoAddSubjEntTemp = function() {
        $("#entTempModal").modal("show");
        $("#entityScaleId").prop("checked", "checked");
        $("#entityTypeId").prop("checked", "checked");
        $("#entityIndustryId").prop("checked", "checked");
        $("#credTypes").hide();
    }

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

    //新增生产经营主体临时备案
    $scope.addSubjEntTemp = function() {
        var industryCheck = [];
        var industryNameCheck = [];
        $("input[name='entityIndustryId']:checked").each(function() {
            industryCheck.push($(this).val());
            industryNameCheck.push($(this).next().text());
        });
        if (industryCheck.length == 0) {
            $scope.industryChecked = true;
            return false;
        }
        $scope.industryChecked = false;
        $scope.subjEntTemp.entityIndustryId = industryCheck.toString();
        $scope.subjEntTemp.entityIndustry = industryNameCheck.toString();
        if (!$("#entTemp").data('bootstrapValidator').validate().isValid()) {
            return false;
        } //必须有
        var province = $("#subjTempSheng").val();
        var city = $("#subjTempShi").val();
        var county = $("#subjTempXian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city + "00";
        } else if (province != null && province != undefined && province != "") {
            areaId = province + "0000";
        }
        $scope.subjEntTemp.entityScale = $("input[name='entityScaleId']:checked").next().text();
        $scope.subjEntTemp.entityType = $("input[name='entityTypeId']:checked").next().text();
        //$scope.subjEntTemp.entityIndustry=$("input[name='entityIndustryId']:checked").next().text();
        $scope.subjEntTemp.areaId = areaId;

        $scope.subjEntTemp.businessLicenceimg = vm.businessLicenceimg[0].src;
        $scope.subjEntTemp.organizationCertificateimg = vm.organizationCertificateimg[0].src;
        $scope.subjEntTemp.positiveIdcardeimg = vm.positiveIdcardeimg[0].src;
        $scope.subjEntTemp.negativeIdcardimg = vm.negativeIdcardimg[0].src;
        $scope.subjEntTemp.handIdcardimg = vm.handIdcardimg[0].src;

        if ($scope.subjEntTemp.entityScale == "企业/个体工商户" || $scope.subjEntTemp.entityScale == "合作社") {
            if (!$scope.subjEntTemp.businessLicenceimg) {
                jBox.tip("请上传企业证件照!");
                return false;
            }
            if ($scope.subjEntTemp.credType == "普通营业执照(有独立组织机构代码证)" && !$scope.subjEntTemp.organizationCertificateimg) {
                jBox.tip("请上传组织机构照!");
                return false;
            }
        } else {
            $scope.subjEntTemp.credType = "";
        }
        /* if(!$scope.subjEntTemp.positiveIdcardeimg){
             jBox.tip("请上传身份证正面!");
             return false;
         }
         if(!$scope.subjEntTemp.negativeIdcardimg){
             jBox.tip("请上传身份证反面!");
             return false;
         }
         if(!$scope.subjEntTemp.handIdcardimg){
             jBox.tip("请上传手持身份证!");
             return false;
         }*/

        $("button").attr("disabled", true);
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/subjEnt/addSubjEntTemp",
            data: $scope.subjEntTemp,
            method: "post"
        }).success(function(data) {
            $("button").attr("disabled", false);
            if (data.httpCode == "200") {
                jBox.tip("保存成功", 'info');
                $scope.subjEnt = data.data;
                $("#enterpriseId").val($scope.subjEnt.id);
                $("#enterpriseName").val($scope.subjEnt.entityName);
                $("#enterpriseAddress").val($scope.subjEnt.address);
                //$("#enterpriseIdcode").val($scope.subjEnt.identityCode)
                $.fn.mycity("ObjShen", "ObjShi", "ObjXian", areaId);
                $scope.disableAreaSelectByAreaId("ObjShen", "ObjShi", "ObjXian");
                $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
                $("#entTempListModal").modal("hide");
                $("#entTempModal").modal("hide");
                $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应
            } else {
                jBox.tip(data.msg, 'info');
            }
        }).error(function() {
            $("button").attr("disabled", false);
            jBox.tip("保存失败", 'info');
        }).error(function(data) {
            
        })
    };
    /**
     * 查看地理位置-“地图”
     */
var map, container, content, overlay, view;
    $("#gis")
        .on("shown.bs.modal", function(e) {
            if (!map) {
                //初始化地图
                (container = document.getElementById("info")),
                    (content = document.getElementById("info-content")),
                    (view = new ol.View({
                        center: [116.402, 39.905],
                        zoom: 14,
                        minZoom:2,
                        projection: "EPSG:4326"
                    }));
                map = new ol.Map({
                    target: "mapDiv",
                    controls: ol.control.defaults({
                        attributionOptions: { collapsed: true }
                    }),
                    view: view,
                    layers: [
                        new ol.layer.Tile({
                            source: new ol.source.TileSuperMapRest({
                                url:
                                    "http://www.bg.qsst.moa.gov.cn/iserver/services/map-China100/rest/maps/China_4326",
                                // projection: "EPSG:4326",
                                origin: [-180.0, 90]
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.SuperMapCloud({
                                layerType: "vec",
                                // projection: "EPSG:4326"
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.SuperMapCloud({
                                layerType: "vec",
                                // projection: "EPSG:4326",
                                isLabel: true
                            })
                        })
                    ]
                });
            }
            overlay = new ol.Overlay({
                element: container,
                autoPan: true,
                autoPanAnimation: {
                    duration: 250
                },
                offset: [0, -20]
            });
            map.getOverlays().clear();
            map.addOverlay(overlay);
            if (
                $scope.subjEnt.longitude == null ||
                $scope.subjEnt.latitude == null
            ) {
                swal("系统提示", "获取该主体经纬度信息失败！", "warning");
                return;
            }
            var point = [$scope.subjEnt.longitude, $scope.subjEnt.latitude];
            var vectorSource = new ol.source.Vector();
            var imgUrl =
                "http://iclient.supermap.io/examples/img/markerbig_select.png";

            var vectorLayer = new ol.layer.Vector({
                source: vectorSource
            });
            map.addLayer(vectorLayer);

            //改变地图中心点
            view.animate({ zoom: 14 }, { center: point });
            var feature = new ol.Feature();

            //主体信息框
            var select = new ol.interaction.Select();
            select.on("select", function(e) {
                if (e.selected.length > 0) {
                    var feature = e.selected[0];
                    var sContent =
                        "<div><p>主体名称：" +
                        $scope.subjEnt.enterpriseName +
                        "</p><p>经纬度：" +
                        $scope.subjEnt.longitude +
                        "," +
                        $scope.subjEnt.latitude +
                        "</p></div>\n";
                    content.innerHTML = sContent;
                    info.className = "ol-popup";
                    overlay.setPosition(feature.getGeometry().getCoordinates());
                } else if (overlay) {
                    overlay.setPosition(undefined);
                }
            });

            //主体定位点
            feature.setGeometry(new ol.geom.Point(point));
            feature.setStyle(
                new ol.style.Style({
                    image: new ol.style.Icon({
                        anchor: [0.5, 1],
                        src: imgUrl
                    })
                })
            );
            vectorSource.clear();
            map.removeInteraction(select);
            vectorSource.addFeatures([feature]);
            map.addInteraction(select);
        })
        .on("hidden.bs.modal", function() {
            map.removeOverlay(overlay);
        });
    //modal 地图展示
    $scope.gis = function() {
        if (
            $scope.subjEnt == null ||
            $scope.subjEnt == "" ||
            $scope.subjEnt == undefined
        ) {
            jBox.tip("请先选择生产经营主体！", "info");
            return false;
        }
        if (
            $scope.subjEnt.longitude == null ||
            $scope.subjEnt.longitude == "" ||
            $scope.subjEnt.longitude == undefined ||
            $scope.subjEnt.latitude == null ||
            $scope.subjEnt.latitude == "" ||
            $scope.subjEnt.latitude == undefined
        ) {
            jBox.tip("该生产经营主体没有标记地理位置！", "info");
            return false;
        }
        $("#gis").modal("show");
    };


    //判断是否有企业证件照,组织机构代码照
    $scope.selectEntityScale = function(entityScale) {
        if (entityScale == "企业/个体工商户" || entityScale == "合作社") {
            $("#businessHead").show();
            $("#businessContent").show();
            $("#organizationCode").show();
            $("#credType").show();
            $("#credTypes").hide();
        } else {
            $("#businessHead").hide();
            $("#businessContent").hide();
            $("#organizationCode").hide();
            $("#credType").hide();
            $("#credTypes").show();
            vm.businessLicenceimg = [{
                vname: '企业证件照',
            }]
            vm.organizationCertificateimg = [{
                vname: '组织机构照',
            }]
        }
    };

    //判断组织机构代码照是否显示
    $scope.selectBusiness = function() {
        if ($scope.subjEntTemp.credType == "普通营业执照(有独立组织机构代码证)") {
            $("#selectOrganizationCertificateimg").show();
            $("#organizationCode").show();
        } else {
            $("#selectOrganizationCertificateimg").hide();
            $("#organizationCode").hide();
            vm.organizationCertificateimg = [{
                vname: '组织机构照',
            }]
        }
};

});
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}