<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>拼车发布</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <%--bootstrap-select--%>
    <link rel="stylesheet" href="../../css/bootstrap-select.min.css"/>

    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <%--bootstrap-select--%>
    <script src="../../js/bootstrap-select.min.js" type="text/javascript"></script>
    <script src="../../js/defaults-zh_CN.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../../css/style.css" type="text/css" />
    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }

        font {
            color: #3164af;
            font-size: 18px;
            font-weight: normal;
            padding: 0 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 引入header.jsp -->
    <jsp:include page="/WEB-INF/pages/passenger_header.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <c:if test="${empty passengerBookdetailList}">
                <h1 class="text-center"><small>暂无订单，请参与拼车完成后来查看哦.</small></h1>
            </c:if>
            <c:if test="${not empty passengerBookdetailList}">
                <table class="table table-hover table-bordered" contenteditable="false">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>产品</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${passengerBookdetailList}" var="pbl">
                        <tr class="success">
                            <td>${pbl.bid}</td>
                            <td>${pbl.book.route.rdeparture} &nbsp;→&nbsp; ${pbl.book.route.rdestination}</td>
                            <td>${pbl.book.begintime}</td>
                            <td>${pbl.book.endtime}</td>
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
                        <p >当前是第<font style="color: red">${passengerBookdetailPageInfo.pageNum}</font>页、共${passengerBookdetailPageInfo.pages}页、${passengerBookdetailPageInfo.total}条记录</p>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                    <%--不是第一页可选，是第一页变为不可选--%>
                                <c:if test="${passengerBookdetailPageInfo.pageNum != passengerBookdetailPageInfo.firstPage}">
                                    <li>
                                        <a href="passengerOrderList?page=${passengerBookdetailPageInfo.firstPage}"><span aria-hidden="true">首页</span></a>
                                    </li>
                                </c:if>
                                <c:if test="${passengerBookdetailPageInfo.pageNum == passengerBookdetailPageInfo.firstPage}">
                                    <li  class="disabled">
                                        <a href="javascript:;"><span aria-hidden="true">首页</span></a>
                                    </li>
                                </c:if>
                                    <%--上一页--%>
                                <c:if test="${passengerBookdetailPageInfo.hasPreviousPage}">
                                    <li>
                                        <a href="passengerOrderList?page=${passengerBookdetailPageInfo.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                    </li>
                                </c:if>
                                <!-- 页码  -->
                                <!-- 当总页数小于等于7时，显示页码1...7页 -->
                                <c:if test="${passengerBookdetailPageInfo.pages<=7}">
                                    <c:forEach begin="1" end="${passengerBookdetailPageInfo.pages}" var="i">
                                        <li <c:if test="${passengerBookdetailPageInfo.pageNum==i }">class="active"</c:if>>
                                            <a href="passengerOrderList?page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <!-- 当总页数大于7时 -->
                                <c:if test="${passengerBookdetailPageInfo.pages>7}">
                                    <!-- 当前页数小于等于4时，显示1到5...最后一页 -->
                                    <c:if test="${passengerBookdetailPageInfo.pageNum<=4}">
                                        <c:forEach begin="1" end="5" var="i">
                                            <li <c:if test="${passengerBookdetailPageInfo.pageNum==i }">class="active"</c:if>>
                                                <a href="passengerOrderList?page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li><a href="#">...</a></li>
                                        <li
                                                <c:if test="${passengerBookdetailPageInfo.pageNum==passengerBookdetailPageInfo.pages }">class="active"</c:if>>
                                            <a href="passengerOrderList?page=${passengerBookdetailPageInfo.pages}">${passengerBookdetailPageInfo.pages}</a>
                                        </li>
                                    </c:if>
                                    <!-- 当前页数大于4时，如果当前页小于总页码数-3，则显示1...n-1,n,n+1...最后一页 -->
                                    <c:if test="${passengerBookdetailPageInfo.pageNum>4}">
                                        <c:if test="${passengerBookdetailPageInfo.pageNum<passengerBookdetailPageInfo.pages-3}">
                                            <li>
                                                <a href="passengerOrderList?page=${1}">${1}</a>
                                            </li>
                                            <li><a href="#">...</a></li>
                                            <c:forEach begin="${passengerBookdetailPageInfo.pageNum-1 }" end="${passengerBookdetailPageInfo.pageNum+1 }"
                                                       var="i">
                                                <li <c:if test="${passengerBookdetailPageInfo.pageNum==i }">class="active"</c:if>>
                                                    <a href="passengerOrderList?page=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <li><a href="#">...</a></li>
                                            <li
                                                    <c:if test="${passengerBookdetailPageInfo.pageNum==passengerBookdetailPageInfo.pages }">class="active"</c:if>>
                                                <a href="passengerOrderList?page=${passengerBookdetailPageInfo.pages}">${passengerBookdetailPageInfo.pages}</a>
                                            </li>
                                        </c:if>
                                    </c:if>
                                    <!-- 当前页数大于4时，如果当前页大于总页码数-4，则显示1...最后一页-3，最后一页-2，最后一页-1，最后一页 -->
                                    <c:if test="${passengerBookdetailPageInfo.pageNum>passengerBookdetailPageInfo.pages-4}">
                                        <li><a href="passengerOrderList?page=${1}">${1}</a>
                                        </li>

                                        <li><a href="#">...</a></li>
                                        <c:forEach begin="${passengerBookdetailPageInfo.pages-3 }"
                                                   end="${passengerBookdetailPageInfo.pages }" var="i">
                                            <li <c:if test="${passengerBookdetailPageInfo.pageNum==i }">class="active"</c:if>>
                                                <a href="passengerOrderList?page=${i}">${i}</a>
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
                                <c:if test="${passengerBookdetailPageInfo.hasNextPage}">
                                    <li>
                                        <a href="passengerOrderList?page=${passengerBookdetailPageInfo.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                                    </li>
                                </c:if>
                                    <%--尾页--%>
                                <c:if test="${passengerBookdetailPageInfo.pageNum != passengerBookdetailPageInfo.lastPage}">
                                    <li>
                                        <a href="passengerOrderList?page=${passengerBookdetailPageInfo.lastPage}"><span aria-hidden="true">尾页</span></a>
                                    </li>
                                </c:if>
                                <c:if test="${passengerBookdetailPageInfo.pageNum == passengerBookdetailPageInfo.lastPage}">
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
            </c:if>
        </div>
    </div>
</div>
</body>
</html>