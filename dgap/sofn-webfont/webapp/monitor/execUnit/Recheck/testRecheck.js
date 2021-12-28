var myapp = angular.module("myapp", []);
myapp.controller("testRecheck", function ($scope, $http, $state) {
    $("#ToCheck").click(function () {
        $("#mygrid").dataTable().api().ajax.reload();
    });

    var organTaskId = window.localStorage.getItem("organTaskId");//机构任务ID

    var monitorClass = window.localStorage.getItem("monitorClass");//任务类型

    var token = window.localStorage.getItem("token");

    var arrayNotJudge = new Array();
    /**
     * 分页展示数据
     */
    $(function () {

        //bootstrap表格
        $("#mygrid").dataTable({
            processing: true, // 加载时提示
            serverSide: false, // 分页，是否服务器端处理
            ordering: false,
            //searching : true,
            //bProcessing: true,
            paging: false,
            //bInfo:true,
            //bJQueryUI: true,
            bPaginate: false,// 分页按钮
            bFilter: false,// 搜索栏
            //iDisplayLength: 10,// 每页显示行数
            //bSort: false,// 排序
            dom: '<"top">rt<"bottom"pli><"clear">',
            bAutoWidth: false,
            //sScrollX :true,
            //sScrollY : "auto",
            autoWidth: true,
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsCheckInfo/getTestItemForRecheck",
                dataSrc: function (json) {
                    json.length = json.data.length;
                    return json.data;
                },
                data: function (params) {
                    params.id = $("input[name='bannerid']").val();//检测信息ID
                    params.token = token;
                    //如果是条码枪扫描的话就只有样品编码  fixme 条码存放在页面哪里还没定
                    // params.sampleCode = $("input[name='bannerid']").val();//样品编码
                },
                type: "post"
            },
            columns: [
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (" " == data) {
                            return '<input id="' + meta.row + '"placeholder="ND" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ",'" + row.id + "'" +');check(this)"/>';
                        } else {
                            return '<input id="' + meta.row + '" value="' + data + '" type="text" name="wu" class="Sup_contain_section_contain_input gui-input" onblur="my123(' + meta.row + "," + row.judgeNum + ",'" + row.id + "'" +');check(this)"/>';
                        }
                    }
                },
                {
                    data: "judgeNum",
                    title: "限量值"
                },
                // {
                //     data: "ID",
                //     checkbox: true,
                //     title: "必填",
                //     render: function (data, type, row,meta) { // 模板化列显示内容
                //         return '<input id="c'+meta.row+'" type="checkbox" name="checkOne" onclick="checkRecode(this)" checked="checked"/>';
                //     },
                // },
                {
                    data: "result",
                    title: "抽检结果",
                    render: function (data, type, row) {
                       /* if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else {
                            return "不合格";
                        }*/

                        var sampleName = $("#xxx").text();
                        var checkProject = row.checkProject;
                        var url = "/sofn-ads-web/adsCheckInfo/judgeCheckItem";

                        var str = "";

                        $.ajax({
                            url: url,
                            type: "post",
                            async: false,
                            data: {
                                checkItem: checkProject,
                                sampleName: sampleName
                            },
                            success: function(datas) {
                                var status = datas.data;
                                if (status == "1"){
                                    str =  "不作判定";
                                    //增加未判断的检测项id
                                    if(arrayNotJudge.indexOf(row.id)<0)
                                        arrayNotJudge.push(row.id);
                                }else {
                                    if ("-1" == data) {
                                        str =  "暂未检测";
                                    } else if ("1" == data) {
                                        str =  "合格";
                                    } else {
                                        str =  "不合格";
                                    }
                                }
                            },
                            fail: function(data) {
                                $.jBox.tip("失败");
                            }
                        });
                        return str;
                    }
                },
                {
                    data: "lod",
                    title: "LOD",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (null == data) {
                            return '<input id="' + meta.row + "c" + '" type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        } else {
                            return '<input id="' + meta.row + "c" + '" type="text" value="' + data + '" class="Sup_contain_section_contain_input" )"/>';
                        }
                    }
                },
                {
                    data: "loo",
                    title: "LOQ",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (null == data) {
                            return '<input id="' + meta.row + "d" + '" type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        } else {
                            return '<input id="' + meta.row + "d" + '" type="text" value="' + data + '" class="Sup_contain_section_contain_input" )"/>';
                        }
                    }
                }
            ],
            sPaginationType: "full_numbers"
        });

        //给input框绑定事件
        $("#myinfo").on("keyup", "input", function () {
            var inputValue = document.getElementById("productName").value;
            var rows = $("#mygrid").DataTable().data();
            var length = rows.length;
            var table = $('#mygrid').DataTable();
            $("#mygrid tr").removeClass('highlighted');
            // $(table..nodes()).removeClass('highlighted');
            for (var i = 1; i <= rows.length; i++) {
                var td = rows[i - 1];
                if ("" != inputValue) {
                    if (-1 != td.checkProject.indexOf(inputValue)) {
                        $(table.row(i - 1).nodes()).addClass('highlighted');
                        // $("#mygrid tr:eq(1)").children("td").addClass("highlighted");
                    }
                }
            }
            // alert(length);
        });

        var checkInfoId = window.localStorage.getItem("checkInfoId");
        var url = "/sofn-ads-web/adsCheckInfo/getNextPrimaryWithInfo?time=" + new Date();
        //首次进入页面根据机构任务ID获取产品编码和样品编码以及下一个样品的ID
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"organId": organTaskId, "checkInfoId": checkInfoId, "token": token},
            success: function (data) {
                var myData = data.data;
                // alert($.isEmptyObject(myData));
                if ($.isEmptyObject(myData)) {
                    return;
                }
                if (!$.isEmptyObject(myData) && myData.chanp == null)
                    myData.chanp = "";
                //加载产品编码
                $("#product").empty();
                var Row = '<span>产品编码 ' + myData.chanp + '</span> ';
                $("#product").html(Row);
                //加载样品编码，查询按钮，上一个样品按钮，下一个样品按钮
                $("#example").empty();
                var Row2 = "";
                if ("" == myData.after && "" == myData.before) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button"  id="after" disabled="true">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button"  id="next" disabled="true">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                } else if ("" != myData.after && "" == myData.before) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button"  id="after" disabled="true">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                } else if ("" == myData.after && "" != myData.before) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button"  id="after">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next" disabled="true">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                } else if ("" != myData.after && "" != myData.before) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button" onclick="before(' + "'" + myData.before + "'" + ')" id="after">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                }
                $("#example").html(Row2);
                //加载查看样品详细信息，查看所有数据，保存按钮
                $("#myinfo").empty();
                Row3 = '<div style="float: left;"><span>样品名称  ' +   '<span id="xxx">' + myData.sampleName +  '</span>' + ' </span>&nbsp;&nbsp;' +
                    '<span class="glyphicon glyphicon-search"></span>' +
                    '<input class="gui-input" type="text" id="productName" name="productName"  placeholder="检测项目模糊搜索"/></div>' +
                    '&nbsp;&nbsp;' +
                    '<span class="gui-btn" type="button" onclick="look(' + "'" + myData.yangp + "'" + ')" id="search">查看样品详细信息</span>&nbsp;&nbsp;' +
                    // '<button id="all" onclick="allInfo()">查看所有数据</button>'+
                    '<span class="gui-btn" id="set" onclick="uploadInfo(' + myData.checkReport + ')">保存</span>';
                $("#myinfo").html(Row3);
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

    });

    window.next = function (id) {
        var url2 = "/sofn-ads-web/adsCheckInfo/getAfter?time=" + new Date();
        $.ajax({
            url: url2,
            type: "post",
            dataType: "json",
            data: {"id": id, "organId": organTaskId, "token": token},
            success: function (data) {
                var myData = data.data;
                //加载产品编码
                $("#product").empty();
                if (!$.isEmptyObject(myData) && myData.chanp == null)
                    myData.chanp = "";
                var Row = '<span>产品编码 ' + myData.chanp + '</span> ';
                $("#product").html(Row);
                //加载样品编码，查询按钮，上一个样品按钮，下一个样品按钮
                $("#example").empty();
                var Row2 = "";

                if ("" == myData.after) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button" onclick="before(' + "'" + myData.before + "'" + ')" id="after">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button"  id="next" disabled="true">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                } else {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button" onclick="before(' + "'" + myData.before + "'" + ')" id="after">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                }
                $("#example").html(Row2);
                //加载查看样品详细信息，查看所有数据，保存按钮
                $("#myinfo").empty();
                Row3 = '<div style="float: left;"><span>样品名称  ' +  '<span id="xxx">' + myData.sampleName +  '</span>'+ ' </span>&nbsp;&nbsp;' +
                    '<span class="glyphicon glyphicon-search"></span>' +
                    '<input class="gui-input" type="text" id="productName" name="productName"  placeholder="检测项目模糊搜索"/></div>' +
                    '&nbsp;&nbsp;' +
                    '<span class="gui-btn" type="button" onclick="look(' + "'" + myData.yangp + "'" + ')" id="search">查看样品详细信息</span>&nbsp;&nbsp;' +
                    // '<button id="all" onclick="allInfo()">查看所有数据</button>'+
                    '<span class="gui-btn" id="set" onclick="uploadInfo(' + myData.checkReport + ')">保存</span>';
                $("#myinfo").html(Row3);
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    //返回
    window.back = function () {
        $state.go("index.content.monitor/execUnit/Recheck/testListRecheck");
    };

    window.before = function (id) {
        var url3 = "/sofn-ads-web/adsCheckInfo/getBefore?time=" + new Date();
        $.ajax({
            url: url3,
            type: "post",
            dataType: "json",
            data: {"id": id, "organId": organTaskId, "token": token},
            success: function (data) {
                var myData = data.data;
                //加载产品编码
                $("#product").empty();
                if (!$.isEmptyObject(myData) && myData.chanp == null)
                    myData.chanp = "";
                var Row = '<span>产品编码 ' + myData.chanp + '</span> ';
                $("#product").html(Row);
                //加载样品编码，查询按钮，上一个样品按钮，下一个样品按钮
                $("#example").empty();
                var Row2 = "";
                if ("" == myData.before) {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button"  id="after" disabled="true">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                } else {
                    Row2 = '<span><div style="float: left;">样品编码 <span><input value="' + myData.yangp + '" type="text" name="myName" class="Sup_contain_section_contain_input gui-input"/></span></div></span>&nbsp;&nbsp;' +
                        // '<button onclick="find2()">查询</button>&nbsp;&nbsp;'+
                        '<span class="gui-btn" type="button" onclick="before(' + "'" + myData.before + "'" + ')" id="after">上一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="next(' + "'" + myData.after + "'" + ')" id="next">下一个样品</span>&nbsp;&nbsp;' +
                        '<span class="gui-btn" type="button" onclick="back()">返回</span>&nbsp;&nbsp;' +
                        '<input class="gui-input" type="hidden" name="bannerid" id="bannerid" value="' + myData.id + '">';
                }
                $("#example").html(Row2);
                //加载查看样品详细信息，查看所有数据，保存按钮
                $("#myinfo").empty();
                Row3 = '<div style="float: left;"><span>样品名称  ' +  '<span id="xxx">' + myData.sampleName +  '</span>' + ' </span>&nbsp;&nbsp;' +
                    '<span class="glyphicon glyphicon-search"></span>' +
                    '<input class="gui-input" type="text" id="productName" name="productName"  placeholder="检测项目模糊搜索"/></div>' +
                    '&nbsp;&nbsp;' +
                    '<span class="gui-btn" type="button" onclick="look(' + "'" + myData.yangp + "'" + ')" id="search">查看样品详细信息</span>&nbsp;&nbsp;' +
                    // '<button id="all" onclick="allInfo()">查看所有数据</button>'+
                    '<span class="gui-btn" id="set" onclick="uploadInfo(' + myData.checkReport + ')">保存</span>';
                $("#myinfo").html(Row3);
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    window.find2 = function () {
        $("#mygrid").dataTable().api().ajax.reload();
    }

    //保存
    var url = "/sofn-ads-web/adsInfoProject/updateResult?time=" + new Date();
    window.uploadInfo = function (checkReport) {
        if (checkReport == "1") {
            $.jBox.tip("已上报的检测信息不能修改");
            return;
        }
        var x = [];//必填的选项
        // var n = [];//不用填的选项
        var rows = $("#mygrid").DataTable().data();
        //检测信息判定结果
        var result = 1;
        for (var i = 1; i <= rows.length; i++) {
            var td = rows[i - 1];
            var b = i - 1;
            var checkNum = $("#" + b + "").val();

            if ("" == checkNum) {
                checkNum = "ND";
            }

            var check2 = 0;
            if ("ND" == checkNum) {
                checkNum = 0;
                check2 = 1;
            }

            var infoResult = 0;

            //获取检测信息判定结果
            var checkNum = checkNum;
            var judgeNum = td.judgeNum;
            // if(judgeNum.endsWith(".0")){
            //     judgeNum = judgeNum.replace(".0","");
            // }
          /*  if (parseFloat(checkNum) > parseFloat(judgeNum)) {
                result = 0;
                infoResult = 0;
            } else {
                infoResult = 1;
            }*/

            if(arrayNotJudge.indexOf(td.id)<0){
                if ( parseFloat(checkNum) > parseFloat(judgeNum)) {
                    result = 0;
                    infoResult = 0;
                } else {
                    infoResult = 1;
                }
            } else
                infoResult = 2;

            if (1 == check2) {
                checkNum = "ND";
            }

            var obj = new Object();
            obj.id = td.id;
            obj.checkNum = checkNum;
            obj.result = infoResult;
            obj.LOD = $("#" + b + "c").val();
            obj.LOO = $("#" + b + "d").val();
            x.push(obj);

        }
        var json = JSON.stringify(x);
        // var notObj = JSON.stringify(n);

        //检测信息的判定结果
        var checkInfoId = $("input[name='bannerid']").val();
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"jsonArray": json, "checkInfoId": checkInfoId, "result": result, "token": token},
            success: function (data) {
                $.messager.show({
                    title: "提示信息",
                    msg: "保存成功！",
                    showType: "slide",
                    timeout: 5000
                });
                // var set = document.getElementById("set");
                // set.disabled=true;
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    //修改检测值的时候onblur事件
    window.my123 = function (index, judgeNum,id) {

        if(arrayNotJudge.indexOf(id)>-1)
            return;

        var inputValue = document.getElementById(index).value;
        if ("" == inputValue) {
            return;
        }
        if ("ND" == inputValue) {
            inputValue = 0;
        }
        real = index + 1;
        if (inputValue <= judgeNum) {
            $("#mygrid tr:eq(" + real + ") td:eq(3)").html("合格");
        } else {
            $("#mygrid tr:eq(" + real + ") td:eq(3)").html("不合格");
        }
    }

    window.check = function (e) {
        var re = /^\d+(?=\.{0,1}\d+$|$)/;
        if (e.value == "ND") {
        } else if (e.value != "") {
            if (!re.test(e.value)) {
                alert("请输入正确的数字");
                e.value = "";
                e.focus();
            }
        }
    }

    //页面跳转到详情
    window.look = function (data) {
        // window.localStorage.setItem("sampleCode",data);
        // window.localStorage.setItem("organTaskId", organTaskId);
        // // $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleDetail")
        // var newWin=window.open("#/index/content/monitor/execUnit/AdsOrganTask/sampleDetail","newWin");
        // newWin.focus();
        // return false;
        //给样品ID赋值
        $("#sampleCode").val(data);
        $http({
            url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById?time=" + new Date(),
            method: "post",
            params: {sampleCode: data, organTaskId: organTaskId}
        }).success(function (data) {
            $scope.data = data.data;
            var place = data.data.SAMPLE_PLACE;
            var produce = data.data.PRODUCE_CERTIFICATE;
            var status = data.data.PRODUCTIONS_TATUS;
            if (data.data.PRODUCING_AREA == null) {
                $scope.producingAreaName = "";
            } else {
                $scope.producingAreaName = $.fn.Cityname(data.data.PRODUCING_AREA);
            }
            if (data.data.PRODUCE_DATE == null || data.data.PRODUCE_DATE == "1970-01-01") {
                $scope.produceDate = "";
            } else {
                $scope.produceDate = data.data.PRODUCE_DATE;
            }
            $scope.sampleDate = data.data.SAMPLE_DATE;
            if (place != undefined) {
                if (place == "生产基地/企业") {
                    $("#base").prop("checked", true);
                }
                if (place == "屠宰场") {
                    $("#slaughter").prop("checked", true);
                }
                if (place == "农贸市场") {
                    $("#market").prop("checked", true);
                }
                if (place == "批发市场") {
                    $("#wholesale").prop("checked", true);
                }
                if (place == "超市") {
                    $("#supermar").prop("checked", true);
                }
                if (place == "其他") {
                    $("#else").prop("checked", true);
                }
            }
            if (produce != undefined) {
                if (produce == "无公害农产品") {
                    $("#innocuity").prop("checked", true);
                }
                if (produce == "绿色食品") {
                    $("#green").prop("checked", true);
                }
                if (produce == "有机食品") {
                    $("#organic").prop("checked", true);
                }
                if (produce == "其他") {
                    $("#other").prop("checked", true);
                }
            }

            if (status != undefined) {
                if (status == "生产") {
                    $("#production").prop("checked", true);
                }
                if (status == "进货") {
                    $("#stock").prop("checked", true);
                }
            }
            $("#code").empty();// 解决二维码重复生成BUG
            //二维码
            star = toUtf8(data);
            $("#code").qrcode({
                render: "table", //table方式
                width: 90, //宽度
                height: 90, //高度
                text: star //任意内容
            });
            $("#dModal").modal("show");
        }).error(function () {
        });
    }

    //打印设置
    $("#printArea").click(function () {
        $("#printCount").printArea();
        $("#dModal").modal("hide");
    });

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }

    /**
     * 下载检测任务单
     */
    window.downFile = function (data) {
        // $scope.downFile = function () {
        var submit = function (v, h, f) {
            // var innerText = document.getElementById("detailModal").innerHTML;
            // var adsMonitorSample = {};
            // adsMonitorSample.innerText = innerText;
            var sampleCode = $("#sampleCode").val();
            // var monitorClass=$scope.monitorClass;
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/writeWordFile",
                    method: "post",
                    dataType: "json",
                    params: {sampleCode: sampleCode, organTaskId: organTaskId, monitorClass: monitorClass},
                    responseType: 'arraybuffer',
                    headers: {
                        post: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '抽样单.doc');
                        a.click();
                    } else {
                        jBox.tip("下载失败！", "info")
                    }
                });

            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    //页面跳转到信息汇总页面
    window.allInfo = function () {
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorList")
    }

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/taskDetail")
    }

    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck")
    }

    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/testListRecheck")
    }

    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/sampleMessageRecheck")
    }

    $scope.routineReportUpload = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/recheckReportUpload")
    }

})


