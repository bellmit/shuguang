angular.module("myapp", []).controller("printSubjEntCreditInfo", function ($scope, $http, $state) {
    $scope.entityCode = window.localStorage.getItem('entityCode');
    //监视localstorage更新数据
    window.addEventListener('storage', function (e) {
        if (e.key === 'entityCode') {
            $scope.$apply(function () {
                console.log('storage changed!', e);
                $scope.entityCode = window.localStorage.getItem('entityCode');
                $scope.request();
            })
        }
    });
    //获取生产经营主体(包括临时主体)及其不良记录列表
    $scope.request = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEntBadrecord/findSubjEntAndBadRecodeListByEntityCode",
            params: {
                entityIdCode: $scope.entityCode
            },
            method: "POST"
        }).then(function (result) {
            $scope.entityInfo = result.data.data.subjEnt;
            //处理区域
            if ($scope.entityInfo.area || $scope.entityInfo.areaId) {
                $scope.entityInfo.area = $.fn.Cityname($scope.entityInfo.area || $scope.entityInfo.areaId);
            }
            //不良记录列表
            $scope.badRecodeList = result.data.data.badRecodeList;
            //表示是否为临时主体
            $scope.tempFlag = result.data.data.tempFlag;
            //处理图片
            $scope.handlePicUrl($scope.entityInfo);
            //处理不良记录
            $scope.handleBadRecode($scope.badRecodeList);
        });
    };
    //加载时直接调用
    $scope.request();

    //处理不良记录显示
    $scope.handleBadRecode = function (list) {
        for (var i = 0; i < list.length; i++) {
            list[i].rowNum = i + 1;
            if (list[i].createTime) {
                list[i].createTime = new Date(parseInt(list[i].createTime) + 28800000).toISOString().slice(0, 10);
            }
            if (list[i].sourceType) {
                var data = list[i].sourceType;
                if (data === "1") {
                    list[i].sourceType = "基地巡查"
                } else if (data === "2") {
                    list[i].sourceType = "行政处罚"
                } else if (data === "3") {
                    list[i].sourceType = "抽样检测"
                } else if (data === "4") {
                    list[i].sourceType = "现场巡查"
                }
            }
        }
    };
    //处理图片显示
    $scope.handlePicUrl = function (o) {
        //特殊处理excel导入主体,无图片情况
        $scope.importPicUrl = "/sofn-sys-web/sysTemplate/downPic?fileUrl=系统批量导入待修改";
        var picArray = ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"];
        var PrefixUrl = "/sofn-sys-web/sysTemplate/downPic?fileUrl=";
        var exceptionIp = "//202.127.45.189";
        for (var i = 0; i < picArray.length; i++) {
            if (o[picArray[i]]) {
                if (o[picArray[i]].indexOf(PrefixUrl) !== -1) {
                    break;
                }
                if (o[picArray[i]].indexOf(exceptionIp) === -1) {
                    o[picArray[i]] = PrefixUrl + o[picArray[i]];
                } else if (o[picArray[i]].indexOf("http") === -1){
                    o[picArray[i]] = "http:" + o[picArray[i]];
                }
            }
        }
    };

    //打印
    $scope.openPrint = function () {
        swal(
            {
                title: "打印中...",
                text: "正在打印中...请稍后!",
                type: "success",
                timer: 600,
                showConfirmButton: false
            },
            function() {
                swal.close();
            }
        );
        //去除head中的样式干扰
        $("#printHtml").printArea({removeHead: true});
    };
});



