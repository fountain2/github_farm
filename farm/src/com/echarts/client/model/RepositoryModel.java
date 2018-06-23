package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.User;

public class RepositoryModel {
	// 用对象序列化进行发送Message和接受返回的结果
	private Socket socket;
	// 对象序列化对象：将数据发送到服务器
	private ObjectOutputStream objos;
	// 对象反序列化：将数据从服务器接收过来
	private ObjectInputStream objis;

	public RepositoryModel() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 9999);

	}

	/**
	 * 查询用户的方法
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User checkUser(Message message) throws IOException, ClassNotFoundException{
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		objis = new ObjectInputStream(socket.getInputStream());
		return (User)objis.readObject();
	}
	
	/**
	 * 显示仓库的model
	 * 
	 * @param message
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Repository[] showRepository(Message message) throws IOException, ClassNotFoundException {
		System.out.println("model启动");
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();

		objis = new ObjectInputStream(socket.getInputStream());
		Repository[] rc = (Repository[]) objis.readObject();
		System.out.println("通过返回");
		if (rc[0] == null) {
			System.out.println("这页什么都没有");
			return null;
		}
		System.out.println("rc" + rc[0].getUserName());

		// objis.close();
		// objos.close();
		return rc;
	}

	/**
	 * 查看剩余土地的方法
	 * 
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int checkField(Message message) throws IOException, ClassNotFoundException {
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();

		int fieldRemaining = -1;
		objis = new ObjectInputStream(socket.getInputStream());
		fieldRemaining = (int) objis.readObject();
		if (fieldRemaining == -1) {
			System.out.println("毛都没有");
		}

		return fieldRemaining;
	}

	/**
	 * 使用功能
	 * @param message
	 * @return
	 * @throws IOException
	 */
	public boolean useSeed(Message message) throws IOException{

		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		objis = new ObjectInputStream(socket.getInputStream());
		return objis.readBoolean();
		
	}
	
	/**
	 * 售出功能，返回售出的钱
	 * @param message
	 * @return
	 * @throws IOException
	 */
	public int sellGoods(Message message) throws IOException{
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		objis = new ObjectInputStream(socket.getInputStream());
		return objis.readInt();
	}
}
