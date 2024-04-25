package com.example.homework8.controller;

import com.example.homework8.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        HttpSession session = request.getSession();
        LoginService loginService = (LoginService) session.getAttribute("loginHandle");
        if (loginService == null) {
            loginService = new LoginService();
            session.setAttribute("loginHandle", loginService);
        }

        TreeMap<String, Object> res = new TreeMap<>();

        String[] loginRes = loginService.login(username, password);
        res.put("status", loginRes[0]);
        if (loginRes[0].equals("ok"))
            res.put("name", loginRes[1]);

        res.put("currentRequest", loginService.incrementCurrentRequests());
        res.put("totalVisit", loginService.incrementTotalVisits(request, response));

        return res;
    }
}
