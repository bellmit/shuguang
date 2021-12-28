var myapp = angular.module("myapp", []);
myapp.controller("dictTypeView", function($scope, $http, $state) {
    $("#dictTypeView").bootstrapValidator();
    document.onkeydown = keyListener;

    function keyListener(e) {
        if (e.keyCode == 13) {
            $scope.queryList();
        }
    }
    var dictTypeId;
    $('#attributeForm').bootstrapValidator(); //必须有
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('字典管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
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
    //请求数据
    $scope.myReload = function(keyWord) {
        keyWord = $.trim(keyWord); /*去掉前后空格*/
        /*汉字1-30个，字母和数字1-60个*/
        // var KW=/^[\u4e00-\u9fa5]$/;
        // var len=0;
        // for(var i=0;i<keyWord.length;i++){
        //      if(KW.test(keyWord[i])){
        //          len=len+2;
        //      }else {
        //          len++;
        //      }
        //      if(len>60){
        //          break;
        //      }
        // }
        // keyWord=keyWord.substring(0,i);
        $http({
            url: "/sofn-sys-web/sysDict/getDictTypes",
            headers: {
                token: window.localStorage.getItem("token")
            },
            //data:{
            //    "keyWord":keyWord
            //},
            params: {
                'keyWord': keyWord
            },
            method: "POST"
        }).success(function(json) {
            var vm = $scope.vm = {};
            vm.dictType = json.data;
            //vm.dictType = [
            //    {
            //        id: 1, dictTypeName: '一班',
            //        dictTypeSub: [
            //            { id: 1, dictTypeSubName: '张三' },
            //            { id: 2, dictTypeSubName: '李四' }
            //        ]
            //    },
            //    {
            //        id: 2, dictTypeName: '二班',
            //        dictTypeSub: [
            //            { id: 3, dictTypeSubName: '王五' },
            //            { id: 4, dictTypeSubName: '马六' }
            //        ]
            //    },
            //    {
            //        id: 3, dictTypeName: '三班',
            //        dictTypeSub: [
            //            { id: 5, dictTypeSubName: '曹七' },
            //            { id: 6, dictTypeSubName: '余八' }
            //        ]
            //    },
            //    { id: 4, dictTypeName: '四班' }
            //];
        }).error(function(data, status) {
            ajaxErrorHandler(status);
        });
    };

    // 初始化数据
    $scope.myReload($.trim($("#keyWord").val()));

    // 关键字查询
    $scope.queryList = function() {
        $scope.myReload($.trim($("#keyWord").val()));
        //enter submit
    };
    $("#dictTypeView").find(".gui-btn").on("click", $scope.queryList);
    $scope.onInputChanged = function($event) {
        if ($event && $event.keyCode == 13) {
            $scope.queryList();
        }
    };

    // 新增模态框
    $scope.addModal = function() {
        $("#attributeForm").data('bootstrapValidator').destroy(); //摧毁验证
        $('#attributeForm').bootstrapValidator({

        }); //必须有
        $scope.title = "新增";
        //设置actnion
        // 初始化下拉列表
        getParentDictTypes();
        $("#add_modal").modal("show");
    };

    //新增数据字典信息方法(点击提交后请求服务器)
    $scope.addDictData = function() {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } //必须有
        var params = {};
        params.pid = $("select[name='typeId']").val();
        params.code = $("input[name='code']").val();
        params.name = $("input[name='name']").val();
        params.enable = $("select[name='enable']").val();
        params.remark = $("input[name='remark']").val();
        $.ajax({
            url: "/sofn-sys-web/sysDict/addDictType",
            headers: {
                token: window.localStorage.getItem('token')
            },
            data: params,
            type: "post"
        }).success(function(data) {
            if (data.httpCode == 200) {
                $scope.myReload($.trim($("#keyWord").val()));
                $("#add_modal").modal("hide");
                jBox.tip('保存成功', 'success');
            } else {
                jBox.tip('保存失败', 'error');
            }
        }).error(function(data) {
            ajaxErrorHandler(data.status);
        });
    };

    // 下拉列表事件
    $scope.changeDictType = function() {
        var id = $("select[name='pid']").val();
        // $("#checkInput").html('');
        $scope.updateNewModal(id);
    };

    // 选中编辑
    //$scope.updateDictTypeDate = {};
    //
    //$scope.doYouClick = function(event,id){
    //    updateDictTypeDate.pid =  $("select[name='pid']").val();
    //    updateDictTypeDate.enable = $("select[name='newEnable']").val();
    //    $scope.subType = [];
    //    var obj = event.target;
    //    if(obj.checked){
    //        subType.enable = 'Y';
    //    }else
    //        subType.enable = 'N';
    //    updateDictTypeDate.push(subType);
    //    console.log($scope.updateDictTypeDate);
    //};

    //<editor-fold desc="前一个版本的编辑">
    ///////////////////////////////////////////////////////////////
    // 编辑
    $scope.updateModal = function(id) {
        //alert($scope.hidenId);
        if ($scope.hidenId == null || $scope.hidenId.id == '') {
            alert("请选择需要编辑的字典类型");
            return;
        }
        $scope.title = "编辑";
        $http({
            url: "/sofn-sys-web/sysDict/getDictTypeById",
            params: {
                'id': $scope.hidenId
            },
            method: "POST",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(json) {
            $scope.sysDictNewType = json.data;
            $("#pid").find("[value='" + json.data.pid + "']").attr("selected", "selected");
            $("#enable").find("[value='" + json.data.enable + "']").attr("selected", "selected");
            $("#update_modal").modal("show");
        }).error(function() {
            alert("保存失败");
        });
    };

    // 更新方法
    $scope.updateDictType = function() {
        var params = {};
        params.id = $scope.hidenId;
        params.pid = $("#pid").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.enable = $("#newEnable").val();
        params.remark = $("#remark").val();
        $.ajax({
            url: "/sofn-sys-web/sysDict/updateDictType",
            data: params,
            type: "post"
        }).success(function() {
            $scope.myReload($.trim($("#keyWord").val()));
            $("#update_modal").modal("hide");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function() {
            alert("保存失败");
        });
    };

    ///////////////////////////////////////////////////////////////
    //</editor-fold>
    $("#resource").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysDict/getDictTypes2",
            headers: {
                token: window.localStorage.getItem('token')
            },
            data: function(params) {
                // console.log(window.localStorage.getItem("dictTypeId"));
                if (dictTypeId == null || dictTypeId == '') {
                    dictTypeId = 'bcf062df2aa94c308a55d6a1acec941eda6cb4b29bcc4615a9cb73c230a3c2a1';
                }
                params.id = dictTypeId;
                params.keyWord = $.trim($("#keyWord").val());
            },
            type: "post"
        },
        fnDrawCallback: function() {
            $('.bottom').hide();
        },
        columns: [{
            data: "name",
            title: "字典名称"
        }, {
            data: "enable",
            title: "有效",
            render: function(flag, type, row) {
                return '<div class="text-center"><input type="checkbox" name="enable-checkbox" data-id="' + row.id + '"' + (flag == "Y" ? " checked=checked" : "") + '/></div>'
            }
        }]
    }).on('draw.dt', function() {
        $("[name='enable-checkbox']").bootstrapSwitch({
            size: 'mini',
            onText: '启用',
            offText: '禁用',
            onSwitchChange: function(evt, state) {
                updateDictType2(evt.currentTarget.getAttribute('data-id'));
            }
        });
    });

    //修改
    window.updateDictType2 = function(data) {
        $.ajax({
            url: "/sofn-sys-web/sysDict/updateDictType2",
            headers: {token: window.localStorage.getItem('token')},
            type: "post",
            dataType: "json",
            data: { id: data },
            success: function(data) {
                if (data.httpCode == 200) {
                    $("#resource").dataTable().api().ajax.reload();
                } else {
                    $.jBox.tip("修改数据失败！");
                }
            },
            error: function(data) {
                ajaxErrorHandler(data.status);
            }
        });
    }

    // 编辑回显
    $scope.updateNewModal = function(id) {
        $scope.dictSub = "";
        dictTypeId = id;
        $scope.title = "编辑";
        //  $("#resource").dataTable().api().ajax.reload();
        $("#updateType").modal("show");
        $("#resource").dataTable().api().ajax.reload();
        // $http({
        //     url: "/sofn-sys-web/sysDict/getDictTypes",
        //     params: {
        //         'id': id
        //     },
        //     method: "POST",
        //     headers: {
        //         post: {'Content-Type': 'application/x-www-form-urlencoded'}
        //     }
        // }).success(function (json) {
        //     //var vr = $scope.vr = {};
        //     //vr.dictType = json.data;
        //     // $scope.updateType.pid = json.data[0].id;
        //     // $scope.updateType.enable = json.data[0].enable;
        //     $("#pid").val(json.data[0].id);
        //     $("#newEnable").val(json.data[0].enable);
        //
        //     // 设置复选框是否被选中
        //     //$scope.updateType= json.data;
        //
        //     $("#updateType").modal("show");
        //     $scope.dictSub = json.data[0].sysDictType;
        //
        //     $("#resource").dataTable({
        //         ajax:"",
        //         columns:[{
        //             data: "dictName",
        //             title: "字典名称",
        //         },{
        //             data: "dictType",
        //             title: "字典状态",
        //         }]
        //     })
        //
        //     // for (var i = 0; i < json.data[0].sysDictType.length; i++) {
        //     //     $("#checkTable").append('<label style="display: inline-block">' +
        //     //         '<label class="mr15"> ' +
        //     //         '<input name="dictTypeSub" checked="checked" type="checkbox" value="'
        //     //         + json.data[0].sysDictType[i].name + '" id = "'
        //     //         + json.data[0].sysDictType[i].id + '" />'
        //     //         + json.data[0].sysDictType[i].name + '</label></label>')
        //     // }
        //     //
        //     // for (var i = 0; i < $scope.dictSub.length; i++) {
        //     //     console.log($scope.dictSub[i].enable == 'Y');
        //     //     var obj = $("#" + $scope.dictSub[i].id);
        //     //     if ($scope.dictSub[i].enable == 'N') {
        //     //         console.log(obj);
        //     //         // todo 不起作用
        //     //         obj.removeAttr("checked");
        //     //         //obj.attr("checked","false");
        //     //     }
        //     // }
        // }).error(function () {
        //     alert("保存失败");
        // });


    };

    /**
     * 点击事件,点击一次将 id 和是否被选中添加到隐藏域中
     * 记录需要被更新的数据ID
     */
    /*更多*/

    $scope.showMore = function($event) {
        $($event.target).next().toggleClass("type-warp-maxheight");
    };

    // 更新方法
    $scope.updateDictType = function(target) {
        var checkedBoxs = target;
        var subDictType = "";
        for (var i = 0; i < checkedBoxs.length; i++) {
            //console.log(checkedBoxs[0].id);
            var isChecked = $('#' + checkedBoxs[i].id).attr('checked');
            // console.log(isChecked);
            if (isChecked == 'checked') {
                subDictType += checkedBoxs[i].id + "@" + "Y,";
            } else
                subDictType += checkedBoxs[i].id + "@" + "N,";
        }
        var params = {};
        params.pid = $("#pid").val();
        params.enable = $("#newEnable").val();
        params.subDictType = subDictType;
        $.ajax({
            url: "/sofn-sys-web/sysDict/updateDictType",
            data: params,
            type: "post"
        }).success(function() {
            $scope.myReload($.trim($("#keyWord").val()));
            $("#updateType").modal("hide");
        }).error(function() {
            alert("保存失败");
        });
    };

    //跳转页面-数据字典详情
    window.goToDictDataView = function(obj) {
        // console.log(obj.name);
        window.localStorage.setItem("dictTypeId", obj.name);
        $state.go("index.content.sys/dictManager/dictDataView");
    };


    //初始化字典类别下拉
    function getParentDictTypes() {
        $http({
            url: "/sofn-sys-web/sysDict/getParentDictTypes",
            params: {},
            method: "get",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).then(function(json){
            $scope.dictTypes = json.data.data;
        }, function(){
             alert("失败");
        });
    }
    getParentDictTypes();

    $("#updateType").on("hide.bs.modal", function(){
        $scope.$evalAsync(function() {
            $scope.updateType.pid = '';
        });
    });
});