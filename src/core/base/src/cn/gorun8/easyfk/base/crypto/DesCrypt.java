/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.base.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.DESedeKeySpec;

import cn.gorun8.easyfk.base.util.Base64;
import cn.gorun8.easyfk.base.util.GeneralException;

/**
 * Utility class for doing DESded (3DES) Two-Way Encryption
 *
 */
public class DesCrypt {

    public static final String module = DesCrypt.class.getName();

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");

        // generate the DES3 key
        return keyGen.generateKey();
    }

    public static String encryptString(String rawKey, String data){
		try {
			SecretKey key = getDesKey(rawKey.getBytes());
			byte[] enc = encrypt(key, data.getBytes());
			String rel = new String(Base64.base64Encode(enc));
			return rel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }
    
    public static String decryptString(String rawKey, String data) {
    	try{
	    	SecretKey key = getDesKey(rawKey.getBytes());
	    	byte[] enc =  Base64.base64Decode(data.getBytes());
	    	enc = decrypt(key, enc);
	    	String rel = new String(enc);
	    	return rel;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return "";
    }	   
    
    public static byte[] encrypt(SecretKey key, byte[] bytes) throws GeneralException {
        Cipher cipher = DesCrypt.getCipher(key, Cipher.ENCRYPT_MODE);
        byte[] encBytes = null;
        try {
            encBytes = cipher.doFinal(bytes);
        } catch (IllegalStateException e) {
            throw new GeneralException(e);
        } catch (IllegalBlockSizeException e) {
            throw new GeneralException(e);
        } catch (BadPaddingException e) {
            throw new GeneralException(e);
        }
        return encBytes;
    }

    public static byte[] decrypt(SecretKey key, byte[] bytes) throws GeneralException {
        Cipher cipher = DesCrypt.getCipher(key, Cipher.DECRYPT_MODE);
        byte[] decBytes = null;
        try {
            decBytes = cipher.doFinal(bytes);
        } catch (IllegalStateException e) {
            throw new GeneralException(e);
        } catch (IllegalBlockSizeException e) {
            throw new GeneralException(e);
        } catch (BadPaddingException e) {
            throw new GeneralException(e);
        }
        return decBytes;
    }

    public static SecretKey getDesKey(byte[] rawKey) throws GeneralException {
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("DESede");
        } catch (NoSuchAlgorithmException e) {
            throw new GeneralException(e);
        }

        // load the raw key
        if (rawKey.length > 0) {
            DESedeKeySpec desedeSpec1 = null;
            try {
                desedeSpec1 = new DESedeKeySpec(rawKey);
            } catch (InvalidKeyException e) {
                throw new GeneralException(e);
            }

            // create the SecretKey Object
            SecretKey key = null;
            try {
                key = skf.generateSecret(desedeSpec1);
            } catch (InvalidKeySpecException e) {
                throw new GeneralException(e);
            }
            return key;
        } else {
            throw new GeneralException("Not a valid DESede key!");
        }
    }

    // return a cipher for a key - DESede/CBC/PKCS5Padding IV = 0
    protected static Cipher getCipher(SecretKey key, int mode) throws GeneralException {
        byte[] zeros = { 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec iv = new IvParameterSpec(zeros);

        // create the Cipher - DESede/CBC/NoPadding
        Cipher encCipher = null;
        try {
            encCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new GeneralException(e);
        } catch (NoSuchPaddingException e) {
            throw new GeneralException(e);
        }
        try {
            encCipher.init(mode, key, iv);
        } catch (InvalidKeyException e) {
            throw new GeneralException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new GeneralException(e);
        }
        return encCipher;
    }
}
