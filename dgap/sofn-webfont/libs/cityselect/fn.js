$.fn.mycity=function(i,j,k){
    var a=function(i,j,o,m){
        $.each(province, function (k, p) {
            var option = "<option value='" + p.province + "'>" + p.name + "</option>";
            $("#"+i).append(option);
        });
        $("#"+i).change(function () {
            var selValue = $(this).val();
            $("#"+j+" option:gt(0)").remove();
            $.each(city, function (k, p) {
                if (p.province == selValue) {
                    var araeID=p.province+ p.city
                    var option = "<option value='" + araeID + "'>" + p.name + "</option>";
                    $("#"+j).append(option);
                }
            });
            $("#"+j).trigger("change");
        });
        $("#"+j).change(function () {
            var selValue = $(this).val();
            $("#"+o+" option:gt(0)").remove();
            $.each(District, function (k, p) {
                var cityID=p.province+ p.city
                var cuntryID=p.province+ p.city+ p.country
                if (cityID== selValue) {
                    var option = "<option value='" +cuntryID+ "'>" + p.name + "</option>";
                    $("#"+o).append(option);
                }
            });
        });
        //初始化省市区
        var x="",y="",z="";
        if(m!=null&&m!=undefined){
            m= m.toString()
            if(m.length>=2){
                x=m.substring(0,2);
            }
            if(m.length>=4){
                if(m.substring(2,4)=="00"){
                    y=""
                }else {
                    y=m.substring(0,4);
                }
            }
            if(m.length>=6){
                if(m.substring(4,6)=="00"){
                    z="";
                }else {
                    z=m.substring(0,6);
                }
            }
        }
        $("#"+i).val(x);
        $("#"+i).trigger("change");
        $("#"+j).val(y);
        $("#"+j).trigger("change");
        $("#"+o).val(z);

    }
    return a;
}(jQuery);
//========================根据编码返回名称=============
$.fn.Cityname=function(x){
    //var myprobinceID= x.substring(0,2)
    //alert(myprobinceID)

    var b=function(x){
        x= x.toString()
        var myprobince="",mycity="",myDistrict="";
        var myprobinceID=x.substring(0,2);
        var mycityID=x.substring(2,4);
        var myDistrictID=x.substring(4,6);
        $.each(province, function (k, p) {
            if (p.province == myprobinceID) {
                myprobince= p.name;

            }
        });
            $.each(city, function (k, p) {
                if (p.province == myprobinceID&&mycityID== p.city) {
                    mycity= p.name;
                }
            });
            $.each(District, function (k, p) {
                if (p.province == myprobinceID&&mycityID== p.city&&p.country==myDistrictID) {
                    myDistrict= p.name;
                }
            });
return myprobince+mycity+myDistrict;
    }
    return b;
}(jQuery);
