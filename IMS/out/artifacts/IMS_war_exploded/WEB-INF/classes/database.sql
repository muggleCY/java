# 创建数据库
create database if not exists ims default char set utf8;
# 没有外键的表 部门表 角色表 配置表 菜单表
# 依赖关系 员工表依赖于部门表  用户表依赖于角色表,员工表,配置表 请假表(报销表)依赖于员工和配置
# 权限表依赖于菜单和角色

# 创建角色表
create table ims.t_role
(
  id int primary key auto_increment,
  t_role_name varchar(30) not null,
  t_create_time datetime
)engine=Innodb;
# 添加角色表信息
insert into ims.t_role(id,t_role_name,t_create_time) values(1,'管理员',now());
insert into ims.t_role(id,t_role_name,t_create_time) values(2,'普通用户',now());
insert into ims.t_role(id,t_role_name,t_create_time) values(3,'人事专员',now());

# 配置表 （暂时）
# 主键 配置类型 配置项的key值 页面显示值 创建时间
create table ims.t_sys_config(
                               id int primary key auto_increment,
                               t_config_type varchar(20),
                               t_config_key int unique,
                               t_config_page_value varchar(10),
                               t_create_time datetime
)engine=Innodb;

# 添加配置表的信息
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqType',1,'差旅费',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqType',2,'招待费',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqType',3,'办公费',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqState',4,'草稿',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqState',5,'已提交',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqState',6,'正常',now());
insert into ims.t_sys_config(t_config_type, t_config_key, t_config_page_value, t_create_time)
values('reqState',7,'注销',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',11,'事假',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',12,'婚嫁',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',13,'年假',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',14,'调休',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',15,'病假',now());
insert into ims.t_sys_config(t_config_type,t_config_key,t_config_page_value,t_create_time)
values('leave',16,'丧假',now());

# 创建菜单表
create table ims.t_menu(
                         id int primary key,
                         t_menu_name varchar(50) not null,
                         t_href_url varchar(200),
                         t_parent_id int,
                         t_create_time datetime
)engine=Innodb;
# 添加菜单表
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(1,'人事管理','',null,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(2,'财务管理','',null,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(3,'系统管理','',null,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(4,'部门管理','njwb/dept/dept.jsp',1,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(5,'员工管理','njwb/emp/employee.jsp',1,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(6,'请假管理','njwb/holiday/holiday.jsp',1,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(7,'报销管理','njwb/fund/expense.jsp',2,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(8,'账户维护','njwb/user/user.jsp',3,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(9,'密码重置','njwb/sys/reset.jsp',3,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(10,'角色管理','njwb/sys/roleMan.jsp',3,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(11,'权限管理','njwb/sys/permissionMan.jsp',3,now());
insert into ims.t_menu(id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(12,'系统退出','clearLoginState()',3,now());


# 创建部门表
# 部门编号、部门名称、部门位置、部门负责人、创建时间
create table ims.t_dept(
                         id int primary key auto_increment,
                         t_dept_no char(5),
                         t_dept_name varchar(20) not null,
                         t_dept_loc varchar(30) not null,
                         t_dept_desc varchar(30),
                         t_create_time datetime
)engine=Innodb;
insert into ims.t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time)
values('A0001','一号部门','101','我是xxx',now());
insert into ims.t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time)
values('A0002','二号部门','102','我是ooo',now());
insert into ims.t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time)
values('A0003','三号部门','142','我是oox',now());
# 先添加部门信息，再去添加外键
# 主键,员工编号,员工姓名,所属部门，部门表的部门编号,
# 性别，0：女，1：男,学历,邮箱,联系方式,入职时间,创建时间,修改时间
create table ims.t_employee(
                             id int primary key auto_increment,
                             t_emp_no varchar(10),
                             t_emp_name varchar(20),
                             t_emp_dept_id int,
                             t_emp_super_id int,
                             t_sex varchar(1),
                             t_education varchar(10),
                             t_email varchar(20),
                             t_phone varchar(20),
                             t_entry_time datetime,
                             t_create_time datetime
)engine=Innodb;
alter table ims.t_employee
  add constraint t_employee_t_dept_id_fk
    foreign key (t_emp_dept_id) references ims.t_dept (id);
alter table t_employee
    add t_emp_state int null;
# 添加员工信息
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0001','张三',1,null,'男','本科','xxxxx@gamil.com','13812345678','2017-12-10 19:52:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0008','李四',2,null,'男','本科','xxxxx@outlook.com','1486234678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id,t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0002','王五',2,2,'男','专科','xxxxx@outlook.com','14234345678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0007','赵柳',2,2,'女','博士','xxxxx@btrhg.com','14862345678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0009','答复阿',1,1,'男','硕士','xxxxx@asdfe.com','148623342678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0010','网四',3,null,'男','本科','xxxxx@daee.com','14862345678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0003','李斯',3,1,'男','本科','xxxxx@qq.com','1482345678','2019-12-10 19:43:11',now(),0);
insert into ims.t_employee(t_emp_no, t_emp_name, t_emp_dept_id,t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time,t_emp_state)
values('Y0011','李大地',null,null,'男','本科','xxxxx@qq.com','1482345678','2019-12-10 19:43:11',now(),1);

# 创建用户表
# id,用户账号,用户密码,所属员工编号,角色(角色表的id)
create table ims.t_user(
                         id int primary key  auto_increment,
                         t_user_account varchar(20) not null ,
                         t_user_pwd varchar(20) not null,
                         t_emp_no int,
                         t_role_id int,
                         t_user_status_id int,
                         t_create_time datetime
)engine=Innodb;
# 添加t_user的外键
# 添加t_role_id的外键
# 添加用户状态的外键
alter table ims.t_user
  add constraint  t_user_t_role_id_fk
    foreign key (t_role_id) references ims.t_role(id);
alter table ims.t_user
  add constraint t_usr_t_emp_no_fk
    foreign key (t_emp_no) references ims.t_employee(id);
alter table ims.t_user
  add constraint t_user_status_id_fk
    foreign key (t_user_status_id) references ims.t_sys_config(t_config_key);

# 添加管理员
insert into ims.t_user(t_user_account, t_user_pwd, t_emp_no, t_role_id, t_user_status_id, t_create_time)
values('admin','admin',null,1,6,now()) ;

# 添加用户
insert into ims.t_user(t_user_account, t_user_pwd, t_emp_no, t_role_id, t_user_status_id, t_create_time)
values('zs','123',2,2,6,now());

# 已经注销的用户
insert into ims.t_user(t_user_account, t_user_pwd, t_emp_no, t_role_id, t_user_status_id, t_create_time)
values('w5','123',4,2,7,now());
# 添加人事
insert into ims.t_user(t_user_account, t_user_pwd, t_emp_no, t_role_id, t_user_status_id, t_create_time)
values('lisi','123',3,3,6,now()) ;
# 创建 请假表
# 主键 请假编号 申请人
# 请假类型：1、事假2、婚假3、年假4、调休5、病假6、丧假（配置表）请假事由
# 开始时间 结束时间 申请状态：1、草稿2、提交 创建时间
create table ims.t_holiday(
                            id int primary key auto_increment,
                            t_holiday_no varchar(10),
                            t_holiday_user_id int,
                            t_holiday_type_id int,
                            t_holiday_bz varchar(100),
                            t_start_time datetime,
                            t_end_time datetime,
                            t_holiday_status_id int,
                            t_create_time datetime
);

alter table ims.t_holiday
  add constraint t_holiday_user_id_fk
    foreign key (t_holiday_user_id) references ims.t_employee(id);

alter table ims.t_holiday
  add constraint t_holiday_type_id_fk
    foreign key (t_holiday_type_id) references ims.t_sys_config(t_config_key);

alter table ims.t_holiday
  add constraint t_holiday_status_id_fk
    foreign key (t_holiday_status_id) references ims.t_sys_config(t_config_key);

#初始化请假表
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1001',1,12,'结婚',now(),now(),4,now());
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1002',3,14,'结婚','2019-12-11 19:43:11','2019-12-20 19:43:11',5,'2019-12-9 19:43:11');
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1003',4,13,'年假','2019-12-11 19:43:11','2019-12-20 19:43:11',4,'2019-12-9 19:43:11');
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1004',5,14,'调休','2019-12-11 19:43:11','2019-12-20 19:43:11',5,'2019-12-9 19:43:11');
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1005',7,15,'病假','2019-12-11 19:43:11','2019-12-20 19:43:11',5,'2019-12-9 19:43:11');
insert into ims.t_holiday(t_holiday_no,t_holiday_user_id,t_holiday_type_id,t_holiday_bz,t_start_time,t_end_time,t_holiday_status_id,t_create_time)
values('QJ1006',6,16,'去世','2019-12-11 19:43:11','2019-12-20 19:43:11',5,'2019-12-9 19:43:11');

# 创建报销表
create table ims.t_expense(
                            id int primary key  auto_increment,
                            t_expense_num varchar(10),
                            t_req_person_id int,
                            t_expense_type_id int,
                            t_expense_money double,
                            t_req_time datetime,
                            t_req_state int,
                            t_create_time datetime
)engine=Innodb;
# 添加摘要字段
alter table ims.t_expense
  add t_summary_exp varchar(256) null;
# 添加外键
alter table ims.t_expense
  add constraint t_expense_t_req_person_id_fk
    foreign key (t_req_person_id) references ims.t_employee(id);

alter table ims.t_expense
  add constraint t_expense_type_id_fk
    foreign key (t_expense_type_id) references ims.t_sys_config(t_config_key);

alter table ims.t_expense
  add constraint t_expense_t_req_state_fk
    foreign key (t_req_state) references ims.t_sys_config(t_config_key);

# 添加报销单
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time,t_summary_exp)
values('BX1001','1','3','1000.4',now(),5,now(),'2019.5.4-2019.5.9 车票 300 住宿400 餐饮300.4');
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time,t_expense.t_summary_exp)
values('BX1002','1','1','100.4',now(),5,now(),'2019.5.5-2019.5.9 车票 300 住宿400 餐饮230.4');
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time)
values('BX1003','2','1','10.4',now(),4,now());
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time)
values('BX1004','5','1','102.4',now(),5,now());
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time)
values('BX1005','4','3','230.4',now(),4,now());
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time)
values('BX1006','2','2','300.4',now(),5,now());
insert into ims.t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state,t_create_time)
values('BX1007','2','2','400.4',now(),5,now());
# 创建权限表
create table ims.t_permissions
(
  id int primary key auto_increment,
  t_role_id int not null,
  t_menu_id int not null,
  t_create_time datetime
)engine=Innodb;
# 给t_role_id列添加外键
alter table ims.t_permissions
  add constraint t_permissions_t_role_id_fk
    foreign key (t_role_id) references ims.t_role (id);
alter table ims.t_permissions
  add constraint t_permissions_t_menu_id_fk
    foreign key (t_menu_id) references ims.t_menu (id);
# 添加权限信息
# 管理员有所有的权限
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,1,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,2,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,3,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,4,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,5,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,6,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,7,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,8,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,9,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,10,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,11,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,1,12,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,2,6,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,2,7,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,2,9,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,2,12,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,3,5,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,3,6,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,3,9,now());
insert into ims.t_permissions(id,t_role_id,t_menu_id,t_create_time) values(null,3,12,now());


# select A.*,B.t_emp_name,C.t_config_page_value,D.t_config_page_value
# from ims.t_holiday A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D
# where A.t_holiday_user_id =B.id
#   and A.t_holiday_type_id = C.t_config_key
#   and A.t_holiday_status_id = D.t_config_key;
# 搜索某个角色拥有的菜单
# select t_menu_id from ims.t_permissions where t_role_id=2;
#
#
#
# # 查找Menu的名字
# select id,t_menu_name,t_href_url from ims.t_menu where id in (select t_menu_id from ims.t_permissions where t_role_id=1)
# and t_parent_id is not null ;
#
# # 搜索顶级菜单
# select * from ims.t_menu  where t_parent_id is null;
#
# # 搜索某个顶级菜单下面的所有菜单
# select * from ims.t_menu where t_parent_id = 1;
#
# select * from ims.t_user where t_user_account='zs' and t_user_pwd='123';

# 根据申请人的id去找到申请人的姓名,根据报销类型去找到报销类型在页面的显示类型,去找到申请状态的页面表示数据
#         //3表查询
# select A.*,B.t_emp_name,C.t_config_page_value,D.t_config_page_value from ims.t_expense A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D
# where A.t_req_person_id = B. id
#   and A.t_expense_type_id = C.t_config_key
#   and A.t_req_state = D.t_config_key
# and A.id = 2;
#

# 查询1号部门的经理
# select t_emp_name,id,t_emp_super_id from ims.t_employee where t_emp_dept_id = 2 and t_emp_super_id is null;

# select A.*,B.t_emp_name from ims.t_dept A,ims.t_employee B where
#         B.t_emp_dept_id = A.id and B.t_emp_super_id is null
# ;

# select * from t_employee where t_emp_dept_id = 1;
#新
update ims.t_menu set t_href_url = 'njwb/role/roleMan.jsp' where id=10;
update ims.t_menu set t_href_url = 'njwb/permiss/permission.jsp' where id=11;
#查询权限表
# select A.*,B.t_role_name,C.t_menu_name from ims.t_permissions A,ims.t_role B,ims.t_menu C
# where A.t_role_id = B.id
#     and A.t_menu_id = C.id
#删除一级菜单显示
delete from ims.t_permissions where id = 1;
delete from ims.t_permissions where id = 2;
delete from ims.t_permissions where id = 3;

# delete from ims.t_permissions where id = 21;
# delete from ims.t_permissions where id = 22;
# delete from ims.t_permissions where id = 23;
# delete from ims.t_permissions where id = 24;
# delete from ims.t_permissions where id = 25;
# delete from ims.t_permissions where id = 26;
# delete from ims.t_permissions where id = 27;
# delete from ims.t_permissions where id = 28;
# delete from ims.t_permissions where id = 29;
# delete from ims.t_permissions where id = 30;
# delete from ims.t_permissions where id = 31;

# select  	A.id,A.t_emp_name,A.t_sex,A.t_entry_time,B.t_dept_name  from 	ims.t_employee A,ims.t_dept B  where 1=1   and  A.t_emp_dept_id=B.id limit  1,3;
# select  	A.id,A.t_emp_name,A.t_sex,A.t_entry_time,B.t_dept_name  from 	ims.t_employee A,ims.t_dept B  where 1=1  and   A.t_emp_dept_id=B.id  limit 1,3;

# insert into    t_employee(  	t_emp_no, t_emp_name, t_emp_dept_id, t_sex, t_education,
#                           t_email, t_phone, t_entry_time, t_create_time,t_emp_state )
#  values('af12321','员工一号',1,'男',null,null,null,'2019-12-18','2019-12-17',0);
#
#
# select  max(substring(t_emp_no,2,length(t_emp_no)-1)) as maxNo from ims.t_employee;
# select  max(substring(t_dept_no,2,length(t_dept_no)-1)) as maxNo from ims.t_dept;
#
# select max(convert(substring(t_emp_no,2,length(t_emp_no)-1), SIGNED)) from  ims.t_employee;
#
# select A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,A.t_emp_state,B.t_dept_name from t_employee as A,t_dept as B where
#             A.t_emp_dept_id=B.id
#                 and t_emp_state = 0
#                 and A.id = 1;


# insert into t_expense(id, t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state, t_create_time,t_summary_exp)
#
# insert into ims.t_user(id, t_user_account, t_user_pwd, t_emp_no, t_role_id, t_user_status_id, t_create_time)
# update ims.t_user
# set t_user_account = 'admin1'
# where id= 1;
# update  	t_user  set  	t_user_account='admin', t_user_pwd='admin1',
#                      t_emp_no= null, t_role_id=1,t_user_status_id=6, t_create_time=now()  where  	id = 1;