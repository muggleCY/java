package com.web.entity;

/**
 * @Author cy
 * @TIME 19-12-10
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Dept {
    /**
     * 部门id
     */
    private Integer id;
    /**
     * 部门编号
     */
    private String deptNo;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门位置
     */
    private String deptLoc;
    /**
     * 部门描述
     */
    private String deptDesc;
    /**
     * 部门创建时间
     */
    private String deptCreateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptCreateTime() {
        return deptCreateTime;
    }

    public void setDeptCreateTime(String deptCreateTime) {
        this.deptCreateTime = deptCreateTime;
    }
}
