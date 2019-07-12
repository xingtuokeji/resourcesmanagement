package com.simtop.dao;

import com.simtop.entity.ImageType;

import java.util.List;

public interface ImageTypeDao {

    List<ImageType> selectImageTypes();

    int insertImageType(ImageType imageType);

    ImageType selectByImageTypeId(Integer id);

    int updateImageType(ImageType imageType);

    int deleteByImageTypeId(int id);
}
