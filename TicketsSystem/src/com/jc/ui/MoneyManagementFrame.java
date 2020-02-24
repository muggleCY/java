package com.jc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jc.entity.Recharge;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.AlreadyPassException;
import com.jc.service.proxy.RechargeServiceProxy;
import com.jc.service.proxy.UserServiceProxy;

public class MoneyManagementFrame {
	JFrame moneyFrame = null;
	JLabel welcomLabel = null;
	JTable userMoneyTable = null;
	JScrollPane columnNamePane = null;
	JButton passButton = null;
	
	RechargeServiceProxy rechargeServiceProxy = new RechargeServiceProxy();
//	User user = null;
//	public PersonalFrame(User user){
//		this.user = user;
//	}
	UserServiceProxy userServiceProxy = new UserServiceProxy();
	public void init(){
		moneyFrame = new JFrame("车票售卖系统 -- 充值管理");
		welcomLabel = new JLabel("充值中心");
		userMoneyTable = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		columnNamePane = new JScrollPane(userMoneyTable);
		passButton = new JButton("通过");
	}
	public void build(){
		moneyFrame.setSize(500, 300);
		moneyFrame.setLocation(200, 200);
		moneyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		moneyFrame.setLayout(null);
		
		queryRecharge();
		
		welcomLabel.setBounds(10, 10, 200, 25);
		moneyFrame.add(welcomLabel);
		
		columnNamePane.setBounds(10, 40, 480, 180);
		moneyFrame.add(columnNamePane);
		
		passButton.setBounds(380, 230, 100, 25);
		moneyFrame.add(passButton);
		
		moneyFrame.setVisible(true);
	}
	public void addAction(){
		passButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer row = userMoneyTable.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(moneyFrame, "亲您还没选择哦","error",JOptionPane.ERROR_MESSAGE);
				}else{
					Integer rgid = (Integer) userMoneyTable.getValueAt(row, 0);
					Integer uid = (Integer) userMoneyTable.getValueAt(row, 1);
					try {
						rechargeServiceProxy.passRecharge(rgid,uid);
					} catch (AlreadyPassException e) {
						JOptionPane.showMessageDialog(moneyFrame, "不要重复通过","error",JOptionPane.ERROR_MESSAGE);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				queryRecharge();
			}
		});
	}
	public void start(){
		init();
		build();
		addAction();
	}
	public void queryRecharge(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Class rechargeClass = Recharge.class;
		Field[] fields = rechargeClass.getDeclaredFields();
		String[] columnNames = {"编号","用户编号","充值金额","申请时间","状态"};
		resultMap.put("columnNames", columnNames);
		try {
			List<Recharge> rechargeList = rechargeServiceProxy.selectRecharge();
			Object[][] data = new Object[rechargeList.size()][fields.length];
			for (int i = 0; i < data.length; i++) {
				Recharge recharge = rechargeList.get(i);
				for (int j = 0; j < fields.length; j++) {
					String fieldName = fields[j].getName();
					String methodName = "get" + fieldName.substring(0, 1).toUpperCase() 
										+fieldName.substring(1);
//					Method method = ticketClass.getDeclaredMethod(methodName);
//					Object obj = method.invoke(ticket);
//					System.out.println("obj"+obj);
//					data[i][j] = obj;
					Method method;
					Object obj;
					if(methodName.equals("getRechargeMode")){
						method = rechargeClass.getDeclaredMethod(methodName);
						obj = method.invoke(recharge);
						if(	obj.equals(0)){
							obj = "已通过";
						}else if(obj.equals(1)){
							obj = "未通过";
						}
						data[i][j] = obj;
					}else{
						method = rechargeClass.getDeclaredMethod(methodName);
						obj = method.invoke(recharge);
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
		userMoneyTable.setModel(model);
	}
}
