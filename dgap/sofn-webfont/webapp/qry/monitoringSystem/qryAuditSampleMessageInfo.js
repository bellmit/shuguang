var myapp=angular.module("myapp",[]);
myapp.controller("qryAuditSampleMessageInfo",function($scope,$http,$state){

    var organTaskId=window.localStorage.getItem("organTaskId");//监测任务
    var token=window.localStorage.getItem("token");//监测机构名称
    /**
     * 分页展示数据
     */
    $(function() {

        //bootstrap表格
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            // fnDrawCallback: function() {
            //     //点击行内任意内容即选择行
            //     $("#grid tbody tr td").each(function(i,o){
            //         $(o).click(function(){
            //             var checked = $(this).parent().find("input[name='checkOne']")[0]
            //             if(checked.checked){
            //                 checked.checked = false;
            //                 $(this).parent().removeClass("selected");
            //             }else {
            //                 checked.checked = true;
            //                 $(this).parent().addClass("selected");
            //             }
            //         });
            //     })
            // },
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getAuditSamplePageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                error : function (msg) {
                    console.log(msg);
                   
                },
                data: function (params) {
                    params.organTaskId = organTaskId;
                    // if ($('#sampleOrgan option:selected').text() != "全部") {//抽样单位
                    //     params.sampleOrgan = $('#sampleOrgan option:selected').text();
                    // }
                    // params.sampleCode = $scope.sampleCode;    //抽样编码
                    // params.sampleName = $scope.sampleName;    //样品名称
                    // var sampleDate = $("input[name='sampleDate']").val();
                    // if (sampleDate != "") {
                    //     params.sampleDate = sampleDate + "-01-01" + " 00:00:00"
                    // }
                    // if ($('#taskName option:selected').text() != "全部") {
                    //     params.taskName = $('#taskName option:selected').text();
                    // }
                    // if ($('#producingAreaName option:selected').text() != "全部") {
                    //     params.producingAreaName = $('#producingAreaName option:selected').text();
                    // }
                },
                type: "post"
            },
            columns: [
                {
                    data: "ID",
                    checkbox: true,
                    title: '<input id="checkAll" type="checkbox" value="" onclick="checkAll(this)" />',
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (row.SAMPLE_REPORT == "2") {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '" disabled/>';
                        } else {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '"/>';
                        }

                    }
                },
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        //return '<input readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        if (data!=null){
                            return '<input style="overflow: auto; overflow-y:hidden" readonly="readonly" class="gui-input"  value="'+data+'" type="text"/>';
                        }else{
                            data = "";
                            return '<input style="overflow: auto; overflow-y:hidden" readonly="readonly" class="gui-input"  value="'+data+'" type="text"/>';
                        }
                    }
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    render: function (data) { // 模板化列显示内容
                        return '<a name="SAMPLE_CODE"   onclick="checkClick(\'' + data + '\')" >' + data + '</a>';
                    }
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位"
                },
                {
                    data: "SAMPLE_PLACE",
                    title: "抽样地点"
                    // render: function (data, type, row) { // 模板化列显示内容
                    //     if (row.CITY_CODE != undefined) {
                    //         return $.fn.Cityname(row.CITY_CODE)
                    //     } else {
                    //         return "";
                    //     }
                    // }
                },
                {
                    data: "PRODUCTIONS_TATUS",
                    title: "样品来源"
                },
                {
                    data: "SAMPLE_DATE",
                    title: "抽样日期"
                    // render: function (data, type, row) { // 模板化列显示内容
                    // return new Date(data).getFullYear() + "-" + (new Date(data).getMonth()+1) + "-" + (new Date(data).getDay()-1);
                    //  }
                }
            ],
        });
    });
})
