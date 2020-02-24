package com.web.service;

import com.web.entity.User;
import com.web.exception.ExpAddException;
import com.web.exception.ExpDeleteException;
import com.web.util.Pager;
import com.web.vo.ExpenseVO;

public interface ExpenseService {
    Pager<ExpenseVO> showAllExpenseByPager(Integer page);
    Pager<ExpenseVO> showAllExpenseByPagerByCondi(Integer page, Integer typeNo, Integer stateNo);
    ExpenseVO getOneExpenseVO(Integer id);
    void modifyExpense(Integer expId, Integer expType,String expSummary, Double expMoney);
    void removeExpense(Integer id) throws ExpDeleteException;

    void addOneExpense( Integer expType, String expCont, Double expMoney, User user,Integer state) throws ExpAddException,Exception;
}
