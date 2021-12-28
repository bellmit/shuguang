var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("asmsPersonalCenterAdmin",function($scope,$http,$state){
$scope.callBack=function(){
    $state.go(window.localStorage.getItem("historyUrl"))

}
    /** 第一部分，初始化时加载的内容 **/
    $("#changePwdForm").bootstrapValidator();//初始化验证，必须有
    $("#subjSvCancelForm").bootstrapValidator();//初始化验证，必须有
    $("#subjSvChangeForm").bootstrapValidator();//初始化验证，必须有
    //获取用户信息
    $scope.user = {};
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.currentOrg=data.user.organization;
        if($scope.user.userType=="SUPERADMIN"){
            $scope.user.userType="超级管理员";
        }else if($scope.user.userType=="SYSADMIN"){
            $scope.user.userType="用户管理员";
        }else if($scope.user.userType=="SUBJADMIN"){
            $scope.user.userType="主体管理员";
        }else if($scope.user.userType=="NORMAL"){
            $scope.user.userType="用户";
        }
    }).error(function (data) {
        
    });

    $scope.subjSvChange={};
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSvByUser",
        method:"post"
    }).success(function(data){
        $scope.subjSv=data.subjSv;
        //请求机构数据
        $scope.svLevelData=$scope.subjSv.svLevel;
        if($scope.subjSv.svLevel=="0"){
            $scope.subjSv.svLevel="部级";
        }else if($scope.subjSv.svLevel=="1"){
            $scope.subjSv.svLevel="省级";
        }else if($scope.subjSv.svLevel=="2"){
            $scope.subjSv.svLevel="市级";
        }else if($scope.subjSv.svLevel=="3"){
            $scope.subjSv.svLevel="县级";
        }
        window.localStorage.setItem("subjSvId",data.subjSv.id);
        $scope.subjSvChange=data.subjSvChange;
        //$scope.subjSv.svAreaId = $.fn.Cityname($scope.subjSv.svAreaId);
        //初始化区域下拉框初始选项
        $.fn.mycity("sheng","shi","xian",$scope.subjSvChange.svAreaId);
    }).error(function (data) {
        
    });

    //获取机构级别数据字典
    $scope.orgLevel=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"1",
        method:"post"
    }).success(function(data) {
        $scope.orgLevel=JSON.parse(data.data);
        $("select[name='svLevel']").attr("disabled","disabled");
    }).error(function (data) {
        
    });
    //获取机构类别数据字典
    $scope.orgType=[];
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/sysDict/getSysDictByCode",
        data:"0",
        method:"post"
    }).success(function(data) {
        $scope.orgType=JSON.parse(data.data);
        for(var i=0;i<$scope.orgType.length;i++){
            if($scope.orgType[i].dictName=="监管机构"){
                $scope.subjSvChange.svTypeId=$scope.orgType[i].id;
                $("select[name='svType']").attr("disabled","disabled");
            }
        }
    }).error(function (data) {
        
    });

    //获取行业数据字典
    $scope.industryType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/sysDict/getSysDictByCode",
        data: "2",
        method: "post"
    }).success(function (data) {
        $scope.industryType = JSON.parse(data.data);
    }).error(function (data) {
        
    });


    //bootstrap表格
    $("#grid").dataTable({
        ajax : {
            headers:{
                token:window.localStorage.getItem("token")
            },
            url : "/sofn-asms-web/subjSupervise/getChangeListBySvId",
            dataSrc:function(json){
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                for(var i=0;i<json.list.length;i++){
                    if(json.list[i].APPLY_TIME!=null&&json.list[i].APPLY_TIME!=""&&json.list[i].APPLY_TIME!=undefined){
                        json.list[i].APPLY_TIME=new Date(parseInt(json.list[i].APPLY_TIME)+28800000).toISOString().slice(0,10);
                    }
                }
                return json.list;
            },
            type:"post"
        },
        columns : [
            {
                title : "序号",
                data : function(data, type, row, meta) {
                    return meta.row + 1
                },
                width:"4%"
            },
            {
                data : "CHANGE_CONTENT",
                title : "变更内容",
                width:"50%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>38){
                        return "<a id='CHANGE_CONTENT"+row.ID+"' onmouseover=showPopContent('CHANGE_CONTENT"+row.ID+"','"+data+"')>"+data.substring(0,38)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "APPLY_REASON",
                title : "变更原因",
                width:"20%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>16){
                        return "<a id='APPLY_REASON"+row.ID+"' onmouseover=showPopContent('APPLY_REASON"+row.ID+"','"+data+"')>"+data.substring(0,16)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "APPLY_TIME",
                title : "申请时间",
                width:"10%"
            },
            //{
            //    data : "APPLY_USER_ID",
            //    title : "申请人"
            //},
            {
                data : "AUDIT_STATE",
                title : "审核状态",
                width:"10%",
                render:function(data,type,row){
                    if(data!=null&&data!=""&&data!=undefined){
                        if(data=="2"){
                            return "未通过"
                        }else if(data=="1"){
                            return "已通过";
                        }else if(data=="0"){
                            return "待审核";
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
                    return '<a style="cursor: pointer" class="Check-report color"  onclick="viewSubjSvChange(\''+data+'\')">查看</a>';//转义传值
                }
            },
        ],
    });

    /** 第二部分，主动调用才执行的部分 **/
    //显示密码修改模态框
    $scope.showPwdModal=function() {
        $("#pwdModal").modal("show");
        $("#changePwdForm").data("bootstrapValidator").resetForm(true);
    }
    //获取焦点清除验证
    $(".oldPwd").focus(function () {
        $(".oldPwd").css("border-color","#2b542c");
        $(".dirVilidator").css("display","none");
    })

    //修改密码
    $scope.changePwd=function() {
        //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
        if($("input[name='newPwd']").val()!=$("input[name='newPwdC']").val()){
            $("#changePwdForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#changePwdForm").bootstrapValidator();//初始化验证，与上面对应
            $("#changePwdForm").data('bootstrapValidator').validate();
            return false;
        }
        if (!$("#changePwdForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $("button").attr("disabled",true);
        $http({
            url: "/sofn-asms-web/getPublicKey",
            method: "post",
            dataType: "json"
        }).success(function(json){
            //需要加密的业务数据
            var data = {
                account: $scope.user.account,
                oldPwd: $scope.oldPwd,
                newPwd: $scope.newPwd
            };
            //生成des随机key,此key用作提交的业务数据加密和服务器返回数据的解密
            var desKey = Math.uuid().replace(/-/g, '');
            //调用加密
            var dataStr = encodeDES(data, desKey);
            //调用RSA加密
            var encodeKey = encodeRSA(desKey, json.publicKey);
            $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url:"/sofn-asms-web/subjSupervise/changePwd",
                data:{ciphertext: dataStr,desKey: encodeKey},
                method:"post"
            }).success(function(data){
                $("button").attr("disabled",false);
                if(data.httpCode=="200") {
                    $http({
                        url: "/sofn-asms-web/logout",
                        method: "post",
                        dataType: "json",
                        headers: {
                            token: window.localStorage.getItem("token"),
                            type: "pc"
                        }
                    }).success(function(json){
                        if(json.httpCode!=200){
                            return;
                        }
                        $(".dirVilidator").css("display","none");
                        $(".oldPwd").css("border-color","#2b542c");
                        jBox.tip("修改成功", 'info');
                        $("#pwdModal").modal("hide");
                        localStorage.clear();
                        $.cookie("account", "", { expires: -1 }); // 清除cookie
                        $.cookie("password", "", { expires: -1 }); // 清除 cookie
                        setTimeout(function () {
                            $state.go("login");
                        },200);
                    }).error(function () {
                        jBox.tip("修改失败", 'info');
                    });
                }else if(data.httpCode=="405"){
                    $(".dirVilidator").css("display","block");
                    $(".oldPwd").css("border-color","#843534");
                    jBox.tip("旧密码不正确", 'info');
                }else{
                    jBox.tip("修改失败", 'info');
                }
            }).error(function(){
                $("button").attr("disabled",false);
            });
        }).error(function(){
            $("button").attr("disabled",false);
        });
    }

    //弹出修改模态框
    $("#modifyInfo").click(function(){
        $scope.$apply();
        if($scope.subjSv.status=="1"){
            jBox.tip("还有变更待审核！", 'info');
            return false;
        }else if($scope.subjSv.status=="2"){
            jBox.tip("还有注销待审核！", 'info');
            return false;
        }else if($scope.subjSv.status=="3"){
            jBox.tip("还有撤销待审核！", 'info');
            return false;
        }else{
            //字典行业类型
            var arr=$scope.industryType;
            //机构的所属行业
            var svIndustryTypeId=$scope.subjSv.industryId;
            if(svIndustryTypeId!=null &&svIndustryTypeId!='' &&svIndustryTypeId!=undefined){
                svIndustryTypeId=svIndustryTypeId.split(",");
                for(var i=0;i<arr.length;i++){
                    for(var j=0;j<svIndustryTypeId.length;j++){
                        if(arr[i].id==svIndustryTypeId[j]) {
                            $("input[value='"+svIndustryTypeId[j]+"']").attr("checked",true);
                        }
                    }
                }
                //$("input[type='checkbox']").attr("disabled","disabled");
            }
            // $("input[type='checkbox']").attr("disabled","disabled");//机构行业不允许修改
            $("#subjSvChangeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#subjSvChangeModal").modal("show");
            $("#subjSvChangeForm").bootstrapValidator();//初始化验证，与上面对应
        }
    });

    //弹出注销模态框
    $("#modifyInfoCancle").click(function(){
        if($scope.subjSv.svLevel=="部级"){
            jBox.tip("部级机构不能注销！", 'info');
            return false;
        }
        if($scope.subjSv.status=="1"){
            jBox.tip("还有变更待审核！", 'info');
            return false;
        }else if($scope.subjSv.status=="2"){
            jBox.tip("还有注销待审核！", 'info');
            return false;
        }else if($scope.subjSv.status=="3"){
            jBox.tip("还有撤销待审核！", 'info');
            return false;
        }else{
            $("#subjSvCancelForm").data("bootstrapValidator").resetForm(true);
            $("#subjSvCancelModal").modal("show");
        }
    });

    //保存变更申请
    $scope.applySubjSvChange=function(){
        if (!$("#subjSvChangeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        if (!$scope.checkIndustry()) {
            return;
        }
        if($scope.subjSvChange.svLevel=="0"){
            $scope.subjSvChange.svLevel="部级";
        }else if($scope.subjSvChange.svLevel=="1"){
            $scope.subjSvChange.svLevel="省级";
        }else if($scope.subjSvChange.svLevel=="2"){
            $scope.subjSvChange.svLevel="市级";
        }else if($scope.subjSvChange.svLevel=="3"){
            $scope.subjSvChange.svLevel="县级";
        }
        //字典行业类型
        var arr=$scope.industryType;
        //所属行业
        var entityIndustryId = $("input[name='entityIndustryId']:checked");
        var arr01 = [];
        var arr02 = [];
        var arr03 = [];
        for(var i=0;i<entityIndustryId.length;i++){
            var aa=$(entityIndustryId[i]).val();
            arr01.push($(entityIndustryId[i]).val());
            for(var j=0;j<arr.length;j++){
                if(arr[j].id==aa){
                    arr02.push(arr[j].dictName);
                    arr03.push(arr[j].dictValue)
                }
            }
        }
        var industryId=arr01.toString();
        var industryName=arr02.toString();
        var industryValue=arr03.toString();
        //行业id
        $scope.subjSvChange.industryId=industryId;
        //行业名称
        $scope.subjSvChange.industryName=industryName;
        //行业字典值
        $scope.subjSvChange.industryValue=industryValue;
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city + "00";
        } else if (province != null && province != undefined && province != "") {
            areaId = province + "0000";
        }
        $scope.subjSvChange.svAreaId = areaId;
        $scope.subjSvChange.svArea = $.fn.Cityname(areaId);
        var before = JSON.stringify($scope.subjSvChange);
        $scope.subjSv.applyReason = $scope.subjSvChange.applyReason;//赋值后进行比较
        var after = JSON.stringify($scope.subjSv);
        if(before==after){
            $.jBox.tip("请至少修改一处！");
            return false;
        }
        $.jBox.confirm("确认进行注册变更申请？", "提交申请成功......", function(v){
            if(v==true) {
                $scope.subjSvChange.svType = $("select[name='svType'] option:selected").text();
                // $scope.subjSvChange.svLevel = $("select[name='svLevel'] option:selected").attr("dictVal");
                $scope.subjSvChange.svLevel = $scope.svLevelData;
                $http({
                    url: "/sofn-asms-web/subjSupervise/addSubjSvChange",
                    data: $scope.subjSvChange,
                    method: "post",
                    headers: {
                        token: window.localStorage.getItem("token")
                    }
                }).success(function (data) {
                    if (data.httpCode === 200) {
                        $("#web_app_model_userOprXz").modal("hide");
                        jBox.tip("申请成功", 'info');
                        $scope.subjSv.status = "1";
                        $("#subjSvChangeModal").modal("hide");
                        $("#grid").dataTable().api().ajax.reload();
                    }else if(data.httpCode === 409){
                        jBox.tip("机构名称、机构代码、所属区域,机构级别和所属行业有一样已存在！", 'info');
                    }else if (data.httpCode === 400) {
                        jBox.tip(data.msg);
                    }else if(data.httpCode === 500){
                        jBox.tip("申请失败", 'info');
                    }else {

                    }
                }).error(function () {
                })
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //取消变更申请方法
    $scope.cancelSubjSvChange=function(){
        $.jBox.confirm("取消并退出注册变更申请？","", function(v){
            if(v==true){
                $("#subjSvChangeModal").modal("hide");
                return true;
                jBox.tip("取消成功", 'info');
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //取消注销申请方法
    $scope.cancelSubjSvCancel=function(){
        $.jBox.confirm("取消并退出注册注销申请？", "取消成功", function(v){
            if(v==true){
                $("#subjSvCancelModal").modal("hide");
                jBox.tip("取消成功", 'info');
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //申请机构注销
    $scope.applySubjSvCancel=function(){
        if (!$("#subjSvCancelForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $.jBox.confirm("确认进行注册注销申请？", "申请成功", function(v){
            if(v==true) {
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-asms-web/subjSupervise/addSubjSvCancel",
                    method: "post",
                    data: {svId: $scope.ID, applyReason: $scope.applyReason}
                }).success(function (data) {
                    $scope.subjSv.status = "2";
                    $("#subjSvCancelModal").modal("hide");
                }).error(function () {
                });
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //查看详情页面
    window.viewSubjSvChange=function(id){
        window.localStorage.setItem("subjSvChangeId",id);
        $state.go("account/checkSubjEntChange");
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

    //打开帮助中心
    $scope.gotoHelpCenter = function () {
        if (!$scope.currentOrg||!$scope.currentOrg.orgType) {
            return;
        }
        var webappSysname = $scope.currentOrg.orgType.toLowerCase();
        $.ajax({
            url: "/sofn-sys-web/document/queryList",
            type: "post",
            dataType: "json",
            data: {
                token: window.localStorage.getItem("token"),
                enableFlag: 'Y',
                applyTo:webappSysname,
                start: 0,
                length: 1000000
            },
            success: function (data) {
                var result = data.data.list;
                var html = "";
                for (var i = 0; i < result.length; i++) {
                    html += '<li class="document"><div></div>';
                    html += '<a onclick="goto(\'' + result[i].id + '\')">' + result[i].title + '</a href="">';
                    html += '<em>' + timestampToDate(result[i].createTime) + '</em>';
                    html += '</li>';
                }
                $("#helpList .message-list").html(html);
                $("#helpList").modal("show");
            },
            fail: function (data) {
                alert("获取数据失败");
            }
        })
    }
    //帮助中心查看详情
    window.goto = function (id) {
        var token = window.localStorage.getItem("token");
        $.ajax({
            url: "/sofn-sys-web/document/queryOne",
            type: "post",
            dataType: "json",
            data: {"id": id, "token": token},
            success: function (data) {
                var htm = data.data.content;
                $("#helpListInfo").modal("show");
                $("#myInfo").html(htm);
                $("#helpListTitile2").html(data.data.title);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }
    $scope.loginOut = function () {
        swal({
            title: "退出登录",
            text: "已成功注销账号信息!",
            type: "success",
            timer: 2000,
            showConfirmButton: false
        }, function () {
            swal.close();
            window.localStorage && window.localStorage.clear();
            window.sessionStorage && window.sessionStorage.clear();
            $state.go("login/login");
        });
    }
    window.timestampToDate = function(timestamp) {
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate();
        return Y+M+D;
    }
    
    $scope.checkIndustry = function () {
        if ($("input[name='entityIndustryId']:checked").length===0) {
            $scope.industryChecked = true;
            return false;
        }else {
            $scope.industryChecked = false;
            return true;
        }
    }

});

