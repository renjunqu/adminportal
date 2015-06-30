<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<style type="text/css">
.center_align {
     position: absolute;
     left: 50%;
     top: 50%;
     -ms-transform:translate(-50%,-50%);
     -webkit-transform:translate(-50%,-50%);
     -moz-transform:translate(-50%,-50%);
     -o-transform:translate(-50%,-50%);
     transform:translate(-50%,-50%);
}

</style>
<div style="height:90px;width:100%;position:relative;">
      <div style="height:30px;width:100%;">
	      <div style="line-height:30px;height:30px;float:left;margin-left:10px;width:120px;">
		      显示内容选择:
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:120px;height:30px;">
		      <div class="center_align" style="width:120px;">
			      <input  checked=true id="scontent1" type="checkbox" style="width:20px;"/><span style="margin-left:5px;width:80px;">订单数量 </span>
		      </div>
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:120px;height:30px;">
		      <div class="center_align" style="width:120px;">
			      <input  checked=true id="scontent2" type="checkbox" style="width:20px;"/><span style="margin-left:5px;width:80px;">租用时间 </span>
		      </div>
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:120px;height:30px;">
		      <div class="center_align" style="width:120px;">
			      <input  checked=true id="scontent3" type="checkbox" style="width:20px;"/><span style="margin-left:5px;width:80px;">收益 </span>
		      </div>
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:120px;height:30px;">
		      <div class="center_align" style="width:120px;">
			      <input  checked=true id="scontent4" type="checkbox" style="width:20px;"/><span style="margin-left:5px;width:80px;">平均租用时间 </span>
		      </div>
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:120px;height:30px;">
		      <div class="center_align" style="width:120px;">
			      <input  checked=true id="scontent5" type="checkbox" style="width:20px;"/><span style="margin-left:5px;width:80px;">平均收益 </span>
		      </div>
	      </div>
	      <div style="clear:both;"></div>
      </div>
      <div style="height:30px;width:100%;">
	      <div style="float:left;margin-left:10px;width:120px;height:30px;line-height:30px;">
                      图表类型:
              </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType0" style="width:120px;height:25px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType1" style="width:120px;height:25px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType2" style="width:120px;height:25px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType3" style="width:120px;height:25px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType4" style="width:120px;height:25px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="clear:both;"></div>
      </div>
      <div style="height:30px;width:100%;">
	      <div style="float:left;margin-left:10px;width:120px;height:30px;line-height:30px;">
                      时间范围:
              </div>
	      <div style="position:relative;float:left;margin-left:10px;width:230px;height:30px;">
		      <div class="center_align" style="width:230px;">
                              <span style="width:80px;margin-right:10px;">起始时间: </span>
			      <input  style="height:20px;width:140px;" id="date1" type="date" value="2015-04-01" />
		      </div>
	      </div>
	      <div style="position:relative;float:left;margin-left:10px;width:230px;height:30px;">
		      <div class="center_align" style="width:230px;">
                              <span style="width:80px;margin-right:10px;">截止时间: </span>
			      <input  style="height:20px;width:140px;" id="date2" type="date" value="2015-06-30" />
		      </div>
	      </div>
	      <div style="clear:both;"></div>
      </div>
</div>

