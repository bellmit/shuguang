var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("comComplain",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("投诉受理");
    //获取缓存查询参数
    $scope.comComplainQueryParams = JSON.parse(window.sessionStorage.getItem("comComplainQueryParams"));
    var a="";
    var area = "";
    var jibie=""
    var beareaid="";
    $http({
        url:"/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        area = data.data.regionId;
        jibie= data.data.orgLevel;
        if(jibie==1){
            area=0
        }
        if(jibie==2){
            area=area.substr(0,2);
        }
        if(jibie==3){
            area=area.substr(0,4)
        }
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        $.fn.mycity("sheng","shi","xian",area);
        if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
            $("#sheng").attr("disabled", true);
        }
        if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
            $("#shi").attr("disabled", true);
        }
        if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
            $("#xian").attr("disabled", true);
        }
        if($scope.comComplainQueryParams){
            $("select[name='complaintTitle']").val($scope.comComplainQueryParams.complaintTitle);
            $("select[name='status']").val($scope.comComplainQueryParams.status);
            $("input[name='queryCon']").val($scope.comComplainQueryParams.queryCon);
            $("select[name='complaintTitle']").val($scope.comComplainQueryParams.complaintTitle);
            $("select[name='type']").val($scope.comComplainQueryParams.type);
            $.fn.mycity("sheng","shi","xian",$scope.comComplainQueryParams.areaId);
        }
        loadGrid();
    }).error(function (data) {
        
    });
    //点击查询按钮刷新datatable
    $("#ToCheck").click(function(){
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var comComplainQueryParams = {};
        comComplainQueryParams.complaintTitle=$("select[name='complaintTitle']").val();
        comComplainQueryParams.status=$("select[name='status']").val();
        comComplainQueryParams.queryCon=$("input[name='queryCon']").val();
        comComplainQueryParams.complaintTitle=$("select[name='complaintTitle']").val();
        comComplainQueryParams.type=$("select[name='type']").val();
        ObjShen=$("select[name='ObjShen']").val();
        ObjShi=$("select[name='ObjShi']").val();
        ObjXian=$("select[name='ObjXian']").val();
        if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
            if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                    comComplainQueryParams.areaId=ObjXian
                }else{
                    comComplainQueryParams.areaId=ObjShi
                }
            }else {
                comComplainQueryParams.areaId = ObjShen
            }
        }
        window.sessionStorage.setItem("comComplainQueryParams",JSON.stringify(comComplainQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });

    //获取投诉主题(问题类型)数据字典
    $scope.comTitle=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"12",
        method:"post"
    }).success(function(data) {
        $scope.comTitle=JSON.parse(data.data);
    }).error(function (data) {
        
    });


