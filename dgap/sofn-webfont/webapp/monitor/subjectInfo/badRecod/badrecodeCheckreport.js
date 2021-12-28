var myapp=angular.module("myapp",[]);
myapp.controller("badrecodeCheckreport",function($scope,$http,$state) {
    $scope.ID = window.localStorage.getItem("badrecodeID");
    $scope.enterprise = {};
    $scope.enterprise.id = {};
    $scope.enterprise.entityIdCode = {};
    $scope.subjEnt = {};
    $scope.subjEnt.id = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/subjEntBadrecord/findEnterpriseById",
        method: "post",
        //data:$scope.ID
        params: {
            'entityIdCode': $scope.ID
        }
    }).success(function (dataEnterprise) {
        $scope.enterprise = dataEnterprise.enterprise;
        if ($scope.enterprise != null) {
            if(!($scope.enterprise.creditCode!=null&&$scope.enterprise.creditCode!=""&&$scope.enterprise.creditCode!=undefined)){
                $("#creditCodeDiv").hide();
            }
            $("#enterpriseTmep").hide();//临时主体，
            $("#enterprisezhuti").show();
            if ($scope.enterprise.area != null && $scope.enterprise.area != "" && $scope.enterprise.area != undefined) {
                $scope.area = $.fn.Cityname($scope.enterprise.area);
            }
            $("#badRecordGrid").dataTable().api().ajax.reload();
        } else {
            $("#enterprisezhuti").hide();
            $("#enterpriseTmep").show();//临时主体，
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/subjEntBadrecord/findEnterpriseTmpById",
                method: "post",
                //data:$scope.ID
                params: {
                    'entityIdCode': $scope.ID
                }
            }).success(function (dataEnterprise) {
                $scope.subjEnt = dataEnterprise.dataSubjEntTemp;
                if ($scope.subjEnt.areaId != null && $scope.subjEnt.areaId != "" && $scope.subjEnt.areaId != undefined) {
                    $scope.areaId = $.fn.Cityname($scope.subjEnt.areaId);
                }
                $("#badRecordGrid").dataTable().api().ajax.reload();
            }).error(function () {

            });
        }
    }).error(function (data) {
        
    });
    $("#badRecordGrid").dataTable({
        ajax: {
            url: "/sofn-ales-web/subjEntBadrecord/getAsmsSubjEntBadrecordByIdList",
            headers: {
                token: window.localStorage.getItem("token")
            },
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.page.recordsTotal;//总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start;//起始位置
                json.length = json.page.length;
                for (var i = 0; i < json.list.length; i++) {
                    if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                        json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                    }
                }
                return json.list;
            },
            //传入后台的参数（点击查询 前台页面传入后台的参数）
            data: function (params) {
                if ($scope.enterprise != null) {
                    params.enterpriseId = $scope.enterprise.id;
                } else {
                    params.enterpriseId = $scope.subjEnt.id
                }

            },
            type: "POST"
        },
        columns: [
            {
                //data : "RN",
                title: "序号",
                width:"5%",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }

            },
            {
                data : "createTime",
                title : "时间",
                width:"8%",
            },
            {
                data:"sourceType",
                title:"来源",
                width:"8%",
                render:function(data,type,row){
                    if(data == "1"){
                        data = "基地巡查"
                    }else if(data == "2"){
                        data = "行政处罚"
                    }else if(data == "3"){
                        data = "抽样检测"
                    } else if(data == "4"){
                        data = "现场巡查"
                    }
                    return data;
                }
            },
            {
                data : "badrecordContent",
                title : "不良记录描述",
                width:"50%",
                render:function(data,type,row){
                    if(data!=null&&data!=undefined&&data.length>43){
                        return "<a id='badrecordContent"+row.id+"' onmouseover=showPopContent('badrecordContent"+row.id+"','"+data+"')>"+data.substring(0,43)+"...</a>";
                    }else{
                        return data;
                    }
                }

            },
            {
                data : "badrecordFile",
                title : "附件",
                width:"22%",
                render: function (data, type, row) { // 模板化列显示内容
                    var subjEntsFileurls = [];
                    var subjEntsFileNames = [];
                    if (row.badrecordFile) {
                        subjEntsFileurls = row.badrecordFile.split("*##*");
                    }
                    var butt = "";
                    if (row.badrecordFileName) {
                        subjEntsFileNames = row.badrecordFileName.split("*##*");
                    }else {
                        for(var i=0;i<subjEntsFileurls.length;i++){
                            butt += '<a style="cursor: pointer" class="Check-report"  onclick=downloadFiles(\''+subjEntsFileurls[i]+'\',\''+"附件"+(i+1)+subjEntsFileurls[i].substring(subjEntsFileurls[i].lastIndexOf("."),subjEntsFileurls[i].length)+'\')>'+"附件"+(i+1)+subjEntsFileurls[i].substring(subjEntsFileurls[i].lastIndexOf("."),subjEntsFileurls[i].length)+'</a>';//转义传值
                            if(i!=subjEntsFileurls.length-1){
                                butt += ',';
                            }
                        }
                        return butt;
                    }
                    for(var i=0;i<subjEntsFileurls.length;i++){
                        butt += '<a style="cursor: pointer" class="Check-report"  onclick=downloadFiles(\''+encodeURI(subjEntsFileurls[i])+'\',\''+encodeURI(subjEntsFileNames[i])+'\')>'+subjEntsFileNames[i]+'</a>';//转义传值
                        if(i!=subjEntsFileurls.length-1){
                            butt += ',';
                        }
                    }
                    return butt;
                }
            }
        ]
    });
    //modal 地图展示
   /**
    * 地图
    */
 var map, container, content, overlay, view;
    $("#gis")
        .on("shown.bs.modal", function(e) {
            if (!map) {
                //初始化地图
                (container = document.getElementById("info")),
                    (content = document.getElementById("info-content")),
                    (view = new ol.View({
                        center: [116.402, 39.905],
                        zoom: 14,
                        minZoom:2,
                        projection: "EPSG:4326"
                    }));
                map = new ol.Map({
                    target: "mapDiv",
                    controls: ol.control.defaults({
                        attributionOptions: { collapsed: true }
                    }),
                    view: view,
                    layers: [
                        new ol.layer.Tile({
                            source: new ol.source.TileSuperMapRest({
                                url:
                                    "http://www.bg.qsst.moa.gov.cn/iserver/services/map-China100/rest/maps/China_4326",
                                projection: "EPSG:4326",
                                origin: [-180.0, 90]
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.Tianditu({
                                layerType: "vec",
                                projection: "EPSG:4326"
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.Tianditu({
                                layerType: "vec",
                                projection: "EPSG:4326",
                                isLabel: true
                            })
                        })
                    ]
                });
            }
            overlay = new ol.Overlay({
                element: container,
                autoPan: true,
                autoPanAnimation: {
                    duration: 250
                },
                offset: [0, -20]
            });
            map.getOverlays().clear();
            map.addOverlay(overlay);
            if (
                $scope.subjEnt.longitude == null ||
                $scope.subjEnt.latitude == null
            ) {
                swal("系统提示", "获取该主体经纬度信息失败！", "warning");
                return;
            }
            var point = [$scope.subjEnt.longitude, $scope.subjEnt.latitude];
            var vectorSource = new ol.source.Vector();
            var imgUrl =
                "http://iclient.supermap.io/examples/img/markerbig_select.png";

            var vectorLayer = new ol.layer.Vector({
                source: vectorSource
            });
            map.addLayer(vectorLayer);

            //改变地图中心点
            view.animate({ zoom: 14 }, { center: point });
            var feature = new ol.Feature();

            //主体信息框
            var select = new ol.interaction.Select();
            select.on("select", function(e) {
                if (e.selected.length > 0) {
                    var feature = e.selected[0];
                    var sContent =
                        "<div><p>主体名称：" +
                        $scope.subjEnt.enterpriseName +
                        "</p><p>经纬度：" +
                        $scope.subjEnt.longitude +
                        "," +
                        $scope.subjEnt.latitude +
                        "</p></div>\n";
                    content.innerHTML = sContent;
                    info.className = "ol-popup";
                    overlay.setPosition(feature.getGeometry().getCoordinates());
                } else if (overlay) {
                    overlay.setPosition(undefined);
                }
            });

            //主体定位点
            feature.setGeometry(new ol.geom.Point(point));
            feature.setStyle(
                new ol.style.Style({
                    image: new ol.style.Icon({
                        anchor: [0.5, 1],
                        src: imgUrl
                    })
                })
            );
            vectorSource.clear();
            map.removeInteraction(select);
            vectorSource.addFeatures([feature]);
            map.addInteraction(select);
        })
        .on("hidden.bs.modal", function() {
            map.removeOverlay(overlay);
        });
    //modal 地图展示
    $scope.gis = function() {
        if (
            $scope.subjEnt == null ||
            $scope.subjEnt == "" ||
            $scope.subjEnt == undefined
        ) {
            jBox.tip("请先选择生产经营主体！", "info");
            return false;
        }
        if (
            $scope.subjEnt.longitude == null ||
            $scope.subjEnt.longitude == "" ||
            $scope.subjEnt.longitude == undefined ||
            $scope.subjEnt.latitude == null ||
            $scope.subjEnt.latitude == "" ||
            $scope.subjEnt.latitude == undefined
        ) {
            jBox.tip("该生产经营主体没有标记地理位置！", "info");
            return false;
        }
        $("#gis").modal("show");
    };
    $scope.closeModel = function(){
        $("#baseInspection").modal('hide');
    }
    window.queryInfo = function(sourceId, sourceType){
        if(sourceType==1){
            //基地巡查
            $scope.queryBaseInspectionInfo(sourceId, sourceType);
            $('#baseInspection').modal('show');
        }else if(sourceType==2){
            //行政处罚
            $scope.queryProduceAdminPunish(sourceId, sourceType);
            $('#produceAdminPunish').modal('show');
        }else if(sourceType==3){
            //抽样检测
            $scope.queryProductAndAdsCheckInfo(sourceId, sourceType);
            $('#productAndAdsCheckInfo').modal('show');
        }else if(sourceType==4){
            //现场巡查
            $scope.getDailyEnforceLawInfo(sourceId, sourceType);
            $('#etlDailyEnforceLaw').modal('show');
        }
    }
    //获取基地巡查详情
    $scope.queryBaseInspectionInfo = function (sourceId, sourceType) {
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesBaseInspection/findBaseInspectionById",
            method:"post",
            data:{id:sourceId}
        }).success(function(data){
            $scope.baseInspection=data.data;
            if ($scope.baseInspection.inspectionTime) {
                $scope.baseInspection.inspectionTime=new Date(parseInt($scope.baseInspection.inspectionTime+28800000)).toISOString().slice(0,10);
            }
            //处理巡查类型
            if(data.data.inspectionType=="1"){
                $scope.baseInspection.inspectionType = "基地";
            }else if(data.data.inspectionType=="2"){
                $scope.baseInspection.inspectionType = "主体";
            }else if(data.data.inspectionType=="3"){
                $scope.baseInspection.inspectionType = "农资";
            }else if(data.data.inspectionType=="0"){
                $scope.baseInspection.inspectionType = "其他";
            }
            //处理巡查结果
            if(data.data.inspectionResult=="1"){
                $scope.baseInspection.inspectionResult = "合格";
            }else if(data.data.inspectionResult=="2"){
                $scope.baseInspection.inspectionResult = "不合格";
            }else if(data.data.inspectionResult=="3"){
                $scope.baseInspection.inspectionResult = "整改";
            }
            //处理图片
            $("#imgSpot").html("");
            if($scope.baseInspection.inspectionImages!=null&&$scope.baseInspection.inspectionImages!=undefined&&$scope.baseInspection.inspectionImages!="") {
                var array = $scope.baseInspection.inspectionImages.split("*##*");
                for (var i = 0; i < array.length; i++) {
                    if(array[i]!=null&&array[i]!=undefined&&array[i].indexOf("/sofn-sys-web/sysTemplate/downPic")==-1){
                        array[i] = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+array[i];
                    }
                    $("#imgSpot").append('<a data-fancybox href="'+array[i]+'"><img style="padding-right: 10px;" src="' + array[i] + '" alt=""/></a>');
                }
            }
            if($scope.baseInspection.headSignFile!=null&&$scope.baseInspection.headSignFile!=undefined&&$scope.baseInspection.headSignFile!=""){
                if($scope.baseInspection.headSignFile.indexOf("/sofn-sys-web/sysTemplate/downPic")==-1){
                    $scope.baseInspection.headSignFileShow = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.baseInspection.headSignFile;
                }
                $("#headSignFile").attr("src",$scope.baseInspection.headSignFileShow);
                // var array2 = $scope.baseInspection.headSignFile.split("*##*");
                // for(var i=0;i<array2.length-1;i++){
                //     $("#headSignFile").attr("src",array2[i]);
                // }
            }
            $scope.subjEnt = data.data.enterpriseId;
            $http({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url:"/sofn-ales-web/subjEnt/findSubjEntById",
                method:"post",
                data:{id:data.data.enterpriseId}
            }).success(function(dataEnterprise){
                if(dataEnterprise.data==null||dataEnterprise.data==""||dataEnterprise.data==undefined){
                    $http({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url:"/sofn-ales-web/subjEnt/findSubjEntTempById",
                        method:"post",
                        data:{id:data.data.enterpriseId}
                    }).success(function(dataSubjEntTemp){
                        $scope.subjEnt=dataSubjEntTemp.data;
                        $scope.subjEnt.enterpriseName=dataSubjEntTemp.data.entityName;
                        $scope.subjEnt.entityTypeName=dataSubjEntTemp.data.entityType;
                        $scope.subjEnt.entityScaleName= dataSubjEntTemp.data.entityScale;
                        $scope.subjEnt.entityIndustryName=dataSubjEntTemp.data.entityIndustry;
                        $("#frdb").hide();//临时主体，隐藏法人
                        $("#dbdh").hide();//临时主体，隐藏法人电话
                    }).error(function () {
                    });
                }else {
                    $scope.subjEnt = dataEnterprise.data;
                    $("#frdb").show();//主体，显示法人
                    $("#dbdh").show();//主体，显示法人电话
                }
            }).
            error(function(){
            });
        }).error(function (data) {
            
        });
    }
    $scope.getDailyEnforceLawInfo = function(sourceId, sourceType){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEntBadrecord/findSubjEntBadcordDetail",
            method:"post",
            params:{sourceId:sourceId, sourceType:sourceType}
        }).success(function(data){
            $scope.reCord=data.dailyEnforceLaw;
            if($scope.reCord.taskBeginTime) {
                $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)+28800000).toISOString().slice(0, 10);
            }
            if($scope.reCord.taskEndTime){
                $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
            }
            if($scope.reCord.areaId!=null&&$scope.reCord.areaId!=""&&$scope.reCord.areaId!=undefined) {
                $scope.areaId=$.fn.Cityname($scope.reCord.areaId);
            }
            if($scope.reCord.enforceLawResultFlag!=null&&$scope.reCord.enforceLawResultFlag!=""&&$scope.reCord.enforceLawResultFlag!=undefined){
                if($scope.reCord.enforceLawResultFlag=="1"){
                    $scope.reCord.enforceLawResultFlag="合格";
                }
                if($scope.reCord.enforceLawResultFlag=="2"){
                    $scope.reCord.enforceLawResultFlag="不合格";
                }
                if($scope.reCord.enforceLawResultFlag=="3"){
                    $scope.reCord.enforceLawResultFlag="整改";
                }
                if($scope.reCord.enforceLawResultFlag=="4"){
                    $scope.reCord.enforceLawResultFlag="其他";
                }
            }
        }).error(function(data){
            
        });
    }
    //行政处罚模态框详细信息
    $scope.queryProduceAdminPunish = function(sourceId, sourceType){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEntBadrecord/findSubjEntBadcordDetail",
            method:"post",
            params:{sourceId:sourceId, sourceType:sourceType}
        }).success(function (data) {
            $scope.reCord = data.produceAdminPunish;
        }).error(function (data) {
            
        });
    }
    //抽样检测模态框详细信息
    $scope.queryProductAndAdsCheckInfo =function(sourceId, sourceType){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/subjEntBadrecord/findSubjEntBadcordDetail",
            method:"post",
            params:{sourceId:sourceId, sourceType:sourceType}
        }).success(function (data) {
            $scope.productAndAdsCheckInfo = data.productAndAdsCheckInfo;
            var datas = $scope.productAndAdsCheckInfo.projectList;
            if (datas.length > 0){
                $("#badProductListGrid tbody").html("");
                for (var i = 1; i <=datas.length; i++){
                    var td = datas[i-1];
                    if("-1" == td.result){
                        td.result = "暂未检测";
                    }else if("1" == td.result){
                        td.result = "合格";
                    }else{
                        td.result = "不合格";
                    }
                    td.checkNum = td.checkNum||'ND';
                    td.lod = td.lod||'';
                    td.loo = td.loo||'';
                    var Row = "<tr><td>"+td.checkProject+"</td> <td>"+td.checkNum+"</td><td>"+td.judgeNum+"</td><td>"+td.unit+"</td><td>"+td.result+"</td><td>"+td.lod+"</td><td>"+td.loo+"</td></tr>";
                    if($("#badProductListGrid tbody").children().length==0){
                        $("#badProductListGrid tbody").prepend(Row);
                    }else {
                        $("#badProductListGrid tbody tr:last").after(Row);
                    }
                }
            }
        }).error(function (data) {
            
        });
    }
    //主体审核信息
    window.lookEnterprise=function(){
        window.localStorage.setItem("badrecodeIDD",$scope.ID);
        $state.go("index.content.ales/subjectInfo/badrecod/enterpriseview")
    }
    window.removeWindow=function(){
        window.localStorage.setItem("badrecodeID",$scope.ID);
        $state.go("index.content.ales/subjectInfo/badrecod/badrecodeCheckreport")
    }
    //附件的下载
    window.downloadFiles=function(fileUrl,fileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ales-web/alesFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', decodeURI(fileUrl));
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', decodeURI(fileName));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
    window.downloadFile=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ales-web/alesFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.reCord.punishFiles);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.reCord.punishFilesName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
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
});

