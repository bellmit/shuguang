var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckObjectEdit",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //tab跳转
    $scope.goCheckObject = function () {
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject")
    }
    $scope.goCheckStandard = function () {
        $state.go("index.content.monitor/model/adsModelCheckStandard/adsModelCheckStandard")
    }
    $scope.goCheckItem =function () {
        $state.go("index.content.monitor/model/checkItem/adsModelCheckItemList")
    }
    $scope.goModelType = function () {
        $state.go("index.content.monitor/model/adsModelType/modelTypeList")
    }
    $scope.goSampleLink = function () {
        $state.go("index.content.monitor/model/adsModelSampleLink/adsModelSampleLink")
    }
    $scope.goJudgeStabdard = function () {
        $state.go("index.content.monitor/model/judgeStandard/adsModelJudgeStandardList")
    }
    $("#checkObject").click(function(){
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject")
    });

    //获取本地存储的id
    var id = window.localStorage.getItem("checkObjectId");

    /**
     * 根据id查询检测对象详情信息
     */
    $http({
        method:'POST',
        data:{"id":id},
        url:'/sofn-ads-web/adsModelCheckObject/queryById'
    }).success(function(data){
        var adsModelCheckObject = data.adsModelCheckObject;
        $("input[name='id']").val(adsModelCheckObject.id);
        $("input[name='name']").val(adsModelCheckObject.name);
        $("input[name='gbCode']").val(adsModelCheckObject.gbCode);
        $("input[name='productCode']").val(adsModelCheckObject.productCode);
        $("input[name='productName']").val(adsModelCheckObject.productName);
        $("input[name='pdIndustry']").val(adsModelCheckObject.industry);
        //$("input[name='numbers']").val(adsModelCheckObject.numbers);
    })

    $("#ToCheck").click(function(){
        $("#productList").dataTable().api().ajax.reload();
    });

    var isType = {};    //行业类型
    $http({
        method:'POST',
        url:'/sofn-ads-web/adsDictData/getIndustryTypeData'
    }).success(function(data){
        $scope.activities =data.data;
        if(data.data != null){
            for(var i in data.data){
                isType[data.data[i].dictValue]=data.data[i].dictName;
            }
        }
    })

    $('#updateForm').bootstrapValidator();
    $("#update").click(function () {
        if (!$("#updateForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        $scope.updateAdsModel();
    })

    //修改检测对象
    $scope.updateAdsModel = function(){
        var id = $("input[name='id']").val();
        var name = $("input[name='name']").val();
        var gbCode = $("input[name='gbCode']").val();
        var productCode = $("input[name='productCode']").val();
        var productName = $("input[name='productName']").val();
        var industry = $("input[name='pdIndustry']").val();
        //var numbers = $("input[name='numbers']").val();
        if(id==null || id==''){
            jBox.tip("对象id丢失，修改失败", 'info');
            return false;
        }
        if(name==null || name=='' || productCode==null || productCode==''){
            jBox.tip("请先选择产品信息", 'info');
            return false;
        }
        $.ajax({
            url:"/sofn-ads-web/adsModelCheckObject/updateAdsModelCheckObject",
            type:"post",
            dataType:"json",
            data:{"id":id,"gbCode":gbCode,"productCode":productCode,"productName":productName,"industry":industry,"name":name,"token":token},
            success:function(data){
                if(data.httpCode == 200){
                    jBox.tip("修改成功", 'success');
                }else if(data.httpCode == 400){
                    jBox.tip("没有登录", 'info');
                }else if(data.httpCode == 409){
                    jBox.tip("修改失败,该名称已被使用", 'error');
                }else{
                    jBox.tip("修改失败", 'error');
                }
            }
        });
    };

    $scope.back = function(){
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject");
    };

    $scope.edit = function(){
        window.localStorage.setItem("objectId",id);
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObjectEditO");
    };

    /**
     * 选择产品名称
     */
    window.findProductInfo = function() {
        //产品信息框展示
        $("#product_info").modal('show');
        //清理数据
        var tableName = $('#productList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#productList").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            // info:false,//是否显示过滤
            // lengthChange:false,//是否显示控制每页显示条数
            // sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : true,
            ajax : {
                url : "/sofn-ads-web/adsDictData/getProductPageInfo",
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
                    params.industry =$("#pro_industry").find("option:selected").val();
                    params.keyWord =$("input[name='keyWord']").val();
                },
                type:"post"
            },
            columns:[
                {
                    data: "typeId",
                    title: "所属行业",
                    render : function(data,type,row){
                        return isType[row.typeId];
                    }
                },
                {
                    data: "name",
                    title: "名称"
                },
                {
                    data : "id",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var name = row.name;
                        var gbCode = row.gbCode;
                        var productCode = row.productCode;
                        var productName = row.parentName;
                        var pro = {name:name,gbCode:gbCode,productCode:productCode,productName:productName};
                        return "<button class='gui-btn' onclick='findProduct("+JSON.stringify(pro)+")'>选定产品</button>"

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
        }).on( 'shown.bs.tab', function (e) {
            $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
        } );
    }

    /**
     * 选定产品，保存必要数据
     */
    window.findProduct = function(product){
        $("input[name='name']").val(product.name);  //产品名称
        $("input[name='gbCode']").val(product.gbCode);  //产品种类编号
        $("input[name='productCode']").val(product.productCode);    //产品编号
        $("input[name='productName']").val(product.productName);    //产品种类名称
        var industry = $("select[name='industry']").find("option:selected").text();
        $("input[name='pdIndustry']").val(industry);    //产品所属行业
        $("#product_info").modal('hide');
        jBox.tip("选定成功", 'success');
    }

});