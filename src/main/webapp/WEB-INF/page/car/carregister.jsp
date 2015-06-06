<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
    //Bind time with date selected in the picker
    var query = "";
    $.query = query;

    //Bind time with date selected in the picker
   function BindTimeWithDate(field,date) {
     try {
		  var currentTime = new Date()

	    // Get a current hours and add with date.
	       date.setHours(currentTime.getHours());

	    // Get a current minutes and add with date.
	       date.setMinutes(currentTime.getMinutes());
	       date.setSeconds(currentTime.getSeconds());
                                                 //altFormats: 'Y-m-d  H:i:s',

	       dtDateTimeValue = date.format('Y-m-d  H:i:s');
               console.log("hello here 1");
               field.setValue(dtDateTimeValue);;
	    }
	     catch (e) {
	       alert(e.message);
	     }
    }

    function isNum(str) {
        var re = /^[\d]+$/
        return re.test(str);
    }
    function isRangeNum(str_num, small, big) {
        if (!isNum(str_num)) // 检查是否为数字
            return false

        if (small == "" && big == "")
            throw str_num + "没有定义最大，最小值数字！";

        if (small != "") {
            if (str_num < small)
                return false;
        }
        if (big != "") {
            if (str_num > big)
                return false;
        }
        return true;

    }

    var writer = new Ext.data.JsonWriter({
       encode: true,
       writeAllFields: true // write all fields, not just those that changed
    });


    var store = new Ext.data.JsonStore({
            url: '${contextPath}/carRegister/ext/store',
            root: 'root',
            totalProperty: 'total',
            idProperty:'id',
            fields: ['id', 'licensenum','vinNum','type','vendor','tboxnum','productdate','buydate','enginenum','terminalnum',
                 'carinfostatus','carbackstatus','mileage','restrictdate','updatetime','remarks','registerState','RSAPubKey','RSAPriKey'],
            writer:writer,
        });
    $.store = store;

    function reloadGrid() {
        store.load(store.lastOptions);
    }


    Ext.onReady(function () {
        Ext.QuickTips.init();

        function showCertWin(record,row_index){
	    var fp=new Ext.form.FormPanel({ //注意：Ext.form.FormPanel=Ext.FormPanel
		height:400,
		width:450,
                id:"certPanel",
		layout: {
		    type: 'hbox',
		    pack: 'left',
		    align: 'stretch'
		},
		items:[{
			layout: {type: 'vbox',pack: 'top',align: 'center'},
			height:400, 
			width:300,
			items:[
				 {
				   xtype:'hidden', 
                                   id:"carId",
                                   name:"carId",
				 },
				 {
				   xtype:'label', 
				   text:'公私钥信息 (只能由系统修改)',
				   style:{
				       color:"red",
				       "padding-top":'10px',
				       "padding-bottom":'20px', 
				       "font":"bold 15px arial,sans-serif"
				   }
				 },
				 {
				   xtype:'label', 
				   text:'当前车辆公钥:',
				   width:250,
				   style:{
				       "padding-bottom":'5px',
				   }
				 },
				 {
					xtype:'textarea',  
					width:250 ,
					readOnly:true,
                                   value:record['RSAPubKey'],
					height:100,
                                        id:"rsaKey_pub",
                                        name:"rsaKy_pub",
					style:{
					}
				 },
				 {
				   xtype:'label', 
				   text:'当前车辆私钥:',
				   width:250,
				   style:{
				       "padding-top":'20px',
				       "padding-bottom":'5px',
				   }
				 },
				 {
						id:"rsaKey_pri",
						name:"rsaKey_pri",
						xtype:'textarea',  
                                   value:record['RSAPriKey'],
						width:250 ,
						readOnly:true,
						height:100,
				 },
			 ] 

		      }
			 ,
		      
		{
			layout: {type: 'vbox',pack: 'center',align: 'center'},
			width:150,
			items:[
				   {xtype:'button', text:'重新生成秘钥',width:80,height:50,
				   id:"regenerate_keys",
				   name:"regenerate_keys",
                                   handler:function(btn){
                                           Ext.MessageBox.show({
                                                                    msg: '正在生成秘钥...',
                                                                    progressText: '保存中...',
                                                                    width: 300,
                                                                    wait: true,
                                                                    waitConfig: { interval: 200 },
                                                                    icon: 'download',
                                                                    animateTarget: 'bt5'
                                           });
                                           var ajaxParam = {};
                                           ajaxParam.carId = record['id'];
                                           Ext.Ajax.request({
					       	url: '${contextPath}/carRegister/key/generate',
					       	params: ajaxParam,
					       	method: 'POST',
					       	success: function (response, options) {
                                                    Ext.MessageBox.hide();
					       	    Ext.MessageBox.alert('成功', '重新生成秘钥成功');
                                                     $.res = response; 
                                                     $.opt = options;
                                                     var result  = JSON.parse(response.responseText);
                                                     Ext.getCmp('rsaKey_pri').setValue(result.pri);
                                                     Ext.getCmp('rsaKey_pub').setValue(result.pub);
					       	},
					       	failure: function (response, options) {
                                                    Ext.MessageBox.hide();
					       	    Ext.MessageBox.alert('失败', '生成秘钥失败');
					       	}
					   });

                                   },   
				   style:{
				       "padding-bottom":"10px",
				   }
				    },
				   {xtype:'button', text:'秘钥下发',width:80,height:50,
				   id:"repub_key",
				   name:"repub_key",   
                                   handler:function(btn){
                                           var keyPub = Ext.getCmp('rsaKey_pub').getValue();
                                           if(keyPub.length==0)
                                           {
					       Ext.MessageBox.alert('提示', '请先生成证书');
                                               return;
                                           }
                                           Ext.MessageBox.show({
                                                                    msg: '正在下发证书...',
                                                                    progressText: '保存中...',
                                                                    width: 300,
                                                                    wait: true,
                                                                    waitConfig: { interval: 200 },
                                                                    icon: 'download',
                                                                    animateTarget: 'bt5'
                                           });
                                           var ajaxParam = {};
                                           ajaxParam.carId = record['id'];
                                           Ext.Ajax.request({
					       	url: '${contextPath}/carRegister/key/pub',
					       	params: ajaxParam,
					       	method: 'POST',
					       	success: function (response, options) {
                                                    Ext.MessageBox.hide();
					       	    Ext.MessageBox.alert('成功', '下发证书完毕');
					       	},
					       	failure: function (response, options) {
                                                    Ext.MessageBox.hide();
					       	    Ext.MessageBox.alert('失败', '下发证书失败');
					       	}
					   });

                                   },   
					   style:{
					       "padding-bottom":"10px",
					   }
				   }
			] 

		      }
		   ] 
		});
                  var certWin = new Ext.Window({
                      title:"查看/修改车辆注册信息",
                      modal:true,
                      resizable:false,
                      items:[fp],
                      buttonAlign:'center',
                  });
                  certWin.show(); 
        }


        function showCarWin(record,rowIndex){
		    var formFields = new Array();
                    var columns =  ['id', 'licensenum','vinNum','type','vendor','tboxnum','productdate','buydate','enginenum','terminalnum','carinfostatus','carbackstatus','mileage','restrictdate','updatetime','remarks'];
                    var names =  ['编号', '车牌号','vinNum','车型','供应商','T-BOX 编号','生产日期','购买日期','发动机编号','设备终端号','车辆标识是否齐全','车辆备品是否齐全','车辆续航里程','限行时间','更新时间','备注'];



		    formFields[formFields.length]  = new Ext.form.Hidden({
				id:"rowIndex",
				name:"rowIndex",
				value: rowIndex==null?-1:rowIndex,
			  });
                    $(columns).each(function(colIndex,colName){
                            if(names[colIndex].indexOf('日期')>=0 || names[colIndex].indexOf('时间')>=0 ) {
                              //      console.log('hello here');
				    formFields[formFields.length] = new Ext.form.DateField({
						fieldLabel: names[colIndex],
						id:colName,
						name:colName,
						value: record==null?'':record[colName],
                                                 altFormats: 'Y-m-d  H:i:s',
	                                         format:'Y-m-d  H:i:s',
                                                listeners :  {
						    render: function(el) {
							el.validate();
						    },
                                                 select: function(field,date) {BindTimeWithDate(field,date)}
						}
				     });
                             } else if (names[colIndex].indexOf('是否')>=0){
					  formFields[formFields.length]  = new Ext.form.ComboBox({
					    fieldLabel: names[colIndex],
					    id:colName,
					    name:colName,
					    typeAhead: true,
                                            emptyText : '请选择',            //未选择时显示的文字
                                            blankText : '该选项必选填写',    //未选择时，提交表单显示的错误信息,
					    triggerAction: 'all',
					    lazyRender:true,
                                            value:record==null?0:record[colName],
					    mode: 'local',
					    store: new Ext.data.ArrayStore({
						id: 0,
						fields: [
						    'id',
						    'content'
						],
						data: [[0, '否'], [1, '是']]
					    }),
					    valueField: 'id',
					    displayField: 'content',
                                            listeners :  {
						    render: function(el) {
							el.validate();
						    }
					    }
					});
                             } else {
				    formFields[formFields.length] = new Ext.form.TextField({
						fieldLabel: names[colIndex],
					        id:colName,
					        name:colName,
						value: record==null?'':record[colName],
					    });
                             }
                             //some special config 
                             formField = formFields[formFields.length-1];
                             switch(colIndex){
                                  case 0:
                                           formField.disabled = true;
                                         break;
                                  case 1:
                                  case 2:
                                  case 3:
                                  case 5:
                                  case 7:
                                  case 8:
                                        formField.allowBlank = false;
                                        formField.blankText  = colName + "不能为空";
                                        formField.listeners =  {
					    render: function(el) {
						el.validate();
					    }
					};
                                        break;
                                  default:break;
                             }
                     });
                   
 
		    var fs = new Ext.FormPanel({
                        id:"mwin_panel",
			autoHeight:true,
			buttonAlign: 'left',
			items:[{
			    layout:'column',
			    labelWidth: 200,
			    border:false,
			    defaults:{layout: 'form',columnWidth:1,border: false},
			    items:getFormFieldsForColumnLayout(formFields),
			    anchor:"95%"
			}]
		    });
                  var detailModifyWin = new Ext.Window({
                      title:"查看/修改车辆注册信息",
                      modal:true,
                      width:500,
                      resizable:false,
                      items:[fs],
                      buttonAlign:'center',
                      buttons:[
		              {
				    text: '确定',
				    handler: function (btn) {
                                        console.log('button push ');
                                        var form = Ext.getCmp('mwin_panel').getForm();
                                        if(form.isValid()) {
                                           console.log("input is valid");
                                           var carJson = {};
                                           var carFields =  ['id', 'licensenum','vinNum','type','vendor','tboxnum','productdate','buydate','enginenum','terminalnum','carinfostatus','carbackstatus','mileage','restrictdate','updatetime','remarks'];
                                           $(carFields).each(function(index,e){
                                               console.log("index is "+index);
                                               console.log("e is "+e);
                                               var value = form.findField(e).getValue();
                                              if(value instanceof Date) {
                                                  //console.log("it is a date");
	                                          value = value.format('Y-m-d  H:i:s');
                                              }
                                               carJson[e] = value;//form.findField(e).getValue();
                                            });
                                           console.log(JSON.stringify(carJson));
                                           var rowIndex = form.findField("rowIndex").getValue();
                                           var ajaxParam = {};
                                           if(rowIndex>=0){
                                                 //update operation
                                                 //console.log("it is a update operation");    
                                                 //console.log(rowIndex);
                                                 //console.log($.myData[rowIndex]);
                                                 //$.myData[rowIndex] = carJson;
                                                 ajaxParam.xaction = 'update';
                                           } else {
                                                 //insert operation
                                                 //console.log("it is a insert operation");    
                                                 //$.myData[$.myData.length] = carJson;
                                                 ajaxParam.xaction = 'insert';
                                           }
                                           Ext.apply(ajaxParam,carJson);
                                           Ext.MessageBox.show({
                                                                    msg: '正在更新数据...',
                                                                    progressText: '保存中...',
                                                                    width: 300,
                                                                    wait: true,
                                                                    waitConfig: { interval: 200 },
                                                                    icon: 'download',
                                                                    animateTarget: 'bt5'
                                           });
                                           Ext.Ajax.request({
					       	url: '${contextPath}/carRegister/ext/store',
					       	params: ajaxParam,
					       	method: 'POST',
					       	success: function (response, options) {
                                                    Ext.MessageBox.hide();
					       	    Ext.MessageBox.alert('成功', '车辆信息更新成功');
					       	},
					       	failure: function (response, options) {
					       	    Ext.MessageBox.alert('失败', '车辆信息更新失败');
					       	}
					   });
                                           reloadGrid();
                                           //store.loadData($.myData);
                                           //if()
                                        } else {
                                           console.log("input is not valid");
                                        }
                                        //console.log("try show "+form.getForm().findField("id").getValue());
                                        detailModifyWin.close();
				    }
			      },
		              {
				    text: '取消',
				    handler: function (btn) {
                                        detailModifyWin.close();
				    }
			      },
                      ]
                  });
                  detailModifyWin.show(); 
        }

        var cm = new Ext.grid.ColumnModel([
            {header: 'ID', width: 1, dataIndex: 'id'},
            {header: '车牌号', width: 1, dataIndex: 'licensenum'},
            {header: 'vinNum', width: 1, dataIndex: 'vinNum'},
            {header: '类型', width: 1, dataIndex: 'type'},
            {header: 'T-BOX 编号', width: 1, dataIndex: 'tboxnum'},
            {header: '入网状态123', width: 1, dataIndex: 'registerState',renderer:function(value,cellmeta,record,rowIndex, columnIndex, store){
                            if(record.get('registerState')==0) {
                                return "未完成";
                            } else {
                                return "已完成";
                            }

            }},
              {   header: '操作',
                xtype: 'actioncolumn',
                width: 1,
                items: [
                    {
                        icon: '${contextPath}/static/assets/images/commons/edit.gif',  // Use a URL in the icon config
                        tooltip: '查看详细信息',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = store.getAt(rowIndex);
                            //q = record;
                            showCarWin(record.data,rowIndex);
                            //showCarWin(null);

                        }
                    },{
                        icon: '${contextPath}/static/assets/images/commons/delete.gif',  // Use a URL in the icon config
                        tooltip: '删除',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = store.getAt(rowIndex);
                            Ext.Msg.show({
				   title:'友情提醒',
				   msg: '确定要删除这条数据吗? 此操作不可逆转',
				   buttons: Ext.Msg.YESNO,
				   fn: function(btn){
                                          if(btn=='yes') {
                                              //$.myData.splice(rowIndex,1); 
                                              //record.data.id 
                                              Ext.MessageBox.show({
                                                                    msg: '正在删除...',
                                                                    progressText: '保存中...',
                                                                    width: 300,
                                                                    wait: true,
                                                                    waitConfig: { interval: 200 },
                                                                    icon: 'download',
                                                                    animateTarget: 'bt5'
                                             });
                                              Ext.Ajax.request({
							url: '${contextPath}/carRegister/ext/store',
							params: { id: record.data.id, xaction: 'destroy'},
							method: 'POST',
							success: function (response, options) {
							    Ext.MessageBox.hide();
							    Ext.MessageBox.alert('删除成功', '车辆信息删除成功!');
                                                            reloadGrid();
							},
							failure: function (response, options) {
							    Ext.MessageBox.alert('删除失败', '车辆信息删除失败!');
							}
					     });
                                              
                                          }
                                          //store.loadData($.myData);
                                   },
				   animEl: 'elId'
			    });
                        }
                    },{

                        icon: '${contextPath}/static/assets/images/commons/gears.gif',  // Use a URL in the icon config
                        tooltip: '查看证书信息',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = store.getAt(rowIndex);
                            showCertWin(record.data,rowIndex);
                            //showCarWin(record.data,rowIndex);

                        }
                    }
                ]
            }
        ]);

        var grid = new Ext.grid.GridPanel({
            viewConfig: {forceFit: true},
            autoHeight: true,
            autoScroll: true,
            title: "车辆注册管理",
            store: store,
            cm: cm,
            tbar: new Ext.Toolbar({
                height:40,
                items: [
                    {
                        icon: '${contextPath}/static/assets/images/commons/add.gif',  // Use a URL in the icon config
                        tooltip: '注册一辆新车',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = store.getAt(rowIndex);
                            //q = record;
                            //showCarWin(record.data,rowIndex);
                            showCarWin(null,null);

                        }
                     },
                    {
                        text:"过滤",
                        id:"doFilter",
                        name:"doFilter",
                        handler:function(btn){
                             $.query = "";
                             var cardespFilter = Ext.getCmp("cardespFilter").getValue();
                             if(cardespFilter!=null && cardespFilter.length>0) {
                                 $.query += " Licensenum like \'%" + cardespFilter + "%\' and"; 
                              }
                             var vinNumFilter  = Ext.getCmp("vinNumFilter").getValue();
                             if(vinNumFilter!=null && vinNumFilter.length>0) {
                                 $.query += " vinNum like \'%" + vinNumFilter + "%\' and"; 
                              }
                             var tboxFilter  = Ext.getCmp("tboxFilter").getValue();
                             if(tboxFilter!=null && tboxFilter.length>0) {
                                 $.query += " Tboxnum like \'%" + cardespFilter + "%\' and"; 
                              }
                             if($.query.length > 0 ) {
                                 $.query = $.query.substring(0,$.query.length-3);
                                 params_new = store.lastOptions.params;
                                 params_new.query = $.query;
                                 store.load({params: params_new});
                            } else {
                                 params_new = store.lastOptions.params;
                                 delete params_new['query'];
                                 store.load({params: params_new});
                            }
                        },
			style:{
                             "margin-left":"10px",
			}
                    },
                    {
                        xtype:'label',
                        text:"车牌号: ",
			style:{
			     "font":"italic 12px/20px arial,sans-serif",
                             "padding-left":"10px",
			}
                    },
                    {
                        xtype:'textarea',
                        height:30,
                        width:100,
			id:"cardespFilter",
			name:"cardespFilter",
                        style:{
                               marginLeft:"10px",
                               marginTop:"3px"
                        }
                    },
                    {
                        xtype:'label',
                        text:"vinNum: ",
			style:{
                             "padding-left":"30px",
			     "font":"italic 12px/20px arial,sans-serif"
			}
                    },
                    {
                        xtype:'textarea',
                        height:30,
                        width:100,
			id:"vinNumFilter",
			name:"vinNumFilter",
                        style:{
                               marginLeft:"10px",
                               marginTop:"3px"
                        }
                    },
                    {
                        xtype:'label',
                        text:"T-BOX 编号: ",
			style:{
                             "padding-left":"30px",
			     "font":"italic 12px/20px arial,sans-serif"
			}
                    },
                    {
                        xtype:'textarea',
                        height:30,
                        width:100,
			id:"tboxFilter",
			name:"tboxFilter",
                        style:{
                               marginLeft:"10px",
                               marginTop:"3px"
                        }
                    },
                       
                ]
            }),
            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: store,
                displayInfo: true,
                emptyMsg: "没有记录",
            })
        });
        grid.render("a2");
        store.load({params: {start: 0, limit: 20}});
    })
</script>
<title></title>
</head>
<body>
<div id="a2" style="margin:5px;"></div>
<div id="a3" style="margin:5px;"></div>
<div id="a4" style="margin:5px;"></div>
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function() {

    });
</script>

