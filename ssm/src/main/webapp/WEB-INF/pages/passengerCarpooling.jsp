<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的拼车</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../../css/style.css" type="text/css" />
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
            <c:if test="${empty book}">
                <h1 class="text-center"><small>当前没有正在进行的拼车.</small></h1>
            </c:if>
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
                        <td>
                            <c:if test="${empty book.begintime}">
                                拼车即将开始
                            </c:if>
                            <c:if test="${not empty book.begintime}">
                                ${book.begintime}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${empty book.did}">
                                等待司机加入
                            </c:if>
                            <c:if test="${not empty book.did}">
                                ${book.driver.dname}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${empty book.cid}">
                                等待司机加入
                            </c:if>
                            <c:if test="${not empty book.cid}">
                                ${book.car.cbrand}&nbsp;${book.car.ctype}&nbsp; ${car.ccolor}
                            </c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <c:if test="${not empty book}">
                <table class="table">
                    <tbody>
                    <tr class="text-center">
                        <td></td><td></td><td></td><td></td>
                        <td>
                            <div class="col-sm-3"><button class="btn btn-danger" type="button" onclick="exitCarpool()">退出拼车</button></div>
                            <script>
                                function exitCarpool() {
                                    if (confirm("你确定要退出本次拼车吗？")) {
                                        alert("退出成功");
                                        window.location.href="passengerQuitPublish?bid=${book.bid}&uid=${user.uid}";

                                    }
                                }
                            </script>
                            <!-- 弹出子窗体 -->
                            <div class="col-sm-3">
                                <button class="btn" type="button" data-toggle="modal" data-target="#callDriver">联系司机</button>
                            </div>

                            <!-- 弹出子窗体 -->
                            <div class="col-sm-3">
                                <button class="btn btn-success" type="button" data-toggle="modal" data-target="#affirmGo">确认出发</button>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </c:if>

            <!-- Modal -->
            <div class="modal fade" id="callDriver" tabindex="-1" role="dialog" aria-labelledby="callDriverModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="callDriverModalLabel">联系司机</h4>
                        </div>
                        <div class="modal-body">
                            <h4>司机信息</h4>
                            <c:if test="${empty book.driver}">
                                司机还未加入
                            </c:if>
                            <c:if test="${not empty book.driver}">
                                姓名：${book.driver.dname}<br>
                                电话：${book.driver.dphone}<br>
                            </c:if>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                        </div>
                    </div>
                </div>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="affirmGo" tabindex="-1" role="dialog" aria-labelledby="affirmGoModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="affirmGoModalLabel">核对信息无误后确认</h4>
                        </div>
                        <div class="modal-body">
                            <h4>司机信息</h4>

                            姓名：${book.driver.dname}<br>
                            电话：${book.driver.dphone}<br>
                            车辆信息：${book.car.cbrand}${book.car.ctype}&nbsp;${book.car.ccolor}

                            <hr align="left" style="width: 50%;border: 0;height: 1px;background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));">
                            <h4>路线信息</h4>
                            拼车线路：${book.route.rdeparture} &nbsp;→&nbsp; ${book.route.rdestination}<br>
                            信号灯数：${book.route.rsignal}个<br>
                            预计里程：${book.route.rdistance}公里<br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="affirmGo()">确认</button>
                            <script>
                                function affirmGo() {
                                    alert("确认成功");
                                    window.location.href="passengerConfirm?bid=${book.bid}&uid=${user.uid}";
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
