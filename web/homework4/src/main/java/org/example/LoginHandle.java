package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandle {
    private int currentRequests = 0;

    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("123456");
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