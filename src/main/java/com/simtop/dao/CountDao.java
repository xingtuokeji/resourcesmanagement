package com.simtop.dao;

import com.simtop.entity.Count;

import java.util.List;

public interface CountDao {

    Count selectByImageId(Integer imageId);

    int insert(Integer imageId);

    int updateByImageId(Integer imageId);

    List<Count> findAll();
}
