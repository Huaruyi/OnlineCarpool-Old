<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择您的类型</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
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

        .container .row div {
            /* position:relative;
                         float:left; */

        }

        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }
        .alt{
            color: red;
            font-size: 15px;
        }
    </style>
</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/WEB-INF/pages/passenger_header.jsp"></jsp:include>


<div class="container">
    <div class="row-fluid">
        <div class="span12">
            <h3 class="text-center">
                请选择：
            </h3>
        </div>
    </div>
    <div class="btn-group btn-group-justified" style="text-align:center">
        <a href="/passengerPublishTypeUI?id=1" class="btn btn-primary">我要拼车</a>
        <a href="/passengerPublishTypeUI?id=2" class="btn btn-primary">我要预定</a>
    </div>
</div>
<!-- 引入footer.jsp -->
<jsp:include page="/WEB-INF/pages/footer.jsp"></jsp:include>
</body>
</html>






