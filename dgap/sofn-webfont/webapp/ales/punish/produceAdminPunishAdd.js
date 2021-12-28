//    angularjs数据传输
var myapp=angular.module("myapp",[]);
myapp.controller("produceAdminPunishAdd",function($scope,$http,$state, $stateParams){
    var mytype="";
    var myend="";
    var entityIdCode = "";
    var underArea ="";
    var jibie="";
    var orgId ="";
    console.log($stateParams);
    $scope.produceAdminPunish = {};
    $scope.updateFlag = window.localStorage.getItem("ales_punish_updateFlag");
    window.localStorage.removeItem("ales_punish_updateFlag");
    $scope.id=window.localStorage.getItem("id");
    var vm = this;
    //获取区域数据
    $http({
        url:"/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        orgId = data.data.orgId;
        area = data.data.regionId;

        /*$scope.subjLevel= data.data.orgLevel;//主体级别
        $scope.jurisdictionArea = data.data.regionId.substr(0,2*($scope.subjLevel-1));//区域*/

        $scope.underArea = area.substr(0, 2 * (data.data.orgLevel - 1));
        $.fn.mycity("subjTempSheng", "subjTempShi", "subjTempXian", $scope.underArea);
        $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.underArea);
        $.fn.mycity("queryTempSheng", "queryTempShi", "queryTempXian", $scope.underArea);

        //限定查询区域
        $scope.disableAreaSelectByAreaId("subjTempSheng", "subjTempShi", "subjTempXian");
        $scope.disableAreaSelectByAreaId("querySheng", "queryShi", "queryXian");
        $scope.disableAreaSelectByAreaId("queryTempSheng", "queryTempShi", "queryTempXian");

    }).error(function(data){
        
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

    //获取行政处罚来源字典
    var initPenaltySources = function() {
        $scope.penaltySources = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/sysDict/getSysDictByCode",
            data: "30",
            method: "post"
        }).success(function (data) {
            $scope.penaltySources = JSON.parse(data.data);
            // $scope.penaltySources = [];
            // var arr = new Array();
            // for(var i=0;i<$scope.a.length;i++){
            //     arr.push($scope.a[i]);
            // }
            // $scope.penaltySources = arr;
        }).error(function (msg) {
            
        });
    }

    initPenaltySources();

    //根据处罚来源变换添加信息
    window.selectPenaly = function(){
         $scope.produceAdminPunish.penaltySource=$("select[name='penaltySource']").val();
        if($scope.produceAdminPunish.penaltySource!="检查发现" ){
             $("#dailyTaskInput").css("display","none");
            $("#entButton").css("display","block");
            $("#entTempButton").css("display","block");
        }else{
            $("#dailyTaskInput").css("display","block");
            $("#entButton").css("display","none");
            $("#entTempButton").css("display","none");
        }
    }


    $scope.queryDailyEnforceLaw = function(id) {
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
            method:"post",
            data:{id:id}
        }).success(function(data){
            $scope.dailyEnforceLaw=data.dailyEnforceLaw;
        }).error(function(msg){
        });
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
    // 2019年3月15日-------------------------------------------bing
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

    //--------------------------------------------------------end
    $scope.updateByGet = function() {
        if ($scope.updateFlag == "1") {
            $("#OprTitle").html("修改执法日志");
            $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/administrativePenalty/getPunishiById",
                method: "post",
                params: {id: $scope.id}
            }).success(function (data) {

                $scope.produceAdminPunish = data.produceAdminPunish;
                //判断行政处罚来源是否为检查发现
                if($scope.produceAdminPunish.penaltySource != "检查发现" ){
                    $("#entButton").css("display","block");
                    $("#entTempButton").css("display","block");
                }

                $scope.produceAdminPunish.userIdcode = data.produceAdminPunish.enterpriseCode;
                var dailyTaskCode = data.produceAdminPunish.dailyTaskCode;
                $("input[name='dailyTaskCode']").val(dailyTaskCode);
                $scope.queryDailyEnforceLaw(data.produceAdminPunish.dailyTaskCode);
                vm.files=[];
                if($scope.produceAdminPunish.punishFiles!=null&&$scope.produceAdminPunish.punishFiles!=undefined&&      $scope.produceAdminPunish.punishFiles!="") {
                    var urls = $scope.produceAdminPunish.punishFiles.split("*##*");
                    var names = $scope.produceAdminPunish.punishFilesName.split("*##*");
                    for (var i = 0; i < urls.length; i++) {
                        console.debug("src is:"+urls[i]+",name is:"+names[i]);
                        vm.files = vm.files.concat([{
                            hidden: false,
                            src: urls[i],
                            name: names[i]}]);
                    }
                };            
            }).error(function (data) {
                
            });
        } else {
            $("#attributeForm").form("reset");
        }
    };
    $scope.updateByGet();

    //激活验证规则
    $scope.AlesProduceAdminPunish = {};
    $('#attributeForm').bootstrapValidator();//必须有
    $('#entTemp').bootstrapValidator();//必须有

    $scope.inspectionTime = new Date().toISOString().slice(0,10);
    /**
     * 当前用户机构 获得执法单位
     * */
    $http({
        url:"/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        orgId = data.data.orgId;
        $("input[name='enforceLawId']").val(data.data.orgName);
        underArea = data.data.regionId;
        jibie= data.data.orgLevel;
        if(jibie==1){
            $scope.underArea=null;
        }
        if(jibie==2){
            $scope.underArea=underArea.substr(0,2);
        }
        if(jibie==3){
            $scope.underArea=underArea.substr(0,4)
        }
        if(jibie==4){
            $scope.underArea=underArea.substring(0,6)
        }
        $scope.loadDailyTaskGrid();
        loadEntGrid();
    }).error(function(data){
        console.log(data);
        
    });
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
    }).error(function (data) {
        console.log(data);
        
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
    }).error(function (data) {
        console.log(data);
        
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
    }).error(function (data) {
        console.log(data);
        
    })

    //***
    //* 多文件上传
    //* */
    var vm = this;

    //文件上传配置
    vm.uploadOptions = {
        headers:{
            token:window.localStorage.getItem("token")
        },
        //接口地址
        url: '/sofn-ales-web/alesFile/upload',
        accept:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,image/png, image/jpeg,image/gif,application/pdf',
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        filter: function(file, info) {
            var acceptFileTypes = /^(image\/(jpg|gif|jpeg|png))$|^(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document|pdf))$/i;
            if (file.type && file.type != '') {
                if (!acceptFileTypes.test(file.type)) {
                    swal('', '只能上传word,pdf或者图片类型文档', 'warning');
                    return false;
                }
            } else {
                if (!/\.(doc|docx|pdf|jpg|gif|jpeg|png)$/i.test(file.name)) {
                    swal('', '只能上传word,pdf或者图片类型文档', 'warning');
                    return false;
                }
            }

            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.loadDailyTaskGrid = function() {

        $("#dailyTaskGrid").dataTable({
            ajax: {
                url: "/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawList",
                dataSrc: function (json) {
                    if(json.data == null || json.data == undefined){
                        json.recordsTotal=0;//总个数
                        json.recordsFiltered=0;
                        json.pageNumber=1;//页码
                        json.pageSize=10;//每页个数
                        json.start=0;//起始位置
                        json.length=10;
                        return json.list;
                    }
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].TASK_BEGIN_TIME != null && json.data.list[i].TASK_BEGIN_TIME != "" && json.data.list[i].TASK_BEGIN_TIME != undefined) {
                            json.data.list[i].TASK_BEGIN_TIME = new Date(parseInt(json.data.list[i].TASK_BEGIN_TIME) + 28800000).toISOString().slice(0, 10);
                            //json.data.list[i].TASK_END_TIME = new Date(parseInt(json.data.list[i].TASK_END_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.createOrgId = orgId;
                    // params.taskYear = $("input[name='taskYear']").val();
                    params.queryCon = $("input[name='queryCon']").val();
                    // params.dateBegin = $("input[name='datebegin']").val();
                    // params.dateEnd = $("input[name='dateend']").val();
                    // params.enforceLawResultFlag = $("select[name='enforceLawResultFlag']").val();
                    params.enforceLawResultFlag = "2,3";
                    params.areaId = $scope.underArea;
                    // ObjShen = $("select[name='ObjShen']").val();
                    // ObjShi = $("select[name='ObjShi']").val();
                    // ObjXian = $("select[name='ObjXian']").val();
                    // if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                    //     if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                    //         if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    //             params.areaId = ObjXian
                    //         } else {
                    //             params.areaId = ObjShi
                    //         }
                    //     } else {
                    //         params.areaId = ObjShen
                    //     }
                    // }
                },
                type: "POST"
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function (data, type, row) { // 模板化列显示内容
                        
                        if($("input[name='dailyTaskCode']").val() == row.ID){
                            return '<input checked="true" name="dailyTaskSelect" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                        }
                        return '<input name="dailyTaskSelect" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                    },
                    width:"3%"
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"
                },
                {
                    data: "TASK_BEGIN_TIME",
                    title: "年度",
                    width: "5%",
                    render: function (data, type, row) { // 模板化列显示内容
                        return data.substring(0, 4);
                    },
                },
                {
                    data: "TASK_NAME",
                    title: "任务名称",
                    width: "10%"
                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "被执法对象",
                    width: "10%"
                },
                {
                    data: "AREA_ID",
                    title: "区域",
                    width: "10%",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (data != null && data != "" && data != undefined) {
                            return $.fn.Cityname(row.AREA_ID)
                        }
                    }
                },
                {
                    data: "ENTERPRISE_ADDRESS",
                    title: "被执法对象地址",
                    width: "20%"
                },
                {
                    data: "ENFORCE_LAW_RESULT_FLAG",
                    title: "巡查结果",
                    width: "5%",
                    render: function (data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        }
                    }
                }
            ],
            fnDrawCallback: function () {
                //设置选中状态
                $("#dailyTaskGrid >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='dailyTaskSelect']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#dailyTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });
    }

    $scope.showDailyTaskModal = function(){

        $("#dailyTaskModal").modal('show');
    }

    $scope.queryDailyTask = function(){
        $("#dailyTaskGrid").dataTable().api().ajax.reload();
    }

    $scope.resetDailyTaskQuery = function(){
        $("#queryCon").val("");
    }

    $scope.selectDailyTask = function(){
        var rows = $("#dailyTaskGrid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请至少选择一行现场巡查记录！");
            return false;
        }
        $scope.dailyTask=rows[0];
        $("#dailyTaskCodeName").val($scope.dailyTask.TASK_NAME);
        $("#dailyTaskCode").val($scope.dailyTask.ID);
        $("#dailyTaskModal").modal('hide');
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEnt/findSubjEntById",
            data:{id:$scope.dailyTask.ENTERPRISE_ID},
            method:"post"
        }).success(function(json) {
            if(json.data==null||json.data==""||json.data==undefined){
                $http({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url:"/sofn-ales-web/subjEnt/findSubjEntTempById",
                    method:"post",
                    data:{id:$scope.dailyTask.ENTERPRISE_ID}
                }).success(function(dataSubjEntTemp){
                    $scope.subjEnt=dataSubjEntTemp.data;
                    $("#ztenterpriseName").val($scope.subjEnt.entityName);
                    $("#ztEnterpriseMa").val($scope.subjEnt.identityCode);
                    $("#legalPerson").val($scope.subjEnt.contactName);
                    $("#enterpriseId").val($scope.subjEnt.id);
                }).
                error(function(data){
                    console.log(data);
                    
                });
            }else {
                $scope.subjEnt = json.data;
                $("#ztenterpriseName").val($scope.subjEnt.enterpriseName);
                $("#ztEnterpriseMa").val($scope.subjEnt.userIdcode);
                $("#legalPerson").val($scope.subjEnt.legalName);
                $("#enterpriseId").val($scope.subjEnt.id);
            }
        })
    }
    /**
     * ---------人员选择列表--------------
     * */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects  = new Array();

    $('#selectModal').modal({
        show:false,
        backdrop:'static',
        keyboard:false
    });
    $scope.selectPerson= function(){
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };

    //$.fn.mycity("selectQueryShen","selectQueryShi","selectQueryXian");

    //确定
    $scope.selectModalQdButt = function(){
        var userName = "";
        for(var i = 0; i < $scope.selects.length ; i++){
            if ($scope.selects[i] != undefined){
                if (userName != ""){
                    userName+=","+$scope.selects[i].userName;
                }else {
                    userName = $scope.selects[i].userName;
                }
            }
        }
        $("input[name='enforceLawPeople']").val(userName);
        $("input[name='enforceLawPeople']").val(userName);
        if($("input[name='enforceLawPeople']").val()==""){
            $(".objPerson").css("display","block");
            return;
        }else{
            $(".objPerson").css("display","none")
        }
        $("#selectModal").modal('hide');
    };

    $("#selectGrid").dataTable({
            fnDrawCallback: function() {
                //点击行内任意内容即选择行
                $("#selectGrid tbody tr td").each(function(i,o){
                    $(o).click(function(){
                        var checked = $(this).parent().find("input[type='checkbox']")[0]
                        if(checked.checked){
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                            //人员移除
                            var u_d = $(this).parent().find("[name='ck']").val();
                            for (var i = 0 ; i < $scope.selects.length ; i++){
                                if($scope.selects[i] != undefined && u_d == $scope.selects[i].id){
                                    delete $scope.selects[i];
                                }
                            }
                        }else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                            //人员选择记录
                            var map={
                                id:$(this).parent().find("[name='ck']").val(),
                                userName:$(this).parent().find("[name='userName']").html()
                            };
                            $scope.selects.push(map);
                            console.info($scope.selects)
                        }
                    });
                })
            },
            ajax : {
                url : "/sofn-ales-web/dailyEnforceLaw/getUsersListByUserToken",
                dataSrc:function(json){
                    if(json.data == null || json.data == undefined){
                        json.recordsTotal=0;//总个数
                        json.recordsFiltered=0;
                        json.pageNumber=1;//页码
                        json.pageSize=10;//每页个数
                        json.start=0;//起始位置
                        json.length=10;
                        return json.data;
                    }
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.pageNumber = json.page.start/json.page.length;//页码
                    json.pageSize = json.page.length;//每页个数
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.data;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data : function(params) {
                    params.token= window.localStorage.getItem("token");
                    params.querySheng = $("#querySheng").val();
                    params.queryShi = $("#queryShi").val();
                    params.queryXian = $("#queryXian").val();
                    params.keyword = $("input[name='selectQueryName']").val();
                },
                type:"post",
                headers:{
                    token:window.localStorage.getItem("token")
                }
            },
            columns : [
                {
                    data : "id",
                    title : '*',
                    render : function(data, type, row) {
                        for(var i = 0; i <  $scope.selects.length;i++){
                            if ($scope.selects[i] != undefined && data == $scope.selects[i].id){
                                return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                            }
                        }
                        return '<input type="checkbox" name="ck" onclick="checkSelectThisBox(this)" value="'+data+'"/>';
                    },
                    width : "3%"
                },
                {
                    data : 'id',
                    title : 'ID',
                    visible : false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data : "userName",
                    title : "姓名",
                    render : function(data, type, row) {
                        return '<span name = "userName">'+data+'</span>';
                    }
                },
                {
                    data : "phone",
                    title : "联系电话"
                },
                {
                    data : "email",
                    title : "电子邮箱"
                }
            ],
        });

    $("#selectTocheck").click(function(){
        $("#selectGrid").dataTable().api().ajax.reload();
    });
    //针对复选框无法选中bug
    window.checkSelectThisBox = function(o){
        if(o.checked){
            o.checked = false;
            $(o).removeClass("selected");
        }else {
            o.checked = true;
            $(o).addClass("selected");
        }
    };

    //查询条件重置
    $scope.cleanSelectModelQuery = function(){
        $("input[name='selectQueryName']").val("");
    };
    //清空已选择
    $scope.cleanSelected = function(){
        $scope.selects  = null;
        $scope.selects  = new Array();
        $('#selectGrid').dataTable().api().ajax.reload();
    };
    //时间不能为空的提示
    window.timeClick=function(){
        $(".objmanufactureDate").toggle($("input[name='manufactureDate']").val()=="");
    }



    //保存
    $scope.savePunish=function(){
        for(var i=0;i<vm.files.length;i++){
            $scope.AlesProduceAdminPunish.file=vm.files[i].src;
            $scope.AlesProduceAdminPunish.fileName=vm.files[i].name;
        }
        if($scope.AlesProduceAdminPunish.file==null||$scope.AlesProduceAdminPunish.file==""||$scope.AlesProduceAdminPunish.file==undefined){
            jBox.tip("请上传文件！", 'info');
            return false;
        }
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            $scope.AlesProduceAdminPunish.file = "";
            $scope.AlesProduceAdminPunish.fileName = "";
            if($("input[name='enforceLawPeople']").val()==""){
                $(".objPerson").css("display","block");
            }else{
                $(".objPerson").css("display","none")
            };
            if($("input[name='manufactureDate']").val()==""){
                $(".objmanufactureDate").css("display","block");
            }else{
                $(".objmanufactureDate").css("display","none")
            }
            return false;
        }//必须有