<div id="container" style="min-width: 310px; height: 720px; margin: 0 auto"></div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [], function() {
      $('#container').highcharts({
        chart: {
            type: 'spline',
	    zoomType:'x'
        },
        title: {
            text: '订单数据统计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
              type: 'datetime',
	      dateTimeLabelFormats:{
	         day:"%Y 年 %m 月 %e日",
	         week:"%Y 年 %m 月 %e日",
	         year:"%Y 年" 
	      },
	     labels: {
                rotation: 90,
                y:20
            },
            title: {
                text: ''
            },
	    min:Date.UTC(2015,3,1),
	    max:Date.UTC(2015,5,30),
	    tickInterval: 24 * 3600 * 1000*7,
        },
        yAxis: [{ 
           // 第一个Y轴,订单个数
	    title:{text:""},
            labels: {
                format: '{value} 个',
                style: {
                    color: Highcharts.getOptions().colors[0]
                }
            }
        }, 
        { //第二个Y轴，订单分钟数
            gridLineWidth: 0,
	    title:{text:""},
            labels: {
                format: '{value} 分钟',
                style: {
                    color: Highcharts.getOptions().colors[1]
                }
            },
        }, 
	{ //  第三个坐标轴，订单收益
            gridLineWidth: 0,
	    title:{text:""},
            labels: {
                format: '{value} 元',
                style: {
                    color: Highcharts.getOptions().colors[2]
                }
            },
            opposite: true
        },
	{ //  第四个坐标轴，订单平均分钟数
            gridLineWidth: 0,
	    title:{text:""},
            labels: {
                format: '{value} 分钟/订单',
                style: {
                    color: Highcharts.getOptions().colors[3]
                }
            },
            opposite: true
        },
	{ //  第五个坐标轴，订单收益
	    title:{text:""},
            gridLineWidth: 0,
            labels: {
                format: '{value} 元/订单',
                style: {
                    color: Highcharts.getOptions().colors[4]
                }
            },
            opposite: true
        }
      ],





        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
         rangeSelector: {
            selected: 1
        },
       series: [
		{
		    name: '订单数量',
		    marker: {
		      enabled:false
		    },
		    data:
		      [
			[Date.UTC(2015,3,1),89],
			[Date.UTC(2015,4,2),8],
			[Date.UTC(2015,5,1),9],
			[Date.UTC(2015,6,1),23],
		     ],
		    color: Highcharts.getOptions().colors[0],
		    visible:true,
		    yAxis:0,
		    tooltip: {
				valueSuffix: ' 个'
		    }
		    
		}, 
		{
		    name: '租用分钟数',
		    marker: {
		      enabled:false
		    },
		    data:
		      [
			[Date.UTC(2015,2,1),1],
			[Date.UTC(2015,4,1,20),2],
			[Date.UTC(2015,5,1),3],
			[Date.UTC(2015,6,1),4],
		     ],
		    visible:true,
		    color: Highcharts.getOptions().colors[1],
		    tooltip: {
				valueSuffix: ' 分钟'
		    },
		    yAxis:1
		},
		{
		    name: '收益',
		    marker: {
		      enabled:false
		    },
		    data:
		      [
			[Date.UTC(2015,2,1),18.9],
			[Date.UTC(2015,4,1,20),289],
			[Date.UTC(2015,5,1),2.89],
			[Date.UTC(2015,6,1),38.9],
		     ],
		    visible:true,
		    color: Highcharts.getOptions().colors[2],
		    tooltip: {
				valueSuffix: ' 元'
		    },
		    yAxis:2
		},
		{
		    name: '订单平均时间',
		    marker: {
		      enabled:false
		    },
		    data:
		      [
			[Date.UTC(2015,2,1),8.9],
			[Date.UTC(2015,4,1,20),189],
			[Date.UTC(2015,5,1),289],
			[Date.UTC(2015,6,1),289],
		     ],
		    visible:true,
		    color: Highcharts.getOptions().colors[3],
		    tooltip: {
				valueSuffix: ' 分钟/订单'
		    },
		    yAxis:3
		}, 
		{
		    name: '订单平均收益',
		    marker: {
		      enabled:false
		    },
		    data:
		      [
			[Date.UTC(2015,2,1),8],
			[Date.UTC(2015,4,1,20),12],
			[Date.UTC(2015,5,1),12],
			[Date.UTC(2015,6,1),90],
		     ],
		    visible:true,
		    color: Highcharts.getOptions().colors[4],
		    tooltip: {
				valueSuffix: ' 元/订单'
		    },
		    yAxis:4
		} 

        ]
    });
    //注册过滤事件
    $("[id^=picType]").on("change",function(e,value){
        var sid = $(this).attr("id");
        var ctype = $(this).val(); 
        sid = parseInt(sid.replace("picType",""));
        var chart = $('#container').highcharts();
        chart.series[sid].update({
                   type:ctype
        }); 
    });
    $("[id^=scontent]").on("change",function(){
        var sid = $(this).attr("id");
        var svalue = $(this).is(':checked');
        sid = parseInt(sid.replace("scontent","")) - 1;
        var chart = $('#container').highcharts();
        chart.series[sid].update({
            visible:svalue
        }); 
    });
    $("#date1").on("change",function(){
		var timeStr = $(this).val();
		var time;
		if(timeStr.length==0) {
	            $(this).val("2015-04-01");	
		    time = new Date("2015-04-01");      
		} else {
		    time = new Date(timeStr);      
		    var timeValue = time.getTime();
		    var maxTimeStr = new Date($("#date2").val());
		    var maxTimeValue = maxTimeStr.getTime();
		    if(maxTimeValue<=timeValue) {
		     //最小时间不能大于等于开始时间 
			    $(this).val("2015-04-01");	
			    time = new Date("2014-04-01");      
		    }
		}
		var chart = $('#container').highcharts();
		chart.xAxis[0].update({
		      min:time.getTime()	    
	        });
    });
    $("#date2").on("change",function(){
		var timeStr = $(this).val();
		var time;
		if(timeStr.length==0) {
	            $(this).val("2015-06-30");	
		    time = new Date("2015-06-30");      
		} else {
		    var chart = $('#container').highcharts();
		    time = new Date(timeStr);  
		    var timeValue = time.getTime();
		    var minTimeStr = new Date($("#date1").val());
		    var minTimeValue = minTimeStr.getTime();
		    if(minTimeValue>=timeValue) {
		     //最小时间不能大于等于开始时间 
			    $(this).val("2015-06-30");	
			    time = new Date("2015-06-30");      
		    }
		}
		var chart = $('#container').highcharts();
		chart.xAxis[0].update({
		      max:time.getTime()	    
	        });
    });
});
</script>
