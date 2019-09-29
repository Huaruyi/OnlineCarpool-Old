<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>随玩拼车首页</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>

</head>

<body>
<div class="container-fluid">
    <!-- 引入header.jsp -->
    <jsp:include page="/WEB-INF/pages/passenger_header.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="col-md-4">
            <a href="passengerPublishUI" style="text-decoration: none"><button class="btn btn-block btn-large btn-info" type="button">发布拼车</button></a>
        </div>
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
        </div>
    </div>

    <div class="row-fluid">
        <div class="span12">
            <div class="tabbable" id="tabs-592780">
                <ul class="nav nav-tabs">
                    <li>
                        <a href="#1" data-toggle="tab" onclick="publish()">实时拼车</a>
                        <script>
                            function publish() {
                                window.location.href="forward"
                            }
                        </script>
                    </li>
                    <li class="active">
                        <a href="#2" data-toggle="tab">预定拼车</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane" id="1"></div>
                    <div class="tab-pane active" id="2">
                        <div style="margin-top:25px;"></div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-hover table-bordered" contenteditable="false">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>产品</th>
                                        <th>座位</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${passengerPublishList}" var="bl">
                                        <tr class="warning">
                                            <td>${bl.bid}</td>
                                            <td>${bl.route.rdeparture} &nbsp;→&nbsp; ${bl.route.rdestination}</td>
                                            <td>${3 - bl.bookdetail.bdseat}个</td>
                                            <td>

                                                <!-- 弹出子窗体 -->
                                                <a href="javascript:;" data-toggle="modal" onclick="unable()" data-target="#addModal">加入</a>
                                                <script>
                                                    function unable() {
                                                        alert("请先登录");
                                                    }
                                                </script>
                                                |
                                                <!-- 弹出子窗体 -->
                                                <a href="javascript:;" data-toggle="modal" onclick="unable()" data-target="#detailModal">详细</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <div style="margin-top:25px;"></div>

                                <%--分页部分开始--%>
                                <div class="row-fluid">
                                    <div class="span4">
                                    </div>
                                    <div class="span4 text-center">
                                        <p >当前是第<font style="color: red">${allPassengerPublishPage.pageNum}</font>页、共${allPassengerPublishPage.pages}页、${allPassengerPublishPage.total}条记录</p>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <%--不是第一页可选，是第一页变为不可选--%>
                                                <c:if test="${allPassengerPublishPage.pageNum != allPassengerPublishPage.firstPage}">
                                                    <li>
                                                        <a href="bookList?page=${allPassengerPublishPage.firstPage}"><span aria-hidden="true">首页</span></a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${allPassengerPublishPage.pageNum == allPassengerPublishPage.firstPage}">
                                                    <li  class="disabled">
                                                        <a href="javascript:;"><span aria-hidden="true">首页</span></a>
                                                    </li>
                                                </c:if>
                                                <%--上一页--%>
                                                <c:if test="${allPassengerPublishPage.hasPreviousPage}">
                                                    <li>
                                                        <a href="bookList?page=${allPassengerPublishPage.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                                    </li>
                                                </c:if>
                                                <!-- 页码  -->
                                                <!-- 当总页数小于等于7时，显示页码1...7页 -->
                                                <c:if test="${allPassengerPublishPage.pages<=7}">
                                                    <c:forEach begin="1" end="${allPassengerPublishPage.pages}" var="i">
                                                        <li <c:if test="${allPassengerPublishPage.pageNum==i }">class="active"</c:if>>
                                                            <a href="bookList?page=${i}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- 当总页数大于7时 -->
                                                <c:if test="${allPassengerPublishPage.pages>7}">
                                                    <!-- 当前页数小于等于4时，显示1到5...最后一页 -->
                                                    <c:if test="${allPassengerPublishPage.pageNum<=4}">
                                                        <c:forEach begin="1" end="5" var="i">
                                                            <li <c:if test="${allPassengerPublishPage.pageNum==i }">class="active"</c:if>>
                                                                <a href="bookList?page=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <li><a href="#">...</a></li>
                                                        <li
                                                                <c:if test="${allPassengerPublishPage.pageNum==allPassengerPublishPage.pages }">class="active"</c:if>>
                                                            <a href="bookList?page=${allPassengerPublishPage.pages}">${allPassengerPublishPage.pages}</a>
                                                        </li>
                                                    </c:if>
                                                    <!-- 当前页数大于4时，如果当前页小于总页码数-3，则显示1...n-1,n,n+1...最后一页 -->
                                                    <c:if test="${allPassengerPublishPage.pageNum>4}">
                                                        <c:if test="${allPassengerPublishPage.pageNum<allPassengerPublishPage.pages-3}">
                                                            <li>
                                                                <a href="bookList?page=${1}">${1}</a>
                                                            </li>
                                                            <li><a href="#">...</a></li>
                                                            <c:forEach begin="${allPassengerPublishPage.pageNum-1 }" end="${allPassengerPublishPage.pageNum+1 }"
                                                                       var="i">
                                                                <li <c:if test="${allPassengerPublishPage.pageNum==i }">class="active"</c:if>>
                                                                    <a href="bookList?page=${i}">${i}</a>
                                                                </li>
                                                            </c:forEach>
                                                            <li><a href="#">...</a></li>
                                                            <li
                                                                    <c:if test="${allPassengerPublishPage.pageNum==allPassengerPublishPage.pages }">class="active"</c:if>>
                                                                <a href="bookList?page=${allPassengerPublishPage.pages}">${allPassengerPublishPage.pages}</a>
                                                            </li>
                                                        </c:if>
                                                    </c:if>
                                                    <!-- 当前页数大于4时，如果当前页大于总页码数-4，则显示1...最后一页-3，最后一页-2，最后一页-1，最后一页 -->
                                                    <c:if test="${allPassengerPublishPage.pageNum>allPassengerPublishPage.pages-4}">
                                                        <li><a href="bookList?page=${1}">${1}</a>
                                                        </li>

                                                        <li><a href="#">...</a></li>
                                                        <c:forEach begin="${allPassengerPublishPage.pages-3 }"
                                                                   end="${allPassengerPublishPage.pages }" var="i">
                                                            <li <c:if test="${allPassengerPublishPage.pageNum==i }">class="active"</c:if>>
                                                                <a href="bookList?page=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                                <%--&lt;%&ndash;当前页&ndash;%&gt;
                                                 <c:forEach var="page" begin="1" end="${allPublishPage.pages}">
                                                     <c:if test="${page == allPublishPage.pageNum}">
                                                         <li class="active"><a href="javascript:;">${page}</a> </li>
                                                     </c:if>
                                                     <c:if test="${page != allPublishPage.pageNum}">
                                                         <li><a href="publishList?page=${page}">${page}</a></li>
                                                     </c:if>
                                                 </c:forEach>--%>
                                                <%----%>

                                                <%--下一页--%>
                                                <c:if test="${allPassengerPublishPage.hasNextPage}">
                                                    <li>
                                                        <a href="bookList?page=${allPassengerPublishPage.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                                                    </li>
                                                </c:if>
                                                <%--尾页--%>
                                                <c:if test="${allPassengerPublishPage.pageNum != allPassengerPublishPage.lastPage}">
                                                    <li>
                                                        <a href="bookList?page=${allPassengerPublishPage.lastPage}"><span aria-hidden="true">尾页</span></a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${allPassengerPublishPage.pageNum == allPassengerPublishPage.lastPage}">
                                                    <li  class="disabled">
                                                        <a href="javascript:;"><span aria-hidden="true">尾页</span></a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>
                                    <div class="span4">
                                    </div>
                                </div>
                                <%--分页部分结束--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 引入footer.jsp -->
    <jsp:include page="/WEB-INF/pages/footer.jsp"></jsp:include>
</div>
</body>

</html>