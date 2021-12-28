var myapp=angular.module("myapp",[]).controller("subjEntCancelAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjEntCancelId=window.localStorage.getItem("subjEntCancelId");
    $('#cancelForm').bootstrapValidator();//初始化验证，必须有

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnt/findSubjEntChangeById",
        method:"post",
        data:{id:$scope.subjEntCancelId}
    }).success(function(data){
        $scope.subjEntCancel=data.subjEntChange;
        // $scope.subjEnt=JSON.parse(data.subjEntChange.updateBeforeJson);
        $scope.subjEnt=$scope.subjEntCancel;
        $scope.subjEnt.area=$.fn.Cityname($scope.subjEnt.area);
        if($scope.subjEntCancel.approveStatus=="1"||$scope.subjEntCancel.approveStatus=="2"){
            $("textarea[name='approveOpinion']").attr("readonly","readonly");
        }else{
            //待审核状态下审核意见默认为空
            $scope.subjEntCancel.approveOpinion="";
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        //营业期限长期与否的正确显示
        if($scope.subjEnt.isLong=='true'){
        //长期
            $("#isTrueLong").show();
            $("#isNotLong").hide();
        }else{
            //非长期
            $("#isNotLong").show();
            $("#isTrueLong").hide();
        }

        //根据生产经营主体组织形式展示不同内容
        if($scope.subjEnt.entityScaleName.trim()=="企业/个体工商户"||$scope.subjEnt.entityScaleName.trim()=="合作社"){
            $("#firstContent").show();
            $("#fourthContent").show();
            if($scope.subjEnt.cardType.trim()!="三证合一营业执照（无独立组织机构代码证）"){
                $("#organizationCode").show();
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
        if($scope.subjEntCancel.entityScaleName.trim()=="个人"){
            $("#thirdContent").show();
            $("#sixthContent").show();
        }
        //三品认证信息显示判断
        if ($scope.subjEntCancel.spybLicType) {
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
        //显示附件
        if($scope.subjEntCancel.documentImages!=null&&$scope.subjEntCancel.documentImages!=""&&$scope.subjEntCancel.documentImages!=undefined){
            $("#cancelFile").show();
            $("#noFile").hide();
        }else{
            $("#noFile").show();
            $("#cancelFile").hide();
        }
        // 处理图片
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
        }        //时间戳转成ISO时间格式要加8个小时，即28800000毫秒
        if($scope.subjEnt.businessOperationStart!=null&&$scope.subjEnt.businessOperationEnd!=null){
            $scope.subjEnt.businessOperationStart=new Date(parseInt($scope.subjEnt.businessOperationStart)+28800000).toISOString().slice(0, 10);
            $scope.subjEnt.businessOperationEnd=new Date(parseInt($scope.subjEnt.businessOperationEnd)+28800000).toISOString().slice(0, 10);
        }
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjEntCancelId");
        $state.go("index.content.asms/subjEnt/subjEntCancelList");
    }

    //审核生产经营主体注销申请
    $scope.auditSubjEntCancel=function(auditState){
        if (!$("#cancelForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjEntCancel.approveStatus=auditState;
        $scope.subjEntCancel.approveType="1";//代表变更待审核-3-撤销-2-变更
        $scope.subjEntCancel.approveOpinion=$("textarea[name='approveOpinion']").val();//审核意见
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/auditSubjEntChange",
            method:"post",
            data:$scope.subjEntCancel
        }).success(function(data){
            $("button").attr("disabled",false);
            if (data.httpCode == 200) {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjEntCancelId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjEnt/subjEntCancelList");
                },500)
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function (data) {
            
        });
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


    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        if($scope.subjEntCancel.documentImages!=null&&$scope.subjEntCancel.documentImages!=undefined&&$scope.subjEntCancel.documentImages.indexOf("//202.127.45.189")>=0){
            form.attr('action', "http://202.127.45.186/sofn-sys-web/sysTemplate/dowload");
        }else{
            form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        }
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.subjEntCancel.documentImages);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', "附件"+$scope.subjEntCancel.documentImages.substr($scope.subjEntCancel.documentImages.lastIndexOf(".")));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //三品一标模态框
    $scope.openModal = function (event) {
        $("#modalTitle").html($(event.target).attr("title")+"详情");
        var type = $(event.target).data("type");
        $(".modal-body").hide();
        if (!$scope.subjEnt || !$scope.subjEnt.id) {
            return;
        }
        $http.post('/sofn-asms-web/subjEnt/findSubjEntAuthenticationList',$.param({id:$scope.subjEntCancel.entityId, type: type}), {
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