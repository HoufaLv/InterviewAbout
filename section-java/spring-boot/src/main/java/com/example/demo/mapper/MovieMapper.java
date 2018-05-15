package com.example.demo.mapper;

import com.example.demo.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {

    Movie selectById(Integer id);

    List<Movie> selectAll();
}
