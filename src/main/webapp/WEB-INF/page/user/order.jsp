<%--
  Created by IntelliJ IDEA.
  User: figoxu
  Date: 15/4/24
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>


<div id="a1"></div>


<script type="text/javascript">
</script>


<script type="text/javascript">
    var scripts=[];
  $('.page-content-area').ace_ajax('loadScripts', scripts, function() {

      var orderStore;
      Ext.onReady(function () {
          Ext.QuickTips.init();

          orderStore = new Ext.data.JsonStore({
              url: '${contextPath}/order/ext/store',
              root: 'root',
              totalProperty: 'total',
              fields: ["id"
                      , "mobileNo","carId","startTime", "stopTime"
                      ,"delMark", "batonMode","state", "carVinNum"
              ]
          });

          var cm = new Ext.grid.ColumnModel([
              {header: '订单号', width: 1, dataIndex: 'id'}

      ,
      {header: '手机号码', width: 1, dataIndex: 'mobileNo'},
      {header: '车辆ID', width: 1, dataIndex: 'carId'},
      {header: '下单时间', width: 1, dataIndex: 'startTime'},
      {header: '还车时间', width: 1, dataIndex: 'stopTime'},
      {header: '订单状态', width: 1, dataIndex: 'state',renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
          if (record.get('state') == 1) {
              return "正常租用";
          } else if (record.get('state') == 2) {
              return "等待付款";
          } else if (record.get('state') == 3) {
              return "付款已经完成";
          }
      }},
      {header: '车辆号', width: 1, dataIndex: 'carVinNum'},
          ]);


          var searchTextField = new Ext.form.TextField({
              fieldLabel: '搜索'
          });
          var toolbar = new Ext.Toolbar({
              items: [searchTextField,
                  {
                      text: '搜索',
                      handler: function() {
                          orderStore.load({params: {start: 0, limit: 20}});
                      }
                  }
              ]
          });
          orderGrid = new Ext.grid.GridPanel({
              viewConfig: {forceFit: true},
//            height:$(window).height(),
              autoHeight:true,
              autoScroll: true,
              title: "订单管理",
              store: orderStore,
              cm: cm,
              tbar:toolbar,
              bbar: new Ext.PagingToolbar({
                  pageSize: 20,
                  store: orderStore,
                  displayInfo: true,
                  emptyMsg: "没有记录"
              })
          });
          orderGrid.render("a1");
          orderStore.on('beforeload', function (thiz, options) {
              thiz.baseParams["query"] = searchTextField.getValue();
          });
          orderStore.load({params: {start: 0, limit: 20}});
      })
  });
</script>
