
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Client_byte {

//AES encryption
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
        }

    }

// Secure Hash Algorithm (SHA-1)
    public static String mySHA(String file) {
    	try{
//    	 java.security.MessageDigest digest = null;
//    	 digest = java.security.MessageDigest.getInstance("SHA-1");
//    	 digest.reset();
//    	 digest.update(x.getBytes("UTF-8"));
//    	 digest.update(x.getBytes("UTF-8"));
//    	 return digest.digest();
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
			//System.out.println("Length = " +length);
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
			int port = 8888;// Integer.parseInt(args[0]);

			Socket st = new Socket("localhost", port);

			//BufferedReader rd = new BufferedReader(new InputStreamReader(st.getInputStream()));
			Scanner conn = new Scanner(System.in);
			//PrintWriter wr = new PrintWriter(st.getOutputStream(), true);
			DataOutputStream d = new DataOutputStream(st.getOutputStream());
			
			DataInputStream di = new DataInputStream(st.getInputStream());
			
			
			
			System.out.println("Client");


			do {
				String ID, myKey, Kc1 = "";
				BigInteger Yc;
				int Xc;
				String userCheck = "none";
				
				String username, password;
				
				// P
				BigInteger p = new BigInteger("197221152031991558322935568090317202983");
				
				// G
				BigInteger g = new BigInteger("2");
				
				// Xc
				Xc = 233;
				System.out.println("-------------------Login Details-------------------");
				System.out.print("	Username :");
				username = conn.nextLine();

				System.out.print("	Password :");
				password = conn.nextLine();

				/*-------------------Message 1-------------------*/
				// ID
				ID = username;
				Thread.currentThread().sleep(1000);
				mywrite(ID,d);
				Thread.currentThread().sleep(100);

				userCheck = myread(di);
				
				System.out.println();
				System.out.println("-------------------Username Validation-------------------");
				if (userCheck.equals("Warn")) {
					System.out.println("	Username Mismatch");

					userCheck = myread(di);
					Thread.currentThread().sleep(100);
				}

				if (!(userCheck.equals("Warn"))) {
					System.out.println("	Username Match");
					// Shared Key and convert it to Ascii
					myKey = password;
					for (int i = 0; i < myKey.length(); i++)
						Kc1 += Integer.toString((int) myKey.charAt(i));

					// P

					mywrite(p.toString(), d);
					Thread.currentThread().sleep(100);
					//wr.println(p.toString());

					// G

					mywrite(g.toString(), d);
					Thread.currentThread().sleep(100);
					//wr.println(g.toString());

					// Yc = g^Xc.modp
					BigInteger big = g.pow(Xc);
					Yc = (big).mod(p);

					// Convert Ascii to long
					BigInteger Kc2_long = new BigInteger(Kc1);

					// Kc2 = E(Pass, Yc), here E is xor
					// Kc2 = E(KUs, Yc), here E is RSA encryption
					ObjectInputStream inputStream = null;
					inputStream = new ObjectInputStream(new FileInputStream(Server_RSA.PUBLIC_KEY_FILE));
					PublicKey publicKey = (PublicKey) inputStream.readObject();
					byte[] Kc2 = Server_RSA.encrypt(Yc.toString().getBytes(), publicKey);
					
					d.writeInt(Kc2.length);
					d.flush();
					Thread.currentThread().sleep(100);
					d.write(Kc2);
					d.flush();
					Thread.currentThread().sleep(1000);
					
					
					String Kc2_str = Kc2.toString();
					System.out.println();
					System.out.println("-------------------Message 1-------------------");
					System.out.println(" p = " + p.toString() + ", g = " + g.toString() + ", Xc = " + Xc);
					System.out.println(", E(Pass, g^Xc.modp) = " + Kc2_str);
					

					

					/*-------------------Message 2-------------------*/

					// Ks2 = E(Pass,g^Xsmodp))

					//String Ks2_str = "";
					int Ks2_len=0;
					Ks2_len = di.readInt();
					System.out.println("Ks2_length = "+Ks2_len);
					byte[] Ks2_byte = new byte[Ks2_len];
					Thread.currentThread().sleep(100);
					if(Ks2_len>0) {
					    di.read(Ks2_byte); // read the message
					}
					Thread.currentThread().sleep(1000);
					
					// Kc3 = g^Xc = D(KPs,Kc2)
					// Decrypt Kc2 using the private key.
					inputStream = new ObjectInputStream(new FileInputStream(Server_RSA.PRIVATE_KEY_FILE));
					PrivateKey privateKey = (PrivateKey) inputStream.readObject();
					byte[] Ks3_byte = Server_RSA.decrypt(Ks2_byte, privateKey);
					
					String Ks3_str = new String(Ks3_byte);
					BigInteger Ks3 = new BigInteger(Ks3_str);

					// Kcs = g^(Xs*Xc).modp
					BigInteger Kcs = Ks3.pow(Xc).mod(p);

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
			        
			        System.out.println();
			        System.out.println("----------------DH Key ----------------");
			        System.out.println("Kcs = " + Kcs.toString());
			        System.out.println();
			        
					// Decrypt Ns using Kcs as key
					int Kcs_Ns_len=0;
					Kcs_Ns_len = di.readInt();
					System.out.println("Kcs_Ns = "+Kcs_Ns_len);
					byte[] Kcs_Ns_byte = new byte[Kcs_Ns_len];
					Thread.currentThread().sleep(100);
					if(Kcs_Ns_len>0) {
					    di.read(Kcs_Ns_byte); // read the message
					}
					Thread.currentThread().sleep(1000);
					
					System.out.println("Length Kcs_Ns =" + Kcs_Ns_byte.length);
					String Kcs_Ns_str = new String(Kcs_Ns_byte);
					byte[] Ns = new byte[16];
					Ns = decrypt(Aes_key, Kcs_Ns_byte);
					BigInteger Ns_b = new BigInteger(Ns);
					System.out.println();
					System.out.println("----------------Nounce ----------------");
					// Choose Na
					BigInteger Na = new BigInteger("9876543210987654");
					System.out.println("Ns = " + Ns_b.toString());
					System.out.println("Na = " + Na.toString());
					System.out.println();
					System.out.println("-------------------Message 2-------------------");
					System.out.println(" E(KUa,g^Xs.modp) = " + Ks2_byte.toString() + ", E(Kas, Ns) " + Kcs_Ns_str);

					/*-------------------Message 3-------------------*/


					String Ns_Na_str = Ns_b.toString() + Na.toString();
					BigInteger Ns_Na_big = new BigInteger(Ns_Na_str);
					byte[] Kcs_Ns_Na = new byte[16];
					Kcs_Ns_Na = encrypt(Aes_key, Ns_Na_big.toByteArray());
					d.writeInt(Kcs_Ns_Na.length);
					d.flush();
					Thread.currentThread().sleep(100);
					d.write(Kcs_Ns_Na);
					d.flush();
					Thread.currentThread().sleep(1000);
					
					String Kcs_Ns_Na_str = new String(Kcs_Ns_Na); 
					System.out.println();
					System.out.println("-------------------Message 3-------------------");
					System.out.println(" E(Kas, Ns||Na) = " + Kcs_Ns_Na_str);
					/*-------------------Message 4-------------------*/

					String Pass_check = myread(di);
					Thread.currentThread().sleep(100);
					
					if (Pass_check.equals("Deny")) {
						System.out.println("-------------------Password Validation-------------------");
						System.out.println("Access Denied");
						break;
					}

					else if (Pass_check.equals("Warn")) {
						System.out.println("-------------------Password Validation-------------------");
						System.out.println("1. Na value Mismatch");
						System.out.println("2. Password Mismatch");
					} else if (Pass_check.equals("Valid")) {
						// Decrypt Na using Kcs as key
						int lengthb=di.readInt();
						System.out.println("Length Kcs_Na = "+lengthb);
						byte[] Kcs_Na = new byte[lengthb];
						Thread.currentThread().sleep(100);
						di.read(Kcs_Na,0,lengthb);
						Thread.currentThread().sleep(1000);
						
						byte[] Na_check = decrypt(Aes_key, Kcs_Na);
						System.out.println();
						String Kcs_Na_str = new String(Kcs_Na);
						System.out.println("-------------------Message 4-------------------");
						System.out.println(" E(Kas, Na) = " + Kcs_Na);
							System.out.println();
							System.out.println("-------------------Password Validation-------------------");
							System.out.println("1. Na Value Match");
							System.out.println("2. Password Match");

							String filename = myread(di);
							Thread.currentThread().sleep(100);
							
							File abc = new File("my.txt");
							FileOutputStream f = new FileOutputStream(abc);

							lengthb = di.readInt();
							byte[] fil = new byte[lengthb];
							Thread.currentThread().sleep(100);
							di.read(fil,0,lengthb);
							Thread.currentThread().sleep(1000);
							
							String fil_str = new String(fil);
							System.out.println();
							System.out.println("-------------------Message 5-------------------");
							System.out.println("a. E(Kas, File) : "+fil_str);
							System.out.println(fil);

							String Hash = myread(di);
							Thread.currentThread().sleep(100);
							
							System.out.println();
							System.out.println("b. H(file) :" + Hash);
							fil = decrypt(Aes_key, fil);							
							
							f.write(fil);
							f.flush();
							f.close();
							d.close();
							di.close();
							st.close();
							System.out.println("-----------------------------------------------");
							System.out.println();
							System.out.println("File Transfer Complete");
							System.out.println();
							System.out.println("-------------------File Integrity Check -------------------");
							String Hash_check = mySHA("my.txt");
							if(Hash_check.equals(Hash)){
								System.out.println();
								System.out.println("SHA-1 Hash matched");
								break;
							}
							else{
								System.out.println();
								System.out.println("SHA-1 Hash not matched");
							}
					}
				}

			} while (true);
			
			st.close();

		} catch (

		IndexOutOfBoundsException err)

		{
			System.err.println("Please provide port number.");
		} 
		catch (FileNotFoundException ef) {
			ef.printStackTrace();
		}
		catch (

		Exception err)

		{
			err.printStackTrace();
		}
	}

}