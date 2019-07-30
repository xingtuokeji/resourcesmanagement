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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块（权限、登陆、CRUD）
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login/index")
    public String mainIndex(){
        return "index";
    }
    /**
     * {"data":{"id":1,"loginName":"admin","loginPassword":"123","name":"管理员"},"success":true}
     */

    @Autowired
    private UserService userService;

    @Autowired
    private SystemPojoService systemPojoService;

    /**
     * 如果roleId=1表示超级管理员，roleId=2表示普通用户
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, HttpServletRequest request) {
        logger.debug("========接收到登陆请求=========");
        Long startTime = System.currentTimeMillis();
        logger.info("当前系统时间"+startTime);
        //账号不能为空
        if (user.getLoginName().equals("")) {
            session.setAttribute("errMsg1", "登陆账号不能为空");
            return "index";
        }
        //账号错误
        User u = userService.selectByLoginName(user.getLoginName());
        if (u == null) {
            session.setAttribute("errorMsg1", "登陆账号错误");
            return "index";
        }
        // 密码不能为空
        if (user.getLoginPassword().equals("")) {
            session.setAttribute("errorMsg1", "登陆密码不能为空");
            return "index";
        }
        //密码错误
        if (!u.getLoginPassword().equals(user.getLoginPassword())) {
            session.setAttribute("errorMsg1", "登陆密码错误");
            return "index";
        } else {
            //todo 禁用状态下的用户登录不了系统 查询出用户的enableStatus
            if(u.getEnableStatus() == 2){
                session.setAttribute("errorMsg1","该账户已经禁用！");
                return "index";
            }
            session.setAttribute("currentUser", u);
            //当用户登录成功后，获取IP、User_Agents插入到tb_system表 todo
            String ip = request.getRemoteAddr();
            String agent = request.getHeader("User-Agent");
            //解析agent字符串
            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            Browser browser = userAgent.getBrowser();
            //获取操作系统对象
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            String userAgents = operatingSystem.getName() + " " + browser.getName();
            SystemPojo systemPojo = new SystemPojo();
            systemPojo.setIp(ip);
            systemPojo.setUserAgent(userAgents);
            int effectNum = systemPojoService.insert(systemPojo);
            if (effectNum == -1) {
                return "index";
            }
            Long endTime = System.currentTimeMillis();
            logger.info("登陆请求花费的时间为："+(endTime-startTime));
            logger.debug("=============登陆请求结束================");
            return "main";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            session.removeAttribute("currentUser");
        }
        //重定向路径的写法/相当于是项目名称
        return "redirect:/user/login/index";
    }

    @RequestMapping(value = "/findAll")
    public String findAllUser(Integer pageNum, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.selectUsers();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        request.setAttribute("pageInfo", pageInfo);
        return "usermanage";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(User user,Model model){
        //todo 解决后台接受前端post请求中文参数乱码问题 2019年7月30日11:38:15
        // 1、页面编码
        System.out.println("接收到前端添加用户的请求:"+user);
        int effectNum = userService.insertUser(user);
        if (effectNum != -1) {
           model.addAttribute("successMsg","添加用户成功");
        } else {
            model.addAttribute("errorMsg", "添加用户失败");
        }
        return "forward:findAll";
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectById(id);
        if (user != null) {
            map.put("success", true);
            map.put("data", user);
        }
        return map;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify(User user) {
        System.out.println("接收到的前台请求参数为："+user);
        Map<String, Object> map = new HashMap<>();
        int effectNum = userService.modifyUser(user);
        if (effectNum != -1) {
            map.put("success", true);
            map.put("successMsg","修改用户信息成功！");
        }
        return map;
    }

    @RequestMapping(value = "/removeById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> removeById(Integer id) {
        System.out.println(id);
        Map<String, Object> map = new HashMap<>();
        int effectNum = userService.deleteById(id);
        if (effectNum != -1) {
            map.put("success", true);
            map.put("successMsg","删除用户信息成功！");
        }
        return map;
    }

    @RequestMapping(value = "/modifyStatus", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> modifyStatus(Integer id) {
        System.out.println("前台传递过来的id为：" + id);
        User user = userService.selectById(id);
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        int effectNum = userService.updateEnableStatusById(user);
        if (effectNum != -1) {
            map.put("success", true);
            System.out.println("根据id更新状态成功！");
        }
        return map;
    }

    @RequestMapping("/findCurrent")
    //需要从数据库中取出user数据
    public String personInfo(Model model,HttpSession session){
        User user = (User)session.getAttribute("currentUser");
        if(user!=null){
           Integer id = user.getId();
           user = userService.selectById(id);
           model.addAttribute("user",user);
        }
        return "personinfo";
    }

    @RequestMapping("/modifyPwd")
    @ResponseBody
    //根据用户id修改用户密码
    public Map<String,Object> modifyPwdById(Integer id,String loginPassword){
        Map<String,Object> map = new HashMap<>();
        int effectNum = userService.modifyPwdById(id,loginPassword);
        if(effectNum != -1){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }

}
