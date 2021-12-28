var myapp=angular.module("myapp",[]);
myapp.controller("entityCheckreport",function($scope,$http){
    $scope.ID=window.localStorage.getItem("entityIdCode");
    $scope.enterprise={};
    $scope.enterprise.id={};
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/administrativePenalty/findEnterpriseById",
            method:"post",
            //data:$scope.ID
            params:{
                'entityIdCode':$scope.ID
            }
        }).success(function(dataEnterprise){
            $scope.enterprise=dataEnterprise.enterprise;
            if($scope.enterprise.area!=null&&$scope.enterprise.area!=""&&$scope.enterprise.area!=undefined){
                $scope.area=$.fn.Cityname($scope.enterprise.area);
            }
            $("#badRecordGrid").dataTable().api().ajax.reload();
        }).error(function(data){
            console.log(data);
            
         });
    $("#badRecordGrid").dataTable({
        ajax : {
            url : "/sofn-ales-web/administrativePenalty/getAsmsSubjEntBadrecordByIdList",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc:function(json){
                console.log(json);
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                for(var i=0;i<json.list.length;i++){
                    if(json.list[i].createTime!=null&&json.list[i].createTime!=""&&json.list[i].createTime!=undefined){
                        json.list[i].createTime=new Date(parseInt(json.list[i].createTime)+28800000).toISOString().slice(0,10);
                    }
                }
                return json.list;
            },
            error:function (msg) {
               console.log(msg);
                
            },
            headers:{
              token:window.localStorage.getItem("token")
            },
            //传入后台的参数（点击查询 前台页面传入后台的参数）
            data : function(params) {
                params.enterpriseId=$scope.enterprise.id;
            },
            type:"POST"
        },
        columns : [
            //页面展示头信息
            {
                data : "createTime",
                title : "时间"
            },
            {
                data : "badrecordContent",
                title : "不良记录描述"
            },
            {
                data : "badrecordFile",
                title : "附件"
            },
        ],
    });
});

