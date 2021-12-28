var myapp=angular.module("myapp",[]).controller("isNotCheck",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    var entity = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var reason = entity&&entity.approveOpinion?entity.approveOpinion:"";
    $("#reason").html("审核未通过，审核原因："+reason);

})

