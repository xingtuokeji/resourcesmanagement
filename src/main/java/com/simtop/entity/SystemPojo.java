package com.simtop.entity;

import java.util.Date;

public class SystemPojo {
    private Integer id;
    private String ip;
    private String userAgent;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "SystemPojo{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
