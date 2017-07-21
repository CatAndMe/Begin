
  Created by IntelliJ IDEA.
  User: 冰开水
  Date: 2017/7/19
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%
      String path = request.getContextPath();
      String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
<html>
<head>
    <title>Congratulation！！</title>
</head>
<body>
成功
${message}
<br>
<a href="<%=path%>/Action/SuccessView.do">返回</a>
<br>
</body>
</html>
