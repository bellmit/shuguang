    var myapp = angular.module("myapp", []);
    myapp.controller("inspectionAdd", function($scope, $http, $state) {
        /** 第一部分，初始化时加载的内容 **/
        $scope.taskType = {};
        //定义一个人员选择的容器  储存用户 id userName
        $scope.selects = [];
        $scope.taskTypeTouched = false;
        $scope.industryChecked = false; //默认所属行业未选中
        $('#baseInspectionForm').bootstrapValidator(); //必须有
        $('#entTemp').bootstrapValidator(); //必须有
        $scope.baseInspection = {};
        $scope.baseInspection.inspectionType = 1; //任务类型默认为基地
        $scope.baseInspection.inspectionResult = 1; //巡查结果默认为合格
        $scope.inspectionTime = new Date(new Date().getTime() + 28800000).toISOString().slice(0, 10); //巡查时间默认为当日
        $scope.subjEntTemp = {};
        $("#enterpriseName").attr("readonly", "readonly"); //禁止修改企业名称
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
        //从缓存从获取数据
        if (window.localStorage.getItem("asmsAllSysDictAndUserInfo")) {
            //获取用户信息
            $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
            $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
            $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
            $scope.organization = $scope.user.organization;
            $scope.baseInspection.inspectionSvId = $scope.organization.orgId;
            $scope.baseInspection.inspectionSvName = $scope.organization.orgName;
            $scope.baseInspection.inspectionUserName = $scope.user.userName + "##" + $scope.user.id;
            $("input[name='inspectionUserName']").val($scope.user.userName);
            //获取的用户id name 放入到selects中
            $scope.selects.push({
                id: $scope.user.id,
                userName: $scope.user.userName
            });
            //初始化区域下拉框初始选项
            //需要限定为所辖区域
            //定义一个管辖区域，通过机构级别+区域来判断
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea); //新增临时生产经营主体
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.underArea); //正式生产经营主体查询
            $.fn.mycity("queryTempSheng", "queryTempShi", "queryTempXian", $scope.underArea); //临时生产经营主体查询
            $scope.disableAreaSelectByAreaId("sheng", "shi", "xian");
            $scope.disableAreaSelectByAreaId("querySheng", "queryShi", "queryXian");
            $scope.disableAreaSelectByAreaId("queryTempSheng", "queryTempShi", "queryTempXian");
            $("input[name='inspectionSvName']").attr("readonly", "readonly"); //不让选巡查机构
            $scope.subjEntTemp.credType = "普通营业执照(有独立组织机构代码证)"; //默认选中普通执照
            //获取主体类别数据字典
            $scope.subjType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['subjType'];
            //获取临时主体类别数据字典
            $scope.subjEntTempType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['linshizhutileixing'];
            $scope.subjEntTemp.entityTypeId = $scope.subjEntTempType[0].id;
            //获取组织形式数据字典
            $scope.orgMode = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['orgMode'];
            $scope.subjEntTemp.entityScaleId = $scope.orgMode[0].id;
            //获取行业数据字典,移除全部选项
            $scope.industryType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['industryType'];
            for (var i = 0; i < $scope.industryType.length; i++) {
                if ($scope.industryType[i].dictName && $scope.industryType[i].dictName.trim() == "全部") {
                    $scope.industryType.splice(i, 1);
                }
            }
            $scope.subjEntTemp.entityIndustryId = $scope.industryType[0] && $scope.industryType[0].id;
            //所属区域
            $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
            //全部拥有的行业
            $scope.allIndustryId = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['allIndustryId'];
            //巡查结果
            $scope.inspectionResults = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['patrolResult'];
            //获取常用意见
            $scope.commonOpinion = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['commonOpinion'];
            //获取用户自定义常用意见
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/sysDict/getSysDictByCode",
                data: "29",
                method: "post"
            }).success(function(data) {
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
                    if ($scope.commonOpinion[0] != null && $scope.commonOpinion[0] != "" && $scope.commonOpinion[0] != undefined) {
                        $scope.baseInspection.inspectionView = $scope.commonOpinion[0].dictValue;
                    }
                })
                loadEntGrid();
                //加载人员选择数据
                loadPersonGrid();
            }).error(function(data) {
                if (!data) {
                    swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
                    setTimeout(function() {
                        window.location.href = '#/login/login';
                        window.location.reload();
                    }, 2500)
                }
            });
        } else {
            swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
            setTimeout(function() {
                window.location.href = '#/login/login';
                window.location.reload();
            }, 2500)
        }


        /** 第二部分，主动调用才执行的部分 **/
        //选择下拉常用意见改变巡查意见
        window.selectCommonOpinion = function() {
            $scope.baseInspection.inspectionView = $("select[name='commonOpinion'] option:selected").attr("dictVal");
            $("textarea[name='inspectionView']").val($scope.baseInspection.inspectionView);
        }

        //新增基地巡查
        $scope.addBaseInspection = function(status) {
            //状态为1保存草稿,其它保存为已上传状态
            $scope.baseInspection.status = status;
            //取巡查照片，并判断是否有至少一张
            $scope.baseInspection.inspectionImages = ""; //如果未定义，则先定义，未清空，则先清空
            $scope.baseInspection.inspectionImagesName = "";
            $scope.baseInspection.enterpriseName = $scope.subjEnt.enterpriseName;
            $scope.baseInspection.userIdcode = $scope.subjEnt.userIdcode;
            for (var i = 0; i < vm.files.length; i++) {
                if (i == vm.files.length - 1) {
                    $scope.baseInspection.inspectionImages = $scope.baseInspection.inspectionImages + vm.files[i].src;
                    $scope.baseInspection.inspectionImagesName = $scope.baseInspection.inspectionImagesName + vm.files[i].name;
                } else {
                    $scope.baseInspection.inspectionImages = $scope.baseInspection.inspectionImages + vm.files[i].src + "*##*";
                    $scope.baseInspection.inspectionImagesName = $scope.baseInspection.inspectionImagesName + vm.files[i].name + "*##*";
                }
            }
            if ($scope.baseInspection.inspectionImages == null || $scope.baseInspection.inspectionImages == "" || $scope.baseInspection.inspectionImages == undefined) {
                jBox.tip("现场照片请至少上传一张！", 'info');
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
            //任务类型必填判断
            $scope.taskTypeTouched = true;
            var types = [];
            var typesName = [];
            $('[name="inspectionTypej"]:checked').each(function() {
                types.push($(this).val());
            });
            var type = "";
            for (var i = 0; i < types.length; i++) {
                //if(inspectionTypeobj[i].checked) type+=inspectionTypeobj[i].value+","; //如果选中，将value添加到变量s中
                if (types[i] != undefined) {
                    if (type != "") {
                        type += "," + types[i];
                    } else {
                        type = types[i];
                    }
                }
            }
            $('[name="inspectionTypej"]:checked').each(function() {
                typesName.push($(this).next("span").text())
            });
            var typeName = "";
            for (var i = 0; i < typesName.length; i++) {
                if (typesName[i] != undefined) {
                    if (typeName != "") {
                        typeName += "," + typesName[i]
                    } else {
                        typeName = typesName[i]
                    }
                }
            }
            if (types.length == 0) {
                jBox.tip("至少选择一个任务类型!", 'info');
                return false;
            }
            if (!$("#baseInspectionForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            } //必须有
            $scope.baseInspection.inspectionTime = new Date(($("input[name='date']").val() + " 00:00:00").replace(/-/g, "/"));
            $scope.baseInspection.elCheckState = "0";
            $scope.baseInspection.enterpriseId = $("#enterpriseId").val();
            $scope.baseInspection.inspectionType = type;
            $scope.baseInspection.inspectionTypeName = typeName;
            //巡查计划
            $scope.baseInspection.planId = $scope.planId;
            $scope.baseInspection.planName = $scope.planName;
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/baseInspection/addBaseInspection",
                data: $scope.baseInspection,
                params: { "saveCommonOpinion": $("#saveCommonOpinion").is(":checked") },
                method: "post"
            }).success(function(data) {
                $("button").attr("disabled", false);
                if (data.httpCode == "200") {
                    jBox.tip("保存成功", 'info');
                    $state.go("index.content.asms/baseInspection/inspectionList");
                } else if (data.httpCode == 400) {
                    jBox.tip(data.msg, 'info');
                } else {
                    jBox.tip(data.msg, 'info');
                }
            }).error(function(data) {
                
            })
        };
        //清除缓存
        $scope.removeLocalStorage = function() {
            window.localStorage.removeItem("baseInspectionId");
            $state.go("index.content.asms/baseInspection/inspectionList");
        }

        window.clearValidate = function() {
            $("#baseInspectionForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
            $("#baseInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
        }

        var vm = this;

        //临时主体上传照片默认显示
        vm.businessLicenceimg = [{
            vname: '企业证件照',
        }]
        vm.organizationCertificateimg = [{
            vname: '组织机构照',
        }]
        vm.positiveIdcardeimg = [{
            vname: '身份证正面',
            picture: 'images/dict/id_z.png'
        }]
        vm.negativeIdcardimg = [{
            vname: '身份证反面',
            picture: 'images/dict/id_f.png'
        }]
        vm.handIdcardimg = [{
            vname: '手持身份证',
            picture: 'images/dict/id_s.png'
        }]


        //文件上传配置
        vm.uploadOptions = {
            //接口地址
            url: '/sofn-asms-web/asmsFile/upload',
            //业务参数
            data: function() {
                return { time: +new Date(), typeId: '01' };
            },
            //初始文件数量
            min: 1,
            //最大文件数量
            max: 5,
            filter: function(file, info) {
                // var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
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

        vm.uploadHeadOptions = {
            //接口地址
            url: '/sofn-asms-web/asmsFile/upload',
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

        //生产经营主体临时备案
        $scope.gotoAddSubjEntTemp = function() {
            if (!$scope.subjEntTemp.entityIndustryId) {
                $.jBox.tip("该账号没有行业权限!");
                return;
            }
            $("#entTempModal").modal("show");
            $("input[name='entityScaleId']:first").attr("checked", "checked");
            $("input[name='entityTypeId']:first").attr("checked", "checked");
            $("input[name='entityIndustryId']:first").attr("checked", "checked");
        }

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
            var province = $("#sheng").val();
            var city = $("#shi").val();
            var county = $("#xian").val();
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
            $scope.subjEntTemp.areaId = areaId;
            $scope.subjEntTemp.businessLicenceimg = vm.businessLicenceimg[0].src;
            $scope.subjEntTemp.organizationCertificateimg = vm.organizationCertificateimg[0].src;
            $scope.subjEntTemp.positiveIdcardeimg = vm.positiveIdcardeimg[0].src;
            $scope.subjEntTemp.negativeIdcardimg = vm.negativeIdcardimg[0].src;
            $scope.subjEntTemp.handIdcardimg = vm.handIdcardimg[0].src;

            //附件预留字段
            // $scope.subjEntTemp.attachmentAddress = vm.attachmentAddress[0].src;
            // $scope.subjEntTemp.attachmentName = m.attachmentAddress[0].name;

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



            // 法定代表人信息修改为非必填
            // if(!$scope.subjEntTemp.positiveIdcardeimg){
            //     jBox.tip("请上传身份证正面!");
            //     return false;
            // }
            // if(!$scope.subjEntTemp.negativeIdcardimg){
            //     jBox.tip("请上传身份证反面!");
            //     return false;
            // }
            // if(!$scope.subjEntTemp.handIdcardimg){
            //     jBox.tip("请上传手持身份证!");
            //     return false;
            // }
            $("button").attr("disabled", true);
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/subjEnt/addSubjEntTemp",
                data: $scope.subjEntTemp,
                method: "post"
            }).success(function(data) {
                $("button").attr("disabled", false);
                if (data.httpCode == "200") {
                    jBox.tip("保存成功", 'info');
                    $scope.subjEnt = data.data;
                    $scope.subjEnt.enterpriseName = data.data.entityName;
                    $scope.subjEnt.entityTypeName = data.data.entityType;
                    $scope.subjEnt.entityScaleName = data.data.entityScale;
                    $scope.subjEnt.entityIndustryName = data.data.entityIndustry;
                    $("#baseInspectionForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
                    $("#entTempModal").modal("hide");
                    $("#frdb").hide(); //临时主体，隐藏法人
                    $("#dbdh").hide(); //临时主体，隐藏法人电话
                    $("#baseInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
                } else if (data.httpCode == "407") {
                    jBox.tip("临时登记主体已存在此证件号码", 'info');
                } else {
                    jBox.tip(data.msg, 'info');
                }
            }).error(function(data) {
                
            })
        }

        //选择生产经营主体模态框显示
        $("#enterpriseName").click(function() {
            $("#enterpriseModal").modal("show");
        });

        //选择临时生产经营主体模态框显示
        $scope.selectEntTemp = function() {
            $("#entTempListModal").modal("show");
            $scope.querySubjEntTempList();
        };

        //切换到生产经营主体
        $scope.switchEnt = function() {
            $("#entTab").addClass("gui-TabCheck");
            $("#entTempTab").removeClass("gui-TabCheck");
            $("#entDiv").show();
            $("#entTempDiv").hide();
        }

        //切换到临时生产经营主体
        $scope.switchEntTemp = function() {
            $("#entTempTab").addClass("gui-TabCheck");
            $("#entTab").removeClass("gui-TabCheck");
            $("#entTempDiv").show();
            $("#entDiv").hide();
        }

        //生产经营主体列表
        function loadEntGrid() {
            $("#enterpriseGrid").dataTable({
                ajax: {
                    url: "/sofn-asms-web/subjEnt/getSubjEntList",
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
                        params.enterpriseName = $("input[name='entName']").val();
                        params.userIdcode = $("input[name='userIdcode']").val();
                        params.approveStatus = "1,3,4,5"; //查询可用的生产经营主体
                        params.entityIndustry = $scope.allIndustryId; //所属行业
                        var province = $("#querySheng").val();
                        var city = $("#queryShi").val();
                        var county = $("#queryXian").val();
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
                    //选择按钮
                    {
                        data: "ck",
                        title: '选择',
                        render: function(data, type, row) { // 模板化列显示内容
                            return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                        },
                        width: "2%"
                    },
                    {
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1
                        },
                        width: "2%"

                    },
                    {
                        data: "enterpriseName",
                        title: "主体名称",
                        width: "13%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 16) {
                                return "<a id='subjEntEnterpriseName" + row.id + "' onmouseover=showPopContent('subjEntEnterpriseName" + row.id + "','" + data + "')>" + data.substring(0, 16) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "entityIndustryName",
                        title: "所属行业",
                        width: "7%"
                    },
                    {
                        data: "address",
                        title: "主体地址",
                        width: "12%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 14) {
                                return "<a id='subjEntAddress" + row.id + "' onmouseover=showPopContent('subjEntAddress" + row.id + "','" + data + "')>" + data.substring(0, 14) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "legalName",
                        title: "法定代表人",
                        width: "4%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 4) {
                                return "<a id='legalName" + row.id + "' onmouseover=showPopContent('legalName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "legalPhone",
                        title: "代表电话",
                        width: "5%"
                    },
                    {
                        data: "contactName",
                        title: "联系人",
                        width: "4%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 4) {
                                return "<a id='contactName" + row.id + "' onmouseover=showPopContent('contactName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "contactPhone",
                        title: "联系电话",
                        width: "5%"
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

            $("#entTempGrid").dataTable({
                ajax: {
                    url: "/sofn-asms-web/subjEnt/getSubjEntTempList",
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
                        params.entityName = $("input[name='entTempName']").val();
                        params.entityIndustry = $scope.allIndustryId;
                        var province = $("#queryTempSheng").val();
                        var city = $("#queryTempShi").val();
                        var county = $("#queryTempXian").val();
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
                    //选择按钮
                    {
                        data: "ck",
                        title: '选择',
                        render: function(data, type, row) { // 模板化列显示内容
                            return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                        },
                        width: "2%"
                    },
                    {
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1
                        },
                        width: "2%"
                    },
                    {
                        data: "entityName",
                        title: "主体名称",
                        width: "15%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 20) {
                                return "<a id='subjEntTempEnterpriseName" + row.id + "' onmouseover=showPopContent('subjEntTempEnterpriseName" + row.id + "','" + data + "')>" + data.substring(0, 20) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "entityIndustry",
                        title: "所属行业",
                        width: "7%"
                    },
                    {
                        data: "address",
                        title: "主体地址",
                        width: "15%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 20) {
                                return "<a id='subjEntTempAddress" + row.id + "' onmouseover=showPopContent('subjEntTempAddress" + row.id + "','" + data + "')>" + data.substring(0, 20) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "contactName",
                        title: "联系人",
                        width: "5%",
                        render: function(data, type, row) {
                            if (data != null && data != undefined && data.length > 5) {
                                return "<a id='contactTempName" + row.id + "' onmouseover=showPopContent('contactTempName" + row.id + "','" + data + "')>" + data.substring(0, 5) + "...</a>";
                            } else {
                                return data;
                            }
                        },
                    },
                    {
                        data: "contactPhone",
                        title: "联系电话",
                        width: "5%"
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

        //提交生产经营主体
        $scope.submitEnt = function() {
            var rows = $("#enterpriseGrid").DataTable().rows('.selected').data();
            if (rows.length <= 0) {
                $.jBox.tip("请至少选择一行记录！");
                return false;
            }
            $scope.subjEnt = rows[0];
            if ($scope.subjEnt.enterpriseName == null || $scope.subjEnt.enterpriseName == "" || $scope.subjEnt.enterpriseName == undefined) {
                $scope.subjEnt.enterpriseName = $scope.subjEnt.legalName;
            }
            $("#baseInspectionForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
            $("#enterpriseModal").modal("hide");
            $("#frdb").show(); //显示法定代表人
            $("#dbdh").show(); //显示法定代表人电话
            $("#baseInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
        }

        //提交临时生产经营主体
        $scope.submitEntTemp = function() {
            var rows = $("#entTempGrid").DataTable().rows('.selected').data();
            if (rows.length <= 0) {
                $.jBox.tip("请至少选择一行记录！");
                return false;
            }
            $scope.subjEnt = rows[0];
            $scope.subjEnt.enterpriseName = rows[0].entityName;
            $scope.subjEnt.entityTypeName = rows[0].entityType;
            $scope.subjEnt.entityScaleName = rows[0].entityScale;
            $scope.subjEnt.entityIndustryName = rows[0].entityIndustry;
            $scope.subjEnt.userIdcode = rows[0].userIdcode;
            $("#baseInspectionForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
            $("#enterpriseModal").modal("hide");
            $("#frdb").hide(); //不显示法定代表人
            $("#dbdh").hide(); //不显示法定代表人电话
            $("#baseInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
        }

        //重新加载生产经营主体表格
        $scope.querySubjEntList = function() {
            //点击间隔
            $scope.btnEntDisabled = true;
            setTimeout(function(){
                $scope.$apply($scope.btnEntDisabled = false);
            }, 500);
            $("#enterpriseGrid").dataTable().api().ajax.reload();
        }

        //重新加载临时生产经营主体表格
        $scope.querySubjEntTempList = function() {
            //点击间隔
            $scope.btnTempEntDisabled = true;
            setTimeout(function(){
                $scope.$apply($scope.btnTempEntDisabled = false);
            }, 500);
            $("#entTempGrid").dataTable().api().ajax.reload();
        }

        //打开机构选择模态框
        $scope.selectInspectionSv = function() {
            // $("#superviseModal").modal("show");
        }
        //dataTable
        //巡查机构列表
        $("#superviseGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjSupervise/getSubjSuperviseList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.svName = $("input[name='svName']").val();
                    params.token = window.localStorage.getItem("token");
                },
                type: "post"
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function(data, type, row) { // 模板化列显示内容
                        return '<input name="sv" id="' + row.id + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.id + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "svName",
                    title: "机构名称"
                },
                {
                    data: "svType",
                    title: "机构类别"
                },
                {
                    data: "svLevel",
                    title: "机构级别"
                },
                {
                    data: "svAreaId",
                    title: "所属区域",
                    render: function(data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data: "svLeader",
                    title: "机构负责人"
                },
                {
                    data: "createTime",
                    title: "备案时间"
                }
            ],
            fnDrawCallback: function() {
                //设置选中状态
                $("#superviseGrid >tbody >tr").click(function() {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $(this).removeClass('selected');
                    } else {
                        $('#superviseGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });

        //提交巡查机构
        $scope.submitSv = function() {
            var rows = $("#superviseGrid").DataTable().rows('.selected').data();
            if (rows.length <= 0) {
                $.jBox.tip("请至少选择一行记录！");
                return false;
            }
            $scope.subjSv = rows[0];
            $("input[name='inspectionSvName']").val($scope.subjSv.svName);
            $("#superviseModal").modal("hide");
            //重新加载人员
            $scope.baseInspection.inspectionSvId = $scope.subjSv.id;
            $("#selectGrid").dataTable().api().ajax.reload();
        }

        //重新加载巡查机构表格
        $scope.querySubjSvList = function() {
            $("#superviseGrid").dataTable().api().ajax.reload();
        }

        /**
         * ---------人员选择列表--------------
         * */

        /**
         * 已选择数据
         * id、name
         * */

        $('#selectModal').modal({
            show: false,
            backdrop: 'static',
            keyboard: false
        });
        $scope.selectPerson = function() {
            $('#selectGrid').dataTable().api().ajax.reload();
            $("#selectModal").modal('show');
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
            $("#baseInspectionForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
            $("#selectModal").modal('hide');
            $("#baseInspectionForm").bootstrapValidator(); //初始化验证，与上面对应
        };

        function loadPersonGrid() {
            $("#selectGrid").dataTable({
                fnDrawCallback: function() {
                    //判断是否全选
                    $("#checkAll").prop("checked", $("input[name='ck']").length == $("input[name='ck']:checked").length && $("input[name='ck']").length > 0);
                    //点击行内任意内容即选择行
                    $("#selectGrid tbody tr td").each(function(i, o) {
                        $(o).click(function() {
                            var checked = $(this).parent().find("input[type='checkbox']")[0]
                            if (checked.checked) {
                                checked.checked = false;
                                $(this).parent().removeClass("selected");
                                //人员移除
                                var u_d = $(this).parent().find("[name='ck']").val();
                                for (var i = 0; i < $scope.selects.length; i++) {
                                    if ($scope.selects[i] != undefined && u_d == $scope.selects[i].id) {
                                        delete $scope.selects[i];
                                    }
                                }
                            } else {
                                checked.checked = true;
                                $(this).parent().addClass("selected");
                                //人员选择记录
                                var map = {
                                    id: $(this).parent().find("[name='ck']").val(),
                                    userName: $(this).parent().find("[name='userName']").html()
                                };
                                $scope.selects.push(map);
                            }
                            //判断是否全选
                            $("#checkAll").prop("checked", $("input[name='ck']").length == $("input[name='ck']:checked").length && $("input[name='ck']").length > 0);
                        });
                    })
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
                                if ($scope.selects[i] != undefined && data == $scope.selects[i].id) {
                                    return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                                }
                            }
                            return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                        }
                    },
                    {
                        data: 'id',
                        title: 'ID',
                        visible: false
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
                            return '<span name = "userName">' + data + '</span>';
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
                ]
            });
        };

        $("#selectTocheck").click(function() {
            $("#selectGrid").dataTable().api().ajax.reload();
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
        //巡查人员全选,全不选
        window.checkAll = function(checked) {
            //本页人员全部移除
            $("input[name='ck']").each(function(i, o) {
                $(o).prop("checked", checked);
                var u_d = $(o).val();
                for (var i = 0; i < $scope.selects.length; i++) {
                    if ($scope.selects[i] != undefined && u_d == $scope.selects[i].id) {
                        delete $scope.selects[i];
                    }
                }
            })
            //添加本页全部人员
            if (checked) {
                $("input[name='ck']:checked").each(function(i, o) {
                    var map = {
                        id: $(o).val(),
                        userName: $(o).parent().parent().find("[name='userName']").html()
                    };
                    $scope.selects.push(map);
                });
            }
        }

        //查询条件重置
        $scope.cleanSelectModelQuery = function() {
            $("input[name='selectQueryName']").val("");
        };

        //清空已选择
        $scope.cleanSelected = function() {
            $scope.selects = null;
            $scope.selects = new Array();
            $('#selectGrid').dataTable().api().ajax.reload();
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
        //判断是否有企业证件照,组织机构代码照
        $scope.selectEntityScale = function(entityScale) {
            if (entityScale == "企业/个体工商户" || entityScale == "合作社") {
                $("#businessHead").show();
                $("#businessContent").show();
                $("#credType").show();
                $("#organizationCode").show();
                $("#zjhm1").show();
                $("#zjhm2").hide();
                $("#zjhm3").hide();
                $scope.selectBusiness();
            } else {
                $("#zjhm1").hide();
                $("#zjhm2").hide();
                $("#zjhm3").show();
                $("#businessHead").hide();
                $("#businessContent").hide();
                $("#credType").hide();
                $("#organizationCode").hide();
                $scope.subjEntTemp.organizationCode = "";
                vm.businessLicenceimg = [{
                    vname: '企业证件照',
                }]
                vm.organizationCertificateimg = [{
                    vname: '组织机构照',
                }]
            }
        }
        //判断组织机构代码,组织机构代码照是否显示
        $scope.selectBusiness = function() {
            if ($scope.subjEntTemp.credType == "普通营业执照(有独立组织机构代码证)") {
                $("#selectOrganizationCertificateimg").show();
                $("#organizationCode").show();
                $("#zjhm1").show();
                $("#zjhm2").hide();
                $("#zjhm3").hide();
            } else {
                $("#zjhm1").hide();
                $("#zjhm2").show();
                $("#zjhm3").hide();
                $("#selectOrganizationCertificateimg").hide();
                $("#organizationCode").hide();
                $scope.subjEntTemp.organizationCode = "";
                vm.organizationCertificateimg = [{
                    vname: '组织机构照',
                }]
            }
        }
        //鼠标悬停弹窗显示
        window.showPopContent = function(id, content) {
            $("#" + id).popover({
                animation: true,
                html: false,
                placement: 'right',
                trigger: 'hover',
                content: content,
                delay: 0,
                container: 'body'
            });
            $("#" + id).popover("show");
        }

    //巡查人员考核任务列表
        $("#enterpriseGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntList",
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
                    params.enterpriseName = $("input[name='entName']").val();
                    params.userIdcode = $("input[name='userIdcode']").val();
                    params.approveStatus = "1,3,4,5"; //查询可用的生产经营主体
                    params.entityIndustry = $scope.allIndustryId; //所属行业
                    var province = $("#querySheng").val();
                    var city = $("#queryShi").val();
                    var county = $("#queryXian").val();
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
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function(data, type, row) { // 模板化列显示内容
                        return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                    },
                    width: "2%"
                },
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "2%"

                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    width: "13%",
                    render: function(data, type, row) {
                        if (data != null && data != undefined && data.length > 16) {
                            return "<a id='subjEntEnterpriseName" + row.id + "' onmouseover=showPopContent('subjEntEnterpriseName" + row.id + "','" + data + "')>" + data.substring(0, 16) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width: "7%"
                },
                {
                    data: "address",
                    title: "主体地址",
                    width: "12%",
                    render: function(data, type, row) {
                        if (data != null && data != undefined && data.length > 14) {
                            return "<a id='subjEntAddress" + row.id + "' onmouseover=showPopContent('subjEntAddress" + row.id + "','" + data + "')>" + data.substring(0, 14) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "legalName",
                    title: "法定代表人",
                    width: "4%",
                    render: function(data, type, row) {
                        if (data != null && data != undefined && data.length > 4) {
                            return "<a id='contactName" + row.id + "' onmouseover=showPopContent('contactName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "legalPhone",
                    title: "代表电话",
                    width: "5%"
                },
                {
                    data: "contactName",
                    title: "联系人",
                    width: "4%",
                    render: function(data, type, row) {
                        if (data != null && data != undefined && data.length > 4) {
                            return "<a id='contactName" + row.id + "' onmouseover=showPopContent('contactName" + row.id + "','" + data + "')>" + data.substring(0, 4) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                },
                {
                    data: "contactPhone",
                    title: "联系电话",
                    width: "5%"
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
        //巡查计划-模态框
        $scope.selectPlanGrid = function() {
            $scope.loadPlanGrid();
            $("#planModal").modal("show");
        }
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
                        width: "3%"

                    },
                    {
                        data: "planName",
                        title: "巡查计划",
                        width: "13%"
                    },
                    {
                        data: "taskName",
                        title: "考核任务",
                        width: "13%"
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
                        data: "planEndDate",
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
                            });
                            return resArr.join(",");
                        }
                    },
                    {
                        data: "status",
                        title: "任务状态",
                        width: "8%",
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