import java.io.*;
import java.net.*;


public class server {
	
	public static void main(String[] args) {
	try{	
		File abc = new File("server.txt");
		ServerSocket s= new ServerSocket(8810);
		Socket s1 = s.accept();
		System.out.println("connected to a client");
		//BufferedReader r1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		
		BufferedInputStream r = new BufferedInputStream(s1.getInputStream());
		PrintWriter o = new PrintWriter(new OutputStreamWriter(s1.getOutputStream()));
		int a;
		FileOutputStream f = new FileOutputStream(abc);
		while((a = r.read())!=-1){
						f.write(a);
						o.println("Echo :" + a);
		}
		
		f.flush();
		f.close();
		System.out.println("file data input done");
		o.close();
		o.flush();
	}
	
	catch(IOException e){
		System.out.println("error1");
	}
	
	}
	
}
