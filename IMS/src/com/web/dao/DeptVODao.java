package com.web.dao;

import com.web.vo.DeptVO;

import java.util.List;

/**
 * @Author zyb
 * @Date 2019/12/15 13:07
 **/
public interface DeptVODao {
    List<DeptVO> getAllDept();
    DeptVO selectDeptById(Integer id);
}
