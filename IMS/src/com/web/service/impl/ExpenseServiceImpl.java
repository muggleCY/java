package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.*;
import com.web.entity.Employee;
import com.web.entity.Expense;
import com.web.entity.User;
import com.web.exception.ExpAddException;
import com.web.exception.ExpDeleteException;
import com.web.service.ExpenseService;
import com.web.util.DateFormateUtils;
import com.web.util.GetNoUtils;
import com.web.util.Pager;
import com.web.vo.ExpenseVO;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
@lombok.Setter
public class ExpenseServiceImpl implements ExpenseService {
    private CountDao countDao;
    private ExpenseVODao expenseVODao;
    private ExpenseDao expenseDao;
    private MaxNoDao maxNoDao;
    @Override
    public Pager<ExpenseVO> showAllExpenseByPager(Integer pageNumber) {
        List<ExpenseVO> expenseVOS = expenseVODao.selectAllExpenseRecByPager(pageNumber, Constants.PAGE_SIZE_3);
        Pager<ExpenseVO> pager = new Pager<>();
        pager.setList(expenseVOS);
        pager.setPageNo(pageNumber);
        pager.setTotalPage(countDao.countExpenseVO(),Constants.PAGE_SIZE_3);
        return pager;
    }

    @Override
    public Pager<ExpenseVO> showAllExpenseByPagerByCondi(Integer pageNumber, Integer typeNo, Integer stateNo) {
        List<ExpenseVO> expenseVOS = expenseVODao.selectAllExpenseRecByPagerAndCodi(pageNumber,
                Constants.PAGE_SIZE_3,typeNo,stateNo);
        Pager<ExpenseVO> pager = new Pager<>();
        pager.setList(expenseVOS);
        pager.setPageNo(pageNumber);
        pager.setTotalPage(countDao.countExpenseVO(),Constants.PAGE_SIZE_3);
        return pager;
    }

    @Override
    public ExpenseVO getOneExpenseVO(Integer id) {
        return expenseVODao.selectOneExpenseRecById(id);
    }

    @Override
    public void modifyExpense(Integer expId, Integer expType, String expSummary, Double expMoney) {
        //如果什么都没修改,不允许访问数据库(前台实现)

        //从数据库总取出该报销单,更新内容,放回数据库
        Expense expense = expenseDao.selectExpenseById(expId);
        expense.setExpenseTypeId(expType);
        expense.setSummaryExp(expSummary);
        expense.setExpenseMoney(expMoney);
        expenseDao.updateExpense(expense);
    }


    @Override
    public void removeExpense(Integer id) throws ExpDeleteException {
        //已经提交的报销不允许删除
        ExpenseVO expenseVO = expenseVODao.selectOneExpenseRecById(id);
        if ("已提交".equals(expenseVO.getReqStateName())){
            throw  new ExpDeleteException(ExceptionConstant.CAN_NOT_DELTE_SUBMIT_EXP);
        }
        expenseDao.deleteExpense(id);
    }

    @Override
    public void addOneExpense(Integer expType, String expCont, Double expMoney, User user,Integer state) throws ExpAddException, Exception {
        //格式正确与否在前端判断
        String expNO = GetNoUtils.getExpNO(maxNoDao.selectExpenseNoMax());
        Expense expense = new Expense();
        expense.setExpenseNum(expNO);
        expense.setReqPersonId(user.getEmpNo());
        expense.setExpenseTypeId(expType);
        expense.setExpenseMoney(expMoney);
        expense.setReqTime(DateFormateUtils.getNowTime());
        expense.setReqState(state);
        expense.setCreateTime(DateFormateUtils.getNowTime());
        expense.setSummaryExp(expCont);

        try {
            expenseDao.addExpense(expense);
        }catch (Exception e){
            e.printStackTrace();
            throw new ExpAddException(ExceptionConstant.SYS_ERROR);
        }


    }


}
