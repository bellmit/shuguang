var myapp = angular.module("myapp", ['720kb.tooltips']);
myapp.controller("search", function($scope, $http, $state, $timeout) {
    $scope.query = function() {

    };
    var tree = {
        "rootDown": {
            "id": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "superId": null,
            "productBatchCode": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "productName": "稻谷",
            "productType": "稻",
            "salesNum": null,
            "salesUnit": null,
            "qualityInspection": "合格",
            "subjectIdentityCode": "1.2.156.326.8.1.0.0.0.182456793321456782",
            "subjectName": "授权测试11111",
            "subjectAddress": "天晖中街曙光过节56号",
            "legalName": "授权",
            "contactInformation": "18888888888",
            "name": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "label": "授权测试11111",
            "category": 0,
            "children": [{
                    "id": "24467b2fb5f14a5298f4fd46c718dbc2a50224ca80db4779ba3596f14e4e8685",
                    "superId": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
                    "productBatchCode": "24467b2fb5f14a5298f4fd46c718dbc2a50224ca80db4779ba3596f14e4e8685",
                    "productName": "稻谷",
                    "productType": "稻",
                    "salesNum": null,
                    "salesUnit": null,
                    "qualityInspection": null,
                    "subjectIdentityCode": null,
                    "subjectName": "成都绿康佳农业发展有限公司",
                    "subjectAddress": "中和镇双龙社区龙祥佳苑",
                    "legalName": "追溯001",
                    "contactInformation": "18802343688",
                    "name": "24467b2fb5f14a5298f4fd46c718dbc2a50224ca80db4779ba3596f14e4e8685",
                    "label": "成都绿康佳农业发展有限公司",
                    "category": 2,
                    "children": []
                },
                {
                    "id": "d9184c42ad1243688826c5cbd0caa5248ecc8b8de5a0443dbc834edccebd9f49",
                    "superId": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
                    "productBatchCode": "d9184c42ad1243688826c5cbd0caa5248ecc8b8de5a0443dbc834edccebd9f49",
                    "productName": "稻谷",
                    "productType": "稻",
                    "salesNum": null,
                    "salesUnit": null,
                    "qualityInspection": null,
                    "subjectIdentityCode": null,
                    "subjectName": "成都市绿康源智慧农业发展有限公司",
                    "subjectAddress": "双流县中和镇双龙社区",
                    "legalName": "trace002",
                    "contactInformation": "15288886666",
                    "name": "d9184c42ad1243688826c5cbd0caa5248ecc8b8de5a0443dbc834edccebd9f49",
                    "label": "成都市绿康源智慧农业发展有限公司",
                    "category": 2,
                    "children": []
                },
                {
                    "id": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510105618",
                    "superId": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
                    "productBatchCode": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510105618",
                    "productName": "稻谷",
                    "productType": "稻",
                    "salesNum": null,
                    "salesUnit": null,
                    "qualityInspection": "合格",
                    "subjectIdentityCode": "1.2.156.326.8.1.0.0.0.852741963369852147",
                    "subjectName": "成都市绿康源智慧农业发展有限公司",
                    "subjectAddress": "双流县中和镇双龙社区",
                    "legalName": "trace002",
                    "contactInformation": "15288886666",
                    "name": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510105618",
                    "label": "成都市绿康源智慧农业发展有限公司",
                    "category": 1,
                    "children": [{
                            "id": "c52364ed2e244feeb23880c398cff17a98eb281656af4bf2afc41fec07e159ef",
                            "superId": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510105618",
                            "productBatchCode": "c52364ed2e244feeb23880c398cff17a98eb281656af4bf2afc41fec07e159ef",
                            "productName": "稻谷",
                            "productType": "稻",
                            "salesNum": null,
                            "salesUnit": null,
                            "qualityInspection": null,
                            "subjectIdentityCode": null,
                            "subjectName": "农贸市场",
                            "subjectAddress": "成都郫县",
                            "legalName": "李晓然",
                            "contactInformation": "18780154241",
                            "name": "c52364ed2e244feeb23880c398cff17a98eb281656af4bf2afc41fec07e159ef",
                            "label": "农贸市场",
                            "category": 2,
                            "children": []
                        },
                        {
                            "id": "df5f098f06bd43ac902379fd00aeb920cb96affa6bd841719c84b24349bc164b",
                            "superId": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510105618",
                            "productBatchCode": "df5f098f06bd43ac902379fd00aeb920cb96affa6bd841719c84b24349bc164b",
                            "productName": "稻谷",
                            "productType": "稻",
                            "salesNum": null,
                            "salesUnit": null,
                            "qualityInspection": null,
                            "subjectIdentityCode": null,
                            "subjectName": "成都牛肉干加工厂",
                            "subjectAddress": "锦江区锦江大道2511",
                            "legalName": "徐玉",
                            "contactInformation": "18726743081",
                            "name": "df5f098f06bd43ac902379fd00aeb920cb96affa6bd841719c84b24349bc164b",
                            "label": "成都牛肉干加工厂",
                            "category": 2,
                            "children": []
                        }
                    ]
                }
            ]
        },
        "rootUp": {
            "id": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "superId": null,
            "productBatchCode": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "productName": "稻谷",
            "productType": "稻",
            "salesNum": null,
            "salesUnit": null,
            "qualityInspection": "合格",
            "subjectIdentityCode": "1.2.156.326.8.1.0.0.0.182456793321456782",
            "subjectName": "授权测试11111",
            "subjectAddress": "天晖中街曙光过节56号",
            "legalName": "授权",
            "contactInformation": "18888888888",
            "name": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
            "label": "授权测试11111",
            "category": 0,
            "children": [{
                    "id": "1.2.156.326.8.1.2.1.1.123456789987456321.01010101.20170510105304",
                    "superId": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
                    "productBatchCode": "1.2.156.326.8.1.2.1.1.123456789987456321.01010101.20170510105304",
                    "productName": "稻谷",
                    "productType": "稻",
                    "salesNum": null,
                    "salesUnit": null,
                    "qualityInspection": "合格",
                    "subjectIdentityCode": "1.2.156.326.8.1.0.0.0.123456789987456321",
                    "subjectName": "成都绿康佳农业发展有限公司",
                    "subjectAddress": "中和镇双龙社区龙祥佳苑",
                    "legalName": "trace001",
                    "contactInformation": "18223403699",
                    "name": "1.2.156.326.8.1.2.1.1.123456789987456321.01010101.20170510105304",
                    "label": "成都绿康佳农业发展有限公司",
                    "category": 3,
                    "children": []
                },
                {
                    "id": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510104931",
                    "superId": "1.2.156.326.8.1.2.1.1.182456793321456782.01010101.20170510105344",
                    "productBatchCode": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510104931",
                    "productName": "稻谷",
                    "productType": "稻",
                    "salesNum": null,
                    "salesUnit": null,
                    "qualityInspection": "合格",
                    "subjectIdentityCode": "1.2.156.326.8.1.0.0.0.852741963369852147",
                    "subjectName": "成都市绿康源智慧农业发展有限公司",
                    "subjectAddress": "双流县中和镇双龙社区",
                    "legalName": "trace002",
                    "contactInformation": "15288886666",
                    "name": "1.2.156.326.8.1.2.1.1.852741963369852147.01010101.20170510104931",
                    "label": "成都市绿康源智慧农业发展有限公司",
                    "category": 3,
                    "children": []
                }
            ]
        }
    };
    $scope.rootUp = tree.rootUp;
    $scope.rootDown = tree.rootDown;
    $timeout(function() {
        var width = 0;
        $('.tree-wrap').find('.ui-tree').each(function() {
            width += $(this).outerWidth();
        }).end().css({ width: width + 10 }).find('.ui-tree').removeClass('tree-hidden');
    }, 200);
    window.update = function() {
        var width = 0;
        $('.tree-wrap').find('.ui-tree').each(function() {
            width += $(this).outerWidth();
        }).end().css({ width: width + 10 }).find('.ui-tree').removeClass('tree-hidden');
    }
});