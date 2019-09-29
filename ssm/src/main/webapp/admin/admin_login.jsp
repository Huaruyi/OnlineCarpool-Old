<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员登录</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
    <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../css/style.css" type="text/css" />

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




<div class="container"
     style="width: 100%; height: 460px; background: black">
    <div class="row">

        <div class="col-md-4">
            <!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
        </div>
        <div class="col-md-4">
            <div
                    style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
                <font>管理员登录</font>ADMIN LOGIN
                <div>&nbsp;</div>
                <form action="/adminLogin" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="aname" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="aname"
                                   name="aname"  placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="apassword" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="apassword"
                                   name="apassword"  placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="arole" class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-6">
                            <select id="arole" name="arole" class="form-control selectpicker" data-live-search="true">
                                <option value="0">超级管理员</option>
                                <option value="1">审核管理员</option>
                                <option value="2">路线管理员</option>
                                <option value="3">评价管理员</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" width="100" value="登录" name="submit"
                                   style="background: url('../images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                        </div>
                    </div>
                    <font color="red" size="12px">${message}</font>
                </form>
            </div>
        </div>
        <div class="col-md-4">

        </div>
    </div>
</div>



</body>
</html>·