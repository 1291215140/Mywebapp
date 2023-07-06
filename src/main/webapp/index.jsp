<%@ page import="poms.pom3" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
</head>
<body>
<%
    String user = request.getParameter("username");
    new Thread(new pom3()).start();
%>
<h1>
    欢迎您<%=user%>
</h1>
</body>
</html>