<%--
  Created by IntelliJ IDEA.
  User: figoxu
  Date: 15/4/24
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="a1"></div>


<script type="text/javascript">
</script>


<script type="text/javascript">
    var scripts=[];
  $('.page-content-area').ace_ajax('loadScripts', scripts, function() {

      var store2;
      Ext.onReady(function () {
          Ext.QuickTips.init();

          store2 = new Ext.data.JsonStore({
              url: '/order/ext/store',
              root: 'root',
              totalProperty: 'total',
              fields: ["id"
        , "mobileno","carid","starttime", "stoptime"
        ,"rentstatus","delmark", "batonmode","state", "carvinnum","destination"
              ]
          });

          var cm = new Ext.grid.ColumnModel([
              {header: '订单号', width: 1, dataIndex: 'id'}

      ,
      {header: '手机号码', width: 1, dataIndex: 'mobileno'},
      {header: '车辆ID', width: 1, dataIndex: 'carid'},
      {header: '下单时间', width: 1, dataIndex: 'starttime'},
      {header: '还车时间', width: 1, dataIndex: 'stoptime'},
      {header: '订单状态', width: 1, dataIndex: 'state'},
      {header: '车辆号', width: 1, dataIndex: 'carvinnum'},
      {header: '目的地', width: 1, dataIndex: 'destination'}
//      ,{   header: '操作',
//        xtype: 'actioncolumn',
//        width: 0.5,
//        items: [
//          {
//            icon: '/static/assets/images/commons/gears.gif',  // Use a URL in the icon config
//            tooltip: '查看',
//            handler: function (grid, rowIndex, colIndex) {
//              var record = store.getAt(rowIndex);
//              showUserWin(record);
//            }
//          },{
//            icon: '/static/assets/images/commons/edit.gif',  // Use a URL in the icon config
//            tooltip: '修改',
//            handler: function (grid, rowIndex, colIndex) {
//              var record = store.getAt(rowIndex);
//              showModify(record);
//            }
//          }
//        ]
//      }
          ]);


          var searchTextField = new Ext.form.TextField({
              fieldLabel: '搜索'
          });
          var toolbar = new Ext.Toolbar({
              items: [searchTextField,
                  {
                      text: '搜索',
                      handler: function() {
                          store2.load({params: {start: 0, limit: 20}});
                      }
                  }
              ]
          });

          var grid = new Ext.grid.GridPanel({
              viewConfig: {forceFit: true},
//            height:$(window).height(),
              autoHeight:true,
              autoScroll: true,
              title: "订单管理",
              store: store2,
              cm: cm,
              tbar:toolbar,
              bbar: new Ext.PagingToolbar({
                  pageSize: 20,
                  store: store2,
                  displayInfo: true,
                  emptyMsg: "没有记录"
              })
          });
          grid.render("a1");
          store2.on('beforeload', function (thiz, options) {
              thiz.baseParams["query"] = searchTextField.getValue();
          });
          store2.load({params: {start: 0, limit: 20}});
      })
  });
</script>