//            <!-- 页面跳转至基地巡查信息汇总-->
//            数据传递
//
        if($("input[name='manufactureDate']").val()==""){
            $.jBox.tip("请输入生产日期或批次");
            return;
        }
        if($("input[name='enforceLawPeople']").val()==""){
            $.jBox.tip("请选择执法人员！");
            return;
        }
        $("button").attr("disabled",true);
        $scope.produceAdminPunish.punishCode=$("input[name='punishCode']").val(),
        $scope.produceAdminPunish.enterpriseIdName=$("input[name='ztenterpriseName']").val(),
        $scope.produceAdminPunish.enterpriseCode=$("input[name='ztEnterpriseMa']").val(),
        $scope.produceAdminPunish.idEnterprise=$("input[name='ztEnterpriseId']").val(),
        $scope.produceAdminPunish.legalPerson=$("input[name='legalPerson']").val(),
        $scope.produceAdminPunish.caseName=$("input[name='caseName']").val(),
        $scope.produceAdminPunish.productName=$("input[name='productName']").val(),
        $scope.produceAdminPunish.enterpriseName=$("input[name='enterpriseName']").val(),
        $scope.produceAdminPunish.manufactureDate=$("input[name='manufactureDate']").val(),
        $scope.produceAdminPunish.punishQualitative=$("textarea[name='punishQualitative']").val(),
        $scope.produceAdminPunish.punishResult=$("textarea[name='punishResult']").val(),
        $scope.produceAdminPunish.punishFiles=$scope.AlesProduceAdminPunish.file,
        $scope.produceAdminPunish.punishFilesName=$scope.AlesProduceAdminPunish.fileName,
        $scope.produceAdminPunish.enforceLawId=$("input[name='enforceLawId']").val(),
        $scope.produceAdminPunish.enforceLawPeople=$("input[name='enforceLawPeople']").val(),
        $scope.produceAdminPunish.createOrgId=orgId,
        $scope.produceAdminPunish.dailyTaskCode=$("#dailyTaskCode").val(),
        $scope.produceAdminPunish.penaltySource=$("select[name='penaltySource']").val()


        
        if($scope.updateFlag == "1"){
            $scope.produceAdminPunish.punishFiles = $scope.AlesProduceAdminPunish.file;
            $scope.produceAdminPunish.punishFilesName = $scope.AlesProduceAdminPunish.fileName;
            $http({
                url:"/sofn-ales-web/administrativePenalty/updateAdministrativePenalty",
                data:$scope.produceAdminPunish,
                method:"post",
                //post传输方式   头信息
                headers: {
                    token:window.localStorage.getItem("token")
                }
            }).success(function(data){
                
                if(data.httpCode == "400"){
                    $("button").attr("disabled",false);
                    $.jBox.tip(data.msg);
                }else{
                    $("button").attr("disabled",false);
                    $.jBox.tip("修改成功！");
                    $state.go("index.content.ales/punish/produceAdminPunishlist");
                }

            }).error(function(){
                $("button").attr("disabled",false);
                $.jBox.tip("修改失败！");
                }).error(function (data) {
                
            });
        }else{
            $scope.produceAdminPunish.enterpriseId=$("input[name='enterpriseId']").val(),
            $http({
                url:"/sofn-ales-web/administrativePenalty/addAdministrativePenalty",
                data:{
                    punishCode:$("input[name='punishCode']").val(),
                    enterpriseIdName:$("input[name='ztenterpriseName']").val(),
                    enterpriseCode:$("input[name='ztEnterpriseMa']").val(),
                    idEnterprise:$("input[name='ztEnterpriseId']").val(),
                    enterpriseId:$("input[name='enterpriseId']").val(),
                    legalPerson:$("input[name='legalPerson']").val(),
                    caseName:$("input[name='caseName']").val(),
                    productName:$("input[name='productName']").val(),
                    enterpriseName:$("input[name='enterpriseName']").val(),
                    manufactureDate:$("input[name='manufactureDate']").val(),
                    punishQualitative:$("textarea[name='punishQualitative']").val(),
                    punishResult:$("textarea[name='punishResult']").val(),
                    punishFiles:$scope.AlesProduceAdminPunish.file,
                    punishFilesName:$scope.AlesProduceAdminPunish.fileName,
                    enforceLawId:$("input[name='enforceLawId']").val(),
                    enforceLawPeople:$("input[name='enforceLawPeople']").val(),
                    createOrgId:orgId,
                    dailyTaskCode:$("#dailyTaskCode").val(),
                    penaltySource:$("select[name='penaltySource']").val()
                },
                method:"post",
                //post传输方式   头信息
                headers: {
                    token:window.localStorage.getItem("token")
                }
            }).success(function(data){
                
                if(data.httpCode == "400"){
                    $("button").attr("disabled",false);
                    $.jBox.tip(data.msg);
                }else{
                    $("button").attr("disabled",false);
                    $.jBox.tip("新增成功！");
                    $state.go("index.content.ales/punish/produceAdminPunishlist");
                }

            }).error(function(){
                $("button").attr("disabled",false);
                $.jBox.tip("新增失败！");
                }).error(function (data) {
               
            });
        }

    };

    //选择生产经营主体模态框显示
    $scope.selectEnt = function(){
        $("#enterpriseModal").modal("show");
    };

    //选择临时生产经营主体模态框显示
    $scope.selectEntTemp = function(){
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

    //dataTable
    //生产经营主体列表
    function loadEntGrid() {
        $("#enterpriseGrid").dataTable({
            ajax: {
                url: "/sofn-ales-web/subjEnt/getSubjEntList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    if (json.list == null || json.list == undefined) {
                        json.recordsTotal = 0; //总个数
                        json.recordsFiltered = 0;
                        json.start = 0; //起始位置
                        json.length = 10;
                        return json.list;
                    }
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.list;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data: function (params) {
                    
                    params.enterpriseName = $("input[name='entName']").val();
                   // params.entityIdcode = $("input[name='entIdCode']").val();
                    params.approveStatus="1,3,4,5";//查询可用的生产经营主体
                    // var temp = $scope.organization.regionId;
                    // if(temp.substr(4,6)=="00"){
                    //     temp = temp.substr(0,4);
                    //     if(temp.substr(2,4)=="00"){
                    //         temp = temp.substr(0,2);
                    //     }
                    // }
                    params.area = $scope.underArea;

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
                type: "post",
                headers:{
                    token:window.localStorage.getItem("token")
                }
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
                    }
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
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
            fnDrawCallback: function () {
                //设置选中状态
                $("#enterpriseGrid >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ent']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#enterpriseGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });

        $("#entTempGrid").dataTable({
            ajax: {
                url: "/sofn-ales-web/subjEnt/getSubjEntTempList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    if (json.list == null || json.list == undefined) {
                        json.recordsTotal = 0; //总个数
                        json.recordsFiltered = 0;
                        json.start = 0; //起始位置
                        json.length = 10;
                        return json.list;
                    }
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.list;
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data: function (params) {
                    params.entityName = $("input[name='entTempName']").val();
                    params.areaId = $scope.underArea;

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
                headers:{
                    token:window.localStorage.getItem("token")
                }
            },
            columns: [
                //选择按钮
                {
                    data: "ck",
                    title: '选择',
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<input name="ent" id="' + row.ID + '" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '"/>';
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
                    data: "contactName",
                    title: "联系人"
                },
                {
                    data: "contactPhone",
                    title: "联系电话"
                }
            ],
            fnDrawCallback: function () {
                //设置选中状态
                $("#entTempGrid >tbody >tr").click(function () {
                    $(this).children("td").eq(0).children().prop("checked", true);
                    $(this).siblings().children("td").eq(0).children().removeAttr("checked", "checked");
                    if ($(this).hasClass('selected')) {
                        $("input[name='ent']").removeAttr("checked");
                        $(this).removeClass('selected');
                    }
                    else {
                        $('#entTempGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            }
        });
    }
    var enterpriseArea = ""
    //提交生产经营主体
    $scope.submitEnt=function(){
        var rows = $("#enterpriseGrid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        $scope.subjEnt=rows[0];
        $("#ztenterpriseName").val($scope.subjEnt.enterpriseName);
        $("#ztEnterpriseMa").val($scope.subjEnt.userIdcode);
        $("#legalPerson").val($scope.subjEnt.legalName);
        $("#enterpriseId").val($scope.subjEnt.id);
        enterpriseArea = $scope.subjEnt.area
        $.fn.mycity("ObjShen","ObjShi","ObjXian",enterpriseArea);
        $("#ObjShen").attr("disabled",true);
        $("#ObjShi").attr("disabled",true);
        $("#ObjXian").attr("disabled",true);
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#enterpriseModal").modal("hide");
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    }

    //提交临时生产经营主体
    var enterpriseAreaId = ""
    $scope.submitEntTemp=function(){
        var rows = $("#entTempGrid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        $scope.subjEnt=rows[0];
        $("#ztenterpriseName").val($scope.subjEnt.entityName);
        $("#ztEnterpriseMa").val($scope.subjEnt.identityCode);
        $("#legalPerson").val($scope.subjEnt.contactName);
        $("#enterpriseId").val($scope.subjEnt.id);
        enterpriseAreaId = $scope.subjEnt.areaId;
        $.fn.mycity("ObjShen","ObjShi","ObjXian",enterpriseAreaId);
        $("#ObjShen").attr("disabled",true);
        $("#ObjShi").attr("disabled",true);
        $("#ObjXian").attr("disabled",true);
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#enterpriseModal").modal("hide");
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    }

    //重新加载生产经营主体表格
    $scope.querySubjEntList=function(){
        $("#enterpriseGrid").dataTable().api().ajax.reload();
    }

    //重新加载临时生产经营主体表格
    $scope.querySubjEntTempList=function(){
        $("#entTempGrid").dataTable().api().ajax.reload();
    }

    //生产经营主体临时备案
    $scope.gotoAddSubjEntTemp = function() {
        $("#entTempModal").modal("show");
        $("input[name='entityScaleId']:first").attr("checked", "checked");
        $("input[name='entityTypeId']:first").attr("checked", "checked");
        $("input[name='entityIndustryId']:first").attr("checked", "checked");
        $("#credTypes").hide();
    }

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
        if (!$("#entTemp").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
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
        //$scope.subjEntTemp.entityIndustry=$("input[name='entityIndustryId']:checked").next().text();

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
        $scope.subjEntTemp.areaId=areaId;
        $("button").attr("disabled",true);s
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
                $scope.subjEnt=data.data;
                $("#ztenterpriseName").val($scope.subjEnt.entityName);
                $("#ztEnterpriseMa").val($scope.subjEnt.identityCode);
                $("#legalPerson").val($scope.subjEnt.contactName);
                $("#enterpriseId").val($scope.subjEnt.id);
                // $.fn.mycity("ObjShen","ObjShi","ObjXian",areaId);
                // $scope.disableAreaSelectByAreaId("ObjShen","ObjShi","ObjXian");
                $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
                $("#entTempListModal").modal("hide");
                $("#entTempModal").modal("hide");
                $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(){
            $("button").attr("disabled",false);
            jBox.tip("保存失败", 'info');
        }).error(function (data) {
            console.log(data);
            
        });
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

});

//checkbox[name=ck]点击事件
function checkClick(ck) {
    $($(ck).parent().parent()[0]).toggleClass('selected');
    var e = window.event||arguments.callee.caller.arguments[0];
    if (e && e.stopPropagation) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
}

function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}



