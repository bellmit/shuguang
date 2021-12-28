/**
 * Created by Administrator on 2016/11/10.
 */
var myapp = angular.module("myapp", []);
myapp.controller("forceQuery", function ($scope, $http, $state) {
    $scope.isShow = false;
    $scope.zsm = "";
    /*初始化echarts实例*/
    $scope.myChart = echarts.init(document.getElementById('myChart'));
    /*定义option*/
    $scope.option = {
        animationDuration: 1500,
        animationEasing: 'QuarticInOut',
        loadingOption: {
            text: '数据读取中...',
            effect: 'dynamicLine'
        },
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble'
        },
        /*title: {
         text: '流通信息',
         subtext: '',
         },*/
        legend: {
            x: 'left',
            data: ['流通主体', '入市主体']
        },
        tooltip: {
            formatter: function (params, ticket, callback) {
                var res = '';
                if (params.data.productBatchCode != null && params.data.productBatchCode != '') {
                    res += '产品批次码 : ' + params.data.productBatchCode + '<br/>';
                    res += '产品名称 : ' + params.data.productName + '<br/>';
                    res += '产品类别 : ' + params.data.productType + '<br/>';
                    // res += '销售数量 : ' + params.data.salesNum + ' ' + params.data.salesUnit + '<br/>';
                    res += '质检情况 : ' + params.data.qualityInspection + '<br/>';
                    res += '主体身份码 : ' + params.data.subjectIdentityCode + '<br/>';
                }
                res += '主体名称 : ' + params.data.subjectName + '<br/>';
                res += '主体地址 : ' + params.data.subjectAddress + '<br/>';
                res += '法人名称 : ' + params.data.legalName + '<br/>';
                res += '联系方式 : ' + params.data.contactInformation + '<br/>';

                return res;
            }
        },
        series: [{
            name: '力向图',
            type: 'force',
            linkSymbol: 'arrow',
            roam: true,
            symbol: 'circle',
            symbolSize: 15,
            center: ['50%', '50%'],
            size: '100%',
            categories: [
                {
                    name: '当前查询主体'
                },
                {
                    name: '流通主体'
                },
                {
                    name: '入市主体'
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position: 'bottom',
                        textStyle: {
                            color: '#333'
                        }
                    },
                    nodeStyle: {
                        brushType: 'both',
                        borderColor: '#5182ab',
                        borderWidth: 1
                    },
                    linkStyle: {
                        type: 'line',
                        color: '#5182ab',
                        width: 1
                    }
                },
                emphasis: {
                    label: {
                        show: true
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle: {
                        //r: 30
                    },
                    linkStyle: {}
                }
            },
            data: [],
            links: [],
        }]
    };
    /*查询*/
    $scope.query = function () {
        $scope.isShow = true;
        $scope.myChart.clear();
        // 判断输入的码段为追溯码还是批次码
        var type = $scope.zsm.substr(16, 1);

        if (type == "" || type == null) {
            jBox.tip("批次码或追溯码不能为空！", 'info');
            return;
        } else if (type != "2" && type != "3") {
            jBox.tip("批次码或追溯码格式错误！", 'info');
            return;
        }
        $.ajax({
            // url: "/sofn-qry-web/zsm/getForceDataByTraceCode/" + $scope.zsm,
            url: "/sofn-qry-web/traceQuery/getForceDataByTraceCode/" + $scope.zsm,
            type: 'post',//POST方式
            dataType: 'json',//返回json类型
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function (data) {
                if (!data.data) {
                    jBox.tip("不存在此记录！", 'info');
                    return;
                }
                $scope.option.series[0].data = data.data;
                $scope.option.series[0].links = data.links;
                $scope.myChart.setOption($scope.option);
            }
        });
    };
    /*清空*/
    $scope.clean = function () {
        $scope.zsm = "";
        // 图表清空
        $scope.myChart.clear();
    };
});