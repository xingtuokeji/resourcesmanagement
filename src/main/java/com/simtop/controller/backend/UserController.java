package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.SystemPojo;
import com.simtop.entity.User;
import com.simtop.service.SystemPojoService;
import com.simtop.service.UserService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块（权限、登陆、CRUD）
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    /**
     * {"data":{"id":1,"loginName":"admin","loginPassword":"123","name":"管理员"},"success":true}
     */

    @Autowired
    private UserService userService;

    @Autowired
    private SystemPojoService systemPojoService;
    /**
     * 如果roleId=1表示超级管理员，roleId=2表示普通用户
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session,HttpServletRequest request){
        // 1、根据loginName和loginPassword进行查询，如果查询成功，将数据保存在session中，否则返回失败信息
        try{
            System.out.println(user);
            User u = userService.login(user);
            if(u!=null){
                session.setAttribute("currentUser",u);
                System.out.println("登陆成功！");
            }
            //当用户登录成功后，获取IP、User_Agents插入到tb_system表 todo
            String ip = request.getRemoteAddr();
            System.out.println("获取到的客户端IP地址为："+ip);
            String agent = request.getHeader("User-Agent");
            System.out.println(agent);
            //解析agent字符串
            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            Browser browser = userAgent.getBrowser();
            //获取操作系统对象
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
//            System.out.println("浏览器名:"+browser.getName());
//            System.out.println("操作系统名:"+operatingSystem.getName());
            String userAgents = operatingSystem.getName()+" "+browser.getName();
            SystemPojo systemPojo = new SystemPojo();
            systemPojo.setIp(ip);
            systemPojo.setUserAgent(userAgents);
            int effectNum = systemPojoService.insert(systemPojo);
            if(effectNum == -1){
                throw new RuntimeException("插入系统登录信息数据失败！");
            }else{
                System.out.println("插入姓名为："+user.getName()+"的用户系统信息成功！");
            }
        }catch (Exception e){
          throw new RuntimeException("插入系统登录信息数据失败！!");
        }
        return "main";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user != null){
            session.removeAttribute("currentUser");
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAllUser(Integer pageNum,HttpServletRequest request){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.selectUsers();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        request.setAttribute("pageInfo",pageInfo);
        return "usermanage";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(User user){
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
        int effectNum = userService.insertUser(user);
        if(effectNum != -1){
            System.out.println("添加用户成功！");
            map.put("success",true);
            map.put("state",0);
        }else{
            map.put("success",false);
            map.put("errMsg","添加用户失败");
        }
        return map;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        User user = userService.selectById(id);
        if(user != null){
            map.put("success",true);
            map.put("data",user);
        }
        return map;
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modify(User user){
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
        int effectNum = userService.modifyUser(user);
        if(effectNum != -1){
            map.put("success",true);
            System.out.println("修改用户数据成功");
        }
        return map;
    }

    @RequestMapping(value = "/removeById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> removeById(Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        int effectNum = userService.deleteById(id);
        if(effectNum != -1){
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping(value = "/modifyStatus",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> modifyStatus(Integer id){
        System.out.println("前台传递过来的id为："+id);
        User user = userService.selectById(id);
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
        int effectNum = userService.updateEnableStatusById(user);
        if(effectNum != -1){
            map.put("success",true);
            System.out.println("根据id更新状态成功！");
        }
        return map;

    }

}
