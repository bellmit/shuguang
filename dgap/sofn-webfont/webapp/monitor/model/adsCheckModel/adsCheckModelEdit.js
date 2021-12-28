var myapp=angular.module("myapp",[]);
myapp.controller("adsCheckModelEdit",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的id,modelName,industry
    var id = window.localStorage.getItem("id");
    var modelName = window.localStorage.getItem("modelName");
    var industry = window.localStorage.getItem("industry");
    var industryId = window.localStorage.getItem("industryId");

    $scope.IndustryType = new Array();
    window.loadA = function () {
        /**
         * 加载行业分类字典
         */
        $http({
            url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
            method: "post",
            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded"}
        }).success(function (data) {
            $scope.IndustryType = data.data;
            $scope.selectedIndustry = industry;
        }).error( function (data) {
            $.jBox.tip("失败");
        });
    };

    //二级联动改变值和获取值
    $(document).ready(function(){
        $('#industryU').change(function(){
            var s = $('#industryU option:selected').val();
            $($scope.IndustryType).each(function (idx) {
                var a = $(this)[0];
                if(a.dictName == s){
                    industryId = a.dictCode;
                    return false;
                }
            });
        });
    });

    $(function () {
       loadA();
       $("#idU").val(id);
       $("#modelNameU").val(modelName);
    });

    //修改检测模型按钮事件
    $scope.updateAdsModel = function(){
        if (!$("#updateCheckModel").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var id = $("#idU").val();
        var modelName = $("#modelNameU").val();
        var industry = $("#industryU option:selected").val();
        var industryId = industryId;
        var length = modelName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if(length > 60){
            jBox.tip("模型名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
            return false;
        }
        $.ajax({
            url:"/sofn-ads-web/adsCheckModel/updateAdsCheckModel",
            type:"post",
            dataType:"json",
            data:{"id":id,"modelName":modelName,"industry":industry,"industryId":industryId,"token":token},
            success:function(data){
                if(data.httpCode == 200){
                    jBox.tip("修改成功", 'info');
                }else if(data.httpCode == 400){
                    jBox.tip("没有登录", 'info');
                }else if(data.httpCode == 409){
                    jBox.tip("修改失败,该名称已被使用", 'info');
                }else{
                    jBox.tip("修改失败", 'info');
                }
            }
        });
        $http({
            url:"/sofn-ads-web/adsCheckModel/updateAdsCheckModel",
            data:{"id":id,"modelName":modelName,"industry":industry,"industryId":industryId,"token":token},
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(data){
            if(data.httpCode == 200){
                jBox.tip("修改成功", 'info');
                $("#grid").dataTable().api().ajax.reload();

            }else if(data.httpCode == 409){
                jBox.tip("修改失败,该名称已被使用", 'info');
            }else{
                jBox.tip("修改失败", 'info');
            }
        }).error(function(){
            jBox.tip("修改失败", 'info');
        })
    };

    $scope.back = function(){
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModel");
    };

    //跳转到编辑种类的页面
    $scope.editType = function () {
        window.localStorage.setItem("modelId",id);
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModelType");
    };

    //跳转到编辑抽样环节的页面
    $scope.editSampleLink = function () {
        window.localStorage.setItem("modelId",id);
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModelSampleLink");
    };

    //跳转到编辑检测对象的页面
    $scope.editCheckObject = function () {
        window.localStorage.setItem("modelId",id);
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModelCheckObject");
    };

    //跳转到编辑判定标准的页面
    $scope.editJudgeStandard = function () {
        window.localStorage.setItem("modelId",id);
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModelJudgeStandard");
    };

    $("#checkModel").click(function(){
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModel");
    });

    $(document).ready(function() {
        $('#updateCheckModel').bootstrapValidator();
    });
});