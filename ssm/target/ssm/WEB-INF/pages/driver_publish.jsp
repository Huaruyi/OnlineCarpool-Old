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
    <jsp:include page="/WEB-INF/pages/driver_header.jsp"></jsp:include>
    <form id="form" action="/driverPublishBook?id=1" method="post" class="form-horizontal" onsubmit="Hint()" style="margin-top: 5px;">
        <script>
            function Hint() {
                alert("发起实时拼车成功");
            }
        </script>
        <div class="form-group">
            <label for="rdeparture" class="col-sm-2 control-label">出发地</label>
            <div class="col-sm-6">
                <select id="rdeparture" name="route.rdeparture" class="form-control selectpicker" data-live-search="true">
                    <c:forEach items="${depa}" var="dep">
                        <option value="${dep}">${dep}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="rdestination" class="col-sm-2 control-label">目的地</label>
            <div class="col-sm-6">
                <select id="rdestination" name="route.rdestination" class="form-control selectpicker" data-live-search="true">

                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="distance" class="col-sm-2 control-label">预计里程</label>
            <div class="col-sm-6" id="distance">

            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" width="100" name="submit" value="发布">
            </div>
        </div>
    </form>
</div>
<script>
    /*根据出发地的变化而改变目的地*/

    $(function () {
        /*参考链接  https://blog.csdn.net/qq_33220391/article/details/88886200*/
        $("#rdeparture").trigger("change"); //规定使用事件对象的被选元素要触发的事件 （选中#id的元素 给他发change事件
        $("#rdestination").trigger("change"); //规定使用事件对象的被选元素要触发的事件 （选中#id的元素 给他发change事件
    });

    $("#rdeparture").change(function () {
        var depa = $("#rdeparture").val();
        $.ajax({
            timeout: 3000,
            async:false,
            type:"post",
            url:"/changedest?depa="+depa,
            success:function (data) {
                //清空之前添加的   否则会重复添加
                $("#rdestination").empty();
                //根据查询到的结果动态添加
                for (var i= 0 ; i<data.length;i++) {
                    $("#rdestination").append("<option value="+data[i]+">" + data[i] + "</option>");
                }
                $("#rdestination").selectpicker("refresh");
            }
        });
    });

    $("#rdestination").change(function () {
        var depa = $("#rdeparture").val();
        var dest = $("#rdestination").val();
        $.ajax({
            timeout: 3000,
            async:false,
            type:"post",
            url:"/selectdistance?depa="+depa+"&dest="+dest,
            success:function (data) {
                //清空之前添加的   否则会重复添加
                $("#distance").empty();
                //根据查询到的结果动态添加
                for (var i= 0 ; i<data.length;i++) {
                    $("#distance").append("<p style='color: red'>" + data[i]+"公里" + "</p>");
                }
                $("#distance").selectpicker("refresh");
            }
        });
    });
</script>
</body>
</html>
