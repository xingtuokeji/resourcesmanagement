package com.simtop.dto;

import java.io.InputStream;

/**
 * 封装vo vo<-dto
 */
public class ImageDto {
    private Integer id;
    private String imageName;
    private Integer imageTypeId;
    private String imageDesc;
    //图片上传人
    private String uploader;
    private InputStream inputStream;//图片文件输入流
    private String fileName;//图片名字
    private String uploadPath;//图片的上传目录

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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imageTypeId=" + imageTypeId +
                ", imageDesc='" + imageDesc + '\'' +
                ", uploader='" + uploader + '\'' +
                ", inputStream=" + inputStream +
                ", fileName='" + fileName + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                '}';
    }
}
