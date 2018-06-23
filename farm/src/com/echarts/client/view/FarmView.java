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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.User;
import com.echarts.client.controller.FarmViewController;
import com.echarts.client.model.FarmModel;

/**
 * 
 * 农场页面
 */
public class FarmView extends JFrame {

	private JPanel contentPane;
	private Timer timer;
	private Timer timerPlants;
	
	public FarmView(int userID){
		try {
			//先初始化一次
			timerFarmView(userID);
			//设置定时刷新
			timer = new Timer();
			TimerTask timeTask = new TimerTask() {
				@Override
				public void run() {
					try {
						timerPlants.cancel();
						timerFarmView(userID);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
			};
			timer.schedule(timeTask, 30000L, 30000L);
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 实例化时将生成一个农场界面，需用户ID作为参数
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public void timerFarmView(int userID) throws ClassNotFoundException, UnknownHostException, IOException {
		// 需通过用户ID获取用户的各项信息，分别赋给对应的组件
		User user = new FarmModel().initFarmData(userID);

		// 赋给各个组件的变量暂定如下，根据需要自行修改
		int farmLV = user.getUserLv();// 农场等级
		int userMoney = user.getUserMoney();// 用户金币
		String userName = user.getUserName();// 用户名
		String userMessage = "你好," + userName + "，欢迎回到农场(LV" + farmLV + ")";// 画面左上角的显示信息
		int[] plantNumber = new int[15];// 十五个土地的植物的标号，用户的土地情况遍历完存入该数组即可使用
										// 若植物未生长完成则设置为-1，没有种植则设置为0，默认为0
		List<Field> fieldList = new FarmModel().queryField(userID);
		List<Seed> seedList = new ArrayList<>();
		int z =0;
		Seed seed;
		for (Field field : fieldList) {
			seed = new FarmModel().querySeed(field);
			seedList.add(seed);
			
			//当前时间
			long ntime = new Date().getTime();
			long htime = field.getFieldPlantingTime().getTime()+seed.getSeedHarvestTime()*60*1000;
			//判断当前时间是否超过或者等于成熟的时间
			if (ntime >= htime) {
				if (field.getFieldStatus()!=0) {
					plantNumber[z] = seed.getCropId();
					//修改状态代码
					new FarmModel().updateFieldStatus(field,1);
				}
			}else{
				if (field.getFieldStatus()== -1) {
					plantNumber[z] = -1;
				}
			}
			z++;
		}
		

		// 以下是绘制画面的相关代码——————————————————————————————————————————
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸

		// 设置背景图片
		Icon background = new ImageIcon("img\\FarmViewBG_lv" + farmLV + ".png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件
		JLabel farmInfo = new JLabel(userMessage);// 画面左上角的信息显示区域
		JLabel moneyInfo = new JLabel(String.valueOf(userMoney));// 用户金币的信息显示区域
		JButton toRepositoryButton = new JButton();// 仓库按钮
		JButton toShopButton = new JButton();// 商城按钮
		JButton toConfigButton = new JButton();// 设置按钮
		JButton toOtherFarmButton = new JButton();// 访问农场按钮

		// 设置各项组件的属性和状态
		farmInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		farmInfo.setForeground(Color.WHITE);// 设置字体颜色
		farmInfo.setBounds(24, 12, 310, 30);// 设置区域的大小和位置

		moneyInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		moneyInfo.setForeground(Color.WHITE);// 设置字体颜色
		moneyInfo.setBounds(431, 12, 190, 30);// 设置区域的大小和位置

		toRepositoryButton.setBounds(677, 2, 90, 50);// 设置按钮的大小和位置
		toRepositoryButton.setContentAreaFilled(false);// 将按钮透明化
		toRepositoryButton.setBorder(null);// 隐藏边框

		toShopButton.setBounds(777, 2, 90, 50);// 设置按钮的大小和位置
		toShopButton.setContentAreaFilled(false);// 将按钮透明化
		toShopButton.setBorder(null);// 隐藏边框

		toConfigButton.setBounds(877, 2, 90, 50);// 设置按钮的大小和位置
		toConfigButton.setContentAreaFilled(false);// 将按钮透明化
		toConfigButton.setBorder(null);// 隐藏边框

		toOtherFarmButton.setBounds(793, 574, 180, 70);// 设置按钮的大小和位置
		toOtherFarmButton.setContentAreaFilled(false);// 将按钮透明化
		toOtherFarmButton.setBorder(null);// 隐藏边框

		// 添加各项组件
		contentPane = new JPanel();
		contentPane.add(farmInfo);
		contentPane.add(moneyInfo);
		contentPane.add(toRepositoryButton);
		contentPane.add(toShopButton);
		contentPane.add(toConfigButton);
		contentPane.add(toOtherFarmButton);

		// ——————特殊start-土地按钮&植物显示-根据农场等级添加对应数量的土地按钮——————
		// 初始化
		JButton[] area = new JButton[15];// 土地按钮
		Icon[] plant = new ImageIcon[15];// 植物图标
		for (int i = 0; i < 15; i++) {
			plant[i] = new ImageIcon("img\\Plants\\Plants_" + plantNumber[i] + "_"+1+"_test.png");// 根据各土地的植物标号
			area[i] = new JButton(plant[i]);// 把植物图标给土地按钮
		}
		
		
		// 属性设置
		switch (farmLV) {
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
			// 组件添加
			contentPane.add(area[i]);
		}
		// ——————特殊end-土地按钮&植物显示-根据农场等级添加对应数量的土地按钮——————
		contentPane.add(bgLabel);// 最后再加背景图

		// 设置底部面板的各项属性和状态
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// 显示界面窗口
		setContentPane(contentPane);
		setVisible(true);
		
		
		// 动画测试
		timerPlants= new Timer();
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

		// 按钮的监听事件（注：按钮的点击事件的具体方法应写在controller层，这里仅作测试用）
		// 具体根据需要自行修改
		toRepositoryButton.addActionListener(new ActionListener() {// 仓库按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new RepositoryView(userID);
					timer.cancel();
					timerPlants.cancel();
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		toShopButton.addActionListener(new ActionListener() {// 商城按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShopView(userID);
				timer.cancel();
				timerPlants.cancel();
				dispose();
			}
		});
		toConfigButton.addActionListener(new ActionListener() {// 设置按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigView(userID);
				timer.cancel();
				timerPlants.cancel();
				dispose();
			}
		});
		toOtherFarmButton.addActionListener(new ActionListener() {// 访问农场按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<User> userlist = new FarmModel().queryUserList();
					List<Integer> userIdList = new ArrayList<>();
					for (User user : userlist) {
						userIdList.add(user.getUserId());
					}
					Random random = new Random();
					while(true) {
						int rannum =random.nextInt(userIdList.size());
						int toUserId = userIdList.get(rannum);
						if (toUserId != userID) {
							new OtherFarmView(userID, toUserId);
							timer.cancel();
							timerPlants.cancel();
							break;
						}
					}
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
				}
			}
		});
		
		//土地按钮监听
		for (int i = 0; i < 15; i++) {
			final int number = i;
			area[i].addActionListener(new FarmViewController(FarmView.this,number,plantNumber,fieldList,seedList,area[i]));
		}
		
	}

	/**
	 * 测试用的主方法，使用FarmView类时需要先实例化
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FarmView frame = new FarmView(3);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
