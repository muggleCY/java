package com.web.mapper;

import com.web.entity.Holiday;
import com.web.util.DateFormateUtils;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @Author cy
 * @Time 19-12-11 下午3:18
 **/
public class HolidayMapper implements RowMapper<Holiday> {

    @Override
    public Holiday mapperObject(ResultSet rs) throws Exception {

        Holiday holiday = new Holiday();
        holiday.setId(rs.getInt("id"));
        holiday.setHolidayNo(rs.getString("t_holiday_no"));
        holiday.setHolidayUser(rs.getInt("t_holiday_user_id"));
        holiday.setHolidayType(rs.getInt("t_holiday_type_id"));
        holiday.setHolidayBz(rs.getString("t_holiday_bz"));
        holiday.setStartTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_start_time")));
        holiday.setEndTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_end_time")));
        holiday.setHolidayStatus(rs.getInt("t_holiday_status_id"));
        holiday.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_create_time")));
        return holiday;
    }
}
