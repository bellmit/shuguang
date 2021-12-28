var myapp=angular.module("myapp",[]).controller("qrySubjEntSPYBView",function($scope,$http,$stateParams, $state){
    //获取本地存储的ID
    $scope.subjEntId=window.localStorage.getItem("subjEntId");

    $http.post('/sofn-qry-web/subject/findSubjEntAllAuthentication',$.param({id:$scope.subjEntId}), {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
            token: window.localStorage.getItem("token")
        }
    }).then(function(res){
        if(res.data.httpCode=="200") {
            //返回数据不存在或为空对象则显示无数据提示
            if (!res.data.data||JSON.stringify(res.data.data)=="{}") {
                $("#noDataInfo").show();
                return ;
            }
            $scope.greenFood = res.data.data.greenFood;
            $scope.organicFood = res.data.data.organicFood;
            $scope.pollutionFreeAgricultureProducts = res.data.data.pollutionFreeAgricultureProducts;
        }else{
            jBox.tip(res.data.msg,"info");
        }
        if ($scope.greenFood) {
            $("#spybInfo").show();
            $("#LSInfo").show();
            $scope.greenFood.issueDate = timestampToDate($scope.greenFood.issueDate);
            $scope.greenFood.licDeadlineBeg = timestampToDate($scope.greenFood.licDeadlineBeg);
            $scope.greenFood.licDeadlineEnd = timestampToDate($scope.greenFood.licDeadlineEnd);
            $scope.checkData($scope.greenFood);
        }
        if ($scope.organicFood) {
            $("#spybInfo").show();
            $("#YJInfo").show();
            $scope.organicFood.recTime = timestampToDate($scope.organicFood.recTime);
            $scope.organicFood.validity = timestampToDate($scope.organicFood.validity);
            $scope.checkData($scope.organicFood);
        }
        if ($scope.pollutionFreeAgricultureProducts) {
            $("#spybInfo").show();
            $("#WGHInfo").show();
            $scope.pollutionFreeAgricultureProducts.certificateEnddate = timestampToDate($scope.pollutionFreeAgricultureProducts.certificateEnddate);
            $scope.pollutionFreeAgricultureProducts.checkTime = timestampToDate($scope.pollutionFreeAgricultureProducts.checkTime);
            $scope.pollutionFreeAgricultureProducts.certificateBegindat = timestampToDate($scope.pollutionFreeAgricultureProducts.certificateBegindat);
            $scope.checkData($scope.pollutionFreeAgricultureProducts);
        }

    }, function(res){
        
    })

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
    //字段值为空显示为无
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }

})