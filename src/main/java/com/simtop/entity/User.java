package com.simtop.entity;

public class User {
    private Integer id;
    private String loginName;
    private String loginPassword;
    private String name;

    //新增角色id
    private Integer roleId; //1表示超级管理员 2表示普通用户

    //新增用户使用状态
    private int enableStatus;//1代表可用 2代表禁用

    public int getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(int enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", enableStatus=" + enableStatus +
                '}';
    }
}
