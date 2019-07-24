package com.simtop.service.impl;

import com.simtop.dao.EchartsDao;
import com.simtop.entity.Count;
import com.simtop.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsDao echartsDao;

    @Override
    public List<Count> selectByTopThree() {
        return echartsDao.findByTopThree();
    }
}
