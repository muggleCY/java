package com.jc.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.jc.constant.ComMethods;
import com.jc.constant.Constants;
import com.jc.entity.Ticket;
import com.jc.exception.TicketIsExistException;
import com.jc.exception.UserAlreadyExistsException;
import com.jc.service.proxy.TicketServiceProxy;
import com.jc.service.proxy.UserServiceProxy;

public class AddTicketFrame {
	JFrame addTicketFrame = null;
	JLabel welcomLabel = null;
	JLabel startStationLabel = null;
	JTextField startStationField = null;
	JLabel arrivalStationLabel = null;
	JTextField arrivalStationField = null;
	JLabel startTimeLabel = null; 
	JTextField startTimeField = null;
	JLabel ticketNumLabel = null;
	JTextField ticketNumField = null;
	JLabel ticketMoneyLabel = null;
	JTextField ticketMoneyField = null;
	JButton addButton = null; //注册
	JButton returnButton = null; //返回登录界面
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date1 = new Date();
	public void init(){
		addTicketFrame = new JFrame("车票售卖系统 -- 添加车票");
		welcomLabel = new JLabel("欢迎添加^-^");
		startStationLabel = new JLabel("出发站:");
		startStationField = new JTextField();
		
		arrivalStationLabel = new JLabel("终点站:");
		arrivalStationField = new JTextField();
		
		startTimeLabel = new JLabel("发车时间:");
		startTimeField = new JTextField("2000-01-01 00:00:00");
		startTimeField.setForeground(Color.GRAY);
		ticketNumLabel = new JLabel("票数:");
		ticketNumField = new JTextField();

		ticketMoneyLabel = new JLabel("票价:");
		ticketMoneyField = new JTextField();
		
//		
//		phoneNumLabel = new JLabel("手 机 号:");
//		phoneNumField = new JTextField();
//		
//		idCardNumLabel = new JLabel("身 份 证:");
//		idCardNumField = new JTextField();
		
		addButton = new JButton("添加");
		returnButton = new JButton("返回");
	}
	public void build(){
		addTicketFrame.setSize(300, 400);
		addTicketFrame.setLocation(200, 200);
		addTicketFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addTicketFrame.setLayout(null);
		welcomLabel.setBounds(100, 10, 200, 50);
		addTicketFrame.add(welcomLabel);
		
		startStationLabel.setBounds(50, 80, 80, 35);
		addTicketFrame.add(startStationLabel);
		startStationField.setBounds(140, 80, 125, 25);
		addTicketFrame.add(startStationField);
		
		arrivalStationLabel.setBounds(50, 130, 80, 35);
		addTicketFrame.add(arrivalStationLabel);
		arrivalStationField.setBounds(140, 130, 125, 25);
		addTicketFrame.add(arrivalStationField);
//		
		startTimeLabel.setBounds(50, 180, 80, 35);
		addTicketFrame.add(startTimeLabel);
		startTimeField.setBounds(140, 180, 125, 25);
		addTicketFrame.add(startTimeField);
//		
		ticketNumLabel.setBounds(50, 230, 80, 35);
		addTicketFrame.add(ticketNumLabel);
		ticketNumField.setBounds(140, 230, 125, 25);
		addTicketFrame.add(ticketNumField);

		ticketMoneyLabel.setBounds(50, 280, 80, 35);
		addTicketFrame.add(ticketMoneyLabel);
		ticketMoneyField.setBounds(140, 280, 125, 25);
		addTicketFrame.add(ticketMoneyField);
//		
//		phoneNumLabel.setBounds(50, 280, 80, 35);
//		addTicketFrame.add(phoneNumLabel);
//		phoneNumField.setBounds(140, 280, 120, 25);
//		addTicketFrame.add(phoneNumField);
//		
//		idCardNumLabel.setBounds(50, 330, 80, 35);
//		addTicketFrame.add(idCardNumLabel);
//		idCardNumField.setBounds(140, 330, 120, 25);
//		addTicketFrame.add(idCardNumField);
		
		addButton.setBounds(40, 330, 100, 25);
		addTicketFrame.add(addButton);
//		
		returnButton.setBounds(160, 330, 100, 25);
		addTicketFrame.add(returnButton);
		addTicketFrame.setVisible(true);
	}
	public void addAction(){
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer ticketNum = 0;
				Double ticketMoney = 0.0;
				String startStation = startStationField.getText();
				String arriveStation = arrivalStationField.getText();
				String startTime = startTimeField.getText();
				String ticketNumString = ticketNumField.getText();
				String ticketMoneyString = ticketMoneyField.getText();
				StringBuffer message = new StringBuffer();
				boolean isDateTime = ComMethods.matches(Constants.REGEX_DATETIME,startTime);
				boolean isTicketNum = ComMethods.matches(Constants.REGEX_NUMBER,ticketNumString);
				boolean isTicketMoney = ComMethods.matches(Constants.REGEX_MONEY,ticketMoney.toString());
				if(!isDateTime){
					message.append("时间格式不正确").append("\n");
				}
				try {
					if(isDateTime&&format.parse(startTime).before(date1)){
						message.append("时间过时").append("\n");
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if(!isTicketNum){
					message.append("票数格式不正确").append("\n");
				}
				if( isTicketNum&&(ticketNum = Integer.valueOf(ticketNumString)) < 0 ){
					message.append("票数输入错误").append("\n");
				}
				if(!isTicketMoney){
					message.append("票价格式不正确").append("\n");
				}
				if(isTicketMoney&&(ticketMoney = Double.valueOf(ticketMoneyString)) < 0){
					message.append("票价输入错误").append("\n");
				}
				if(message.length() != 0){
					JOptionPane.showMessageDialog(addTicketFrame,message, "注册失败",JOptionPane.ERROR_MESSAGE);
				}else{

					TicketServiceProxy ticketServiceProxy = new TicketServiceProxy();
					try {
						ticketServiceProxy.insertTicket(startStation,arriveStation,startTime,ticketNum,ticketMoney);
						
					} catch (TicketIsExistException e) {
						JOptionPane.showMessageDialog(addTicketFrame, "该车已经存在","已存在",JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				startStationField.setText(null);
				arrivalStationField.setText(null);
				startTimeField.setText(null);
				ticketNumField.setText(null);
				ticketMoneyField.setText(null);
				message.delete(0, message.length()); //清空message
			}
		});
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addTicketFrame.dispose();
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
}
