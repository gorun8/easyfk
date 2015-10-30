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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Blowfish (Two-Way) Byte/String encryption
 *
 */
public class BlowFishCrypt {

    private SecretKeySpec secretKeySpec = null;

    /**
     * Creates a new BlowFishCrypt object.
     * @param secretKeySpec A SecretKeySpec object.
     */
    public BlowFishCrypt(SecretKeySpec secretKeySpec) {
        this.secretKeySpec = secretKeySpec;
    }

    /**
     * Creates a new BlowFishCrypt object.
     * @param key An encoded secret key
     */
    public BlowFishCrypt(byte[] key) {
        try {
            secretKeySpec = new SecretKeySpec(key, "Blowfish");
        } catch (Exception e) {}
    }

    /**
     * Creates a new BlowFishCrypt object.
     * @param keyFile A file object containing the secret key as a String object.
     */
    public BlowFishCrypt(File keyFile) {
        try {
            FileInputStream is = new FileInputStream(keyFile);
            ObjectInputStream os = new ObjectInputStream(is);
            String keyString = (String) os.readObject();

            is.close();

            byte[] keyBytes = keyString.getBytes();

            secretKeySpec = new SecretKeySpec(keyBytes, "Blowfish");
        } catch (Exception e) {}
    }

    /**
     * Encrypt the string with the secret key.
     * @param string The string to encrypt.
     */
    public byte[] encrypt(String string) {
        return encrypt(string.getBytes());
    }

    /**
     * Decrypt the string with the secret key.
     * @param string The string to decrypt.
     */
    public byte[] decrypt(String string) {
        return decrypt(string.getBytes());
    }

    /**
     * Encrypt the byte array with the secret key.
     * @param bytes The array of bytes to encrypt.
     */
    public byte[] encrypt(byte[] bytes) {
        byte[] resp = null;

        try {
            resp = crypt(bytes, Cipher.ENCRYPT_MODE);
        } catch (Exception e) {
            return null;
        }
        return resp;
    }

    /**
     * Decrypt the byte array with the secret key.
     * @param bytes The array of bytes to decrypt.
     */
    public byte[] decrypt(byte[] bytes) {
        byte[] resp = null;

        try {
            resp = crypt(bytes, Cipher.DECRYPT_MODE);
        } catch (Exception e) {
            return null;
        }
        return resp;
    }

    private byte[] crypt(byte[] bytes, int mode) throws Exception {
        if (secretKeySpec == null)
            throw new Exception("SecretKey cannot be null.");
        Cipher cipher = Cipher.getInstance("Blowfish");

        cipher.init(mode, secretKeySpec);
        return cipher.doFinal(bytes);
    }

    public static byte[] generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
        keyGen.init(448);

        SecretKey secretKey = keyGen.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        return keyBytes;
    }

    public static boolean testKey(byte[] key) {
        String testString = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstufwxyz";
        BlowFishCrypt c = new BlowFishCrypt(key);
        byte[] encryptedBytes = c.encrypt(testString);
        String encryptedMessage = new String(encryptedBytes);

        byte[] decryptedBytes = c.decrypt(encryptedMessage);
        String decryptedMessage = new String(decryptedBytes);

        if (testString.equals(decryptedMessage)) {
            return true;
        }

        return false;
    }

    public static void main(String args[]) throws Exception {
        if (args[0] == null) {
            args[0] = "ofbkey";
        }

        byte[] key = generateKey();
        if (testKey(key)) {
            FileOutputStream fos = new FileOutputStream(args[0]);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            String keyString = new String(key);
            os.writeObject(keyString);
            fos.close();
        }
    }
}
