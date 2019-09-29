<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>绑定车辆</title>
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
<jsp:include page="/WEB-INF/pages/driver_header.jsp"></jsp:include>


<div class="container"
     style="width: 100%;">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8"
             style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
            <font>司机注册-绑定车辆</font>RIGISTCAR
            <div>&nbsp;</div>
            <form action="driverRigistCar" method="post" enctype="multipart/form-data" class="form-horizontal">
                <div class="form-group">
                    <div class="col-sm-6">
                        <input type="hidden" name="did" value="${did}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="brand" class="col-sm-2 control-label">品牌</label>
                    <div class="col-sm-6">
                        <select id="brand" name="cbrand" class="form-control" onchange="changeCar()">
                            <option>品牌</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="brand" class="col-sm-2 control-label">型号</label>
                    <div class="col-sm-6">
                        <select id="type" name="ctype" class="form-control">
                            <option>型号</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="color" class="col-sm-2 control-label">颜色</label>
                    <div class="col-sm-6">
                        <select id="color" name="ccolor" class="form-control">
                            <option>白色</option>
                            <option>黑色</option>
                            <option>灰色</option>
                            <option>红色</option>
                            <option>蓝色</option>
                            <option>绿色</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cno" class="col-sm-2 control-label">车牌号</label>
                    <div class="col-sm-6">
                        <input type="text" id="cno" name="cno" value="黑A" class="form-control">

                    </div>
                </div>
                <div class="form-group">
                    <label for="ccarlicense" class="col-sm-2 control-label">上传行驶证</label>
                    <div class="col-sm-6">
                        <input type="file" id="ccarlicense" name="multipartFile" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" width="100" value="注册" name="submit"
                               style="background: url('../../images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                        <span class="sr-only">100% Complete</span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/WEB-INF/pages/footer.jsp"></jsp:include>

<script>
    var carList = new Array();
    carList['丰田'] = ['卡罗拉','雷凌','威驰','奕泽','C-HR'];
    carList['大众'] = ['宝来','POLO','高尔夫','朗逸','速腾'];
    carList['本田'] = ['飞度','思域','缤智','凌派','XR-V'];
    function changeCar() {
        var types = document.getElementById("type");
        var brands = document.getElementById("brand");
        types.options.length = 0;
        for (var i in carList) {
            if (i == brands.value)
                for (var j in carList[i]) {
                    types.add(new Option(carList[i][j], carList[i][j]), null);
                }
        }
    }
    function allCar(){
        var brand=document.getElementById("brand");
        for (var i in carList){
            brand.add(new Option(i, i),null);
        }
    }
    window.onload = function (){
        allCar();
    }
</script>

</body>
</html>