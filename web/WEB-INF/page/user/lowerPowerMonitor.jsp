<%--
  Created by IntelliJ IDEA.
  User: figoxu
  Date: 15/4/22
  Time: 下午8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
  <title>车辆运行监控</title>
  <style type="text/css">
    body{
      margin:0;
      height:100%;
      width:100%;
      position:absolute;
    }
    #mapContainer{
      position: absolute;
      top:0;
      left: 0;
      right:0;
      bottom:0;
    }

    #tip{
      position:absolute;
      bottom:30px;
      right:10px;
      background-color:#FFFFFF;
      height:50px;
      border-radius:3px;
      font-size:12px;
      line-height: 50px;
      border:1px solid #CCCCCC;
    }
    #tip input[type='text']{
      margin-left: 10px;
      height:25px;
      border:1px solid #CCCCCC;
      border-radius:3px;
      padding-left:3px;
    }

    #tip input[type='button']{
      margin-left: 10px;
      margin-right:10px;
      margin-top:10px;
      background-color: #0D9BF2;
      height:30px;
      text-align:center;
      line-height:30px;
      color:#fff;
      font-size:12px;
      border-radius:3px;
      outline: none;
      border:0;
      float:right;
    }
  </style>
</head>
<body>
<div id='mapContainer' >



</div>

<div id="gridPanel" style="width:600px;position: absolute;">

</div>

</body>
</html>

<script type="text/javascript">


  var map;
  var currentCarId;
