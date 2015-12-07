package MyCoolMath;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
	public static void main(String[] args) {
	System.out.println("Server Side"); 
	System.out.println("Waiting for request on port no: 8888...");
	
	try {
		ServerSocket s = new ServerSocket(8889); 
		
		try (Socket incoming = s.accept()) {
			
			BufferedReader in; 
			in = new BufferedReader(new InputStreamReader(incoming.getInputStream())); 
        
			PrintWriter out;
			out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream())); 

			out.println("Write Here (QUIT for end)"); 
			out.flush(); 
			System.out.println("Listening..."); 

			for (;;) {
				String str = in.readLine(); 
                
				if (str == null)
					break; 
				else {
					out.println("Echo: " + str);  
                	out.flush();
                	System.out.println("Input [" + str	+"]");
                	
                	if (str.trim().equals("QUIT"))
                        break; 
                }
			}
		} 
	}
	catch (Exception e) {
		System.out.println("Error: " + e); 
	}

	System.out.println("Server stopped."); 
	}
}
