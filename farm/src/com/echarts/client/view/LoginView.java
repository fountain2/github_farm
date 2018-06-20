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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * 登录页面
 */
public class LoginView extends JFrame {

	private JPanel contentPane;

	/**
	 * LoginView的构造方法，实例化时将生成一个登录界面
	 */
	public LoginView() {
		// 设置窗口（即父类JFrame）的各项属性和状态
		setTitle("过气农场");// 设置窗口标题
		setResizable(false);// 设置窗口属性为不可拉伸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1000, 679);// 设置窗口尺寸

		// 设置背景图片
		Icon background = new ImageIcon("img\\LoginViewBG.png");
		JLabel bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 初始化各项组件
		JTextField userNameField = new JTextField();// 用户名的输入框
		JPasswordField userPasswordField = new JPasswordField();// 密码的输入框
		JButton loginButton = new JButton();// 登录按钮
		JButton registerButton = new JButton();// 注册按钮

		// 设置各项组件的属性和状态
		userNameField.setFont(new Font("宋体", 1, 20));// 设置字体样式
		userNameField.setForeground(Color.WHITE);// 设置字体颜色
		userNameField.setCaretColor(Color.WHITE);// 设置光标的颜色
		userNameField.setBounds(494, 309, 240, 30);// 设置输入框的大小和位置
		userNameField.setBackground(new Color(136, 76, 30));// 设置输入框的背景颜色
		userNameField.setBorder(null);// 设置输入框的边框
		
		userPasswordField.setFont(new Font("宋体", 1, 20));// 设置字体样式
		userPasswordField.setForeground(Color.WHITE);// 设置字体颜色
		userPasswordField.setCaretColor(Color.WHITE);// 设置光标的颜色
		userPasswordField.setBounds(494, 369, 240, 30);// 设置输入框的大小和位置
		userPasswordField.setBackground(new Color(136, 76, 30));// 设置输入框的背景颜色
		userPasswordField.setBorder(null);// 设置输入框的边框
		
		loginButton.setBounds(340, 452, 180, 60);// 设置按钮的大小和位置
		loginButton.setContentAreaFilled(false);// 将按钮透明化
		loginButton.setBorder(null);// 隐藏边框
		
		registerButton.setBounds(583, 452, 180, 60);// 设置按钮的大小和位置
		registerButton.setContentAreaFilled(false);// 将按钮透明化
		registerButton.setBorder(null);// 隐藏边框

		// 添加各项组件
		contentPane = new JPanel();
		contentPane.add(userNameField);
		contentPane.add(userPasswordField);
		contentPane.add(loginButton);
		contentPane.add(registerButton);
		contentPane.add(bgLabel);//最后再加背景图

		// 设置底部面板的各项属性和状态
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// 显示界面窗口
		setContentPane(contentPane);
		setVisible(true);

		// 按钮的监听事件（注：按钮的点击事件的具体方法应写在controller层，这里仅作测试用）
		// 登录按钮的要求：成功登录（即数据库存在该用户）时，跳转到农场页面FarmView，并传递该用户的ID给FarmView
		// 注册按钮的跳转已完成，可以不改动
		// 具体根据需要自行修改
		loginButton.addActionListener(new ActionListener() {// 登录按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("测试-登录按钮");
			}
		});
		registerButton.addActionListener(new ActionListener() {// 注册按钮的点击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
				dispose();
			}
		});
	}

	/**
	 * 测试用的主方法，使用LoginView类时需要先实例化
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
