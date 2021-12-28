var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("qryProduceAdminPunishlist",function($scope,$http,$state){
    var area = ""
    var jibie= ""
    $http({
        url:"/sofn-qry-web/subject/findCurrentUserByToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        area = data.user.regionId;
        jibie= data.user.orgLevel;
        if(jibie==1){
            area=0
        }
        if(jibie==2){
            area=area.substr(0,2);
        }
        if(jibie==3){
            area=area.substr(0.4)
        }
        if(jibie==4){
            area=area.substring(0,6)
        }
        $.fn.mycity("sheng","shi","xian",area);
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        if($("#sheng").val()!=null&&$("#sheng").val()!=""&&$("#sheng").val()!=undefined){
            $("#sheng").attr("disabled",true);
        }
        if($("#shi").val()!=null&&$("#shi").val()!=""&&$("#shi").val()!=undefined){
            $("#shi").attr("disabled",true);
        }
        if($("#xian").val()!=null&&$("#xian").val()!=""&&$("#xian").val()!=undefined){
            $("#xian").attr("disabled",true);
        }
        //所属行业默认不限、组织形式不限、主体类别不限
        $("input[name='entityIndustryLimit']:first").attr("checked",true);
        // $("input[name='entityScaleLimit']:first").attr("checked",true);
        $("input[name='entityTypeLimit']:first").attr("checked",true);
        loadGrid();
    }).error(function(){

    });
    $scope.cleanSelectModelQuery = function(){
        $("input[name='taskYear']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='queryCon']").val("");
        $("input[type='checkbox']:checked").attr("checked",false);
        $("input[type='checkbox']").attr("disabled",false);
        $.fn.mycity("sheng","shi","xian",area);
        $("#grid").dataTable().api().ajax.reload();
    };
    //省市县的三级联动
    //$.fn.mycity("sheng","shi","xian",510722)
    //点击查询按钮刷新datatable
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
//分页

    //获取行业数据字典
    $scope.industryType=[];
    $http({
        url:"/sofn-qry-web/sysDict/getSysDictByCode",
        data:2,
        method:"post"
    }).success(function(data) {
        $scope.industryType=JSON.parse(data.data);
    })

    //获取主体类型数据字典
    $scope.entityType=[];
    $http({
        url:"/sofn-qry-web/sysDict/getSysDictByCode",
        data:31,
        method:"post"
    }).success(function(data) {
        $scope.entityType=JSON.parse(data.data);
    })
        //bootstrap表格
    function loadGrid() { $("#grid").dataTable({
        ajax : {
            url : "/sofn-qry-web/administrativePenalty/getAdministrativePenaltyList",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc:function(json){
                json.recordsTotal=json.data.total;//总个数
                json.recordsFiltered=json.data.total;
                json.pageNumber=json.data.pageNum;//页码
                json.pageSize=json.data.pageSize;//每页个数
                json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                json.length=json.data.pageSize;
                console.log(json.data.list)
                for(var i=0;i<json.data.list.length;i++){
                    if(json.data.list[i].ENFORCE_LAW_TIME!=null&&json.data.list[i].ENFORCE_LAW_TIME!=""&&json.data.list[i].ENFORCE_LAW_TIME!=undefined){
                        json.data.list[i].ENFORCE_LAW_TIME=new Date(parseInt(json.data.list[i].ENFORCE_LAW_TIME)+28800000).toISOString().slice(0,10);
                    }
                }
                return json.data.list;
            },error:function (msg) {
                console.log(msg);
                
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                //所属行业
                var entityIndustry = $("input[name='entityIndustry']:checked");
                var arr01 = new Array();
                for(var i=0;i<entityIndustry.length;i++){
                    arr01.push($(entityIndustry[i]).val());
                }
                params.entityIndustry = arr01.toString();

                //主体类型
                var entityType = $("input[name='entityType']:checked");
                var arr02 = new Array();
                for(var i=0;i<entityType.length;i++){
                    arr02.push($(entityType[i]).val());
                }
                params.entityType = arr02.toString();
                params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                params.taskYear=$("input[name='taskYear']").val();
                params.queryCon=$("input[name='queryCon']").val();
                params.dateBegin=$("input[name='datebegin']").val();
                params.dateEnd=$("input[name='dateend']").val();
                ObjShen=$("select[name='ObjShen']").val();
                ObjShi=$("select[name='ObjShi']").val();
                ObjXian=$("select[name='ObjXian']").val();
                if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
                    if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                        if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                            params.area=ObjXian
                        }else{
                            params.area=ObjShi
                        }
                    }else {
                        params.area = ObjShen
                    }
                }
            },
            type:"post",
        },
        columns : [
            {
                //data : "RN",
                title : "序号",
                width : "4%",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                },
            },
            {
                data : "ENFORCE_LAW_TIME",
                title : "年度",
                width : "4%",
                render : function(data, type, row) { // 模板化列显示内容
                    return data.substring(0,4);
                },
            },
            {
                data : "CASE_NAME",
                title : "案件名称",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>18){
                        return "<a id='CASE_NAME"+row.ID+"' onmouseover=showPopContent('CASE_NAME"+row.ID+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "PUNISH_CODE",
                title : "行政处罚决定案号",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>25){
                        return "<a id='PUNISH_CODE"+row.ID+"' onmouseover=showPopContent('PUNISH_CODE"+row.ID+"','"+data+"')>"+data.substring(0,25)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "ENTERPRISE_NAME",
                title : "当事主体",
                width : "25%"
            },
            {
                data : "ENTITY_INDUSTRY_NAME",
                title : "所属行业"
            },
            {
                data : "ENTITY_TYPE_NAME",
                title : "主体类型"
            },
            {
                data : "AREA",
                title : "区域",
                width : "12%",
                render : function(data, type, row) { // 模板化列显示内容
                    if(data!=null&&data!=""&&data!=undefined){
                        return $.fn.Cityname(row.AREA);
                    }
                }
            },
            {
                data : "ENFORCE_LAW_TIME",
                title : "执法时间",
                width : "7%"
            },
            {
                data : "ID",
                title : "操作",
                width : "4%",
                render : function(data, type, row) { // 模板化列显示内容
                    return '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\''+data+'\')">查看</a>';//转义传值
                },
            },
        ],
    });}

