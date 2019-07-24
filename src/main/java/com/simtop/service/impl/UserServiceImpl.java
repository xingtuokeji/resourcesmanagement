package com.simtop.service.impl;

import com.simtop.dao.UserDao;
import com.simtop.entity.User;
import com.simtop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public int deleteByName(String name) {
        return userDao.deleteByName(name);
    }

    @Override
    public User selectByName(String name) {
        return userDao.selectByName(name);
    }

    @Override
    public List<User> selectUsers() {
        return userDao.selectUsers();
    }

    //登陆业务层
    @Override
    public User login(User user) {
        String loginName = user.getLoginName();
        String loginPassword = user.getLoginPassword();
        return userDao.selectByLoginNameAndLoginPassword(loginName,loginPassword);
    }

    @Override
    public User selectById(Integer id) {
        User user = userDao.selectById(id);
        if(user != null){
            return user;
        }
        return null;
    }

    @Override
    public int updateEnableStatusById(User user) {
        int enableStatus = user.getEnableStatus();
        System.out.println("最初的状态值为："+enableStatus);
        if(enableStatus == 1){
            enableStatus = 2;
        }else{
            enableStatus = 1;
        }
        user.setEnableStatus(enableStatus);
        System.out.println("变化后的状态值为："+user.getEnableStatus());
        return userDao.updateStatusById(user);
    }

    @Override
    public User selectByLoginName(String loginName) {
        return userDao.selectByLoginName(loginName);
    }
}
