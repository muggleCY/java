package com.web.constant;

public interface ExceptionConstant {
    String UNAME_OR_PWD_ERROR ="用户名密码错误";
    String CAN_NOT_DELTE_SUBMIT_EXP="不能删除已经提交的报销单";
    String HOLIDAY_IS_SUBMIT_M = "请假已经提交不能修改";
    String HOLIDAY_IS_SUBMIT_D = "请假已经提交不能删除";
    String DEPT_HAS_EMP = "该部门下还有员工关联，不允许删除！";
    String ROLE_HAVE_USER = "角色下还有用户不允许删除";
    String PERMISS_DELETE_FAIL = "该权限为系统默认，不可删除";
    String PERMISS_ALREADY_EXIST = "该权限已经存在，请勿重复添加";
    String DEPT_MANAGER_DOES_NOT_EXISIT = "需要添加的部门经理不存在";
    String DEPT_MANAGER_DOES_MATCH = "该员工已经是其他部门的部门经理";
    String DETP_MOFIFY_NONE = "未修改任何部门信息";
    String DEPT_NO_EXIST = "部门编号已经存在";
    String DEPT_NAME_EXIST = "部门名已经存在";
    String EMP_DELTE_FAIL = "该员工是部门经理,请先移除该部门";
    String EMP_NO_EXISIT = "员工编号已经存在";
    String EMP_Name_EXISIT = "员工姓名已经存在";
    String DEPT_DOES_NOT_EXISIT = "部门不存在";
    String SYS_ERROR = "系统错误";
    String HOLIDAY_INSERT_FAIL = "不能添加";
    String EMP_MANAGER_FORBI_MODIFY_DEPT = "部门经理禁止修改部门";
    String ROLE_ALREADY_EXIST = "角色已经存在";
    String EMP_NP_NO_EXIST = "员工不存在";
    String USER_EXIST = "账户已经存在";
    String USER_LOGOUT = "账户已经注销";
    String ADMIN_FORBI = "管理员禁止请假";
}
