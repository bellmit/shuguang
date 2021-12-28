
var myapp = angular.module("myapp", []);
myapp.controller("sysCodeRuleEdit", function ($scope, $http) {
    $scope.sysRuleCodeId=window.localStorage.getItem("sysRuleCodeId");
    /*修改编码*/
    //跳转修改页面
    $http({
        url:"/sofn-sys-web/sysCodeRule/read/detail",
        method:"post",
        data:{
            id:$scope.sysRuleCodeId
        },
    }).success(function (data) {
        console.info(data);
        $scope.sysCodeRule = data.data;
    })


    /*保存编码*/
    $scope.saveCodeRule=function () {
        $http({
            url: "/sofn-sys-web/sysCodeRule/update",
            data: {
                id:$scope.sysRuleCodeId,
                ios: $("select[name='ios']").val(),
                area: $("select[name='area']").val(),
                country: $("select[name='country']").val(),
                industry: $("select[name='industry']").val(),
                platformLevel: $("select[name='platformLevel']").val(),
                platformType: $("select[name='platformType']").val(),
                codeAttrs:$("input[name='codeAttrs']:checked").val(),
                caseType:$("input[name='caseType']:checked").val(),
                organization:$("input[name='organization']:checked").val(),
                mainType:$("input[name='mainType']:checked").val(),
            },
            method: "post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            alert("保存成功");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function () {
            alert("保存失败");
        })
    }

    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("sysRuleCodeId");
    }
})



