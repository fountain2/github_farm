package com.echarts.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.echarts.client.bean.Message;
import com.echarts.client.bean.User;

public class UserModel {
	private Socket socket;
	
	public UserModel() throws UnknownHostException, IOException {
		socket =new Socket("127.0.0.1", 9999);
	}
	
	public User queryUser(Message message) throws IOException, ClassNotFoundException {
		ObjectOutputStream objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		ObjectInputStream objis = new ObjectInputStream(socket.getInputStream());
		User user = (User)objis.readObject();
		return user;
	}

	public void registerUser(Message message2) throws IOException {
		ObjectOutputStream objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message2);
		objos.flush();
	}
	
	public User login(Message message) throws IOException, ClassNotFoundException {
		ObjectOutputStream objos = new ObjectOutputStream(socket.getOutputStream());
		objos.writeObject(message);
		objos.flush();
		
		ObjectInputStream objis = new ObjectInputStream(socket.getInputStream());
		User user = (User)objis.readObject();
		return user;
	}
}
