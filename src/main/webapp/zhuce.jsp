<%@ page import="com.ceshi.ceshi" %><%--
  Created by IntelliJ IDEA.
  User: 12912
  Date: 2023/7/5
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String againpassword = request.getParameter("againpassword");
    if(password.compareTo(againpassword)!=0)
    {
        out.print("<script>\n" +
                "    window.alert(\"两次输入密码不相同，请重新输入\");\n" +
                "    window.history.back();\n" +
                "</script>");
    }
    else
    {
        ceshi ce = new ceshi();
        boolean bool = ce.zhuce(username,password,ce);
        if(bool)
        {
            out.print("<script>\n" +
                    "    window.alert(\"注册成功\");\n" +
                    "</script>");

            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        else
        {
            out.print("<script>\n" +
                    "    window.alert(\"该用户已经注册\");\n" +
                    "    window.history.back();\n" +
                    "</script>");
        }
    }
%>
</body>
</html>
