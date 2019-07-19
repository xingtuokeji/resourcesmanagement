package com.simtop.service.impl;

import com.simtop.dao.CountDao;
import com.simtop.entity.Count;
import com.simtop.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private CountDao countDao;

    @Override
    public Count findByImageId(Integer imageId) {
        return countDao.selectByImageId(imageId);
    }

    @Override
    public int add(Integer imageId) {
        return countDao.insert(imageId);
    }

    @Override
    public int update(Integer imageId) {
        return countDao.updateByImageId(imageId);
    }

    @Override
    public List<Count> selectAll() {
        return countDao.findAll();
    }
}
