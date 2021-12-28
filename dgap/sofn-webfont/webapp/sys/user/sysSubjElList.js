var myapp=angular.module("myapp",[]);
myapp.controller("sysSubjElList",function($scope,$http,$state){
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('机构用户管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    //判定页面权限、tab页、按钮
   //  var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
   //  for(var i=0;i< a.length;i++){
   //      if(a[i].text=="执法机构主体信息"){
   //          $scope.iswrite=a[i].authority;
   //          break;
   //      }
   //  }
   // a.map(function(item,i){
   //      if(item.text=="备案变更待审核"){
   //          $(".subjElChangeList").css("display","inline-block");
   //      }else if(item.text=="备案注销待审核"){
   //          $(".subjElCancelList").css("display","inline-block");
   //      }else if(item.text=="备案撤销待审核"){
   //          $(".subjElRevokeList").css("display","inline-block");
   //      }
   //  });
    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        loadGrid();
    }).error(function(){
    });

    //重置区域
    $scope.reloadArea=function(){
        $("#queryForm").form("reset");
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("sheng", "shi", "xian");
        }
    }

    $scope.getAreaBySelect=function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (province != null && province != undefined && province != "") {
            areaId = province;
        }
        return areaId;
    }

    //批量导入执法机构主体
    $scope.importSubjEl=function(){
        $("#save").attr("disabled",true);
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        if(!file){
            jBox.tip("请先选择文件，再提交","info");
            $("#save").attr("disabled", false);
            return;
        }
        fd.append('file', file);
        $http({
            method:'POST',
            url:"/sofn-asms-web/subjEnforceLaw/importSubjEnforceLaw",
            data: fd,
            headers: {
                token:window.localStorage.getItem("token"),
                'Content-Type':undefined},
            transformRequest: angular.identity
        }).success( function ( data )
        {
            $("#save").attr("disabled", false);
            if(data.httpCode=="200") {
                //上传成功的操作
                jBox.tip("上传成功","info");
                $("#importModal").modal("hide");
                $("#elGrid").dataTable().api().ajax.reload();
            }else if(data.httpCode=="400"){
                alert(data.importMsg,"info");
            }else{
                jBox.tip(data.msg,"info");
            }
        });
    }

    //模态框显示
    $("#importSubjEl").click(function(){
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $("#importModal").modal("show");
    });

    //bootstrap表格
    //执法机构主体信息
    function loadGrid() {
        $("#elGrid").dataTable({
            ajax: {
                url: "/sofn-asms-web/subjEnforceLaw/getSubjEnforceLawListForSys",
                //dataSrc : "rows", // 加载表格的相应数据源
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
                    params.elAreaId = $scope.getAreaBySelect();
                    params.elName = $("input[name='elName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.token = window.localStorage.getItem("token");
                },
                type: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            },
            columns: [
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }

                },
                {
                    data: "elName",
                    title: "机构名称",
                    render: function (data, type, row) {
                        if (data.length > 8) {
                            return "<a id='" + row.id + "' onmouseover=showPopContent('" + row.id + "','" + data + "')>" + data.substring(0, 8) + "...</a>";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "elAreaId",
                    title: "所属区域",
                    render: function (data, type, row) {
                        return $.fn.Cityname(data);
                    }
                },
                {
                    data: "account",
                    title: "管理员账号"
                },
                {
                    data: "password",
                    title: "初始密码"
                },
                {
                    data: "createTime",
                    title: "注册时间"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjElView(\'' + data + '\')">查看</a>';
                        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
                            butt += '<a style="cursor: pointer" class="Check-report color" onclick="subjElRevokeApply(\'' + row.account + '\')">重置密码</a>';//转义传值
                        }
                        return butt;
                    }
                }
            ]
        });
    }

    //重新加载列表
    $scope.querySubjElList=function(){
        $("#elGrid").dataTable().api().ajax.reload();
    }
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
    //跳转到主体备案查看页面
    window.subjElView=function(data){
        window.localStorage.setItem("subjElId",data);
        $state.go("index.content.sys/user/sysSubjElView", {source: 'sys'});
    }
    //权限判定按钮是否能操作
    //跳转到主体撤销申请页面
    window.subjElRevokeApply=function(account){
        if (confirm('是否重置密码？')) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-sys-web/user/resetPassword",
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                params: {'account': account}
            }).success(function (data) {
                if (data.httpCode == 200)
                    jBox.tip("重置成功！", 'info');
                else
                    jBox.tip("重置失败！", 'info');
                $("#elGrid").dataTable().api().ajax.reload();
            }).error(function () {
                jBox.tip("重置失败！", 'info');
            });
        }
    }
})