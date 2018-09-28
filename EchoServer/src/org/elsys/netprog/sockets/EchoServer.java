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
	
	EchoServer(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
    public void run() {
    	try {
    		while(true) {
    			sockets.add(serverSocket.accept());
    			System.out.println("client connected from " + sockets.get(sockets.size() - 1).getInetAddress());
    		} 
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
				System.out.println(server.sockets.size());
				if (server.sockets.size() == 2) {
				out.add(new PrintWriter(server.sockets.get(0).getOutputStream(), true));
				out.add(new PrintWriter(server.sockets.get(1).getOutputStream(), true));
				in.add(new BufferedReader(new InputStreamReader(server.sockets.get(0).getInputStream())));
				in.add(new BufferedReader(new InputStreamReader(server.sockets.get(1).getInputStream())));
				break;
				}
			}
			    
			String inputLine;
			server.start();
		    while(true)
		    {
		    	
		    	if((inputLine = in.get(0).readLine()) != null){
		    		out.get(1).println(inputLine);
		    		if (inputLine.equals("exit"))
		    		{
		    			break;
		    		}
			            
		    	}
		    	if((inputLine = in.get(1).readLine()) != null){
		    		out.get(0).println(inputLine);
		    		if (inputLine.equals("exit"))
		    		{
		    			 break;
		    		}
			           
		    	}
		    }
		    
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
