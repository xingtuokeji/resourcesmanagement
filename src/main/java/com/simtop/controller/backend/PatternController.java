package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.dto.PatternDto;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/add")
    public String add(Integer pageNum, PatternVo patternVo, Model model, HttpSession session){
        //todo 获模型作者信息
        User user = (User)session.getAttribute("currentUser");
        patternVo.setPatternUploader(user.getName());
        PatternDto patternDto = new PatternDto();
        patternDto.setFileName(patternVo.getFile().getOriginalFilename());
        patternDto.setPatternName(patternVo.getPatternName());
        patternDto.setPatternDesc(patternVo.getPatternDesc());
        patternDto.setPatternTypeId(patternVo.getPatternTypeId());
        patternDto.setPatternUploader(patternVo.getPatternUploader());
        try {
            patternDto.setInputStream(patternVo.getFile().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int effectNum = 0;
        try {
            effectNum = patternService.add(patternDto);
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

    @RequestMapping("/modify")
    public String modify(Integer pageNum, PatternVo patternVo, Model model, HttpSession session){
        //todo 获模型作者信息
        User user = (User)session.getAttribute("currentUser");
        patternVo.setPatternUploader(user.getName());
        PatternDto patternDto = new PatternDto();
        patternDto.setFileName(patternVo.getFile().getOriginalFilename());
        patternDto.setPatternName(patternVo.getPatternName());
        patternDto.setPatternDesc(patternVo.getPatternDesc());
        patternDto.setPatternTypeId(patternVo.getPatternTypeId());
        patternDto.setPatternUploader(patternVo.getPatternUploader());
        patternDto.setId(patternVo.getId());
        try {
            patternDto.setInputStream(patternVo.getFile().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int effectNum = 0;
        try {
            effectNum = patternService.modify(patternDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(effectNum == -1){
            throw new RuntimeException("模型修改失败！");
        }
        return "forward:findAll?pageNum="+pageNum;
    }

}
