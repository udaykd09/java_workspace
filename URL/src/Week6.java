import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Week6 {

	public static void main(String[] args) {
		try{
			
			//1. Create an URL object with http as protocol and www.yahoo.com as resource name.
			URL u1 = new URL("http://www.yahoo.com/index.html");
			
			//2. Parse the newly created URL object and print out
			System.out.println("----------------------URL-Parameters---------------------");
			System.out.println("Protocol :" + u1.getProtocol());
			System.out.println("Host :" + u1.getHost());
			System.out.println("Filename :" + u1.getFile());
			System.out.println("Port :" + u1.getPort());
			System.out.println("Reference :" + u1.getRef());
			System.out.println("---------------------Input-Stream------------------------");
			
			//3. Read directly from the URL object and print out all the contents from the input stream to the display
			URLConnection u2 = u1.openConnection();
			BufferedReader br1 = new BufferedReader(new InputStreamReader(u2.getInputStream()));
			String inputLine;
			while ((inputLine = br1.readLine()) != null)
			{
				System.out.println(inputLine);
			}
			br1.close();
			System.out.println("---------------------------------------------------------");
		}
		
		catch (MalformedURLException malE)
		{
			System.out.println("MalformedURLException: " + malE);
		}
		
		catch (IOException ioE)
		{
			System.out.println("IOException: " + ioE);
		}			
		
	}

}
