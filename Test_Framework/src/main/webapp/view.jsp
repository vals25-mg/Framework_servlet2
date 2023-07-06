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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    <title>View</title>
</head>
<body>

    <div class="container w-50 shadow p-4 rounded-3">
        <h1>Page View</h1>

        <ul>
            <% if (request.getAttribute("Emp1").equals(null) || request.getAttribute("Emp2").equals(null)) { %>
            <li><%=request.getAttribute("Emp1")%>
            </li>
            <li><%=request.getAttribute("Emp2")%>
            </li>
            <% } %>
        </ul>


    </div>
</body>
</html>
