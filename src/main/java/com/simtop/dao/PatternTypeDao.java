package com.simtop.dao;

import com.simtop.entity.PatternType;

import java.util.List;

public interface PatternTypeDao {
    List<PatternType> selectAll();

    int insert(PatternType patternType);

    PatternType selectById(Integer id);

    int update(PatternType patternType);

    int deleteById(Integer id);
}
