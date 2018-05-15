package com.example.demo.service.impl;

import com.example.demo.cache.RedisCacheHelper;
import com.example.demo.entity.Movie;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.service.MovieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private RedisCacheHelper redisCacheHelper;

    private Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    /**
     * 根据id查询对应的Movie 对象
     *
     * @param id
     * @return
     */
    @Override
    public Movie selectById(Integer id) {
        String movieKey = "movie:" + id;

        Movie movie = (Movie) redisCacheHelper.get(movieKey, Movie.class);
        if (movie==null){
            movie = movieMapper.selectById(id);

            //将从数据库中查出来的对象放入到redis 中做缓存
            redisCacheHelper.set(movieKey,movie);
            logger.info("从DB中查询数据: {}",movie);
        }

        return movie;
    }

    /**
     * 根据页号查出所有数据(PageInfo 对象)
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Movie> selectAllByPageNo(Integer pageNo) {
        //每页十条数据
        PageHelper.startPage(pageNo,10);
        //pageInfo 内部会自动分页处理
        PageInfo<Movie> moviePageInfo = new PageInfo<>(movieMapper.selectAll());

        return moviePageInfo;
    }

    /**
     * 查询所有movie 数据
     *
     * @return
     */
    @Override
    public List<Movie> selectAll() {
        return movieMapper.selectAll();
    }
}

