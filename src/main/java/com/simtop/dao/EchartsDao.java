package com.simtop.dao;

import com.simtop.entity.Count;

import java.util.List;

public interface EchartsDao {

    List<Count> findByTopThree();
}
