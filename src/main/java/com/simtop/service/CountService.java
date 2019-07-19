package com.simtop.service;

import com.simtop.entity.Count;

import java.util.List;

public interface CountService {

    Count findByImageId(Integer imageId);

    int add(Integer imageId);

    int update(Integer imageId);

    List<Count> selectAll();
}
