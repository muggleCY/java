package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.User;
import com.web.exception.EmpNoNotEXISTException;
import com.web.exception.LoginFailException;
import com.web.exception.UserExistException;
import com.web.exception.UserLogoutException;
import com.web.service.UserService;
import com.web.util.ActionUtils;
import com.web.util.Pager;
import com.web.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@lombok.Setter
public class UserAction {
	private UserService userService;
	private UserService userServiceProxy;
	public String login(HttpServletRequest request, HttpServletResponse resp) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		JSONObject returnMessage = new JSONObject();
		try {
			user = userService.login(username, password);
			returnMessage.put("state","true");
			HttpSession session = request.getSession();
			//去除session中拦截器添加的提示信息
			session.removeAttribute("info");
			session.setAttribute("user", user);
		} catch (LoginFailException e) {
			returnMessage.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnMessage.toJSONString());
		return "success";
	}
	public String getOldPwd(HttpServletRequest request, HttpServletResponse resp){

		String oldPwd = request.getParameter("oldPwd");
		User user = (User) request.getSession().getAttribute("user");
		JSONObject retJson = new JSONObject();
		if (user.getUserPwd().equals(oldPwd)){
			retJson.put("state","200");
			ActionUtils.writeJson(resp,retJson.toJSONString());
		}
		return "success";
	}

	public String clearLogin(HttpServletRequest request, HttpServletResponse resp){
//		清空session中的user
		request.getSession().removeAttribute("user");
		ActionUtils.writeJson(resp,"200");
		return "success";
	}

	public String resetPwd(HttpServletRequest request, HttpServletResponse resp){
		User user = (User) request.getSession().getAttribute("user");
		userServiceProxy.changePwd(user,request.getParameter("newPwd"));
		return "success";
	}
	public String showUsers(HttpServletRequest req, HttpServletResponse resp){
		Integer pageNo = Integer.valueOf(req.getParameter("pageNo"));
		String account = req.getParameter("account");
		Integer status = Integer.valueOf(req.getParameter("status"));
		Integer roleId = Integer.valueOf(req.getParameter("roleId"));
		Pager<UserVo> pager = userService.selectUsersByPage(account,status,roleId,pageNo);
		try {
			PrintWriter out = resp.getWriter();
			out.write(JSONObject.toJSON(pager).toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String deleteUser(HttpServletRequest req, HttpServletResponse resp){
		Integer id = Integer.parseInt(req.getParameter("id"));
		PrintWriter out = null;
		JSONObject returnCode = new JSONObject();
		try {
			out= resp.getWriter();
			userService.deleteUser(id);
//			如果删除成功返回状态200
			returnCode.put("state","200");
		} catch (Exception e) {
//			如果出现异常,将异常信息传递给前端
			returnCode.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnCode.toJSONString());
		return "success";
	}
	public String showOneUser(HttpServletRequest req, HttpServletResponse resp){
		Integer id = Integer.parseInt(req.getParameter("id"));
		PrintWriter out = null;
		JSONObject returnJson = new JSONObject();
		try {
			out= resp.getWriter();
			UserVo userVo = userService.selectUser(id);
			out.write(JSONObject.toJSON(userVo).toString());
		} catch (Exception e) {
			returnJson.put("state",e.getMessage());
			out.write(returnJson.toJSONString());
		}
		out.flush();
		out.close();
		return "success";
	}
	public String updateUser(HttpServletRequest req, HttpServletResponse resp){
		Integer id = Integer.valueOf(req.getParameter("id"));
		Integer status = Integer.parseInt(req.getParameter("status"));
		Integer roleId = Integer.valueOf(req.getParameter("role"));
		PrintWriter out = null;
		JSONObject returnCode = new JSONObject();
		try {
			out = resp.getWriter();
			userService.updateUser(id,roleId,status);
			returnCode.put("state","200");
		} catch (IOException e) {
			returnCode.put("state",e.getMessage());
		}catch (Exception e) {
			returnCode.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnCode.toJSONString());

		return "success";
	}
	public String addUser(HttpServletRequest req, HttpServletResponse resp){
		Integer roleId = Integer.valueOf(req.getParameter("roleId"));
		String account = req.getParameter("account");
		Integer status = Integer.valueOf(req.getParameter("status"));
		String empNo = req.getParameter("empNo");
		String empName = req.getParameter("empName");
		JSONObject returnCode = new JSONObject();
		try {
			userService.addUser(account,empNo,empName,roleId,status);
			returnCode.put("state","200");
		} catch (EmpNoNotEXISTException e) {
			returnCode.put("state",e.getMessage());
		} catch (UserExistException e) {
			returnCode.put("state",e.getMessage());
		} catch (UserLogoutException e) {
			returnCode.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnCode.toJSONString());
		return "success";
	}
}
