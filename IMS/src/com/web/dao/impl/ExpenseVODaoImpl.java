package com.web.dao.impl;

import com.web.dao.ExpenseVODao;
import com.web.mapper.ExpenseVOMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.ExpenseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class ExpenseVODaoImpl implements ExpenseVODao {
    JDBCTemplate<ExpenseVO> template = new JDBCTemplate<>();
    @Override
    public List<ExpenseVO> selectAllExpenseRecByPager(Integer pageNumber,Integer pageSize) {
//        根据申请人的id去找到申请人的姓名,根据报销类型去找到报销类型在页面的显示类型,去找到申请状态的页面表示数据
        //三表查询
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_expense_num, A.t_req_person_id, A.t_expense_type_id, A.t_expense_money, A.t_req_time, A.t_req_state,A.t_create_time,A.t_summary_exp,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	ims.t_expense A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D ")
                .append(" where ")
                .append("   A.t_req_person_id = B. id ")
                .append(" and A.t_expense_type_id = C.t_config_key")
                .append(" and A.t_req_state = D.t_config_key")
                .append(" limit ?,? ")
                .toString();
        return template.selectAll(new ExpenseVOMapper(),sql,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public ExpenseVO selectOneExpenseRecById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_expense_num, A.t_req_person_id, A.t_expense_type_id, A.t_expense_money, A.t_req_time, A.t_req_state,A.t_create_time,A.t_summary_exp,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	ims.t_expense A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D ")
                .append(" where ")
                .append("   A.t_req_person_id = B. id ")
                .append(" and A.t_expense_type_id = C.t_config_key")
                .append(" and A.t_req_state = D.t_config_key")
                .append(" and A.id = ? ")
                .toString();
        return template.selectOne(new ExpenseVOMapper(),sql,id);
    }

    @Override
    public List<ExpenseVO> selectAllExpenseRecByPagerAndCodi(Integer pageNumber, Integer pageSize, Integer typeNo, Integer stateNo) {

        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_expense_num, A.t_req_person_id, A.t_expense_type_id, A.t_expense_money, A.t_req_time, A.t_req_state,A.t_create_time,A.t_summary_exp,B.t_emp_name,C.t_config_page_value,D.t_config_page_value ")
                .append(" from ")
                .append("	ims.t_expense A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D ")
                .append(" where 1=1 ")
                .append(" and A.t_req_person_id = B. id ")
                .append(" and A.t_expense_type_id = C.t_config_key")
                .append(" and A.t_req_state = D.t_config_key");

        List params = new ArrayList();
        if (typeNo!=-1) {
            sql.append(" and A.t_expense_type_id = ? ");
            params.add(typeNo);
        }
        if (stateNo!=-1){
            sql.append(" and A.t_req_state = ?");
            params.add(stateNo);
        }
        sql.append(" limit ?,? ");
        params.add((pageNumber-1)*pageSize);
        params.add(pageSize);
        return template.selectAll(new ExpenseVOMapper(),sql.toString(),params.toArray());
    }
}
