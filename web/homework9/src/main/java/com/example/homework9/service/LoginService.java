package com.example.homework9.service;

import com.example.homework9.mapper.LoginMapper;
import com.example.homework9.pojo.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;

    public String[] login(String username, String password) {
        password = DigestUtils.md5Hex(password);

        String[] res = new String[2];
        if (loginMapper.loginByUsername(username)) {
            User user = loginMapper.loginByUsernameAndPassword(username, password);
            if (user != null) {
                res[0] = "ok";
                res[1] = user.getZhName();
                return res;
            }
        }

        res[0] = "账号或密码错误";
        return res;
    }

    public int incrementCurrentRequests(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer currentRequests = (Integer)session.getAttribute("currentRequests");
        if (currentRequests == null) {
            currentRequests = 0;
        }
        currentRequests++;
        session.setAttribute("currentRequests", currentRequests);

        return currentRequests;
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
