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

<script type="text/javascript">

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
                , "corpLatitude","corpLongitude", "lastActiveTime"
            ]          //传入需要显示的字段
        });

        var cm = new Ext.grid.ColumnModel([
            {header: '手机号码', width: 1, dataIndex: 'mobileNo'},
            {header: '用户名', width: 1, dataIndex: 'username'},
            {header: '电子邮件', width: 1, dataIndex: 'email'},
            {header: '性别', width: 1, dataIndex: 'gender',renderer:function(value,cellmeta,record,rowIndex, columnIndex, store){
                console.log("@val:"+value)
                if(value+""==0+"") {
                    return "女";
                } else {
                    return "男";
                }

            }},
            {header: '住址', width: 1, dataIndex: 'addr'},
            {header: '年龄', width: 1, dataIndex: 'age'},
            {header: '身份信息审批状态', width: 1, dataIndex: 'authenticateId',renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
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
            {header: '驾照号码', width: 1, dataIndex: 'authenticateDriver',renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
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
            {header: '家庭住址', width: 1, dataIndex: 'homeAddr'},
            {header: '公司住址', width: 1, dataIndex: 'corpAddr'},
            {   header: '操作',
                xtype: 'actioncolumn',
                width: 0.5,
                items: [
                    {
                        icon: '${contextPath}/static/assets/images/commons/gears.gif',  // Use a URL in the icon config
                        tooltip: '查看用户详细信息',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = userStore.getAt(rowIndex);
                            showUserWin(record);
                        }
                    },
                    {
                        icon: '${contextPath}/static/assets/images/commons/g.gif',  // Use a URL in the icon config
                        tooltip: '用户身份信息审批',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = userStore.getAt(rowIndex);
                            showAuthenticateIdWin(record);
                        }
                    },
                    {
                        icon: '${contextPath}/static/assets/images/commons/car.gif',  // Use a URL in the icon config
                        tooltip: '用户驾照信息审批',
                        handler: function (grid, rowIndex, colIndex) {
                            var record = userStore.getAt(rowIndex);
                            showAuthenticateDriverWin(record);
                        }
                    },
                ]
            }
        ]);
        var searchTextField = new Ext.form.TextField({
            fieldLabel: '搜索'
        });


        var styleData = [
            ['', '全部'],
            ['0', '待审批'],
            ['1', '已审批']
        ];
        var styleStore = new Ext.data.SimpleStore({
            fields: ['value', 'text'],
            data: styleData
        });
        var filterCombo = new Ext.form.ComboBox({
            width:100,
            anchor: '92%',
            id: 'type',
            name: 'type',
            store: styleStore,
            mode: 'local',
            triggerAction: 'all',
            valueField: 'value',
            displayField: 'text',
            allowBlank: false,
            editable: false,
            listeners : {
                select : function(f, r, i) {
                    store.load({params: {start: 0, limit: 20}});
                }
            }
        });
        var filterCombo2 = new Ext.form.ComboBox({
            width:100,
            anchor: '92%',
            store: styleStore,
            mode: 'local',
            triggerAction: 'all',
            valueField: 'value',
            displayField: 'text',
            allowBlank: false,
            editable: false,
            listeners : {
                select : function(f, r, i) {
                    store.load({params: {start: 0, limit: 20}});
                }
            }
        });

        var toolbar = new Ext.Toolbar({
            items: [searchTextField,
                {
                    text: '搜索',
                    handler: function() {
                        store.load({params: {start: 0, limit: 20}});
                    }
                },'->','审批状态过滤：',filterCombo,'驾照号审批过滤：',filterCombo2
            ]
        });

        var grid = new Ext.grid.GridPanel({
            viewConfig: {forceFit: true},
            tbar:toolbar,
//            height:$(window).height(),
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
            thiz.baseParams["query"] = searchTextField.getValue();
            thiz.baseParams['state']=filterCombo.getValue();
            thiz.baseParams['state2']=filterCombo2.getValue();
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

