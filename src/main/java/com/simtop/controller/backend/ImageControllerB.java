package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageService;
import com.simtop.service.ImageTypeService;
import com.simtop.util.PathUtil;
import com.simtop.vo.ImageVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 系统默认加载出页面所需要的所有数据库
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String imageBackMain(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Image> images = imageService.findAll();
        PageInfo<Image> pageInfo = new PageInfo<Image>(images);
        //System.out.println("测试findAll方法返回的一条数据库数据为："+pageInfo.getList().get(2));
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

    /**
     * 根据前台传递的素材id返回一条查询数据
     */

    @RequestMapping("/findById")
    @ResponseBody
    public Map<String,Object> findById(int id){
        System.out.println("前台传递的id为："+id);
        Map<String,Object> mapModel = new HashMap<String,Object>();
        try {
            Image image = imageService.findById(id);
            System.out.println(image);
            if(image != null){
                mapModel.put("success",true);
                mapModel.put("data",image);
            }else{
                mapModel.put("success",false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mapModel;
    }

    //修改时加载前台的素材图片
    @RequestMapping("/getImage")
    public void getImage(int id, OutputStream outputStream) {
        String path = imageService.findImageUrlById(id);
        String basePath = PathUtil.getImageBasePath();
        String absolutePath = basePath +path;
        imageService.getImage(absolutePath, outputStream);
    }

    @RequestMapping("/modify")
    //修改完成之后，转发至findAll
    public String modify(ImageVo imageVo, HttpSession session, Model model, Integer pageNum){
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
            int effectNum = imageService.modify(productDto);
            if(effectNum != -1){
                model.addAttribute("successMsg","修改素材成功");
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg",e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public Map<String,Object> deleteById(int id){
        System.out.println("前台传给后台需要删除的素材编号为："+id);
        Map<String,Object> mapModel = new HashMap<String, Object>();
        try{
            int effectNum = imageService.deleteById(id);
            if(effectNum != -1){
                mapModel.put("success",true);
                System.out.println("删除数据成功！");
            }else{
                mapModel.put("success",false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapModel;
    }
}
