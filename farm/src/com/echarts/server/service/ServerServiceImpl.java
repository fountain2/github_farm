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
			//��ȡ�ͻ��˵�����
			objis = new ObjectInputStream(socket.getInputStream());
			message = (Message) objis.readObject();
			
			//����������ͻ��˵������
			objos = new ObjectOutputStream(socket.getOutputStream());
			
			String pageFlag = message.getPageFlag();
			
			switch (pageFlag) {
			
				//��¼ҳ��
			case IOperation.LOGINVIEW:
				break;
				//ע��ҳ��
			case IOperation.REGISTERVIEW:
				break;
				//ũ��ҳ��
			case IOperation.FARMVIEW:
				FarmViewBean farmViewBean = message.getFarmViewBean();
				//���в���
				
				break;
				//����ũ��ҳ��
			case IOperation.OTHERFARMVIEW:
				break;
				//�ֿ�ҳ��
			case IOperation.REPOSITORYVIEW:
				break;
				//�̵�ҳ��
			case IOperation.SHOPVIEW:
				break;
				//����ҳ��
			case IOperation.CONFIGVIEW:
				break;
			}
			
			
		} catch (Exception e) {
		}
	}
	
	
}
