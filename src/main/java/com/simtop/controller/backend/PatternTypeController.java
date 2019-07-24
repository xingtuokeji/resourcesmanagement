package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.PatternType;
import com.simtop.service.PatternTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/patternType")
public class PatternTypeController {

    @Autowired
    private PatternTypeService patternTypeService;

    @RequestMapping("/findAll")
    public String showPatternUpload(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<PatternType> patternTypeList = patternTypeService.findAll();
        PageInfo<PatternType> pageInfo = new PageInfo<PatternType>(patternTypeList);
        model.addAttribute("pageInfo",pageInfo);
        return "patterntypemanage";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(PatternType patternType){
        System.out.println("前台传入的参数为："+patternType);
        Map<String,Object> map = new HashMap<>();
        int effectNum = patternTypeService.add(patternType);
        if(effectNum != -1){
            map.put("success",true);
            map.put("successMsg","添加数据成功！");
        }else{
            map.put("success",false);
            map.put("errorMsg","添加数据失败！");
        }
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        PatternType patternType = patternTypeService.findById(id);
        if(patternType != null){
            map.put("success",true);
            map.put("data",patternType);
        }else{
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Map<String,Object> modify(PatternType patternType){
        Map<String,Object> map = new HashMap<>();
        int effectNum = patternTypeService.modify(patternType);
        if(effectNum != -1){
            map.put("success",true);
            map.put("successMsg","修改数据成功！");
        }else{
            map.put("success",false);
            map.put("errorMsg","修改数据失败！");
        }
        return map;
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public Map<String,Object> removeById(Integer id){
        Map<String,Object> map = new HashMap<>();
        int effectNum = patternTypeService.removeById(id);
        if(effectNum != -1){
            map.put("success",true);
            map.put("successMsg","删除数据成功！");
        }else{
            map.put("success",false);
            map.put("errorMsg","删除数据失败！");
        }
        return map;
    }
}
