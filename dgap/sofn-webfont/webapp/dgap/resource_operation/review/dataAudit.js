var myapp = angular.module("myapp", []);
myapp.controller("dataAudit", function ($scope, $http, $state) {

    $scope.resourceId = window.localStorage.getItem("resourceId");


    //重新加载表格
    $scope.resourceDirs = function () {
        loadDataGrid();
    };
    /* 清除查询框内容*/
    $scope.cleanData = function () {
        $("select[name='resourceTypeSelct']").val("");
        loadDataGrid();
    };
    loadDataGrid();
    //模态框显示
    //bootstrap表格
    var columnsJson=[];
    var list ={};
    function loadDataGrid() {
        $.ajax({
            type: 'POST',
            url: "sofn-dgap-web/dataImport/getcolumns",
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: {
                resourceId : $scope.resourceId,
            },
            success: function(d) {
                $scope.fields=d.data.columns;
                if(columnsJson.length>0){//清空数组
                    columnsJson=[];
                }
                list={
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        var unEnable = row.DATAID;
                        return '<input onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" value="' + row.
                            ID +'" type="checkbox"  name="ck"  />';
                    },
                    width: "5%"
                }
                columnsJson.push(list);
                for(var i=0;i<$scope.fields.length;i++){//制作表头
                    var filedType=$scope.fields[i].type;
                    if(filedType=="time"||filedType=="timestamp"){
                        list ={
                            data: $scope.fields[i].englishName,
                            title: $scope.fields[i].chineseName,
                            render: function (data, type, row) {
                                return formatDate(data)
                            },
                            width : "200px",
                        };
                    } else if(filedType=="varchar"||filedType=="varchar2"){
                        list ={
                            data: $scope.fields[i].englishName,
                            title: $scope.fields[i].chineseName,
                            width : "10%",
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
                    }else if(filedType=="BLOB"){
                        list ={
                            data: $scope.fields[i].englishName,
                            title: $scope.fields[i].chineseName,
                            width : "10%",
                            render: function(data) {
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
                            data: $scope.fields[i].englishName,
                            title: $scope.fields[i].chineseName,
                        }
                    }

                    columnsJson.push(list);
                }
                list={
                    data: "STATUS",
                    title: "状态",
                    render: function (data){
                        if(data=="Y"){
                           return "人工审核通过"
                        }else if(data=="N"){
                            return "人工审核不通过"
                        }else if(data=="U"){
                            return "未审核"
                        }else {
                            return "自动导入"
                        }
                    }
                };
                columnsJson.push(list);
                list={
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        var status =row.STATUS;
                        if(status=="U"){
                            return  '<a type="button"  style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="checkDetails(\'' + data + '\')">查看详情</a> &nbsp; ' +
                            '<a type="button"  style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="auditYes(\'' + data + '\')">通过</a> &nbsp; ' +
                                '<a type="button"  style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="auditNo(\'' + data + '\')">不通过</a>' ;//转义传值
                        }else{
                            return  '<a type="button"  style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="checkDetails(\'' + data + '\')">查看详情</a> &nbsp; '
                        }
                    }
                    };
                columnsJson.push(list);
                if ($('#reDir').hasClass('dataTable')) {
                    var oldTable = $('#reDir').dataTable({

                    });
                    oldTable.fnClearTable(false); //清空一下table
                    oldTable.fnDestroy(); //还原初始化了的dataTable
                    $('#reDir').empty();
                }
                $('#reDir').DataTable({
                    "scrollX":true,
                    ajax: {
                        url: "/sofn-dgap-web/dataImport/dataLists",
                        dataSrc: function (json) {
                            json.recordsTotal = json.data.dataSource.total; //总个数
                            json.recordsFiltered = json.data.dataSource.total;
                            json.pageNumber = json.data.dataSource.pageNum; //页码
                            json.pageSize = json.data.dataSource.pageSize; //每页个数
                            json.start = (json.data.dataSource.pageNum - 1) * json.data.dataSource.pageSize; //起始位置
                            json.length = json.data.dataSource.pageSize;
                            return json.data.dataSource.list;
                        },
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        data: function (params) {
                            params.resourceId = $scope.resourceId;
                            params.status=$("select[name='resourceTypeSelct']").val();
                            params.condition="";
                        },
                        type: "post"
                    },
                    dom: "Bfrtip",
                    columns:columnsJson
                });
            }
        });
    }
    //审核通过
    window.auditYes = function (data) {
        $scope.dataId = data;
        $http({
            url: "/sofn-dgap-web/dataImport/checkDataY",
            data: {
                dataId: $scope.dataId,
                resourceId:$scope.resourceId,
                status:"Y"
            },
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if(data.httpCode==200){
                loadDataGrid();
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！！！");
        });
    };
    //审核不通过
    window.auditNo = function (data) {
        $scope.dataId = data;
        $http({
            url: "/sofn-dgap-web/dataImport/checkDataN",
            data: {
                dataId: $scope.dataId,
                resourceId:$scope.resourceId,
                status:"N"
            },
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if(data.httpCode==200){
                loadDataGrid();
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！！！");
        });
    };
    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#reDir input[type='checkbox']:not(:disabled)").prop("checked", true);
            $("#reDir tbody tr").addClass("selected");
        } else {
            $("#reDir input[type='checkbox']").prop("checked", false);
            $("#reDir tbody tr").removeClass("selected");
        }
    });
    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }

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
    $scope.updateData=function () {
        $("#auditModal").modal("show");
    }
    //批量审核通过
    $scope.passAudit=function () {
        var delIds = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            delIds = delIds + $(this).parent().parent().children().eq(0).children().eq(0).val() + ",";
        });
        $http({
            url: "/sofn-dgap-web/dataImport/checkDataY",
            data: {
                dataId: delIds,
                resourceId:$scope.resourceId,
                status:"Y"
            },
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if(data.httpCode==200){
                loadDataGrid();
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！！！");
        });
    }
    //批量审核不通过
    $scope.noPassAudit=function () {
        var delIds = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            delIds +=  $(this).parent().parent().children().eq(0).children().eq(0).val() + ",";
        });
        $http({
            url: "/sofn-dgap-web/dataImport/checkDataN",
            data: {
                dataId: delIds,
                resourceId:$scope.resourceId,
                status:"N"
            },
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            dataSrc: function (json) {
                return json.data.dataSource.list;
            },
        }).success(function (data) {
            if(data.httpCode==200){
                loadDataGrid();
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！！！");
        });
    }


    window.checkDetails = function (data) {
        $("#checkDiv").modal("show");
        if ($('#checkTable').hasClass('dataTable')) {
            var checkTable = $('#checkTable').dataTable({

            });
            checkTable.fnClearTable(false); //清空一下table
            checkTable.fnDestroy(); //还原初始化了的dataTable
            $('#checkTable').empty();
        }
        $("#checkTable").dataTable({
            "paging": false,
            "info": false,
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-dgap-web/dataImport/getDataById",
                data: {
                    id: data,
                    resourceId:$scope.resourceId,
                },
                type: "post",
                dataSrc: function (data) {
                    return data.data.fieldsData;
                },
            },
            columns: [{
                data: "columnName",
                title: "字段",
                width : "45%",
            }, {
                data: "value",
                title: "值",
                width : "55%",
                render: function(data) {
                    if (data) {
                        data = data.substring(0, data.length);
                        var showdata = data;
                        if (data.length > 30) {
                            showdata = data.substring(0, 30) + "..";
                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        }
                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                    } else {
                        return '--';
                    }
                } ,
            }]
        });
    }
});
