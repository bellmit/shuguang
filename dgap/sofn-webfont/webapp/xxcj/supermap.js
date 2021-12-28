var myapp = angular
	.module("myapp", [])
	.controller("supermap", function($scope, $http, $state, $timeout) {
		setTimeout(function() {
			var map, container, content, overlay, view;
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
				var point = [116.402, 39.905];
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
							"<div><p>经纬度：116.402" +
							",39.905" +
							"</p></div>\n";
						content.innerHTML = sContent;
						info.className = "ol-popup";
						overlay.setPosition(
							feature.getGeometry().getCoordinates()
						);
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
			}
		}, 2000);
	});
