package com.simtop.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 封装前台表单字段
 */
public class FileVo {
    private Integer id;
    private String fileName;
    private CommonsMultipartFile file;
    private String fileUploader;
    private String apiDesc;

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
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

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileVo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", file=" + file +
                ", fileUploader='" + fileUploader + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                '}';
    }
}
