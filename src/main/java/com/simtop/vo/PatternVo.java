package com.simtop.vo;


/**
 * 封装前台新增模型的数据
 */
public class PatternVo {
    private Integer id;
    private String patternName;
    private String patternDesc;//换行符处理
    private Integer patternTypeId;
    private String patternUploader;
    //新增模型面数
    private String patternSurfaceNumber;

    public String getPatternSurfaceNumber() {
        return patternSurfaceNumber;
    }

    public void setPatternSurfaceNumber(String patternSurfaceNumber) {
        this.patternSurfaceNumber = patternSurfaceNumber;
    }

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

    @Override
    public String toString() {
        return "PatternVo{" +
                "id=" + id +
                ", patternName='" + patternName + '\'' +
                ", patternDesc='" + patternDesc + '\'' +
                ", patternTypeId=" + patternTypeId +
                ", patternUploader='" + patternUploader + '\'' +
                ", patternSurfaceNumber='" + patternSurfaceNumber + '\'' +
                '}';
    }
}
