$.when(areaDtd).then(function() {
	angular.bootstrap(document, ["myapp"]);
	//查询升级信息
	function requestUprade() {
		$.ajax({
			url: "/sofn-sys-web/document/queryList",
			type: "post",
			data: {
				// token: window.localStorage.getItem("token"),
				start: 0,
				length: 1,
				type: "系统升级",
				applyTo: "sys",
				enableFlag: "Y"
			}
		})
			.done(function(res) {
				if(res.httpCode != 200){
					swal("系统提示", "升级接口查询失败", "error");
					return;
				}
				// if (res.data && res.data.list.length) {
				// 	requestUpradeText(res.data.list[0].id);
				// }
			})
			.fail(function(err) {
				swal("系统提示", "升级接口查询异常", "error");
			});
	}

	function requestUpradeText(id) {
		$.ajax({
			url: "/sofn-sys-web/document/queryOne",
			type: "post",
			data: {
				// token: window.localStorage.getItem("token"),
				id: id
			}
		})
			.done(function(res) {
				if(res.httpCode != 200){
					swal("系统提示", "升级信息接口查询失败", "error");
					return;
				}
				$(".gui-upgrade")
					.find(".upgrade-content")
					.html(res.data.content)
					.end()
					.removeClass("gui-hide");
			})
			.fail(function(err) {
				swal("系统提示", "升级信息接口查询异常", "error");
			});
	}
	requestUprade();
	$(document).on( 'click', '.gui-upgrade .btn-close', function(){
		$(this).closest('.gui-upgrade').addClass('gui-hide');
	});
});
