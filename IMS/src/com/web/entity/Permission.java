package com.web.entity;

/**
 * @Author cy
 * @Time 19-12-16 上午9:19
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Permission {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
