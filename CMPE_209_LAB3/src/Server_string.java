import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Server_string {

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
			String filename = "client.txt";// args[1];
			ServerSocket s = new ServerSocket(port);
			System.out.println("Server");
			try (Socket incoming = s.accept()) {

				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
				
				PrintWriter out;
				out = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));

				int count = 0;

				do {
					String username, password, strn;
					String ID, myKey, Ks1 = "";
					int Xs = 97;
					/*-------------------Message 1-------------------*/
					// ID
					ID = in.readLine().trim();
					System.out.println();
					System.out.println("-------------------Username Validation-------------------");
					if (!(ID.equals("uday"))) {
						System.out.println("Username Mismatch");
						out.println("Warn");
						out.flush();
					} else {
						System.out.println("Username Match");
						out.println("Valid");
						out.flush();
					}

					if (ID.equals("uday")) {
						// Pass
						myKey = "Pass";
						for (int i = 0; i < myKey.length(); i++)
							Ks1 += Integer.toString((int) myKey.charAt(i));
						BigInteger Ks2_long = new BigInteger(Ks1);

						// KUc client public key
						String KUc = in.readLine().trim();

						// P
						BigInteger p = new BigInteger(in.readLine().trim());

						// G
						BigInteger g = new BigInteger(in.readLine().trim());

						// Kc2 = E(Pass,g^Xcmodp)
						String Kc2_str = in.readLine();
						
						//BigInteger Kc2 = new BigInteger(Kc2_str);
						byte[] Kc2_byte = Kc2_str.getBytes();
						
						System.out.println();
						System.out.println("-------------------Message 1-------------------");
						System.out.println(" p = " + p.toString() + ", g = " + g.toString() + ", Xs = " + Xs
								+ ", E(KUs, g^Xc.modp) = " + Kc2_str);

						// Kc3 = g^Xc = D(KPs,Kc2)
						// Decrypt Kc2 using the private key.
						ObjectInputStream inputStream = null;
						inputStream = new ObjectInputStream(new FileInputStream(Server_RSA.PRIVATE_KEY_FILE));
						final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
						final String Kc3_str = Server_RSA.decrypt(Kc2_byte, privateKey);
						
						BigInteger Kc3 = new BigInteger(Kc3_str);

						// Ys = g^Xs.modp
						BigInteger big = g.pow(Xs);
						BigInteger Ys = (big).mod(p);

						/*-------------------Message 2-------------------*/
						// Ks2 = E(KUc,g^Xs.modp)
						inputStream = new ObjectInputStream(new FileInputStream(Client_RSA.PUBLIC_KEY_FILE));
						final PublicKey publicKey = (PublicKey) inputStream.readObject();
						final byte[] Ks2 = Client_RSA.encrypt(Ys.toString(), publicKey);
						String Ks2_str = new String(Ks2);

						out.println(Ks2_str);
						out.flush();

						// Kcs = g^(Xc*Xs).modp
						BigInteger Kcs = Kc3.pow(Xs).mod(p);

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

						// System.out.println(Aes_key);

						// Choose Ns
						String Ns = "1234567890123456";

						// Encrypt Ns using Kcs as key
						String Kcs_Ns = encrypt(Aes_key, Ns);
						out.println(Kcs_Ns);
						out.flush();
						System.out.println();
						System.out.println("----------------DH Key / Nounce----------------");
						System.out.println("Kas = " + Kcs.toString() + ", Ns = " + Ns);
						System.out.println();
						System.out.println("-------------------Message 2-------------------");
						System.out.println(" E(Pass,g^Xs.modp) = " + Ks2_str + ", E(Kas, Ns) = " + Kcs_Ns);
						/*-------------------Message 3-------------------*/
						String Kas_Ns_Na = in.readLine().trim();
						System.out.println();
						System.out.println("-------------------Message 3-------------------");
						System.out.println(" E(Kas, Ns||Na) = " + Kas_Ns_Na);
						String Ns_Na = decrypt(Aes_key, Kas_Ns_Na);

						/*-------------------Message 4-------------------*/
						// BigInteger Ns_big = new BigInteger(Ns);
						// BigInteger Ns_Na_big = new BigInteger(Ns_Na);
						// BigInteger Na_big = Ns_big.xor(Ns_Na_big);
						String Ns_check = Ns_Na.substring(0, 16);
						System.out.println();
						System.out.println("-------------------Password Validation-------------------");
						if ((count >= 2) & (!(Ns.equals(Ns_check)))) {
							System.out.println("Access Denied");
							out.println("Deny");
							out.flush();
							break;
						}

						else if (!(Ns.equals(Ns_check))) {
							System.out.println("1. Na value Mismatch");
							System.out.println("2. Password Mismatch");
							out.println("Warn");
							out.flush();
							count++;
						} else {
							System.out.println("1. Na Value Match");
							System.out.println("2. Password Match");
							out.println("Valid");
							out.flush();

							String Na = Ns_Na.substring(16);
							String Kcs_Na = encrypt(Aes_key, Na);
							out.println(Kcs_Na);
							out.flush();
							System.out.println();
							System.out.println("-------------------Message 4-------------------");
							System.out.println(" E(Kas, Na) = " + Kcs_Na);
							out.println(filename);
							out.flush();
							File my_file = new File(filename);
							FileInputStream f = new FileInputStream(my_file);
							DataOutputStream d = new DataOutputStream(incoming.getOutputStream());
							byte[] buf = new byte[(int) my_file.length()];
							f.read(buf);
							String v = new String(buf);
							System.out.println();
							System.out.println("-------------------Message 5-------------------");
							System.out.println("a. File contents :");
							System.out.println(v);
							v = encrypt(Aes_key, v);
							System.out.println();
							System.out.println("b. E(Kas, File) : ");
							System.out.println(v);
							String hash_file = mySHA(filename);
							System.out.println();
							System.out.println("c. H(file) : " + hash_file);

							// E(Kas, File) || H(file)
							v = v + hash_file;
							buf = v.getBytes();
							d.write(buf);
							f.close();
							d.close();
							System.out.println("-----------------------------------------------");
							System.out.println("File Transfer Complete !");
							System.out.println("-----------------------------------------------");
							out.flush();
							break;
						}
					}
					out.flush();
				} while (true);
				s.close();
			}
		} catch (IndexOutOfBoundsException e1) {
			System.err.println("Please provide port number & filename");
		} catch (FileNotFoundException ef) {
			System.out.println("File Not found");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}