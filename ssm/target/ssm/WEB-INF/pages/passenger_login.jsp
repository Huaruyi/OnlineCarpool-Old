<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>乘客登录</title>
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


<div class="container"
     style="width: 100%; height: 460px; background: #FF2C4C url('../../images/loginbg.png') no-repeat;">
    <div class="row">

        <div class="col-md-4">
            <!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
        </div>
        <div class="col-md-4">
            <div
                    style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
                <font>乘客登录</font>PASSENGER LOGIN
                <div>&nbsp;</div>
                <form action="passengerLogin" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="uphone" class="col-sm-2 control-label">账号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="uphone"
                                   name="uphone"  placeholder="请输入手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="upassword" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="upassword"
                                   name="upassword"  placeholder="请输入密码">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" width="100" value="登录" name="submit"
                                   style="background: url('../../images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                        </div>
                    </div>
                    <font class="alt">${message}</font>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/WEB-INF/pages/footer.jsp"></jsp:include>

</body>
</html>