package com.example.demo.controller;

import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestJdbcController {

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/insertStudent")
    public String insertStudent(){
        studentDao.insertStudent("olio",18,"NewYork");
        return "user/index";
    }

}
