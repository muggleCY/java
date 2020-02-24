package com.web.entity;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     *所属员工编号
     */
    private Integer empNo;
    /**
     *角色(角色表的id)
     */
    private Integer roleId;
    /**
     * 用户状态Id
     */
    private Integer userStatusId;
    /**
     *创建时间
     */
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
