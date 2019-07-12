package com.simtop.service.impl;

import com.simtop.dao.ImageTypeDao;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageTypeServiceImpl implements ImageTypeService {

    @Autowired
    private ImageTypeDao imageTypeDao;

    @Override
    public List<ImageType> findAll() {
        return imageTypeDao.selectImageTypes();
    }

    @Override
    public int insertImageType(ImageType imageType) {
        return imageTypeDao.insertImageType(imageType);
    }

    @Override
    public ImageType selectById(Integer id) {
        return imageTypeDao.selectByImageTypeId(id);
    }

    @Override
    public int modifyImageType(ImageType imageType) {
        //前段传递的是id和imageTypeName
        return imageTypeDao.updateImageType(imageType);
    }

    @Override
    public int deleteById(int id) {
        return imageTypeDao.deleteByImageTypeId(id);
    }
}
