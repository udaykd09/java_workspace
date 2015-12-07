
//Import all necessary files
import java.io.*;
import java.net.*;

//Client
public class echoClient {
	public static void main(String[] args) {
		try	{
			//Read from the command line
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String filePath;

            do {
            	//Open socket and define port
				Socket clientSock = new Socket("localhost", 8891);
				System.out.println("Give the full path of the file. (Quit for terminate)");

				//Store path name
				filePath = in.readLine();

				//File object for file
				File file =new File(filePath);

				//Validate (check whether file exists or not)
				if (file.exists()) {
					
					//Write to server
					PrintStream printWr = new PrintStream(clientSock.getOutputStream(), true);
					
					//Send the extension along with delimiter (*) for EOF 
					printWr.print(filePath.substring(filePath.lastIndexOf("."))+"*");

					//Create object to Read content of file
					FileInputStream fis =new FileInputStream(file);

					//File size
					byte lb_b[]=new byte[(int)file.length()];

					//Read every content of file
					fis.read(lb_b);
					
					//Write
					printWr.write(lb_b);
					
					//Close
					printWr.close();
					fis.close();
				} else
					System.out.println("Give the full path of the file. (Quit for terminate)");

			} while ( !filePath.trim().equalsIgnoreCase(""
					+ "") );
		} catch (Exception err) {
			System.err.println(err);
		}
	}    
}