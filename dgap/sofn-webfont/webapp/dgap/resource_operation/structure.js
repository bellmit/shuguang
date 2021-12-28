var myapp = angular.module("myapp", []);
myapp.controller("structure", function ($scope, $http, $state) {
    /*加载外部的js*/
    var testScript = document.createElement('script');
    testScript.src = 'assets/js/dgap/common.js';
    testScript.type = 'text/javascript';
    $scope.resource=[];
    loadGrid();


    //bootstrap表格
    //资源发布主体信息
    function loadGrid() {
        if($scope.tableId==null||$scope.tableId==""||$scope.tableId==undefined){
            $scope.tableId="null"
        }
        $("#resource").dataTable({
            processing: true, // 加载时提示
            serverSide: false, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            paging:false,
            bInfo:false,
            bJQueryUI: false,
            bPaginate: false,// 分页按钮
            bFilter: false,// 搜索栏
            // iDisplayLength: 10,// 每页显示行数
            bSort: true,// 排序
            bAutoWidth: false,
            //sScrollY: "auto",
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-dgap-web/resourcePublish/getFields",
                dataSrc: function (json) {
                    return json.data;
                },
                data: function (params) {
                    params.token=window.localStorage.getItem("token");
                    params.tableId=$scope.tableId
                },
                type: "post"
            },
            columns: [{
                data: "englishName",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input  value="' +data+ '" onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" type="checkbox" name="ck"/>';
                },
                width: "3%",
            }, {
                data: "chineseName",
                title: "中文字段名",
                width: "8%",
            }, {
                data: "englishName",
                title: "英文字段名",
                width: "10%",
            }, {
                data: "type",
                title: "字段类型",
                width: "10%",
            }, {
                data: "len",
                title: "字段长度",
                render: function (data) {
                    if(data!="4000"){
                        return data;
                    }else{
                        return "";
                    }

                },
                width: "10%",
            },
                {
                    data: "englishName",
                    title: "是否可作为查询条件",
                    render: function (data, type, row) {
                        return '<input  id="' +data + '" value="' + data + '"  name="addCondition" onclick="addCondition(\'' + row.englishName + '\')" type="checkbox" />';
                    },
                    width: "10%",
                }
            ]
        })
    }

    //重新加载表格
    $scope.queryList = function () {
        $("#resource").dataTable().api().ajax.reload();
    }


    //得到默认资源url
    $http({
        url: "/sofn-dgap-web/resourcePublish/getbaseUrls",
        method: 'POST',
    }).success(function (data) {
        $scope.reourceURL = data.data;
    })


    //展示仓库表数据页面
    $scope.selectData = function(){
        $("#dataModal").modal("show");
        loadDataGrid();
    };
    var columnsJson=[];
    function loadDataGrid() {
        if($scope.tableId==null||$scope.tableId==""||$scope.tableId==undefined){
            $scope.tableId="null"
        }
        $.ajax({
            type: 'POST',
            url: "/sofn-dgap-web/resourcePublish/getColumns",
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: {
                tableId : $scope.tableId
            },
            success: function(d) {
                $scope.fields=d.data.fields;
                if(columnsJson.length>0){//清空数组
                    columnsJson=[];
                }
                for(var i=0;i<$scope.fields.length;i++){//制作表头
                    var filedType=$scope.fields[i].type;
                    if(filedType=="DATE"||filedType=="TIMESTAMP(6)"){//格式化时间
                        if($scope.fields[i].title==null||$scope.fields[i].title==""){ //中文为空显示英文
                            list ={
                                data: $scope.fields[i].dataIndex,
                                title: $scope.fields[i].dataIndex,
                                render: function (data, type, row) {
                                    return formatDate(data)
                                }
                            };
                        }else{
                            list ={
                                data: $scope.fields[i].dataIndex,
                                title: $scope.fields[i].title,
                                render: function (data, type, row) {
                                    return formatDate(data)
                                }
                            };
                        }
                    }else if(filedType=="VARCHAR"||filedType=="VARCHAR2"||filedType=="BLOB"){//缩略展示长文本
                        if($scope.fields[i].title==null||$scope.fields[i].title==""){//中文为空显示英文
                            list ={
                                data: $scope.fields[i].dataIndex,
                                title: $scope.fields[i].dataIndex,
                                render: function(data, type, row, meta) {
                                    if (data) {
                                        data = data.substring(0, data.length);
                                        var showdata = data;
                                        if (data.length > 10) {
                                            showdata = data.substring(0, 10) + "..";
                                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                        }
                                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                    } else {
                                        return '--';
                                    }
                                } ,

                            };
                        }else{
                            list ={
                                data: $scope.fields[i].dataIndex,
                                title: $scope.fields[i].title,
                                render: function(data, type, row, meta) {
                                    if (data) {
                                        data = data.substring(0, data.length);
                                        var showdata = data;
                                        if (data.length > 10) {
                                            showdata = data.substring(0, 10) + "..";
                                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                        }
                                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                                    } else {
                                        return '--';
                                    }
                                } ,

                            };
                        }
                    }else if($scope.fields[i].title==null||$scope.fields[i].title==""){//中文为空显示英文
                        list ={ data: $scope.fields[i].dataIndex, title: $scope.fields[i].dataIndex};
                    }else{
                        list ={ data: $scope.fields[i].dataIndex, title: $scope.fields[i].title	};
                    }
                    columnsJson.push(list);
                }
                if ($('#dataGrid').hasClass('dataTable')) {
                    var oldTable = $('#dataGrid').dataTable();
                    oldTable.fnClearTable(false); //清空一下table
                    oldTable.fnDestroy(); //还原初始化了的dataTable
                    $('#dataGrid').empty();
                }
                $('#dataGrid').DataTable({
                    "scrollX":true,
                    ajax: {
                        url: "/sofn-dgap-web/resourcePublish/getPages",
                        dataSrc: function (json) {
                            json.recordsTotal = json.data.total; //总个数
                            json.recordsFiltered = json.data.total;
                            json.pageNumber = json.data.pageNum; //页码
                            json.pageSize = json.data.pageSize; //每页个数
                            json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                            json.length = json.data.pageSize;
                            // json.recordsTotal = json.data.tabletotal; //总个数
                            // json.recordsFiltered = json.data.table.total;
                            // json.pageNumber = json.data.table.pageNum; //页码
                            // json.pageSize = json.data.table.pageSize; //每页个数
                            // json.start = (json.data.table.pageNum - 1) * json.data.table.pageSize; //起始位置
                            // json.length = json.data.table.pageSize;
                            return json.data.list;
                        },
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        data: function (params) {
                            params.tableId = $scope.tableId;
                        },
                        type: "post"
                    },
                    dom: "Bfrtip",
                    columns:columnsJson
                });
            }
        });
    }

    //展示仓库表数据页面
    $scope.pushResource = function(){
        $("#pushModal").modal("show");
    };
    window.formatDate= function ( timestamp) {
        var date=new Date(timestamp)
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y+M+D+h+m+s;
    }

    //获取树状仓库表列表
    $scope.queryTableName = function (){
        var tableName=$("input[name='tableName']").val();
        $http({
            url: "/sofn-dgap-web/resourcePublish/getTables",
            data:  {
                // token:window.localStorage.getItem("token"),
                tableName:tableName
            },
            headers:{
                token:window.localStorage.getItem("token"),
                // "Content-Type": 'application/x-www-form-urlencoded'
            },
            method: "post"
        }).success(function (data) {
            $scope.resourcePublis = data.data[0].children;
        }).error(function () {
            $.jBox.tip("操作不成功！");
        })
    }
    $scope.queryTableName();

    //获取资源目录
    $http({
        url: "/sofn-dgap-web/resourceDirectory/tree",
        data: {},
        method: "post",
    }).success(function (data) {
        $scope.resourceDir = data.children;
    }).error(function () {
        $.jBox.tip("操作不成功！");
    })
    //获取资源类型
    $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceType",
        method: 'POST',
    }).success(function (data) {
        $scope.resourceType = data.data;
    })

    /*  发布资源*/
    $('#pushForm').bootstrapValidator(); //必须有
    $(".pushResources").click(function () {
        if (!$("#pushForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } //必须有
        //           提交执行函数全写在这下面
        pushResources();
    })
    //新增资源管理;
    function pushResources() {
        $scope.pushResources = function () {
            $http({
                url: "/sofn-dgap-web/resourcePublish/configResource2",
                data: {
                    name: $scope.resourceName,
                    wsdlUrl: $scope.reourceURL,
                    filePath: $scope.reourceFilePath,
                    type:$("select[name='resourceTypes']").val(),
                    directoryId:$("select[name='resourceDirName']").val(),
                    description: $("textarea[name='description']").val(),
                    flag: $("select[name='resourceFlag']").val(),
                    publishFields:$scope.publishFields,
                    conditionFields:$scope.conditionFields
                },
                headers:{
                    tableId: $scope.tableId,
                    token:window.localStorage.getItem("token")
                },
                method: 'post'
            }).success(function (data) {
                $("#pushModal").modal("hide");
                if(data.httpCode == 400){
                    $.jBox.tip("数据类型不存在","info");
                }else if(data.httpCode == 409){
                    $.jBox.tip("不能新增资源类型为仓库资源的数据","info");
                }else {
                    if (data.httpCode == 200) {
                        $.jBox.tip("新增成功！！！");
                        $("select[name='resourceDirName']").val("");
                        $scope.resourceName="";
                        $("select[name='resourceFlag']").val("");
                        $scope.reourceFilePath="";
                        $("textarea[name='description']").val("");
                        $("#resource").dataTable().api().ajax.reload();
                    } else {
                        $.jBox.tip("操作不成功！");
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
            })
        }
    }



    //    显示隐藏列表
    $scope.treeshow = function ($event) {
        $scope.directoryIds = null;
        $($event.currentTarget).addClass('active').siblings().removeClass('active');
        $("#resource").dataTable().api().ajax.reload();
    }
    //    点击资源目录
    $scope.treeMainLi = function (obj, $event) {
        $($event.currentTarget).addClass('active').siblings().removeClass('active');
        $scope.tableId = obj;
        $("#resource").dataTable().api().ajax.reload();
    }

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
        var publishFields = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            publishFields = publishFields + $(this).parent().parent().children().eq(0).children().eq(0).val() + ",";
        })
        $scope.publishFields=publishFields;
    }
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#resource input[type='checkbox']:not(:disabled)").prop("checked", true);
            $("#resource tbody tr").addClass("selected");
        } else {
            $("#resource input[type='checkbox']").prop("checked", false);
            $("#resource tbody tr").removeClass("selected");
        }
        var publishFields = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            publishFields = publishFields + $(this).parent().parent().children().eq(0).children().eq(0).val() + ",";
        })
        $scope.publishFields=publishFields;
        var conditionFields="";
        $("input[name='addCondition']:checkbox:checked ").each(function () {
            conditionFields = conditionFields + $(this).parent().parent().children().eq(0).children().eq(0).val() + ",";
        })
        $scope.conditionFields=conditionFields;
    });


    //添加查询条件
    window.addCondition = function (data) {
        var conditionFields = "";
        $("input[name='addCondition']:checkbox:checked ").each(function () {
            conditionFields = conditionFields + $(this).parent().parent().children().eq(5).children().eq(0).val() + ",";
        })
        $scope.conditionFields=conditionFields;
    }
})
