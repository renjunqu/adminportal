package cn.futuremove.adminportal.test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.springframework.security.crypto.codec.Base64;

import sun.misc.BASE64Decoder;


import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.security.InvalidKeyException;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException; 

import sun.misc.BASE64Encoder;
  

public class testRSA {
	
	public static void main(String [] args) throws NoSuchAlgorithmException, NoSuchProviderException{
		
		 KeyPairGenerator keyPairGen= null;  
	        try {  
	            keyPairGen= KeyPairGenerator.getInstance("RSA");  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        BASE64Encoder encoder = new BASE64Encoder();
	        keyPairGen.initialize(1024, new SecureRandom());  
	        KeyPair keyPair= keyPairGen.generateKeyPair();  
	       
	        RSAPrivateKey privateKey= (RSAPrivateKey) keyPair.getPrivate(); 
	        RSAPublicKey  publicKey= (RSAPublicKey) keyPair.getPublic(); 
	        System.out.println(((String)(encoder.encode(publicKey.getEncoded()))).length());
	        
	        
	        
	  
	    
	}

}
