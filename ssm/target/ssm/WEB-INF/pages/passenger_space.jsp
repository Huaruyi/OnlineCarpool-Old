<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人中心</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css" />
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../../css/style.css" type="text/css" />
    <%--引入layUI css文件，显示星级评价--%>
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
    <style>
        /*Validate错误信息提示的字色5*/
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <table class="table">
                <thead><tr><th>个人信息</th></tr></thead>
                <tbody>
                <tr><td>姓名</td> <td>${user.uname}</td></tr>
                <tr><td>手机号</td> <td>${user.uphone}</td></tr>
                <tr><td>性别</td> <td>${user.ugender}</td></tr>
                <tr><td>生日</td> <td><fmt:formatDate value="${user.ubirthday}" type="date"/></td></tr>
                <tr><td>注册时间</td> <td>${user.uregistday}</td></tr>
                <tr><td>拼车次数</td><td>${user.utimes}次</td></tr>
                <tr>
                    <td>评分</td>
                    <td>
                        <c:if test="${empty user.uevaluate}">
                            暂无评分，请先参与一场拼车呦！
                        </c:if>
                        <c:if test="${not empty user.uevaluate}">
                            <div id="rate"></div>
                        </c:if>
                        <c:if test="${not empty user.uevaluate}">
                            ${user.uevaluate}分
                            <c:if test="${user.uevaluate >= 4.5}">
                                &nbsp;&nbsp;&nbsp;您的评分很高哦，请继续保持
                            </c:if>
                            <c:if test="${4.5>user.uevaluate and user.uevaluate >= 4.0}">
                                &nbsp;&nbsp;&nbsp;您的评分还不错，加油吧
                            </c:if>
                            <c:if test="${4.0>user.uevaluate and user.uevaluate >= 3.0}">
                                &nbsp;&nbsp;&nbsp;您的评分一般，需要提高分数
                            </c:if>
                            <c:if test="${3.0>user.uevaluate and user.uevaluate >= 2.5}">
                                &nbsp;&nbsp;&nbsp;您的评分略低，请提高您的个人行为
                            </c:if>
                            <c:if test="${2.5>user.uevaluate and user.uevaluate >= 2.0}">
                                &nbsp;&nbsp;&nbsp;您的评分极低，如果不提高分数可能会被封号
                            </c:if>
                        </c:if>
                    </td>

                </tr>
                <tr>
                    <!-- 弹出子窗体 -->
                    <td>
                        <button class="btn btn-danger" type="button" data-toggle="modal" data-target="#modifyPassword">修改密码</button>
                    <!-- Modal -->
                    <div class="modal fade" id="modifyPassword" tabindex="-1" role="dialog" aria-labelledby="modifyPasswordLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modifyPasswordLabel">修改密码</h4>
                                </div>
                                <div class="modal-body">
                                    <form id="modifyPasswordForm" action="modifyUpassword" method="post" class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <input type="hidden" name="uid" value="${user.uid}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="upassword" class="col-sm-2 control-label">密码</label>
                                            <div class="col-sm-4">
                                                <input type="password" class="form-control" id="upassword" name="upassword" placeholder="请输入密码">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="newupassword" class="col-sm-2 control-label">新密码</label>
                                            <div class="col-sm-4">
                                                <input type="password" class="form-control" id="newupassword" name="newupassword" placeholder="请输入密码">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="repassword" class="col-sm-2 control-label">确认密码</label>
                                            <div class="col-sm-4">
                                                <input type="password" class="form-control" id="repassword" name="repassword" placeholder="请在次输入密码">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">我再想想</button>
                                    <button type="button" onclick="modifyPasswordForm()" class="btn btn-primary">确定修改</button>
                                    <script>
                                        function modifyPasswordForm() {
                                            $("#modifyPasswordForm").submit();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    </td>
                    <td><button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modifyPhone">更换手机</button>
                    <!-- Modal -->
                    <div class="modal fade" id="modifyPhone" tabindex="-1" role="dialog" aria-labelledby="modifyPhoneLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="modifyPhoneLabel">更换手机</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <form id="modifyPhoneForm" action="modifyUphone" method="post" class="form-horizontal">
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <input type="hidden" name="uid" value="${user.uid}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="uphone" class="col-sm-2 control-label">手机</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="uphone" name="uphone" placeholder="请输入手机号">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="upassword" class="col-sm-2 control-label">密码</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" name="upassword" placeholder="请输入密码">
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">我再想想</button>
                                    <button type="button" onclick="modifyPhoneForm()" class="btn btn-primary">确认更换</button>
                                    <script>
                                        function modifyPhoneForm() {
                                            $("#modifyPhoneForm").submit();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    $(function () {
        jQuery.validator.addMethod("checkUphone", function (value,element,params) {
                //ajax
                var flag = false;
                $.ajax({
                    async:false,
                    type:"post",
                    data:{uphone:value},
                    url:"/checkUphone",
                    success:function (data) {
                        if (!data.success)
                            flag = true;
                    }
                });
                return flag;
        },"格式错误");
        jQuery.validator.addMethod("checkUpassword", function (value,element,params) {
            //ajax
            var flag = false;
            $.ajax({
                async:false,
                type:"post",
                data:{upassword:value},
                url:"/checkUpassword",
                success:function (data) {
                    if (!data.success)
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
        $("#modifyPhoneForm").validate({
            rules:{
                uphone:{
                    required:true,
                    minlength:11,
                    isMobile:true,
                    checkUphone:true
                },upassword:{
                    required:true,
                    checkUpassword:true
                }
            },
            messages:{
                uphone:{
                    required:"请输入新手机号",
                    minlength:"新手机号位数不能少于11",
                    isMobile:"请正确填写手机号码",
                    checkUphone:"该手机号已经被注册"
                },upassword:{
                    required:"密码不能为空",
                    checkUpassword:"密码有误!"
                }
            }
        });



        $("#modifyPasswordForm").validate({
            rules:{
                upassword:{
                    required:true,
                    rangelength:[4,16],
                    checkUpassword:true
                },
                newupassword:{
                    required:true,
                    rangelength:[4,16]
                },
                repassword:{
                    required:true,
                    equalTo:"[name='newupassword']"
                }
            },
            messages:{
                upassword:{
                    required:"密码不能为空",
                    rangelength:"密码必须介于4-16位之间",
                    checkUpassword:"密码错误"
                },
                newupassword:{
                    required:"新密码不能为空",
                    rangelength:"新密码必须介于4-16位之间"
                },
                repassword:{
                    required:"确认密码不能为空",
                    equalTo:"两次密码不一致"
                }
            }
        });
    });
</script>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'rate'], function(){
        var rate = layui.rate;

        //只读
        rate.render({
            elem: '#rate'
            ,value: ${user.uevaluate}
            ,readonly: true
            ,half: true //开启半星
        });
    });
</script>
</body>
</html>