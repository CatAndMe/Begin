<%@ page import="static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 冰开水
  Date: 2017/7/21
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignList</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.min.js" ></script>

</head>
<body>
<table border="1" align="center">
    <tr>
        <td align="center"></td>
        <td align="center">名字</td>
        <td align="center">编号</td>
        <td align="center">时间</td>
        <td align="center">类型</td>
        <td align="center">状态</td>
        <td align="center">理由</td>
        <td align="center">实际签到时间</td>

    </tr>


    <%int i=1;%>
    <c:if test="${!empty signList}">
          <c:forEach var="sign" items="${signList}">
         <tr>
             <td align="center"><%i++;%></td>
             <td align="center">${sign.emplName}</td>
             <td align="center">${sign.emplId}</td>
             <td align="center">${sign.time}</td>
             <td align="center">${sign.login}</td>
             <td align="center">${sign.loginState}</td>
             <td align="center">${sign.signOut}</td>
             <td align="center">${sign.signOutState}</td>
             <td align="center">${sign.trueTime}</td>
         </tr>
            </c:forEach>
        </c:if>

</table>
<a href="/SignView/PostTest.html">测试</a>


</body>
</html>
