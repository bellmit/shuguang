var myapp = angular.module("myapp", []);
myapp.controller("resource_export_fields", function ($scope, $http, $state) {
    /*加载外部的js*/
    var testScript = document.createElement('script');
    testScript.src = 'assets/js/dgap/common.js';
    testScript.type = 'text/javascript';

    //window.localStorage.setItem("resourceId",resourceId);
    $scope.resourceId = window.localStorage.getItem("resourceId");
    loadGrid();

    //bootstrap表格
    //资源发布主体信息
    function loadGrid() {
        if($scope.resourceId==null||$scope.resourceId==""||$scope.resourceId==undefined){
            $scope.resourceId="null"
        }
        $("#fields").dataTable({
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
                url: "/sofn-dgap-web/resourcePublish/getPublishedFiledsByResourceId",
                dataSrc: function (json) {
                    return json.data;
                },
                data: function (params) {
                    params.token=window.localStorage.getItem("token");
                    params.resourceId=$scope.resourceId
                },
                type: "post"
            },
            columns: [{
                data: "fieldCnName",
                title: "中文字段名",
                width: "8%",
            }, {
                data: "fieldName",
                title: "英文字段名",
                width: "10%",
            }, {
                data: "type",
                title: "字段类型",
                width: "10%",
            }, {
                data: "length",
                title: "字段长度",
                render: function (data) {
                    if(data!="4000"){
                        return data;
                    }else{
                        return "";
                    }

                },
                width: "10%",
            }, {
                    data: "isCondition",
                    title: "是否可作为查询条件",
                    render: function (data, type, row) {
                        if(data=="Y"){
                            return "是";
                        }else{
                            return "否";
                        }
                    },
                    width: "10%",
                }
            ]
        })
    }

 })
