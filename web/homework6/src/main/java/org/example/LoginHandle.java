package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

public class LoginHandle {
    private int currentRequests = 0;

    public String[] login(String username, String password) {
        password = DigestUtils.md5Hex(password);
        String[] res = new String[2];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?user=root&password=362573");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `user` where username = '" + username + "'");
            if (resultSet.next()) {
                resultSet = statement.executeQuery("select * from `user` where username = '" + username + "' and password = '" + password + "'");
                if (resultSet.next()) {
                    res[0] = "ok";
                    res[1] = resultSet.getString(3);
                    return res;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        res[0] = "账号或密码错误";
        return res;
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