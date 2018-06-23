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
 * 1���޸����밴ť��showview��
 * 2�������˻���ť��showview��
 *
 */
public class ConfigController implements ActionListener{

	//�޸���������ύʱ����Ҫ�ύ�Ķ���
	private JPasswordField oldPasswordField;// ������������
	private JPasswordField newPasswordField1;// ������������
	private JPasswordField newPasswordField2;// ȷ��������������
	private JButton changePasswordButton;// �޸����밴ť
	private int userID;
	private JFrame ConfigView;
	
	
	/**
	 * ���޸�����Ľ��棬�ύ��Ϣ��ʱ����Ҫ����������췽����
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
		//�ж��Ƿ����޸����밴ť
		if(e.getSource() == changePasswordButton){
			//���ս��洫����������
			String oldPassword = String.valueOf(oldPasswordField.getPassword());
			String newPassword1 = String.valueOf(newPasswordField1.getPassword());
			String newPassword2 = String.valueOf(newPasswordField2.getPassword());

			
			//�ж���������������Ƿ�һ��
			if(newPassword1.equals(newPassword2)&& (!"".equals(newPassword1.trim()))){
				ConfigViewBean configViewBean = new ConfigViewBean(oldPassword, newPassword1, userID);
				Message message = new Message(configViewBean,IOperation.CONFIGVIEW);
				 //������Ͳ����������
	            
	            //��message���������洫��
				try {
					ConfigViewBean backMessage = new ConfigModel().configUser(message);
					String result = backMessage.getResult();
					if(result.equals("�޸�����ɹ�")){
						oldPasswordField.setText("");
						newPasswordField1.setText("");
						newPasswordField2.setText("");
						JOptionPane.showMessageDialog(changePasswordButton, "�޸�����ɹ�");
					}else{
						oldPasswordField.setText("");
						newPasswordField1.setText("");
						newPasswordField2.setText("");
						JOptionPane.showMessageDialog(changePasswordButton, "�����벻ƥ��");
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
				JOptionPane.showMessageDialog(changePasswordButton, "��������벻һ��", "���벻һ��",JOptionPane.ERROR_MESSAGE);
				return;
			}
			         
                      
            	
		}
		
	}
	
}
