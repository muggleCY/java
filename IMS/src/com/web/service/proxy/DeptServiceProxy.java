package com.web.service.proxy;


import com.web.entity.Dept;
import com.web.exception.DeptAddException;
import com.web.service.DeptService;
import com.web.trans.Transaction;
import com.web.vo.DeptVO;

import java.util.List;

@lombok.Setter
public class DeptServiceProxy implements DeptService {
	private DeptService deptService;
	private Transaction trans;


	@Override
	public List<DeptVO> selectDepts() throws Exception {
		return null;
	}

	@Override
	public Dept selectDeptById(Integer id) throws Exception {
		return null;
	}


	public void deleteDept(Integer id) throws Exception {
		trans.begin();
		try {
			deptService.deleteDept(id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void addDept(String deptName, String deptLoc, String deptMaster,String deptDesc) throws DeptAddException, Exception {
		trans.begin();
		try {
			deptService.addDept(deptName,deptLoc,deptMaster,deptDesc);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}

	public void updateDept(Integer id,String deptName,String deptLoc,String deptMaster,String deptDesc) throws Exception {
		trans.begin();
		try {
			deptService.updateDept(id,deptName,deptLoc,deptMaster,deptDesc);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
}
