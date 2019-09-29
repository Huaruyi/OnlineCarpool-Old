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
    <title>路线管理</title>

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
    <a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a id="deleteBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">下线</a>
    <a id="renewBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">恢复</a>
</div>

<!-- 编辑窗口 -->
<div id="win" class="easyui-window" title="编辑用户" style="width:500px;height:300px"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <form id="editForm" method="post">
        <%--提供id隐藏域 --%>
        <input type="hidden" name="rid">
        出发地：<input type="text" name="rdeparture" class="easyui-validatebox" data-options="required:true"/><br/>
        目的地：<input type="text" name="rdestination" class="easyui-validatebox" data-options="required:true"/><br/>
        距离：<input type="text" name="rdistance" class="easyui-validatebox" data-options="required:true"/><br/>
        信号数目：<input type="text" name="rsignal" class="easyui-validatebox" data-options="required:true"/><br/>
        <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    </form>
</div>

<script type="text/javascript">
    $(function(){
        $("#list").datagrid({
            //url:后台数据查询的地址
            url:"/listRouteByPage",
            //columns：填充的列数据
            //field:后台对象的属性
            //title:列标题
            columns:[[
                {
                    field:"rid",
                    title:"路线编号",
                    width:100,
                    checkbox:true
                },
                {
                    field:"rdeparture",
                    title:"出发地",
                    width:200
                },
                {
                    field:"rdestination",
                    title:"目的地",
                    width:200
                },
                {
                    field:"rdistance",
                    title:"距离",
                    width:200
                },
                {
                    field:"rsignal",
                    title:"信号数目",
                    width:200
                },
                {
                    field:"rstate",
                    title:"路线状态",
                    width:200
                },
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
                url:"/saveRoute",
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

            //表单回显
            $("#editForm").form("load","/findRouteById?rid="+rows[0].rid);

            $("#win").window("open");
        });

        /*下线数据*/
        $("#deleteBtn").click(function(){
            var rows =$("#list").datagrid("getSelections");
            if(rows.length==0){
                $.messager.alert("提示","下线操作至少选择一行","warning");
                return;
            }

            //格式： id=1&id=2&id=3
            $.messager.confirm("提示","确认下线路线吗?",function(value){
                if(value){
                    var idStr = "";
                    //遍历数据
                    $(rows).each(function(i){
                        idStr+=("rid="+rows[i].rid+"&");
                    });
                    idStr = idStr.substring(0,idStr.length-1);

                    //传递到后台
                    $.post("/deleteRoute",idStr,function(data){
                        if(data.success){
                            //刷新datagrid
                            $("#list").datagrid("reload");

                            $.messager.alert("提示","下线成功","info");
                        }else{
                            $.messager.alert("提示","下线失败："+data.msg,"error");
                        }
                    },"json");
                }
            });
        });

        /*恢复数据*/
        $("#renewBtn").click(function(){
            var rows =$("#list").datagrid("getSelections");
            if(rows.length==0){
                $.messager.alert("提示","下线操作至少选择一行","warning");
                return;
            }

            //格式： id=1&id=2&id=3
            $.messager.confirm("提示","确认恢复路线吗?",function(value){
                if(value){
                    var idStr = "";
                    //遍历数据
                    $(rows).each(function(i){
                        idStr+=("rid="+rows[i].rid+"&");
                    });
                    idStr = idStr.substring(0,idStr.length-1);

                    //传递到后台
                    $.post("/renewRoute",idStr,function(data){
                        if(data.success){
                            //刷新datagrid
                            $("#list").datagrid("reload");

                            $.messager.alert("提示","恢复成功","info");
                        }else{
                            $.messager.alert("提示","恢复失败："+data.msg,"error");
                        }
                    },"json");
                }
            });
        });
    });

</script>
</body>
</html>
