package com.simtop.dao;

import com.simtop.entity.FilePojo;

import java.util.List;

public interface FileDao {

    int insert(FilePojo file);

    List<FilePojo> selectAll();

    FilePojo findById(int id);

    int update(FilePojo file);

}
