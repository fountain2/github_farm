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
 * ע��ҳ��
 */
public class RegisterView extends JFrame {

	private JPanel contentPane;

	/**
	 * RegisterView�Ĺ��췽����ʵ����ʱ������һ��ע�����
	 */
	public RegisterView() {
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\RegisterViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JTextField userNameField = new JTextField();// �û����������
		JPasswordField userPasswordField1 = new JPasswordField();// ����������
		JPasswordField userPasswordField2 = new JPasswordField();// ȷ������������
		JButton registerButton = new JButton();// ע�ᰴť
		JButton backButton = new JButton();// ���ذ�ť

		// ���ø�����������Ժ�״̬
		userNameField.setFont(new Font("����", 1, 20));// ����������ʽ
		userNameField.setForeground(Color.WHITE);// ����������ɫ
		userNameField.setCaretColor(Color.WHITE);// ���ù�����ɫ
		userNameField.setBounds(509,288,240,30);// ���������Ĵ�С��λ��
		userNameField.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		userNameField.setBorder(null);// ���������ı߿�
		
		userPasswordField1.setFont(new Font("����", 1, 20));// ����������ʽ
		userPasswordField1.setForeground(Color.WHITE);// ����������ɫ
		userPasswordField1.setCaretColor(Color.WHITE);// ���ù�����ɫ
		userPasswordField1.setBounds(509,338,240,30);// ���������Ĵ�С��λ��
		userPasswordField1.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		userPasswordField1.setBorder(null);// ���������ı߿�
		
		userPasswordField2.setFont(new Font("����", 1, 20));// ����������ʽ
		userPasswordField2.setForeground(Color.WHITE);// ����������ɫ
		userPasswordField2.setCaretColor(Color.WHITE);// ���ù�����ɫ
		userPasswordField2.setBounds(509,388,240,30);// ���������Ĵ�С��λ��
		userPasswordField2.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		userPasswordField2.setBorder(null);// ���������ı߿�
		
		registerButton.setBounds(340,452,180,60);// ���ð�ť�Ĵ�С��λ��
		registerButton.setContentAreaFilled(false);// ����ť͸����
		registerButton.setBorder(null);// ���ر߿�
		
		backButton.setBounds(583,452,180,60);// ���ð�ť�Ĵ�С��λ��
		backButton.setContentAreaFilled(false);// ����ť͸����
		backButton.setBorder(null);// ���ر߿�

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(userNameField);
		contentPane.add(userPasswordField1);
		contentPane.add(userPasswordField2);
		contentPane.add(registerButton);
		contentPane.add(backButton);
		contentPane.add(bgLabel);//����ټӱ���ͼ

		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);
		
		// ��ť�ļ����¼���ע����ť�ĵ���¼��ľ��巽��Ӧд��controller�㣬������������ã�
		// ע�ᰴť��Ҫ�󣺳ɹ�ע��ʱ������ע��ɹ�����Ϣ��֮����ת����¼ҳ��LoginView
		// ���ذ�ť����ת����ɣ����Բ��Ķ�
		// ���������Ҫ�����޸�
		registerButton.addActionListener(new ActionListener() {// ע�ᰴť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����-ע�ᰴť");
			}
		});
		backButton.addActionListener(new ActionListener() {// ���ذ�ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView();
				dispose();
			}
		});
	}

	/**
	 * �����õ���������ʹ��RegisterView��ʱ��Ҫ��ʵ����
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView frame = new RegisterView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
