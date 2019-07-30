package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.dto.FileDto;
import com.simtop.entity.FilePojo;
import com.simtop.entity.User;
import com.simtop.service.FileService;
import com.simtop.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件模块控制类
 */
@Controller
@RequestMapping("/backend/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/findAll")
    public String mainPage(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<FilePojo> fileList = fileService.findAll();
        PageInfo<FilePojo> pageInfo = new PageInfo<FilePojo>(fileList);
        model.addAttribute("pageInfo",pageInfo);
        return "filemanage";
    }

    @RequestMapping("/add")
    public String add(FileVo fileVo, HttpSession session,Integer pageNum){
        //获取当前上传文档的用户
        User u = (User) session.getAttribute("currentUser");
        String fileUploader = u.getName();
        Map<String,Object> map = new HashMap<String,Object>();
        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileVo.getFileName());
        fileDto.setApiDesc(fileVo.getApiDesc());
        try {
            fileDto.setInputStream(fileVo.getFile().getInputStream());
            fileDto.setUploadFileName(fileVo.getFile().getOriginalFilename());
            fileDto.setFileUploader(fileUploader);
            //todo 设置上传的uploadPath 在业务层中进行处理
            int effectNum = fileService.add(fileDto);
            if(effectNum != -1){
                map.put("success",true);
            }else{
                map.put("success",false);
                map.put("errMsg","添加文件失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Map<String,Object> findById(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        FilePojo file = fileService.selectById(id);
        System.out.println(file);
        if(file!=null){
            map.put("success",true);
            map.put("data",file);
        }else{
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping("/modify")
    public String modify(FileVo fileVo,Integer pageNum){
        Map<String,Object> map = new HashMap<String,Object>();
        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileVo.getFileName());
        try {
            fileDto.setId(fileVo.getId());
            fileDto.setInputStream(fileVo.getFile().getInputStream());
            fileDto.setUploadFileName(fileVo.getFile().getOriginalFilename());
            //todo 设置上传的uploadPath 在业务层中进行处理
            int effectNum = fileService.modify(fileDto);
            if(effectNum != -1){
                map.put("success",true);
            }else{
                map.put("success",false);
                map.put("errMsg","修改文件失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "forward:findAll?pageNum="+pageNum;

    }
}
