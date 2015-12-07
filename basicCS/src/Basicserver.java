

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Basicserver {

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(8888);
		Socket s1 = s.accept();
		
		BufferedReader r = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		PrintWriter p = new PrintWriter(s1.getOutputStream());
		p.println("write here");
		p.flush();
		String a;
		for(;;){
			a=r.readLine();
			p.println("Echo" + a);
			p.flush();
			System.out.println("input="+a);
		}
			
	}
	
}
