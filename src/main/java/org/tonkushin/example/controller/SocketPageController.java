package org.tonkushin.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SocketPageController {
    @GetMapping("/socket-page")
    public String list(HttpServletRequest request, Model model) {
        return "socket-page";
    }
}
