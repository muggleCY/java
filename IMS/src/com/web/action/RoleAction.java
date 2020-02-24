package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.dao.RoleDao;
import com.web.entity.Role;
import com.web.exception.RoleAlreadyExistException;
import com.web.service.RoleService;
import com.web.util.ActionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-13 下午2:48
 **/
@lombok.Setter
public class RoleAction {
    private RoleService roleService;
    private RoleDao roleDao;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public String showRoles(HttpServletRequest req, HttpServletResponse resp){
        List<Role> roles = roleService.selectRoles();
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(JSONObject.toJSON(roles).toString());
        out.flush();
        out.close();
        return "success";
    }
    public String showOneRole(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("id"));
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            Role role = roleService.selectOneRole(id);
            out.write(JSONObject.toJSON(role).toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    public String deleteRole(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("id"));
        PrintWriter out = null;
        JSONObject returnJson = new JSONObject();
        try {
            out= resp.getWriter();
            roleService.deleteRole(id);
//			如果删除成功返回状态200
            returnJson.put("state","200");
        } catch (Exception e) {
//			如果出现异常,将异常信息传递给前端
            returnJson.put("state",e.getMessage());
        }
        out.write(returnJson.toJSONString());
        out.flush();
        out.close();
        return "success";
    }
    public String updateRole(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("roleId"));
        String roleName = req.getParameter("roleName");
        JSONObject returnCode = new JSONObject();
        try {
            roleService.updateRole(id,roleName);
            returnCode.put("state","200");
        }  catch (RoleAlreadyExistException e) {
            returnCode.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,returnCode.toJSONString());
        return "success";
    }
    public String insertRole(HttpServletRequest req, HttpServletResponse resp){
        String roleName = req.getParameter("roleName");
        PrintWriter out = null;
        JSONObject returnCode = new JSONObject();
        try {
            out= resp.getWriter();
            roleService.addRole(roleName);
//			如果删除成功返回状态200
            returnCode.put("state","200");
        } catch (RoleAlreadyExistException e) {
//			如果出现异常,将异常信息传递给前端
            returnCode.put("state",e.getMessage());
        } catch (Exception e) {
//			如果出现异常,将异常信息传递给前端
            returnCode.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,returnCode.toJSONString());
        return "success";
    }
    public String getRoleList(HttpServletRequest req, HttpServletResponse resp){
        List<Role> roles = roleDao.selectRoles();
        ActionUtils.writeJson(resp,JSONObject.toJSONString(roles));
        return "success";
    }
    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
