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
		      <select id="picType0" style="width:120px;height:20px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType1" style="width:120px;height:20px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType2" style="width:120px;height:20px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType3" style="width:120px;height:20px;">
				<option value="line">流线图</option>
				<option value="column">柱状图</option>
		       </select>
	      </div>
	      <div style="float:left;margin-left:10px;width:120px;">
		      <select id="picType4" style="width:120px;height:20px;">
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
			      <input  style="height:20px;width:140px;" id="date2" type="date" value="2015-04-01" />
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
            type: 'spline'
        },
        title: {
            text: '订单数据统计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
              type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b'
            },
            title: {
                text: '运营时间(月/&lt;周序号&gt;)'
            }
        },
        yAxis: {
            title: {
                text: '订单数/平均租用时间(分钟)'
            },
            min:0,
            max:100
        },
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
       series: [
		{
		    name: '订单数量',
		    marker: {
			symbol: 'square'
		    },
		    data:
		      [
			[Date.UTC(2015,4,1),89],
			[Date.UTC(2015,4,2),24],
			[Date.UTC(2015,4,3),87],
			[Date.UTC(2015,4,4),85],
			[Date.UTC(2015,4,5),64],
			[Date.UTC(2015,4,6),93],
			[Date.UTC(2015,4,7),61],
			[Date.UTC(2015,4,8),78],
			[Date.UTC(2015,4,9),62],
			[Date.UTC(2015,4,10),7],
			[Date.UTC(2015,4,11),84],
			[Date.UTC(2015,4,12),10],
			[Date.UTC(2015,4,13),15],
			[Date.UTC(2015,4,14),12],
			[Date.UTC(2015,4,15),26],
			[Date.UTC(2015,4,16),9],
			[Date.UTC(2015,4,17),82],
			[Date.UTC(2015,4,18),20],
			[Date.UTC(2015,4,19),59],
			[Date.UTC(2015,4,20),63],
			[Date.UTC(2015,4,21),10],
			[Date.UTC(2015,4,22),3],
			[Date.UTC(2015,4,23),6],
			[Date.UTC(2015,4,24),10],
			[Date.UTC(2015,4,25),39],
			[Date.UTC(2015,4,26),47],
			[Date.UTC(2015,4,27),70],
			[Date.UTC(2015,4,28),44],
			[Date.UTC(2015,5,1),78],
			[Date.UTC(2015,5,2),3],
			[Date.UTC(2015,5,3),55],
			[Date.UTC(2015,5,4),69],
			[Date.UTC(2015,5,5),38],
			[Date.UTC(2015,5,6),24],
			[Date.UTC(2015,5,7),45],
			[Date.UTC(2015,5,8),93],
			[Date.UTC(2015,5,9),48],
			[Date.UTC(2015,5,10),91],
			[Date.UTC(2015,5,11),81],
			[Date.UTC(2015,5,12),64],
			[Date.UTC(2015,5,13),23],
			[Date.UTC(2015,5,14),60],
			[Date.UTC(2015,5,15),5],
			[Date.UTC(2015,5,16),16],
			[Date.UTC(2015,5,17),48],
			[Date.UTC(2015,5,18),57],
			[Date.UTC(2015,5,19),1],
			[Date.UTC(2015,5,20),45],
			[Date.UTC(2015,5,21),1],
			[Date.UTC(2015,5,22),53],
			[Date.UTC(2015,5,23),69],
			[Date.UTC(2015,5,24),73],
			[Date.UTC(2015,5,25),47],
			[Date.UTC(2015,5,26),97],
			[Date.UTC(2015,5,27),30],
			[Date.UTC(2015,5,28),91],
			[Date.UTC(2015,5,29),89],
			[Date.UTC(2015,5,30),52],
			[Date.UTC(2015,5,31),42],
			[Date.UTC(2015,6,1),64],
			[Date.UTC(2015,6,2),89],
			[Date.UTC(2015,6,3),15],
			[Date.UTC(2015,6,4),42],
			[Date.UTC(2015,6,5),38],
			[Date.UTC(2015,6,6),59],
			[Date.UTC(2015,6,7),54],
			[Date.UTC(2015,6,8),86],
			[Date.UTC(2015,6,9),50],
			[Date.UTC(2015,6,10),53],
			[Date.UTC(2015,6,11),20],
			[Date.UTC(2015,6,12),60],
			[Date.UTC(2015,6,13),17],
			[Date.UTC(2015,6,14),55],
			[Date.UTC(2015,6,15),47],
			[Date.UTC(2015,6,16),90],
			[Date.UTC(2015,6,17),98],
			[Date.UTC(2015,6,18),97],
			[Date.UTC(2015,6,19),9],
			[Date.UTC(2015,6,20),10],
			[Date.UTC(2015,6,21),41],
			[Date.UTC(2015,6,22),64],
			[Date.UTC(2015,6,23),87],
			[Date.UTC(2015,6,24),97],
			[Date.UTC(2015,6,25),32],
			[Date.UTC(2015,6,26),72],
			[Date.UTC(2015,6,27),1],
			[Date.UTC(2015,6,28),1],
			[Date.UTC(2015,6,29),17],
			[Date.UTC(2015,6,30),62]
		     ],
		    visible:true
		}, 
		{
		    name: '租用时间(分钟)',
		    marker: {
			symbol: 'diamond'
		    },
		     data: [
			[Date.UTC(2015,4,1),96],
			[Date.UTC(2015,4,2),54],
			[Date.UTC(2015,4,3),76],
			[Date.UTC(2015,4,4),49],
			[Date.UTC(2015,4,5),17],
			[Date.UTC(2015,4,6),13],
			[Date.UTC(2015,4,7),10],
			[Date.UTC(2015,4,8),51],
			[Date.UTC(2015,4,9),45],
			[Date.UTC(2015,4,10),58],
			[Date.UTC(2015,4,11),83],
			[Date.UTC(2015,4,12),17],
			[Date.UTC(2015,4,13),29],
			[Date.UTC(2015,4,14),93],
			[Date.UTC(2015,4,15),71],
			[Date.UTC(2015,4,16),65],
			[Date.UTC(2015,4,17),90],
			[Date.UTC(2015,4,18),42],
			[Date.UTC(2015,4,19),13],
			[Date.UTC(2015,4,20),50],
			[Date.UTC(2015,4,21),35],
			[Date.UTC(2015,4,22),64],
			[Date.UTC(2015,4,23),50],
			[Date.UTC(2015,4,24),95],
			[Date.UTC(2015,4,25),83],
			[Date.UTC(2015,4,26),47],
			[Date.UTC(2015,4,27),54],
			[Date.UTC(2015,4,28),62],
			[Date.UTC(2015,5,1),56],
			[Date.UTC(2015,5,2),93],
			[Date.UTC(2015,5,3),15],
			[Date.UTC(2015,5,4),91],
			[Date.UTC(2015,5,5),98],
			[Date.UTC(2015,5,6),7],
			[Date.UTC(2015,5,7),9],
			[Date.UTC(2015,5,8),58],
			[Date.UTC(2015,5,9),50],
			[Date.UTC(2015,5,10),57],
			[Date.UTC(2015,5,11),54],
			[Date.UTC(2015,5,12),50],
			[Date.UTC(2015,5,13),91],
			[Date.UTC(2015,5,14),97],
			[Date.UTC(2015,5,15),21],
			[Date.UTC(2015,5,16),13],
			[Date.UTC(2015,5,17),55],
			[Date.UTC(2015,5,18),1],
			[Date.UTC(2015,5,19),76],
			[Date.UTC(2015,5,20),51],
			[Date.UTC(2015,5,21),31],
			[Date.UTC(2015,5,22),47],
			[Date.UTC(2015,5,23),36],
			[Date.UTC(2015,5,24),82],
			[Date.UTC(2015,5,25),23],
			[Date.UTC(2015,5,26),52],
			[Date.UTC(2015,5,27),88],
			[Date.UTC(2015,5,28),69],
			[Date.UTC(2015,5,29),92],
			[Date.UTC(2015,5,30),67],
			[Date.UTC(2015,5,31),63],
			[Date.UTC(2015,6,1),35],
			[Date.UTC(2015,6,2),23],
			[Date.UTC(2015,6,3),98],
			[Date.UTC(2015,6,4),3],
			[Date.UTC(2015,6,5),35],
			[Date.UTC(2015,6,6),6],
			[Date.UTC(2015,6,7),50],
			[Date.UTC(2015,6,8),89],
			[Date.UTC(2015,6,9),79],
			[Date.UTC(2015,6,10),47],
			[Date.UTC(2015,6,11),36],
			[Date.UTC(2015,6,12),85],
			[Date.UTC(2015,6,13),29],
			[Date.UTC(2015,6,14),43],
			[Date.UTC(2015,6,15),94],
			[Date.UTC(2015,6,16),70],
			[Date.UTC(2015,6,17),23],
			[Date.UTC(2015,6,18),30],
			[Date.UTC(2015,6,19),100],
			[Date.UTC(2015,6,20),37],
			[Date.UTC(2015,6,21),54],
			[Date.UTC(2015,6,22),74],
			[Date.UTC(2015,6,23),59],
			[Date.UTC(2015,6,24),55],
			[Date.UTC(2015,6,25),87],
			[Date.UTC(2015,6,26),86],
			[Date.UTC(2015,6,27),5],
			[Date.UTC(2015,6,28),36],
			[Date.UTC(2015,6,29),37],
			[Date.UTC(2015,6,30),13]
		    ],
		    visible:true
		},
		{
		    name: '收益(元)',
		    marker: {
			symbol: 'circle'
		    },
		     data: [
[Date.UTC(2015,4,1), 40.73 ],
[Date.UTC(2015,4,2), 7.43 ],
[Date.UTC(2015,4,3), 43.32 ],
[Date.UTC(2015,4,4), 15.60 ],
[Date.UTC(2015,4,5), 45.66 ],
[Date.UTC(2015,4,6), 36.11 ],
[Date.UTC(2015,4,7), 33.83 ],
[Date.UTC(2015,4,8), 19.24 ],
[Date.UTC(2015,4,9), 32.78 ],
[Date.UTC(2015,4,10), 29.15 ],
[Date.UTC(2015,4,11), 34.59 ],
[Date.UTC(2015,4,12), 30.38 ],
[Date.UTC(2015,4,13), 20.72 ],
[Date.UTC(2015,4,14), 41.05 ],
[Date.UTC(2015,4,15), 41.90 ],
[Date.UTC(2015,4,16), 37.32 ],
[Date.UTC(2015,4,17), 30.13 ],
[Date.UTC(2015,4,18), 43.70 ],
[Date.UTC(2015,4,19), 17.61 ],
[Date.UTC(2015,4,20), 16.35 ],
[Date.UTC(2015,4,21), 34.47 ],
[Date.UTC(2015,4,22), 31.47 ],
[Date.UTC(2015,4,23), 5.44 ],
[Date.UTC(2015,4,24), 18.04 ],
[Date.UTC(2015,4,25), 23.93 ],
[Date.UTC(2015,4,26), 41.11 ],
[Date.UTC(2015,4,27), 39.99 ],
[Date.UTC(2015,4,28), 13.62 ],
[Date.UTC(2015,5,1), 22.50 ],
[Date.UTC(2015,5,2), 32.73 ],
[Date.UTC(2015,5,3), 23.33 ],
[Date.UTC(2015,5,4), 1.61 ],
[Date.UTC(2015,5,5), 12.76 ],
[Date.UTC(2015,5,6), 34.06 ],
[Date.UTC(2015,5,7), 24.51 ],
[Date.UTC(2015,5,8), 40.09 ],
[Date.UTC(2015,5,9), 35.17 ],
[Date.UTC(2015,5,10), 4.00 ],
[Date.UTC(2015,5,11), 6.01 ],
[Date.UTC(2015,5,12), 14.11 ],
[Date.UTC(2015,5,13), 36.67 ],
[Date.UTC(2015,5,14), 31.25 ],
[Date.UTC(2015,5,15), 48.32 ],
[Date.UTC(2015,5,16), 5.89 ],
[Date.UTC(2015,5,17), 45.95 ],
[Date.UTC(2015,5,18), 31.93 ],
[Date.UTC(2015,5,19), 46.41 ],
[Date.UTC(2015,5,20), 22.93 ],
[Date.UTC(2015,5,21), 42.67 ],
[Date.UTC(2015,5,22), 20.56 ],
[Date.UTC(2015,5,23), 24.17 ],
[Date.UTC(2015,5,24), 38.81 ],
[Date.UTC(2015,5,25), 35.68 ],
[Date.UTC(2015,5,26), 20.08 ],
[Date.UTC(2015,5,27), 22.92 ],
[Date.UTC(2015,5,28), 4.98 ],
[Date.UTC(2015,5,29), 13.11 ],
[Date.UTC(2015,5,30), 14.19 ],
[Date.UTC(2015,5,31), 3.69 ],
[Date.UTC(2015,6,1), 34.59 ],
[Date.UTC(2015,6,2), 20.31 ],
[Date.UTC(2015,6,3), 24.01 ],
[Date.UTC(2015,6,4), 15.98 ],
[Date.UTC(2015,6,5), 22.64 ],
[Date.UTC(2015,6,6), 6.51 ],
[Date.UTC(2015,6,7), 3.23 ],
[Date.UTC(2015,6,8), 17.94 ],
[Date.UTC(2015,6,9), 30.00 ],
[Date.UTC(2015,6,10), 31.22 ],
[Date.UTC(2015,6,11), 13.86 ],
[Date.UTC(2015,6,12), 24.44 ],
[Date.UTC(2015,6,13), 28.86 ],
[Date.UTC(2015,6,14), 16.20 ],
[Date.UTC(2015,6,15), 15.69 ],
[Date.UTC(2015,6,16), 23.54 ],
[Date.UTC(2015,6,17), 12.32 ],
[Date.UTC(2015,6,18), 27.51 ],
[Date.UTC(2015,6,19), 37.97 ],
[Date.UTC(2015,6,20), 13.60 ],
[Date.UTC(2015,6,21), 24.11 ],
[Date.UTC(2015,6,22), 25.03 ],
[Date.UTC(2015,6,23), 31.48 ],
[Date.UTC(2015,6,24), 33.48 ],
[Date.UTC(2015,6,25), 36.69 ],
[Date.UTC(2015,6,26), 42.66 ],
[Date.UTC(2015,6,27), 32.99 ],
[Date.UTC(2015,6,28), 14.61 ],
[Date.UTC(2015,6,29), 3.68 ],
[Date.UTC(2015,6,30), 32.13 ]
		    ],
		    visible:true
		},
		{
		    name: '平均租用时间(分钟/订单 * 100)',
		    marker: {
			symbol: 'triangle-down'
		    },
		     data: [
[Date.UTC(2015,4,1),107.87 ],
[Date.UTC(2015,4,2),225.00 ],
[Date.UTC(2015,4,3),87.36 ],
[Date.UTC(2015,4,4),57.65 ],
[Date.UTC(2015,4,5),26.56 ],
[Date.UTC(2015,4,6),13.98 ],
[Date.UTC(2015,4,7),16.39 ],
[Date.UTC(2015,4,8),65.38 ],
[Date.UTC(2015,4,9),72.58 ],
[Date.UTC(2015,4,10),828.57 ],
[Date.UTC(2015,4,11),98.81 ],
[Date.UTC(2015,4,12),170.00 ],
[Date.UTC(2015,4,13),193.33 ],
[Date.UTC(2015,4,14),775.00 ],
[Date.UTC(2015,4,15),273.08 ],
[Date.UTC(2015,4,16),722.22 ],
[Date.UTC(2015,4,17),109.76 ],
[Date.UTC(2015,4,18),210.00 ],
[Date.UTC(2015,4,19),22.03 ],
[Date.UTC(2015,4,20),79.37 ],
[Date.UTC(2015,4,21),50.00 ],
[Date.UTC(2015,4,22),23.33 ],
[Date.UTC(2015,4,23),33.33 ],
[Date.UTC(2015,4,24),50.00 ],
[Date.UTC(2015,4,25),12.82 ],
[Date.UTC(2015,4,26),10.00 ],
[Date.UTC(2015,4,27),77.14 ],
[Date.UTC(2015,4,28),40.91 ],
[Date.UTC(2015,5,1),71.79 ],
[Date.UTC(2015,5,2),31.00 ],
[Date.UTC(2015,5,3),27.27 ],
[Date.UTC(2015,5,4),31.88 ],
[Date.UTC(2015,5,5),57.89 ],
[Date.UTC(2015,5,6),29.17 ],
[Date.UTC(2015,5,7),20.00 ],
[Date.UTC(2015,5,8),62.37 ],
[Date.UTC(2015,5,9),104.17 ],
[Date.UTC(2015,5,10),62.64 ],
[Date.UTC(2015,5,11),66.67 ],
[Date.UTC(2015,5,12),78.12 ],
[Date.UTC(2015,5,13),395.65 ],
[Date.UTC(2015,5,14),161.67 ],
[Date.UTC(2015,5,15),420.00 ],
[Date.UTC(2015,5,16),81.25 ],
[Date.UTC(2015,5,17),114.58 ],
[Date.UTC(2015,5,18),1.75 ],
[Date.UTC(2015,5,19),76.00 ],
[Date.UTC(2015,5,20),113.33 ],
[Date.UTC(2015,5,21),31.00 ],
[Date.UTC(2015,5,22),88.68 ],
[Date.UTC(2015,5,23),52.17 ],
[Date.UTC(2015,5,24),112.33 ],
[Date.UTC(2015,5,25),48.94 ],
[Date.UTC(2015,5,26),53.61 ],
[Date.UTC(2015,5,27),293.33 ],
[Date.UTC(2015,5,28),75.82 ],
[Date.UTC(2015,5,29),103.37 ],
[Date.UTC(2015,5,30),128.85 ],
[Date.UTC(2015,5,31),150.00 ],
[Date.UTC(2015,6,1),54.69 ],
[Date.UTC(2015,6,2),25.84 ],
[Date.UTC(2015,6,3),653.33 ],
[Date.UTC(2015,6,4),7.14 ],
[Date.UTC(2015,6,5),92.11 ],
[Date.UTC(2015,6,6),10.17 ],
[Date.UTC(2015,6,7),92.59 ],
[Date.UTC(2015,6,8),103.49 ],
[Date.UTC(2015,6,9),158.00 ],
[Date.UTC(2015,6,10),88.68 ],
[Date.UTC(2015,6,11),180.00 ],
[Date.UTC(2015,6,12),141.67 ],
[Date.UTC(2015,6,13),170.59 ],
[Date.UTC(2015,6,14),78.18 ],
[Date.UTC(2015,6,15),20.00 ],
[Date.UTC(2015,6,16),77.78 ],
[Date.UTC(2015,6,17),23.47 ],
[Date.UTC(2015,6,18),30.93 ],
[Date.UTC(2015,6,19),1111.11 ],
[Date.UTC(2015,6,20),370.00 ],
[Date.UTC(2015,6,21),131.71 ],
[Date.UTC(2015,6,22),115.62 ],
[Date.UTC(2015,6,23),67.82 ],
[Date.UTC(2015,6,24),56.70 ],
[Date.UTC(2015,6,25),271.88 ],
[Date.UTC(2015,6,26),119.44 ],
[Date.UTC(2015,6,27),50.00 ],
[Date.UTC(2015,6,28),36.00 ],
[Date.UTC(2015,6,29),217.65 ],
[Date.UTC(2015,6,30),20.97 ]
		    ],
		    visible:true
		},
		{
		    name: '平均收益(元/订单)',
		    marker: {
			symbol: 'triangle'
		    },
		     data: [
[Date.UTC(2015,4,1),45.76 ],
[Date.UTC(2015,4,2),30.96 ],
[Date.UTC(2015,4,3),49.79 ],
[Date.UTC(2015,4,4),18.35 ],
[Date.UTC(2015,4,5),71.34 ],
[Date.UTC(2015,4,6),38.83 ],
[Date.UTC(2015,4,7),55.46 ],
[Date.UTC(2015,4,8),24.67 ],
[Date.UTC(2015,4,9),52.87 ],
[Date.UTC(2015,4,10),416.43 ],
[Date.UTC(2015,4,11),41.18 ],
[Date.UTC(2015,4,12),303.80 ],
[Date.UTC(2015,4,13),138.13 ],
[Date.UTC(2015,4,14),342.08 ],
[Date.UTC(2015,4,15),161.15 ],
[Date.UTC(2015,4,16),414.67 ],
[Date.UTC(2015,4,17),36.74 ],
[Date.UTC(2015,4,18),218.50 ],
[Date.UTC(2015,4,19),29.85 ],
[Date.UTC(2015,4,20),25.95 ],
[Date.UTC(2015,4,21),344.70 ],
[Date.UTC(2015,4,22),14.00 ],
[Date.UTC(2015,4,23),90.67 ],
[Date.UTC(2015,4,24),180.40 ],
[Date.UTC(2015,4,25),61.36 ],
[Date.UTC(2015,4,26),87.47 ],
[Date.UTC(2015,4,27),57.13 ],
[Date.UTC(2015,4,28),30.95 ],
[Date.UTC(2015,5,1),28.85 ],
[Date.UTC(2015,5,2),91.00 ],
[Date.UTC(2015,5,3),42.42 ],
[Date.UTC(2015,5,4),2.33 ],
[Date.UTC(2015,5,5),33.58 ],
[Date.UTC(2015,5,6),141.92 ],
[Date.UTC(2015,5,7),54.47 ],
[Date.UTC(2015,5,8),43.11 ],
[Date.UTC(2015,5,9),73.27 ],
[Date.UTC(2015,5,10),4.40 ],
[Date.UTC(2015,5,11),7.42 ],
[Date.UTC(2015,5,12),22.05 ],
[Date.UTC(2015,5,13),159.43 ],
[Date.UTC(2015,5,14),52.08 ],
[Date.UTC(2015,5,15),966.40 ],
[Date.UTC(2015,5,16),36.81 ],
[Date.UTC(2015,5,17),95.73 ],
[Date.UTC(2015,5,18),56.02 ],
[Date.UTC(2015,5,19),46.00 ],
[Date.UTC(2015,5,20),50.96 ],
[Date.UTC(2015,5,21),42.00 ],
[Date.UTC(2015,5,22),38.79 ],
[Date.UTC(2015,5,23),35.03 ],
[Date.UTC(2015,5,24),53.16 ],
[Date.UTC(2015,5,25),75.91 ],
[Date.UTC(2015,5,26),20.70 ],
[Date.UTC(2015,5,27),76.40 ],
[Date.UTC(2015,5,28),5.47 ],
[Date.UTC(2015,5,29),14.73 ],
[Date.UTC(2015,5,30),27.29 ],
[Date.UTC(2015,5,31),8.79 ],
[Date.UTC(2015,6,1),54.05 ],
[Date.UTC(2015,6,2),22.82 ],
[Date.UTC(2015,6,3),60.07 ],
[Date.UTC(2015,6,4),38.05 ],
[Date.UTC(2015,6,5),59.58 ],
[Date.UTC(2015,6,6),11.03 ],
[Date.UTC(2015,6,7),5.98 ],
[Date.UTC(2015,6,8),20.86 ],
[Date.UTC(2015,6,9),60.00 ],
[Date.UTC(2015,6,10),58.91 ],
[Date.UTC(2015,6,11),69.30 ],
[Date.UTC(2015,6,12),40.73 ],
[Date.UTC(2015,6,13),169.76 ],
[Date.UTC(2015,6,14),29.45 ],
[Date.UTC(2015,6,15),33.38 ],
[Date.UTC(2015,6,16),26.16 ],
[Date.UTC(2015,6,17),12.57 ],
[Date.UTC(2015,6,18),28.36 ],
[Date.UTC(2015,6,19),41.89 ],
[Date.UTC(2015,6,20),16.00 ],
[Date.UTC(2015,6,21),58.80 ],
[Date.UTC(2015,6,22),39.11 ],
[Date.UTC(2015,6,23),36.18 ],
[Date.UTC(2015,6,24),34.52 ],
[Date.UTC(2015,6,25),114.66 ],
[Date.UTC(2015,6,26),59.25 ],
[Date.UTC(2015,6,27),32.00 ],
[Date.UTC(2015,6,28),1461.00 ],
[Date.UTC(2015,6,29),21.65 ],
[Date.UTC(2015,6,30),51.82 ]
		    ],
		    visible:true
		},

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
});
</script>
