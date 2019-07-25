package com.simtop.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.Pattern;
import com.simtop.entity.PatternType;
import com.simtop.entity.User;
import com.simtop.service.PatternService;
import com.simtop.service.PatternTypeService;
import com.simtop.vo.PatternParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 前台页面对应的模型控制层
 */

@Controller
@RequestMapping("/front/pattern")
public class PatternControllerF {

    @Autowired
    private PatternService patternService;

    @Autowired
    private PatternTypeService patternTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model, HttpSession session){
        //如果当前用户未登录 则重定向至404页面    //如果session中的user对向为空则报错
        if((User)session.getAttribute("currentUser")== null){
            return "redirect:/error/nopriority.jsp";
        }
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,8);
        List<Pattern> patternList = patternService.findAll();
        PageInfo<Pattern> pageInfo = new PageInfo<Pattern>(patternList);
        model.addAttribute("pageInfo",pageInfo);
        return "pattern";
    }

    //加载页面中的模型类别信息
    @ModelAttribute("patternTypes")
    public List<PatternType> loadFrontPatternTypes(){
        List<PatternType> patternTypes = patternTypeService.findAll();
        return patternTypes;
    }

    //前台页面查询功能 根据模型名称和模型分类
    @RequestMapping(value = "/findByParams")
    public String findByParams(HttpSession session, Model model, Integer pageNum, PatternParamsVo params){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,8);
        //根据参数查询
        try{
            List<Pattern> patterns = patternService.findByParams(params);
            PageInfo<Pattern> pageInfo = new PageInfo<Pattern>(patterns);
            model.addAttribute("pageInfo",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pattern";
    }

    @RequestMapping("/detail")
    public String patternDetail(Integer id,Model model){
        //根据前台传入的模型id，查询出模型信息，转发至详情页取值
        System.out.println("接收到前段请求"+id);
        Pattern pattern = patternService.findById(id);
        model.addAttribute("pattern",pattern);
        return "detail";
    }

}
