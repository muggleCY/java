package com.jc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jc.exception.LoginFailException;
import com.jc.exception.UserExistException;
import com.jc.pojo.Manager;
import com.jc.pojo.Province;
import com.jc.pojo.User;
import com.jc.service.ProvinceService;
import com.jc.service.UserService;
import com.jc.util.Pager;
import com.jc.vo.UserVo;
/**
 * 用户控制层
 * @author soft01
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceService provinceService;
	/**
	 * 登录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public ModelAndView login(User user,HttpServletRequest request){
		String username = user.getUsername();
		ModelAndView mav = new ModelAndView();
		User newUser = null;
		try {
			newUser = userService.login(user);
			HttpSession session = request.getSession();
			//去除session中拦截器添加的提示信息
//			session.removeAttribute("user");
			session.setAttribute("user", newUser);
			mav.setViewName("redirect:/view/userProtal.jsp");			
		} catch (LoginFailException e) {
			mav.addObject("isError", true);
			mav.addObject("errorMsg", e.getMessage());
			mav.setViewName("/view/userLogin.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMsg", e.getMessage());
			mav.setViewName("/view/userLogin.jsp");
		}
		return mav;
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser")
	public ModelAndView addUser(User user){
		ModelAndView mav = new ModelAndView();
		try {
			userService.addUser(user);
			mav.addObject("user",user);
			mav.setViewName("/view/userLogin.jsp");
		} catch (UserExistException e) {
			mav.addObject("isError", true);
			mav.addObject("errorMsg", e.getMessage());
			mav.setViewName("/view/userRegister.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMsg", e.getMessage());
			mav.setViewName("/view/userRegister.jsp");
		}
		return mav;
	}
	/**
	 * 查询所有的用户
	 * @param pageNo
	 * @param uname
	 * @param phone
	 * @param resp
	 */
	@ResponseBody
	@RequestMapping("/getUsersByPage")
	public void getUsersByPage(Integer pageNo, String username, String iphone,HttpServletResponse resp){
		
		Pager<User> users = userService.selectUserByPage(pageNo, username, iphone);
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String json = JSONObject.fromObject(users).toString();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	/**
	 * 修改状态
	 * @param req
	 * @param resp
	 */
	@ResponseBody
	@RequestMapping("/changeStatus")
	public void changUserState(HttpServletRequest req,HttpServletResponse resp) {
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] ids = (String[]) req.getParameterValues("ids");
		Integer status = Integer.valueOf(String.valueOf(req.getParameter("status")));
		System.out.println(ids[0]);
		System.out.println(status);
		for (int i = 0; i < ids.length; i++) {
			try {
				userService.modifyUserState(status,userService.selectUserById(Integer.parseInt(ids[i])));
				pw.write("success");
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
				pw.write("fail");
				pw.flush();
				pw.close();
			}
		}
	}
	@RequestMapping("getUserById")
	@ResponseBody
	public void getUserById(Integer id, HttpServletResponse response)
			throws IOException {
		User user = null;
		try {
			user = userService.selectUserById(id);
			Province province = provinceService.getProvById(user.getProvinceId());
			UserVo  userVo = new UserVo();
			userVo.setId(user.getId());
			userVo.setUsername(user.getUsername());
			userVo.setPassword(user.getPassword());
			userVo.setIphone(user.getIphone());
			userVo.setProvinceId(user.getProvinceId());
			userVo.setTariffe(user.getTariffe());
			userVo.setCurrency(user.getCurrency());
			userVo.setCardAmount(user.getCardAmount());
			userVo.setStatus(user.getStatus());
			userVo.setCreateTime(user.getCreateTime());
			userVo.setSex(user.getSex());
			userVo.setProvinceName(province.getProvinceName());
			PrintWriter out = response.getWriter();
			out.write(JSONArray.fromObject(userVo).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping("/modifyPwd")
	public ModelAndView modifyPwd(Integer id,String userPwd,HttpSession session){
		ModelAndView mv = new ModelAndView();
		System.out.println("id" + id);
		System.out.println("userPwd" + userPwd);
		try {
			User user = userService.selectUserById(id);
			user.setPassword(userPwd);
			userService.modifyUser(user);
			session.removeAttribute("user");
			mv.setViewName("redirect:/view/userLogin.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("/view/Detail.jsp");
			mv.addObject("errorMessage","修改密码失败");
			mv.addObject("isError",true);
		}
		return mv;
	}
	@RequestMapping("/exit")
	public ModelAndView exit(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		mav.setViewName("redirect:/view/userLogin.jsp");			
		return mav;
	}
}
