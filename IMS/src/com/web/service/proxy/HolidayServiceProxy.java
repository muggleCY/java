package com.web.service.proxy;

import com.web.entity.Holiday;
import com.web.exception.HolidayInsertFailException;
import com.web.exception.HolidayIsSubmitException;
import com.web.service.HolidayService;
import com.web.trans.Transaction;
import com.web.util.Pager;
import com.web.vo.HolidayVo;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-11 下午4:23
 **/
public class HolidayServiceProxy implements HolidayService {
    private HolidayService holidayService;
    private Transaction trans;
    @Override
    public List<Holiday> selectHolidays() {
        List<Holiday> holidays = holidayService.selectHolidays();
        return holidays;
    }

    @Override
    public Pager<HolidayVo> selectHolidaysByPage(Integer id, String holidayUser, Integer holidayType, Integer holidayStatus, Integer pageNo) {
        Pager<HolidayVo> pager = holidayService.selectHolidaysByPage(id,holidayUser,holidayType,holidayStatus,pageNo);
        return pager;
    }

    @Override
    public HolidayVo selectOneHoliday(Integer id) {
        HolidayVo holidayVo = holidayService.selectOneHoliday(id);
        return holidayVo;

    }

    @Override
    public void updateHoliday(Integer id,String holidayNo,Integer holidayUser,Integer holidayType,String holidayBz,String startTime,String endTime,Integer holidayStatus) throws HolidayIsSubmitException {
        trans.begin();
        try {
            holidayService.updateHoliday(id,holidayNo,holidayUser,holidayType,holidayBz,startTime,endTime,holidayStatus);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            throw e;
        }
    }

    @Override
    public void deleteHoliday(Integer id) throws HolidayIsSubmitException {
        trans.begin();
        try {
            holidayService.deleteHoliday(id);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            throw e;
        }
    }

    @Override
    public void insertHoliday(Integer empNo,Integer holidayType,String holidayBz,String startTime,String endTime,Integer status) throws HolidayInsertFailException,Exception{
        trans.begin();
        try {
            holidayService.insertHoliday(empNo,holidayType,holidayBz,startTime,endTime,status);
            trans.commit();
        } catch (HolidayInsertFailException holidayInsertFail) {
            holidayInsertFail.printStackTrace();
            throw holidayInsertFail;
        } catch (Exception e){
            trans.rollback();
            throw e;
        }
    }

    @Override
    public void addHoliday(Integer empNo, Integer holidayType, Integer holidayStatus, String startTime, String endTime, String reqReason) throws Exception {

    }

    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }
}
