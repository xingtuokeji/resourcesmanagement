package com.simtop.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simtop.entity.Count;
import com.simtop.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/image")
public class DataStaticsController {

    @Autowired
    private CountService countService;

    //数据统计的逻辑 todo
    //step 1 接受前台ajax请求
    //step 2 根据传入的id更新图片对应的统计量这个字段
    //step 3 数据统计展现
    @RequestMapping("/downloadStatics")
    public Map<String,Object> dataStatic(Integer imageId){
        System.out.println("已经接收到前端图片ID"+imageId);
        Map<String,Object> map = new HashMap<String, Object>();
        // id=9的图片下载量
        //如果数据表中没有id=9的这一条记录则向tb_count中插入一条数据
        if(countService.findByImageId(imageId)==null){
            countService.add(imageId);
        }
        //否则更新tb_count中的image_download_nums字段(+1)
        int effectNum = countService.update(imageId);
        if(effectNum != -1){
            System.out.println("更新成功！");
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping("/showAll")
    public String main(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,5);
        List<Count> countList = countService.selectAll();
        System.out.println(countList);
        PageInfo<Count> pageInfo = new PageInfo<Count>(countList);
        model.addAttribute("pageInfo",pageInfo);
        return "imagestatistics";
    }


}
