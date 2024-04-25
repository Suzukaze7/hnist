package com.example.homework9.controller;

import com.example.homework9.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        Map<String, Object> res = new TreeMap<>();

        String[] loginRes = loginService.login(username, password);
        res.put("status", loginRes[0]);
        if (loginRes[0].equals("ok"))
            res.put("name", loginRes[1]);

        res.put("currentRequest", loginService.incrementCurrentRequests(request, response));
        res.put("totalVisit", loginService.incrementTotalVisits(request, response));

        return res;
    }
}
