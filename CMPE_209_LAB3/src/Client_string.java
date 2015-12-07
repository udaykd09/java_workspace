
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Client_string {

	// AES encryption
	public static String encrypt(String key, String value) {
		try {
			// IvParameterSpec iv = new
			// IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			// System.out.println("encrypted string: "
			// + Base64.encodeBase64String(encrypted));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			return "ERROR00000000000";
		}

	}

	// AES decryption
	public static String decrypt(String key, String encrypted) {
		try {
			// IvParameterSpec iv = new
			// IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			return "ERROR00000000000";
		}
	}

	// Secure Hash Algorithm (SHA-1)
	public static String mySHA(String file) {
		try {
			// java.security.MessageDigest digest = null;
			// digest = java.security.MessageDigest.getInstance("SHA-1");
			// digest.reset();
			// digest.update(x.getBytes("UTF-8"));
			// digest.update(x.getBytes("UTF-8"));
			// return digest.digest();
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			FileInputStream fis = new FileInputStream(file);

			byte[] data = new byte[1024];
			int read = 0;
			while ((read = fis.read(data)) != -1) {
				sha1.update(data, 0, read);
			}
			;
			byte[] hashBytes = sha1.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hashBytes.length; i++) {
				sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			String fileHash = sb.toString();
			return fileHash;
		} catch (Exception e) {
			System.err.println(e);
			String s = "-1";
			return s;
		}
	}

	// Main method
	public static void main(String[] args) {
		try {
			int port = 8888;// Integer.parseInt(args[0]);

			Socket st = new Socket("localhost", port);

			BufferedReader rd = new BufferedReader(new InputStreamReader(st.getInputStream()));
			BufferedReader conn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter wr = new PrintWriter(st.getOutputStream(), true);

			System.out.println("Client");

			do {
				String ID, myKey, Kc1 = "";
				BigInteger g, Yc;
				int Xc;
				String userCheck = "none";

				String username, password;

				// P
				BigInteger p = new BigInteger("197221152031991558322935568090317202983");

				// G
				g = BigInteger.valueOf(2);

				// Xc
				Xc = 233;
				System.out.println("-------------------Login Details-------------------");
				System.out.print("	Username :");
				username = conn.readLine().trim();

				System.out.print("	Password :");
				password = conn.readLine().trim();

				/*-------------------Message 1-------------------*/
				// ID
				ID = username;
				wr.println(ID);
				wr.flush();

				userCheck = rd.readLine().trim();
				System.out.println();
				System.out.println("-------------------Username Validation-------------------");
				if (userCheck.equals("Warn")) {
					System.out.println("	Username Mismatch");
					userCheck = "Warn";
				}

				if (!(userCheck.equals("Warn"))) {
					System.out.println("	Username Match");
					// Shared Key and convert it to Ascii
					myKey = password;
					for (int i = 0; i < myKey.length(); i++)
						Kc1 += Integer.toString((int) myKey.charAt(i));

					//KUc
					wr.println(Client_RSA.PUBLIC_KEY_FILE);
					
					// P
					wr.println(p.toString());

					// G
					wr.println(g.toString());

					// Yc = g^Xc.modp
					BigInteger big = g.pow(Xc);
					Yc = (big).mod(p);

					// Convert Ascii to long
					BigInteger Kc2_long = new BigInteger(Kc1);

					// Kc2 = E(KUs, Yc), here E is RSA encryption
					ObjectInputStream inputStream = null;
					inputStream = new ObjectInputStream(new FileInputStream(Server_RSA.PUBLIC_KEY_FILE));
					final PublicKey publicKey = (PublicKey) inputStream.readObject();
					final byte[] Kc2 = Server_RSA.encrypt(Yc.toString(), publicKey);
					
					DataOutputStream d1 = new DataOutputStream(st.getOutputStream());
					d1.write(Kc2);
					d1.flush();
					
					
					String Kc2_str = new String(Kc2);
					
					System.out.println();
					System.out.println("-------------------Message 1-------------------");
					System.out.println(" p = " + p.toString() + ", g = " + g.toString() + ", Xc = " + Xc
							+ ", E(KUs, g^Xc.modp) = " + Kc2_str);

					/*-------------------Message 2-------------------*/

					// Ks2 = E(KUc,g^Xsmodp))
					String Ks2_str = rd.readLine().trim();
					//BigInteger Ks2 = new BigInteger(Ks2_str);

					// Ks3 = g^Xs = D(KPc,Ks2)
					// Decrypt Kc2 using the private key.
					inputStream = new ObjectInputStream(new FileInputStream(Client_RSA.PRIVATE_KEY_FILE));
					final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
					final String Ks3_str = Server_RSA.decrypt(Ks2_str.getBytes(), privateKey);

					
					BigInteger Ks3 = new BigInteger(Ks3_str);


					// Kcs = g^(Xs*Xc).modp
					BigInteger Kcs = Ks3.pow(Xc).mod(p);

					// Pad Kcs to 128 bits
					String Aes_key = Kcs.toString();
					if (Aes_key.length() < 16) {
						int l = Aes_key.length();
						for (int i = 0; i < (16 - l); i++)
							Aes_key += "0";
					}
					if (Aes_key.length() > 16) {
						Aes_key = Aes_key.substring(0, 16);
					}

					// Decrypt Ns using Kcs as key
					String Kcs_Ns = rd.readLine().trim();
					String Ns = decrypt(Aes_key, Kcs_Ns);
					System.out.println();
					System.out.println("----------------DH Key / Nounce----------------");
					System.out.println("Kas(Aes_Key) = " + Kcs.toString() + ", Ns = " + Ns);
					System.out.println();
					System.out.println("-------------------Message 2-------------------");
					System.out.println(" E(Pass,g^Xs.modp) = " + Ks2_str + ", E(Kas, Ns) = " + Kcs_Ns);

					/*-------------------Message 3-------------------*/

					// Choose Na
					String Na = "9876543210987654";

					// BigInteger Ns_big = new BigInteger(Ns);
					// BigInteger Na_big = new BigInteger(Na);
					// BigInteger Ns_Na_big = Ns_big.xor(Na_big);
					String Ns_Na_str = Ns + Na;

					String Kcs_Ns_Na = encrypt(Aes_key, Ns_Na_str);
					wr.println(Kcs_Ns_Na);
					wr.flush();
					System.out.println();
					System.out.println("-------------------Message 3-------------------");
					System.out.println(" E(Kas, Ns||Na) = " + Kcs_Ns_Na);
					/*-------------------Message 4-------------------*/

					String Pass_check = rd.readLine().trim();
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
						String Kcs_Na = rd.readLine().trim();
						String Na_check = decrypt(Aes_key, Kcs_Na);
						System.out.println();
						System.out.println("-------------------Message 4-------------------");
						System.out.println(" E(Kas, Na) = " + Kcs_Na);
						if (Na.equals(Na_check)) {
							System.out.println();
							System.out.println("-------------------Password Validation-------------------");
							System.out.println("1. Na Value Match");
							System.out.println("2. Password Match");
							String filename = rd.readLine().trim();
							File abc = new File("my.txt");
							FileOutputStream f = new FileOutputStream(abc);

							String s = rd.readLine();
							System.out.println();
							System.out.println("-------------------Message 5-------------------");
							System.out.println("a. E(Kas, File)||H(file) : ");
							System.out.println(s);
							String Hash = s.substring(s.length() - 40, s.length());
							System.out.println();
							System.out.println("b. H(file) :" + Hash);
							s = s.substring(0, s.length() - 40);
							s = decrypt(Aes_key, s);
							System.out.println();
							System.out.println("c. D(Kas, E(Kas, File)) :");
							System.out.println(s);
							f.write(s.getBytes());
							f.flush();
							f.close();
							d1.close();
							System.out.println("-----------------------------------------------");
							System.out.println();
							System.out.println("File Transfer Complete");
							System.out.println();
							System.out.println("-------------------File Integrity Check -------------------");
							String Hash_check = mySHA("my.txt");
							if (Hash_check.equals(Hash)) {
								System.out.println();
								System.out.println("SHA-1 Hash matched");
								break;
							} else {
								System.out.println();
								System.out.println("SHA-1 Hash not matched");
							}

						}
					}
				}

			} while (true);
			wr.flush();
			st.close();

		} catch (

		IndexOutOfBoundsException err)

		{
			System.err.println("Please provide port number.");
		} catch (FileNotFoundException ef) {
			System.out.println("File Not found");
		} catch (

		Exception err)

		{
			System.err.println(err);
		}
	}

}