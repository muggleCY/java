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

import com.jc.entity.Record;
import com.jc.entity.User;
import com.jc.service.proxy.RecordServiceProxy;
import com.jc.service.proxy.UserServiceProxy;

public class AdminiUserFrame {
	JFrame adminiUserFrame = null;
	JLabel welcomLabel = null;
	JTable userTable = null;
	JScrollPane columnNamePane = null;
	JButton rechargeCenterButton = null;
	JButton banOrNotBanButton = null;

	// User user = null;
	// public PersonalFrame(User user){
	// this.user = user;
	// }
	UserServiceProxy userServiceProxy = new UserServiceProxy();

	public void init() {
		adminiUserFrame = new JFrame("车票售卖系统 -- 用户管理");
		welcomLabel = new JLabel("用户信息");
		userTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};
		columnNamePane = new JScrollPane(userTable);
		rechargeCenterButton = new JButton("充值中心");
		banOrNotBanButton = new JButton("封禁/解禁");
	}

	public void build() {
		adminiUserFrame.setSize(500, 300);
		adminiUserFrame.setLocation(200, 200);
		adminiUserFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		adminiUserFrame.setLayout(null);

		queryUser();

		welcomLabel.setBounds(10, 10, 200, 25);
		adminiUserFrame.add(welcomLabel);

		columnNamePane.setBounds(10, 40, 480, 180);
		adminiUserFrame.add(columnNamePane);
		// userTable.setEnabled(false); //设置

		rechargeCenterButton.setBounds(50, 230, 100, 25);
		adminiUserFrame.add(rechargeCenterButton);

		banOrNotBanButton.setBounds(350, 230, 100, 25);
		adminiUserFrame.add(banOrNotBanButton);
		adminiUserFrame.setVisible(true);
	}

	public void addAction() {
		rechargeCenterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MoneyManagementFrame().start();

			}
		});
		banOrNotBanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer row = userTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(adminiUserFrame, "亲您还没选择哦", "error", JOptionPane.ERROR_MESSAGE);
				} else {
					Integer obj1 = (Integer) userTable.getValueAt(row, 0);
					User user;
					try {
						user = userServiceProxy.selectUserById(obj1);
						userServiceProxy.updateUser(user);
						if (user.getUserMode() == 0) {
							JOptionPane.showMessageDialog(adminiUserFrame, "成功解封", "解封成功", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(adminiUserFrame, "成功封禁", "封禁成功", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				queryUser();
			}
		});
	}

	public void start() {
		init();
		build();
		addAction();
	}

	public void queryUser() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Class userClass = User.class;

		String[] columnNames = { "uid", "username", "userMoney", "userMode" };
		String[] cols = {"用户编号","用户名","金额","状态"};
		resultMap.put("columnNames", cols);
		try {
			List<User> userList = userServiceProxy.selectUser();
			Object[][] data = new Object[userList.size()][columnNames.length];
			for (int i = 0; i < data.length; i++) {
				User user = userList.get(i);
				for (int j = 0; j < columnNames.length; j++) {
					String fieldName = columnNames[j];
					String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method method;
					Object obj;
					if (methodName.equals("getUserMode")) {
						method = userClass.getDeclaredMethod(methodName);
						obj = method.invoke(user);
						if (obj.equals(0)) {
							obj = "正常";
						} else if (obj.equals(2)) {
							obj = "封禁";
						}
						data[i][j] = obj;
					} else {
						method = userClass.getDeclaredMethod(methodName);
						obj = method.invoke(user);
						System.out.println("obj" + obj);
						data[i][j] = obj;
					}
				}
			}
			resultMap.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object[][] data = (Object[][]) resultMap.get("data");
		TableModel model = new DefaultTableModel(data, columnNames);
		userTable.setModel(model);

	}
}
