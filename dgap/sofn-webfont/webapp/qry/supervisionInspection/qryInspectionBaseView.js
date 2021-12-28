var myapp=angular.module("myapp",[]);
myapp.controller("qryInspectionBaseView",function($scope,$http,$state){
    //获取本地存储
    $scope.baseInspectionId=window.localStorage.getItem("baseInspectionId");

    //获取基地巡查详情
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/supervisionInspection/findBaseInspectionById",
        method:"post",
        data:{id:$scope.baseInspectionId}
    }).success(function(data){
        $scope.baseInspection=data.data;
        $scope.baseInspection.inspectionTime=new Date(parseInt($scope.baseInspection.inspectionTime+28800000)).toISOString().slice(0,10);
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
        if($scope.baseInspection.inspectionImages!=null&&$scope.baseInspection.inspectionImages!=undefined&&$scope.baseInspection.inspectionImages!="") {
            var array = $scope.baseInspection.inspectionImages.split("*##*");
            for (var i = 0; i < array.length; i++) {
                //$("#imgSpot").append('<img src="' + array[i] + '" alt=""/>');
                if(array[i]!=null&&array[i]!=undefined&&array[i].indexOf("/sofn-sys-web/sysTemplate/downPic")==-1){
                    array[i] = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+array[i];
                }
                $("#imgSpot").append('<a data-fancybox href="'+array[i]+'"> <img style="padding-right: 10px;" src="' + array[i] + '" alt=""/></a>');
            }
        }
        //处理视频
        if($scope.baseInspection.videoUrl) {
            if($scope.baseInspection.videoUrl.indexOf("/sofn-sys-web/sysTemplate/downPic")==-1){
                $scope.baseInspection.videoUrl = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.baseInspection.videoUrl;
            }
            $("#videoSpot").append('<video controls="controls" preload="none" style="padding-right: 10px;width: 400px;" src="' + $scope.baseInspection.videoUrl + '" alt="'+ $scope.baseInspection.videoName +'"/></video>');
        }else {
            $("#videoSpot").append('<div>暂无视频</div>')
        }
        if($scope.baseInspection.headSignFile!=null&&$scope.baseInspection.headSignFile!=undefined&&$scope.baseInspection.headSignFile!=""){
            if($scope.baseInspection.headSignFile.indexOf("/sofn-sys-web/sysTemplate/downPic")==-1){
                $scope.baseInspection.headSignFile = "/sofn-sys-web/sysTemplate/downPic?fileUrl="+$scope.baseInspection.headSignFile;
            }
            $("#headSignFile").html("<a data-fancybox href="+$scope.baseInspection.headSignFile+"><img src="+$scope.baseInspection.headSignFile+" ></a>");
        }
		$http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/subject/findSubjEntById",
			method:"post",
			data:{id:data.data.enterpriseId}
		}).success(function(dataEnterprise){
            if(dataEnterprise.data==null||dataEnterprise.data==""||dataEnterprise.data==undefined){
                $http({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url:"/sofn-qry-web/subject/findSubjEntTempById",
                    method:"post",
                    data:{id:data.data.enterpriseId}
                }).success(function(dataSubjEntTemp){
                    $scope.subjEnt=dataSubjEntTemp.data;
                    $scope.subjEnt.enterpriseName=dataSubjEntTemp.data.entityName;
                    $scope.subjEnt.entityTypeName=dataSubjEntTemp.data.entityType;
                    $scope.subjEnt.entityIndustryName=dataSubjEntTemp.data.entityIndustry;
                    $scope.subjEnt.entityScaleName = $scope.subjEnt.entityScale;
                    $("#frdb").hide();//临时主体，隐藏法人
                    $("#dbdh").hide();//临时主体，隐藏法人电话
                }).error(function(data){
                    
                })
            }else {
                $scope.subjEnt = dataEnterprise.data;
            }
		}).error(function(data){
            
        })
        //空字段默认为"无"
        for (var p in $scope.subjEnt){
            if(!$scope.subjEnt[p]){
                $scope.subjEnt[p] = "无"
            }
        }
        for (var p in $scope.baseInspection){
            if(!$scope.baseInspection[p]){
                $scope.baseInspection[p] = "无"
            }
        }
    }).error(function(data){
        
    })

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("baseInspectionId");
        var inspectionViewEntryType = window.localStorage.getItem("inspectionViewEntryType");
        if("0"==inspectionViewEntryType){
            $state.go("index.content.qry/supervisionInspection/qryInspectionBaseList");
        }
        else if("1"==inspectionViewEntryType){
            $state.go("index.content.qry/supervisionInspection/qryAssessList");
        }
        else if("2" == inspectionViewEntryType){
            $state.go("index.content.qry/subject/qrySubjEntInfoView");
        }
    }

    //打印设置
    $("#printArea").click(function(){
        $(".Sup_contain_section_contain").printArea();
    });

    /**
     * 地图
     */
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
                        minZoom: 2,
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
                                // projection: "EPSG:4326",
                                origin: [-180.0, 90]
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.SuperMapCloud({
                                layerType: "vec",
                                // projection: "EPSG:4326"
                            })
                        }),
                        new ol.layer.Tile({
                            source: new ol.source.SuperMapCloud({
                                layerType: "vec",
                                // projection: "EPSG:4326",
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
});