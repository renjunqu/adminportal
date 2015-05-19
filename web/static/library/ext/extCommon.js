function getFormFieldsForColumnLayout(formFields) {
      var itemArray = new Array();
      for (var i = 0; i < formFields.length; i++) {
          formFields[i].anchor = '90%';
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