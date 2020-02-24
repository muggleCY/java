package com.web.dao.impl;

import com.web.dao.UserDao;
import com.web.entity.User;
import com.web.mapper.RowMapper;
import com.web.mapper.UserMapper;
import com.web.mapper.UserVoMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.UserVo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
public class UserDaoImpl implements UserDao {
    JDBCTemplate<User> temp = new JDBCTemplate<User>();
    JDBCTemplate<UserVo> tempVo = new JDBCTemplate<UserVo>();
    JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
    @Override
    public User selectUserNameByUsernameAndPwd(String username, String password) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id, t_user_account, t_user_pwd, t_emp_no, t_role_id,t_user_status_id, t_create_time")
                .append(" from ")
                .append("   t_user ")
                .append(" where ")
                .append("   t_user_account = ? ")
                .append("   and t_user_pwd= ? ")
                .toString();
        return temp.selectOne(new UserMapper(),sql,username,password);
    }

    @Override
    public void updateUser(User user) {
        String sql = new StringBuffer()
                .append(" update ")
                .append(" 	t_user ")
                .append(" set ")
                .append(" 	t_user_account=?, t_user_pwd=?, t_emp_no=?, t_role_id=?,t_user_status_id=?, t_create_time=?")
                .append(" where ")
                .append(" 	id = ? ")
                .toString();
        temp.update(sql,user.getUserAccount(),user.getUserPwd(),user.getEmpNo(),user.getRoleId(),user.getUserStatusId(),user.getCreateTime(),user.getId());
    }

    @Override
    public List<UserVo> selectUsers() {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   A.*,B.t_emp_name,B.t_emp_no,C.t_role_name,D.t_config_page_value ")
                .append(" from ")
                .append("   t_user A,t_employee B,t_role C,t_sys_config D ")
                .append(" where ")
                .append("   A.t_emp_no = B. id ")
                .append("   and A.t_role_id = C.id ")
                .append("   A.t_user_status_id = D.t_config_key ")
                .toString();
        return tempVo.selectAll(new UserVoMapper(),sql);
    }

    @Override
    public UserVo selectUser(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   A.*,B.t_emp_name,B.t_emp_no,B.t_emp_no,C.t_role_name,D.t_config_page_value ")
                .append(" from ")
                .append("   t_user A,t_employee B,t_role C,t_sys_config D ")
                .append(" where ")
                .append("   A.t_emp_no = B. id ")
                .append("   and A.t_role_id = C.id ")
                .append("   and A.t_user_status_id = D.t_config_key ")
                .append("   and A.id = ? ")
                .toString();
        return tempVo.selectOne(new UserVoMapper(),sql,id);
    }

    @Override
    public User selectUserByEmp(Integer empNo) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id, t_user_account, t_user_pwd, t_emp_no, t_role_id,t_user_status_id, t_create_time")
                .append(" from ")
                .append("   t_user ")
                .append(" where ")
                .append("   t_emp_no = ? ")
                .toString();
        return temp.selectOne(new UserMapper(),sql,empNo);
    }

    @Override
    public Integer countUsers(String account, Integer status, Integer roleId) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append("   count(id) nums ")
                .append(" from ")
                .append("   t_user ")
                .append(" where ")
                .append("   1 = 1 ");
        List<Object> param = new ArrayList<Object>();
        if(account != null && !account.equals("")){
            sql.append(" and t_user_account like ? ");
            param.add("%"+account+"%");
        }
        if(status != null && status != 0){
            sql.append(" and t_user_status_id = ? ");
            param.add(status);
        }
        if(roleId != null && roleId != 0){
            sql.append(" and t_role_id = ? ");
            param.add(roleId);
        }
        return tempCount.selectOne(new RowMapper<Integer>() {
            @Override
            public Integer mapperObject(ResultSet rs) throws Exception {
                return rs.getInt("nums");
            }
        },sql.toString(),param.toArray());
    }

    @Override
    public List<UserVo> selectUsersByPage(String account, Integer status, Integer roleId, Integer pageNo, Integer pageSize) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append("   A.*,B.t_emp_name,B.t_emp_no,C.t_role_name,D.t_config_page_value ")
                .append(" from ")
                .append("   t_user A,t_employee B,t_role C,t_sys_config D ")
                .append(" where ")
                .append("   A.t_emp_no = B. id ")
                .append("   and A.t_role_id = C.id ")
                .append("   and A.t_user_status_id = D.t_config_key ");
        List<Object> param = new ArrayList<Object>();
        if(account != null && !account.equals("")){
            sql.append(" and t_user_account like ? ");
            param.add("%"+account+"%");
        }
        if(status != null && status != 0){
            sql.append(" and t_user_status_id = ? ");
            param.add(status);
        }
        if(roleId != null && roleId != 0){
            sql.append(" and t_role_id = ? ");
            param.add(roleId);
        }
        sql.append(" limit ")
           .append("    ?,? ");
        param.add((pageNo - 1) * pageSize);
        param.add(pageSize);
        return tempVo.selectAll(new UserVoMapper(),sql.toString(),param.toArray());
    }


    @Override
    public List<User> selectUsersByRoleId(Integer roleId) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("   id, t_user_account, t_user_pwd, t_emp_no, t_role_id,t_user_status_id, t_create_time")
                .append(" from ")
                .append("   t_user ")
                .append(" where ")
                .append("   t_role_id = ? ")
                .toString();
        return temp.selectAll(new UserMapper(),sql,roleId);
    }

    @Override
    public void deleteUser(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("   t_user ")
                .append(" where ")
                .append("   id = ? ")
                .toString();
        temp.delete(sql,id);
    }

    @Override
    public void insertUser(String account,Integer empNo,Integer roleId,Integer status) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("	t_user(id,t_user_account,t_user_pwd,t_emp_no,t_role_id,t_user_status_id,t_create_time) ")
                .append(" values ")
                .append("	(null,?,'12345',?,?,?,sysdate()) ")
                .toString();
        temp.insert(sql,account,empNo,roleId,status);
    }
}
