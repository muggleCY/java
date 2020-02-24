package com.jc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jc.constant.ComMethods;
import com.jc.constant.Constants;
import com.jc.entity.Recharge;
import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.AlreadyRefundException;
import com.jc.service.proxy.RechargeServiceProxy;
import com.jc.service.proxy.RecordServiceProxy;
import com.jc.service.proxy.TicketServiceProxy;
import com.jc.service.proxy.UserServiceProxy;

public class PersonalFrame {
	JFrame personalFrame = null;
	JLabel welcomLabel = null;
	JTable recordTable = null;
	JScrollPane columnNamePane = null;
	JButton rechargeButton = null;
	JButton refundButton = null;
	
	User user = null;
	public PersonalFrame(User user){
		this.user = user;
	}
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	TicketServiceProxy ticketServiceProxy = new TicketServiceProxy();
	UserServiceProxy userServiceProxy = new UserServiceProxy();
	RecordServiceProxy recordServiceProxy = new RecordServiceProxy();
	RechargeServiceProxy rechargeServiceProxy = new RechargeServiceProxy();
	public void init(){
		personalFrame = new JFrame("车票售卖系统 -- 个人中心");
		welcomLabel = new JLabel("购买记录");
		recordTable = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		columnNamePane = new JScrollPane(recordTable);
		rechargeButton = new JButton("充值");
		refundButton = new JButton("退票");
	}
	public void build(){
		personalFrame.setSize(500, 300);
		personalFrame.setLocation(200, 200);
		personalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		personalFrame.setLayout(null);
		
		queryRecord();
		
		welcomLabel.setBounds(10, 10, 200, 25);
		personalFrame.add(welcomLabel);
		
		columnNamePane.setBounds(10, 40, 480, 180);
		personalFrame.add(columnNamePane);
		
		rechargeButton.setBounds(300, 230, 80, 25);
		personalFrame.add(rechargeButton);
		refundButton.setBounds(400, 230, 80, 25);
		personalFrame.add(refundButton);
		personalFrame.setVisible(true);
	}
	public void addAction(){
		rechargeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = JOptionPane.showInputDialog(personalFrame,"请输入要充值的金额","充值金额",JOptionPane.QUESTION_MESSAGE);
				if(str == null){
					JOptionPane.showMessageDialog(personalFrame, "输入为空","error",JOptionPane.ERROR_MESSAGE);

				}else{
					if(ComMethods.matches(Constants.REGEX_MONEY,str)){
						try {
							rechargeServiceProxy.applyMoney(user,Double.valueOf(str));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(personalFrame, "输入格式不正确","error",JOptionPane.ERROR_MESSAGE);
					}
				}


			}
		});
		refundButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer row = recordTable.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(personalFrame, "亲您还没选择哦","error",JOptionPane.ERROR_MESSAGE);
				}else{
					Integer obj1 = (Integer) recordTable.getValueAt(row, 0);
					try {
						ticketServiceProxy.refundTicket(obj1);
					} catch (AlreadyRefundException e) {
						JOptionPane.showMessageDialog(personalFrame, "不能重复退票哦","重复退票",JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				queryRecord();
			}
		});
	}
	public void start(){
		init();
		build();
		addAction();
	}
	public void queryRecord(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Class recodeClass = RecordAndTicket.class;
		Field[] fields = recodeClass.getDeclaredFields();
		String[] columnNames = {"编号","车次编号","起点站","终点站","购买时间","状态"};

		resultMap.put("columnNames", columnNames);
		RecordServiceProxy recordServiceProxy = new RecordServiceProxy();
		try {
			List<RecordAndTicket> recordList = recordServiceProxy.selectRecord(user.getUid());
			Object[][] data = new Object[recordList.size()][fields.length];
			for (int i = 0; i < data.length; i++) {
				RecordAndTicket recordAndTicket = recordList.get(i);
				for (int j = 0; j < fields.length; j++) {
					String fieldName = fields[j].getName();
					String methodName = "get" + fieldName.substring(0, 1).toUpperCase() 
										+fieldName.substring(1);
//					Method method = recodeClass.getDeclaredMethod(methodName);
//					Object obj = method.invoke(record);
//					data[i][j] = obj;
					Method method;
					Object obj;
					if(methodName.equals("getRecordMode")){
						method = recodeClass.getDeclaredMethod(methodName);
						obj = method.invoke(recordAndTicket);
						if(	obj.equals(0)){
							obj = "已购买";
						}else if(obj.equals(1)){
							obj = "已退票";
						}
						data[i][j] = obj;
					}else{
						method = recodeClass.getDeclaredMethod(methodName);
						obj = method.invoke(recordAndTicket);
						data[i][j] = obj;
					}
				}
			}
			resultMap.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object[][] data = (Object[][]) resultMap.get("data");
		TableModel model = new DefaultTableModel(data,columnNames);
		recordTable.setModel(model);
	}
}
