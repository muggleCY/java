package com.web.vo;

import com.web.entity.User;

/**
 * @Author cy
 * @Time 19-12-16 下午6:26
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class UserVo extends User {
    private String empNoo;
    private String empName;
    private String roleName;
    private String ConfigPageValue;

    public String getEmpNoo() {
        return empNoo;
    }

    public void setEmpNoo(String empNoo) {
        this.empNoo = empNoo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getConfigPageValue() {
        return ConfigPageValue;
    }

    public void setConfigPageValue(String configPageValue) {
        ConfigPageValue = configPageValue;
    }
}
