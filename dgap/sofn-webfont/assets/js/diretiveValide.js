function XXXX() {
  function randomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  }
  $("#captchaOperation").html(
    [randomNumber(1, 100), "+", randomNumber(1, 200), "="].join(" ")
  );

  $("#defaultForm").bootstrapValidator({
    //        live: 'disabled',
    message: "This value is not valid",
    feedbackIcons: {
      valid: "glyphicon glyphicon-ok",
      invalid: "glyphicon glyphicon-remove",
      validating: "glyphicon glyphicon-refresh"
    },
    fields: {
      firstName: {
        validators: {
          notEmpty: {
            message: "请输入姓"
          }
        }
      },
      lastName: {
        validators: {
          notEmpty: {
            message: "请输入名"
          }
        }
      },
      username: {
        message: "The username is not valid",
        validators: {
          notEmpty: {
            message: "用户名不能为空"
          },
          //                                           用户名组成类型
          regexp: {
            regexp: /^[a-zA-Z0-9_]+$/,
            message: "用户名只能由字母、数字和下划线组成"
          }
          //请求后台验证
          //remote: {
          //    url: 'remote.php',
          //    message: '用户名不能为空'
          //},
          //                        different: {
          //                            field: 'password',
          //                            message: '用户名和密码不能相同'
          //                        }
        }
      },
      email: {
        validators: {
          emailAddress: {
            message: "请输入正确的邮件地址"
          }
        }
      },
      password: {
        validators: {
          different: {
            field: "username",
            message: "用户名和密码不能相同"
          },
          notEmpty: {
            message: "密码不能为空"
          }
        }
      },
      confirmPassword: {
        validators: {
          notEmpty: {
            message: "确认密码不能为空"
          },
          identical: {
            field: "password",
            message: "密码和它确认密码不一样"
          },
          different: {
            field: "username",
            message: "用户名和密码不能相同"
          }
        }
      },
      birthday: {
        validators: {
          date: {
            format: "YYYY/MM/DD",
            message: "格式不正确"
          }
        }
      },
      gender: {
        validators: {
          notEmpty: {
            message: "性别是必须选的"
          }
        }
      },
      Mobilephone: {
        validators: {
          regexp: {
            regexp: /((^(\+86|0086)?1[3|5|8]\d{9}$)|(^(\+86|0086)?1(400|410|440)\d{7}$)|(^(\+86|0086)?1(45|46|47|48|49|70|71|72|73|74|75|76|77|78|65|66|98|99)\d{8}$))/,
            message: "请输入正确的手机号码"
          },
          notEmpty: {
            message: "请输入手机号"
          }
        }
      },
      Phonenumber: {
        validators: {
          regexp: {
            regexp: /^0[1-9](\d-?\d{8}|\d{2}-?\d{7,8})$/,
            message: "请输入正确的座机号码"
          }
        }
      },
      IDcard: {
        validators: {
          regexp: {
            regexp: /\d{17}[\d|x]|\d{15}/,
            message: "请输入正确的身份号码"
          },
          notEmpty: {
            message: "身份号码不能为空"
          }
        }
      },
      captcha: {
        validators: {
          callback: {
            message: "答案错误",
            callback: function(value, validator) {
              var items = $("#captchaOperation")
                  .html()
                  .split(" "),
                sum = parseInt(items[0]) + parseInt(items[2]);
              return value == sum;
            }
          }
        }
      }
    }
  });
  // 提交按钮
  $("#validateBtn").click(function() {
    $("#defaultForm").bootstrapValidator("validate");
  });

  $("#resetBtn").click(function() {
    $("#defaultForm")
      .data("bootstrapValidator")
      .resetForm(true);
  });
}
