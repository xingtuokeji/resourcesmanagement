package com.simtop.service;

import com.simtop.entity.User;

import java.util.List;

public interface UserService {

    //新增用户
    int insertUser(User user);

    //更新用户
    int modifyUser(User user);

    //根据id删除用户
    int deleteById(int id);

    //根据name删除用户
    int deleteByName(String name);

    //根据name查询用户数据
    User selectByName(String name);

    //查询所有用户数据
    List<User> selectUsers();

    User login(User user);

    User selectById(Integer id);

    int updateEnableStatusById(User user);

    User selectByLoginName(String loginName);

    int modifyPwdById(Integer id, String loginPassword);
}
