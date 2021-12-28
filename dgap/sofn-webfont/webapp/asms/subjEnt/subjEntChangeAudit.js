var myapp=angular.module("myapp",[]).controller("subjEntChangeAudit",function($scope,$http,$state){
    $scope.subjEnt = {};
    $scope.subjEntChange = {};
    //获取本地存储的ID
    $scope.subjEntChangeId=window.localStorage.getItem("subjEntChangeId");
    $('#changeForm').bootstrapValidator();//初始化验证，必须有

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnt/findSubjEntChangeById",
        method:"post",
        data:{id:$scope.subjEntChangeId}
    }).success(function(data){
        $scope.subjEntChange=data.subjEntChange;
        $scope.subjEnt=JSON.parse(data.subjEntChange.updateBeforeJson);
        //组织形式不同显示不同
        if($scope.subjEnt.entityScaleName.trim()=="企业/个体工商户"){
            $("#qygt").show();
        }
        if($scope.subjEnt.entityScaleName.trim()=="合作社"){
            $("#hezuoshe").show();
        }
        if($scope.subjEnt.entityScaleName.trim()=="家庭农场"){
            $("#jiatingnongchang").show();
        }
        if($scope.subjEnt.entityScaleName.trim()=="个人"){
            $("#geren").show();
        }
        if($scope.subjEnt.cardType!=null && $scope.subjEnt.cardType!="" && $scope.subjEnt.cardType!=undefined){
            if($scope.subjEnt.cardType.trim()!="三证合一营业执照（无独立组织机构代码证）"){
                $("#organizationCodeD01").show();
                $("#organizationCodeD02").show();
            }
        }
        //是否长期转换
        if($scope.subjEnt.isLong=='true'){
            $scope.subjEnt.isLong='是';
        } else{
            $scope.subjEnt.isLong='否';
        }
        $scope.subjEnt.areaName=$.fn.Cityname($scope.subjEnt.area);
        $scope.subjEntChange.areaName=$.fn.Cityname($scope.subjEntChange.area);
        //是否长期转换
        if($scope.subjEntChange.isLong=='true'){
            $scope.subjEntChange.isLong='是';
        }else{
            $scope.subjEntChange.isLong='否';
        }
        //营业时间转成时间格式
        if($scope.subjEnt.businessOperationStart!=null&&$scope.subjEnt.businessOperationStart!=""&&$scope.subjEnt.businessOperationStart!=undefined) {
            $scope.subjEnt.businessOperationStart = new Date(parseInt($scope.subjEnt.businessOperationStart) + 28800000).toISOString().slice(0, 10);
        }
        if($scope.subjEnt.businessOperationEnd!=null&&$scope.subjEnt.businessOperationEnd!=""&&$scope.subjEnt.businessOperationEnd!=undefined) {
            $scope.subjEnt.businessOperationEnd = new Date(parseInt($scope.subjEnt.businessOperationEnd) + 28800000).toISOString().slice(0, 10);
        }
        if($scope.subjEntChange.businessOperationStart!=null&&$scope.subjEntChange.businessOperationStart!=""&&$scope.subjEntChange.businessOperationStart!=undefined) {
            $scope.subjEntChange.businessOperationStart = new Date(parseInt($scope.subjEntChange.businessOperationStart) + 28800000).toISOString().slice(0, 10);
        }
        if($scope.subjEntChange.businessOperationEnd!=null&&$scope.subjEntChange.businessOperationEnd!=""&&$scope.subjEntChange.businessOperationEnd!=undefined) {
            $scope.subjEntChange.businessOperationEnd = new Date(parseInt($scope.subjEntChange.businessOperationEnd) + 28800000).toISOString().slice(0, 10);
        }
        //审核意见默认为空
        //$("textarea[name='approveOpinion']").

        if($scope.subjEntChange.approveStatus=="1"||$scope.subjEntChange.approveStatus=="2"){
            $("textarea[name='approveOpinion']").attr("readonly","readonly");
        }else{
            //待审核状态审核意见默认为空
            //$("textarea[name='approveOpinion']").val("");
            $scope.subjEntChange.approveOpinion="";
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        //处理图片
        handlePicUrl($scope.subjEnt, ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"]);
        handlePicUrl($scope.subjEntChange, ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"]);
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjEntId");
        $state.go("index.content.asms/subjEnt/subjEntChangeList");
    }
    //审核生产经营主体变更申请
    $scope.auditSubjEntChange=function(auditState){
        // 表单验证
        if (!$("#changeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //变更后图片处理
        if($scope.subjEntChange.businessLicenceimg&&$scope.subjEntChange.businessLicenceimg.indexOf("/sofn-sys-web/sysTemplate/downPic")!=-1){
            $scope.subjEntChange.businessLicenceimg = $scope.subjEntChange.businessLicenceimg.substring("/sofn-sys-web/sysTemplate/downPic?fileUrl=".length);
        }
        if($scope.subjEntChange.positiveIdcardeimg&&$scope.subjEntChange.positiveIdcardeimg.indexOf("/sofn-sys-web/sysTemplate/downPic")!=-1){
            $scope.subjEntChange.positiveIdcardeimg = $scope.subjEntChange.positiveIdcardeimg.substring("/sofn-sys-web/sysTemplate/downPic?fileUrl=".length);
        }
        if($scope.subjEntChange.negativeIdcardimg&&$scope.subjEntChange.negativeIdcardimg.indexOf("/sofn-sys-web/sysTemplate/downPic")!=-1){
            $scope.subjEntChange.negativeIdcardimg = $scope.subjEntChange.negativeIdcardimg.substring("/sofn-sys-web/sysTemplate/downPic?fileUrl=".length);
        }
        if($scope.subjEntChange.handIdcardimg&&$scope.subjEntChange.handIdcardimg.indexOf("/sofn-sys-web/sysTemplate/downPic")!=-1){
            $scope.subjEntChange.handIdcardimg = $scope.subjEntChange.handIdcardimg.substring("/sofn-sys-web/sysTemplate/downPic?fileUrl=".length);
        }
        if($scope.subjEntChange.organizationCertificateimg&&$scope.subjEntChange.organizationCertificateimg.indexOf("/sofn-sys-web/sysTemplate/downPic")!=-1){
            $scope.subjEntChange.organizationCertificateimg = $scope.subjEntChange.organizationCertificateimg.substring("/sofn-sys-web/sysTemplate/downPic?fileUrl=".length);
        }
        //必须有
        $scope.subjEntChange.approveStatus=auditState;
        $scope.subjEntChange.approveType="2";//代表变更待审核-1-注销-3-撤销
        $scope.subjEntChange.approveOpinion=$("textarea[name='approveOpinion']").val();//审核意见
        //是否长期转换
        if($scope.subjEntChange.isLong=='是'){
            $scope.subjEntChange.isLong='true';
        }
        if($scope.subjEntChange.isLong=='否'){
            $scope.subjEntChange.isLong='false';
        }
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/auditSubjEntChange",
            method:"post",
            data:$scope.subjEntChange
        }).success(function(data){
            $("button").attr("disabled",false);
            if (data.httpCode == 200) {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjEntId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjEnt/subjEntChangeList");
                },500)
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function (data) {
            $("button").attr("disabled",false);
             
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
})