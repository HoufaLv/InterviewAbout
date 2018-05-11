package com.example.demo.service.impl;

import com.example.demo.cache.RedisCacheHelper;
import com.example.demo.entity.Movie;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
