package com.echarts.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.echarts.client.bean.Repository;
import com.echarts.client.bean.RepositoryComponentList;
import com.echarts.client.bean.User;
import com.echarts.client.controller.RepositoryController;

/**
 * 
 * �ֿ�ҳ��
 */
public class RepositoryView extends JFrame {
	// ������������ı����ݶ����£�������Ҫ�����޸�
	int userMoney = 0;// �û����
	String userName = "";// �û���
	String userMessage = "";// �������Ͻǵ���ʾ��Ϣ
	public int page = 1;// �ֿ��ҳ��
	public Repository[] repositorys;// �ֿ��
	public User user;// �û�����ȡ�û���

	private JPanel contentPane;

	/**
	 * RepositoryView�Ĺ��췽��
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws UnknownHostException
	 */
	public RepositoryView(int userID) throws UnknownHostException, ClassNotFoundException, IOException {
		// �����ݣ����ݱ�������ʾ��
		RepositoryController rc = new RepositoryController();
		repositorys = rc.checkRepository(userID, 1);
		initUI(userID);
	}

	/**
	 * ����һ���ֿ���棬���û�ID��Ϊ����
	 * 
	 * @param userID
	 *            ����ֿ��ӵ����ID�����������ID��ʾ�ֿ⡣
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws UnknownHostException
	 */
	public void initUI(int userID) throws UnknownHostException, ClassNotFoundException, IOException {
		// ��ͨ���û�ID��ȡ�û��ĸ�����Ϣ���ֱ𸳸���Ӧ�����

		// �����ǻ��ƻ������ش��롪����������������������������������������������������������������������������������
		// ���ô��ڣ�������JFrame���ĸ������Ժ�״̬
		setTitle("����ũ��");// ���ô��ڱ���
		setResizable(false);// ���ô�������Ϊ��������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// ���ô��ڳߴ�
		User user = new RepositoryController().checkUser(userID);
		userName = user.getUserName();
		userMoney = user.getUserMoney();

		userMessage = "���," + userName + "����������Ĳֿ�";

		// ���ñ���ͼƬ
		Icon background = new ImageIcon("img\\RepositoryViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// ��ʼ���������
		JLabel userInfo = new JLabel(userMessage);// �������Ͻǵ���Ϣ��ʾ����
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// �û���ҵ���Ϣ��ʾ����
		JButton toShopButton = new JButton();// �̳ǰ�ť
		JButton toConfigButton = new JButton();// ���ð�ť
		JButton backToFarmButton = new JButton();// ����ũ����ť
		JButton previousPageButton = new JButton();// ��һҳ��ť
		JButton nextPageButton = new JButton();// ��һҳ��ť
		JTextField pageField = new JTextField(String.valueOf(page));// ҳ�������
		JButton jumpPageButton = new JButton();// ҳ����ת��ť

		RepositoryComponentList[] rcl = new RepositoryComponentList[10];// ��ҳ��10�����߼�¼��Ҫ�õ�����ļ���
		for (int i = 0; i < 10; i++) {// ʵ������ҳ�е�ÿ�����߼�¼
			rcl[i] = new RepositoryComponentList();
		}

		// ���ø�����������Ժ�״̬
		userInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		userInfo.setForeground(Color.WHITE);// ����������ɫ
		userInfo.setBounds(24, 12, 310, 30);// ��������Ĵ�С��λ��

		moneyInfo.setFont(new Font("����", 1, 20));// ����������ʽ
		moneyInfo.setForeground(Color.WHITE);// ����������ɫ
		moneyInfo.setBounds(431, 12, 190, 30);// ��������Ĵ�С��λ��

		toShopButton.setBounds(777, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toShopButton.setContentAreaFilled(false);// ����ť͸����
		toShopButton.setBorder(null);// ���ر߿�

		toConfigButton.setBounds(877, 2, 90, 50);// ���ð�ť�Ĵ�С��λ��
		toConfigButton.setContentAreaFilled(false);// ����ť͸����
		toConfigButton.setBorder(null);// ���ر߿�

		backToFarmButton.setBounds(793, 574, 180, 70);// ���ð�ť�Ĵ�С��λ��
		backToFarmButton.setContentAreaFilled(false);// ����ť͸����
		backToFarmButton.setBorder(null);// ���ر߿�

		previousPageButton.setBounds(311, 552, 80, 40);// ���ð�ť�Ĵ�С��λ��
		previousPageButton.setContentAreaFilled(false);// ����ť͸����

		nextPageButton.setBounds(411, 552, 80, 40);// ���ð�ť�Ĵ�С��λ��
		nextPageButton.setContentAreaFilled(false);// ����ť͸����

		pageField.setFont(new Font("����", 1, 20));// ����������ʽ
		pageField.setForeground(Color.WHITE);// ����������ɫ
		pageField.setBounds(506, 562, 66, 22);// ���������Ĵ�С��λ��
		pageField.setBackground(new Color(108, 50, 6));// ���������ı�����ɫ
		pageField.setCaretColor(Color.WHITE);// ���������Ĺ����ɫ
		pageField.setBorder(null);// ���ر߿�

		jumpPageButton.setBounds(611, 552, 80, 40);// ���ð�ť�Ĵ�С��λ��
		jumpPageButton.setContentAreaFilled(false);// ����ť͸����

		Icon buttonOfUse = new ImageIcon("img\\Button\\Button_use.png");// ʹ�ð�ť��ͼ��
		Icon buttonOfSell = new ImageIcon("img\\Button\\Button_sell.png");// ���۰�ť��ͼ��

		System.out.println("��ʾ�ֿ�");
		if (repositorys != null) {
			// �ж������������飬�������
			for (int i = 0; i < 10 && repositorys[i] != null; i++) {// ����ÿ�����߼�¼�����Ժ�״̬���������޸ģ�����������ԣ�
				// ��ʼ������
				rcl[i].setResID(repositorys[i].getResId());
				rcl[i].getItemID().setText("" + repositorys[i].getResId());// ���õ���id��ʼ��������
				rcl[i].getItemName().setText(repositorys[i].getResGoodsName());// ���õ������Ƴ�ʼ��������
				if (repositorys[i].getResType().equals(1)) {
					rcl[i].getItemType().setText("����");// ���õ������ͳ�ʼ��������
				} else if (repositorys[i].getResType().equals(2)) {
					rcl[i].getItemType().setText("����");
				}
				rcl[i].getItemInfo().setText(repositorys[i].getResBrief());// ���õ��߼���ʼ��������
				rcl[i].getItemNumber().setText("" + repositorys[i].getResNumber());// ���õ��߳�������ʼ��������
				rcl[i].getItemPrice().setText("" + repositorys[i].getResPrice());// ���õ��߳��ۼ۸��ʼ��������
				rcl[i].setResGoodsID(repositorys[i].getResGoodsId());
				rcl[i].getUseButton().setIcon(buttonOfUse);// ����ʹ�ð�ť��ͼ��
				rcl[i].getSellButton().setIcon(buttonOfSell);// ���ó��۰�ť��ͼ��
				// ����λ�úʹ�С
				rcl[i].getItemID().setBounds(0, -30, 10, 20);
				rcl[i].getItemName().setBounds(110, 140 + 40 * i, 75, 40);
				rcl[i].getItemType().setBounds(185, 140 + 40 * i, 45, 40);
				rcl[i].getItemInfo().setBounds(240, 140 + 40 * i, 185, 40);
				rcl[i].getItemNumber().setBounds(430, 140 + 40 * i, 70, 40);
				rcl[i].getItemPrice().setBounds(505, 140 + 40 * i, 80, 40);
				rcl[i].getUseButton().setBounds(585, 143 + 40 * i, 52, 32);
				rcl[i].getSellButton().setBounds(645, 143 + 40 * i, 52, 32);
				// ���ð�ť����ʾ
				if (rcl[i].getItemType().getText().equals("����")) {// ���ݵ������;���ʹ�ð�ť�Ƿ���ʾ
					rcl[i].getUseButton().setVisible(false);
				}
			}
		}

			// ��Ӹ������
			contentPane = new JPanel();
			contentPane.add(userInfo);
			contentPane.add(moneyInfo);
			contentPane.add(toShopButton);
			contentPane.add(toConfigButton);
			contentPane.add(backToFarmButton);
			contentPane.add(previousPageButton);
			contentPane.add(nextPageButton);
			contentPane.add(pageField);
			contentPane.add(jumpPageButton);
			for (int i = 0; i < 10; i++) {
				contentPane.add(rcl[i].getItemID());
				contentPane.add(rcl[i].getItemName());
				contentPane.add(rcl[i].getItemType());
				contentPane.add(rcl[i].getItemInfo());
				contentPane.add(rcl[i].getItemNumber());
				contentPane.add(rcl[i].getItemPrice());
				contentPane.add(rcl[i].getUseButton());
				contentPane.add(rcl[i].getSellButton());
			}
			contentPane.add(bgLabel);// ����ټӱ���ͼ
		
		// ���õײ����ĸ������Ժ�״̬
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ��ʾ���洰��
		setContentPane(contentPane);
		setVisible(true);
		
		// ���������Ҫ�����޸�
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
		backToFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				dispose();
			}
		});
		previousPageButton.addActionListener(new RepositoryController(this, previousPageButton, userID));// ��һҳ
		nextPageButton.addActionListener(new RepositoryController(this, userID, nextPageButton));// ��һҳ
		jumpPageButton.addActionListener(new RepositoryController(this, jumpPageButton, pageField, userID));// ��ת��ť�ĵ���¼�
		System.out.println("view��page" + page);
		for (RepositoryComponentList value : rcl) {// �������߼�¼��Ӧ�İ�ť�ĵ���¼�
			value.getUseButton().addActionListener(new RepositoryController(this, userID, value.getUseButton(),
					value.getItemID().getText(), value.getResGoodsID(), value.getItemNumber().getText()));
			value.getSellButton().addActionListener(new RepositoryController(this, userID, value.getItemID().getText(),
					value.getSellButton(), value.getItemNumber().getText(), value.getItemPrice().getText()));
		}
	}

	/**
	 * �����õ���������ʹ��RepositoryView��ʱ��Ҫ��ʵ����
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RepositoryView frame = new RepositoryView(1);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

}
