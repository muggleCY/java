package com.jc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.UserDao;
import com.jc.exception.LoginFailException;
import com.jc.exception.UserExistException;
import com.jc.pojo.User;
import com.jc.service.UserService;
import com.jc.util.Pager;
/**
 * 用户业务层
 * @author soft01
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	/**
	 * 登录
	 */
	@Override
	@Transactional
	public User login(User user) throws LoginFailException,Exception {
		//username查
		User nUser = userDao.selectUserByNameAndPwd(user);
		//判断有没有这个用户user
		if(nUser == null){
			throw new LoginFailException(Constants.NOUSER);
		}
		if(!nUser.getPassword().equals(user.getPassword())){
			throw new LoginFailException(Constants.PWDERROR);
		}
		if(nUser.getStatus() == 2){
			throw new LoginFailException(Constants.USERCLOSED);
		}
		return nUser;
	}
	/**
	 * 添加User
	 * 手机号不可重复
	 * @throws UserExistException,Exception 
	 */
	@Override
	@Transactional
	public void addUser(User user) throws UserExistException,Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreateTime(sdf.format(date));
		User iUser = userDao.selectUserByIphone(user.getIphone());
		if(iUser != null){
			throw new UserExistException(Constants.USERIPHONEEXIST);
		}
		userDao.insertUser(user);
	}
	/**
	 * 查找所有用户
	 */
	@Override
	@Transactional
	public Pager<User> selectUserByPage(Integer pageNo, String username,
			String iphone) {
		Pager<User> pager = new Pager<User>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("iphone", iphone);
		map.put("pageNo", pageNo);
		map.put("pageSize", Constants.PAGESIZE);
		//找条数
		Integer totalCount = userDao.countUser(map);
		//找数据
		List<User> users = userDao.selectUserByPage(map);
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, Constants.PAGESIZE);
		pager.setList(users);
		return pager;
	}
	/**
	 * 修改状态
	 */
	@Override
	@Transactional
	public void modifyUserState(Integer status, User user) throws Exception {
		user.setStatus(status);
		userDao.updateUser(user);
	}
	@Override
	@Transactional
	public User selectUserById(Integer id) throws Exception {
		User user = new User();
		user.setId(id);
		return userDao.selectUser(user);
	}
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public void modifyUser(User user) throws Exception {
		userDao.updateUser(user);
	}

}
