package com.simtop.service.impl;

import com.simtop.dao.PatternTypeDao;
import com.simtop.entity.PatternType;
import com.simtop.service.PatternTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatternTypeServiceImpl implements PatternTypeService {

    @Autowired
    private PatternTypeDao patternTypeDao;

    @Override
    public List<PatternType> findAll() {
        return patternTypeDao.selectAll();
    }

    @Override
    public int add(PatternType patternType) {
        return patternTypeDao.insert(patternType);
    }

    @Override
    public PatternType findById(Integer id) {
        return patternTypeDao.selectById(id);
    }

    @Override
    public int modify(PatternType patternType) {
        return patternTypeDao.update(patternType);
    }

    @Override
    public int removeById(Integer id) {
        return patternTypeDao.deleteById(id);
    }
}
