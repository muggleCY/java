package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.DeptDao;
import com.web.dao.DeptVODao;
import com.web.dao.EmployeeDao;
import com.web.dao.MaxNoDao;
import com.web.dao.impl.DeptDaoImpl;
import com.web.entity.Dept;
import com.web.entity.Employee;
import com.web.exception.DeptAddException;
import com.web.exception.DeptModifyException;
import com.web.exception.DetpDeleteException;
import com.web.service.DeptService;
import com.web.util.DateFormateUtils;
import com.web.util.GetNoUtils;
import com.web.vo.DeptVO;

import java.util.List;

@lombok.Setter
public class DeptServiceImpl implements DeptService {
	private DeptDao deptDao;
	private DeptVODao deptVODao;
	private EmployeeDao employeeDao;
	private MaxNoDao maxNoDao;
	//查找所有部门数据
	@Override
	public List<DeptVO> selectDepts() throws Exception {

//		组装一个VO供页面显示
		List<DeptVO> deptVOS = null;
		try {
			deptVOS = deptVODao.getAllDept();
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙："+e.getMessage());
		}//阻止原生报错直接出现在页面上
		
		return deptVOS;
	}
	//查找某个部门信息
	@Override
	public Dept selectDeptById(Integer id) throws Exception {
		DeptVO deptVO = null;
		try {
			deptVO = deptVODao.selectDeptById(id);
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙："+e.getMessage());
		}//阻止原生报错直接出现在页面上
		return deptVO;
	}
	//删除部门
	@Override
	public void deleteDept(Integer id) throws DetpDeleteException,Exception {

		try {
			//考虑部门和员工的关联关系
//			删除部门之前，如果该部门下还有除了部门经理之外的员工，则不允许删除
			//如果该部门中只剩下部门经理的话，删除该部门之前，先将部门经理的部门id置为null
			List<Employee> employees = employeeDao.selectEmpByDeptId(id);
			if (employees.size()!=1){
				//说明除了部门经理之外,还有其他人
				throw new DetpDeleteException(ExceptionConstant.DEPT_HAS_EMP);
			}else {
				Employee managerEmp = null;
				for (Employee employee : employees) {
					if (employee.getEmpSuperId()==null){
						managerEmp = employee;
					}
				}
				//找到部门经理之后,将该经理的deptid变为null
				managerEmp.setEmpDeptId(null);
				employeeDao.updateEmp(managerEmp);
			}
			deptDao.deleteDept(id);
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙："+e.getMessage());
		}	
	}

	//增加部门
	@Override
	public void addDept( String deptName, String deptLoc, String deptMaster,String deptDesc) throws DeptAddException,Exception {
		try {
			//添加部门之前,部门编号不可重复,部门名称不可重复,

			//生成部门编号
			String deptNo = GetNoUtils.getDeptNo(maxNoDao.selectDeptNoMax());

			if (deptDao.selectDeptByDeptName(deptName)!=null){
				throw new DeptAddException(ExceptionConstant.DEPT_NAME_EXIST);
			}
//			部门经理不能是其他部门经理或者不存在的员工
			Employee employee = employeeDao.selectEmpByEmpName(deptMaster);
			if (employee==null){
				throw new DeptAddException(ExceptionConstant.DEPT_MANAGER_DOES_NOT_EXISIT);
			}
			//员工serperid和empid都为null,代表该员工不属于任何部门
			if (employee.getEmpSuperId()==null && employee.getEmpDeptId()!=null){
				throw new DeptAddException(ExceptionConstant.DEPT_MANAGER_DOES_MATCH);
			}
			Dept dept = new Dept();
			dept.setDeptNo(deptNo);
			dept.setDeptName(deptName);
			dept.setDeptLoc(deptLoc);
			dept.setDeptDesc(deptDesc);
			dept.setDeptCreateTime(DateFormateUtils.getNowTime());
			deptDao.insertDept(dept);
			//添加部门经理
			employee.setEmpSuperId(null);
			employee.setEmpDeptId(deptDao.selectDeptByDeptNo(deptNo).getId());
			employeeDao.updateEmp(employee);
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙："+e.getMessage());
		}
	}
	//修改部门
	@Override
	public void updateDept(Integer id,String deptName,String deptLoc,String deptMaster,String deptDesc) throws DeptModifyException,Exception {
		try {
			//			根据id,从数据库中拿到部门信息
			Dept dept = deptDao.selectDeptById(id);
			Boolean state = false;
			//如果修改了部门名称
			if (!dept.getDeptName().equals(deptName)){
				dept.setDeptName(deptName);
				state=true;
			}
			//如果修改了部门位置
			if (!dept.getDeptLoc().equals(deptLoc)){
				dept.setDeptLoc(deptLoc);
				state = true;
			}
//			如果修改了部门描述
			if (!dept.getDeptDesc().equals(deptDesc)){
				dept.setDeptDesc(deptDesc);
				state = true;
			}
			//如果修改了经理
			//拿到该部门经理
			Employee empManager = employeeDao.selectDeptMangerByDeptId(id);
			if (!empManager.getEmpName().equals(deptMaster)){
				state = true;
				//判断deptMaster是否合法化
				Employee employee = employeeDao.selectEmpByEmpName(deptMaster);
				//如果该人不存在,则抛出异常
				if (employee==null){
					throw new DeptModifyException(ExceptionConstant.DEPT_MANAGER_DOES_NOT_EXISIT);
				}
				//添加该人是否为以其他部门的部门经理,如果是,则抛出异常,
				if (employee.getEmpSuperId()==null && employee.getEmpDeptId()!=id){
					throw  new DeptModifyException(ExceptionConstant.DEPT_MANAGER_DOES_MATCH);
				}
				//如果该人不是经理,则将当前部门经理的super_id设置为,新添加进来的员工的id
				if (employee.getEmpDeptId()!=null){
					//新部门经理的部门id,为当前部门的id
					employee.setEmpDeptId(dept.getId());
					//上级id为null
					employee.setEmpSuperId(null);
					//原来部门经理的superId改成当前经理的id
					empManager.setEmpSuperId(employee.getId());
				}
				//修改新部门经理的信息
				employeeDao.updateEmp(employee);
				employeeDao.updateEmp(empManager);
//			更新该部门下所有的员工的上级领导
				employeeDao.updateSuperIdByDeptId(employee.getId(),dept.getId());
			}
			if (!state) {
				throw new DeptModifyException(ExceptionConstant.DETP_MOFIFY_NONE);
			}
//			更新部门信息
			deptDao.updateDept(dept);
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null&&!e.getMessage().equals("")) {
				throw e;
			}
			throw new Exception("服务器正忙："+e.getMessage());
		}
		
	}
}
