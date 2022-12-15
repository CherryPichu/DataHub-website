package com.example.datahubwebsite.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class PageCntroller {

    @GetMapping(value = "")
    public String mainPage(Model model){


        return "html/index";
    }

    @GetMapping(value = "/test")
    public String testnPage(Model model){


        return "html/test";
    }


}
