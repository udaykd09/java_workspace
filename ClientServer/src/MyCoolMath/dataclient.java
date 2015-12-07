package MyCoolMath;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
class dataclient {

public static void read(int n){
	n = 10;
}

public static void main(String[] args){
	
	
		try	{
			Socket st = new Socket("localhost", 8889);
			
//			DataOutputStream d = new DataOutputStream(new FilterOutputStream(st.getOutputStream()));
//			InputStream is = st.getInputStream();
//			DataInputStream di = new DataInputStream(is);
			
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(st.getInputStream()));

			PrintWriter out;
			out = new PrintWriter(new OutputStreamWriter(st.getOutputStream()));
			byte[] b1 = "12308923493845".getBytes();
			
			String s = in.readLine().trim();
			System.out.println(s);
			byte[] b = s.getBytes();
			
			System.out.println(b.equals(b1));
			//System.out.println(bu);
//			String s1 = in.readLine().trim();
//			System.out.println(s1);
			out.flush();
			out.close();
			in.close();
			st.close();
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
	
}