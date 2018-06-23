package com.echarts.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.omg.CORBA.REBIND;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.User;
import com.echarts.client.bean.twicepack.RepositoryViewBean;
import com.echarts.client.model.RepositoryModel;
import com.echarts.client.view.RepositoryView;
import com.echarts.util.IOperation;

/**
 * 
 * 1、根据查询repository表中的条目生成对应数目的物品信息 2、使用按钮 有指定的效果（showview） 3、出售按钮
 * 有指定的效果（showview）
 *
 */
public class RepositoryController implements ActionListener {
	public JFrame repositoryFrame;
	private int userID;
	private RepositoryView repositoryView;
	private JButton jumpPageButton;
	private JTextField pageField;
	private JButton previousPageButton;
	private JButton nextPageButton;
	private JButton useButton;
	private String resID;
	private int resGoodsID;
	private String goodsNumber;
	private JButton sellButton;
	private String goodsPrice;

	public RepositoryController() {

	}
	
	/**
	 * 仓库显示构造方法，根据userID和page获取对应id用户的对应页码仓库
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public RepositoryController(int userID, int page) throws UnknownHostException, IOException, ClassNotFoundException {
		checkRepository(userID, page);
	}

	/**
	 * 跳转页面构造方法
	 * 
	 * @param repositoryView
	 *            界面
	 * @param jumpPageButton
	 *            跳转按钮
	 * @param pageField
	 *            跳转的页码
	 * @param userID
	 *            用户的id
	 */
	public RepositoryController(RepositoryView repositoryView, JButton jumpPageButton, JTextField pageField,
			int userID) {
		this.repositoryView = repositoryView;
		this.jumpPageButton = jumpPageButton;
		this.pageField = pageField;
		this.userID = userID;
	}

	/**
	 * 上一页跳转构造方法
	 * 
	 * @param repositoryView
	 *            界面
	 * @param previousPageButton
	 *            上一页按钮
	 * @param userID
	 *            用户的id
	 */
	public RepositoryController(RepositoryView repositoryView, JButton previousPageButton, int userID) {
		this.repositoryView = repositoryView;
		this.previousPageButton = previousPageButton;
		this.userID = userID;
	}

	/**
	 * 下一页跳转构造方法
	 * 
	 * @param repositoryView
	 *            界面
	 * @param userID
	 *            用户的id
	 * @param nextPageButton
	 *            下一页按钮
	 */
	public RepositoryController(RepositoryView repositoryView, int userID, JButton nextPageButton) {
		this.repositoryView = repositoryView;
		this.nextPageButton = nextPageButton;
		this.userID = userID;
	}

	/**
	 * 使用按钮的功能
	 * 
	 * @param repositoryView
	 *            界面
	 * @param userID
	 *            用户id
	 * @param useButton
	 *            使用按钮
	 * @param resId
	 *            使用的物品的id
	 */
	public RepositoryController(RepositoryView repositoryView, int userID, JButton useButton, String resId,
			int resGoodsID, String goodsNumber) {
		this.repositoryView = repositoryView;
		this.userID = userID;
		this.useButton = useButton;
		this.resID = resId;
		this.resGoodsID = resGoodsID;
		this.goodsNumber = goodsNumber;
	}

	/**
	 * 售出按钮的功能
	 * @param repositoryView 界面
	 * @param userID 用户id
	 * @param resId 仓库jd
	 * @param sellButton 售出按钮
	 * @param goodsNumber 剩余的货物数
	 */
	public RepositoryController(RepositoryView repositoryView, int userID, String resId, JButton sellButton,
			 String goodsNumber,String goodsPrice) {
		this.repositoryView = repositoryView;
		this.userID = userID;
		this.sellButton = sellButton;
		this.resID = resId;
		this.goodsNumber = goodsNumber;
		this.goodsPrice = goodsPrice;
	}

	public User checkUser(int userID) throws UnknownHostException, IOException, ClassNotFoundException{
		RepositoryViewBean rp = new RepositoryViewBean();
		rp.setUserID(userID);
		rp.setAction("CHECKUSER");
		
		Message message = new Message(rp,IOperation.REPOSITORYVIEW);
		RepositoryModel rm = new RepositoryModel();
		return rm.checkUser(message);
	}
	
