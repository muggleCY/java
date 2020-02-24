package com.web.dao;

import com.web.entity.Holiday;

import java.util.List;

public interface HolidayDao {
    public List<Holiday> selectHolidays();
    public Integer countHoliday(Integer holidayUser, Integer holidayType, Integer holidayStatus);
    public List<Holiday> selectHolidaysByPage(Integer holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo, Integer pageSize);
    public Holiday selectOneHoliday(Integer id);
    public void updateHoliday(Holiday holiday);
    public void deleteHoliday(Integer id);
    public void insertHoliday(Holiday holiday);
    public String getMaxHolidayNo();
}
