package com.web.mapper;

import com.web.vo.MenuVO;

import java.sql.ResultSet;

/**
 * @Author zyb
 * @TIME 19-12-13
 **/
public class MenuVOMapper implements RowMapper<MenuVO> {
    @Override
    public MenuVO mapperObject(ResultSet rs) throws Exception {
        MenuVO menuVO = new MenuVO(rs.getInt("id"),
                rs.getString("t_href_url"),
                rs.getInt("t_parent_id"),
                rs.getString("t_menu_name"));
        return menuVO;
    }
}
