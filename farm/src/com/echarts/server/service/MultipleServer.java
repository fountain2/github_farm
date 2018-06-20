package com.echarts.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultipleServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			
			while(true){
				Socket socket = serverSocket.accept();
				
				ServerServiceImpl serverService = new ServerServiceImpl(socket);
				serverService.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}