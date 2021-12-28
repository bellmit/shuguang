var myapp=angular.module("myapp",[]).controller("subjEntTempView",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjEntTempId=window.localStorage.getItem("subjEntTempId");

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnt/findSubjEntTempById",
        method:"post",
        data:{id:$scope.subjEntTempId}
    }).success(function(data){
        $scope.subjEntTemp=data.data;
        $scope.subjEntTemp.areaId=$.fn.Cityname(data.data.areaId);
        //证件类型显示
        if(!$scope.subjEntTemp.credType){
            $("#credType").hide();
        }
        if(!$scope.subjEntTemp.organizationCode){
           $("#organizationCode").hide();
        }
        //处理图片
        //处理图片
        handlePicUrl($scope.subjEntTemp, ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"]);
        //相关图片显示判断
        //企业证件照显示
        if(!$scope.subjEntTemp.businessLicenceimg){
            $("#businessLicenceimgF").hide();
        }else{
            $("#businessLicenceimgF").append('<a data-fancybox href="'+$scope.subjEntTemp.businessLicenceimg+'"><img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.businessLicenceimg + '" alt=""/> </a>');
            $("#businessLicenceimgF").show();
        }
        //组织机构照显示
        if(!$scope.subjEntTemp.organizationCertificateimg){//组织机构代码证照
            $("#organizationCertificateimgF").hide();
        }else{
            $("#organizationCertificateimg").append('<a data-fancybox href="'+$scope.subjEntTemp.organizationCertificateimg+'"><img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.organizationCertificateimg + '" alt=""/> </a>');
            $("#organizationCertificateimgF").show();
        }
        if(!$scope.subjEntTemp.positiveIdcardeimg){
            $("#positiveIdcardeimg").append('<span>无</span>');
            $("#positiveIdcardeimgFarm").append('<span>无</span>');
            $("#positiveIdcardeimgPerson").append('<span>无</span>');
        }else{
            $("#positiveIdcardeimg").append('<a data-fancybox href="'+$scope.subjEntTemp.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.positiveIdcardeimg + '" alt=""/> </a>');
            $("#positiveIdcardeimgFarm").append('<a data-fancybox href="'+$scope.subjEntTemp.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.positiveIdcardeimg + '" alt=""/> </a>');
            $("#positiveIdcardeimgPerson").append('<a data-fancybox href="'+$scope.subjEntTemp.positiveIdcardeimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.positiveIdcardeimg + '" alt=""/> </a>');
        }
        if(!$scope.subjEntTemp.negativeIdcardimg){
            $("#negativeIdcardimg").append('<span>无</span>');
            $("#negativeIdcardimgFarm").append('<span>无</span>');
            $("#negativeIdcardimgPerson").append('<span>无</span>');
        }else{
            $("#negativeIdcardimg").append('<a data-fancybox href="'+$scope.subjEntTemp.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.negativeIdcardimg + '" alt=""/> </a>');
            $("#negativeIdcardimgFarm").append('<a data-fancybox href="'+$scope.subjEntTemp.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.negativeIdcardimg + '" alt=""/> </a>');
            $("#negativeIdcardimgPerson").append('<a data-fancybox href="'+$scope.subjEntTemp.negativeIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.negativeIdcardimg + '" alt=""/> </a>');
        }
        if(!$scope.subjEntTemp.handIdcardimg){
            $("#handIdcardimg").append('<span>无</span>');
            $("#handIdcardimgFarm").append('<span>无</span>');
            $("#handIdcardimgPerson").append('<span>无</span>');
        }else{
            $("#handIdcardimg").append('<a data-fancybox href="'+$scope.subjEntTemp.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.handIdcardimg + '" alt=""/> </a>');
            $("#handIdcardimgFarm").append('<a data-fancybox href="'+$scope.subjEntTemp.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.handIdcardimg + '" alt=""/> </a>');
            $("#handIdcardimgPerson").append('<a data-fancybox href="'+$scope.subjEntTemp.handIdcardimg+'"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEntTemp.handIdcardimg + '" alt=""/> </a>');
        }


    }).error(function(data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjEntTempId");
        $state.go("index.content.asms/subjEnt/subjEntTempList");
    }
})