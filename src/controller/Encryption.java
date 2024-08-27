package controller;

import java.awt.RenderingHints.Key;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	public Encryption() {
		
	}
	
	public byte[] encryptKey(SecretKeySpec aesKey, String str) {
		
        
		byte[] encrypted = null;
		
        try {
			
        	Cipher cipher = Cipher.getInstance("AES");
        	// encrypt the text
        	cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(str.getBytes());
		    
        
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return encrypted;
	}
	
	public Cipher getCipher() {
		
		Cipher cipher = null;
		
		try {
		cipher = Cipher.getInstance("AES");
		  
        } catch(Exception e) {
            e.printStackTrace();
        }
		
		return cipher;
		
	}
	
	public SecretKeySpec getSecretKeySpec() {
		
		SecretKeySpec aesKey = null;
		
		try {
	           
			 //Create a 128 bit key
			 String key = "Bar12345Bar12345"; 
	         
	         // Create key and cipher
		     aesKey  = new SecretKeySpec(key.getBytes(), "AES");
	        
	        
	        } catch(Exception e) {
	            e.printStackTrace();
	   }
       
       return aesKey;
	}
	
	public String decrypt(SecretKeySpec aesKey, byte[] encrypted) {
		
		String decrypted = null;
		
		try 
	        {
		
			// decrypt the text
			Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        decrypted = new String(cipher.doFinal(encrypted));
	       
	        
	        } catch(Exception e) {
	            e.printStackTrace();
	    }
		
		return decrypted;
		
	
	}
}
