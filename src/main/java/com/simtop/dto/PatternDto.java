package com.simtop.dto;

import java.io.InputStream;

public class PatternDto {
    private Integer id;
    private String patternName;
    private String patternDesc;
    private Integer patternTypeId;
    private InputStream inputStream;//模型文件输入流
    private String fileName;//模型文件名字
    private String uploadPath;//模型的上传目录
    private String patternUploader;//模型作者

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternUploader() {
        return patternUploader;
    }

    public void setPatternUploader(String patternUploader) {
        this.patternUploader = patternUploader;
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

    public Integer getPatternTypeId() {
        return patternTypeId;
    }

    public void setPatternTypeId(Integer patternTypeId) {
        this.patternTypeId = patternTypeId;
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

    @Override
    public String toString() {
        return "PatternDto{" +
                "id=" + id +
                ", patternName='" + patternName + '\'' +
                ", patternDesc='" + patternDesc + '\'' +
                ", patternTypeId=" + patternTypeId +
                ", inputStream=" + inputStream +
                ", fileName='" + fileName + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", patternUploader='" + patternUploader + '\'' +
                '}';
    }
}
