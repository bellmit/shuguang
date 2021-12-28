//菜单类
$(function (){
    //设置导航栏高度与背景颜色
    //浏览器窗口变化触发事件
    var a="",b="";
    //$(window).resize(function(){
    //     a=$(window).height();
    //     b=$(".header").height()
    //    var c=$(".right").height();
    //    var d=$(window).width()
    //        if($(".right").height()<(a-b)){
    //            $(".bgColor").css("height",(a-b));
    //        }if($(".right").height()>(a-b)){
    //            $(".bgColor").css("height",$(".right").height());
    //        }
    //});
   setInterval(function(){
       a=$(window).height();
       b=$(".header").height()
       var c=$(".right").height();
       var d=$(window).width()
           if($(".right").height()<(a-b)){
               $(".bgColor").css("height",(a-b));
           }if($(".right").height()>(a-b)){
               $(".bgColor").css("height",$(".right").height());
           }
   },0)

});

