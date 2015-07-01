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
	    pointStart:Date.UTC(2015,3,1),
	    pointInterval:1000*3600*24,
	    name: '订单数量',
	    marker: {
		      enabled:false
	    },
            color: Highcharts.getOptions().colors[0],
	    visible:true,
	    yAxis:0,
	    tooltip: {
			valueSuffix: ' 个'
	    },

    data:[6,21,29,37,38,50,58,69,78,79,91,97,102,114,120,134,135,145,149,162,167,180,189,196,203,212,221,226,236,242,250,259,261,271,284,292,300,309,315,326,326,339,341,358,358,372,383,388,397,403,407,422,427,429,443,446,455,461,471,482,487,497,507,509,524,527,540,547,549,562,574,578,590,591,601,612,614,629,637,642,647,658,669,675,684,687,695,707,709,725,729,735,]
},
{
	    pointStart:Date.UTC(2015,3,1),
	    pointInterval:1000*3600*24,
	    name: '租用时间',
	    marker: {
		      enabled:false
	    },
            color: Highcharts.getOptions().colors[1],
	    visible:true,
	    yAxis:1,
	    tooltip: {
			valueSuffix: ' 分钟'
	    },

    data:[17,47,140,245,278,527,407,1118,841,1605,1010,2228,1580,2475,2691,4113,4307,3953,3753,4062,5250,6964,8471,8200,10017,7788,8271,6462,11146,8782,8504,10061,11469,14206,17451,10530,19493,15148,16305,13683,17537,20259,17562,23806,28171,24391,22436,20323,29201,35826,22274,24624,33189,35346,34569,47593,38677,38590,47712,40356,42316,56350,31834,34909,44122,40391,43062,59978,44983,60246,72901,78123,61309,86998,66493,87826,92156,71278,81936,94300,73800,108847,90639,63137,112225,79757,87160,82971,69722,79578,100782,70756,]
},
{
	    pointStart:Date.UTC(2015,3,1),
	    pointInterval:1000*3600*24,
	    name: '收益',
	    marker: {
		      enabled:false
	    },
            color: Highcharts.getOptions().colors[2],
	    visible:true,
	    yAxis:2,
	    tooltip: {
			valueSuffix: ' 元'
	    },

    data:[157.14,178.41,419.32,381.33,338.91,406.45,530.63,947.06,735.04,687.39,1100.23,1219.43,1052.62,1354.78,1018.15,1914.91,1798.43,1727.84,1268.39,1661.69,1415.78,1880.82,2558.12,1830.87,1819.83,2312.31,1810.79,2393.36,1883.33,2268.37,2349.85,3451.26,3727.89,4022.66,2556.15,3051.65,3837.54,4242.06,2455.63,3196.32,4305.62,4025.14,3216.63,4049.98,3414.83,3914.92,3979.22,5411.53,4343.47,5048.81,5559.94,4904.96,4500.32,5465.10,5040.21,4272.75,3991.51,5677.95,6113.16,5115.58,5082.86,7136.75,6676.60,5773.71,6670.51,5152.60,6108.23,7849.71,4353.71,6955.87,5352.30,7540.20,4626.50,6904.01,4767.88,8473.89,5408.46,8566.59,8995.16,6407.00,6152.43,8196.70,5863.17,6501.11,7142.03,9335.37,6014.78,8611.36,9429.32,5939.91,5904.28,6860.00,]
},
{
	    pointStart:Date.UTC(2015,3,1),
	    pointInterval:1000*3600*24,
	    name: '平均租用时间',
	    marker: {
		      enabled:false
	    },
            color: Highcharts.getOptions().colors[3],
	    visible:true,
	    yAxis:3,
	    tooltip: {
			valueSuffix: ' 时间/订单'
	    },

    data:[2.83,2.24,4.83,6.62,7.32,10.54,7.02,16.20,10.78,20.32,11.10,22.97,15.49,21.71,22.43,30.69,31.90,27.26,25.19,25.07,31.44,38.69,44.82,41.84,49.34,36.74,37.43,28.59,47.23,36.29,34.02,38.85,43.94,52.42,61.45,36.06,64.98,49.02,51.76,41.97,53.79,59.76,51.50,66.50,78.69,65.57,58.58,52.38,73.55,88.90,54.73,58.35,77.73,82.39,78.03,106.71,85.00,83.71,101.30,83.73,86.89,113.38,62.79,68.58,84.20,76.64,79.74,109.65,81.94,107.20,127.01,135.16,103.91,147.20,110.64,143.51,150.09,113.32,128.63,146.88,114.06,165.42,135.48,93.54,164.07,116.09,125.41,117.36,98.34,109.76,138.25,96.27,]
},
{
	    pointStart:Date.UTC(2015,3,1),
	    pointInterval:1000*3600*24,
	    name: '平均收益',
	    marker: {
		      enabled:false
	    },
            color: Highcharts.getOptions().colors[4],
	    visible:true,
	    yAxis:4,
	    tooltip: {
			valueSuffix: ' 元/订单'
	    },

    data:[26.19,8.50,14.46,10.31,8.92,8.13,9.15,13.73,9.42,8.70,12.09,12.57,10.32,11.88,8.48,14.29,13.32,11.92,8.51,10.26,8.48,10.45,13.54,9.34,8.96,10.91,8.19,10.59,7.98,9.37,9.40,13.33,14.28,14.84,9.00,10.45,12.79,13.73,7.80,9.80,13.21,11.87,9.43,11.31,9.54,10.52,10.39,13.95,10.94,12.53,13.66,11.62,10.54,12.74,11.38,9.58,8.77,12.32,12.98,10.61,10.44,14.36,13.17,11.34,12.73,9.78,11.31,14.35,7.93,12.38,9.32,13.05,7.84,11.68,7.93,13.85,8.81,13.62,14.12,9.98,9.51,12.46,8.76,9.63,10.44,13.59,8.65,12.18,13.30,8.19,8.10,9.33,]
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
