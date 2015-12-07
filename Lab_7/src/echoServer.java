
//Import the necessary files
import java.io.*;
import java.net.*;

//Server Class
public class echoServer {
	public static void main(String[] args) {
		try	{
			//Create one port.
			ServerSocket serSock = new ServerSocket(8891);
			
			PrintWriter out;
			BufferedInputStream bis;
			Socket incoming;
			
			while (true) {
				
				//Socket connection from client
				incoming = serSock.accept();
				System.out.println("Client connected. Waiting for request.");
				
				//Write the content
				out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));
				
				//Read the content
				bis =new BufferedInputStream(incoming.getInputStream());
				
				//Identifying the file extension
				int li_i, li_k, li_l=0;
				char lc_file[]=new char[10];
				
				for (li_k=0;li_k<lc_file.length;li_k++) {
					lc_file[li_k]=(char)bis.read();
					
					if(lc_file[li_k]=='#') {
						li_l=li_k;
						break;
					}
				}
				
				
				String fileExt = new String(lc_file);
				fileExt = fileExt.substring(0,li_l);

				// Reading the File Extenstion End
				// Saving a file from the Client on the server.Start
				//Create a File to copy the Contents from the Client
				
				FileOutputStream fos =new FileOutputStream("fromClient." + fileExt);
				
				while ((li_i=bis.read())!=-1) {
					fos.write(li_i);
				}
				
				bis.close();
				fos.flush();
				fos.close();
				
				// Saving a file from the Client on the server.End
				System.out.println("File Transfered.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		
		System.out.println("No request from client. Shutting down.");
	}
}