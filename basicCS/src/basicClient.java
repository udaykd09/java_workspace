

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class basicClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost",8888);
		PrintWriter p = new PrintWriter(s.getOutputStream());
		BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedReader r1 = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(r.readLine());
		String line;
		do{
//			line = r.readLine();
//			if(line!= null)
				System.out.println(r.readLine());
			
			line = r1.readLine();
			p.println(line);
			p.flush();
		}while(!line.trim().equals("QUIT"));
	}
}
