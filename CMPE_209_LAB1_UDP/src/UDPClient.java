
import java.io.*;
import java.net.*;

class UDPClient {

	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);

			BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
			InetAddress IPAddress = InetAddress.getByName("localhost");
			DatagramSocket clientSocket = new DatagramSocket();
			byte[] sendData = new byte[64];
			byte[] receiveData = new byte[64];
			String line1, username, password;

			do {
				System.out.print("Username :");
				username = conn.readLine();
				sendData = username.getBytes();
				DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
				clientSocket.send(sendPacket1);
				System.out.print("Password :");
				password = conn.readLine();
				sendData = password.getBytes();
				DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
				clientSocket.send(sendPacket2);
				DatagramPacket receivePacket1 = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket1);
				line1 = new String(receivePacket1.getData()).trim();
				if (line1.equals("Valid")) {
					 DatagramPacket receivePacket2 = new DatagramPacket(receiveData, receiveData.length);
					 clientSocket.receive(receivePacket2);
					 String fileName = new String(receivePacket2.getData()).trim();
					 
					 byte[] receiveData1 = new byte[64];
					 DatagramPacket receivePacket3 = new DatagramPacket(receiveData1, receiveData1.length);
					 clientSocket.receive(receivePacket3);
					 String check =new String(receivePacket3.getData()).trim();
					 int fileLength = Integer.parseInt(check);
					 
					 byte[] fileData = new byte[fileLength];
					 DatagramPacket receivePacket4 = new DatagramPacket(fileData, fileLength);
					 clientSocket.receive(receivePacket4);
					 fileData = receivePacket4.getData();
					 
					 File abc = new File(fileName);
					 FileOutputStream f = new FileOutputStream(abc);
					 f.write(fileData);
					 f.flush();
					 f.close();
					System.out.println("File Transferred!");
					break;
				} else if (line1.equals("Warn")) {
					System.out.println("Incorrect details, Please try again!");
				} else if (line1.equals("Deny")) {
					System.out.println("Access Denied");
					break;
				}
			} while (true);
			clientSocket.close();

		} catch (IndexOutOfBoundsException err) {
			System.err.println("Please provide port number.");
		} catch (Exception err1) {
			err1.printStackTrace();
		}
	}

}