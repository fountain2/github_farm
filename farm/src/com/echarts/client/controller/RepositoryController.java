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
 * 1�����ݲ�ѯrepository���е���Ŀ���ɶ�Ӧ��Ŀ����Ʒ��Ϣ 2��ʹ�ð�ť ��ָ����Ч����showview�� 3�����۰�ť
 * ��ָ����Ч����showview��
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
	 * �ֿ���ʾ���췽��������userID��page��ȡ��Ӧid�û��Ķ�Ӧҳ��ֿ�
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 */
	public RepositoryController(int userID, int page) throws UnknownHostException, IOException, ClassNotFoundException {
		checkRepository(userID, page);
	}

	/**
	 * ��תҳ�湹�췽��
	 * 
	 * @param repositoryView
	 *            ����
	 * @param jumpPageButton
	 *            ��ת��ť
	 * @param pageField
	 *            ��ת��ҳ��
	 * @param userID
	 *            �û���id
	 */
	public RepositoryController(RepositoryView repositoryView, JButton jumpPageButton, JTextField pageField,
			int userID) {
		this.repositoryView = repositoryView;
		this.jumpPageButton = jumpPageButton;
		this.pageField = pageField;
		this.userID = userID;
	}

	/**
	 * ��һҳ��ת���췽��
	 * 
	 * @param repositoryView
	 *            ����
	 * @param previousPageButton
	 *            ��һҳ��ť
	 * @param userID
	 *            �û���id
	 */
	public RepositoryController(RepositoryView repositoryView, JButton previousPageButton, int userID) {
		this.repositoryView = repositoryView;
		this.previousPageButton = previousPageButton;
		this.userID = userID;
	}

	/**
	 * ��һҳ��ת���췽��
	 * 
	 * @param repositoryView
	 *            ����
	 * @param userID
	 *            �û���id
	 * @param nextPageButton
	 *            ��һҳ��ť
	 */
	public RepositoryController(RepositoryView repositoryView, int userID, JButton nextPageButton) {
		this.repositoryView = repositoryView;
		this.nextPageButton = nextPageButton;
		this.userID = userID;
	}

	/**
	 * ʹ�ð�ť�Ĺ���
	 * 
	 * @param repositoryView
	 *            ����
	 * @param userID
	 *            �û�id
	 * @param useButton
	 *            ʹ�ð�ť
	 * @param resId
	 *            ʹ�õ���Ʒ��id
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
	 * �۳���ť�Ĺ���
	 * @param repositoryView ����
	 * @param userID �û�id
	 * @param resId �ֿ�jd
	 * @param sellButton �۳���ť
	 * @param goodsNumber ʣ��Ļ�����
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

		System.out.println("control����");
		RepositoryModel rm = new RepositoryModel();
		return rm.showRepository(initMessage);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jumpPageButton) {
			int temp = repositoryView.page;// ���ԭҳ�룬�����תҳ�벻������ͣ����ԭҳ��

			System.out.println("ctrll���page" + repositoryView.page);
			try {
				repositoryView.page = Integer.parseInt(pageField.getText());
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("��ҳ��ʲô��û��");
					if(repositoryView.page!=1)
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ�����Ĳֿ�û����һҳ");
					repositoryView.page = temp;
					pageField.setText(""+repositoryView.page);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(repositoryView, "��Ǹ��������������");
				System.out.println("��������");
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == previousPageButton) {
			int temp = repositoryView.page;// ���ԭҳ�룬�����תҳ�벻������ͣ����ԭҳ��
			repositoryView.page--;
			try {
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("��ҳ��ʲô��û��");
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ���Ѿ��ǵ�һҳ��");
					repositoryView.page = temp;
				}
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == nextPageButton) {
			int temp = repositoryView.page;// ���ԭҳ�룬�����תҳ�벻������ͣ����ԭҳ��
			repositoryView.page++;
			try {
				repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
				if (repositoryView.repositorys != null)
					repositoryView.initUI(userID);
				else {
					System.out.println("��ҳ��ʲô��û��");
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ���Ѿ������һҳ��");
					repositoryView.page = temp;
				}
			} catch (ClassNotFoundException | IOException e1) {

			}
		} else if (e.getSource() == useButton) {
			int useNumber = 0;
			int fieldRemaining = -1;
			// ������ʾ�û���ʣ�¶��ٿ�����
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
			String remain = JOptionPane.showInputDialog(repositoryView, "����ʣ��" + fieldRemaining + "���,������Ҫʹ�����ӵĸ���");
			if ("".equals(remain)) {
				JOptionPane.showMessageDialog(repositoryView, "��������Ҫʹ�õ�������");
			} else if (remain == null)
				System.out.println("ȡ��");
			else {
				try {
					useNumber = Integer.parseInt(remain);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ��������������");
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
							JOptionPane.showMessageDialog(repositoryView, "ʹ�����ӳɹ�����ֲ��"+useNumber+"������");
						} else {
							JOptionPane.showMessageDialog(repositoryView, "ʹ������ʧ��");
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
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ������Ҫʹ�õ�����̫�࣬������");
				} else if (useNumber == 0) {
					// ��ʹ������
				} else {
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ������ȷ����һ��������");
				}

			}
		} else if (e.getSource() == sellButton) {
			int sellNumber = 0;
			String sellNumberString = JOptionPane.showInputDialog(repositoryView, "����������Ҫ�����Ļ�����(����max��ȫ��)");
			if ("".equals(sellNumberString))
				JOptionPane.showMessageDialog(repositoryView, "��Ǹ������������Ҫ�۳��Ļ�����");
			else if (null == sellNumberString)
				System.out.println("�û�ȡ������");
			else {
				try{
				sellNumber = Integer.parseInt(sellNumberString);
				}catch(NumberFormatException e1){
					if("max".equals(sellNumberString)||"MAX".equals(sellNumberString)){
						sellNumber = Integer.parseInt(goodsNumber);
					}else
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ����Ǹ������������");
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
							JOptionPane.showMessageDialog(repositoryView, "��ϲ�����۳�����õ�"+acqMoney+"�����");
							repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
							if (repositoryView.repositorys != null||repositoryView.page==1)
								repositoryView.initUI(userID);
							else if(repositoryView.page>1){
								repositoryView.page--;
								repositoryView.repositorys = checkRepository(this.userID, repositoryView.page);
								repositoryView.initUI(userID);
							}
						}else{
							JOptionPane.showMessageDialog(repositoryView, "��Ǹ���۳�����ʧ��");
						}
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				} else if (sellNumber > Integer.parseInt(goodsNumber)) {
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ������Ҫ�۳��Ļ�����࣬������");
				} else if (sellNumber == 0) {
					// ���۳�
				} else {
					JOptionPane.showMessageDialog(repositoryView, "��Ǹ������ȷ����һ��������");
				}
			}

		}
	}

}
