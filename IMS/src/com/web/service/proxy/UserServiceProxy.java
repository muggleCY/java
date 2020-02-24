package com.web.service.proxy;

import com.web.entity.User;
import com.web.exception.EmpNoNotEXISTException;
import com.web.exception.LoginFailException;
import com.web.exception.UserExistException;
import com.web.exception.UserLogoutException;
import com.web.service.UserService;
import com.web.trans.Transaction;
import com.web.util.Pager;
import com.web.vo.UserVo;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
@lombok.Setter
public class UserServiceProxy implements UserService {
    private UserService userService;
    private Transaction trans;
    @Override
    public User login(String username, String password) throws LoginFailException {
        return null;
    }

    @Override
    public void changePwd(User user, String newPwd) {
        trans.begin();
        try {
            userService.changePwd(user,newPwd);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            throw e;
        }
    }

    @Override
    public Pager<UserVo> selectUsersByPage(String account, Integer status, Integer roleId, Integer pageNo) {
        Pager<UserVo> pager = userService.selectUsersByPage(account,status,roleId,pageNo);
        return pager;
    }

    @Override
    public List<UserVo> selectUsers() {
        List<UserVo> userVos = userService.selectUsers();
        return userVos;
    }

    @Override
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    @Override
    public UserVo selectUser(Integer id) {
        UserVo userVo = userService.selectUser(id);
        return userVo;
    }

    @Override
    public void updateUser(Integer id, Integer roleId, Integer status) {
        userService.updateUser(id,roleId,status);
    }

    @Override
    public void addUser(String account,String empNo,String empName,Integer roleId,Integer status) throws EmpNoNotEXISTException, UserExistException, UserLogoutException {
        userService.addUser(account, empNo, empName, roleId, status);
    }

}
