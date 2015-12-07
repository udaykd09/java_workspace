
import java.io.*;
import java.net.*;

class TCPClient {

	public static void main(String[] args) {
		try {
            // Set Port number from Arguments
		int port = Integer.parseInt(args[0]);
			
            // Create Socket
			Socket st = new Socket("localhost", port);
            
            // Create Stream Variables for data transmission between Client and Server
			BufferedReader rd = new BufferedReader(new InputStreamReader(st.getInputStream()));
			BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter wr = new PrintWriter(st.getOutputStream(), true);

			System.out.println("Client End");
			System.out.println("Connected to the port no: 8888...");
			String line1,username,password;
			
			do{
                //Get Username and Password from User to login
				System.out.print("Username :");
				username = conn.readLine();
                
                //Send Username and Password to Server
				wr.println(username);
				System.out.print("Password :");
				password = conn.readLine();
				wr.println(password);
				wr.flush();
                
                //Read the Responce from Server
				line1 = rd.readLine();
                
                //If Responce is Valid i.e. Credentials are valid
				if(line1.trim().equals("Valid")){
                    
                    // Start File Transfer
					File abc = new File(rd.readLine().trim());
					FileOutputStream f = new FileOutputStream(abc);
					int a;
					while((a = rd.read())!=-1){
									f.write(a);
					}
					f.flush();
					f.close();
					System.out.println("File Transferred!");
					break;
				}
                
                //If Responce is Warn i.e. Credentials are invalid
				else if(line1.trim().equals("Warn")){					
					System.out.println("Incorrect details, Please try again!");
				}
                
                //If Responce is Deny i.e. Account is Locked
				else if(line1.trim().equals("Deny")){
					System.out.println("Access Denied");
					 break;
				}
				}while(true);
			wr.flush();
			st.close();

		}catch (IndexOutOfBoundsException err) {
			System.err.println("Please provide port number.");
		} 
		catch (Exception err) {
			System.err.println(err);
		}
	}

}