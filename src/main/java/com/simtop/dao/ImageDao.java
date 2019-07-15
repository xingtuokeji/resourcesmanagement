package com.simtop.dao;


import com.simtop.entity.Image;

import java.util.List;

public interface ImageDao {

    int insert(Image image);

    List<Image> selectAll();

}
