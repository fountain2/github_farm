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
import javax.swing.border.EmptyBorder;

import com.echarts.client.bean.User;

/**
 * 
 * ũ��ҳ��
 */
public class FarmView extends JFrame {

	private JPanel contentPane;

	/**
	 * FarmView�Ĺ��췽����ʵ����ʱ������һ��ũ�����棬���û�ID��Ϊ����
	 */
	public FarmView(int userID) {
		// ��ͨ���û�ID��ȡ�û��ĸ�����Ϣ���ֱ𸳸���Ӧ�����
//		User user = new 

		// ������������ı����ݶ����£�������Ҫ�����޸�
		int farmLV = 3;// ũ���ȼ�
		int userMoney = 1100;// �û����
		String userName = "foun";// �û���
		String userMessage = "���," + userName + "����ӭ�ص�ũ��(LV" + farmLV + ")";// �������Ͻǵ���ʾ��Ϣ
		int[] plantNumber = new int[15];// ʮ������ص�ֲ��ı�ţ��û�����������������������鼴��ʹ��
										// ��ֲ��δ�������������Ϊ-1��û����ֲ������Ϊ0��Ĭ��Ϊ0

		// �����ǻ��ƻ������ش��롪����������������������������������������������������������������������������������
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\FarmViewBG_lv" + farmLV + ".png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JLabel farmInfo = new JLabel(userMessage);// �������Ͻǵ���Ϣ��ʾ����
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// �û���ҵ���Ϣ��ʾ����
		JButton toRepositoryButton = new JButton();// �ֿⰴť
		JButton toShopButton = new JButton();// �̳ǰ�ť
		JButton toConfigButton = new JButton();// ���ð�ť
		JButton toOtherFarmButton = new JButton();// ����ũ����ť

		// ���ø�����������Ժ�״̬
		farmInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		farmInfo.setForeground(Color.WHITE);// ����������ɫ
		farmInfo.setBounds(24, 12, 310, 30);// ��������Ĵ�С��λ��

		moneyInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		moneyInfo.setForeground(Color.WHITE);// ����������ɫ
		moneyInfo.setBounds(431, 12, 190, 30);// ��������Ĵ�С��λ��

		toRepositoryButton.setBounds(677, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toRepositoryButton.setContentAreaFilled(false);// ����ť͸����
		toRepositoryButton.setBorder(null);// ���ر߿�

		toShopButton.setBounds(777, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toShopButton.setContentAreaFilled(false);// ����ť͸����
		toShopButton.setBorder(null);// ���ر߿�

		toConfigButton.setBounds(877, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toConfigButton.setContentAreaFilled(false);// ����ť͸����
		toConfigButton.setBorder(null);// ���ر߿�

		toOtherFarmButton.setBounds(793, 574, 180, 70);// ���ð�ť�Ĵ�С��λ��
		toOtherFarmButton.setContentAreaFilled(false);// ����ť͸����
		toOtherFarmButton.setBorder(null);// ���ر߿�

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(farmInfo);
		contentPane.add(moneyInfo);
		contentPane.add(toRepositoryButton);
		contentPane.add(toShopButton);
		contentPane.add(toConfigButton);
		contentPane.add(toOtherFarmButton);

		// ����������������start-���ذ�ť&ֲ����ʾ-����ũ���ȼ���Ӷ�Ӧ���������ذ�ť������������
		// ��ʼ��
		JButton[] area = new JButton[15];// ���ذ�ť
		Icon[] plant = new ImageIcon[15];// ֲ��ͼ��
		for (int i = 0; i < 15; i++) {
			plant[i] = new ImageIcon("img\\Plants\\Plants_" + plantNumber[i] + ".png");// ���ݸ����ص�ֲ����
			area[i] = new JButton(plant[i]);// ��ֲ��ͼ������ذ�ť
		}
		// ��������
		switch (farmLV) {
		case 6:
			for (int i = 12; i <= 14; i++) {
				area[i].setBounds(612, 184 + (i - 12) * 116, 100, 100);
			}
		case 5:
			area[10].setBounds(372, 416, 100, 100);
			area[11].setBounds(492, 416, 100, 100);
		case 4:
			area[8].setBounds(492, 184, 100, 100);
			area[9].setBounds(492, 300, 100, 100);
		case 3:
			area[6].setBounds(372, 184, 100, 100);
			area[7].setBounds(372, 300, 100, 100);
		case 2:
			area[4].setBounds(132, 416, 100, 100);
			area[5].setBounds(252, 416, 100, 100);
		case 1:
			area[0].setBounds(132, 184, 100, 100);
			area[1].setBounds(252, 184, 100, 100);
			area[2].setBounds(132, 300, 100, 100);
			area[3].setBounds(252, 300, 100, 100);
		}
		for (int i = 0; i < 15; i++) {
			area[i].setContentAreaFilled(false);
			area[i].setBorder(null);
			// ������
			contentPane.add(area[i]);
		}
		// ����������������end-���ذ�ť&ֲ����ʾ-����ũ���ȼ���Ӷ�Ӧ���������ذ�ť������������
		contentPane.add(bgLabel);// ����ټӱ���ͼ

		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);

		// ��ť�ļ����¼���ע����ť�ĵ���¼��ľ��巽��Ӧд��controller�㣬������������ã�
		// ���������Ҫ�����޸�
		toRepositoryButton.addActionListener(new ActionListener() {// �ֿⰴť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new RepositoryView(userID);
				dispose();
			}
		});
		toShopButton.addActionListener(new ActionListener() {// �̳ǰ�ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShopView(userID);
				dispose();
			}
		});
		toConfigButton.addActionListener(new ActionListener() {// ���ð�ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigView(userID);
				dispose();
			}
		});
		toOtherFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new OtherFarmView(userID, 2);
				System.out.println("����-����ũ����ť");
			}
		});
		for (int i = 0; i < 15; i++) {
			area[i].addActionListener(new ActionListener() {// ���صĵ���¼�
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("����-���ذ�ť");
				}
			});
		}
	}

	/**
	 * �����õ���������ʹ��FarmView��ʱ��Ҫ��ʵ����
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmView frame = new FarmView(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
