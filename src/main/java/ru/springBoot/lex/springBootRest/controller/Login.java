package ru.springBoot.lex.springBootRest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequestMapping()
public class Login {

    @GetMapping("/login")
    public String getLoginPage() { return "login"; }

    @GetMapping("/logout")
    public String getLogOutPage() { return "login";}

}
