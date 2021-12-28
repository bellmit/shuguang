var myapp = angular.module("myapp", []);
myapp.controller("table_diagram", function($scope, $http, $state) {
    var p = new Paging();
    p.init({
        target: "#pageTool",
        pagesize: 8,
        count: 0,
        callback: function(pagecount, size, count) {
            requestList(pagecount);
        }
    });
    function showLoading() {
        $("#loading").removeClass('gui-hide');
    }

    function hideLoading() {
        $("#loading").addClass('gui-hide');
    }
    requestList();
    function requestList(size) {
        size = size == null ? 1 : size;
        showLoading();
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/resourcePublish/getTablesDetails",
            method: 'POST',
            contentType: "application/x-www-form-urlencoded",
            params:
                {
                start: 8 * (size - 1),
                length: 8
            }
        }).then(function(res) {
            var data = res.data;
            hideLoading();
            if (data.httpCode != 200) {
                alert(data.msg);
                return;
            }

            $("#tableList").html(
                _.template($("#tableTpl").html())(data.data)
            );

            //重新渲染分页使用render
            p.render({
                count: data.data.total,
                pagesize: 8,
                current: size
            });
        },function() {
                hideLoading();
        });
    }
});
