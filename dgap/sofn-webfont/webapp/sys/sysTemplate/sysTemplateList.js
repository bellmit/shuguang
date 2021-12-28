var myapp = angular.module("myapp", []);
myapp.controller("sysTemplateList", function($scope, $http, $state) {
    //表单验证
    $('#attributeForm').bootstrapValidator(); //必须有
    $("#attributeForm2").bootstrapValidator(); //初始化验证，与上面对应
    //查询
    $("#ToCheck").click(function() {
        var keyWord = $.trim($("input[name='templateName']").val());
        /*汉字1-30个，字母和数字1-60个*/
        var KW = /^[\u4e00-\u9fa5]$/;
        var len = 0;
        for (var i = 0; i < keyWord.length; i++) {
            if (KW.test(keyWord[i])) {
                len = len + 2;
            } else {
                len++;
            }
        }
        if (len > 60) {
            alert("1-60个字符或者1-30个汉字")
        } else {
            $("#grid").dataTable().api().ajax.reload();
        }
    });
    //新增
    $scope.listAdd = function() {
        $("#attributeForm").data("bootstrapValidator").destroy(); //先去掉验证，与下面对应
        $("#fileNameshow").css("display", "none");
        $("#myModal").modal("show");
        $("#attributeForm").bootstrapValidator(); //初始化验证，与上面对应
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
        $scope.templeName = "";
        $('#tempFile').fileinput('reset');
    }
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('附件管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    var vm = this;

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-sys-web/sysTemplate/upload',
        //业务参数
        data: function() {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            //	           if (!acceptFileTypes.test(file.type)) {
            //	               swal('', '只能上传word类型文档', 'warning');
            //	               return false;
            //	           }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };

    vm.uploadOptions2 = {
        //接口地址
        url: '/sofn-sys-web/sysTemplate/upload',
        //业务参数
        data: function() {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }
            return true;
        }
    };
    // 异步请求错误处理函数
    function ajaxErrorHandler(statusCode) {
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

    //点击保存
    $scope.addSysTemplate = function() {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            $("#fileNameshow").css("display", "block");
            return false;
        }
        if ($("#fileNameshow").css("display") == "block") {
            return false;
        }
        add();
    };

    function add() {
        if (vm.files.length <= 0) {
            alert("附件没上传,请先进行上传操作");
            return false;
        }
        if (_.isEmpty(vm.files[0].src)) {
            jBox.tip('文件正在上传,请上传完成后再保存', 'info');
            return false;
        }
        $scope.sysTemplate.templateName = $("input[name='templateName2']").val();
        $scope.sysTemplate.templateType = "全部"; //$("input[name='templateType2']").val();
        $scope.sysTemplate.remark = $("textarea[name='remark2']").val();
        $scope.sysTemplate.templateFilename = vm.files[0].name; //$("input[name='templateFilename2id']").val();
        $scope.sysTemplate.fileAddress = vm.files[0].src;
        $http({
            url: "/sofn-sys-web/sysTemplate/addSysTemplate",
            data: $scope.sysTemplate,
            method: "post",
            headers: {
                "token": window.localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }
        }).success(function(data) {
            if (data.httpCode == 409) {
                alert("附件名称重复，请重新输入!");
            } else if (data.httpCode == 200) {
                alert("保存成功");
                $("#myModal").modal("hide");
                $(".kv-upload-progress").hide();
                $("#grid").dataTable().api().ajax.reload();
                vm.files.pop();
            } else {
                alert("保存失败");
            }
        }).error(function(data) {
            ajaxErrorHandler(data.httpCode);
        })
    }

    // 设置dataTable数据操作
    function settingTableOperation(id, fileAddress, fileName) {
        var link = '<a class="color " style="cursor: pointer" href="/sofn-sys-web/sysTemplate/fileDownload?fileUrl=<%= fileAddress %>&fileName=<%= fileName %>">下载</a>';
        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
            link += '<a class="color mr15 ml15" style="cursor: pointer" onclick="updateData(\'<%= id %>\')">修改</a>';
        }

        var template = _.template(link);

        return template({id: id, fileAddress: encodeURIComponent(fileAddress), fileName: encodeURIComponent(fileName)});
    }
        /**
         * 分页展示数据
         */
        //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysTemplate/getPageInfo",
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
                params.templateName = $.trim($("input[name='templateName']").val());
                params.templateType = "全部";
                params.token = window.localStorage.getItem("token");
            },
            type: "post"
        },
        columns: [{
                data: "tpck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function(data, type, row) {
                    return '<input type="checkbox" name="tpck"/>';
                },
                width: "5%"

            }, {
                data: "templateName",
                title: "附件名称",
                width: "20%"
            },

            // {
            // 	data: "templateType",
            // 	title: "模板类型"
            // },
            {
                data: "templateFilename",
                title: "附件文件名",
                width: "15%"
            }, {
                data: "updateTime",
                title: "最后更新时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                },
                width: "10%"
            }, {
                data: "remark",
                title: "备注",
                width: "30%"
            }, {
                data: "status",
                title: "启用",
                render: function(data, type, row) { // 模板化列显示内容
                    if (row.status == "Y") {
                        return '<img src="assets/imgs/icon/sys-on.png" status="' + row.status + '" id="' + row.id + '"  class="xxx" onclick="changeStatus(this)"/>';
                    } else {
                        return '<img src="assets/imgs/icon/sys-off.png" status="' + row.status + '" id="' + row.id + '"  class="xxx" onclick="changeStatus(this)"/>';
                    }
                }
            }, {
                data: "id",
                title: "操作",
                render: function(data, type, row) {
                    // 数据操作选择项
                    return settingTableOperation(data, row.fileAddress, row.templateFilename);
                },
                width: "10%"
            }
        ]
    }).on('page.dt', function() {
        var table = $(this);
        table.find('thead').find(':checkbox').prop('checked', false);
    });

    /**
     * 跳转到查询页面
     */
    window.lookView = function(data) {
        $http({
            url: "/sofn-sys-web/sysTemplate/queryById",
            method: "post",
            data: {
                id: data
            }
        }).success(function(data1) {
            if (data1 != null) {
                window.open(data1.sysTemplate.pdfAddress);
            } else {
                alert("系统异常,请联系工作人员");
            }
        })
    }

    /**
     * 获取需要修改的数据
     */
    window.updateData = function(data) {
        $('#tempFile2').fileinput('reset');
        $scope.id = data;
        $http({
            url: "/sofn-sys-web/sysTemplate/queryById",
            headers: {token: window.localStorage.getItem('token')},
            method: "post",
            dataType: "json",
            data: {
                id: $scope.id
            }
        }).success(function(data) {
            $scope.sysTemplate = data.sysTemplate;
            // $scope.templateFilename = data.sysTemplate.templeName;
            // $scope.fileAddress = data.sysTemplate.path;
            vm.files2 = [{
                name: data.sysTemplate.templateFilename,
                src: data.sysTemplate.fileAddress
            }];
            $("#myModal2").modal("show");
        }).error(function(data) {
            ajaxErrorHandler(data.httpCode);
        });

        $scope.filChange = function() {
            var s = $("input[name='templateFilename3']").val();
            $("input[name='copyTemplateFilename3']").val(s);
        }

        /**
         * 修改
         */
        $scope.updateSysTemplate = function() {
            if (!$("#attributeForm2").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            var id = $scope.id;
            $scope.sysTemplate.id = id;
            $scope.sysTemplate.templateName = $("input[name='templateName2']").val();
            $scope.sysTemplate.remark = $("textarea[name='remark2']").val();

            $scope.sysTemplate.templateFilename = vm.files2[0].name;
            $scope.sysTemplate.fileAddress = vm.files2[0].src;

            $http({
                    url: "/sofn-sys-web/sysTemplate/updateSysTemplate",
                    data: $scope.sysTemplate,
                    method: "post",
                    headers: {
                        token: window.localStorage.getItem('token')
                    }
                }).success(function(data) {
                    $("#myModal2").modal("hide");
                    $(".kv-upload-progress").hide();
                    $("#grid").dataTable().api().ajax.reload();
                }).error(function(data) {
                    ajaxErrorHandler(data.httpCode);
                })
                //$.jBox.confirm("文件已准备就绪,再次点击查看文件", "查看提示", submit, { buttons: { '确认': true} });

        }

        //清楚缓存
        $scope.RemoveLocal = function() {
            window.localStorage.removeItem("id");
        };
    }

    /**
     * 批量删除
     */
    $scope.deleteTemp = function() {
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            if (confirm('是否批量删除已选择行？')) {
                $.ajax({
                    url: "sofn-sys-web/sysTemplate/deleteAll",
                    type: "post",
                    dataType: "json",
                    data: {
                        idStr: i.toString()
                    },
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
                url: "/sofn-sys-web/sysTemplate/deleteSysTemplate",
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
    window.changeStatus = function(obj) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        var enable = obj.getAttribute("status") == "N" ?
            "Y" :
            "N";
        var id = obj.id;
        var imgObj = $('#' + obj.id);
        var submit = function(v, h, f) {
            $.ajax({
                url: "/sofn-sys-web/sysTemplate/updateTempStatus",
                headers: {token: window.localStorage.getItem('token')},
                data: {
                    "id": id,
                    "enable": enable
                },
                type: "post"
            }).success(function(data) {
                //                    alert(data.httpCode)
                if (data.httpCode != 200) {
                    jBox.tip("修改失败", 'info');
                    return false;
                }
                jBox.tip("修改成功", 'info');
                if (enable == "Y") {
                    imgObj.attr("src", "assets/imgs/icon/sys-on.png");
                    imgObj.attr("status", "Y");
                } else {
                    imgObj.attr("src", "assets/imgs/icon/sys-off.png");
                    imgObj.attr("status", "N");
                }
            }).error(function(data) {
                ajaxErrorHandler(data.status);
            });

            return true;
        };
        submit();
    }
})