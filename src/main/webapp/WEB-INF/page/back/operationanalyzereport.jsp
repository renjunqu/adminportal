<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>


<div id="container" style="min-width: 310px; height: 720px; margin: 0 auto"></div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', [], function() {
           $('#container').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: '周订单状况统计'
        },
        subtitle: {
            text: '订单与平均租用时间对比'
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
            min:0
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
       series: [{
            name: '平均租用时间',
            marker: {
                symbol: 'square'
            },
            data:
              [
                [Date.UTC(2015, 4, 16), 5.1],
                [Date.UTC(2015, 4, 23), 3.4],
                [Date.UTC(2015, 5, 1), 6.1],
                [Date.UTC(2015, 5, 8), 8.2],
                [Date.UTC(2015, 5, 15), 7.8],
                [Date.UTC(2015, 5, 22), 9.9],
                [Date.UTC(2015, 5, 28), 11.2],
                [Date.UTC(2015, 6, 4), 15.1],
                [Date.UTC(2015, 6, 11), 8.4],
                [Date.UTC(2015, 6, 18), 7.9],
                [Date.UTC(2015, 6, 25), 12.1],
             ]

        }, {
            name: '订单数',
            marker: {
                symbol: 'diamond'
            },
             data: [
                [Date.UTC(2015, 4, 16), 79],
                [Date.UTC(2015, 4, 23), 66],
                [Date.UTC(2015, 5, 1), 61],
                [Date.UTC(2015, 5, 8), 41],
                [Date.UTC(2015, 5, 15), 58],
                [Date.UTC(2015, 5, 22), 69],
                [Date.UTC(2015, 5, 28), 60],
                [Date.UTC(2015, 6, 4), 51],
                [Date.UTC(2015, 6, 11), 44],
                [Date.UTC(2015, 6, 18), 69],
                [Date.UTC(2015, 6, 25), 121],
            ]
        }]
    });
});
</script>
