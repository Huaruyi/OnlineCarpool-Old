<%--
  Created by IntelliJ IDEA.
  User: lenovo-pc
  Date: 2019-07-02
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo-pc
  Date: 2019-06-28
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>司机审核</title>
    <!-- 导入easyui的资源文件 -->
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link id="themeLink" rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
</head>

<body>
<table id="list"></table>

<!-- 工具条 -->
<div id="tb">
    <a id="acBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">通过</a>
    <a id="rejBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">驳回</a>
</div>

<!-- 编辑窗口 -->
<div id="win" class="easyui-window" title="编辑用户" style="width:500px;height:300px"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <form id="editForm" method="post">
        <%--提供id隐藏域 --%>
        <input type="hidden" name="did">
        电话：<input type="text" name="dphone" class="easyui-validatebox" data-options="required:true"/><br/>
        姓名：<input type="text" name="dname" class="easyui-validatebox" data-options="required:true"/><br/>
        性别：
        <input type="radio" name="dgender" value="男"/>男
        <input type="radio" name="dgender" value="女"/>女
        <br/>
        <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    </form>
</div>

<script type="text/javascript">
    $(function(){
        $("#list").datagrid({
            //url:后台数据查询的地址
            url:"/driverPendingList",
            //columns：填充的列数据
            //field:后台对象的属性
            //title:列标题
            columns:[[
                {
                    field:"did",
                    title:"司机编号",
                    width:100,
                    checkbox:true
                },
                {
                    field:"dphone",
                    title:"司机电话",
                    width:200
                },
                {
                    field:"dname",
                    title:"司机姓名",
                    width:200
                },
                {
                    field:"dgender",
                    title:"性别",
                    width:200
                },
                {
                    field:"ddriverlicense",
                    title:"司机认证",
                    width:200
                }
            ]],
            //显示分页
            pagination:true,
            //工具条
            toolbar:"#tb"
        });

        /*通过审核*/
        $("#acBtn").click(function(){
            var rows =$("#list").datagrid("getSelections");
            if(rows.length==0){
                $.messager.alert("提示","至少选择一行","warning");
                return;
            }

            //格式： id=1&id=2&id=3
            $.messager.confirm("提示","确认通过验证吗?",function(value){
                if(value){
                    var idStr = "";
                    //遍历数据
                    $(rows).each(function(i){
                        idStr+=("did="+rows[i].did+"&");
                    });
                    idStr = idStr.substring(0,idStr.length-1);

                    //传递到后台
                    $.post("/verifyDriver",idStr,function(data){
                        if(data.success){
                            //刷新datagrid
                            $("#list").datagrid("reload");

                            $.messager.alert("提示","成功","info");
                        }else{
                            $.messager.alert("提示","失败："+data.msg,"error");
                        }
                    },"json");
                }
            });
        });

        /*驳回*/
        $("#rejBtn").click(function(){
            var rows =$("#list").datagrid("getSelections");
            if(rows.length==0){
                $.messager.alert("提示","至少选择一行","warning");
                return;
            }

            //格式： id=1&id=2&id=3
            $.messager.confirm("提示","确认驳回验证吗?",function(value){
                if(value){
                    var idStr = "";
                    //遍历数据
                    $(rows).each(function(i){
                        idStr+=("did="+rows[i].did+"&");
                    });
                    idStr = idStr.substring(0,idStr.length-1);

                    //传递到后台
                    $.post("/reject",idStr,function(data){
                        if(data.success){
                            //刷新datagrid
                            $("#list").datagrid("reload");

                            $.messager.alert("提示","成功","info");
                        }else{
                            $.messager.alert("提示","失败："+data.msg,"error");
                        }

                    },"json");
                }
            });
        });
    });

</script>
</body>
</html>