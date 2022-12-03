package com.example.datahubwebsite.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class PageCntroller {

    @GetMapping(value = "")
    public String mainPage(Model model){


        return "html/index";
    }
}
