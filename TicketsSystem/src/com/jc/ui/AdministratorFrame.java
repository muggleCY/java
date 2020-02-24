package com.jc.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.NotBuyTicketException;
import com.jc.exception.TicketNotSaleException;
import com.jc.factory.ObjectFactory;
import com.jc.service.TicketService;
import com.jc.service.proxy.TicketServiceProxy;
import com.jc.util.Pager;

public class AdministratorFrame {
	JFrame administratorFrame = null;
	
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
	JButton firstPageButton = null;//首页
	JButton backPageButton = null;//上一页
	JButton nextPageButton = null;//下一页
	JButton lastPageButton = null;//末页
	JButton deleteButton = null;
	JButton addButton = null;
	JButton onOrNotSaleButton = null; //停售/可售
	
	TicketService ticketServiceProxy = (TicketService)ObjectFactory.getObject("ticketServiceProxy"); //代理类
	Pager<Ticket> page = new Pager<Ticket>();
	
	Integer pageNum = 1;
	
	User user = null;
	public AdministratorFrame(User user){
		this.user = user;
	}
	
	public void init(){
		administratorFrame = new JFrame("车票售卖系统 -- 管理员");
		startStationLabel = new JLabel("起 始 站");
		startStationField = new JTextField();
		
		
		
		arriveStationLabel = new JLabel("终 点 站");
		arriveStationField = new JTextField();
		
		startTimeLabel = new JLabel("出发时间");
		startTimeField = new JTextField("2000-01-01 00:00:00");
		startTimeField.setForeground(Color.GRAY);
		searchButton = new JButton("查询");
		personalButton = new JButton("用户管理");
		searchResultTable = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		columnNamePane = new JScrollPane(searchResultTable);
		firstPageButton = new JButton("首页");//首页
		backPageButton = new JButton("上一页");//上一页
		nextPageButton = new JButton("下一页");//下一页
		lastPageButton = new JButton("末页");//末页
		deleteButton = new JButton("删除");
		addButton = new JButton("添加车票");
		onOrNotSaleButton = new JButton("停售/可售");
		
	}
	public void build(){
		administratorFrame.setSize(700, 400);
		administratorFrame.setLocation(100, 100);
		administratorFrame.setLayout(null);
		administratorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		queryTicketForTable(1);
		
		columnNamePane.setBounds(20, 80, 660, 220);
		administratorFrame.add(columnNamePane);
//		searchResultTable.setEnabled(false); //设置
		columnNamePane.repaint();
		
		startStationLabel.setBounds(20, 30, 80, 25);
		administratorFrame.add(startStationLabel);
		
		startStationField.setBounds(80, 30, 70, 25);
		administratorFrame.add(startStationField);

		arriveStationLabel.setBounds(170, 30, 80, 25);
		administratorFrame.add(arriveStationLabel);
		
		arriveStationField.setBounds(230, 30, 70, 25);
		administratorFrame.add(arriveStationField);
		
		startTimeLabel.setBounds(320, 30, 80, 25);
		administratorFrame.add(startTimeLabel);
		startTimeField.setBounds(375, 30, 125, 25);
		administratorFrame.add(startTimeField);
		
		searchButton.setBounds(500, 30, 80, 25);
		administratorFrame.add(searchButton);
		
		personalButton.setBounds(600, 30, 90, 25);
		administratorFrame.add(personalButton);
		
		firstPageButton.setBounds(100, 320, 90, 25);
		administratorFrame.add(firstPageButton);
		backPageButton.setBounds(200, 320, 90, 25);
		administratorFrame.add(backPageButton);
		nextPageButton.setBounds(300, 320, 90, 25);
		administratorFrame.add(nextPageButton);
		lastPageButton.setBounds(400, 320, 90, 25);
		administratorFrame.add(lastPageButton);
		
		deleteButton.setBounds(500, 310, 80, 25);
		administratorFrame.add(deleteButton);
		onOrNotSaleButton.setBounds(590, 310, 100, 25);
		administratorFrame.add(onOrNotSaleButton);
		addButton.setBounds(540, 340, 100, 25);
		administratorFrame.add(addButton);
		administratorFrame.setVisible(true);
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
				// TODO Auto-generated method stub
				new AdminiUserFrame().start();
				
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer row = searchResultTable.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(administratorFrame, "亲您还没选择哦","error",JOptionPane.ERROR_MESSAGE);
				}else{
					Integer obj1 = (Integer) searchResultTable.getValueAt(row, 0);
					try {
						Ticket ticket = ticketServiceProxy.getTicketById(obj1);
						ticket.setTicketMode(2);
						ticketServiceProxy.updateTicketById(ticket);
						JOptionPane.showMessageDialog(administratorFrame, "成功删除","删除成功",JOptionPane.INFORMATION_MESSAGE);
						queryTicketForTable(pageNum);
					} catch (NotBuyTicketException e1) {
						JOptionPane.showMessageDialog(administratorFrame, "该车票已经被删除啦","不能重复删除",JOptionPane.ERROR_MESSAGE);
					}	catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		onOrNotSaleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer row = searchResultTable.getSelectedRow();
				Ticket ticket = null;
				if(row == -1){
					JOptionPane.showMessageDialog(administratorFrame, "亲您还没选择哦","error",JOptionPane.ERROR_MESSAGE);
				}else{
					Integer obj1 = (Integer) searchResultTable.getValueAt(row, 0);
					try {
						ticket = ticketServiceProxy.getTicketById(obj1);
						if(ticket.getTicketMode() == 0){
							ticket.setTicketMode(1);
							ticketServiceProxy.updateTicketById(ticket);
							JOptionPane.showMessageDialog(administratorFrame, "成功停售","停售成功",JOptionPane.INFORMATION_MESSAGE);
						}else{
							ticket.setTicketMode(0);
							ticketServiceProxy.updateTicketById(ticket);
							JOptionPane.showMessageDialog(administratorFrame, "成功开售","开售成功",JOptionPane.INFORMATION_MESSAGE);
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					queryTicketForTable(pageNum);
				}
			}
		});
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTicketFrame().start();
				
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
}
