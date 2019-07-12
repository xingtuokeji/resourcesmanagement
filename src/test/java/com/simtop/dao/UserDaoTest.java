package com.simtop.dao;

import com.simtop.BaseTest;
import com.simtop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoTest extends BaseTest {

        @Autowired
        private UserDao userDao;

        @Test
        //测试添加用户数据
        public void testAddUser(){
            User user = new User();
            user.setLoginName("admin");
            user.setLoginPassword("123456");
            user.setName("管理员");
            int effectNum = userDao.addUser(user);
            if(effectNum != -1){
                System.out.println("新增用户信息成功！");
            }else {
                System.out.println("新增用户信息失败！");
            }
        }

        @Test
        //测试根据用户id删除用户数据
        public void testDeleteById(){
            int effectNum = userDao.deleteById(2);
            if(effectNum != -1){
                System.out.println("删除数据成功！");
            }else{
                System.out.println("删除数据失败！");
            }
        }

        @Test
        //测试更新用户数据
        public void testUpdateUser(){
            User user = new User();
            user.setId(1);
            user.setLoginName("admin");
            user.setLoginPassword("123");
            int effectNum = userDao.updateUser(user);
            if(effectNum != -1){
                System.out.println("更新数据成功！");
            }else{
                System.out.println("更新数据失败！");
            }
        }

        @Test
        //测试根据用户姓名查询数据
        public void testSelectByName(){
            User user = userDao.selectByName("管理员");
            if(user == null){
                System.out.println("该用户不存在");
            }else{
                System.out.println("查询成功"+user);
            }
        }

        @Test
        //测试查询用户模块所有的数据
        public void testSelectUsers(){
            List<User> userList = userDao.selectUsers();
            System.out.println(userList);
        }

        @Test
        //测试根据用户姓名删除用户数据
        public void testDeleteByName(){
            String name = "测试";
            int effectNum = userDao.deleteByName(name);
            if(effectNum != -1){
                System.out.println("删除用户信息成功！");
            }else {
                System.out.println("删除用户信息失败！");
            }
        }


}
