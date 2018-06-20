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
 * 农场页面
 */
public class OtherFarmView extends JFrame {

	private JPanel contentPane;

	/**
	 * FarmView的构造方法，实例化时将生成一个其他（玩家）农场界面，需用户ID和另一个用户的ID作为参数，
	 */
	public OtherFarmView(int userID,int otherUserID) {
		// 需通过用户ID获取用户的各项信息，分别赋给对应的组件

		// 赋给各个组件的变量暂定如下，根据需要自行修改
		int otherFarmLV = 1;
		String otherUserName = "ttt";// 其他玩家的用户名
		String otherUserMessage = "这里是 " + otherUserName + " 的农场";// 画面左上角的显示信息
		int[] plantNumber = new int[15];// 十五个土地的植物的标号，用户的土地情况遍历完存入该数组即可使用
										// 若植物未生长完成则设置为-1，没有种植则设置为0，默认为0

		// 以下是绘制画面的相关代码——————————————————————————————————————————
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸

		// 设置背景图片
		Icon background = new ImageIcon("img\\OtherFarmViewBG_lv" + otherFarmLV + ".png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件
		JLabel otherFarmInfo = new JLabel(otherUserMessage);// 画面左上角的信息显示区域
		JButton backToFarmButton = new JButton();// 返回农场按钮

		// 设置各项组件的属性和状态
		otherFarmInfo.setFont(new Font("宋体", 1, 20));// 设置字体样式
		otherFarmInfo.setForeground(Color.WHITE);// 设置字体颜色
		otherFarmInfo.setBounds(24, 12, 310, 30);// 设置区域的大小和位置

		backToFarmButton.setBounds(793, 574, 180, 70);// 设置按钮的大小和位置
		backToFarmButton.setContentAreaFilled(false);// 将按钮透明化
		backToFarmButton.setBorder(null);// 隐藏边框

		// 添加各项组件
		contentPane = new JPanel();
		contentPane.add(otherFarmInfo);
		contentPane.add(backToFarmButton);

		// ——————特殊start-土地按钮&植物显示-根据农场等级添加对应数量的土地按钮——————
		// 初始化
		JButton[] area = new JButton[15];// 土地按钮
		Icon[] plant = new ImageIcon[15];// 植物图标
		for (int i = 0; i < 15; i++) {
			plant[i] = new ImageIcon("img\\Plants\\Plants_" + plantNumber[i] + ".png");// 根据各土地的植物标号
			area[i] = new JButton(plant[i]);// 把植物图标给土地按钮
		}
		// 属性设置
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

		// 按钮的监听事件（注：按钮的点击事件的具体方法应写在controller层，这里仅作测试用）
		// 具体根据需要自行修改
		backToFarmButton.addActionListener(new ActionListener() {// 返回农场按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new FarmView(userID);
				dispose();
			}
		});
		for (int i = 0; i < 15; i++) {
			area[i].addActionListener(new ActionListener() {// 土地的点击事件
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("测试-土地按钮");
				}
			});
		}
	}

	/**
	 * 测试用的主方法，使用OtherFarmView类时需要先实例化
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
