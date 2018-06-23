package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.ShopComponentList;
import com.echarts.client.bean.twicepack.ShopViewBean;
import com.echarts.client.model.ShopViewModel;
import com.echarts.util.IOperation;

/**
 * 1�����ݲ�ѯseed���е���Ŀ���ɶ�Ӧ��Ŀ����Ʒ��Ϣ 
 * 2������ť��showview�� ��ָ����Ч��
 */
public class ShopViewController implements ActionListener {

	// ��Ա����������������������������������������������������������������������������
	// �̵�ҳ��Ĳ�������-�û�id
	private JFrame shopView;
	private int userID;
	private JLabel userMoneyLabel;

	// �̵�ҳ��Ĳ�������-������ť
	private JButton levelUpButton;

	// �̵�ҳ��Ĳ�������-ҳ���л���ť
	private ShopComponentList[] scl;
	private int flag;// ����/��ҳ��ť�ı�ʶ��ǣ�1������һҳ��ť��2������һҳ��ť
	private JLabel pageInfo;// ����ҳ�õ���ҳ��ֵ������
	private JButton PNPageButton;// ����ҳ�İ�ť
	private JTextField pageField;// ��תҳ��������
	private JButton changePageButton;// ��תҳ��İ�ť

	// �̵�ҳ��Ĳ�������-����ť
	private ShopComponentList aSCL;

	// ���췽��������������������������������������������������������������������������
	// ���췽��-��ҳ��ʼ��
	public ShopViewController(int userID) {
		this.userID = userID;
	}

	// ���췽��-������ť
	public ShopViewController(JFrame shopView, int userID, JButton levelUpButton, JLabel userMoneyLabel) {
		this.shopView = shopView;
		this.userID = userID;
		this.levelUpButton = levelUpButton;
		this.userMoneyLabel = userMoneyLabel;
	}

	// ���췽��-����ҳ�л���ť
	public ShopViewController(JFrame shopView, int userID, int flag, JTextField pageField, JLabel pageInfo,
			JButton PNPageButton, ShopComponentList[] scl) {
		this.shopView = shopView;
		this.userID = userID;
		this.flag = flag;
		this.pageField = pageField;
		this.pageInfo = pageInfo;
		this.PNPageButton = PNPageButton;
		this.scl = scl;
	}

	// ���췽��-��תҳ�л���ť
	public ShopViewController(JFrame shopView, int userID, JTextField pageField, JLabel pageInfo,
			JButton changePageButton, ShopComponentList[] scl) {
		this.shopView = shopView;
		this.userID = userID;
		this.pageField = pageField;
		this.pageInfo = pageInfo;
		this.changePageButton = changePageButton;
		this.scl = scl;
	}

	// ���췽��-����ť
	public ShopViewController(JFrame shopView, int userID, JLabel userMoneyLabel, ShopComponentList aSCL) {
		this.shopView = shopView;
		this.userID = userID;
		this.userMoneyLabel = userMoneyLabel;
		this.aSCL = aSCL;
	}

