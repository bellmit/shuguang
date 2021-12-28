var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("alesPersonalCenterAdmin",function($scope,$http,$state, $timeout){
    //返回前一页
    $scope.callBack=function(){
        $state.go(window.localStorage.getItem("historyUrl"))
    }
    $("#changePwdForm").bootstrapValidator();//初始化验证，必须有
    $("#subjElCancelForm").bootstrapValidator();//初始化验证，必须有
    $("#subjElChangeForm").bootstrapValidator();//初始化验证，必须有

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
        $scope.httpurl();
    }).error(function (data) {
        
    });
    //当前用户所在机构
    $scope.currentOrg = {};
    $.ajax({
        url: "/sofn-sys-web/user/findSysUserOrganization",
        type: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            if (json.httpCode == 500) {
                alert("获取用户机构信息失败");
                return;
            }
            $scope.currentOrg = json.user.organization;
            $scope.user = json.user;
        },
        error: function(data) {
            alert("失败");
        }
    });

    //请求菜单数据
    $scope.httpurl = function() { //向后台发起请求获取数据
        var token = window.localStorage.getItem('token');
        return $http.post('/sofn-sys-web/ssoMenu/getMenusByUserId', {}, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                token: token
            }
        }).then(function(res) {
            var data = res.data;
            if (data.httpCode != 200) {
                jBox.tip(data.msg);
                return;
            }
            var systems = formattedSystems(data.data);
            $scope.systems = systems;
            return data;
        }, function(err) {

        });
    }

    function formattedSystems(data) {
        var systems = _.groupBy(data, 'menuValue');
        return systems;
    }

    //显示密码修改模态框
    $scope.showPwdModal=function() {
        $("#pwdModal").modal("show");
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
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/login/getPublicKey",
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
                url:"/sofn-ales-web/subjEnforceLaw/changePwd",
                data:{ciphertext: dataStr,desKey: encodeKey},
                method:"post"
            }).success(function(data){
                $("button").attr("disabled",false);
                if(data.httpCode=="200") {
                    $http({
                        url: "/sofn-ales-web/login/logout",
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

    //请求机构数据
    $scope.subjElChange={};
    $scope.subjElChange.elTypeId="";
    $scope.subjElChange.elLevelId="";
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/subjEnforceLaw/findSubjElByUser",
        method:"post",
        data:{id:$scope.subjElId}
    }).success(function(data){
        $scope.subjEl=data.subjEl;
        if($scope.subjEl.elLevel=="0"){
            $scope.subjEl.elLevel="部级";
        }else if($scope.subjEl.elLevel=="1"){
            $scope.subjEl.elLevel="省级";
        }else if($scope.subjEl.elLevel=="2"){
            $scope.subjEl.elLevel="市级";
        }else if($scope.subjEl.elLevel=="3"){
            $scope.subjEl.elLevel="县级";
        }
        $scope.subjElChange=data.subjElChange;
        $scope.initOrgLevel();
        $scope.initOrgType();
        //区域ID转换成名称
        $scope.subjEl.elAreaId=$.fn.Cityname($scope.subjEl.elAreaId);
        //初始化区域下拉框初始选项
        $.fn.mycity("sheng","shi","xian",$scope.subjElChange.elAreaId);
    }).error(function(){
    });

    //获取机构级别数据字典--获取用户信息后初始化
    $scope.initOrgLevel = function() {
        $scope.orgLevel = [];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/sysDict/getSysDictByCode",
            data: "1",
            method: "post"
        }).success(function (data) {
            $scope.orgLevel = JSON.parse(data.data);
            for (var i = 0; i < $scope.orgLevel.length; i++) {
                if ($scope.subjElChange.elLevelId == $scope.orgLevel[i].id) {
                    $("select[name='elLevel']").append("<option selected value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                } else {
                    $("select[name='elLevel']").append("<option value='" + $scope.orgLevel[i].id + "' dictVal='" + $scope.orgLevel[i].dictValue + "'>" + $scope.orgLevel[i].dictName + "</option>");
                }
            }
            $("select[name='elLevel']").attr("disabled", "disabled");
        })
    }
    //获取机构类别数据字典--获取用户信息后初始化
    $scope.initOrgType = function() {
        $scope.orgType = [];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/sysDict/getSysDictByCode",
            data: "0",
            method: "post"
        }).success(function (data) {
            $scope.orgType = JSON.parse(data.data);
            for (var i = 0; i < $scope.orgType.length; i++) {
                if ($scope.orgType[i].dictName == "执法机构") {
                    $scope.subjElChange.elTypeId = $scope.orgType[i].id;
                    $("select[name='elType']").append("<option value='" + $scope.orgType[i].id + "'>" + $scope.orgType[i].dictName + "</option>");
                    $("select[name='elType']").attr("disabled", "disabled");
                }
            }
        })
    }

    //显示修改申请模态框
    $scope.showChangeModal=function(){
        if($scope.subjEl.status=="1"){
            jBox.tip("还有变更待审核！", 'info');
            return false;
        }else if($scope.subjEl.status=="2"){
            jBox.tip("还有注销待审核！", 'info');
            return false;
        }else if($scope.subjEl.status=="3"){
            jBox.tip("还有撤销待审核！", 'info');
            return false;
        }else{
            $("#subjElChangeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
            $("#subjElChangeModal").modal("show");
            $("#subjElChangeForm").bootstrapValidator();//初始化验证，与上面对应
        }
    }

    //显示注销模态框
    $scope.showCancelModal=function(){
        // if($scope.subjEl.elLevel=="部级"){
        //     jBox.tip("部级机构不能注销！", 'info');
        //     return false;
        // }

        if($scope.subjEl.status=="1"){
            jBox.tip("还有变更待审核！", 'info');
            return false;
        }else if($scope.subjEl.status=="2"){
            jBox.tip("还有注销待审核！", 'info');
            return false;
        }else if($scope.subjEl.status=="3"){
            jBox.tip("还有撤销待审核！", 'info');
            return false;
        }else{
            $("#subjElCancelModal").modal("show");
            $("#subjElCancelForm").data("bootstrapValidator").resetForm(true);
        }
    }

    //注销申请
    $scope.applySubjElCancel=function(){
        if (!$("#subjElCancelForm").data('bootstrapValidator').validate().isValid()) {//必须有
            return false;
        }
        $.jBox.confirm("确认申请注销本执法机构？", "申请成功", function(v){
            if(v==true){
                if (!$("#subjElCancelForm").data('bootstrapValidator').validate().isValid()) {//必须有
                    return false;
                }

                $http({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url:"/sofn-ales-web/subjEnforceLaw/addSubjElCancel",
                    method:"post",
                    data:$scope.subjElCancel
                }).success(function(data){

                }).error(function(){
                });
                jBox.tip("申请成功", 'info');
                $scope.subjEl.status="2";
                $("#subjElCancelModal").modal("hide");
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    }

    //取消备案变更申请
    $scope.cancelSubjElChange=function(){
        $.jBox.confirm("取消并退出注册变更申请？", "取消成功", function(v){
            if(v==true){
                $("#subjElChangeModal").modal("hide");
                jBox.tip("取消成功", 'info');
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //保存变更信息
    $scope.saveSubjElChange=function(){
        if (!$("#subjElChangeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        if($scope.subjElChange.elLevel=="0"){
            $scope.subjElChange.elLevel="部级";
        }else if($scope.subjElChange.elLevel=="1"){
            $scope.subjElChange.elLevel="省级";
        }else if($scope.subjElChange.elLevel=="2"){
            $scope.subjElChange.elLevel="市级";
        }else if($scope.subjElChange.elLevel=="3"){
            $scope.subjElChange.elLevel="县级";
        }
        var before = JSON.stringify($scope.subjElChange);
        $scope.subjEl.applyReason = $scope.subjElChange.applyReason;//赋值后进行比较
        var after = JSON.stringify($scope.subjEl);
        
        if(before==after){
            $.jBox.tip("请至少修改一处！");
            return false;
        }
        var elArea = "";
        var xian = $("#xian").val()
        if(xian == ""){
            elArea = $("#shi").val()+"01";
        }else{
            elArea = $("#xian").val();
        }
        $scope.subjElChange.elArea=$.fn.Cityname(elArea);
        $scope.subjElChange.elAreaId = elArea;
        $scope.subjElChange.elType=$("select[name='elType'] option:selected").text();
        $scope.subjElChange.elLevelId=$("select[name='elLevel'] option:selected").val();
        $scope.subjElChange.elLevel=$("select[name='elLevel'] option:selected").attr("dictVal");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEnforceLaw/addSubjElChange",
            data:$scope.subjElChange,
            method:"post"
        }).success(function(data){
            if(data.httpCode=='409'){
                jBox.tip("机构名称或机构代码已存在", 'info');
            }else{
                $.jBox.tip("申请成功");
                $scope.subjEl.status="1";
                $("#subjElChangeModal").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
            }
        }).error(function(){
            $.jBox.tip("申请失败");
        })
    };

    //分页
    //bootstrap表格
    $("#grid").dataTable({
        ajax : {
            url : "/sofn-ales-web/subjEnforceLaw/getChangeListByElId",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc:function(json){
                json.recordsTotal=json.page.recordsTotal;//总个数
                json.recordsFiltered=json.page.recordsFiltered;
                json.start=json.page.start;//起始位置
                json.length=json.page.length;
                for(var i=0;i<json.list.length;i++){
                    if(json.list[i].applyTime!=null&&json.list[i].applyTime!=""&&json.list[i].applyTime!=undefined){
                        json.list[i].applyTime=new Date(parseInt(json.list[i].applyTime)+28800000).toISOString().slice(0,10);
                    }
                }
                return json.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                params.token=window.localStorage.getItem("token");
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
                data : "changeContent",
                title : "变更内容",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>60){
                        return "<a id='"+row.id+"' onmouseover=showPopContent('"+row.id+"','"+data+"')>"+data.substring(0,60)+"...</a>";
                    }else{
                        return data;
                    }
                }
            },
            {
                data : "applyReason",
                title : "变更原因"
            },
            {
                data : "applyTime",
                title : "申请时间"
            },
            // {
            //     data : "applyUserId",
            //     title : "申请人"
            // },
            {
                data : "auditState",
                title : "审核状态",
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
                data : "id",
                title : "操作",
                render : function(data, type, row) { // 模板化列显示内容
                    return '<a style="cursor: pointer" class="Check-report"  onclick="viewSubjELChange(\''+data+'\')">查看</a>';//转义传值
                }
            },
        ],
    });

    //显示变更明细数据
    window.checkChangeDetail=function(changeId){
        $("#change_modal").modal("show");
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesSubjElChange/findAdministrativePenaltyById",
            method:"post",
            data:{id:changeId}
        }).success(function(data){
            //得到变更前信息
            $scope.before=data.alesSubjEnforceLaw;
            //得到变更后信息
            $scope.after=data.alesSubjElChange;

        }).error(function(){
        });
    };

    $scope.go = function () {
        var token = window.localStorage.getItem("token");
        if (token == null || token == '') {
            swal('系统提示', '获取登录信息失败，请重新登录', 'error');
            return;
        }
        window.location.href = '/Fxjc/?token=' + token;
    };
    //打开帮助中心
    $scope.gotoHelpCenter = function ($event) {
        console.log("----------------------------------");
        $event.preventDefault();
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
    $scope.loginOut = function ($event) {
        $event.preventDefault();
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
            $state.go("login");
        });
    }
    //查看详情页面
    window.viewSubjELChange=function(obj){
        window.localStorage.setItem("subjElChangeId",obj);
        $state.go("account/checkSubjEnChange");
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
    window.timestampToDate = function(timestamp) {
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate();
        return Y+M+D;
    }
});

