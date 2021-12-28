var myapp=angular.module("myapp",[]);
myapp.controller("qryCheckSample",function($scope,$http,$state) {

    /**
     * public
     * */
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.organTaskId = window.localStorage.getItem("asms_sample_info_organTaskId");
    $scope.sampleId = window.localStorage.getItem("asms_sample_info_id");
    if ($scope.asms_Check_Task_Type_Title == 'JDCC'){
        $("#titleName").html("监督抽查");
        $scope.monitorClass = '监督抽查';
    }
    if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
        $("#titleName").html("例行监测");
        $scope.monitorClass = '例行监测';
    }
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC'){
        $("#titleName").html("专项检测");
        $scope.monitorClass = '专项检测';
    }
    if ($scope.asms_Check_Task_Type_Title == 'FJRW'){
        $("#titleName").html("复检任务");
        $scope.monitorClass = '复检任务';
    }

    $scope.cyxxid = window.localStorage.getItem("asms_sample_info_id");//抽样单id
    $scope.alesTaskSample = {};//抽样单信息
    $scope.alesTaskSample.sampleCode = "";

    $("input").attr("disabled","disabled");
    $("select").attr("disabled","disabled");
    //获取单位字典值
    var initSampleUnitTypes = function() {
        $scope.sampleUnitTypes = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "9",
            method: "post"
        }).success(function (data) {
            $scope.a = JSON.parse(data.data);
            $scope.sampleUnitTypes = [];
            var arr = new Array();
            //var idArr = new Array();
            for(var i=0;i<$scope.a.length;i++){
                arr.push($scope.a[i]);
                //idArr.push($scope.a[i].dictName);
            }
            //$("select[name='sampleUnitType'] option:first").val(idArr.toString());
            $scope.sampleUnitTypes = arr;
        }).error(function(data){
            
        })
    }
    /**
     * 信息展示
     * */
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/monitorTask/getTaskSampleById",
        method:"post",
        data: {"sampleCode": $scope.sampleId, "organTaskId":$scope.organTaskId}
    }).success(function(data){
        $scope.data = data.data;
        $scope.produceDate = data.data.PRODUCE_DATE;
        $scope.sampleDate = data.data.SAMPLE_DATE;
        $scope.FILE_ADDRESS = data.data.FILE_ADDRESS;
        if($scope.FILE_ADDRESS!=null&&$scope.FILE_ADDRESS!=undefined&&$scope.FILE_ADDRESS!="") {
            var array = $scope.FILE_ADDRESS.split("*##*");
            for (var i = 0; i < array.length; i++) {
                // $("#imgSpot").append('<img style="padding-right: 10px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                $("#imgSpot").append('<a data-fancybox href="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/> </a>');

            }
        }
        if( data.data.PRODUCTIONS_TATUS == "自产"){
            $(".sampleSource").css("display","none");
            $(".selfProduced").attr("checked",true);

        }else{
            $(".sampleSource").css("display","block");
            $(".notSelfProduced").attr("checked",true);
        };


        //二维码
        // star = toUtf8($scope.sampleId);
        // $("#code").qrcode({
        //     render: "table", //table方式
        //     width: 90, //宽度
        //     height:90, //高度
        //     text:star //任意内容
        // });
    }).error(function(data){
        
    }).then(initSampleUnitTypes);

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
    $scope.back = function(){
        $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if($scope.asms_Check_Task_Type_Title == "LXJC"){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "ZXJC"){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "JDCC"){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }else{
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }
    }
});


