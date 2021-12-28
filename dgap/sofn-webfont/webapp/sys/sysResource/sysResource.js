var myapp = angular.module("myapp", []);
myapp.controller("sysResource", function ($scope, $http, $state, $timeout) {
    $scope.sysResource = {
        province: "51",
        city: "",
        county: ""
    };
    $scope.sysResourceId=window.localStorage.getItem("sysResourceId");
    $scope.addForm = {
        region: "",
        addProvinceSelect: "",
        addCitySelect: "",
        addCountySelect: "",
        valided: false
    };

    $scope.typeList = [{name:'专家',value:'1'}];
    $scope.typeSelect='1';
    $scope.addResourceTypeSelect='1';
    $scope.updateResourceTypeSelect='1';


    $scope.fieldList = [{name:'专业领域选择',value:''},{name:'全部',value:''},{name:'检测',value:'1'},{name:'农药',value:'2'},{name:'检验检疫',value:'3'}];
    $scope.filedSelect='';
    $scope.addFieldList =[{name:'检测',value:'1'},{name:'农药',value:'2'},{name:'检验检疫',value:'3'}];
    $scope.addTypeSelect='';

    $scope.statusList = [{name:'正常',value:'1'},{name:'禁用',value:'0'}];
    $scope.statusForAddSelect='1';
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('资源管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;

    $.fn.mycity("sheng","shi","xian");
    $.fn.mycity("shengAdd","shiAdd","xianAdd");
    $.fn.mycity("shengUpdate","shiUpdate","xianUpdate");

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
    //查询
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });

    $scope.jobTitles = [];


    $.ajax({
        method: 'POST',
        url: "/sofn-sys-web/sysDict/getDictDataByType",
        dataType: "json",
        async: true,
        data: {
            id: "a00e467943a84d399d25e45d859a5e9aaae44ac7e21941d891bfd74dcf3ee44b",
            draw: 1,
            start: 1,
            length: 50000
        },
        headers: {
            post: {'Content-Type': 'application/x-www-form-urlencoded'},
            token: window.localStorage.getItem('token')
        }
    }).success(function (response) {
        for (var i = 0; i < response.list.length; i++) {
            var data = response.list[i];
            $scope.jobTitles[i] = {dictName: data.dictName, dictValue: data.dictValue};
        }
        // console.log($scope.jobTitles);

        sysResTable();
    }).error(function(data) {
        ajaxErrorHandler(data.status);
    });

    function getLocationValue(sheng,shi,xian){
        var location = "";
        if(xian.length == 6){
            location = xian;
        }else if(shi.length == 4){
            location = shi + "00";
        } else if(sheng.length == 2){
            location = sheng + "0000";
        }
        return location;
    }
    //跳转新增页面
    $scope.addResource = function () {
        $scope.removeLocalStorage();/*清楚缓存*/
        $scope.addResourceTypeSelect = "1";
        $scope.sysResource.name = "";
        $scope.addTypeSelect = "";
        $scope.addJobTitleSelect = "1";
        $scope.sysResource.unit = "";
        $("select[id='shengAdd']").val("");
        $("select[id='shiAdd']").val("");
        $("select[id='xianAdd']").val("");
        $scope.addForm.addProvinceSelect = "";
        $scope.addForm.addCitySelect = "";
        $scope.addForm.addCountySelect = "";
        $scope.sysResource.address = "";
        $scope.sysResource.contract = "";
        $scope.statusForAddSelect = "1";
        $("select").css("border-color","#e0e0e0");
        $("input").css("border-color","#e0e0e0");
        $scope.addModalForm.$setPristine(); // 重置校验
        $scope.addForm.valided = false;
        $("#addModal").modal("show");
    }
    // 重复资源检查
    $scope.checkRepeat = function(callback) {
        // 修改资源如果姓名和单位没有改动，不进行重复资源检查
        if ($scope.updateResourceForm.name.$dirty == false && $scope.updateResourceForm.unit.$dirty == false) {
            callback();
            return;
        }
        // 重复资源检查
        $http({
            url: "/sofn-sys-web/sysResource/repeatCount",
            method: "post",
            data: {
                name: $scope.sysResource.name,
                unit: $scope.sysResource.unit
            },
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            if (json.data.count == 0) {
                callback();
            } else {
                alert('已有相同单位和姓名的资源存在，不能重复添加');
            }
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        });
    }
    /*新增资源*/
    $scope.submitForm = function(valid) {
        $scope.addForm.valided = true;
        if (!valid) {
            alert('请输入必填项');
        } else {
            // 重复资源检查
            $scope.checkRepeat($scope.saveResource);
        }
        return false;
    };
    $scope.saveResource = function () {
        $http({
            url: "/sofn-sys-web/sysResource/add",
            data: {
                resourcetype: $scope.addResourceTypeSelect,
                name: $("input[name='NAME']").val(),
                professionalfiled: $scope.addTypeSelect,
                jobtitle: $scope.addJobTitleSelect,
                unit: $scope.sysResource.unit,
                location: getLocationValue($("select[id='shengAdd']").val(),
                    $("select[id='shiAdd']").val(),$("select[id='xianAdd']").val()),
                address: $scope.sysResource.address,
                contract: $scope.sysResource.contract,
                status: $scope.statusForAddSelect
            },
            method: "post",
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).success(function (data) {
            if (data.httpCode == 200) {
                alert("保存成功");
                $("#grid").dataTable().api().ajax.reload();
                $("#addModal").modal("hide");
            } else {
                alert("保存失败");
            }
        }).error(function (data, status) {
            ajaxErrorHandler(status);
        })
    },
        //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("sysResourceId");
    }
    /* 取消保存*/
    $scope.cancelResource = function (id) {
        if(id == 'cancelAdd'){
            $("#addModal").modal("hide");
        }
        if(id == 'cancelUpdate'){
            $("#updateModal").modal("hide");
        }
    }

    $("#shengUpdate").on("change", function(){
        var value = $(this).val();
        $scope.$evalAsync(function(){
            $scope.sysResource.province = value;
            $scope.updateResourceForm.$dirty = true;
        });
    });
    $("#shiUpdate").on("change", function(){
        var value = $(this).val();
        $scope.$evalAsync(function(){
            $scope.sysResource.city = value;
            $scope.updateResourceForm.$dirty = true;
        });
    });
    $("#xianUpdate").on("change", function(){
        var value = $(this).val();
        $scope.$evalAsync(function(){
            $scope.sysResource.county = value;
            $scope.updateResourceForm.$dirty = true;
        });
    });

    $scope.updateResourceValided = false;
    //跳转修改页面
    window.update = function (data) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $scope.updateResourceForm.$setPristine();
        $scope.updateResourceValided = false;
        $scope.dataId = data;
        window.localStorage.removeItem("sysResourceId", data);

        $http({
            url: "/sofn-sys-web/sysResource/read/detail",
            headers: {
                token: window.localStorage.getItem("token")
            },
            method: "post",
            data: {
                id: data
            },
        }).success(function (data) {
            $scope.sysResource = data.data;
            $.fn.mycity("shengUpdate","shiUpdate","xianUpdate", $scope.sysResource.location);
            $scope.sysResource.province = $("#shengUpdate").val();
            $scope.sysResource.city = $("#shiUpdate").val();
            $scope.sysResource.county = $("#xianUpdate").val();
            $scope.statusForUpdateSelect = $scope.sysResource.status;

            $("#updateModal").modal("show");
        }).error(function (data, status) {
            ajaxErrorHandler(status);
        });
    }

    /*修改资源*/
    $scope.updateResource = function () {
        /** 以下变量赋值仅用于校验 **/
        $scope.updateResourceValided = true;
        $scope.sysResource.province = $("#shengUpdate").val();
        $scope.sysResource.city = $("#shiUpdate").val();
        $scope.sysResource.county = $("#xianUpdate").val();

        // 校验通过保存更改
        if ($scope.updateResourceForm.$valid) {
            if ($scope.updateResourceForm.$dirty) { // 重复资源检查
                $scope.checkRepeat(function () {
                    $http({
                        url: "/sofn-sys-web/sysResource/update",
                        data: {
                            id: $scope.dataId,
                            resourcetype: $scope.updateResourceTypeSelect,
                            name: $scope.sysResource.name,
                            professionalfiled: $scope.sysResource.professionalfiled,
                            jobtitle: $("select[name='updateJobTitle']").val(),
                            unit: $("input[name='unit']").val(),
                            location: getLocationValue($("select[id='shengUpdate']").val(),
                                $("select[id='shiUpdate']").val(), $("select[id='xianUpdate']").val()),
                            address: $("input[name='address']").val(),
                            contract: $("input[name='contract']").val(),
                            status: $scope.statusForUpdateSelect,
                        },
                        method: "post",
                        headers: {
                            token: window.localStorage.getItem("token")
                        }
                    }).success(function (data) {
                        if (data.httpCode == 200) {
                            alert("保存成功");
                            $("#updateModal").modal("hide");
                            $("#grid").dataTable().api().ajax.reload();
                        } else {
                            alert("保存失败");
                        }

                    }).error(function (data, status) {
                        ajaxErrorHandler(status);
                    })
                });
            } else {
                $("#updateModal").modal("hide");
            }

        } else {
            alert('请输入必填项');
        }
    }


    /*资源导入*/
    $scope.importResource = function () {
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('file', file);
        $http({
            method: 'POST',
            url: "",
            data: fd,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function (response) {
            //上传成功的操作
            alert("上传成功");
            $("#importModal").modal("hide");
            $("#grid").dataTable().api().ajax.reload();
        });
    }

    //导入模态框显示
    $("#importResource").click(function () {
        $("#importModal").modal("show");
    });

    //查询功能
    // $("#ToCheck").click(function () {
    //     var keyWord=$.trim($("input[name='name']").val());
    //     /*汉字1-30个，字母和数字1-60个*/
    //     var KW=/^[\u4e00-\u9fa5]$/;
    //     var len=0;
    //     for(var i=0;i<keyWord.length;i++){
    //         if(KW.test(keyWord[i])){
    //             len=len+2;
    //         }else {
    //             len++;
    //         }
    //     }
    //     if(len>60){
    //         alert("查询名称只能为1-60个字符或者1-30个汉字")
    //     }else {
    //         $("#grid").dataTable().api().ajax.reload();
    //     }
    // });
    //监听浏览器变化重新加载
    // $(window).resize(function () {
    //     $("#grid").dataTable().api().ajax.reload();
    // })
//分页

         function sysResTable(){
            $("#grid").dataTable({
            ajax: {
                url: "/sofn-sys-web/sysResource/read/list",
                headers: {token: window.localStorage.getItem("token")},
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.length; i++) {
                        if (json.data[i].INSPECTION_TIME != null && json.data[i].INSPECTION_TIME != "" && json.data[i].INSPECTION_TIME != undefined) {
                            json.data[i].INSPECTION_TIME = new Date(parseInt(json.data[i].INSPECTION_TIME) + 1000 * 60 * 60 * 24).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.resourcetype = $scope.typeSelect;
                    params.professionalfiled = $scope.filedSelect;
                    var sheng =  $("select[id='sheng']").val();
                    var shi =  $("select[id='shi']").val();
                    var xian =  $("select[id='xian']").val();
                    var location = "";
                    if(xian.length == 6){
                        location = xian;
                    }else if(shi.length == 4){
                        location = shi;
                    } else if(sheng.length == 2){
                        location = sheng;
                    }
                    params.location = location;
                    params.name =$.trim($("input[name='name']").val()) ;
                },
                type: "POST",
            },
            columns: [
                {
                    data : "ck",
                    title : '<input id="checkAll"  type="checkbox"/>',
                    render : function(data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width:"3%"

                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"3%"
                },
                {
                    data: "RESOURCETYPE",
                    title: "资源类型",
                    width:"10%",
                    render: function (data, type, row) {
                        for (var j = 0; j < $scope.typeList.length; j++){
                            if($scope.typeList[j].value == data) {
                                return $scope.typeList[j].name;
                            }
                        }
                        return data;
                    }
                },
                {
                    data: "PROFESSIONALFILED",
                    title: "专业领域",
                    width:"10%",
                    render: function (data, type, row) {
                        for (var j = 0; j < $scope.fieldList.length; j++){
                            if($scope.fieldList[j].value == data){
                                return $scope.fieldList[j].name;
                            }
                        }
                        return data;
                    }
                },
                {
                    data: "NAME",
                    title: "姓名",
                    width:"10%"
                },
                {
                    data: "JOBTITLE",
                    title: "职称",
                    width:"10%",
                    render: function (data, type, row) {
                        for (var j = 0; j < $scope.jobTitles.length; j++){
                            if($scope.jobTitles[j].dictValue == data){
                                 return $scope.jobTitles[j].dictName;
                            }
                        }
                        return data;
                    }
                },
                {
                    data: "LOCATION",
                    title: "所在地",
                    render: function (data, type, row) {
                        if(data==null){
                            data=""
                        }
                        return $.fn.Cityname(data);
                    },
                    width:"10%"
                },
                {
                    data: "UNIT",
                    title: "单位",
                    width:"10%"
                },
                {
                    data: "ADDRESS",
                    title: "详细地址",
                    width:"15%"
                },
                {
                    data: "CONTRACT",
                    title: "联系方式",
                    width:"10%"
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<input class="idValue" type="hidden" value="'+data+'"><a style="cursor: pointer" class= "color Check-report xiugai"   onclick="update(\'' + data + '\')">修改</a>';//转义传值
                    },
                    width:"8%"
                },
            ],
        })};


    //删除
    $scope.deleteResource=function(){
        var i = $scope.getSelectRowsIds();
        var delIds="";
        $("input[name='ck']:checked ").each(function () {
            delIds=delIds+$(this).parent().parent().children().eq(10).children().eq(0).val()+"|";
            console.info(delIds);
        })
        if (i!=false){
            if(confirm('是否批量删除已选择行？'))
            {
                var data = {
                    dataInfo : delIds
                };
                $.ajax({
                    url:"/sofn-sys-web/sysResource/delete",
                    type:"post",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    dataType:"json",
                    data:data,
                    success:function(data){
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();
                    },
                    error: function (data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        }
    };



    $scope.getSelectRowsIds = function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if(length<=0){
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }

        return ids;
    }
    document.onkeydown=keyListener;
    function keyListener(e){
        if(e.keyCode == 13){
            $("#grid").dataTable().api().ajax.reload();
        }
    }
});
