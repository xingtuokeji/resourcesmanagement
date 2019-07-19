package com.simtop.service;

import com.simtop.entity.SystemPojo;

import java.util.List;

public interface SystemPojoService {
    List<SystemPojo> findAll();

    int insert(SystemPojo systemPojo);
}
