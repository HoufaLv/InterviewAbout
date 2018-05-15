package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    // TODO: 2018/5/10 0010 使用mybatis框架从数据库中查出movie表中的数据传到表现层
    // TODO: 2018/5/10 0010 在servicImpl中记录日志
    // TODO: 2018/5/11 0011 使用PgaeHelper简单做一下分页

    @Autowired
    private MovieService movieService;
    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/{id}")
    public Movie viewMovieById(@PathVariable Integer id){
        Movie movie = movieService.selectById(id);
        return movie;
    }

    @GetMapping("/")
    public PageInfo<Movie> viewAllMovieByPage(@RequestParam(required = false,defaultValue = "1",name = "p")Integer pageNo){
        PageInfo<Movie> pageInfo = movieService.selectAllByPageNo(pageNo);
        logger.info("查询分页数据,数据总量为:{}",pageInfo.getList().size());
        return pageInfo;
    }

    @GetMapping("/all")
    public List<Movie> viewAllMovie(){
        List<Movie> movieList = movieService.selectAll();
        return movieList;
    }
}
