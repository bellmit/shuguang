myapp
  .run([
    "$rootScope",
    "$window",
    "$location",
    "$log",
    function(
      $rootScope,
      $scope,
      $window,
      $location,
      $log,
      $state,
      $stateParams
    ) {
      $rootScope.$state = $state;
      $rootScope.$stateParams = $stateParams;
    }
  ])
  .directive("idCheck", idCheck)
  .directive("phoneCheck", phoneCheck)
  .directive("mobileCheck", mobileCheck)
  .directive("telCheck", telCheck)
  .directive("mailCheck", mailCheck)
  .directive("ipv4Check", ipv4Check)
  .directive("urlCheck", urlCheck)
  .directive("numberCheck", numberCheck)
  .directive("usciCheck", usciCheck)
  .directive("ocCheck", ocCheck)
  .directive("bcCheck", bcCheck)
  .directive("textCheck", textCheck);

myapp.filter("trustHtml", function($sce) {
  return function(input) {
    return $sce.trustAsHtml(input);
  };
});

myapp.directive("trim", function() {
  return {
    require: "ngModel",
    link: function(scope, elm, attrs, ctrl) {
      elm.bind("input", function() {
        $(this).val(
          $(this)
            .val()
            .replace(/\s/g, "")
        );
      });
    }
  };
});
//身份证号
idCheck.$inject = ["$http", "$q"];

