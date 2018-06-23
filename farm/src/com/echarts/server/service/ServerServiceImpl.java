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
 *	业务逻辑层 
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
			//获取客户端的输入
			objis = new ObjectInputStream(socket.getInputStream());
			message = (Message) objis.readObject();
			
			//创建输出到客户端的输出流
			objos = new ObjectOutputStream(socket.getOutputStream());
			
			String pageFlag = message.getPageFlag();
			
			switch (pageFlag) {
			
				//登录页面
			case IOperation.LOGINVIEW:
				LoginViewBean loginViewBean = message.getLoginViewBean();
				User userLogin = loginViewBean.getUser();
				LoginViewDao loginViewDao = new LoginViewDao();
				User userLoginBack = loginViewDao.login(userLogin);
				objos.writeObject(userLoginBack);
				objos.flush();
				break;
				
				//注册页面
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
				
				//农场页面
			case IOperation.FARMVIEW:
				FarmViewBean farmViewBean = message.getFarmViewBean();
				FarmViewDao farmViewDao = new FarmViewDao();
				
				String operationFlag = farmViewBean.getOperationFlag();
				
				switch (operationFlag) {
				
				//查询用户数据
				case "QUERY_USER":
					int userId = farmViewBean.getUser().getUserId();
					User user = farmViewDao.queryUserInfoById(userId);
					objos.writeObject(user);
					objos.flush();
					break;
					
				//查询土地数据
				case "QUERY_FIELD":
					List<Field> fieldList = farmViewDao.queryFieldInfoById(farmViewBean.getUser().getUserId());
					objos.writeObject(fieldList);
					objos.flush();
					break;
				
				//查询种子数据
				case "QUERY_SEED":
					int seedId =farmViewBean.getField().getSeedId();
					Seed seed = farmViewDao.querySeedInfoById(seedId);
					objos.writeObject(seed);
					objos.flush();
					break;
					
				//修改指定土地的状态
				case "UPDATE_FIELD_STATUS":
					Field field = farmViewBean.getField();
					int field_toStatus = farmViewBean.getField_toStatus();
					farmViewDao.updateFieldStatus(field.getUserId(),field.getFieldId(),field_toStatus);
					break;
					
				//查询指定用户的仓库是否有指定 作物
				case "QUERY_REPOSITORY":
					Repository repository = farmViewBean.getRepository();
					Repository repos = farmViewDao.queryRepository(repository.getUserId(),repository.getResType(),repository.getResGoodsId());
					objos.writeObject(repos);
					objos.flush();
					break;
				
				//插入仓库
				case "INSERT_REPOSITORY":
					Repository repository2 = farmViewBean.getRepository();
					farmViewDao.insertRepository(repository2.getUserId(),repository2.getResGoodsId(),repository2.getResType(),repository2.getResNumber());
					break;
				
				//更新仓库
				case "UPDATE_REPOSITORY":
					Repository repository3 = farmViewBean.getRepository();
					farmViewDao.updateRepository(repository3.getUserId(),repository3.getResGoodsId(),repository3.getResType(),repository3.getResNumber());
					break;	
					
					
				//修改剩余数量
				case "UPDATE_FIELD_HARVESTNUMBER":
					Field field2 = farmViewBean.getField();
					farmViewDao.updateFieldHarvestNumber(field2.getUserId(),field2.getFieldId(),field2.getFiledHarvestNumber());
					break;	
				
				//查询用户列表
				case "QUERY_USERLIST":
					List<User> userlist = farmViewDao.queryUserList();
					objos.writeObject(userlist);
					objos.flush();
					break;	
					
				}
				
				break;
				
				//其他农场页面
			case IOperation.OTHERFARMVIEW:
				break;
				
				//仓库页面
			case IOperation.REPOSITORYVIEW:
				RepositoryViewBean repositoryViewBean = message.getRepositoryViewBean();
				RepositoryDaoImpl repositoryDao = new RepositoryDaoImpl();
				switch (repositoryViewBean.getAction()) {
				case "SHOW":
					// 执行查询仓库方法，向客户端传输仓库表
					System.out.println("service show");
					Repository[] repository;
					repository = repositoryDao.showRepository(repositoryViewBean.getUserID(),
							repositoryViewBean.getPage());
					objos.writeObject(repository);
					System.out.println("service 返回");
					objos.flush();
					// objos.close();
					break;
				case "CHECKFIELD":
					// 执行查询土地剩余情况，向客户端传输剩余的土地数。
					int fieldRemaining = -1;
					fieldRemaining = repositoryDao.checkField(repositoryViewBean.getUserID());
					objos.writeObject(fieldRemaining);
					objos.flush();
					break;
				case "USESEED":
					// 执行使用种子，向客户端传输是否使用成功int userID, int resID, int resGoodsID,
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
				
				//商店页面
			case IOperation.SHOPVIEW:
				// 操作内容Start————
				if (message.getShopViewBean().getManageFlag().equals("GETMONEY")) {// 获取用户金币数的操作
					int userID = message.getShopViewBean().getUserID();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					int result = svdi.getMoney(userID);// 调用DAO层中升级农场的方法
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setAboutMoney(result);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("LVUP")) {// 升级农场的操作
					int userID = message.getShopViewBean().getUserID();
					boolean isWantToBuy = message.getShopViewBean().isWantToBuy();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					int result = svdi.levelUp(userID, isWantToBuy);// 调用DAO层中升级农场的方法
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setAboutMoney(result);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("CHANGEPAGE")) {// 切换页码的操作
					int userID = message.getShopViewBean().getUserID();
					int page = message.getShopViewBean().getPage();
					ShopViewDaoImpl svdi = new ShopViewDaoImpl();
					List<Seed> seedList = svdi.getSeedPage(userID, page);// 调用DAO层中切换页码的方法
					ShopViewBean backMessage = new ShopViewBean();
					backMessage.setSeedList(seedList);
					objos.writeObject(backMessage);
					objos.flush();
				} else if (message.getShopViewBean().getManageFlag().equals("BUYSEED")) {// 购买种子的操作
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

				// 操作内容End——————
				break;
				
				//设置页面
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
