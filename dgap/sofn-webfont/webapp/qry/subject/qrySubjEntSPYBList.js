/**
 * Created by Administrator on 2018/8/20 0020.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("qrySubjEntSPYBList",function($scope,$http,$state,$timeout){

    var vm = this;
    //判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function(item,i){
        if(item.text=="检测机构主体"){
            $(".qrySubjDtList").css("display","inline-block");
        }else if(item.text=="执法机构主体"){
            $(".qrySubjElList").css("display","inline-block");
        }else if(item.text=="监管机构主体"){
            $(".qrySubjSvList").css("display","inline-block");
        }else if(item.text=="生产经营主体"){
            $(".qrySubjEntList").css("display","inline-block");
        }
    });
    //认证信息字典
    $scope.authenticationType = [{"dictName":"绿色食品", "dictValue":"绿色认证"},
        {"dictName":"有机食品", "dictValue":"有机认证"},
        {"dictName":"无公害农产品", "dictValue":"无公害认证"},
        {"dictName":"无", "dictValue":"无"}]
    //三品一标主体信息完善情况下拉菜单(2为未完善,其余为已完善)
    $scope.infoImprovedStateList = [{"dictName":"已完善", "dictValue":"0,1,3,4,5,6,7"},
        {"dictName":"未完善", "dictValue":"2"}]
    loadGrid();
    //bootstrap表格
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSubjEntList",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime)+28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    var subjEntSPYBListQueryparams = JSON.parse(window.sessionStorage.getItem("subjEntSPYBListQueryparams"));
                    params.accountResource = "SPYB";
                    params.spybLicType = $("select[name='spybLicType']").val();
                    params.approveStatus = $("select[name='infoImprovedState']").val();
                    params.enterpriseName = $("input[name='entName']").val();

                    $scope.chartParams = params; //图标参数赋值
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"

                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    width:"24%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='enterpriseName"+row.id+"' onmouseover=showPopContent('enterpriseName"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "entityScaleName",
                    title: "组织形式",
                    width:"10%",
                    render: function(data) {
                        if (data&&"β"==data) {
                            return "无"
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width:"12%",
                    render: function(data) {
                        if (data&&"β"==data) {
                            return "无"
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "entityTypeName",
                    title: "主体类别",
                    width:"10%",
                    render: function(data) {
                        if (data&&"β"==data) {
                            return "无"
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "approveStatus",
                    title: "信息完善情况",
                    width:"10%",
                    render: function(data) {
                        if (data == 2) {
                            return "未完善";
                        }
                        return "已完善";
                    }
                },
                {
                    data: "spybLicType",
                    title: "认证类型",
                    width:"15%",
                    render: function (data) {
                        return data==null?"无":data;

                    }
                },
                {
                    data : "createTime",
                    title : "注册时间",
                    width:"10%"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntSPYBView(\'' + data + '\'); javascript:void(0);">查看</a>';
                        return butt;
                    }
                },
            ],
        });
    }

    $scope.reloadArea = function () {
        $("input[name='entName']").val("");
        $("select[name='spybLicType']").find("option:first").prop("selected",true);
        $("select[name='infoImprovedState']").find("option:first").prop("selected",true);
    }

    //重新加载表格
    $scope.querySubjEntList=function(){
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //跳转到生产经营主体备案查看页面
    window.subjEntSPYBView=function(data){
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId",data);
        $state.go("index.content.qry/subject/qrySubjEntSPYBView");
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
    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-qry-web/subject/getSubjEntListStatis",
        grid: [2,2], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
})