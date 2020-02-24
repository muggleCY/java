package com.web.entity;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Employee {
    /**
     * 员工id
     */
    private Integer id;
    /**
     * 员工编号
     */
    private String empNo;
    /**
     * 员工姓名
     */
    private String empName;
    /**
     * 员工部门id
     */
    private Integer empDeptId;
    /**
     * 上级领导的id
     */
    private Integer empSuperId;
    /**
     * 员工性别
     */
    private String sex;
    /**
     * 教育程度
     */
    private String education;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 员工状态
     */
    private Integer empState;
}
