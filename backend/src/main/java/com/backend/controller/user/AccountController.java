package com.backend.controller.user;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.io.IoUtil;
import com.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/check")
    public String check() {
        return "ok";
    }

    @PostMapping("/user/login")
    public Map<String, String> login(@RequestParam Map<String, String> data) {
        return userService.login(data.get("username"), data.get("password"));
    }

    @PostMapping("/user/register")
    public Map<String, String> register(@RequestParam Map<String, String> data) {
        return userService.register(data.get("username"), data.get("password"), data.get("confirm"));
    }

    @GetMapping("/checkcode")
    public void checkCode(HttpServletResponse response, HttpSession session) {
        try {
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
            String code = lineCaptcha.getCode();
            session.setAttribute("captcha", code);
            response.setContentType("image/png");
            IoUtil.write(response.getOutputStream(), true, lineCaptcha.getImageBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
