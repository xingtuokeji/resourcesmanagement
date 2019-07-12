package com.simtop.service;

import com.simtop.entity.ImageType;

import java.util.List;

public interface ImageTypeService {

    //查询所有的素材分类名称
    List<ImageType> findAll();

    int insertImageType(ImageType imageType);

    ImageType selectById(Integer id);

    int modifyImageType(ImageType imageType);

    int deleteById(int id);
}
