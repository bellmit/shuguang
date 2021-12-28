var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("produceAdminPunishlist",function($scope,$http,$state){
    $scope.authorityArray = window.localStorage.getItem("menus");
    var authorityArray = JSON.parse($scope.authorityArray);
     var author = "";
    for(var i =0; i < authorityArray.length;i++){
        if(authorityArray[i].id == "516"){
            if(authorityArray[i].authority == "1"){
                author = authorityArray[i].authority;
                $("#newId").attr("disabled", true);
                $("#produceId").attr("disabled", true);
                $("#delId").attr("disabled", true);
                break;
            }
        }
    }
    /**
     * 默认取用户所属区域,设置区域省市悬三级联动
     * 注释日期:2018年2月26日10:17:55
     */
    var area = ""
    var jibie= ""
    var orgId = ""
    $http({
        url:"/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method:"post",
        dataType: "json",
        headers:{
            token:window.localStorage.getItem("token")
        }
    }).success(function(data){
        orgId = data.data.orgId;
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
        loadGrid();
    }).error(function(msg){
        console.log(msg);
        
    });
    /**
     * 获取当前登录用户Id
     * 注释日期:2018年2月26日10:17:32
     */
    $scope.linePoliticsUserId='';
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/llesEntrustDetection/getCreateBy",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.linePoliticsUserId =data.data;
    }).error(function (data) {
        
    });
    /**
     * 重置查询参数,默认显示用户所属区域内行政处罚信息
     * 注释日期:2018年2月26日10:17:17
     */
    $scope.cleanSelectModelQuery = function(){
        $("input[name='taskYear']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='queryCon']").val("");
        $.fn.mycity("sheng","shi","xian",area);
        $("#grid").dataTable().api().ajax.reload();
    };
    /**
     * 点击查询按钮刷新datatable
     * 注释日期:2018年2月26日10:17:00
     */
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 默认查询绑定回车事件。
     * 注释日期:2018年2月26日10:16:45
     */
    $('#queryCon').keydown(function(e){
        if(e.keyCode==13){
            $("#grid").dataTable().api().ajax.reload();
        }
    });
    /**
     * 行政处罚信息分页呈现,采用bootstrap表格。
     * 注释日期:2018年2月26日10:16:33
     */
    function loadGrid() {
        $("#grid").dataTable({
        fnDrawCallback: function() {
            //点击行内任意内容即选择行
            $("#grid tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[type='radio']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                        //移除
                        var u_d = $(this).parent().find("[name='ck']").val();
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                    }
                });
            })
        },
        ajax : {
            url : "/sofn-ales-web/administrativePenalty/getAdministrativePenaltyList",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc:function(json){
                json.recordsTotal=json.data.total;//总个数
                json.recordsFiltered=json.data.total;
                json.pageNumber=json.data.pageNum;//页码
                json.pageSize=json.data.pageSize;//每页个数
                json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                json.length=json.data.pageSize;
                //console.log(json.data.list)
                for(var i=0;i<json.data.list.length;i++){
                    if(json.data.list[i].ENFORCE_LAW_TIME!=null&&json.data.list[i].ENFORCE_LAW_TIME!=""&&json.data.list[i].ENFORCE_LAW_TIME!=undefined){
                        json.data.list[i].ENFORCE_LAW_TIME=new Date(parseInt(json.data.list[i].ENFORCE_LAW_TIME)+28800000).toISOString().slice(0,10);
                    }
                    if(json.data.list[i].CREATE_TIME!=null&&json.data.list[i].CREATE_TIME!=""&&json.data.list[i].CREATE_TIME!=undefined){
                        json.data.list[i].CREATE_TIME=new Date(parseInt(json.data.list[i].CREATE_TIME)+28800000).toISOString().slice(0,10);                    
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
                params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                params.taskYear=$("input[name='taskYear']").val();
                params.queryCon=$("input[name='queryCon']").val();
                params.dateBegin=$("input[name='datebegin']").val();
                params.dateEnd=$("input[name='dateend']").val();
                params.createOrgId=orgId;
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
            //选择按钮
            {
                data : "ck",
                title : '选择',
                render : function(data, type, row) { // 模板化列显示内容
                    return '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="' + row.ID + '" onclick="checkClick(this)" />';
                },
                width: "3%",
            },
            {
                //data : "RN",
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                },
                width: "5%",
            },
            {
                data : "ENFORCE_LAW_TIME",
                title : "年度",
                render : function(data, type, row) { // 模板化列显示内容
                    return data.substring(0,4);
                },
                width: "7%",
            },
            {
                data : "CASE_NAME",
                title : "案件名称",
                render: function(data, type, row, meta) {
                    if (data) {
                        var showdata = data;
                        if (data.length > 10) {
                            showdata = data.substring(0, 10) + "..";
                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        }
                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                    } else {
                        return '--';
                    }
                },
                width: "10%",
            },
            {
                data : "PUNISH_CODE",
                title : "行政处罚决定案号",
                render: function(data, type, row, meta) {
                    if (data) {
                        var showdata = data;
                        if (data.length > 10) {
                            showdata = data.substring(0, 10) + "..";
                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        }
                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                    } else {
                        return '--';
                    }
                },
                width: "10%",
            },
            {
                data : "NAME_ENTERPRISE",
                title : "当事主体",
                render: function(data, type, row, meta) {
                    if (data) {
                        var showdata = data;
                        if (data.length > 10) {
                            showdata = data.substring(0, 10) + "..";
                            return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        }
                        return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                    } else {
                        return '--';
                    }
                },
                width: "10%",
            },
            {
                data : "AREA",
                title : "区域",
                render : function(data, type, row) { // 模板化列显示内容
                    if(data!=null&&data!=""&&data!=undefined){
                        var region = $.fn.Cityname(row.AREA);
                        var regions;
                        if (region) {
                            regions = region.substring(0, data.length);
                            var showdata = regions;
                            if (region.length > 10) {
                                showdata = region.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + region + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                       /* return $.fn.Cityname(row.AREA);*/
                    }
                },
                width: "10%",
            },
            {
                data : "ENFORCE_LAW_TIME",
                title : "执法时间",
                width: "10%",
            },
            {
                data : "CREATE_TIME",
                title : "创建时间",
                width: "10%",
            },
            {
                data:"CREATE_BY",
                title:"",
                visible: false
            },
            {
                data : "ID",
                title : "操作",
                render : function(data, type, row) { // 模板化列显示内容
                    if(row.CREATE_BY == $scope.linePoliticsUserId && author != "1"){
                    return '<a type="button" style="cursor:pointer;" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\''+data+'\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" onclick="GotoUpdateWindow(\''+data+'\')">修改</a>';//转义传值
                    }else {
                        return '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\''+data+'\')">查看</a> <a style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer;" '//转义传值
                    }
                },
                width: "10%",
            },
        ],
    });}
    /**
     * data加入缓存。
     * 注释日期:2018年2月26日10:16:11
     */
    window.GotoUpdateWindow = function(data){
        window.localStorage.setItem("id",data);
        window.localStorage.setItem("ales_punish_updateFlag","1");
        $state.go("index.content.ales/punish/produceAdminPunishAdd");
    }
    /**
     * 查看详情页面
     * 注释日期:2018年2月26日10:47:55
     */
    $scope.punishEnterpriseId ="";
    window.Checkreport=function(data) {
        console.log("传入数据值===="+data);
        window.localStorage.setItem("punishLawID",data)
        window.localStorage.removeItem("motaikuangBiaoshi");
        $http({
            headers:{

                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/administrativePenalty/getPunishiById",
            method: "post",
            params: {id: data}
        }).success(function (data) {
            $scope.reCord = data.produceAdminPunish;
            console.log($scope.reCord);
            $scope.punishEnterpriseId =  $scope.reCord.id;
            console.log("punishEnterpriseId====>>>>"+$scope.punishEnterpriseId);
        }).error(function (data) {
            console.log(data.data.id);
            
            });
    }
    /**
     *行政处罚信息打印。
     *注释日期:2018年2月26日10:15:45
     */
    window.produceAdminPunishPrint=function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        console.log(length);
        if(length <= 0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        radioID=$("input[type='radio']:checked").val() //获取选择的对象ID
        window.localStorage.setItem("radioID",radioID)
        $state.go("index.content.ales/punish/produceAdminPunishPrint");
    }
    window.checkClick=function(ck){
        console.log("选择的ID号=："+ck);
        $($(ck).parent().parent()[0]).toggleClass('selected');
        var e = window.event||arguments.callee.caller.arguments[0];
        if (e && e.stopPropagation) {
            e.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
    }

    /**
     * 点击查询按钮刷新行政处罚列表
     * 注释日期:2018年2月26日10:29:59
     */
    $scope.queryAdminPunishList = function(){
        $("#grid").dataTable().api().ajax.reload();
    };
    
    /**
     *信息删除
     *注释日期:2018年2月26日10:34:58
     */
    $scope.del = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var k = 0; k < length; k++) {
            if(rows[k].CREATE_BY == ''){
            console.log(rows[k].create_by);
            console.log(rows[k].CREATE_BY);
            console.log($scope.linePoliticsUserId);
                $.jBox.tip("无效数据");
                return false;
            }
            if(rows[k].CREATE_BY != $scope.linePoliticsUserId){
                $.jBox.tip("没有权限删除本任务");
                console.log(rows[k].create_by);
                console.log(rows[k].CREATE_BY);
                console.log($scope.linePoliticsUserId);
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }

        if (rows != false) {
            $.jBox.confirm("是否删除已选择行政处罚？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(ids)
                    };
                    $.ajax({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url: "/sofn-ales-web/administrativePenalty/del",
                        type: "post",
                        dataType: "json",
                        data: data,
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $scope.queryAdminPunishList();
                        },
                        fail: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    }).error(function (data) {
                        console.log(data);
                        
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

    /**
     * 获取缓存中的数据（定位标识）
     * 注释日期:2018年2月26日10:37:02
     */
    var punish_ID = "";
    var huoquBiaoshiPunishDaily = "";
    huoquBiaoshiPunishDaily = window.localStorage.getItem("motaikuangBiaoshi");
    if(huoquBiaoshiPunishDaily!=null){
        $("#myModal").modal("show");
        punish_ID = window.localStorage.getItem("punishLawID");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/administrativePenalty/getPunishiById",
            method: "post",
            params: {id: punish_ID}
        }).success(function (data) {
            console.log(data)
            $scope.reCord = data.produceAdminPunish;
            console.log($scope.reCord);
            $scope.punishEnterpriseId =  $scope.reCord.id;
        }).error(function (data) {
            console.log(data);
            
            });

    }

    /**
     * 查看主体性详细信息
     * 注释日期:2018年2月26日10:37:58
     */
    window.selectEnterpriseInfo = function(){
        $('#myModal').one('hidden.bs.modal', function () {
            window.localStorage.setItem("dELPUNISH_EnterpriseId",$scope.punishEnterpriseId);
            $state.go("index.content.ales/punish/etlPunishEnterpriseview");
        });
        $("#myModal").modal("hide");
    }


    /**
     * 清除主体信息中缓存的标识
     * 注释日期:2018年2月26日10:38:43
     */
    window.qinchuhuanchun=function(){
        window.localStorage.removeItem("motaikuangBiaoshi");
        window.localStorage.removeItem("punishLawID");
        $("#myModal").modal("hide");

    }
    /**
     * 文件下载
     * 注释日期:2018年2月26日10:39:30
     */
    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ales-web/alesFile/downloadFile");
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

