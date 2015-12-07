	package MyCoolMath;

	import java.io.*;
	import java.net.*;
	class EchoClient2 {


	public static void main(String[] args)
	{
		String userInput;
			try	{
				Socket st = new Socket("localhost", 8888);
				
				BufferedReader rd = new BufferedReader(new InputStreamReader(st.getInputStream()));
				PrintWriter wr = new PrintWriter(st.getOutputStream(), true);
				BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));

				System.out.println("Client End");
				System.out.println("Connected to the port no: 8888...");
	            
				String line;
				
				do {
					line = rd.readLine();
					if ( line != null )
						System.out.println(line);
	                 
					line = conn.readLine();
					wr.println(line);
				} while ( !line.trim().equals("QUIT") );
			}
			catch (Exception err) {
				System.err.println(err);
			}
		}
		
}
