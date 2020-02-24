package com.web.dao;

import com.web.vo.ExpenseVO;

import java.util.List;

/**
 * 为页面提供报销单的显示信息
 */
public interface ExpenseVODao {
    /**
     * 获取所有的报销单
     * @return
     */
    List<ExpenseVO> selectAllExpenseRecByPager(Integer pageNumber, Integer pageSize);

    /**
     * 根据id获取一张报销单
     * @param id
     * @return
     */
    ExpenseVO selectOneExpenseRecById(Integer id);

    List<ExpenseVO> selectAllExpenseRecByPagerAndCodi(Integer pageNumber, Integer pageSize, Integer typeNo, Integer stateNo);
}
