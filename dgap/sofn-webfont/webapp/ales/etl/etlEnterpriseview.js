/**
 * Created by Administrator on 2017/4/12.
 */
var myapp=angular.module("myapp",[]).controller("etlEnterpriseview",function($scope,$http,$stateParams, $state){
    //获取本地存储的ID
    $scope.subjEntId = window.localStorage.getItem("dELE_EnterpriseId");
    $scope.subjEntIds = window.localStorage.getItem("dELE_EnterpriseIds");
    $scope.TAGinfo= window.localStorage.getItem("TAGinfo");
    $scope.subjEnt={};
    $scope.subjEntTemp={};
    //获取生产经营主体信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/dailyEnforceLaw/findSubjEntById",
        method:"post",
        data:{id:$scope.subjEntId}
    }).success(function(data){
        if(data.data==null||data.data==""||data.data==undefined){
          $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url:"/sofn-ales-web/subjEnt/findSubjEntTempById",
                method:"post",
                //data:$scope.ID
                data:{id:$scope.subjEntId}
            }).success(function(data){
                $scope.subjEntTemp = data.data;
                console.log("地址=："+$scope.subjEntTemp.areaId);
                console.log("id："+$scope.subjEntId);
                if($scope.subjEntTemp.area!=null&&$scope.subjEntTemp.areaId!=""&&$scope.subjEntTemp.areaId!=undefined){
                    $scope.subjEntTemp.area =$.fn.Cityname($scope.subjEntTemp.areaId);
                }else {
                    $scope.subjEntTemp.areaId=null;
                }
                $("#sevenContent").show();
                //$("#enitContent").show();
            }).error(function(data){
               
            });
        }else {
            $scope.subjEnt = data.data;
            if ($scope.subjEnt.area != null && $scope.subjEnt.area != "" && $scope.subjEnt.area != undefined) {
                $scope.area = $.fn.Cityname($scope.subjEnt.area);
            }
            //根据生产经营主体组织形式展示不同内容
            if ($scope.subjEnt.entityScaleName.trim() == "企业/个体工商户" || $scope.subjEnt.entityScaleName.trim() == "合作社") {
                $("#firstContent").show();
                $("#fourthContent").show();
                if ($scope.subjEnt.cardType.trim() != "三证合一营业执照（无独立组织机构代码证）") {
                    $("#organizationCode").show();
                }
            }
            if ($scope.subjEnt.entityScaleName.trim() == "家庭农场") {
                $("#secondContent").show();
                $("#fifthContent").show();
            }
            if ($scope.subjEnt.entityScaleName.trim() == "个人") {
                $("#thirdContent").show();
                $("#sixthContent").show();
            }
            //相关图片显示判断

            if($scope.subjEnt.businessLicenceimg!=null&&$scope.subjEnt.businessLicenceimg!=undefined&&!$scope.subjEnt.businessLicenceimg.startsWith("/sofn-ales-web/sysTemplate/downPic")){
                $scope.subjEnt.businessLicenceimg = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.subjEnt.businessLicenceimg;
            }
            if($scope.subjEnt.positiveIdcardeimg!=null&&$scope.subjEnt.positiveIdcardeimg!=undefined&&!$scope.subjEnt.positiveIdcardeimg.startsWith("/sofn-ales-web/sysTemplate/downPic")){
                $scope.subjEnt.positiveIdcardeimg = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.subjEnt.positiveIdcardeimg;
            }
            if($scope.subjEnt.negativeIdcardimg!=null&&$scope.subjEnt.negativeIdcardimg!=undefined&&!$scope.subjEnt.negativeIdcardimg.startsWith("/sofn-ales-web/sysTemplate/downPic")){
                $scope.subjEnt.negativeIdcardimg = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.subjEnt.negativeIdcardimg;
            }
            if($scope.subjEnt.handIdcardimg!=null&&$scope.subjEnt.handIdcardimg!=undefined&&!$scope.subjEnt.handIdcardimg.startsWith("/sofn-ales-web/sysTemplate/downPic")){
                $scope.subjEnt.handIdcardimg = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.subjEnt.handIdcardimg;
            }
            if($scope.subjEnt.organizationCertificateimg!=null&&$scope.subjEnt.organizationCertificateimg!=undefined&&!$scope.subjEnt.organizationCertificateimg.startsWith("/sofn-ales-web/sysTemplate/downPic")){
                $scope.subjEnt.organizationCertificateimg = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.subjEnt.organizationCertificateimg;
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

            if($scope.subjEnt.organizationCertificateimg==null ||$scope.subjEnt.organizationCertificateimg=='' ||$scope.subjEnt.organizationCertificateimg==undefined){//组织机构代码证照
                $("#organizationCertificateimgF").hide();
            }else{
                $("#organizationCertificateimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/>');
                $("#organizationCertificateimgF").show();
            }
            //处理图片
            $("#businessLicenceimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.businessLicenceimg + '" alt=""/>');
            // $("#organizationCertificateimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/>');
            $("#positiveIdcardeimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
            $("#negativeIdcardimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
            $("#handIdcardimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
            $("#positiveIdcardeimgFarm").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
            $("#negativeIdcardimgFarm").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
            $("#handIdcardimgFarm").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
            $("#positiveIdcardeimgPerson").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/>');
            $("#negativeIdcardimgPerson").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/>');
            $("#handIdcardimgPerson").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/>');
            //时间戳转成ISO时间格式要加8个小时，即28800000毫秒
            if ($scope.subjEnt.businessOperationStart != null && $scope.subjEnt.businessOperationStart != "" && $scope.subjEnt.businessOperationStart != undefined) {
                $scope.subjEnt.businessOperationStart = new Date(parseInt($scope.subjEnt.businessOperationStart) + 28800000).toISOString().slice(0, 10);
            }
            if ($scope.subjEnt.businessOperationEnd != null && $scope.subjEnt.businessOperationEnd != "" && $scope.subjEnt.businessOperationEnd != undefined) {
                $scope.subjEnt.businessOperationEnd = new Date(parseInt($scope.subjEnt.businessOperationEnd) + 28800000).toISOString().slice(0, 10);
            }
        }

    }).error(function(data){
        console.log(data);
        
    });
    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("dELE_EnterpriseId");
        if($scope.TAGinfo == "1"){
            window.localStorage.setItem("motaikuangBiaoshi",$scope.TAGinfo);
        }else if($scope.TAGinfo == "2"){
            window.localStorage.setItem("motaikuangBiaoshi",$scope.TAGinfo);
        }
        $state.go("index.content.ales/etl/etlDailyEnforceLawlist");
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