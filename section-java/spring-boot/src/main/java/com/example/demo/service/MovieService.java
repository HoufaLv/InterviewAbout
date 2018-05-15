package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MovieService {

    /**
     * 根据id查询对应的Movie 对象
     * @return
     * @param id
     */
    Movie selectById(Integer id);

    /**
     * 根据页号查出所有数据(PageInfo 对象)
     * @param pageNo
     * @return
     */
    PageInfo<Movie> selectAllByPageNo(Integer pageNo);

    /**
     * 查询所有movie 数据
     * @return
     */
    List<Movie> selectAll();
}
