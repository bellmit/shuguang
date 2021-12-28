var myapp=angular.module("myapp",[]).controller("ttsScltxxcjComplainAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    var map = {};
    // $http({
    //     headers:{
    //         token:window.localStorage.getItem("token")
    //     },
    //     url:"/sofn-tts-web/ttsScltxxcjComplain/getIssueType",
    //     method:"post",
    //     headers:{
    //         token:window.localStorage.getItem("token")
    //     }
    // }).success(function(data){
    //     if(data && data.length>0){
    //        var arr = [];
    //         for(var i in data){
    //             var arrChild = {site : data[i].dictName,id:data[i].dictValue};
    //             arr.push(arrChild);
    //             map[data[i].code] = data[i].dictName;
    //         }
    //         $scope.types = arr;
    //         window.localStorage.setItem("IssueType",JSON.stringify(map));
    //     }else{
    //         $scope.types = [];
    //     }
    // }).error(function(){
    //     $scope.types = [];
    // })
    //校验样式
    $('#attributeForm').bootstrapValidator();//必须有

    //modal 展示
    $scope.myModal61=function(){
        $("#myModal1").modal("show");
        /**
         * 基地图片1
         * @type {string}
         */
        $scope.LunboName = "";
        $scope.uploadUrl = "/sofn-tts-web/ttsScltxxcjComplain/upload";
        $("#file-1").fileinput({
            language: 'zh',
            uploadUrl: $scope.uploadUrl,
            //allowedFileExtensions : ['jpg', 'png','gif'],
            maxFileSize: 5300,
            maxFilesNum: 1,
            enctype: 'multipart/form-data',
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            msgFilesTooMany: "请选择{m}个附件！",
            msgSizeTooLarge: "只允许上传5MB以内的附件",
            dropZoneTitle: '附件预览'
        }).on("fileuploaded", function (event, data, previewId, index) {
            console.log('arguments', arguments);
            if (data && data['response']) {
                $scope.response = data.response.path;
                $("#attachPath").val(data.response.path);
                $("#fileName").val($($('div[class="file-caption-name"]')[0]).text());
            }
            return;
        });
    }



    //modal 主体信息
    $scope.findEntityInfo = function(){
        //产品信息框展示
        $("#select_Entity").modal("show");
        //$('#entityList').empty();
    };

    $scope.findEntity = function(){
        //清理数据
        var tableName = $('#entityList');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        var submit;
        var keywords = $("input[name='keywords']").val();
        if(!keywords){
            $.jBox.info("请输入搜索条件！", "提示", submit, { buttons: { '确认': true} });
            return;
        }
        //加载数据
        $("#entityList").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
//            bAutoWidth:true,
//            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : true,
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-tts-web/ttsScltxxcjComplain/getEntityPageInfo",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    params.keywords = keywords;
                    params.id = $.parseJSON(window.localStorage.getItem("entityInfo")).data.id;
                    //分页条件查询，不实现，自己写
                },
                type:"post"
            },
            columns : [
                {
                    data: "ENTERPRISE_NAME",
                    title: "实体名称"
                },
                {
                    data: "ENTITY_TYPE_NAME",
                    title: "实体类型"
                },
                {
                    data: "ENTITY_INDUSTRY_NAME",
                    title: "行业"
                },
                {
                    data: "AREA",
                    title: "区域"
                    ,render : function(data, type, row) {
                         return data?$.fn.Cityname(data):"";
                     }
                },
                {
                    data: "USER_IDCODE",
                    title: "主体用户码",
                    visible : false
                },
                {
                    data: "ENTITY_IDCODE",
                    title: "主体身份码",
                    visible : false
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        return "<button class='btn btn-primary btn-xs'" +
                            "onclick=selectProduct('"+row.USER_IDCODE+"','"+row.ENTERPRISE_NAME+"','"+row.ENTITY_IDCODE+"')>" +
                            "选定主体</button>";
                    }
                },
            ],
            language : {
                "sProcessing" : "处理中...",
                "sLengthMenu" : "显示 _MENU_ 项结果",
                "sZeroRecords" : "没有匹配结果",
                "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
                "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix" : "",
                "sSearch" : "搜索:",
                "sUrl" : "",
                "sEmptyTable" : "表中数据为空",
                "sLoadingRecords" : "载入中...",
                "sInfoThousands" : ",",
                "oPaginate" : {
                    "sFirst" : "首页",
                    "sPrevious" : "上页",
                    "sNext" : "下页",
                    "sLast" : "末页"
                },
                "oAria" : {
                    "sSortAscending" : ": 以升序排列此列",
                    "sSortDescending" : ": 以降序排列此列"
                }
            },
            sPaginationType : "full_numbers"
        });
    }

    /**
     * 将实体的id，名称，行业类型进行插入表单
     */
    window.selectProduct = function(userId,name,entityId){
        $("input[name='beEntityIdcode']").val(entityId);
        $("input[name='beUserIdcode']").val(userId);
        $("input[name='complaintEntName']").val(name);
    }

    $scope.setFileInput = function(){
        if(!$($('div[class="file-caption-name"]')[0]).text()){
            $("#attachPath").val("");
            $("#fileName").val("");
        }
    }

    //新增测试信息
    $scope.addTtsScltxxcjComplain = function(){
        var submit = null;
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        var userId;
        if(!entityInfo){
            $.jBox.info("你已注销！", "提示", submit, { buttons: { '确认': true} });
            return ;
        }
        userId = entityInfo.data.id;
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有

        var type = $("select[name='type']").val();
        var complaintEntName = $("input[name='complaintEntName']").val();
        if(!type || type == "? undefined:undefined ?"){
            jBox.tip("请选择问题类型！", 'info');
            return;
        }
        if(!complaintEntName){
            jBox.tip("请选择要投诉的主体！", 'info');
            return;
        }
    	 $scope.ttsScltxxcjComplain.complaintTitle = $("input[name='complaintTitle']").val();
    	 $scope.ttsScltxxcjComplain.content = $("textarea[name='content']").val();
    	 $scope.ttsScltxxcjComplain.type = $("select[name='type']").val();
         $scope.ttsScltxxcjComplain.status = "0";
        $scope.ttsScltxxcjComplain.attachmentName = $("#fileName").val();
    	 $scope.ttsScltxxcjComplain.attachmentPath = $("#fileName").val()?$("#attachPath").val():"";
         $scope.ttsScltxxcjComplain.beEntityIdcode = $("input[name='beEntityIdcode']").val();
         $scope.ttsScltxxcjComplain.beUserIdcode = $("input[name='beUserIdcode']").val();
         $scope.ttsScltxxcjComplain.complaintEntName = $("input[name='complaintEntName']").val();
        $scope.ttsScltxxcjComplain.typeName = $('select[name="type"]').find("option:selected").text();


        var type = $("select[name='type']").val();
        var typename = $('select[name="type"]').find("option:selected").val();
        console.log("type:"+type);
        console.log("typename:"+typename);

        $http({
            url:"/sofn-tts-web/ttsScltxxcjComplain/addTtsScltxxcjComplain?userId="+userId,
            data:$scope.ttsScltxxcjComplain,
            method:"post",
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(data.httpCode==200){
                alert("保存成功");
                $state.go("index.content.tts/TtsScltxxcjComplain/TtsScltxxcjComplainlist");
            }else{
                alert("保存失败");
            }
        }).error(function(){
            alert("保存失败");
        })
    }
})
