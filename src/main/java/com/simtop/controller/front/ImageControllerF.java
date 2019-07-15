package com.simtop.controller.front;

import com.simtop.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/front/image")
public class ImageControllerF {

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String imageShowMain(HttpSession session, Model model){
        User u = (User) session.getAttribute("currentUser");
        model.addAttribute("user",u);
        System.out.println("访问前段素材页面的请求已经到达后台！");
        return "front";
    }


}
