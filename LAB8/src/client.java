import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


public class client {

	public client(String string, int i) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws UnknownHostException, IOException{
		File filename = new File("client.txt");
		//Socket s = new Socket("localhost", 8811);
		String abc = "http://www.udaydungarwal.com/lab8/PHPJavaServer.php?filename="+filename;
		URL url = new URL(abc);
		//URLConnection conn = url.openConnection();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		FileInputStream f = new FileInputStream(filename);
		PrintStream p = new PrintStream(conn.getOutputStream(), true);
		int l = (int)filename.length();
		for (int i = 0; i < l; i++) {
			p.write(f.read());
		}
		
		p.close();
		
		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		System.out.println(r.readLine());
		r.close();
		f.close();

	}
}
