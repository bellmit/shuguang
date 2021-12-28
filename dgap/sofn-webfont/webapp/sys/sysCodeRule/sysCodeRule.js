var myapp = angular.module("myapp", []);
myapp.controller("sysCodeRule", function($scope, $http, $state) {
    $scope.codeRuleModifyURL = "";
    $scope.modalTitle = "";
    //分页
    $(function() {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-sys-web/sysCodeRule/read/list",
                headers: {token: window.localStorage.getItem("token")},
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.pageNumber = json.page.start / json.page.length; //页码
                    json.pageSize = json.page.pageSize; //每页个数
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    pageNumber = json.page.pageNumber;
                    pageSize = json.page.pageSize;
                    // for (var i = 0; i < json.length; i++) {
                    //     if (json.data[i].INSPECTION_TIME != null && json.data[i].INSPECTION_TIME != "" && json.data[i].INSPECTION_TIME != undefined) {
                    //         json.data[i].INSPECTION_TIME = new Date(parseInt(json.data[i].INSPECTION_TIME) + 1000 * 60 * 60 * 24).toISOString().slice(0, 10);
                    //     }
                    // }
                    return json.data;
                },
                type: "POST",
            },
            fnDrawCallback: function() {
                //点击行内任意内容即选择行
                var syncCheckboxSelects = function(target) {
                    var checkboxs = target.closest('tbody').find(':checkbox:checked');
                    var vals = [];
                    checkboxs.each(function(index, item) {
                        var map = {
                            id: $(item).val()
                        };
                        vals[vals.length] = map;
                    });
                    $scope.selects = vals;
                    console.log('$scope.selects', $scope.selects);
                }
                this.on('change', 'tbody :checkbox', function() {
                    syncCheckboxSelects($(this));
                });
            },
            columns: [{
                data: "id",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function(data, type, row) {
                    return '<input type="checkbox" name="ck" value="'+data+'" />';
                },
                width: "5%"
            }, {
                data: "ruleName",
                title: "规则名称"
            }, {
                data: "id",
                title: "操作",
                render: function(data, type, row) { // 模板化列显示内容
                    return '<input class="idValue" type="hidden" value="' + data + '"><a style="margin-right: 10px;cursor: pointer" class=" color Check-report"  onclick="view(\'' + data + '\')">查看</a><a class="color"  style="margin-right: 10px;cursor: pointer" onclick="updateCodeRule(\'' + data + '\')">修改</a>'; //转义传值
                },
                width: "20%"
            }, ]
        });
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });
    //跳转修改页面
    window.updateCodeRule = function(id) {
        window.localStorage.setItem("sysRuleCodeId", id);
        // $state.go("index.content.sys/sysCodeRule/sysCodeRuleEdit");
        $scope.$evalAsync(function () {
            $scope.codeRuleModifyURL = '/webapp/sys/sysCodeRuleAdd/codeRule/?id=' + id;
            $scope.modalTitle = "修改";
        });
        $("#popupModal").modal("show");
    }
    //全选/全不选
    $("#checkAll").click(function() {
        if ($(this).prop("checked") == true) {
            $("#grid input[type='checkbox']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    //跳转详情页面
    window.view = function(id) {
            // $state.go("index.content.sys/sysCodeRule/sysCodeRuleView");
        $scope.$evalAsync(function () {
            $scope.codeRuleModifyURL = '/webapp/sys/sysCodeRuleAdd/codeRule/?id=' + id;
            $scope.modalTitle = "查看";
        });
        $("#popupModal").modal("show");
            // window.location.href = '/webapp/sys/sysCodeRuleAdd/codeRule/?id=' + id;
    }
    // 新增
    $scope.addCodeRule = function() {
        $scope.$evalAsync(function () {
            $scope.codeRuleModifyURL = '/webapp/sys/sysCodeRuleAdd/codeRule/';
            $scope.modalTitle = "新增";
        });
        $("#popupModal").modal("show");
    }
    $('#popupModal').on('hide.bs.modal', function(){
        $("#grid").dataTable().api().ajax.reload();
    });
        //删除
    $scope.deleteCodeRule = function() {
        var i = $scope.selects.length;
        var delIds = $scope.selects.map(function(item){
            return item.id;
        }).join('|');
        if (i != false) {
            if (confirm('是否批量删除已选择行？')) {
                var data = {
                    dataInfo: delIds
                };
                $.ajax({
                    url: "/sofn-sys-web/sysCodeRule/delete",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function(data) {
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();
                    },
                    fail: function(data) {
                        alert("失败");
                    }
                });
            }
        }
    };
})