function idCheck($http, $q) {
  function validateSecIdCard(g) {
    var f = 0;
    var a = g;
    var e = {
      11: "北京",
      12: "天津",
      13: "河北",
      14: "山西",
      15: "内蒙",
      21: "辽宁",
      22: "吉林",
      23: "黑龙",
      31: "上海",
      32: "江苏",
      33: "浙江",
      34: "安徽",
      35: "福建",
      36: "江西",
      37: "山东",
      41: "河南",
      42: "湖北",
      43: "湖南",
      44: "广东",
      45: "广西",
      46: "海南",
      50: "重庆",
      51: "四川",
      52: "贵州",
      53: "云南",
      54: "西藏",
      61: "陕西",
      62: "甘肃",
      63: "青海",
      64: "宁夏",
      65: "新疆",
      71: "台湾",
      81: "香港",
      82: "澳门",
      91: "国外"
    };
    if (!/^\d{17}(\d|x)$/i.test(a)) {
      return false;
    }
    a = a.replace(/x$/i, "a");
    if (e[parseInt(a.substr(0, 2))] == null) {
      return false;
    }
    var c =
      a.substr(6, 4) +
      "-" +
      Number(a.substr(10, 2)) +
      "-" +
      Number(a.substr(12, 2));
    var h = new Date(c.replace(/-/g, "/"));
    if (c != h.getFullYear() + "-" + (h.getMonth() + 1) + "-" + h.getDate()) {
      return false;
    }
    for (var b = 17; b >= 0; b--) {
      f += (Math.pow(2, b) % 11) * parseInt(a.charAt(17 - b), 11);
    }
    if (f % 11 != 1) {
      return false;
    }
    return true;
  }
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!validateSecIdCard(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

//固定电话
phoneCheck.$inject = ["$http", "$q"];

function phoneCheck($http, $q) {
  var REG = /^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

//手机号
mobileCheck.$inject = ["$http", "$q"];

function mobileCheck($http, $q) {
  var REG = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

function telCheck($http, $q) {
  var REG = /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))|(^(\+86|0086)?0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$)/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

//邮箱
mailCheck.$inject = ["$http", "$q"];

function mailCheck($http, $q) {
  var REG = /^([\w-_]+(?:\.[\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\.[a-z]{2,6})$/i;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (attrs.required && (value == null || value.length <= 0)) {
          return false;
        }
        if (!REG.test(value) && value) {
          return false;
        }
        return true;
      };
    }
  };
}

//ipv4
ipv4Check.$inject = ["$http", "$q"];

function ipv4Check($http, $q) {
  var REG = /^(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])(?:\.(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])){3}$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

//url

urlCheck.$inject = ["$http", "$q"];

function urlCheck($http, $q) {
  var REG = /^(?:(?:ht|f)tp(?:s?)\:\/\/|~\/|\/)?(?:\w+:\w+@)?((?:(?:[-\w\d{1-3}]+\.)+(?:com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|edu|co\.uk|ac\.uk|it|fr|tv|museum|asia|local|travel|[a-z]{2}))|((\b25[0-5]\b|\b[2][0-4][0-9]\b|\b[0-1]?[0-9]?[0-9]\b)(\.(\b25[0-5]\b|\b[2][0-4][0-9]\b|\b[0-1]?[0-9]?[0-9]\b)){3}))(?::[\d]{1,5})?(?:(?:(?:\/(?:[-\w~!$+|.,=]|%[a-f\d]{2})+)+|\/)+|\?|#)?(?:(?:\?(?:[-\w~!$+|.,*:]|%[a-f\d{2}])+=?(?:[-\w~!$+|.,*:=]|%[a-f\d]{2})*)(?:&(?:[-\w~!$+|.,*:]|%[a-f\d{2}])+=?(?:[-\w~!$+|.,*:=]|%[a-f\d]{2})*)*)*(?:#(?:[-\w~!$ |\/.,*:;=]|%[a-f\d]{2})*)?$/i;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

numberCheck.$inject = ["$http", "$q"];

function numberCheck($http, $q) {
  var REG = /^((0\.?\d{0,2})|([1-9]\d*\.?\d{0,2}))$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

usciCheck.$inject = ["$http", "$q"];

function usciCheck($http, $q) {
  var REG = /^[0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

ocCheck.$inject = ["$http", "$q"];

function ocCheck($http, $q) {
  var REG = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

bcCheck.$inject = ["$http", "$q"];

function bcCheck($http, $q) {
  var REG = /^[0-9]{15}$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var value = modelValue || viewValue;
        if (!REG.test(value)) {
          return false;
        }
        return true;
      };
    }
  };
}

textCheck.$inject = ["$http", "$q"];

function textCheck($http, $q) {
  var REG = /^[0-9]{15}$/;
  return {
    restrict: "A",
    require: "ngModel",
    link: function($scope, element, attrs, ctrl) {
      // 同步验证
      ctrl.$validators.char = function(modelValue, viewValue) {
        var text = modelValue || viewValue;
        var current = "";
        var length = 0;
        if (text != null && text.length > 0) {
          for (var i = 0, l = text.length; i < l; i++) {
            length += /[^ -~]/g.test(text[i]) ? 2 : 1;
            if (length <= 60) {
              current += text[i];
            }
          }
        }
        // console.log(current, length);
        return true;
      };
    }
  };
}
myapp.directive("fileUpload", function($http) {
  return {
    restrict: "EA", //E = element（元素）, A = attribute（属性）, C = class, M = comment
    scope: {
      //@ reads the attribute value, = provides two-way binding, & works with functions
      //@ 读取属性值， = 提供双向绑定， & 以函数一起工作
      options: "&",
      files: "="
    },
    templateUrl: "include/fileupload.html",
    controller: function($scope, $sce) {
      //默认配置
      var defaults = {
        min: 1,
        data: function() {
          return {};
        },
        accept: "image/png, image/jpg, image/jpeg, image/bmp"
        // filter: function() {},
        // complete: function(err, xhr) {
        //     if (!err) {
        //         // All files successfully uploaded.
        //         swal("导入成功", "操作已成功!", "success");
        //     } else {
        //         swal("导入失败", "操作失败!", "error");
        //     }
        // }
      };

      //读取自定义配置
      var options = $scope.options ? $scope.options() : {};
      var settings = $.extend({}, defaults, options);
      $scope.settings = settings;

      var files = $scope.files || [];
      $scope.files = files;

      $scope.remove = function($event) {
        var index = $event.target.getAttribute("data-index");

        //reset last selected
        $($event.target)
          .closest("dd")
          .find("input")
          .val("");
        $($event.target)
          .closest("dd")
          .siblings(".item-plus")
          .find("input")
          .val("");

        if (settings.min != settings.max) {
          $scope.files.splice(+index, 1);
        } else {
          $scope.files[index].src = null;
          $scope.files[index].preview = null;
          $scope.files[index].name = null;
            $scope.files[index].vsrc = null;
          $scope.files[index].progress = 0;
        }
      };

      $scope.reset = function(index) {
        $scope.files[index] = $scope.files[index] ? $scope.files[index] : {};
        $scope.files[index].src = null;
        $scope.files[index].preview = null;
        $scope.files[index].name = null;
        $scope.files[index].uploading = false;
      };
      $scope.getBackgroundForPath = function(file) {
        var src =
          (file.name &&
            (/\.(png|jpg|gif|jpeg|bmp)$/i.test(file.name)
              ? "/sofn-sys-web/sysTemplate/downPic?fileUrl=" + file.src
              : "../assets/imgs/dist/file-default.png")) ||
          (file.name &&
            (/\.(mp4|ogg|webp)$/i.test(file.name)
              ? file.src
              : "../assets/imgs/dist/video_bg.png")) ||
          file.src ||
          file.picture ||
          file.preview ||
          file.vsrc ||
          "../assets/imgs/dist/plus-mask.png";
        return { "background-image": "url(" + src + ")" };
      };
    }, //Embed a custom controller in the directive 在指令中嵌入一个自定义控制器
    link: function($scope, element, attrs) {
      var settings = $scope.settings;

      $(element).on("change", 'input[type="file"]', function(evt) {
        var files = FileAPI.getFiles(evt); // Retrieve file list
        // var index = this.getAttribute("data-index");
        var index = $(this)
          .closest("dd")
          .index();
        $scope.reset(index);
        var filesProps = $scope.files[index] ? $scope.files[index] : {};
        FileAPI.filterFiles(
          files,
          function(file, info /**Object*/) {
            var isOk = true,
              imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            //使用自定义filter规则过滤
            isOk = settings.filter ? settings.filter(file, info) : isOk;

            if (!isOk) {
              return false;
            }

            //若是image类型，则设置预览图
            if (imageTypes.test(file.type)) {
              FileAPI.Image(file)
                .resize(320, 240, "max")
                .get(function(err, img) {
                  if (!err) {
                    filesProps.isUpload = false;
                    filesProps.preview = img.src;
                  }
                });
            } else {
              filesProps.preview = "../assets/imgs/dist/file-default.png";
            }

            filesProps.type = file.type;
            filesProps.type = file.type;
            filesProps.size = file.size;
            filesProps.name = file.name;
            filesProps.progress = 0;

            $scope.$apply(function() {
              $scope.files[index] = filesProps;
            });

            return isOk;
          },
          function(
            files,
            /**Array*/
            rejected /**Array*/
          ) {
            if (files.length) {
              // Uploading Files
              // $timeout(function() {
              FileAPI.upload({
                url: settings.url,
                files: {
                  file: files[0]
                },
                headers: { Accept: "application/json" },
                data: settings.data(),
                progress: function(evt) {
                  var pr = (evt.loaded / evt.total) * 100;
                  $scope.$apply(function() {
                    $scope.files[index].progress = pr;
                    $scope.files[index].uploading = true;
                  });
                },
                complete: function(err, xhr) {
                  var props = $scope.files[index];
                  props.progress = 0;
                  if (!err) {
                    var data = JSON.parse(xhr.responseText);
                    if (data.httpCode != 200 && data.code != 200) {
                      //服务器出错
                      if (settings.min != settings.max && props.src == null) {
                        $scope.files.splice(+index, 1);
                      }
                      props.preview = null;
                      props.name = null;
                      props.progress = 0;
                      props.uploading = false;
                      swal("上传失败", data.msg, "error");
                      return;
                    } else {
                      //上传成功
                      var src = props.src;
                      if (data.data) {
                        src = ~data.data.indexOf("//")
                          ? data.data
                          : "//" + data.data;
                      } else if (data.path) {
                        src = ~data.path.indexOf("//")
                          ? data.path
                          : "//" + data.path;
                      } else if (data.address) {
                        src = ~data.address.indexOf("//")
                          ? data.address
                          : "//" + data.address;
                      }

                      if (!/\.(png|jpg|jpeg|bmp)$/i.test(src)) {
                        $scope.files[index].vsrc =
                          "../assets/imgs/dist/file-default.png";
                      }
                      props.src = src;
                      props.preview = null;
                      props.isUpload = true;
                      props.uploading = false;
                      props.progress = 0;
                    }
                    $scope.$apply(function() {
                      $scope.files[index] = props;
                    });
                  } else {
                    props;
                    props.preview = null;
                    props.name = null;
                    props.uploading = false;
                    $scope.files.splice(0, $scope.files.length);
                    $scope.$apply(function() {
                      $scope.files[index] = props;
                    });
                    swal("上传失败", "上传失败!", "error");
                  }
                  settings.complete && settings.complete(err, xhr);
                }
              });
            }
          }
        );
      });
    } //DOM manipulation DOM 操作
  };
});

/*省市区指令*/
myapp.directive("pcc", function() {
  return {
    restrict: "E",
    transclude: true,
    scope: false,
    controller: [
      "$scope",
      "$http",
      function($scope, $http) {
        /*设置默认展示的省，市，区*/
        init();

        function init() {
          /*省对应的市，市对应的区县数组*/
          $scope.cis = [];
          $scope.Dis = [];

          /*默认input框内显示的内容*/
          if ($scope.areaCode != null && $scope.areaCode.length > 0) {
            var ids = $scope.areaCode
              .replace(/(\d{2})/g, "$1,")
              .substr(0, 8)
              .split(",");
            $scope.showProId = ids[0];
            $scope.showCiId = ids[1];
            $scope.showDiId = ids[2];
          } else {
            $scope.showPro = "北京市";
            $scope.showProId = "11";
            $scope.showCi = "市辖区";
            $scope.showCiId = "01";
            $scope.showDi = "东城区";
            $scope.showDiId = "01";
            $scope.areaCode = [
              $scope.showProId,
              $scope.showCiId,
              $scope.showDiId
            ].join("");
          }

          $scope.showAllArray = [];
          /*获取省的数据*/
          $scope.pro = GlobalAreaJson.province;
          angular.forEach($scope.pro, function(data) {
            if (data.province == $scope.showProId) {
              $scope.showPro = data.name;
            }
          });

          // $http({
          //     method: 'GET',
          //     url: '../../mocks/ProJson.json'
          // }).then(function successCallback(response) {
          //     $scope.pro = response.data.province;
          //     angular.forEach(response.data.province, function(data) {
          //         if (data.province == $scope.showProId) {
          //             $scope.showPro = data.name;
          //         }
          //     });
          //
          // }, function errorCallback(response) {
          //     alert("获取省份数据失败")
          // });
          /*获取市数据*/
          $scope.ci = GlobalAreaJson.city;
          /*默认北京市对应的市*/
          angular.forEach($scope.ci, function(data) {
            if (data.province == $scope.showProId) {
              $scope.cis.push(data);
            }
            if (
              data.province == $scope.showProId &&
              data.city == $scope.showCiId
            ) {
              $scope.showCi = data.name;
            }
          });

          // $http({
          //     method: 'GET',
          //     url: '../../mocks/CityJson.json'
          // }).then(function successCallback(response) {
          //     $scope.ci = response.data.city;
          //     /*默认北京市对应的市*/
          //     angular.forEach($scope.ci, function(data) {
          //         if (data.province == $scope.showProId) {
          //             $scope.cis.push(data);
          //         }
          //         if (data.province == $scope.showProId && data.city == $scope.showCiId) {
          //             $scope.showCi = data.name;
          //         }
          //     });
          // }, function errorCallback(response) {
          //     alert("获取市级数据失败")
          // });

          /*获取区县的数据*/
          $scope.Di = GlobalAreaJson.District;
          /*默认北京市市辖区对应的区县*/
          angular.forEach($scope.Di, function(data) {
            if (
              data.province == $scope.showProId &&
              data.city == $scope.showCiId
            ) {
              $scope.Dis.push(data);
            }
            if (
              data.province == $scope.showProId &&
              data.city == $scope.showCiId &&
              data.country == $scope.showDiId
            ) {
              $scope.showDi = data.name;
            }
          });
          $scope.showAll = $scope.showPro + $scope.showCi + $scope.showDi;

          // $http({
          //     method: 'GET',
          //     url: '../../mocks/DistrictJson.json'
          // }).then(function successCallback(response) {
          //     $scope.Di = response.data.District;
          //     /*默认北京市市辖区对应的区县*/
          //     angular.forEach($scope.Di, function(data) {
          //         if (data.province == $scope.showProId && data.city == $scope.showCiId) {
          //             $scope.Dis.push(data);
          //         }
          //         if (data.province == $scope.showProId && data.city == $scope.showCiId && data.country == $scope.showDiId) {
          //             $scope.showDi = data.name;
          //         }
          //     });
          //     $scope.showAll = $scope.showPro + $scope.showCi + $scope.showDi;
          // }, function errorCallback(response) {
          //     alert("获取市级数据失败")
          // });
        }
        $scope.initPcc = init;

        /*点击选择省*/
        $scope.chosePro = function($event) {
          $scope.showPro = angular
            .element($event.target)
            .html(); /*展示选择的省*/
          $scope.showProId = $event.target.getAttribute(
            "value"
          ); /*获取所选择省的id*/
          $scope.cis = []; /*清空数组*/
          /*  $event.stopPropagation();/!*阻止冒泡*!/*/
          /*选择省对应的市*/
          angular.forEach($scope.ci, function(data) {
            if (data.province == $scope.showProId) {
              $scope.cis.push(data);
            }
          });
          $scope.showCi = "请选择";
          $scope.restChange(1);
        };

        /*点击选择市*/
        $scope.choseCity = function($event) {
          $scope.showCi = angular
            .element($event.target)
            .html(); /*展示所选择市*/
          $scope.showCiId = $event.target.getAttribute(
            "value"
          ); /*获取所选择市的id*/
          $scope.Dis = []; /*清空数组*/
          /*选择省对应的区县*/
          angular.forEach($scope.Di, function(data) {
            if (
              data.province == $scope.showProId &&
              +data.city == +$scope.showCiId
            ) {
              $scope.Dis.push(data);
              $scope.showCiId = data.city;
            }
          });
          $scope.showDi = "请选择";
          $scope.restChange(2);
        };
        /*点击选择区县*/
        $scope.choseDistrict = function($event) {
          $scope.showDi = angular
            .element($event.target)
            .html(); /*展示所选择区县*/
          $scope.showDiId = $event.target.getAttribute(
            "value"
          ); /*获取所选择区县的id*/
          $scope.showAll = $scope.showPro + $scope.showCi + $scope.showDi;
          $scope.areaCode = [
            $scope.showProId,
            $scope.showCiId,
            $scope.showDiId
          ].join("");
          $scope.tabbableStyleDis();
          angular
            .element($event.target)
            .parent()
            .parent()
            .parent()
            .parent()
            .hide();
        };
        /*tabbable隐藏与显示*/
        $scope.tabbableStyleShow = function() {
          $scope.tabbableStyle = {
            display: "block"
          };
        };
        $scope.tabbableStyleDis = function() {
          $scope.tabbableStyle = {
            display: "none"
          };
        };
        /*active的消失和出现*/
        $scope.ppcSelected = 1; /*默认出现状态*/
        $scope.changeActive = function(n) {
          $scope.ppcSelected = n;
        };
        /*点击选择具体项时展示不同的nav和内容*/
        $scope.restChange = function(n) {
          $scope.changeActive(n + 1);
          $(".nav-tabs li")
            .eq(n)
            .css("display", "block");
          $(".nav-tabs li")
            .eq(n + 1)
            .css("display", "none");
        };
        /*阻止冒泡*/
        $scope.bubbleUp = function($event) {
          $event.stopPropagation();
        };
      }
    ],
    link: function($scope, element, attrs) {
      $(element)
        .prev(".gui-input")
        .on("click", function() {
          $(element).show();
        });
      $(window).on("click", function() {
        $(element).hide();
        // $scope.tabbableStyleDis();
        // $(element).css('display', 'none');
      });
    },
    template:
      '<div class="tabbable" ng-style="tabbableStyle" >' +
      '<ul class="nav nav-tabs" >' +
      '<li ng-class="{active: ppcSelected==1}"  ng-click="changeActive(1)">' +
      '<a href="" >{{showPro}}</a>' +
      "</li>" +
      '<li ng-class="{active: ppcSelected==2}"  ng-click="changeActive(2)">' +
      '<a href="" >{{showCi}}</a>' +
      "</li>" +
      '<li ng-class="{active: ppcSelected==3}"  ng-click="changeActive(3)">' +
      '<a href="" >{{showDi}}</a>' +
      "</li>" +
      "</ul >" +
      '<div class="tab-content " >' +
      '<div class="tab-pane" ng-class="{active: ppcSelected==1}" >' +
      '<ul style="width: 100%">' +
      '<li ng-repeat="P in pro" value="{{P.province}}" ng-click="chosePro($event)">{{P.name}}</li>' +
      "</ul>" +
      "</div>" +
      '<div class="tab-pane" ng-class="{active: ppcSelected==2}" >' +
      "<ul>" +
      '<li ng-repeat="C in cis" value="{{C.city}}" ng-click="choseCity($event)">{{C.name}}</li>' +
      "</ul>" +
      "</div>" +
      '<div class="tab-pane" ng-class="{active: ppcSelected==3}" >' +
      "<ul>" +
      '<li ng-repeat="D in Dis" value="{{D.country}}" ng-click="choseDistrict($event)">{{D.name}}</li>' +
      "</ul>" +
      "</div>" +
      "</div>" +
      "</div>",
    replace: true
  };
});

/*省市区指令 多选*/
myapp.directive("area", function() {
  return {
    restrict: "E",
    transclude: true,
    scope: false,
    controller: [
      "$scope",
      "$http",
      function($scope, $http) {
        $scope.initArea = function() {
          $scope.state = { num: 0 };
          $scope.provinces = [];
          $scope.current = [{}, {}, {}];
          $scope.citys = [];
          $scope.districts = [];
          $scope.data = [];
          $scope.level = 0; /*[0, 1, 2]省市区*/

          /*获取省的数据*/
          $http({
            method: "GET",
            url: "../../mocks/area.json"
          }).then(
            function(response) {
              var data = response.data;
              $scope.province = data.province;
              $scope.city = data.city;
              $scope.country = data.District;
              $scope.data = $scope.province;
              $scope.current = [{ name: "请选择" }, {}, {}];
            },
            function(response) {
              alert("获取区域数据失败");
            }
          );
        };
        $scope.initArea();
        $scope.cancelBubble = function($event) {
          $event.stopPropagation();
          return false;
        };

        $scope.tabChange = function(level) {
          var current = $scope.current[level];
          if (current.name == null || ~current.name.indexOf("请选择")) {
            return;
          }
          $scope.level = level;
          switch (level) {
            case 0:
              $scope.data = $scope.province;
              break;

            case 1:
              getCitys(current);
              break;

            case 2:
              getCountrys(current);
              break;
          }
        };

        function getProvinces(current) {
          $scope.data = $scope.province;
        }

        function getCitys(current) {
          var next = $scope.city;
          $scope.data = next.filter(function(item) {
            return item.province == current.province;
          });
        }

        function getCountrys(current) {
          var next = $scope.country;
          $scope.data = next.filter(function(item) {
            return (
              item.province == current.province && item.city == current.city
            );
          });
        }

        $scope.areaChange = function(index) {
          // var level, levelName, current, nextName, next;
          var level = $scope.level;
          var current = $scope.data[index];
          $scope.current[level] = current;

          if (level >= 2) {
            return;
          }
          //
          //
          level++;
          $scope.level = level;
          $scope.current = $scope.current.slice(0, level);
          $scope.current[level] = { name: "请选择" };
          //
          switch (level) {
            case 0:
              $scope.data = $scope.province;
              break;
            case 1:
              getCitys(current);
              break;

            case 2:
              getCountrys(current);
              break;
          }
        };
        function total() {
          // var province = $scope.province.filter(function(item){
          //     return item.checked;
          // }).map(function(item){
          //     return ['' + item.province + '0000', item.name].join(',')
          // });

          // var city = $scope.city.filter(function(item){
          //     return item.checked;
          // }).map(function(item){
          //     return ['' + item.province + item.city + '00', item.name].join(',')
          // });

          var country = $scope.country
            .filter(function(item) {
              return item.checked;
            })
            .map(function(item) {
              var province = $scope.province.filter(function(provinceItem) {
                return provinceItem.province == item.province;
              });
              var city = $scope.city.filter(function(cityItem) {
                return (
                  cityItem.city == item.city &&
                  cityItem.province == item.province
                );
              });
              return [
                "" + item.province + item.city + item.country,
                province[0].name,
                city[0].name,
                item.name
              ].join(",");
            });

          $scope.state = {
            num: country.length,
            text: country.join(";")
          };
        }
        $scope.areaSelected = function(checked, index) {
          // var level, levelName, current, nextName, next;
          // console.log(this);
          var level = $scope.level;
          var current = $scope.data[index];
          current.part = false;
          switch (level) {
            case 0:
              $scope.city.map(function(item) {
                if (item.province == current.province) {
                  item.checked = checked;
                  item.part = false;
                }
              });
              $scope.country.map(function(item) {
                if (item.province == current.province) {
                  item.checked = checked;
                  item.part = false;
                }
              });
              break;

            case 1:
              var checkedItems = $scope.data.filter(function(item) {
                return !!item.checked == checked;
              });

              //部分选中
              if (checkedItems.length != $scope.data.length) {
                $scope.province.map(function(item) {
                  if (item.province == current.province) {
                    checked && (item.checked = checked);
                    item.part = true;
                  }
                });
              } else {
                //全选|全不选
                $scope.province.map(function(item) {
                  if (item.province == current.province) {
                    item.checked = checked;
                    item.part = false;
                  }
                });
              }

              $scope.country.map(function(item) {
                if (
                  item.province == current.province &&
                  item.city == current.city
                ) {
                  item.checked = checked;
                }
              });

              break;

            case 2:
              var checkedItems = $scope.data.filter(function(item) {
                return !!item.checked == checked;
              });

              //部分选中
              if (checkedItems.length != $scope.data.length) {
                $scope.city.map(function(item) {
                  if (
                    item.province == current.province &&
                    item.city == current.city
                  ) {
                    checked && (item.checked = checked);
                    item.part = true;
                  }
                });
              } else {
                //全选|全不选
                $scope.city.map(function(item) {
                  if (
                    item.province == current.province &&
                    item.city == current.city
                  ) {
                    item.checked = checked;
                    item.part = false;
                  }
                });
              }

              var currentCitys = $scope.city.filter(function(item) {
                return item.province == current.province;
              });

              var cityCheckedItems = currentCitys.filter(function(item) {
                return !!item.checked == checked;
              });

              if (cityCheckedItems.length != currentCitys.length) {
                //部分选中
                $scope.province.map(function(item) {
                  if (item.province == current.province) {
                    checked && (item.checked = checked);
                    item.part = true;
                  }
                });
              } else {
                $scope.province.map(function(item) {
                  if (item.province == current.province) {
                    item.checked = checked;
                    item.part = false;
                  }
                });
              }
              break;
          }
          total();
        };
      }
    ],
    link: function($scope, element, attrs) {
      $(element)
        .prev(".gui-input")
        .on("click", function() {
          $(element).show();
        });
      $(window).on("click", function(evt) {
        $(element).hide();
      });
    },
    template:
      '<div class="gui-area"><ul class="area-tab clearfix"><li ng-class="{active: level == 0}" ng-click="tabChange(0)"><span>{{current[0].name}}</span></li><li ng-class="{active: level == 1}" ng-click="tabChange(1)"><span>{{current[1].name}}</span></li><li ng-class="{active: level == 2}" ng-click="tabChange(2)"><span>{{current[2].name}}</span></li></ul>\n<ul class="area-content clearfix"><li ng-repeat="item in data"><span class="text"><input type="checkbox" ng-class="{\'is-part\':data[$index].part}" ng-model="data[$index].checked" ng-change="areaSelected(data[$index].checked ,$index)" /><span ng-click="areaChange($index)">{{item.name}}</span></span></li></ul></div>',
    replace: true
  };
});

/*自定义查询 视图 echart展示*/
myapp.directive("sgChart", function($timeout) {
  return {
    restrict: "EA", //E = element（元素）, A = attribute（属性）, C = class, M = comment
    scope: {
      //@ reads the attribute value, = provides two-way binding, & works with functions
      //@ 读取属性值， = 提供双向绑定， & 以函数一起工作
      options: "&",
      update: "="
    },
    controller: [
      "$scope",
      "$http",
      function($scope, $http) {
        $scope.data = [];
        $scope.origin = [];
        //默认配置
        var defaults = {};

        //读取自定义配置
        var options = $scope.options ? $scope.options() : {};
        var settings = $.extend({}, defaults, options);
        $scope.settings = settings;
        // console.log("$scope.settings", $scope.settings);
        //饼图默认option
        var pieOption = {
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          series: [
            {
              name: "统计数据",
              type: "pie",
              radius: "55%",
              center: ["50%", "50%"],
              data: [],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)"
                }
              }
            }
          ]
        };
        //柱状图默认option
        var barOption = {
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c}"
          },
          grid: {
            bottom: "35%"
          },
          xAxis: {
            type: "category",
            data: [],
            axisLabel: { interval: 0, rotate: 35 }
          },
          yAxis: {
            type: "value"
          },
          series: [
            {
              data: [],
              type: "bar"
            }
          ]
        };

        function formatAreaNameByCode(data) {
          return data.map(function(code) {
            return $.fn.Cityname(code);
          });
        }

        function format(origin) {
          //格式化数据
          return origin.map(function(item) {
            var option = {};
            switch (item.type) {
              case "pie":
                option = $.extend(true, {}, pieOption);

                option.series[0] = $.extend({}, option.series[0], item);
                break;
              case "bar":
                option = $.extend(true, {}, barOption);
                item.data.map(function(sub) {
                  option.series[0].data.push(sub.value);
                  sub.name = sub.name && sub.name.substr(0, 10);
                  option.xAxis.data.push(sub.name);
                  option.series[0].name = item.name;
                });
                option.xAxis.data = item.area
                  ? formatAreaNameByCode(option.xAxis.data)
                  : option.xAxis.data;
                break;
            }

            // console.log("option", option);
            return option;
          });
        }

        /*获取数据*/
        $scope.request = function(callback) {
          // console.log("settings.data()", settings.data());
          $http({
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
              token: window.localStorage.getItem("token")
            },
            method: "post",
            data: $.param(settings.data()),
            url: settings.url
          }).then(
            function(response) {
              var data;
              if (settings.complete) {
                data = settings.complete(response);
              } else {
                if (response.data.httpCode != 200) {
                  swal("", "请求图表数据出错", "error");
                  return;
                }
                data = response.data.data;
              }
              $scope.origin = data;
              $scope.data = format(data);
              callback && callback($scope.data);
            },
            function(response) {
              swal("", "获取图表数据异常", "error");
            }
          );
        };
      }
    ],
    link: function($scope, element, attrs) {
      function render(dom, option, isInit) {
        // console.log('dom', dom);
        var chart = echarts.init(dom);
        if (option && typeof option === "object") {
          chart.setOption(option, true);
          chart.resize();
        }
      }

      $scope.initChart = function() {
        $scope.request(function() {
          $timeout(function() {
            var elements = $(element).find(".graph-block");
            $scope.data.map(function(item, index) {
              render(elements[index], item);
            });
          }, 500);
        });
      };
      $scope.internalUpdate = $scope.update || {};
      $scope.internalUpdate.update = $scope.initChart;
    },
    templateUrl: "include/chart.html",
    replace: true
  };
});
