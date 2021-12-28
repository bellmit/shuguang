var myapp=angular.module("myapp",[]);
myapp.controller("infoPage",function($scope,$http,$state){

    $scope.monitorClass=window.localStorage.getItem("monitorClass");//监测任务

    var checkInfoId = window.localStorage.getItem("checkInfoId");

    var finishTime = window.localStorage.getItem("finishTime");

    var token = window.localStorage.getItem("token");

    /**
     * 分页展示数据
     */
    $(function() {

        $("#productList").dataTable({
            processing : true, // 加载时提示
            serverSide : false, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            paging:false,
            bAutoWidth:true,
            // sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getChild",
                dataSrc:function(json){
                    return json.data;
                },
                data : function(params) {
                    params.id = checkInfoId;
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data : "checkProject",
                    title : "检测项目"
                },
                {
                    data : "checkNum",
                    title : "检测值",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(0 == data){
                            return '<a id="'+meta.row+'" value="'+"未检出"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">未检出</a>';
                        }else{
                            return '<a id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                        }
                    },
                },
                {
                    data : "judgeNum",
                    title : "判定值"
                },
                {
                    data : "result",
                    title : "抽检结果",
                    render : function(data, type, row) {
                        if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    }
                },
                {
                    title : "LOD（位）",
                    data : function(data, type, row, meta) {
                        return ""
                    }
                },
                {
                    title : "：mgkg LOO",
                    data : function(data, type, row, meta) {
                        return ""
                    }
                }
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
        });

        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/getCheckInfo",
            type: "post",
            dataType: "json",
            data: {"checkInfoId":checkInfoId},
            success: function (data) {
                var date = new Date().format("yyyy-MM-dd");
                var datas = data.data;
                $("#yangp").append(datas.sampleName);
                $("#bianh").append(datas.sampleCode);
                $("#tongz").append(date);
                $("#wanc").append(finishTime);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/getMonitorTaskByCheckInfoId",
            type: "post",
            dataType: "json",
            data: {"checkInfoId":checkInfoId},
            success: function (data) {
                var datas = data.data;
                $("#name").append(datas.taskName);
                $("#year").append(datas.year);
                $("#danwei").append(datas.releaseUnit);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/getOrganTaskByCheckInfoId",
            type: "post",
            dataType: "json",
            data: {"checkInfoId":checkInfoId},
            success: function (data) {
                var datas = data.data;
                $("#detectionOrgan").append(datas.detectionOrgan);
                $("#numbers").append(datas.numbers);
                $("#danwei").append(datas.releaseUnit);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

    });

    //打印设置
   /* $("#printArea").click(function(){
        $("#dayin").printArea();
        // var headstr = "<html><head><title></title></head><body>";
        // var footstr = "</body>";
        // var newstr = document.getElementById("dayin").innerHTML;
        // var oldstr = document.body.innerHTML;
        // document.body.innerHTML = headstr+newstr+footstr;
        // window.print();
        // document.body.innerHTML = oldstr;
        location.reload()
        // return false;
    });*/

    //打印设置
    $("#printArea").click(function () {
        $("#dayin").printArea();
    });

    /**
     * 下载检测任务单
     */
    window.downFile = function(data){
    // $scope.downFile = function () {
        var submit = function (v, h, f) {
            var innerText = document.getElementById("dayin").innerHTML;
            var adsCheckInfo ={};
            adsCheckInfo.innerText = innerText;
            if (v == true)

                // $.ajax({
                //     url:"/sofn-ads-web/adsCheckInfo/writeWordFile",
                //     type: "post",
                //     dataType: "json",
                //     data : {"innerText":innerText},
                //     // responseType: 'arraybuffer',
                //     // headers: { "Content-Type": "application/x-www-form-urlencoded" },
                //     success: function (data) {
                //         alert("1");
                //         var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                //         if(blob.size > 0){
                //             var objectUrl = URL.createObjectURL(blob);
                //             var a = document.createElement('a');
                //             document.body.appendChild(a);
                //             a.setAttribute('style', 'display:none');
                //             a.setAttribute('href', objectUrl);
                //             a.setAttribute('download', '检测任务单.doc');
                //             a.click();
                //         }else{
                //             jBox.tip("下载失败！","info")
                //         }
                //     },
                //     fail: function (data) {
                //         alert("2");
                //         $.jBox.tip("失败");
                //     }
                // });

                $http({
                    url:"/sofn-ads-web/adsCheckInfo/writeWordFile",
                    method:"post",
                    // dataType:"json",
                    data : adsCheckInfo,
                    responseType: 'arraybuffer',
                    // headers: { "Content-Type": "application/x-www-form-urlencoded" }
                    headers: {
                        post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                    }
                }).success(function(data){
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if(blob.size > 0){
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '检测任务单.doc');
                        a.click();
                    }else{
                        jBox.tip("下载失败！","info")
                    }
                });

            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }

})