	/**
	 * ��ʼ����ҵķ���
	 */
	public int getMoney() {
		ShopViewBean svb = new ShopViewBean();
		svb.setUserID(userID);
		svb.setManageFlag("GETMONEY");
		Message message = new Message(svb, IOperation.SHOPVIEW);
		ShopViewBean backMassage = null;
		try {
			// ������ʹ���õ����ݲ����շ��ص����ݡ�������
			ShopViewModel svm = new ShopViewModel();
			backMassage = svm.shopViewMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// �����ѷ��ص����ݡ�������
		int result = backMassage.getAboutMoney();
		return result;
	}

	/**
	 * ��ʼ�������б�ķ���
	 */
	public List<Seed> getSeedList() {
		// ����Ҫ��������ݡ�������
		ShopViewBean svb = new ShopViewBean();
		svb.setUserID(userID);
		svb.setManageFlag("CHANGEPAGE");
		svb.setPage(1);
		Message message = new Message(svb, IOperation.SHOPVIEW);
		ShopViewBean backMassage = null;
		try {
			// ������ʹ���õ����ݲ����շ��ص����ݡ�������
			ShopViewModel svm = new ShopViewModel();
			backMassage = svm.shopViewMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// �����ѷ��ص����ݡ�������
		List<Seed> seedList = backMassage.getSeedList();
		return seedList;
	}

	/**
	 * ���������б�ķ���
	 */
	private void setSeedList(List<Seed> seedList) {
		for (int i = 0; i < seedList.size(); i++) {// ����ÿ�����߼�¼�����Ժ�״̬
			// ��ʼ������
			scl[i].getSeedID().setText("" + seedList.get(i).getSeedId());// ���õ���id������
			scl[i].getSeedName().setText(seedList.get(i).getSeedName());// �����������Ƶ�����
			scl[i].getSeedPrice().setText("" + seedList.get(i).getSeedPrice());// �������ӹ���۸������
			scl[i].getSeedInfo().setText(seedList.get(i).getSeedIntroduction());// ���õ��߼�������
			scl[i].getSeedGetNumber().setText("" + seedList.get(i).getSeedHarvestNumber());// ���������ճ���������
			scl[i].getSeedGetTime().setText("" + seedList.get(i).getSeedHarvestTime() + " ����");// ���������ճ�ʱ�������
			scl[i].getItemNumber().setText("" + seedList.get(i).getSeedNumber());// ���øõ��߳�����������
			scl[i].getBuyButton().setVisible(true);// ��ʾ��ť
		}
		for (int i = seedList.size(); i < 10; i++) {// δ��10���Ĳ���,��ս�����������
			// ��ʼ������
			scl[i].getSeedID().setText("");
			scl[i].getSeedName().setText("");
			scl[i].getSeedPrice().setText("");
			scl[i].getSeedInfo().setText("");
			scl[i].getSeedGetNumber().setText("");
			scl[i].getSeedGetTime().setText("");
			scl[i].getItemNumber().setText("");
			scl[i].getBuyButton().setVisible(false);// ���ذ�ť
		}
	}

	/**
	 * �������ӵķ���
	 */
	private void buySeed(int needNumber, int haveNumber) {
		// �жϽ���Ƿ��㹻��������������������������������
		int aPrice = Integer.parseInt(aSCL.getSeedPrice().getText());// ����
		int haveMoney = Integer.parseInt(userMoneyLabel.getText());// ���еĽ��
		if (needNumber * aPrice <= haveMoney) {// ���㹺��������ʱ
			// ��������������ʾ
			int seedID = Integer.parseInt(aSCL.getSeedID().getText());
			int numberResult = needNumber + haveNumber;
			int moneyResult = haveMoney - needNumber * aPrice;
			aSCL.getItemNumber().setText("" + numberResult);
			userMoneyLabel.setText("" + moneyResult);
			// ������ݷ��͵�������ȥ�������ݿ������
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("BUYSEED");
			svb.setSeedID(seedID);
			svb.setNumberResult(numberResult);
			svb.setMoneyResult(moneyResult);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// ������ʹ���õ����ݲ����շ��ص����ݡ�������
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(shopView, "����ɹ���");
		} else {// ��Ҳ���ʱ
			JOptionPane.showMessageDialog(shopView, "��Ľ�Ҳ��㡣");
		}
	}

	/**
	 * ������ť�ķ���
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// ************************������ť************************
		if (arg0.getSource() == levelUpButton) {
			// ��ʾ����ũ����Ҫ�Ľ�ѯ���û��Ƿ�����
			// ����Ҫ��������ݡ�������
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("LVUP");
			svb.setWantToBuy(false);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// ������ʹ���õ����ݲ����շ��ص����ݡ�������
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// �����ѷ��ص����ݡ�������
			int result = backMassage.getAboutMoney();
			if (result == -1) {// �ȼ��Ѵ�����ʱ
				JOptionPane.showMessageDialog(shopView, "�Ѵ���ߵȼ����޷����������ˡ�");
			} else {// �ȼ�δ������ʱ
				int YorN = JOptionPane.showConfirmDialog(shopView, "��Ҫ" + result + "���", "�Ƿ��������ũ��",
						JOptionPane.YES_NO_OPTION);
				if (YorN == 0) {// ѡ���ǵĳ���
					// ִ�������Ĳ���
					// ����Ҫ��������ݡ�������
					svb.setWantToBuy(true);
					message = new Message(svb, IOperation.SHOPVIEW);
					try {
						// ������ʹ���õ����ݲ����շ��ص����ݡ�������
						ShopViewModel svm = new ShopViewModel();
						backMassage = svm.shopViewMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					// �����ѷ��ص����ݡ�������
					result = backMassage.getAboutMoney();
					if (result == -2) {// ����Ǯ����ĳ���
						JOptionPane.showMessageDialog(shopView, "��û���㹻�Ľ�ҡ�");
					} else {
						userMoneyLabel.setText("" + result);
						JOptionPane.showMessageDialog(shopView, "ũ���ȼ�������������ӵ�и���������ˡ�");
					}
				} // ѡ���ʱʲô��������
			}

			// ************************����ҳ�밴ť************************
		} else if (arg0.getSource() == PNPageButton) {
			int page = Integer.parseInt(pageInfo.getText());
			if (flag == 1) {// ������һҳ��ť�ĳ���
				if (--page == 0) {// �ڵ�һҳ����һҳ��ť�����
					JOptionPane.showMessageDialog(shopView, "�Ѿ��ǵ�һҳ�ˣ�");
				} else {
					pageInfo.setText("" + page);
					pageField.setText("" + page);
					// ����Ҫ��������ݡ�������
					ShopViewBean svb = new ShopViewBean();
					svb.setUserID(userID);
					svb.setManageFlag("CHANGEPAGE");
					svb.setPage(page);
					Message message = new Message(svb, IOperation.SHOPVIEW);
					ShopViewBean backMassage = null;
					try {
						// ������ʹ���õ����ݲ����շ��ص����ݡ�������
						ShopViewModel svm = new ShopViewModel();
						backMassage = svm.shopViewMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					// �����ѷ��ص����ݡ�������
					List<Seed> seedList = backMassage.getSeedList();
					setSeedList(seedList);// �������������б�ķ���
				}
			} else {// ������һҳ��ť�ĳ���
				// ����Ҫ��������ݡ�������
				++page;
				ShopViewBean svb = new ShopViewBean();
				svb.setUserID(userID);
				svb.setManageFlag("CHANGEPAGE");
				svb.setPage(page);
				Message message = new Message(svb, IOperation.SHOPVIEW);
				ShopViewBean backMassage = null;
				try {
					// ������ʹ���õ����ݲ����շ��ص����ݡ�������
					ShopViewModel svm = new ShopViewModel();
					backMassage = svm.shopViewMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// �����ѷ��ص����ݡ�������
				List<Seed> seedList = backMassage.getSeedList();
				if (seedList.isEmpty()) {// ��ʾ�ѵ����һҳ
					JOptionPane.showMessageDialog(shopView, "�Ѿ������һҳ��");
				} else {
					pageInfo.setText("" + page);
					pageField.setText("" + page);
					setSeedList(seedList);// �������������б�ķ���
				}
			}

			// ************************��תҳ�밴ť************************
		} else if (arg0.getSource() == changePageButton) {
			int page = Integer.parseInt(pageInfo.getText());
			try {
				page = Integer.parseInt(pageField.getText());
				if (page < 1) {
					new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(shopView, "ҳ��Ϊ�ջ�Ϊ��������������ȷ��ҳ�룡");
				pageField.setText(pageInfo.getText());// ����ҳ�������Ϊ��ǰҳ
			}
			// ����Ҫ��������ݡ�������
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("CHANGEPAGE");
			svb.setPage(page);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// ������ʹ���õ����ݲ����շ��ص����ݡ�������
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// �����ѷ��ص����ݡ�������
			List<Seed> seedList = backMassage.getSeedList();
			if (seedList.isEmpty()) {// ��ʾ�����ҳ�泬������
				JOptionPane.showMessageDialog(shopView, "ҳ��С��1��������ޣ���������ȷ��ҳ�룡");
				pageField.setText(pageInfo.getText());// ����ҳ�������Ϊ��ǰҳ
			} else {
				pageInfo.setText("" + page);
				setSeedList(seedList);// �������������б�ķ���
			}

			// ************************�������Ӱ�ť************************
		} else if (arg0.getSource() == aSCL.getBuyButton()) {
			try {
				int haveNumber = Integer.parseInt(aSCL.getItemNumber().getText());// ���е�����
				int needNumber = 0;
				if (haveNumber >= 99) {// ������Ѵ�99ʱ
					JOptionPane.showMessageDialog(shopView, "�������Ѵ�99���޷�����");
				} else {
					String input = JOptionPane.showInputDialog(shopView, "������Ҫ���������(���������99��)��");
					if (!(input==null)) {
						needNumber = Integer.parseInt(input);
						// �жϹ��������Ƿ����������������������������������������
						if (needNumber < 1) {// �������С��1ʱ
							throw new NumberFormatException();
						} else if (needNumber + haveNumber > 99) {// Ԥ�ƿ��������99ʱ
							needNumber = 99 - haveNumber;
							int YorN = JOptionPane.showConfirmDialog(shopView,
									"�����������������������99�����ֻ�ܹ���" + needNumber + "����\n�Ƿ��������", "�Ƿ��������",
									JOptionPane.YES_NO_OPTION);
							if (YorN == 0) {// ѡ�ǵĳ��ϼ������й�����
								buySeed(needNumber, haveNumber);
							} // ѡ�������·���
						} else { // Ԥ�ƿ����������99ʱֱ�ӽ��й�����
							buySeed(needNumber, haveNumber);
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(shopView, "�������������С��1��Ϊ�ջ�Ϊ�����֣������²�����");
			} 
		}
	}

}
