var myapp = angular.module("myapp", []);
myapp.controller("sysTemplateList", function($scope, $http, $state) {
    //查询
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 分页展示数据
     */
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-qry-web/testController/getPageInfo",
            dataSrc: function(json) {
                json.recordsTotal = json.page.recordsTotal; //总个数
                json.recordsFiltered = json.page.recordsTotal;
                json.pageNumber = json.page.pageNum; //页码
                json.pageSize = json.page.pageSize; //每页个数
                json.start = (json.page.pageNum - 1) * json.page.pageSize; //起始位置
                json.length = json.page.pageSize;
                return json.list;
            },
            data: function(params) {
                //分页条件查询，不实现，自己写
                params.templateName = $("input[name='templateName']").val();
                params.templateType = "全部";
            },
            type: "post"
        },
        columns: [
            {
                data: "templateName",
                title: "附件名称",
                width: "20%"
            },

            // {
            // 	data: "templateType",
            // 	title: "模板类型"
            // },
            {
                data: "templateFilename",
                title: "附件文件名",
                width: "15%"
            }, {
                data: "updateTime",
                title: "最后更新时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                },
                width: "10%"
            }, {
                data: "remark",
                title: "备注",
                width: "30%"
            }
        ]
    }).on( 'page.dt', function () {
      var table = $(this);
      table.find('thead').find(':checkbox').prop('checked', false);
    });

    /**
     * 获取复选框选中行
     * @returns {*}
     */
    $scope.getSelectRowsIds = function() {
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
})
window.exportData = function() {
    var form = $("<form>");
    form.attr('style', 'display:none');
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', "/sofn-qry-web/testController/export");
    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'name');
    input1.attr('value', $("input[name='templateName']").val());
    $('body').append(form);
    form.append(input1);
    form.submit();
}
