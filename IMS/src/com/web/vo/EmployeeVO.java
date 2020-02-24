package com.web.vo;

import com.web.entity.Employee;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class EmployeeVO extends Employee {
    private String deptName;
}
