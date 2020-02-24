package com.web.entity;

/**
 * @Author cy
 * @Time 19-12-11 下午3:12
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Holiday {
    /**
     * 请假id
     */
    private Integer id;
    /**
     * 请假编号
     */
    private String holidayNo;
    /**
     * 请假user的id
     */
    private Integer holidayUser;
    /**
     * 请假的类型Id
     */
    private Integer holidayType;
    /**
     * 请假事由
     */
    private String holidayBz;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 请假申请的状态id
     */
    private Integer holidayStatus;
    /**
     * 创建时间
     */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHolidayNo() {
        return holidayNo;
    }

    public void setHolidayNo(String holidayNo) {
        this.holidayNo = holidayNo;
    }

    public Integer getHolidayUser() {
        return holidayUser;
    }

    public void setHolidayUser(Integer holidayUser) {
        this.holidayUser = holidayUser;
    }

    public Integer getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(Integer holidayType) {
        this.holidayType = holidayType;
    }

    public String getHolidayBz() {
        return holidayBz;
    }

    public void setHolidayBz(String holidayBz) {
        this.holidayBz = holidayBz;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getHolidayStatus() {
        return holidayStatus;
    }

    public void setHolidayStatus(Integer holidayStatus) {
        this.holidayStatus = holidayStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
