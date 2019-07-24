package com.simtop.service;

import com.simtop.entity.Count;

import java.util.List;

public interface EchartsService {

    List<Count> selectByTopThree();
}
