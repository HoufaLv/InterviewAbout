package com.example.demo.service.impl;

import com.example.demo.entity.Movie;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    /**
     * 根据id查询对应的Movie 对象
     *
     * @param id
     * @return
     */
    @Override
    public Movie selectById(Integer id) {
        return movieMapper.selectById(id);
    }
}
