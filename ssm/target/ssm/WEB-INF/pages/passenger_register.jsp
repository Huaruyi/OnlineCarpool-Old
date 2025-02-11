<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>乘客注册</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
<script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
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
.error{
	color: red;
}
</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/WEB-INF/pages/passenger_header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%;">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>乘客注册</font>PASSENGER REGISTER
				<form id="form" action="passengerRegister" method="post" enctype="multipart/form-data" class="form-horizontal" style="margin-top: 5px;">
					<div class="form-group">
						<label for="uphone" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="uphone"
								   name="uphone" placeholder="请输入手机号">
						</div>
					</div>

					<%--<div class="form-group">
						<label for="name" class="col-sm-2 control-label">账号</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name"
								name="name" placeholder="请输入账号">
						</div>
					</div>--%>
					<div class="form-group">
						<label for="upassword" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="upassword"
								name="upassword" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="repassword" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="repassword"
								   name="repassword" placeholder="请在次输入密码">
						</div>
					</div>
					<%--<div class="form-group">
						<label for="email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email"
								   name="email" placeholder="请输入邮箱">
						</div>
					</div>--%>

					<div class="form-group">
						<label for="uname" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="uname"
								   name="uname" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="ugender" id="inlineRadio1" checked="checked" value="男">
								男
							</label>
							<label class="radio-inline"> <input type="radio"
								name="ugender" id="inlineRadio2" value="女">
								女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="ubirthday" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" id="ubirthday" name="ubirthday" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="uidcard" class="col-sm-2 control-label">上传身份证</label>
						<div class="col-sm-6">
							<input type="file" id="uidcard" name="multipartFile" class="form-control">
						</div>
					</div>
                    <div class="form-group">
                        <label for="code" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-6">
                            <input type="tel" id="code" name="code" class="form-control">
                        </div>
                        <div class="col-sm-2">
                            <canvas id="canvas" width="120" height="45"></canvas>
                            <a href="#" id="changeImg">看不清，换一张</a>
                        </div>
                    </div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('../../images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/WEB-INF/pages/footer.jsp"></jsp:include>
	<%--验证码  https://blog.csdn.net/qq_41915456/article/details/80018354--%>
	<script>
        /**生成一个随机数**/
        function randomNum(min,max){
            return Math.floor( Math.random()*(max-min)+min);
        }
        /**生成一个随机色**/
        function randomColor(min,max){
            var r = randomNum(min,max);
            var g = randomNum(min,max);
            var b = randomNum(min,max);
            return "rgb("+r+","+g+","+b+")";
        }
        drawPic();
        document.getElementById("changeImg").onclick = function(e){
            e.preventDefault();
            drawPic();
        }

        var contxt;

        /**绘制验证码图片**/
        function drawPic(){
            var canvas=document.getElementById("canvas");
            var width=canvas.width;
            var height=canvas.height;
            var ctx = canvas.getContext('2d');
            ctx.textBaseline = 'bottom';

            /**绘制背景色**/
            ctx.fillStyle = randomColor(180,240); //颜色若太深可能导致看不清
            ctx.fillRect(0,0,width,height);
            /**绘制文字**/
            var str = 'ABCEFGHJKLMNPQRSTWXY123456789';
            contxt="";
            for(var i=0; i<4; i++){
                var txt = str[randomNum(0,str.length)];
                contxt+=txt;
                ctx.fillStyle = randomColor(50,160);  //随机生成字体颜色
                ctx.font = randomNum(15,40)+'px SimHei'; //随机生成字体大小
                var x = 10+i*25;
                var y = randomNum(25,45);
                var deg = randomNum(-45, 45);
                //修改坐标原点和旋转角度
                ctx.translate(x,y);
                ctx.rotate(deg*Math.PI/180);
                ctx.fillText(txt, 0,0);
                //恢复坐标原点和旋转角度
                ctx.rotate(-deg*Math.PI/180);
                ctx.translate(-x,-y);
            }
            /**绘制干扰线**/
            for(var i=0; i<8; i++){
                ctx.strokeStyle = randomColor(40,180);
                ctx.beginPath();
                ctx.moveTo( randomNum(0,width), randomNum(0,height) );
                ctx.lineTo( randomNum(0,width), randomNum(0,height) );
                ctx.stroke();
            }
            /**绘制干扰点**/
            for(var i=0; i<100; i++){
                ctx.fillStyle = randomColor(0,255);
                ctx.beginPath();
                ctx.arc(randomNum(0,width),randomNum(0,height), 1, 0, 2*Math.PI);
                ctx.fill();
            }
        }

		$(function () {

		    jQuery.validator.addMethod("checkUphone",
				function (value,element,params) {
					//ajax
					var flag = false;
					$.ajax({
						async:false,
						type:"post",
						data:{uphone:value},
						url:"/checkUphone",
						success:function (data) {
						    if (!data.success)
						        /*alert(data.success);*/
								flag = true;
                        }
					});
		        	return flag;
                },"格式错误");


            jQuery.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
                var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                return this.optional(element) || (length === 11 && mobile.test(value));
            }, "请正确填写手机号码");

            jQuery.validator.addMethod("checkCode", function(value, element) {
                value = value.toUpperCase();
                return this.optional(element) || (value === contxt);
            }, "请正确填写手机号码");

			$("#form").validate({
                rules:{
                    uphone:{
                        required:true,
                        minlength:11,
                        isMobile:true,
                        checkUphone:true
                    },
                    upassword:{
                        required:true,
                        rangelength:[4,16]
					},
                    repassword:{
                        required:true,
                        equalTo:"[name='upassword']"
                    },
                    uname:{
                        required:true
                    },
                    ubirthday:{
                        required:true
                    },code:{
                        required:true,
                        checkCode:true
                    }
                },
                messages:{
                    uphone:{
                        required:"请输入手机号",
                        minlength:"不能小于11个字符",
                        isMobile:"请正确填写手机号码",
                        checkUphone:"该手机号已经被注册"
                    },
                    upassword:{
                        required:"密码不能为空",
                        rangelength:"密码必须介于4-16位之间"
					},
                    repassword:{
                        required:"确认密码不能为空",
						equalTo:"两次密码不一致"
					},
					uname:{
                        required:"姓名不能为空"
                    },
                    ubirthday:{
                        required:"必须填入生日"
                    },code:{
                        required:"需填写验证码",
                        checkCode:"验证码错误"
                    }
                }
			});
        });
	</script>
</body>
</html>




