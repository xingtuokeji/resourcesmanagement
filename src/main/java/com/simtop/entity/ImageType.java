package com.simtop.entity;

import java.util.Date;

/**
 * 图片类型
 */
public class ImageType {
    private Integer id;
    private String imageTypeName;
    private Date createTime;
    private Date lastEditTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageTypeName() {
        return imageTypeName;
    }

    public void setImageTypeName(String imageTypeName) {
        this.imageTypeName = imageTypeName;
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
        return "ImageType{" +
                "id=" + id +
                ", imageTypeName='" + imageTypeName + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }
}
