var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("badrecodeList",function($scope,$http,$state){
    //获取行业数据字典
    $scope.industryType = [];
    $http({
        url: "/sofn-qry-web/sysDict/getSysDictByCode",
        data: 2,
        method: "post"
    }).success(function(data) {
        $scope.industryType = JSON.parse(data.data);
    });
    //获取主体类型数据字典
    $scope.subjType = [];
    $http({
        url: "/sofn-qry-web/sysDict/getSysDictByCode",
        data: 7,
        method: "post"
    }).success(function(data) {
        $scope.subjType = JSON.parse(data.data);
    });
    //获取当前登录用户机构信息，并完成初始化
    $http({
        url: "/sofn-ales-web/dailyEnforceLaw/getOrgbyToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    })
    .success(function(data) {
        orgId = data.data.orgId;
        $scope.subjLevel = data.data.orgLevel; //主体级别
        $scope.jurisdictionArea = data.data.regionId.substr(
                0,
                2 * ($scope.subjLevel - 1)
        ); //管辖区域
        //限定查询区域
        $.fn.mycity("sheng", "shi", "xian", $scope.jurisdictionArea);
        if (
                $("#sheng").val() != null &&
                $("#sheng").val() != "" &&
                $("#sheng").val() != undefined
        ) {
            $("#sheng")
            .attr("disabled", true)
            .parent()
            .addClass("disabled");
        }
        if (
                $("#shi").val() != null &&
                $("#shi").val() != "" &&
                $("#shi").val() != undefined
        ) {
            $("#shi")
            .attr("disabled", true)
            .parent()
            .addClass("disabled");
        }
        if (
                $("#xian").val() != null &&
                $("#xian").val() != "" &&
                $("#xian").val() != undefined
        ) {
            $("#xian")
            .attr("disabled", true)
            .parent()
            .addClass("disabled");
        }
        //所属行业默认不限、组织形式不限、主体类别不限
        $("input[name='entityIndustryLimit']:first").prop(
                "checked",
                true
        );
        // $("input[name='entityScaleLimit']:first").attr("checked",true);
        $("input[name='entityTypeLimit']:first").prop("checked", true);
       /* $scope.loadGrid();*/
        loadGrid();
    })
    .error(function(msg) {
        console.log(msg);
        
    });
    //点击查询按钮刷新datatable
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
//分页
    //bootstrap表格
    function loadGrid() {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEntBadrecord/getAsmsSubjEntBadrecordList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                //传入后台的参数（点击查询 前台页面传入后台的参数）
                data: function (params) {
                        params.xcPsersionId = window.localStorage.getItem("xcPsersionId", "xcPsersionId");
                        params.entityIndustry = $scope.allIndustryId;
                        params.entityType = $("select[name='entityType']").val();
                        params.entityIndustry = $("select[name='entityIndustry']").val();
                        params.queryCon = $("input[name='queryCon']").val();

                        ObjShen = $("select[name='ObjShen']").val();
                        ObjShi = $("select[name='ObjShi']").val();
                        ObjXian = $("select[name='ObjXian']").val();

                        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                                    params.area = ObjXian
                                } else {
                                    params.area = ObjShi
                                }
                            } else {
                                params.area = ObjShen
                            }
                        }

                },
                type: "POST"
            },
            columns: [
                //页面展示头信息
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"
                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    width:"21%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>16){
                            return "<a id='ENTERPRISE_NAME"+row.ID+"' onmouseover=showPopContent('ENTERPRISE_NAME"+row.ID+"','"+data+"')>"+data.substring(0,16)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "ENTITY_INDUSTRY_NAME",
                    title: "所属行业",
                    width:"11%"
                },
                {
                    data: "ENTITY_SCALE_NAME",
                    title: "组织形式",
                    width:"10%"
                },
                {
                    data: "ENTITY_TYPE_NAME",
                    title: "主体类别",
                    width:"8%"
                },
                {
                    data: "ENTITY_IDCODE",
                    title: "主体身份码",
                    width:"18%"
                },
                {
                    data: "AREA",
                    title: "区域",
                    width:"15%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>12){
                            return "<a id='AREA"+row.ID+"' onmouseover=showPopContent('AREA"+row.ID+"','"+data+"')>"+data.substring(0,12)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt = '<a style="cursor: pointer" class="Check-report"  onclick="Checkreport(\'' + row.ENTITY_IDCODE + '\')">查看</a>';//转义传值
                        butt += '<a style="cursor: pointer" class="ml15" onclick="printSubjEntCreditInfo(\'' + row.ENTITY_IDCODE + '\')"  target="_blank">打印</a>';
                        return butt;
                    },
                    width:"7%"
                },
            ],
        });
    }

    window.printSubjEntCreditInfo = function(entityCode) {
        window.localStorage.setItem("entityCode", entityCode);
        window.open("#/printSubjEntCreditInfo", "_bank");
    };

    $scope.cleanSelectModelQuery = function(){
        $("input[name='queryCon']").val("");
        $("select[name='entityType']").find("option:first").prop("selected",true);
        $("select[name='entityIndustry']").find("option:first").prop("selected",true);
        $.fn.mycity("sheng","shi","xian",$scope.jurisdictionArea);
        $("#grid").dataTable().api().ajax.reload();
    };
    //查看详情页面
    window.Checkreport=function(badrecodeID){
        window.localStorage.setItem("badrecodeID",badrecodeID);
        $state.go("index.content.ales/subjectInfo/badRecod/badrecodeCheckreport")
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



