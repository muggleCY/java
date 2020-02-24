package com.web.vo;

import com.web.entity.Dept;

/**
 * @Author zyb
 * @Date 2019/12/15 11:54
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class DeptVO extends Dept {
    private String deptManagerName;
    public String getDeptManagerName() {
        return deptManagerName;
    }

    public void setDeptManagerName(String deptManagerName) {
        this.deptManagerName = deptManagerName;
    }
}
