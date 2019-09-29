

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试图片回显</title>
</head>
<body>



<img src="http://localhost:8080/uploads/${car.ccarlicense}" alt="车辆行驶证" height="250" width="400">

<form action="carDownImg" method="post">
    <input type="hidden" name="ccarlicense" value="${car.ccarlicense}">
    <input type="submit" value="下载">
</form>
</body>
</html>

