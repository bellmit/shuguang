var myapp = angular.module("myapp", []);
myapp.controller("search", function($scope, $http, $state) {
    $scope.selectFields = [];
    $scope.exportCheckboxs = [];
    $scope.exportItems = {};
    $(document).on("click", "#export", function() {
        var num = 0;
        for (var obj in $scope.exportItems) {
            num++;
        }
        if (num <= 0) {
            swal('系统提示', '请勾选要导出的表', 'warning');
            return;
        } else {
            exportExcel();
        }
    });
    $scope.loadGrid = function() {
        $("#businessData").dataTable({
            "iDeferLoading": 0,
            ajax: {
                url: "/sofn-query/getResult",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    return $.extend({}, params, $scope.paramsData);
                },
                type: "post",
                dataSrc: function(json) {
                    json.recordsTotal = json.indexCount; //总个数
                    json.recordsFiltered = json.indexCount;

                    var title =
                        '<p style="padding-left:20px;">共匹配到' +
                        json.indexCount +
                        "张表，总共有" +
                        json.recordCount +
                        '条记录 <span id="export" style="color:blue;cursor:pointer;">导出到excel</span></p>';
                    var head_item = $("#businessData")
                        .DataTable()
                        .columns(0)
                        .header();
                    $(head_item).html(title);
                    $scope.tableData = json.list;
                    return json.list;
                }
            },
            fnDrawCallback: function() {
                var table = $(this);
                table
                    .find("tbody")
                    .off("click", ".tableName")
                    .on("click", ".tableName", function(e) {
                        $scope.currentIndex = $.trim(
                            $(this).attr("data-index")
                        );
                        $("#TaskInfoModel").modal("show");
                    });

                table
                    .find("tbody")
                    .off("change", ".export")
                    .on("change", ".export", function(e) {
                        var seleIndex = $(this)
                            .siblings("h3")
                            .attr("data-index");
                        $scope.currentTableName =
                            $scope.tableData[seleIndex].indexName;
                        delete $scope.exportItems[$scope.currentTableName];
                        if (this.checked) {
                            var seleIndex = $(this)
                                .siblings("h3")
                                .attr("data-index");
                            var arr = [];
                            for (var key in $scope.selectFields[seleIndex]) {
                                arr.push({
                                    flag: key,
                                    title: $scope.selectFields[seleIndex][key]
                                });
                            }

                            $scope.$apply(function() {
                                $scope.exportCheckboxs = arr;
                            });
                            console.log("arr", arr);

                            $("#TaskExportModel").modal("show");
                        }
                    });
            },
            columns: [
                {
                    render: function(data, type, row, meta) {
                        $scope.selectFields.push(row.selectFields);
                        var desc = "";
                        row.showRecords.map(function(item) {
                            var strs = [];
                            for (var key in item) {
                                strs[strs.length] =
                                    '"' + key + '":"' + item[key] + '"';
                            }
                            desc += "<p>{" + strs.join(",") + "}</p>";
                        });
                        var params = $('#businessData').DataTable().ajax.params();
                        return (
                            '<div class="result-item"><div class="title clerafix"><h3 class="tableName" data-index="' +
                            (params.start + meta.row) +
                            '">' +
                            row.tableName +
                            '</h3><span>- \u7D22\u5F15\u5E93\uFF1A</span><span class="indexName">' +
                            row.indexName +
                            ", \u5171" +
                            row.oneIndexCount +
                            '\u6761\u8BB0\u5F55</span><input type="checkbox" name="export" class="export">\u5BFC\u51FA</div><div class="desc">' +
                            desc +
                            "</div></div>"
                        );
                    }
                }
            ]
        });
    };

    //重置区域
    $scope.reloadArea = function() {
        $("#searchForm")[0].reset();
        $scope.initArea();
    };

    //重新加载表格
    $scope.querySubjSvList = function() {
        if ($.trim($scope.keyWord).length <= 0) {
            swal('系统提示', '请输入查询关键字', 'warning');
            return;
        }
        
        var params = {
            keyword: $scope.keyWord || "",
            startDate: $scope.startDate || "",
            endDate: $scope.endDate || "",
            regionsInfo:
                $scope.state.num > 0 ? $scope.state.text : ""
        };
        $scope.paramsData = params;
        $("#businessData")
            .dataTable()
            .api()
            .ajax.reload();
    };

    $scope.enterSearch = function($event){
        if($event.keyCode == 13){
            $scope.querySubjSvList();
        }
    }

    $scope.loadGrid();

    //checkbox勾选 导出
    $scope.checkboxOnclick = function(checkbox) {
        console.log(checkbox);
    };
    $("#TaskInfoModel").modal({
        keyboard: true,
        show: false
    });

    $("#TaskInfoModel").on("shown.bs.modal", function(e) {
        loadInfo($scope.currentIndex);
    });

    $("#TaskInfoModel").on("hidden.bs.modal", function(e) {
        businessInfoTable && businessInfoTable.destroy(false);
        $("#businessInfo").empty();
    });

    $("#TaskExportModel").on("hidden.bs.modal", function(e) {
        exportConfirm();
    });

    var businessInfoTable;
    function initInfoTable(columns, data) {
        console.log(columns, data);
        businessInfoTable = $("#businessInfo").DataTable({
            processing: false, // 加载时提示
            serverSide: false, // 分页，是否服务器端处理
            retrieve: false, //判断dataTable是否已经初始化，如果已经初始化则只返回实例
            scrollY: 600,
            fixedHeader: true,
            autoWidth: true,
            columns: columns,
            data: data
        });
        businessInfoTable.draw();
    }
    function loadInfo(index) {
        $http({
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-query/getDetail",
            method: "post",
            data: $.param({
                keyword: $scope.paramsData.keyword,
                startDate: $scope.paramsData.startDate || "",
                endDate: $scope.paramsData.endDate || "",
                regionsInfo: $scope.paramsData.regionsInfo,
                index: index
            })
        }).then(
            function(res) {
                console.log("res", res);
                var data = res.data;
                var keys = data.eng2chn;
                var columns = [];
                for (var key in keys) {
                    columns[columns.length] = {
                        title: keys[key],
                        data: key
                    };
                }
                initInfoTable(columns, data.records);
            },
            function(err) {}
        );
    }

    function exportExcel() {
        $http({
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-query/export",
            method: "post",
            data: $.param({
                keyword: $scope.paramsData.keyword,
                startDate: $scope.paramsData.startDate || "",
                endDate: $scope.paramsData.endDate || "",
                regionsInfo: $scope.paramsData.regionsInfo,
                exportItems: JSON.stringify($scope.exportItems)
            })
        }).then(
            function(res) {
                console.log("res", res);
                if (res.data.flag == "success") {
                    download(res.data.filePath);
                } else {
                    alert(res.data.reason);
                }
            },
            function(err) {}
        );
    }

    function download(url) {
        var form = document.createElement("form");
        form.setAttribute("action", url);
        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);
    }

    function exportConfirm() {
        var checkboxs = [];
        $("#TaskExportModel")
            .find("ul :checked")
            .each(function() {
                checkboxs[checkboxs.length] = $(this).val();
            });
        $scope.exportItems[$scope.currentTableName] = checkboxs.join(",");
        console.log("$scope.exportItems", $scope.exportItems);
    }
});
