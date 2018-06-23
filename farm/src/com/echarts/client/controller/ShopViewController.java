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
 * 1、根据查询seed表中的条目生成对应数目的商品信息 
 * 2、购买按钮（showview） 有指定的效果
 */
public class ShopViewController implements ActionListener {

	// 成员变量————————————————————————————————————
	// 商店页面的操作对象-用户id
	private JFrame shopView;
	private int userID;
	private JLabel userMoneyLabel;

	// 商店页面的操作对象-升级按钮
	private JButton levelUpButton;

	// 商店页面的操作对象-页面切换按钮
	private ShopComponentList[] scl;
	private int flag;// 按上/下页按钮的辨识标记，1代表上一页按钮，2代表下一页按钮
	private JLabel pageInfo;// 上下页用到的页码值存放组件
	private JButton PNPageButton;// 上下页的按钮
	private JTextField pageField;// 跳转页码的输入框
	private JButton changePageButton;// 跳转页码的按钮

	// 商店页面的操作对象-购买按钮
	private ShopComponentList aSCL;

	// 构造方法————————————————————————————————————
	// 构造方法-首页初始化
	public ShopViewController(int userID) {
		this.userID = userID;
	}

	// 构造方法-升级按钮
	public ShopViewController(JFrame shopView, int userID, JButton levelUpButton, JLabel userMoneyLabel) {
		this.shopView = shopView;
		this.userID = userID;
		this.levelUpButton = levelUpButton;
		this.userMoneyLabel = userMoneyLabel;
	}

	// 构造方法-上下页切换按钮
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

	// 构造方法-跳转页切换按钮
	public ShopViewController(JFrame shopView, int userID, JTextField pageField, JLabel pageInfo,
			JButton changePageButton, ShopComponentList[] scl) {
		this.shopView = shopView;
		this.userID = userID;
		this.pageField = pageField;
		this.pageInfo = pageInfo;
		this.changePageButton = changePageButton;
		this.scl = scl;
	}

	// 构造方法-购买按钮
	public ShopViewController(JFrame shopView, int userID, JLabel userMoneyLabel, ShopComponentList aSCL) {
		this.shopView = shopView;
		this.userID = userID;
		this.userMoneyLabel = userMoneyLabel;
		this.aSCL = aSCL;
	}

