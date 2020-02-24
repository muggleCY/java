package com.web.service.impl;



import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.EmployeeDao;
import com.web.dao.HolidayDao;
import com.web.dao.HolidayVoDao;
import com.web.entity.Employee;
import com.web.entity.Holiday;
import com.web.exception.HolidayInsertFailException;
import com.web.exception.HolidayIsSubmitException;
import com.web.service.HolidayService;
import com.web.util.HolidayNoUtil;
import com.web.util.Pager;
import com.web.vo.HolidayVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-11 下午3:46
 **/
public class HolidayServiceImpl implements HolidayService {
    private HolidayDao holidayDao;
    private HolidayVoDao holidayVoDao;
    private EmployeeDao employeeDao;
    @Override
    public List<Holiday> selectHolidays() {
        List<Holiday> holidays = holidayDao.selectHolidays();
        return holidays;
    }

    @Override
    public Pager<HolidayVo> selectHolidaysByPage(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo) {
        Pager<HolidayVo> pager = new Pager<HolidayVo>();
        pager.setPageNo(pageNo);
        Integer totalCount = holidayVoDao.countHoliday(holidayUser,holidayType,holidayStatus);
        if(totalCount == null){
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, Constants.PAGE_SIZE_3);
        //判断是哪个用户查询
        Employee employee = employeeDao.selectEmpById(id);
        List<HolidayVo> holidays = new ArrayList<HolidayVo>();
        if(employee == null){
            holidays = holidayVoDao.selectHolidaysByPage(null,holidayUser,holidayType,holidayStatus,pageNo, Constants.PAGE_SIZE_3);
        } else if(employee.getEmpSuperId() == null){
            Integer deptId = employee.getEmpDeptId();
            holidays = holidayVoDao.selectHolidaysByPage(deptId,holidayUser,holidayType,holidayStatus,pageNo, Constants.PAGE_SIZE_3);
        }else{
            totalCount = holidayVoDao.countHolidayById(id,holidayUser,holidayType,holidayStatus);
            if(totalCount == null){
                totalCount = 0;
            }
            pager.setTotalPage(totalCount, Constants.PAGE_SIZE_3);
            holidays = holidayVoDao.selectHolidaysByPageAndId(id,holidayUser,holidayType,holidayStatus,pageNo, Constants.PAGE_SIZE_3);
        }
        //请假的时间需要修改
        pager.setList(holidays);
        return pager;
    }

    @Override
    public HolidayVo selectOneHoliday(Integer id) {
        HolidayVo holidayVo = holidayVoDao.selectOneHoliday(id);
        return holidayVo;
    }

    @Override
    public void updateHoliday(Integer id,String holidayNo,Integer holidayUser,Integer holidayType,String holidayBz,String startTime,String endTime,Integer holidayStatus) throws HolidayIsSubmitException {
        //如果请假已经是提交状态,不允许修改
//        if(holiday.getHolidayStatus().equals("5")){
//            throw new HolidayIsSubmitException(ExceptionConstant.HOLIDAY_IS_SUBMIT_M);
//        }
        //如果完全没有改动,不允许提交到数据库(页面完成)
        Holiday holiday = new Holiday();
        holiday.setId(id);
        holiday.setHolidayNo(holidayNo);
        holiday.setHolidayUser(holidayUser);
        holiday.setHolidayType(holidayType);
        holiday.setHolidayBz(holidayBz);
        holiday.setStartTime(startTime);
        holiday.setEndTime(endTime);
        holiday.setHolidayStatus(holidayStatus);
        holidayDao.updateHoliday(holiday);
    }

    @Override
    public void deleteHoliday(Integer id)  throws HolidayIsSubmitException {
        Holiday holiday = holidayDao.selectOneHoliday(id);
        //如果请假已经提交,则不允许被删除
        if(holiday.getHolidayStatus().equals("5")){
            throw new HolidayIsSubmitException(ExceptionConstant.HOLIDAY_IS_SUBMIT_D);
        }
        holidayDao.deleteHoliday(id);
    }

    @Override
    public void insertHoliday(Integer empNo,Integer holidayType,String holidayBz,String startTime,String endTime,Integer status) throws HolidayInsertFailException {
//        Employee employee = employeeDao.selectEmpByEmpNo(empNo);
        Employee employee = employeeDao.selectEmpById(empNo);
        if(employee == null||employee.getEmpSuperId() == null){
            throw new HolidayInsertFailException(ExceptionConstant.ADMIN_FORBI);
        }
        Holiday holiday = new Holiday();
        holiday.setHolidayNo(new HolidayNoUtil().getNexHolidayNo());
        holiday.setHolidayUser(empNo);
        holiday.setHolidayType(holidayType);
        holiday.setHolidayBz(holidayBz);
        holiday.setStartTime(startTime);
        holiday.setEndTime(endTime);
        holiday.setHolidayStatus(status);
        holidayDao.insertHoliday(holiday);
    }

    @Override
    public void addHoliday(Integer empNo, Integer holidayType, Integer holidayStatus, String startTime, String endTime,String reqReason) {
        //判断开始时间是否小于结束时间,如果是,抛出异常

    }


    public HolidayDao getHolidayDao() {
        return holidayDao;
    }

    public void setHolidayDao(HolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

    public HolidayVoDao getHolidayVoDao() {
        return holidayVoDao;
    }

    public void setHolidayVoDao(HolidayVoDao holidayVoDao) {
        this.holidayVoDao = holidayVoDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
