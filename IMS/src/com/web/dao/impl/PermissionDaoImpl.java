package com.web.dao.impl;

import com.web.dao.PermissionDao;
import com.web.entity.Permission;
import com.web.mapper.PermissionMapper;
import com.web.mapper.PermissionVoMapper;
import com.web.mapper.RowMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.PermissionVo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-16 上午9:32
 **/
public class PermissionDaoImpl implements PermissionDao {
    JDBCTemplate<PermissionVo> temp = new JDBCTemplate<PermissionVo>();
    JDBCTemplate<Permission> tmp = new JDBCTemplate<Permission>();
    JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();

    @Override
    public List<PermissionVo> selectAllPer() {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   A.id,A.t_role_id,A.t_menu_id,A.t_create_time,B.t_role_name,C.t_menu_name")
                .append(" from ")
                .append("   t_permissions A,t_role B,t_menu C ")
                .append(" where ")
                .append("   A.t_role_id = B.id")
                .append("   and A.t_menu_id = C.id ")
                .toString();
        return temp.selectAll(new PermissionVoMapper(),sql);
    }

    @Override
    public Integer countPer(Integer roleId, Integer menuId) {
        StringBuffer sb = new StringBuffer()
                .append(" select ")
                .append("   count(id) nums")
                .append(" from ")
                .append("   t_permissions  ")
                .append(" where  ")
                .append("   1 = 1  ");
        List<Object> param = new ArrayList<Object>();
        if(roleId != null && roleId!= 0){
            sb.append(" and t_role_id = ? ");
            param.add(roleId);
        }
        if(menuId != null && menuId!= 0){
            sb.append(" and t_menu_id = ? ");
            param.add(menuId);
        }
        return tempCount.selectOne(new RowMapper<Integer>() {
            @Override
            public Integer mapperObject(ResultSet rs) throws Exception {
                return rs.getInt("nums");
            }
        },sb.toString(),param.toArray());
    }

    @Override
    public List<PermissionVo> selectAllPerByPage(Integer roleId, Integer menuId, Integer pageNo, Integer pageSize) {
        StringBuffer sb = new StringBuffer()
                .append(" select ")
                .append("   A.id,A.t_role_id,A.t_menu_id,A.t_create_time,B.t_role_name,C.t_menu_name")
                .append(" from ")
                .append("   t_permissions A,t_role B,t_menu C ")
                .append(" where ")
                .append("   A.t_role_id = B.id")
                .append("   and A.t_menu_id = C.id ");
        List<Object> param = new ArrayList<Object>();
        if(roleId != null && roleId!= 0){
            sb.append(" and t_role_id = ? ");
            param.add(roleId);
        }
        if(menuId != null && menuId!= 0){
            sb.append(" and t_menu_id = ? ");
            param.add(menuId);
        }
        sb.append(" limit")
          .append("    ?,? ");
        param.add((pageNo - 1) * pageSize);
        param.add(pageSize);
        return temp.selectAll(new PermissionVoMapper(),sb.toString(),param.toArray());
    }

    @Override
    public Permission selectOnePer(Integer roleId,Integer menuId) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id,t_role_id,t_menu_id,t_create_time ")
                .append(" from ")
                .append("   t_permissions ")
                .append(" where ")
                .append("   t_role_id = ? ")
                .append("  and t_menu_id = ? ")
                .toString();
        return tmp.selectOne(new PermissionMapper(),sql,roleId,menuId);
    }

    @Override
    public Permission selectOnePerById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id,t_role_id,t_menu_id,t_create_time ")
                .append(" from ")
                .append("   t_permissions ")
                .append(" where ")
                .append("   id = ? ")
                .toString();
        return tmp.selectOne(new PermissionMapper(),sql,id);
    }

    @Override
    public void insertPer(Permission permission) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("	t_permissions(id,t_role_id,t_menu_id,t_create_time) ")
                .append(" values ")
                .append("	(null,?,?,?) ")
                .toString();
        tmp.insert(sql,permission.getRoleId(),permission.getMenuId(),permission.getCreateTime());
    }

    @Override
    public void deletePer(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("	t_permissions ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        tmp.delete(sql, id);
    }

    @Override
    public void updatePer(Integer id, Integer roleId, Integer menuId) {
        String sql = new StringBuffer()
                .append(" update ")
                .append("   t_permissions ")
                .append(" set ")
                .append("   t_role_id = ?, ")
                .append("   t_menu_id = ?, ")
                .append("  t_create_time = sysdate() ")
                .append("  where ")
                .append("   id = ? ")
                .toString();
        temp.update(sql,roleId,menuId,id);
    }
}
