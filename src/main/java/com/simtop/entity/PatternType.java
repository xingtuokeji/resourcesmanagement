package com.simtop.entity;

import java.util.Date;

/**
 * 模型类别
 */
public class PatternType {
    private Integer id;
    private String patternTypeName;
    private Date createTime;
    private Date lastEditTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternTypeName() {
        return patternTypeName;
    }

    public void setPatternTypeName(String patternTypeName) {
        this.patternTypeName = patternTypeName;
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
        return "PatternType{" +
                "id=" + id +
                ", patternTypeName='" + patternTypeName + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }
}
