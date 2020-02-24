package com.web.dao.impl;

import com.web.dao.HolidayDao;
import com.web.entity.Holiday;
import com.web.mapper.HolidayMapper;
import com.web.mapper.RowMapper;
import com.web.util.JDBCTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-11 下午3:29
 **/
public class HolidayDaoImpl implements HolidayDao {
    JDBCTemplate<Holiday> temp = new JDBCTemplate<Holiday>();
    JDBCTemplate<Integer> tempCount = new JDBCTemplate<Integer>();
    @Override
    public List<Holiday> selectHolidays() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time ")
                .append(" from ")
                .append("	t_holiday ")
                .toString();
        return temp.selectAll(new HolidayMapper(),sql);
    }

    @Override
    public Integer countHoliday(Integer holidayUser,Integer holidayType,Integer holidayStatus) {
        StringBuffer sb = new StringBuffer()
                .append(" select ")
                .append(" 	count(id) nums ")
                .append(" from ")
                .append("	t_holiday ")
                .append(" where ")
                .append("   1 = 1 ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && holidayUser!= 0){
            sb.append(" and t_holiday_user_id = ? ");
            param.add(holidayUser);
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
    public List<Holiday> selectHolidaysByPage(Integer holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time ")
                .append(" from ")
                .append("	t_holiday ")
                .append(" where ")
                .append("   1 = 1 ");
        List<Object> param = new ArrayList<Object>();
        if(holidayUser != null && holidayUser!= 0){
            sql.append(" and t_holiday_user_id = ? ");
            param.add(holidayUser);
        }
        if(holidayType != null && holidayType!= 0){
            sql.append(" and t_holiday_type_id = ? ");
            param.add(holidayType);
        }
        if(holidayStatus != null && holidayStatus!= 0){
            sql.append(" and t_holiday_status_id = ? ");
            param.add(holidayStatus);
        }
        sql.append(" limit")
           .append("    ?,? ");
        param.add((pageNo - 1) * pageSize);
        param.add(pageSize);
        return temp.selectAll(new HolidayMapper(),sql.toString(),param.toArray());
    }

    @Override
    public Holiday selectOneHoliday(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time ")
                .append(" from ")
                .append("	t_holiday ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        return temp.selectOne(new HolidayMapper(),sql,id);
    }

    @Override
    public void updateHoliday(Holiday holiday) {
        String sql = new StringBuffer()
                .append(" update ")
                .append("	t_holiday ")
                .append(" set ")
                .append("	t_holiday_user_id = ?, ")
                .append("	t_holiday_type_id = ?, ")
                .append("	t_holiday_bz = ?, ")
                .append("	t_start_time = ?, ")
                .append("	t_end_time = ?, ")
                .append("	t_holiday_status_id = ?, ")
                .append("	t_create_time = sysdate() ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.update(sql,holiday.getHolidayUser(),holiday.getHolidayType(),holiday.getHolidayBz(),holiday.getStartTime(),holiday.getEndTime(),holiday.getHolidayStatus(),holiday.getId());
    }

    @Override
    public void deleteHoliday(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("	t_holiday ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.delete(sql, id);
    }

    @Override
    public void insertHoliday(Holiday holiday) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("	t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time) ")
                .append(" values ")
                .append("	(?,?,?,?,?,?,?,sysdate()) ")
                .toString();
        temp.insert(sql,holiday.getHolidayNo(),holiday.getHolidayUser(),holiday.getHolidayType(),holiday.getHolidayBz(),holiday.getStartTime(),holiday.getEndTime(),holiday.getHolidayStatus());
    }

    @Override
    public String getMaxHolidayNo() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time ")
                .append(" from ")
                .append("	t_holiday ")
                .append(" where ")
                .append("	t_holiday_no = (select max(t_holiday_no) from t_holiday) ")
                .toString();
        return temp.selectOne(new HolidayMapper(),sql).getHolidayNo();
    }
}
