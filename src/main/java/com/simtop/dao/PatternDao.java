package com.simtop.dao;

import com.simtop.entity.Pattern;
import com.simtop.vo.PatternParamsVo;

import java.util.List;

public interface PatternDao {

    List<Pattern> selectAll();

    int add(Pattern pattern);

    Pattern selectById(Integer id);

    String selectPatternUrlById(int id);

    int updateSome(Pattern pattern);

    int deleteById(Integer id);

    List<Pattern> selectByParams(PatternParamsVo params);

}
