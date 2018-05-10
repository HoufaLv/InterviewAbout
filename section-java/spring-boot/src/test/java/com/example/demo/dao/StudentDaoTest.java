package com.example.demo.dao;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void insertStudent() {
        studentDao.insertStudent("olio",18,"NewYork");
    }
}