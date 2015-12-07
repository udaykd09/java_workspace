import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		File abc = new File("client.txt");
		Socket s = new Socket("localhost", 8810);
		
		FileInputStream f = new FileInputStream(abc);
		
		DataOutputStream d = new DataOutputStream(s.getOutputStream());
		//DataInputStream i = new DataInputStream(s.getInputStream());
		
		//BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//PrintWriter o = new PrintWriter(s.getOutputStream(), true);
		
		byte[] buf = new byte[(int)abc.length()];

		
		f.read(buf);
		d.write(buf);
		
		f.close();
		d.close();
	}
}
