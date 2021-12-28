var myapp = angular
	.module("myapp", [])
	.controller("traceQueryCount", function($scope, $http, $state, $timeout) {
		var vm = this;
		//获取用户信息、组织信息
		$scope.user = {};
		$scope.organization = {};

		$http({
			headers: {
				token: window.localStorage.getItem("token")
			},
			url: "/sofn-qry-web/subject/findCurrentUserByToken",
			method: "post"
		})
			.success(function(data) {
				$scope.user = data.user;
				$scope.organization = $scope.user.organization;
				//定义一个管辖区域，通过机构级别+区域来判断
                if ($scope.organization.orgLevel != 4) {
                    $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
                }else {
                    $scope.underArea = $scope.organization.regionId;
                }
				//初始化区域下拉框初始选项
				$scope.reloadArea();
				//所属行业默认不限
				$scope.resetQuery();
				$scope.loadGrid();
			})
			.error(function(data) {
				
			});

		$scope.resetQuery = function() {
			$("input[type='checkbox']:checked").attr("checked", false);
			$("input[name='entityIndustryLimit']").prop("checked", true);
			$("input[name='countTimeLimit']").prop("checked", true);
			$("input[name='dateCreateBegin']").attr("disabled", "disabled");
			$("input[name='dateCreateEnd']").attr("disabled", "disabled");
			$("input[name='dateCreateBegin']").val("");
			$("input[name='dateCreateEnd']").val("");
			$("#productSort").val("");
			$("input[name='keyWord']").val("");
			$scope.reloadArea();
		};
		//重置区域
		$scope.reloadArea = function() {
			if ($scope.organization.orgLevel != "1") {
				$.fn.mycity("sheng", "shi", "xian", $scope.underArea);
				if (
					$("#sheng").val() != null &&
					$("#sheng").val() != "" &&
					$("#sheng").val() != undefined
				) {
					$("#sheng").attr("disabled", true);
				}
				if (
					$("#shi").val() != null &&
					$("#shi").val() != "" &&
					$("#shi").val() != undefined
				) {
					$("#shi").attr("disabled", true);
				}
				if (
					$("#xian").val() != null &&
					$("#xian").val() != "" &&
					$("#xian").val() != undefined
				) {
					$("#xian").attr("disabled", true);
				}
			} else {
				$.fn.mycity("sheng", "shi", "xian");
			}
		};

		$scope.getAreaBySelect = function() {
			var province = $("#sheng").val();
			var city = $("#shi").val();
			var county = $("#xian").val();
			var areaId = "";
			if (county != null && county != undefined && county != "") {
				areaId = county;
			} else if (city != null && city != undefined && city != "") {
				areaId = city;
			} else if (
				province != null &&
				province != undefined &&
				province != ""
			) {
				areaId = province;
			}
			return areaId;
		};

		//获取行业数据字典
		$scope.industryType = [];
		$http
			.post("/sofn-qry-web/sysDict/getSysDictByCode", "2", {
				headers: {
					token: window.localStorage.getItem("token")
				},
				dataType: "json",
				contentType: "application/json"
			})
			.then(
				function(res) {
					$scope.industryType = JSON.parse(res.data.data);
				},
				function(res) {
					
				}
			);

		//获取产品种类列表
		$scope.productSort = [];
		$http({
			headers: {
				token: window.localStorage.getItem("token")
			},
			url: "/sofn-qry-web/traceQuery/getProSortAllList",
			method: "post"
		}).success(function(data) {
			$scope.productSort = data;
		});

		//控制复选框
		$scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
			var _limChec = $("input[name='" + limitCheckBoxName + "']:checked");
			var _lim = $("input[name='" + limitCheckBoxName + "']");
			var _chec = $("input[name='" + checkBoxName + "']");
			for (var i = 0; i < _lim.length; i++) {
				var lim = _lim[i].checked;
				if (lim) {
					_chec.prop("checked", false);
				}
			}
			if (_limChec.length == 0) {
				_chec.prop("checked", true);
			}
			if (_limChec.length > 0 && _lim.length == _limChec.length) {
				_chec.prop("checked", true);
				_limChec.each(function(i, o) {
					$(o).prop("checked", false);
				});
			}
		};
		//控制复选框--不限
		$scope.limitCheckBoxAll = function(checkBoxName, limitCheckBoxName) {
			var _lim = $("input[name='" + limitCheckBoxName + "']");
			var _chec = $("input[name='" + checkBoxName + "']");
			for (var i = 0; i < _lim.length; i++) {
				var lim = _lim[i].checked;
				if (lim) {
					_chec.prop("checked", false);
				}
			}
		};

		$scope.limitCountTime = function() {
			if (
				$("input[type='checkbox'][name='countTimeLimit']").is(
					":checked"
				)
			) {
				$("input[name='dateCreateBegin']").attr("disabled", "disabled");
				$("input[name='dateCreateEnd']").attr("disabled", "disabled");
			} else {
				$("input[name='dateCreateBegin']").removeAttr("disabled");
				$("input[name='dateCreateEnd']").removeAttr("disabled");
			}
		};

		$scope.loadGrid = function() {
			$("#data-grid").dataTable({
				ajax: {
					url: "/sofn-qry-web/traceQuery/getProTraceQueryStatisList",
					dataSrc: function(json) {
						if (
							json.data.page == null ||
							json.data.page == undefined
						) {
							json.data.page.recordsTotal = 0; //总个数
							json.data.page.recordsFiltered = 0;
							json.data.page.pageNumber = 1; //页码
							json.data.page.pageSize = 10; //每页个数
							json.data.page.start = 0; //起始位置
							json.data.page.length = 10;
							return json.data.page.list;
						}
						json.recordsTotal = json.data.page.total; //总个数
						json.recordsFiltered = json.data.page.total;
						json.pageNumber = json.data.page.pageNum; //页码
						json.pageSize = json.data.page.pageSize; //每页个数
						json.start =
							(json.data.page.pageNum - 1) *
							json.data.page.pageSize; //起始位置
						json.length = json.data.page.pageSize;
						$scope.$apply(function() {
							$scope.tableTotal = json.data.allBathCount || 0;
						});
						return json.data.page.list;
					},
					headers: {
						token: window.localStorage.getItem("token")
					},
					data: function(params) {
						var entityIndustry = $(
							"input[name='entityIndustry']:checked"
						);
						var arr = new Array();
						for (var i = 0; i < entityIndustry.length; i++) {
							arr.push($(entityIndustry[i]).val());
						}
						params.industry = arr.toString();
						if (
							!$(
								"input[type='checkbox'][name='countTimeLimit']"
							).is(":checked")
						) {
							params.startDate = $(
								"input[name='dateCreateBegin']"
							).val();
							params.endDate = $(
								"input[name='dateCreateEnd']"
							).val();
						}
						params.area = $scope.getAreaBySelect();
						params.keyWord = $.trim(
							$("input[name='keyWord']").val()
						);
						params.productSort = $("#productSort").val();
                        $scope.chartParams = params; //图标参数赋值
                        if (vm.chart) {
                            vm.chart.update(); //刷新图标
                        } else {
                            $timeout(function () {
                                vm.chart.update(); //刷新图标
                            }, 1000);
                        }
					},
					type: "post"
				},
				columns: [
					{
						data: "ID",
						title: "ID",
						visible: false
					},
					{
						title: "序号",
						data: function(data, type, row, meta) {
							return meta.row + 1;
						}
					},
					{
						data: "PRODUCT_NAME",
						title: "产品名称",
						render: function(data, type, row) {
							if (
								data != null &&
								data != undefined &&
								data.length > 42
							) {
								return (
									"<a id='RMNAME" +
									row.ID +
									"' onmouseover=showPopContent('RMNAME" +
									row.ID +
									"','" +
									data +
									"')>" +
									data.substring(0, 35) +
									"...</a>"
								);
							} else {
								return data;
							}
						}
					},
					{
						data: "PRODUCT_SORT",
						title: "产品种类"
					},
					{
						data: "BATCH_COUNT",
						title: "生产批次总数"
					},
					{
						data: "AREA",
						title: "所属区域",
						render: function(data, type, row) {
							return $.fn.Cityname(data);
						}
					},
					{
						data: "ID",
						title: "操作",
						render: function(data, type, row, meta) {
							return (
								"<a href=\"javascript:showDetailQryInfo('" +
								meta.row +
								"')\">查看详情</a>"
							);
						}
					}
				]
			});
		};

		$scope.queryDataTable = function() {
            //点击间隔
            $scope.btnDisabled = true;
            setTimeout(function(){
                $scope.$apply($scope.btnDisabled = false);
            }, 500);
			$("#data-grid")
				.dataTable()
				.api()
				.ajax.reload();
		};

		$scope.loadDeatilQryInfo = function() {
			if ($scope.row != null) {
				$scope.detailQryInfoTable = $("#detail-data-gird").dataTable({
					ajax: {
						url: "/sofn-qry-web/traceQuery/getProBathListByCon",
						dataSrc: function(json) {
							json.recordsTotal = json.data.total; //总个数
							json.recordsFiltered = json.data.total;
							json.pageNumber = json.data.pageNum; //页码
							json.pageSize = json.data.pageSize; //每页个数
							json.start =
								(json.data.pageNum - 1) * json.data.pageSize; //起始位置
							json.length = json.data.pageSize;
							return json.data.list;
						},
						headers: {
							token: window.localStorage.getItem("token")
						},
						data: function(params) {
							var entityIndustry = $(
								"input[name='entityIndustry']:checked"
							);
							var arr = new Array();
							for (var i = 0; i < entityIndustry.length; i++) {
								arr.push($(entityIndustry[i]).val());
							}
							params.industry = arr.toString();
							params.productSort = $scope.row.PRODUCT_SORT;
							params.productName = $scope.row.PRODUCT_NAME;
							params.area = $scope.row.AREA;
							params.batchCount = $scope.row.BATCH_COUNT;
							params.keyWord = $.trim($("#keyWordPlus").val());
							if (
								!$(
									"input[type='checkbox'][name='countTimeLimit']"
								).is(":checked")
							) {
								params.startDate = $(
									"input[name='dateCreateBegin']"
								).val();
								params.endDate = $(
									"input[name='dateCreateEnd']"
								).val();
							}
						},
						type: "post"
					},
					columns: [
						{
							title: "序号",
							data: function(data, type, row, meta) {
								return meta.row + 1;
							}
						},
						{
							data: "PRODUCT_NAME",
							title: "产品名称"
						},
						{
							data: "PRODUCT_PC",
							title: "产品追溯码",
							render: function(data, type, row) {
								if (
									row.PRODUCT_SOURCE == "采购" &&
									row.BILL_STRATRUS != "1"
								) {
									return row.FROMZSM == null
										? "无"
										: row.FROMZSM;
								} else {
									return data;
								}
							}
						},
						{
							data: "PRODUCT_SORT",
							title: "产品种类"
						},
						{
							data: "PRODUCT_AMOUNT",
							title: "收货数量"
						},
						{
							data: "PRODUCT_SOURCE",
							title: "产品来源"
						},
						{
							data: "HARVEST_TIME",
							title: "收货时间",
							render: function(data, type, row) {
								if (
									data != null &&
									data != "" &&
									data != undefined
								) {
									return new Date(parseInt(data) + 28800000)
										.toISOString()
										.slice(0, 10);
								}
							}
						},
						{
							data: "MEDI_CHECK",
							title: "质检情况"
						},
						{
							data: "PROOF_NUMBER",
							title: "动物检疫合格证号",
							render: function(data, type, row) {
								return data == null ? "无" : data;
							}
						},
						{
							data: "SPYB_TYPE",
							title: "认证信息",
							render: function(data, type, row) {
								if(data != null){
									var list = data.split(',');
									var strs = [];
									for(var i=0; i<list.length; i++){
										if(list[i] == "wgh"){
											strs.push("无公害农产品");
										} else if(list[i] == "youji"){
											strs.push("有机食品");
										} else if(list[i] == "lvse"){
											strs.push("绿色食品");
										}
									}
                                    if(strs.length >0){
                                        return	strs.join(",");
                                    }
									return	"无";
								}
								return "无";
							}
						}
					]
				});
			}
		};

		window.reloadDetailQryData = function() {
            //点击间隔
            $scope.$apply($scope.btnDisabled = true);
            setTimeout(function(){
                $scope.$apply($scope.btnDisabled = false);
            }, 500);
			$scope.detailQryInfoTable.api().ajax.reload();
		};

		window.showDetailQryInfo = function(index) {
			$("#keyWordPlus").val("");
			var rows = $("#data-grid")
				.DataTable()
				.rows()
				.data();
			$scope.row = rows[index];
			if ($("#detail-data-gird").hasClass("dataTable")) {
				$scope.detailQryInfoTable.api().ajax.reload();
			} else {
				$scope.loadDeatilQryInfo();
			}
			$("#myModal").modal("show");
		};

		//导出Excel
		$scope.exportData = function() {
			var form = $("<form id='exportForm'>");
			form.attr("style", "display:none");
			form.attr("target", "");
			form.attr("method", "post");
			form.attr(
				"action",
				"/sofn-qry-web/traceQuery/exportProTraceQueryStatisList"
			);
			var token = window.localStorage.getItem("token");
			var input1 = $("<input>");
			input1.attr("type", "hidden");
			input1.attr("name", "token");
			input1.attr("value", token);

			var entityIndustry = $("input[name='entityIndustry']:checked");
			var arr = new Array();
			for (var i = 0; i < entityIndustry.length; i++) {
				arr.push($(entityIndustry[i]).val());
			}
			var input2 = $("<input>");
			input2.attr("type", "hidden");
			input2.attr("name", "industry");
			input2.attr("value", arr.toString());

			var area = $scope.getAreaBySelect();
			var input3 = $("<input>");
			input3.attr("type", "hidden");
			input3.attr("name", "area");
			input3.attr("value", area);

			var productSort = $("#productSort").val();
			var input4 = $("<input>");
			input4.attr("type", "hidden");
			input4.attr("name", "productSort");
			input4.attr("value", productSort);

			var keyWord = $.trim($("input[name='keyWord']").val());
			var input5 = $("<input>");
			input5.attr("type", "hidden");
			input5.attr("name", "keyWord");
			input5.attr("value", keyWord);

			if (
				!$("input[type='checkbox'][name='countTimeLimit']").is(
					":checked"
				)
			) {
				var startDate = $("input[name='dateCreateBegin']").val();
				var endDate = $("input[name='dateCreateEnd']").val();

				var input5 = $("<input>");
				input5.attr("type", "hidden");
				input5.attr("name", "startDate");
				input5.attr("value", startDate);

				var input6 = $("<input>");
				input6.attr("type", "hidden");
				input6.attr("name", "endDate");
				input6.attr("value", endDate);
			}

			$("body").append(form);
			form.append(input1);
			form.append(input2);
			form.append(input3);
			form.append(input4);
			form.append(input5);
			form.append(input6);
			form.submit();
			$("#exportForm").remove();
		};

		//鼠标悬停弹窗显示
		window.showPopContent = function(id, content) {
			$("#" + id).popover({
				animation: true,
				html: false,
				placement: "right",
				trigger: "hover",
				content: content,
				delay: 0,
				container: "body"
			});
			$("#" + id).popover("show");
		};

        /**************************
         CUSTOM QUERY CONDITION
         *************************/

        $scope.confirmCondition = function() {
            var modal = $("#modalCondition");
            modal
                .find(".gui-list li")
                .not(".disabled")
                .map(function() {
                    var key = $.trim($(this).attr("data-key"));
                    var checked = $(this)
                        .find(":checkbox")
                        .is(":checked");
                    if (!checked) {
                        var control = $(
                            '.gui-form-group .condition-custom[ng-show="visible' +
                            key +
                            '"]'
                        );
                        var controlType = control.attr("data-control");
                        switch (controlType) {
                            case "text":
                                $scope[key] = "";
                                break;
                            case "select":
                                $scope[key] = "";
                                control
                                    .find("option:first")
                                    .prop("selected", true)
                                    .trigger("change");
                                break;
                            case "checkbox":
                                $scope[key] = "";
                                control
                                    .find(":checkbox")
                                    .first()
                                    .prop("checked", true)
                                    .end()
                                    .siblings()
                                    .prop("checked", false);
                                break;
                            case "date":
                                control
                                    .find("input:text")
                                    .val("")
                                    .trigger("input");
                                break;
                            case "radio":
                                break;
                        }
                    }
                    $scope["visible" + key] = checked;
                    //切换表格列是否可见
                    var table = $("#data-grid").DataTable();
                    var column = table.column(key + ":name");
                    column.visible(checked);
                });
            modal.modal("hide");
        };
        $("#modalCondition")
            .on("change", ".condition-checked-all", function() {
                var checked = $(this).is(":checked");
                $("#modalCondition")
                    .find("li")
                    .not(".disabled")
                    .find(":checkbox")
                    .prop("checked", checked);
            })
            .on("change", ".gui-list :checkbox", function() {
                var unchecked = $(this)
                    .closest(".gui-list")
                    .find(":checkbox")
                    .filter(function() {
                        return !$(this).is(":checked");
                    });
                $(".condition-checked-all").prop("checked", !!!unchecked.length);
            })
            .on("shown.bs.modal", function() {
                $(".gui-form-group .condition-custom").each(function() {
                    var visible = $(this).is(":visible");
                    var key = $(this)
                        .attr("ng-show")
                        .replace("visible", "");
                    $(".gui-list.list-condition")
                        .find('li[data-key="' + key + '"]')
                        .find(":checkbox")
                        .prop("checked", visible)
                        .trigger("change");
                });
            });

        /************************************************
         ******* chart *******
         ************************************************/

        //视图展示切换
        $scope.isListView = true;
        $scope.toggleView = function(isListView) {
            $scope.isListView = isListView;
            $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
            $(window).trigger("resize");
        };

        //图表参数配置
        vm.chart = {};
        vm.chartOptions = {
            //接口地址
            url: "/sofn-qry-web/traceQuery/getProTraceQueryStatis",
            grid: [1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };

	});
