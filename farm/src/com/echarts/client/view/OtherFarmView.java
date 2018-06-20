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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * ũ��ҳ��
 */
public class OtherFarmView extends JFrame {

	private JPanel contentPane;

	/**
	 * FarmView�Ĺ��췽����ʵ����ʱ������һ����������ң�ũ�����棬���û�ID����һ���û���ID��Ϊ������
	 */
	public OtherFarmView(int userID,int otherUserID) {
		// ��ͨ���û�ID��ȡ�û��ĸ�����Ϣ���ֱ𸳸���Ӧ�����

		// ������������ı����ݶ����£�������Ҫ�����޸�
		int otherFarmLV = 1;
		String otherUserName = "ttt";// ������ҵ��û���
		String otherUserMessage = "������ " + otherUserName + " ��ũ��";// �������Ͻǵ���ʾ��Ϣ
		int[] plantNumber = new int[15];// ʮ������ص�ֲ��ı�ţ��û�����������������������鼴��ʹ��
										// ��ֲ��δ�������������Ϊ-1��û����ֲ������Ϊ0��Ĭ��Ϊ0

		// �����ǻ��ƻ������ش��롪����������������������������������������������������������������������������������
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\OtherFarmViewBG_lv" + otherFarmLV + ".png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JLabel otherFarmInfo = new JLabel(otherUserMessage);// �������Ͻǵ���Ϣ��ʾ����
		JButton backToFarmButton = new JButton();// ����ũ����ť

		// ���ø�����������Ժ�״̬
		otherFarmInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		otherFarmInfo.setForeground(Color.WHITE);// ����������ɫ
		otherFarmInfo.setBounds(24, 12, 310, 30);// ��������Ĵ�С��λ��

		backToFarmButton.setBounds(793, 574, 180, 70);// ���ð�ť�Ĵ�С��λ��
		backToFarmButton.setContentAreaFilled(false);// ����ť͸����
		backToFarmButton.setBorder(null);// ���ر߿�

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(otherFarmInfo);
		contentPane.add(backToFarmButton);

		// ����������������start-���ذ�ť&ֲ����ʾ-����ũ���ȼ���Ӷ�Ӧ���������ذ�ť������������
		// ��ʼ��
		JButton[] area = new JButton[15];// ���ذ�ť
		Icon[] plant = new ImageIcon[15];// ֲ��ͼ��
		for (int i = 0; i < 15; i++) {
			plant[i] = new ImageIcon("img\\Plants\\Plants_" + plantNumber[i] + ".png");// ���ݸ����ص�ֲ����
			area[i] = new JButton(plant[i]);// ��ֲ��ͼ������ذ�ť
		}
		// ��������
		switch (otherFarmLV) {
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
		backToFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				dispose();
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
	 * �����õ���������ʹ��OtherFarmView��ʱ��Ҫ��ʵ����
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtherFarmView frame = new OtherFarmView(1,2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
