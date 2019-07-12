package com.simtop.service;

import com.simtop.BaseTest;
import com.simtop.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setName("测试");
        user.setLoginName("test");
        user.setLoginPassword("123");
        int effectNum = userService.insertUser(user);
        if(effectNum != -1){
            System.out.println("新增用户数据成功！");
        }else{
            System.out.println("新增用户数据失败！");
        }
    }

    @Test
    public void testModifyUser(){
        User user = new User();
        user.setId(4);
        user.setLoginName("test1");
        user.setLoginPassword("test2");
        int effectNum = userService.modifyUser(user);
        if(effectNum != -1){
            System.out.println("修改用户数据成功!");
        }else{
            System.out.println("修改用户数据失败！");
        }
    }

    @Test
    public void testDeleteByName(){
        String name = "";
        int effectNum = userService.deleteByName(name);
        if(effectNum != -1){
            System.out.println("根据name删除用户数据成功！");
        }else{
            System.out.println("根据name删除用户数据失败！");
        }
    }

    @Test
    public void testDeleteById(){
        int effectNum = userService.deleteById(6);
        if(effectNum != -1){
            System.out.println("根据id删除用户成功！");
        }else{
            System.out.println("根据id删除用户失败！");
        }
    }

    @Test
    public  void testSelectByName(){
        String name = "周杰";
        User user = userService.selectByName(name);
        if(user == null){
            System.out.println("该用户不存在！");
        }else{
            System.out.println("根据name查询用户成功！"+user);
        }
    }

    @Test
    public void testSelectUsers(){
        List<User> userList = userService.selectUsers();
        System.out.println(userList);
    }
}
