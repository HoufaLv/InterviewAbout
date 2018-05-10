package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    // TODO: 2018/5/10 0010 使用mybatis框架从数据库中查出movie表中的数据传到表现层
    // TODO: 2018/5/10 0010 记录日志

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public Movie viewMovieById(@PathVariable Integer id){
        Movie movie = movieService.selectById(id);
        logger.info("查询 movie 对象 {} ",movie);

        return movie;
    }
}
