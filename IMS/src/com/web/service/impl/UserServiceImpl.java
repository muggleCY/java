package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.EmployeeDao;
import com.web.dao.UserDao;
import com.web.entity.Employee;
import com.web.entity.User;
import com.web.exception.EmpNoNotEXISTException;
import com.web.exception.LoginFailException;
import com.web.exception.UserExistException;
import com.web.exception.UserLogoutException;
import com.web.service.UserService;
import com.web.util.DateFormateUtils;
import com.web.util.Pager;
import com.web.vo.UserVo;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
@lombok.Setter
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private EmployeeDao employeeDao;

    @Override
    public User login(String username, String password) throws LoginFailException {
        User user = userDao.selectUserNameByUsernameAndPwd(username, password);
        if (user==null){
            throw  new LoginFailException(ExceptionConstant.UNAME_OR_PWD_ERROR);
        }
        return user;
    }

    @Override
    public void changePwd(User user, String newPwd) {
        user.setUserPwd(newPwd);
        userDao.updateUser(user);
    }

    @Override
    public Pager<UserVo> selectUsersByPage(String account, Integer status, Integer roleId, Integer pageNo) {
        Pager<UserVo> pager = new Pager<UserVo>();
        pager.setPageNo(pageNo);
        Integer totalCount = userDao.countUsers(account,status,roleId);
        if(totalCount == null){
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, Constants.PAGE_SIZE_3);
        List<UserVo> userVos = userDao.selectUsersByPage(account,status,roleId,pageNo,Constants.PAGE_SIZE_3);
        pager.setList(userVos);
        return pager;
    }

    @Override
    public List<UserVo> selectUsers() {
        List<UserVo> userVos =  userDao.selectUsers();
        return userVos;
    }

    @Override
    public void deleteUser(Integer id) {
        //如果该用户正常使用,不允许删除
        //删除用户时,如果该用户还存在,会有键的关联,软删除,设置为注销状态
        userDao.deleteUser(id);
    }

    @Override
    public UserVo selectUser(Integer id) {
        UserVo userVo = userDao.selectUser(id);
        return userVo;
    }

    @Override
    public void updateUser(Integer id, Integer roleId, Integer status) {
        UserVo userVo = userDao.selectUser(id);
        User user = new User();
        user.setId(id);
        user.setUserAccount(userVo.getUserAccount());
        user.setUserPwd(userVo.getUserPwd());
        user.setEmpNo(userVo.getEmpNo());
        user.setRoleId(roleId);
        user.setUserStatusId(status);
        user.setCreateTime(DateFormateUtils.getNowTime());
        userDao.updateUser(user);
    }

    @Override
    public void addUser(String account,String empNo,String empName,Integer roleId,Integer status) throws EmpNoNotEXISTException, UserExistException, UserLogoutException {
        //首先去查询用户是否在在员工表存在,不存在,抛出异常
        Employee employee = employeeDao.selectEmyByEmpNoAndName(empNo,empName);
        if(employee == null){
            throw new EmpNoNotEXISTException(ExceptionConstant.EMP_NP_NO_EXIST);
        }else if(employee.getEmpState() == 7){
            //如果该员工的账户是注销的账户,给出提示信息(异常)
            throw new UserLogoutException(ExceptionConstant.USER_EXIST);
        }

        User user = userDao.selectUserByEmp(employee.getId());
        if(user != null){
            //该员工是否已经用户账户,是的话,抛出异常()
            throw new UserExistException(ExceptionConstant.USER_EXIST);
        }
        Integer empId = employee.getId();
        userDao.insertUser(account, empId, roleId, status);
    }

}
