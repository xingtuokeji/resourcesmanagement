package com.simtop.service;

import com.simtop.dto.FileDto;
import com.simtop.entity.FilePojo;

import java.util.List;

public interface FileService {
    List<FilePojo> findAll();

    int add(FileDto fileDto);

    FilePojo selectById(int id);

    int modify(FileDto fileDto);
}
