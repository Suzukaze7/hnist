<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String name = (String) session.getAttribute("name");
    String solution = request.getParameter("address");
%>
<form id="form1" name="form1" method="post" action="">
    <table width="28%" border="0">
        <tr>
            <td colspan="2">
                <div align="center"><strong>显示答案</strong></div>
            </td>
        </tr>
        <tr>
            <td width="49%">
                <div align="left">您的名字是：</div>
            </td>
            <td width="51%"><label>
                <div align="left">
                    <%=name%>
                </div>
            </label></td>
        </tr>
        <tr>
            <td><label>
                <div align="left">您最喜欢去的地方是：</div>
            </label></td>
            <td>
                <div align="left"><%=solution%>
                </div>
            </td>
        </tr>
    </table>
</form>