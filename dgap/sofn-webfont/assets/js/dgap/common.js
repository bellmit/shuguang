/**
 * Created by Administrator on 2016/11/4.
 */
function lookupName(id, map) {
    if (map != null) {
        for (var j = 0; j < map.length; j++) {
            if (map[j].id == id) {
                return map[j].name;
            }
        }
    }
    return "";
}
/*
转换下拉框的id和name*/
function lookupNameSelect(map, selectId) {
    if (map != null) {
        for (var i = 0; i < map.length; i++) {
            if (selectId == map[i].id) {
                return map[i].name;
            }

        }
    }
    return "";
}
