package com.simtop.service;

import com.simtop.dto.PatternDto;
import com.simtop.entity.Pattern;
import com.simtop.vo.PatternParamsVo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface PatternService {
    List<Pattern> findAll();

    int add(PatternDto patternDto) throws IOException;

    Pattern findById(Integer id);

    String findPatternUrlById(int id);

    void getPattern(String absolutePath, OutputStream outputStream) throws IOException;

    int modify(PatternDto patternDto) throws IOException;

    int removeById(Integer id);

    List<Pattern> findByParams(PatternParamsVo params);
}
