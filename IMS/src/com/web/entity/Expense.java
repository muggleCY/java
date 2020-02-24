package com.web.entity;

import java.util.Date;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Expense {
    /**
     * 报销id
     */
    private Integer id;
    /**
     * 报销号码
     */
    private String expenseNum;
    /**
     * 报销人的id
     */
    private Integer reqPersonId;
    /**
     * 报销的类型id
     */
    private Integer expenseTypeId;
    /**
     * 报销的总金额
     */
    private Double expenseMoney;
    /**
     * 请求报销的时间
     */
    private String reqTime;
    /**
     * 请求的状态id
     */
    private Integer reqState;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 报销详细内容
     */
    private String summaryExp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpenseNum() {
        return expenseNum;
    }

    public void setExpenseNum(String expenseNum) {
        this.expenseNum = expenseNum;
    }

    public Integer getReqPersonId() {
        return reqPersonId;
    }

    public void setReqPersonId(Integer reqPersonId) {
        this.reqPersonId = reqPersonId;
    }

    public Integer getExpenseTypeId() {
        return expenseTypeId;
    }

    public void setExpenseTypeId(Integer expenseTypeId) {
        this.expenseTypeId = expenseTypeId;
    }

    public Double getExpenseMoney() {
        return expenseMoney;
    }

    public void setExpenseMoney(Double expenseMoney) {
        this.expenseMoney = expenseMoney;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public Integer getReqState() {
        return reqState;
    }

    public void setReqState(Integer reqState) {
        this.reqState = reqState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSummaryExp() {
        return summaryExp;
    }

    public void setSummaryExp(String summaryExp) {
        this.summaryExp = summaryExp;
    }
}
