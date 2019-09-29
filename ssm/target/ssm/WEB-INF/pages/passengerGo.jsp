<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>正在出发</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../../css/style.css" type="text/css" />
    <%--引入layUI css文件，显示星级评价--%>
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
    <style>
        /*Validate错误信息提示的字色5*/
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- 引入header.jsp -->
    <jsp:include page="/WEB-INF/pages/passenger_header.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <c:if test="${not empty book}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>订单编号</th>
                        <th>产品</th>
                        <th>开始时间</th>
                        <th>司机姓名</th>
                        <th>车辆信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${book.bid}</td>
                        <td>${book.route.rdeparture} &nbsp;→&nbsp; ${book.route.rdestination}</td>
                        <td>${book.begintime}</td>
                        <td>${book.driver.dname}</td>
                        <td>${book.car.cbrand}&nbsp;${book.car.ctype}&nbsp; ${car.ccolor}</td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">

            <table class="table">
                <tbody>
                <tr class="text-center">
                    <td></td><td></td><td></td><td></td>
                    <td>
                        <!-- 弹出子窗体 -->
                        <div class="col-sm-3">
                            <button class="btn btn-success" type="button" data-toggle="modal" data-target="#affirmGo">确认到达</button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>


            <!-- Modal -->
            <div class="modal fade" id="affirmGo" tabindex="-1" role="dialog" aria-labelledby="affirmGoModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="affirmGoModalLabel">请司机一个评分吧！</h4>
                        </div>
                        <form action="passengerComment?uid=${user.uid}&bid=${book.bid}" method="post">
                            <input type="hidden" name="did" value="${book.did}">
                            <div id="rate"></div>
                            <input id="number" type="text" name="devaluate" style="display: none">
                            <input type="submit" value="提交">

                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary">确认</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'rate'], function(){
        var rate = layui.rate;

        rate.render({
            elem: '#rate'
            ,value: 0
            ,text: true
            ,setText:function (value) {
                var arrs = {
                    '1': '极差'
                    , '2': '差'
                    , '3': '中等'
                    , '4': '好'
                    , '5': '非常好'
                };
                this.span.text(arrs[value]);
                $("#number").val(value);
            }
        })
    });
</script>
</body>
</html>