//查看详情页面
        window.Checkreport=function(data) {
            $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/administrativePenalty/getPunishiById",
                method: "post",
                params: {id: data}
            }).success(function (data) {
                $scope.reCord = data.produceAdminPunish;
            }).
                error(function (data) {
                console.log(data.httpCode);
                
                });
        }
 /*   window.produceAdminPunishPrint=function(){
        radioID=$("input[type='radio']:checked").val()
        window.localStorage.setItem("radioID",radioID)
        $state.go("index.content.ales/punish/produceAdminPunishPrint");
    }*/

    //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
    $scope.limitCheckBox = function(checkBoxName,limitCheckBoxName){
        for(var i =0;i<$("input[name='" + limitCheckBoxName + "']").length;i++) {
            var lim = $("input[name='" + limitCheckBoxName + "']")[i].checked;
            if (lim) {
                $("input[name='" + checkBoxName + "']:checked").attr("checked", false);
            }
        }
    }

    //数据导出
    $scope.exportData=function(){
        //所属行业
        var entityIndustry = $("input[name='entityIndustry']:checked");
        var arr01 = new Array();
        for(var i=0;i<entityIndustry.length;i++){
            arr01.push($(entityIndustry[i]).val());
        }


        //主体类型
        var entityType = $("input[name='entityType']:checked");
        var arr02 = new Array();
        for(var i=0;i<entityType.length;i++){
            arr02.push($(entityType[i]).val());
        }
        var entityIndustry = arr01.toString();
        var entityType = arr02.toString();
        var taskYear=$("input[name='taskYear']").val();
        var queryCon=$("input[name='queryCon']").val();
        var dateBegin=$("input[name='datebegin']").val();
        var dateEnd=$("input[name='dateend']").val();
        ObjShen=$("select[name='ObjShen']").val();
        ObjShi=$("select[name='ObjShi']").val();
        ObjXian=$("select[name='ObjXian']").val();
        var area="";
        if(ObjShen!=""&& ObjShen!=null&&ObjShen!=undefined){
            if(ObjShi!=""&& ObjShi!=null&&ObjShi!=undefined) {
                if(ObjXian!=""&& ObjXian!=null&&ObjXian!=undefined){
                    var area=ObjXian
                }else{
                    var area=ObjShi
                }
            }else {
                var area = ObjShen
            }
        }
        //提交数据
        var token=window.localStorage.getItem("token");
        //form
        var form=$("<form>");
        form.attr('style','display:none');
        form.attr('target','');
        form.attr('method','post');
        form.attr('action',"/sofn-qry-web/administrativePenalty/exportSubjSv?token="+token+'&entityIndustry='+entityIndustry+'&entityType='+entityType+'&taskYear='+taskYear+'&queryCon='+queryCon
            +'&dateBegin='+dateBegin +'&dateEnd='+dateEnd +'&area='+area);
        $('body').append(form);
        form.submit();
    }
    window.downloadFileName=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/qryFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.reCord.punishFiles);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.reCord.punishFilesName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

});

