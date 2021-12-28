/**
 * Created by Administrator on 2018/8/20 0020.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("sysSubjSPYBList",function($scope,$http,$state){
    //认证类型
    $scope.authenticationType = [{"dictName":"绿色食品", "dictValue":"绿色认证"},
        {"dictName":"有机食品", "dictValue":"有机认证"},
        {"dictName":"无公害农产品", "dictValue":"无公害认证"},
        {"dictName":"无", "dictValue":"无"}]
    //账号来源
    $scope.accountResource = [{"dictName":"三品一标导入", "dictValue":"SPYB"},
        {"dictName":"其它", "dictValue":"其它"}]
    //三品一标主体信息完善情况下拉菜单(2为未完善,其余为已完善)
    $scope.infoImprovedStateList = [{"dictName":"已完善", "dictValue":"0,1,3,4,5,6,7"},
        {"dictName":"未完善", "dictValue":"2"}]
    loadGrid();
    //bootstrap表格
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntListForSys",
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
                    params.accountResource = "SPYB";
                    params.enterpriseName = $("input[name='entName']").val();
                    params.spybLicType = $("select[name='spybLicType']").val();
                    params.approveStatus = $("select[name='infoImprovedState']").val();
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
                    width:"22%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>18){
                            return "<a id='enterpriseName"+row.id+"' onmouseover=showPopContent('enterpriseName"+row.id+"','"+data+"')>"+data.substring(0,18)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                {
                    data: "account",
                    title: "管理员账号",
                    width:"8%",
                },
                {
                    data: "userPassword",
                    title: "初始密码",
                    width:"8%",
                },
                {
                    data: "entityScaleName",
                    title: "组织形式",
                    width:"8%",
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
                    width:"8%",
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
                    width:"8%",
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
                    width:"8%",
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
                    width:"14%",
                    render: function (data) {
                        return data==null?"无":data;

                    }
                },
                {
                    data : "createTime",
                    title : "注册时间",
                    width:"8%"
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
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //跳转到生产经营主体备案查看页面
    window.subjEntSPYBView=function(data){
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId",data);
        $state.go("index.content.asms/subjEnt/subjEntSPYBView", { source: 'sys' });
    };

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
    /**
     * 导出账号
     */
    $scope.exportSubjEnt = function() {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/subjEnt/exportSubjEnt");

        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'enterpriseName');
        input1.attr('value', $("input[name='entName']").val());
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'token');
        input2.attr('value', window.localStorage.getItem("token"));
        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'spybLicType');
        input3.attr('value',  $("select[name='spybLicType']").val());
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'accountResource');
        input4.attr('value',  'SPYB');
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'approveStatus');
        input5.attr('value',  $("select[name='infoImprovedState']").val());
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.submit();
    }

})