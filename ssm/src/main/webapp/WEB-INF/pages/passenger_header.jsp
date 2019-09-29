<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<div class="container-fluid">
    <div class="col-md-3">

    </div>
    <div class="col-md-5">

    </div>
    <div class="col-md-4" style="padding-top:20px">
        <ol class="list-inline">

            <c:if test="${empty user}">
                <li><a href="loginTypeUI">登录</a></li>

                <li><a href="registerTypeUI">注册</a></li>
            </c:if>

            <c:if test="${not empty user}">
                <li><a href="javascript:;" style="text-decoration: none;color: #0C0C0C">你好，${user.uname}</a></li>
                <li><a href="logout">退出</a></li>
                <li><a href="passengerCarpooling">我的拼车</a></li>
                <li><a href="passengerApprove">我的认证</a></li>
                <li><a href="passengerSpace">个人中心</a></li>
                <li><a href="passengerOrderList">我的订单</a></li>
            </c:if>
        </ol>
    </div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://localhost:8080/passengerIndex">首页</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="cul">


                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>
    </nav>
</div>