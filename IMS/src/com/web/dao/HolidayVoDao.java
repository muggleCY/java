package com.web.dao;

import com.web.vo.HolidayVo;

import java.util.List;

public interface HolidayVoDao {
    public Integer countHoliday(String holidayUser, Integer holidayType, Integer holidayStatus);
    public Integer countHolidayById(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus);
    public List<HolidayVo> selectHolidaysByPage(Integer deptId, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize);
    public List<HolidayVo> selectHolidaysByPageAndId(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize);
    public HolidayVo selectOneHoliday(Integer id);
}
