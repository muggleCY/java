package com.web.dao;

import com.web.entity.User;
import com.web.vo.UserVo;

import java.util.List;

public interface UserDao {
    public User selectUserNameByUsernameAndPwd(String username, String password);
    public void updateUser(User user);
    public List<UserVo> selectUsers();
    public UserVo selectUser(Integer id);
    public User selectUserByEmp(Integer empNo);
    public Integer countUsers(String account, Integer status, Integer roleId);
    public List<UserVo> selectUsersByPage(String account, Integer empNo, Integer roleId, Integer pageNo, Integer pageSize);
    public List<User> selectUsersByRoleId(Integer roleId);
    public void deleteUser(Integer id);
    public void insertUser(String account, Integer empNo, Integer roleId, Integer status);
}
