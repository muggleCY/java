package com.web.action;


import com.alibaba.fastjson.JSONObject;
import com.web.entity.Dept;
import com.web.mvc.Action;
import com.web.service.DeptService;
import com.web.util.ActionUtils;
import com.web.vo.DeptVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@lombok.Setter
public class DeptAction {
	private DeptService deptService;
	private DeptService deptServiceProxy;
	//传输所有查找到数据
	public String showDepts(HttpServletRequest req, HttpServletResponse resp){
		try {
			List<DeptVO> deptVOS = deptService.selectDepts();
			ActionUtils.writeJson(resp,JSONObject.toJSONString(deptVOS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	//修改部门信息
	public String updateDept(HttpServletRequest req, HttpServletResponse resp){
		String deptName = req.getParameter("deptName");
		String deptLoc = req.getParameter("deptLoc");
		String deptMaster = req.getParameter("deptMaster");
		String deptDesc = req.getParameter("deptDesc");
		Integer id = Integer.valueOf(req.getParameter("id"));
		JSONObject returnCode = new JSONObject();
		try{
			deptServiceProxy.updateDept(id,deptName,deptLoc,deptMaster,deptDesc);
			returnCode.put("state","200");
		} catch (Exception e) {
			returnCode.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnCode.toJSONString());;
		return "success";
	}

	/**
	 * 获取一个部门的信息
	 * @param req
	 * @param resp
	 * @return
	 */
	public String showOneDept(HttpServletRequest req, HttpServletResponse resp){
		Integer id = Integer.valueOf(req.getParameter("id"));
		try {
			Dept newDept = deptService.selectDeptById(id);
			ActionUtils.writeJson(resp,JSONObject.toJSONString(newDept));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String deleteOneDept(HttpServletRequest req, HttpServletResponse resp){
		Integer id = Integer.valueOf(req.getParameter("id"));
		JSONObject returnJson = new JSONObject();
		try {
			deptServiceProxy.deleteDept(id);
//			如果删除成功返回状态200
			returnJson.put("state","200");
		} catch (Exception e) {
//			如果出现异常,将异常信息传递给前端
			returnJson.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnJson.toJSONString());
		return "success";
	}

	public String addOneDept(HttpServletRequest req, HttpServletResponse resp){
		String deptName = req.getParameter("deptName");
		String deptLoc = req.getParameter("deptLoc");
		String deptMaster = req.getParameter("deptMaster");
		String deptDesc = req.getParameter("deptDesc");
		JSONObject returnVal = new JSONObject();
		try {
			deptServiceProxy.addDept(deptName,deptLoc,deptMaster,deptDesc);
			returnVal.put("state","200");
		} catch (Exception e) {
			returnVal.put("state",e.getMessage());
		}
		ActionUtils.writeJson(resp,returnVal.toJSONString());
		return "success";
	}

}
