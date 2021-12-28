var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckObjectAdd",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

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

    $scope.back = function(){
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject");
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
                    var str = $("input[name='keyWord']").val();
                    str = str.replace(/^\s+|\s+$/g,"");
                    params.keyWord = str;
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

    $('#addForm').bootstrapValidator();
    $("#add").click(function () {
        if (!$("#addForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        $scope.addCheckObject();
    })

    /**
     * 新增检测对象数据
     */
    $scope.addCheckObject = function(){
        var name = $("input[name='name']").val();
        var gbCode = $("input[name='gbCode']").val();
        var productCode = $("input[name='productCode']").val();
        var productName = $("input[name='productName']").val();
        var industry = $("input[name='pdIndustry']").val();
        if(name==null || name=='' || productCode==null || productCode==''){
            jBox.tip("请先选择产品信息", 'info');
            return false;
        }
        $.ajax({
            url:"/sofn-ads-web/adsModelCheckObject/addAdsModelCheckObject",
            type:"post",
            dataType:"json",
            data:{"gbCode":gbCode,"productCode":productCode,"productName":productName,"industry":industry,"name":name,"token":token},
            success:function(data){
                if(data.httpCode == 200){
                     //保存成功，跳转页面到列表页
                    jBox.tip("保存成功", 'success');
                    $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject");
                }else if(data.httpCode == 409){
                    jBox.tip("保存失败,该产品已存在", 'info');
                }else{
                    jBox.tip("保存失败", 'error');
                }
            }
        });
    };

});