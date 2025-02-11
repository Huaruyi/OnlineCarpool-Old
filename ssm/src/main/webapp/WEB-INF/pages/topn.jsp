<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ECharts USE</title>
    <!-- 引入 ECharts 文件 -->
    <script src="js/echarts.min.js"></script>
    <script src="js/jquery-1.11.3.min.js"></script>
</head>
<body>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '乘客注册组成统计',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {
                name: '访问次数',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: (function () {
                    var courses = [];
                    $.ajax({
                        type: "GET",
                        url: "/selectPassengerRegisted",
                        dataType: 'json',
                        async: false,
                        success: function (result) {
                            for (var i = 0; i < result.length; i++) {
                                courses.push({"value": result[i].value, "name": result[i].name});
                            }
                        }
                    })
                    return courses;
                })(),
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>