package com.echarts.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.echarts.client.bean.Seed;
import com.echarts.client.bean.ShopComponentList;
import com.echarts.client.controller.ShopViewController;

/**
 * 
 * �̵�ҳ��
 */
public class ShopView extends JFrame {

	private JPanel contentPane;

	/**
	 * ShopView�Ĺ��췽����ʵ����ʱ������һ���̳ǽ��棬���û�ID��Ϊ����
	 */
	public ShopView(int userID) {
		// ��ͨ���û�ID��ȡ�û��ĸ�����Ϣ���ֱ𸳸���Ӧ�����
		ShopViewController svc = new ShopViewController(userID);
		List<Seed> seedList = svc.getSeedList();

		// ������������ı����ݶ����£�������Ҫ�����޸�
		int userMoney = svc.getMoney();// �û����
		String userMessage = "��ã�����Ҫ��Щʲô��";// �������Ͻǵ���ʾ��Ϣ

		// �����ǻ��ƻ������ش��롪����������������������������������������������������������������������������������
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\ShopViewBG-0.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JLabel pageInfo = new JLabel("1");// ���ص�ҳ����Ϣ����¼��ǰҳ����Ϣ
		JLabel userInfo = new JLabel(userMessage);// �������Ͻǵ���Ϣ��ʾ����
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// �û���ҵ���Ϣ��ʾ����
		JButton toRepositoryButton = new JButton();// �ֿⰴť
		JButton toConfigButton = new JButton();// ���ð�ť
		JButton backToFarmButton = new JButton();// ����ũ����ť
		JButton levelUpButton = new JButton();// ũ��������ť
		JButton previousPageButton = new JButton();// ��һҳ��ť
		JButton nextPageButton = new JButton();// ��һҳ��ť
		JTextField pageField = new JTextField("1");// ҳ�������
		JButton jumpPageButton = new JButton();// ҳ����ת��ť
		ShopComponentList[] scl = new ShopComponentList[10];// ��ҳ��10����Ʒ��¼��Ҫ�õ�����ļ���
		for (int i = 0; i < 10; i++) {// ʵ������ҳ�е�ÿ����Ʒ��¼
			scl[i] = new ShopComponentList();
		}

		// ���ø�����������Ժ�״̬
		pageInfo.setBounds(-30, -30, 10, 10);// ���������С��λ��

		userInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		userInfo.setForeground(Color.BLACK);// ����������ɫ
		userInfo.setBounds(20, 25, 310, 30);// ��������Ĵ�С��λ��

		moneyInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		moneyInfo.setForeground(Color.WHITE);// ����������ɫ
		moneyInfo.setBounds(431, 12, 190, 30);// ��������Ĵ�С��λ��

		toRepositoryButton.setBounds(677, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toRepositoryButton.setContentAreaFilled(false);// ����ť͸����
		toRepositoryButton.setBorder(null);// ���ر߿�

		toConfigButton.setBounds(877, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toConfigButton.setContentAreaFilled(false);// ����ť͸����
		toConfigButton.setBorder(null);// ���ر߿�

		backToFarmButton.setBounds(33, 574, 180, 70);// ���ð�ť�Ĵ�С��λ��
		backToFarmButton.setContentAreaFilled(false);// ����ť͸����
		backToFarmButton.setBorder(null);// ���ر߿�

		levelUpButton.setBounds(387, 547, 160, 40);// ���ð�ť�Ĵ�С��λ��
		levelUpButton.setContentAreaFilled(false);// ����ť͸����

		previousPageButton.setBounds(588, 547, 80, 40);// ���ð�ť�Ĵ�С��λ��
		previousPageButton.setContentAreaFilled(false);// ����ť͸����

		nextPageButton.setBounds(688, 547, 80, 40);// ���ð�ť�Ĵ�С��λ��
		nextPageButton.setContentAreaFilled(false);// ����ť͸����

		pageField.setFont(new Font("����", 1, 20));// ����������ʽ
		pageField.setForeground(Color.WHITE);// ����������ɫ
		pageField.setBounds(784, 558, 66, 22);// ���������Ĵ�С��λ��
		pageField.setBackground(new Color(108, 50, 6));// ���������ı�����ɫ
		pageField.setCaretColor(Color.WHITE);// ���������Ĺ����ɫ
		pageField.setBorder(null);// ���ر߿�

		jumpPageButton.setBounds(888, 547, 80, 40);// ���ð�ť�Ĵ�С��λ��
		jumpPageButton.setContentAreaFilled(false);// ����ť͸����

		Icon buttonOfBuy = new ImageIcon("img\\Button\\Button_buy.png");// ����ť��ͼ��
		for (int i = 0; i < seedList.size(); i++) {// ����ÿ�����߼�¼�����Ժ�״̬���������޸ģ�����������ԣ�
			// ��ʼ������
			scl[i].getSeedID().setText("" + seedList.get(i).getSeedId());// ���õ���id��ʼ��������
			scl[i].getSeedName().setText(seedList.get(i).getSeedName());// �����������Ƴ�ʼ��������
			scl[i].getSeedPrice().setText("" + seedList.get(i).getSeedPrice());// �������ӹ���۸��ʼ��������
			scl[i].getSeedInfo().setText(seedList.get(i).getSeedIntroduction());// ���õ��߼���ʼ��������
			scl[i].getSeedGetNumber().setText("" + seedList.get(i).getSeedHarvestNumber());// ���������ճ�����ʼ��������
			scl[i].getSeedGetTime().setText("" + seedList.get(i).getSeedHarvestTime() + " ����");// ���������ճ�ʱ���ʼ��������
			scl[i].getItemNumber().setText("" + seedList.get(i).getSeedNumber());// ���øõ��߳�������ʼ��������
			scl[i].getBuyButton().setIcon(buttonOfBuy);// ���ù���ť��ͼ��
			// ����λ�úʹ�С
			scl[i].getSeedID().setBounds(0, -30, 10, 20);
			scl[i].getSeedName().setBounds(382, 135 + 40 * i, 80, 40);
			scl[i].getSeedPrice().setBounds(475, 135 + 40 * i, 50, 40);
			scl[i].getSeedInfo().setBounds(525, 135 + 40 * i, 170, 40);
			scl[i].getSeedGetNumber().setBounds(720, 135 + 40 * i, 65, 40);
			scl[i].getSeedGetTime().setBounds(780, 135 + 40 * i, 80, 40);
			scl[i].getItemNumber().setBounds(875, 135 + 40 * i, 60, 40);
			scl[i].getBuyButton().setBounds(922, 138 + 40 * i, 52, 32);
		}

		// ��Ӹ������
		contentPane = new JPanel();
		contentPane.add(userInfo);
		contentPane.add(moneyInfo);
		contentPane.add(toRepositoryButton);
		contentPane.add(toConfigButton);
		contentPane.add(backToFarmButton);
		contentPane.add(levelUpButton);
		contentPane.add(previousPageButton);
		contentPane.add(nextPageButton);
		contentPane.add(pageField);
		contentPane.add(jumpPageButton);
		for (int i = 0; i < 10; i++) {
			contentPane.add(scl[i].getSeedID());
			contentPane.add(scl[i].getSeedName());
			contentPane.add(scl[i].getSeedPrice());
			contentPane.add(scl[i].getSeedInfo());
			contentPane.add(scl[i].getSeedGetNumber());
			contentPane.add(scl[i].getSeedGetTime());
			contentPane.add(scl[i].getItemNumber());
			contentPane.add(scl[i].getBuyButton());
		}
		contentPane.add(bgLabel);// ����ټӱ���ͼ

		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);

		// ���ﶯ������ط���
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int test = 0;
			int time = 0;
			boolean start = true;

			public void run() {
				time++;
				if (time == 21) {
					start = false;
				} else if (time == 50) {
					userInfo.setText("���������⿴���򡭡�Ҳ�С�");
					start = true;
				} else if (time == 71) {
					start = false;
				} else if (time == 100) {
					userInfo.setText("��������ͦ����ģ��ʺ����");
					start = true;
				}else if (time == 121) {
					start = false;
				} else if (time == 150) {
					userInfo.setText("�����ĵ����Ӳ���Ҫ��ˮʩ�ʡ�");
					start = true;
					time = 0;
				}
				if (start) {
					test++;
					if (test > 3)
						test = 0;
					bgLabel.setIcon(new ImageIcon("img\\ShopViewBG-" + test + ".png"));
				}

			}
		};
		timer.schedule(task, 100L, 100L);

		// ��ť�ļ����¼�
		// ���������Ҫ�����޸�
		toRepositoryButton.addActionListener(new ActionListener() {// �ֿⰴť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new RepositoryView(userID);
					timer.cancel();
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		toConfigButton.addActionListener(new ActionListener() {// ���ð�ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigView(userID);
				timer.cancel();
				dispose();
			}
		});
		backToFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				timer.cancel();
				dispose();
			}
		});
		levelUpButton.addActionListener(new ShopViewController(this, userID, levelUpButton, moneyInfo));// ũ��������ť�ĵ���¼�
		previousPageButton.addActionListener(
				new ShopViewController(this, userID, 1, pageField, pageInfo, previousPageButton, scl));// ��һҳ��ť�ĵ���¼�
		nextPageButton
				.addActionListener(new ShopViewController(this, userID, 2, pageField, pageInfo, nextPageButton, scl));// ��һҳ��ť�ĵ���¼�
		jumpPageButton
				.addActionListener(new ShopViewController(this, userID, pageField, pageInfo, jumpPageButton, scl));// ��ת��ť�ĵ���¼�
		for (ShopComponentList value : scl) {// �������Ӽ�¼��Ӧ�İ�ť�ĵ���¼�// ����ť�ĵ���¼�
			value.getBuyButton().addActionListener(new ShopViewController(this, userID, moneyInfo, value));
		}
	}

}
