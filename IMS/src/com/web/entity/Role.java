package com.web.entity;

import java.util.Date;

/**
 * @Author cy
 * @Time 19-12-13 下午2:18
 **/
public class Role {
    private Integer id;
    private String roleName;
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
