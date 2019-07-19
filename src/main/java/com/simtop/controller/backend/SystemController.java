package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.SystemPojo;
import com.simtop.service.SystemPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend/system")
public class SystemController {

    @Autowired
    private SystemPojoService systemService;

    @RequestMapping("/findAll")
    public String showSystemPage(Integer pageNum, HttpSession session, Model model){
        /**
         * 分页查询系统相关的登陆日志
         */
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,10);
        List<SystemPojo> systemList = systemService.findAll();
        PageInfo<SystemPojo> pageInfo = new PageInfo<SystemPojo>(systemList);
        model.addAttribute("pageInfo",pageInfo);
        return "systeminfo";
    }
}
