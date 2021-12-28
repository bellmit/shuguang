var myapp = angular.module("myapp", []);
myapp.controller("changeConfig", function($scope, $http, $state) {
    $(".addConfig").on("click",function(){
    	// console.log($(this).parent(".mt15"));
    	var $cloneNode = $("#cloneNode");
    	var newNode = $cloneNode.clone(true);
    	$(this).parent(".mt15").after(newNode.attr("id",""));

    })
    $(".removeConfig").on("click",function(){
    	$(this).parent(".mt15").remove();
    })
})
