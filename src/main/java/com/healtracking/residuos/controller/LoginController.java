package com.healtracking.residuos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String exibirLogin() {
        return "login"; // Vai buscar o arquivo login.html
    }
}
