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
	private Socket me;
	private Socket them;
	private PrintWriter them_out;
	private BufferedReader me_in;
	
	EchoServer(Socket client_me, Socket client_them){
		this.me = client_me;
		this.them = client_them;
		try {
			this.them_out = new PrintWriter(this.them.getOutputStream(), true);
			this.me_in = new BufferedReader(
			        new InputStreamReader(this.me.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    public void run() {
    	String input;
    	try {
			while ((input = me_in.readLine()) != null) {
			    them_out.println(input);
			    if (input.equals("exit"))
			        break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			this.me.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	public static void main(String[] args) throws IOException {
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
			
			while(true) {
				sockets.add(serverSocket.accept());
    			System.out.println("client connected from " + sockets.get(sockets.size() - 1).getInetAddress());
    			if(sockets.size() == 2)
    			{
    				break;
    			}
    		} 

			EchoServer connection1 = (new EchoServer(sockets.get(0), sockets.get(1)));
			connection1.start();
			
			EchoServer connection2= (new EchoServer(sockets.get(1), sockets.get(0)));
			connection2.start();
		    while(true) {}
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
