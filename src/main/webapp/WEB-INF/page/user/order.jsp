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

<script type="text/javascript" src="${contextPath}/static/assets/js/jquery.datetimepicker.js"></script>
<link    rel="stylesheet" href="${contextPath}/static/assets/css/jquery.datetimepicker.css" />

<div id="a1"></div>



<script type="text/javascript">
    function isNum(str) {
        var re = /^[\d\.]+$/
        return re.test(str);
    }
    var geocoder; 
    var scripts = [];//["http://webapi.amap.com/maps?v=1.3&key=103e3fae6c781ad2da0587f2b04a2034"];
    var stateStore = new Ext.data.SimpleStore({
            fields: ['value', 'text'],
            data: [
		    ['0', '全部'],
		    ['1', '租用中'],
		    ['2', '待支付'],
		    ['3', '支付完毕'],
		]
   });
   var sicon = new AMap.Icon({
                                      image: "http://cache.amap.com/lbs/static/jsdemo002.png",
                                       size:new AMap.Size(44,44),
                                imageOffset: new AMap.Pixel(-334, -180)
                            });

  var eicon = new AMap.Icon({
                                                image: "http://cache.amap.com/lbs/static/jsdemo002.png",
                                                size:new AMap.Size(44,44),
                                               imageOffset: new AMap.Pixel(-334, -134)
  });

  var payTypeStore = new Ext.data.SimpleStore({
            fields: ['value', 'text'],
            data: [
		    [0, '全部'],
		    [1, '支付宝'],
		    [2, '微信'],
		]
   });
  function showFeeWin(index){
                var record = orderStore.getAt(index);
                if(record.get("state")==1){
                     alert("租用中的无费用信息");
                     return;
               }
	       var startTime = new Date(parseInt(record.get('startTime'),10)); 
	       var stopTime = new Date(parseInt(record.get('stopTime'),10)); 
	       var diffTime = stopTime.getTime() - startTime.getTime();
	        var fee =  (diffTime / (1000*60*100)).format(2,4)+"元";
                var cNum = record.get('cNum');

                if(!isNum(cNum)) {
                    cNum = "未使用优惠卷"
                }
                var payType = record.get('payType');
                var payBalance  = record.get('payBalance');
                if(!isNum(payType)) {
                    payType="未使用其他手段支付"
                    payBalance = "";
                } else {
                     if(isNum==1) {
                        payType="支付宝";
                        payBalance = payBalance.format(2,4)+"元";
                     } else if(isNum==2) {
                        payType="微信";
                        payBalance = payBalance.format(2,4)+"元";
                     } else {
			    payType="未使用其他手段支付"
			    payBalance = "";
                    }
               }

		var fs = new Ext.FormPanel({
			buttonAlign: 'center',
			items:[
				{
				     xtype:"textfield",
				     fieldLabel: '总金额',
				     value:fee,
                                     inputStyleAttribute:{"border":"none"},
				},
				{
				     xtype:"textfield",
				     fieldLabel: '优惠劵额度',
				     value:cNum
				},
				{
				     xtype:"textfield",
				     fieldLabel: '其他支付手段',
				     value:payType
				},
				{
				     xtype:"textfield",
				     fieldLabel: '支付额度',
				     value:payBalance
				},
                        ]
		     });

		var feeWin = new Ext.Window({
		    title: "费用信息",
		    modal: true,
		    width: 400,
		    height: 160,
		    resizable: false,
		    autoScroll: false,
		    items: [ fs ]
		});
             feeWin.show(); 
  }

  function showMapWin(index){

                var record = orderStore.getAt(index);
                var formFields = new Array();
		formFields[formFields.length] = new Ext.BoxComponent({
		    width: 640, //图片宽度
		    height: 500, //图片高度
		    autoEl: {
			tag: 'div',    //指定为div标签
                        html:'<div id="mapContainer" style="width:640px;height:500px;">hello</div>'
		    }
		});
		    var fs = new Ext.FormPanel({
			autoHeight:true,
			bodyStyle:"padding:10px",
			buttonAlign: 'center',
			items:[{
			    layout:'form',
			    border:false,
			    items:getFormFieldsForColumnLayout(formFields),
			    anchor:"95%"
			}]
		     });

		var mapWin = new Ext.Window({
		    title: "详细轨迹",
		    modal: true,
		    width: 640,
		    height: 500,
		    resizable: true,
		    autoScroll: true,
		    items: [ fs ]
		});
             mapWin.show(); 
              var   map = new AMap.Map('mapContainer', { 
                                resizeEnable: true, 
                                rotateEnable: true, 
                                dragEnable: true, 
                                zoomEnable: true, 
                                //设置可缩放的级别 
                                zooms: [3,18], 
                                view: new AMap.View2D({ 
                                        center: new AMap.LngLat(116.510756,39.894255), 
                                        zoom: 12 
                                }) 
                 }); 
                map.plugin(['AMap.ToolBar'],function(){ 
                            var toolBar = new AMap.ToolBar(); 
                          map.addControl(toolBar); 
                });
                 var startPos  = {
				map:map,
				icon:sicon,
				position:new AMap.LngLat(record.get('startLongitude'), record.get('startLatitude')),
                                        offset : {
                                                        x : -16,
                                                     y : -40
                             }
                  };
                var startMarker = new AMap.Marker(startPos);
                startMarker.setMap(map);
                if(parseInt(record.get('state'))!=1) {
                       var stopPos  = {
				map:map,
				icon:eicon,
				position:new AMap.LngLat(record.get('stopLongitude'), record.get('stopLatitude')),
                                        offset : {
                                                        x : -16,
                                                     y : -40
                                      }
                       };
                       var stopMarker = new AMap.Marker(stopPos);
                       stopMarker.setMap(map);
                }
               map.setFitView();
      }

  $('.page-content-area').ace_ajax('loadScripts', scripts, function() {

    //加载地理编码服务
       AMap.service(["AMap.Geocoder"], function() {       
		geocoder = new AMap.Geocoder({
			city:"010", //城市，默认：“全国”
			radius:1000 //范围，默认：500
		});
	    });


      Ext.onReady(function () {
          Ext.QuickTips.init();

          orderStore = new Ext.data.JsonStore({
              url: '${contextPath}/order/ext/store',
              root: 'root',
              totalProperty: 'total',
              fields: ["id"
                      , "mobileNo","carId","startTime", "stopTime"
                      ,"delMark","rentStatus", "type","batonMode","destination","state", "carVinNum",
		      "ifBlueTeeth","startLatitude","stopLatitude","startLongitude","stopLongitude",
		      //后面的数据是通过联合查询得到的扩展字段
		      //车牌号(真车)   车牌号(假车)  支付的额度   支付的手段   使用的优惠卷的个数
		      "carLicenseNum",    "carDesp","payBalance","payType",   "cNum"
              ]
          });

          var cm = new Ext.grid.ColumnModel([
              {header: '订单号', width: 1, dataIndex: 'id'},
	      {header: '车牌号', width: 1, renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
	           var cLn = record.get("carLicenseNum");
	           var cDes = record.get("carDesp");
		   if(cLn!=null && cLn!=undefined && cLn.length>0) {
		            return ""+cLn; 
		   } else if(cDes!=null && cDes!=undefined && cDes.length>0) {
		            return ""+cDes;	
	           } else {
		              return '<div style="color:red;">无法获取车牌号</div>';
		   }
	      
	      }},
              {header: '手机号', width: 1, dataIndex: 'mobileNo'},
	      {header: '费用',   width: 1, renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
			        var startTime = new Date(parseInt(record.get('startTime'),10)); 
			        var stopTime = new Date(parseInt(record.get('stopTime'),10)); 
				var diffTime = stopTime.getTime() - startTime.getTime();
				return (diffTime / (1000*60*100)).format(2,4)+"元";
	      }},
	      {header: '车辆型号',width: 1, renderer:function(){return "电动车";}},
	      {header: '开始租用时间', width: 1, dataIndex: 'startTime',renderer:function(value) {
			        var startTime = new Date(parseInt(value,10)); 
			        return startTime.Format("yyyy-MM-dd      hh:mm:ss");
	      }},
	      {header: '结束租用时间', width: 1, dataIndex: 'stopTime',renderer:function(value){
			        var stopTime = new Date(parseInt(value,10)); 
			        return stopTime.Format("yyyy-MM-dd      hh:mm:ss");
	      }},
	      {header: '租用地点', width: 1, renderer:function(value,cellmeta,record,rowIndex,columnIndex,store){
			  try {
				var lnglatXY = new AMap.LngLat(record.get('startLongitude'),record.get('startLatitude'));
				       geocoder.getAddress(lnglatXY, function(status, result){
						if(status === 'complete' && result.info === 'OK'){
						     //console.log(JSON.stringify(result));
						     //orderData.startAddr = result.formattedAddress;
						        try {
								var addrName = result.regeocode.addressComponent.businessAreas[0].name;
								$("#startAddr"+rowIndex).html(addrName);
							} catch(err) {
								console.log(JSON.stringify(result)); 
								$("#startAddr"+rowIndex).html("地址加载失败");
								$("#startAddr"+rowIndex).css({"color":"red"});
							}
						} else {
						        console.log(JSON.stringify(result)); 
							$("#startAddr"+rowIndex).html("地址加载失败");
							$("#startAddr"+rowIndex).css({"color":"red"});
						     }
						}); 
	                            return  '<div id="startAddr'+rowIndex+'" style="width:120px;height:30px;color:gray">--地址正在加载--</div>';
			      } catch(err) {
			           console.log(err); 
	                          return  '<div id="startAddr'+rowIndex+'" style="width:120px;height:30px;color:red">--地址加载失败--</div>';
			      }
	      }},
               {header: '还车地址', width: 1, renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
			if(parseInt(record.get('state'))==1) {
	                     return  '<div id="stopAddr'+rowIndex+'" style="width:120px;height:30px;color:red">订单未完成无还车地址</div>';
			} else {
			      try {
				       var lnglatXY = new AMap.LngLat(record.get('stopLongitude'),record.get('stopLatitude'));
				       geocoder.getAddress(lnglatXY, function(status, result){
						if(status === 'complete' && result.info === 'OK'){
						     //console.log(JSON.stringify(result));
						     //orderData.startAddr = result.formattedAddress;
						      try {
							     var addrName = result.regeocode.addressComponent.businessAreas[0].name;
							     $("#stopAddr"+rowIndex).html(addrName);
						       } catch(err) {
								console.log(JSON.stringify(result)); 
								$("#stopAddr"+rowIndex).html("地址加载失败");
								$("#stopAddr"+rowIndex).css({"color":"red"});
							}
						 } else {
						        console.log(JSON.stringify(result)); 
							$("#stopAddr"+rowIndex).html("地址加载失败");
							$("#stopAddr"+rowIndex).css({"color":"red"});
						 }
					});
	                         return  '<div id="stopAddr'+rowIndex+'" style="width:120px;height:30px;color:gray">--地址正在加载--</div>';
			      }  catch(err) {
			         //console.log(err+"hahah"); 
	                         return  '<div id="stopAddr'+rowIndex+'" style="width:120px;height:30px;color:red">--地址加载失败--</div>';
			      }
			}
	       }},
               {header: '订单状态', width: 1, renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
	                      return stateStore.getAt(parseInt(record.get('state'))).data['text']; 
	       }},
               {header: '是否违章', width: 1, renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
	                       return "否";
	       }},
               {header: '查看信息', width: 1, renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
			var resultStr = '<input class="qrjGridButton"  onclick="showMapWin('+rowIndex+');" type="button" value="轨迹"></input>';
			var state = parseInt(record.get('state'));
			if(state!=1) {
			      resultStr += '<input class="qrjGridButton" onclick="showFeeWin('+rowIndex+')" type="button" value="费用详情"></input>';
			}
			return resultStr;
	       }},
               {header: '操作', width: 1, renderer:function(value,cellmeta,record,rowIndex, columnIndex, store) {
			var state = parseInt(record.get('state'));
			var resultStr = "";
			if(state==1) {
			    resultStr += '<input class="qrjGridButton" type="button" value="取消订单"></input>';
			}
			return resultStr;
	       }},
          
      ]);

          var toolbar = new Ext.Toolbar({
	      height:60,
              items: [
		{
		   id:"searchInput",
		   xtype:'textfield',
		   width:200,
		   height:30,
		   autoHeight:false,
		   emptyText:"收入手机号或者订单号搜索",
		   style:{
		      margin:"0 10px 0 0"
		   }
		},
                {
		    xtype:'button',
		    id:"searchButton",
		    text: '<b><font color="red">搜索</font></b>',
                    handler: function() {
		        mNoFilter = Ext.getCmp("searchInput");
			var mNo =  mNoFilter.getValue();
			if(mNo=="") {
                             // userStore.load(userStore.lastOptions);
                             delete orderStore.lastOptions.params.mobileNo;
			     orderStore.load(orderStore.lastOptions);
			} else if(isNum(mNo) && mNo.length<=11 ) {
                             orderStore.lastOptions.params.mobileNo = mNo;
			     orderStore.load(orderStore.lastOptions);
			} else {
				alert("请输入正确的手机号");
			}
                    },
                    style:{
		       border:"1px",
		       borderStyle:"solid",
	               borderColor:"black"
	            }
                },
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">状态筛选</font>',
		{
			id:"stateFilter",
			xtype:"combo",
			width:100,
			store: stateStore,
			mode: 'local',
			triggerAction: 'all',
			allowBlank: false,
			editable: false,
			forceSelection:true,
			displayField: 'text',
			valueField:'value',
			value:0,
			listeners:{
			select : function() {
				      stateFilter =  Ext.getCmp("stateFilter");
				      var sValue = stateFilter.getValue();
				      if(sValue!=0) {
                                         orderStore.lastOptions.params.state = sValue;
				      } else {
                                         delete orderStore.lastOptions.params.state;
				      }
				      orderStore.load(orderStore.lastOptions);
				      
			   }
			},

		},
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">开始时间 从:</font>',
		'<input id="minStartTime" placeholder="YYYY-MM-DD HH:mm:ss" type="text" style="width:150px;"></input>',
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">到:</font>',
		'<input id="maxStartTime" placeholder="YYYY-MM-DD HH:mm:ss" type="text" style="width:150px;"></input>',
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">结束时间 从:</font>',
		'<input id="minStopTime" placeholder="YYYY-MM-DD HH:mm:ss" type="text" style="width:150px;"></input>',
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">到:</font>',
		'<input id="maxStopTime" placeholder="YYYY-MM-DD HH:mm:ss" type="text" style="width:150px;"></input>',
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">支付手段筛选</font>',
		{
			id:"payTypeFilter",
			xtype:"combo",
			width:100,
			store: payTypeStore,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			valueField:'value',
			value:0,
			allowBlank: false,
			editable: false,
			forceSelection:true,
			listeners : {
			   select : function() {
			    }
		        },
		},
		'<span style="margin:20px;"></span>'
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
              //thiz.baseParams["query"] = searchTextField.getValue();
          });
          orderStore.load({params: {start: 0, limit: 20}});
	  $("#minStartTime").datetimepicker({
				 onChangeDateTime:function(dp,$input){ 
					     try {
						    var q = new Date($input.val());
						    orderStore.lastOptions.params.minStartTime = q.getTime();
						    orderStore.load(orderStore.lastOptions);
						    console.log(q.getTime());
					    } catch(err) {
						       //alert(err); 
					    }
					    jQuery('#minStartTime').datetimepicker('hide'); //support hide,show and destroy command
				  }
	   });
	   $("#maxStartTime").datetimepicker({
				 onChangeDateTime:function(dp,$input){ 
					     try {
						    var q = new Date($input.val());
						    orderStore.lastOptions.params.maxStartTime = q.getTime();
						    orderStore.load(orderStore.lastOptions);
						    console.log(q.getTime());
					    } catch(err) {
						      // alert(err); 
					    }
					    jQuery('#maxStartTime').datetimepicker('hide'); //support hide,show and destroy command
				  }
	   });
	   $("#minStopTime").datetimepicker({
				 onChangeDateTime:function(dp,$input){ 
					     try {
						    var q = new Date($input.val());
						    orderStore.lastOptions.params.minStopTime = q.getTime();
						    orderStore.load(orderStore.lastOptions);
					    } catch(err) {
						       //alert(err); 
					    }
					    jQuery('#minStopTime').datetimepicker('hide'); //support hide,show and destroy command
				  }
	   });
	   $("#maxStopTime").datetimepicker({
				 onChangeDateTime:function(dp,$input){ 
					     try {
						    var q = new Date($input.val());
						    orderStore.lastOptions.params.maxStopTime = q.getTime();
						    orderStore.load(orderStore.lastOptions);
					    } catch(err) {
						      // alert(err); 
					    }
					    jQuery('#maxStopTime').datetimepicker('hide'); //support hide,show and destroy command
				  }
	   });
          //制作需要的css
          $(".x-form-text").css({
            "height":"25px"
          });
          $(".x-form-trigger").css({
            "height":"25px"
          });
          $(".x-form-item-label").css({
            "width":"200px",
            "color":"red"
          });
          $(".x-form-element .x-form-field").css({
            "border":"0px solid"
          });
      })
  });
</script>
