var myapp = angular.module("myapp", []);
myapp.controller("regionList", function ($scope, $http, $state) {
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('行政区划管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;

    $scope.region = {
        id: "",
        parentId: "",
        parentText: "",
        regionName: "",
        regionCode: "",
        remark: "",
        oldRegionCode: "",
        isProvAdmCounty: ""
    };
    $scope.submittingValidate = false;
    // 是否省管县下拉框选项
    $scope.isProvAdmCountyOptions = [
        {
            text: "是",
            value: "Y"
        },
        {
            text: "否",
            value: "N"
        }
    ];

    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }

    window.saveURL = "";
    window.regionId = "";
    window.regionText = "";

    document.onkeydown = keyListener;
    function keyListener(e){
        if(e.keyCode == 13){
            $scope.queryList();
        }
    }
    var vm = this;
    //导航展开
    $("#collapseThree").attr("class", "panel-collapse collapse in");
    $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");

    //combotree
    $('#cc').combotree({
        url: 'sofn-sys-web/region/recursionQuery?delFlag=Y',
        // required: true,
        onLoadSuccess: function (node,data) { //数据加载完毕事件
           /* var treeObject = $('#cc').combotree('tree');
            $("#"+treeObject.tree("getRoot").domId).hide();*/
            //$('#cc').combotree("setValue",5);
            //console.log(22);
        },
        onChange: function (n, o) {//will be triggered when value changes
            var treeObject = $('#cc').combotree('tree');	// get the tree object
            var treeNode = treeObject.tree('getSelected');		// get selected node

            $scope.$evalAsync(function () {
                $scope.region.parentId = treeNode.id;
                $scope.region.parentText = treeNode.text;
            });
        }
    });
    //IE兼容
    if (navigator.appVersion.substring(22, 23) == 9) {
        if ($(".myPlaceholder").val() == "") {
            $(this).val("点击选择查询日期");
        }
    }

    //query
    $scope.queryList = function () {
        var node = $('#regionEasyUi').tree('getSelected');
        var param = {
            "parentId": node ? node.id : 1,
            // "start": 0,
            keyWord:$("input[name='keyWord']").val()
        };
        $("#grid").dataTable().api().settings()[0].ajax.data = param;
        $("#grid").dataTable().api().ajax.reload();
    }

    //add
    $scope.addModal = function () {
        $scope.title = "新增";
        $scope.clearRow();
        window.saveURL = "/sofn-sys-web/region/add";
        $scope.region.parentId = window.regionId;
        $scope.region.parentText = window.regionText;
        $("#add_modal").modal("show");
        // $('#cc').combotree("clear");
    }

    //modify
    $scope.updateModal = function (row) {
/*        var bool = $scope.getSelectRow();
         if(!bool){
         return;
         }*/
        $('#cc').combotree("setValue", "");
        $scope.putRow(row);
        $scope.title = "编辑";
        window.saveURL = "/sofn-sys-web/region/update";
        $("#update_modal").modal("show");
    }
    window.modifyRegion = $scope.updateModal;
// 设置dataTable数据操作
    window.settingTableOperation = function(id) {
        var link = '';
        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
            link += '<a id="modify<%= id %>" style="cursor: pointer" class="color">修改</a>';
        }
        var template = _.template(link);

        return template({id: id});
    }
    //put the value of one record into the modal form
    $scope.putRow = function (row) {
/*        var treeObject = $('#cc').combotree('tree');
        var root=treeObject.tree("getRoot");
        if(root.id==row.parentId){
            $("#treeCombox").hide();
        }else{
            $("#treeCombox").show();
        }*/
/*        $('#cc').combotree("setValue",row.parentId);*/
/*        $("input[name='id']").val(row.id);
        $("input[name='parentId']").val(row.parentId);
        $("input[name='regionName']").val(row.regionName);
        $("input[name='regionCode']").val(row.regionCode);
        $("input[name='sortid']").val(row.sortid);
        $("textarea[name='remark']").val(row.remark);*/
       /* $("input[name='enname']").val(row.enname);
        $("input[name='gbCode']").val(row.gbCode);
        $("textarea[name='description']").val(row.description);*/

        $scope.region.id = row.id;
        $scope.region.parentId = row.parentId;
        $scope.region.regionName = row.regionName;
        $scope.region.regionCode = row.regionCode;
        $scope.region.remark = row.remark;
        $scope.region.oldRegionCode = row.regionCode;
        $scope.region.isProvAdmCounty = row.isProvAdmCounty;

        $http({
            url: "/sofn-sys-web/region/queryById",
            method: "post",
            data: {id: row.parentId},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            $scope.region.parentText = json.data.regionName;
        });
    }

    //clear the form of modal
    $scope.clearRow = function () {
/*        $("input[name='id']").val("");
        $("input[name='parentId']").val("");
        $("input[name='regionName']").val("");
        $("input[name='regionCode']").val("");
        $("input[name='sortid']").val("");
        $("textarea[name='remark']").val("");*/
        $scope.region.id = "";
        $scope.region.parentId = "";
        $scope.region.parentText = "";
        $scope.region.regionName = "";
        $scope.region.regionCode = "";
        $scope.region.remark = "";
        $scope.region.oldRegionCode = "";
    }

    //close the modal
    // $scope.cancelMain = function () {
    //     //$scope.getFormJson( $("#add_modal"));
    //     $("#add_modal").modal("hide");
    // };

    window.submitting = false; // 正在提交数据的标识，用于防重复提交
    //save the data(update & add)
    $scope.save = function(isValid) {
        $scope.submittingValidate = true;

        if (isValid && (! window.submitting)) {
            window.submitting = true;
            $scope.submittingValidate = false;
            save($state, $http, $scope);
        }
    }

    //delete the records
    $scope.delete = function(){
        var i = $scope.getSelectRowsIds();
        if (i!=false){
            var submit = function (v, h, f) {
                if (v == true) {
                    var params = {
                        jsonIds : JSON.stringify(i),
                        token: window.localStorage.getItem("token")
                    };

                    $.ajax({
                        url:"/sofn-sys-web/region/delete",
                        type:"post",
                        dataType:"json",
                        data:params,
                        success:function(data){
                            if (data.httpCode == 200) {
                                jBox.tip("删除成功", 'info');
                                $scope.queryList();
                                $('#regionEasyUi').tree('options').url = "/sofn-sys-web/region/queryMenu?regionId=0";
                                $('#regionEasyUi').tree('reload');
                            } else {
                                jBox.tip("删除失败", 'error');
                            }
                        },
                        fail: function (data) {
                            jBox.tip("删除失败", 'error');
                        }
                    });
                }
            };
            $.jBox.confirm("确定删除吗？", "删除提示", submit, { buttons: { '确认': true, '取消': false} });
        }
    }

    //get the id which the rows been selected
    $scope.getSelectRowsIds = function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if(length<=0){
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        return ids;
    }

    //get selected row's values
    $scope.getSelectRow = function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if(length<1){
            alert("请至少选择一行记录！");
            return false;
        }else if(length>1){
            alert("只能选择一行记录！");
            return false;
        }
      return rows[0];
    }
    //未知
    /*setup()*/
    //init the dataTable
    var dataTable=datatable1();

    //init the easyui tree
    regionEasytree();

    //select the whole table or nots
    checkBoxChange();


    $(function () {
        $("[data-toggle='tooltip']").tooltip({html : true });
    });
})

