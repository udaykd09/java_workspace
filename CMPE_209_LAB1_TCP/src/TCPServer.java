import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) {
		try{
		int port = Integer.parseInt(args[0]);
		String filename = args[1];
            
        //Create Server Socket and connect to client
		ServerSocket s = new ServerSocket(port);
		try(Socket incoming = s.accept()) {

            
            //Create Stream variables for data transfer in Client and Server
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						incoming.getInputStream()));

				PrintWriter out;
				out = new PrintWriter(new OutputStreamWriter(
						incoming.getOutputStream()));
				
				String username, password, strn;

				do {
                        //Read login details from Client
						username = in.readLine().trim();
						password = in.readLine().trim();
                    
                        //Verify details in Database
						strn = Login_DAO.isUserValid(username, password);
                    
                        // If details Valid
						if (strn.equals("Valid")) {
							out.println("Valid");
							out.flush();
							out.println(filename);
							out.flush();
							File my_file = new File(filename);
							FileInputStream f = new FileInputStream(my_file);
							DataOutputStream d = new DataOutputStream(incoming.getOutputStream());
							byte[] buf = new byte[(int)my_file.length()];							
							f.read(buf);
							d.write(buf);							
							f.close();
							d.close();							
							out.flush();
							break;
						}
                        // If details invalid
						else if (strn.equals("Warn")){
							out.println("Warn");
							out.flush();
						}
                        // If Account locked
						else if (strn.equals("Deny")){
							out.println("Deny");
							out.flush();
						}
					
						out.flush();
					}while(true);
				s.close();
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