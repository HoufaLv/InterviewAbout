package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class HelloController {

    /**
     * 前往1.html 页面
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String method(Model model){


        int[] numbers = new int[]{1, 2, 3, 4};

        model.addAttribute("name","oli");
        model.addAttribute("numbers",Arrays.asList(numbers));
        model.addAttribute("id",1);

        return "views/1";
    }

    @GetMapping("/testThymeleaf")
    public String method(){
        return "user/index";
    }
}
