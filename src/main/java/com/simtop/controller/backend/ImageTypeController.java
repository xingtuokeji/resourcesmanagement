package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 素材（图片类型管理）
 */
@Controller
@RequestMapping("/imageType")
public class ImageTypeController {

    @Autowired
    private ImageTypeService imageTypeService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String findAll(Integer pageNum,HttpServletRequest request, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        //查询出image_type数据表中的素材类型数据 todo
        List<ImageType> imageTypeList = imageTypeService.findAll();
        PageInfo<ImageType> pageInfo = new PageInfo<ImageType>(imageTypeList);
        model.addAttribute("pageInfo",pageInfo);
        //返回给imagemanage.jsp页面 todo
        return "imagetypemanage";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addImageType(ImageType imageType,Integer pageNum) {
        System.out.println(imageType);
        Map<String, Object> map = new HashMap<>();
        int effectNum = imageTypeService.insertImageType(imageType);
        if (effectNum != -1) {
            System.out.println("添加素材分类成功！");
            map.put("success", true);
            map.put("state", 0);
        } else {
            map.put("success", false);
            map.put("errMsg", "添加素材分类失败");
        }
        return map;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findById(Integer id){
        System.out.println("素材模块传递过来的[findById]id为"+id);
        Map<String,Object> map = new HashMap<>();
        ImageType imageType = imageTypeService.selectById(id);
        System.out.println(imageType);
        if(imageType != null){
            map.put("success",true);
            map.put("data",imageType);
        }
        return map;
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modify(ImageType imageType){
        System.out.println(imageType);
        Map<String,Object> map = new HashMap<>();
        int effectNum = imageTypeService.modifyImageType(imageType);
        if(effectNum != -1){
            map.put("success",true);
            System.out.println("修改素材数据成功");
        }
        return map;
    }

    @RequestMapping(value = "/removeById",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> removeById(Integer id){
        Map<String,Object> map = new HashMap<>();
        System.out.println("接收到的前段传递过来的id为："+id);
        int effectNum = imageTypeService.deleteById(id);
        if(effectNum != -1){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errMsg","删除素材名称失败！");
        }
        return map;
    }
}
