/**
 * Created by Administrator on 2016/11/10.
 */
var myapp = angular.module("myapp", []);
myapp.controller("treeQuery", function($scope, $http, $state) {
    $scope.isShow = false;
    $scope.zsm = "";
    /*初始化echarts实例*/
    //上游
    $scope.upstreamChart = echarts.init(document.getElementById('upstreamChart'));
    //下游
    $scope.downstreamChart = echarts.init(document.getElementById('downstreamChart'));
    /*定义option*/
    $scope.option = {
        title: {
            text: ''
        },
        tooltip: {
            formatter: function(params, ticket, callback) {
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
            name: '树图',
            type: 'tree',
            orient: 'horizontal', // vertical horizontal
            direction: '',
            rootLocation: { x: 'center', y: 'center' }, // 根节点位置  {x: 'center',y: 10}
            nodePadding: 20,
            symbol: 'circle',
            symbolSize: 30,
            roam: true,
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position: 'bottom',
                        textStyle: {
                            color: '#333'
                        }
                    },
                    lineStyle: {
                        color: '#000',
                        width: 1,
                        type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                    }
                },
                emphasis: {
                    label: {
                        show: true
                    }
                }
            },
            data: []
        }]
    };
    /*查询*/
    $scope.query = function() {
        $scope.isShow = true;
        $scope.upstreamChart.clear();
        $scope.downstreamChart.clear();
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
            // url: "/sofn-qry-web/zsm/getByTrace/0/" + $scope.zsm,
            url: "/sofn-qry-web/traceQuery/getTreeDataByTraceCode/" + $scope.zsm,
            type: 'post', //POST方式
            dataType: 'json', //返回json类型
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function(data) {
                if (!data.rootUp && !data.rootDown) {
                    jBox.tip("不存在此记录！", 'info');
                    return;
                }
                if (data.rootUp) {
                    $scope.option.title.text = '上游';
                    $scope.option.series[0].direction = 'inverse'; //旋转方向
                    // $scope.option.series[0].rootLocation = {x: '90%', y: 'top'};//坐标位置
                    $scope.option.series[0].data[0] = data.rootUp;
                    $scope.upstreamChart.setOption($scope.option);
                }

                if (data.rootDown) {
                    $scope.option.title.text = '下游';
                    $scope.option.series[0].direction = ''; //旋转方向
                    // $scope.option.series[0].rootLocation = {x: '10%', y: 'top'};//坐标位置
                    $scope.option.series[0].data[0] = data.rootDown;
                    $scope.downstreamChart.setOption($scope.option);
                }
            }
        });
    };
    /*清空*/
    $scope.clean = function() {
        $scope.zsm = "";
        // 图表清空
        $scope.upstreamChart.clear();
        $scope.downstreamChart.clear();
    };
});