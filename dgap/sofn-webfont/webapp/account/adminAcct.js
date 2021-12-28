var myapp = angular.module("myapp", []);
myapp.controller("adminAcct", function ($scope, $http, $state) {
    //返回前一页
    $scope.callBack=function(){
        $state.go(window.localStorage.getItem("historyUrl"))

    }
    var token = window.localStorage.getItem("token");
    var dtId="";

    /**
     * 加载机构类别字典
     */
    $http({
        url:"/sofn-ads-web/adsDictData/getOrgTypeDictData",
        method:"post"
    }).success(function(data){
        var orgs = new Array();
        $.each(data.data,function (idx) {
            var a = $(this)[0];
            var d = {name: a.dictName, val: a.id, title: a.dictName};
            orgs.push(d);
        })
        $scope.orgtype = orgs;
        $scope.selectOrgType = $scope.orgtype[0].val;
    })

    /**
     * 加载资格证书字典
     */
    $http({
        url:"/sofn-ads-web/adsDictData/getQualificationsDictData",
        method:"post"
    }).success(function(data){
        var qual = new Array();
        $.each(data.data,function (idx) {
            var a = $(this)[0];
            var d = {name: a.dictName, val: a.id, title: a.dictName};
            qual.push(d);
        })
        $scope.qualifications = qual;
        $scope.selectQualifications = $scope.qualifications[0].val;
    })

    //下拉框、资格证书
   /* $scope.qualifications = [
        {name: "CNAS", val: "3", title: "CNAS"},
        {name: "CAL", val: "1", title: "CAL"},
        {name: "CMA", val: "2", title: "CMA"}
    ];*/
    $scope.changeQualifications = function () {
        console.log($scope.selectQualifications);
    }

    /**
     * 加载机构级别字典
     */
    $http({
        url:"/sofn-ads-web/adsDictData/getLevelDictData",
        method:"post"
    }).success(function(data){
        var level = new Array();
        $.each(data.data,function (idx) {
            var a = $(this)[0];
            var d = {name: a.dictName, val: a.id, title: a.dictName};
            level.push(d);
        })
        $scope.level = level;
        $scope.selectLevel = $scope.level[0].val;
    })

    //机构级别、测试数据
   /* $scope.level = [
        {name: "部级", val: "3", title: "部级"},
        {name: "省级", val: "1", title: "省级"},
        {name: "市级", val: "2", title: "市级"},
        {name: "县级", val: "4", title: "县级"},
        {name: "国家级", val: "5", title: "国家级"}
    ];*/
    $scope.changeLevel = function () {
        console.log($scope.selectLevel);
    }


    /**
     * 加载机构性质字典
     */
    $http({
        url:"/sofn-ads-web/adsDictData/getNatureDictData",
        method:"post"
    }).success(function(data){
        var nature = new Array();
        $.each(data.data,function (idx) {
            var a = $(this)[0];
            var d = {name: a.dictName, val: a.id, title: a.dictName};
            nature.push(d);
        });
        $scope.nature = nature;
        $scope.selectNature = $scope.nature[0].val;
    })
    //机构性质
    /*$scope.nature = [
        {name: "事业单位", val: "1", title: "事业单位"},
        {name: "公务员", val: "2", title: "公务员"},
    ];
    $scope.selectNature = $scope.nature[0].val;*/
    $scope.changeNature = function () {
        console.log($scope.selectNature);
    }

    var vm = this;


    //IE兼容
    if (navigator.appVersion.substring(22, 23) == 9) {
        if ($(".myPlaceholder").val() == "") {
            $(this).val("点击选择查询日期");
        }
    }

    /**
     * 查看机构变更历史
     * @param id
     */
    window.viewSubjSvChange = function (id) {
        $scope.subjElChangeId=id;
       $("#dModal").modal("show");
        setTimeout(function () {
            $http({
                url:"/sofn-ads-web/acctInfo/findSubjDtChangeById",
                method:"post",
                data:{id:$scope.subjElChangeId}
            }).success(function(data){
                //得到变更前信息
                 $scope.before=data.subjDt;
                 //得到变更后信息
                 $scope.after=data.subjDtChange;
                 if(data.subjDtChange.auditState=="0"){
                    $scope.after.auditState="未通过";
                 }else if(data.subjDtChange.auditState=="1"){
                    $scope.after.auditState="已通过";
                 }else{
                    $scope.after.auditState="待审核";
                 }

            }).error(function(){
            });
        },300);
    }

    $scope.queryList = function () {
        //$("#tb123").treegrid("reload");
        var param = {
            //"parentId": node.id,
            keyword: $scope.keyword,
            typeId: $scope.selectIndustry,
            //"start": 0
        };
        $("#grid").dataTable().api().settings()[0].ajax.data = param;
        $("#grid").dataTable().api().ajax.reload();
        $("#grid").dataTable().api().ajax.reload();
    }
    $scope.addModal = function () {
        $scope.title = "新增";
        $("#add_modal").modal("show");
    }

    /*$scope.updateModal = function (el) {
        var row = $scope.getSelectRow();
        if (row == null || row == "")  return;
        $scope.putRow(row);
        $scope.title = "编辑";
        $("#add_modal").modal("show");
    }*/
    $scope.updateModal = function () {
        //$scope.putRow(row);
        $scope.title = "账号信息 >  机构信息变更";
        $("#add_modal").modal("show");
    }

    $scope.putRow = function (row) {
        $("input[name='id']").val(row.id);
        $("input[name='typeId']").val(row.typeId);
        $("input[name='productCode']").val(row.productCode);
        $("input[name='useCode']").val(row.useCode);
        $("input[name='name']").val(row.name);
        $("input[name='alias']").val(row.alias);
        $("input[name='enname']").val(row.enname);
        $("input[name='gbCode']").val(row.gbCode);
        $("textarea[name='description']").val(row.description);
    }

    $scope.cancelMain = function () {
        //$scope.getFormJson( $("#add_modal"));
        $("#add_modal").modal("hide");
    };

    $scope.save = function () {
        var params=$scope.subjDt;
        save($state, $http);
    }

    $scope.delete = function () {
        var i = $scope.getSelectRowsIds();

        if (i != false) {
            var submit = function (v, h, f) {
                if (v == true)
                    var params = {
                        jsonIds: JSON.stringify(i)
                    };
                $.ajax({
                    url: "/sofn-sys-web/region/delete",
                    type: "post",
                    dataType: "json",
                    data: params,
                    success: function (resp) {
                        jBox.tip("删除成功", 'info');
                        $scope.queryList();
                        $('#cc').combotree('reload');
                    },
                    fail: function (data) {
                        jBox.tip("删除失败", 'info');
                    }
                });
            };
            $.jBox.confirm("确认修改吗？", "修改提示", submit, {buttons: {'确认': true, '取消': false}});
        }
    }

    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        return ids;
    }

    $scope.getSelectRow = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length < 1) {
            alert("请至少选择一行记录！");
            return false;
        } else if (length > 1) {
            alert("只能选择一行记录！");
            return false;
        }
        return rows[0];
    }

    $scope.import = function () {
        $("#file-1").trigger("click");
    }



    //未知
    /*setup()*/
    //var dataTable = datatable($scope,dtId);
    //easytree(dataTable);
    checkBoxChange();

    //使form里面的input只读
    readOnlyInput();

    var dtAreaId = "";

    //查询表单数据
    $scope.queryForm=function(){
        $http({
            url:"/sofn-ads-web/acctInfo/findDetectionByToken",
            method:"post",
            params : { "token": token},
        }).success(function(data){
            dtId=data.data.id;
            datatable($scope,dtId)
            $scope.subjDt=data.data;
            dtAreaId = $scope.subjDt.dtAreaId;
            $scope.subjDt.dtAreaValue = $.fn.Cityname(dtAreaId);
            $.fn.mycity("sheng","shi","xian",dtAreaId);
        }).error(function(){
        });
    }
    $scope.queryForm();

    //初始化表单验证
    $('#modalForm').bootstrapValidator();
    $("#save").click(function () {
        if (!$("#modalForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        //执行添加操作
        window.save($scope,$http,token);
    })

})

