package com.simtop.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 封装前台素材模块表单提交数据
 */
public class ImageVo {
    private Integer id;
    private String imageName;
    private Integer imageTypeId;
    private String imageDesc;
    private CommonsMultipartFile file;
    //图片上传人
    private String uploader;

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

    public Integer getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(Integer imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "ImageVo{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imageTypeId=" + imageTypeId +
                ", imageDesc='" + imageDesc + '\'' +
                ", file=" + file +
                ", uploader='" + uploader + '\'' +
                '}';
    }
}
