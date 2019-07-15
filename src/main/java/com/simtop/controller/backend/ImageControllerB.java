package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageService;
import com.simtop.service.ImageTypeService;
import com.simtop.vo.ImageVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 素材管理
 */
@Controller
@RequestMapping("/backend/image")
public class ImageControllerB {

    @Autowired
    private ImageTypeService imageTypeService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/findAll")
    public String imageBackMain(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Image> images = imageService.findAll();
        PageInfo<Image> pageInfo = new PageInfo<Image>(images);
        model.addAttribute("pageInfo",pageInfo);
        return "imagemanage";
    }

    /**
     * 添加和修改商品之前加载启用的素材类型
     */
    @ModelAttribute("imageTypes")
    public List<ImageType> loadProductTypes(){
        List<ImageType> imageTypes = imageTypeService.findAll();
        return imageTypes;
    }

    /**
     * 添加素材
     */
    @RequestMapping(value = "/add")
    public String addProduct(ImageVo imageVo, HttpSession session, Model model, Integer pageNum){
        //{   private Integer id;
        //    private String imageName;
        //    private Integer imageTypeId;
        //    private String imageDesc;
        //    private CommonsMultipartFile file;}
        //传递的参数有素材名称、素材所属分类、素材图片、素材描述这些字段 无法获取的原因是web层配置的CommonsMultipartResolver未指定id
        /**
         * 封装前台素材添加的相关数据vo
         */
        // todo vo <<< dto 将参数传递给service层，调用service方法进行业务处理
        try {
            System.out.println("前端请求已经到达后台！");
            ImageDto productDto = new ImageDto();
            //属性赋值的工具类PropertyUtils
            //org.apache.commons.beanutils.PropertyUtils;
            PropertyUtils.copyProperties(productDto,imageVo);
            // id、imageName、imageTypeId、imageDesc
            System.out.println("获取上传文件的文件名："+imageVo.getFile().getOriginalFilename());
            productDto.setFileName(imageVo.getFile().getOriginalFilename());
            System.out.println("获取文件上传的输入流："+imageVo.getFile().getInputStream());
            productDto.setInputStream(imageVo.getFile().getInputStream());
            // todo 文件的上传目录 important
            int effectNum = imageService.add(productDto);
            if(effectNum != -1){
                model.addAttribute("successMsg","添加素材成功");
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg",e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }

}
