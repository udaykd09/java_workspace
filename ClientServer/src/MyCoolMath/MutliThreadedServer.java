package MyCoolMath;

import java.io.*;
import java.net.*;
import java.util.*;

class MutliThreadedServer {
	
	public static void main(String args[]) {
		System.out.println("Server Side"); 
		
		try { 
			ServerSocket server = new ServerSocket(8888);			
		while(true){
			try{
				Socket S = 	server.accept();
				ConnectionHandler handler = new ConnectionHandler(S);
				//new Thread(handler).start();
				handler.start();
			}
			catch(IOException ioe){
				System.out.println("Error");	
			}
		}	
	}
		catch(IOException ioe){
			System.out.println("Err");	
		}
}
}

class ConnectionHandler extends Thread {
	
	private Socket S;
	
	public ConnectionHandler(Socket S){
		this.S = S;
	}

@Override
	public void run() {
		try{
		this.setPriority(3);
		System.out.println("Set Thread Priority : ");
		System.out.println(Thread.currentThread());
		BufferedReader r = new BufferedReader(new InputStreamReader(S.getInputStream()));
		PrintWriter p = new PrintWriter(new OutputStreamWriter(S.getOutputStream()));
		p.println("Write Here :");
		p.flush();
	while(true){
		try{
		String input = r.readLine();
		System.out.println("Input : " + input);
		p.println("Echo :" + input);
		p.flush();
		if (input.trim().equals("QUIT"))
            break; 
	}
	catch(IOException ioee){
		System.out.println("Error3");
	}
	}
	}
	catch(IOException ioee){
			System.out.println("Error2");	
}
}
}
