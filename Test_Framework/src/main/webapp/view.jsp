<%--
  Created by IntelliJ IDEA.
  User: valisoa
  Date: 18/04/2023
  Time: 02:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
    <h1>Page View</h1>
    <ul>

        <li><%=request.getAttribute("Emp1")%></li>
        <li><%=request.getAttribute("Emp2")%></li>
    </ul>
</body>
</html>
