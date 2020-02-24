package com.jc.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jc.entity.Record;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.AlreadyBuyException;
import com.jc.exception.MoneyNotEnoughException;
import com.jc.exception.NoTicketNumException;
import com.jc.exception.NotBuyTicketException;
import com.jc.exception.TicketExpireException;
import com.jc.service.proxy.RecordServiceProxy;
import com.jc.service.proxy.TicketServiceProxy;
import com.jc.service.proxy.UserServiceProxy;
import com.jc.util.Pager;
/**
 * 用户主页面
 * @author soft01
 *
 */


public class MainFrame {
	JFrame mainFrame = null;
	
	JLabel startStationLabel = null; //起始站
	JTextField startStationField = null;
	JLabel arriveStationLabel = null; //终点站
	JTextField arriveStationField = null;
	
	JLabel startTimeLabel = null;//出发时间
	JTextField startTimeField = null;
	JButton searchButton = null;//查询
	JButton personalButton = null;//个人中心
	JTable searchResultTable = null;
	JScrollPane columnNamePane = null;
	
	JLabel userMoneyLabel = null;
	JLabel moneyLabel = null;
	JButton firstPageButton = null;//首页
	JButton backPageButton = null;//上一页
	JButton nextPageButton = null;//下一页
	JButton lastPageButton = null;//末页
	JButton purchaseButton = null;//购买
	
	UserServiceProxy userServiceProxy = new UserServiceProxy();
	TicketServiceProxy ticketServiceProxy = new TicketServiceProxy(); //代理类
	RecordServiceProxy recordServiceProxy = new RecordServiceProxy();
	Pager<Ticket> page = new Pager<Ticket>();
	Integer pageNum = 1;
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	User user = null;
	public MainFrame(User user){
		this.user = user;
	}
	
