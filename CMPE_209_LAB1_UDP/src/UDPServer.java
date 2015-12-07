import java.io.*;
import java.net.*;

public class UDPServer {
	public static void main(String[] args) {
		try{
		int port = Integer.parseInt(args[0]);
		String filename = args[1];
		
		//InetAddress IPAddress = new InetSocketAddress("0.0.0.0",port);  
		DatagramSocket serverSocket = new DatagramSocket(port);
		byte[] receiveData = new byte[64];
		byte[] sendData = new byte[64];
		while(true)
		{                   
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);                   
			serverSocket.receive(receivePacket);                   
			String username = new String( receivePacket.getData()).trim();
			
			DatagramPacket receivePacket1 = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket1);
			String password = new String( receivePacket1.getData()).trim();
			
			InetAddress IPAddress = receivePacket.getAddress();                   
			int port1 = receivePacket.getPort();                                      
					
						String strn = Login_DAO.isUserValid(username, password);
						if (strn.equals("Valid")) {
							sendData = "Valid".getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port1);
							serverSocket.send(sendPacket);
							
							byte[] sendData1 = new byte[64];
							sendData1 = filename.getBytes();
							DatagramPacket sendPacketf1 = new DatagramPacket(sendData1, sendData1.length, IPAddress, port1);
							serverSocket.send(sendPacketf1);
							
							File my_file = new File(filename);
							FileInputStream f = new FileInputStream(my_file);
							
							byte[] sendData2 = new byte[64];
							sendData2 = String.valueOf(my_file.length()).getBytes();
							DatagramPacket sendPacketf2 = new DatagramPacket(sendData2, sendData2.length, IPAddress, port1);
							serverSocket.send(sendPacketf2);
							
							byte[] buf = new byte[(int)my_file.length()];							
							f.read(buf);
							DatagramPacket sendPacketf3 = new DatagramPacket(buf, buf.length, IPAddress, port1);
							serverSocket.send(sendPacketf3);							
							f.close();
						}
						else if (strn.equals("Warn")){
							sendData = "Warn".getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port1);
							serverSocket.send(sendPacket);
						}
						else if (strn.equals("Deny")){
							sendData = "Deny".getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port1);
							serverSocket.send(sendPacket);
						}
}
}
catch(IndexOutOfBoundsException e1){
		System.err.println("Please provide port number & filename");
	}
			catch (Exception e) {
			e.printStackTrace();
		}

	}
}