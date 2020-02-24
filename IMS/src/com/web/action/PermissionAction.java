package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Permission;
import com.web.exception.PermissionDeleteFailException;
import com.web.exception.PremissionAlreadyExistException;
import com.web.service.PermissionService;
import com.web.util.ActionUtils;
import com.web.util.DateFormateUtils;
import com.web.util.Pager;
import com.web.vo.PermissionVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * @Author cy
 * @Time 19-12-16 上午10:11
 **/
public class PermissionAction {
    private PermissionService permissionService;
    public String showPerByPage(HttpServletRequest req, HttpServletResponse resp){

        Integer pageNo = Integer.valueOf(req.getParameter("pageNo"));
        Integer roleId = Integer.valueOf(req.getParameter("roleType"));
        Integer menuId = Integer.valueOf(req.getParameter("menuType"));
        Pager<PermissionVo> pager = permissionService.selectAllPerByPage(roleId,menuId,pageNo);
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
    public String deletePer(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("id"));
        PrintWriter out = null;
        JSONObject returnJson = new JSONObject();
        try {
           out = resp.getWriter();
           permissionService.deletePer(id);
            returnJson.put("state","200");
        } catch (IOException e) {
            returnJson.put("state",e.getMessage());
        } catch (PermissionDeleteFailException e) {
            returnJson.put("state",e.getMessage());
        }
        out.write(returnJson.toJSONString());
        out.flush();
        out.close();
        return "success";
    }
    public String insertPer(HttpServletRequest req, HttpServletResponse resp){
        Integer roleId = Integer.valueOf(req.getParameter("roleId"));
        Integer menuId = Integer.valueOf(req.getParameter("menuId"));
        Permission permission = new Permission();
        permission.setRoleId(roleId);
        permission.setMenuId(menuId);
        //插入的时间为年月日
        permission.setCreateTime(DateFormateUtils.getNowTime());
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = null;
        JSONObject returnJson = new JSONObject();
        try {
            out = resp.getWriter();
            permissionService.insert(permission);
            //
            returnJson.put("state","200");
        } catch (IOException e) {
            returnJson.put("state",e.getMessage());
        } catch (PremissionAlreadyExistException e) {
            returnJson.put("state",e.getMessage());
        }
        out.write(returnJson.toJSONString());
        out.flush();
        out.close();
        return "success";
    }
    public String showOnePer(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("id"));
        Permission permission = permissionService.selectOnePer(id);
        ActionUtils.writeJson(resp, JSONObject.toJSONString(permission));
        return "success";
    }
    public String updatePer(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer roleId = Integer.parseInt(req.getParameter("roleId"));
        Integer menuId = Integer.parseInt(req.getParameter("menuId"));
        PrintWriter out = null;
        JSONObject returnCode = new JSONObject();
        try {
            out = resp.getWriter();
            permissionService.updatePer(id,roleId,menuId);
            returnCode.put("state","200");
        } catch (PremissionAlreadyExistException e) {
            returnCode.put("state",e.getMessage());
        } catch (IOException e) {
            returnCode.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,returnCode.toJSONString());
        return "success";
    }
    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
