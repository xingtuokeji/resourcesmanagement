package com.simtop.entity;

import java.util.List;

/**
 * 柱状图 model
 */
public class ColumnarModel {
    //柱状图标题
    private String title;

    //顶部数据
    private List<String> legendData;

    //横轴数据
    private List<String> xData;

    //柱状图上的数字
    private List<Integer> seriesData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<String> getxData() {
        return xData;
    }

    public void setxData(List<String> xData) {
        this.xData = xData;
    }

    public List<Integer> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Integer> seriesData) {
        this.seriesData = seriesData;
    }

    @Override
    public String toString() {
        return "ColumnarModel{" +
                "title='" + title + '\'' +
                ", legendData=" + legendData +
                ", xData=" + xData +
                ", seriesData=" + seriesData +
                '}';
    }
}
