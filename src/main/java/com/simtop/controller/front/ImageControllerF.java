package com.simtop.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.Image;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageService;
import com.simtop.service.ImageTypeService;
import com.simtop.vo.ImageParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/front/image")
public class ImageControllerF {

    @Autowired
    private ImageService imageService;

    //我忘记写这个标签了，导致NullPointerException todo
    @Autowired
    private ImageTypeService imageTypeService;

    // todo前段页面加载imageType类型
    @ModelAttribute("imageTypes")
    public List<ImageType> loadImageTypes(){
        List<ImageType> imageTypes = imageTypeService.findAll();
        return imageTypes;
    }

    /**
     * 封装前台查询页面查询参数 类ImageParamVo
     * @param session
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public String imageShowMain(HttpSession session, Model model, Integer pageNum){
        System.out.println("访问前段素材页面的请求已经到达后台！");
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,20);
        //根据参数查询
        try{
            List<Image> images = imageService.findAll();
            PageInfo<Image> pageInfo = new PageInfo<Image>(images);
            model.addAttribute("pageInfo",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "front";
    }

    @RequestMapping(value = "/findByParams")
    public String findByParams(HttpSession session, Model model, Integer pageNum, ImageParamsVo params){
        System.out.println("访问前段素材页面的请求已经到达后台！");
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,20);
        //根据参数查询
        try{
            List<Image> images = imageService.findByParams(params);
            PageInfo<Image> pageInfo = new PageInfo<Image>(images);
            model.addAttribute("pageInfo",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "front";
    }



}
