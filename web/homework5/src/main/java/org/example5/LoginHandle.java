package org.example5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginHandle {
    private int currentRequests = 0;

    public String login(String username, String password) {
        password = DigestUtils.md5Hex(password);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?user=root&password=362573");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `user` where username = '" + username + "'");
            if (resultSet.next()) {
                resultSet = statement.executeQuery("select * from `user` where username = '" + username + "' and password = '" + password + "'");
                if (resultSet.next())
                    return "欢迎 " + resultSet.getString(3);
                return "用户名或密码错误";
            }
            return "用户名不存在";
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int incrementCurrentRequests() {
        return ++currentRequests;
    }

    public int incrementTotalVisits(HttpServletRequest request, HttpServletResponse response) {
        int totalVisits = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("totalVisits")) {
                    totalVisits = Integer.parseInt(cookie.getValue()) + 1;
                    break;
                }
            }
        }

        Cookie totalVisitsCookie = new Cookie("totalVisits", String.valueOf(totalVisits));
        totalVisitsCookie.setPath("/");
        totalVisitsCookie.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(totalVisitsCookie);

        return totalVisits;
    }
}