	/**
	 * 初始化金币的方法
	 */
	public int getMoney() {
		ShopViewBean svb = new ShopViewBean();
		svb.setUserID(userID);
		svb.setManageFlag("GETMONEY");
		Message message = new Message(svb, IOperation.SHOPVIEW);
		ShopViewBean backMassage = null;
		try {
			// 打包发送处理好的内容并接收返回的内容————
			ShopViewModel svm = new ShopViewModel();
			backMassage = svm.shopViewMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 处理已发回的内容————
		int result = backMassage.getAboutMoney();
		return result;
	}

	/**
	 * 初始化种子列表的方法
	 */
	public List<Seed> getSeedList() {
		// 处理要打包的内容————
		ShopViewBean svb = new ShopViewBean();
		svb.setUserID(userID);
		svb.setManageFlag("CHANGEPAGE");
		svb.setPage(1);
		Message message = new Message(svb, IOperation.SHOPVIEW);
		ShopViewBean backMassage = null;
		try {
			// 打包发送处理好的内容并接收返回的内容————
			ShopViewModel svm = new ShopViewModel();
			backMassage = svm.shopViewMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 处理已发回的内容————
		List<Seed> seedList = backMassage.getSeedList();
		return seedList;
	}

	/**
	 * 设置种子列表的方法
	 */
	private void setSeedList(List<Seed> seedList) {
		for (int i = 0; i < seedList.size(); i++) {// 设置每条道具记录的属性和状态
			// 初始化内容
			scl[i].getSeedID().setText("" + seedList.get(i).getSeedId());// 设置道具id的内容
			scl[i].getSeedName().setText(seedList.get(i).getSeedName());// 设置种子名称的内容
			scl[i].getSeedPrice().setText("" + seedList.get(i).getSeedPrice());// 设置种子购买价格的内容
			scl[i].getSeedInfo().setText(seedList.get(i).getSeedIntroduction());// 设置道具简介的内容
			scl[i].getSeedGetNumber().setText("" + seedList.get(i).getSeedHarvestNumber());// 设置种子收成数的内容
			scl[i].getSeedGetTime().setText("" + seedList.get(i).getSeedHarvestTime() + " 分钟");// 设置种子收成时间的内容
			scl[i].getItemNumber().setText("" + seedList.get(i).getSeedNumber());// 设置该道具持有数的内容
			scl[i].getBuyButton().setVisible(true);// 显示按钮
		}
		for (int i = seedList.size(); i < 10; i++) {// 未满10条的部分,清空界面的相关数据
			// 初始化内容
			scl[i].getSeedID().setText("");
			scl[i].getSeedName().setText("");
			scl[i].getSeedPrice().setText("");
			scl[i].getSeedInfo().setText("");
			scl[i].getSeedGetNumber().setText("");
			scl[i].getSeedGetTime().setText("");
			scl[i].getItemNumber().setText("");
			scl[i].getBuyButton().setVisible(false);// 隐藏按钮
		}
	}

	/**
	 * 购买种子的方法
	 */
	private void buySeed(int needNumber, int haveNumber) {
		// 判断金币是否足够————————————————
		int aPrice = Integer.parseInt(aSCL.getSeedPrice().getText());// 单价
		int haveMoney = Integer.parseInt(userMoneyLabel.getText());// 已有的金币
		if (needNumber * aPrice <= haveMoney) {// 满足购买所需金币时
			// 处理界面的数据显示
			int seedID = Integer.parseInt(aSCL.getSeedID().getText());
			int numberResult = needNumber + haveNumber;
			int moneyResult = haveMoney - needNumber * aPrice;
			aSCL.getItemNumber().setText("" + numberResult);
			userMoneyLabel.setText("" + moneyResult);
			// 打包数据发送到服务器去处理数据库的数据
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("BUYSEED");
			svb.setSeedID(seedID);
			svb.setNumberResult(numberResult);
			svb.setMoneyResult(moneyResult);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// 打包发送处理好的内容并接收返回的内容————
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(shopView, "购买成功！");
		} else {// 金币不足时
			JOptionPane.showMessageDialog(shopView, "你的金币不足。");
		}
	}

	/**
	 * 各个按钮的方法
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// ************************升级按钮************************
		if (arg0.getSource() == levelUpButton) {
			// 显示升级农场需要的金额并询问用户是否升级
			// 处理要打包的内容————
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("LVUP");
			svb.setWantToBuy(false);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// 打包发送处理好的内容并接收返回的内容————
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// 处理已发回的内容————
			int result = backMassage.getAboutMoney();
			if (result == -1) {// 等级已达上限时
				JOptionPane.showMessageDialog(shopView, "已达最高等级，无法继续升级了。");
			} else {// 等级未达上限时
				int YorN = JOptionPane.showConfirmDialog(shopView, "需要" + result + "金币", "是否升级你的农场",
						JOptionPane.YES_NO_OPTION);
				if (YorN == 0) {// 选择是的场合
					// 执行升级的操作
					// 处理要打包的内容————
					svb.setWantToBuy(true);
					message = new Message(svb, IOperation.SHOPVIEW);
					try {
						// 打包发送处理好的内容并接收返回的内容————
						ShopViewModel svm = new ShopViewModel();
						backMassage = svm.shopViewMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					// 处理已发回的内容————
					result = backMassage.getAboutMoney();
					if (result == -2) {// 不够钱购买的场合
						JOptionPane.showMessageDialog(shopView, "你没有足够的金币。");
					} else {
						userMoneyLabel.setText("" + result);
						JOptionPane.showMessageDialog(shopView, "农场等级提升，你现在拥有更多的土地了。");
					}
				} // 选择否时什么都不操作
			}

			// ************************上下页码按钮************************
		} else if (arg0.getSource() == PNPageButton) {
			int page = Integer.parseInt(pageInfo.getText());
			if (flag == 1) {// 按下上一页按钮的场合
				if (--page == 0) {// 在第一页按上一页按钮的情况
					JOptionPane.showMessageDialog(shopView, "已经是第一页了！");
				} else {
					pageInfo.setText("" + page);
					pageField.setText("" + page);
					// 处理要打包的内容————
					ShopViewBean svb = new ShopViewBean();
					svb.setUserID(userID);
					svb.setManageFlag("CHANGEPAGE");
					svb.setPage(page);
					Message message = new Message(svb, IOperation.SHOPVIEW);
					ShopViewBean backMassage = null;
					try {
						// 打包发送处理好的内容并接收返回的内容————
						ShopViewModel svm = new ShopViewModel();
						backMassage = svm.shopViewMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					// 处理已发回的内容————
					List<Seed> seedList = backMassage.getSeedList();
					setSeedList(seedList);// 调用设置种子列表的方法
				}
			} else {// 按下下一页按钮的场合
				// 处理要打包的内容————
				++page;
				ShopViewBean svb = new ShopViewBean();
				svb.setUserID(userID);
				svb.setManageFlag("CHANGEPAGE");
				svb.setPage(page);
				Message message = new Message(svb, IOperation.SHOPVIEW);
				ShopViewBean backMassage = null;
				try {
					// 打包发送处理好的内容并接收返回的内容————
					ShopViewModel svm = new ShopViewModel();
					backMassage = svm.shopViewMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// 处理已发回的内容————
				List<Seed> seedList = backMassage.getSeedList();
				if (seedList.isEmpty()) {// 表示已到最后一页
					JOptionPane.showMessageDialog(shopView, "已经是最后一页！");
				} else {
					pageInfo.setText("" + page);
					pageField.setText("" + page);
					setSeedList(seedList);// 调用设置种子列表的方法
				}
			}

			// ************************跳转页码按钮************************
		} else if (arg0.getSource() == changePageButton) {
			int page = Integer.parseInt(pageInfo.getText());
			try {
				page = Integer.parseInt(pageField.getText());
				if (page < 1) {
					new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(shopView, "页码为空或不为整数，请输入正确的页码！");
				pageField.setText(pageInfo.getText());// 重置页码输入框为当前页
			}
			// 处理要打包的内容————
			ShopViewBean svb = new ShopViewBean();
			svb.setUserID(userID);
			svb.setManageFlag("CHANGEPAGE");
			svb.setPage(page);
			Message message = new Message(svb, IOperation.SHOPVIEW);
			ShopViewBean backMassage = null;
			try {
				// 打包发送处理好的内容并接收返回的内容————
				ShopViewModel svm = new ShopViewModel();
				backMassage = svm.shopViewMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// 处理已发回的内容————
			List<Seed> seedList = backMassage.getSeedList();
			if (seedList.isEmpty()) {// 表示输入的页面超过上限
				JOptionPane.showMessageDialog(shopView, "页码小于1或大于上限，请输入正确的页码！");
				pageField.setText(pageInfo.getText());// 重置页码输入框为当前页
			} else {
				pageInfo.setText("" + page);
				setSeedList(seedList);// 调用设置种子列表的方法
			}

			// ************************购买种子按钮************************
		} else if (arg0.getSource() == aSCL.getBuyButton()) {
			try {
				int haveNumber = Integer.parseInt(aSCL.getItemNumber().getText());// 已有的数量
				int needNumber = 0;
				if (haveNumber >= 99) {// 库存量已达99时
					JOptionPane.showMessageDialog(shopView, "持有数已达99，无法购买！");
				} else {
					String input = JOptionPane.showInputDialog(shopView, "请输入要购买的数量(持有数最多99个)：");
					if (!(input==null)) {
						needNumber = Integer.parseInt(input);
						// 判断购买数量是否符合条件————————————————
						if (needNumber < 1) {// 输入的数小于1时
							throw new NumberFormatException();
						} else if (needNumber + haveNumber > 99) {// 预计库存数超过99时
							needNumber = 99 - haveNumber;
							int YorN = JOptionPane.showConfirmDialog(shopView,
									"按此数量购买持有数将超过99，最多只能购买" + needNumber + "个。\n是否继续购买？", "是否继续购买？",
									JOptionPane.YES_NO_OPTION);
							if (YorN == 0) {// 选是的场合继续进行购买步骤
								buySeed(needNumber, haveNumber);
							} // 选否则无事发生
						} else { // 预计库存量不超过99时直接进行购买步骤
							buySeed(needNumber, haveNumber);
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(shopView, "输入的数量不可小于1，为空或为非数字，请重新操作！");
			} 
		}
	}

}
