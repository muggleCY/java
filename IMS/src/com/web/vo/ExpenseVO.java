package com.web.vo;

import com.web.entity.Expense;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ExpenseVO extends Expense {
    /**
     * 申请人的姓名
     */
    private String reqPerName;
    /**
     * 报销类型
     */
    private String expenseTypeName;
    /**
     * 申请状态
     */
    private String reqStateName;


    public String getReqPerName() {
        return reqPerName;
    }

    public void setReqPerName(String reqPerName) {
        this.reqPerName = reqPerName;
    }

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        this.expenseTypeName = expenseTypeName;
    }

    public String getReqStateName() {
        return reqStateName;
    }

    public void setReqStateName(String reqStateName) {
        this.reqStateName = reqStateName;
    }
}
