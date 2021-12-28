var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("qrySubjSvView",function($scope,$http,$state){

    /** 第一部分，初始化时加载的内容 **/
    //获取本地存储的ID
    $scope.subjSvId=window.localStorage.getItem("subjSvId");
    //获取用户信息
    $scope.user = {};
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/subject/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
    }).error(function(data){
        
    })
    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/subject/findSubjSuperviseById",
        method:"post",
        data:{id:$scope.subjSvId}
    }).success(function(data){
        if(data.httpCode=="200") {
            $scope.subjSv=data.data;
            if($scope.subjSv.svLevel=="3"){
                $scope.subjSv.svLevel = "县级";
            }else if($scope.subjSv.svLevel=="2"){
                $scope.subjSv.svLevel = "市级";
            }else if($scope.subjSv.svLevel=="1"){
                $scope.subjSv.svLevel = "省级";
            }else if($scope.subjSv.svLevel=="0"){
                $scope.subjSv.svLevel = "部级";
            }
            $scope.subjSv.createTime=new Date(parseInt($scope.subjSv.createTime)+28800000).toISOString().slice(0,10);
            $scope.subjSv.svArea = $.fn.Cityname($scope.subjSv.svAreaId);
            //空字段默认为"无"
            $scope.checkData($scope.subjSv);
        }else{
            jBox.tip(data.msg, 'info');
        }
    }).error(function(data){
        
    })

    /** 第二部分，主动调用才执行的部分 **/

    //机构变更列表
    $("#grid").dataTable({
        ajax : {
            url : "/sofn-qry-web/subject/getChangeListBySvId",
            dataSrc:function(json){
                console.log(json);
                if (json && json.httpCode != 200) {
                    jBox.tip(json.msg);
                    return;
                }
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                for(var i=0;i<json.list.length;i++){
                    if(json.list[i].APPLY_TIME!=null&&json.list[i].APPLY_TIME!=""&&json.list[i].APPLY_TIME!=undefined){
                        json.list[i].APPLY_TIME=new Date(parseInt(json.list[i].APPLY_TIME)+28800000).toISOString().slice(0,10);
                    }
                }
                return json.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                params.token=window.localStorage.getItem("token");
                params.id=$scope.subjSvId;
            },
            type:"post"
        },
        columns : [
            {
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "CHANGE_CONTENT",
                title : "变更内容",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>8){
                        return "<a id='"+row.ID+"' onmouseover=showPopContent('"+row.ID+"','"+data+"')>"+data.substring(0,8)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "APPLY_REASON",
                title : "变更原因"
            },
            {
                data : "APPLY_TIME",
                title : "申请时间"
            },
            {
                data : "AUDIT_STATE",
                title : "审核状态",
                render:function(data,type,row){
                    if(data!=null&&data!=""&&data!=undefined){
                        if(data=="2"){
                            return "未通过"
                        }else if(data=="1"){
                            return "已通过";
                        }else if(data=="0"){
                            return "待审核";
                        }
                    }else{
                        return "待审核";
                    }
                }
            },
            {
                data : "ID",
                title : "操作",
                render : function(data, type, row) { // 模板化列显示内容
                    return '<a style="cursor: pointer" class="Check-report color"  onclick="viewSubjSvChange(\''+data+'\')">查看</a>';//转义传值
                }
            }
        ]
    });

    //查看详情页面
    window.viewSubjSvChange=function(id){
        $("#changeModal").modal("show");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/subject/findSubjSvChangeById",
            method:"post",
            data:{id:id}
        }).success(function(data){
            //得到变更前信息
            $scope.before=data.subjSv;
            //得到变更后信息
            $scope.after=data.subjSvChange;//得到变更后信息
            //对几个非必填项进行null处理
            if($scope.after.svLeaderPhone==null||$scope.after.svLeaderPhone==undefined){
                $scope.after.svLeaderPhone = "";
            }
            if($scope.after.svContactQQ==null||$scope.after.svContactQQ==undefined){
                $scope.after.svContactQQ = "";
            }
            if($scope.after.svContactEmail==null||$scope.after.svContactEmail==undefined){
                $scope.after.svContactEmail = "";
            }
            if($scope.after.svPostcode==null||$scope.after.svPostcode==undefined){
                $scope.after.svPostcode = "";
            }
            if($scope.before.svLevel=="0"){
                $scope.before.svLevel="部级";
            }else if($scope.before.svLevel=="1"){
                $scope.before.svLevel="省级";
            }else if($scope.before.svLevel=="2"){
                $scope.before.svLevel="市级";
            }else if($scope.before.svLevel=="3"){
                $scope.before.svLevel="县级";
            }
            if($scope.after.svLevel=="0"){
                $scope.after.svLevel="部级";
            }else if($scope.after.svLevel=="1"){
                $scope.after.svLevel="省级";
            }else if($scope.after.svLevel=="2"){
                $scope.after.svLevel="市级";
            }else if($scope.after.svLevel=="3"){
                $scope.after.svLevel="县级";
            }
            var state = $scope.after.auditState;
            if (state) {
                if (state == "2") {
                    state = "未通过"
                } else if (state == "1") {
                    state = "已通过";
                } else if (state == "0") {
                    state = "待审核";
                }
            }else {
                state = "待审核";
            }
            $scope.after.auditState = state;
            $scope.checkData($scope.before);
            $scope.checkData($scope.after);
        }).error(function(data){
            
        })
    }

    //返回列表页面
    $scope.callBack = function(){
        window.localStorage.removeItem("subjSvId");
        $state.go("index.content.qry/subject/qrySubjSvList");
    }
    //字段值为空显示为无
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }

    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
        $("#"+id).popover({
            animation:true,
            html:false,
            placement:'right',
            trigger:'hover',
            content:content,
            delay:0,
            container:'body'
        });
        $("#"+id).popover("show");
    }
});

