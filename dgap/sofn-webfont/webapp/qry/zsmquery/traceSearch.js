var myapp = angular.module("myapp", ["720kb.tooltips"]);
myapp.controller("traceSearch", function($scope, $http, $state, $timeout) {
    $scope.zsm = "";
    $scope.rootUp = null;
    $scope.rootDown = null;
    $scope.rootDown = null;
    /*查询*/
    $scope.query = function() {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        // 判断输入的码段为追溯码还是批次码
        /*var type = $scope.zsm.substr(14, 1);

        if (type == "" || type == null) {
            jBox.tip("批次码或追溯码不能为空！", 'info');
            return;
        } else if (type != "1" && type != "2") {
            jBox.tip("批次码或追溯码格式错误！", 'info');
            return;
         }*/

        if ($scope.zsm == "") {
            swal('', "请输入追溯码！", "warning");
            return false;
        }
        // $scope.zsm = '1.2.156.326.8.2.2.237283728392837382.01030302.20170617131919.001';
        $http({
            // url: "/sofn-qry-web/zsm/getByTrace/0/" + $scope.zsm,
            url:
                "/sofn-qry-web/traceQuery/getTreeDataByTraceCode/" + $scope.zsm,
            method: "post", //POST方式
            dataType: "json", //返回json类型
            header: {
                token: window.localStorage.getItem("token")
            }
        }).success(function(data) {
            $scope.rootUp = data.rootUp;
            $scope.rootDown = data.rootDown;
            if (!$scope.rootUp && !$scope.rootDown) {
                jBox.tip("不存在此记录！", "info");
                return;
            }
            $timeout(function() {
                var width = 200;
                $(".tree-wrap")
                    .find(".ui-tree")
                    .each(function() {
                        width += $(this).outerWidth();
                    })
                    .end()
                    .css({ width: width + 10 })
                    .find(".ui-tree")
                    .removeClass("tree-hidden");
            }, 200);
        });
    };
    /*清空*/
    $scope.clean = function() {
        $scope.zsm = "";
        $scope.rootUp = null;
        $scope.rootDown = null;
    };
    $scope.timer = {};
    $scope.treeEnter = function($event) {
        // console.log('enter', $scope.timer, $scope.timer[$event.currentTarget]);
        $timeout.cancel(
            $scope.timer[$event.currentTarget.getAttribute("timerId")]
        );
        // clearTimeout($scope.timer[$event.currentTarget.getAttribute('timerId')])
        $($event.currentTarget)
            .children(".list-detail")
            .show();
        $event.currentTarget.setAttribute("timerId", +new Date());
    };

    $scope.treeLeave = function($event) {
        $scope.timer[$event.currentTarget.getAttribute("timerId")] = $timeout(
            function() {
                $($event.currentTarget)
                    .children(".list-detail")
                    .hide();
            },
            400
        );
        // console.log('leave',$scope.timer, $scope.timer[$event.currentTarget], $event.currentTarget.getAttribute('timerId'));
    };

    window.update = function() {
        var width = 0;
        $(".tree-wrap")
            .find(".ui-tree")
            .each(function() {
                width += $(this).outerWidth();
            })
            .end()
            .css({ width: width + 10 })
            .find(".ui-tree")
            .removeClass("tree-hidden");
    };
});
