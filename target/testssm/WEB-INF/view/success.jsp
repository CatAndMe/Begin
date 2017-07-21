
<%--
  Created by IntelliJ IDEA.
  User: jinglingmei
  Date: 15/9/8
  Time: 下午6:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title></title>
</head>
<body>

登入成功!
<br>
您好!${user.username}
<br>
查询所有：<br>
<a href="/Action/userList.do">List All</a><br><br>

新增：<br><form name="channelform" action="/Action/add.do" onsubmit="return validate_channel_info(this);">
    ID:<input type="text" name="id"><br>
    NAME:<input type="text" name="username"><br>
    PASSWORD:<input type="password" name="password"><br>

    <input type="submit" value="提交">
</form><br>

删除：<br><form name="channelform" action="/Action/delete.do" onsubmit="return validate_channel_info(this);">
    输入要删除的ID：<input type="text" name="id"><input type="submit" value="提交"><br>
</form><br>

查询：<br><form name="channelform" action="/Action/get.do" onsubmit="return validate_channel_info(this);">
    输入要查询的ID：<input type="text" name="id"><input type="submit" value="提交"><br>
</form><br>

修改：<br><form name="channelform" action="/Action/update.do" onsubmit="return validate_channel_info(this);">
    根据ID来改，不支持修改ID，输入的名字，工作为修改值：<br>
    ID:<input type="text" name="id"><br>
    NAME:<input type="text" name="username"><br>
    PASSWORD:<input type="password" name="password"><br>
    <input type="submit" value="提交">
</form><br>

<script type="text/javascript">
    function validate_channel_info(channelform)
    {
        if(!isNumber(channelform.id.value))
        {
            alert("请输入合法ID");
            return false;
        }
        return true;
    }

    function isNumber(str)          // 判断是否为非负整数
    {
        var rx = /^[0-9]+$/;
        return rx.test(str);
    }

</script>
<br>
<a href="/user/logout.do">退出登录</a>
</body>
</html>
