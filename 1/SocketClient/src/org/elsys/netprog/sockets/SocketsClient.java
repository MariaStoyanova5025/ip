package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketsClient extends Thread {
	BufferedReader in;
	SocketsClient(BufferedReader in){
		this.in = in;
	}
    public void run() {
    	try {
    		String line;
    		while ((line = in.readLine()) != null) {
			    System.out.println(line);
			    if (line.equals("exit"))
			        break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			    echoSocket = new Socket("localhost", 10001);
			    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(
			            new InputStreamReader(echoSocket.getInputStream()));
			    BufferedReader stdIn = new BufferedReader(
			            new InputStreamReader(System.in));
			    
			    String userInput;
			    (new SocketsClient(in)).start();
			    while ((userInput = stdIn.readLine()) != null) {
			        out.println(userInput);
			    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				echoSocket.close();
			}
		}
	}

}
