package com.simtop.controller.backend;

import com.simtop.entity.ColumnarModel;
import com.simtop.entity.Count;
import com.simtop.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    @RequestMapping("/show")
    @ResponseBody
    public ColumnarModel columnar(){
        System.out.println("前台请求已经到达");
        ColumnarModel model = new ColumnarModel();
        model.setTitle("素材下载量统计");
        model.setLegendData(new ArrayList<String>(){{add("下载量");}});
        List<Count> countList = echartsService.selectByTopThree();
        System.out.println(countList);
        List<String> xDataList = new ArrayList<String>();
        List<Integer> seriesDataList = new ArrayList<Integer>();
        for(Count count: countList){
            //返回排名前三的素材名称 todo
            xDataList.add(count.getImage().getImageName());
            //返回排名前三的素材下载量 todo
            seriesDataList.add(count.getImageDownloadNums());
        }
        model.setxData(xDataList);
        model.setSeriesData(seriesDataList);
        return model;
    }
}
