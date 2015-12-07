package coolMath;

import java.io.*;
import java.net.*;


public class JavaPHPClient {
	public String httpupload(String filePath) {
		try {
			//BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
			// reads line from the console
            //String filePath,fileName;
				
//			do {
//				System.out.println("Enter the full file path or quit to close");
//				
//				filePath = in.readLine();//Reads the File path to upload
//				
				if(filePath!=null) {
					filePath=filePath.replaceAll("\\\\","/");
					
					String fileName=filePath.substring(filePath.lastIndexOf("/")+1);//Extracts the FileName
					File file =new File(filePath);
					
					if (file.exists()) {//Check the File Existence
                        
						String url="http://udaykdungarwal.com/Woohoo/PHPJavaServer.php?fileName="+fileName;
						URL serverURL = new URL(url);
						//URLConnection conn = url.openConnection();
						HttpURLConnection conn = (HttpURLConnection) serverURL.openConnection();// Opens a HTTPURLConnection
						conn.setDoOutput(true);// Sets the connection to for Output
						conn.setDoInput(true);// Sets the connection to for Output
						conn.setRequestMethod("POST");// Sets the request method to Post
						int flength=(int)file.length();//gets the file length
						FileInputStream fis =new FileInputStream(file);// opens input stream to the file to read the contents
						PrintStream wr = new PrintStream(conn.getOutputStream(), true);//opens a output stream to write into 
						for(int i=0;i<flength;i++) {
							wr.write(fis.read());
						}
						
						wr.close();
						BufferedReader bget=new BufferedReader(new InputStreamReader(conn.getInputStream()));//To read response
						String s = null;
						String s1 = "";
						while ((s = bget.readLine()) != null) {
							s1 += s; 
        				}
						
						bget.close();
						fis.close();
						return fileName + ": " + s1;		
					} 
					else {
						return "Filepath is incorrect";
					}
					}
				else {
					return "NULL filepath";
				}						
		} 
		catch (Exception err) {
			return "Exception occured";
		}
    }
}