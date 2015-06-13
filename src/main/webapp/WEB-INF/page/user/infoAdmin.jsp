<%--
  Created by IntelliJ IDEA.
  User: figoxu
  Date: 15/4/20
  Time: 下午3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<style type="text/css">     
.qrjButtonStyle {
    border: 10px;
    color:#fff;
 }
.x-form-text {
  height:25px !important;
}
.x-form-trigger {
  height:25px !important;
}

.qrjGridButton {
    padding:2px;
    margin:2px;
}
</style>

<script type="text/javascript">
   function ifPhoneNo(inputtxt)  
   {  
      var phoneno = /^\d{11}$/;  
     if((inputtxt.match(phoneno)))  
     {  
        return true;  
     }  else  {  
         return false;  
     }  
   } 

    function renderMaybeEmpty(value) {
            if(value==""||value==undefined||value==null)
		    return '<font color="gray">--用户未输入--</font>';
	    else 
		    return value;
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

    function viewInfoClick(row) {
	    var record = userStore.getAt(row);
	    showUserWin(record);
    }

    function authIdClick(row){
	    var record = userStore.getAt(row);
	    showAuthenticateIdWin(record);
    
    }

   function authDriverClick(row) {
	    var record = userStore.getAt(row);
	    showAuthenticateDriverWin(record);
   }


    var userStore;

    function reloadGrid() {
        userStore.load(userStore.lastOptions);
    }

    function authenticateUser(type,result,id,win){
         var url = ""; 
	    if(type=="id")
                   url ="${contextPath}/userAdmin/approve/id";
	    else 
                   url ="${contextPath}/userAdmin/approve/drive";
	    Ext.Ajax.request({
		url: url,
		method: 'POST',
		params:{
		    id:id,
		    result:result
		},
		text: "Updating...",
		success: function (result, request) {
		    Ext.MessageBox.alert('系统提示', '审批状态修改成功。');
		    reloadGrid();
		    win.hide();
		    delete win;
		},
		failure: function (result, request) {
		    Ext.MessageBox.alert('系统提示', '系统繁忙。');
		}
	    });
    }

    function showAuthenticateIdWin(record) {

        var formFields = new Array();
        formFields[formFields.length] = new Ext.form.Label({
			text:"身份证正面图片",
			style:{"padding":"10px 10px"}
        });
        formFields[formFields.length] = new Ext.BoxComponent({
            width: 200, //图片宽度
            height: 200, //图片高度
            autoEl: {
                tag: 'img',    //指定为img标签
                src: '${contextPath}/userAdmin/image/store?type=id&direction=pos&mobileNo='+ record.get('mobileNo'),    //指定url路径
		alt:"   正在加载图片，请稍后...."
            }
        });
        formFields[formFields.length] = new Ext.form.Label({
			text:"身份证背面图片",
			style:{"padding":"10px 10px"}
        });
        formFields[formFields.length] = new Ext.BoxComponent({
            width: 200, //图片宽度
            height: 200, //图片高度
            autoEl: {
                tag: 'img',    //指定为img标签
                src: '${contextPath}/userAdmin/image/store?type=id&direction=back&mobileNo='+record.get('mobileNo'),    //指定url路径
		alt:"   正在加载图片，请稍后...."
            }
        });
        var authFP = new Ext.FormPanel({
                autoHeight:true,
                bodyStyle:"padding:10px",
                buttonAlign: 'center',
                items:[{
                    layout:'form',
                    border:false,
                    defaults:{
                     layout: 'form',
		     border: false
		    },
                    items:getFormFieldsForColumnLayout(formFields),
		    }]});
        var authUserWin = new Ext.Window({
            title: "审核用户身份信息",
            modal: true,
            width: 640,
            height: 640,
            resizable: false,
            autoScroll: true,
            items: [ authFP ], 
            buttonAlign: 'center',
	    buttons:[{
                        text: '审批通过',
                        formBind: true,
                        handler: function (btn, evt) {
				authenticateUser("id",1,record.get("id"),authUserWin);
                        }
                    },{
                        text: '审批失败',
                        formBind: true,
                        handler: function (btn, evt) {
				authenticateUser("id",3,record.get("id"),authUserWin);
			}
            }] 
        });
        authUserWin.show();
    }
    function showAuthenticateDriverWin(record) {

        var formFields = new Array();
        formFields[formFields.length] = new Ext.form.Label({
			text:"驾照图片",
			style:{"padding":"10px 10px"}
        });
        formFields[formFields.length] = new Ext.BoxComponent({
            width: 200, //图片宽度
            height: 200, //图片高度
            autoEl: {
                tag: 'img',    //指定为img标签
                src: '${contextPath}/userAdmin/image/store?type=driver&direction=pos&mobileNo='+ record.get('mobileNo'),    //指定url路径
		alt:"   正在加载图片，请稍后...."
            }
        });
        var authFP = new Ext.FormPanel({
                autoHeight:true,
                bodyStyle:"padding:10px",
                buttonAlign: 'center',
                items:[{
                    layout:'form',
                    border:false,
                    defaults:{
                     layout: 'form',
		     border: false
		    },
                    items:getFormFieldsForColumnLayout(formFields),
		    }]});
        var authDriverWin = new Ext.Window({
            title: "审核用户驾照信息",
            modal: true,
            width: 640,
            height: 320,
            resizable: false,
            autoScroll: true,
            items: [ authFP ], 
            buttonAlign: 'center',
	    buttons:[{
                        text: '审批通过',
                        formBind: true,
                        handler: function (btn, evt) {
				authenticateUser("driver",1,record.get("id"),authDriverWin);
                        }
                    },{
                        text: '审批失败',
                        formBind: true,
                        handler: function (btn, evt) {
				authenticateUser("driver",3,record.get("id"),authDriverWin);
			}
            }] 
        });
        authDriverWin.show();
    }

    function showUserWin(record){
        var id = record.get("id");
        var mobileNo = record.get("mobileNo");
        var username = record.get("username");
        var gender = record.get("gender");
        var addr = record.get("addr");
        var age = record.get("age");
        var email = record.get("email");
        var homeAddr = record.get("homeAddr");
        var corpAddr = record.get("corpAddr");
        this.getFormPanel = function(){
            var formFields = new Array();
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '手机号码',
                id:'mobileNo',
                name: 'mobileNo',
                value:mobileNo
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '用户名',
                id:'username',
                name: 'username',
                value:username
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '电子邮件',
                id:'email',
                name: 'email',
                value:email
                ,readOnly : true
            });
            var authenticateId = record.get("authenticateId");
            var authenticateDriver = record.get("authenticateDriver");
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '性别',
                format:'Y-m-d',
                id:'gender',
                name: 'gender',
                value:gender
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '住址',
                id:'addr',
                name: 'addr',
                value:addr
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '年龄',
                id:'age',
                name: 'age',
                value:age
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '身份信息审批状态',
                id:'authenticateId',
                name: 'authenticateId',
                value:authenticateId
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '驾照信息审批状态',
                format:'Y-m-d',
                id:'authenticateDriver',
                name: 'authenticateDriver',
                value:authenticateDriver
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '家庭住址',
                id:'homeAddr',
                name: 'homeAddr',
                value:homeAddr
                ,readOnly : true
            });
            formFields[formFields.length] = new Ext.form.TextField({
                fieldLabel: '公司住址',
                id:'corpAddr',
                name: 'corpAddr',
                value:corpAddr
                ,readOnly : true
            });
            var fs = new Ext.FormPanel({
                autoHeight:true,
                bodyStyle:"padding:10px",
                buttonAlign: 'center',
                items:[{
                    layout:'column',
                    border:false,
                    defaults:{layout: 'form',columnWidth:.5,border: false},
                    items:getFormFieldsForColumnLayout(formFields),
                    anchor:"95%"
                }]
            });
            return fs;
        }

        this.getOrderGrid = function(id){
            var orderStore = new Ext.data.JsonStore({
                url: '${contextPath}/userAdmin/order/ext/store',
                root: 'root',
                totalProperty: 'total',
//                fields: ["id","mobileNo", "carId","startTime","stopTime", "delMark","rentstatus"
//                    ,"type", "batonMode", "state", "carVinnum", "destination"]          //传入需要显示的字段

                fields: ["id"
                    , "mobileNo","carId","startTime", "stopTime"
                    ,"rentstatus","delMark", "batonMode","state", "carVinnum","destination"
                ]
            });

            orderStore.on('beforeload', function (thiz, options) {
                thiz.baseParams["mobileNo"] = mobileNo;
            });

            var cm = new Ext.grid.ColumnModel([
                {header: '订单号', width: 1, dataIndex: 'id'},
                {header: '手机号码', width: 1, dataIndex: 'mobileNo'},
                {header: '车辆ID', width: 1, dataIndex: 'carId'},
                {header: '下单时间', width: 1, dataIndex: 'startTime'},
                {header: '还车时间', width: 1, dataIndex: 'stopTime'},
                {header: '订单状态', width: 1, dataIndex: 'state'},
                {header: '车辆号', width: 1, dataIndex: 'carVinnum'},
                {header: '目的地', width: 1, dataIndex: 'destination'}
            ]);

            var orderGrid = new Ext.grid.GridPanel({
                viewConfig: {forceFit: true},
                autoHeight: true,
                autoScroll: true,
                title: "订单信息",
                store: orderStore,
                cm: cm,
	        collapsible: true,
	        collapsed:true,
                bbar: new Ext.PagingToolbar({
                    pageSize: 20,
                    store: orderStore,
                    displayInfo: true,
                    emptyMsg: "没有记录"
                })
            });
            orderStore.load({params: {start: 0, limit: 20}});

            return orderGrid;
        };

        var fs =  this.getFormPanel(id);
        var grid = this.getOrderGrid();


        var height = $(window).height()-200;
        var width = $(window).width()-200;
        var modifyWin = new Ext.Window({
            title: "查看详细信息",
            modal: true,
            width: width,
            height: height,
            resizable: false,
            autoScroll: true,
            items: [ fs,grid ]
        });
        modifyWin.show();
    }

    Ext.onReady(function () {
        Ext.QuickTips.init();

        userStore = new Ext.data.JsonStore({
            url: '${contextPath}/user/ext/store',
            root: 'root',
            totalProperty: 'total',
            fields: ["id","mobileNo", "username","userpwd", "email","registerTime", "gender","addr"
                , "age","failedTimes", "lockedTime","pushKey", "pushKey","authenticateId"
                , "authenticateDriver","deposit", "homeAddr","corpAddr", "homeLatitude","homeLongitude"
                , "corpLatitude","corpLongitude", "lastActiveTime","idNo","idName","driverLicenseNumber"
            ]          //传入需要显示的字段
        });

        var cm = new Ext.grid.ColumnModel([
            {header: '手机号', width: 1, sortable:true,dataIndex: 'mobileNo',renderer:renderMaybeEmpty},
            {header: '用户姓名', width: 1, sortable:true,dataIndex: 'idName',renderer:renderMaybeEmpty},
            {header: '性别', width: 1, dataIndex: 'gender',renderer:function(value){
                console.log("@val:"+value)
                if(value+""==0+"") {
                    return "女";
                } else {
                    return "男";
                }

            }},
            {header: '身份证号', sortable:true,width: 1, dataIndex: 'idNo',renderer:renderMaybeEmpty},
            {header: '驾驶证号', sortable:true,width: 1, dataIndex: 'driverLicenseNumber',renderer:renderMaybeEmpty},
            {header: '身份信息审批状态', width: 1, sortable:true,dataIndex: 'authenticateId',renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
                    var val =  "审批通过";
                    if(value==0){
                        val = "未上传信息";
                    } else if(value==1) {
                        val = "审批通过";
                    } else if(value==2) {
                        val= "审批中";
                    } else if(value==3) {
                        val="审批未通过";
                    }
                    return val;
                }
            },
            {header: '驾照信息审批状态', sortable:true,width: 1, dataIndex: 'authenticateDriver',renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
                var val =  "审批通过";
                if(value==0){
                    val = "未上传信息";
                } else if(value==1) {
                    val = "审批通过";
                } else if(value==2) {
                    val= "审批中";
                } else if(value==3) {
                    val="审批未通过";
                }
                    return val;
                }
            },
            {   header: '操作',
                width: 2.5,
		renderer:function(value,meta,record,rowIndex){
			var resultStr = '<input class="qrjGridButton" onclick="viewInfoClick('+rowIndex+');" type="button" value="查看详细信息"></input>';
			var authIdState = parseInt(record.get('authenticateId'));
			var authDriverState = parseInt(record.get('authenticateDriver'));

			if(authIdState==2) {
		               resultStr += '<input class="qrjGridButton" onclick="authIdClick('+rowIndex+');" type="button" value="审核用户身份信息"></input>';
			} 
			if(authDriverState==2) {
		               resultStr += '<input class="qrjGridButton" onclick="authDriverClick('+rowIndex+');" type="button" value="审核用户驾照信息"></input>';
			}
			return resultStr;
		},
            }
        ]);


        var stateStore = new Ext.data.SimpleStore({
            fields: ['value', 'text'],
            data: [
		    ['0', '全部'],
		    ['1', '审批通过'],
		    ['2', '审批中'],
		    ['3', '审批未通过'],
		]
        });

        var toolbar = new Ext.Toolbar({
	    height:60,
	    items: [
		{
		   id:"mobileNoFilter",
		   xtype:'textfield',
		   width:200,
		   height:30,
		   autoHeight:false,
		   emptyText:"手机号",
		   style:{
		      margin:"0 10px 0 0"
		   }
		},
                {
		    xtype:'button',
		    id:"searchButton",
		    text: '<b><font color="red">手机号筛选</font></b>',
                    handler: function() {
		        mNoFilter = Ext.getCmp("mobileNoFilter");
			var mNo =  mNoFilter.getValue();
			if(mNo=="") {
                             // userStore.load(userStore.lastOptions);
                             delete userStore.lastOptions.params.mobileNo;
                             reloadGrid(); 
			} else if(isNum(mNo) && mNo.length<=11 ) {
                             userStore.lastOptions.params.mobileNo = mNo;
                             reloadGrid(); 
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
		'->',//现在开始进行右对齐
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">身份审核状态筛选</font>',
		{
			id:"idStateFilter",
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
			value:"全部",
			select : function(f, r, i) {
				      isFilter =  Ext.getCmp("idStateFilter");
				      var isValue = isFilter.getValue();
				      if(isValue!=0) {
                                         userStore.lastOptions.params.authenticateId = isValue;
				      } else {
                                         delete userStore.lastOptions.params.authenticateId;
				      }
                                      reloadGrid(); 
			}

		},
		'<font style="border:#000 1px solid;color:red;margin:10px;padding:4px;">驾照审核状态筛选</font>',
		{
			id:"driverStateFilter",
			xtype:"combo",
			width:100,
			store: stateStore,
			mode: 'local',
			triggerAction: 'all',
			valueField: 'value',
			displayField: 'text',
			valueField:'value',
			value:"全部",
			allowBlank: false,
			editable: false,
			forceSelection:true,
			listeners : {
			   select : function(f, r, i) {
				      dsFilter =  Ext.getCmp("driverStateFilter");
				      var dsValue = dsFilter.getValue();
				      if(dsValue!=0) {
                                         userStore.lastOptions.params.authenticateDriver = dsValue;
				      } else {
                                         delete userStore.lastOptions.params.authenticateDriver;
				      }
                                      reloadGrid(); 
			    }
		        },
			/*
			tpl:'<tpl for=".">' +   
				    '<div class="x-combo-list-item" style="height:30px;">' +   
				    '{text}' +   
				    '</div>'+   
			    '</tpl>'  
			*/
		},
		'<span style="margin:20px;"></span>'
            ]
        });

        var grid = new Ext.grid.GridPanel({
            viewConfig: {forceFit: true},
            tbar:toolbar,
            //height:$(window).height(),
            autoHeight:true,
            autoScroll: true,
            title: "用户管理",
            store: userStore,
            cm: cm,
            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: userStore,
                displayInfo: true,
                emptyMsg: "没有记录"
            })
        });
        grid.render("a2");

        userStore.on('beforeload', function (thiz, options) {
            //thiz.baseParams["query"] = searchTextField.getValue();
            //thiz.baseParams['state']=filterCombo.getValue();
            //thiz.baseParams['state2']=filterCombo2.getValue();
        });
        userStore.load({params: {start: 0, limit: 20}});
    })
</script>
<title></title>
</head>
<body>
<div id="a2" style="margin:5px;"></div>
<div id="a3" style="margin:5px;"></div>
<div id="a4" style="margin:5px;"></div>
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [], function() {

    });
</script>