	public void init(){
		mainFrame = new JFrame("车票售卖系统 -- 主页面");
		startStationLabel = new JLabel("起 始 站");
		startStationField = new JTextField();
		arriveStationLabel = new JLabel("终 点 站");
		arriveStationField = new JTextField();
		
		startTimeLabel = new JLabel("出发时间");
		startTimeField = new JTextField("2000-01-01 00:00:00");
		startTimeField.setForeground(Color.GRAY);

		searchButton = new JButton("查询");
		personalButton = new JButton("个人中心");
		searchResultTable = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		columnNamePane = new JScrollPane(searchResultTable);
		
		userMoneyLabel = new JLabel("余额:");
		String money = String.valueOf(user.getUserMoney());
		moneyLabel = new JLabel(money);
		firstPageButton = new JButton("首页");//首页
		backPageButton = new JButton("上一页");//上一页
		nextPageButton = new JButton("下一页");//下一页
		lastPageButton = new JButton("末页");//末页
		purchaseButton = new JButton("购买");//购买
		
	}
	public void build(){
		mainFrame.setSize(700, 400);
		mainFrame.setLocation(100, 100);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		queryTicketForTable(1);
		columnNamePane.setBounds(20, 80, 660, 220);
		mainFrame.add(columnNamePane);
		columnNamePane.repaint();
		
		startStationLabel.setBounds(10, 30, 80, 25);
		mainFrame.add(startStationLabel);
		
		startStationField.setBounds(70, 30, 70, 25);
		mainFrame.add(startStationField);
		arriveStationLabel.setBounds(160, 30, 80, 25);
		mainFrame.add(arriveStationLabel);
		
		arriveStationField.setBounds(220, 30, 70, 25);
		mainFrame.add(arriveStationField);
		startTimeLabel.setBounds(310, 30, 80, 25);
		mainFrame.add(startTimeLabel);
		
		startTimeField.setBounds(365, 30, 125, 25);
		mainFrame.add(startTimeField);
		searchButton.setBounds(500, 30, 80, 25);
		mainFrame.add(searchButton);
		personalButton.setBounds(600, 30, 90, 25);
		mainFrame.add(personalButton);
		
		userMoneyLabel.setBounds(10, 320, 80, 25);
		mainFrame.add(userMoneyLabel);
		moneyLabel.setBounds(40, 320, 100, 25);
		mainFrame.add(moneyLabel);
		
		
		firstPageButton.setBounds(100, 320, 100, 25);
		mainFrame.add(firstPageButton);
		backPageButton.setBounds(200, 320, 100, 25);
		mainFrame.add(backPageButton);
		nextPageButton.setBounds(300, 320, 100, 25);
		mainFrame.add(nextPageButton);
		lastPageButton.setBounds(400, 320, 100, 25);
		mainFrame.add(lastPageButton);
		purchaseButton.setBounds(580, 320, 100, 35);
		mainFrame.add(purchaseButton);
		mainFrame.setVisible(true);
	}
	public void addAction(){
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				pageNum = 1;
				queryTicketForTable(pageNum);
			}
		});
		firstPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					pageNum = 1;
					queryTicketForTable(pageNum);
			}
		});
		backPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pageNum = page.getLastPageNum();
				queryTicketForTable(pageNum);
				
			}
		});
		nextPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pageNum = page.getNextPageNum();
				queryTicketForTable(pageNum);
			}
		});
		lastPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					pageNum = page.getTotalPage();
					queryTicketForTable(pageNum);
				
			}
		});
		personalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PersonalFrame(user).start();
				
			}
		});
		purchaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Ticket ticket = null;
				Integer row = searchResultTable.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(mainFrame, "亲您还没选择哦","error",JOptionPane.ERROR_MESSAGE);
				}else{
					Integer tid = (Integer) searchResultTable.getValueAt(row, 0);
					try {

						ticketServiceProxy.buy(tid,user.getUid());

					}catch (MoneyNotEnoughException e) {
						JOptionPane.showMessageDialog(mainFrame, "钱不够啦  可以充点钱","error",JOptionPane.ERROR_MESSAGE);
					} catch (NoTicketNumException e) {
						JOptionPane.showMessageDialog(mainFrame, "当前车票已经售空","error",JOptionPane.ERROR_MESSAGE);
					} catch(AlreadyBuyException e){
						JOptionPane.showMessageDialog(mainFrame, "请勿重复购买","error",JOptionPane.ERROR_MESSAGE);
					} catch (NotBuyTicketException e) {
						JOptionPane.showMessageDialog(mainFrame, "车票暂停发售","error",JOptionPane.ERROR_MESSAGE);
					} catch (TicketExpireException e) {
						JOptionPane.showMessageDialog(mainFrame, "已发车不能购买啦","error",JOptionPane.ERROR_MESSAGE);
					}catch (Exception e) {
						e.printStackTrace();
					}
//					ticket = ticketServiceProxy.getTicketById(tid);
//					if(user.getUserMoney() > ticket.getTicketMoney()){
//
//					}
//					try {
//						userServiceProxy.updateMoney(user,ticket);
//					} catch (MoneyNotEnoughException e) {
//						JOptionPane.showMessageDialog(mainFrame, "钱不够啦  可以充点钱","error",JOptionPane.ERROR_MESSAGE);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					try {
//						ticketServiceProxy.updateTicketForNum(ticket);
//					} catch (NoTicketNumException e) {
//						JOptionPane.showMessageDialog(mainFrame, "当前车票已经售空","error",JOptionPane.ERROR_MESSAGE);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					try {
//						Record record = new Record();
//						record.setUid(user.getUid());
//						record.setTid(ticket.getTid());
//						record.setBuyTime(format.format(date));
//						recordServiceProxy.insertRecord(record);
//					}catch(AlreadyBuyException e){
//						JOptionPane.showMessageDialog(mainFrame, "请勿重复购买","error",JOptionPane.ERROR_MESSAGE);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					Double nowMoney = user.getUserMoney()-ticket.getTicketMoney();
//					user.setUserMoney(nowMoney);
//					moneyLabel.setText(String.valueOf(user.getUserMoney()));
				}
				queryTicketForTable(pageNum);
			}
		});
		startTimeField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				String temp = startTimeField.getText();
				if(temp.equals("")){
					startTimeField.setForeground(Color.GRAY);
					startTimeField.setText("2000-01-01 00:00:00");
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				String temp = startTimeField.getText();
				if(!temp.equals("")&&temp.equals("2000-01-01 00:00:00")){
					startTimeField.setText("");
					startTimeField.setForeground(Color.BLACK);
				}
			}
		});
	}
	public void start(){
		init();
		build();
		addAction();
	}
