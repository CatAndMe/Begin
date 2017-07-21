<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 冰开水
  Date: 2017/7/18
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body >

    <c:if test="${!empty userList}">
        <c:forEach var="user" items="${userList}">
            姓名：${user.username} 密码：${user.password} id:${user.id}<br>

        </c:forEach>
    </c:if>

</body>
</html>