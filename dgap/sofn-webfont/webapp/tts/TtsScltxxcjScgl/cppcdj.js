var myapp=angular.module("myapp",[]).controller("cppcdj",function($scope,$http,$state){

    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entityIndustry;
    if(userdata){
        entityIndustry = userdata.entityIndustry;
    }
    /**
     * 根据行业类型跳转到不同的产品登记页面
     */
    $.ajax({
        url:"/sofn-tts-web/ttsScltxxcjScgl/getIndustry",
        type:'POST',//POST方式
        dataType:'json',//返回json类型
        data:{
            id:entityIndustry
        },
        header:{
            token : window.localStorage.getItem("token")
        },
        success:function(d){
            var length = Object.keys(d).length;
            if(length == 1){
                for(var key in d){
                    if(2 == key){
                        $state.go("index.content.tts/TtsScltxxcjScgl/scpcdj");
                    }
                    if(1 == key){
                        $state.go("index.content.tts/TtsScltxxcjScgl/xmpcdj");
                    }
                    if(0 == key){
                        $state.go("index.content.tts/TtsScltxxcjScgl/zzpcdj");
                    }
                }
            }
            var target=[];
            var str;
            if(length == 2){
                for(var key in d){
                    target.push(key);
                }
                str = target.join(",");
                if(str == "0,1" || str == "1,0"){
                    $state.go("index.content.tts/TtsScltxxcjScgl/xmpcdj");
                }
                if(str == "2,1" || str == "1,2"){
                    $state.go("index.content.tts/TtsScltxxcjScgl/scpcdj");
                }
                if(str == "0,2" || str == "2,0"){
                    $state.go("index.content.tts/TtsScltxxcjScgl/scpcdj");
                }
            }
            if(length == 3){
                for(var key in d){
                    if(2 == key){
                        $state.go("index.content.tts/TtsScltxxcjScgl/scpcdj");
                    }
                }
            }
        }
    });
})