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
import javax.swing.border.EmptyBorder;

/**
 * 
 * ����ҳ��
 */
public class ConfigView extends JFrame {

	private JPanel contentPane;

	/**
	 * ConfigView�Ĺ��췽����ʵ����ʱ������һ�����ý��棬���û�ID��Ϊ����
	 */
	public ConfigView(int userID) {
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\ConfigViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ��������� 
		JPasswordField oldPasswordField = new JPasswordField();// ������������
		JPasswordField newPasswordField1 = new JPasswordField();// ������������
		JPasswordField newPasswordField2 = new JPasswordField();// ȷ��������������
		JButton changePasswordButton = new JButton();// �޸����밴ť
		JButton changeUserButton = new JButton();// �л��˻���ť
		JButton backToFarmButton = new JButton();// ����ũ����ť

		// ���ø�����������Ժ�״̬
		oldPasswordField.setFont(new Font("����", 1, 20));// ����������ʽ
		oldPasswordField.setForeground(Color.WHITE);// ����������ɫ
		oldPasswordField.setCaretColor(Color.WHITE);// ���ù�����ɫ
		oldPasswordField.setBounds(509, 228, 240, 30);// ���������Ĵ�С��λ��
		oldPasswordField.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		oldPasswordField.setBorder(null);// ���������ı߿�

		newPasswordField1.setFont(new Font("����", 1, 20));// ����������ʽ
		newPasswordField1.setForeground(Color.WHITE);// ����������ɫ
		newPasswordField1.setCaretColor(Color.WHITE);// ���ù�����ɫ
		newPasswordField1.setBounds(509, 278, 240, 30);// ���������Ĵ�С��λ��
		newPasswordField1.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		newPasswordField1.setBorder(null);// ���������ı߿�

		newPasswordField2.setFont(new Font("����", 1, 20));// ����������ʽ
		newPasswordField2.setForeground(Color.WHITE);// ����������ɫ
		newPasswordField2.setCaretColor(Color.WHITE);// ���ù�����ɫ
		newPasswordField2.setBounds(509, 328, 240, 30);// ���������Ĵ�С��λ��
		newPasswordField2.setBackground(new Color(136, 76, 30));// ���������ı�����ɫ
		newPasswordField2.setBorder(null);// ���������ı߿�

		changePasswordButton.setBounds(340, 392, 180, 60);// ���ð�ť�Ĵ�С��λ��
		changePasswordButton.setContentAreaFilled(false);// ����ť͸����
		changePasswordButton.setBorder(null);// ���ر߿�

		changeUserButton.setBounds(583, 392, 180, 60);// ���ð�ť�Ĵ�С��λ��
		changeUserButton.setContentAreaFilled(false);// ����ť͸����
		changeUserButton.setBorder(null);// ���ر߿�
		
		backToFarmButton.setBounds(793,574,180,70);// ���ð�ť�Ĵ�С��λ��
		backToFarmButton.setContentAreaFilled(false);// ����ť͸����
		backToFarmButton.setBorder(null);// ���ر߿�

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(oldPasswordField);
		contentPane.add(newPasswordField1);
		contentPane.add(newPasswordField2);
		contentPane.add(changePasswordButton);
		contentPane.add(changeUserButton);
		contentPane.add(backToFarmButton);
		contentPane.add(bgLabel);//����ټӱ���ͼ

		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);
		
		// ��ť�ļ����¼���ע����ť�ĵ���¼��ľ��巽��Ӧд��controller�㣬������������ã�
		// �޸����밴ť��Ҫ�������޸ĳɹ�ʱ�������޸ĳɹ�����Ϣ��֮��������������
		// �л��˻���ť�ͷ���ũ����ť����ת��ʵ�֣����Բ��Ķ�
		// ���������Ҫ�����޸�
		changePasswordButton.addActionListener(new ActionListener() {// �޸����밴ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����-�޸����밴ť");
			}
		});
		changeUserButton.addActionListener(new ActionListener() {// �л��˻���ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView();
				dispose();
			}
		});
		backToFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				dispose();
			}
		});
	}

	/**
	 * �����õ���������ʹ��ConfigView��ʱ��Ҫ��ʵ����
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigView frame = new ConfigView(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