//save the data when update & add
var save = function(state, http, scope) {
    var params = {};
    params.id = scope.region.id;
    params.parentId = scope.region.parentId;
    params.regionName = scope.region.regionName;
    params.regionCode = scope.region.regionCode;
    params.remark = scope.region.remark;
    params.isProvAdmCounty = scope.region.isProvAdmCounty;

    // if(params.parentId==null || params.parentId==""){
    //     var treeObject = $('#cc').combotree('tree');
    //     var root=treeObject.tree("getRoot");
    //     params.parentId=root.id;
    // }
    var req = {
        method: 'post',
        url: saveURL,
        headers: {
            'token': window.localStorage.getItem("token"),
            'oldRegionCode': scope.region.oldRegionCode
        },
        data: params
    }

    http(req).then(function(data) {

        if (data.data.httpCode == 200) {
            $("#add_modal").modal("hide");
            $("#update_modal").modal("hide");
            setTimeout(function(){
                $("#grid").dataTable().api().ajax.reload();
                $('#regionEasyUi').tree('options').url = "/sofn-sys-web/region/queryMenu?regionId=0";
                $('#regionEasyUi').tree('reload');
            }, 1000);
            $.jBox.tip("保存成功");
        } else if (data.data.httpCode == 407) {
            $.jBox.tip(data.data.msg, 'error');
        } else {
            $.jBox.tip("保存失败", 'error');
        }
        window.submitting = false;
    }, function(data){
        window.submitting = false;
        ajaxErrorHandler(data.status);
    });
}


