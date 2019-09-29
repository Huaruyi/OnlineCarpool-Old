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

    <script>
        function tijiao() {
            ${"addPublishForm"}.submit();
        }
    </script>
</head>

<body>
<div class="container-fluid">
    <!-- 引入header.jsp -->
    <jsp:include page="/WEB-INF/pages/driver_header.jsp"></jsp:include>
    <div class="row-fluid">
        <div class="col-md-4">
            <a href="driverPublishUI" style="text-decoration: none"><button class="btn btn-block btn-large btn-info" type="button">发布拼车</button></a>
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
                                window.location.href="driverPublishList"
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
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${driverPublishList}" var="bl">
                                        <tr class="warning">
                                            <td>${bl.bid}</td>
                                            <td>${bl.route.rdeparture} &nbsp;→&nbsp; ${bl.route.rdestination}</td>
                                            <td>
                                                <c:if test="${bl.did == driver.did}">
                                                    <a href="javascript:;" id="aa">已加入</a>
                                                    <style>
                                                        <%--设置a标签样式--%>
                                                        #aa{
                                                            text-decoration: none;
                                                            color: #c0a16b;
                                                        }
                                                    </style>
                                                </c:if>
                                                <c:if test="${bl.did != driver.did}">
                                                    <!-- 弹出子窗体 -->
                                                    <a href="javascript:;" data-toggle="modal" data-target="#addModal">加入</a>
                                                </c:if>

                                                <!-- Modal -->
                                                <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                <h4 class="modal-title" id="addModalLabel">查看详细信息</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form id="addPublishForm" action="driverAddPublish?did=${bl.bid}&did=${driver.did}" method="post">
                                                                    <div class="form-group opt">
                                                                        <label for="inlineRadio1" class="col-sm-2 control-label">人数</label>
                                                                        <div class="col-sm-6">
                                                                            <label class="radio-inline"> <input type="radio" name="bdseat" id="inlineRadio1" value="1">1</label>
                                                                            <c:if test="${bl.bookdetail.bdseat == 1}">
                                                                                <label class="radio-inline"> <input type="radio" name="bdseat" id="inlineRadio2" value="2">2</label>
                                                                            </c:if>
                                                                            <br>
                                                                        </div>
                                                                    </div>
                                                                </form>

                                                            </div>
                                                            <div style="margin-top:25px;"></div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">我再想想</button>
                                                                <button type="button" onclick="addPublish()" class="btn btn-primary">加入</button>
                                                                <script>
                                                                    function addPublish() {
                                                                        $("#addPublishForm").submit();

                                                                    }
                                                                </script>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                |
                                                <!-- 弹出子窗体 -->
                                                <a href="javascript:;" data-toggle="modal" data-target="#detailModal">详细</a>
                                                <!-- Modal -->
                                                <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                <h4 class="modal-title" id="detailModalLabel">查看详细信息</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <h4>司机信息</h4>
                                                                <c:if test="${empty bl.driver}">
                                                                    司机还未加入
                                                                </c:if>
                                                                <c:if test="${not empty bl.driver}">
                                                                    姓名：${bl.driver.dname}<br>
                                                                    电话：${bl.driver.dphone}<br>
                                                                    拼车：${bl.driver.dtimes}次<br>
                                                                    评价：${bl.driver.devaluate}分<br>
                                                                </c:if>
                                                                <hr align="left" style="width: 50%;border: 0;height: 1px;background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));">
                                                                <h4>路线信息</h4>
                                                                出发时间：${bl.begintime}
                                                                拼车线路：${bl.route.rdeparture} &nbsp;→&nbsp; ${bl.route.rdestination}<br>
                                                                信号灯数：${bl.route.rsignal}个<br>
                                                                预计里程：${bl.route.rdistance}公里<br>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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
                                        <p >当前是第<font style="color: red">${alldriverPublishPage.pageNum}</font>页、共${alldriverPublishPage.pages}页、${alldriverPublishPage.total}条记录</p>
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                                <%--不是第一页可选，是第一页变为不可选--%>
                                                <c:if test="${alldriverPublishPage.pageNum != alldriverPublishPage.firstPage}">
                                                    <li>
                                                        <a href="driverBookList?page=${alldriverPublishPage.firstPage}"><span aria-hidden="true">首页</span></a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${alldriverPublishPage.pageNum == alldriverPublishPage.firstPage}">
                                                    <li  class="disabled">
                                                        <a href="javascript:;"><span aria-hidden="true">首页</span></a>
                                                    </li>
                                                </c:if>
                                                <%--上一页--%>
                                                <c:if test="${alldriverPublishPage.hasPreviousPage}">
                                                    <li>
                                                        <a href="driverBookList?page=${alldriverPublishPage.pageNum-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                                    </li>
                                                </c:if>
                                                <!-- 页码  -->
                                                <!-- 当总页数小于等于7时，显示页码1...7页 -->
                                                <c:if test="${alldriverPublishPage.pages<=7}">
                                                    <c:forEach begin="1" end="${alldriverPublishPage.pages}" var="i">
                                                        <li <c:if test="${alldriverPublishPage.pageNum==i }">class="active"</c:if>>
                                                            <a href="driverBookList?page=${i}">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- 当总页数大于7时 -->
                                                <c:if test="${alldriverPublishPage.pages>7}">
                                                    <!-- 当前页数小于等于4时，显示1到5...最后一页 -->
                                                    <c:if test="${alldriverPublishPage.pageNum<=4}">
                                                        <c:forEach begin="1" end="5" var="i">
                                                            <li <c:if test="${alldriverPublishPage.pageNum==i }">class="active"</c:if>>
                                                                <a href="driverBookList?page=${i}">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <li><a href="#">...</a></li>
                                                        <li
                                                                <c:if test="${alldriverPublishPage.pageNum==alldriverPublishPage.pages }">class="active"</c:if>>
                                                            <a href="driverBookList?page=${alldriverPublishPage.pages}">${alldriverPublishPage.pages}</a>
                                                        </li>
                                                    </c:if>
                                                    <!-- 当前页数大于4时，如果当前页小于总页码数-3，则显示1...n-1,n,n+1...最后一页 -->
                                                    <c:if test="${alldriverPublishPage.pageNum>4}">
                                                        <c:if test="${alldriverPublishPage.pageNum<alldriverPublishPage.pages-3}">
                                                            <li>
                                                                <a href="driverBookList?page=${1}">${1}</a>
                                                            </li>
                                                            <li><a href="#">...</a></li>
                                                            <c:forEach begin="${alldriverPublishPage.pageNum-1 }" end="${alldriverPublishPage.pageNum+1 }"
                                                                       var="i">
                                                                <li <c:if test="${alldriverPublishPage.pageNum==i }">class="active"</c:if>>
                                                                    <a href="driverBookList?page=${i}">${i}</a>
                                                                </li>
                                                            </c:forEach>
                                                            <li><a href="#">...</a></li>
                                                            <li
                                                                    <c:if test="${alldriverPublishPage.pageNum==alldriverPublishPage.pages }">class="active"</c:if>>
                                                                <a href="driverBookList?page=${alldriverPublishPage.pages}">${alldriverPublishPage.pages}</a>
                                                            </li>
                                                        </c:if>
                                                    </c:if>
                                                    <!-- 当前页数大于4时，如果当前页大于总页码数-4，则显示1...最后一页-3，最后一页-2，最后一页-1，最后一页 -->
                                                    <c:if test="${alldriverPublishPage.pageNum>alldriverPublishPage.pages-4}">
                                                        <li><a href="driverBookList?page=${1}">${1}</a>
                                                        </li>

                                                        <li><a href="#">...</a></li>
                                                        <c:forEach begin="${alldriverPublishPage.pages-3 }"
                                                                   end="${alldriverPublishPage.pages }" var="i">
                                                            <li <c:if test="${alldriverPublishPage.pageNum==i }">class="active"</c:if>>
                                                                <a href="driverBookList?page=${i}">${i}</a>
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
                                                <c:if test="${alldriverPublishPage.hasNextPage}">
                                                    <li>
                                                        <a href="driverBookList?page=${alldriverPublishPage.pageNum+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                                                    </li>
                                                </c:if>
                                                <%--尾页--%>
                                                <c:if test="${alldriverPublishPage.pageNum != alldriverPublishPage.lastPage}">
                                                    <li>
                                                        <a href="driverBookList?page=${alldriverPublishPage.lastPage}"><span aria-hidden="true">尾页</span></a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${alldriverPublishPage.pageNum == alldriverPublishPage.lastPage}">
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