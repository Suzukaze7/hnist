<%@ page import="org.example6.LoginHandle" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/plain;charset=UTF-8" %>
<%
    LoginHandle loginHandle = (LoginHandle) session.getAttribute("loginHandle");
    if (loginHandle == null) {
        loginHandle = new LoginHandle();
        session.setAttribute("loginHandle", loginHandle);
    }

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    JSONObject jsonObject = new JSONObject();

    String[] loginRes = loginHandle.login(username, password);
    jsonObject.put("status", loginRes[0]);
    if (loginRes[0].equals("ok"))
        jsonObject.put("name", loginRes[1]);

    jsonObject.put("currentRequest", loginHandle.incrementCurrentRequests());
    jsonObject.put("totalVisit", loginHandle.incrementTotalVisits(request, response));

    out.print(jsonObject.toString());
%>
