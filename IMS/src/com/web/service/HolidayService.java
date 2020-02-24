package com.web.service;


import com.web.entity.Holiday;
import com.web.exception.HolidayInsertFailException;
import com.web.exception.HolidayIsSubmitException;
import com.web.util.Pager;
import com.web.vo.HolidayVo;

import java.util.List;

public interface HolidayService {
    List<Holiday> selectHolidays();
    Pager<HolidayVo> selectHolidaysByPage(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo);
    HolidayVo selectOneHoliday(Integer id);
    void updateHoliday(Integer id, String holidayNo, Integer holidayUser, Integer holidayType, String holidayBz, String startTime, String endTime, Integer holidayStatus) throws HolidayIsSubmitException;
    void deleteHoliday(Integer id) throws HolidayIsSubmitException;
    void insertHoliday(Integer empNo, Integer holidayType, String holidayBz, String startTime, String endTime, Integer status) throws HolidayInsertFailException,Exception;
    void addHoliday(Integer empNo, Integer holidayType, Integer holidayStatus, String startTime, String endTime, String reqReason) throws Exception;
}
