package com.example.bruteforcedemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crack")
public class AppController {

    @RequestMapping("")
    public String goToIndex(){
        return "redirect:http://localhost:8081";
    }

}
