package com.echarts.server.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Message;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.User;
import com.echarts.client.bean.twicepack.ConfigViewBean;
import com.echarts.client.bean.twicepack.FarmViewBean;
import com.echarts.client.bean.twicepack.LoginViewBean;
import com.echarts.client.bean.twicepack.RegisterViewBean;
import com.echarts.client.bean.twicepack.RepositoryViewBean;
import com.echarts.client.bean.twicepack.ShopViewBean;
import com.echarts.server.dao.impl.ConfigViewDaoImpl;
import com.echarts.server.dao.impl.FarmViewDao;
import com.echarts.server.dao.impl.LoginViewDao;
import com.echarts.server.dao.impl.RegisterViewDao;
import com.echarts.server.dao.impl.RepositoryDaoImpl;
import com.echarts.server.dao.impl.ShopViewDaoImpl;
import com.echarts.util.IOperation;

/**
 *	ҵ���߼��� 
 *
 */
public class ServerServiceImpl extends Thread{
	private Socket socket;
	private ObjectInputStream objis;
	private ObjectOutputStream objos;
	
	
	public ServerServiceImpl(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		Message message = null;
		try {
			//��ȡ�ͻ��˵�����
			objis = new ObjectInputStream(socket.getInputStream());
			message = (Message) objis.readObject();
			
			//����������ͻ��˵������
			objos = new ObjectOutputStream(socket.getOutputStream());
			
			String pageFlag = message.getPageFlag();
			
			switch (pageFlag) {
			
				//��¼ҳ��
			case IOperation.LOGINVIEW:
				LoginViewBean loginViewBean = message.getLoginViewBean();
				User userLogin = loginViewBean.getUser();
				LoginViewDao loginViewDao = new LoginViewDao();
				User userLoginBack = loginViewDao.login(userLogin);
				objos.writeObject(userLoginBack);
				objos.flush();
				break;
				
				//ע��ҳ��
			case IOperation.REGISTERVIEW:
				RegisterViewBean registerViewBean = message.getRegisterViewBean();
				String operation = registerViewBean.getOperation();
				if(operation.equals("query")) {
					User user = registerViewBean.getUser();
					RegisterViewDao registerViewDao = new RegisterViewDao();
					User userBack = registerViewDao.queryUser(user.getUserName());
					objos.writeObject(userBack);
					objos.flush();
				}else {
					User userRegister = registerViewBean.getUser();
					new RegisterViewDao().registerUser(userRegister);
				}
				break;
				
				//ũ��ҳ��
			case IOperation.FARMVIEW:
				FarmViewBean farmViewBean = message.getFarmViewBean();
				FarmViewDao farmViewDao = new FarmViewDao();
				
				String operationFlag = farmViewBean.getOperationFlag();
				
				switch (operationFlag) {
				
				//��ѯ�û�����
				case "QUERY_USER":
					int userId = farmViewBean.getUser().getUserId();
					User user = farmViewDao.queryUserInfoById(userId);
					objos.writeObject(user);
					objos.flush();
					break;
					
				//��ѯ��������
				case "QUERY_FIELD":
					List<Field> fieldList = farmViewDao.queryFieldInfoById(farmViewBean.getUser().getUserId());
					objos.writeObject(fieldList);
					objos.flush();
					break;
				
				//��ѯ��������
				case "QUERY_SEED":
					int seedId =farmViewBean.getField().getSeedId();
					Seed seed = farmViewDao.querySeedInfoById(seedId);
					objos.writeObject(seed);
					objos.flush();
					break;
					
				//�޸�ָ�����ص�״̬
				case "UPDATE_FIELD_STATUS":
					Field field = farmViewBean.getField();
					int field_toStatus = farmViewBean.getField_toStatus();
					farmViewDao.updateFieldStatus(field.getUserId(),field.getFieldId(),field_toStatus);
					break;
					
				//��ѯָ���û��Ĳֿ��Ƿ���ָ�� ����
				case "QUERY_REPOSITORY":
					Repository repository = farmViewBean.getRepository();
					Repository repos = farmViewDao.queryRepository(repository.getUserId(),repository.getResType(),repository.getResGoodsId());
					objos.writeObject(repos);
					objos.flush();
					break;
				
				//����ֿ�
				case "INSERT_REPOSITORY":
					Repository repository2 = farmViewBean.getRepository();
					farmViewDao.insertRepository(repository2.getUserId(),repository2.getResGoodsId(),repository2.getResType(),repository2.getResNumber());
					break;
				
				//���²ֿ�
				case "UPDATE_REPOSITORY":
					Repository repository3 = farmViewBean.getRepository();
					farmViewDao.updateRepository(repository3.getUserId(),repository3.getResGoodsId(),repository3.getResType(),repository3.getResNumber());
					break;	
					
					
				//�޸�ʣ������
				case "UPDATE_FIELD_HARVESTNUMBER":
					Field field2 = farmViewBean.getField();
					farmViewDao.updateFieldHarvestNumber(field2.getUserId(),field2.getFieldId(),field2.getFiledHarvestNumber());
					break;	
				
				//��ѯ�û��б�
				case "QUERY_USERLIST":
					List<User> userlist = farmViewDao.queryUserList();
					objos.writeObject(userlist);
					objos.flush();
					break;	
					
				}
				
				break;
				
				//����ũ��ҳ��
			case IOperation.OTHERFARMVIEW:
				break;
				
				//�ֿ�ҳ��
			case IOperation.REPOSITORYVIEW:
				RepositoryViewBean repositoryViewBean = message.getRepositoryViewBean();
				RepositoryDaoImpl repositoryDao = new RepositoryDaoImpl();
				switch (repositoryViewBean.getAction()) {
				case "SHOW":
					// ִ�в�ѯ�ֿⷽ������ͻ��˴���ֿ��
					System.out.println("service show");
					Repository[] repository;
					repository = repositoryDao.showRepository(repositoryViewBean.getUserID(),
							repositoryViewBean.getPage());
					objos.writeObject(repository);
					System.out.println("service ����");
					objos.flush();
					// objos.close();
					break;
				case "CHECKFIELD":
					// ִ�в�ѯ����ʣ���������ͻ��˴���ʣ�����������
					int fieldRemaining = -1;
					fieldRemaining = repositoryDao.checkField(repositoryViewBean.getUserID());
					objos.writeObject(fieldRemaining);
					objos.flush();
					break;
				case "USESEED":
					// ִ��ʹ�����ӣ���ͻ��˴����Ƿ�ʹ�óɹ�int userID, int resID, int resGoodsID,
					// int useNumber
					boolean done = repositoryDao.useSeed(repositoryViewBean.getUserID(), repositoryViewBean.getResID(),
							repositoryViewBean.getResGoodsID(), repositoryViewBean.getUseNumber());
					objos.writeBoolean(done);
					objos.flush();
					break;
				case "SELL":
					int acqMoney = repositoryDao.sellGoods(repositoryViewBean.getUserID(),
							repositoryViewBean.getResID(), 
							repositoryViewBean.getUseNumber(),repositoryViewBean.getResPrice());
					objos.writeInt(acqMoney);
					objos.flush();
					break;
				case "CHECKUSER":
					User user = repositoryDao.checkUser(repositoryViewBean.getUserID());
					objos.writeObject(user);
					objos.flush();
					break;
				}

				break;
				
				//�̵�ҳ��
			case IOperation.SHOPVIEW:
				// ��������Start��������
				if (message.getShopViewBean().getManageFlag().equals("GETMONEY")) {// ��ȡ�û�������Ĳ���
					int userID = message.getShopViewBean().getUserID();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					int result = svdi.getMoney(userID);// ����DAO��������ũ���ķ���
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setAboutMoney(result);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("LVUP")) {// ����ũ���Ĳ���
					int userID = message.getShopViewBean().getUserID();
					boolean isWantToBuy = message.getShopViewBean().isWantToBuy();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					int result = svdi.levelUp(userID, isWantToBuy);// ����DAO��������ũ���ķ���
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setAboutMoney(result);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("CHANGEPAGE")) {// �л�ҳ��Ĳ���
					int userID = message.getShopViewBean().getUserID();
					int page = message.getShopViewBean().getPage();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					List<Seed> seedList = svdi.getSeedPage(userID, page);// ����DAO�����л�ҳ��ķ���
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setSeedList(seedList);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("BUYSEED")) {// �������ӵĲ���
					int userID = message.getShopViewBean().getUserID();
					int seedID = message.getShopViewBean().getSeedID();
					int numberResult = message.getShopViewBean().getNumberResult();
					int moneyResult = message.getShopViewBean().getMoneyResult();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					svdi.buySeed(userID, seedID, numberResult, moneyResult);
					ShopViewBean backMessage = new ShopViewBean();
					objos.writeObject(backMessage);
					objos.flush();
				}

				// ��������End������������
				break;
				
				//����ҳ��
			case IOperation.CONFIGVIEW:
				ConfigViewBean configViewBean = message.getConfigViewBean();
				ConfigViewDaoImpl configViewDao = new ConfigViewDaoImpl();


				String password = configViewDao.searchUser(configViewBean.getUserId(), configViewBean.getOldPassword(),configViewBean.getNewPassword1());
				
				ConfigViewBean result = new ConfigViewBean(password);
				objos.writeObject(result);
				objos.flush();
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