var readOnlyInput=function(){
    var ff=document.forms[0];
    for (var i=0;i<ff.elements.length;i++)
    {
        var ee=ff.elements[i];
        if("INPUT"==ee.tagName||"SELECT"==ee.tagName){
            ee.readOnly=true;
        };
    }
}

var save = function (state, http,token) {
    //省市县区域赋值
    var sheng = $("#sheng option:selected").val();
    var shi = $("#shi option:selected").val();
    var xian = $("#xian option:selected").val();
    if(sheng!=null && sheng!='' && sheng!='0'){
        $("input[name='dtAreaId']").val(sheng);
        $("input[name='dtArea']").val($.fn.Cityname(sheng));
    }
    if(shi!=null && shi!='' && shi!='0') {
        $("input[name='dtAreaId']").val(shi);
        $("input[name='dtArea']").val($.fn.Cityname(shi));
    }
    if(xian!=null && xian!='' && xian!='0'){
        $("input[name='dtAreaId']").val(xian);
        $("input[name='dtArea']").val($.fn.Cityname(xian));
    }
    //字典数据赋值
    var dtType = $("select[name='dtTypeId']").find("option:selected").text();
    var dtQualifications = $("select[name='dtQualificationsId']").find("option:selected").text();
    var dtLevel = $("select[name='dtLevelId']").find("option:selected").text();
    $("input[name='dtType']").val(dtType);
    $("input[name='dtQualifications']").val(dtQualifications);
    $("input[name='dtLevel']").val(dtLevel);

    var params = {};
    var obj = obj || $("#modalForm").serializeArray();
    $.each(obj, function () {
        if (params[this.name]) {
            if (!params[this.name].push) {
                params[this.name] = [params[this.name]];
            }
            params[this.name].push(this.value || '');
        } else {
            params[this.name] = this.value || '';
        }
    });
    var req = {
        method: 'post',
        url: '/sofn-ads-web/acctInfo/addSubjDtChange',
        headers: {
            post: {'Content-Type': 'application/x-www-form-urlencoded'}
        },
        data: params,
        params : {"token" : token}
    }

    http(req).then(function (data) {
        $("#add_modal").modal("hide");
        $("#grid").dataTable().api().ajax.reload();
        if(data.data.httpCode == "200"){
            jBox.tip("保存成功", 'success');
        }else{
            jBox.tip(data.data.msg, 'error');
        }
    }, function (data) {
        $("#tb123").datagrid("reload", {});
        console.log(data);
    });
}
var checkRecode = function (el) {
    var bool = $(el).prop("checked");
    $(el).prop("checked", !bool);
    var tr = $(el).parent().parent();
    bool ? tr.removeClass("selected") : tr.addClass("selected");
}
var checkBoxChange = function () {
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#grid input[name='checkOne']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[name='checkOne']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

    /* $("#grid input[name='checkOne']").on("click",function() {
     if (this.prop("checked") == true) {
     this.prop("checked", true);
     $("#grid tbody tr").addClass("selected");
     } else {
     $("#grid input[name='checkOne']").prop("checked", false);
     $("#grid tbody tr").removeClass("selected");
     }
     });*/
}

