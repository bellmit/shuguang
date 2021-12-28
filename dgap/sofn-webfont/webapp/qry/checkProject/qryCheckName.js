var myapp = angular.module("myapp", []).controller("qryCheckName", function ($scope, $http, $state) {


    $scope.checkProject = window.localStorage.getItem("checkProject");

//地区
    var area = ""
    var jibie = ""
    $http({
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        area = data.user.regionId;
        jibie = data.user.orgLevel;
        if (jibie == 1) {
            area = 0
        }
        if (jibie == 2) {
            area = area.substr(0, 2);
        }
        if (jibie == 3) {
            area = area.substr(0.4)
        }
        if (jibie == 4) {
            area = area.substring(0, 6)
        }
        $.fn.mycity("sheng", "shi", "xian", area);
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
            $("#sheng").attr("disabled", true);
        }
        if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
            $("#shi").attr("disabled", true);
        }
        if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
            $("#xian").attr("disabled", true);
        }


    }).error(function () {

    });


    var type = '';
    $scope.projectName = function (types) {
        if (types == "sc") {
            type = "蔬菜类";
        } else if (types == "sg") {
            type = "水果类";
        } else if (types == "syj") {
            type = "食用菌类";
        } else if (types == "xm") {
            type = "家畜类";
        } else if (types == "scp") {
            type = "水产品类";
        }
        $("#grid").dataTable().api().ajax.reload();
    }


//检测项目类型条数
    $(function () {

        var tokenId = window.localStorage.getItem("token");
        var shucai = "蔬菜类";
        var shuiguo = "水果类";
        var shiyongjun = "食用菌";
        var xumu = "家畜类";
        var shuicanpin = "水产品";

        $http({
            url: "/sofn-qry-web/checkProject/getTypeCount",
            method: "post",
            params: {"productName": shucai},
        }).success(function (datax) {
            if (datax.httpCode == 200) {
                var datasx = datax.data;
                $scope.shucai = datasx;

            } else {
                jBox.tip("数据有误", 'info');
            }
        }).error(function () {
        });

        $http({
            url: "/sofn-qry-web/checkProject/getTypeCount",
            method: "post",
            params: {"productName": shuiguo},
        }).success(function (datax) {
            if (datax.httpCode == 200) {
                var datasx = datax.data;
                $scope.shuiguo = datasx;

            } else {
                jBox.tip("数据有误", 'info');
            }
        }).error(function () {
        });

        $http({
            url: "/sofn-qry-web/checkProject/getTypeCount",
            method: "post",
            params: {"productName": shiyongjun},
        }).success(function (datax) {
            if (datax.httpCode == 200) {
                var datasx = datax.data;
                $scope.shiyongjun = datasx;

            } else {
                jBox.tip("数据有误", 'info');
            }
        }).error(function () {
        });

        $http({
            url: "/sofn-qry-web/checkProject/getTypeCount",
            method: "post",
            params: {"productName": xumu},
        }).success(function (datax) {
            if (datax.httpCode == 200) {
                var datasx = datax.data;
                $scope.xumu = datasx;

            } else {
                jBox.tip("数据有误", 'info');
            }
        }).error(function () {
        });


        $http({
            url: "/sofn-qry-web/checkProject/getTypeCount",
            method: "post",
            params: {"productName": shuicanpin},
        }).success(function (datax) {
            if (datax.httpCode == 200) {
                var datasx = datax.data;
                $scope.shuicanpin = datasx;

            } else {
                jBox.tip("数据有误", 'info');
            }
        }).error(function () {
        });

    })


    /**
     * 任务列表
     * */
    //查询按钮
    $("#sousuo").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $(function () {
        $("#grid").dataTable({
            // fnDrawCallback: function () {
            //     //点击行内任意内容即选择行
            //     // $("#grid tbody tr").each(function (i, o) {
            //     //     $(o).click(function () {
            //     //         var checked = $(this).find("input[type='checkbox']")[0];
            //     //         if (checked.checked) {
            //     //             checked.checked = false;
            //     //             $(this).removeClass("selected");
            //     //         } else {
            //     //             checked.checked = true;
            //     //             $(this).addClass("selected");
            //     //         }
            //     //     });
            //     // })
            // },
            ajax: {
                url: "/sofn-qry-web/checkProject/getCheckInfo",
                dataSrc: function (json) {
                    if (json.data == null || json.data == undefined) {
                        json.recordsTotal = 0;//总个数
                        json.recordsFiltered = 0;
                        json.pageNumber = 1;//页码
                        json.pageSize = 10;//每页个数
                        json.start = 0;//起始位置
                        json.length = 10;
                        return json.data;
                    }
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;

                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.checkProject = $("input[name='checkProject']").val();
                    params.productName = type;
                    // params.dateBegin=$("input[name='datebegin']").val();
                    // params.dateEnd=$("input[name='dateend']").val();
                    // params.rmYear=$("input[name='queryYear']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "checkProject",
                    checkbox: true,
                    title: '<input id="checkall" name="" type="checkbox" value="" />',
                    render: function (data, type, row, meta) {
                        // 数据操作选择项
                        // var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                        // return view;
                        return '<input id="c' + meta.row + '" class="editor-active" type="checkbox" name="checkOne"/>';
                    }
                },
                {
                    data: 'id',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"
                },
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "productName",
                    title: "产品类型"
                },
                {
                    data: "checkNum",
                    title: "检测值",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (0 == data) {
                            return '<a id="' + meta.row + '" value="' + "未检出" + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">未检出</a>';
                        } else {
                            return '<a id="' + meta.row + '" value="' + data + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">' + data + '</a>';
                        }
                    },
                },

                {
                    data: "judgeNum",
                    title: "上限值"
                },
                {
                    data: "result",
                    title: "抽检结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else {
                            return "不合格";
                        }
                    }
                },
                // {
                //     data : "ID",
                //     title : "操作",
                //     render : function(data, type, row) {
                //         return '<a style="cursor:pointer" onclick="queryInfo(\''+data+'\')" class="color">详情</a>  <a style="cursor:pointer" onclick="queryReports(\''+data+'\')" class="color">报告</a>  <a style="cursor:pointer" onclick="querySamples(\''+data+'\')" class="color">抽样单</a>';
                //     },
                //     width : "10%"
                // }
            ],
        });
    });

    /**
     * 任务操作
     * */





window.back=function () {
    $state.go("index.content.qry/checkProject/qryCheckProject")
}

    window.sure = function () {

        var ids = "";
        var rows = $("#grid").DataTable().data();
        var num = 0;
        for (var i = 1; i <= rows.length; i++) {
            var b = i - 1;
            var td = rows[i - 1];
            var checkbox = document.getElementById('c' + b);
            if (checkbox.checked) {
                ids = ids + td.checkProject + ",";
                num++;
                $scope.checkProject = $('input[name="checkOne"]:checked ').val(ids);
                console.log($scope.checkProject);
            }

        }
        if (0 == num) {
            $.jBox.tip("至少选择一行！");
            return;
        }

        window.localStorage.setItem("ids", ids);
        window.localStorage.removeItem("checkProject");
        $state.go("index.content.qry/checkProject/qryCheckProject")

    }

    /**
     * 全选任务数据
     */
    $("#checkall").click(function () {
        if ($(this).prop("checked") == true) {
            $(".editor-active").each(function () {
                if (!$(this).is(':disabled')) {
                    $("#grid input[type='checkbox']").prop("checked", true);
                    $("#grid tbody tr").addClass("selected");
                    $(this).prop('checked', true);
                }
            })
        } else {
            $("#grid input[type='checkbox']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

});
