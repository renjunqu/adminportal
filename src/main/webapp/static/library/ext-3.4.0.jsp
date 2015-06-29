<%
    String ctx = request.getContextPath();
%>

<%--<link rel="stylesheet" type="text/css" href="<%=ctx%>/library/ext/ext-3.4.0/resources/css/ext-all.css"/>--%>
<script type="text/javascript" src="<%=ctx%>/static/library/ext/ext-3.4.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=ctx%>/static/library/ext/ext-3.4.0/ext-all.js"></script>

<script type="text/javascript" src="<%=ctx%>/static/library/ext/ext-3.4.0/src/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=ctx%>/static/library/ext/extCommon.js"></script>

<script type="text/javascript" src="<%=ctx%>/static/library/ext/ext-3.4.0/ux/styleswitcher.js"></script>
<script type="text/javascript" src="<%=ctx%>/static/library/ext/ext-3.4.0/ux/DataView-more.js"></script>

<link rel="stylesheet" type="text/css" href="<%=ctx%>/static/library/ext/ext-3.4.0/resources/css/ext-all-notheme.css" />
<link rel="stylesheet" type="text/css" href="<%=ctx%>/static/library/ext/ext-3.4.0/resources/css/data-view.css" />
<%--<link rel="stylesheet" type="text/css" title="blue"      href="<%=ctx%>/library/ext/ext-3.4.0/resources/css/xtheme-blue.css" />--%>
<%--<link rel="stylesheet" type="text/css" title="gray"      href="<%=ctx%>/library/ext/ext-3.4.0/resources/css/xtheme-gray.css" />--%>


<link rel="stylesheet" type="text/css"  href="<%=ctx%>/static/library/ext/ext-3.4.0/resources/css/xtheme-gray.css" />

<script type="text/javascript">
    Ext.Ajax.timeout = 900000;



    Ext.apply(Ext.form.VTypes,{
        password:function(val,field){               //val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
            if(field.confirmTo){                    //confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
                var pwd=Ext.get(field.confirmTo);   //取得confirmTo的那个id的值
                return (val==pwd.getValue());
            }
            return true;
        }
    });
</script>