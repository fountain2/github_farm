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
 * 仓库页面
 */
public class RepositoryView extends JFrame {
	// 赋给各个组件的变量暂定如下，根据需要自行修改
	int userMoney = 0;// 用户金币
	String userName = "";// 用户名
	String userMessage = "";// 画面左上角的显示信息
	public int page = 1;// 仓库的页码
	public Repository[] repositorys;// 仓库表
	public User user;// 用户表，获取用户名

	private JPanel contentPane;

	/**
	 * RepositoryView的构造方法
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws UnknownHostException
	 */
	public RepositoryView(int userID) throws UnknownHostException, ClassNotFoundException, IOException {
		// 传数据，根据表内容显示。
		RepositoryController rc = new RepositoryController();
		repositorys = rc.checkRepository(userID, 1);
		initUI(userID);
	}

	/**
	 * 生成一个仓库界面，需用户ID作为参数
	 * 
	 * @param userID
	 *            传入仓库的拥有者ID，并依据这个ID显示仓库。
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws UnknownHostException
	 */
	public void initUI(int userID) throws UnknownHostException, ClassNotFoundException, IOException {
		// 需通过用户ID获取用户的各项信息，分别赋给对应的组件

		// 以下是绘制画面的相关代码——————————————————————————————————————————
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸
		User user = new RepositoryController().checkUser(userID);
		userName = user.getUserName();
		userMoney = user.getUserMoney();

		userMessage = "你好," + userName + "，这里是你的仓库";

		// 设置背景图片
		Icon background = new ImageIcon("img\\RepositoryViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件
		JLabel userInfo = new JLabel(userMessage);// 画面左上角的信息显示区域
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// 用户金币的信息显示区域
		JButton toShopButton = new JButton();// 商城按钮
		JButton toConfigButton = new JButton();// 设置按钮
		JButton backToFarmButton = new JButton();// 返回农场按钮
		JButton previousPageButton = new JButton();// 上一页按钮
		JButton nextPageButton = new JButton();// 下一页按钮
		JTextField pageField = new JTextField(String.valueOf(page));// 页码输入框
		JButton jumpPageButton = new JButton();// 页码跳转按钮

		RepositoryComponentList[] rcl = new RepositoryComponentList[10];// 单页中10条道具记录所要用的组件的集合
		for (int i = 0; i < 10; i++) {// 实例化单页中的每条道具记录
			rcl[i] = new RepositoryComponentList();
		}

		// 设置各项组件的属性和状态
		userInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		userInfo.setForeground(Color.WHITE);// 设置字体颜色
		userInfo.setBounds(24, 12, 310, 30);// 设置区域的大小和位置

		moneyInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		moneyInfo.setForeground(Color.WHITE);// 设置字体颜色
		moneyInfo.setBounds(431, 12, 190, 30);// 设置区域的大小和位置

		toShopButton.setBounds(777, 2, 90, 50);// 设置按钮的大小和位置
		toShopButton.setContentAreaFilled(false);// 将按钮透明化
		toShopButton.setBorder(null);// 隐藏边框

		toConfigButton.setBounds(877, 2, 90, 50);// 设置按钮的大小和位置
		toConfigButton.setContentAreaFilled(false);// 将按钮透明化
		toConfigButton.setBorder(null);// 隐藏边框

		backToFarmButton.setBounds(793, 574, 180, 70);// 设置按钮的大小和位置
		backToFarmButton.setContentAreaFilled(false);// 将按钮透明化
		backToFarmButton.setBorder(null);// 隐藏边框

		previousPageButton.setBounds(311, 552, 80, 40);// 设置按钮的大小和位置
		previousPageButton.setContentAreaFilled(false);// 将按钮透明化

		nextPageButton.setBounds(411, 552, 80, 40);// 设置按钮的大小和位置
		nextPageButton.setContentAreaFilled(false);// 将按钮透明化

		pageField.setFont(new Font("宋体", 1, 20));// 设置字体样式
		pageField.setForeground(Color.WHITE);// 设置字体颜色
		pageField.setBounds(506, 562, 66, 22);// 设置输入框的大小和位置
		pageField.setBackground(new Color(108, 50, 6));// 设置输入框的背景颜色
		pageField.setCaretColor(Color.WHITE);// 设置输入框的光标颜色
		pageField.setBorder(null);// 隐藏边框

		jumpPageButton.setBounds(611, 552, 80, 40);// 设置按钮的大小和位置
		jumpPageButton.setContentAreaFilled(false);// 将按钮透明化

		Icon buttonOfUse = new ImageIcon("img\\Button\\Button_use.png");// 使用按钮的图像
		Icon buttonOfSell = new ImageIcon("img\\Button\\Button_sell.png");// 出售按钮的图像

		System.out.println("显示仓库");
		if (repositorys != null) {
			// 有多少条，存数组，按数组的
			for (int i = 0; i < 10 && repositorys[i] != null; i++) {// 设置每条道具记录的属性和状态（可自行修改，这里仅作测试）
				// 初始化内容
				rcl[i].setResID(repositorys[i].getResId());
				rcl[i].getItemID().setText("" + repositorys[i].getResId());// 设置道具id初始化的内容
				rcl[i].getItemName().setText(repositorys[i].getResGoodsName());// 设置道具名称初始化的内容
				if (repositorys[i].getResType().equals(1)) {
					rcl[i].getItemType().setText("谷物");// 设置道具类型初始化的内容
				} else if (repositorys[i].getResType().equals(2)) {
					rcl[i].getItemType().setText("种子");
				}
				rcl[i].getItemInfo().setText(repositorys[i].getResBrief());// 设置道具简介初始化的内容
				rcl[i].getItemNumber().setText("" + repositorys[i].getResNumber());// 设置道具持有数初始化的内容
				rcl[i].getItemPrice().setText("" + repositorys[i].getResPrice());// 设置道具出售价格初始化的内容
				rcl[i].setResGoodsID(repositorys[i].getResGoodsId());
				rcl[i].getUseButton().setIcon(buttonOfUse);// 设置使用按钮的图像
				rcl[i].getSellButton().setIcon(buttonOfSell);// 设置出售按钮的图像
				// 设置位置和大小
				rcl[i].getItemID().setBounds(0, -30, 10, 20);
				rcl[i].getItemName().setBounds(110, 140 + 40 * i, 75, 40);
				rcl[i].getItemType().setBounds(185, 140 + 40 * i, 45, 40);
				rcl[i].getItemInfo().setBounds(240, 140 + 40 * i, 185, 40);
				rcl[i].getItemNumber().setBounds(430, 140 + 40 * i, 70, 40);
				rcl[i].getItemPrice().setBounds(505, 140 + 40 * i, 80, 40);
				rcl[i].getUseButton().setBounds(585, 143 + 40 * i, 52, 32);
				rcl[i].getSellButton().setBounds(645, 143 + 40 * i, 52, 32);
				// 设置按钮的显示
				if (rcl[i].getItemType().getText().equals("谷物")) {// 根据道具类型决定使用按钮是否显示
					rcl[i].getUseButton().setVisible(false);
				}
			}
		}

			// 添加各项组件
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
			contentPane.add(bgLabel);// 最后再加背景图
		
		// 设置底部面板的各项属性和状态
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// 显示界面窗口
		setContentPane(contentPane);
		setVisible(true);
		
		// 具体根据需要自行修改
		toShopButton.addActionListener(new ActionListener() {// 商城按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShopView(userID);
				dispose();
			}
		});
		toConfigButton.addActionListener(new ActionListener() {// 设置按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigView(userID);
				dispose();
			}
		});
		backToFarmButton.addActionListener(new ActionListener() {// 返回农场按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				dispose();
			}
		});
		previousPageButton.addActionListener(new RepositoryController(this, previousPageButton, userID));// 上一页
		nextPageButton.addActionListener(new RepositoryController(this, userID, nextPageButton));// 下一页
		jumpPageButton.addActionListener(new RepositoryController(this, jumpPageButton, pageField, userID));// 跳转按钮的点击事件
		System.out.println("view的page" + page);
		for (RepositoryComponentList value : rcl) {// 各个道具记录对应的按钮的点击事件
			value.getUseButton().addActionListener(new RepositoryController(this, userID, value.getUseButton(),
					value.getItemID().getText(), value.getResGoodsID(), value.getItemNumber().getText()));
			value.getSellButton().addActionListener(new RepositoryController(this, userID, value.getItemID().getText(),
					value.getSellButton(), value.getItemNumber().getText(), value.getItemPrice().getText()));
		}
	}

	/**
	 * 测试用的主方法，使用RepositoryView类时需要先实例化
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