//分页
        //bootstrap表格
    function loadGrid() {
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-asms-web/ttsScltxxcjComplaint/getTtsScltxxcjComplaintList",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        if(json.data.list[i].COMPLAINT_TIME!=null&&json.data.list[i].COMPLAINT_TIME!=""&&json.data.list[i].COMPLAINT_TIME!=undefined){
                            json.data.list[i].COMPLAINT_TIME=new Date(parseInt(json.data.list[i].COMPLAINT_TIME)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].STATUS=="0"){
                            json.data.list[i].STATUS="未受理";
                        }
                        if(json.data.list[i].STATUS=="1"){
                            json.data.list[i].STATUS="已受理";
                        }
                        if(json.data.list[i].STATUS=="2"){
                            json.data.list[i].STATUS="待受理"
                        }
                    }
                    return json.data.list;
                },
                //传入后台的参数（点击查询 前台页面传入后台的参数）
                data : function(params) {
                    params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                    //params.entityIdcode=$("select[name='entityIdcode']").val();
                    //params.beEntityIdcode=$("select[name='beEntityIdcode']").val();

                    var comComplainQueryParams = JSON.parse(window.sessionStorage.getItem("comComplainQueryParams"));
                    if(comComplainQueryParams){
                        params.complaintTitle=comComplainQueryParams.complaintTitle;
                        params.status=comComplainQueryParams.status;
                        params.queryCon=comComplainQueryParams.queryCon;
                        params.complaintTitle=comComplainQueryParams.complaintTitle;
                        params.type=comComplainQueryParams.type;
                        params.areaId=comComplainQueryParams.areaId;
                    }else{
                        params.complaintTitle=$("select[name='complaintTitle']").val();
                        params.status=$("select[name='status']").val();
                        params.queryCon=$("input[name='queryCon']").val();
                        params.complaintTitle=$("select[name='complaintTitle']").val();
                        params.type=$("select[name='type']").val();
                        ObjShen=$("select[name='ObjShen']").val();
                        ObjShi=$("select[name='ObjShi']").val();
                        ObjXian=$("select[name='ObjXian']").val();
                        if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
                            if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                                if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                                    params.areaId=ObjXian
                                }else{
                                    params.areaId=ObjShi
                                }
                            }else {
                                params.areaId = ObjShen
                            }
                        }
                    }
                },
                type:"POST"
            },
            columns : [
                //页面展示头信息
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width : "4%"
                },
                {
                    data : "ENTI_NAME",
                    title : "投诉主体",
                    width : "15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>12){
                            return "<a id='ENTI_NAME"+row.ID+"' onmouseover=showPopContent('ENTI_NAME"+row.ID+"','"+data+"')>"+data.substring(0,12)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "BE_ENTI_NAME",
                    title : "被投诉主体",
                    width : "15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>12){
                            return "<a id='BE_ENTI_NAME"+row.ID+"' onmouseover=showPopContent('BE_ENTI_NAME"+row.ID+"','"+data+"')>"+data.substring(0,12)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "COMPLAINT_TITLE",
                    title : "投诉标题",
                    width : "10%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>7){
                            return "<a id='COMPLAINT_TITLE"+row.ID+"' onmouseover=showPopContent('COMPLAINT_TITLE"+row.ID+"','"+data+"')>"+data.substring(0,7)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "TYPE_NAME",
                    title : "问题类型",
                    width : "7%"
                },
                {
                    data : "BE_AREA_ID",
                    title : "被投诉主体区域",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(data!=null&&data!=""&&data!=undefined){
                            data =  $.fn.Cityname(row.BE_AREA_ID)
                            if(data!=null&&data!=undefined&&data.length>10){
                                return "<a id='BE_AREA_ID"+row.ID+"' onmouseover=showPopContent('BE_AREA_ID"+row.ID+"','"+data+"')>"+data.substring(0,10)+"...</a>";
                            }else{
                                return data;
                            }
                        }
                    },
                    width : "12%"
                },
                {
                    data : "COMPLAINT_TIME",
                    title : "投诉时间",
                    width : "7%"
                },
                {
                    data : "STATUS",
                    title :"当前状态",
                    width : "5%"
                },
                {
                    data : "ACC_ENTITY_IDCODE",
                    title : "受理人",
                    width : "10%",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(data!=null&&data!=""&&data!=undefined){
                            if(data!=null&&data!=undefined&&data.length>8){
                                return "<a id='ACC_ENTITY_IDCODE"+row.ID+"' onmouseover=showPopContent('ACC_ENTITY_IDCODE"+row.ID+"','"+data+"')>"+data.substring(0,8)+"...</a>";
                            }else{
                                return data;
                            }
                        }
                    },
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { // 模板化列显示内容
                        var butt = '<a type="button" data-toggle="modal" data-target="#myModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor: pointer" onclick="Checkreport(\''+row.ID+'\',\''+row.ENTI_NAME+'\',\''+row.BE_ENTI_NAME+'\',\''+row.BE_AREA_ID+'\')">查看</a>';
                        if($scope.iswrite!=2){
                            return butt;
                        }
                        if(row.STATUS=="已受理"){
                            return butt + '<a class="Update" style="padding-left: 0px;padding-right: 0px;margin-left: 5px;color: grey;text-decoration: none;" >受理</a>';//
                        }else{
                            return butt + '<a  style="padding-left: 0px;padding-right: 0px;margin-left: 5px;cursor: pointer" onclick="gotoUpdate(\''+row.ID+'\',\''+row.ENTI_NAME+'\',\''+row.BE_ENTI_NAME+'\')">受理</a>';//转义传值
                        }

                    },
                    width : "6%"
                },
            ],
        });
    }

    window.gotoUpdate=function(data,enti_name,be_enti_name){
        window.localStorage.setItem("ID",data);
        window.localStorage.setItem("enti_name",enti_name);
        window.localStorage.setItem("be_enti_name",be_enti_name);
        $state.go("index.content.asms/complain/comHandle")
    }


    //获取缓存中的数据（定位标识）
    var huqucomplainEnterpriseID = "";
    var huoqucheck_be_enti_name ="";
    var huoqucheck_enti_name = "";
    var huoqucheck_be_area_id="";

    //查看详情页面
    var huoquBiaoshiComplain = "";
    huoquBiaoshiComplain = window.localStorage.getItem("BiaoshiComplain");
    if(huoquBiaoshiComplain!=null){
        $("#myModal").modal("show");
        huqucomplainEnterpriseID=window.localStorage.getItem("complainEnterpriseID");
        huoqucheck_be_enti_name=window.localStorage.getItem("check_be_enti_name");
        huoqucheck_enti_name=window.localStorage.getItem("check_enti_name");
        huoqucheck_be_area_id=window.localStorage.getItem("check_be_area_id");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/ttsScltxxcjComplaint/getTtsScltxxcjComplaintById",
            method: "post",
            data: {id: huqucomplainEnterpriseID}
        }).success(function (data) {
            $scope.reCord = data.ttsScltxxcjComplaint;
            $scope.reCord.id=data.ttsScltxxcjComplaint.id;
            $scope.reCord.enti_name=huoqucheck_enti_name;
            $scope.reCord.be_enti_name=huoqucheck_be_enti_name;
            if($scope.reCord.complaintTime!=null&&$scope.reCord.complaintTime!=""&&$scope.reCord.complaintTime!=undefined) {
                $scope.reCord.complaintTime = new Date(parseInt($scope.reCord.complaintTime)+28800000).toISOString().slice(0, 10);}
            $scope.beAreaId= $.fn.Cityname(huoqucheck_be_area_id);
            if($scope.reCord.status=="0"){
                $scope.reCord.status="未受理";
            }
            if($scope.reCord.status=="1"){
                $scope.reCord.status="已受理";
            }
            if($scope.reCord.status=="2"){
                $scope.reCord.status="待受理"
            }
        }).error(function (data) {
            
        });

    }
    window.Checkreport=function(data,enti_name,be_enti_name,be_area_id) {
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/ttsScltxxcjComplaint/getTtsScltxxcjComplaintById",
            method: "post",
            data: {id: data}
        }).success(function (data) {
            $scope.reCord = data.ttsScltxxcjComplaint;
            $scope.reCord.id=data.ttsScltxxcjComplaint.id;
            $scope.reCord.enti_name=enti_name;
            $scope.reCord.be_enti_name=be_enti_name;
            $scope.reCord.be_area_id = be_area_id;
            if($scope.reCord.complaintTime!=null&&$scope.reCord.complaintTime!=""&&$scope.reCord.complaintTime!=undefined) {
                $scope.reCord.complaintTime = new Date(parseInt($scope.reCord.complaintTime)+28800000).toISOString().slice(0, 10);}
            $scope.beAreaId= $.fn.Cityname(be_area_id);
            if($scope.reCord.status=="0"){
                $scope.reCord.status="未受理";
            }
            if($scope.reCord.status=="1"){
                $scope.reCord.status="已受理";
            }
            if($scope.reCord.status=="2"){
                $scope.reCord.status="待受理"
            }
        }).error(function (data) {
            
        });
    }
    $scope.cleanSelectModelQuery = function(){
        //点击间隔
        $scope.btnReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnReloadDisabled = false);
        }, 500);
        $("input[name='queryCon']").val("");
        $("select[name='status']").val("");
        $("select[name='type']").val("");
        $.fn.mycity("sheng","shi","xian",area);
        $("#grid").dataTable().api().ajax.reload();
    };
    window.selectEnterprideInfo=function(){
        $("#myModal").modal("hide");
        //模态框关闭触发改为延时执行,IE9兼容性
        setTimeout(function () {
            window.localStorage.setItem("entityIdcode", $scope.reCord.entityIdcode);
            window.localStorage.setItem("comComplainEnterUrl", "index.content.asms/complain/comComplain");
            $state.go("index.content.asms/complain/complainEnterpriseview");
        },300)
    }
    window.selectEnterprideInfoBe=function(){
        $("#myModal").modal("hide");
        //模态框关闭触发改为延时执行,IE9兼容性
        setTimeout(function () {
            window.localStorage.setItem("entityIdcode",$scope.reCord.beEntityIdcode);
            window.localStorage.setItem("comComplainEnterUrl","index.content.asms/complain/comComplain");
            $state.go("index.content.asms/complain/complainEnterpriseview");
        },300)
    }
    window.qinchuhuanchun=function(){
        window.localStorage.removeItem("BiaoshiComplain");
        window.localStorage.removeItem("entityIdcode");
        window.localStorage.removeItem("beEntityIdcode");
        window.localStorage.removeItem("complainEnterpriseID");
        window.localStorage.removeItem("check_enti_name");
        window.localStorage.removeItem("check_be_enti_name");
        window.localStorage.removeItem("check_be_area_id");
        $("#myModal").modal("hide");

    }
    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        if($scope.reCord.attachmentPath!=null&&$scope.reCord.attachmentPath!=undefined&&$scope.reCord.attachmentPath.indexOf("//202.127.45.189")>=0){
            form.attr('action', "http://202.127.45.186/sofn-sys-web/sysTemplate/dowload");
        }else{
            form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        }
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.reCord.attachmentPath);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.reCord.attachmentName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
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

