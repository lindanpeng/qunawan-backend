var DOMAIN="http://localhost:8081"
var DO_LOGIN_URL=DOMAIN+"/doLogin"
var INDEX_URL=DOMAIN+"/index"
var SCENIC_PREVIEW_DATA_URL=DOMAIN+"/scenicPreview"
var SCENIC_PREVIEW_VIEW_URL=DOMAIN+"/scenicRank/view"
var NEW_EVALUATE_DATA_URL=DOMAIN+"/evaluates"
var NEW_EVALUATE_VIEW_URL=DOMAIN+"/evaluates/view"
var USER_INFO_URL=DOMAIN+"/userInfo"
var HOME_URL=DOMAIN+"/home"
var LOGOUT_URL=DOMAIN+"/logout"
var SCENIC_RANK_DATA_URL=DOMAIN+"/scenicRank"
var SCENIC_DETAIL_DATA_URL=DOMAIN+"/scenicDetail"
var SCENIC_DETAIL_VIEW_URL=DOMAIN+"/scenicDetail/view"
var USER_EVALUATE_DATA_URL="/userEvaluates"
var USER_SKIM_URL="/userSkim"
function cutString(str, len) {
    //length属性读出来的汉字长度为1
    if (str.length * 2 <= len) {
        return str;
    }
    var strlen = 0;
    var s = "";
    for (var i = 0; i < str.length; i++) {
        s = s + str.charAt(i);
        if (str.charCodeAt(i) > 128) {
            strlen = strlen + 2;
            if (strlen >= len) {
                return s.substring(0, s.length - 1) + "...";
            }
        } else {
            strlen = strlen + 1;
            if (strlen >= len) {
                return s.substring(0, s.length - 2) + "...";
            }
        }
    }
    return s;
}
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}