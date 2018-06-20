package com.echarts.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * ��¼ҳ��
 */
public class LoginView extends JFrame {

	private JPanel contentPane;

	/**
	 * LoginView�Ĺ��췽����ʵ����ʱ������һ����¼����
	 */
	public LoginView() {
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\LoginViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JTextField userNameField = new JTextField();// �û����������
		JPasswordField userPasswordField = new JPasswordField();// ����������
		JButton loginButton = new JButton();// ��¼��ť
		JButton registerButton = new JButton();// ע�ᰴť

		// ���ø�����������Ժ�״̬
		userNameField.setFont(new Font("����", 1, 20));// ����������ʽ
		userNameField.setForeground(Color.WHITE);// ����������ɫ
		userNameField.setCaretColor(Color.WHITE);// ���ù�����ɫ
		userNameField.setBounds(494, 309, 240, 30);// ���������Ĵ�С��λ��
		userNameField.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		userNameField.setBorder(null);// ���������ı߿�
		
		userPasswordField.setFont(new Font("����", 1, 20));// ����������ʽ
		userPasswordField.setForeground(Color.WHITE);// ����������ɫ
		userPasswordField.setCaretColor(Color.WHITE);// ���ù�����ɫ
		userPasswordField.setBounds(494, 369, 240, 30);// ���������Ĵ�С��λ��
		userPasswordField.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		userPasswordField.setBorder(null);// ���������ı߿�
		
		loginButton.setBounds(340, 452, 180, 60);// ���ð�ť�Ĵ�С��λ��
		loginButton.setContentAreaFilled(false);// ����ť͸����
		loginButton.setBorder(null);// ���ر߿�
		
		registerButton.setBounds(583, 452, 180, 60);// ���ð�ť�Ĵ�С��λ��
		registerButton.setContentAreaFilled(false);// ����ť͸����
		registerButton.setBorder(null);// ���ر߿�

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(userNameField);
		contentPane.add(userPasswordField);
		contentPane.add(loginButton);
		contentPane.add(registerButton);
		contentPane.add(bgLabel);//����ټӱ���ͼ

		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);

		// ��ť�ļ����¼���ע����ť�ĵ���¼��ľ��巽��Ӧд��controller�㣬������������ã�
		// ��¼��ť��Ҫ�󣺳ɹ���¼�������ݿ���ڸ��û���ʱ����ת��ũ��ҳ��FarmView�������ݸ��û���ID��FarmView
		// ע�ᰴť����ת����ɣ����Բ��Ķ�
		// ���������Ҫ�����޸�
		loginButton.addActionListener(new ActionListener() {// ��¼��ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����-��¼��ť");
			}
		});
		registerButton.addActionListener(new ActionListener() {// ע�ᰴť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
				dispose();
			}
		});
	}

	/**
	 * �����õ���������ʹ��LoginView��ʱ��Ҫ��ʵ����
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
