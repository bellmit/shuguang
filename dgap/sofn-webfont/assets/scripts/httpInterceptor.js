$.ajaxSetup({
  statusCode: {
    401: function() {
      var timer = null;
      var showModal = function(response) {
        swal(
          {
            title: "系统提示",
            text: "您的账号已失效或在其它设备登录，账号已退出!",
            type: "warning",
            confirmButtonText: "重新登录",
            closeOnConfirm: true
          },
          function() {
            clearInterval(timer);
            swal.close();
            window.localStorage && window.localStorage.clear();
            window.sessionStorage && window.sessionStorage.clear();
            window.location.href = "/";
          }
        );
      };

      timer = setInterval(function() {
        showModal();
      }, 100);
      throw new Error("jquery ajax: http 401 error");
      return false;
    },
    404: function() {
      // swal("404", "请求地址不存在！", "warning");
    },
    500: function() {
      // swal("500", "服务器异常！", "warning");
    }
  }
});

myapp.factory("httpInterceptor", [
  "$q",
  function($q) {
    var httpInterceptor = {
      responseError: function(response) {
        //响应错误拦截
        //这里可以对不同的响应错误进行处理，比如根据返回状态码进行特殊处理
        switch (response.status) {
          case 401:
            // throw new Error("$http status: http 401 error");
            var timer = null;
            var showModal = function() {
              swal(
                {
                  title: "系统提示",
                  text: "您的账号已失效或在其它设备登录，账号已退出!",
                  type: "warning",
                  confirmButtonText: "重新登录",
                  closeOnConfirm: true
                },
                function() {
                  clearInterval(timer);
                  swal.close();
                  window.localStorage && window.localStorage.clear();
                  window.sessionStorage && window.sessionStorage.clear();
                  window.location.href = "/";
                }
              );
            };

            timer = setInterval(function() {
              showModal();
            }, 100);
            // throw new Error("$http httpCode: http 401 error");
            break;
          case 404:
            break;
            defalut: break;
        }
        return $q.reject(response);
      },
      response: function(response) {
        //响应拦截
        //这里可以对所有的响应的进行处理
        if (response.data && response.data.httpCode == 401) {
          var timer = null;
          var showModal = function() {
            swal(
              {
                title:"系统提示",
                text: "您的账号已失效或在其它设备登录，账号已退出!",
                type: "warning",
                confirmButtonText: "重新登录",
                closeOnConfirm: true
              },
              function() {
                clearInterval(timer);
                swal.close();
                window.localStorage && window.localStorage.clear();
                window.sessionStorage && window.sessionStorage.clear();
                window.location.href = "/";
              }
            );
          };

          timer = setInterval(function() {
            showModal();
          }, 100);
          // throw new Error("jquery ajax: http 401 error");
          return $q.reject(response);
        }
        return response;
      },
      request: function(config) {
        //请求拦截
        //这里可以对所有的请求进行处理
        var token = window.localStorage
          ? window.localStorage.getItem("token")
          : undefined;
        if (token) {
          config.headers["token"] = token;
        }
        return config;
      },
      requestError: function(config) {
        //请求错误拦截
        //这里可以对所有的请求错误进行处理
        return $q.reject(config);
      }
    };
    return httpInterceptor;
  }
]);
