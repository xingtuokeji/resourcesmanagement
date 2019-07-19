package com.simtop.entity;

import java.util.Date;

/**
 * 图片（素材实体类）1：N         N:N
 */
public class Image {
    private Integer id;//主键id
    private String imageName;//素材名称
    private String imageDesc;//素材描述
    private String imageUrl;//素材所在服务器路径
    private ImageType imageType;//图片所属分类id
    // 范式

    //统计图片下载量相关信息 新增图片上传者 todo
    private String uploader;//图片上传者

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

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imageDesc='" + imageDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageType=" + imageType +
                ", uploader='" + uploader + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }
}
