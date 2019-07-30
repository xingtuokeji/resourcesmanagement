package com.simtop.entity;

import java.util.Date;

/**
 * 模型实体类
 */
public class Pattern {
    private Integer id;//模型主键id
    private String patternName;//模型名称
    private String patternDesc;//模型描述
    private String patternUploader;//模型作者
    private String patternUrl;//模型缩略图相对地址
    private Date createTime;//创建时间
    private Date lastEditTime;//最后更新时间

    //新增模型文件 todo 2019年7月29日15:09:56
    private String patternFileUrl;//模型文件相对地址

    //新增模型面数 todo 2019年7月30日10:05:21
    private String patternSurfaceNumber;//模型面数

    public String getPatternSurfaceNumber() {
        return patternSurfaceNumber;
    }

    public void setPatternSurfaceNumber(String patternSurfaceNumber) {
        this.patternSurfaceNumber = patternSurfaceNumber;
    }

    public String getPatternFileUrl() {
        return patternFileUrl;
    }

    public void setPatternFileUrl(String patternFileUrl) {
        this.patternFileUrl = patternFileUrl;
    }

    //符合类型 N:1
    private PatternType patternType;

    public PatternType getPatternType() {
        return patternType;
    }

    public void setPatternType(PatternType patternType) {
        this.patternType = patternType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getPatternDesc() {
        return patternDesc;
    }

    public void setPatternDesc(String patternDesc) {
        this.patternDesc = patternDesc;
    }

    public String getPatternUploader() {
        return patternUploader;
    }

    public void setPatternUploader(String patternUploader) {
        this.patternUploader = patternUploader;
    }

    public String getPatternUrl() {
        return patternUrl;
    }

    public void setPatternUrl(String patternUrl) {
        this.patternUrl = patternUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "id=" + id +
                ", patternName='" + patternName + '\'' +
                ", patternDesc='" + patternDesc + '\'' +
                ", patternUploader='" + patternUploader + '\'' +
                ", patternUrl='" + patternUrl + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", patternFileUrl='" + patternFileUrl + '\'' +
                ", patternSurfaceNumber='" + patternSurfaceNumber + '\'' +
                ", patternType=" + patternType +
                '}';
    }
}
