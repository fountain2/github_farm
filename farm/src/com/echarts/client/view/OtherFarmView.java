package com.echarts.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
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

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.User;
import com.echarts.client.controller.FarmViewController;
import com.echarts.client.model.FarmModel;

/**
 * 
 * ũ��ҳ��
 */
public class OtherFarmView extends JFrame {

	private JPanel contentPane;
	private Timer timer;
	private Timer timerPlants;

	public OtherFarmView(int userID, int otherUserID) {
		try {
			timerOtherFarmView(userID, otherUserID);
			timer = new Timer();
			TimerTask timeTask = new TimerTask() {
				@Override
				public void run() {
					try {
						timerPlants.cancel();
						timerOtherFarmView(userID, otherUserID);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			timer.schedule(timeTask, 30000L, 30000L);
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * FarmView�Ĺ��췽����ʵ����ʱ������һ����������ң�ũ�����棬���û�ID����һ���û���ID��Ϊ������
	 * 
	 * @return
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public void timerOtherFarmView(int userID, int otherUserID)
			throws ClassNotFoundException, UnknownHostException, IOException {
		// ��ͨ���û�ID��ȡ�û��ĸ�����Ϣ���ֱ𸳸���Ӧ�����
		User user = new FarmModel().initFarmData(otherUserID);

		// ������������ı����ݶ����£�������Ҫ�����޸�
		int otherFarmLV = user.getUserLv();
		String otherUserName = user.getUserName();// ������ҵ��û���
		String otherUserMessage = "������ " + otherUserName + " ��ũ��";// �������Ͻǵ���ʾ��Ϣ
		int[] plantNumber = new int[15];// ʮ������ص�ֲ��ı�ţ��û�����������������������鼴��ʹ��
										// ��ֲ��δ�������������Ϊ-1��û����ֲ������Ϊ0��Ĭ��Ϊ0

		List<Field> fieldList = new FarmModel().queryField(otherUserID);
		List<Seed> seedList = new ArrayList<>();
		int z = 0;
		Seed seed;
		for (Field field : fieldList) {
			seed = new FarmModel().querySeed(field);
			seedList.add(seed);

			// ��ǰʱ��
			long ntime = new Date().getTime();
			long htime = field.getFieldPlantingTime().getTime() + seed.getSeedHarvestTime() * 60 * 1000;
			// �жϵ�ǰʱ���Ƿ񳬹����ߵ��ڳ����ʱ��
			if (ntime >= htime) {
				if (field.getFieldStatus() != 0) {
					plantNumber[z] = seed.getCropId();
					// �޸�״̬����
					new FarmModel().updateFieldStatus(field, 1);
				}
			} else {
				if (field.getFieldStatus() == -1) {
					plantNumber[z] = -1;
				}
			}
			z++;
		}

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
				area[i].setBounds(612, 154 + (i - 12) * 116, 100, 100);
			}
		case 5:
			area[10].setBounds(372, 386, 100, 100);
			area[11].setBounds(492, 386, 100, 100);
		case 4:
			area[8].setBounds(492, 154, 100, 100);
			area[9].setBounds(492, 270, 100, 100);
		case 3:
			area[6].setBounds(372, 154, 100, 100);
			area[7].setBounds(372, 270, 100, 100);
		case 2:
			area[4].setBounds(132, 386, 100, 100);
			area[5].setBounds(252, 386, 100, 100);
		case 1:
			area[0].setBounds(132, 154, 100, 100);
			area[1].setBounds(252, 154, 100, 100);
			area[2].setBounds(132, 270, 100, 100);
			area[3].setBounds(252, 270, 100, 100);
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
		
		timerPlants = new Timer();
		TimerTask task = new TimerTask() {
			int test = 1;
			public void run() {
				for (int i = 0; i < 15; i++) {
					area[i].setIcon(new ImageIcon("img\\Plants\\Plants_" + plantNumber[i] + "_"+test+".png"));
				}
				test++;
				if(test>4)
					test=1;
			}
		};
		timerPlants.schedule(task, 200L, 100L);

		// ��ť�ļ����¼���ע����ť�ĵ���¼��ľ��巽��Ӧд��controller�㣬������������ã�
		// ���������Ҫ�����޸�
		backToFarmButton.addActionListener(new ActionListener() {// ����ũ����ť�ĵ���¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				timer.cancel();
				timerPlants.cancel();
				dispose();
			}
		});

		// ���ذ�ť����
		for (int i = 0; i < 15; i++) {
			final int number = i;
			area[i].addActionListener(
					new FarmViewController(OtherFarmView.this, number, plantNumber, fieldList, seedList, userID));
		}
	}

	/**
	 * �����õ���������ʹ��OtherFarmView��ʱ��Ҫ��ʵ����
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// OtherFarmView frame = new OtherFarmView(3,4);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
}
