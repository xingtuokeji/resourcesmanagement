package com.simtop.vo;

public class PatternParamsVo {
    private String patternName;
    private Integer patternTypeId;
    private String patternSurfaceNumber;

    public String getPatternSurfaceNumber() {
        return patternSurfaceNumber;
    }

    public void setPatternSurfaceNumber(String patternSurfaceNumber) {
        this.patternSurfaceNumber = patternSurfaceNumber;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public Integer getPatternTypeId() {
        return patternTypeId;
    }

    public void setPatternTypeId(Integer patternTypeId) {
        this.patternTypeId = patternTypeId;
    }

    @Override
    public String toString() {
        return "PatternParamsVo{" +
                "patternName='" + patternName + '\'' +
                ", patternTypeId=" + patternTypeId +
                ", patternSurfaceNumber='" + patternSurfaceNumber + '\'' +
                '}';
    }
}
