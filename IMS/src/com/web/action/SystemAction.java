package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.dao.MenuVODao;
import com.web.vo.MenuVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-13
 * 处理系统功能
 **/
@lombok.Setter
public class SystemAction {
    private MenuVODao menuVODao;
    public String getMenuById(HttpServletRequest req, HttpServletResponse resp){
        Integer roleId = Integer.valueOf(req.getParameter("roleId"));
        PrintWriter out = null;
        try {
             out= resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<MenuVO> menuVOS = menuVODao.selectCurrentMenuByRoleId(roleId);
        out.write(JSONObject.toJSON(menuVOS).toString());
        out.flush();
        out.close();
        return "success";
    }
}