var checkRecode=function(el){
    var bool=$(el).prop("checked");
    $(el).prop("checked",!bool);
    var tr=$(el).parent().parent();
    bool ? tr.removeClass("selected") :  tr.addClass("selected");
}
var checkBoxChange=function(){
    $("#checkAll").click(function() {
        if ($(this).prop("checked") == true) {
            $("#grid input[name='checkOne']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[name='checkOne']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });

}


var datatable1 = function () {
    var dataTable = $("#grid").dataTable({
        processing: true, // 加载时提示
        serverSide: true, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        //paging:true,
        //bInfo:true,
        //bJQueryUI: true,
        //bPaginate: false,// 分页按钮
        //bFilter: true,// 搜索栏
        //iDisplayLength: 10,// 每页显示行数
        //bSort: false,// 排序
        bAutoWidth: true,
        // sScrollY: "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        fnDrawCallback: function() {
            //点击行内任意内容即选择行
            $("#grid tbody tr td").each(function(i,o){
                $(o).click(function(){
                    var checked = $(this).parent().find("input[name='checkOne']")[0]
                    if(checked.checked){
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                    }else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                    }
                });
            })
        },
        ajax: {
            url: "/sofn-sys-web/region/queryByKeyword",
            headers: {
                token: window.localStorage.getItem("token")
            },
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                if (json == null) {
                } else {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    /*for(var i=0;i<json.data.list.length;i++){
                     if(json.data.list[i].INSPECTION_TIME!=null&&json.data.list[i].INSPECTION_TIME!=""&&json.data.list[i].INSPECTION_TIME!=undefined){
                     json.data.list[i].INSPECTION_TIME=new Date(parseInt(json.data.list[i].INSPECTION_TIME)+1000*60*60*24).toISOString().slice(0,10);
                     }
                     }*/
                    return json.data.list;
                }

            },
            data: function (params) {
                params=params || {};
                if(params.parentId==null){
                    params.parentId=1;
                }
                params.keyWord=$("input[name='keyWord']").val();//查询关键字为编号、名称、简拼

                //return params;
            },
            type: "post"
        },
        columns: [
            {
                title: function (data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAll">';
                },
                data: "active",
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" name="checkOne" class="editor-active" onclick="checkRecode(this)">';
                    }
                    return data;
                },
                width:"5%",
                className: "dt-body-center"
            },
            {
                //data : "RN",
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                width:"5%",
            },
            {
                data: "regionCode",
                title: "行政编号",
                width:"10%"
                   },
            {
                data: "regionName",
                title: "名称",
                width:"15%"
            },
            {
                data: "regionPinyin",
                title: "简拼",
                width:"5%"
            },
            {
                data: "regionType",
                title: "级别",
                width:"5%"
            },
            /*,
            {
                data: "status",
                title: "有效",
                width:"10%",
                render : function(data, type, row) { // 模板化列显示内容
                    console.log(row.status);
                    if(row.status == "Y"){
                        return '<img src="assets/imgs/icon/sys-on.png" status="'+row.status+'" id="'+row.id+'"  class="xxx" onclick="changeEable(this)"/>';
                    }else{
                        return '<img src="assets/imgs/icon/sys-off.png" status="'+row.status+'" id="'+row.id+'"  class="xxx" onclick="changeEable(this)"/>';
                    }
                }
            } %/ ,
           /* {
                data: "delFlag",
                title: "有效"
            },*/
            {
                data: "isProvAdmCounty",
                title: "是否省管县",
                width:"10%",
                render: function (data, type, row) {
                    var text = '';
                    if (data == 'N') {
                        text = '<span class="label label-default" style="font-size: 12px">否</span>';
                    } else if (data == 'Y') {
                        text = '<span class="label label-primary" style="font-size: 12px">是</span>';
                    } else {
                        text = '<span class="label label-danger" style="font-size: 12px">无效值</span>';
                    }
                    return text;
                }
            },
            {
                data: "remark",
                title: "备注"
            },
            {
                data: "id",
                title: "id",
                visible:false
            },
            {
                data: "sortid",
                title: "sortid",
                visible:false
            },{
                data: "id",
                title: "操作",
                render: function (data, type, row) {
                    var idtag = '#modify' + data;
                    $(document).on("click", idtag, function(){
                        modifyRegion(row);
                    });
                    return settingTableOperation(data);
                },
                className: "text-center",
                width: "11%"
            }
        ],
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        sPaginationType: "full_numbers"
    })

    return dataTable;
};
var regionEasytree=function(dataTable){
    $("#regionEasyUi").tree({
        lines:true,

        url:"/sofn-sys-web/region/queryMenu?regionId=0&delFlag=Y",
        onBeforeExpand:function(node,param) {
            $('#regionEasyUi').tree('options').url = "/sofn-sys-web/region/queryMenu?&delFlag=Y&regionId=" + node.id;
        },
        onClick: function(node){
            /*$("#grid").dataTable().api().ajax.params().id=node.id;
            //$("#grid").dataTable().api().ajax.params().start=0;
            $("#grid").dataTable().api().ajax.params().draw=5;*/
            // console.log(node);
            var param = {
                "parentId": node.id,
                // "start": 0
            };
            window.regionId = node.id;
            window.regionText = node.text;
            $("#grid").dataTable().api().settings()[0].ajax.data = param;
            $("#grid").dataTable().api().ajax.reload();
        },
        onLoadSuccess:function(node){//隐藏根节点
           /* var nodeId=$('#easyUi').tree('getRoot').id;
            if(nodeId!=1) return;;
            $('#easyUi').tree('options').url = "/sofn-sys-web/region/queryMenu?regionId=" + nodeId;
            $('#easyUi').tree('reload');
            $("#"+$('#easyUi').tree('getRoot').domId).hide();*/
            //$("#"+$('#easyUi').tree('getRoot').domId).css("display","none")
        }

    })
}

// 状态开关
var changeEable = function(obj){
    var enable = obj.getAttribute("status");
    var id = obj.id;
    var imgObj = $('#'+obj.id);
    if(enable == "N"){
        imgObj.attr("src","assets/imgs/icon/sys-on.png");
        imgObj.attr("enable","Y");
    }else {
        imgObj.attr("src","assets/imgs/icon/sys-off.png");
        imgObj.attr("enable","N");
    }
    param={};
    param.id=id;
    param.status=(enable == "N" ? "Y" : "N");
    $.ajax({
        url: "/sofn-sys-web/region/update",
        //contentType : "multipart/form-data",
        data:{'id':id,'status':enable == "N" ? "Y" : "N"},
        type: "post"
    }).success(function () {
        var node = $('#regionEasyUi').tree('getSelected');
        var param = {
            "parentId": node ? node.id : 1,
            // "start": 0,
            keyWord:$("input[name='keyWord']").val()
        };
        $("#grid").dataTable().api().settings()[0].ajax.data = param;
        $("#grid").dataTable().api().ajax.reload();
        //alert("修改成功");
    }).error(function () {
        //alert("保存失败");
    });

}