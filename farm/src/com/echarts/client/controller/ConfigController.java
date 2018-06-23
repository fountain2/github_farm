package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.twicepack.ConfigViewBean;
import com.echarts.client.model.ConfigModel;
import com.echarts.client.model.FarmModel;
import com.echarts.util.IOperation;
/**
 * 1、修改密码按钮（showview）
 * 2、更改账户按钮（showview）
 *
 */
public class ConfigController implements ActionListener{

	//修改密码界面提交时，需要提交的对象
	private JPasswordField oldPasswordField;// 旧密码的输入框
	private JPasswordField newPasswordField1;// 新密码的输入框
	private JPasswordField newPasswordField2;// 确认新密码的输入框
	private JButton changePasswordButton;// 修改密码按钮
	private int userID;
	private JFrame ConfigView;
	
	
	/**
	 * 在修改密码的界面，提交信息的时候，需要调用这个构造方法。
	 * @param oldPasswordField
	 * @param newPasswordField1
	 * @param newPasswordField2
	 * @param changePasswordButton
	 */
	public ConfigController(JPasswordField oldPasswordField, JPasswordField newPasswordField1,
			JPasswordField newPasswordField2, JButton changePasswordButton,int userID) {
		this.oldPasswordField = oldPasswordField;
		this.newPasswordField1 = newPasswordField1;
		this.newPasswordField2 = newPasswordField2;
		this.changePasswordButton = changePasswordButton;
		this.userID = userID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//判断是否是修改密码按钮
		if(e.getSource() == changePasswordButton){
			//接收界面传过来的数据
			String oldPassword = String.valueOf(oldPasswordField.getPassword());
			String newPassword1 = String.valueOf(newPasswordField1.getPassword());
			String newPassword2 = String.valueOf(newPasswordField2.getPassword());

			
			//判断两次输入的密码是否一致
			if(newPassword1.equals(newPassword2)&& (!"".equals(newPassword1.trim()))){
				ConfigViewBean configViewBean = new ConfigViewBean(oldPassword, newPassword1, userID);
				Message message = new Message(configViewBean,IOperation.CONFIGVIEW);
				 //将对象和操作动作打包
	            
	            //将message对象往后面传递
				try {
					ConfigViewBean backMessage = new ConfigModel().configUser(message);
					String result = backMessage.getResult();
					if(result.equals("修改密码成功")){
						oldPasswordField.setText("");
						newPasswordField1.setText("");
						newPasswordField2.setText("");
						JOptionPane.showMessageDialog(changePasswordButton, "修改密码成功");
					}else{
						oldPasswordField.setText("");
						newPasswordField1.setText("");
						newPasswordField2.setText("");
						JOptionPane.showMessageDialog(changePasswordButton, "旧密码不匹配");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				oldPasswordField.setText("");
				newPasswordField1.setText("");
				newPasswordField2.setText("");
				JOptionPane.showMessageDialog(changePasswordButton, "输入的密码不一致", "密码不一致",JOptionPane.ERROR_MESSAGE);
				return;
			}
			         
                      
            	
		}
		
	}
	
}