var datatable = function ($scope,dtId) {
    console.log(dtId);
    //bootstrap表格
    $("#grid").dataTable({
        ajax : {
            url : "/sofn-ads-web/acctInfo/getChangeListBySvId",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc:function(json){
                console.log(json);
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                for(var i=0;i<json.list.length;i++){
                    if(json.list[i].APPLY_TIME!=null&&json.list[i].APPLY_TIME!=""&&json.list[i].APPLY_TIME!=undefined){
                        json.list[i].APPLY_TIME=new Date(parseInt(json.list[i].APPLY_TIME)).toISOString().slice(0,10);
                    }
                }
                return json.list;
            },
            data : function(params) {
                //params.xcPsersionId=window.localStorage.getItem("xcPsersionId","xcPsersionId");
                //params.svCode="123456";
                params.svId=dtId;

            },
            type:"post"
        },
        columns : [
            {
                //data : "RN",
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data : "CHANGE_CONTENT",
                width: '50%',
                title : "变更内容"
            },
            {
                data : "APPLY_REASON",
                title : "变更原因"
            },
            {
                data : "APPLY_TIME",
                title : "申请时间"
            },
            //{
            //    data : "APPLY_USER_ID",
            //    title : "申请人"
            //},
            {
                data : "AUDIT_STATE",
                title : "审核状态",
                render:function(data,type,row){
                    if(data!=null&&data!=""&&data!=undefined){
                        if(data=="0"){
                            return "未通过"
                        }else{
                            return "已通过";
                        }
                    }else{
                        return "待审核";
                    }
                }
            },
            {
                data : "ID",
                title : "操作",
                render : function(data, type, row) { // 模板化列显示内容
                    return '<a style="cursor: pointer" class="Check-report"  onclick="viewSubjSvChange(\''+data+'\')">查看</a>';//转义传值
                }
            },
        ],
    });
};
