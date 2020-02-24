package com.jc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.jc.constant.ComMethods;
import com.jc.constant.Constants;
import com.jc.entity.User;
import com.jc.exception.LoginFailException;
import com.jc.exception.UserAlreadyExistsException;
import com.jc.service.proxy.UserServiceProxy;
/**
 * 注册页面（只能进行旅客注册，注册完后跳转登录页面）
 * @author soft01
 *
 */
public class RegistFrame {
	JFrame registFrame = null;
	JLabel welcomLabel = null;
	JLabel usernameLabel = null;
	JTextField usernameField = null;
	JLabel passwordLabel = null;
	JPasswordField passwordField = null;
	JLabel againPasswordLabel = null; //再次输入密码
	JPasswordField againPasswordField = null;
	JLabel truenameLabel = null;//真实姓名
	JTextField truenameField = null;
	JLabel phoneNumLabel = null;//手机号
	JTextField phoneNumField = null;
	JLabel idCardNumLabel = null;//身份证
	JTextField idCardNumField = null;
	JButton registerButton = null; //注册
	JButton reloginButton = null; //返回登录界面
	public void init(){
		registFrame = new JFrame("车票售卖系统 -- 注册");
		welcomLabel = new JLabel("欢迎注册^-^");
		usernameLabel = new JLabel("登 录 名:");
		usernameField = new JTextField();
		
		passwordLabel = new JLabel("密    码:");
		passwordField = new JPasswordField();
		
		againPasswordLabel = new JLabel("确认密码:");
		againPasswordField = new JPasswordField();
		
		truenameLabel = new JLabel("真实姓名:");
		truenameField = new JTextField();
		
		
		phoneNumLabel = new JLabel("手 机 号:");
		phoneNumField = new JTextField();
		
		idCardNumLabel = new JLabel("身 份 证:");
		idCardNumField = new JTextField();
		
		registerButton = new JButton("注册");
		reloginButton = new JButton("返回登录");
	}
	public void build(){
		registFrame.setSize(300, 500);
		registFrame.setLocation(200, 200);
		registFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		registFrame.setLayout(null);
		welcomLabel.setBounds(100, 10, 200, 50);
		registFrame.add(welcomLabel);
		
		usernameLabel.setBounds(50, 80, 80, 35);
		registFrame.add(usernameLabel);
		usernameField.setBounds(140, 80, 120, 25);
		registFrame.add(usernameField);
		
		passwordLabel.setBounds(50, 130, 80, 35);
		registFrame.add(passwordLabel);
		passwordField.setBounds(140, 130, 120, 25);
		registFrame.add(passwordField);
//		
		againPasswordLabel.setBounds(50, 180, 80, 35);
		registFrame.add(againPasswordLabel);
		againPasswordField.setBounds(140, 180, 120, 25);
		registFrame.add(againPasswordField);
//		
		truenameLabel.setBounds(50, 230, 80, 35);
		registFrame.add(truenameLabel);
		truenameField.setBounds(140, 230, 120, 25);
		registFrame.add(truenameField);
//		
		phoneNumLabel.setBounds(50, 280, 80, 35);
		registFrame.add(phoneNumLabel);
		phoneNumField.setBounds(140, 280, 120, 25);
		registFrame.add(phoneNumField);
		
		idCardNumLabel.setBounds(50, 330, 80, 35);
		registFrame.add(idCardNumLabel);
		idCardNumField.setBounds(140, 330, 120, 25);
		registFrame.add(idCardNumField);
		
		registerButton.setBounds(40, 400, 100, 25);
		registFrame.add(registerButton);
//		
		reloginButton.setBounds(160, 400, 100, 25);
		registFrame.add(reloginButton);
		registFrame.setVisible(true);
	}
	public void addAction(){
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserServiceProxy userServiceProxy = new UserServiceProxy();
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String againPassword = new String(againPasswordField.getPassword());
				String truename = truenameField.getText();
				String phoneNum = phoneNumField.getText();
				String idCardNum = idCardNumField.getText();
				StringBuffer message = new StringBuffer();
				
				boolean isPhone = ComMethods.matches(Constants.REGEX_PHONENUMBER,phoneNum);
				boolean isIdCard = ComMethods.matches(Constants.REGEX_IDCARD,idCardNum);
				
				if(!password.equals(againPassword)){  //判断信息是否正确
					message.append("两次输入密码不匹配").append("\n");
				}
				if(isPhone != true){
					message.append("手机号码输入错误").append("\n");
				}
				if(isIdCard != true){
					message.append("身份证号码输入错误").append("\n");
				}
				if(message.length() != 0){  //如果不正确就清空注册信息
					JOptionPane.showMessageDialog(registFrame,message, "注册失败",JOptionPane.ERROR_MESSAGE);
					message.delete(0, message.length()); //清空message
				}else{
					
					try {
						userServiceProxy.regist(username, password, truename, phoneNum, idCardNum);
						JOptionPane.showMessageDialog(registFrame, "注册成功","注册成功",JOptionPane.INFORMATION_MESSAGE);
						new LoginFrame().start();
						registFrame.setVisible(false);
					} catch (UserAlreadyExistsException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		reloginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginFrame().start();
				registFrame.setVisible(false);
			}
		});
	}
	public void start(){
		init();
		build();
		addAction();
	}
}
