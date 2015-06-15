<%--
  Created by IntelliJ IDEA.
  User: figoxu
  Date: 15/4/22
  Time: 下午8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

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
<div id='mapContainer' >



</div>

<div id="gridPanel" style="width:600px;position: absolute;">

</div>


<script type="text/javascript">


    refreshInterval = 5000;
    startRefreshCar = false;

    function getCarData(){
        if(window.location.hash != "#page/user/lowerPowerMonitor"){
        } else if(startRefreshCar) {
            carStore.load(carStore.lastOptions);
            var total = carStore.getCount();
	    var tempInterval  = 5000;
	    for(var i =0;i<total;i++) {
	       var carData = carStore.getAt(i).data; 
	       if(carData.state!=0) {
		   //临时加快速度
	           tempInterval = 500; 
		   break;
	       }
	    }
	    refreshInterval = tempInterval;
	    //console.log("hahaha   >>>>>>>>>>>>>>>>>>>>>>>"+refreshInterval);
            window.setTimeout(getCarData,refreshInterval);
        } else {
            window.setTimeout(getCarData,refreshInterval);
        }
    }
  function initAMapContainer(){
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

      window.setTimeout(getCarData,refreshInterval);
	Ext.onReady(mapGridInit);
  }

  var map;
  var currentCarId;
  var markerArray = new Array();
  //防止载入多次
  if(typeof loadedAMap=='undefined')
      loadedAMap = false;
  if(loadedAMap==false) {
                  var scripts = ["http://webapi.amap.com/maps?v=1.3&key=103e3fae6c781ad2da0587f2b04a2034"];
		  $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		   loadedAMap = true;
                   initAMapContainer();
	          });
  } else {
		    var scripts = [];
		    $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
						      initAMapContainer();
		    });
  }


  function renderCarByJson(map){
    for(i = 0;i<markerArray.length;i++)
	    markerArray[i].setMap(null);
    markerArray = [];
    startRefreshCar = true;
    var centerMap = map.getCenter();
    for (var i = 0; i < carStore.data.length ; i++) {
      var obj = carStore.getAt(i).data;
      var x = obj.longitude;
      var y = obj.latitude;
	  console.log("hello world");

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
      controlUI.style.backgroundImage='url("${contextPath}/static/assets/images/commons/car2.png")';
      controlUI.style.zIndex = '300';
      controlUI.innerHTML = name;

      if(obj.id == currentCarId){
        controlUI.style.backgroundColor='yellow';
      }

      var marker = new AMap.Marker({
        position: new AMap.LngLat(x, y),
        content: controlUI,

          draggable:true, //点标记可拖拽
          cursor:'move',  //鼠标悬停点标记时的鼠标样式
          raiseOnDrag:true//鼠标拖拽点标记时开启点标记离开地图的效果
      });
      markerArray[markerArray.length]=marker;
      marker.setMap(map);  //在地图上添加点
    }
    $('#'+currentCarId).css("background-color","yellow");


  }

  var jsonCarStore = {root:[],total:0};


  carStore = new Ext.data.JsonStore({
     url:"${contextPath}/car/loadByCenter/withFilter",
     idProperty:"vinNum",
     root:"root",
     fields: ["owner","latitude", "state","vinNum","longitude"],
     listeners:{
          "datachanged":function(store){
                    renderCarByJson(map);
	  } 
     }
    });


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




    var cm = new Ext.grid.ColumnModel([
      {header: 'vinNum', width: 1, dataIndex: 'vinNum'},
      {header: '使用者', width: 1, dataIndex: 'owner'},
      {header: '车辆状态', width: 1, dataIndex: 'state',renderer:function(value,cellmeta,record,rowIndex, columnIndex, store){
                            if(record.get('state')==0) {
                                return "空闲";
			    } else if(record.get('state')==1) {
                                return "被预定";
			    } else if(record.get('state')==2) {
                                return "被租用";
			    } else if(record.get('state')==3) {
			        return "等待授权码下发";
			    } else if(record.get('state')==4) {
			        return "等待预定成功";
			    } else if(record.get('state')==5) {
			        return "等待点火成功";
			    } else if(record.get('state')==6) {
			        return "等待熄火成功";
			    } else if(record.get('state')==7) {
			        return "等待锁车成功";
			    } else if(record.get('state')==8) {
			        return "等待清除授权码成功";
			   }

      }},
      {
        header: '操作',
        xtype: 'actioncolumn',
        width: 0.5,
        items: [
          {
            icon: '${contextPath}/static/assets/images/commons/gears.gif',  // Use a URL in the icon config
            tooltip: '查看',
            handler: function (grid, rowIndex, colIndex) {
              var record = carStore.getAt(rowIndex);
              var x = record.get('longitude');
              var y = record.get('latitude');
              var id = record.get('vinNum');
              currentCarId = id;
              map.setCenter(new AMap.LngLat(x, y));
              $('.car').css("background-color","transparent");
              $('#'+currentCarId).css("background-color","yellow");
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
              carStore.load(carStore.lastOptions);
          }
        },'-',stateCombo,'->',checkBoxAlarm,
        checkBox

      ]
    });
  carGrid = new Ext.grid.GridPanel({
      viewConfig: {forceFit: true},
      tbar: toolbar,
      collapsible: true,
      collapsed:true,
      height:300,
      autoScroll: true,
      title: "车辆信息管理",
      store: carStore,
      cm: cm,
      bbar: new Ext.PagingToolbar({
        pageSize: 5,
        store: carStore,
        displayInfo: true,
        emptyMsg: "没有记录",
      })
    });
    //first time add the data
    carStore.load({params: {start: 0, limit:5}});
    carGrid.render("gridPanel");
  };

</script>
