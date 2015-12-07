package MyCoolMath;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

public class dataServer {
	public static void main(String[] args) {
	System.out.println("Server Side"); 
	System.out.println("Waiting for request on port no: 8888...");
	try{
		ServerSocket s = new ServerSocket(8889); 
		Socket incoming = s.accept();
		
//			DataOutputStream d = new DataOutputStream(new FilterOutputStream(incoming.getOutputStream()));
//			InputStream is = incoming.getInputStream();
//			DataInputStream di = new DataInputStream(is);	

			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));

			PrintWriter out;
			out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));
			
			//System.out.println(incoming2.getInetAddress());
			System.out.println(incoming.getInetAddress());
			
			byte[] b = "12308923493845".getBytes();
			String asa = new String(b);
			out.println(asa);			
			out.flush();
			out.close();
			in.close();
			incoming.close();
			s.close();
//			out.println("yes");
//			out.flush();
			
	}
	catch (Exception e) {
		System.out.println("Error: " + e); 
	}

	System.out.println("Server stopped."); 
	}
}
