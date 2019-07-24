package com.simtop.dao;

import com.simtop.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {

    //增加用户
    int addUser(User user);

    //根据id删除用户
    int deleteById(int id);

    //修改用户
    int updateUser(User user);

    //查询所有的用户信息
    List<User> selectUsers();

    //根据用户姓名查询用户信息
    User selectByName(String name);

    //根据用户姓名删除用户
    int deleteByName(String name);

    User selectByLoginNameAndLoginPassword(@Param("loginName")String loginName, @Param("loginPassword") String loginPassword);

    User selectById(Integer id);

    User selectByLoginName(String loginName);

    int updateStatusById(User user);
}
