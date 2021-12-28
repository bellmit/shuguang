var myapp = angular.module("myapp", ['720kb.tooltips']);
myapp.controller("appcx", function($scope, $http, $state, $timeout, $location) {
    // console.log('$state', $state, $location.search().code);
    $scope.zsm = $location.search().code;
    $scope.rootUp = null;
    $scope.rootDown = null;
    $scope.rootDown = null;
    /*查询*/
    $scope.query = function() {
        /*// 判断输入的码段为追溯码还是批次码
        var type = $scope.zsm.substr(14, 1);

        if (type == "" || type == null) {
            jBox.tip("批次码或追溯码不能为空！", 'info');
            return;
        } else if (type != "1" && type != "2") {
            jBox.tip("批次码或追溯码格式错误！", 'info');
            return;
         }*/
        $http({
            // url: "/sofn-qry-web/zsm/getByTrace/0/" + $scope.zsm,
            url: "/sofn-qry-web/traceQuery/getTreeDataByTraceCode/" + $scope.zsm,
            method: 'post', //POST方式
            dataType: 'json', //返回json类型
            header: {
                token: window.localStorage.getItem("token")
            }
        }).success(function(data) {
            if (!data.rootUp && !data.rootDown) {
                jBox.tip("不存在此记录！", 'info');
                return;
            }
            $scope.rootUp = data.rootUp;
            $scope.rootDown = data.rootDown;
            $timeout(function() {
                var width = 0;
                $('.tree-wrap').find('.ui-tree').each(function() {
                    width += $(this).outerWidth();
                }).end().css({ width: width + 10 }).find('.ui-tree').removeClass('tree-hidden');
            }, 200);
        });
    };
    /*清空*/
    $scope.clean = function() {
        $scope.zsm = "";
    };
    $scope.query();

    window.update = function() {
        var width = 0;
        $('.tree-wrap').find('.ui-tree').each(function() {
            width += $(this).outerWidth();
        }).end().css({ width: width + 10 }).find('.ui-tree').removeClass('tree-hidden');
    }
});