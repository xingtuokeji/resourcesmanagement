package com.simtop.service;

import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;
import com.simtop.vo.ImageParamsVo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ImageService {

    int add(ImageDto productDto) throws IOException;

    List<Image> findAll();

    Image findById(int id);

    void getImage(String path, OutputStream outputStream);

    String findImageUrlById(int id);

    int modify(ImageDto productDto);

    int deleteById(int id);

    List<Image> findByParams(ImageParamsVo params);

}
