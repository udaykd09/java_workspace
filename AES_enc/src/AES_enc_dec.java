import java.awt.print.Printable;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES_enc_dec {
    public static String encrypt(String key, String value) {
        try {
        	//IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            return "-1";
        }
        
    }

    public static String decrypt(String key, String encrypted) {
        try {
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
        	return "-1";
            //ex.printStackTrace();
        }

    }

    public static byte[] mySHA(String x) {
    	try{
    	 java.security.MessageDigest digest = null;
    	 digest = java.security.MessageDigest.getInstance("SHA-1");
    	 digest.reset();
    	 digest.update(x.getBytes("UTF-8"));
    	 digest.update(x.getBytes("UTF-8"));
    	 return digest.digest();
    	 }
    	catch(Exception e){
    		System.err.println(e);
    		String s = "-1";
    		return s.getBytes();
    	}
    }
    
    public static void main(String[] args) throws IOException {
        String key = "1600000000000000"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        String key1 = "Bar12345Bar123s5"; // 128 bit key
       //String x = encrypt(key, "GDTKZeYwA9b2oxwWEkI+Yxg0ymXmMAPW9qMcFhJCPmMYNMpl5jAD1vajHBYSQj5jiKmszfv/7NoMsMGhhP75SA==                                                     ");
//        String x = encrypt(key, "2198yenitewotudgdjnf gvq349857vnt89 tihuv349n58nv343984y5nev nerihu                                                     ");
//        String y = decrypt(key1, x);   
//        
//       System.out.println(x);
//       System.out.println(y);
        String fss = "client.txt";
        FileInputStream f = new FileInputStream(fss);
		byte[] buf = new byte[(int) fss.length()];
		f.read(buf);
		String v = new String(buf);
       System.out.println(mySHA(v));
f.close();
        
//       String Ns_Na = "1234567890123456qwerty"; 
//        String Ns_check = Ns_Na.substring(0,16);
//        System.out.println(Ns_check);
    }
}