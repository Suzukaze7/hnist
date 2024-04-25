<%@ page import="org.example.LoginHandle" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="css/index.css">
<div class="container">
    <%
        LoginHandle loginHandle = (LoginHandle) session.getAttribute("loginHandle");
        if (loginHandle == null) {
            loginHandle = new LoginHandle();
            session.setAttribute("loginHandle", loginHandle);
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println(loginHandle.login(username, password) + "!<br>");
        out.println("当前请求数量: " + loginHandle.incrementCurrentRequests() + "<br>");
        out.println("总访问次数: " + loginHandle.incrementTotalVisits(request, response) + "<br>");
    %>
</div>