	public Repository[] checkRepository(int userID, int page)
			throws UnknownHostException, IOException, ClassNotFoundException {
		RepositoryViewBean rp = new RepositoryViewBean();
		rp.setPage(page);
		rp.setUserID(userID);
		rp.setAction("SHOW");

		Message initMessage = new Message(rp, IOperation.REPOSITORYVIEW);

		System.out.println("control启动");
		RepositoryModel rm = new RepositoryModel();
		return rm.showRepository(initMessage);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jumpPageButton) {
			int temp = repositoryView.page;// 存放原页码，如果跳转页码不存在则停留在原页码

			System.out.println("ctrll里的page" + repositoryView.page);
			try {
				repositoryView.page = Integer.parseInt(pageField.getText());
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("这页里什么都没有");
					if(repositoryView.page!=1)
					JOptionPane.showMessageDialog(repositoryView, "抱歉，您的仓库没有这一页");
					repositoryView.page = temp;
					pageField.setText(""+repositoryView.page);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(repositoryView, "抱歉，您的输入有误");
				System.out.println("输入有误");
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == previousPageButton) {
			int temp = repositoryView.page;// 存放原页码，如果跳转页码不存在则停留在原页码
			repositoryView.page--;
			try {
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("这页里什么都没有");
					JOptionPane.showMessageDialog(repositoryView, "抱歉，已经是第一页了");
					repositoryView.page = temp;
				}
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == nextPageButton) {
			int temp = repositoryView.page;// 存放原页码，如果跳转页码不存在则停留在原页码
			repositoryView.page++;
			try {
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("这页里什么都没有");
					JOptionPane.showMessageDialog(repositoryView, "抱歉，已经是最后一页了");
					repositoryView.page = temp;
				}
			} catch (ClassNotFoundException | IOException e1) {

			}
		} else if (e.getSource() == useButton) {
			int useNumber = 0;
			int fieldRemaining = -1;
			// 首先显示用户还剩下多少块土地
			RepositoryViewBean rp = new RepositoryViewBean();
			rp.setPage(-1);
			rp.setUserID(userID);
			rp.setAction("CHECKFIELD");
			Message message = new Message(rp, IOperation.REPOSITORYVIEW);

			try {
				RepositoryModel rm = new RepositoryModel();
				fieldRemaining = rm.checkField(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String remain = JOptionPane.showInputDialog(repositoryView, "您还剩下" + fieldRemaining + "块地,请输入要使用种子的个数");
			if ("".equals(remain)) {
				JOptionPane.showMessageDialog(repositoryView, "请输入想要使用的种子数");
			} else if (remain == null)
				System.out.println("取消");
			else {
				try {
					useNumber = Integer.parseInt(remain);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(repositoryView, "抱歉，您的输入有误");
				}
				System.out.println(useNumber);
				if (useNumber <= fieldRemaining && useNumber > 0) {
					rp.setUseNumber(useNumber);
					rp.setResGoodsID(resGoodsID);
					rp.setAction("USESEED");
					rp.setResID(Integer.parseInt(this.resID));

					message = new Message(rp, IOperation.REPOSITORYVIEW);
					RepositoryModel rm;
					try {
						rm = new RepositoryModel();

						if (rm.useSeed(message) == true) {
							JOptionPane.showMessageDialog(repositoryView, "使用种子成功，种植了"+useNumber+"块土地");
						} else {
							JOptionPane.showMessageDialog(repositoryView, "使用种子失败");
						}
						
						repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
						if (repositoryView.repositorys != null||repositoryView.page==1)
							repositoryView.initUI(userID);
						else if(repositoryView.page>1){
							repositoryView.page--;
							repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
							repositoryView.initUI(userID);
						}

					} catch (IOException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}

				} else if (useNumber > fieldRemaining || useNumber > Integer.parseInt(goodsNumber)) {
					JOptionPane.showMessageDialog(repositoryView, "抱歉，您想要使用的种子太多，请重试");
				} else if (useNumber == 0) {
					// 不使用种子
				} else {
					JOptionPane.showMessageDialog(repositoryView, "抱歉，请正确输入一个正整数");
				}

			}
		} else if (e.getSource() == sellButton) {
			int sellNumber = 0;
			String sellNumberString = JOptionPane.showInputDialog(repositoryView, "请输入您想要卖出的货物数(输入max可全卖)");
			if ("".equals(sellNumberString))
				JOptionPane.showMessageDialog(repositoryView, "抱歉，请输入您想要售出的货物数");
			else if (null == sellNumberString)
				System.out.println("用户取消操作");
			else {
				try{
				sellNumber = Integer.parseInt(sellNumberString);
				}catch(NumberFormatException e1){
					if("max".equals(sellNumberString)||"MAX".equals(sellNumberString)){
						sellNumber = Integer.parseInt(goodsNumber);
					}else
					JOptionPane.showMessageDialog(repositoryView, "抱歉，抱歉您的输入有误");
				}
				if (sellNumber > 0 && sellNumber <= Integer.parseInt(goodsNumber)) {
					RepositoryViewBean rp = new RepositoryViewBean();
					rp.setResID(Integer.parseInt(resID));
					rp.setUseNumber(sellNumber);
					rp.setUserID(userID);
					rp.setResPrice(Integer.parseInt(goodsPrice));
					rp.setAction("SELL");
					Message message = new Message(rp, IOperation.REPOSITORYVIEW);
					try {
						RepositoryModel rm = new RepositoryModel();
						int acqMoney = 0;
						acqMoney = rm.sellGoods(message);
						if(acqMoney>=0){
							JOptionPane.showMessageDialog(repositoryView, "恭喜您，售出货物得到"+acqMoney+"个金币");
							repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
							if (repositoryView.repositorys != null||repositoryView.page==1)
								repositoryView.initUI(userID);
							else if(repositoryView.page>1){
								repositoryView.page--;
								repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
								repositoryView.initUI(userID);
							}
						}else{
							JOptionPane.showMessageDialog(repositoryView, "抱歉，售出货物失败");
						}
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				} else if (sellNumber > Integer.parseInt(goodsNumber)) {
					JOptionPane.showMessageDialog(repositoryView, "抱歉，您想要售出的货物过多，请重试");
				} else if (sellNumber == 0) {
					// 不售出
				} else {
					JOptionPane.showMessageDialog(repositoryView, "抱歉，请正确输入一个正整数");
				}
			}

		}
	}

}
