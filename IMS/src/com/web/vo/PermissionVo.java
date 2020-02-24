package com.web.vo;

import com.web.entity.Permission;

/**
 * @Author cy
 * @Time 19-12-16 上午9:23
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class PermissionVo extends Permission {
    private String roleName;
    private String menuName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
