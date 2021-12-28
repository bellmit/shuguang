var myapp = angular.module("myapp", []);
myapp.controller("inspectionUpdate", function($scope, $http, $state) {
    $scope.taskType = {};
    $scope.taskTypeTouched = false;
    var vm = this;
    /** 第一部分，初始化时加载的内容 **/
    $scope.industryChecked = false; //默认所属行业未选中
    //获取本地存储
    $scope.baseInspectionId = window.localStorage.getItem("baseInspectionId");
    $scope.baseInspection = {};
    $("#updateInspectionForm").bootstrapValidator(); //必须有
    $("#entTemp").bootstrapValidator(); //必须有
    $scope.subjEntTemp = {};
    // 巡查人员选择列表
    $scope.selects = [];

    //限制区域是否可选
    $scope.disableAreaSelectByAreaId = function(provinceId, cityId, countyId) {
        if (
            $("#" + provinceId).val() != null &&
            $("#" + provinceId).val() != "" &&
            $("#" + provinceId).val() != undefined
        ) {
            $("#" + provinceId).attr("disabled", true);
        }
        if (
            $("#" + cityId).val() != null &&
            $("#" + cityId).val() != "" &&
            $("#" + cityId).val() != undefined
        ) {
            $("#" + cityId).attr("disabled", true);
        }
        if (
            $("#" + countyId).val() != null &&
            $("#" + countyId).val() != "" &&
            $("#" + countyId).val() != undefined
        ) {
            $("#" + countyId).attr("disabled", true);
        }
    };
    //从缓存从获取数据
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
        $scope.organization = $scope.user.organization;
        $scope.baseInspection.inspectionSvId = $scope.organization.orgId;
        $scope.baseInspection.inspectionSvName = $scope.organization.orgName;
        $scope.baseInspection.inspectionUserName =
            $scope.user.userName + "##" + $scope.user.id;
        $("input[name='inspectionUserName']").val($scope.user.userName);
        //获取的用户id name 放入到selects中
        $scope.selects.push({
            id: $scope.user.id,
            userName: $scope.user.userName
        });
        //初始化区域下拉框初始选项
        //需要限定为所辖区域
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(
            0,
            2 * ($scope.organization.orgLevel - 1)
        );
        $("input[name='inspectionSvName']").attr("readonly", "readonly"); //不让选巡查机构
        $scope.subjEntTemp.credType = "普通营业执照(有独立组织机构代码证)"; //默认选中普通执照
        //获取主体类别数据字典
        $scope.subjType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["subjType"];
        //获取临时主体类别数据字典
        $scope.subjEntTempType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["linshizhutileixing"];
        $scope.subjEntTemp.entityTypeId = $scope.subjEntTempType[0].id;
        //获取组织形式数据字典
        $scope.orgMode = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["orgMode"];
        $scope.subjEntTemp.entityScaleId = $scope.orgMode[0].id;
        //获取行业数据字典
        $scope.industryType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["industryType"];
        $scope.subjEntTemp.entityIndustryId = $scope.industryType[0].id;
        //所属区域
        $scope.underArea = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["underArea"];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["allIndustryId"];
        //巡查结果
        $scope.inspectionResults = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["patrolResult"];
        //获取常用意见
        $scope.commonOpinion = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["commonOpinion"];
        //获取用户自定义常用意见
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "29",
            method: "post"
        })
            .success(function(data) {
                //获取用户自定义常用意见
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-asms-web/baseInspection/getOpinionListByUserId",
                    data: "29",
                    method: "post"
                }).success(function(userData) {
                    $scope.commonOpinion = JSON.parse(data.data);
                    for (var i = 0; i < userData.data.length; i++) {
                        $scope.commonOpinion.push(userData.data[i]);
                    }
                });
                //加载人员选择数据
                loadPersonGrid();
            })
            .error(function(data) {
                jBox.tip("保存失败", "info");
            });
    } else {

    }

    //获取基地信息
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/baseInspection/findBaseInspectionById",
        method: "post",
        data: { id: $scope.baseInspectionId }
    })
        .success(function(data) {
            $scope.baseInspection = data.data;
            $("input[name='inspectionUserName']").val(
                data.data.inspectionUserName
            );
            $("input[name='inspectionUserName']").attr(
                "title",
                data.data.inspectionUserName
            );
            $("input[name='enterpriseId']").val(data.data.enterpriseId);
            $("input[name='date']").val(
                new Date(parseInt(data.data.inspectionTime) + 28800000)
                    .toISOString()
                    .slice(0, 10)
            );
            if ($scope.baseInspection.status == "1") {
                $("#updateBaseInspectionDraftBatch").show();
            }
            //初始文件
            vm.files = [];
            if (
                $scope.baseInspection.inspectionImages != null &&
                $scope.baseInspection.inspectionImages != undefined
            ) {
                var array = [];
                if (
                    $scope.baseInspection.inspectionImages != null &&
                    $scope.baseInspection.inspectionImages != undefined &&
                    $scope.baseInspection.inspectionImages != ""
                ) {
                    array = $scope.baseInspection.inspectionImages.split(
                        "*##*"
                    );
                }
                var arrayB = [];
                if (
                    $scope.baseInspection.inspectionImagesName != null &&
                    $scope.baseInspection.inspectionImagesName != undefined &&
                    $scope.baseInspection.inspectionImagesName != ""
                ) {
                    arrayB = $scope.baseInspection.inspectionImagesName.split(
                        "*##*"
                    );
                }
                vm.files = array.map(function(item, i) {
                    return {
                        src: array[i],
                        name: arrayB[i] && arrayB[i] != "null" ?
                            arrayB[i] :
                            "现场照片" + (i + 1) + ".jpg"
                    };
                });
            }
            // 处理签字照片图片显示
            if ($scope.baseInspection.headSignFile) {
                if (
                    $scope.baseInspection.headSignFileName &&
                    $scope.baseInspection.headSignFileName != "null"
                ) {
                    $scope.baseInspection.headSignFileName = "签字照片.jpg";
                }
            } else {
                $scope.baseInspection.headSignFileName = null;
            }
            vm.headSignFile = [
                {
                    src: $scope.baseInspection.headSignFile,
                    name: $scope.baseInspection.headSignFileName
                }
            ];
            //视频回显
            vm.video = [{
                src: $scope.baseInspection.videoUrl,
                name: $scope.baseInspection.videoName
            }];
            //任务类型的回显
            data.data.inspectionType.split(",").map(function(type) {
                $("input[name = 'inspectionTypej'][value=" + type + "]").prop(
                    "checked",
                    true
                );
            });

            //计划名称回显
            $scope.planName = $scope.baseInspection.planName;
            $("input[name='inspectionSvName']").attr("readonly", "readonly"); //不让选巡查机构
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/subjEnt/findSubjEntById",
                method: "post",
                data: { id: data.data.enterpriseId }
            })
                .success(function(dataEnterprise) {
                    if (
                        dataEnterprise.data == null ||
                        dataEnterprise.data == "" ||
                        dataEnterprise.data == undefined
                    ) {
                        $http({
                            headers: {
                                token: window.localStorage.getItem("token")
                            },
                            url: "/sofn-asms-web/subjEnt/findSubjEntTempById",
                            method: "post",
                            data: { id: data.data.enterpriseId }
                        })
                            .success(function(dataSubjEntTemp) {
                                $scope.subjEnt = dataSubjEntTemp.data;
                                $scope.subjEnt.enterpriseName =
                                    dataSubjEntTemp.data.entityName;
                                $scope.subjEnt.entityTypeName =
                                    dataSubjEntTemp.data.entityType;
                                $scope.subjEnt.entityScaleName =
                                    dataSubjEntTemp.data.entityScale;
                                $scope.subjEnt.entityIndustryName =
                                    dataSubjEntTemp.data.entityIndustry;
                                $scope.baseInspection.enterpriseName =
                                    $scope.subjEnt.enterpriseName;
                                $scope.baseInspection.entityIdcode =
                                    $scope.subjEnt.entityIdcode;
                                $("#frdb").hide(); //临时主体，隐藏法人
                                $("#dbdh").hide(); //临时主体，隐藏法人电话
                            })
                            .error(function() {});
                    } else {
                        $scope.subjEnt = dataEnterprise.data;
                        $scope.baseInspection.enterpriseName =
                            $scope.subjEnt.enterpriseName;
                        $scope.baseInspection.entityIdcode =
                            $scope.subjEnt.entityIdcode;
                        $("#frdb").show(); //主体，显示法人
                        $("#dbdh").show(); //主体，显示法人电话
                    }
                })
                .error(function() {});
        })
        .error(function(data) {

        });

    /** 第二部分，主动调用才执行的部分 **/
    //限制区域是否可选
    $scope.disableAreaSelectByAreaId = function(provinceId, cityId, countyId) {
        if (
            $("#" + provinceId).val() != null &&
            $("#" + provinceId).val() != "" &&
            $("#" + provinceId).val() != undefined
        ) {
            $("#" + provinceId).attr("disabled", true);
        }
        if (
            $("#" + cityId).val() != null &&
            $("#" + cityId).val() != "" &&
            $("#" + cityId).val() != undefined
        ) {
            $("#" + cityId).attr("disabled", true);
        }
        if (
            $("#" + countyId).val() != null &&
            $("#" + countyId).val() != "" &&
            $("#" + countyId).val() != undefined
        ) {
            $("#" + countyId).attr("disabled", true);
        }
    };
    //选择下拉常用意见改变巡查意见
    window.selectCommonOpinion = function() {
        $scope.baseInspection.inspectionView = $(
            "select[name='commonOpinion'] option:selected"
        ).attr("dictVal");
        $("textarea[name='inspectionView']").val(
            $scope.baseInspection.inspectionView
        );
    };

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: "/sofn-asms-web/asmsFile/upload",
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: "01" };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 5,
        filter: function(file, info) {
            // var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal("", "只能上传图片", "warning");
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal("", "文件大小不能超过5MB", "warning");
                return false;
            }
            return true;
        }
    };

    vm.uploadHeadOptions = {
        //接口地址
        url: "/sofn-asms-web/asmsFile/upload",
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: "01" };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (!imageTypes.test(file.type)) {
                swal("", "只能上传图片", "warning");
                return false;
            }
            if (file.size > 5 * 1024 * 1024) {
                swal("", "文件大小不能超过5MB", "warning");
                return false;
            }
            return true;
        }
    };

    //视频上传
    vm.uploadVideoOptions = {
        //接口地址
        url: '/sofn-asms-web/asmsFile/upload',
        error: function(msg) {

        },
        accept:'video/*',
        //业务参数
        data: function() {
            return { time: +new Date(), typeId: '01' };
        },
        //初始视频上传数量
        min: 1,
        //最大上传视频数量  avi/mp4/mov/
        max: 1,
        filter: function(file, info) {
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

    window.clearValidate = function() {
        $("#updateInspectionForm")
            .data("bootstrapValidator")
            .destroy(); //先去掉验证，与下面对应
        $("#updateInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
    };

    //修改基地巡查信息
    $scope.updateBaseInspection = function(status) {
        //状态为1保存草稿,其它保存为已上传状态
        $scope.baseInspection.status = status;
        //取巡查照片，并判断是否有至少一张
        $scope.baseInspection.inspectionImages = ""; //如果未定义，则先定义，未清空，则先清空
        $scope.baseInspection.inspectionImagesName = "";
        for (var i = 0; i < vm.files.length; i++) {
            if (i == vm.files.length - 1) {
                $scope.baseInspection.inspectionImages =
                    $scope.baseInspection.inspectionImages + vm.files[i].src;
                $scope.baseInspection.inspectionImagesName =
                    $scope.baseInspection.inspectionImagesName +
                    vm.files[i].name;
            } else {
                $scope.baseInspection.inspectionImages =
                    $scope.baseInspection.inspectionImages +
                    vm.files[i].src +
                    "*##*";
                $scope.baseInspection.inspectionImagesName =
                    $scope.baseInspection.inspectionImagesName +
                    vm.files[i].name +
                    "*##*";
            }
        }
        if (
            $scope.baseInspection.inspectionImages == null ||
            $scope.baseInspection.inspectionImages == "" ||
            $scope.baseInspection.inspectionImages == undefined
        ) {
            jBox.tip("现场照片请至少上传一张！", "info");
            return false;
        }
        //负责人签字照片，非必填项
        $scope.baseInspection.headSignFile = ""; //如果未定义，则先定义，未清空，则先清空
        $scope.baseInspection.headSignFileName = "";
        for (var i = 0; i < vm.headSignFile.length; i++) {
            $scope.baseInspection.headSignFile = vm.headSignFile[i].src;
            $scope.baseInspection.headSignFileName = vm.headSignFile[i].name;
        }
        //视频上传
        $scope.baseInspection.videoUrl = ""; //如果未定义，则先定义，未清空，则先清空
        $scope.baseInspection.videoName = "";
        if (vm.video && vm.video.length>0) {
            $scope.baseInspection.videoUrl = vm.video[0].src;
            $scope.baseInspection.videoName = vm.video[0].name;
        }
        $scope.baseInspection.inspectionTime = new Date(
            ($("input[name='date']").val() + " 00:00:00").replace(/-/g, "/")
        );
        $scope.baseInspection.enterpriseId = $("#enterpriseId").val();
        $scope.taskTypeTouched = true;
        var types = [];
        var typesName = [];
        $('[name="inspectionTypej"]:checked').each(function() {
            types.push($(this).val());
        });
        var type = "";
        for (var i = 0; i < types.length; i++) {
            if (types[i] != undefined) {
                if (type != "") {
                    type += "," + types[i];
                } else {
                    type = types[i];
                }
            }
        }
        $('[name="inspectionTypej"]:checked').each(function() {
            typesName.push(
                $(this)
                    .next("span")
                    .text()
            );
        });
        var typeName = "";
        for (var i = 0; i < typesName.length; i++) {
            if (typesName[i] != undefined) {
                if (typeName != "") {
                    typeName += "," + typesName[i];
                } else {
                    typeName = typesName[i];
                }
            }
        }
        if (types.length == 0) {
            jBox.tip("至少选择一个任务类型!", "info");
            return false;
        }
        if (!$("#updateInspectionForm")
            .data("bootstrapValidator")
            .validate()
            .isValid()
        ) {
            return false;
        } //必须有
        $scope.baseInspection.inspectionType = type;
        $scope.baseInspection.inspectionTypeName = typeName;
        //巡查计划
        $scope.baseInspection.planId = $scope.planId;
        $scope.baseInspection.planName = $scope.planName;
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/baseInspection/updateBaseInspection",
            data: $scope.baseInspection,
            params: {
                saveCommonOpinion: $("#saveCommonOpinion").is(":checked")
            },
            method: "post"
        })
            .success(function(data) {
                if (data.httpCode == "200") {
                    jBox.tip("保存成功", "info");
                    $state.go(
                        "index.content.asms/baseInspection/inspectionList"
                    );
                } else {
                    jBox.tip(data.msg, "info");
                }
            })
            .error(function(data) {

            });
    };

    //清除缓存
    $scope.removeLocalStorage = function() {
        window.localStorage.removeItem("baseInspectionId");
        $state.go("index.content.asms/baseInspection/inspectionList");
    };

    /**
     * ---------人员选择列表--------------
     * */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects = new Array();

    $("#selectModal").modal({
        show: false,
        backdrop: "static",
        keyboard: false
    });
    $scope.selectPerson = function() {
        $("#selectGrid")
            .dataTable()
            .api()
            .ajax.reload();
        $("#selectModal").modal("show");
    };

    //确定
    $scope.selectModalQdButt = function() {
        var userName = "";
        var userId = "";
        for (var i = 0; i < $scope.selects.length; i++) {
            if ($scope.selects[i] != undefined) {
                if (userName != "") {
                    userName += "," + $scope.selects[i].userName;
                    userId += "," + $scope.selects[i].id;
                } else {
                    userName = $scope.selects[i].userName;
                    userId = $scope.selects[i].id;
                }
            }
        }
        $("input[name='inspectionUserName']").val(userName);
        $("input[name='inspectionUserName']").attr("title", userName);
        $scope.baseInspection.inspectionUserName = userName + "##" + userId;
        $("#selectModal").modal("hide");
        $("#updateInspectionForm")
            .data("bootstrapValidator")
            .destroy(); //先去掉验证，与下面对应
        $("#updateInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
    };

    function loadPersonGrid() {
        $("#selectGrid").dataTable({
            fnDrawCallback: function() {
                //判断是否全选
                $("#checkAll").prop(
                    "checked",
                    $("input[name='ck']").length ==
                    $("input[name='ck']:checked").length &&
                    $("input[name='ck']").length > 0
                );
                //点击行内任意内容即选择行
                $("#selectGrid tbody tr td").each(function(i, o) {
                    $(o).click(function() {
                        var checked = $(this)
                            .parent()
                            .find("input[type='checkbox']")[0];
                        if (checked.checked) {
                            checked.checked = false;
                            $(this)
                                .parent()
                                .removeClass("selected");
                            //人员移除
                            var u_d = $(this)
                                .parent()
                                .find("[name='ck']")
                                .val();
                            for (var i = 0; i < $scope.selects.length; i++) {
                                if (
                                    $scope.selects[i] != undefined &&
                                    u_d == $scope.selects[i].id
                                ) {
                                    delete $scope.selects[i];
                                }
                            }
                        } else {
                            checked.checked = true;
                            $(this)
                                .parent()
                                .addClass("selected");
                            //人员选择记录
                            var map = {
                                id: $(this)
                                    .parent()
                                    .find("[name='ck']")
                                    .val(),
                                userName: $(this)
                                    .parent()
                                    .find("[name='userName']")
                                    .html()
                            };
                            $scope.selects.push(map);
                        }
                        //判断是否全选
                        $("#checkAll").prop(
                            "checked",
                            $("input[name='ck']").length ==
                            $("input[name='ck']:checked").length &&
                            $("input[name='ck']").length > 0
                        );
                    });
                });
            },
            ajax: {
                url: "/sofn-asms-web/baseInspection/getSysUserListByOrgId",
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.token = window.localStorage.getItem("token");
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    params.orgId = $scope.baseInspection.inspectionSvId;
                    params.userName = $("input[name='selectQueryName']").val();
                },
                type: "post"
            },
            columns: [{
                data: "id",
                title: '<input id="checkAll" onclick="checkAll(this.checked)" type=checkbox>',
                render: function(data, type, row) {
                    for (var i = 0; i < $scope.selects.length; i++) {
                        if (
                            $scope.selects[i] != undefined &&
                            data == $scope.selects[i].id
                        ) {
                            return (
                                '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' +
                                data +
                                '"/>'
                            );
                        }
                    }
                    return (
                        '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="' +
                        data +
                        '"/>'
                    );
                }
            },
                {
                    data: "id",
                    title: "ID",
                    visible: false
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    }
                },
                {
                    data: "userName",
                    title: "姓名",
                    render: function(data, type, row) {
                        return '<span name = "userName">' + data + "</span>";
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
            language: {
                sProcessing: "处理中...",
                sLengthMenu: "显示 _MENU_ 项结果",
                sZeroRecords: "没有匹配结果",
                sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项结果，共 项",
                sInfoFiltered: "(由 _MAX_ 项结果过滤)",
                sInfoPostFix: "",
                sSearch: "搜索:",
                sUrl: "",
                sEmptyTable: "表中数据为空",
                sLoadingRecords: "载入中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNext: "下页",
                    sLast: "末页"
                },
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列"
                }
            },
            sPaginationType: "full_numbers"
        });
    }

    $("#selectTocheck").click(function() {
        $("#selectGrid")
            .dataTable()
            .api()
            .ajax.reload();
    });

    //针对复选框无法选中bug
    window.checkSelectThisBox = function(o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };
    //全选,全不选
    window.checkAll = function(checked) {
        $("input[name='ck']").each(function(i, o) {
            $(o).prop("checked", checked);
            //本页人员全部移除
            var u_d = $(o).val();
            for (var i = 0; i < $scope.selects.length; i++) {
                if (
                    $scope.selects[i] != undefined &&
                    u_d == $scope.selects[i].id
                ) {
                    delete $scope.selects[i];
                }
            }
        });
        //添加本页全部人员
        if (checked) {
            $("input[name='ck']:checked").each(function(i, o) {
                var map = {
                    id: $(o).val(),
                    userName: $(o)
                        .parent()
                        .parent()
                        .find("[name='userName']")
                        .html()
                };
                $scope.selects.push(map);
            });
        }
    };
    //查询条件重置
    $scope.cleanSelectModelQuery = function() {
        $("input[name='selectQueryName']").val("");
    };

    //清空已选择
    $scope.cleanSelected = function() {
        $scope.selects = null;
        $scope.selects = new Array();
        $("#selectGrid")
            .dataTable()
            .api()
            .ajax.reload();
    };

    /**
     * 地图
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
                        minZoom: 2,
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

    //巡查计划-模态框
    $scope.selectPlanGrid = function() {
        $scope.loadPlanGrid();
        $("#planModal").modal("show");
    };

    //巡查类型数据字典
    var statusDict = [{
        name: "未开始",
        value: "0"
    }, {
        name: "进行中",
        value: "1"
    }, {
        name: "已结束,已完成",
        value: "2"
    }, {
        name: "已结束,未完成",
        value: "3"
    }]
    var taskTypeDict = [{
        name: "基地",
        value: "1"
    }, {
        name: "主体",
        value: "2"
    }, {
        name: "农资",
        value: "3"
    }, {
        name: "其它",
        value: "0"
    }];
    //巡查计划列表
    $scope.loadPlanGrid = function () {
        $("#planGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/inspectionPlan/getInspPlanList",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.start = json.data.start;//起始位置
                    json.length = json.data.length;
                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    // 筛选进行中的巡查计划
                    params.status = 1;
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '选择',
                    render: function (data, type, row) {
                        return '<input name="planId" id="' + row.id + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.id + '"/>';
                    },
                    width: "3%"
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "planName",
                    title: "巡查计划",
                    width: "15%"
                },
                {
                    data: "taskName",
                    title: "考核任务",
                    width: "15%"
                },
                {
                    data: "areaId",
                    title: "巡查区域",
                    width: "10%",
                    render: function (data) {
                        if (data) {
                            return $.fn.Cityname(data);
                        }
                    }
                },
                {
                    data: "planStartDate",
                    title: "开始时间",
                    width: "6%",
                    render: function (data) {
                        if (data) {
                            return new Date(parseInt(data) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data: "planStartDate",
                    title: "结束时间",
                    width: "6%",
                    render: function (data) {
                        if (data) {
                            return new Date(parseInt(data) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data: "taskType",
                    title: "任务类型",
                    width: "8%",
                    render: function (data) {
                        if (!data) {
                            return;
                        }
                        var resArr = [];
                        $.each(data.split(","), function (i, o) {
                            $.each(taskTypeDict, function (j, p) {
                                if (o == p.value) {
                                    resArr.push(p.name);
                                }
                            })
                        })
                        return resArr.join(",");
                    }
                },
                {
                    data: "status",
                    title: "任务状态",
                    width: "5%",
                    render: function (data) {
                        //未发布
                        if(data == 0){
                            return '<span style="color:#1f934b;font-weight: bold;">未开始</span>';
                        }
                        //执行中
                        if(data == 1){
                            return '<span style="color:#333333;font-weight: bold;">执行中</span>';
                        }
                        //已结束,已完成
                        if(data == 2){
                            return '<span style="color:#bfbfbf;font-weight: bold;">已结束,已完成</span>';
                        }
                        //已结束,未完成
                        if(data == 3){
                            return '<span style="color:#ff0d06;font-weight: bold;">已结束,未完成</span>';
                        }
                    }
                }
            ],
            fnDrawCallback: function() {
                //设置选中状态
                $("#planGrid >tbody >tr").click(function() {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='planId']").removeAttr("checked");
                        $(this).removeClass('selected');
                    } else {
                        $('#planGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });
    }

    //提交巡查计划
    $scope.submitPlan = function () {
        var rows = $("#planGrid").DataTable().rows('.selected').data();
        if (rows.length != 1) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.planId = rows[0].id;
        $scope.planName = rows[0].planName;
        $("#planName").val($scope.planName);
        $('#planModal').modal('hide');
    }
});