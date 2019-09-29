<%--
  Created by IntelliJ IDEA.
  User: lenovo-pc
  Date: 2019-07-02
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>司机管理</title>

    <!-- 导入easyui的资源文件 -->
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link id="themeLink" rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
</head>

<body>
<table id="list"></table>

<!-- 工具条 -->
<div id="tb">
    <%--<a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>--%>
    <a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <%--<a id="deleteBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>--%>
</div>

<!-- 编辑窗口 -->
<div id="win" class="easyui-window" title="编辑用户" style="width:500px;height:300px"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <form id="editForm" method="post">]
        <%--提供id隐藏域 --%>
        <input type="hidden" name="did">
        初始化密码：<input type="text" name="dpassword" class="easyui-validatebox" data-options="required:true"/><br/>
        <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    </form>
</div>

<script type="text/javascript">
    $(function(){
        $("#list").datagrid({
            //url:后台数据查询的地址
            url:"/listDriverByPage",
            //columns：填充的列数据
            //field:后台对象的属性
            //title:列标题
            columns:[[
                {
                    field:"did",
                    title:"用户编号",
                    width:100,
                    checkbox:true
                },
                {
                    field:"dphone",
                    title:"用户电话",
                    width:200
                },
                {
                    field:"dname",
                    title:"用户姓名",
                    width:200
                },
                {
                    field:"dgender",
                    title:"性别",
                    width:200
                },
                {
                    field:"ddriverlicense",
                    title:"客户认证",
                    width:200
                }
            ]],
            //显示分页
            pagination:true,
            //工具条
            toolbar:"#tb"
        });

        /*打开编辑窗口*/
        $("#addBtn").click(function(){
            //清空之前提交的表单数据
            $("#editForm").form("clear");
            $("#win").window("open");
        });

        /*保存数据*/
        $("#saveBtn").click(function(){
            $("#editForm").form("submit",{
                //url: 提交到后台的地址
                url:"/saveDriver",
                //onSubmit: 表单提交前的回调函数,返回值：--true：提交表单 --false：不提交表单
                onSubmit:function(){
                    //判断表单的验证是否都通过了
                    return $("#editForm").form("validate");
                },
                //success:服务器执行完毕回调函数
                success:function(data){ 	//data: 服务器返回的数据，类型：字符串
                    //要求Controller返回的数据格式：
                    //成功：要求controller返回一个 {success:true}  失败:{success:false,msg:错误信息}

                    //把data字符串类型转换为对象类型
                    data = eval("("+data+")");

                    if(data.success){
                        //关闭窗口
                        $("#win").window("close");
                        //刷新datagrid
                        $("#list").datagrid("reload");

                        $.messager.alert("提示","保存成功","info");
                    }else{
                        $.messager.alert("提示","保存失败："+data.msg,"error");
                    }
                }
            });

        });

        /*修改数据*/
        $("#editBtn").click(function(){
            //判断只能选择一行
            var rows = $("#list").datagrid("getSelections");
            if(rows.length!=1){
                $.messager.alert("提示","修改操作只能选择一行","warning");
                return;
            }
            //不回显

            /*//表单回显
            $("#editForm").form("load","/findDriverById?did="+rows[0].did);*/

            $("#win").window("open");
        });






        /*删除数据*/
        $("#deleteBtn").click(function(){
            var rows =$("#list").datagrid("getSelections");
            if(rows.length==0){
                $.messager.alert("提示","删除操作至少选择一行","warning");
                return;
            }

            //格式： id=1&id=2&id=3
            $.messager.confirm("提示","确认删除数据吗?",function(value){
                if(value){
                    var idStr = "";
                    //遍历数据
                    $(rows).each(function(i){
                        idStr+=("did="+rows[i].did+"&");
                    });
                    idStr = idStr.substring(0,idStr.length-1);

                    //传递到后台
                    $.post("/deleteDriver",idStr,function(data){
                        if(data.success){
                            //刷新datagrid
                            $("#list").datagrid("reload");

                            $.messager.alert("提示","删除成功","info");
                        }else{
                            $.messager.alert("提示","删除失败："+data.msg,"error");
                        }
                    },"json");
                }
            });
        });
    });

</script>
</body>
</html>
