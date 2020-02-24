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

import com.jc.entity.User;
import com.jc.exception.LoginFailException;
import com.jc.factory.ObjectFactory;
import com.jc.service.UserService;
import com.jc.service.impl.UserServiceImpl;
import com.jc.service.proxy.UserServiceProxy;
/**
 * 登录页面
 * @author soft01
 *
 */
public class LoginFrame {
	JFrame loginFrame = null;
	JLabel welcomLabel = null;
	JLabel usernameLabel = null;
	JTextField usernameField = null;
	JLabel passwordLabel = null;
	JPasswordField passwordField = null;
	JButton loginButton = null;
	JButton registerButton = null;
	public void init(){
		loginFrame = new JFrame("车票售卖系统 -- 登录");
		welcomLabel = new JLabel("欢迎使用本系统");
		usernameLabel = new JLabel("用户名:");
		usernameField = new JTextField();
		passwordLabel = new JLabel("密  码:");
		passwordField = new JPasswordField();
		loginButton = new JButton("登录");
		registerButton = new JButton("注册");
	}
	public void build(){
		loginFrame.setSize(300, 300);
		loginFrame.setLocation(200, 200);
		loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loginFrame.setLayout(null);
		welcomLabel.setBounds(100, 10, 200, 50);
		loginFrame.add(welcomLabel);
		usernameLabel.setBounds(50, 80, 80, 35);
		loginFrame.add(usernameLabel);
		usernameField.setBounds(140, 80, 100, 25);
		loginFrame.add(usernameField);
		passwordLabel.setBounds(50, 120, 80, 35);
		loginFrame.add(passwordLabel);
		passwordField.setBounds(140, 120, 100, 25);
		loginFrame.add(passwordField);
		loginButton.setBounds(50, 200, 80, 30);
		loginFrame.add(loginButton);
		registerButton.setBounds(150, 200, 80, 30);
		loginFrame.add(registerButton);
		loginFrame.setVisible(true);
	}
	public void addAction(){
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();  //获取用户名密码
				String password = new String(passwordField.getPassword());
				if(username.equals("")||password.equals("")){
					JOptionPane.showMessageDialog(loginFrame, "用户名或密码为空","登录失败",JOptionPane.ERROR_MESSAGE);
				}else{
					UserServiceProxy userServiceProxy = new UserServiceProxy(); //获取userService的代理类
					try {
						User user = userServiceProxy.login(username, password);
						if(user.getUserMode() == 1){
							JOptionPane.showMessageDialog(loginFrame, "登录成功","管理员登录成功",JOptionPane.INFORMATION_MESSAGE);
							new AdministratorFrame(user).start();
							loginFrame.setVisible(false);	
						}else if(user.getUserMode() == 0){
							JOptionPane.showMessageDialog(loginFrame, "登录成功","用户登录成功",JOptionPane.INFORMATION_MESSAGE);
							new MainFrame(user).start();
							loginFrame.setVisible(false);
						}else if(user.getUserMode() == 2){
							JOptionPane.showMessageDialog(loginFrame, "您已被禁止登录","登录失败",JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (LoginFailException e1) {
						JOptionPane.showMessageDialog(loginFrame, "登录失败","登录失败",JOptionPane.ERROR_MESSAGE);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegistFrame().start();
				loginFrame.setVisible(false);
			}
		});
	}
	public void start(){
		init();
		build();
		addAction();
	}
}
