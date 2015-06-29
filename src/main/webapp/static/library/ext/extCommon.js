function getFormFieldsForColumnLayout(formFields) {
      var itemArray = new Array();
      for (var i = 0; i < formFields.length; i++) {
          formFields[i].anchor = '50%';
          formFields[i].labelSeparator = ': ';
          itemArray[itemArray.length] = {items:[formFields[i]]};
      }
      return itemArray;
}

String.prototype.startWith=function(str){
      var reg=new RegExp("^"+str);
      return reg.test(this);
}





String.prototype.endWith=function(str){
      var reg=new RegExp(str+"$");
      return reg.test(this);
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


Number.prototype.format = function(n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};
