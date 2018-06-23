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
	// �ö������л����з���Message�ͽ��ܷ��صĽ��
	private Socket socket;
	// �������л����󣺽����ݷ��͵�������
	private ObjectOutputStream objos;
	// �������л��������ݴӷ��������չ���
	private ObjectInputStream objis;

	public RepositoryModel() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 9999);

	}

	/**
	 * ��ѯ�û��ķ���
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
	 * ��ʾ�ֿ��model
	 * 
	 * @param message
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Repository[] showRepository(Message message) throws IOException, ClassNotFoundException {
		System.out.println("model����");
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();

		objis = new ObjectInputStream(socket.getInputStream());
		Repository[] rc = (Repository[]) objis.readObject();
		System.out.println("ͨ������");
		if (rc[0] == null) {
			System.out.println("��ҳʲô��û��");
			return null;
		}
		System.out.println("rc" + rc[0].getUserName());

		// objis.close();
		// objos.close();
		return rc;
	}

	/**
	 * �鿴ʣ�����صķ���
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
			System.out.println("ë��û��");
		}

		return fieldRemaining;
	}

	/**
	 * ʹ�ù���
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
	 * �۳����ܣ������۳���Ǯ
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
