package com.simtop.dao;

import com.simtop.entity.SystemPojo;

import java.util.List;

public interface SystemPojoDao {
    int add(SystemPojo systemPojo);

    List<SystemPojo> selectAll();
}
