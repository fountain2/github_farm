package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.twicepack.ShopViewBean;


/**
 * ShopVie��model�� ���ڿͻ��˺ͷ���������Ϣ�շ�
 */
public class ShopViewModel {

	private Socket socket;
	// �ö������л�������Ϣ������շ�
	private ObjectOutputStream objos;// �������л�
	private ObjectInputStream objis;// �������л�
	
	public ShopViewModel() throws UnknownHostException, IOException{
		socket = new Socket("127.0.0.1",9999);
	}
	
	/**
	 * �ͻ������̵�ҳ�������ݽ����շ��ķ��� 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public ShopViewBean shopViewMessage(Message message) throws IOException, ClassNotFoundException{
		objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		objis = new ObjectInputStream(socket.getInputStream());
		ShopViewBean backMessage = (ShopViewBean) objis.readObject();
		return backMessage;
	}
}