//	/**
//	 * 通过起始站、终点站、出发时间查询车票
//	 * @param startStation
//	 * @param arriveStation
//	 * @param startTime
//	 */
	public void queryTicketForTable(Integer pageNum){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Class ticketClass = Ticket.class;
		Field[] fields = ticketClass.getDeclaredFields();
		String[] columnNames = {"编号","起始地点","终止地点","出发时间","票数","车票状态","票价"};
		resultMap.put("columnNames", columnNames);
		String startStation = startStationField.getText();
		String arriveStation = arriveStationField.getText();
		String startTime = startTimeField.getText();
		if(startTime.equals("2000-01-01 00:00:00")){
			startTime = "";
		}
		System.out.println(startTime+"startTime");
		TicketServiceProxy ticketServiceProxy = new TicketServiceProxy();
		try {
			page = ticketServiceProxy.getTicketByPageAndCond(startStation, arriveStation, startTime, pageNum);
			Object[][] data = new Object[page.getData().size()][fields.length];
			for (int i = 0; i < data.length; i++) {
				Ticket ticket = page.getData().get(i);
				for (int j = 0; j < fields.length; j++) {
					String fieldName = fields[j].getName();
					String methodName = "get" + fieldName.substring(0, 1).toUpperCase() 
										+fieldName.substring(1);
					Method method;
					Object obj;
					if(methodName.equals("getTicketMode")){
						method = ticketClass.getDeclaredMethod(methodName);
						obj = method.invoke(ticket);
						if(	obj.equals(0)){
							obj = "可购买";
						}else if(obj.equals(1)){
							obj = "停售";
						}else if(obj.equals(2)){
							obj = "已删除";
						}
						data[i][j] = obj;
					}else{
						method = ticketClass.getDeclaredMethod(methodName);
						obj = method.invoke(ticket);
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
		searchResultTable.setModel(model);
		
		changeMoney();
		
		if(pageNum == 1||page.getTotalPage() == 0){
			firstPageButton.setEnabled(false);
			backPageButton.setEnabled(false);
		}else{
			firstPageButton.setEnabled(true);
			backPageButton.setEnabled(true);
		}
		if(pageNum == page.getTotalPage()||page.getTotalPage() == 0){
			nextPageButton.setEnabled(false);
			lastPageButton.setEnabled(false);
		}else{
			nextPageButton.setEnabled(true);
			lastPageButton.setEnabled(true);
		}
	}
////得到起始站的地点有哪些
//	public String[] StartStation(){
//		String[] startStationList = null;
//		try {
//			List<String> StationList = ticketServiceProxy.selectStartStation(null, null);
//			startStationList = new String[StationList.size()];
//			for (int i = 0; i < StationList.size(); i++) {
//				startStationList[i] = StationList.get(i);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return startStationList;
//	}
////得到终点站的地点有哪些
//	public String[] ArriveStation(String startStation){
//		String[] arriveStationList = null;
//		try {
//			List<String> StationList = ticketServiceProxy.selectArriveStation(startStation, null);
//			arriveStationList = new String[StationList.size()];
//			for (int i = 0; i < StationList.size(); i++) {
//				arriveStationList[i] = StationList.get(i);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return arriveStationList;
//	}
	public void changeMoney(){
		User againUser = null;
		try {
			againUser = userServiceProxy.selectUserById(user.getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		moneyLabel.setText(String.valueOf(againUser.getUserMoney()));
	}
}
