package com.simtop.entity;

import java.util.Date;

/**
 * 图片（素材实体类）
 */
public class Image {
    private Integer id;//主键id
    private String imageName;//素材名称
    private String imageDesc;//素材描述
    private String imageUrl;//素材所在服务器路径
    private Integer imageTypeId;//图片所属分类id
    //时间戳
    private Date createTime;//创建时间
    private Date lastEditTime;//最后一次更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(Integer imageTypeId) {
        this.imageTypeId = imageTypeId;
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
        return "Image{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imageDesc='" + imageDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageTypeId=" + imageTypeId +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }
}
