package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Message;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.User;
import com.echarts.client.bean.twicepack.FarmViewBean;
import com.echarts.util.IOperation;

public class FarmModel {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	public FarmModel() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 9999);
	}

	/**
	 * 根据用户id 查询用户信息
	 * @param userID
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User initFarmData(int userID) throws IOException, ClassNotFoundException{
		User user =new User();
		user.setUserId(userID);
		FarmViewBean farmViewBean = new FarmViewBean(user,"QUERY_USER");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		User user_retrun = (User) ois.readObject();
		
		return user_retrun;
	}

	/**
	 * 根据用户id 查询土地信息
	 * @param userID
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Field> queryField(int userID) throws IOException, ClassNotFoundException {
		User user =new User();
		user.setUserId(userID);
		FarmViewBean farmViewBean = new FarmViewBean(user,"QUERY_FIELD");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		List<Field> fieldList = (List<Field>) ois.readObject();
		return fieldList;
	}

	/**
	 * 查询指定土地的 种子 信息
	 * @param field
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Seed querySeed(Field field) throws IOException, ClassNotFoundException {
		FarmViewBean farmViewBean = new FarmViewBean(field,"QUERY_SEED");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		Seed seed =  (Seed) ois.readObject();
		return seed;
	}

	/**
	 * 将指定土地 修改 为 指定的状态
	 * @param field
	 * @param i 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void updateFieldStatus(Field field, int field_toStatus) throws IOException, ClassNotFoundException {
		FarmViewBean farmViewBean = new FarmViewBean(field,field_toStatus,"UPDATE_FIELD_STATUS");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
	}

	/**
	 * 查询仓库是否存在此种 作物
	 * @param repository
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Repository queryRepository(Repository repository) throws IOException, ClassNotFoundException {
		FarmViewBean farmViewBean = new FarmViewBean(repository,"QUERY_REPOSITORY");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		return (Repository) ois.readObject();
	}

	/**
	 * 插入
	 * @param repository
	 * @throws IOException 
	 */
	public void insertRepository(Repository repository) throws IOException {
		FarmViewBean farmViewBean = new FarmViewBean(repository,"INSERT_REPOSITORY");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
	}

	/**
	 * 更新
	 * @param repos
	 * @throws IOException
	 */
	public void updateRepository(Repository repos) throws IOException {
		FarmViewBean farmViewBean = new FarmViewBean(repos,"UPDATE_REPOSITORY");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
	}

	
	//其他农场页面的model
	/**
	 * 修改剩余数量
	 * @param field
	 * @throws IOException 
	 */
	public void updateFieldHarvestNumber(Field field) throws IOException {
		FarmViewBean farmViewBean = new FarmViewBean(field,"UPDATE_FIELD_HARVESTNUMBER");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
	}

	public List<User> queryUserList() throws IOException, ClassNotFoundException {
		FarmViewBean farmViewBean = new FarmViewBean("QUERY_USERLIST");
		Message message =new Message(farmViewBean, IOperation.FARMVIEW);
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		List<User> userlist = (List<User>) ois.readObject();
		return userlist;
	}

}
