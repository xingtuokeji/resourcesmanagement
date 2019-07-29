package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.Pattern;
import com.simtop.entity.PatternType;
import com.simtop.entity.User;
import com.simtop.service.PatternService;
import com.simtop.service.PatternTypeService;
import com.simtop.util.PathUtil;
import com.simtop.vo.PatternVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/pattern")
public class PatternController {

    @Autowired
    private PatternService patternService;

    @Autowired
    private PatternTypeService patternTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Pattern> patternList = patternService.findAll();
        PageInfo<Pattern> pageInfo = new PageInfo<Pattern>(patternList);
        model.addAttribute("pageInfo",pageInfo);
        return "patternmanage";
    }

    //加载模型类型信息
    @ModelAttribute("patternTypes")
    public List<PatternType> loadPatternTypes(){
        List<PatternType> patternTypes = patternTypeService.findAll();
        return patternTypes;
    }

    //多文件上传
    @RequestMapping("/add")
    public String add(Integer pageNum, PatternVo patternVo, @RequestParam("file") MultipartFile[] files, Model model, HttpSession session){
        System.out.println(files.length);
        //todo 获取模型作者信息
        User user = (User)session.getAttribute("currentUser");
        String patternUploader = user.getName();
        Pattern pattern = new Pattern();
        pattern.setPatternUploader(patternUploader);
        pattern.setPatternName(patternVo.getPatternName());
        pattern.setPatternDesc(patternVo.getPatternDesc());
        PatternType patternType = new PatternType();
        patternType.setId(patternVo.getPatternTypeId());
        pattern.setPatternType(patternType);
//        patternVo.setPatternUploader(user.getName());
//        //todo 多文件处理
//        List<CommonsMultipartFile> commonsMultipartFiles = patternVo.getFile();
//        PatternDto patternDto = new PatternDto();
//        patternDto.setFileName(commonsMultipartFiles);
//        patternDto.setPatternName(patternVo.getPatternName());
//        //前后台换行符\n的处理
//        String patternDesc = patternVo.getPatternDesc();
//        patternDesc = FilterLineBreak.filterLineBreak(patternDesc);
//        patternDto.setPatternDesc(patternDesc);
//        patternDto.setPatternTypeId(patternVo.getPatternTypeId());
//        patternDto.setPatternUploader(patternVo.getPatternUploader());getPatternUploader
//        //输入流的处理 todo
//        try {
//            patternDto.setInputStream(commonsMultipartFiles.);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        int effectNum = 0;
        try {
            //文件流传入service层进行处理
            effectNum = patternService.add(pattern,files);
            // todo 后台进行请求的转发 前段获取request域中的successMsg属性，进行友好的客户端提示！
            model.addAttribute("successMsg","添加模型成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(effectNum == -1){
           throw new RuntimeException("模型上传失败！");
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Pattern pattern = patternService.findById(id);
        if(pattern != null){
            map.put("success",true);
            map.put("data",pattern);
        }else{
            map.put("success",false);
            map.put("errorMsg","查询模型数据失败！");
        }
        return map;
    }

    @RequestMapping("/getPattern")
    public void getImage(int id, OutputStream outputStream) {
        String patternUrl = patternService.findPatternUrlById(id);
        String basePath = PathUtil.getImageBasePath();
        String absolutePath = basePath + patternUrl;
        try {
            patternService.getPattern(absolutePath, outputStream);
        } catch (IOException e) {
            System.out.println("获取文件失败");
            e.printStackTrace();
        }
    }

//    @RequestMapping("/modify")
//    public String modify(Integer pageNum, PatternVo patternVo, Model model, HttpSession session){
//        //todo 获模型作者信息
//        User user = (User)session.getAttribute("currentUser");
//        patternVo.setPatternUploader(user.getName());
//        PatternDto patternDto = new PatternDto();
//        patternDto.setFileName(patternVo.getFile());
//        patternDto.setPatternName(patternVo.getPatternName());
//        patternDto.setPatternDesc(patternVo.getPatternDesc());
//        patternDto.setPatternTypeId(patternVo.getPatternTypeId());
//        patternDto.setPatternUploader(patternVo.getPatternUploader());
//        patternDto.setId(patternVo.getId());
//        try {
//            patternDto.setInputStream(patternVo.getFile().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int effectNum = 0;
//        try {
//            effectNum = patternService.modify(patternDto);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(effectNum == -1){
//            throw new RuntimeException("模型修改失败！");
//        }
//        return "forward:findAll?pageNum="+pageNum;
//    }

    //多文件上传
    @RequestMapping("/modify")
    public String modify(Integer pageNum, PatternVo patternVo, @RequestParam("file") MultipartFile[] files, Model model, HttpSession session){
        System.out.println(files.length);
        //todo 获取模型作者信息
        User user = (User)session.getAttribute("currentUser");
        String patternUploader = user.getName();
        Pattern pattern = new Pattern();
        pattern.setPatternUploader(patternUploader);
        pattern.setPatternName(patternVo.getPatternName());
        pattern.setPatternDesc(patternVo.getPatternDesc());
        PatternType patternType = new PatternType();
        patternType.setId(patternVo.getPatternTypeId());
        pattern.setPatternType(patternType);
        pattern.setId(patternVo.getId());
//        patternVo.setPatternUploader(user.getName());
//        //todo 多文件处理
//        List<CommonsMultipartFile> commonsMultipartFiles = patternVo.getFile();
//        PatternDto patternDto = new PatternDto();
//        patternDto.setFileName(commonsMultipartFiles);
//        patternDto.setPatternName(patternVo.getPatternName());
//        //前后台换行符\n的处理
//        String patternDesc = patternVo.getPatternDesc();
//        patternDesc = FilterLineBreak.filterLineBreak(patternDesc);
//        patternDto.setPatternDesc(patternDesc);
//        patternDto.setPatternTypeId(patternVo.getPatternTypeId());
//        patternDto.setPatternUploader(patternVo.getPatternUploader());getPatternUploader
//        //输入流的处理 todo
//        try {
//            patternDto.setInputStream(commonsMultipartFiles.);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        int effectNum = 0;
        try {
            //文件流传入service层进行处理
            effectNum = patternService.modify(pattern,files);
            // todo 后台进行请求的转发 前段获取request域中的successMsg属性，进行友好的客户端提示！
            model.addAttribute("successMsg","修改模型成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(effectNum == -1){
            throw new RuntimeException("模型上传失败！");
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public Map<String,Object> removeById(Integer id){
        Map<String,Object> map = new HashMap<>();
        int effectNum = patternService.removeById(id);
        if(effectNum != -1){
            map.put("success",true);
            map.put("successMsg","删除模型类型成功！");
        }else{
            map.put("success",false);
            map.put("errorMsg","删除模型类型失败！");
        }
        return map;
    }

}
