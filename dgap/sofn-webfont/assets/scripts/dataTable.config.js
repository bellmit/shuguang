$.extend(true, $.fn.dataTable.defaults, {
  retrieve: true, //判断dataTable是否已经初始化，如果已经初始化则只返回实例
  processing: true, // 加载时提示
  serverSide: true, // 分页，是否服务器端处理
  ordering: false,
  searching: false,
  bProcessing: true,
  bAutoWidth: true,
  dom: '<"top">rt<"bottom"pli><"clear">',
  autoWidth: false,
  ajax: {
    error: function(msg) {
    }
  },
  fnDrawCallback: function() {
    var table = $(this);
    table
      .find("tbody")
      .off("click", "tr")
      .on("click", "tr", function(e) {
        if (~["a", "input"].indexOf(e.target.tagName.toLowerCase())) {
          return;
        }
        var checkbox = $(this).find(":checkbox");
        var isChecked = checkbox.prop("checked");
        var disabled = checkbox.prop("disabled");
        if (disabled) {
          return;
        }
        checkbox
          .prop("checked", !isChecked)
          .closest("tr")
          .toggleClass("selected", !isChecked);
        isChecked &&
          table
            .find("thead")
            .find(":checkbox")
            .prop("checked", false);
        var checkedAll =
          table
            .find("tbody")
            .find(":checkbox")
            .not(":checked").length <= 0;
        table
          .find("thead")
          .find(":checkbox")
          .prop("checked", checkedAll);
      })
      .off("change", ":checkbox")
      .on("change", ":checkbox", function(e) {
        e.stopPropagation();
        var isChecked = $(this).prop("checked");
        !isChecked &&
          table
            .find("thead")
            .find(":checkbox")
            .prop("checked", false);
        var checkedAll =
          table
            .find("tbody")
            .find(":checkbox")
            .not(":checked").length <= 0;
        table
          .find("thead")
          .find(":checkbox")
          .prop("checked", checkedAll);
        $(this)
          .closest("tr")
          .toggleClass("selected", isChecked);
      })
      .end()
      .find("thead")
      .off("change", ":checkbox")
      .on("change", ":checkbox", function() {
        var isChecked = $(this).prop("checked");
        table
          .find("tbody")
          .find(":checkbox")
          .not(":disabled")
          .prop("checked", isChecked)
          .closest("tr")
          .toggleClass("selected", isChecked);
      });
    table.off("page.dt, draw.dt").on("page.dt, draw.dt", function() {
      var table = $(this);
      table
        .find("thead")
        .find(":checkbox")
        .prop("checked", false);
      var disabledAll =
        table
          .find("tbody")
          .find(":checkbox")
          .not(":disabled").length <= 0;
      table
        .find("thead")
        .find(":checkbox")
        .prop("disabled", disabledAll);
    });
  },
  columnDefs: [
    {
      defaultContent: "",
      targets: "_all"
    }
  ],
  language: {
    sProcessing:
      '<div class="gui-loader"><div class="ball-beat"><div></div><div></div><div></div></div><p>加载中...</p></div>',
    sLengthMenu: "显示 _MENU_ 项结果",
    sZeroRecords: "没有匹配结果",
    sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    sInfoEmpty: "显示第 0 至 0 项结果，共 项",
    sInfoFiltered: "(由 _MAX_ 项结果过滤)",
    sInfoPostFix: "",
    sSearch: "搜索:",
    sUrl: "",
    sEmptyTable: "表中数据为空",
    sLoadingRecords: "载入中...",
    sInfoThousands: ",",
    oPaginate: {
      sFirst: "首页",
      sPrevious: "上页",
      sNext: "下页",
      sLast: "末页"
    },
    oAria: {
      sSortAscending: ": 以升序排列此列",
      sSortDescending: ": 以降序排列此列"
    }
  },
  sPaginationType: "full_numbers"
});
