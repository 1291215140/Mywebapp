<%@ page import="com.ceshi.ceshi" %><%--
  Created by IntelliJ IDEA.
  User: 12912
  Date: 2023/7/5
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String ss= null;
    String user = request.getParameter("user");
    String password = request.getParameter("password");
    if(user==null|| user == "" || password==null||password=="")
    {
        out.print("<script>\n" +
                "    window.alert(\"请不要输入空的字符哦\");\n" +
                "    window.history.back();\n" +
                "</script>");
    }
    ceshi c = new ceshi();
    boolean bool = c.denglu(user,password,c);
    if(bool)
    {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    else
    {

        out.print("<script>\n" +
                "    window.alert(\"密码错误或账户不存在\");\n" +
              "    window.history.back();\n" +
                "</script>");
    }
%>
</body>
</html>
