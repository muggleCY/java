--创建四张表
create table t_user(  				--用户表
	id int primary key auto_increment,--编号
	username varchar(20),           --用户名
	password varchar(20),			--密码
	truename varchar(20),			--真实姓名			
	phonenumber varchar(20),		--手机号
	idCardNum varchar(20),			--身份证
	user_money double,				--余额
	user_mode int					--用户状态（0正常用户 ，1管理员，2封禁用户）
);

create table t_ticket(				--车票表
	id int primary key auto_increment,--编号
	startStation varchar(20),		--起始地点
	arriveStation varchar(20),		--终止地点
	startTime datetime,			--出发时间
	ticket_Num int,					--票数
	ticket_mode int,				--车票状态(0 可购买，1 不可购买，2已经删除）
	ticketmoney double				--票价
);

create table t_record(				--购买记录表
	id int primary key auto_increment,--编号
	user_id int ,					--用户编号
	foreign key (user_id) references t_user(id), --外键
	ticket_id int,					--车票编号
	foreign key (ticket_id) references t_ticket(id),
	buyTime datetime,			--购买时间
	record_mode int					--购买状态(0 已购，1 已退票，2 已删除)
);


create table t_recharge(			--充值申请表
	id int primary key auto_increment,--编号
	user_id int,					--用户编号
	foreign key (user_id) references t_user(id),
	recharge_money double,			--申请金额
	applyTime datetime,			--申请时间
	recharge_mode int				--申请状态(0 已通过，1 未通过）
);

--添加数据
insert into t_user(id,username,password,truename,phonenumber,idCardNum,user_money,user_mode) values(null,'user1','123','张三','15866138635','330327200001017849','1000','0');
insert into t_user(id,username,password,truename,phonenumber,idCardNum,user_money,user_mode) values(null,'user2','123','赵四','15866138635','330327199701017849','1000','0');
insert into t_user(id,username,password,truename,phonenumber,idCardNum,user_money,user_mode) values(null,'admini','321','李四','13466138635','330327199902024725','1000','1');
insert into t_user(id,username,password,truename,phonenumber,idCardNum,user_money,user_mode) values(null,'user3','aaa','xx','17798536697','330327199802024210','1000','2');


insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'南京','苏州','2019-10-01 08:00:00',10,0,100.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'苏州','南京','2019-11-10 12:30:00',10,0,50.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'南昌','杭州','2019-11-11 09:00:00',10,0,100.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'南宁','上海','2019-11-12 15:00:00',10,2,150.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'宁波','合肥','2019-11-15 15:00:00',10,0,100.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'温州','三亚','2019-11-18 15:00:00',10,1,100.0);
insert into t_ticket(id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney) values(null,'南京','三亚','2019-11-18 15:00:00',1,1,100.0);


insert into t_record(id,user_id,ticket_id,buyTime,record_mode)values(null,1,1,'2019-09-30 9:30',0);
insert into t_record(id,user_id,ticket_id,buyTime,record_mode)values(null,2,2,'2019-10-01 9:30',1);


insert into t_recharge(id,user_id,recharge_money,applyTime,recharge_mode)values(null,1,100,'2019-09-30 9:30',0);

