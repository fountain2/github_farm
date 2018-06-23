package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.User;
import com.echarts.client.bean.twicepack.RegisterViewBean;
import com.echarts.client.model.UserModel;
import com.echarts.client.view.LoginView;
import com.echarts.util.IOperation;

/**
 * 传送数据 
 * 1、注册按钮( new Register(xxx).init()) 
 * 2、登录按钮( new FarmView(xxxx ).init() )
 *
 */
public class UserController implements ActionListener{

	private JFrame jf;
	private JTextField userNameField;
	private JPasswordField userPasswordField1;
	private JPasswordField userPasswordField2;
	private JButton registerButton;
	
	
	public UserController(JTextField userNameField, JPasswordField userPasswordField1,
			JPasswordField userPasswordField2, JButton registerButton, JFrame jf) {
		this.userNameField = userNameField;
		this.userPasswordField1 = userPasswordField1;
		this.userPasswordField2 = userPasswordField2;
		this.registerButton = registerButton;
		this.jf = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registerButton) {
			String userName = userNameField.getText();
			if ("".equals(userName.trim())) {
				JOptionPane.showMessageDialog(jf, "用户名不能为空", "用户名不能为空", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String userPassword1 = String.valueOf(userPasswordField1.getPassword());
			String userPassword2 = String.valueOf(userPasswordField2.getPassword());
			User user = new User();
			user.setUserName(userName);
			if(userPassword1.equals(userPassword2) && (!"".equals(userPassword1.trim()))) {
				user.setUserPaswword(userPassword1);
			}else {
				JOptionPane.showMessageDialog(jf, "密码不一致", "密码不一致", JOptionPane.ERROR_MESSAGE);
				userPasswordField1.setText("");
				userPasswordField2.setText("");
				return;
			}
			
			RegisterViewBean rvb = new RegisterViewBean();
			rvb.setUser(user);
			rvb.setOperation("query");
			
			Message message = new Message(rvb, IOperation.REGISTERVIEW);
			
			try {
				User user1 = new UserModel().queryUser(message);
				if(user1 != null) {
					JOptionPane.showMessageDialog(jf, "用户名已存在", "用户名已存在", JOptionPane.ERROR_MESSAGE);
					userNameField.setText("");
					userPasswordField1.setText("");
					userPasswordField2.setText("");
					return;
				}
				RegisterViewBean rvb2 = new RegisterViewBean();
				rvb2.setUser(user);
				rvb2.setOperation("insert");
				
				Message message2 = new Message(rvb2, IOperation.REGISTERVIEW);
				
				new UserModel().registerUser(message2);
				JOptionPane.showMessageDialog(jf, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
				new LoginView();
				jf.dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
