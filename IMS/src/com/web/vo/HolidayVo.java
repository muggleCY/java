package com.web.vo;

import com.web.entity.Holiday;

/**
 * @Author cy
 * @Time 19-12-13 上午10:27
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class HolidayVo extends Holiday {
    private String empName;
    private String configKey;
    private String configPageValue;


}
