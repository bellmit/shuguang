var myapp = angular.module("myapp", []);
myapp.controller("sysNocList", function($scope, $http, $state) {
    $scope.releaseState = [
        {
            name: '已发布',
            value: '1'
        }, {
            name: '未发布',
            value: ''
        }, {
            name: '未发布',
            value: '0'
        }, {
            name: '未发布',
            value: null
        }
    ];

    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('公告信息管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });

    /**
	 * 分页展示数据
	 */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            //sScrollY : "auto",
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-sys-web/sysNoticerelease/getPageInfo",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsTotal;
                    json.pageNumber = json.page.pageNum; //页码
                    json.pageSize = json.page.pageSize; //每页个数
                    json.start = (json.page.pageNum - 1) * json.page.pageSize; //起始位置
                    json.length = json.page.pageSize;
                    return json.list;
                },
                data: function(params) {
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                    params.noticeType = $("select[name='noticeType']").val();
                    params.title = $("input[name='title']").val();
                    params.releaseTime = $("input[name='releaseTime']").val().replace(/\ +/g, "");
                    params.releasePerson = $("input[name='releasePerson']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "nock",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function(data, type, row) {
                        return '<input type="checkbox" name="nock"/>';
                    },
                    width: "5%"
                }, {
                    data: "noticeType",
                    title: "类型",
                    width: "10%"
                }, {
                    data: "title",
                    title: "标题",
                    width: "20%"
                }, {
                    data: "releasePerson",
                    title: "发布人",
                    width: "20%"
                }, {
                    data: "reservedField1",
                    title: "发布状态",
                    width: "20%",
                    render: function(data, type, row) {
                        for (var j = 0; j < $scope.releaseState.length; j++) {
                            if ($scope.releaseState[j].value == data) {
                                return $scope.releaseState[j].name;
                            }
                        }
                        return data;
                    }
                }, {
                    data: "releaseTime",
                    title: "发布时间",
                    render: function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd hh:mm:ss');
                    },
                    width: "15%"
                }, {
                    data: "id",
                    title: "操作",
                    render: function(data, type, row) {
                        // 数据操作选择项
                        var link = '<a class="color" style="cursor: pointer" class="Check-report"  onclick="lookView(\'' + data + '\')">查看</a>';
                        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
                            link += '<a class="color ml15" style="cursor: pointer" onclick="updateData(\'' + data + '\')">编辑</a>';
                        }
                        // var delete_a = '<a style="cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
                        return link;
                    },
                    width: "15%"
                }
            ]
        }).on('page.dt, draw.dt', function () {
          var table = $(this);
          table.find('thead').find(':checkbox').prop('checked', false);
        });
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });
    /**
     * 跳转到查询页面
     */
    window.lookView = function(data) {
        window.localStorage.setItem("sys_sysNoticerelease_id", data);
        $state.go("index.content.sys/sysNoticerelease/sysNoticereleaseView")
    }

    /**
     * 执行修改的方法
     */
    window.updateData = function(data) {
        window.localStorage.setItem("sys_sysNoticerelease_id", data);
        $state.go("index.content.sys/sysNoticerelease/sysNoticereleaseUpdate")
    }

    /**
     * 发布
     */
    $scope.releaseNotice = function() {
        alert("待开发中");
    }

    /**
     * 批量删除
     */
    $scope.deleteNotice = function() {
        var i = $scope.getSelectRowsIds();
        // var delIds="";
        // $(".NewsSize input[name='nock']:checked ").each(function () {
        //     delIds=delIds+$(this).parent().parent().children().eq(10).children().eq(0).val()+"|";
        //     console.info(delIds);
        // })
        if (i != false) {
            if (confirm('是否批量删除已选择行？')) {
                // var data = {
                //     dataInfo : delIds
                // };
                $.ajax({
                    url: "sofn-sys-web/sysNoticerelease/deleteAll",
                    type: "post",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    dataType: "json",
                    data: {
                        idStr: i.toString()
                    },
                    success: function(data) {
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();
                    },
                    error: function(data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        }
    }


    /**
     * 获取复选框选中行
     * @returns {*}
     */
    $scope.getSelectRowsIds = function() {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }

        return ids;
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data) {
        //选择是否进行删除
        if (confirm('是否进行删除...')) {
            $.ajax({
                url: "/sofn-sys-web/sysNoticerelease/deleteSysNoticerelease",
                type: "post",
                dataType: "json",
                data: {
                    id: data
                },
                success: function(data) {
                    alert("删除成功！");
                    $("#grid").dataTable().api().ajax.reload();
                }
            });
        }
    };
    document.onkeydown = keyListener;
    function keyListener(e) {
        if (e.keyCode == 13) {
            $("#grid").dataTable().api().ajax.reload();
        }
    }
})
