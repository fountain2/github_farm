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
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * 设置页面
 */
public class ConfigView extends JFrame {

	private JPanel contentPane;

	/**
	 * ConfigView的构造方法，实例化时将生成一个设置界面，需用户ID作为参数
	 */
	public ConfigView(int userID) {
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸

		// 设置背景图片
		Icon background = new ImageIcon("img\\ConfigViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件 
		JPasswordField oldPasswordField = new JPasswordField();// 旧密码的输入框
		JPasswordField newPasswordField1 = new JPasswordField();// 新密码的输入框
		JPasswordField newPasswordField2 = new JPasswordField();// 确认新密码的输入框
		JButton changePasswordButton = new JButton();// 修改密码按钮
		JButton changeUserButton = new JButton();// 切换账户按钮
		JButton backToFarmButton = new JButton();// 返回农场按钮

		// 设置各项组件的属性和状态
		oldPasswordField.setFont(new Font("宋体", 1, 20));// 设置字体样式
		oldPasswordField.setForeground(Color.WHITE);// 设置字体颜色
		oldPasswordField.setCaretColor(Color.WHITE);// 设置光标的颜色
		oldPasswordField.setBounds(509, 228, 240, 30);// 设置输入框的大小和位置
		oldPasswordField.setBackground(new Color(136, 76, 30));// 设置输入框的背景颜色
		oldPasswordField.setBorder(null);// 设置输入框的边框

		newPasswordField1.setFont(new Font("宋体", 1, 20));// 设置字体样式
		newPasswordField1.setForeground(Color.WHITE);// 设置字体颜色
		newPasswordField1.setCaretColor(Color.WHITE);// 设置光标的颜色
		newPasswordField1.setBounds(509, 278, 240, 30);// 设置输入框的大小和位置
		newPasswordField1.setBackground(new Color(136, 76, 30));// 设置输入框的背景颜色
		newPasswordField1.setBorder(null);// 设置输入框的边框

		newPasswordField2.setFont(new Font("宋体", 1, 20));// 设置字体样式
		newPasswordField2.setForeground(Color.WHITE);// 设置字体颜色
		newPasswordField2.setCaretColor(Color.WHITE);// 设置光标的颜色
		newPasswordField2.setBounds(509, 328, 240, 30);// 设置输入框的大小和位置
		newPasswordField2.setBackground(new Color(136, 76, 30));// 设置输入框的背景颜色
		newPasswordField2.setBorder(null);// 设置输入框的边框

		changePasswordButton.setBounds(340, 392, 180, 60);// 设置按钮的大小和位置
		changePasswordButton.setContentAreaFilled(false);// 将按钮透明化
		changePasswordButton.setBorder(null);// 隐藏边框

		changeUserButton.setBounds(583, 392, 180, 60);// 设置按钮的大小和位置
		changeUserButton.setContentAreaFilled(false);// 将按钮透明化
		changeUserButton.setBorder(null);// 隐藏边框
		
		backToFarmButton.setBounds(793,574,180,70);// 设置按钮的大小和位置
		backToFarmButton.setContentAreaFilled(false);// 将按钮透明化
		backToFarmButton.setBorder(null);// 隐藏边框

		// 添加各项组件
		contentPane = new JPanel();
		contentPane.add(oldPasswordField);
		contentPane.add(newPasswordField1);
		contentPane.add(newPasswordField2);
		contentPane.add(changePasswordButton);
		contentPane.add(changeUserButton);
		contentPane.add(backToFarmButton);
		contentPane.add(bgLabel);//最后再加背景图

		// 设置底部面板的各项属性和状态
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// 显示界面窗口
		setContentPane(contentPane);
		setVisible(true);
		
		// 按钮的监听事件（注：按钮的点击事件的具体方法应写在controller层，这里仅作测试用）
		// 修改密码按钮的要求：密码修改成功时，弹出修改成功的消息框，之后清空所有输入框
		// 切换账户按钮和返回农场按钮的跳转已实现，可以不改动
		// 具体根据需要自行修改
		changePasswordButton.addActionListener(new ActionListener() {// 修改密码按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("测试-修改密码按钮");
			}
		});
		changeUserButton.addActionListener(new ActionListener() {// 切换账户按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView();
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
	}

	/**
	 * 测试用的主方法，使用ConfigView类时需要先实例化
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigView frame = new ConfigView(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
