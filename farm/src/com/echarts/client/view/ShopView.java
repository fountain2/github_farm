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
 * 商店页面
 */
public class ShopView extends JFrame {

	private JPanel contentPane;

	/**
	 * ShopView的构造方法，实例化时将生成一个商城界面，需用户ID作为参数
	 */
	public ShopView(int userID) {
		// 需通过用户ID获取用户的各项信息，分别赋给对应的组件
		ShopViewController svc = new ShopViewController(userID);
		List<Seed> seedList = svc.getSeedList();

		// 赋给各个组件的变量暂定如下，根据需要自行修改
		int userMoney = svc.getMoney();// 用户金币
		String userMessage = "你好，请问要买些什么？";// 画面左上角的显示信息

		// 以下是绘制画面的相关代码——————————————————————————————————————————
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸

		// 设置背景图片
		Icon background = new ImageIcon("img\\ShopViewBG-0.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件
		JLabel pageInfo = new JLabel("1");// 隐藏的页面信息，记录当前页码信息
		JLabel userInfo = new JLabel(userMessage);// 画面左上角的信息显示区域
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// 用户金币的信息显示区域
		JButton toRepositoryButton = new JButton();// 仓库按钮
		JButton toConfigButton = new JButton();// 设置按钮
		JButton backToFarmButton = new JButton();// 返回农场按钮
		JButton levelUpButton = new JButton();// 农场升级按钮
		JButton previousPageButton = new JButton();// 上一页按钮
		JButton nextPageButton = new JButton();// 下一页按钮
		JTextField pageField = new JTextField("1");// 页码输入框
		JButton jumpPageButton = new JButton();// 页码跳转按钮
		ShopComponentList[] scl = new ShopComponentList[10];// 单页中10条商品记录所要用的组件的集合
		for (int i = 0; i < 10; i++) {// 实例化单页中的每条商品记录
			scl[i] = new ShopComponentList();
		}

		// 设置各项组件的属性和状态
		pageInfo.setBounds(-30, -30, 10, 10);// 设置区域大小和位置

		userInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		userInfo.setForeground(Color.BLACK);// 设置字体颜色
		userInfo.setBounds(20, 25, 310, 30);// 设置区域的大小和位置

		moneyInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		moneyInfo.setForeground(Color.WHITE);// 设置字体颜色
		moneyInfo.setBounds(431, 12, 190, 30);// 设置区域的大小和位置

		toRepositoryButton.setBounds(677, 2, 90, 50);// 设置按钮的大小和位置
		toRepositoryButton.setContentAreaFilled(false);// 将按钮透明化
		toRepositoryButton.setBorder(null);// 隐藏边框

		toConfigButton.setBounds(877, 2, 90, 50);// 设置按钮的大小和位置
		toConfigButton.setContentAreaFilled(false);// 将按钮透明化
		toConfigButton.setBorder(null);// 隐藏边框

		backToFarmButton.setBounds(33, 574, 180, 70);// 设置按钮的大小和位置
		backToFarmButton.setContentAreaFilled(false);// 将按钮透明化
		backToFarmButton.setBorder(null);// 隐藏边框

		levelUpButton.setBounds(387, 547, 160, 40);// 设置按钮的大小和位置
		levelUpButton.setContentAreaFilled(false);// 将按钮透明化

		previousPageButton.setBounds(588, 547, 80, 40);// 设置按钮的大小和位置
		previousPageButton.setContentAreaFilled(false);// 将按钮透明化

		nextPageButton.setBounds(688, 547, 80, 40);// 设置按钮的大小和位置
		nextPageButton.setContentAreaFilled(false);// 将按钮透明化

		pageField.setFont(new Font("宋体", 1, 20));// 设置字体样式
		pageField.setForeground(Color.WHITE);// 设置字体颜色
		pageField.setBounds(784, 558, 66, 22);// 设置输入框的大小和位置
		pageField.setBackground(new Color(108, 50, 6));// 设置输入框的背景颜色
		pageField.setCaretColor(Color.WHITE);// 设置输入框的光标颜色
		pageField.setBorder(null);// 隐藏边框

		jumpPageButton.setBounds(888, 547, 80, 40);// 设置按钮的大小和位置
		jumpPageButton.setContentAreaFilled(false);// 将按钮透明化

		Icon buttonOfBuy = new ImageIcon("img\\Button\\Button_buy.png");// 购买按钮的图像
		for (int i = 0; i < seedList.size(); i++) {// 设置每条道具记录的属性和状态（可自行修改，这里仅作测试）
			// 初始化内容
			scl[i].getSeedID().setText("" + seedList.get(i).getSeedId());// 设置道具id初始化的内容
			scl[i].getSeedName().setText(seedList.get(i).getSeedName());// 设置种子名称初始化的内容
			scl[i].getSeedPrice().setText("" + seedList.get(i).getSeedPrice());// 设置种子购买价格初始化的内容
			scl[i].getSeedInfo().setText(seedList.get(i).getSeedIntroduction());// 设置道具简介初始化的内容
			scl[i].getSeedGetNumber().setText("" + seedList.get(i).getSeedHarvestNumber());// 设置种子收成数初始化的内容
			scl[i].getSeedGetTime().setText("" + seedList.get(i).getSeedHarvestTime() + " 分钟");// 设置种子收成时间初始化的内容
			scl[i].getItemNumber().setText("" + seedList.get(i).getSeedNumber());// 设置该道具持有数初始化的内容
			scl[i].getBuyButton().setIcon(buttonOfBuy);// 设置购买按钮的图像
			// 设置位置和大小
			scl[i].getSeedID().setBounds(0, -30, 10, 20);
			scl[i].getSeedName().setBounds(382, 135 + 40 * i, 80, 40);
			scl[i].getSeedPrice().setBounds(475, 135 + 40 * i, 50, 40);
			scl[i].getSeedInfo().setBounds(525, 135 + 40 * i, 170, 40);
			scl[i].getSeedGetNumber().setBounds(720, 135 + 40 * i, 65, 40);
			scl[i].getSeedGetTime().setBounds(780, 135 + 40 * i, 80, 40);
			scl[i].getItemNumber().setBounds(875, 135 + 40 * i, 60, 40);
			scl[i].getBuyButton().setBounds(922, 138 + 40 * i, 52, 32);
		}

		// 添加各项组件
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
		contentPane.add(bgLabel);// 最后再加背景图

		// 设置底部面板的各项属性和状态
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		// 显示界面窗口
		setContentPane(contentPane);
		setVisible(true);

		// 人物动画的相关方法
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
					userInfo.setText("慢慢看，光看不买……也行。");
					start = true;
				} else if (time == 71) {
					start = false;
				} else if (time == 100) {
					userInfo.setText("今天天气挺不错的，适合种田。");
					start = true;
				}else if (time == 121) {
					start = false;
				} else if (time == 150) {
					userInfo.setText("我卖的的种子不需要浇水施肥。");
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

		// 按钮的监听事件
		// 具体根据需要自行修改
		toRepositoryButton.addActionListener(new ActionListener() {// 仓库按钮的点击事件
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
		toConfigButton.addActionListener(new ActionListener() {// 设置按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigView(userID);
				timer.cancel();
				dispose();
			}
		});
		backToFarmButton.addActionListener(new ActionListener() {// 返回农场按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				timer.cancel();
				dispose();
			}
		});
		levelUpButton.addActionListener(new ShopViewController(this, userID, levelUpButton, moneyInfo));// 农场升级按钮的点击事件
		previousPageButton.addActionListener(
				new ShopViewController(this, userID, 1, pageField, pageInfo, previousPageButton, scl));// 上一页按钮的点击事件
		nextPageButton
				.addActionListener(new ShopViewController(this, userID, 2, pageField, pageInfo, nextPageButton, scl));// 下一页按钮的点击事件
		jumpPageButton
				.addActionListener(new ShopViewController(this, userID, pageField, pageInfo, jumpPageButton, scl));// 跳转按钮的点击事件
		for (ShopComponentList value : scl) {// 各个种子记录对应的按钮的点击事件// 购买按钮的点击事件
			value.getBuyButton().addActionListener(new ShopViewController(this, userID, moneyInfo, value));
		}
	}

}