var markerArray = new Array();

  var oldMarkerArray = new Array();

  var scripts = ["http://webapi.amap.com/maps?v=1.3&key=d5fb922a4ac027ae7b0e7bde31ef9a43"];
  $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
    var width = $("#main-content").width();
    var height = $(window).height()-100;

    $('#mapContainer').width(width);
    $('#mapContainer').height(height);

    map = new AMap.Map('mapContainer', {
      resizeEnable: true,
      dragEnable:true,
      zoomEnable:true
    });

    map.setZoom(20);

    window.setInterval(function(){
      loadCarByCenterPosition(map);
    },5000);

    Ext.onReady(mapGridInit);

  });


  function renderCarByJson(json,map){
    var centerMap = map.getCenter();
    for (var i = 0; i < json.length ; i++) {
      var obj = json[i];
      var x = obj.longitude;
      var y = obj.latitude;

console.log("@x:"+x+"    @y:"+y);

//      longitude":116.511016,"latitude":39.896615

      var controlUI = document.createElement("DIV");

      controlUI.id= obj.id;
      controlUI.className= 'car';
      controlUI.style.width = '25px';
      controlUI.style.lineHeight = '25px';
      controlUI.style.height = '25px';
      controlUI.style.borderRadius = '12px';
      controlUI.style.boxShadow = ' 0 3px 14px rgba(0,0,0,.5)';
      controlUI.style.textAlign = 'center';
      controlUI.style.backgroundImage='url("/static/assets/images/commons/car2.png")';
      controlUI.style.zIndex = '300';
      controlUI.innerHTML = name;

      if(obj.id == currentCarId){
        controlUI.style.backgroundColor='yellow';
      }

      var marker = new AMap.Marker({
        position: new AMap.LngLat(x, y),
        content: controlUI,

//          draggable:true, //点标记可拖拽
//          cursor:'move',  //鼠标悬停点标记时的鼠标样式
//          raiseOnDrag:true//鼠标拖拽点标记时开启点标记离开地图的效果
      });
      markerArray[markerArray.length]=marker;
      marker.setMap(map);  //在地图上添加点
    }
    $('#'+currentCarId).css("background-color","yellow");


  }


  function loadCarByCenterPosition(map){

    var centerMap = map.getCenter();
    Ext.Ajax.request({
//        url: '/car/loadByCenter',
      url: '/car/loadByCenter/withFilter',
      method: 'POST',
      params:{
        lat:centerMap.lat
        ,lng:centerMap.lng
        ,powerAlarmFlag:checkBoxAlarm.getValue()
        ,outOfRangeFlag:checkBox.getValue()
        ,state:stateCombo.getValue()
        ,filterText:searchTextField.getValue()
      },
      text: "Updating...",
      success: function (result, request) {
        //clean
        oldMarkerArray = markerArray;
        markerArray = new Array();

        var json = eval('(' + result.responseText + ')');
        renderCarByJson(json,map);


        for(i=0;i<oldMarkerArray.length;i++){
          var marker = oldMarkerArray[i];
          marker.setMap(null);
        }
      },
      failure: function (result, request) {
//        Ext.MessageBox.alert('系统提示', '系统繁忙。');

      }
    });

  }


  var stateCombo, checkBox, checkBoxAlarm,searchTextField;
  var mapGridInit = function () {
    Ext.QuickTips.init();
    var styleData = [
      ['all', '全部车辆'],
      ['rent', '已租用车辆'],
      ['un_rent', '未租用车辆']
    ];
    var styleStore = new Ext.data.SimpleStore({
      fields: ['value', 'text'],
      data: styleData
    });
    stateCombo = new Ext.form.ComboBox({
      width:100,
      anchor: '92%',
      id: 'type',
      name: 'type',
      fieldLabel: '系統風格',
      store: styleStore,
      emptyText: '请选择',
      mode: 'local',
      triggerAction: 'all',
      valueField: 'value',
      displayField: 'text',
      allowBlank: false,
      editable: false
    });

    checkBox = new Ext.form.Checkbox({
      id : "reportId",
      name : "reportIDs",
      autoScroll : false,
      width : 90,
      boxLabel : '越界车辆',
      inputValue : '越界车辆',
      anchor : "90%",
      hideLabel : false,
      listeners : { "check" : function(obj,ischecked){
        console.log("@obj:"+obj+"  @isChecked:"+ischecked);
      }}
    });

     checkBoxAlarm = new Ext.form.Checkbox({
      autoScroll : false,
      width : 90,
      boxLabel : '电量报警',
      inputValue : '电量报警',
      anchor : "90%",
      hideLabel : false,
      listeners : { "check" : function(obj,ischecked){
        console.log("@obj:"+obj+"  @isChecked:"+ischecked);
      }}
    });

     searchTextField = new Ext.form.TextField({
      fieldLabel: '搜索'
    });

    store2 = new Ext.data.JsonStore({
      url: '/car/ext/store',
      root: 'root',
      totalProperty: 'total',
//      fields: ["id", "positionx", "positiony", "state", "desp", "mobileno"]
      fields: ["vinNum", "longitude", "latitude", "desLongitude", "desLatitude"
        , "version", "owner", "state"
      ]
    });



    var cm = new Ext.grid.ColumnModel([
      {header: 'vinNum', width: 1, dataIndex: 'vinNum'},
      {header: 'longitude', width: 1, dataIndex: 'longitude'},
      {header: 'latitude', width: 1, dataIndex: 'latitude'},
      {header: 'desLongitude', width: 1, dataIndex: 'desLongitude'},
      {header: 'desLatitude', width: 1, dataIndex: 'desLatitude'},
      {header: 'version', width: 1, dataIndex: 'version'},
      {header: 'owner', width: 1, dataIndex: 'owner'},
      {header: 'state', width: 1, dataIndex: 'state'},
      {
        header: '操作',
        xtype: 'actioncolumn',
        width: 0.5,
        items: [
          {
            icon: '/static/assets/images/commons/gears.gif',  // Use a URL in the icon config
            tooltip: '查看',
            handler: function (grid, rowIndex, colIndex) {
              var record = store2.getAt(rowIndex);
              console.log("===============");
              var x = record.get('longitude');
              var y = record.get('latitude');
              var id = record.get('vinNum');
              currentCarId = id;
              map.setCenter(new AMap.LngLat(x, y));
              $('.car').css("background-color","transparent");
              $('#'+currentCarId).css("background-color","yellow");
              loadCarByCenterPosition(map);
            }
          }
        ]
      }
    ]);

    var toolbar = new Ext.Toolbar({
//      layout:'form',
      items: [searchTextField,
        {
          text: '搜索',
          handler: function() {
            store2.reload(store2.lastOptions);
          }
        },'-',stateCombo,'->',checkBoxAlarm,
        checkBox

      ]
    });
    var grid = new Ext.grid.GridPanel({
      viewConfig: {forceFit: true},
//            height:$(window).height(),
      tbar: toolbar,
      collapsible: true,
      collapsed:true,
      height:300,
      autoScroll: true,
      title: "车辆信息管理",
      store: store2,
      cm: cm,
      bbar: new Ext.PagingToolbar({
        pageSize: 20,
        store: store2,
        displayInfo: true,
        emptyMsg: "没有记录"
      })
    });
    grid.render("gridPanel");

    store2.on("beforeload", function(thiz, options) {
      thiz.baseParams["powerAlarmFlag"] = checkBoxAlarm.getValue();
      thiz.baseParams["outOfRangeFlag"] = checkBox.getValue();
      thiz.baseParams["state"] = stateCombo.getValue();
      thiz.baseParams["filterText"] =searchTextField.getValue();
    });

    store2.load({params: {start: 0, limit: 20}});


      window.setInterval(function(){
          store2.reload(store2.lastOptions);
      },5000);
  };

</script>