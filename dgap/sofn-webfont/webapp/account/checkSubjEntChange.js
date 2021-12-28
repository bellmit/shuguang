var myapp=angular.module("myapp",[]);
myapp.controller("checkSubjEntChange",function($scope,$http,$state){
    $scope.subjSvChangeId=window.localStorage.getItem("subjSvChangeId");

	//获取用户信息
	$scope.user = {};
	$http({
		headers:{
			token:window.localStorage.getItem("token")
		},
		url:"/sofn-asms-web/subjSupervise/findCurrentUserByToken",
		method:"post"
	}).success(function(data){
		$scope.user=data.user;
		if($scope.user.userType=="SUPERADMIN"){
			$scope.user.userType="超级管理员";
		}else if($scope.user.userType=="SYSADMIN"){
			$scope.user.userType="用户管理员";
		}else if($scope.user.userType=="SUBJADMIN"){
			$scope.user.userType="主体管理员";
		}else if($scope.user.userType=="NORMAL"){
			$scope.user.userType="用户";
		}
		$scope.httpurl();
	}).error(function (data) {
		
	});

	//请求菜单数据
	$scope.httpurl = function() { //向后台发起请求获取数据
		var token = window.localStorage.getItem('token');
		return $http.post('/sofn-sys-web/ssoMenu/getMenusByUserId', {}, {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
				token: token
			}
		}).then(function(res) {
			var data = res.data;
			if (data.httpCode != 200) {
				jBox.tip(data.msg);
				return;
			}
			var systems = formattedSystems(data.data);
			$scope.systems = systems;
			return data;
		}, function(err) {

		})
	}

	function formattedSystems(data) {
		var systems = _.groupBy(data, 'menuValue');
		return systems;
	}

	$scope.sysName = function(name) {
		var menus = formattedMenuByName(formattedSystems(JSON.parse(window.localStorage.getItem("menus")))[name.toUpperCase()]);
		$scope.firstLink = getFirstLink(menus[0].children);
		$state.go($scope.firstLink);
	}
	function getFirstLink(menus) {
		menus.map(function(menu) {
			if (menu.children && menu.children.length) {
				getFirstLink(menu.children)
			} else if (menu.tab && menu.tab.length) {
				getFirstLink(menu.tab)
			} else {
				$scope.firstLink = $scope.firstLink ? $scope.firstLink : menu.url;
			}
		});
		return $scope.firstLink;
	}
	function formattedMenuByName(current) {
		var isRoot = false;
		var menus = [];
		_.each(current, function(menu) {
			if (menu.authority == 0) {
				return;
			}
			isRoot = !_.size(_.filter(current, function(item) {
				if (item.authority == 0) {
					return;
				}
				if (menu.parentId == item.id) {
					item.children = item.children || [];
					if (menu.menuType == 2) {
						item.tab = item.tab || [];
						item.tab[item.tab.length] = menu;
					} else {
						item.state = "closed";
						item.children[item.children.length] = menu;
					}
				}
				item.text = item.menuName;
				item.url = item.url != '#' ? item.url : null;
				return menu.parentId == item.id;
			}));
			menus = isRoot ? [menu] : menus;
		});
		if (menus.length) { menus[0].state = "open"; }
		var formatmenu = menus.length > 0 ? menus[0].children : null;
		//每次刷新Tab页，以防权限变动
		var flag = true;
		//一级菜单
		for (var i = 0; formatmenu != null && i < formatmenu.length && flag; i++) {
			//如果一级菜单就是包含Tab页
			if (formatmenu[i].tab != null && formatmenu[i].tab != undefined && formatmenu[i].tab.length > 0) {
				window.localStorage.removeItem("menuTabObj");
				window.localStorage.setItem("menuTabObj", JSON.stringify(formatmenu[i]));
				flag = false;
				break;
			}
			//如果二级菜单--包含Tab页，暂时只处理这两级
			for (var j = 0; formatmenu[i].children != null && j < formatmenu[i].children.length && flag; j++) {
				if (formatmenu[i].children[j].tab != null && formatmenu[i].children[j].tab != undefined && formatmenu[i].children[j].tab.length > 0) {
					window.localStorage.removeItem("menuTabObj");
					window.localStorage.setItem("menuTabObj", JSON.stringify(formatmenu[i].children[j]));
					flag = false;
				}
			}
		}
		return menus;
	}


	//获取变更详情
	$http({
		headers:{
			token:window.localStorage.getItem("token")
		},
		url:"/sofn-asms-web/subjSupervise/findSubjSvChangeById",
		method:"post",
		data:{id:$scope.subjSvChangeId}
	}).success(function(data){
		//得到变更前信息
		$scope.before=data.subjSv;
		//得到变更后信息
		$scope.after=data.subjSvChange;//得到变更后信息
		if($scope.after.svLeaderPhone==null||$scope.after.svLeaderPhone==undefined){
			$scope.after.svLeaderPhone = "";
		}
		if($scope.after.svContactQQ==null||$scope.after.svContactQQ==undefined){
			$scope.after.svContactQQ = "";
		}
		if($scope.after.svContactEmail==null||$scope.after.svContactEmail==undefined){
			$scope.after.svContactEmail = "";
		}
		if($scope.after.svPostcode==null||$scope.after.svPostcode==undefined){
			$scope.after.svPostcode = "";
		}
		if($scope.before.svLevel=="0"){
			$scope.before.svLevel="部级";
		}else if($scope.before.svLevel=="1"){
			$scope.before.svLevel="省级";
		}else if($scope.before.svLevel=="2"){
			$scope.before.svLevel="市级";
		}else if($scope.before.svLevel=="3"){
			$scope.before.svLevel="县级";
		}
		if($scope.after.svLevel=="0"){
			$scope.after.svLevel="部级";
		}else if($scope.after.svLevel=="1"){
			$scope.after.svLevel="省级";
		}else if($scope.after.svLevel=="2"){
			$scope.after.svLevel="市级";
		}else if($scope.after.svLevel=="3"){
			$scope.after.svLevel="县级";
		}
		if(data.subjSvChange.auditState=="2"){
			$scope.after.auditState="未通过";
		}else if(data.subjSvChange.auditState=="1"){
			$scope.after.auditState="已通过";
		}else{
			$scope.after.auditState="待审核";
		}
	}).error(function (data) {
		
	});

});
