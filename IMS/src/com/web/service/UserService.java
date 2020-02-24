package com.web.service;

import com.web.entity.User;
import com.web.exception.EmpNoNotEXISTException;
import com.web.exception.LoginFailException;
import com.web.exception.UserExistException;
import com.web.exception.UserLogoutException;
import com.web.util.Pager;
import com.web.vo.UserVo;

import java.util.List;

public interface UserService {
        public User login(String username, String password) throws LoginFailException;
        public void changePwd(User user, String newPwd);
        public Pager<UserVo> selectUsersByPage(String account, Integer status, Integer roleId, Integer pageNo);
        public List<UserVo> selectUsers();
        public void deleteUser(Integer id);
        public UserVo selectUser(Integer id);
        public void updateUser(Integer id, Integer roleId, Integer status);
        void addUser(String account, String empNo, String empName, Integer roleId, Integer status) throws EmpNoNotEXISTException, UserExistException, UserLogoutException;
}
