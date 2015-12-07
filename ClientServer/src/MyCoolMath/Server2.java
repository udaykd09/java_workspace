package MyCoolMath;

import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

	public static void main(String[] args) {
		System.out.println("Server2");
		try{
		ServerSocket S = new ServerSocket(9998);
			Socket S1 = S.accept();
			System.out.println(S1.isConnected());
		}
catch(Exception e){
	e.printStackTrace();
}
	}

}
