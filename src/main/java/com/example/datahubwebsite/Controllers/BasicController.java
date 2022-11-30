package com.example.datahubwebsite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/api/login")
    public String hello1() {
        return "hello";
    }

    @GetMapping("/api/logout")
    public String hello2(@RequestParam String param) {
        return param;
    }



}
