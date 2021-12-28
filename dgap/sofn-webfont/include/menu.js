var myapp = angular.module("myapp", ["ui.router", "oc.lazyLoad"]);
myapp.controller("menu", function($scope, $http, $state) {
    //        easyui
    //�˵�����
    var menuUrlID = window.location.href;
    alert(menuUrlID)
    thismenuUrlID = (menuUrlID.split("/index/content/")[1]).split("/")[0]
    alert(thismenuUrlID)
    if (thismenuUrlID = "asms") {
        menuUrl = "asms_data.json";
    } else if (thismenuUrlID = ales) {
        menuUrl = "ales_data.json";
    } else if (thismenuUrlID = dgap) {
        menuUrl = "dgap_data.json";
    } else if (thismenuUrlID = tts) {
        menuUrl = "tts_data.json";
    } else if (thismenuUrlID = "sys") {
        menuUrl = "asms_data.json";
    }
    $("#tt").tree({
        //lines:true,
        url: "include/navData/" + menuUrl,
        onClick: function(node) {
            //����
            if (node.state == "closed") {
                $("#tt").tree("expand", node.target).parent().siblings().children().tree("collapse", node.target);
            } else {
                $("#tt").tree("collapse", node.target);
            }
            node.menuUrl != null && $state.go(node.menuUrl);
        },
        onLoadSuccess: function() { //���ظ��ڵ�
            $("#" + $('#tt').tree('getRoot').domId).hide();
        }
    });
    window.getSelected = function() {
        var node = $('#tt').tree('getSelected');
        if (node) {
            var s = node.url;
            if (node.attributes) {
                s += "," + node.attributes.p1 + "," + node.attributes.p2;
            }
            //alert(s);
        }
    }
});
