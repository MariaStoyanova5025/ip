package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer extends Thread {
	ServerSocket serverSocket;
	public ArrayList<Socket> sockets = new ArrayList<Socket>();
	public ArrayList<PrintWriter> out = new ArrayList<PrintWriter>();
	public ArrayList<BufferedReader> in = new ArrayList<BufferedReader>();
	
	EchoServer(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
		try {
			out.add(new PrintWriter(sockets.get(0).getOutputStream(), true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			out.add(new PrintWriter(sockets.get(1).getOutputStream(), true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in.add(new BufferedReader(new InputStreamReader(sockets.get(0).getInputStream())));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in.add(new BufferedReader(new InputStreamReader(sockets.get(1).getInputStream())));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    public void run() {
    	
    		
		try {
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
    }
    
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
			EchoServer server = (new EchoServer(serverSocket));
			ArrayList<PrintWriter> out = new ArrayList<PrintWriter>();
			ArrayList<BufferedReader> in = new ArrayList<BufferedReader>();
			while(true) {
				server.sockets.add(serverSocket.accept());
    			System.out.println("client connected from " + server.sockets.get(server.sockets.size() - 1).getInetAddress());
    			if(server.sockets.size() == 2)
    			{
    				break;
    			}
    		} 
			    
			String inputLine;
			server.start();
		    
		    
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			System.out.println("Server closed");
		}
	}

}
