package com.web.dao.impl;

import com.web.dao.RoleDao;
import com.web.entity.Role;
import com.web.mapper.RoleMapper;
import com.web.util.JDBCTemplate;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-13 下午2:29
 **/
public class RoleDaoImpl implements RoleDao {
    JDBCTemplate<Role> temp = new JDBCTemplate<Role>();

    @Override
    public List<Role> selectRoles() {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id,t_role_name,t_create_time ")
                .append(" from ")
                .append("   t_role ")
                .toString();
        return temp.selectAll(new RoleMapper(),sql);
    }

    @Override
    public Role selectOneRole(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id,t_role_name,t_create_time ")
                .append(" from ")
                .append("   t_role ")
                .append(" where ")
                .append("   id = ? ")
                .toString();

        return temp.selectOne(new RoleMapper(),sql,id);
    }

    @Override
    public Role selectOneRole(String roleName) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id,t_role_name,t_create_time ")
                .append(" from ")
                .append("   t_role ")
                .append(" where ")
                .append("   t_role_name = ? ")
                .toString();

        return temp.selectOne(new RoleMapper(),sql,roleName);
    }

    @Override
    public void deleteRole(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("   t_role ")
                .append(" where ")
                .append("   id = ? ")
                .toString();
        temp.delete(sql,id);
    }

    @Override
    public void updateRole(Role role) {
        String sql = new StringBuffer()
                .append(" update ")
                .append("   t_role ")
                .append(" set ")
                .append("   t_role_name = ?, ")
                .append("  t_create_time = sysdate() ")
                .append("  where ")
                .append("   id = ? ")
                .toString();
        temp.update(sql,role.getRoleName(),role.getId());
    }

    @Override
    public void insertRole(String roleName) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("	t_role(id,t_role_name,t_create_time) ")
                .append(" values ")
                .append("	(null,?,sysdate()) ")
                .toString();
        temp.insert(sql,roleName);
    }
}
