package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.User;
import com.echarts.client.bean.twicepack.LoginViewBean;
import com.echarts.client.model.UserModel;
import com.echarts.client.view.FarmView;
import com.echarts.util.IOperation;

public class LoginController implements ActionListener{
	private JFrame jf;
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	private JButton loginButton;
	
	public LoginController(JFrame jf, JTextField userNameField, JPasswordField userPasswordField, JButton loginButton) {
		this.jf = jf;
		this.userNameField = userNameField;
		this.userPasswordField = userPasswordField;
		this.loginButton = loginButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			String userName = userNameField.getText();
			String userPassword = String.valueOf(userPasswordField.getPassword());
			User user = new User();
			user.setUserName(userName);
			user.setUserPaswword(userPassword);
			
			LoginViewBean lvb = new LoginViewBean();
			lvb.setUser(user);
			
			Message message = new Message(lvb, IOperation.LOGINVIEW);
			
			try {
				User user1 = new UserModel().login(message);
				if(user1 == null) {
					JOptionPane.showMessageDialog(jf, "«ÎºÏ≤È’Àªß «∑Ò¥Ê‘⁄£¨√‹¬Î «∑Ò’˝»∑", "µ«¬Ω ß∞‹", JOptionPane.ERROR_MESSAGE);
					userNameField.setText("");
					userPasswordField.setText("");
					return;
				}else {
					new FarmView(user1.getUserId());
					jf.dispose();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}