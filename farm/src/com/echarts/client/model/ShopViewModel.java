package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.twicepack.ShopViewBean;


/**
 * ShopVie的model层 用于客户端和服务器的信息收发
 */
public class ShopViewModel {

	private Socket socket;
	// 用对象序列化进行信息对象的收发
	private ObjectOutputStream objos;// 对象序列化
	private ObjectInputStream objis;// 对象反序列化
	
	public ShopViewModel() throws UnknownHostException, IOException{
		socket = new Socket("127.0.0.1",9999);
	}
	
	/**
	 * 客户端中商店页面打包数据进行收发的方法 
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
