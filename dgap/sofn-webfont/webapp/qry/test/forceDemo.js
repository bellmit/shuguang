var myapp = angular.module("myapp", []);
myapp.controller("forceDemo", function($scope, $http, $state) {

    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        // title : {
        //     text: '人物关系：乔布斯',
        //     subtext: '数据来自人立方',
        //     x:'right',
        //     y:'bottom'
        // },
        tooltip : {
            trigger: 'item',
            formatter: '{a} : {b}'
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        legend: {
            x: 'left',
            data:['生产主体','经营主体']
        },
        series : [
            {
                type:'force',
                name : "",
                ribbonType: false,
                categories : [
                    {
                        name: '人物'
                    },
                    {
                        name: '生产主体',
                        symbol: 'diamond'
                    },
                    {
                        name:'经营主体'
                    }
                ],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#333'
                            }
                        },
                        nodeStyle : {
                            brushType : 'both',
                            borderColor : 'rgba(255,215,0,0.4)',
                            borderWidth : 1
                        }
                    },
                    emphasis: {
                        label: {
                            show: false
                            // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                        },
                        nodeStyle : {
                            //r: 30
                        },
                        linkStyle : {}
                    }
                },
                minRadius : 15,
                maxRadius : 25,
                gravity: 1.1,
                scaling: 1.2,
                draggable: false,
                linkSymbol: 'arrow',
                steps: 10,
                coolDown: 0.9,
                preventOverlap: true,
                nodes:[
                    {
                        category:1, name: '第一内容生产经营主体', value : 10,draggable: true
                    },
                    {category:2,draggable: true, name: '第二内容生产经营主体',value : 8},
                    {category:1,draggable: true, name: '第三内容生产经营主体',value : 10},
                    {category:2,draggable: true, name: '第四内容生产经营主体',value : 8},
                    {category:2,draggable: true, name: '第五内容生产经营主体',value : 8},
                    {category:2,draggable: true, name: '第六内容生产经营主体',value : 10}
                ],
                links : [
                    {source : '第一内容生产经营主体', target : '第二内容生产经营主体', weight : 1, itemStyle: {
                        normal: {
                            width: 1.5,
                            color: 'red'
                        }
                    }},
                    {source : '第三内容生产经营主体', target : '第二内容生产经营主体', weight : 1, itemStyle: {
                        normal: { color: 'red' }
                    }},
                    {source : '第二内容生产经营主体', target : '第四内容生产经营主体', weight : 1},
                    {source : '第二内容生产经营主体', target : '第五内容生产经营主体', weight : 1},
                    {source : '第四内容生产经营主体', target : '第六内容生产经营主体', weight : 1},
                    {source : '第五内容生产经营主体', target : '第六内容生产经营主体', weight : 1}
                ]
            }
        ]
    };

    myChart.setOption(option);
})
