var myapp = angular.module("myapp", []);
myapp.controller("resourceApplication", function ($scope, $http, $state) {
    /*加载外部的js*/
    var testScript = document.createElement('script');
    testScript.src = 'assets/js/dgap/common.js';
    testScript.type = 'text/javascript';
    var head = document.getElementsByTagName('head');
    head[0].appendChild(testScript);
    //验证表单
    $('#attributeForm').bootstrapValidator();//必须有
    $(".pass").click(function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        pass();
    });
    $(".not").click(function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        not();
    });
    /*执行修改之后清空上次添加的内容*/
    $('#application').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
//查询功能
    $scope.search = function () {
        $("#resource").dataTable().api().ajax.reload();
    };

    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.search();
        }
    };
//清除查询条件
    $scope.cleanData = function () {
        $("input[name='resourceName']").val("");
        $("select[name='resourType']").val("");
        $("input[name='username']").val("");
        $("#resource").dataTable().api().ajax.reload();
    };
    //分页
    $("#resource").dataTable({
        ajax: {
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/resourceApplication/lists",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].EFFECTIVE_DATE != null && json.data.list[i].EFFECTIVE_DATE != "" && json.data.list[i].EFFECTIVE_DATE != undefined) {
                        json.data.list[i].EFFECTIVE_DATE = new Date(parseInt(json.data.list[i].EFFECTIVE_DATE)+8*3600*1000).toISOString().slice(0, 10);
                    }
                }
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].EXPIRE_DATE != null && json.data.list[i].EXPIRE_DATE != "" && json.data.list[i].EXPIRE_DATE != undefined) {
                        json.data.list[i].EXPIRE_DATE = new Date(parseInt(json.data.list[i].EXPIRE_DATE)+8*3600*1000).toISOString().slice(0, 10);

                    }else{
                        json.data.list[i].EXPIRE_DATE ="无"
                    }
                }
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].CREATE_TIME != null && json.data.list[i].CREATE_TIME != "" && json.data.list[i].CREATE_TIME != undefined) {
                        json.data.list[i].CREATE_TIME = formatTime(json.data.list[i].CREATE_TIME);

                    }
                }
                //js将时间为时间戳格式转成string格式
                function addZero(num) {
                    return num < 10 ? '0' + num : num;
                }

                function formatTime(timeStamp) {
                    var date = new Date(timeStamp);
                    var year = date.getFullYear();
                    var month = addZero(date.getMonth() + 1);
                    var day = addZero(date.getDate());
                    return [year, month, day].join('-');
                }

                return json.data.list;
            },
            //查询条件
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.resourceName = $("input[name='resourceName']").val();
                params.resourceType = $("select[name='resourType']").val();
                params.userName = $("input[name='username']").val()
            },
            type: "post"
        },

        columns: [
            {
                data: "DNAME",
                title: "资源名称"

            },
            {
                data: "TYPE",
                render: function (data) {
                    if(data=="di"){
                        return  "数据导入";
                    }else  if(data=="ds"){
                        return "仓库资源"
                    }else {
                        return "无"
                    }
                },
                title: "资源类型"
            },
            {
                data: "USER_NAME",
                title: "申请用户"

            },
            {
                data: "EFFECTIVE_DATE",
                title: "生效时间"

            }, {
                data: "EXPIRE_DATE",
                title: "失效时间"

            },
            {
                data: "PASS_OR_NOT",
                title: "审批状态",
                render: function (data) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return lookupName(data, $scope.ResourceApplicationPassOrNot);//转义传值
                    }else{
                        return "待审批"
                    }

                },
                width: "8%"

            },
            {
                data: "APPROVAL",
                title: "审批意见",
                render: function (data, type, row) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return  data;
                    }else{
                        return "无"
                    }
                },
                width: "30%",
            }, {
                data: "CREATE_TIME",
                title: "申请时间"
            },
            {
                data: "ID",
                title: "操作",
                render: function (data,type,row) { // 模板化列显示内容
                    if (row.PASS_OR_NOT == "Y" || row.PASS_OR_NOT== "N") {
                        return '<input class="idValue" type="hidden" value="' + data + '"> <span class="color" >已审批</span>'
                    }
                    {
                        return '<a data-toggle="modal1" data-target="#Modal" style="margin-right: 10px;cursor: pointer" id="myId" class="color" onclick="application(\'' + data + '\')">审批</a>'
                    }
                }
            }
        ]
    });

    /*打开模态框*/
    window.application = function (data) {
        $scope.resId = data;
        $http({
            url: "/sofn-dgap-web/resourceApplication/getApplication",
            data: {id: $scope.resId},
            method: "post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $("#application").modal("show");
        })
    };
    /*审批意见通过*/
    function pass() {
        $scope.applicationUpdata = function () {
            //获取表格数据
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-dgap-web/resourceApplication/applicationdataUpdate",
                data: {
                    id: $scope.resId,
                    approval: $("textarea[name='approval']").val(),
                    passOrNot: "Y"
                },
                method: "post"
            }).success(function (data) {
                jBox.tip("审批通过", 'info');
                //$state.go("index.content.dgap/resource/dgapResourceApplication");
                $("#resource").dataTable().api().ajax.reload();
            }).error(function () {
                jBox.tip("操作不成功", 'info');
            })
        }
    }

    /*审批意见未通过*/
    function not() {
        $scope.applicationNoUpdata = function () {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-dgap-web/resourceApplication/applicationdataUpdate",
                data: {
                    id: $scope.resId,
                    approval: $("textarea[name='approval']").val(),
                    passOrNot: "N"
                },
                method: "post"
            }).success(function (data) {
                jBox.tip("申请未通过", 'info');
                //$state.go("index.content.dgap/resource/dgapResourceApplication");
                $("#resource").dataTable().api().ajax.reload();
            }).error(function () {
                jBox.tip("操作不成功", 'info');
            })
        }
    }

//获取资源名称
    $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceType",
        method: 'POST'
    }).success(function (data) {
        $scope.resourceType = data.data;
        // $("#resource").dataTable().api().ajax.reload();
    });

    // //获取web服务接口名称
    // $http({
    //     url: "/sofn-dgap-web/alertConfig/getWeb",
    //     method: "post"
    // }).success(function (data) {
    //     $scope.webs = data.data;
    // });

    $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceApplicationPassOrNot",
        mathod: "post"
    }).success(function (data) {
        $scope.ResourceApplicationPassOrNot = data.data;
        // $("#resource").dataTable().api().ajax.reload();
    }).error(function () {
        $.jBox.tip("操作不成功！！！");
    })
});
