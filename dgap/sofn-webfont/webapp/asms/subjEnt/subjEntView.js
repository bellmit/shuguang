var myapp=angular.module("myapp",[]).controller("subjEntView",function($scope,$http,$stateParams, $state){
    //获取本地存储的ID
    $scope.subjEntId=window.localStorage.getItem("subjEntId");
    //判断监管系统显示还是系统管理
    if(window.localStorage.getItem("webappSysname")=="sys"){
        $(".gui-breadcrumbs").html('<a href="">系统管理</a>&gt; <a href="#/index/content/sys/user/sysSubjEntList">用户管理</a>&gt; <a href="#/index/content/sys/user/sysSubjEntList">机构用户管理</a>&gt; <a href="#/index/content/sys/user/sysSubjEntList">生产经营主体</a>&gt; <span>查看</span>');
    }

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnt/findSubjEntById",
        method:"post",
        data:{id:$scope.subjEntId}
    }).success(function(data){
        $scope.subjEnt=data.data;
        $scope.subjEnt.area=$.fn.Cityname(data.data.area);
        //根据生产经营主体组织形式展示不同内容
        if($scope.subjEnt.entityScaleName.trim()=="企业/个体工商户"||$scope.subjEnt.entityScaleName.trim()=="合作社"){
            $("#firstContent").show();
            $("#fourthContent").show();
            if($scope.subjEnt.cardType.trim()!="三证合一营业执照（无独立组织机构代码证）"){
                $("#organizationCode").show();
            }
            if($scope.subjEnt.isLong=="true"){
               $("#yinyeshifoushanchangqi").show();
            }
            if($scope.subjEnt.businessOperationStart!=null&&$scope.subjEnt.businessOperationEnd!=null){
                $("#yingyeshijian").show();
            }
        }
        //年产量是否显示
        if($scope.subjEnt.annualOutput==null || $scope.subjEnt.annualOutput=="" ||$scope.subjEnt.annualOutput==undefined){
            $("#zzOutput").hide();
            $("#zzOutput02").hide();
            $("#zzOutput03").hide();
        }
        if($scope.subjEnt.annualOutputX==null || $scope.subjEnt.annualOutputX=="" ||$scope.subjEnt.annualOutputX==undefined){
            $("#xmOutput").hide();
            $("#xmOutput02").hide();
            $("#xmOutput03").hide();
        }
        if($scope.subjEnt.annualOutputS==null || $scope.subjEnt.annualOutputS=="" ||$scope.subjEnt.annualOutputS==undefined){
            $("#scOutput").hide();
            $("#scOutput02").hide();
            $("#scOutput03").hide();
        }
        if($scope.subjEnt.entityScaleName.trim()=="家庭农场"){
            $("#secondContent").show();
            $("#fifthContent").show();
        }
        if($scope.subjEnt.entityScaleName.trim()=="个人"){
            $("#thirdContent").show();
            $("#sixthContent").show();
        }

        //处理图片
        handlePicUrl($scope.subjEnt, ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"]);
        //相关图片显示判断
        if($scope.subjEnt.organizationCertificateimg&&$scope.subjEnt.cardType&&$scope.subjEnt.cardType.trim()!="三证合一营业执照（无独立组织机构代码证）"){//组织机构代码证照
            $("#organizationCertificateimg").append('<a data-fancybox href="'+$scope.subjEnt.organizationCertificateimg+'"><img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/> </a>');
            $("#organizationCertificateimgF").show();
        }else{
            $("#organizationCertificateimgF").hide();
        }
        if($scope.subjEnt.businessLicenceimg){
            $("#businessLicenceimg").append('<a data-fancybox href="'+$scope.subjEnt.businessLicenceimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.businessLicenceimg + '" alt=""/> </a>');
        }else {
            $("#businessLicenceimg").append("<span>无</span>");
        }
        if ($scope.subjEnt.positiveIdcardeimg) {
            $("#positiveIdcardeimg").append('<a data-fancybox href="'+$scope.subjEnt.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        } else {
            $("#positiveIdcardeimg").append("<span>无</span>");
        }
        if ($scope.subjEnt.negativeIdcardimg) {
            $("#negativeIdcardimg").append('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        } else {
            $("#negativeIdcardimg").append("<span>无</span>");
        }
        if ($scope.subjEnt.handIdcardimg) {
            $("#handIdcardimg").append('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        } else {
            $("#handIdcardimg").append("<span>无</span>");
        }
        if ($scope.subjEnt.positiveIdcardeimg) {
            $("#positiveIdcardeimgFarm").append('<a data-fancybox href="'+$scope.subjEnt.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        }else {
            $("#positiveIdcardeimgFarm").append("<span>无</span>");
        }
        if ($scope.subjEnt.negativeIdcardimg) {
            $("#negativeIdcardimgFarm").append('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        }else {
            $("#negativeIdcardimgFarm").append("<span>无</span>");
        }
        if ($scope.subjEnt.handIdcardimg) {
            $("#handIdcardimgFarm").append('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        }else {
            $("#handIdcardimgFarm").append("<span>无</span>");
        }
        if ($scope.subjEnt.positiveIdcardeimg) {
            $("#positiveIdcardeimgPerson").append('<a data-fancybox href="'+$scope.subjEnt.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        }else {
            $("#positiveIdcardeimgPerson").append("<span>无</span>");
        }
        if ($scope.subjEnt.negativeIdcardimg) {
            $("#negativeIdcardimgPerson").append('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        }else {
            $("#negativeIdcardimgPerson").append("<span>无</span>");
        }
        if ($scope.subjEnt.handIdcardimg) {
            $("#handIdcardimgPerson").append('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        }else {
            $("#handIdcardimgPerson").append("<span>无</span>");
        }
        loadGrid();
        //时间戳转成ISO时间格式要加8个小时，即28800000毫秒
        if($scope.subjEnt.businessOperationStart!=null&&$scope.subjEnt.businessOperationEnd!=null){
            $scope.subjEnt.businessOperationStart=new Date(parseInt($scope.subjEnt.businessOperationStart)+28800000).toISOString().slice(0, 10);
            $scope.subjEnt.businessOperationEnd=new Date(parseInt($scope.subjEnt.businessOperationEnd)+28800000).toISOString().slice(0, 10);
        }
        //三品认证信息显示判断
        if ($scope.subjEnt.spybLicType) {
            if ($scope.subjEnt.spybLicType.indexOf("有机认证") != -1) {
                $("#YJShow").show();
                $("#spybPic").show();
            }
            if ($scope.subjEnt.spybLicType.indexOf("绿色认证") != -1) {
                $("#LSShow").show();
                $("#spybPic").show();
            }
            if ($scope.subjEnt.spybLicType.indexOf("无公害认证") != -1) {
                $("#WGHShow").show();
                $("#spybPic").show();
            }
        } else {
            $("#nospybPic").show();
            $("#spybPic").hide();
        }
        $http.post('/sofn-asms-web/subjEnt/findSubjEntAllAuthentication',$.param({id:$scope.subjEnt.id}), {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
                token: window.localStorage.getItem("token")
            }
        }).then(function(res){
            if(res.data.httpCode=="200") {
                $scope.greenFood = res.data.data.greenFood;
                $scope.organicFood = res.data.data.organicFood;
                $scope.pollutionFreeAgricultureProducts = res.data.data.pollutionFreeAgricultureProducts;
            }else{
                jBox.tip(res.data.msg,"info");
            }
        }, function(res){
            
        })
    }).error(function(res) {
        
    });

    //bootstrap表格
    //生产经营主体变更信息
    function loadGrid() {
        $("#changeGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getChangeListByEntityId",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                data: function (params) {
                    params.entityId = $scope.subjEnt.id;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"5%"

                },
                {
                    data : "UPDATE_TIME",
                    title : "变更时间",
                    render:function (data, type, row){
                        if (data != null && data != "" && data != undefined) {
                            return new Date(parseInt(data)+ 28800000).toISOString().slice(0, 10);
                        }
                    },
                    width:"8%"
                },
                {
                    data : "UPDATE_CONTENT",
                    title : "变更内容",
                    width:"60%",
                    render:function (data){
                        if (data) {
                            var arr = data.split(";");
                            var text = ["营业执照变更为", "组织机构代码照变更为", "身份证正面照变更为", "身份证反面照变更为", "手持身份证照变更为","手持身份证变更为","身份证正面变更为","身份证反面变更为"];
                            for (var i = 0; i < arr.length; i++) {
                                var item = arr[i];
                                for (var j = 0; j < text.length; j++) {
                                    var t = text[j];
                                    if (item && item.indexOf(t) !== -1) {
                                        var url = item.substring(item.indexOf(":")+1);
                                        var showUrl = handleOnePicUrl(url) ;
                                        var img = '<a data-fancybox href="'+showUrl+'">点击查看图片</a>';
                                        arr[i] = arr[i].replace(url, img);
                                    }
                                }
                            }
                            return arr.join(";");
                        }
                    }
                },
                {
                    data : "REASON",
                    title : "变更原因",
                }
            ],
        });

        $("#badRecordGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEntBadrecord/getAsmsSubjEntBadrecordByIdList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                data: function (params) {
                    params.enterpriseId = $scope.subjEnt.id;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    width:"5%",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data : "createTime",
                    title : "时间",
                    width:"8%",
                    render:function(data,type,row) {
                        if (data != null && data != "" && data != undefined) {
                            return new Date(parseInt(data)+ 28800000).toISOString().slice(0, 10);
                        }
                    }
                },
                {
                    data : "badrecordContent",
                    title : "不良记录描述",
                    width:"60%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>30){
                            return "<a id='badrecordContent"+row.id+"' onmouseover=showPopContent('badrecordContent"+row.id+"','"+data+"')>"+data.substring(0,30)+"...</a>";
                        }else{
                            return data;
                        }
                    }

                },
                {
                    data:"sourceType",
                    title:"来源",
                    width:"8%",
                    render:function(data,type,row){
                            if(data == "1"){
                                data = "基地巡查"
                            }else if(data == "2"){
                                data = "行政处罚"
                            }else if(data == "3"){
                                data = "检测不合格"
                            } else if(data == "4"){
                                data = "现场巡查"
                            }
                            return data;
                    }
                },
                {
                    data : "badrecordFile",
                    title : "附件",
                    render: function (data, type, row) { // 模板化列显示内容
                        var subjEntsFileurls = [];
                        var subjEntsFileNames = [];
                        if(row.badrecordFile!=null&&row.badrecordFile!=""&&row.badrecordFile!=undefined) {
                            subjEntsFileurls = row.badrecordFile.split("*##*");
                            if (row.badrecordFileName) {
                                subjEntsFileNames = row.badrecordFileName.split("*##*");
                            }
                        }
                        var butt = "";
                        for(var i=0;i<subjEntsFileurls.length;i++){
                            if (!subjEntsFileNames[i]) {
                                break;
                            }
                            butt += '<a style="cursor: pointer" class="Check-report"  onclick=downloadFiles(\''+subjEntsFileurls[i]+'\',\''+subjEntsFileNames[i].replace(/\s+/g,"")+'\')>'+subjEntsFileNames[i]+'</a>';//转义传值
                            if(i!=subjEntsFileurls.length-1){
                                butt += ',';
                            }
                        }
                        return butt;
                    }
                }
            ]
        });
    }
    //附件的下载
    window.downloadFiles=function(fileUrl,fileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', decodeURI(fileUrl));
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', decodeURI(fileName));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjEntId");
        if ($stateParams.source && $stateParams.source == 'sys') {
            $state.go("index.content.sys/user/subjEntList");
        }else{
            $state.go("index.content.asms/subjEnt/subjEntList");
        }
    }

    //设置点击放大
    $(".thisUrl").click(function(){
        $scope.thissrc = $(this).attr("src");
        $(".Sup_contain_section_img img").attr("src",$scope.thissrc);
        $(".Sup_contain_section_img").css("top","0%");
        $("body").css("overflow","hidden");
    });
    $(".Sup_contain_section_img span").click(function(){
        $(".Sup_contain_section_img").css("top","-10000%");
        $("body").css("overflow","visible");
    });
    //打印
    $scope.printInfo = function(){
        //判断打印显示的三品一标信息
        $("#spybPic").hide();
        if ($scope.subjEnt.spybLicType) {
            if ($scope.subjEnt.spybLicType.indexOf("有机认证") != -1) {
                $("#YJPrint").show();
                $("#spybPrint").show();
            }
            if ($scope.subjEnt.spybLicType.indexOf("绿色认证") != -1) {
                $("#LSPrint").show();
                $("#spybPrint").show();
            }
            if ($scope.subjEnt.spybLicType.indexOf("无公害认证") != -1) {
                $("#WGHPrint").show();
                $("#spybPrint").show();
            }
        } else {
            $("#nospybPic").show();
            $("#spybPic").hide();
        }
        //图片去除href标签打印
        $("#businessLicenceimg").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.businessLicenceimg + '" alt=""/>');
        if($scope.subjEnt.cardType.trim()==="三证合一营业执照（无独立组织机构代码证）" || !$scope.subjEnt.organizationCertificateimg){//组织机构代码证照
            $("#organizationCertificateimgF").hide();
        }else{
            $("#organizationCertificateimg").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/>');
            $("#organizationCertificateimgF").show();
        }
        $("#positiveIdcardeimg").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
        $("#negativeIdcardimg").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
        $("#handIdcardimg").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
        $("#positiveIdcardeimgFarm").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
        $("#negativeIdcardimgFarm").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
        $("#handIdcardimgFarm").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
        $("#positiveIdcardeimgPerson").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
        $("#negativeIdcardimgPerson").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
        $("#handIdcardimgPerson").html('<img class="ml15" style="height: 100px;width: 165px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
        $("#badRecordGrid_length").hide();
        $("#badRecordGrid_info").hide();
        $("#changeGrid_length").hide();
        $("#changeGrid_info").hide();
        $("#changeGrid_paginate").hide();
        $("#badRecordGrid_paginate").hide();
        $(".Sup_contain_section_contain_show_Linkman").each(function(i, o){
            $(o).addClass("mt15");
        })
        $("#printContent").printArea();
        ////图片恢复去除的href标签打印
        if($scope.subjEnt.cardType.trim()==="三证合一营业执照（无独立组织机构代码证）" || !$scope.subjEnt.organizationCertificateimg){//组织机构代码证照
            $("#organizationCertificateimgF").hide();
        }else{
            $("#organizationCertificateimg").html('<a data-fancybox href="'+$scope.subjEnt.organizationCertificateimg+'"><img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/> </a>');
            $("#organizationCertificateimgF").show();
        }
        $("#businessLicenceimg").html('<a data-fancybox href="'+$scope.subjEnt.businessLicenceimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.businessLicenceimg + '" alt=""/> </a>');
        // $("#organizationCertificateimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/>');
        $("#positiveIdcardeimg").html('<a data-fancybox href="'+$scope.subjEnt.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimg").html('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimg").html('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        $("#positiveIdcardeimgFarm").html('<a data-fancybox href="'+$scope.subjEnt.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimgFarm").html('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimgFarm").html('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        $("#positiveIdcardeimgPerson").html('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimgPerson").html('<a data-fancybox href="'+$scope.subjEnt.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimgPerson").html('<a data-fancybox href="'+$scope.subjEnt.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        $("#badRecordGrid_length").show();
        $("#badRecordGrid_info").show();
        $("#changeGrid_length").show();
        $("#changeGrid_info").show();
        $("#changeGrid_paginate").show();
        $("#badRecordGrid_paginate").show();
        $("#spybPic").show();
        $("#spybPrint").hide();
    }
    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
        $("#"+id).popover({
            animation:true,
            html:false,
            placement:'right',
            trigger:'hover',
            content:content,
            delay:0,
            container:'body'
        });
        $("#"+id).popover("show");
    }
    //三品一标模态框
    $scope.openModal = function (event) {
        $("#modalTitle").html($(event.target).attr("title")+"详情");
        var type = $(event.target).data("type");
        $(".modal-body").hide();
        if (!$scope.subjEnt || !$scope.subjEnt.id) {
            return;
        }
        $http.post('/sofn-asms-web/subjEnt/findSubjEntAuthenticationList',$.param({id:$scope.subjEnt.id, type: type}), {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
                token: window.localStorage.getItem("token")
            }
        }).then(function(res){
            if(res.data.httpCode=="200") {
                if (type == "YJ") {
                    $scope.organicFoodList = res.data.data;
                    $("#organicFoodContent").html("");
                    for (var i = 0; i < $scope.organicFoodList.length; i++) {
                        $scope.organicFoodList[i].recTime = timestampToDate($scope.organicFoodList[i].recTime);
                        $scope.organicFoodList[i].validity = timestampToDate($scope.organicFoodList[i].validity);
                        $scope.checkData($scope.organicFoodList[i]);
                        var row = '<div class="field-head field-head-90" onclick="showEntityInfo(this)">' +
                            '<span class="gui-icon icon-khxx"></span><span class="field-title"> 认证信息'+(i+1)+'</span>' +
                            '</div>';
                        row += '<table style="display: none" class="gui-table mt15 ml15">' +
                                '<tr><td width="30%">企业编号</td><td>'+$scope.organicFoodList[i].entBaseId+'</td></tr>' +
                                '<tr><td width="30%" style="color: red">企业名称</td><td style="color: red">'+$scope.organicFoodList[i].entName+'</td></tr>'+
                                '<tr><td>曾用名</td><td>'+$scope.organicFoodList[i].entOldname+'</td></tr>'+
                                '<tr><td style="color: red">组织机构代码</td><td style="color: red">'+$scope.organicFoodList[i].orgCode+'</td></tr>'+
                                '<tr><td>企业注册办公地址</td><td>'+$scope.organicFoodList[i].entAddr+'</td></tr>'+
                                '<tr><td>企业邮编</td><td>'+$scope.organicFoodList[i].entPostCode+'</td></tr>'+
                                '<tr><td>企业电话</td><td>'+$scope.organicFoodList[i].entTel+'</td></tr>'+
                                '<tr><td>企业传真</td><td>'+$scope.organicFoodList[i].fax+'</td></tr>'+
                                '<tr><td>企业电子邮箱</td><td>'+$scope.organicFoodList[i].entEmail+'</td></tr>'+
                                '<tr><td>注册资本</td><td>'+$scope.organicFoodList[i].regCap+'</td></tr><tr>' +
                                '<td>员工数</td><td>'+$scope.organicFoodList[i].staffNum+'</td></tr>'+
                                '<tr><td>技术人员数</td><td>'+$scope.organicFoodList[i].technicistNum+'</td></tr>'+
                                '<tr><td style="color: red">法人姓名</td><td style="color: red">'+$scope.organicFoodList[i].legRep+'</td></tr>'+
                                '<tr><td>法人手机</td><td>'+$scope.organicFoodList[i].legRepMobile+'</td></tr>'+
                                '<tr><td>法人电话</td><td>'+$scope.organicFoodList[i].legRepTel+'</td></tr>'+
                                '<tr><td>联系人姓名</td><td>'+$scope.organicFoodList[i].linkman+'</td></tr>'+
                                '<tr><td>联系人手机</td><td>'+$scope.organicFoodList[i].linkmanMobile+'</td></tr>'+
                                '<tr><td>联系人电话</td><td>'+$scope.organicFoodList[i].linkmanTel+'</td></tr>'+
                                '<tr><td>邮寄联系人</td><td>'+$scope.organicFoodList[i].mailer+'</td></tr>'+
                                '<tr><td>邮寄联系人手机</td><td>'+$scope.organicFoodList[i].mailerMobile+'</td></tr>'+
                                '<tr><td>邮寄联系人电话</td><td>'+$scope.organicFoodList[i].mailerTel+'</td></tr>'+
                                '<tr><td>邮寄地址</td><td>'+$scope.organicFoodList[i].mailAddr+'</td></tr>'+
                                '<tr><td>邮寄邮编</td><td>'+$scope.organicFoodList[i].postCode+'</td></tr>'+
                                '<tr><td>销售额</td><td>'+$scope.organicFoodList[i].sales+'</td></tr>'+
                                '<tr><td>出口额</td><td>'+$scope.organicFoodList[i].exportAm+'</td></tr>'+
                                '<tr><td>行政区划编号</td><td>'+$scope.organicFoodList[i].xzqhId+'</td></tr>'+
                                '<tr><td>记录时间</td><td>'+$scope.organicFoodList[i].recTime+'</td></tr>'+
                                '<tr><td width="30%">产品名称</td><td>'+$scope.organicFoodList[i].prodName+'</td></tr>'+
                                '<tr><td>产品描述</td><td>'+$scope.organicFoodList[i].prodDesc+'</td></tr>'+
                                '<tr><td>证书编号</td><td>'+$scope.organicFoodList[i].certNo+'</td></tr>'+
                                '<tr><td>产品编号</td><td>'+$scope.organicFoodList[i].prodId+'</td></tr>'+
                                '<tr><td>批准产量</td><td>'+$scope.organicFoodList[i].output+'</td></tr>'+
                                '<tr><td>许可期限</td><td>'+$scope.organicFoodList[i].validity+'</td></tr>'+
                                '<tr><td>颁证机构</td><td>'+$scope.organicFoodList[i].approver+'</td></tr>'+
                                '<tr><td>颁证日期</td><td>'+$scope.organicFoodList[i].approveDate+'</td></tr>'+
                                '</table>'
                        $("#organicFoodContent").append(row);
                    }
                    $("#organicFood").show();
                    $("#organicFoodContent table").eq(0).slideToggle(400);
                    $("#organicFoodContent table").eq(0).toggleClass("field-head-90");
                } else if (type == "LS") {
                    $scope.greenFoodList = res.data.data;
                    $("#greenFoodContent").html("");
                    for (var i = 0; i < $scope.greenFoodList.length; i++ ) {
                        $scope.greenFoodList[i].issueDate = timestampToDate($scope.greenFoodList[i].issueDate);
                        $scope.greenFoodList[i].licDeadlineBeg = timestampToDate($scope.greenFoodList[i].licDeadlineBeg);
                        $scope.greenFoodList[i].licDeadlineEnd = timestampToDate($scope.greenFoodList[i].licDeadlineEnd);
                        $scope.checkData($scope.greenFoodList[i]);
                        var row = '<div class="field-head field-head-90" onclick="showEntityInfo(this)">' +
                            '<span class="gui-icon icon-khxx"></span><span class="field-title"> 认证信息'+(i+1)+'</span>' +
                            '</div>';
                        row += '<table style="display: none" class="gui-table mt15 ml15">' +
                                '<tr class=""><td width="30%" style="color: red">企业名称</td><td style="color: red">'+$scope.greenFoodList[i].entNameCn+'</td></tr>' +
                                '<tr><td style="color: red">统一社会信用代码</td><td style="color: red">'+$scope.greenFoodList[i].unifiedInfo+'</td></tr>'+
                                '<tr><td>注册地址</td><td>'+$scope.greenFoodList[i].entRegAdd+'</td></tr>'+
                                '<tr><td>企业传真</td><td>'+$scope.greenFoodList[i].entFax+'</td></tr>'+
                                '<tr><td>企业邮箱</td><td>'+$scope.greenFoodList[i].entEmail+'</td></tr>'+
                                '<tr><td>联系地址</td><td>'+$scope.greenFoodList[i].entCommAdd+'</td></tr>'+
                                '<tr><td>联系人</td><td>'+$scope.greenFoodList[i].contactPerson+'</td></tr>'+
                                '<tr><td>联系电话</td><td>'+$scope.greenFoodList[i].contactPersonPhone+'</td></tr>'+
                                '<tr><td>省绿办</td><td>'+$scope.greenFoodList[i].grOrgName+'</td></tr>'+
                                '<tr><td style="color: red">法定代表人／企业负责人</td><td style="color: red">'+$scope.greenFoodList[i].legalPerson+'</td></tr>'+
                                '<tr><td>法人手机号</td><td>'+$scope.greenFoodList[i].legalPersonMobile+'</td></tr>'+
                                '<tr><td>产品名称</td><td>'+$scope.greenFoodList[i].proNameCn+'</td></tr>'+
                                '<tr><td>产品编号</td><td>'+$scope.greenFoodList[i].licNo+'</td></tr>'+
                                '<tr><td>企业信息码</td><td>'+$scope.greenFoodList[i].entInfoCode+'</td></tr>'+
                                '<tr><td>生产商</td><td>'+$scope.greenFoodList[i].entManuCn+'</td></tr>'+
                                '<tr><td>批准产量</td><td>'+$scope.greenFoodList[i].actAnnProSca+'</td></tr>'+
                                '<tr><td>证书开始日期</td><td>'+$scope.greenFoodList[i].licDeadlineBeg+'</td></tr>'+
                                '<tr><td>证书截止日期</td><td>'+$scope.greenFoodList[i].licDeadlineEnd+'</td></tr>'+
                                '<tr><td>颁证机构</td><td>'+$scope.greenFoodList[i].banzhengjigou+'</td></tr>'+
                                '<tr><td>颁证日期</td><td>'+$scope.greenFoodList[i].issueDate+'</td></tr>' +
                                '</table>'
                        $("#greenFoodContent").append(row);
                    }
                    $("#greenFood").show();
                    $("#greenFoodContent table").eq(0).slideToggle(400);
                    $("#greenFoodContent table").eq(0).toggleClass("field-head-90");
                } else if (type == "WGH") {
                    $scope.pfaProList = res.data.data;
                    $("#pfaProContent").html("");
                    for (var i = 0; i < $scope.pfaProList.length; i++) {
                        $scope.pfaProList[i].certificateEnddate = timestampToDate($scope.pfaProList[i].certificateEnddate);
                        $scope.pfaProList[i].checkTime = timestampToDate($scope.pfaProList[i].checkTime);
                        $scope.pfaProList[i].certificateBegindat = timestampToDate($scope.pfaProList[i].certificateBegindat);
                        $scope.checkData($scope.pfaProList[i]);
                        var row = '<div class="field-head field-head-90" onclick="showEntityInfo(this)">' +
                            '<span class="gui-icon icon-khxx"></span><span class="field-title"> 认证信息'+(i+1)+'</span>' +
                            '</div>';
                        row += '<table style="display: none" class="gui-table mt15 ml15">' +
                                '<tr><td width="30%">申请人全称</td><td>'+$scope.pfaProList[i].applicanter+'</td></tr>'+
                                '<tr><td>单位性质</td><td>'+$scope.pfaProList[i].corpType+'</td></tr>'+
                                '<tr><td style="color: red">法定代表人</td><td style="color: red">'+$scope.pfaProList[i].legalPerson+'</td></tr>'+
                                '<tr><td>法人手机</td><td>'+$scope.pfaProList[i].legalPersonMobile+'</td></tr>'+
                                '<tr><td>联系人</td><td>'+$scope.pfaProList[i].linkPerson+'</td></tr>'+
                                '<tr><td>联系人手机</td><td>'+$scope.pfaProList[i].linkPersonMobile+'</td></tr>'+
                                '<tr><td>传真</td><td>'+$scope.pfaProList[i].fax+'</td></tr>'+
                                '<tr><td>email</td><td>'+$scope.pfaProList[i].email+'</td></tr>'+
                                '<tr><td>申请人地址</td><td>'+$scope.pfaProList[i].applicantAdd+'</td></tr>'+
                                '<tr><td style="color: red">获证单位（主体名称）</td><td style="color: red">'+$scope.pfaProList[i].applicantFullname+'</td></tr>'+
                                '<tr><td>产地地址</td><td>'+$scope.pfaProList[i].productingareaAdd+'</td></tr>'+
                                '<tr><td>产品名称</td><td>'+$scope.pfaProList[i].productName+'</td></tr>'+
                                '<tr><td>批准产量</td><td>'+$scope.pfaProList[i].annualOutput+'</td></tr>'+
                                '<tr><td>证书编号</td><td>'+$scope.pfaProList[i].certificateId+'</td></tr>'+
                                '<tr><td>有效开始日期</td><td>'+$scope.pfaProList[i].certificateBegindat+'</td></tr>'+
                                '<tr><td>有效截止日期</td><td>'+$scope.pfaProList[i].certificateEnddate+'</td></tr>'+
                                '<tr><td>获证时间</td><td>'+$scope.pfaProList[i].checkTime+'</td></tr>'+
                                '</table>'
                        $("#pfaProContent").append(row);
                    }
                    $("#pollutionFreeAgricultureProducts").show();
                    $("#pfaProContent table").eq(0).slideToggle(400);
                    $("#pfaProContent table").eq(0).toggleClass("field-head-90");
                } else if (type == "DL") {
                    //暂未实现
                    // $scope.agricultureProductsGeography = res.data.data;
                    // $("#agricultureProductsGeography").show();
                }
                $("#threeProductsAndOneStandard").modal("show");
            }else{
                jBox.tip(res.data.msg,"info");
            }
        }, function(res){
            
        })

    }

    window.showEntityInfo = function (target) {
        $(target).next().slideToggle(400);
        $(target).toggleClass("field-head-90");
    }
    $scope.timestampToDate = function(timestamp) {
        if (!timestamp) {
            return "";
        }
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate();
        return Y+M+D;
    }
    //检查json对象值是否为空
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }

})