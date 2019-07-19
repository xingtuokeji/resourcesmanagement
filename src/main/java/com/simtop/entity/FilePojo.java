package com.simtop.entity;

import java.util.Date;

/**
 * 文件实体类
 */
public class FilePojo {
    private Integer id;//主键编号
    private String fileName;//文件描述
    private String fileUrl;//数据库中的数据

    //todo 新增文件创建时间和文件上传者
    private Date createTime;
    private String fileUploader;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUploader() {
        return fileUploader;
    }

    public void setFileUploader(String fileUploader) {
        this.fileUploader = fileUploader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "FilePojo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", createTime=" + createTime +
                ", fileUploader='" + fileUploader + '\'' +
                '}';
    }
}
