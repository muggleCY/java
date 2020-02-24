package com.web.dao.impl;

import com.web.dao.MenuVODao;
import com.web.mapper.MenuVOMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.MenuVO;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-13
 **/
public class MenuVOImpl implements MenuVODao{
    @Override
    public List<MenuVO> selectCurrentMenuByRoleId(Integer id) {
        JDBCTemplate<MenuVO> template = new JDBCTemplate<>();
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_menu_name,t_href_url,t_parent_id,t_menu_name ")
                .append(" from ")
                .append("	ims.t_menu ")
                .append(" where ")
                .append("   id in  ")
                .append("   (select t_menu_id from ims.t_permissions where t_role_id = ?) ")
                .append(" and t_parent_id is not null ")
                .toString();
        return template.selectAll(new MenuVOMapper(),sql,id);
    }
}
