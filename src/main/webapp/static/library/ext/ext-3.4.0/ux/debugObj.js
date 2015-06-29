function debug(msg) {

    Ext.log(msg);
}
function debugObj(e) {
    var description = "";
    var count = 0;
    for (var i in  e) {
        var property = e[i];
        description += i + " = " + property + "\n";
        count++;
        if (count % 5 == 0) {
            debug(description);
            description = "";
        }
    }
    debug(description);
}