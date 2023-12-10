<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/10/2023
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%String nameCourse = request.getAttribute("nameCourse").toString();%>
    <h1>Chi tiết khoá học <%=nameCourse%></h1>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/3UDefkrGQ_w?si=0BBzoJ4u_F7Sr-yN" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/e3agRuWPCng?si=sVzzkZ3lFg28k_V0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
</body>
</html>
