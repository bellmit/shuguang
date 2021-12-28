
var myapp = angular.module("myapp", []);
myapp.controller("sysCodeRuleAdd", function ($scope, $http) {
    /* $scope.loaded=function () {
     $("input[name='zhuti']").click(function () {
     var c = $(this).parent().parent();
     $(c).attr("title",$(this).val());
     alert($(this).val());
     })
     //alert($("input[name='zhuti']:checked").val());
     }*/
    $scope.saveCodeRule=function () {
        $http({
            url: "/sofn-sys-web/sysCodeRule/add",
            data: {
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
})



