package com.simtop.service;

import com.simtop.entity.PatternType;

import java.util.List;

public interface PatternTypeService {

    List<PatternType> findAll();

    int add(PatternType patternType);

    PatternType findById(Integer id);

    int modify(PatternType patternType);

    int removeById(Integer id);
}
