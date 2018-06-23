package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.twicepack.ConfigViewBean;


public class ConfigModel {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ConfigModel() throws UnknownHostException, IOException{
		socket = new Socket("127.0.0.1",9999);
	}
	
	
	public ConfigViewBean configUser(Message message) throws IOException, ClassNotFoundException{
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		// ��message�����͹�ȥ������
		oos.writeObject(message);
		oos.flush();
		
		ois = new ObjectInputStream(socket.getInputStream());
		//���շ������������Ķ���
		ConfigViewBean userRegSucces = (ConfigViewBean)ois.readObject();	
		
		return userRegSucces;
		
	}
}
