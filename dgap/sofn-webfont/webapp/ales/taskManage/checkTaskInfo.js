var myapp=angular.module("myapp",[]);
myapp.controller("checkTaskInfo",function($scope,$http,$state){
    //操作动作
    $("span[name='OprTitle']").html("新增");
    //Modal初始化
    $('#bModal').modal({
        keyboard: true,
        show:false
    });
    $scope.bModalOprIndex = 'add';//模态框操作标示
    $scope.bModalOprIndexTr = null;//记录暂存
    //修改操作初始化
    $scope.XX_XX_X = window.localStorage.getItem("asms_Check_SjTask");
    $scope.asms_Check_SjTask = $scope.XX_XX_X;//修改任务id
    if ($scope.XX_XX_X != null || $scope.XX_XX_X != undefined){
        //操作动作
        $("span[name='OprTitle']").html("修改");
        //主信息获取
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/checkTask/getTaskById",
            method:"post",
            data:{id:$scope.asms_Check_SjTask}
        }).success(function(obj){
            $("input[name='taskName']").val(obj.data.taskName);
            $("input[name='taskYear']").val(obj.data.taskYear);
            //区域
            var taskAreaId = obj.data.taskAreaId;
            var taskAreaIds = taskAreaId.substring(0,3);
            $("select[name='taskAreaIds']").val(taskAreaIds);
            var taskAreaIdss = taskAreaId.substring(3,6);
            $("select[name='taskAreaIdss']").val(taskAreaIdss);
            var taskAreaIdx = taskAreaId.substring(6,10);
            $("select[name='taskAreaIdx']").val(taskAreaIdx);
            //
            $("input[name='taskBeginTime']").val(new Date(parseInt(obj.data.taskBeginTime)).toISOString().slice(0,10));
            $("input[name='taskEndTime']").val(new Date(parseInt(obj.data.taskEndTime)).toISOString().slice(0,10));
            $("select[name='taskIsSeparate']").val(obj.data.taskIsSeparate);
            $("input[name='taskReleaseUnit']").val(obj.data.taskReleaseUnit);
            $("input[name='taskSampleDeadline']").val(new Date(parseInt(obj.data.taskSampleDeadline)).toISOString().slice(0,10));
        }).error(function(){
        });
        //关联信息获取
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/checkTask/getObjById",
            method:"post",
            data:{id:$scope.asms_Check_SjTask}
        }).success(function(obj){
            var datas = obj.data;
            if (datas.length > 0){
                for (var i = 1; i <=datas.length; i++){
                    var td = datas[i-1];
                    var Row = "<tr><td><input type=\"checkbox\" name=\"ckb\"></td> <td><input  type=\"text\"  name=\"productType\" class=\"form-control\" readonly=\"readonly\" value=\""+td.PRODUCTTYPE+"\"></td> <td><input  type=\"text\" name=\"productName\" class=\"form-control\"readonly=\"readonly\" value=\""+td.PRODUCTNAME+"\"></td> <td><input  type=\"text\" name=\"monitorNum\" class=\"form-control\"readonly=\"readonly\" value=\""+td.MONITORNUM+"\"></td> <td><input  type=\"text\" name=\"sampleUnitId\" class=\"form-control\" readonly=\"readonly\" value=\""+td.SAMPLEUNITID+"\"></td> <td><input  type=\"text\" name=\"detectionUnitId\" class=\"form-control\"readonly=\"readonly\" value=\""+td.DETECTIONUNITID+"\"></td> <td><input  type=\"text\" name=\"taskBeginTime\" class=\"form-control\"readonly=\"readonly\"  value=\""+new Date(parseInt(td.TASKBEGINTIME)).toISOString().slice(0,10)+"\" \"></td><td><input  type=\"text\" name=\"taskEndTime\" class=\"form-control\" readonly=\"readonly\" value=\""+new Date(parseInt(td.TASKENDTIME)).toISOString().slice(0,10)+"\"\"></td><td><input  type=\"text\" name=\"detectionStandard\" class=\"form-control\" readonly=\"readonly\"  value=\""+td.DETECTIONSTANDARD+"\"></td><td><input  type=\"text\" name=\"judgeStandard\" class=\"form-control\" readonly=\"readonly\" value=\""+td.JUDGESTANDARD+"\"></td></tr>";
                    if($("#Fjtable tbody").children().length==0){
                        $("#Fjtable tbody").prepend(Row);
                    }else {
                        $("#Fjtable tbody tr:last").after(Row);
                    }
                }
            }
        }).error(function(){
        });
    }
    //新增
    $scope.addLine=function(){
        $scope.bModalOprIndex = 'add';
        $("#modalTitle").html("添加抽查对象");
        $("#bModal").modal('show');
    };

    //新增到页面
    $scope.addObjLine=function(){
        var productType = $("#bModal select[name='productType").val();
        var productName = $("#bModal select[name='productName']").val();
        var taskBeginTime = $("#bModal input[name='taskBeginTime']").val();
        var taskEndTime = $("#bModal input[name='taskEndTime']").val();
        var sampleUnitId = $("#bModal select[name='sampleUnitId']").val();
        var detectionUnitId = $("#bModal select[name='detectionUnitId']").val();
        var detectionStandard = $("#bModal select[name='detectionStandard']").val();
        var judgeStandard = $("#bModal select[name='judgeStandard']").val();
        var monitorNum = $("#bModal input[name='monitorNum']").val();
        var Row = "<tr><td><input type=\"checkbox\" name=\"ckb\"></td> <td><input  type=\"text\"  name=\"productType\" class=\"form-control\" readonly=\"readonly\" value=\""+productType+"\"></td> <td><input  type=\"text\" name=\"productName\" class=\"form-control\"readonly=\"readonly\" value=\""+productName+"\"></td> <td><input  type=\"text\" name=\"monitorNum\" class=\"form-control\"readonly=\"readonly\" value=\""+monitorNum+"\"></td> <td><input  type=\"text\" name=\"sampleUnitId\" class=\"form-control\" readonly=\"readonly\" value=\""+sampleUnitId+"\"></td> <td><input  type=\"text\" name=\"detectionUnitId\" class=\"form-control\"readonly=\"readonly\" value=\""+detectionUnitId+"\"></td> <td><input  type=\"text\" name=\"taskBeginTime\" class=\"form-control\"readonly=\"readonly\"  value=\""+taskBeginTime+"\"\"></td><td><input  type=\"text\" name=\"taskEndTime\" class=\"form-control\" readonly=\"readonly\" value=\""+taskEndTime+"\"\"></td><td><input  type=\"text\" name=\"detectionStandard\" class=\"form-control\" readonly=\"readonly\"  value=\""+detectionStandard+"\"></td><td><input  type=\"text\" name=\"judgeStandard\" class=\"form-control\" readonly=\"readonly\" value=\""+judgeStandard+"\"></td></tr>";
        if ($scope.bModalOprIndex == 'add'){
            if($("#Fjtable tbody").children().length==0){
                $("#Fjtable tbody").prepend(Row);
            }else {
                $("#Fjtable tbody tr:last").after(Row);
            }
        }
        if ($scope.bModalOprIndex == 'upd'){
            var o = $scope.bModalOprIndexTr;
            $(o).replaceWith(Row);
            $scope.bModalOprIndex = 'add';
            $scope.cleanBModal();
        }
        $("#bModal").modal('hide');
    };

    //modal清空
    $scope.cleanBModal=function(){
        $("#bModal input[name='recheckSampleName").val("");//样品名称
        $("#bModal input[name='recheckSampleCode']").val("");//样品编码
    };

    //删除行
    $scope.delLine=function(){
        var ckbs=$("input[name=\'ckb\']:checked");
        if(ckbs.size()==0) {
            $.jBox.tip("请至少选中一行数据！");return;
        }
        if(ckbs.size()!=0){
            $.jBox.confirm("是否删除选中行？", "等待服务器响应", function(v){
                if(v==true){
                    ckbs.each(function(){
                        $(this).parent().parent().remove();
                    });
                }
                return true;
            }, { buttons: { '确认': true, '取消': false} });
        }
    };

    //修改行
    $scope.updLine=function(){
        $scope.bModalOprIndex = 'upd';
        $("#modalTitle").html("修改对象");
        var ckbs=$("input[name=\'ckb\']:checked");
        $scope.bModalOprIndexTr = $("input[name=\'ckb\']:checked").parent().parent();
        if(ckbs.size()==0 || ckbs.size()>1) {
            $.jBox.tip("请选中一行数据！");return;
        }
        var otr = $("input[name=\'ckb\']:checked").parent().parent().find("input[type='text']");
        $("#bModal select[name='productType").val(otr[0].value);
        $("#bModal select[name='productName']").val(otr[1].value);
        $("#bModal input[name='monitorNum']").val(otr[2].value);
        $("#bModal select[name='sampleUnitId']").val($.trim(otr[3].value));
        $("#bModal select[name='detectionUnitId']").val($.trim(otr[4].value));
        $("#bModal input[name='taskBeginTime']").val(otr[5].value);
        $("#bModal input[name='taskEndTime']").val(otr[6].value);
        $("#bModal select[name='detectionStandard']").val(otr[7].value);
        $("#bModal select[name='judgeStandard']").val(otr[8].value);
        $("#bModal").modal('show');
    };

    $("#Fjtable tbody tr td").each(function(i,o){
        $(o).click(function(){
            var checked = $(this).parent().find("input[type='checkbox']")[0]
            if(checked.checked){
                checked.checked = false;
                $(this).parent().removeClass("selected");
            }else {
                checked.checked = true;
                $(this).parent().addClass("selected");
            }
        });
    })

    $scope.infoSave=function(){
        var Objects = $scope.getTableData("Fjtable");//对象信息信息
        $scope.param = {
            id:$scope.asms_Check_SjTask,
            taskName:$("input[name='taskName']").val(),
            taskYear : $("input[name='taskYear']").val(),
            taskAreaId:$("select[name='taskAreaIds']").val()+$("select[name='taskAreaIdss']").val()+$("select[name='taskAreaIdx']").val(),
            taskBeginTime : new Date(($("input[name='taskBeginTime']").val()+" 00:00:00").replace(/-/g,"/")),
            taskEndTime : new Date(($("input[name='taskEndTime']").val()+" 00:00:00").replace(/-/g,"/")),
            taskIsSeparate : $("select[name='taskIsSeparate']").val(),
            taskReleaseUnit :$("input[name='taskReleaseUnit']").val(),
            taskSampleDeadline : new Date(($("input[name='taskSampleDeadline']").val()+" 00:00:00").replace(/-/g,"/")),
            Objects : Objects
        };
        if ($scope.asms_Check_SjTask != null || $scope.asms_Check_SjTask != undefined){
            $("button").attr("disabled",true);
            $.ajax({
                url:"/sofn-asms-web/checkTask/update",
                type:"post",
                dataType:"json",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data:$scope.param,
                success:function(data){
                    $("button").attr("disabled",false);
                    $.jBox.tip("成功！");
                },
                fail: function (data) {
                    $("button").attr("disabled",false);
                    $.jBox.tip("失败");
                }
            });
        }else {
            $("button").attr("disabled",true);
            $.ajax({
                url:"/sofn-asms-web/checkTask/add",
                type:"post",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                dataType:"json",
                data:$scope.param,
                success:function(data){
                    $("button").attr("disabled",false);
                    $.jBox.tip("成功！");
                },
                fail: function (data) {
                    $("button").attr("disabled",false);
                    $.jBox.tip("失败");
                }
            });
        }
    };

    $("#allCkb").click(function(){
        if ($(this).prop("checked") == true) {
            $("#Fjtable input[type='checkbox']").prop("checked", true);
            $("#Fjtable tbody tr").addClass("selected");
        } else {
            $("#Fjtable input[type='checkbox']").prop("checked", false);
            $("#Fjtable tbody tr").removeClass("selected");
        }
    });

    $scope.getTableData=function(tid){
        var t_data = "";
        var f = -1;
        var td_data_temp = "";
        //tr
        $("#" + tid).find("tr").each(function(i) {
            if ($("#" + tid).find("tr:eq(" + i + ")").find("td").length != 0) {
                //td
                $("#" + tid).find("tr:eq(" + i + ")").find("td").each(function(j) {
                    if ($(this).css("display") != "none") {
                        if ($(this).find("input[type='text']").length == 0) {

                            td_data_temp = ($(this).text() == "" ? " " : $(this).text());
                        } else {
                            td_data_temp = ($(this).find("input[type='text']:eq(0)").val() == "" ? " " : $(this).find("input[type='text']:eq(0)").val());
                        }

                        if (td_data_temp.toString().indexOf("全选") != -1) {
                            f = j;
                        } else {
                            if (f != j)
                                t_data += td_data_temp + "TableTD";
                        }
                    }
                });
            } else {
                //th
                $("#" + tid).find("tr:eq(" + i + ")").find("th").each(function(j) {
                    if ($(this).css("display") != "none") {
                        if ($(this).find("input[type='text']").length == 0) {

                            td_data_temp = ($(this).text() == "" ? " " : $(this).text());
                        } else {
                            td_data_temp = ($(this).find("input[type='text']:eq(0)").val() == "" ? " " : $(this).find("input[type='text']:eq(0)").val());
                        }

                        if (td_data_temp.toString().indexOf("全选") != -1) {
                            f = j;
                        } else {
                            if (f != j)
                                t_data += td_data_temp + "TableTD";
                        }
                    }
                });
            }
            t_data += "TableTR";
        });
        return t_data;
    };

    $scope.cleanCookie=function(){
        window.localStorage.removeItem("asms_Check_SjTask");
    };

    //内容部分位置
    if($(window).width()>1349){
        $(".Sup_contain_section_contain").css("margin-left",($(".Sup_contain_section").width()-$(".Sup_contain_section_contain").width())/2+"px");

    }
});
