import java.io.*;
import java.math.BigInteger;
import java.net.*;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Server {

	// AES encryption
    public static byte[] encrypt(byte[] key, byte[] value) {
        try {
        	//IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(value);

            return encrypted;
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return "-1".getBytes();
        }
        
    }

    public static byte[] decrypt(byte[] key, byte[] encrypted) {
        try {
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] original = cipher.doFinal(encrypted);

            return original;
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return "-1".getBytes();
            //ex.printStackTrace();
        }

    }

	// Secure Hash Algorithm (SHA-1)
    public static String mySHA(String file) {
    	try{

    		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(file);
      
            byte[] data = new byte[1024];
            int read = 0; 
            while ((read = fis.read(data)) != -1) {
                sha1.update(data, 0, read);
            };
            byte[] hashBytes = sha1.digest();
      
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hashBytes.length; i++) {
              sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
             
            String fileHash = sb.toString();
    		return fileHash;
    	 }
    	catch(Exception e){
    		System.err.println(e);
    		String s = "-1";
    		return s;
    	}
    }
    
    public static void mywrite(String s, DataOutputStream d){
    	try {
    		
			d.writeInt(s.length());
			d.flush();
			//Thread.currentThread().sleep(100);
			d.writeBytes(s);
			d.flush();
		} catch (IOException   e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String myread(DataInputStream di){
    	try {
    		
    		int length=di.readInt();
			byte[] data=new byte[length];
			//System.out.println("Length = "+ length);
			//Thread.currentThread().sleep(100);
			di.read(data,0,length);
			String s = new String(data);
			s = s.trim();
			return s;
		} catch (IOException   e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
    }
    
	// Main method
	public static void main(String[] args) {
		try {
			int port = 8889;// Integer.parseInt(args[0]);
			String filename = "testfile3.pdf";// args[1];
			ServerSocket s = new ServerSocket(port);
			System.out.println("Server");
			try (Socket incoming = s.accept()) {

//				BufferedReader in;
//				in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
//
//				PrintWriter out;
//				out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));

				DataOutputStream d = new DataOutputStream(incoming.getOutputStream());
				
				DataInputStream di = new DataInputStream(incoming.getInputStream());
				
				int count = 0;

				do {

					String ID, myKey, Ks1 = "";
					int Xs = 7809;
					/*-------------------Message 1-------------------*/
					// ID
					Thread.currentThread().sleep(4000);
					ID = myread(di);
					Thread.currentThread().sleep(100);
					
					System.out.println(ID);
					System.out.println("-------------------Username Validation-------------------");
					if (!(ID.equals("uday"))) {
						System.out.println("Username Mismatch");

						mywrite("Warn", d);
						
					} else {
						System.out.println("Username Match");

						mywrite("Valid", d);
						Thread.currentThread().sleep(100);
					}

					if (ID.equals("uday")) {
						// Pass
						myKey = "rainyday";
						for (int i = 0; i < myKey.length(); i++)
							Ks1 += Integer.toString((int) myKey.charAt(i));
						BigInteger Ks2_long = new BigInteger(Ks1);

						// P

						String p_str = myread(di);
						Thread.currentThread().sleep(100);
						BigInteger p = new BigInteger(p_str);

						// G

						String g_str = myread(di);
						Thread.currentThread().sleep(100);
						BigInteger g = new BigInteger(g_str);
						
						// Kc2 = E(Pass,g^Xcmodp)

						String Kc2_str = "";
						Kc2_str = myread(di);
						Thread.currentThread().sleep(1000);
						System.out.println("Kc2 length = "+Kc2_str.length());
						BigInteger Kc2 = new BigInteger(Kc2_str);
						
						System.out.println();
						System.out.println("-------------------Message 1-------------------");
						System.out.println(" p = " + p.toString() + ", g = " + g.toString() + ", Xs = " + Xs  + ", E(Pass, g^Xc.modp) = " + Kc2_str);

						// Kc3 = g^Xc = D(Pass,Kc2)
						BigInteger Kc3 = Kc2.xor(Ks2_long);

						// Ys = g^Xs.modp
						BigInteger big = g.pow(Xs);
						BigInteger Ys = (big).mod(p);

						/*-------------------Message 2-------------------*/
						// Ks2 = E(Pass,g^Xs.modp)
						BigInteger Ks2 = Ys.xor(Ks2_long);
						String Ks2_str = Ks2.toString();
						byte[] Ks2_byte = Ks2_str.getBytes();
						
						d.writeInt(Ks2_byte.length);
						d.flush();
						System.out.println("Ks2 length= "+Ks2_byte.length);
						Thread.currentThread().sleep(100);
						d.write(Ks2_byte);
						d.flush();
						Thread.currentThread().sleep(1000);

						// Kcs = g^(Xc*Xs).modp
						BigInteger Kcs = Kc3.pow(Xs).mod(p);

						// Pad Kcs to 128 bits
						byte[] Aes_key = Kcs.toByteArray();
						
						int byte_len = Aes_key.length;
				        if(byte_len<16){
				        	byte[] new_byte = new byte[16];
				        	for(int i=0;i<byte_len;i++){
				        		new_byte[i] = Aes_key[i];
				        	}
				        	for(int i=byte_len;i<16;i++){
				        		new_byte[i] = (byte)0;
				        	}
				        	Aes_key = new_byte;
				        }
				        if(byte_len>=16){
				        	Aes_key = Arrays.copyOfRange(Aes_key,0,16);
				        }

						// Choose Ns
						BigInteger Ns = new BigInteger("1185030000000000");
						byte[] Ns_byte = Ns.toByteArray();
						
						// Encrypt Ns using Kcs as key
						byte[] Kcs_Ns = new byte[16];
						Kcs_Ns = encrypt(Aes_key, Ns_byte);
						
						System.out.println("*Length = "+Kcs_Ns.length);
						d.writeInt(Kcs_Ns.length);
						d.flush();
						Thread.currentThread().sleep(100);
						d.write(Kcs_Ns);
						d.flush();
						Thread.currentThread().sleep(1000);
						
						String Kcs_Ns_str = new String(Kcs_Ns);
						System.out.println();
						System.out.println("----------------DH Key / Nounce----------------");
						System.out.println("Kcs = " + Kcs.toString() + ", Ns = " + Ns.toString());
						System.out.println();
						System.out.println("-------------------Message 2-------------------");
						System.out.println(" E(Pass,g^Xs.modp) = " + Ks2_str + ", E(Kas, Ns) = " + Kcs_Ns_str);
						/*-------------------Message 3-------------------*/
						int Kas_Ns_Na_len = di.readInt();
						System.out.println(Kas_Ns_Na_len);
						byte[] Kas_Ns_Na = new byte[Kas_Ns_Na_len];
						Thread.currentThread().sleep(100);
						if(Kas_Ns_Na_len>0) {
						    di.read(Kas_Ns_Na, 0, Kas_Ns_Na.length); // read the message
						}
						Thread.currentThread().sleep(1000);
						
						String Kas_Ns_Na_str = new String(Kas_Ns_Na);
						System.out.println();
						System.out.println("-------------------Message 3-------------------");
						System.out.println(" E(Kas, Ns||Na) = " +Kas_Ns_Na_str);
						byte[] Ns_Na = decrypt(Aes_key, Kas_Ns_Na);

						/*-------------------Message 4-------------------*/

						BigInteger Ns_Na_b = new BigInteger(Ns_Na);
						String Ns_Na_str = Ns_Na_b.toString();
						String Ns_Na_str_byte = new String(Ns_Na);
						
						if(Ns_Na_str_byte.equals("-1")){
							Ns_Na_str = "00000000000000000000000000000000";
						}
						String Ns_check = Ns_Na_str.substring(0, 16);
						System.out.println();
						System.out.println("-------------------Password Validation-------------------");
						if ((count >= 2) & (!(Ns_check.equals(Ns.toString())))) {
							System.out.println("Access Denied");

							mywrite("Deny", d);
							Thread.currentThread().sleep(100);
							break;
						}

						else if (!(Ns_check.equals(Ns.toString()))) {
							System.out.println("1. Na value Mismatch");
							System.out.println("2. Password Mismatch");

							mywrite("Warn",d);
							Thread.currentThread().sleep(100);
							count++;
						} else {
							System.out.println("1. Na Value Match");
							System.out.println("2. Password Match");

							mywrite("Valid",d);
							Thread.currentThread().sleep(100);

							String Na_str = Ns_Na_str.substring(16);
							BigInteger Na_big = new BigInteger(Na_str);
							byte[] Na = Na_big.toByteArray();
							byte[] Kcs_Na = new byte[16];
							Kcs_Na = encrypt(Aes_key, Na);
							d.writeInt(Kcs_Na.length);
							d.flush();
							Thread.currentThread().sleep(100);
							d.write(Kcs_Na);
							d.flush();
							Thread.currentThread().sleep(1000);
							
							System.out.println();
							String Kcs_Na_str = new String(Kcs_Na);
							System.out.println("-------------------Message 4-------------------");
							System.out.println(" E(Kas, Na) = " + Kcs_Na_str);

							mywrite(filename, d);
							Thread.currentThread().sleep(100);
						
							File my_file = new File(filename);
							FileInputStream f = new FileInputStream(my_file);
							
							byte[] buf = new byte[(int) my_file.length()];
							f.read(buf);
							System.out.println();
							System.out.println("-------------------Message 5-------------------");
							buf = encrypt(Aes_key, buf);
							String v = new String(buf);
							System.out.println();
//							System.out.println("a. E(Kas, File) : ");
//							System.out.println(v);
							String hash_file = mySHA(filename);
							System.out.println();
							System.out.println("b. H(file) : " + hash_file);

							// E(Kas, File) || H(file)
							d.writeInt(buf.length);
							d.flush();
							Thread.currentThread().sleep(100);
							d.write(buf);
							d.flush();
							Thread.currentThread().sleep(1000);
							
							f.close();
							mywrite(hash_file, d);
							Thread.currentThread().sleep(100);
							
							System.out.println("-----------------------------------------------");
							System.out.println("File Transfer Complete !");
							System.out.println("-----------------------------------------------");
							d.close();
							di.close();
							incoming.close();
							s.close();
							break;
						}
					}
				} while (true);
				s.close();
			}
		} catch (IndexOutOfBoundsException e1) {
			e1.printStackTrace();
			//System.err.println("Please provide port number & filename");
		} 
		catch (FileNotFoundException ef) {
			System.out.println("File Not found");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}