package com.web.dao.impl;

import com.web.dao.HolidayVoDao;
import com.web.mapper.HolidayVoMapper;
import com.web.mapper.RowMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.HolidayVo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-17 下午7:08
 **/
public class HolidayVoDaoImpl implements HolidayVoDao {
    JDBCTemplate<HolidayVo> temp = new JDBCTemplate<HolidayVo>();
    JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
    @Override
    public Integer countHoliday(String holidayUser, Integer holidayType, Integer holidayStatus) {
        //select A.*,B.t_emp_name,C.t_config_page_value,D.t_config_page_value from ims.t_holiday A,ims.t_employee B,ims.t_sys_config C ,ims.t_sys_config D
        //where A.t_holiday_user_id = B.id
        //  and A.t_holiday_type_id = C.t_config_key
        //  and A.t_holiday_status_id = D.t_config_key
        StringBuffer sb = new StringBuffer()
                .append(" select ")
                .append(" 	count(A.id) nums ")
                .append(" from ")
                .append("	t_holiday A,t_employee B,t_sys_config C ,t_sys_config D ")
                .append(" where ")
                .append("   A.t_holiday_user_id = B.id ")
                .append("   and A.t_holiday_type_id = C.t_config_key ")
                .append("   and A.t_holiday_status_id = D.t_config_key ")
                .append("   and A.t_holiday_status_id = 5 ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && !holidayUser.equals("")){
            sb.append(" and t_emp_name like ? ");
            param.add("%"+holidayUser+"%");
        }
        if(holidayType != null && holidayType!= 0){
            sb.append(" and t_holiday_type_id = ? ");
            param.add(holidayType);
        }
        if(holidayStatus != null && holidayStatus!= 0){
            sb.append(" and t_holiday_status_id = ? ");
            param.add(holidayStatus);
        }
        return tempCount.selectOne(new RowMapper<Integer>() {
            @Override
            public Integer mapperObject(ResultSet rs) throws Exception {
                return rs.getInt("nums");
            }
        },sb.toString(),param.toArray());
    }

    @Override
    public Integer countHolidayById(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus) {
        StringBuffer sb = new StringBuffer()
                .append(" select ")
                .append(" 	count(A.id) nums ")
                .append(" from ")
                .append("	t_holiday A,t_employee B,t_sys_config C ,t_sys_config D ")
                .append(" where ")
                .append("   A.t_holiday_user_id = B.id ")
                .append("   and A.t_holiday_type_id = C.t_config_key ")
                .append("   and A.t_holiday_status_id = D.t_config_key ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && !holidayUser.equals("")){
            sb.append(" and t_emp_name like ? ");
            param.add("%"+holidayUser+"%");
        }
        if(holidayType != null && holidayType!= 0){
            sb.append(" and t_holiday_type_id = ? ");
            param.add(holidayType);
        }
        if(holidayStatus != null && holidayStatus!= 0){
            sb.append(" and t_holiday_status_id = ? ");
            param.add(holidayStatus);
        }
        sb.append(" and A.t_holiday_user_id = ? ");
        param.add(id);
        return tempCount.selectOne(new RowMapper<Integer>() {
            @Override
            public Integer mapperObject(ResultSet rs) throws Exception {
                return rs.getInt("nums");
            }
        },sb.toString(),param.toArray());
    }

    @Override
    public List<HolidayVo> selectHolidaysByPage(Integer deptId, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_holiday_no,A.t_holiday_user_id,A.t_holiday_type_id,A.t_holiday_bz,A.t_start_time,A.t_end_time,A.t_holiday_status_id,A.t_create_time,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	t_holiday A,t_employee B,t_sys_config C ,t_sys_config D ")
                .append(" where ")
                .append("   A.t_holiday_user_id = B.id ")
                .append("   and A.t_holiday_type_id = C.t_config_key ")
                .append("   and A.t_holiday_status_id = D.t_config_key ")
                .append("   and A.t_holiday_status_id = 5 ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && !holidayUser.equals("")){
            sql.append(" and t_emp_name like ? ");
            param.add("%"+holidayUser+"%");
        }
        if(holidayType != null && holidayType!= 0){
            sql.append(" and t_holiday_type_id = ? ");
            param.add(holidayType);
        }
        if(holidayStatus != null && holidayStatus!= 0){
            sql.append(" and t_holiday_status_id = ? ");
            param.add(holidayStatus);
        }
        if(deptId != null && deptId != 0){
            sql.append(" and B.t_emp_dept_id = ? ");
            param.add(deptId);
        }
        sql.append(" limit")
                .append("    ?,? ");
        param.add((pageNo - 1) * pageSize);
        param.add(pageSize);
        return temp.selectAll(new HolidayVoMapper(),sql.toString(),param.toArray());
    }

    @Override
    public List<HolidayVo> selectHolidaysByPageAndId(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_holiday_no,A.t_holiday_user_id,A.t_holiday_type_id,A.t_holiday_bz,A.t_start_time,A.t_end_time,A.t_holiday_status_id,A.t_create_time,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	t_holiday A,t_employee B,t_sys_config C ,t_sys_config D ")
                .append(" where ")
                .append("   A.t_holiday_user_id = B.id ")
                .append("   and A.t_holiday_type_id = C.t_config_key ")
                .append("   and A.t_holiday_status_id = D.t_config_key ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && !holidayUser.equals("")){
            sql.append(" and t_emp_name like ? ");
            param.add("%"+holidayUser+"%");
        }
        if(holidayType != null && holidayType!= 0){
            sql.append(" and t_holiday_type_id = ? ");
            param.add(holidayType);
        }
        if(holidayStatus != null && holidayStatus!= 0){
            sql.append(" and t_holiday_status_id = ? ");
            param.add(holidayStatus);
        }
        sql.append(" and A.t_holiday_user_id = ? ");
        param.add(id);
        sql.append(" limit")
                .append("    ?,? ");
        param.add((pageNo - 1) * pageSize);
        param.add(pageSize);
        return temp.selectAll(new HolidayVoMapper(),sql.toString(),param.toArray());
    }

    @Override
    public HolidayVo selectOneHoliday(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_holiday_no,A.t_holiday_user_id,A.t_holiday_type_id,A.t_holiday_bz,A.t_start_time,A.t_end_time,A.t_holiday_status_id,A.t_create_time,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	t_holiday A,t_employee B,t_sys_config C ,t_sys_config D ")
                .append(" where ")
                .append("   A.t_holiday_user_id = B.id ")
                .append("   and A.t_holiday_type_id = C.t_config_key ")
                .append("   and A.t_holiday_status_id = D.t_config_key ")
                .append("   and A.id = ? ")
                .toString();
        return temp.selectOne(new HolidayVoMapper(),sql,id);
    }
}
