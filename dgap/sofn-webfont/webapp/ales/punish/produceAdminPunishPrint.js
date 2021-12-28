//    angularjs数据传输
var myapp = angular.module("myapp", []);
var mytype = "";
var myend = "";
myapp.controller("produceAdminPunishPrint", function($scope, $http) {
  $scope.radioID = window.localStorage.getItem("radioID");
  console.log($scope.radioID);
  $http({
    headers: {
      token: window.localStorage.getItem("token")
    },
    url: "/sofn-ales-web/administrativePenalty/findAdministrativePenaltyById",
    method: "post",
    data: { id: $scope.radioID }
  })
    .success(function(data) {
      $scope.reCord = data.produceAdminPunish;
      $("input[name='punishCode']").val($scope.reCord.punishCode);
      $("input[name='enterpriseId']").val($scope.reCord.enterpriseId);
      $("input[name='enterpriseCode']").val($scope.reCord.enterpriseCode);
      $("input[name='legalPerson']").val($scope.reCord.legalPerson);
      $("input[name='caseName']").val($scope.reCord.caseName);
      $("input[name='productName']").val($scope.reCord.productName);
      $("input[name='enterpriseName']").val($scope.reCord.enterpriseName);
      $("input[name='manufactureDate']").val($scope.reCord.manufactureDate);
      $("input[name='punishQualitative']").val($scope.reCord.punishQualitative);
      $("input[name='punishResult']").val($scope.reCord.punishResult);
      $("input[name='punishFiles']").val($scope.reCord.punishFiles);
      $("input[name='manufactureDate']").val($scope.reCord.manufactureDate);
    })
    .error(function(data) {
      console.log(data);
    });
  //打印设置
  $("#PrintArea").click(function() {
    $http({
      headers: {
        token: window.localStorage.getItem("token")
      },
      url: "/sofn-ales-web/administrativePenalty/findAdministrativePenaltyById",
      method: "post",
      data: { id: $scope.radioID }
    })
      .success(function(data) {
        $scope.reCord = data.produceAdminPunish;
        $("input[name='punishCode']").val($scope.reCord.punishCode);
        $("input[name='enterpriseId']").val($scope.reCord.enterpriseId);
        $("input[name='enterpriseCode']").val($scope.reCord.enterpriseCode);
        $("input[name='legalPerson']").val($scope.reCord.legalPerson);
        $("input[name='caseName']").val($scope.reCord.caseName);
        $("input[name='productName']").val($scope.reCord.productName);
        $("input[name='enterpriseName']").val($scope.reCord.enterpriseName);
        $("input[name='manufactureDate']").val($scope.reCord.manufactureDate);
        $("input[name='punishQualitative']").val(
          $scope.reCord.punishQualitative
        );
        $("input[name='punishResult']").val($scope.reCord.punishResult);
        $("input[name='punishFiles']").val($scope.reCord.punishFiles);
        $("input[name='manufactureDate']").val($scope.reCord.manufactureDate);
      })
      .error(function(data) {});
    $("#printContent").printArea();
  });
  //查看详情页面
  //点击查询按钮刷新datatable
  $("#Cancel").click(function() {
    location.href = "produceAdminPunishlist.html";
  });
});
