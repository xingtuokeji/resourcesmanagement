package com.simtop.dao;


import com.simtop.entity.Image;
import com.simtop.vo.ImageParamsVo;

import java.util.List;

public interface ImageDao {

    int insert(Image image);

    List<Image> selectAll();

    Image selectById(int id);

    String selectImgUrlById(int id);

    int update(Image image);

    int removeById(int id);

    List<Image> selectByParams(ImageParamsVo params);
}
