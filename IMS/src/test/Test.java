package test;

import com.web.dao.impl.EmployeeVODaoImpl;
import com.web.dao.impl.MaxNoDaoImpl;
import com.web.util.GetNoUtils;
import com.web.vo.EmployeeVO;

/**
 * @Author zyb
 * @TIME 19-12-10
 **/
public class Test {
    public static void main(String[] args) {
//        User user = new UserDaoImpl().selectUserNameByUsernameAndPwd("admin", "admin");
//        System.out.println(user);
//        List<Employee> employees = new EmployeeDaoImpl().selectAllEmp();
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
//        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//        employeeService.setCountDao(new CountDaoImpl());
//        employeeService.setEmployeeDao(new EmployeeDaoImpl());
//        employeeService.setEmployeeVODao(new EmployeeVODaoImpl());
//        Pager<Employee> empListByPage = employeeService.getEmpListByPage(1);
//        Pager<EmployeeVO> empVOListByPage = employeeService.getEmpVOListByPage(2);
//        for (EmployeeVO employeeVO : empVOListByPage.getList()) {
//            System.out.println(employeeVO);
//        }
//        System.out.println(empListByPage.getList());
//        List<EmployeeVO> employeeVOS = new EmployeeVODaoImpl().selectAllEmp();
//        for (EmployeeVO employeeVO : employeeVOS) {
//            System.out.println(employeeVO);
//        }
//        List<ExpenseVO> expenseVOS = new ExpenseVODaoImpl().selectAllExpenseRecByPager(2, 5);
//        for (ExpenseVO expenseVO : expenseVOS) {
//            System.out.println(expenseVO.getId());
//            System.out.println(expenseVO);
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println(NumberGenUtils.getExpenseNumber());
//        }
//        new ExpenseVODaoImpl().selectOneExpenseRecById(3);
//        List<MenuVO> menuVOS = new MenuVOImpl().selectCurrentMenuByRoleId(2);
//        for (MenuVO menuVO : menuVOS) {
//            System.out.println(menuVO);
//        }
//        RoleService roleService = (RoleService)ObjectFactory.getBean("roleService");
//        List<Role> roles = roleService.selectRoles();
//        for (Role role:roles) {
//            System.out.println(role);
//        }
//        RoleDao roleDao = new RoleDaoImpl();
//        List<Role> roles = roleDao.selectRoles();
//        for (Role role:roles) {
//            System.out.println(role);
//       }
//        UserDao userDao = new UserDaoImpl();
//        List<User> users = userDao.selectUsersByRoleId(1);
//        for (User user:users
//             ) {
//            System.out.println(user);
//        }
//        PermissionService permissionService = (PermissionService)ObjectFactory.getBean("permissionService");
//        Pager<PermissionVo> roles = permissionService.selectAllPerByPage(null,null,1);
//        for (PermissionVo user:roles.getList()
//             ) {
//            System.out.println(user);
//        }
//        Integer integer = new CountDaoImpl().countEmployeeVOAndCodi(null, "");
//        System.out.println(integer);
//        String stringTimeFromObj = DateFormateUtils.getStringTimeFromObj("2019-12-03");
//        System.out.println(stringTimeFromObj);
//        new java.util.Date()
//        System.out.printf(NumberGenUtils.getEmpNumber());
//        System.out.println(GetNoUtils.getEmpNo());
//        System.out.println(GetNoUtils.getEmpNo(100));
//        Integer integer = new MaxNoDaoImpl().selectEmpNoMax();
//        System.out.println(integer);
//        MaxNoDaoImpl maxNoDao = new MaxNoDaoImpl();
//        System.out.println(maxNoDao.selectDeptNoMax());
//        System.out.println(maxNoDao.selectExpenseNoMax());
//        System.out.println(maxNoDao.selectHolidayNoMax());
        EmployeeVO employeeVO = new EmployeeVODaoImpl().selectEmpByEmpId(1);
        System.out.println(employeeVO);
        System.out.println("dafdfa.css".endsWith("css"));
    }
}
