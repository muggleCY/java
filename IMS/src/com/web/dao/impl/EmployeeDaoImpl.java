package com.web.dao.impl;

import com.web.dao.EmployeeDao;
import com.web.entity.Employee;
import com.web.mapper.EmployeeMapper;
import com.web.util.JDBCTemplate;
import com.web.util.Pager;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class EmployeeDaoImpl implements EmployeeDao {
    JDBCTemplate<Employee> temp = new JDBCTemplate<Employee>();
    @Override
    public List<Employee> selectAllEmp() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_emp_no, t_emp_name, t_emp_dept_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time, t_modify_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .toString();
        return temp.selectAll(new EmployeeMapper(),sql);
    }

    @Override
    public List<Employee> selectAllEmpByPager(Integer pageNumber,Integer pageSize) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_emp_no, t_emp_name, t_emp_dept_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time, t_modify_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" limit ?,? ")
                .toString();
        return temp.selectAll(new EmployeeMapper(),sql,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Employee> selectEmpByDeptId(Integer deptId) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where t_emp_dept_id = ?")
                .append(" and t_emp_state = 0 ")
                .toString();
        return temp.selectAll(new EmployeeMapper(),sql,deptId);
    }

    @Override
    public Employee selectDeptMangerByDeptId(Integer deptId) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where t_emp_dept_id = ?")
                .append(" and t_emp_super_id is null ")
                .append(" and t_emp_state = 0")
                .toString();
        return temp.selectOne(new EmployeeMapper(),sql,deptId);
    }

    @Override
    public Employee selectEmpById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where id = ?")
                .append(" and t_emp_state = 0 ")
                .toString();
        return temp.selectOne(new EmployeeMapper(),sql,id);
    }

    @Override
    public Employee selectEmpByEmpName(String empName) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where t_emp_name = ?")
                .append(" and t_emp_state = 0 ")
                .toString();
        return temp.selectOne(new EmployeeMapper(),sql,empName);
    }

    @Override
    public Employee selectEmyByEmpNo(String empNo) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where t_emp_no = ?")
                .append(" and t_emp_state = 0 ")
                .toString();
        return temp.selectOne(new EmployeeMapper(),sql,empNo);
    }

    @Override
    public void updateSuperIdByDeptId(Integer superId, Integer deptId) {
        String sql = new StringBuffer()
                .append(" update ")
                .append("	t_employee ")
                .append(" set ")
                .append("	t_emp_super_id = ? ")
                .append(" where ")
                .append("	t_emp_dept_id = ? ")
                .append(" and t_emp_super_id is not null ")
                .append(" and t_emp_state = 0 ")
                .toString();
        temp.update(sql,superId,deptId);
    }

    @Override
    public void insertEmp(Employee employee) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("   t_employee( ")
                .append(" 	t_emp_no, t_emp_name, t_emp_dept_id, t_sex, t_emp_super_id,t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ) ")
                .append(" values(?,?,?,?,?,?,?,?,?,?,?) ")
                .toString();
        temp.insert(sql,
                employee.getEmpNo(),
                employee.getEmpName(),
                employee.getEmpDeptId(),
                employee.getSex(),
                employee.getEmpSuperId(),
                employee.getEducation(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getEntryTime(),
                employee.getCreateTime(),
                employee.getEmpState());
    }

    @Override
    public void deleteEmp(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("	t_employee ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.delete(sql, id);
    }

    @Override
    public void updateEmp(Employee employee) {
//        , t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time
        String sql = new StringBuffer()
                .append(" update ")
                .append("	t_employee ")
                .append(" set ")
                .append("	t_emp_name = ?, ")
                .append("	t_emp_dept_id = ?, ")
                .append("	t_emp_super_id = ?, ")
                .append("	t_sex = ?,")
                .append("	t_education = ? ,")
                .append("	t_email = ? ,")
                .append("	t_phone = ? ,")
                .append("	t_entry_time = ? ,")
                .append("	t_create_time = ? ,")
                .append("   t_emp_state = ? ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.update(sql,employee.getEmpName(),employee.getEmpDeptId(),employee.getEmpSuperId(),
                employee.getSex(),employee.getEducation(),employee.getEmail(),employee.getPhone(),
                employee.getEntryTime(),employee.getCreateTime(),employee.getEmpState(),employee.getId());
    }

    @Override
    public Employee selectEmyByEmpNoAndName(String empNo, String empName) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state ")
                .append(" from ")
                .append("	t_employee ")
                .append(" where t_emp_no = ?")
                .append(" and t_emp_name = ?")
                .toString();
        return temp.selectOne(new EmployeeMapper(),sql,empNo,empName);
    }
}
