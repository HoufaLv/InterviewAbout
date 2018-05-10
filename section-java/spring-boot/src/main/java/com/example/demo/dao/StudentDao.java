package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertStudent(String stuName,int stuAge,String stuAddress){
        String sql = "insert into t_student (stuname,stuage,stuaddress) values(?,?,?)";
        jdbcTemplate.update(sql,stuName,stuAge,stuAddress);
    }
}
