package com.example.demo.service;

import com.example.demo.entity.Movie;

public interface MovieService {

    /**
     * 根据id查询对应的Movie 对象
     * @return
     * @param id
     */
    Movie selectById(Integer id);
}
