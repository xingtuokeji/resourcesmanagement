package com.simtop.service;

import com.simtop.entity.Pattern;
import com.simtop.vo.PatternParamsVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface PatternService {
    List<Pattern> findAll();


    Pattern findById(Integer id);

    String findPatternUrlById(int id);

    void getPattern(String absolutePath, OutputStream outputStream) throws IOException;

//    int modify(PatternDto patternDto) throws IOException;

    int removeById(Integer id);

    List<Pattern> findByParams(PatternParamsVo params);

    int add(Pattern pattern, MultipartFile[] files) throws IOException;

    int modify(Pattern pattern, MultipartFile[] files) throws IOException;
}
