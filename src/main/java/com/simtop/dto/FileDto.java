package com.simtop.dto;

import java.io.InputStream;

/**
 * CommonsMultipartFile <<< InputStream
 */
public class FileDto {
    private Integer id;
    private String fileName;//描述
    private InputStream inputStream;
    private String uploadFileName;
    private String uploadPath;//文件上传目录
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", inputStream=" + inputStream +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", fileUploader='" + fileUploader + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                '}';
    }
}
