package com.simtop.dao;

import com.simtop.BaseTest;
import com.simtop.entity.Count;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountDaoTest  extends BaseTest {

    @Autowired
    private CountDao countDao;

    @Test
    public void testFindAll(){
        List<Count> list = countDao.findAll();
        System.out.println(list);
    }
}
