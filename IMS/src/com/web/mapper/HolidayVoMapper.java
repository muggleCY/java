package com.web.mapper;


import com.web.util.DateFormateUtils;
import com.web.vo.HolidayVo;

import java.sql.ResultSet;

/**
 * @Author cy
 * @Time 19-12-13 上午10:33
 **/
public class HolidayVoMapper implements RowMapper<HolidayVo> {

    @Override
    public HolidayVo mapperObject(ResultSet rs) throws Exception {
        HolidayVo holidayVo = new HolidayVo();
        holidayVo.setId(rs.getInt("A.id"));
        holidayVo.setHolidayNo(rs.getString("A.t_holiday_no"));
        holidayVo.setHolidayUser(rs.getInt("A.t_holiday_user_id"));
        holidayVo.setHolidayType(rs.getInt("A.t_holiday_type_id"));
        holidayVo.setHolidayBz(rs.getString("A.t_holiday_bz"));
        holidayVo.setStartTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("A.t_start_time")));
        holidayVo.setEndTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("A.t_end_time")));
        holidayVo.setHolidayStatus(rs.getInt("A.t_holiday_status_id"));
        holidayVo.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("A.t_create_time")));
        holidayVo.setEmpName(rs.getString("B.t_emp_name"));
        holidayVo.setConfigKey(rs.getString("C.t_config_page_value"));
        holidayVo.setConfigPageValue(rs.getString("D.t_config_page_value"));
        return holidayVo;
    }
}
