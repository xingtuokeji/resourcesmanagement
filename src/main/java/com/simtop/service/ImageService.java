package com.simtop.service;

import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    int add(ImageDto productDto) throws IOException;

    List<Image> findAll();
}
