package com.example.demo.mapper;

import com.example.demo.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {

    Movie selectById(Integer id);
}
