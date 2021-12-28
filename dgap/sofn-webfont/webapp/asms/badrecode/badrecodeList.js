var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("badrecodeList",function($scope,$http,$state){
    $scope.badrecodeListQueryParams = JSON.parse(window.sessionStorage.getItem("badrecodeListQueryParams"));
    var area = ""
    var jibie=""
    //从缓存从获取数据
    if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
        //获取用户信息
        $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
        $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
        //获取主体类别数据字典
        $scope.subjType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['subjType'];
        //获取临时主体类别数据字典
        $scope.subjEntTempType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['linshizhutileixing'];
        //获取组织形式数据字典
        $scope.orgMode = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['orgMode'];
        //获取行业数据字典
        $scope.industryType = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['industryType'];
        //所属区域
        $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['allIndustryId'];
        area = $scope.underArea;
        jibie= $scope.organization.orgLevel;
        if(jibie==1){
            area=0
        }
        if(jibie==2){
            area=area.substr(0,2);
        }
        if(jibie==3){
            area=area.substr(0,4)
        }
        $.fn.mycity("sheng","shi","xian",area);
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
        setTimeout(function() {
            if($scope.badrecodeListQueryParams){
                $("#queryCon").val($scope.badrecodeListQueryParams.queryCon);
                $.fn.mycity("sheng","shi","xian",$scope.badrecodeListQueryParams.area);
                $scope.entityType = $scope.badrecodeListQueryParams.entityType;
                $("select[name='entityIndustry']").val($scope.badrecodeListQueryParams.entityIndustry);
                $("select[name='entityType']").val($scope.badrecodeListQueryParams.entityType);
            }
        },50)
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }

    //点击查询按钮刷新datatable
    $("#ToCheck").click(function(){

        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        var badrecodeListQueryParams = {};
        badrecodeListQueryParams.entityIndustry = $("select[name='entityIndustry']").val();
        badrecodeListQueryParams.entityType = $("select[name='entityType']").val();
        badrecodeListQueryParams.queryCon = $("input[name='queryCon']").val();

        ObjShen = $("select[name='ObjShen']").val();
        ObjShi = $("select[name='ObjShi']").val();
        ObjXian = $("select[name='ObjXian']").val();

        if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
            if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                if (ObjXian != "" && ObjXian != null && ObjXian != undefined) {
                    badrecodeListQueryParams.area = ObjXian
                } else {
                    badrecodeListQueryParams.area = ObjShi
                }
            } else {
                badrecodeListQueryParams.area = ObjShen
            }
        }
        window.sessionStorage.setItem("badrecodeListQueryParams",JSON.stringify(badrecodeListQueryParams));
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
                    var badrecodeListQueryParams = JSON.parse(window.sessionStorage.getItem("badrecodeListQueryParams"));
                    if(badrecodeListQueryParams){
                        params.xcPsersionId = badrecodeListQueryParams.xcPsersionId;
                        params.entityIndustry = badrecodeListQueryParams.entityIndustry;
                        params.entityType = badrecodeListQueryParams.entityType;
                        params.queryCon = badrecodeListQueryParams.queryCon;
                        params.area = badrecodeListQueryParams.area
                    }else{
                        params.xcPsersionId = window.localStorage.getItem("xcPsersionId", "xcPsersionId");
                        params.entityIndustry = $scope.allIndustryId;
                        params.entityType = $("select[name='entityType']").val();
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
        $.fn.mycity("sheng","shi","xian",area);
        $("#grid").dataTable().api().ajax.reload();
    };
    //查看详情页面
    window.Checkreport=function(badrecodeID){
        window.localStorage.setItem("badrecodeID",badrecodeID);
        $state.go("index.content.asms/badrecode/badrecodeCheckreport")
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



