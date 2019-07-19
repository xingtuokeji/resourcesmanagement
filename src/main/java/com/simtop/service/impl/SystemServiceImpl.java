package com.simtop.service.impl;

import com.simtop.dao.SystemPojoDao;
import com.simtop.entity.SystemPojo;
import com.simtop.service.SystemPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemPojoService {

    @Autowired
    private SystemPojoDao systemPojoDao;

    @Override
    public List<SystemPojo> findAll() {
        return systemPojoDao.selectAll();
    }

    @Override
    public int insert(SystemPojo systemPojo) {
        return systemPojoDao.add(systemPojo);
    }
}
