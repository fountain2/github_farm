package com.echarts.server.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.twicepack.FarmViewBean;
import com.echarts.util.IOperation;

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
				break;
				//注册页面
			case IOperation.REGISTERVIEW:
				break;
				//农场页面
			case IOperation.FARMVIEW:
				FarmViewBean farmViewBean = message.getFarmViewBean();
				//进行操作
				
				break;
				//其他农场页面
			case IOperation.OTHERFARMVIEW:
				break;
				//仓库页面
			case IOperation.REPOSITORYVIEW:
				break;
				//商店页面
			case IOperation.SHOPVIEW:
				break;
				//设置页面
			case IOperation.CONFIGVIEW:
				break;
			}
			
			
		} catch (Exception e) {
		}
	}
	
	
}
