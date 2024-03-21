<%@ page import="org.example.LoginHandle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取或创建 HttpSession 对象
    // 从 session 中获取 LoginHandler 对象，如果不存在则创建一个新的
    LoginHandle loginHandle = (LoginHandle) session.getAttribute("loginHandle");
    if (loginHandle == null) {
        loginHandle = new LoginHandle();
        session.setAttribute("loginHandle", loginHandle);
    }

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    boolean loggedIn = loginHandle.login(username, password);

    if (loggedIn) {
        out.println("Welcome, " + username + "!<br>");
    } else {
        out.println("Invalid username or password.<br>");
    }
    out.println("Current Requests: " + loginHandle.incrementCurrentRequests() + "<br>");
    out.println("Total Visits: " + loginHandle.incrementTotalVisits(request, response) + "<br>");
%>
